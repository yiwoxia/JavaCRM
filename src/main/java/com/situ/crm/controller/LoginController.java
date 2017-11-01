package com.situ.crm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;
import com.situ.crm.service.ILoginService;

@Controller
@RequestMapping("/index")
public class LoginController {

	@Autowired
	private ILoginService loginService;
	
	//跳转到登录界面
	@RequestMapping("/log")
	private String index(){
		return "login";
		
	}
	
	//判断登陆是否成功和或取用户名密码
	@RequestMapping(value="/login")
	@ResponseBody
	public ServerResponse login(String name, String password,Model model, HttpServletRequest request){
		User user = loginService.logi(name,password, request);
		System.out.println(user+"-------------------------------------------------------------------");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("backuser", user);
			return ServerResponse.createSuccess("登录成功");
		}else{
			String error = "用户名或密码错误";
			model.addAttribute("error", error);
			return ServerResponse.createError("登录失败");
		}
		
	}
	
	//修改密码
	@RequestMapping(value="/updatePassword")
	@ResponseBody
	public ServerResponse updatePassword(User user){
		return loginService.updateUserByName(user);
	}
	
	//检测用户名
	@RequestMapping(value="/checkName")
	@ResponseBody
	public Boolean cheackName(String name){
		return loginService.checkUser(name);
	}
	
	//检测密码
	@RequestMapping(value="/checkPassword")
	@ResponseBody
	public Boolean cheackPassword(String password,String name){
		return loginService.checkPassword(password,name);
		
	}
}
