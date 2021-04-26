import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
        注册驱动的非常方法
           类加载方法
        */
public class test2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/yzx";//数据库url
        String uesr = "root";//数据库用户名
        String password = "root";//密码
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,uesr,password);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
