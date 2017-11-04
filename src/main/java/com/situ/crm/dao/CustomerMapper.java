package com.situ.crm.dao;

import com.situ.crm.pojo.Customer;
import com.situ.crm.pojo.CustomerExample;
import com.situ.crm.vo.CustomerConstitute;
import com.situ.crm.vo.CustomerContribute;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    /*在customer表中查找已经流失的客户*/
	List<Customer> findLossCustomer();
	/*查询客户订单总额*/
	List<CustomerContribute> findCustomerContribute(Map<String, Object> map);
	/*客户构成分析 */
	List<CustomerConstitute> findCustomerConstitute();
}