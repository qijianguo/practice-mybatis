package com.qijianguo;

import com.qijianguo.mapper.IOrderMapper;
import com.qijianguo.pojo.Order;
import com.qijianguo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrderTest {

    @Test
    public void selectOrderAndUser() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();

        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<Order> byOrderAndUser = mapper.findByOrderAndUser();
        System.out.println(byOrderAndUser);
        sqlSession.close();
    }

    @Test
    public void selectUserAndOrder() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<User> userAndOrder = mapper.findUserAndOrder();
        System.out.println(userAndOrder);
        sqlSession.close();
    }

    /* 注解测试 ：*/

    @Test
    public void selectByIdTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        Order order = mapper.selectById(1);
        System.out.println(order);
        sqlSession.close();
    }

}
