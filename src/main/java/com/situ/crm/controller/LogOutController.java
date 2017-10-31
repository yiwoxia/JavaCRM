package com.situ.crm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.crm.common.ServerResponse;
import com.situ.crm.pojo.User;

@Controller
@RequestMapping("/logOut")
public class LogOutController {

	@RequestMapping("/logOut")
	@ResponseBody
	public ServerResponse loginout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("currentUser");
		if (null == session.getAttribute("currentUser")) {
			return ServerResponse.createSuccess("退出成功");
		}else{
			return ServerResponse.createError("退出失败");
		}
		
	}
}
