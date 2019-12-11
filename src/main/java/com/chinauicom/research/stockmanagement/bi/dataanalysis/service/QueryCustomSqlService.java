package com.chinauicom.research.stockmanagement.bi.dataanalysis.service;

import java.util.List;
import java.util.Map;

import com.chinauicom.research.commons.utils.Page;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomSql;

/**
 * 执行自定义查询语句服务接口
 * 
 * @author zhaich5
 * @since 2018/7/12
 * @version 1.0
 * 
 */
public interface QueryCustomSqlService {
    
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
    
	/**
     * 查询自定义SQL(分页)
     * @param sql
     * 			自定义sql对象
     * @param currPage
     * 			当前页
     * @param pageSize
     * 			每页记录数
     * @return page
     * 			分页对象
     * @throws Exception
     */
    public Page queryCustomSql(CustomSql sql, int currPage, int pageSize) 
    		throws Exception;

}
