/**
 * 
 */
package com.chinauicom.research.stockmanagement.bi.dataanalysis.entity;

/**
 * 条件表达式
 * @author zhaich5
 * @since 2018/5/23
 *
 */
public class ConditionalExpression {

	/** 序号 */
	private Integer num;
	
	/** 多重条件 AND,OR,NOT */
	private String multipleCondition;
	
	/** 条件名称 */
	private String name;
	
	/** 谓词 */
	private String predicate;
	
	/** 条件值 */
	private Object value;
	
	/** 条件值java类型 */
	private String javaType;

	/** 条件值jdbc类型 */
	private String jdbcType;

	/** 条件值类型长度 */
	private String length;

	/** 条件值类型格式 */
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getMultipleCondition() {
		return multipleCondition;
	}

	public void setMultipleCondition(String multipleCondition) {
		this.multipleCondition = multipleCondition;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
