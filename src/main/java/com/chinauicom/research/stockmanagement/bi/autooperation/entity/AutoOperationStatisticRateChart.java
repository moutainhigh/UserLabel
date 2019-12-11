package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

public class AutoOperationStatisticRateChart {
	private String categoryName;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getFirstMonth() {
		return firstMonth;
	}
	public void setFirstMonth(String firstMonth) {
		this.firstMonth = firstMonth;
	}
	public String getSecondMonth() {
		return secondMonth;
	}
	public void setSecondMonth(String secondMonth) {
		this.secondMonth = secondMonth;
	}
	public String getThirdMonth() {
		return thirdMonth;
	}
	public void setThirdMonth(String thirdMonth) {
		this.thirdMonth = thirdMonth;
	}
	public float getRate1() {
		return rate1;
	}
	public void setRate1(float rate1) {
		this.rate1 = rate1;
	}
	public float getRate2() {
		return rate2;
	}
	public void setRate2(float rate2) {
		this.rate2 = rate2;
	}
	public float getRate3() {
		return rate3;
	}
	public void setRate3(float rate3) {
		this.rate3 = rate3;
	}
	private String firstMonth;
	private String secondMonth;
	private String thirdMonth;
	private float rate1;
	private float rate2;
	private float rate3;
}
