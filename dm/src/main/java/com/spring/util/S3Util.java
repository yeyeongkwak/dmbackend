package com.spring.util;

import java.io.IOException;


import java.io.InputStream;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;

import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

public class S3Util {
   

   public static final String BUCKET = "test-busan";
   public static final String DOWNLOAD = "https://" + BUCKET + ".s3.ap-northeast-2.amazonaws.com/";

   
   public static void uploadFile(String fileName, InputStream inputStream) throws S3Exception, AwsServiceException, SdkClientException, IOException {
      S3Client client = S3Client.builder().build();
      
      PutObjectRequest request = PutObjectRequest.builder()
                                       .bucket(BUCKET)
                                       .key(fileName)
                                       .build();
      
      client.putObject(request, RequestBody.fromInputStream(inputStream,inputStream.available()));
   
      S3Waiter waiter = client.waiter();
      HeadObjectRequest waitRequest = HeadObjectRequest.builder()
            .bucket(BUCKET).key(fileName).build();
      WaiterResponse<HeadObjectResponse> waiterResponse = waiter.waitUntilObjectExists(waitRequest);
      
      waiterResponse.matched().response().ifPresent(x -> {
         System.out.println("The file " + fileName + " is now ready");
      });
   }

   // s3파일 삭제
   public static void deleteFile(String fileName) {
      S3Client client = S3Client.builder().build();
      
      DeleteObjectRequest request = DeleteObjectRequest.builder()
                                           .bucket(BUCKET)
                                           .key(fileName)
                                           .build();
            
      client.deleteObject(request);
   }

}