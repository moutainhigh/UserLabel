package com.chinauicom.research.stockmanagement.bi.autooperation.service.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinauicom.research.stockmanagement.bi.autooperation.dao.AutoOperationMonthInfoHandlerDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.dao.AutoOperationSceneBranchDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.dao.AutoOperationDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthDetail;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthInfo;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthWeb;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSceneBranch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatistic;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationMonthInfoHandlerService;

@Service
public class AutoOperationMonthInfoHandlerBo implements AutoOperationMonthInfoHandlerService {

	@Autowired
	private AutoOperationMonthInfoHandlerDao autoOperationMonthInfoHandlerDao;
	@Autowired
	private AutoOperationSceneBranchDao autoOperationSceneBranchDao;
	@Autowired
	private AutoOperationDao autoOperationDao;
	@Override
	public List<AutoOperationMonthWeb> getAutoOperationMonthInfo(String year, String month, String orgCode)throws Exception {
		Map<String,String> info = new HashMap<String,String>();
		info.put("year", year);
		info.put("month", month);
		info.put("province_code", orgCode);
		info.put("id", "");
		List<AutoOperationMonthWeb> alist = new ArrayList<AutoOperationMonthWeb>();
		int total = autoOperationMonthInfoHandlerDao.selectMonthInfoCount(info);
		if(total == 0 ){
			alist = autoOperationMonthInfoHandlerDao.selectAutoOperationMonthInfoFirst(info);
		}else{
			//获取已经填写的运营数据
			alist = autoOperationMonthInfoHandlerDao.selectAutoOperationMonthInfo(info);
		}
		
		return alist;
	}
	@Override
	@Transactional
	public int addAutoOperationMonthInfo(AutoOperationMonthWeb dlist) throws RuntimeException,Exception {
		List<AutoOperationMonthDetail> lMonthDetail  = dlist.getDlist();
		//先删除当月运营数据
		Map<String,String> dinfo = new HashMap<String,String>();
		dinfo.put("year", lMonthDetail.get(0).getYear());
		dinfo.put("month", lMonthDetail.get(0).getMonth());
		dinfo.put("province_code", lMonthDetail.get(0).getProvinceCode());
		autoOperationMonthInfoHandlerDao.deleteAutoOperationMonthInfo(dinfo);
		//删除省分场景
		autoOperationSceneBranchDao.deleteAutoOperationSceneBranch(dinfo);
		if(autoOperationDao.checkMonthInfoList(dinfo)==0){
			autoOperationDao.insertMonthInfoList(dinfo);
		}
		//再增加当月新的运营数据
		List<AutoOperationMonthInfo>  lMonthInfo = new ArrayList<AutoOperationMonthInfo>();
		for(AutoOperationMonthDetail monthDetail : lMonthDetail){
			if(monthDetail.getSceneType().equals("1")){//总部场景,入MonthInfo表
				lMonthInfo.add(handleAutoOperationMonthDetail(monthDetail));
			}else if(monthDetail.getSceneType().equals("2")){//省份场景，先入省分场景表，再入monthInfo表
				if(monthDetail.getSceneName() != null && StringUtils.isNotBlank(monthDetail.getSceneName())){//新加入的省分场景
					AutoOperationSceneBranch branch = new AutoOperationSceneBranch();
					branch.setCategoryId(monthDetail.getCategoryId());
					branch.setMonth(monthDetail.getMonth());
					branch.setName(monthDetail.getSceneName());
					branch.setProvinceCode(monthDetail.getProvinceCode());
					branch.setRemark(monthDetail.getSceneRemark());
					branch.setYear(monthDetail.getYear());
					monthDetail.setSceneId(autoOperationSceneBranchDao.inserAutoOperationSceneBranch(branch).getId());
					lMonthInfo.add(handleAutoOperationMonthDetail(monthDetail));
				}
				
				
			}
		}
		autoOperationMonthInfoHandlerDao.insertAutoOperationMonthInfo(lMonthInfo);
		
		//20%
		List<AutoOperationStatistic> statistic1 = new ArrayList<AutoOperationStatistic>();
		List<AutoOperationStatistic> statistic2 = new ArrayList<AutoOperationStatistic>();
		statistic1=autoOperationMonthInfoHandlerDao.selectTotalInfo(dinfo);
		Map<String,String> dinfo2 = new HashMap<String,String>();
		String lastYear=null;
		String lastMonth=null;
		float reachNumber=0;
		float orderNumber=0;
		float dataplantIncome=0;
		float lastReachNumber=0;
		float lastOrderNumber=0;
		float lastDataplantIncome=0;
		//lMonthDetail.get(0).getYear()
		if(lMonthDetail.get(0).getMonth().equals("01")){
	        lastMonth = "12";
	        lastYear =String.valueOf(Integer.valueOf(lMonthDetail.get(0).getYear())-1);
        }else{
        	lastMonth = String.format("%02d",Integer.valueOf(lMonthDetail.get(0).getMonth())-1);
        	lastYear = lMonthDetail.get(0).getYear();
        }
		dinfo2.put("year", lastYear);
		dinfo2.put("month", lastMonth);
		dinfo2.put("province_code", lMonthDetail.get(0).getProvinceCode());
		statistic2=autoOperationMonthInfoHandlerDao.selectTotalInfo(dinfo2);
		for(int i=0; i < statistic1.size(); i++){
			AutoOperationStatistic aos = statistic1.get(i);
			reachNumber=aos.getReachNumber();
			orderNumber=aos.getOrderNumber();
			dataplantIncome=aos.getDataplantIncome();
		}
		for(int i=0; i < statistic2.size(); i++){
			AutoOperationStatistic aos = statistic2.get(i);
			lastReachNumber=aos.getReachNumber();
			lastOrderNumber=aos.getOrderNumber();
			lastDataplantIncome=aos.getDataplantIncome();
		}
		float rate1 = reachNumber/lastReachNumber;
		float rate2 = orderNumber/lastOrderNumber;
		float rate3 = dataplantIncome/lastDataplantIncome;
		if(rate1<0.8||rate1>1.2){
			return 1;
		}else if(rate2<0.8||rate2>1.2){
			return 2;
		}else if(rate3<0.8||rate3>1.2){
			return 3;
		}else{
			return 0;
		}
		
	}
	
	public AutoOperationMonthInfo handleAutoOperationMonthDetail(AutoOperationMonthDetail monthDetail){
		AutoOperationMonthInfo monthInfo = new AutoOperationMonthInfo();
		monthInfo.setCategoryId(monthDetail.getCategoryId());
		monthInfo.setDataplantIncome(monthDetail.getDataplantIncome());
		monthInfo.setMonth(monthDetail.getMonth());
		monthInfo.setOrderNumber(monthDetail.getOrderNumber());
		monthInfo.setProvinceCode(monthDetail.getProvinceCode());
		monthInfo.setReachNumber(monthDetail.getReachNumber());
		monthInfo.setSceneId(monthDetail.getSceneId());
		monthInfo.setSceneType(monthDetail.getSceneType());
		monthInfo.setYear(monthDetail.getYear());
		
		return   monthInfo;
	}

}
