package com.chinauicom.research.stockmanagement.bi.dataanalysis.entity;

/**
 * 定制数据请求参数实体
 * @author zhaich5
 * @since 2018/6/12
 *
 */
public class CustomDataInVo extends BaseInVo {

	/** 数据名称 */
	private String dataName;
	
	/** 自定义SQL */
	private CustomSql customSql;

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public CustomSql getCustomSql() {
		return customSql;
	}

	public void setCustomSql(CustomSql customSql) {
		this.customSql = customSql;
	}
	
}
