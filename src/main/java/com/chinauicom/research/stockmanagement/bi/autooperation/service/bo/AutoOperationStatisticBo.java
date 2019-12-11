package com.chinauicom.research.stockmanagement.bi.autooperation.service.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationStatisticService;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatistic;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.dao.AutoOperationStatisticDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartSceneTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartIncomeRate;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticChart;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticRateChart;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticValue;
@Service
public class AutoOperationStatisticBo implements AutoOperationStatisticService {
	@Autowired
	private AutoOperationStatisticDao statisticDao;
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatistic> selectStatisticTotal(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticTotal(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatistic> selectStatisticDetail(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticDetail(params);
	}
	public int selectCategoryCount() throws Exception{
		return statisticDao.selectCategoryCount();
	}
	@SuppressWarnings({ "rawtypes" })
	public String selectDatapackage(String categoryId) throws Exception{
		return statisticDao.selectDatapackage(categoryId);
	}
	public List<AutoOperationCategory> selectCategory() throws Exception{
		return statisticDao.selectCategory();
	}
	public List<AutoOperationSwitch> selectSwitch() throws Exception{
		return statisticDao.selectSwitch();
	}
	public List<AutoOperationSwitch> selectSwitchClose() throws Exception{
		return statisticDao.selectSwitchClose();
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationChartTotal> selectChartTotal(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectChartTotal(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationChartTotal> selectChartTotalByYear(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectChartTotalByYear(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationChartSceneTotal> selectChartSceneTotal(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectChartSceneTotal(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationChartIncomeRate> selectIncomeRate(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectIncomeRate(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticChart> selectStatisticChart(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticChart(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticChart> selectStatisticChart2(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticChart2(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticRateChart> selectStatisticChart3(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticChart3(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticChart> selectStatisticChart4(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticChart4(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticChart> selectStatisticChart5(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticChart5(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticChart> selectStatisticChart6(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticChart6(params);
	}
	public List<Map<String,Object>> selectExportScene(@SuppressWarnings("rawtypes") Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectExportScene(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticValue> selectStatisticNewValue(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticNewValue(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticValue> selectStatisticNewValue1(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticNewValue1(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticValue> selectStatisticNewValue2(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticNewValue2(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationStatisticValue> selectStatisticNewSceneValue(Map params) throws Exception{
		if (params == null) return null;
		return statisticDao.selectStatisticNewSceneValue(params);
	}
}
