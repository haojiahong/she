package com.hjh.she.she.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hjh.she.basedao.JPAUtil;
import com.hjh.she.she.oa.service.LoginService;
import com.hjh.she.shiro.ShiroUser;
import com.hjh.she.util.CommonUtil;
import com.hjh.she.util.Constants;
import com.hjh.she.viewModel.MenuModel;

@Component("loginService")
public class LoginServiceImpl implements LoginService {

	@Override
	public List<MenuModel> findMenuList() throws Exception{
		ShiroUser user = CommonUtil.getCurrendUser();
		System.out.println("user====>" + user);
		String sql = null;
		// 超级管理员默认拥有所有功能权限
		if (Constants.SYSTEM_ADMINISTRATOR.equals(user.getAccount())) {
			sql = "SELECT p.PERMISSION_ID,p.PID,p.NAME,p.ICONCLS,p.URL FROM OA_PERMISSION AS p "
					+ " where p.STATUS='A' and p.TYPE='F' and p.ISUSED='Y'";
		} else {
			sql = "SELECT DISTINCT p.PERMISSION_ID,p.PID,p.NAME,p.ICONCLS,p.URL FROM\n"
					+ "ROLE_PERMISSION AS rp\n"
					+ "INNER JOIN ROLE AS r ON rp.ROLE_ID = r.ROLE_ID\n"
					+ "INNER JOIN USER_ROLE AS ur ON rp.ROLE_ID = ur.ROLE_ID\n"
					+ "INNER JOIN USERS AS u ON u.USER_ID = ur.USER_ID\n"
					+ "INNER JOIN PERMISSION AS p ON rp.PERMISSION_ID = p.PERMISSION_ID\n"
					+ "WHERE rp.STATUS='A' and r.STATUS='A' and ur.STATUS='A' and u.STATUS='A' and p.STATUS='A' and p.TYPE='F' and p.ISUSED='Y'\n"
					+ "and u.USER_ID=" + user.getUserId() + "";
		}
		// List listmenu = publicDaoSQL.findBySQL(sql);
		List listmenu = JPAUtil.executeNativeQuery(sql);
		List<MenuModel> parentList = new ArrayList<MenuModel>();
		for (Object object : listmenu) {
			Object[] objs = (Object[]) object;
			String id = String.valueOf(objs[0]);
			if (objs[1] == null) {
				MenuModel menuModel = new MenuModel();
				menuModel.setName(String.valueOf(objs[2]));
				menuModel.setIconCls(String.valueOf(objs[3]));
				menuModel.setUrl(String.valueOf(objs[4]));
				List<MenuModel> childList = new ArrayList<MenuModel>();
				for (Object obj2 : listmenu) {
					MenuModel menuChildModel = new MenuModel();
					Object[] objs2 = (Object[]) obj2;
					String sid = String.valueOf(objs2[1]);
					if (sid.equals(id)) {
						menuChildModel.setName(String.valueOf(objs2[2]));
						menuChildModel.setIconCls(String.valueOf(objs2[3]));
						menuChildModel.setUrl(String.valueOf(objs2[4]));
						childList.add(menuChildModel);
					}
				}
				menuModel.setChild(childList);
				parentList.add(menuModel);
			}
		}
		return parentList;
	}
}
