package com.hjh.she.she.oa.service;

import java.util.List;

import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.model.oa.Role;

public interface RoleService {

	List<Role> findAllUserList(String roleNameSch, SortParamList sortInfo, PageInfo pageInfo);

}
