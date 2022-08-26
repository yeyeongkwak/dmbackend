package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Workspace;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long>{
	public Workspace getWorkspaceByWorkspaceNo(Long workspaceNo);
}
