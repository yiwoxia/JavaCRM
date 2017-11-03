package com.situ.crm.service;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CusDevPlan;

public interface ICusDevPlanService {

	/**
	 * 查询所有
	 * @param page
	 * @param rows
	 * @param cusDevPlan
	 * @return
	 */
	EasyUIDataGrideResult findAll(Integer page, Integer rows, CusDevPlan cusDevPlan);

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	ServerResponse delete(String ids);
	/**
	 * 根据Id删除
	 * @param id
	 * @return
	 */
	ServerResponse deleteById(Integer id);
	/**
	 * 添加
	 * @param cusDevPlan
	 * @return
	 */
	ServerResponse add(CusDevPlan cusDevPlan);
	/**
	 *  修改
	 * @param cusDevPlan
	 * @return
	 */
	ServerResponse update(CusDevPlan cusDevPlan);

}
