package com.chinauicom.research.stockmanagement.bi.autooperation.service;

import java.util.List;
import java.util.Map;

import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;

/**
 * 
 * @ClassName: AutoOperationSwitchService 
 * @Description: (编辑开关接口) 
 * @author jiajie
 *
 */
public interface AutoOperationSwitchService {
	/**
	 * 查找所有数据库记录
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，
     * AutoOperationSwitch类型  操作失败返回null
     */
	public List<AutoOperationSwitch> getAll() throws Exception;
    
    /**
     * 查询单月开关状态
     * @param String year
     * @param String month
     * @return type : int  返回查询结果，修改接口是否开启，操作失败返回null
     * @throws Exception
     */
	public int branchInpoutStatus(String year, String month) throws Exception;
	
	/**
     * 关闭当前月开关并插入一条下月记录为开启状态
     * @param AutoOperationSwitch record
     * @return type : int 1 成功
     * @throws Exception
     */
	public int disableSingleYearAddNew(AutoOperationSwitch record) throws Exception;
	
	/**
     * 打开当前月开关
     * @param AutoOperationSwitch record
     * @return type : int 1 成功
     * @throws Exception
     */
	public int enableSingleYear(AutoOperationSwitch record) throws Exception;
	
	/**
     * 关闭当前月开关
     * @param AutoOperationSwitch record
     * @return type : int 1 成功
     * @throws Exception
     */
	public int disableSingleYear(AutoOperationSwitch record) throws Exception;
	public String selectMonthByYear(String year) throws Exception;
	public void insertModelScene(@SuppressWarnings("rawtypes") Map params) throws Exception;

}
