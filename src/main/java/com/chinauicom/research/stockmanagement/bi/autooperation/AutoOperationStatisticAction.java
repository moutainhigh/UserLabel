package com.chinauicom.research.stockmanagement.bi.autooperation;

import com.google.gson.Gson;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationStatisticService;
import com.chinauicom.research.stockmanagement.bi.system.operator.entity.SysOperator;
import com.chinauicom.research.stockmanagement.bi.system.operator.service.bo.SysOperatorBo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatistic;
import com.chinauicom.research.commons.Message;
import com.chinauicom.research.commons.constant.WcsSessionConstant;
import com.chinauicom.research.commons.utils.DateUtils;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationCategory;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartSceneTotal;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationChartIncomeRate;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticChart;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticRateChart;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder; 
import java.util.*;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.OutputStream; 
import java.io.FileOutputStream;    
import java.io.IOException; 

@Controller
@RequestMapping("/statistic")
public class AutoOperationStatisticAction {
	private final Log log = LogFactory.getLog(SysOperatorBo.class);
	@Resource
	AutoOperationStatisticService statistic;
	
	@RequestMapping("/toStatisticPage.do")
    public String toFinancePage(HttpServletRequest request)throws  Exception{
		String year=null;
        String month=null;
        List<AutoOperationSwitch> autoSwitch = new ArrayList<AutoOperationSwitch>();
        autoSwitch = statistic.selectSwitch();
        for(int i=0; i < autoSwitch.size(); i++){
        	AutoOperationSwitch as = autoSwitch.get(i);
        	year = as.getYear();
        	month = as.getMonth();
        }
        request.setAttribute("Year",year);
        request.setAttribute("Month",month);
        return "statistic/statisticManager";
    }
	
	@RequestMapping("/toChart.do")
    public String toChart(HttpServletRequest request)throws  Exception{
        return "statistic/statisticChart";
    }
	
	@RequestMapping(value="/toStatisticList.do",produces="text/html;charset=UTF-8")
	@ResponseBody
    public String toFinanceList(HttpServletRequest request)throws  Exception{
		SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		String year=request.getParameter("year");
        String month=request.getParameter("month");
        String json = null;
        Map<String,Object> dataMap=new HashMap<>();
        try {
        	//查询表头
        	month = String.format("%02d",Integer.valueOf(month));
        	List<AutoOperationCategory> category = new ArrayList<AutoOperationCategory>();
        	log.debug("开始查询分类数目");
        	category = statistic.selectCategory();
        	List<String> categoryName = new ArrayList<String>();
        	List<Integer> width = new ArrayList<Integer>();
        	List<String> columnName = new ArrayList<String>();
        	categoryName.add("省分");
        	categoryName.add("汇总");
        	width.add(1);
        	width.add(3);
        	columnName.add("省分");
        	columnName.add("流量包总收入");
        	columnName.add("触达用户总数");
        	columnName.add("成功用户总数");
        	log.debug("录入汇总列名");
        	for(int i=0; i < category.size(); i++){
        		AutoOperationCategory ca = category.get(i);
        		categoryName.add(ca.getName());
        		if (ca.getDatapackageStatus().equals("1")){
        			width.add(8);
        			columnName.add("总部场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        			columnName.add("收入（万）");
        			columnName.add("省分场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        			columnName.add("收入（万）");
        		}else{
        			width.add(6);
        			columnName.add("总部场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        			columnName.add("省分场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        		}
        	}
        	dataMap.put("categoryName", categoryName);
        	dataMap.put("width", width);
        	dataMap.put("columnName", columnName);
        	//查询汇总内容
        	int startColumn = 4;
        	List<Map<String, Object>> dataList = selectStatisticTable(year,month,operator.getOrgCode(),startColumn);
        	
        	dataMap.put("dataList", dataList);
        	Gson gs = new Gson();
			json = gs.toJson(dataMap);
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return json;
    }
	
	@RequestMapping(value="/toChartTotal.do")
	@ResponseBody
    public List<AutoOperationChartTotal> toChartTotal(HttpServletRequest request)throws  Exception{
        Map<String,Object>paramMap=new HashMap<>();
        List<AutoOperationChartTotal> chart = new ArrayList<AutoOperationChartTotal>();
        try {
        	log.debug("开始查询当前月份");
        	checkSwitchCloseBetween(paramMap);//查询关账后最新月份
	        
	        log.debug("查询图表数据");
	        chart = statistic.selectChartTotal(paramMap);
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return chart;
    }
	
	@RequestMapping(value="/toChartScene.do",produces="text/html;charset=UTF-8")
	@ResponseBody
    public String toChartScene(HttpServletRequest request)throws  Exception{
		String orderBy=request.getParameter("orderBy");
        String json = null;
        Map<String,Object>paramMap=new HashMap<>();
        try {
        	checkSwitchClose(paramMap);//查询关账后最新月份
            paramMap.put("orderBy", orderBy+" desc");
	        List<AutoOperationStatistic> list = new ArrayList<AutoOperationStatistic>();
	        log.debug("查询图表数据");
	        list = statistic.selectStatisticTotal(paramMap);
	        Gson gs = new Gson();
			json = gs.toJson(list);
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return json;
    }
	
	@RequestMapping(value="/toChartSceneType.do",produces="text/html;charset=UTF-8")
	@ResponseBody
    public String toChartSceneType(HttpServletRequest request)throws  Exception{
        String json = null;
        Map<String,Object>paramMap=new HashMap<>();
        try {
        	checkSwitchClose(paramMap);//查询关账后最新月份
	        List<AutoOperationChartSceneTotal> list = new ArrayList<AutoOperationChartSceneTotal>();
	        log.debug("查询图表数据");
	        list = statistic.selectChartSceneTotal(paramMap);
	        Gson gs = new Gson();
			json = gs.toJson(list);
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return json;
    }
	
	@RequestMapping(value="/toChartIncomeRate.do",produces="text/html;charset=UTF-8")
	@ResponseBody
    public String toChartIncomeRate(HttpServletRequest request)throws  Exception{
        String json = null;
        Map<String,Object>paramMap=new HashMap<>();
        try {
        	checkSwitchClose(paramMap);//查询关账后最新月份
	        List<AutoOperationChartIncomeRate> list = new ArrayList<AutoOperationChartIncomeRate>();
	        log.debug("查询图表数据");
	        list = statistic.selectIncomeRate(paramMap);
	        Gson gs = new Gson();
			json = gs.toJson(list);
        }catch (Exception e){
            log.error("ERROR:",e);
        }
        return json;
    }
	
	@RequestMapping(value="/toStatisticChart.do",produces="text/html;charset=UTF-8")
	@ResponseBody
    public String toStatisticChart(HttpServletRequest request){
        String json = null;
//        String callback = request.getParameter("callback");
        Map<String, Object> obj = new HashMap<String, Object>();
        String year=null;
		String month=null;
		String lastYear=null;
		String lastMonth=null;
		String previousYear=null;
		String previousMonth=null;
        try {
        	log.debug("开始查询当前月份");
    		List<AutoOperationSwitch> autoSwitch = statistic.selectSwitchClose();
        	log.debug("查询结束");
        	for(int i=0; i < autoSwitch.size(); i++){
	        	AutoOperationSwitch as = autoSwitch.get(i);
	        	year = as.getYear();
	        	month = as.getMonth();
	        }
        	if(month.equals("01")){
		        lastMonth = "12";
		        lastYear = String.valueOf(Integer.valueOf(year)-1);
		        previousMonth = "11";
		        previousYear = String.valueOf(Integer.valueOf(year)-1);
	        }else if(month.equals("02")){
		        lastMonth = "01";
		        lastYear = year;
		        previousMonth = "12";
		        previousYear = String.valueOf(Integer.valueOf(year)-1);
	        }else{
	        	lastMonth = String.format("%02d",Integer.valueOf(month)-1);
	        	lastYear = year;
	        	previousMonth = String.format("%02d",Integer.valueOf(month)-2);
		        previousYear = year;
	        }
        	String previousDate = previousYear+previousMonth;
	        String startMonth = lastYear+lastMonth;
	        String endMonth = year+month;
	        Map<String,Object>paramMap1=new HashMap<>();
	        Map<String,Object>paramMap2=new HashMap<>();
	        paramMap1.put("startMonth", startMonth);
	        paramMap1.put("endMonth", endMonth);
	        paramMap2.put("startMonth", previousDate);
	        paramMap2.put("endMonth", endMonth);
	        obj.put("month", Integer.valueOf(month));
	        obj.put("previousMonth", Integer.valueOf(previousMonth));
	        List<AutoOperationStatisticChart> list1 = statistic.selectStatisticChart(paramMap1);
	        obj.put("list1", list1);
	        
	        paramMap2.put("groupBy", "a.category_id");
	        List<AutoOperationStatisticChart> list2 = statistic.selectStatisticChart2(paramMap2);
	        obj.put("list2", list2);
	        
	        paramMap1.put("groupBy", "a.category_id");
	        List<AutoOperationStatisticChart> list3 = statistic.selectStatisticChart(paramMap1);
	        obj.put("list3", list3);
	        
	        paramMap1.put("groupBy", "a.province_code");
	        paramMap1.put("orderBy", "SUM(IF(CONCAT(a.year,a.month)=\'"+endMonth+"\',a.order_number,0)) DESC LIMIT 5");
	        List<AutoOperationStatisticChart> list4 = statistic.selectStatisticChart(paramMap1);
	        obj.put("list4", list4);
	        
	        paramMap1.put("orderBy", "SUM(IF(CONCAT(a.year,a.month)=\'"+endMonth+"\',a.order_number,0))-SUM(IF(CONCAT(a.year,a.month)=\'"+startMonth+"\',a.order_number,0)) DESC LIMIT 5");
	        List<AutoOperationStatisticChart> list5 = statistic.selectStatisticChart(paramMap1);
	        obj.put("list5", list5);
	        
	        paramMap1.put("orderBy", "SUM(IF(CONCAT(a.year,a.month)=\'"+endMonth+"\',a.dataplant_income,0)) DESC LIMIT 5");
	        List<AutoOperationStatisticChart> list6 = statistic.selectStatisticChart(paramMap1);
	        obj.put("list6", list6);
	        
	        paramMap2.put("groupBy", "a.province_code");
	        paramMap2.put("orderBy", "IFNULL(SUM(IF(CONCAT(a.year,a.month)=\'"+endMonth+"\',a.dataplant_income,0))/SUM(IF(CONCAT(a.year,a.month)=\'"+previousDate+"\',a.dataplant_income,0)),2) DESC LIMIT 5");
	        List<AutoOperationStatisticChart> list7 = statistic.selectStatisticChart(paramMap2);
	        obj.put("list7", list7);
	        
	        Map<String,String>paramMap3=checkSwitchClose();
	        List<AutoOperationStatisticRateChart> list8 = statistic.selectStatisticChart3(paramMap3);
	        obj.put("list8", list8);
	        
	        Gson gs = new Gson();
			json = gs.toJson(obj);
			
			
        }catch (Exception e){
            log.error("ERROR:",e);
        }
//        return callback+"("+json+")";
        return json;
    }
	
	@RequestMapping("/export.do")
    public void Export(HttpServletRequest request,HttpServletResponse response)throws  Exception{
		XSSFWorkbook wb = null;
		SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
		String year=request.getParameter("year");
        String month=request.getParameter("month");
        Map<String,Object> dataMap=new HashMap<>();
        try {
        	//查询表头
        	month = String.format("%02d",Integer.valueOf(month));
        	List<AutoOperationCategory> category = new ArrayList<AutoOperationCategory>();
        	log.debug("开始查询分类数目");
        	category = statistic.selectCategory();
        	List<String> categoryName = new ArrayList<String>();
        	List<Integer> width = new ArrayList<Integer>();
        	List<String> columnName = new ArrayList<String>();
        	categoryName.add("省分");
        	categoryName.add("汇总");
        	width.add(1);
        	width.add(3);
        	columnName.add("省分");
        	columnName.add("流量包总收入");
        	columnName.add("触达用户总数");
        	columnName.add("成功用户总数");
        	log.debug("录入汇总列名");
        	for(int i=0; i < category.size(); i++){
        		AutoOperationCategory ca = category.get(i);
        		categoryName.add(ca.getName());
        		if (ca.getDatapackageStatus().equals("1")){
        			width.add(8);
        			columnName.add("总部场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        			columnName.add("收入（万）");
        			columnName.add("省分场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        			columnName.add("收入（万）");
        		}else{
        			width.add(6);
        			columnName.add("总部场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        			columnName.add("省分场景数");
        			columnName.add("触达用户数");
        			columnName.add("成功用户数");
        		}
        	}
        	dataMap.put("categoryName", categoryName);
        	dataMap.put("width", width);
        	dataMap.put("columnName", columnName);
        	//查询汇总内容
        	int column = 4;//数据库内查询随机列起始位置
        	List<Map<String, Object>> dataList = selectStatisticTable(year,month,operator.getOrgCode(),column);
        	
        	dataMap.put("dataList", dataList);
        	wb = new XSSFWorkbook();
        	XSSFSheet sheet = wb.createSheet("汇总");
        	sheet.setDefaultColumnWidth(14);
        	mergeColumn(wb,sheet,categoryName,width,0);//分类头
        	columnHeader(wb,sheet,columnName,1,1);//列头
        	writeContent(wb,sheet,dataList,2);//数据内容
        	writeToFilePath(wb,"自动化运营数据汇总.xlsx",response);//输出
        }catch (Exception e){
            log.error("ERROR:",e);
        }finally{
        	  try {     
        		  wb.close();
              } catch (IOException e) {    
                  e.printStackTrace();  
              }  
          }
	}
	
	@RequestMapping("/exportScene.do")
    public void ExportScene(HttpServletRequest request,HttpServletResponse response)throws  Exception{
		XSSFWorkbook wb = null;
		String year=request.getParameter("year");
        String month=request.getParameter("month");
        SysOperator operator = (SysOperator)request.getSession().getAttribute(WcsSessionConstant.SESSION_OPERATOR);
        try {
        	month = String.format("%02d",Integer.valueOf(month));
        	List<String> columnName = new ArrayList<String>();
        	columnName.add("省分");
        	columnName.add("场景分类");
        	columnName.add("分类");
        	columnName.add("场景名称");
        	columnName.add("场景模型及触发行为");
        	columnName.add("当月触发用户数");
        	columnName.add("成功订购产品数");
        	columnName.add("流量包收入(万元)");
        	//查询汇总内容
        	Map<String, Object> dataItem = new HashMap<String, Object>();
        	dataItem.put("year", year);
        	dataItem.put("month", month);
        	String province = operator.getOrgCode();
        	if(province.equals("001")){
        	}else{
            	dataItem.put("province", operator.getOrgCode());
        	}
        	List<Map<String, Object>> dataList = statistic.selectExportScene(dataItem);

        	wb = new XSSFWorkbook();
        	XSSFSheet sheet = wb.createSheet("统计");
        	sheet.setDefaultColumnWidth(14);
        	columnHeader(wb,sheet,columnName,0,0);//列头
        	writeContent(wb,sheet,dataList,columnName);//数据内容
        	writeToFilePath(wb,month+"月各场景信息统计表.xlsx",response);//输出
        }catch (Exception e){
            log.error("ERROR:",e);
        }finally{
        	  try {     
        		  wb.close();
              } catch (IOException e) {    
                  e.printStackTrace();  
              }  
          }
	}
	
	public void mergeColumn(XSSFWorkbook wb, XSSFSheet sheet, List<String> categoryName, List<Integer> width, int startColumn){
		CellRangeAddress cra1=new CellRangeAddress(startColumn, startColumn+1, 0, 0);
		sheet.addMergedRegion(cra1);
		XSSFCellStyle cs = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)12);    
        font.setBoldweight(font.BOLDWEIGHT_BOLD);
        cs.setFont(font);    
        cs.setAlignment(cs.ALIGN_CENTER);    
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        
		XSSFRow r = sheet.createRow(startColumn);
		XSSFCell c = r.createCell(0); 
		c.setCellValue(categoryName.get(0));
		c.setCellStyle(cs);
		int column = 1;
		for(int i=1; i < categoryName.size(); i++){
			CellRangeAddress cra=new CellRangeAddress(startColumn, startColumn, column, column+width.get(i)-1);
			sheet.addMergedRegion(cra);
			XSSFCell rc = r.createCell(column);
			rc.setCellValue(categoryName.get(i));
			rc.setCellStyle(cs);
			column=column+width.get(i);
		}
	}
	
	public void columnHeader(XSSFWorkbook wb, XSSFSheet sheet, List<String> columnName, int startColumn, int startRow){
		XSSFCellStyle cs = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)12);    
        font.setBoldweight(font.BOLDWEIGHT_BOLD);
        cs.setFont(font);    
        cs.setAlignment(cs.ALIGN_CENTER);    
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        
		XSSFRow r = sheet.createRow(startColumn);
		for(int i=startRow; i < columnName.size(); i++){
			XSSFCell rc = r.createCell(i);
			rc.setCellValue(columnName.get(i));
			rc.setCellStyle(cs);
		}
	}
	
	public void writeContent(XSSFWorkbook wb, XSSFSheet sheet, List<Map<String, Object>> dataList, int startColumn){
		XSSFCellStyle cs = wb.createCellStyle();
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        
		for(int i=startColumn; i < dataList.size()+startColumn; i++){
			XSSFRow r = sheet.createRow(i);
			Map<String, Object> map = dataList.get(i-startColumn);
			Set set = map.keySet();
			int j=0;
			for(Iterator iter = set.iterator(); iter.hasNext();){
				String key = (String)iter.next();
				XSSFCell rc = r.createCell(j);
				Object v = null;
				v = map.get(key);
				if (v instanceof String) {
					rc.setCellValue((String)v);
					rc.setCellStyle(cs);
				}else if (v instanceof Boolean) {
					rc.setCellValue((Boolean)v);
					rc.setCellStyle(cs);
				}else if (v instanceof Calendar) {    
					rc.setCellValue((Calendar)v);
					rc.setCellStyle(cs);
				}else if (v instanceof Double) {    
					rc.setCellValue((Double)v); 
					rc.setCellStyle(cs);
				}else if (v instanceof Integer || v instanceof Long || v instanceof Short || v instanceof Float) {    
					rc.setCellValue(Double.parseDouble(v.toString()));
					rc.setCellStyle(cs);
				}else if (v instanceof HSSFRichTextString) {    
					rc.setCellValue((HSSFRichTextString)v);
					rc.setCellStyle(cs);
				}else {    
					if (v==null){
						rc.setCellValue("");  
						rc.setCellStyle(cs);
					}else{
						rc.setCellValue(v.toString()); 
						rc.setCellStyle(cs);
					}
				}
				j++;
			}
		}
	}
	public void writeContent(XSSFWorkbook wb, XSSFSheet sheet, List<Map<String, Object>> dataList, List<String> columnName){
		XSSFCellStyle cs = wb.createCellStyle();
        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cs.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		for(int i=0; i < dataList.size(); i++){
			XSSFRow r = sheet.createRow(i+1);
			Map<String, Object> map = dataList.get(i);
			int j=0;
			for(int a=0;a<columnName.size();a++){
				String key = columnName.get(a);
				XSSFCell rc = r.createCell(j);
				Object v = null;
				v = map.get(key);
				if (v instanceof String) {
					rc.setCellValue((String)v);
					rc.setCellStyle(cs);
				}else if (v instanceof Boolean) {
					rc.setCellValue((Boolean)v);
					rc.setCellStyle(cs);
				}else if (v instanceof Calendar) {    
					rc.setCellValue((Calendar)v);
					rc.setCellStyle(cs);
				}else if (v instanceof Double) {    
					rc.setCellValue((Double)v); 
					rc.setCellStyle(cs);
				}else if (v instanceof Integer || v instanceof Long || v instanceof Short || v instanceof Float) {    
					rc.setCellValue(Double.parseDouble(v.toString()));
					rc.setCellStyle(cs);
				}else if (v instanceof HSSFRichTextString) {    
					rc.setCellValue((HSSFRichTextString)v);
					rc.setCellStyle(cs);
				}else {    
					if (v==null){
						rc.setCellValue("");  
						rc.setCellStyle(cs);
					}else{
						rc.setCellValue(v.toString()); 
						rc.setCellStyle(cs);
					}
				}
				j++;
			}
		}
	}
	
	public void writeToFilePath(XSSFWorkbook wb, String fileName, HttpServletResponse response){
		OutputStream out = null; 
		try{
			out = response.getOutputStream(); 
	        response.setContentType("application/x-msdownload");  
	        response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(fileName, "UTF-8"));
	        wb.write(out);  
		}catch (Exception e){
            log.error("ERROR:",e);
        }finally{
      	  try {     
              out.close();    
          } catch (IOException e) {    
              e.printStackTrace();  
          }  
      }
		
	}
	
	public List<Map<String, Object>> selectStatisticTable(String year, String month, String orgCode, int startColumn)throws  Exception {
		
    	List<AutoOperationStatistic> list = new ArrayList<AutoOperationStatistic>();
    	List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
    	Map<String, Object> dataItem = null;
		int column1 = startColumn;//数据库内查询随机列起始位置
		log.debug("查询汇总数据");
		try{
			int categoryCount = statistic.selectCategoryCount();
	    	categoryCount = categoryCount+1;
	    	for(int i=0; i < categoryCount; i++){
	    		if (i==0){
	            	Map<String,Object>paramMap=new HashMap<>();
	            	if(!orgCode.equals("001")){
	            		paramMap.put("orgCode", orgCode);
	            	}
	            	paramMap.put("year", year);
	                paramMap.put("month", month);
	                paramMap.put("orderBy", "order_by");
	    			list = statistic.selectStatisticTotal(paramMap);
	    			for(int a=0; a < list.size(); a++){
	        			dataItem = new LinkedHashMap<String, Object>();
	        			AutoOperationStatistic map = list.get(a);
	        			dataItem.put("0",map.getOrgName());
	        			dataItem.put("1",map.getDataplantIncome());
	        			dataItem.put("2",map.getReachNumber());
	        			dataItem.put("3",map.getOrderNumber());
	        			dataList.add(dataItem);
	    			}
	    		}else{
	    			for(int j=1; j < 3; j++){
	        			Map<String,Object>paramMap=new HashMap<>();
	        			if(!orgCode.equals("001")){
	                		paramMap.put("orgCode", orgCode);
	                	}
	                	paramMap.put("year", year);
	                    paramMap.put("month", month);
	                    paramMap.put("sceneType", j);
	                    paramMap.put("categoryId", i);
	                    paramMap.put("orderBy", "order_by");
	        			list = statistic.selectStatisticDetail(paramMap);
	        			if (statistic.selectDatapackage(String.valueOf(i)).equals("1")){
	        				for(int a=0; a < list.size(); a++){
	        					int column = column1;
	            				Map<String,Object> map1=new LinkedHashMap<String,Object>();
	            				map1 = dataList.get(a);
	                			AutoOperationStatistic map = list.get(a);
	                			map1.put(String.valueOf(column),map.getCount());
	                			column++;
	                			map1.put(String.valueOf(column),map.getReachNumber());
	                			column++;
	                			map1.put(String.valueOf(column),map.getOrderNumber());
	                			column++;
	                			map1.put(String.valueOf(column),map.getDataplantIncome());
	                			column++;
	                			dataList.set(a, map1);
	                			if(a==list.size()-1){
	                    			column1=column;
	                			}
	            			}
	        			}else{
	        				for(int a=0; a < list.size(); a++){
	        					int column = column1;
	            				Map<String,Object> map1=new HashMap<>();
	            				map1 = dataList.get(a);
	                			AutoOperationStatistic map = list.get(a);
	                			map1.put(String.valueOf(column),map.getCount());
	                			column++;
	                			map1.put(String.valueOf(column),map.getReachNumber());
	                			column++;
	                			map1.put(String.valueOf(column),map.getOrderNumber());
	                			column++;
	                			dataList.set(a, map1);
	                			if(a==list.size()-1){
	                    			column1=column;
	                			}
	            			}
	        			}
	    			}
	    		}
	    	}
		}catch (Exception e){
            log.error("ERROR:",e);
        }
		
    	return dataList;
	}
	public Map<String,Object> checkSwitchClose(Map<String,Object> paramMap)throws  Exception{
		String year=null;
		String month=null;
		try {
			log.debug("开始查询当前月份");
    		List<AutoOperationSwitch> autoSwitch = new ArrayList<AutoOperationSwitch>();
        	autoSwitch = statistic.selectSwitchClose();
        	log.debug("查询结束");
	        for(int i=0; i < autoSwitch.size(); i++){
	        	AutoOperationSwitch as = autoSwitch.get(i);
	        	year = as.getYear();
	        	month = as.getMonth();
	        }
	        paramMap.put("year", year);
	        paramMap.put("month", month);
		}catch (Exception e){
            log.error("ERROR:",e);
        }
		return paramMap;
	}
	public Map<String,Object> checkSwitchCloseBetween(Map<String,Object> paramMap)throws  Exception{
		String year=null;
		String month=null;
		String lastYear=null;
		String lastMonth=null;
		try {
			log.debug("开始查询当前月份");
    		List<AutoOperationSwitch> autoSwitch = new ArrayList<AutoOperationSwitch>();
        	autoSwitch = statistic.selectSwitchClose();
        	log.debug("查询结束");
	        for(int i=0; i < autoSwitch.size(); i++){
	        	AutoOperationSwitch as = autoSwitch.get(i);
	        	year = as.getYear();
	        	month = as.getMonth();
	        }
	        if(month.equals("12")){
		        lastMonth = "01";
		        lastYear = year;
	        }else{
	        	lastMonth = String.format("%02d",Integer.valueOf(month)+1);
	        	lastYear = String.valueOf(Integer.valueOf(year)-1);
	        }
	        String startMonth = lastYear+lastMonth;
	        String endMonth = year+month;
	        paramMap.put("startMonth", startMonth);
	        paramMap.put("endMonth", endMonth);
		}catch (Exception e){
            log.error("ERROR:",e);
        }
		return paramMap;
	}
	
	public Map<String, String> checkSwitchClose() throws Exception {
		Map<String, String> paramMap = new HashMap<>();
	
		List<AutoOperationSwitch> autoSwitch = statistic.selectSwitchClose();
		String year = "";
		String month = "";
		for (int i = 0; i < autoSwitch.size(); i++) {
			AutoOperationSwitch as = autoSwitch.get(i);
			year = as.getYear();
			month = as.getMonth();
		}
		paramMap.put("time4", year+month);
		int j = 1;
		for(int i = -3; i < 0; i++){
			String times = DateUtils.calcDateMonth(year+month,i);
			paramMap.put("time" + j, times);
			j++;
		}
		return paramMap;
	}
}
