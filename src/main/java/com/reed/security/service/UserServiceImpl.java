package com.reed.security.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.reed.security.domain.User;
import com.reed.security.domain.UserExample;
import com.reed.security.domain.UserGroup;
import com.reed.security.domain.UserGroupExample;
import com.reed.security.domain.UserExample.Criteria;
import com.reed.security.mapper.UserGroupMapper;
import com.reed.security.mapper.UserMapper;
import com.reed.security.utils.CommonUtil;

public class UserServiceImpl implements UserService {
	private static Log logger = LogFactory.getLog(UserService.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserGroupMapper userGroupMapper;

	@Override
	@Cacheable(value = CommonUtil.CACHEUSER, condition = "#id != null", unless = "#result == null")
	public User findById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	@CacheEvict(value = CommonUtil.CACHEUSER, condition = "#t != null", allEntries = true, beforeInvocation = true)
	public int save(User t) {
		logger.info("===>save user");
		int r = userMapper.insert(t);
		return r;
	}

	@Override
	@CacheEvict(value = CommonUtil.CACHEUSER, condition = "#t != null", allEntries = true, beforeInvocation = true)
	public int update(User t) {
		int r = userMapper.updateByPrimaryKeySelective(t);
		return r;
	}

	/**
	 * 级联删除user_group
	 */
	@Override
	@Caching(evict = {
			@CacheEvict(value = CommonUtil.CACHEUSER, condition = "#id != null", key = "'UserServiceImpl_findById_' + #id + '_'", beforeInvocation = true),
			@CacheEvict(value = CommonUtil.CACHEUSER, condition = "#id != null", allEntries = true, beforeInvocation = true),
			@CacheEvict(value = CommonUtil.CACHEUSERGROUP, condition = "#id != null", allEntries = true, beforeInvocation = true) })
	public int deleteById(Integer id) {
		int r = 0;
		if (id != null) {
			UserGroupExample ug = new UserGroupExample();
			ug.createCriteria().andUidEqualTo(id);
			userGroupMapper.deleteByExample(ug);
			r = userMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	@Cacheable(value = CommonUtil.CACHEUSER, condition = "#page != null")
	public List<User> findByPage(Page page, String order) {
		List<User> r = null;
		UserExample u = new UserExample();
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
			if (order != null) {
				u.setOrderByClause(order);
			}
			r = userMapper.selectByExample(u);
		} else {
			r = userMapper.selectByExample(u);
		}

		return r;

	}

	@Override
	@Cacheable(value = CommonUtil.CACHEUSER)
	public List<User> findByConditonAnd(String account, String name,
			String email, Page page, String order) {
		List<User> r = null;
		UserExample u = new UserExample();
		Criteria c = u.createCriteria();
		if (account != null) {
			c.andAccountEqualTo(account);
		}
		if (name != null) {
			c.andNameEqualTo(name);
		}
		if (email != null) {
			c.andEmailEqualTo(email);
		}
		if (order != null) {
			u.setOrderByClause(order);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = userMapper.selectByExample(u);
		return r;
	}

	@Cacheable(value = CommonUtil.CACHEUSER)
	public List<User> findByConditonOr(String account, String name,
			String email, Page page, String order) {
		List<User> r = null;
		UserExample u = new UserExample();
		// Criteria c = u.or();
		if (account != null) {
			u.or().andAccountEqualTo(account);
		}
		if (name != null) {
			u.or().andNameEqualTo(name);
		}
		if (email != null) {
			u.or().andEmailEqualTo(email);
		}
		if (order != null) {
			u.setOrderByClause(order);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = userMapper.selectByExample(u);
		return r;
	}

	@Override
	@Cacheable(value = CommonUtil.CACHEUSER, condition = "#account != null", unless = "#result == null")
	public User findByAccount(String account) {
		User u = null;
		List<User> list = findByConditonAnd(account, null, null, null, null);
		if (list != null && list.size() > 0) {
			u = list.get(0);
		}
		return u;
	}

	@Override
	public int findCount(UserExample u) {
		int r = 0;
		// UserExample u = new UserExample();
		r = userMapper.countByExample(u);

		return r;
	}

}
