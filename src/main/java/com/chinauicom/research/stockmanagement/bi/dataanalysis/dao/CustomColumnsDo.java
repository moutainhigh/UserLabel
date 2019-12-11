/**
 * com.chinauicom.research.iotoperation.system.reportforms.dao.CustomColumnsDao.java
 */
package com.chinauicom.research.stockmanagement.bi.dataanalysis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chinauicom.research.commons.dao.BaseDao;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomColumns;

/**
 * 自定义列DAO
 * @author zhaich5
 * @since 2018/1/9
 *
 */
@Repository
public class CustomColumnsDo extends BaseDao {

    /**
	 * 查询全部自定义列信息列表
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectAllCustomColumnList() throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectAllCustomColumnList", null);
	}
	
    /**
	 * 查询全部固定列和自定义列信息列表
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectAllFixedAndCustomColumnList() throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectAllFixedAndCustomColumnList", null);
	}
	
    /**
	 * 查询全部非父节点的固定列信息列表，目前仅支持二级结构
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectAllFixedColumnList() throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectAllFixedColumnList", null);
	}
	
    /**
	 * 根据列编号集合查询自定义列信息列表
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectCustomColumnsByColumnCodes(
			List<CustomColumns> columnCodeList) 
					throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectCustomColumnsByColumnCodes", 
				columnCodeList);
	}
	
	/**
	 * 根据模板编号查询模板的自定义列信息列表
	 * @param templateId
	 * 				模板编号
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectCustomColumnsByTemplateId(String templateId) 
			throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectCustomColumnsByTemplateId", 
				templateId);
	}
	
    /**
	 * 查询全部一级自定义列信息列表（即所有表编码信息列表）
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectLevelOneCustomColumnList() throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectLevelOneCustomColumnList", null);
	}
	
    /**
	 * 查询用户全部一级自定义列信息列表（即所有用户表编码信息列表）
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectLevelOneCustomColumnListByUser(
			String operId) throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectLevelOneCustomColumnListByUser", operId);
	}
	
    /**
	 * 查询组织机构全部一级自定义列信息列表（即组织机构所有表编码信息列表）
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectLevelOneCustomColumnListByOrg(
			String orgCode) throws Exception {
		return queryForList("CUSTOM_COLUMNS.selectLevelOneCustomColumnListByOrg", orgCode);
	}
	
    /**
	 * 根据列编码递归查询子节点自定义列信息列表
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectChildrenCustomColumnListByColumnCode(
			String columnCode) throws Exception {
		return queryForList(
				"CUSTOM_COLUMNS.selectChildrenCustomColumnListByColumnCode", 
				columnCode);
	}
	
    /**
	 * 根据列编码递归查询子节点字段类型的自定义列信息列表
	 * @return type : List 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<CustomColumns> selectChildrenColumnListByColumnCode(
			String columnCode) throws Exception {
		return queryForList(
				"CUSTOM_COLUMNS.selectChildrenColumnListByColumnCode", 
				columnCode);
	}
		
}
