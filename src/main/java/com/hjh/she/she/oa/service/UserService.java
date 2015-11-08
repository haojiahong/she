package com.hjh.she.she.oa.service;

import java.util.List;

import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.User;

public interface UserService {

	public List<User> findAllUserList(String userNameSch, SortParamList sortInfo, PageInfo pageInfo);

}
