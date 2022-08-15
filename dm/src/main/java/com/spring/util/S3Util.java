package com.spring.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.spring.dto.DocumentDTO;
import com.spring.exception.UploadFailedException;

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
   
   public static String getFileUrl(String fileName) {
	      AmazonS3Client s3Client = (AmazonS3Client) AmazonS3Client.builder().build();
	      return s3Client.getResourceUrl(BUCKET,fileName);
	   }
   
   // S3 파일 업로드(문서함)
   public static DocumentDTO S3Upload(MultipartFile multipart, DocumentDTO documentDTO) throws UploadFailedException {   
      String originalFileName =  multipart.getOriginalFilename();
      String filename = "document/"+UUID.randomUUID().toString() + "_" + originalFileName;
      
      try {
         S3Util.uploadFile(filename, multipart.getInputStream());
         documentDTO.setOriginalName(originalFileName);
         documentDTO.setFileName(filename);
         documentDTO.setFilePath(S3Util.getFileUrl(filename));
         return documentDTO;
      } catch (Exception e) {
          throw new UploadFailedException(e.getMessage());
      }
   };
   
//   public static void readFile(String fileName) {
//	   AmazonS3Client s3Client = (AmazonS3Client) AmazonS3Client.builder().build();
//	   S3Object file =s3Client.getObject(BUCKET,fileName);
//	   S3ObjectInputStream s3is = file.getObjectContent();
//	   System.out.println(s3is);
	   
	   
//	   S3Object file = 
	   
//   }
   // S3 임시 저장 파일 업로드(워크스페이스)
//   public static WorkspaceDTO S3TempUpload()
   

}