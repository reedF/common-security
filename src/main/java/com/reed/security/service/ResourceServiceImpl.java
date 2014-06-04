package com.reed.security.service;

import java.util.List;

import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.reed.security.domain.Model;
import com.reed.security.domain.Resource;
import com.reed.security.domain.ResourceExample;
import com.reed.security.domain.Role;
import com.reed.security.domain.RoleExample;
import com.reed.security.domain.RoleResourceExample;
import com.reed.security.mapper.ModelMapper;
import com.reed.security.mapper.ResourceMapper;
import com.reed.security.mapper.RoleResourceMapper;
import com.reed.security.utils.CommonUtil;

public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Override
	public Resource findById(Integer id) {

		return resourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(Resource t) {
		int r = 0;
		if (t != null) {
			Model m = modelMapper.selectByPrimaryKey(t.getModelid());
			if (m != null) {
				if (m.getUrl().endsWith(CommonUtil.UrlPer)) {
					t.setFullurl(m.getUrl() + t.getUrl());
				} else {
					t.setFullurl(m.getUrl() + CommonUtil.UrlPer + t.getUrl());
				}
				r = resourceMapper.insert(t);
			}
		}

		return r;
	}

	@Override
	public int update(Resource t) {

		return resourceMapper.updateByPrimaryKeySelective(t);
	}

	/**
	 * 级联删除role_resource
	 */
	@Override
	public int deleteById(Integer id) {
		int r = 0;
		if (id != null) {
			RoleResourceExample rr = new RoleResourceExample();
			rr.createCriteria().andRsidEqualTo(id);
			roleResourceMapper.deleteByExample(rr);
			r = resourceMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	public List<Resource> findByPage(Page page, String order) {
		List<Resource> r = null;
		ResourceExample u = new ResourceExample();
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
			if (order != null) {
				u.setOrderByClause(order);
			}
			r = resourceMapper.selectByExample(u);
		} else {
			r = resourceMapper.selectByExample(u);
		}

		return r;
	}

	@Override
	public int findCount(ResourceExample u) {

		return resourceMapper.countByExample(u);
	}

}
