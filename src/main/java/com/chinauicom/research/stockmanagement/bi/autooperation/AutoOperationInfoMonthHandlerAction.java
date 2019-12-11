package com.chinauicom.research.stockmanagement.bi.autooperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinauicom.research.commons.Message;
import com.chinauicom.research.commons.constant.WcsSessionConstant;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationMonthWeb;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationMonthInfoHandlerService;
import com.chinauicom.research.stockmanagement.bi.system.operator.entity.SysOperator;

@Controller
@RequestMapping("/autooperation")
public class AutoOperationInfoMonthHandlerAction {
	protected final  Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AutoOperationMonthInfoHandlerService autoOperationMonthInfoHandlerService;
	
	@RequestMapping("/edit.do")
	public String getAutoOperationInfo(String year,String month,HttpServletRequest request){
		try{
			SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
			List<AutoOperationMonthWeb> alist = autoOperationMonthInfoHandlerService.getAutoOperationMonthInfo(year, month, operator.getOrgCode());
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("monthInfo", alist);
		}catch(Exception e){
			log.error("获取月数据出错:",e);
		}
		return "autooperation/editMonthInfo";
	}
	
	@RequestMapping("/close.do")
	public String closeAutoOperationInfo(String year,String month,HttpServletRequest request){
		try{
			SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
			List<AutoOperationMonthWeb> alist = autoOperationMonthInfoHandlerService.getAutoOperationMonthInfo(year, month, operator.getOrgCode());
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("monthInfo", alist);
		}catch(Exception e){
			log.error("获取月数据出错:",e);
		}
		return "autooperation/CloseMonthInfo";
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public Message addAutoOperationInfo(AutoOperationMonthWeb dlist,HttpServletRequest request){
		Message msg = new Message();
		try{
			int type = autoOperationMonthInfoHandlerService.addAutoOperationMonthInfo(dlist);
			if(type==0){
				msg.setFlag(true);
				msg.setType("0");
				msg.setMsg("增加运营数据成功");
			}else if(type==1){
				msg.setFlag(true);
				msg.setType("1");
				msg.setMsg("数据已保存。注意：触达用户次数与上月差值超过20%");
			}else if(type==2){
				msg.setFlag(true);
				msg.setType("2");
				msg.setMsg("数据已保存。注意：成功订购产品数与上月差值超过20%");
			}else if(type==3){
				msg.setFlag(true);
				msg.setType("3");
				msg.setMsg("数据已保存。注意：流量包收入与上月差值超过20%");
			}
		}catch(Exception e){
			log.error("增加运营数据出错:",e);
			msg.setFlag(false);
			msg.setMsg("增加运营数据出错");
		}
		return msg;
	}
}
