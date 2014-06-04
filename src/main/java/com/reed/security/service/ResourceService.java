package com.reed.security.service;

import java.awt.image.RescaleOp;

import com.reed.security.domain.Resource;
import com.reed.security.domain.ResourceExample;

public interface ResourceService extends BaseService<Resource> {


	public int findCount(ResourceExample u);
}
