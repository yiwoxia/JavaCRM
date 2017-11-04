package com.situ.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.EasyUIDataGrideResult;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.dao.CustomerLossMapper;
import com.situ.crm.dao.CustomerMapper;
import com.situ.crm.dao.CustomerOrderMapper;
import com.situ.crm.dao.CustomerServiceMapper;
import com.situ.crm.pojo.Customer;
import com.situ.crm.pojo.CustomerExample;
import com.situ.crm.pojo.CustomerExample.Criteria;
import com.situ.crm.pojo.CustomerLoss;
import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.service.ICustomerService;
import com.situ.crm.util.Util;
import com.situ.crm.vo.CustomerConstitute;
import com.situ.crm.vo.CustomerContribute;
import com.situ.crm.vo.CustomerServiceAnalysis;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerOrderMapper customerOrderMapper;
	@Autowired
	private CustomerLossMapper customerLossMapper;
	@Autowired
	private CustomerServiceMapper customerServiceMapper;
	// 根据分页信息返回所有数据
	@Override
	public EasyUIDataGrideResult findAll(Integer page,Integer rows,Customer customer) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		CustomerExample customerExample = new CustomerExample();
		//1.设置分页
		PageHelper.startPage(page,rows);
		//2.执行查询
		//rows(分页之后的数据)
		Criteria createCriteria = customerExample.createCriteria();
		String customerName = customer.getName();
		if(StringUtils.isNotEmpty(customer.getName())){
			createCriteria.andNameLike(Util.formatLike(customer.getName()));
		}
		if(StringUtils.isNotEmpty(customer.getNum())){
			createCriteria.andNumLike(Util.formatLike(customer.getNum()));
		}
		if(StringUtils.isNotEmpty(customer.getManagerName())){
			createCriteria.andManagerNameLike(Util.formatLike(customer.getManagerName()));
		}
		
		List<Customer> customerList = customerMapper.selectByExample(customerExample);
		//4,取分页后的结果
		PageInfo<Customer> pageInfo = new PageInfo<>(customerList);
		int total = (int)pageInfo.getTotal();
		result.setTotal(total);
		result.setRows(customerList);
		return result;
	}
	/**
	 * 删除
	 */
	@Override
	public ServerResponse delete(String ids) {
		String[] idArray = ids.split(",");
		for (String id : idArray) {
			customerMapper.deleteByPrimaryKey(Integer.parseInt(id));
		}
		return ServerResponse.createSuccess("数据已经成功删除");
	}
	/**
	 * 添加
	 */
	@Override
	public ServerResponse add(Customer customer) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//设置日期格式
		String date = df.format(new Date());
		String num = "KH" + date;
		customer.setNum(num);
		if(customerMapper.insert(customer) > 0 ){
			return ServerResponse.createSuccess("添加成功！");
		}

		return ServerResponse.createError("添加失败！");
	}
	/**
	 * 修改
	 */
	@Override
	public ServerResponse update(Customer customer) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSSS");//设置日期格式
		String date = df.format(new Date());
		String num = "KH" + date;
		customer.setNum(num);
		
		if (customerMapper.updateByPrimaryKey(customer) > 0) {
			return ServerResponse.createSuccess("修改成功! ");
			}
			return ServerResponse.createError("修改失败!");
		}
	//根据ID查询
	@Override
	public Customer findById(Integer id) {
		// TODO Auto-generated method stub
		return customerMapper.selectByPrimaryKey(id);
	}

	// 根据id查找营销机会
	@Override
	public ServerResponse<Customer> findCustomerById(Integer customerId) {
		Customer customer = customerMapper.selectByPrimaryKey(customerId);
		if (customer != null) {
			return ServerResponse.createSuccess("成功");
		}
		return ServerResponse.createError("服务器繁忙，请稍后重试");
	}
	// quartz定时任务
	@Override
	public void checkCustomerLoss() {

		 System.out.println("CustomerServiceImpl.checkCustomerLoss()");
		    // 1、查找流失客户
		    List<Customer> customerList = customerMapper.findLossCustomer();
		    for (Customer customer : customerList) {
		       // 2、实例化客户流失实体
		       CustomerLoss customerLoss = new CustomerLoss();
		        customerLoss.setCustomerNo(customer.getNum()); // 客户编号
		        customerLoss.setCustomerName(customer.getName()); // 客户名称
		        customerLoss.setCustomerManager(customer.getManagerName()); // 客户经理
		        // 3、查找指定客户最近的订单
		       CustomerOrder customerOrder = customerOrderMapper.findLastOrderByCustomerId(customer.getId());
		       if (customerOrder == null) {
		           customerLoss.setLastOrderTime(null);
		       } else {
		           customerLoss.setLastOrderTime(customerOrder.getOrderDate()); // 设置最近的下单日期
		       }
		        // 4、添加到客户流失表
		        customerLossMapper.insert(customerLoss);
		       // 5、客户表中客户状态修改成1 流失状态
		       customer.setStatus(1);
		        customerMapper.updateByPrimaryKeySelective(customer);
		    }

		
	}
	//查询客户订单总额
	@Override
	public EasyUIDataGrideResult findCustomerContribute(Integer page, Integer rows,
			CustomerContribute customerContribute) {
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		//1、设置分页 
		PageHelper.startPage(page, rows);
		//2、执行查询
		//rows(分页之后的数据)
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNoneBlank(customerContribute.getName())) {
			map.put("name", customerContribute.getName());
		}
		List<CustomerContribute> list = customerMapper.findCustomerContribute(map);
		//total
		PageInfo<CustomerContribute> pageInfo = new PageInfo<>(list);
		int total = (int)pageInfo.getTotal();
		
		result.setTotal(total);
		result.setRows(list);
		return result;
	}
	@Override
	public ServerResponse findCustomerConstitute() {
		List<CustomerConstitute> list = customerMapper.findCustomerConstitute();
		if (null != list) {
			return ServerResponse.createSuccess("查找成功", list);
		}
		return ServerResponse.createError("查找失败");
	}
	/*客户服务查询 */
	@Override
	public ServerResponse findService() {
		List<CustomerServiceAnalysis> list = customerServiceMapper.findService();
		return ServerResponse.createSuccess("查找成功", list);
	}


}


	

