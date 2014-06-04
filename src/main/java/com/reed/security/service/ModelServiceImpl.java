package com.reed.security.service;

import java.util.List;

import org.mybatis.generator.plugin.Page;
import org.springframework.beans.factory.annotation.Autowired;

import com.reed.security.domain.Model;
import com.reed.security.domain.ModelExample;
import com.reed.security.domain.Resource;
import com.reed.security.domain.ResourceExample;
import com.reed.security.mapper.ModelMapper;
import com.reed.security.mapper.ResourceMapper;
import com.reed.security.utils.CommonUtil;

public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public Model findById(Integer id) {

		return modelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(Model t) {

		return modelMapper.insert(t);
	}

	/**
	 * 级联更新resource.fullurl
	 */
	@Override
	public int update(Model t) {
		int r = 0;
		if (t != null) {
			List<Resource> rs = securityService
					.findResourceByModelId(t.getId());
			if (rs != null) {
				for (Resource re : rs) {
					if (re != null) {
						if (t.getUrl().endsWith(CommonUtil.UrlPer)) {
							re.setFullurl(t.getUrl() + re.getUrl());
						} else {
							re.setFullurl(t.getUrl() + CommonUtil.UrlPer
									+ re.getUrl());
						}

						resourceMapper.updateByPrimaryKeySelective(re);
					}
				}
			}
			r = modelMapper.updateByPrimaryKeySelective(t);
		}

		return r;
	}

	/**
	 * 级联删除rescource
	 */
	@Override
	public int deleteById(Integer id) {

		int r = 0;
		if (id != null) {
			ResourceExample re = new ResourceExample();
			re.createCriteria().andModelidEqualTo(id);
			resourceMapper.deleteByExample(re);
			r = modelMapper.deleteByPrimaryKey(id);
		}
		return r;
	}

	@Override
	public List<Model> findByPage(Page page, String order) {
		List<Model> r = null;
		ModelExample u = new ModelExample();
		if (page != null) {
			u.setLimitEnd(page.getLength());
			u.setLimitStart(page.getBegin());
			if (order != null) {
				u.setOrderByClause(order);
			}
			r = modelMapper.selectByExample(u);
		} else {
			r = modelMapper.selectByExample(u);
		}

		return r;
	}

}
