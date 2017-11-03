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
import com.situ.crm.pojo.Customer;
import com.situ.crm.pojo.OrderItem;
import com.situ.crm.pojo.User;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.service.IOrderItemService;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@Autowired
	private IOrderItemService orderItemService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping("/index")
	public String index(){
		return "customer_order_item_manager";
	}
	//根据id查找营销机会
	@RequestMapping("/findCustomerById")
	@ResponseBody
	private ServerResponse<Customer> findCustomerById(Integer customerId){
		return customerService.findCustomerById(customerId);
	}
	
	//根据分页信息返回所有数据
	@RequestMapping("findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,Integer orderId ){
		return orderItemService.findAll(page,rows,orderId);
	}

	//根据id删除
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return orderItemService.delete(ids);
	}
	//添加
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(Integer orderId, OrderItem orderItem) {
		return orderItemService.add(orderId,orderItem);
	}
	//更新
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(Integer orderId, OrderItem orderItem) {
		return orderItemService.update(orderId, orderItem);
	}
	//查找客户经理
	@RequestMapping("/findAssignMan")
	@ResponseBody
	public List<User> findAssignMan() {
		return userService.findAssignMan();
	}
	//删除计划项
	@RequestMapping("/deleteById")
	@ResponseBody
	private ServerResponse deleteById(Integer id) {
		return orderItemService.deleteById(id);
	}
	
}
