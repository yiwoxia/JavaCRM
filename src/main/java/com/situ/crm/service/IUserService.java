package com.situ.crm.service;


import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;

public interface IUserService {
	/**
	 * 根据分页信息返回所有数据
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrideResult findAll(Integer page,Integer rows,User user);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */

	ServerResponse delete(String ids);
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	ServerResponse add(User user);
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	ServerResponse update(User user);


	

}
