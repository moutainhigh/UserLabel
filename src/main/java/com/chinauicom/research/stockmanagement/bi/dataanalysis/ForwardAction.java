package com.chinauicom.research.stockmanagement.bi.dataanalysis;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinauicom.research.commons.constant.WcsSessionConstant;
import com.chinauicom.research.stockmanagement.bi.system.organize.service.SysOrganizeService;
import com.chinauicom.research.stockmanagement.bi.system.operator.entity.SysOperator;

/**
 * 数据分析跳转Controller
 * 
 * @author zhaich5
 * @since 2018/6/4
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/dataAnalysis")
public class ForwardAction {
	
	/**
	 * 日志
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	SysOrganizeService sysOrganizeService;
    /**
     * 访问定制查询表单
     * @param request
     * @return 
     * @throws Exception
     */
    @RequestMapping("/toCustomQueryForm.do")
    public String toCustomQueryForm(HttpServletRequest request) 
    		throws Exception {
    	
    	LOG.info("开始访问定制查询表单");
    	LOG.info("访问定制查询表单结束");
    	SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
    	request.setAttribute("orgCode", operator.getOrgCode());
    	String orgName = sysOrganizeService.selectCategoryCount(operator.getOrgCode());
    	request.setAttribute("orgName", orgName);
        return "dataAnalysis/customDataPage";
    }
}
