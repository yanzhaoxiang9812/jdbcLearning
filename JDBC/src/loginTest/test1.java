package loginTest;

import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

public class test1 {
    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        System.out.print("输入用户名：");
        String username = s1.nextLine();
        System.out.print("输入密码：");
        String password = s1.nextLine();
        databaseConnection(username, password);
    }

    private static void databaseConnection(String s1, String s2) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String driver = resourceBundle.getString("driver");
        String url = resourceBundle.getString("url");
        String uesr = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, uesr, password);
            System.out.println(connection + "链接成功！");
            statement = connection.createStatement();
            String selectSql = "select * from admin where username = '" + s1 + "' and password = '" + s2 + "'";
            resultSet = statement.executeQuery(selectSql);
            if (resultSet.next()) {
                System.out.println( s1 + "欢迎登录成功");
            } else {
                System.out.println( "登录失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}

