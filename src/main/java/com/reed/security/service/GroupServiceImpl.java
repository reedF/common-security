package com.reed.security.service;

import java.util.List;

import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;
import com.reed.security.domain.Group;
import com.reed.security.domain.GroupExample;
import com.reed.security.domain.Model;
import com.reed.security.domain.RoleGroupExample;
import com.reed.security.mapper.GroupMapper;
import com.reed.security.mapper.RoleGroupMapper;

public class GroupServiceImpl implements GroupService{
	@Autowired
	private GroupMapper groupMapper ;
	@Autowired
	private RoleGroupMapper roleGroupMapper;

	@Override
	public Group findById(Integer id) {
		
		return groupMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(Group t) {
		
		return groupMapper.insert(t);
	}

	@Override
	public int update(Group t) {
		return groupMapper.updateByPrimaryKeySelective(t);
	}

	/**
	 * 级联删除role_group
	 */
	@Override
	public int deleteById(Integer id) {
		int r =0;
		if (id != null){
			RoleGroupExample rg = new RoleGroupExample();
			rg.createCriteria().andGidEqualTo(id);
			roleGroupMapper.deleteByExample(rg);
			r = groupMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	public List<Group> findByPage(Page page, String order) {
		List<Group> r = null;
		GroupExample u = new GroupExample();
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
			if (order != null) {
				u.setOrderByClause(order);
			}
			r = groupMapper.selectByExample(u);
		} else {
			r = groupMapper.selectByExample(u);
		}

		return r;
	}
}
