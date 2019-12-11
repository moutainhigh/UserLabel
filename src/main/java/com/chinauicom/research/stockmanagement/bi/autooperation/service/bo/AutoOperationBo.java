package com.chinauicom.research.stockmanagement.bi.autooperation.service.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationService;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthInfoList;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.dao.AutoOperationDao;
@Service
public class AutoOperationBo implements AutoOperationService {
	@Autowired
	private AutoOperationDao autoOperationDao;
	@SuppressWarnings({ "rawtypes" })
	public List<AutoOperationMonthInfoList> selectMonthInfoList(Map params) throws Exception{
		if (params == null) return null;
		return autoOperationDao.selectMonthInfoList(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public boolean updateSwitchStatus(Map params) throws Exception{
		autoOperationDao.updateSwitchStatus(params);
		return true;
	}
	@SuppressWarnings({ "rawtypes" })
	public boolean insertSwitchStatus(Map params) throws Exception{
		autoOperationDao.insertSwitchStatus(params);
		return true;
	}
	@SuppressWarnings({ "rawtypes" })
	public int checkNextSwitch(Map params) throws Exception{
		if (params == null) return 1;
		return autoOperationDao.checkNextSwitch(params);
	}
	@SuppressWarnings({ "rawtypes" })
	public int checkMonthInfoList(Map params) throws Exception{
		if (params == null) return 1;
		return autoOperationDao.checkMonthInfoList(params);
	}
}
