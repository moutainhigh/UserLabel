package com.chinauicom.research.stockmanagement.bi.autooperation.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinauicom.research.commons.dao.BaseDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;

/**
 * @file autoOperationDao.java
 * @author JiaJie
 * @version
 * 
 */
@Repository
public class AutoOperationSwitchDao extends BaseDao {
	
	
	/**
	 * 对数据库进行查询并返回一个AutoOperationSwitch list
	 * @return type : List 返回查询操作所有符合条件的记录的AutoOperationSwitch对象集合
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List selectAll() throws Exception {
		return queryForList("AUTO_OPERATION_SWITCH.selectAll", null);
	}
	/**
	 * 查询单个省分录入权限信息
	 * 
	 * @param AutoOperationSwitch record
	 * @return Integer
	 * @throws Exception
	 */
	public Integer checkBranchStatus(AutoOperationSwitch record) throws Exception {
		
		return (Integer) getSqlSession().selectOne("AUTO_OPERATION_SWITCH.selectByMonthAndYear", record);
	}

	/**
	 * 更新数据库中对象的状态为启用
	 * 
	 * @return type : int 返回操作是否成功
	 * @throws Exception
	 */
	public int enableSingleYear(AutoOperationSwitch record) throws Exception {
		String month = record.getMonth();
  		String year  = record.getYear();
		if (month != null && year != null) {
			update("AUTO_OPERATION_SWITCH.enableSingleYear", record);
			return 1;
		}else {
			return 0;
		}
	}

	/**
	 * 更新数据库中对象的状态为禁用
	 * 
	 * @return type : int 返回操作是否成功
	 * @throws Exception
	 */
	public int disableSingleYear(AutoOperationSwitch record) throws Exception {
		String month = record.getMonth();
  		String year  = record.getYear();
		if (month != null && year != null) {
			record.setSwitchStatus("0");
			update("AUTO_OPERATION_SWITCH.disableSingleYear", record);
			return 1;
		}else {
			return 0;
		}
	}

	/**
      	 * 插入下一月信息
      	 * @param String year String month
      	 * @return type : int 返回操作是否成功
      	 * @throws Exception
      	 */
	public int insertNewYear(AutoOperationSwitch record) throws Exception {
      		/*String currentYear = year.substring(0, 4); 
          	String currentMonth = year.substring(4);*/
		return getSqlSession().insert("AUTO_OPERATION_SWITCH.insertRec", record);
          }
	public void insertModelScene(@SuppressWarnings("rawtypes") Map params) throws Exception {
  		/*String currentYear = year.substring(0, 4); 
      	String currentMonth = year.substring(4);*/
		getSqlSession().insert("AUTO_OPERATION_SWITCH.insertModelScene", params);
    }
	public String selectMonthByYear(String year) throws Exception{
		return (String)getSqlSession().selectOne("AUTO_OPERATION_SWITCH.selectMonthByYear",year);
	}
}
