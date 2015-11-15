package com.hjh.she.she.oa.action;

import java.util.ArrayList;
import java.util.List;

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
	private String userId;
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
		user = userService.add();
		return "editUser";
	}
	
	public String delUser() throws Exception{
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

}
