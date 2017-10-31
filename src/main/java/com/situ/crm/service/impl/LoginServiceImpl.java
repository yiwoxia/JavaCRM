package com.situ.crm.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.UserMapper;
import com.situ.crm.pojo.UserExample.Criteria;
import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;
import com.situ.crm.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private  UserMapper userMapper;
	
	//检测用户名
	@Override
	public Boolean checkUser(String name) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		if (StringUtils.isNotEmpty(name)) {
			try {
				createCriteria.andNameEqualTo(new String(name.getBytes("iso-8859-1"),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//list
		List<User> userList = userMapper.selectByExample(userExample);
		for (User user : userList) {
			return true;
		}
		return false;
	}

	//修改密码
	@Override
	public ServerResponse updateUserByName(User user) {
		try {
			if(userMapper.updateUserByName(user) < 1){
				return ServerResponse.createError("修改数据失败！");
			}
		} catch (Exception e) {
			return ServerResponse.createError("修改数据失败！");
		}
		return ServerResponse.createSuccess("修改数据成功");
	}

	@Override
	public Boolean checkPassword(String password, String name) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		if (StringUtils.isNotEmpty(name)) {
			try {
				createCriteria.andNameEqualTo(new String(name.getBytes("iso-8859-1"),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(password)) {
			try {
				createCriteria.andPasswordEqualTo(new String(password.getBytes("iso-8859-1"),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//list
		List<User> userList = userMapper.selectByExample(userExample);
		for (User user : userList) {
			return true;
		}
		return false;
	}

	@Override
	public User logi(String name,String password, HttpServletRequest request) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		if (StringUtils.isNotEmpty(name)) {
			try {
				createCriteria.andNameEqualTo(new String(name.getBytes("iso-8859-1"),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//list
		List<User> userList = userMapper.selectByExample(userExample);
		for (User user : userList) {
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}

}
