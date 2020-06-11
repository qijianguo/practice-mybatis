package com.qijianguo;

import com.qijianguo.dao.IUserDao;
import com.qijianguo.dao.IUserProxyDao;
import com.qijianguo.dao.UserDao;
import com.qijianguo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;

public class MyBatisTest {

    @Test
    public void selectAllTest() throws IOException {
        // 配置文件加载，并加载成字节输入流
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 解析配置文件
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 生成sqlSession,默认开启一个事务，当进行增删改操作时不会自动提交事务，需要手动提交。
        SqlSession sqlSession = build.openSession();
        // 执行sql
        List<User> objects = sqlSession.selectList("user.findAll");
        System.out.println(objects);
        //
        sqlSession.close();
    }

    @Test
    public void insertTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        User user = new User();
        user.setId(5);
        user.setUsername("www555w");
        user.setPassword("www555w");
        sqlSession.insert("user.saveUser", user);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void updateTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        User user = new User();
        user.setId(4);
        user.setPassword("www123333");
        sqlSession.update("user.updateUser", user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        int id = 3;
        sqlSession.delete("user.deleteUser", id);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void selectAllDaoTest() throws IOException {
        IUserDao userDao = new UserDao();
        List<User> all = userDao.findAll();
        System.out.println(all);

    }

    @Test
    public void selectAllProxyDaoTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserProxyDao mapper = sqlSession.getMapper(IUserProxyDao.class);
        List<User> all = mapper.findAll();
        System.out.println(all);
        sqlSession.close();
    }

    @Test
    public void selectByConditionProxyDaoTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserProxyDao mapper = sqlSession.getMapper(IUserProxyDao.class);
        User user = new User();
        user.setUsername("root");
        user.setPassword("root");
        List<User> all = mapper.findByCondition(user);
        System.out.println(all);
        sqlSession.close();
    }


    @Test
    public void selectByIdsProxyDaoTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserProxyDao mapper = sqlSession.getMapper(IUserProxyDao.class);
        List<Integer> ids = Arrays.asList(1,2,3);
        List<User> all = mapper.findByIds(ids);
        System.out.println(all);
        sqlSession.close();
    }
}
