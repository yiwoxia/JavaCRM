package com.situ.crm.service;


import java.util.List;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.DataDic;

public interface IDataDicService {
	/**
	 * 根据分页信息返回所有数据
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrideResult findAll(Integer page,Integer rows,DataDic dataDic);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */

	ServerResponse delete(String ids);
	/**
	 * 添加
	 * @param user
	 * @return
	 */
	ServerResponse add(DataDic dataDic);
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	ServerResponse update(DataDic dataDic);
	/**
	 * 查询下拉列表的展示
	 * @return
	 */
	List<DataDic> findDataDicName();


	

}
