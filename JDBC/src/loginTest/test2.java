package loginTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class test2 {
    public static void main(String[] args) {
        DB db = new DB();
        Scanner s = new Scanner(System.in);
        Connection c = db.getConnection();
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
            db.closeCon();
        }
    }
}
class DB{
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
