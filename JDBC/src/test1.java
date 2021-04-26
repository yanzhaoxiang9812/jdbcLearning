import java.sql.*;

/*

 */
public class test1 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        try {

            //注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            // 获取链接
            String url = "jdbc:mysql://localhost:3306/yzx";//数据库url
            String uesr = "root";//数据库用户名
            String password = "root";//密码
            connection = DriverManager.getConnection(url,uesr,password);//创建Connection对象链接数据库
            System.out.println("数据库连接成功，该对象为：" + connection);

            //操作SQL
            statement = connection.createStatement();//创建Statement对象调用方法执行SQL语句对数据库操作

            String insertSql = "insert into dept(deptno,dname,loc) values(50,'搬砖','工地')";
            //statement.executeUpdate(insertSql);

            String updateSql = "update dept set dname = '打飞机',loc = '床上' where deptno = 20";
            statement.executeUpdate(updateSql);

            String deleteSql = "delete from dept where deptno = 0";
            statement.executeUpdate(deleteSql);

            String selectSql = "selet * from dept";
            //statement.executeUpdate(selectSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (statement !=null){
                statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                   if (connection !=null){
                        connection.close();}
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

    }
}


