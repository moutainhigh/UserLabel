package com.chinauicom.research.stockmanagement.bi.dataanalysis.service.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinauicom.research.stockmanagement.bi.dataanalysis.dao.CustomColumnsDo;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.dao.CustomSqlDo;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomColumns;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomSql;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.service.DataAnalysisService;

/**
 * 数据分析服务实现
 * @author zhaich5
 * @since 2018/1/9
 *
 */
@Service
public class DataAnalysisBo implements DataAnalysisService {

	@Autowired
	private CustomColumnsDo customColumnsDo;
	
	@Autowired
	private CustomSqlDo customSqlDo;
	
	@Override
	public List<CustomColumns> queryUserCustomTableList(String operId) 
			throws Exception {
		return customColumnsDo.selectLevelOneCustomColumnListByUser(operId);
	}

	@Override
	public List<CustomColumns> queryOrgCustomTableList(String orgCode) 
			throws Exception {
		return customColumnsDo.selectLevelOneCustomColumnListByOrg(orgCode);
	}
	
	@Override
	public List<CustomColumns> queryTableColumnList(String columnCode) 
			throws Exception {
		return customColumnsDo
				.selectChildrenColumnListByColumnCode(columnCode);
	}

	@Override
	public List<Map<String, Object>> queryCustomSql(CustomSql sql) 
			throws Exception {
		return customSqlDo.selectByCustomSql(sql);
	}

}
