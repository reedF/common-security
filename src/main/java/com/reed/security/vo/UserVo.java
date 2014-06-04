package com.reed.security.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.generator.plugin.Page;

import com.reed.security.domain.Group;
import com.reed.security.domain.User;

public class UserVo {

	private User user;

	private List<Group> groups = new ArrayList<Group>();

	private Set<Integer> groupIds;

	private Page page;

	// private Map<Integer, String> groups;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public Set<Integer> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Set<Integer> groupIds) {
		this.groupIds = groupIds;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	// public Map<Integer, String> getGroups() {
	// return groups;
	// }
	//
	// public void setGroups(Map<Integer, String> groups) {
	// this.groups = groups;
	// }

}
