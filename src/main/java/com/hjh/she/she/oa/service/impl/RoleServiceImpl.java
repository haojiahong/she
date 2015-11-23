package com.hjh.she.she.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hjh.she.basedao.JPAUtil;
import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.QueryParamList;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.Permission;
import com.hjh.she.model.oa.Role;
import com.hjh.she.model.oa.RolePermissionRela;
import com.hjh.she.she.oa.service.RoleService;
import com.hjh.she.shiro.MyShiroRealm;
import com.hjh.she.util.CommonUtil;

@Component("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private MyShiroRealm myShiroRealm;

	@Override
	public List<Role> findAllRoleList(String roleNameSch, SortParamList sortInfo, PageInfo pageInfo) {
		String jpql = "select role from Role role where 1=1";
		QueryParamList params = new QueryParamList();
		if (!CommonUtil.strIsNull(roleNameSch)) {
			jpql += " and role.name like :name";
			params.addParam("name", "%" + roleNameSch + "%");
		}
		List<Role> result = JPAUtil.find(jpql, params, sortInfo, pageInfo);
		return result;
	}

	@Override
	public Role retrieveOne(String roleId) {
		return JPAUtil.loadById(Role.class, roleId);
	}

	@Override
	public Role addRole() {
		return new Role();
	}

	@Override
	public void save(Role role) {
		if (CommonUtil.strIsNull(role.getRoleId())) {
			role.setRoleId(CommonUtil.genUUID());
			JPAUtil.create(role);
		} else {
			JPAUtil.update(role);
		}
	}

	@Override
	public void remove(Role role) {
		JPAUtil.refresh(role);
		JPAUtil.remove(Role.class, role.getRoleId());

	}

	@Override
	public List<Permission> getRolePermission(String roleId) {
		String jpql = "select perm from Permission perm join fetch perm.rolePermissionRelaLs rela where rela.roleId =:roleId";
		QueryParamList params = new QueryParamList();
		params.addParam("roleId", roleId);
		List<Permission> result = JPAUtil.find(jpql, params);
		return result;
	}

	@Override
	@Transactional
	public void savePermission(String roleId, String permissionIds) {
		String oldJpql = "select rela from RolePermissionRela rela where rela.roleId =:roleId";
		QueryParamList oldParams = new QueryParamList();
		oldParams.addParam("roleId", roleId);
		List<RolePermissionRela> relaLs = JPAUtil.find(oldJpql, oldParams);
		if (relaLs.size() > 0) {
			JPAUtil.remove(relaLs);
		}
		List<String> permissionIdLs = CommonUtil.paraseStrs(permissionIds);
		if (permissionIdLs.size() > 0) {
			for (String permissionId : permissionIdLs) {
				RolePermissionRela rela = new RolePermissionRela();
				rela.setId(CommonUtil.genUUID());
				rela.setPermissionId(permissionId);
				rela.setRoleId(roleId);
				JPAUtil.create(rela);
			}
		}
		// 权限设置成功后需要清空shiro中的权限缓存数据
		myShiroRealm.clearAllCachedAuthorizationInfo();
	}

}
