package com.reed.security.service;

import java.util.List;

import org.mybatis.generator.plugin.Page;

public interface BaseService<T>{

	public T findById(Integer id);
	
	public int save(T t);
	
	public int update(T t);
	
	public int deleteById(Integer id);
	
	public List<T> findByPage(Page page ,String order);
	

}
