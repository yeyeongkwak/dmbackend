package com.spring.dto;

import com.spring.entity.Document;
import com.spring.entity.DocumentUser;
import com.spring.entity.User;
import com.spring.model.Authority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentUserDTO {

	private UserDTO userNo;
	
	private DocumentDTO documentNo;
	
	private Integer important;
	
	private Integer recycleBin;
	
	private Authority authority;
	
	public DocumentUser toEntity(DocumentUserDTO dto) {
		UserDTO user = dto.getUserNo();
		DocumentDTO document = dto.getDocumentNo();
		DocumentUser documentUser = DocumentUser.builder().userNo(user != null ? user.toEntity(user) : null)
											  .documentNo(document != null ? document.toEntity(document) : null)
											  .important(dto.getImportant())
											  .recycleBin(dto.getRecycleBin())
											  .authority(dto.getAuthority())
											  .build();
		return documentUser;										  
	}
	
	public DocumentUserDTO(DocumentUserDTO oldDTO, DocumentUserDTO newDTO) {
		documentNo = oldDTO.getDocumentNo();
		userNo = oldDTO.getUserNo();
		if(newDTO.getImportant() != null) {
			important = newDTO.getImportant();
		}else {
			important = oldDTO.getImportant();
		}
		if(newDTO.getRecycleBin() != null) {
			recycleBin = newDTO.getRecycleBin();
		}else {
			recycleBin = oldDTO.getRecycleBin();
		}
		if(newDTO.getAuthority() != null) {
			authority = newDTO.getAuthority();
		}else {
			authority = oldDTO.getAuthority();
		}
	}
}
