package com.chinauicom.research.stockmanagement.bi.autooperation.service;

import java.util.List;
import java.util.Map;

import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatistic;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartSceneTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartIncomeRate;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticChart;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticRateChart;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticValue;

public abstract interface AutoOperationStatisticService {
	public List<AutoOperationStatistic> selectStatisticTotal(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatistic> selectStatisticDetail(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public int selectCategoryCount() throws Exception;
	public String selectDatapackage(String categoryId) throws Exception;
	public List<AutoOperationCategory> selectCategory() throws Exception;
	public List<AutoOperationSwitch> selectSwitch() throws Exception;
	public List<AutoOperationSwitch> selectSwitchClose() throws Exception;
	public List<AutoOperationChartTotal> selectChartTotalByYear(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationChartTotal> selectChartTotal(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationChartSceneTotal> selectChartSceneTotal(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationChartIncomeRate> selectIncomeRate(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticChart> selectStatisticChart(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticChart> selectStatisticChart2(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticRateChart> selectStatisticChart3(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticChart> selectStatisticChart4(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticChart> selectStatisticChart5(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticChart> selectStatisticChart6(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<Map<String,Object>> selectExportScene(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticValue> selectStatisticNewValue(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticValue> selectStatisticNewValue1(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticValue> selectStatisticNewValue2(@SuppressWarnings("rawtypes") Map params) throws Exception;
	public List<AutoOperationStatisticValue> selectStatisticNewSceneValue(@SuppressWarnings("rawtypes") Map params) throws Exception;
}
