package com.reed.security.vo;

import java.util.Set;

import com.reed.security.domain.Group;

public class GroupVo {

	private Group group;
	
	private Set<Integer> roleIds;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Set<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	
	
	
}
