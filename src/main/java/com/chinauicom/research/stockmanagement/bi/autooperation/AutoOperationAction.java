package com.chinauicom.research.stockmanagement.bi.autooperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinauicom.research.commons.Message;
import com.chinauicom.research.commons.constant.WcsSessionConstant;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationStatisticService;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationMonthInfoHandlerService;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationService;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationSwitchService;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthInfoList;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthWeb;
import com.chinauicom.research.stockmanagement.bi.system.operator.entity.SysOperator;
import com.google.gson.Gson;

@Controller
@RequestMapping("/autooperationList")
public class AutoOperationAction {
	protected final  Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private AutoOperationStatisticService statistic;
	@Resource
	private AutoOperationService autoOperation;
	@Resource
	private AutoOperationSwitchService switchService;
	@Resource
	private AutoOperationMonthInfoHandlerService autoOperationMonthInfoHandlerService;
	@RequestMapping("/monthInfoRecord.do")
	public String getAutoOperationInfoList(HttpServletRequest request){
		try{
			String year=null;
			String month=null;
			SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
			List<AutoOperationSwitch> autoSwitch = new ArrayList<AutoOperationSwitch>();
	        autoSwitch = statistic.selectSwitch();
	        for(int i=0; i < autoSwitch.size(); i++){
	        	AutoOperationSwitch as = autoSwitch.get(i);
	        	year = as.getYear();
	        	month = as.getMonth();
	        }
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("orgCode", operator.getOrgCode());
		}catch(Exception e){
			log.error("获取月数据出错:",e);
		}
		return "autooperation/monthInfoManager";
	}
	@RequestMapping(value="/getMonthInfoList.do",produces="text/html;charset=UTF-8")
	@ResponseBody
    public String getMonthInfoList(HttpServletRequest request)throws Exception{
        Map<String,Object>paramMap=new HashMap<>();
        String orgCode=request.getParameter("orgCode");
        String year=request.getParameter("year");
        paramMap.put("orgCode", orgCode);
        paramMap.put("year", year);
        List<AutoOperationMonthInfoList> list = new ArrayList<AutoOperationMonthInfoList>();
        String json = null;
        
        try {
            log.debug("开始查询！");
            list=autoOperation.selectMonthInfoList(paramMap);
            Gson gs = new Gson();
			json = gs.toJson(list);
            log.debug("查询列表结束");
            
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return json;
    }
	@RequestMapping("/check.do")
	public String getAutoOperationInfo(String year,String month,String orgCode,HttpServletRequest request){
		try{
			SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
			List<AutoOperationMonthWeb> alist = autoOperationMonthInfoHandlerService.getAutoOperationMonthInfo(year, month, orgCode);
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("province", operator.getOrgCode());
			request.setAttribute("orgCode", orgCode);
			request.setAttribute("monthInfo", alist);
		}catch(Exception e){
			log.error("获取月数据出错:",e);
		}
		return "autooperation/checkMonthInfo";
	}
	@RequestMapping("/monthInfoSwitch.do")
	public String getAutoOperationSwitch(HttpServletRequest request){
		try{
			String year=null;
			String month=null;
			String switchStatus=null;
			List<AutoOperationSwitch> autoSwitch = new ArrayList<AutoOperationSwitch>();
	        autoSwitch = statistic.selectSwitch();
	        for(int i=0; i < autoSwitch.size(); i++){
	        	AutoOperationSwitch as = autoSwitch.get(i);
	        	year = as.getYear();
	        	month = as.getMonth();
	        	switchStatus = as.getSwitchStatus();
	        }
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("switchStatus", switchStatus);
		}catch(Exception e){
			log.error("获取月数据出错:",e);
		}
		return "autooperation/monthInfoSwitch";
	}
	@RequestMapping("/updateSwitchStatus.do")
    public @ResponseBody boolean updateFinalStatus(HttpServletRequest request)throws Exception{
		Map<String,Object>paramMap=new HashMap<>();
		Map<String,Object>nextMonthMap=new HashMap<>();
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        String switchType=request.getParameter("switchType");
        paramMap.put("year", year);
        paramMap.put("month", month);
        paramMap.put("switchType", switchType);
        
        String nextYear=null;
        String nextMonth=null;
        if (month.equals("12")){
        	nextMonth = "01";
        	nextYear = String.valueOf(Integer.valueOf(year)+1);
        }else{
        	nextMonth = String.format("%02d",Integer.valueOf(month)+1);
        	nextYear = year;
        }
        nextMonthMap.put("year", nextYear);
        nextMonthMap.put("month", nextMonth);
        if(autoOperation.updateSwitchStatus(paramMap)){
        	if(autoOperation.checkNextSwitch(nextMonthMap)==0){
        		String nextSwitchType="1";
                nextMonthMap.put("switchType", nextSwitchType);
                if(autoOperation.insertSwitchStatus(nextMonthMap)){
                	nextMonthMap.put("sceneId", "1");
                	nextMonthMap.put("sceneName", "总部场景");
                	switchService.insertModelScene(nextMonthMap);
                	nextMonthMap.put("sceneId", "2");
                	nextMonthMap.put("sceneName", "省分场景");
                	switchService.insertModelScene(nextMonthMap);
                	return true;
                }
        	}else{
        		String nextSwitchType=null;
        		if(switchType.equals("1")){
        			nextSwitchType="0";
        		}else if(switchType.equals("0")){
        			nextSwitchType="1";
        		}
                nextMonthMap.put("switchType", nextSwitchType);
                if(autoOperation.updateSwitchStatus(nextMonthMap)){
                	return true;
                }
        	}
        }
        return false;
	}
}
