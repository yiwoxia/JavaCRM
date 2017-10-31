package com.situ.crm.dao;

import com.situ.crm.pojo.User;
import com.situ.crm.pojo.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 返回数据字典名字去重之后的列表
     * @return
     */
	List<User> findRoleName();
	/**
	 * 检测用户名是否存在
	 * @param user
	 * @return
	 */
	User checkUser(User user);
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	int updateUserByName(User user);
}