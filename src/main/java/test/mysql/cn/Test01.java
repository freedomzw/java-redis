package test.mysql.cn;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Test01 {
    @Test
    // 往day20_1数据库的user表中添加一条记录
    public void insert() throws Exception{
        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获得连接
        String url = "jdbc:mysql://localhost:3306/school2";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建执行sql语句对象
        Statement statement = connection.createStatement();

        // 4.执行sql语句,处理结果
        String sql = "insert into user values(null,'zl','123456','赵六')";
        int rows = statement.executeUpdate(sql);
        System.out.println("受影响的行数:"+rows);

        // 5.释放资源
        if (statement != null){
            statement.close();
        }

        if (connection != null){
            connection.close();
        }
    }

    @Test
    // 修改day20_1数据库的user表中wangwu的密码为123456
    public void update()throws Exception{
        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获得连接
        String url = "jdbc:mysql://localhost:3306/school2";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建执行sql语句对象
        Statement statement = connection.createStatement();

        // 4.执行sql语句,处理结果
        String sql = "update user set password = '123456' where username = 'wangwu'";
        int rows = statement.executeUpdate(sql);
        System.out.println("受影响的行数:"+rows);

        // 5.释放资源
        if (statement != null){
            statement.close();
        }

        if (connection != null){
            connection.close();
        }
    }

    @Test
    // 删除day20_1数据库的user表中id为3的记录
    public void delete() throws Exception{
        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获得连接
        String url = "jdbc:mysql://localhost:3306/school2";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建执行sql语句对象
        Statement statement = connection.createStatement();

        // 4.执行sql语句,处理结果
        String sql = "delete from user where id = 3";
        int rows = statement.executeUpdate(sql);
        System.out.println("受影响的行数:"+rows);

        // 5.释放资源
        if (statement != null){
            statement.close();
        }

        if (connection != null){
            connection.close();
        }
    }

    @Test
    // 查询day20_1数据库的user表中所有记录
    public void select1() throws Exception{
        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获得连接
        String url = "jdbc:mysql://localhost:3306/school2";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建执行sql语句对象
        Statement statement = connection.createStatement();

        // 4.执行sql语句,处理结果
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);

        // 创建ArrayList集合,限制集合元素类型为User
        ArrayList<User> list = new ArrayList<>();

        while (resultSet.next()) {
            // 创建一个User对象,用来封装记录的列数据
            User use = new User();
            // 封装数据
            use.setId(resultSet.getInt("id"));
            use.setUsername(resultSet.getString("username"));
            use.setPassword(resultSet.getString("password"));
            use.setNickname(resultSet.getString("nickname"));

            // 添加对象到集合
            list.add(use);
        }

        // 5.释放资源
        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }

        if (connection != null){
            connection.close();
        }

        //...
        for (User user1 : list) {
            System.out.println(user1);
        }
    }

    @Test
    // 查询day20_1数据库的user表中姓名为zs并且密码为123456的记录
    public void select2() throws Exception{
        // 1.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获得连接
        String url = "jdbc:mysql://localhost:3306/school2";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建执行sql语句对象
        Statement statement = connection.createStatement();

        // 4.执行sql语句,处理结果
        String sql = "select * from user where username = 'zs' and password = '123456'";
        ResultSet resultSet = statement.executeQuery(sql);

        User use = null;

        while (resultSet.next()) {
            // 创建一个User对象,用来封装记录的列数据
            use = new User();

            // 封装数据
            use.setId(resultSet.getInt("id"));
            use.setUsername(resultSet.getString("username"));
            use.setPassword(resultSet.getString("password"));
            use.setNickname(resultSet.getString("nickname"));
        }
        // 5.释放资源
        if (resultSet != null){
            resultSet.close();
        }

        if (statement != null){
            statement.close();
        }

        if (connection != null){
            connection.close();
        }
        // ...
        if (use == null){
            System.out.println("失败");
        }else{
            System.out.println("成功");
        }
    }
}
