import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/*
                避免出现SQL注入

 */
public class test6 {
    public static void main(String[] args) {
        PreparedStatement ps = null;//该对象可以避免出现SQL注入
        //ps = c.prepareStatement(selectSql)这条语句会先对sql语句预处理。从而避免出现SQL注入。
        ResultSet resultSet = null;
        DBC dbc = new DBC();
        Scanner s = new Scanner(System.in);
        Connection c = dbc.getConnection();
        System.out.print("输入用户名：");
        String username = s.nextLine();
        System.out.print("输入密码：");
        String password = s.nextLine();
        String selectSql = "select * from admin where username = ? and password = ?";
        try {
            ps = c.prepareStatement(selectSql);
            ps.setString(1,username);//这里的1是sql语句中的第1个问号
            ps.setString(2,password);
            resultSet = ps.executeQuery();
            if (resultSet.next()){
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
