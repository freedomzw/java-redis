package test.dao;

import test.entity.User;

import java.util.List;

/***
 *
 *  UserDao 接口就是我们的持久层接口（也可以写成 UserMapper) .我们就写成UserDao
 *  执行findAll()方法的目的是:执行一个查询所有用户的SQL语句，
 *  并且将查询到的结果封装到List<User>
 */

public interface UserDao {
    /**
     * 查询user的全部内容
     */
    public List<User> findAll();

    public int addUser(User user);

    /**
     * 根据id进行删除
     */
    public int deleteById(int id);

    /**
     * 修改用户
     */
    public void updateUser(User user);

  /**
   * @Author zhaowei
   * @Description 
   * @Date  
   * @Param 
   * @return 
  ***/
    public User findById(int id);


}
