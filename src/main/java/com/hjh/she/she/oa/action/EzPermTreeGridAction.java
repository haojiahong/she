package com.hjh.she.she.oa.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjh.she.model.oa.Permission;
import com.hjh.she.she.BaseAction;
import com.hjh.she.she.oa.service.PermissionService;
import com.hjh.she.util.Constants;
import com.hjh.she.viewModel.eztree.EzPermTreeBean;

@Namespace("/oa")
@Action(value = "/ezPermTreeGridAction")
public class EzPermTreeGridAction extends BaseAction<Object> {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PermissionService permissionService;

	private List<EzPermTreeBean> ezTree = new ArrayList<EzPermTreeBean>();
	private Long id;// ezUi默认的提交

	public String retrieve() throws Exception {
		List<Permission> permParentList = permissionService.retrieveParentPermLs();
		for (Permission perm : permParentList) {
			EzPermTreeBean treeBean = initTreeBean(perm);
			ezTree.add(treeBean);
		}
		OutputJson(ezTree);
		return null;
	}

	private void initChild(EzPermTreeBean zt, String id) {
		List<Permission> permSubList = permissionService.retrieveSubPermLs(id);
		for (Permission sub : permSubList) {
			EzPermTreeBean treeBean = initTreeBean(sub);
			zt.getChildren().add(treeBean);
		}
	}

	private EzPermTreeBean initTreeBean(Permission perm) {
		EzPermTreeBean treeBean = new EzPermTreeBean();
		treeBean.setId(perm.getPermissionId());
		treeBean.setText(perm.getName());
		treeBean.setIconCls(perm.getIconcls());
		treeBean.setHavaSub(perm.getHaveSub());
		treeBean.setName(perm.getName());// 名称
		treeBean.setPid(perm.getPid());
		treeBean.setPname(perm.getPname());// 父权限名称
		treeBean.setSortNum(perm.getSortNum());// 排序号
		treeBean.setUrl(perm.getUrl());// 地址
		treeBean.setMyid(perm.getMyid());// 权限编码
		treeBean.setType(perm.getType());// 权限类型
		treeBean.setIsused(perm.getIsused());// 是否使用
		treeBean.setDescription(perm.getDescription());// 描述
		if (Constants.YES.equals(perm.getHaveSub())) {
			treeBean.setState("closed");
			this.initChild(treeBean, treeBean.getId());
		}
		return treeBean;
	}

	public List<EzPermTreeBean> getEzTree() {
		return ezTree;
	}

	public void setEzTree(List<EzPermTreeBean> ezTree) {
		this.ezTree = ezTree;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
