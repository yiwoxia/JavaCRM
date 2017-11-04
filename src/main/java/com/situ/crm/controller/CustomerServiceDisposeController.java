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
import com.situ.crm.service.ICustomerServiceCreateService;
import com.situ.crm.service.ICustomerServiceDisposeService;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/customerServiceDispose")
public class CustomerServiceDisposeController {

	@Autowired
	private ICustomerServiceDisposeService customerServiceDisposeService;
	@Autowired
	private IUserService userService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@RequestMapping(value="/index")
	public String index(){
		return "customer_service_dispose";
	}
	
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return customerServiceDisposeService.delete(ids);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CustomerService customerService) {
		return customerServiceDisposeService.update(customerService);
	}
	
	@RequestMapping(value="/add")
	@ResponseBody
	public ServerResponse add(CustomerService customerService){
		return customerServiceDisposeService.add(customerService);
	}
	
	@RequestMapping(value="/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, CustomerService customerService,Date beginTime, Date endTime){
		return customerServiceDisposeService.findAll(page, rows, customerService, beginTime, endTime);
	}
	

	@RequestMapping("/finddisposeMan")
	@ResponseBody
	public List<User> finddisposeMan() {
		return userService.finddisposeMan();
	}
}
