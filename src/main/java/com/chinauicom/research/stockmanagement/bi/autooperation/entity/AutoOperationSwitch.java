package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

public class AutoOperationSwitch {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getSwitchStatus() {
		return switchStatus;
	}
	public void setSwitchStatus(String switchStatus) {
		this.switchStatus = switchStatus;
	}
	private String year;
	private String month;
	private String switchStatus;
}
