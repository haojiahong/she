package com.hjh.she.she.oa.service;

import java.util.List;

import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.Role;
import com.hjh.she.model.oa.User;

public interface UserService {

	public List<User> findAllUserList(String userNameSch, SortParamList sortInfo, PageInfo pageInfo);

	public User retrieveOne(String userId);

	public void save(User user);

	public User add();

	public void remove(User user);

	/**
	 * 查询用户已经设置的角色
	 * 
	 * @param userId
	 * @param pageInfo
	 * @param sortParamList
	 * 
	 * @return
	 */
	public List<Role> setRoleRetrieve(String userId, SortParamList sortParamList, PageInfo pageInfo);

	/**
	 * 查询用户未拥有的角色,从而可设置成新角色
	 * 
	 * @param userId
	 * @param sortInfo
	 * @param pageInfo
	 * @return
	 */
	public List<Role> setNewRoleRetrieve(String userId, SortParamList sortInfo, PageInfo pageInfo);

	/**
	 * 为选中用户设定角色
	 * 
	 * @param roleIds
	 * @param userId
	 */
	public void setRoles(String roleIds, String userId);

}
