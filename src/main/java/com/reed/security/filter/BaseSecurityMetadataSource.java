package com.reed.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.reed.security.domain.Resource;
import com.reed.security.service.ResourceService;

//1 加载资源与权限的对应关系
public class BaseSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private static Log logger = LogFactory
			.getLog(BaseSecurityMetadataSource.class);

	@Autowired
	private ResourceService resourceService;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	// 由spring调用
	public BaseSecurityMetadataSource(ResourceService resourceService) {
		this.resourceService = resourceService;
		loadResourceDefine();
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

	// 加载所有资源与权限的关系
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resource> resources = this.resourceService.findByPage(null,
					null);
			for (Resource resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				// 以权限名封装为Spring的security Object
				ConfigAttribute configAttribute = new SecurityConfig(
						resource.getName());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getFullurl(), configAttributes);
			}
		}
		// home page
		// Collection<ConfigAttribute> home = new ArrayList<ConfigAttribute>();
		// home.add(new SecurityConfig("Home"));
		// resourceMap.put("/index.jsp", home);

		Set<Entry<String, Collection<ConfigAttribute>>> resourceSet = resourceMap
				.entrySet();
		Iterator<Entry<String, Collection<ConfigAttribute>>> iterator = resourceSet
				.iterator();

	}

	// 返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {

		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		logger.info("requestUrl is " + requestUrl);
		if (requestUrl.indexOf("?") > 0) {
			String[] tmp = requestUrl.split("\\?");
			requestUrl = tmp[0];
		}
		logger.info("requestUrl is " + requestUrl);
		
		if (resourceMap == null) {
			loadResourceDefine();
		}
		// check permisson is null
		if (resourceMap.containsKey(requestUrl)) {
			return resourceMap.get(requestUrl);
		} else {
			Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
			returnCollection.add(new SecurityConfig("No resources"));
			return returnCollection;
		}
		// return resourceMap.get(requestUrl);
	}

}
