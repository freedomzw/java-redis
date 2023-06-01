package test.mysql.cn;


import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class MySqlDemo01 {

    public static void main(String[] args) throws SQLException {
        // 	需求:查询所有的用户, 输出到控制台
        // 分析:
        // 1.创建项目,拷贝驱动jar包到模块下,并添加到classpath路径中
        // 2.注册驱动
        DriverManager.registerDriver(new Driver() {
            @Override
            public Connection connect(String url, Properties info) throws SQLException {
                return null;
            }

            @Override
            public boolean acceptsURL(String url) throws SQLException {
                return false;
            }

            @Override
            public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
                return new DriverPropertyInfo[0];
            }

            @Override
            public int getMajorVersion() {
                return 0;
            }

            @Override
            public int getMinorVersion() {
                return 0;
            }

            @Override
            public boolean jdbcCompliant() {
                return false;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return null;
            }
        });

        // 3.获得连接
        String url = "jdbc:mysql://localhost:3306/school2";
        String username = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, username, password);

        // 4.创建执行sql的对象
        Statement statement = connection.createStatement();

        // 5.执行sql语句,处理结果
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            // 取出来
            //System.out.print(resultSet.getObject("id")+" ");
            //System.out.print(resultSet.getObject("username")+" ");
            //System.out.print(resultSet.getObject("password")+" ");
            //System.out.println(resultSet.getObject("nickname"));
            System.out.print(resultSet.getObject(1) + "  ");
            System.out.print(resultSet.getObject(2) + "  ");
            System.out.print(resultSet.getObject(3) + "  ");
            System.out.println(resultSet.getObject(4));
            System.out.println("------------------------------------------------");
        }

        // 6.释放资源
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
}
