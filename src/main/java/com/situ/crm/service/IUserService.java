package com.situ.crm.service;


import java.util.List;

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
	/**
	 *  返回数据字典名字去重之后的列表
	 * @return
	 */
	List<User> findRoleName();
	/**
	 * 查找客户经理
	 * @return LinkedList<SaleChance>
	 */
	List<User> findAssignMan();
	/**
	 * 销售主管
	 * @return LinkedList<SaleChance>
	 */
	List<User> finddisposeMan();
	
	


	

}
