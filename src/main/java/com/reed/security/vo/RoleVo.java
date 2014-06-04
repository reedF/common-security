package com.reed.security.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.reed.security.domain.Group;
import com.reed.security.domain.Resource;
import com.reed.security.domain.Role;

public class RoleVo {

	private Role role;

	private List<Group> groups = new ArrayList<Group>();

	private List<Resource> res;

	private Set<Integer> groupIds;

	private Set<Integer> resIds;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Resource> getRes() {
		return res;
	}

	public void setRes(List<Resource> res) {
		this.res = res;
	}

	public Set<Integer> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Set<Integer> groupIds) {
		this.groupIds = groupIds;
	}

	public Set<Integer> getResIds() {
		return resIds;
	}

	public void setResIds(Set<Integer> resIds) {
		this.resIds = resIds;
	}

}
