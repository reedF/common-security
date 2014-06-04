package com.reed.security.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.reed.security.domain.Resource;
import com.reed.security.domain.ResourceExample;
import com.reed.security.domain.RoleGroup;
import com.reed.security.domain.RoleGroupExample;
import com.reed.security.domain.RoleResource;
import com.reed.security.domain.RoleResourceExample;
import com.reed.security.domain.UserGroup;
import com.reed.security.domain.UserGroupExample;
import com.reed.security.domain.UserExample.Criteria;
import com.reed.security.mapper.ResourceMapper;
import com.reed.security.mapper.RoleGroupMapper;
import com.reed.security.mapper.RoleMapper;
import com.reed.security.mapper.RoleResourceMapper;
import com.reed.security.mapper.UserGroupMapper;

public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserGroupMapper userGroupMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private RoleGroupMapper roleGroupMapper;

	@Override
	public int saveUserGroup(Integer userId, Integer gId) {
		int r = 0;
		if (userId != null && gId != null) {
			UserGroup u = new UserGroup();
			u.setGid(gId);
			u.setUid(userId);
			r = userGroupMapper.insert(u);
		}
		return r;
	}

	@Override
	public int saveRoleResource(Integer roleId, Integer rsId) {
		int r = 0;
		if (roleId != null && rsId != null) {
			RoleResource rr = new RoleResource();
			rr.setRid(roleId);
			rr.setRsid(rsId);
			r = roleResourceMapper.insert(rr);
		}
		return r;
	}

	@Override
	public int deleteUserGroupById(Integer id) {
		int r = 0;
		if (id != null) {
			r = userGroupMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	public int deleteUserGroupByGIdAndUId(Integer groupId, Integer userId) {
		int r = 0;
		if (groupId != null && userId != null) {
			UserGroupExample u = new UserGroupExample();
			u.createCriteria().andGidEqualTo(groupId).andUidEqualTo(userId);
			r = userGroupMapper.deleteByExample(u);
		}

		return r;
	}

	@Override
	public int deleteRoleGroupByRId(Integer roleId) {
		int r = 0;
		RoleGroupExample u = new RoleGroupExample();
		if (roleId != null) {
			u.createCriteria().andRidEqualTo(roleId);
			r = roleGroupMapper.deleteByExample(u);
		}
		return r;
	}
	
	public int deleteRoleGroupByGId(Integer groupId) {
		int r = 0;
		RoleGroupExample u = new RoleGroupExample();
		if (groupId != null) {
			u.createCriteria().andGidEqualTo(groupId);
			r = roleGroupMapper.deleteByExample(u);
		}
		return r;
	}

	@Override
	public int deleteRoleResourceByRId(Integer roleId) {
		int r = 0;
		RoleResourceExample u = new RoleResourceExample();
		if (roleId != null) {
			u.createCriteria().andRidEqualTo(roleId);
			r = roleResourceMapper.deleteByExample(u);
		}
		return r;
	}

	public int deleteUserGroupByUId(Integer userId) {
		int r = 0;
		UserGroupExample u = new UserGroupExample();
		if (userId != null) {
			u.createCriteria().andUidEqualTo(userId);
			r = userGroupMapper.deleteByExample(u);
		}
		return r;
	}

	public int deleteUserGroupByGId(Integer groupId) {
		int r = 0;
		UserGroupExample u = new UserGroupExample();
		if (groupId != null) {
			u.createCriteria().andGidEqualTo(groupId);
			r = userGroupMapper.deleteByExample(u);
		}
		return r;
	}

	@Override
	public int deleteRoleResourceById(Integer id) {
		int r = 0;
		if (id != null) {
			r = roleResourceMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	public List<UserGroup> findUserGroupByUserOrGroup(Integer userId,
			Integer gId, Page page) {
		List<UserGroup> r = null;
		UserGroupExample u = new UserGroupExample();
		if (userId != null) {
			u.or().andUidEqualTo(userId);
		}
		if (gId != null) {
			u.or().andGidEqualTo(gId);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = userGroupMapper.selectByExample(u);

		return r;
	}

	@Override
	public List<RoleResource> findRoleResourceByRoleOrRs(Integer roleId,
			Integer rsId, Page page) {
		List<RoleResource> r = null;
		RoleResourceExample u = new RoleResourceExample();
		if (roleId != null) {
			u.or().andRidEqualTo(roleId);
		}
		if (rsId != null) {
			u.or().andRsidEqualTo(rsId);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = roleResourceMapper.selectByExample(u);

		return r;
	}

	@Override
	public List<Resource> findResourceByRole(Integer roleId) {
		List<Resource> r = null;
		if (roleId != null) {
			List<RoleResource> urs = findRoleResourceByRoleOrRs(roleId, null,
					null);
			if (urs != null) {
				r = new ArrayList<Resource>();
				for (RoleResource ur : urs) {
					if (ur != null) {
						r.add(resourceMapper.selectByPrimaryKey(ur.getRsid()));
					}
				}
			}
		}
		return r;
	}

	@Override
	public List<Resource> findResourceByModelId(Integer modelId) {
		List<Resource> r = null;
		if (modelId != null) {
			ResourceExample u = new ResourceExample();
			u.createCriteria().andModelidEqualTo(modelId);
			r = resourceMapper.selectByExample(u);
		}
		return r;
	}

	@Override
	public int saveRoleGroup(Integer roleId, Integer groupId) {
		int r = 0;
		if (roleId != null && groupId != null) {
			RoleGroup rg = new RoleGroup();
			rg.setGid(groupId);
			rg.setRid(roleId);
			r = roleGroupMapper.insert(rg);
		}
		return r;
	}

	@Override
	public int deleteRoleGroupById(Integer id) {
		int r = 0;
		if (id != null) {
			r = roleGroupMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	public List<RoleGroup> findRoleGroupByRoleOrGroup(Integer roleId,
			Integer groupId, Page page) {
		List<RoleGroup> r = null;
		RoleGroupExample u = new RoleGroupExample();
		if (roleId != null) {
			u.or().andRidEqualTo(roleId);
		}
		if (groupId != null) {
			u.or().andGidEqualTo(groupId);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = roleGroupMapper.selectByExample(u);

		return r;
	}

	public List<RoleGroup> findRoleGroupByRoleAndGroup(Integer roleId,
			Integer groupId, Page page) {
		List<RoleGroup> r = null;
		RoleGroupExample u = new RoleGroupExample();
		com.reed.security.domain.RoleGroupExample.Criteria c = u
				.createCriteria();
		if (roleId != null) {
			c.andRidEqualTo(roleId);
		}
		if (groupId != null) {
			c.andGidEqualTo(groupId);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = roleGroupMapper.selectByExample(u);

		return r;
	}

	@Override
	public List<UserGroup> findUserGroupByUserAndGroup(Integer userId,
			Integer groupId, Page page) {
		List<UserGroup> r = null;
		UserGroupExample u = new UserGroupExample();
		com.reed.security.domain.UserGroupExample.Criteria c = u
				.createCriteria();
		if (userId != null) {
			c.andUidEqualTo(userId);
		}
		if (groupId != null) {
			c.andGidEqualTo(groupId);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = userGroupMapper.selectByExample(u);

		return r;
	}

	@Override
	public List<RoleResource> findRoleResourceByRoleAndRs(Integer roleId,
			Integer rsId, Page page) {
		List<RoleResource> r = null;
		RoleResourceExample u = new RoleResourceExample();
		com.reed.security.domain.RoleResourceExample.Criteria c = u
				.createCriteria();
		if (roleId != null) {
			c.andRidEqualTo(roleId);
		}
		if (rsId != null) {
			c.andRsidEqualTo(rsId);
		}
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
		}
		r = roleResourceMapper.selectByExample(u);

		return r;
	}

	@Override
	public int findCountUserGroupByUserOrGroup(Integer userId, Integer groupId) {
		int r = 0;
		UserGroupExample u = new UserGroupExample();
		if (groupId != null) {
			u.or().andGidEqualTo(groupId);
		}
		if (userId != null) {
			u.or().andUidEqualTo(userId);
		}
		r = userGroupMapper.countByExample(u);
		return r;
	}

}
