package com.spring.dto;


import java.time.LocalDateTime;
import java.util.List;

import com.spring.entity.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO {
    
   private Long documentNo; 

   private UserDTO user;
   
   private LocalDateTime registerDate;

   private LocalDateTime modifyDate;
   
   private String content;
   
   private String fileName;
   
   private String filePath;
   
   private String originalName;
   
   private Double fileSize;
//   private List<UserDTO> userList;
   
   public Document toEntity(DocumentDTO dto) {
      UserDTO user = dto.getUser();
      Document document = Document.builder().documentNo(dto.getDocumentNo())
                                   .user(user != null ? user.toEntity(user) : null)
                                   .registerDate(dto.getRegisterDate())
                                   .modifyDate(dto.getModifyDate())
                                   .content(dto.getContent())
                                   .fileName(dto.getFileName())
                                   .filePath(dto.getFilePath())
                                   .originalName(dto.getOriginalName())
                                   .fileSize(dto.getFileSize())
                                   .build();
      return document;                                
   }
   
   public DocumentDTO(DocumentDTO orginalDTO, DocumentDTO newDTO) {
      documentNo = orginalDTO.getDocumentNo();
      user = orginalDTO.getUser();
      registerDate = orginalDTO.getRegisterDate();
      fileSize = orginalDTO.getFileSize();
      if(newDTO.getContent() != null) {
         content = newDTO.getContent();
      }else {
         content = orginalDTO.getContent();
      }
      if(newDTO.getFileName() != null) {
         fileName = newDTO.getFileName();
      }else {
         fileName = orginalDTO.getFileName();
      }
      if(newDTO.getFilePath() != null) {
         filePath = newDTO.getFilePath();
      }else {
         filePath = orginalDTO.getFilePath();
      }
      if(newDTO.getOriginalName() != null) {
         originalName = newDTO.getOriginalName();
      }else {
         originalName = orginalDTO.getOriginalName();
      }
   }
}