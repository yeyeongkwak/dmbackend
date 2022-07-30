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

import com.spring.dto.WorkspaceDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Workspace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workspace_no")
	private Long workspaceNo;
	
	@ManyToOne
	@JoinColumn(name="master")
	private User master;
	
	@Size(max = 50)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@CreatedDate
	private LocalDateTime registerDate;
	
	@LastModifiedDate
	private LocalDateTime modifyDate;
	
	public WorkspaceDTO toDTO(Workspace workspace) {
		WorkspaceDTO workspaceDTO = WorkspaceDTO.builder()
									.workspaceNo(workspace.getWorkspaceNo())
									.master(workspace.getMaster().toDTO(workspace.getMaster()))
									.title(workspace.getTitle())
									.content(workspace.getContent())
									.registerDate(workspace.getRegisterDate())
									.modifyDate(workspace.getModifyDate())
									.build();
		return workspaceDTO;
	}
	
}
