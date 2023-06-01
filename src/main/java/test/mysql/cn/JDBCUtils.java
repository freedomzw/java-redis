package test.mysql.cn;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            // 使用Properties对象加载配置文件
            // 创建Properties对象
            Properties pro = new Properties();

            // 读取配置文件中的数据
            //pro.load(new FileInputStream("redis\\src\\db.properties"));
            // 返回的输入流的路径默认到src路径
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
            pro.load(is);

            // 通过pro对象给属性赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");

            // 1.加载驱动
            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得连接
     *
     * @return 连接
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        // 2.获得连接
        Connection connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

    /**
     * 释放资源
     *
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void release(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
