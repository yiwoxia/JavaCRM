package com.situ.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.UserMapper;
import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;
import com.situ.crm.pojo.UserExample.Criteria;
import com.situ.crm.service.IUserService;
import com.situ.crm.util.Util;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	// 根据分页信息返回所有数据
	@Override
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,User user) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		UserExample userExample = new UserExample();
		//1.设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = userExample.createCriteria();
		String userName = user.getName();
		if(StringUtils.isNotEmpty(user.getName())){
			createCriteria.andNameLike(Util.formatLike(user.getName()));
		}
		List<User> userList = userMapper.selectByExample(userExample);
		//4,取分页后的结果
		PageInfo<User> pageInfo = new PageInfo<>(userList);
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(userList);
		return result;
	}
	/**
	 * 删除
	 */
	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}
	/**
	 * 添加
	 */
	@Override
	public ServerResponse add(User user) {
		
		if(userMapper.insert(user) > 0 ){
			return ServerResponse.createSuccess("添加成功！");
		}

		return ServerResponse.createError("添加失败！");
	}
	/**
	 * 修改
	 */
	@Override
	public ServerResponse update(User user) {
		if (userMapper.updateByPrimaryKey(user) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
			}
			return ServerResponse.createError("修改失败!");
		}
	}


	

