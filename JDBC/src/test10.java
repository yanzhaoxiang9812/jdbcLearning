/*

            提交事务的重要方法：

                开始事务
                    conn.setAutoCommit(false);
                提交事务
                     conn.commit();
                回滚事务
                     conn.rollback();
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test10 {
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    static DBC dbc = new DBC();
    static String selectSql = "select name,ifnull(balance,0) from balance";
    static String updateSql = "update balance set balance = ? where name = ? ";
    public static void main(String[] args) {
        conn =  dbc.getConnection();
        try {
            //      ****开始事务****
            //将自动提交事务机制改为手动提交。
            conn.setAutoCommit(false);

            test10.showTabel(selectSql);
            test10.updateSql(updateSql,0,"yzx");
            test10.updateSql(updateSql,666,"zx");

            //    ****提交事务*****
            //如果转账完成并且没有出现异常，再提交事务。
            conn.commit();
            test10.showTabel(selectSql);
        } catch (SQLException throwables) {

            //  如果转账出现异常，则需要保证账户数据安全，使用回滚机制。
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }

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
