package com.hjh.she.she.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hjh.she.basedao.JPAUtil;
import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.QueryParamList;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.User;
import com.hjh.she.she.oa.service.UserService;
import com.hjh.she.util.CommonUtil;

@Component("userService")
public class UserServiceImpl implements UserService {
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
}
