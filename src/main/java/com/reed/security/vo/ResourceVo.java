package com.reed.security.vo;

import org.mybatis.generator.plugin.Page;

import com.reed.security.domain.Resource;

public class ResourceVo {

	private Resource resource;
	

	private Page page;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}



}
