package com.chinauicom.research.stockmanagement.bi.dataanalysis.service;

import java.util.List;
import java.util.Map;

import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomColumns;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomSql;


/**
 * 数据分析服务接口
 * 
 * @author zhaich5
 * @since 2018/6/4
 * @version 1.0
 * 
 */
public interface DataAnalysisService {
	
	/**
	 * 查询用户全部自定义表信息列表
	 * @param operId 用户ID
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
    public List<CustomColumns> queryUserCustomTableList(String operId) 
    		throws Exception;
    
	/**
	 * 查询组织机构全部自定义表信息列表
	 * @param orgCode 组织机构编码
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
    public List<CustomColumns> queryOrgCustomTableList(String orgCode) 
    		throws Exception;
    
	/**
	 * 查询指定表字段信息列表
     * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
     */
    public List<CustomColumns> queryTableColumnList(String columnCode) 
    		throws Exception;
    
    /**
     * 查询自定义SQL
     * @param sql
     * 			自定义sql对象
     * @return list
     * 			
     * @throws Exception
     */
    public List<Map<String, Object>> queryCustomSql(CustomSql sql) 
    		throws Exception;
    
}
