package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

import java.util.List;

/**
 * 自动化运营每月情况，录入页面用
 * @author longyue
 *
 */
public class AutoOperationMonthWeb {
	private int categoryId;
	private String categoryName;
	private String datapackageStatus;
	private List<AutoOperationMonthDetail> dlist;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<AutoOperationMonthDetail> getDlist() {
		return dlist;
	}
	public void setDlist(List<AutoOperationMonthDetail> dlist) {
		this.dlist = dlist;
	}
	public String getDatapackageStatus() {
		return datapackageStatus;
	}
	public void setDatapackageStatus(String datapackageStatus) {
		this.datapackageStatus = datapackageStatus;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
