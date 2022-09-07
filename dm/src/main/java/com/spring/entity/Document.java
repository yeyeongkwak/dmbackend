package com.spring.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.spring.dto.DocumentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Document {
   
   @Id 
   @Column(name = "document_no")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long documentNo; 
   
   @ManyToOne
   @JoinColumn(name = "user_no")
   private User user;
   
   @CreatedDate
   @Column(name = "register_date")
   private LocalDateTime registerDate;

   @LastModifiedDate
   @Column(name = "modify_date")
   private LocalDateTime modifyDate;
   
   @Size(max = 200)
   private String content;
   
   @Column(name = "file_name")
   @Size(max = 255)
   private String fileName;
   
   // 타입 바꿔야함!!!!!!!!!!!
   @Column(name = "file_path")
   @Size(max = 255)
   private String filePath;
   
   @Column(name = "original_name")
   @Size(max = 50)
   private String originalName;
   
   @Column(name= "file_size")
   private Double fileSize;
   
   @Column(name= "file_category")
   private String fileCategory;
   
   public DocumentDTO toDTO(Document document) {
      User user = document.getUser();
      DocumentDTO documentDTO = DocumentDTO.builder().documentNo(document.getDocumentNo())
                                          .user(user.toDTO(user))
                                          .registerDate(document.getRegisterDate())
                                          .modifyDate(document.getModifyDate())
                                          .content(document.getContent())
                                          .fileName(document.getFileName())
                                          .filePath(document.getFilePath())
                                          .originalName(document.getOriginalName())
                                          .fileSize(document.getFileSize())
                                          .fileCategory(document.getFileCategory())
                                          .build();
      return documentDTO;
   }
}