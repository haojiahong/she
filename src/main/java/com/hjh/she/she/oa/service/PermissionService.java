package com.hjh.she.she.oa.service;

import java.util.List;

import com.hjh.she.model.oa.Permission;

public interface PermissionService {

	/**
	 * 查詢每個節點下的子節點
	 * 
	 * @param id
	 * @return
	 */
	public List<Permission> retrieveSubPermLs(String id);

	/**
	 * 查询父节点为空的树节点（即：最高层节点）
	 * 
	 * @return
	 */
	public List<Permission> retrieveParentPermLs();

}
