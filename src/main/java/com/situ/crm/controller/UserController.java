package com.situ.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/index")
	private String index(){
		return "user_manage";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	private EasyUIDataGrideResult findAll(Integer page,Integer rows,User user){
		return userService.findAll(page,rows,user);
	}

	//删除
	@RequestMapping("delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return userService.delete(ids);
	}
	
}
