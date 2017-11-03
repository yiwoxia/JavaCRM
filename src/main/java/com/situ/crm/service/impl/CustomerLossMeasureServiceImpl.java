package com.situ.crm.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.CustomerLossMeasureMapper;
import com.situ.crm.dao.SaleChanceMapper;
import com.situ.crm.dao.CustomerLossMeasureMapper;
import com.situ.crm.pojo.CustomerLossMeasureExample;
import com.situ.crm.pojo.CustomerLossMeasure;
import com.situ.crm.pojo.CustomerLossMeasureExample;
import com.situ.crm.pojo.CustomerLossMeasureExample.Criteria;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.service.ICustomerLossMeasureService;
import com.situ.crm.service.ICustomerPlanService;
import com.situ.crm.service.ICustomerLossMeasureService;
import com.situ.crm.util.Util;

@Service
public class CustomerLossMeasureServiceImpl implements ICustomerLossMeasureService{
	
	@Autowired
	private SaleChanceMapper saleChanceMapper;

	@Autowired
	private CustomerLossMeasureMapper customerLossMeasureMapper;
	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, Integer lossId) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerLossMeasureExample customerLossMeasureExample = new CustomerLossMeasureExample();
		//1、设置分页 
/*		PageHelper.startPage(page, rows);*/
		//2、执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = customerLossMeasureExample.createCriteria();
		if (lossId != null) {
			createCriteria.andLossIdEqualTo(lossId);
		}
		
		//查询状态为已分配的 
		List<CustomerLossMeasure> saleChanceList = customerLossMeasureMapper.selectByExample(customerLossMeasureExample);
		//total
		PageInfo<CustomerLossMeasure> pageInfo = new PageInfo<>(saleChanceList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(saleChanceList);
		System.out.println("============"+saleChanceList);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerLossMeasureMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	@Override
	public ServerResponse add(Integer saleChanceId, CustomerLossMeasure customerLossMeasure) {
		
		//customerLossMeasure.setSaleChanceId(saleChanceId);
		if (customerLossMeasureMapper.insert(customerLossMeasure) > 0) {
			return ServerResponse.createSuccess("添加成功! ");
		}
		return ServerResponse.createError("添加失败!");
	}

	@Override
	public ServerResponse update(Integer saleChanceId, CustomerLossMeasure customerLossMeasure) {
		//customerLossMeasure.setSaleChanceId(saleChanceId);
		if (customerLossMeasureMapper.updateByPrimaryKeySelective(customerLossMeasure) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
		}
		return ServerResponse.createError("修改失败!");
	}

	@Override
	public SaleChance findBySaleChanceId(Integer saleChanceId) {
		return saleChanceMapper.selectByPrimaryKey(saleChanceId);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		if (id == null) {
			return ServerResponse.createError("id不能为空");
		}
		int result = customerLossMeasureMapper.deleteByPrimaryKey(id);
		if (result > 0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}

}
