package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

/**
 * 自动化运营分类
 * @author longyue
 *
 */
public class AutoOperationCategory {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDatapackageStatus() {
		return datapackageStatus;
	}
	public void setDatapackageStatus(String datapackageStatus) {
		this.datapackageStatus = datapackageStatus;
	}
	private String name;
	private String remark;
	private String datapackageStatus;
}
