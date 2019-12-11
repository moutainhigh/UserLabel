package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

public class AutoOperationChartIncomeRate {
	private String provinceName;
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	private float rate;
}
