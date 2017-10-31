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
import com.situ.crm.dao.SaleChanceMapper;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.SaleChanceExample;
import com.situ.crm.pojo.SaleChanceExample.Criteria;
import com.situ.crm.service.ICustomerPlanService;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.util.Util;

@Service
public class CustomerPlanServiceImpl implements ICustomerPlanService{
	@Autowired
	private SaleChanceMapper saleChanceMapper;

	@Override
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, SaleChance saleChance, Date beginTime, Date endTime) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		SaleChanceExample saleChanceExample = new SaleChanceExample();
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = saleChanceExample.createCriteria();
		if (StringUtils.isNotEmpty(saleChance.getCustomerName())) {
			createCriteria.andCustomerNameLike(Util.formatLike(saleChance.getCustomerName()));
		}
		if (StringUtils.isNotEmpty(saleChance.getLinkMan())) {
			createCriteria.andLinkManLike(Util.formatLike(saleChance.getLinkMan()));
		}
		if (StringUtils.isNotEmpty(saleChance.getCreateMan())) {
			createCriteria.andCreateManLike(Util.formatLike(saleChance.getCreateMan()));
		}
		if (beginTime != null && endTime != null) {
			createCriteria.andCreateTimeBetween(beginTime, endTime);
		}
		if (saleChance.getDevResult() != null) {
			createCriteria.andDevResultEqualTo(saleChance.getDevResult());
		}
		
		//查询状态为已分配的 
		createCriteria.andStatusEqualTo(1);
		List<SaleChance> saleChanceList = saleChanceMapper.selectByExample(saleChanceExample);
		//total
		PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(saleChanceList);
		return result;
	}

	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	@Override
	public ServerResponse add(SaleChance saleChance) {
		
		
		if (saleChanceMapper.insert(saleChance) > 0) {
			return ServerResponse.createSuccess("添加成功! ");
		}
		return ServerResponse.createError("添加失败!");
	}

	@Override
	public ServerResponse update(SaleChance saleChance) {
		if (saleChanceMapper.updateByPrimaryKey(saleChance) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
		}
		return ServerResponse.createError("修改失败!");
	}


	/*@Override
	public List<SaleChance> findSaleChanceName() {
		LinkedList<SaleChance> findSaleChanceName = saleChanceMapper.findSaleChanceName();
		SaleChance saleChance = new SaleChance();
		saleChance.setSaleChanceName(null);
		findSaleChanceName.addFirst(saleChance);
		return findSaleChanceName;
	}*/

}
