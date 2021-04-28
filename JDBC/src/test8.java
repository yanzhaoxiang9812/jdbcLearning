import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
            PreparedStatement对数据库的增删改

 */
public class test8 {
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    static DBC dbc = new DBC();
    static String selectSql = "select * from dept";
    static String updateSql = "update dept set dname = ?,loc = ? where deptno = ? ";
    static String deleteSql = "delete from dept where deptno = ?";
    static String insertSql = "insert into dept(deptno,dname,loc) values(?,?,?)";
    public static void main(String[] args) {
        conn =  dbc.getConnection();
        try {
            test8.showTabel(selectSql);
            /*
            test8.insertData(insertSql,"102","FUCK","HEEL");
            test8.showTabel(selectSql);
            test8.deleteSql(deleteSql,99);
            test8.showTabel(selectSql);
            test8.updateSql(updateSql,"YZX","XI'an" , "10");
            test8.showTabel(selectSql);

             */
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
            String deptno =rs.getString(1);
            String dname =rs.getString(2);
            String loc =rs.getString(3);
            System.out.println(deptno + "\t" + dname + "\t" + loc);
        }
        System.out.println("=============================================");
    }
    private static void insertData(String SQL ,String s1,String s2,String s3) throws SQLException {
        ps = conn.prepareStatement(SQL);
        ps.setString(1,s1);
        ps.setString(2,s2);
        ps.setString(3,s3);
        ps.executeUpdate();
    }
    private static void deleteSql(String SQL,int i) throws SQLException {
        ps = conn.prepareStatement(SQL);
        ps.setInt(1, i);
        ps.executeUpdate();
    }
    private static void updateSql(String SQL,String s1,String s2,String s3) throws SQLException {
        ps = conn.prepareStatement(SQL);
        ps.setString(1, s1);
        ps.setString(2, s2);
        ps.setString(3, s3);
        ps.executeUpdate();
    }
}
