package com.reed.security.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.reed.security.domain.Role;
import com.reed.security.domain.RoleExample;
import com.reed.security.domain.RoleGroup;
import com.reed.security.domain.RoleGroupExample;
import com.reed.security.domain.RoleResourceExample;
import com.reed.security.domain.User;
import com.reed.security.domain.UserExample;
import com.reed.security.mapper.RoleGroupMapper;
import com.reed.security.mapper.RoleMapper;
import com.reed.security.mapper.RoleResourceMapper;

public class RoleServiceImpl implements RoleService {
	private static Log logger = LogFactory.getLog(RoleService.class);

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private RoleGroupMapper roleGroupMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public Role findById(Integer id) {

		return roleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(Role role) {

		return roleMapper.insert(role);
	}

	@Override
	public int update(Role role) {

		return roleMapper.updateByPrimaryKeySelective(role);
	}

	/**
	 * 级联删除role_group，role_resource
	 */
	@Override
	public int deleteById(Integer id) {
		int r = 0;
		if (id != null) {
			RoleGroupExample rg = new RoleGroupExample();
			rg.createCriteria().andRidEqualTo(id);
			roleGroupMapper.deleteByExample(rg);
			RoleResourceExample rr = new RoleResourceExample();
			rr.createCriteria().andRidEqualTo(id);
			roleResourceMapper.deleteByExample(rr);
			r = roleMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	public List<Role> findByPage(Page page, String order) {
		List<Role> r = null;
		RoleExample u = new RoleExample();
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
			if (order != null) {
				u.setOrderByClause(order);
			}
			r = roleMapper.selectByExample(u);
		} else {
			r = roleMapper.selectByExample(u);
		}

		return r;
	}

}
