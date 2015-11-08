package com.hjh.she.she.oa.service;

import java.util.List;

import com.hjh.she.viewModel.MenuModel;

public interface LoginService {
	public List<MenuModel> findMenuList() throws Exception;
}