/**
 * com.chinauicom.research.datasource.DynamicDataSource.java
 */
package com.chinauicom.research.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 扩展Spring的AbstractRoutingDataSource抽象类，实现动态数据源.
 * @author zhaich5
 * @since 2018/4/28
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getDataSource();
	}

}
