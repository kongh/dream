package com.coder.dream.base.web.vo;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * jquery DataTable分页参数
 * 
 * @author Administrator
 *
 */
//iSortCol_0:0
	//sSortDir_0:asc
	//iSortingCols:1
	//bSortable_0:true
	//bSortable_1:true
	//bSortable_2:true
	//bSortable_3:true
	//bSortable_4:true
public class DataTablePageParams {
	public static String sEcho_KEY = "sEcho";
	public static String iColumns_KEY = "iColumns";
	public static String sColumns_KEY = "sColumns";
	public static String iDisplayStart_KEY = "iDisplayStart";
	public static String iDisplayLength_KEY = "iDisplayLength";
	public static String mDataProp_KEY = "mDataProp_";
	public static String bSortable_KEY = "bSortable_";
	public static String iTotalRecords_KEY = "iTotalRecords";
	public static String iTotalDisplayRecords_KEY = "iTotalDisplayRecords";
	
	private Map<String,String> params;
	private DataTablePageParams(Map<String,String> params){
		this.params = params;
	}
	
	/**
	 * 开始记录索引
	 * 
	 * @return
	 */
	public int getStart(){
		String startStr = this.params.get(iDisplayStart_KEY);
		return StringUtils.isBlank(startStr) ? 0 : Integer.parseInt(startStr);
	}
	
	/**
	 * 获取每页最多显示的记录数
	 * 
	 * @return
	 */
	public int getTotalOfPage(){
		String perPageTotalStr = this.params.get(iDisplayLength_KEY);
		return StringUtils.isBlank(perPageTotalStr) ? 20 : Integer.parseInt(perPageTotalStr);
	}
	
	/**
	 * 获取当前请求的页索引
	 * 
	 * @return
	 */
	public int getIndexOfCurrentPage(){
		int start = getStart();
		int totalOfPage = getTotalOfPage();
		return totalOfPage == 0 ? 0 : start / totalOfPage;
	}
	
	public static DataTablePageParams newInstance(Map<String,String> params){
		return new DataTablePageParams(params);
	}
}
