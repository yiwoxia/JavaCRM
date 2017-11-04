package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.service.ICustomerLossService;

@Controller
@RequestMapping("/customerLoss")
public class CustomerLossController {
	
	@Autowired
	private ICustomerLossService customerLossService;
	
	@RequestMapping("/index")
	private String index(){
		return "customer_loss_manager";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	private EasyUIDataGrideResult findAll(Integer page,Integer rows,CustomerLoss customerLoss){
		return customerLossService.findAll(page,rows,customerLoss);
	}
	

	@RequestMapping("/findAllLoss")
	@ResponseBody
	private EasyUIDataGrideResult findAllLoss(Integer page,Integer rows,CustomerLoss customerLoss){
		return customerLossService.findAllLoss(page,rows,customerLoss);
	}

	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return customerLossService.delete(ids);
	}
	//添加
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(CustomerLoss customerLoss){
		return customerLossService.add(customerLoss);
	};
	//修改
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerLoss customerLoss){
	
		return customerLossService.update(customerLoss);
		
	} 
}
