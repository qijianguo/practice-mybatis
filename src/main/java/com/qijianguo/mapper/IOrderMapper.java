package com.qijianguo.mapper;

import com.qijianguo.pojo.Order;
import com.qijianguo.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderMapper {

    List<Order> findByOrderAndUser();

    List<User> findUserAndOrder();

    @Select(value = "select * from orders where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "title", column = "title"),
            @Result(property = "price", column = "price"),
            @Result(property = "time", column = "time"),
            @Result(property = "user", column = "uid", javaType = User.class,
                one = @One(select = "com.qijianguo.mapper.IUserMapper.selectById")),
    })
    Order selectById(int id);

    @Select(value = "select * from orders where uid = #{uid}")
    List<Order> selectByUserId(int uid);
}
