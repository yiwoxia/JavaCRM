package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.DataDic;
import com.situ.crm.service.IDataDicService;

@Controller
@RequestMapping("/dataDic")
public class DataDicController {
	
	@Autowired
	private IDataDicService dataDicService;
	
	@RequestMapping("/index")
	private String index(){
		return "data_dic_manage";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	private EasyUIDataGrideResult findAll(Integer page,Integer rows,DataDic dataDic){
		return dataDicService.findAll(page,rows,dataDic);
	}

	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return dataDicService.delete(ids);
	}
	//添加
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(DataDic dataDic){
		return dataDicService.add(dataDic);
	};
	//修改
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(DataDic dataDic){
	
		return dataDicService.update(dataDic);
		
	} 
	
	@RequestMapping("/findDataDicName")
	@ResponseBody
	public List<DataDic> findDataDicName(){
		return dataDicService.findDataDicName();
		
	}
}
