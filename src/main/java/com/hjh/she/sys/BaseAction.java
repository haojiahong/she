package com.hjh.she.sys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.hjh.she.model.base.PageInfo;
import com.hjh.she.model.base.SortParamList;
import com.hjh.she.viewModel.SheJson;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("default-package")
@Namespace("/")
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private int page;
	private int rows = 20;// 默认值
	private String sort;
	private String order;
	private PageInfo pageInfo = new PageInfo();
	private SortParamList sortInfo = new SortParamList();

	public void OutputJson(Object object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}

	public void OutputJson(Object object, String type) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext.getResponse();
		httpServletResponse.setContentType(type);
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}

	public SheJson getMessage(boolean flag) {
		SheJson json = new SheJson();
		if (flag) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		} else {
			json.setMessage("提交失败了！");
		}
		return json;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public PageInfo getPageInfo() {
		pageInfo.setRowOfPage(rows);// 一页几行
		pageInfo.setCurPageNum(page);// 当前页数
		return pageInfo;
	}

	public SortParamList getSortInfo() {
		if (sort != null)
			sortInfo.addParam(sort, order);
		return sortInfo;
	}

}
