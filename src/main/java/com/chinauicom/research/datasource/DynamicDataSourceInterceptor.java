/**
 * com.chinauicom.research.datasource.DynamicDataSourceInterceptor.java
 */
package com.chinauicom.research.datasource;

/**
 * 数据源动态切换拦截器
 * @author zhaich5
 * @since 2018/4/27
 *
 */
public class DynamicDataSourceInterceptor {
    
	/**
	 * 切换到数据源1
	 */
	public void setDataSource1() {
    	DynamicDataSourceHolder.setDataSource("mysqlDataSource");
	}
    
    /** 
     * 切换到数据源2 
     */
	public void setDataSource2() {   
		DynamicDataSourceHolder.setDataSource("prestoDataSource");
	}  
	
    /** 
     * 清除清除标志
     */
	public void clearDataSource() {
		DynamicDataSourceHolder.clearDataSource();
	}
	
}
