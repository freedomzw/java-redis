package test.mysql.cn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Test02 {
    public static void main(String[] args) throws Exception {
         /*
            需求:
                在控制台输入用户名和密码,查询数据库,如果数据库存在当前用户,显示登录成功!
                如果数据库不存在当前用户,显示登录失败!
         */
        // 0.获取控制台输入的用户名和密码
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String username = sc.nextLine();
        System.out.println("请输入密码:");
        String password = sc.nextLine();

        // 1.注册驱动,获得连接
        Connection connection = JDBCUtils.getConnection();

        // 2.创建执行sql语句对象
        Statement statement = connection.createStatement();

        // 3.执行sql语句,处理结果  select * from user where username = 'zs' and password = '123456';
        String sql = "select * from user where  username = '"+username+"' and password = '"+password+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        // 创建User对象,封装数据
        User user = null;
        while (resultSet.next()) {
            user = new User();// 查询到了就new对象
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setNickname(resultSet.getString("nickname"));
        }
        // 4.释放资源
        JDBCUtils.release(resultSet,statement,connection);
        // 5.判断结果,显示信息(判断user对象是否为null,如果为null说明就没有查询到数据,否则就查询到了)
        if (user == null){
            // 没有查询到
            System.out.println("登录失败!");
        }else{
            // 查询到
            System.out.println("登录成功,欢迎回来!");
        }

    }

}
