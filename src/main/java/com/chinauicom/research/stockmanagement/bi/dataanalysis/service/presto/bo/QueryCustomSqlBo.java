package com.chinauicom.research.stockmanagement.bi.dataanalysis.service.presto.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinauicom.research.stockmanagement.bi.dataanalysis.dao.CustomSqlDo;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomSql;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.service.QueryCustomSqlService;
import com.chinauicom.research.commons.utils.Page;

/**
 * 执行自定义查询语句服务实现
 * 适用于Presto数据源
 * @author zhaich5
 * @since 2018/1/9
 *
 */
@Service
public class QueryCustomSqlBo implements QueryCustomSqlService {

	@Autowired
	private CustomSqlDo customSqlDo;

	@Override
	public List<Map<String, Object>> queryCustomSql(CustomSql sql) 
			throws Exception {
		return customSqlDo.selectByCustomSql(sql);
	}

	@Override
	public Page queryCustomSql(CustomSql sql, int currPage, int pageSize) 
			throws Exception {
		return customSqlDo.selectByCustomSql(sql, currPage, pageSize);
	}
	
}
