package com.hjh.she.she.oa.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.hjh.she.model.oa.User;
import com.hjh.she.she.BaseAction;
import com.hjh.she.she.oa.service.UserService;
import com.hjh.she.util.Constants;
import com.hjh.she.viewModel.EasyUIDrop;
import com.hjh.she.viewModel.GridModel;
import com.hjh.she.viewModel.SheJson;
import com.hjh.she.viewModel.ezdrop.GenderDrop;

@Namespace("/oa")
@Action(value = "userAction")
@Results({ @Result(name = "editUser", location = "useradd.jsp") })
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	private List<EasyUIDrop> genderDropList = new ArrayList<EasyUIDrop>();// 性别下拉

	private String userNameSch;// 用户名称查询
	private String roleIds;// 设置角色时，选中的角色id
	private User user = new User();

	public String retrieve() throws Exception {
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findAllUserList(userNameSch, getSortInfo(), getPageInfo()));
		gridModel.setTotal(getPageInfo().getAllRowNum());
		OutputJson(gridModel);
		return null;
	}

	public String editUser() throws Exception {
		user = userService.retrieveOne(getModel().getUserId());
		return "editUser";
	}

	public String addUser() throws Exception {
		if (!SecurityUtils.getSubject().isPermitted("userAdd")) {
			System.out.println("no permission======================");
			throw new AuthorizationException("No Permission");
		}
		user = userService.add();
		return "editUser";
	}

	public String delUser() throws Exception {
		userService.remove(getModel());
		SheJson json = new SheJson();
		json.setMessage("删除成功");
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	public String save() throws Exception {
		userService.save(getModel());
		SheJson json = new SheJson();
		json.setMessage("保存成功");
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	/**
	 * 查询用户已经设置的角色
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setRoleRetrieve() throws Exception {
		GridModel roleGridModel = new GridModel();
		roleGridModel.setRows(userService.setRoleRetrieve(getModel().getUserId(), getSortInfo(), getPageInfo()));
		roleGridModel.setTotal(getPageInfo().getAllRowNum());
		OutputJson(roleGridModel);
		return null;
	}

	/**
	 * 查询用户未拥有的角色,从而可设置成新角色
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setNewRoleRetrieve() throws Exception {
		GridModel newRoleGridModel = new GridModel();
		newRoleGridModel.setRows(userService.setNewRoleRetrieve(getModel().getUserId(), getSortInfo(), getPageInfo()));
		newRoleGridModel.setTotal(getPageInfo().getAllRowNum());
		OutputJson(newRoleGridModel);
		return null;
	}

	/**
	 * 设置新添加的角色
	 * 
	 * @return
	 * @throws Exception
	 */
	public String setRoles() throws Exception {
		userService.setRoles(roleIds, getModel().getUserId());
		SheJson json = new SheJson();
		json.setStatus(true);
		json.setMessage("设置成功");
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	public String delRoles() throws Exception {
		userService.delRoles(roleIds, getModel().getUserId());
		SheJson json = new SheJson();
		json.setStatus(true);
		json.setMessage("删除成功");
		OutputJson(json, Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	// 性别下拉
	public String genderDropList() throws Exception {
		genderDropList.addAll(GenderDrop.getGenderList());
		OutputJson(genderDropList);
		return null;
	}

	public String getUserNameSch() {
		return userNameSch;
	}

	public void setUserNameSch(String userNameSch) {
		this.userNameSch = userNameSch;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<EasyUIDrop> getGenderDropList() {
		return genderDropList;
	}

	public void setGenderDropList(List<EasyUIDrop> genderDropList) {
		this.genderDropList = genderDropList;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

}
