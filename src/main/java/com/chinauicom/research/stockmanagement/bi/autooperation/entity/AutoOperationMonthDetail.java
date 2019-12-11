package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

/**
 * 自动化运营每月情况，页面用
 * @author Administrator
 *
 */
public class AutoOperationMonthDetail {
	private String sceneName;
	private String sceneRemark;
	private int categoryId;
	private int sceneId;
	private String sceneType;
	private int reachNumber;
	private int orderNumber;
	private float dataplantIncome;
	private String provinceCode;
	private String year;
	private String month;
	public String getSceneName() {
		return sceneName;
	}
	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
	public String getSceneRemark() {
		return sceneRemark;
	}
	public void setSceneRemark(String sceneRemark) {
		this.sceneRemark = sceneRemark;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSceneId() {
		return sceneId;
	}
	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}
	public String getSceneType() {
		return sceneType;
	}
	public void setSceneType(String sceneType) {
		this.sceneType = sceneType;
	}
	public int getReachNumber() {
		return reachNumber;
	}
	public void setReachNumber(int reachNumber) {
		this.reachNumber = reachNumber;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public float getDataplantIncome() {
		return dataplantIncome;
	}
	public void setDataplantIncome(float dataplantIncome) {
		this.dataplantIncome = dataplantIncome;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
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
	
	
}
