package com.chinauicom.research.stockmanagement.bi.autooperation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinauicom.research.commons.dao.BaseDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthInfo;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthWeb;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatistic;

@Repository
public class AutoOperationMonthInfoHandlerDao extends BaseDao{
	@SuppressWarnings("unchecked")
	public List<AutoOperationMonthWeb> selectAutoOperationMonthInfo(Map<String,String> info) throws Exception {
		return queryForList("Auto_Operation_Month_Info.selectAutoOperationMonthInfo", info);
	}
	
	@SuppressWarnings("unchecked")
	public List<AutoOperationMonthWeb> selectAutoOperationMonthInfoFirst(Map<String,String> info) throws Exception{
		return queryForList("Auto_Operation_Scene_Hq.selectAutoOperationMonthInfoFirst", info);
	}
	
	@SuppressWarnings("unchecked")
	public List<AutoOperationMonthWeb> selectAutoOperationMonthInfoDetail(Map<String,String> info) throws Exception{
		return queryForList("Auto_Operation_Month_Info.selectAutoOperationMonthInfoDetail", info);
	}
	
	public int selectMonthInfoCount(Map<String,String> info) throws Exception{
		return queryForInt("Auto_Operation_Month_Info.selectMonthInfoCount", info);
	}
	
	public int deleteAutoOperationMonthInfo(Map<String,String> info) throws Exception{
		return delete("Auto_Operation_Month_Info.deleteAutoOperationMonthInfo", info);
	}
	
	public void insertAutoOperationMonthInfo(List<AutoOperationMonthInfo> list) throws Exception{
		getSqlSession().insert("Auto_Operation_Month_Info.insertAutoOperationMonthInfo", list);
	}
	public List<AutoOperationStatistic> selectTotalInfo(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("Auto_Operation_Month_Info.selectTotalInfo",params);
	}
}
