import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
        将链接数据库的所有信息配置到配置文件中。
 */
public class test3 {
    public static void main(String[] args) {
        //  使用资源绑定器，绑定属性配置文件。
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String driver = resourceBundle.getString("driver");
        String url = resourceBundle.getString("url");
        String uesr = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,uesr,password);
            System.out.println(connection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
