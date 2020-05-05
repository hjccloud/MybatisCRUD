package edu.hut.dao;

import edu.hut.domain.User;


import java.util.List;

/**
 * 用户的持久层接口
 */
public interface UserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 根据id删除用户
     */
    void deleteUser(int id);

    /**
     * 添加用户
     */
    void saveUser(User user);

    /**
     * 更新用户
     */
    void updateUser(User user);

    List<User> findByName(String s);

    int findTotal();
}
