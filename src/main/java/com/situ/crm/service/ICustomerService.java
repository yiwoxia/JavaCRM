package com.situ.crm.service;


import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Customer;
import com.situ.crm.vo.CustomerContribute;

public interface ICustomerService {
	/**
	 * 根据分页信息返回所有数据
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrideResult findAll(Integer page,Integer rows,Customer customer);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */

	ServerResponse delete(String ids);
	/**
	 * 添加
	 * @param customer
	 * @return
	 */
	ServerResponse add(Customer customer);
	/**
	 * 修改
	 * @param customer
	 * @return
	 */
	ServerResponse update(Customer customer);
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Customer findById(Integer id);

	/**
	 * 根据id查找营销机会
	 * @param customerId
	 * @return ServerResponse<Customer>
	 */
	ServerResponse<Customer> findCustomerById(Integer customerId);
	/**
	 * quartz定时任务
	 */
	void checkCustomerLoss();
	/**
	 * 查询客户订单总额
	 * @param page
	 * @param rows
	 * @param customerContribute
	 * @return
	 */
	EasyUIDataGrideResult findCustomerContribute(Integer page, Integer rows, CustomerContribute customerContribute);
	
	ServerResponse findCustomerConstitute();
	/**
	 * 客户服务查询 
	 * 
	 * */
	ServerResponse findService();



	

}
