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
import com.situ.crm.vo.CustomerContribute;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping("/index")
	private String index(){
		return "customer_manager";
	}
	//客户贡献
	@RequestMapping("/indexContribute")
	public String indexContribute() {
		return "customer_kf_contribute_gx";
	}
	//客户构成
	@RequestMapping("/indexConstitute")
	public String indexConstitute() {
		return "customer_kf_constitute_gc";
	}
	//客户服务
	@RequestMapping("/indexService")
	public String indexService() {
		return "customer_kf_service";
	}
	//客户流失
	@RequestMapping("/indexLose")
	public String indexLose() {
		return "customer_kf_lose";
	}
	
	//查询客户订单总额
	@RequestMapping("findCustomerContribute")
	@ResponseBody
	public EasyUIDataGrideResult findCustomerContribute(Integer page, Integer rows,CustomerContribute customerContribute){
		return customerService.findCustomerContribute(page, rows, customerContribute);
	}
	//客户构成
	@RequestMapping("/findCustomerConstitute")
	@ResponseBody
	public ServerResponse findCustomerConstitute(){
		return customerService.findCustomerConstitute();
	}
	//客户服务
	@RequestMapping("/findService")
	@ResponseBody
	public ServerResponse findService(){
		return customerService.findService();
	}
	//查询所有
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
