package com.spring.util;

import java.io.IOException;


import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.spring.dto.DocumentDTO;
import com.spring.exception.UploadFailedException;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectTaggingRequest;
import software.amazon.awssdk.services.s3.model.GetObjectTaggingResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.Tag;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

import software.amazon.awssdk.services.s3.model.S3Exception;

@Component
@RequiredArgsConstructor
public class S3Util {
	
   
//   public final String BUCKET = "test-busan";
   
   private AmazonS3 s3Client;
   

	private final AmazonS3Client amazonS3Client;
	
	@Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;
    
    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

       s3Client = (AmazonS3Client) AmazonS3ClientBuilder.standard()
   			.withRegion(region)
   			.withCredentials(new AWSStaticCredentialsProvider(credentials))
   			.build();
    }
   
   public void uploadFile(String fileName, InputStream inputStream) throws S3Exception, AwsServiceException, SdkClientException, IOException {
//	   System.out.println(bucket);
//      S3Client client = S3Client.builder().build();
//      PutObjectRequest request = PutObjectRequest.builder()
//                                       .bucket(bucket)
//                                       .key(fileName)
//                                       .build();
      s3Client.putObject(bucket, fileName, inputStream, null);
//	   s3Client.putObject(new PutObjectRequest(bucket,fileName,inputStream,null).withCannedAcl(CannedAccessControlList.PublicRead));
//	   s3Client.putObject(new PutObjectRequest(bucket, fileName, input, null)
//               .withCannedAcl(CannedAccessControlList.PublicRead));
//      client.putObject(request, RequestBody.fromInputStream(inputStream,inputStream.available()));
   
//      S3Waiter waiter = client.waiter();
//      HeadObjectRequest waitRequest = HeadObjectRequest.builder()
//            .bucket(bucket).key(fileName).build();
//      WaiterResponse<HeadObjectResponse> waiterResponse = waiter.waitUntilObjectExists(waitRequest);
//      
//      waiterResponse.matched().response().ifPresent(x -> {
//         System.out.println("The file " + fileName + " is now ready");
//      });

   }

   // s3파일 삭제
   public void deleteFile(String fileName) {
//      S3Client client = S3Client.builder().build();
//      
//      DeleteObjectRequest request = DeleteObjectRequest.builder()
//                                           .bucket(bucket)
//                                           .key(fileName)
//                                           .build();
//            
//      client.deleteObject(request);
	   
      boolean isExistObject = amazonS3Client.doesObjectExist(bucket, fileName);
      if (isExistObject == true) {
    	  amazonS3Client.deleteObject(bucket, fileName);
      }
      System.out.println(getFileUrl(fileName));
   }
   
   public String getFileUrl(String fileName) {
	   return amazonS3Client.getUrl(bucket, fileName).toString();
//	      AmazonS3Client s3Client = (AmazonS3Client) AmazonS3Client.builder().build();
//	      return s3Client.getResourceUrl(bucket,fileName);
	   }
   
   // S3 파일 업로드(문서함)
   public String S3Upload(MultipartFile multipart, String filename) throws UploadFailedException {   
//      String originalFileName =  multipart.getOriginalFilename();
//      String filename = "document/"+UUID.randomUUID().toString() + "_" + originalFileName;
      
      try {
         uploadFile(filename, multipart.getInputStream());
//         documentDTO.setOriginalName(originalFileName);
//         documentDTO.setFileName(filename);
//         documentDTO.setFileCategory(multipart.getContentType().substring(multipart.getContentType().indexOf("/")+1));
//         documentDTO.setFileSize(Math.round((((double)multipart.getSize()/1024))*100)/100.0);
//         documentDTO.setFilePath(getFileUrl(filename));
//         return documentDTO;
         return getFileUrl(filename);
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