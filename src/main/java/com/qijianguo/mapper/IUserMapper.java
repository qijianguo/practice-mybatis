package com.qijianguo.mapper;

import com.qijianguo.pojo.Role;
import com.qijianguo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserMapper {

    List<User> findUserOrderAndRole();

    List<Role> findRoleUser();

    /* 注解开发*/

    @Insert(value = "insert into user (username, password) values (#{username}, #{password})")
    void insert(User user);

    @Update(value = "update user set password = #{password} where id = #{id}")
    void update(User user);

    @Select(value = "select * from user where id = #{id}")
    User selectById(int id);

    @Delete(value = "delete from user where id = #{id}")
    void deleteById(int id);

    @Select(value = "select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "orders", column = "id", javaType = List.class,
                many = @Many(select = "com.qijianguo.mapper.IOrderMapper.selectByUserId"))
    })
    List<User> selectAll();

    @Select(value = "select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "roles", column = "id", javaType = List.class,
                many = @Many(select = "com.qijianguo.mapper.IRoleMapper.findRoleByUid"))
    })
    List<User> selectAllUserAndRole();

    @Select(value = "select * from user")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "roles", column = "id", javaType = List.class,
                    many = @Many(select = "com.qijianguo.mapper.IRoleMapper.findRoleByUid")),
            @Result(property = "orders", column = "id", javaType = List.class,
                    many = @Many(select = "com.qijianguo.mapper.IOrderMapper.selectByUserId"))
    })

    List<User> selectAllUserAndRoleAndOrder();
}
