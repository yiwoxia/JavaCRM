package com.situ.crm.service;

import java.util.List;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLinkman;

public interface ICustomerLinKmanService {
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	ServerResponse delete(String ids);
	
	/**
	 * 查询所有
	 * @param id
	 * @return
	 */
	List<CustomerLinkman> findAll(Integer id);
	
	/**
	 * 添加
	 * @param customerLinkman
	 * @return
	 */
	ServerResponse add(CustomerLinkman customerLinkman);
	/**
	 * 更新
	 * @param customerLinkman
	 * @return
	 */
	ServerResponse update(CustomerLinkman customerLinkman);
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	ServerResponse deleteById(Integer id);



}
