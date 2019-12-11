package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

public class AutoOperationStatisticValue {
	String year;
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
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	String month;
	String provinceCode;
	double value;
	double reachNumber;
	public double getReachNumber() {
		return reachNumber;
	}
	public void setReachNumber(double reachNumber) {
		this.reachNumber = reachNumber;
	}
	public double getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(double orderNumber) {
		this.orderNumber = orderNumber;
	}
	public double getDataplantIncome() {
		return dataplantIncome;
	}
	public void setDataplantIncome(double dataplantIncome) {
		this.dataplantIncome = dataplantIncome;
	}
	double orderNumber;
	double dataplantIncome;
	String categoryId;
	String sceneId;
	public String getSceneId() {
		return sceneId;
	}
	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
