package com.spring.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceUserId implements Serializable{
	private Long workspaceNo;
	private Long userNo;
}
