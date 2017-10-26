package com.situ.crm.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.dao.UserMapper;
import com.situ.crm.pojo.CusDevPlanExample.Criteria;
import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;
import com.situ.crm.util.Util;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,User user) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		//1.设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows(分页之后的数据)
		com.situ.crm.pojo.UserExample.Criteria createCriteria = userExample.createCriteria();
		String userName = user.getUserName();
		if(StringUtils.isNotEmpty(user.getUserName())){
			createCriteria.andUserNameLike(Util.formatLike(user.getUserName()));
		}
		List<User> userList = userMapper.selectByExample(userExample);
		//4,取分页后的结果
		PageInfo<User> pageInfo = new PageInfo<>(userList);
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(userList);
		return result;
	}
	
	

}
