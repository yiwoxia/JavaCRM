package com.situ.crm.dao;

import com.situ.crm.pojo.CustomerOrder;
import com.situ.crm.pojo.CustomerOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CustomerOrderMapper {
    int countByExample(CustomerOrderExample example);

    int deleteByExample(CustomerOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CustomerOrder record);

    int insertSelective(CustomerOrder record);

    List<CustomerOrder> selectByExample(CustomerOrderExample example);

    CustomerOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    int updateByExample(@Param("record") CustomerOrder record, @Param("example") CustomerOrderExample example);

    int updateByPrimaryKeySelective(CustomerOrder record);

    int updateByPrimaryKey(CustomerOrder record);
    /*在customer_order表中根据客户id查找最后一个订单*/
	CustomerOrder findLastOrderByCustomerId(Integer id);
}