package com.chinauicom.research.stockmanagement.bi.autooperation.entity;

import com.chinauicom.research.commons.utils.BaseVO;

/**
 * 自动化运营月表
 * @author Administrator
 *
 */
public class AutoOperationMonthInfo extends BaseVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
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

	private String year;
	private String month;
	private int sceneId;
	private String sceneType;
	private int categoryId;
	private int reachNumber;
	private int orderNumber;
	private float dataplantIncome;
	private String provinceCode;
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
}
