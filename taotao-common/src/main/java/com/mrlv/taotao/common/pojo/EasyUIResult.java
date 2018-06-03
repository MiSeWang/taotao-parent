package com.mrlv.taotao.common.pojo;

import java.util.List;

/**
 * Easyui中datagrid表格控件要求的返回数据格式为：
 * {total:”2”,rows:[{“id”:”1”,”name”,”张三”},{“id”:”2”,”name”,”李四”}]}
 * 所以创建一个工具类用于响应的json数据格式
 * 
 * @total	当前条件下查询的数据的总条数
 * @rows	当前条件下所查询出的数据
 */
public class EasyUIResult {
	private Long total;
	private List<?> rows;
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
