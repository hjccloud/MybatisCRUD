package edu.hut;

import edu.hut.dao.UserDao;
import edu.hut.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 测试crud
 */
public class MyTest {
    private InputStream in;
    private UserDao userDao;
    private SqlSession session;
    @Before
    public void init() throws Exception {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlConfig.xml");
        //2.创建SqlSessionFactory工厂()
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession(true);
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(UserDao.class);
    }

    @Test
//    @Select("select * from user")
    public void findAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void deleteUser(){
        userDao.deleteUser(1);
        findAll();
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setUsername("亚托克斯");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("英雄联盟");
        userDao.saveUser(user);
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(6);
        user.setUsername("亚托克斯");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("英雄联盟");
        userDao.updateUser(user);
    }
    @Test
    public void testFindByName(){
        //5.执行查询一个方法
        List<User> users = userDao.findByName("%王%");

        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试查询总记录条数
     */
    @Test
    public void testFindTotal(){
        //5.执行查询一个方法
        int count = userDao.findTotal();
        System.out.println(count);
    }






    @After
    public void destroy() throws IOException {
//        session.commit();
        session.close();
        in.close();

    }


}
