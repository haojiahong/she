package com.hjh.she.she.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hjh.she.basedao.JPAUtil;
import com.hjh.she.model.base.QueryParamList;
import com.hjh.she.model.oa.Permission;
import com.hjh.she.she.oa.service.PermissionService;

@Component("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Override
	public List<Permission> retrieveParentPermLs() {
		String jpql = "select perm from Permission perm where perm.pid is null";
		List<Permission> permLs = JPAUtil.find(jpql, null, null, null);
		return permLs;
	}

	@Override
	public List<Permission> retrieveSubPermLs(String pid) {
		String jpql = "select perm from Permission perm where perm.upPermission.permissionId= :pid";
		QueryParamList params = new QueryParamList();
		params.addParam("pid", pid);
		List<Permission> permLs = JPAUtil.find(jpql, params, null, null);
		return permLs;
	}

}
