
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

/*
        这个程序存在bug
            如若输入如下用户名和密码(该用户名密码并不存在)
                用户名：yzx
                密码：yzx' or '1'='1

                程序照旧可以登录
                这种现象叫做SQL注入
                实际上：yzx' or '1'='1 这个字符串会与sql语句合并，' or '1'='1会与程序中的sql语句合并，使sql语句查询结果永远正确。
 */
public class test5 {
        public static void main(String[] args) {
            DBC dbc = new DBC();
            Scanner s = new Scanner(System.in);
            Connection c = dbc.getConnection();
            System.out.print("输入用户名：");
            String username = s.nextLine();
            System.out.print("输入密码：");
            String password = s.nextLine();
            String selectSql = "select * from admin where username = '" + username + "' and password = '" + password + "'";
            try {

                if (c.createStatement().executeQuery(selectSql).next()){
                    System.out.println("登陆成功");
                }else {
                    System.out.println("登陆失败");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                dbc.closeCon();
            }
        }
    }
    class DBC{
        private Connection connection = null;
        public Connection getConnection(){
            ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
            String driver = resourceBundle.getString("driver");
            String url = resourceBundle.getString("url");
            String uesr = resourceBundle.getString("user");
            String password = resourceBundle.getString("password");
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url,uesr,password);
                System.out.println("数据库链接成功！");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return connection;
        }
        public void closeCon(){
            if(connection != null)
                try {
                    connection.close();
                    System.out.println("数据库链接已关闭！");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }
