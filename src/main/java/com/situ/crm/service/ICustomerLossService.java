package com.situ.crm.service;


import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLoss;

public interface ICustomerLossService {
	/**
	 * 根据分页信息返回所有数据
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrideResult findAll(Integer page,Integer rows,CustomerLoss customerLoss);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */

	ServerResponse delete(String ids);
	/**
	 * 添加
	 * @param customerLoss
	 * @return
	 */
	ServerResponse add(CustomerLoss customerLoss);
	/**
	 * 修改
	 * @param customerLoss
	 * @return
	 */
	ServerResponse update(CustomerLoss customerLoss);
	
	ServerResponse<CustomerLoss> findCustomerLossById(Integer lossId);
	
	EasyUIDataGrideResult findAllLoss(Integer page, Integer rows, CustomerLoss customerLoss);



	

}
