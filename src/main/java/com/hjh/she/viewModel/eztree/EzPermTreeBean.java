package com.hjh.she.viewModel.eztree;

/**
 * 权限菜单treebean
 * 
 * @author haojiahong
 * @date 2015年11月16日
 * @desc
 */
public class EzPermTreeBean extends EasyTree {
	private String name;// 名称
	private String pid;// 父id
	private String pname;// 父名称
	private Integer sortNum;// 序号
	private String url;// 菜单路径
	private String myid;// 菜单编码
	private String type;// 菜单类型
	private String isused;// 是否启用
	private String description;// 描述

	private String havaSub;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHavaSub() {
		return havaSub;
	}

	public void setHavaSub(String havaSub) {
		this.havaSub = havaSub;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
