package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.User;
import com.spring.entity.WorkspaceUser;

@Repository
public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, Long>{

	public List<WorkspaceUser> findAllByUserNoUserNo(Long userNo);
	
//	@Query(value = "DELETE FROM workspace_user WHERE user_no = :userNo AND workspace_no = :workspaceNo",nativeQuery = true)
//	public void deleteUserWorkspace(Long userNo,Long workspaceNo);
	
	public void deleteByUserNoUserNoAndWorkspaceNoWorkspaceNo(Long userNo,Long workspaceNo);
	
//	public List<WorkspaceUser> findAllByWorkspaceNoWorkspaceNo(Long workspaceNo);
	
	@Query(value = "SELECT user_no,name,dept_no FROM user WHERE user_no in(SELECT user_no FROM workspace_user WHERE workspace_no = :workspaceNo)",nativeQuery = true)
	public List<String> findUserByWorkspaceUser(Long workspaceNo);
	
}
