package com.chinauicom.research.stockmanagement.bi.autooperation.service.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinauicom.research.stockmanagement.bi.autooperation.dao.AutoOperationCategoryDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationCategoryService;

@Service
public class AutoOperationCategoryBo implements AutoOperationCategoryService {
	@Autowired
	private AutoOperationCategoryDao autoOperationCategoryDao;
	@Override
	public AutoOperationCategory getCategoryById(String id) throws Exception {
		// TODO Auto-generated method stub
		return autoOperationCategoryDao.getCategoryById(id);
	}

}
