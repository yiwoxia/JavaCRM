package com.situ.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
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
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.User;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/saleChance")
public class SaleChanceController {
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd"), true));
    }
	
	@Autowired
	private ISaleChanceService saleChanceService;
	
	@Autowired
	private IUserService userService;
	
	//跳转界面
	@RequestMapping("/index")
	public String index() {
		return "sale_chance_manager";
	}
	//根据分页信息返回所有数据
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page, Integer rows, SaleChance saleChance,Date beginTime, Date endTime) {
		System.out.println("page:"+page+"rows:"+rows+"saleChance:"+saleChance+"beginTime:"+beginTime+"endTime"+endTime);
		return saleChanceService.findAll(page, rows, saleChance, beginTime, endTime);
	}
	//根据id删除
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids) {
		return saleChanceService.delete(ids);
	}
	//添加
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(SaleChance saleChance) {
		return saleChanceService.add(saleChance);
	}
	//更新
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(SaleChance saleChance) {
		return saleChanceService.update(saleChance);
	}
	//查找客户经理
	@RequestMapping("/findAssignMan")
	@ResponseBody
	public List<User> findAssignMan() {
		return userService.findAssignMan();
	}
	
	@RequestMapping("findById")
	@ResponseBody
	public ServerResponse findById(Integer id){
		return saleChanceService.findById(id);
	}
}
