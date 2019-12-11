package com.chinauicom.research.stockmanagement.bi.autooperation.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chinauicom.research.commons.dao.BaseDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSceneBranch;

@Repository
public class AutoOperationSceneBranchDao extends BaseDao{
	public AutoOperationSceneBranch inserAutoOperationSceneBranch(AutoOperationSceneBranch info) throws Exception{
		return (AutoOperationSceneBranch)insert("Auto_Operation_SceneBranch.inserAutoOperationSceneBranch",info);
	}
	
	public void deleteAutoOperationSceneBranch(Map<String,String> info) throws Exception{
		delete("Auto_Operation_SceneBranch.deleteAutoOperationSceneBranch",info);
	}
}
