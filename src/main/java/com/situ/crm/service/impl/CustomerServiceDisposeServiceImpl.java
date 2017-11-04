package com.situ.crm.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.CustomerOrderMapper;
import com.situ.crm.dao.CustomerServiceMapper;
import com.situ.crm.dao.SaleChanceMapper;
import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.pojo.CustomerOrderExample;
import com.situ.crm.pojo.CustomerService;
import com.situ.crm.pojo.CustomerServiceExample;
import com.situ.crm.pojo.CustomerServiceExample.Criteria;
import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.pojo.CustomerOrderExample;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.SaleChanceExample;
import com.situ.crm.pojo.UserExample;
import com.situ.crm.service.ICustomerServiceAssignService;
import com.situ.crm.service.ICustomerServiceCreateService;
import com.situ.crm.service.ICustomerServiceDisposeService;
import com.situ.crm.util.Util;

@Service
public class CustomerServiceDisposeServiceImpl implements ICustomerServiceDisposeService{
	
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	
	@Override
	public ServerResponse delete(String ids) {
		
		try {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				if(customerServiceMapper.deleteByPrimaryKey(Integer.parseInt(id)) <1){
					return ServerResponse.createError("删除数据失败！");
				}
			}
		} catch (Exception e) {
			return ServerResponse.createError("删除数据失败！");
		}
		return ServerResponse.createSuccess("删除数据成功！");
	}


	@Override
	public ServerResponse add(CustomerService customerService) {
		// TODO Auto-generated method stub
		if (customerServiceMapper.insert(customerService) > 0) {
			return ServerResponse.createSuccess("添加成功! ");
		}
		return ServerResponse.createError("添加失败!");
	}

	@Override
	public ServerResponse update(CustomerService customerService) {
		try {
			if(customerServiceMapper.updateByPrimaryKey(customerService) < 1){
				return ServerResponse.createError("添加数据失败！");
			}
		} catch (Exception e) {
			return ServerResponse.createError("添加数据失败！");
		}
		return ServerResponse.createSuccess("添加数据成功！");
	}


	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerService customerService, Date beginTime, Date endTime) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerServiceExample customerServiceExample = new CustomerServiceExample();
		//1.设置分页
		PageHelper.startPage(page,rows);
		
		Criteria createCriteria = customerServiceExample.createCriteria();
		
		if(StringUtils.isNotEmpty(customerService.getStatus())){
		}
		createCriteria.andStatusEqualTo("已分配");
		List<CustomerService> customerServiceList = customerServiceMapper.selectByExample(customerServiceExample);
		
		/*if (StringUtils.isNotEmpty(customerService.getCustomer())) {
			createCriteria.andCustomerLike(Util.formatLike(customerService.getCustomer()));
		}*/
		
		//total
		PageInfo<CustomerService> pageInfo = new PageInfo<>(customerServiceList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(customerServiceList);
		return result;
	}




}
