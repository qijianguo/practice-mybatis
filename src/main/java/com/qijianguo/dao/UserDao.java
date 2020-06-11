package com.qijianguo.dao;

import com.qijianguo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDao implements IUserDao{

    public List<User> findAll() throws IOException {
        // 配置文件加载，并加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 解析配置文件
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 生成并开启sqlSession,默认开启一个事务，当进行增删改操作时不会自动提交事务，需要手动提交。
        SqlSession sqlSession = build.openSession();
        // 执行sql
        List<User> objects = sqlSession.selectList("user.findAll");
        // 关闭sqlSession
        sqlSession.close();
        return objects;
    }
}
