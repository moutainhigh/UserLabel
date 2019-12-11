package com.chinauicom.research.stockmanagement.bi.autooperation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatistic;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartSceneTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartIncomeRate;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticChart;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticRateChart;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticValue;
import com.chinauicom.research.commons.dao.BaseDao;

@Repository
public class AutoOperationStatisticDao extends BaseDao {
	public List<AutoOperationStatistic> selectStatisticTotal(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticTotal",params);
	}
	public List<AutoOperationStatistic> selectStatisticDetail(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticDetail",params);
	}
	public int selectCategoryCount() throws Exception{
		return (int)getSqlSession().selectOne("AUTO_OPERATION_STATISTIC.selectCategoryCount");
	}
	public String selectDatapackage(String categoryId) throws Exception{
		return (String)getSqlSession().selectOne("AUTO_OPERATION_STATISTIC.selectDatapackage",categoryId);
	}
	public List<AutoOperationCategory> selectCategory() throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectCategory");
	}
	public List<AutoOperationSwitch> selectSwitch() throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectSwitch");
	}
	public List<AutoOperationSwitch> selectSwitchClose() throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectSwitchClose");
	}
	public List<AutoOperationChartTotal> selectChartTotal(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectChartTotal",params);
	}
	public List<AutoOperationChartTotal> selectChartTotalByYear(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectChartTotalByYear",params);
	}
	public List<AutoOperationChartSceneTotal> selectChartSceneTotal(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectChartSceneTotal",params);
	}
	public List<AutoOperationChartIncomeRate> selectIncomeRate(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectIncomeRate",params);
	}
	public List<AutoOperationStatisticChart> selectStatisticChart(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticChart",params);
	}
	public List<AutoOperationStatisticChart> selectStatisticChart2(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticChart2",params);
	}
	public List<AutoOperationStatisticRateChart> selectStatisticChart3(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticChart3",params);
	}
	public List<AutoOperationStatisticChart> selectStatisticChart4(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticChart4",params);
	}
	public List<AutoOperationStatisticChart> selectStatisticChart5(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticChart5",params);
	}
	public List<AutoOperationStatisticChart> selectStatisticChart6(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticChart6",params);
	}
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> selectExportScene(@SuppressWarnings("rawtypes") Map params) throws Exception {
		return queryForList("AUTO_OPERATION_STATISTIC.selectExportScene", params);
	}
	public List<AutoOperationStatisticValue> selectStatisticNewValue(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticNewValue",params);
	}
	public List<AutoOperationStatisticValue> selectStatisticNewValue1(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticNewValue1",params);
	}
	public List<AutoOperationStatisticValue> selectStatisticNewValue2(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticNewValue2",params);
	}
	public List<AutoOperationStatisticValue> selectStatisticNewSceneValue(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("AUTO_OPERATION_STATISTIC.selectStatisticNewSceneValue",params);
	}
}
