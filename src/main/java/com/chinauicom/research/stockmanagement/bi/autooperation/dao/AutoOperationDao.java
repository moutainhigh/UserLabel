package com.chinauicom.research.stockmanagement.bi.autooperation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthInfoList;
import com.chinauicom.research.commons.dao.BaseDao;

@Repository
public class AutoOperationDao extends BaseDao {

	public List<AutoOperationMonthInfoList> selectMonthInfoList(@SuppressWarnings("rawtypes") Map params) throws Exception{
		return getSqlSession().selectList("Auto_Operation.selectMonthInfoList",params);
	}
	public void updateSwitchStatus(@SuppressWarnings("rawtypes") Map params)throws Exception {
		getSqlSession().update("Auto_Operation.updateSwitchStatus",params);
	}
	public void insertSwitchStatus(@SuppressWarnings("rawtypes") Map params)throws Exception {
		getSqlSession().insert("Auto_Operation.insertSwitchStatus",params);
	}
	public int checkNextSwitch(@SuppressWarnings("rawtypes") Map params)throws Exception {
		return (int)getSqlSession().selectOne("Auto_Operation.checkNextSwitch", params);
	}
	public int checkMonthInfoList(@SuppressWarnings("rawtypes") Map params)throws Exception {
		return (int)getSqlSession().selectOne("Auto_Operation.checkMonthInfoList", params);
	}
	public void insertMonthInfoList(@SuppressWarnings("rawtypes") Map params)throws Exception {
		getSqlSession().insert("Auto_Operation.insertMonthInfoList",params);
	}
}
