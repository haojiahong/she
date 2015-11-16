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
			EzPermTreeBean treeBean = new EzPermTreeBean();
			treeBean.setId(perm.getPermissionId());
			treeBean.setText(perm.getName());
			treeBean.setIconCls(perm.getIconcls());
			treeBean.setHavaSub(perm.getHaveSub());
			if (Constants.YES.equals(perm.getHaveSub())) {
				treeBean.setState("closed");
				this.initChild(treeBean, treeBean.getId());
			}
			ezTree.add(treeBean);
		}
		OutputJson(ezTree);
		return null;
	}

	private void initChild(EzPermTreeBean zt, String id) {

		List<Permission> permSubList = permissionService.retrieveSubPermLs(id);

		for (Permission sub : permSubList) {
			EzPermTreeBean treeBean = new EzPermTreeBean();
			treeBean.setId(sub.getPermissionId());
			treeBean.setText(sub.getName());
			treeBean.setIconCls(sub.getIconcls());
			// treeBean.setHavaSub(sub.getHaveSub());
			if (Constants.YES.equals(sub.getHaveSub())) {
				treeBean.setState("closed");

				this.initChild(treeBean, treeBean.getId());
			}
			zt.getChildren().add(treeBean);

		}

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
