//package test.mysql.cn;
//
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
//public class MySqlDemo05 {
//
//
//    /**
//     * @Description:
//     * @Param:
//     * @return:
//     * @Author: Mr.ZhaoWei
//     * @Date: 2020/12/5
//     */
//
//
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql:///test?characterEncoding=utf-8&serverTimezone=UTC";
//    private static final String DB_USER = "root";
//    private static final String DB_PWD = "mysql";
//
//    public List<Emp> findAll() {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet res = null;
//        try {
//            // 1.注册驱动
//            Class.forName(JDBC_DRIVER);
//            // 2.创建conn对象
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
//            // 3.SQL语句
////            String sql = "select * from emp";
//            String sql = "select * from salaries";
//            // 4.创建statement对象
//            stmt = conn.createStatement();
//            // 5.执行SQL语句
//            res = stmt.executeQuery(sql);
//            Emp emp = null;
//            List<Emp> list = new ArrayList<Emp>();
//
//            while (res.next()) {
//                int id = res.getInt("id");
//                String ename = res.getString("ename");
//                int job_id = res.getInt("job_id");
//                int mgr = res.getInt("mgr");
//                Date joindate = res.getDate("joindate");
//                double salary = res.getDouble("salary");
//                double bonus = res.getDouble("bonus");
//                int dept_id = res.getInt("dept_id");
//                emp = new Emp();
//                emp.setId(id);
//                emp.setEname(ename);
//                emp.setJob_id(job_id);
//                emp.setMgr(mgr);
//                emp.setJoindate(joindate);
//                emp.setSalary(salary);
//                emp.setBonus(bonus);
//                emp.setDept_id(dept_id);
////                    int emp_no = res.getInt("emp_no");
////                    int salary = res.getInt("salary");
////                    Date from_date = res.getDate("from_date");
////                    Date to_date = res.getDate("to_date");
////
//                list.add(emp);
//
//            }
//
//            // 6.打印sql
//
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (res != null) {
//                try {
//                    res.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (stmt != null) {
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return null;
//    }
//
//    public List<Stu> findAll2() {
//        Connection conn = null;
//        Statement stat = null;
//        ResultSet rs = null;
//
//        try {
//            //1.注册驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //2.创建Conn连接
//            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
//            //3.执行的SQL连接
//            String sql = "select * from stu";
//            //4.创建Statement对象
//            stat = conn.createStatement();
//            //5.执行sql语句
//            rs = stat.executeQuery(sql);
//            //6.执行结果
//            System.out.println(rs);
//
//            System.out.println("-------------------------");
//
//            Stu stu = null;
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//
//                stu = new Stu();
//
//                stu.setId(id);
//                stu.setName(name);
//                List<Stu> list = new ArrayList<Stu>();
//                list.add(stu);
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (stat != null) {
//                try {
//                    stat.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return null;
//
//    }
//}
