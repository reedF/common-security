package com.reed.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.reed.security.domain.Resource;
import com.reed.security.domain.RoleGroup;
import com.reed.security.domain.UserGroup;
import com.reed.security.service.ResourceService;
import com.reed.security.service.RoleService;
import com.reed.security.service.SecurityService;
import com.reed.security.service.UserService;

//2
public class BaseUserDetailServiceImpl implements UserDetailsService {
	private static Log logger = LogFactory
			.getLog(BaseUserDetailServiceImpl.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private SecurityService securityService;

	// 使用缓存
	@Autowired
	private UserCache userCache;

	// 登录验证
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		logger.info("username is:" + username);
		// 这里应该可以不用再查了
		com.reed.security.domain.User user = userService
				.findByAccount(username);

		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);

		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		// 封装成spring security的user
		User userdetail = new User(user.getAccount(), user.getPassword(),
				enables, accountNonExpired, credentialsNonExpired,
				accountNonLocked, grantedAuths);
		//use cache
//		if (userCache != null) {			
//			UserDetails u = userCache.getUserFromCache(user.getAccount());
//			userCache.putUserInCache(userdetail);
//		}
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(
			com.reed.security.domain.User user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		List<Resource> resources = new ArrayList<Resource>();
		List<UserGroup> groups = securityService.findUserGroupByUserOrGroup(
				user.getId(), null, null);
		if (groups != null && groups.size() > 0) {
			for (UserGroup ug : groups) {
				if (ug != null) {
					List<RoleGroup> roles = securityService
							.findRoleGroupByRoleOrGroup(null, ug.getGid(), null);
					if (roles != null) {
						for (RoleGroup role : roles) {
							if (role != null) {
								List<Resource> tempRes = securityService
										.findResourceByRole(role.getRid());
								for (Resource res : tempRes) {
									resources.add(res);
								}
							}
						}
					}
				}
			}
		}

		for (Resource res : resources) {
			authSet.add(new GrantedAuthorityImpl(res.getName()));
		}
		return authSet;
	}

}
