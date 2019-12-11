package com.chinauicom.research.stockmanagement.bi.autooperation.dao;

import org.springframework.stereotype.Repository;

import com.chinauicom.research.commons.dao.BaseDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;

@Repository
public class AutoOperationCategoryDao  extends BaseDao{
	
	public AutoOperationCategory getCategoryById(String id){
		return (AutoOperationCategory)getSqlSession().selectOne("Auto_Operation_Category.getCategoryById", id);
	}
}
