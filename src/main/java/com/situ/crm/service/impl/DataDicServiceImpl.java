package com.situ.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.DataDicMapper;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.pojo.DataDicExample;
import com.situ.crm.pojo.DataDicExample.Criteria;
import com.situ.crm.service.IDataDicService;
import com.situ.crm.util.Util;

@Service
public class DataDicServiceImpl implements IDataDicService {
	
	@Autowired
	private DataDicMapper dataDicMapper;
	// 根据分页信息返回所有数据
	@Override
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,DataDic dataDic) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		DataDicExample dataDicExample = new DataDicExample();
		//1.设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = dataDicExample.createCriteria();
		if (StringUtils.isNotEmpty(dataDic.getDataDicName())) {
			createCriteria.andDataDicNameLike(Util.formatLike(dataDic.getDataDicName()));
		}
		List<DataDic> dataDicList = dataDicMapper.selectByExample(dataDicExample);
		//4,取分页后的结果
		PageInfo<DataDic> pageInfo = new PageInfo<>(dataDicList);
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(dataDicList);
		return result;
	}
	/**
	 * 删除
	 */
	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			dataDicMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}
	/**
	 * 添加
	 */
	@Override
	public ServerResponse add(DataDic dataDic) {
		
		if(dataDicMapper.insert(dataDic) > 0 ){
			return ServerResponse.createSuccess("添加成功！");
		}

		return ServerResponse.createError("添加失败！");
	}
	/**
	 * 修改
	 */
	@Override
	public ServerResponse update(DataDic dataDic) {
		if (dataDicMapper.updateByPrimaryKey(dataDic) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
			}
			return ServerResponse.createError("修改失败!");
		}
	/**
	 * 返回数据字典名字去重之后的列表
	 */
	@Override
	public List<DataDic> findDataDicName() {
		// TODO Auto-generated method stub
		return dataDicMapper.findDataDicName();
	}
	}


	

