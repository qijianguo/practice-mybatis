package com.qijianguo.dao;

import com.qijianguo.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * 代理模式
 */
public interface IUserProxyDao {

    List<User> findAll() throws IOException;

    List<User> findByCondition(User user);

    List<User> findByIds(List<Integer> ids);

}
