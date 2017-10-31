package com.situ.crm.service;

import javax.servlet.http.HttpServletRequest;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;

public interface ILoginService {
	
	/**
	 * 检测用户名
	 * @param name
	 * @return
	 */
	Boolean checkUser(String name);
	/***
	 * 修改密码
	 * @param user
	 * @return
	 */
	ServerResponse updateUserByName(User user);
	/**
	 * 检测密码
	 * @param password
	 * @param name
	 * @return
	 */
	Boolean checkPassword(String password, String name);


	User logi(String name,String password, HttpServletRequest request);

}
