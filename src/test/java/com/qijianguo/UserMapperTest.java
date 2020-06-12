package com.qijianguo;

import com.qijianguo.mapper.IOrderMapper;
import com.qijianguo.mapper.IUserMapper;
import com.qijianguo.pojo.Order;
import com.qijianguo.pojo.Role;
import com.qijianguo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserMapperTest {

    private IUserMapper mapper;

    private SqlSession sqlSession;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void findUserOrderAndRoleTest() throws IOException {
        List<User> userOrderAndRole = mapper.findUserOrderAndRole();
        System.out.println(userOrderAndRole);
    }

    @Test
    public void findRoleUserTest() throws IOException {
        List<Role> roleUser = mapper.findRoleUser();
        System.out.println(roleUser);
    }

    /* 注解方式测试*/
    @Test
    public void insertTest() {
        User user = new User();
        user.setUsername("ffff");
        user.setPassword("eeeee");
        mapper.insert(user);
        sqlSession.commit();
    }

    /* 注解方式测试*/
    @Test
    public void updateTest() {
        User user = new User();
        user.setId(4);
        user.setPassword("xfda34JFd");
        mapper.update(user);
        sqlSession.commit();
    }

    @Test
    public void selectTest() {
        System.out.println(mapper.selectById(4));
    }

    @Test
    public void deleteTest() {
        mapper.deleteById(5);
        sqlSession.commit();
    }

    @Test
    public void selectAllTest() {
        System.out.println(mapper.selectAll());
    }

    @Test
    public void selectAllUserAndRoleTest() {
        System.out.println(mapper.selectAllUserAndRole());
    }

    @Test
    public void selectAllUserAndRoleAndOrderTest() {
        System.out.println(mapper.selectAllUserAndRoleAndOrder());
    }

    @After
    public void after() {
        sqlSession.close();
    }

}
