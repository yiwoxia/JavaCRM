package com.situ.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.dao.UserMapper;
import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public EasyUIDataGrideResult findAll() {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		//1.设置分页
		int total = userMapper.countByExample(userExample);
		//2.执行查询
		List<User> rows = userMapper.selectByExample(userExample);
		//rows(分页之后的数据)
		
		result.setTotal(total);
		result.setRows(rows);
		return result;
	}
	
	

}
