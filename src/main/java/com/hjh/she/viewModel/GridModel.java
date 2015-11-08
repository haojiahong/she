package com.hjh.she.viewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 表格模型
 * @author haojiahong
 * @date 2015年11月8日
 * @desc
 */
@SuppressWarnings("rawtypes")
public class GridModel {
	private List rows = new ArrayList();
	private int total = 0;

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
}
