package com.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@ToString
@IdClass(WorkspaceUserId.class)
public class WorkspaceUser{
	
	@Id
	@ManyToOne
	@JoinColumn(name = "workspace_no")
	private Workspace workspaceNo;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_no")
	private User userNo;
}
