package com.chinauicom.research.stockmanagement.bi.autooperation.service.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinauicom.research.stockmanagement.bi.autooperation.dao.AutoOperationSwitchDao;
import com.chinauicom.research.stockmanagement.bi.autooperation.entity.AutoOperationSwitch;
import com.chinauicom.research.stockmanagement.bi.autooperation.service.AutoOperationSwitchService;

	/**
	 * 
	 * @ClassName: AutoOperationSwitchBo 
	 * @Description: 省分月度数据录入开关
	 * @author 
	 * @date 
	 *
	 */
	@Service
	public class AutoOperationSwitchBo implements AutoOperationSwitchService {
	@Autowired
	private AutoOperationSwitchDao autoOperationSwitchDao;
	
	/*public SysRole get(String pkId) throws Exception{
		if(pkId==null) return null;
     	return sysRoleDao.selectByPk(pkId);
	}*/

	@Override
	public List<AutoOperationSwitch> getAll() throws Exception {
		return autoOperationSwitchDao.selectAll();
	}
	/**
     * 查询指定月份开关状态
     * @param year  年 
     * @param month 月
     * @return type : boolean true为开 false为关闭 操作失败返回null
     * @throws Exception
     */
	
	@Override
	public int branchInpoutStatus(String year, String month) throws Exception {
		Integer res = null;
		AutoOperationSwitch record = new AutoOperationSwitch();
		record.setYear(year);
		record.setMonth(month);
		res  = autoOperationSwitchDao.checkBranchStatus(record);
		/*if(resultList.get(0).getSwitchStatus()=="1") {
			res = 1;
		}else if(resultList.get(0).getSwitchStatus()=="0") {
			res = 0;
		}	*/
		return res;
	}
	
	 /**
     * 删除数据库中与传入的值对象对应的记录
     * @param vo 与数据库中记录对应的值对象
     * @return type : boolean 返回删除操作是否成功
     * @throws Exception
     */
   /* public boolean delete(SysFunOperate vo) throws Exception{
    	if(vo == null) return false;
     	return sysFunOperateDao.deleteByVo(vo);
    }*/
	/**
     * 开启指定月份编辑开关
     * @param AutoOperationSwitch : (String year, String month, String switchStatus)
     * @return type : int 1为开 0为关闭 
     * @throws Exception
     */
	@Override
	public int enableSingleYear(AutoOperationSwitch record) throws Exception {
		return autoOperationSwitchDao.enableSingleYear(record);
	}
	
	/**
     * 关闭指定月份编辑开关
     * @param AutoOperationSwitch : (String year, String month, String switchStatus)
     * @return type : int 1为开 0为关闭 
     * @throws Exception
     */
	@Override
	public int disableSingleYear(AutoOperationSwitch record) throws Exception {
		return autoOperationSwitchDao.disableSingleYear(record);
	}
	
	/**
     * 关闭指定月份编辑开关，并插入下月信息，开关为开启状态
     * @param AutoOperationSwitch record 
     * @return type : int 1为开启  0关闭 操作失败返回2
     * @throws Exception
     */
    public int disableSingleYearAddNew(AutoOperationSwitch record) throws Exception {
  		int res = autoOperationSwitchDao.disableSingleYear(record);
		
		String month = record.getMonth();
  		String year = record.getYear();
  		String newYear = null;
      	String nextMonth = null;
      	try {
      	    int mtInt = Integer.parseInt(month);
      	    if (mtInt == 12) {
      	    	int yrInt = Integer.parseInt(year);
      	    	nextMonth = "01"; 
      	    	yrInt++;
      	    	newYear = String.valueOf(yrInt);
      	    	record.setMonth(nextMonth);
      	    	record.setYear(newYear);
      	    	record.setSwitchStatus("1");
      	    	//boolean res = autoOperationSwitchDao.getSqlSession().insert("AUTO_OPERATION_SWITCH.insertRec", record);
      	    	return autoOperationSwitchDao.insertNewYear(record);
      	    	//return getSqlSession().insert("AUTO_OPERATION_SWITCH.insertRec", record);
        	}else {
        		mtInt++;
        		nextMonth = String.valueOf(mtInt);
        		record.setMonth(nextMonth);
      	    	record.setSwitchStatus("1");
        		//return getSqlSession().insert("AUTO_OPERATION_SWITCH.insertRec", record);
        		return autoOperationSwitchDao.insertNewYear(record);
        	}
      	    
      	} catch (NumberFormatException e) {
      	    e.printStackTrace();
      	}
		return 2;
	}
    public String selectMonthByYear(String year) throws Exception{
		return autoOperationSwitchDao.selectMonthByYear(year);
	}
    public void insertModelScene(Map params) throws Exception{
		autoOperationSwitchDao.insertModelScene(params);
	}
}
