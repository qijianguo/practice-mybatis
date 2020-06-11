package com.qijianguo.dao;

import com.qijianguo.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * 普通模式
 */
public interface IUserDao {

    List<User> findAll() throws IOException;

}
