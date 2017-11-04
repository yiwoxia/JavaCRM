package com.situ.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.pojo.CustomerService;
import com.situ.crm.pojo.User;
import com.situ.crm.service.ICustomerServiceAssignService;
import com.situ.crm.service.ICustomerServiceCreateService;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/customerServiceAssign")
public class CustomerServiceAssignController {

	@Autowired
	private ICustomerServiceAssignService customerServiceAssignService;
	@Autowired
	private IUserService userService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@RequestMapping(value="/index")
	public String index(){
		return "customer_service_assign";
	}
	
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return customerServiceAssignService.delete(ids);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerService customerService) {
		return customerServiceAssignService.update(customerService);
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public ServerResponse add(CustomerService customerService){
		return customerServiceAssignService.add(customerService);
	}
	
	@RequestMapping(value="/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerService customerService,Date beginTime, Date endTime){
		return customerServiceAssignService.findAll(page, rows, customerService, beginTime, endTime);
	}
	

	@RequestMapping("/findAssignMan")
	@ResponseBody
	public List<User> findAssignMan() {
		return userService.findAssignMan();
	}
}
