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
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationStatisticValue;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationStatisticService;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationSwitchService;
import com.chinauicom.research.stockmanagement.bi.system.operator.entity.SysOperator;

@Controller
@RequestMapping("/statisticNew")
public class AutoOperationStatisticNewAction {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AutoOperationSwitchService switchService;
	@Autowired
	private AutoOperationStatisticService statistic;
	
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
        return "statistic/statisticNew";
    }
	
	@RequestMapping("/getTableData.do")
	@ResponseBody
	public Message getTableData(HttpServletRequest request) {
		Message msg = new Message();
		String year=request.getParameter("year");
		String month=request.getParameter("month");
		String type=request.getParameter("type");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		try {
	        if(type.equals("1")){
	        	Map<String, Object> map1 = new HashMap<String, Object>();
		        map1.put("type", "成功订购数(万)");
		        Map<String, Object> map2 = new HashMap<String, Object>();
		        map2.put("type", "流量包总收入(万)");
		        Map<String, Object> map3 = new HashMap<String, Object>();
		        map3.put("type", "触达用户数(万)");
		        
		        List<AutoOperationStatisticValue> list = null;
		        Map<String, Object> mapS = new HashMap<String, Object>();
		        mapS.put("year", year);
		        list=statistic.selectStatisticNewValue1(mapS);
		        for(int a=0; a < list.size(); a++){
		        	AutoOperationStatisticValue map = list.get(a);
		        	String key = map.getMonth();
		        	map1.put(key, map.getOrderNumber());
		        	map2.put(key, map.getDataplantIncome());
		        	map3.put(key, map.getReachNumber());
		        }
		        mapS.put("previous", "previous");
		        List<AutoOperationStatisticValue> listPrevious = statistic.selectStatisticNewValue1(mapS);
		        if(listPrevious.size()>0){
		        	map1.put("12P", listPrevious.get(0).getOrderNumber());
		        	map2.put("12P", listPrevious.get(0).getDataplantIncome());
		        	map3.put("12P", listPrevious.get(0).getReachNumber());
		        }
		        dataList.add(map1);
		        dataList.add(map2);
		        dataList.add(map3);
		        resultMap.put("dataList", dataList);
	        }else if(type.equals("2")){
	        	Map<String, Object> map1 = new HashMap<String, Object>();
		        map1.put("type", "流量包");
		        Map<String, Object> map2 = new HashMap<String, Object>();
		        map2.put("type", "畅越");
		        Map<String, Object> map3 = new HashMap<String, Object>();
		        map3.put("type", "合约续约");
		        Map<String, Object> map4 = new HashMap<String, Object>();
		        map4.put("type", "终端换机");
		        
		        List<AutoOperationStatisticValue> list = null;
		        Map<String, Object> mapS = new HashMap<String, Object>();
		        mapS.put("year", year);
		        list=statistic.selectStatisticNewValue2(mapS);
		        for(int a=0; a < list.size(); a++){
		        	AutoOperationStatisticValue map = list.get(a);
		        	if(map.getCategoryId().equals("1")){
		        		String key = map.getMonth();
			        	map1.put(key, map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("2")){
		        		String key = map.getMonth();
			        	map2.put(key, map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("4")){
		        		String key = map.getMonth();
			        	map3.put(key, map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("5")){
		        		String key = map.getMonth();
			        	map4.put(key, map.getOrderNumber());
		        	}
		        }
		        mapS.put("previous", "previous");
		        List<AutoOperationStatisticValue> listPrevious = statistic.selectStatisticNewValue2(mapS);
		        for(int a=0; a < listPrevious.size(); a++){
		        	AutoOperationStatisticValue map = listPrevious.get(a);
		        	if(map.getCategoryId().equals("1")){
			        	map1.put("12P", map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("2")){
			        	map2.put("12P", map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("4")){
			        	map3.put("12P", map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("5")){
			        	map4.put("12P", map.getOrderNumber());
		        	}
		        }
		        dataList.add(map1);
		        dataList.add(map2);
		        dataList.add(map3);
		        dataList.add(map4);
		        resultMap.put("dataList", dataList);
	        }else if(type.equals("3")||type.equals("4")||type.equals("5")||type.equals("8")||type.equals("9")||type.equals("10")||type.equals("11")){
	        	dataList=getDataList(year,type);
	        	resultMap.put("dataList", dataList);
	        }else if(type.equals("6")){
	        	dataList=getSceneDataList(year,month);
	        	resultMap.put("dataList", dataList);
	        }else if(type.equals("7")){
	        	Map<String, Object> map1 = new HashMap<String, Object>();
		        map1.put("type", "流量包");
		        Map<String, Object> map2 = new HashMap<String, Object>();
		        map2.put("type", "畅越");
		        Map<String, Object> map3 = new HashMap<String, Object>();
		        map3.put("type", "畅视");
		        Map<String, Object> map4 = new HashMap<String, Object>();
		        map4.put("type", "合约续约");
		        Map<String, Object> map5 = new HashMap<String, Object>();
		        map5.put("type", "终端换机");
		        
		        List<AutoOperationStatisticValue> list = null;
		        Map<String, Object> mapS = new HashMap<String, Object>();
		        mapS.put("year", year);
		        list=statistic.selectStatisticNewValue2(mapS);
		        for(int a=0; a < list.size(); a++){
		        	AutoOperationStatisticValue map = list.get(a);
		        	if(map.getCategoryId().equals("1")){
		        		String key = map.getMonth();
			        	map1.put(key, map.getOrderNumber());
			        	map1.put(key+"R", map.getReachNumber());
		        	}else if(map.getCategoryId().equals("2")){
		        		String key = map.getMonth();
			        	map2.put(key, map.getOrderNumber());
			        	map2.put(key+"R", map.getReachNumber());
		        	}else if(map.getCategoryId().equals("3")){
		        		String key = map.getMonth();
			        	map3.put(key, map.getOrderNumber());
			        	map3.put(key+"R", map.getReachNumber());
		        	}else if(map.getCategoryId().equals("4")){
		        		String key = map.getMonth();
			        	map4.put(key, map.getOrderNumber());
			        	map4.put(key+"R", map.getReachNumber());
		        	}else if(map.getCategoryId().equals("5")){
		        		String key = map.getMonth();
			        	map5.put(key, map.getOrderNumber());
			        	map5.put(key+"R", map.getReachNumber());
		        	}
		        }
		        mapS.put("previous", "previous");
		        List<AutoOperationStatisticValue> listPrevious = statistic.selectStatisticNewValue2(mapS);
		        for(int a=0; a < listPrevious.size(); a++){
		        	AutoOperationStatisticValue map = listPrevious.get(a);
		        	if(map.getCategoryId().equals("1")){
			        	map1.put("12P", map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("2")){
			        	map2.put("12P", map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("3")){
			        	map3.put("12P", map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("4")){
			        	map4.put("12P", map.getOrderNumber());
		        	}else if(map.getCategoryId().equals("5")){
			        	map5.put("12P", map.getOrderNumber());
		        	}
		        }
		        dataList.add(map1);
		        dataList.add(map2);
		        dataList.add(map3);
		        dataList.add(map4);
		        dataList.add(map5);
		        resultMap.put("dataList", dataList);
	        }
	        month=switchService.selectMonthByYear(year);
	        resultMap.put("month", month);
			msg.setFlag(true);
			msg.setResult(resultMap);
		} catch (Exception e) {
			log.error("获取汇总数据出错:", e);
			msg.setFlag(false);
			msg.setMsg("获取汇总数据出错");
		}

		return msg;
	}
	public List<Map<String, Object>> getDataList(String year,String type)throws  Exception {
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        Map<String, Object> map4 = new HashMap<String, Object>();
        Map<String, Object> map5 = new HashMap<String, Object>();
        Map<String, Object> map6 = new HashMap<String, Object>();
        Map<String, Object> map7 = new HashMap<String, Object>();
        Map<String, Object> map8 = new HashMap<String, Object>();
        Map<String, Object> map9 = new HashMap<String, Object>();
        Map<String, Object> map10 = new HashMap<String, Object>();
        Map<String, Object> map11 = new HashMap<String, Object>();
        Map<String, Object> map12 = new HashMap<String, Object>();
        Map<String, Object> map13 = new HashMap<String, Object>();
        Map<String, Object> map14 = new HashMap<String, Object>();
        Map<String, Object> map15 = new HashMap<String, Object>();
        Map<String, Object> map16 = new HashMap<String, Object>();
        Map<String, Object> map17 = new HashMap<String, Object>();
        Map<String, Object> map18 = new HashMap<String, Object>();
        Map<String, Object> map19 = new HashMap<String, Object>();
        Map<String, Object> map20 = new HashMap<String, Object>();
        Map<String, Object> map21 = new HashMap<String, Object>();
        Map<String, Object> map22 = new HashMap<String, Object>();
        Map<String, Object> map23 = new HashMap<String, Object>();
        Map<String, Object> map24 = new HashMap<String, Object>();
        Map<String, Object> map25 = new HashMap<String, Object>();
        Map<String, Object> map26 = new HashMap<String, Object>();
        Map<String, Object> map27 = new HashMap<String, Object>();
        Map<String, Object> map28 = new HashMap<String, Object>();
        Map<String, Object> map29 = new HashMap<String, Object>();
        Map<String, Object> map30 = new HashMap<String, Object>();
        Map<String, Object> map31 = new HashMap<String, Object>();
        map1.put("province", "北京");
        map2.put("province", "天津");
        map3.put("province", "河北");
        map4.put("province", "山东");
        map5.put("province", "山西");
        map6.put("province", "内蒙古");
        map7.put("province", "辽宁");
        map8.put("province", "吉林");
        map9.put("province", "黑龙江");
        map10.put("province", "河南");
        map11.put("province", "上海");
        map12.put("province", "江苏");
        map13.put("province", "浙江");
        map14.put("province", "安徽");
        map15.put("province", "福建");
        map16.put("province", "江西");
        map17.put("province", "湖南");
        map18.put("province", "湖北");
        map19.put("province", "广东");
        map20.put("province", "广西");
        map21.put("province", "海南");
        map22.put("province", "重庆");
        map23.put("province", "四川");
        map24.put("province", "贵州");
        map25.put("province", "云南");
        map26.put("province", "陕西");
        map27.put("province", "西藏");
        map28.put("province", "甘肃");
        map29.put("province", "宁夏");
        map30.put("province", "青海");
        map31.put("province", "新疆");

        List<AutoOperationStatisticValue> list = null;
        List<AutoOperationStatisticValue> listPrevious = null;
		if(type.equals("3")){
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("valueType", "order_number");
	        map.put("year", year);
			list=statistic.selectStatisticNewValue(map);
			map.put("previous", "previous");
			listPrevious = statistic.selectStatisticNewValue(map);
		}else if(type.equals("4")){
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("valueType", "dataplant_income");
	        map.put("year", year);
			list=statistic.selectStatisticNewValue(map);
			map.put("previous", "previous");
			listPrevious = statistic.selectStatisticNewValue(map);
		}else if(type.equals("5")){
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("valueType", "reach_number");
	        map.put("year", year);
	        list=statistic.selectStatisticNewValue(map);
			map.put("previous", "previous");
			listPrevious = statistic.selectStatisticNewValue(map);
		}else if(type.equals("8")){
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("valueType", "order_number");
	        map.put("year", year);
	        map.put("category", "1");
	        list=statistic.selectStatisticNewValue(map);
			map.put("previous", "previous");
			listPrevious = statistic.selectStatisticNewValue(map);
		}else if(type.equals("9")){
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("valueType", "order_number");
	        map.put("year", year);
	        map.put("category", "2");
	        list=statistic.selectStatisticNewValue(map);
			map.put("previous", "previous");
			listPrevious = statistic.selectStatisticNewValue(map);
		}else if(type.equals("10")){
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("valueType", "order_number");
	        map.put("year", year);
	        map.put("category", "4");
	        list=statistic.selectStatisticNewValue(map);
			map.put("previous", "previous");
			listPrevious = statistic.selectStatisticNewValue(map);
		}else if(type.equals("11")){
			Map<String, Object> map = new HashMap<String, Object>();
	        map.put("valueType", "order_number");
	        map.put("year", year);
	        map.put("category", "5");
	        list=statistic.selectStatisticNewValue(map);
			map.put("previous", "previous");
			listPrevious = statistic.selectStatisticNewValue(map);
		}
		for(int i=0; i < list.size(); i++){
			AutoOperationStatisticValue map = list.get(i);
			String key = map.getMonth();
			if(map.getProvinceCode().equals("011")){map1.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("013")){map2.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("018")){map3.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("017")){map4.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("019")){map5.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("010")){map6.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("091")){map7.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("090")){map8.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("097")){map9.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("076")){map10.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("031")){map11.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("034")){map12.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("036")){map13.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("030")){map14.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("038")){map15.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("075")){map16.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("074")){map17.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("071")){map18.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("051")){map19.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("059")){map20.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("050")){map21.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("083")){map22.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("081")){map23.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("085")){map24.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("086")){map25.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("084")){map26.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("079")){map27.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("087")){map28.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("088")){map29.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("070")){map30.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("089")){map31.put(key, map.getValue());}
		}
		
		for(int i=0; i < listPrevious.size(); i++){
			AutoOperationStatisticValue map = listPrevious.get(i);
			String key = "12P";
			if(map.getProvinceCode().equals("011")){map1.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("013")){map2.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("018")){map3.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("017")){map4.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("019")){map5.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("010")){map6.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("091")){map7.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("090")){map8.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("097")){map9.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("076")){map10.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("031")){map11.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("034")){map12.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("036")){map13.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("030")){map14.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("038")){map15.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("075")){map16.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("074")){map17.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("071")){map18.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("051")){map19.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("059")){map20.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("050")){map21.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("083")){map22.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("081")){map23.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("085")){map24.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("086")){map25.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("084")){map26.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("079")){map27.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("087")){map28.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("088")){map29.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("070")){map30.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("089")){map31.put(key, map.getValue());}
		}
		dataList.add(map1);
		dataList.add(map2);
		dataList.add(map3);
		dataList.add(map4);
		dataList.add(map5);
		dataList.add(map6);
		dataList.add(map7);
		dataList.add(map8);
		dataList.add(map9);
		dataList.add(map10);
		dataList.add(map11);
		dataList.add(map12);
		dataList.add(map13);
		dataList.add(map14);
		dataList.add(map15);
		dataList.add(map16);
		dataList.add(map17);
		dataList.add(map18);
		dataList.add(map19);
		dataList.add(map20);
		dataList.add(map21);
		dataList.add(map22);
		dataList.add(map23);
		dataList.add(map24);
		dataList.add(map25);
		dataList.add(map26);
		dataList.add(map27);
		dataList.add(map28);
		dataList.add(map29);
		dataList.add(map30);
		dataList.add(map31);

		return dataList;
	}
	public List<Map<String, Object>> getSceneDataList(String year,String month)throws  Exception {
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        Map<String, Object> map3 = new HashMap<String, Object>();
        Map<String, Object> map4 = new HashMap<String, Object>();
        Map<String, Object> map5 = new HashMap<String, Object>();
        Map<String, Object> map6 = new HashMap<String, Object>();
        Map<String, Object> map7 = new HashMap<String, Object>();
        Map<String, Object> map8 = new HashMap<String, Object>();
        Map<String, Object> map9 = new HashMap<String, Object>();
        Map<String, Object> map10 = new HashMap<String, Object>();
        Map<String, Object> map11 = new HashMap<String, Object>();
        Map<String, Object> map12 = new HashMap<String, Object>();
        Map<String, Object> map13 = new HashMap<String, Object>();
        Map<String, Object> map14 = new HashMap<String, Object>();
        Map<String, Object> map15 = new HashMap<String, Object>();
        Map<String, Object> map16 = new HashMap<String, Object>();
        Map<String, Object> map17 = new HashMap<String, Object>();
        Map<String, Object> map18 = new HashMap<String, Object>();
        Map<String, Object> map19 = new HashMap<String, Object>();
        Map<String, Object> map20 = new HashMap<String, Object>();
        Map<String, Object> map21 = new HashMap<String, Object>();
        Map<String, Object> map22 = new HashMap<String, Object>();
        Map<String, Object> map23 = new HashMap<String, Object>();
        Map<String, Object> map24 = new HashMap<String, Object>();
        Map<String, Object> map25 = new HashMap<String, Object>();
        Map<String, Object> map26 = new HashMap<String, Object>();
        Map<String, Object> map27 = new HashMap<String, Object>();
        Map<String, Object> map28 = new HashMap<String, Object>();
        Map<String, Object> map29 = new HashMap<String, Object>();
        Map<String, Object> map30 = new HashMap<String, Object>();
        Map<String, Object> map31 = new HashMap<String, Object>();
        map1.put("province", "北京");
        map2.put("province", "天津");
        map3.put("province", "河北");
        map4.put("province", "山东");
        map5.put("province", "山西");
        map6.put("province", "内蒙古");
        map7.put("province", "辽宁");
        map8.put("province", "吉林");
        map9.put("province", "黑龙江");
        map10.put("province", "河南");
        map11.put("province", "上海");
        map12.put("province", "江苏");
        map13.put("province", "浙江");
        map14.put("province", "安徽");
        map15.put("province", "福建");
        map16.put("province", "江西");
        map17.put("province", "湖南");
        map18.put("province", "湖北");
        map19.put("province", "广东");
        map20.put("province", "广西");
        map21.put("province", "海南");
        map22.put("province", "重庆");
        map23.put("province", "四川");
        map24.put("province", "贵州");
        map25.put("province", "云南");
        map26.put("province", "陕西");
        map27.put("province", "西藏");
        map28.put("province", "甘肃");
        map29.put("province", "宁夏");
        map30.put("province", "青海");
        map31.put("province", "新疆");

        List<AutoOperationStatisticValue> list = null;
        Map<String, Object> mapS = new HashMap<String, Object>();
        mapS.put("year", year);
        mapS.put("month", month);
        list=statistic.selectStatisticNewSceneValue(mapS);
		for(int i=0; i < list.size(); i++){
			AutoOperationStatisticValue map = list.get(i);
			String key = map.getCategoryId()+map.getSceneId();
			if(map.getProvinceCode().equals("011")){map1.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("013")){map2.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("018")){map3.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("017")){map4.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("019")){map5.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("010")){map6.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("091")){map7.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("090")){map8.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("097")){map9.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("076")){map10.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("031")){map11.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("034")){map12.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("036")){map13.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("030")){map14.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("038")){map15.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("075")){map16.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("074")){map17.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("071")){map18.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("051")){map19.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("059")){map20.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("050")){map21.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("083")){map22.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("081")){map23.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("085")){map24.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("086")){map25.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("084")){map26.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("079")){map27.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("087")){map28.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("088")){map29.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("070")){map30.put(key, map.getValue());}
			else if(map.getProvinceCode().equals("089")){map31.put(key, map.getValue());}
		}
		dataList.add(map1);
		dataList.add(map2);
		dataList.add(map3);
		dataList.add(map4);
		dataList.add(map5);
		dataList.add(map6);
		dataList.add(map7);
		dataList.add(map8);
		dataList.add(map9);
		dataList.add(map10);
		dataList.add(map11);
		dataList.add(map12);
		dataList.add(map13);
		dataList.add(map14);
		dataList.add(map15);
		dataList.add(map16);
		dataList.add(map17);
		dataList.add(map18);
		dataList.add(map19);
		dataList.add(map20);
		dataList.add(map21);
		dataList.add(map22);
		dataList.add(map23);
		dataList.add(map24);
		dataList.add(map25);
		dataList.add(map26);
		dataList.add(map27);
		dataList.add(map28);
		dataList.add(map29);
		dataList.add(map30);
		dataList.add(map31);
		return dataList;
	}
}
