package com.situ.crm.service;


import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Product;

public interface IProductService {

	/**
	 * 查询所有
	 * @param page
	 * @param rows
	 * @param product
	 * @return
	 */
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, Product product);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public ServerResponse delete(String ids);
	/**
	 * 添加
	 * @param product
	 * @return
	 */
	public ServerResponse add(Product product);
	/**
	 * 修改
	 * @param product
	 * @return
	 */
	public ServerResponse update(Product product);

}
