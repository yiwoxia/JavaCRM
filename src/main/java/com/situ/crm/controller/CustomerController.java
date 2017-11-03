package com.situ.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.Customer;
import com.situ.crm.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping("/index")
	private String index(){
		return "customer_manager";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	private EasyUIDataGrideResult findAll(Integer page,Integer rows,Customer customer){
		return customerService.findAll(page,rows,customer);
	}

	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return customerService.delete(ids);
	}
	//添加
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Customer customer){
		return customerService.add(customer);
	};
	//修改
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(Customer customer){
	
		return customerService.update(customer);
		
	} 
	//根据ID查
	@RequestMapping("findById")
	@ResponseBody
	public Customer findById(Integer id){
		return customerService.findById(id);
		}
	}
