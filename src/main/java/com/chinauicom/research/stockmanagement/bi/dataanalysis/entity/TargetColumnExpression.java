/**
 * 
 */
package com.chinauicom.research.stockmanagement.bi.dataanalysis.entity;

/**
 * 目标列表达式
 * @author zhaich5
 * @since 2018/5/23
 *
 */
public class TargetColumnExpression {
	
	/** 序号 */
	private Integer num;
	
	/** 列表达式 */
	private String columnExpression;
	
	/** 是否有别名 */
	private boolean isAs = false;
	
	/** 别名 */
	private String asName;
	
	/** 中文名称 */
	private String chineseName;
	
	/** 表达式java类型 */
	private String javaType;

	/** 表达式jdbc类型 */
	private String jdbcType;

	/** 表达式类型长度 */
	private String length;

	/** 表达式类型格式 */
	private String format;

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getColumnExpression() {
		return columnExpression;
	}

	public void setColumnExpression(String columnExpression) {
		this.columnExpression = columnExpression;
	}

	public boolean getIsAs() {
		return isAs;
	}

	public void setIsAs(boolean isAs) {
		this.isAs = isAs;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}
	
}
