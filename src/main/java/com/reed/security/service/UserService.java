package com.reed.security.service;

import java.util.List;

import org.mybatis.generator.plugin.Page;

import com.reed.security.domain.User;
import com.reed.security.domain.UserExample;

public interface UserService extends BaseService<User> {

	// public User findById(Integer id);
	//
	// public int saveUser(User user);
	//
	// public int updateUser(User user);
	//
	// public int deleteById(Integer id);
	//
	// public List<User> findByPage(Page page ,String order);

	public List<User> findByConditonAnd(String account, String name,
			String email, Page page, String order);

	public List<User> findByConditonOr(String account, String name,
			String email, Page page, String order);
	
	public User findByAccount(String account);
	
	
	public int findCount(UserExample u);
}
