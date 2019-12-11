package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

public class AutoOperationStatistic {
	private String orgName;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getReachNumber() {
		return reachNumber;
	}
	public void setReachNumber(float reachNumber) {
		this.reachNumber = reachNumber;
	}
	public float getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(float orderNumber) {
		this.orderNumber = orderNumber;
	}
	public float getDataplantIncome() {
		return dataplantIncome;
	}
	public void setDataplantIncome(float dataplantIncome) {
		this.dataplantIncome = dataplantIncome;
	}
	private int count;
	private float reachNumber;
	private float orderNumber;
	private float dataplantIncome;
}
