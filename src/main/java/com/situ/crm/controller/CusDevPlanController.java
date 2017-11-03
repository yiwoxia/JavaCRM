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
import com.situ.crm.pojo.CusDevPlan;
import com.situ.crm.pojo.SaleChance;
import com.situ.crm.pojo.User;
import com.situ.crm.service.ICusDevPlanService;
import com.situ.crm.service.ISaleChanceService;
import com.situ.crm.service.IUserService;

@Controller
@RequestMapping("/cusDevPlan")
public class CusDevPlanController {

	
	@Autowired
	private ICusDevPlanService cusDevPlabService;
	@Autowired
	private ISaleChanceService saleChanceService;
	@Autowired
	private IUserService userService;
	
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    dateFormat.setLenient(false); 
	    binder.registerCustomEditor(Date.class,
	           new CustomDateEditor(dateFormat, true));
	}
	
	//跳转到jsp界面
	@RequestMapping("/index")
	public String index(){
		return "customer_dev_plan_item_manager";
		
	}
	//查询所有
	@RequestMapping("/findAll")
	@ResponseBody
	public EasyUIDataGrideResult findAll(Integer page,Integer rows, CusDevPlan cusDevPlan){
		return cusDevPlabService.findAll(page,rows,cusDevPlan);
	}
	
	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public ServerResponse delete(String ids){
		return cusDevPlabService.delete(ids);
	}

	//根据Id删除
	@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id){
		return cusDevPlabService.deleteById(id);
	}
	//添加
	@RequestMapping("/add")
	@ResponseBody
	public ServerResponse add(CusDevPlan cusDevPlan){
		return cusDevPlabService.add(cusDevPlan);
	}
	//修改
	@RequestMapping("/update")
	@ResponseBody
	public ServerResponse update(CusDevPlan cusDevPlan){
		return cusDevPlabService.update(cusDevPlan);
	}
	//根据id查找营销机会
	@RequestMapping("/findSaleChanceById")
	@ResponseBody
	public ServerResponse<SaleChance> findSaleChanceById(Integer saleChanceId){
		return saleChanceService.findSaleChanceById(saleChanceId);
		
	}
	//更新营销机会的开发状态
	@RequestMapping("/findAssignMan")
	@ResponseBody
	public List<User> findAssignMan(){
		return userService.findAssignMan();
	}
	
	@RequestMapping("/updateSaleChanceDevResult")
	@ResponseBody
	private ServerResponse updateSaleChanceDevResult(SaleChance salechance){
		return saleChanceService.updateSaleChanceDevResult(salechance);
	}
	
}
