package com.chinauicom.research.stockmanagement.bi.dataanalysis.entity;

/**
 * 请求基础参数实体
 * @author zhaich5
 * @since 2018/7/16
 *
 */
public class BaseInVo {
	
	/** 每页的记录数*/
	private int pageSize;
	
	/** 当前页数 */
	private int currentPage;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
