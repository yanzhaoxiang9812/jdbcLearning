import java.sql.*;


/*
        JDBC工具类
     */
public class DBUilt {
    private DBUilt(){ }
    //使用静态代码块，类加载。
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //  获取数据库连接对象，并返回。
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/yzx";//数据库url
        String uesr = "root";//数据库用户名
        String password = "root";//密码
        return DriverManager.getConnection(url,uesr,password);
    }
    //关闭资源。
    public static void close(Connection conn, Statement sta, ResultSet res){
        if (res != null ){
            try {
                res.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (sta != null ){
            try {
                sta.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (conn != null ){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}