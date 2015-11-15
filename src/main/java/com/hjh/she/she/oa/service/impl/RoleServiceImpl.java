package com.hjh.she.she.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hjh.she.basedao.JPAUtil;
import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.QueryParamList;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.Role;
import com.hjh.she.she.oa.service.RoleService;
import com.hjh.she.util.CommonUtil;

@Component("roleService")
public class RoleServiceImpl implements RoleService {

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

}
