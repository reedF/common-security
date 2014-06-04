package com.reed.security.service;

import java.util.List;

import org.mybatis.generator.plugin.Page;

import com.reed.security.domain.Resource;
import com.reed.security.domain.Role;
import com.reed.security.domain.RoleGroup;
import com.reed.security.domain.RoleResource;
import com.reed.security.domain.UserGroup;

public interface SecurityService {

	/**
	 * 保存user_group
	 * 
	 * @param userId user ID
	 * @param groupId group ID
	 * @return 0, failes; 1, successed
	 */
	 int saveUserGroup(Integer userId, Integer groupId);

	/**
	 * 保存role_resource
	 * 
	 * @param roleId
	 * @param rsId
	 * @return
	 */
	public int saveRoleResource(Integer roleId, Integer rsId);

	/**
	 * 保存role_group
	 * 
	 * @param roleId
	 * @param groupId
	 * @return
	 */
	public int saveRoleGroup(Integer roleId, Integer groupId);

	/**
	 * 根据主键删除user_group
	 * 
	 * @param id
	 * @return
	 */
	public int deleteUserGroupById(Integer id);

	/**
	 * 根据组id和用户ID删除user_group
	 * 
	 * @param groupId
	 * @param userId
	 * @return
	 */
	public int deleteUserGroupByGIdAndUId(Integer groupId, Integer userId);

	/**
	 * 根据用户ID删除user_group
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteUserGroupByUId(Integer userId);

	/**
	 * 根据角色ID删除role_group
	 * 
	 * @param groupId
	 * @return
	 */
	public int deleteRoleGroupByRId(Integer roleId);

	/**
	 * 根据组id删除role_group
	 * 
	 * @param groupId
	 * @return
	 */
	public int deleteRoleGroupByGId(Integer groupId);

	/**
	 * 根据角色ID，删除role_resource
	 * 
	 * @param roleId
	 * @return
	 */
	public int deleteRoleResourceByRId(Integer roleId);

	/**
	 * 根据组id删除User_group
	 * 
	 * @param groupId
	 * @return
	 */
	public int deleteUserGroupByGId(Integer groupId);

	/**
	 * 根据主键删除role_resource
	 * 
	 * @param id
	 * @return
	 */
	public int deleteRoleResourceById(Integer id);

	/**
	 * 根据主键删除role_group
	 * 
	 * @param id
	 * @return
	 */
	public int deleteRoleGroupById(Integer id);

	/**
	 * 根据userid或groupId查询UserGroup
	 * 
	 * @param userId
	 * @param group
	 * @param page
	 * @return
	 */
	public List<UserGroup> findUserGroupByUserOrGroup(Integer userId,
			Integer group, Page page);

	/**
	 * 根据roleId和resourceId查询RoleResource
	 * 
	 * @param roleId
	 * @param rsId
	 * @param page
	 * @return
	 */
	public List<RoleResource> findRoleResourceByRoleOrRs(Integer roleId,
			Integer rsId, Page page);

	/**
	 * 根据userid或groupId查询RoleGroup
	 * 
	 * @param roleId
	 * @param groupId
	 * @param page
	 * @return
	 */
	public List<RoleGroup> findRoleGroupByRoleOrGroup(Integer roleId,
			Integer groupId, Page page);

	/**
	 * 根据roleId查询Resource
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Resource> findResourceByRole(Integer roleId);

	/**
	 * 根据modelId查询Resource
	 * 
	 * @param modelId
	 * @return
	 */
	public List<Resource> findResourceByModelId(Integer modelId);

	/**
	 * 
	 * @param userId
	 * @param group
	 * @param page
	 * @return
	 */
	public List<UserGroup> findUserGroupByUserAndGroup(Integer userId,
			Integer group, Page page);

	/**
	 * 
	 * @param roleId
	 * @param rsId
	 * @param page
	 * @return
	 */
	public List<RoleResource> findRoleResourceByRoleAndRs(Integer roleId,
			Integer rsId, Page page);

	/**
	 * 
	 * @param roleId
	 * @param groupId
	 * @param page
	 * @return
	 */
	public List<RoleGroup> findRoleGroupByRoleAndGroup(Integer roleId,
			Integer groupId, Page page);

	/**
	 * 根据userId或groupId查询UserGroup总数
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public int findCountUserGroupByUserOrGroup(Integer userId, Integer groupId);

}
