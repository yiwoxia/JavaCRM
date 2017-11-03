package com.situ.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.CustomerMapper;
import com.situ.crm.pojo.Customer;
import com.situ.crm.pojo.CustomerExample;
import com.situ.crm.pojo.CustomerExample.Criteria;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.util.Util;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;
	// 根据分页信息返回所有数据
	@Override
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,Customer customer) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerExample customerExample = new CustomerExample();
		//1.设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = customerExample.createCriteria();
		String customerName = customer.getName();
		if(StringUtils.isNotEmpty(customer.getName())){
			createCriteria.andNameLike(Util.formatLike(customer.getName()));
		}
		if(StringUtils.isNotEmpty(customer.getNum())){
			createCriteria.andNumLike(Util.formatLike(customer.getNum()));
		}
		if(StringUtils.isNotEmpty(customer.getManagerName())){
			createCriteria.andManagerNameLike(Util.formatLike(customer.getManagerName()));
		}
		
		List<Customer> customerList = customerMapper.selectByExample(customerExample);
		//4,取分页后的结果
		PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerList);
		return result;
	}
	/**
	 * 删除
	 */
	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}
	/**
	 * 添加
	 */
	@Override
	public ServerResponse add(Customer customer) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//设置日期格式
		String date = df.format(new Date());
		String num = "KH" + date;
		customer.setNum(num);
		if(customerMapper.insert(customer) > 0 ){
			return ServerResponse.createSuccess("添加成功！");
		}

		return ServerResponse.createError("添加失败！");
	}
	/**
	 * 修改
	 */
	@Override
	public ServerResponse update(Customer customer) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//设置日期格式
		String date = df.format(new Date());
		String num = "KH" + date;
		customer.setNum(num);
		
		if (customerMapper.updateByPrimaryKey(customer) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
			}
			return ServerResponse.createError("修改失败!");
		}
	//根据ID查询
	@Override
	public Customer findById(Integer id) {
		// TODO Auto-generated method stub
		return customerMapper.selectByPrimaryKey(id);
	}

	// 根据id查找营销机会
	@Override
	public ServerResponse<Customer> findCustomerById(Integer customerId) {
		Customer customer = customerMapper.selectByPrimaryKey(customerId);
		if (customer != null) {
			return ServerResponse.createSuccess("成功");
		}
		return ServerResponse.createError("服务器繁忙，请稍后重试");
	}

}


	

