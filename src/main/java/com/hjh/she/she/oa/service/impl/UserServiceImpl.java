package com.hjh.she.she.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hjh.she.basedao.JPAUtil;
import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.QueryParamList;
import com.hjh.she.model.base.SortParam;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.Role;
import com.hjh.she.model.oa.User;
import com.hjh.she.model.oa.UserRoleRela;
import com.hjh.she.she.oa.service.UserService;
import com.hjh.she.shiro.MyShiroRealm;
import com.hjh.she.util.CommonUtil;
import com.hjh.she.util.Constants;

@Component("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private MyShiroRealm myShiroRealm;

	@Override
	public List<User> findAllUserList(String userNameSch, SortParamList sortInfo, PageInfo pageInfo) {
		String jpql = "select user from User user where 1=1";
		QueryParamList params = new QueryParamList();
		if (!CommonUtil.strIsNull(userNameSch)) {
			jpql += " and user.name =:name";
			params.addParam("name", userNameSch);
		}
		List<User> result = JPAUtil.find(jpql, params, sortInfo, pageInfo);
		return result;
	}

	@Override
	public User retrieveOne(String userId) {
		User user = JPAUtil.loadById(User.class, userId);
		return user;
	}

	@Override
	public void save(User user) {
		if (CommonUtil.strIsNull(user.getUserId())) {
			user.setUserId(CommonUtil.genUUID());
			JPAUtil.create(user);
		} else {
			JPAUtil.update(user);
		}

	}

	@Override
	public User add() {
		return new User();
	}

	@Override
	public void remove(User user) {
		JPAUtil.refresh(user);
		JPAUtil.remove(User.class, user.getUserId());
	}

	@Override
	public List<Role> setRoleRetrieve(String userId, SortParamList sorts, PageInfo pageInfo) {
		String jpql = "select role from Role role join fetch role.userRoleRelaLs rela join rela.user user where user.userId =:userId";
		QueryParamList params = new QueryParamList();
		params.addParam("userId", userId);
		sorts = this.genNewSorts("role", sorts);
		List<Role> roleLs = JPAUtil.find(jpql, params, sorts, pageInfo);
		return roleLs;
	}

	@Override
	public List<Role> setNewRoleRetrieve(String userId, SortParamList sorts, PageInfo pageInfo) {
		String jpql = "select role from Role role where role.roleId not in "
				+ " (select rela.roleId from UserRoleRela rela join rela.user user where user.userId =:userId)";
		QueryParamList params = new QueryParamList();
		params.addParam("userId", userId);
		sorts = this.genNewSorts("role", sorts);
		List<Role> roleLs = JPAUtil.find(jpql, params, sorts, pageInfo);
		return roleLs;
	}

	// 排序
	private SortParamList genNewSorts(String alias, SortParamList sorts) {

		if (sorts == null || sorts.getParams() == null) {
			sorts = new SortParamList();
			sorts.addParam("created", SortParam.SORT_TYPE_DESCENDING, alias);
			return sorts;
		}
		SortParamList result = new SortParamList();
		for (SortParam param : sorts.getParams()) {
			result.addParam(param.getSortProperty(), param.getSortType(), alias);
		}
		return result;
	}

	@Override
	@Transactional
	public void setRoles(String roleIds, String userId) {
		List<String> roleIdLs = CommonUtil.paraseStrs(roleIds);
		if (roleIdLs.size() > 0) {
			for (String roleId : roleIdLs) {
				UserRoleRela rela = new UserRoleRela();
				rela.setId(CommonUtil.genUUID());
				rela.setUserId(userId);
				rela.setRoleId(roleId);
				rela.setStatus(Constants.PERSISTENCE_STATUS);
				JPAUtil.create(rela);
			}
			// 角色设置成功后需要清空shiro中的权限缓存数据
			myShiroRealm.clearAllCachedAuthorizationInfo();
		}
	}

	@Override
	@Transactional
	public void delRoles(String roleIds, String userId) {
		List<String> roleIdLs = CommonUtil.paraseStrs(roleIds);
		String jpql = "select rela from UserRoleRela rela where rela.roleId in (:roleIdLs) and rela.userId =:userId";
		QueryParamList params = new QueryParamList();
		params.addParam("roleIdLs", roleIdLs);
		params.addParam("userId", userId);
		List<UserRoleRela> relaLs = JPAUtil.find(jpql, params);
		if (relaLs.size() > 0) {
			JPAUtil.remove(relaLs);
			// 角色设置成功后需要清空shiro中的权限缓存数据
			myShiroRealm.clearAllCachedAuthorizationInfo();
		}

	}
}
