package com.chinauicom.research.stockmanagement.bi.autooperation.service;

import java.util.List;

import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthInfo;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthWeb;

public interface AutoOperationMonthInfoHandlerService {
	/**
	 * 获取指定月份自动运营情况信息
	 * @param year 年 yyyy
	 * @param month 月 mm
	 * @param OrgCode 省份编码
	 * @return
	 */
	public List<AutoOperationMonthWeb>  getAutoOperationMonthInfo(String year,String month,String OrgCode) throws Exception;
	
	/**
	 * 增加或修改自动运营情况信息
	 * @param monthInfoList
	 * @throws Exception
	 */
	public int addAutoOperationMonthInfo(AutoOperationMonthWeb dlist) throws Exception;
}
