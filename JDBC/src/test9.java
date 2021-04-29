import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
        JDBC执行一条SQL语句回自动提交事务，这样会导致程序存在安全隐患

 */
public class test9 {
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    static DBC dbc = new DBC();
    static String selectSql = "select name,ifnull(balance,0) from balance";
    static String updateSql = "update balance set balance = ? where name = ? ";
    public static void main(String[] args) {
        conn =  dbc.getConnection();
        try {
            test9.showTabel(selectSql);
            // 模拟转账。
            //yzx账户向zx账户转账全部余额。
            test9.updateSql(updateSql,0,"yzx");
            /*
                由于JDBC自动提交事务，在23行代码运行完成之后，JDBC会使数据库提交事务，安全性存在性隐患。
             */
            test9.updateSql(updateSql,666,"zx");
            test9.showTabel(selectSql);
       } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    //  打印表方法
    private static void showTabel(String SQL) throws SQLException {
        ps = conn.prepareStatement(SQL);//预编译SQL语句
        rs = ps.executeQuery(SQL);
        while (rs.next()){
            String name =rs.getString(1);
            String balance =rs.getString(2);
            System.out.println(name + "\t" + balance);
        }
        System.out.println("=============================================");
    }
    private static void updateSql(String SQL,int i,String s) throws SQLException {
        ps = conn.prepareStatement(SQL);
        ps.setInt(1, i);
        ps.setString(2, s);
        ps.executeUpdate();
    }
}
