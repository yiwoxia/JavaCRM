package com.situ.crm.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.SaleChance;

public interface ISaleChanceService {
	/**
	 * 根据分页信息返回所有数据
	 * @param page 当前页
	 * @param rows 一页多少数据
	 * @return EasyUIDataGrideResult
	 */
	EasyUIDataGrideResult findAll(Integer page, Integer rows, SaleChance saleChance, Date beginTime, Date endTime);

	/**
	 * 根据id删除
	 * @param ids
	 * @return ServerResponse
	 */
	ServerResponse delete(String ids);

	/**
	 * 添加
	 * @param saleChance
	 * @return ServerResponse
	 */
	ServerResponse add(SaleChance saleChance);

	/**
	 * 更新
	 * @param saleChance
	 * @return ServerResponse
	 */
	ServerResponse update(SaleChance saleChance);

	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	ServerResponse findById(Integer id);
	/**
	 * 根据id查找营销机会
	 * @param saleChanceId
	 * @return
	 */
	ServerResponse<SaleChance> findSaleChanceById(Integer saleChanceId);
	/**
	 * 更新营销机会的开发状态
	 * @param salechance
	 * @return
	 */
	ServerResponse updateSaleChanceDevResult(SaleChance salechance);

}
