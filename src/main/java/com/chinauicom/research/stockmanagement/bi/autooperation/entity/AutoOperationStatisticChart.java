package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

public class AutoOperationStatisticChart {
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	public float getReachNumberAdd() {
		return reachNumberAdd;
	}
	public void setReachNumberAdd(float reachNumberAdd) {
		this.reachNumberAdd = reachNumberAdd;
	}
	public float getOrderNumberAdd() {
		return orderNumberAdd;
	}
	public void setOrderNumberAdd(float orderNumberAdd) {
		this.orderNumberAdd = orderNumberAdd;
	}
	public float getDataplantIncomeAdd() {
		return dataplantIncomeAdd;
	}
	public void setDataplantIncomeAdd(float dataplantIncomeAdd) {
		this.dataplantIncomeAdd = dataplantIncomeAdd;
	}
	public float getSuccessRate() {
		return successRate;
	}
	public void setSuccessRate(float successRate) {
		this.successRate = successRate;
	}
	public float getSuccessRateAdd() {
		return successRateAdd;
	}
	public void setSuccessRateAdd(float successRateAdd) {
		this.successRateAdd = successRateAdd;
	}
	private String provinceName;
	private String categoryName;
	private float reachNumber;
	private float orderNumber;
	private float dataplantIncome;
	private float reachNumberAdd;
	private float orderNumberAdd;
	private float dataplantIncomeAdd;
	private float orderNumberAddRate;
	private float reachNumberAddRate;
	public float getReachNumberAddRate() {
		return reachNumberAddRate;
	}
	public void setReachNumberAddRate(float reachNumberAddRate) {
		this.reachNumberAddRate = reachNumberAddRate;
	}
	public float getOrderNumberAddRate() {
		return orderNumberAddRate;
	}
	public void setOrderNumberAddRate(float orderNumberAddRate) {
		this.orderNumberAddRate = orderNumberAddRate;
	}
	public float getDataplantIncomeAddRate() {
		return dataplantIncomeAddRate;
	}
	public void setDataplantIncomeAddRate(float dataplantIncomeAddRate) {
		this.dataplantIncomeAddRate = dataplantIncomeAddRate;
	}
	private float dataplantIncomeAddRate;
	private float successRate;
	private float successRateAdd;
	private float successRateAddRate;
	public float getSuccessRateAddRate() {
		return successRateAddRate;
	}
	public void setSuccessRateAddRate(float successRateAddRate) {
		this.successRateAddRate = successRateAddRate;
	}
}
