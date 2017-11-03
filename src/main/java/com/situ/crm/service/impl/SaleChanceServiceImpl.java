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
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.util.Util;

@Service
public class SaleChanceServiceImpl implements ISaleChanceService{
	@Autowired
	private SaleChanceMapper saleChanceMapper;

	//根据分页信息返回所有数据
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
		if (saleChance.getStatus() != null) {
			createCriteria.andStatusEqualTo(saleChance.getStatus());
		}
		if (beginTime != null && endTime != null) {
			createCriteria.andCreateTimeBetween(beginTime, endTime);
		}
		
		List<SaleChance> saleChanceList = saleChanceMapper.selectByExample(saleChanceExample);
		//total
		PageInfo<SaleChance> pageInfo = new PageInfo<>(saleChanceList);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(saleChanceList);
		return result;
	}

	//删除
	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			saleChanceMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}

	//添加
	@Override
	public ServerResponse add(SaleChance saleChance) {
		
		if (saleChance.getAssignMan() == null && saleChance.getAssignMan().trim().equals("")) {
			saleChance.setStatus(0);
		}
		if (saleChance.getAssignMan().equals("--暂不指派--")) {
			saleChance.setAssignMan(null);
			saleChance.setStatus(0);
		}
		
		if (saleChanceMapper.insert(saleChance) > 0) {
			return ServerResponse.createSuccess("添加成功! ");
		}
		return ServerResponse.createError("添加失败!");
	}

	//更新
	@Override
	public ServerResponse update(SaleChance saleChance) {
		saleChance.setStatus(1);
		if (saleChanceMapper.updateByPrimaryKey(saleChance) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
		}
		return ServerResponse.createError("修改失败!");
	}
	//根据Id查询
	@Override
	public ServerResponse findById(Integer id) {

		SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(id);
		if (saleChance != null) {
			return ServerResponse.createSuccess("查找成功",saleChance);
		}
		
		return ServerResponse.createError("查找失败!");
	}
	//根据id查找营销机会
	@Override
	public ServerResponse<SaleChance> findSaleChanceById(Integer saleChanceId) {
		SaleChance saleChance = saleChanceMapper.selectByPrimaryKey(saleChanceId);
		if (saleChance != null) {
			return ServerResponse.createError("服务器繁忙，请稍后重试");
		}
		return null;
	}
	//更新营销机会的开发状态
	@Override
	public ServerResponse updateSaleChanceDevResult(SaleChance salechance) {
		int result = saleChanceMapper.updateByPrimaryKeySelective(salechance);
		if (result > 0) {
			return ServerResponse.createError("更改成功");
		}
		return ServerResponse.createError("服务器繁忙，请稍后再试");

	}

}
