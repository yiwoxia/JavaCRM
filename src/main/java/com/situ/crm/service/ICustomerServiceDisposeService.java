package com.situ.crm.service;

import java.util.Date;
import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.pojo.CustomerService;

public interface ICustomerServiceDisposeService {
	/**
	 * 返回所有数据
	 * @param rows 
	 * @param page 
	 * @param saleChanceName 
	 * @return 分装好的EasyUIDataGrideResult对象
	 */

	ServerResponse delete(String ids);

	EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerService customerService, Date beginTime, Date endTime);

	ServerResponse update(CustomerService customerService);

	ServerResponse add(CustomerService customerService);




}
