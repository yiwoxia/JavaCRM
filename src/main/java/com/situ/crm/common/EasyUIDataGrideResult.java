package com.situ.crm.common;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 封装DataGride返回数据
 */
public class EasyUIDataGrideResult implements Serializable {
	/**全部*/
	private Integer total;
	/**行*/
	private List<?> rows;

	public EasyUIDataGrideResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EasyUIDataGrideResult(Integer total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "EasyUIDataGrideResult [total=" + total + ", rows=" + rows + "]";
	}
	
}
