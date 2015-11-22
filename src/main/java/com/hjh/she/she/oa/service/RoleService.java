package com.hjh.she.she.oa.service;

import java.util.List;

import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.Permission;
import com.hjh.she.model.oa.Role;

public interface RoleService {

	public List<Role> findAllRoleList(String roleNameSch, SortParamList sortInfo, PageInfo pageInfo);

	public Role retrieveOne(String roleId);

	public Role addRole();

	public void save(Role role);

	public void remove(Role model);

	/**
	 * 查询角色已经设置的权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Permission> getRolePermission(String roleId);

	/**
	 * 为角色设置选中的权限
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	public void savePermission(String roleId, String permissionIds);

}
