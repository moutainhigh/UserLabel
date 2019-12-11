package com.chinauicom.research.stockmanagement.bi.autooperation.service;

import java.util.List;
import java.util.Map;

import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthInfoList;

public abstract interface AutoOperationService {
	public List<AutoOperationMonthInfoList> selectMonthInfoList(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public abstract boolean updateSwitchStatus(@SuppressWarnings("rawtypes") Map params)throws Exception;
	public abstract boolean insertSwitchStatus(@SuppressWarnings("rawtypes") Map params)throws Exception;
	public abstract int checkNextSwitch(@SuppressWarnings("rawtypes") Map params)throws Exception;
	public abstract int checkMonthInfoList(@SuppressWarnings("rawtypes") Map params)throws Exception;
}
