package test.mysql.cn;


import java.sql.*;

public class MySqlDemo04 {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql:///test?characterEncoding=utf-8&serverTimezone=UTC";

    static final String DB_USER = "root";
    static final String DB_PWD = "mysql";

    public static void main(String[] args) {
        try {
            Connection conn = null;
            Statement stmt = null;

            //1.注册驱动
            Class.forName(JDBC_DRIVER);
            //2.定义sql
            String s1 = "select * from salaries";
            //3.获取Connection连接对象
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            //4.获取执行sql对象 Statement
            stmt = conn.createStatement();
            //5.执行sql
            ResultSet res = stmt.executeQuery(s1);
            //6.打印结果
            System.out.println(res);
            while (res.next()){
                int id = res.getInt(1);
                String name = res.getString("from_date");
                System.out.println(id+"-----------------"+name);

            }

            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (stmt != null) stmt.close();
//            } catch (SQLException se2) {
//            }// 什么都不做
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
        }
        System.out.println("Goodbye!");
    }
}
