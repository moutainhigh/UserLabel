package com.chinauicom.research.stockmanagement.bi.dataanalysis;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinauicom.research.commons.Message;
import com.chinauicom.research.commons.constant.WcsSessionConstant;
import com.chinauicom.research.commons.exception.BizException;
import com.chinauicom.research.commons.utils.Page;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.ConditionalExpression;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomColumns;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomDataInVo;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.entity.CustomSql;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.service.DataAnalysisService;
import com.chinauicom.research.stockmanagement.bi.dataanalysis.service.QueryCustomSqlService;
import com.chinauicom.research.stockmanagement.bi.system.operator.entity.SysOperator;

/**
 * 数据分析Controller
 * 
 * @author zhaich5
 * @since 2018/6/4
 * @version 1.0
 * 
 */
@Controller
@RequestMapping("/dataAnalysis")
public class DataAnalysisAction {
	
	/**
	 * 日志
	 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataAnalysisService dataAnalysisService;
	
	@Autowired
	private QueryCustomSqlService queryCustomSqlService;
	
	/**
     * 获取用户自定义表信息列表
     * @param request
     * @return
     * @throws BizException
     */
	@RequestMapping("/getCustomTableList.do")
	public @ResponseBody List<CustomColumns> getCustomTableList(
			HttpServletRequest request) throws BizException {
		LOG.info("开始获取用户自定义表信息列表");
		HttpSession session = request.getSession();
		SysOperator operator = (SysOperator) session
				.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		if (null == operator || StringUtils.isEmpty(operator.getOperId())) {
			return null;
		}
		//String operId = operator.getOperId(); //获取操作用户ID
		String orgCode = operator.getOrgCode(); //组织编码
		
		List<CustomColumns> list = null;
		
		try {
			list = dataAnalysisService.queryOrgCustomTableList(orgCode);
		} catch (Exception e) {
			LOG.error("获取用户自定义表信息列表异常", e);
			throw new BizException(BizException.FAILURE, "获取用户自定义表信息列表异常");
		}
		
		LOG.info("获取用户自定义表信息列表结束");
		return list;
	}
	
	/**
	 * 获取表的字段信息列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getTableColumn.do")
	public @ResponseBody List<CustomColumns> getTableColumn(HttpServletRequest request) 
			throws Exception {
		
		LOG.info("开始获取表的字段信息列表");
		HttpSession session = request.getSession();
		SysOperator operator = (SysOperator) session
				.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		if (null == operator || StringUtils.isEmpty(operator.getOperId())) {
			return null;
		}
		
		String tableCode = request.getParameter("tableCode");// 表编码
		
		List<CustomColumns> list = null;
			
		try {
			list = dataAnalysisService.queryTableColumnList(tableCode);
		} catch (Exception e) {
			LOG.error("获取表的字段信息列表异常", e);
			throw new BizException(BizException.FAILURE, "获取表的字段信息列表异常");
		}
		
		LOG.info("获取表的字段信息列表结束");
		
		return list;
		
	}
	
    /**
     * 执行定制数据
     * @param request
     * @return map</br>
     * 			map.msg：操作结果信息</br>
     * 			map.dataName：定制数据名称</br>
     * 			map.titleList：定制数据标题列表</br>
     * 			map.bodyList：定制数据数据体列表</br>
     * @throws ParseException 
     * @throws Exception
     */
    @RequestMapping("/executeCustomData.do")
    public @ResponseBody Map<String, Object> executeCustomData(
    		@RequestBody CustomDataInVo inVo, 
    		HttpServletRequest request) 
    		throws BizException, ParseException {
    	
    	LOG.info("开始执行定制数据");
		HttpSession session = request.getSession();
		SysOperator operator = (SysOperator) session
				.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		if (null == operator || StringUtils.isEmpty(operator.getOperId())) {
			return null;
		}
		
		//定义返回对象
		Map<String, Object> map = new HashMap<String, Object>();
		Message msg = new Message(); //定义返回操作结果信息对象
		
		//String operId = operator.getOperId(); //获取操作用户ID
		
		List<Map<String, Object>> bodyList = null;
		try {
			//bodyList = dataAnalysisService.queryCustomSql( //mysql数据源
			bodyList = queryCustomSqlService.queryCustomSql( //presto数据源
					inVo.getCustomSql());
		} catch (Exception e) {
			LOG.error("查询自定义Sql错误", e);
			msg.setFlag(false);
			msg.setMsg("查询自定义Sql错误:" + e.getMessage());
			map.put("msg", msg);
			return map;
		}
		
		msg.setFlag(true);
		msg.setMsg("执行定制数据成功");
		map.put("msg", msg);
		map.put("dataName", inVo.getDataName());
		map.put("titleList", inVo.getCustomSql().getTargetColumnExpressionList());
		map.put("bodyList", bodyList);
		
		LOG.info("执行定制数据结束");
		
		return map;
		
    }
    
    /**
     * 执行定制数据（分页）
     * @param request
     * @return map</br>
     * 			map.msg：操作结果信息</br>
     * 			map.dataName：定制数据名称</br>
     * 			map.titleList：定制数据标题列表</br>
     * 			map.page：定制数据数据体列表分页对象</br>
     * @throws ParseException 
     * @throws Exception
     */
    @RequestMapping("/executeCustomDataForPage.do")
    public @ResponseBody Map<String, Object> executeCustomDataForPage(
    		@RequestBody CustomDataInVo inVo, 
    		HttpServletRequest request) 
    		throws BizException, ParseException {
    	
    	LOG.info("开始执行定制数据");
		HttpSession session = request.getSession();
		SysOperator operator = (SysOperator) session
				.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		if (null == operator || StringUtils.isEmpty(operator.getOperId())) {
			return null;
		}
		
		//分页参数
		int currPage = inVo.getCurrentPage() == 0 ? 1 : inVo.getCurrentPage(); //当前页码
		int pageSize = inVo.getPageSize(); //当前页记录数
		
		//定义返回对象
		Map<String, Object> map = new HashMap<String, Object>();
		Message msg = new Message(); //定义返回操作结果信息对象
		
		//String operId = operator.getOperId(); //获取操作用户ID
		
		formatConditionalExpressionType(inVo.getCustomSql());
		
		Page page = null;
		try {
			page = queryCustomSqlService.queryCustomSql( //presto数据源
					inVo.getCustomSql(), currPage, pageSize);
		} catch (Exception e) {
			LOG.error("查询自定义Sql错误", e);
			msg.setFlag(false);
			msg.setMsg("查询自定义Sql错误:" + e.getMessage());
			map.put("msg", msg);
			return map;
		}
		
		msg.setFlag(true);
		msg.setMsg("执行定制数据成功");
		map.put("msg", msg);
		map.put("dataName", inVo.getDataName());
		map.put("titleList", inVo.getCustomSql().getTargetColumnExpressionList());
		map.put("page", page);
		
		LOG.info("执行定制数据结束");
		
		return map;
		
    }
    
    /**
     * 格式化条件表达式类型
     * @param customSql
     */
    private void formatConditionalExpressionType(CustomSql customSql) {
    	//XXX 有待优化，因为现在只应用到String，Integer，Double类型，所以其他类型未添加 
    	if (customSql != null) {
        	//处理条件表达式
        	if (customSql.getConditionalExpressionList() != null) {
        		for (ConditionalExpression conditionalExpression : customSql.getConditionalExpressionList()) {
        			if ("Integer".equals(conditionalExpression.getJavaType())) {
        				conditionalExpression.setValue(Integer.parseInt((String) conditionalExpression.getValue()));
        			}else if ("Double".equals(conditionalExpression.getJavaType())) {
        				conditionalExpression.setValue(Double.parseDouble((String) conditionalExpression.getValue()));
        			}
        		}
        	}
        	//处理分组条件表达式
        	if (customSql.getGroup() != null && customSql.getGroup().getConditionalExpressionList() != null) {
        		for (ConditionalExpression conditionalExpression : customSql.getGroup().getConditionalExpressionList()) {
        			if ("Integer".equals(conditionalExpression.getJavaType())) {
        				conditionalExpression.setValue(Integer.parseInt((String) conditionalExpression.getValue()));
        			}else if ("Double".equals(conditionalExpression.getJavaType())) {
        				conditionalExpression.setValue(Double.parseDouble((String) conditionalExpression.getValue()));
        			}
        		}
        	}
    	}

    }
	
}
