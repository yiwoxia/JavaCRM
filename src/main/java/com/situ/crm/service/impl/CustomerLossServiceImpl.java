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
import com.situ.crm.dao.CustomerLossMapper;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.pojo.CustomerLossExample;
import com.situ.crm.pojo.CustomerLossExample.Criteria;
import com.situ.crm.service.ICustomerLossService;
import com.situ.crm.util.Util;

@Service
public class CustomerLossServiceImpl implements ICustomerLossService {
	
	@Autowired
	private CustomerLossMapper customerLossMapper;
	// 根据分页信息返回所有数据
	@Override
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,CustomerLoss customerLoss) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerLossExample customerLossExample = new CustomerLossExample();
		//1.设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = customerLossExample.createCriteria();
		String customerLossName = customerLoss.getCustomerName();
		if(StringUtils.isNotEmpty(customerLoss.getCustomerName())){
			createCriteria.andCustomerNameLike(Util.formatLike(customerLoss.getCustomerName()));
		}
		if(StringUtils.isNotEmpty(customerLoss.getCustomerManager())){
			createCriteria.andCustomerManagerLike(Util.formatLike(customerLoss.getCustomerManager()));
		}
		
		List<CustomerLoss> customerLossList = customerLossMapper.selectByExample(customerLossExample);
		//4,取分页后的结果
		PageInfo<CustomerLoss> pageInfo = new PageInfo<>(customerLossList);
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerLossList);
		return result;
	}
	
	// 根据分页信息返回所有数据
		@Override
		public EasyUIDataGrideResult findAllLoss(Integer page,Integer rows,CustomerLoss customerLoss) {
			EasyUIDataGrideResult result = new EasyUIDataGrideResult();
			CustomerLossExample customerLossExample = new CustomerLossExample();
			//1.设置分页
			PageHelper.startPage(page,rows);
			//2.执行查询
			//rows(分页之后的数据)
			Criteria createCriteria = customerLossExample.createCriteria();
			String customerLossName = customerLoss.getCustomerName();
			if(StringUtils.isNotEmpty(customerLoss.getCustomerName())){
				createCriteria.andCustomerNameLike(Util.formatLike(customerLoss.getCustomerName()));
			}
			if(StringUtils.isNotEmpty(customerLoss.getCustomerManager())){
				createCriteria.andCustomerManagerLike(Util.formatLike(customerLoss.getCustomerManager()));
			}
			if (null != customerLoss.getStatus()) {
				createCriteria.andStatusEqualTo(customerLoss.getStatus());
			}
			createCriteria.andStatusEqualTo(1);
			List<CustomerLoss> customerLossList = customerLossMapper.selectByExample(customerLossExample);
			//4,取分页后的结果
			PageInfo<CustomerLoss> pageInfo = new PageInfo<>(customerLossList);
			int total = (int)pageInfo.getTotal();
			result.setTotal(total);
			result.setRows(customerLossList);
			return result;
		}
	/**
	 * 删除
	 */
	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerLossMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}
	/**
	 * 添加
	 */
	@Override
	public ServerResponse add(CustomerLoss customerLoss) {
		if(customerLossMapper.insert(customerLoss) > 0 ){
			return ServerResponse.createSuccess("添加成功！");
		}

		return ServerResponse.createError("添加失败！");
	}
	/**
	 * 修改
	 */
	@Override
	public ServerResponse update(CustomerLoss customerLoss) {
		
		if (customerLossMapper.updateByPrimaryKey(customerLoss) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
			}
			return ServerResponse.createError("修改失败!");
		}
	@Override
	public ServerResponse<CustomerLoss> findCustomerLossById(Integer lossId) {
		
		CustomerLoss customerLoss = customerLossMapper.selectByPrimaryKey(lossId);
		
		if (null != customerLoss) {
			return ServerResponse.createSuccess("成功!",customerLoss);
			}
			return ServerResponse.createError("失败!");
		}


}


	

