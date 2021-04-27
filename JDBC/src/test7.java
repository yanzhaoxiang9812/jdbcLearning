import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
        Statement进行SQL语句拼接
 */
public class test7 {
    public static void main(String[] args) {
        DBC dbc = new DBC();
        Connection c = null;
        Statement sta = null;
        ResultSet rs = null;
        Scanner s = new Scanner(System.in);
        System.out.print("输入desc或asc：");
        String str = s.nextLine();
        String Sql = "select ename,sal from emp order by sal " + str;
        try {
            c = dbc.getConnection();
            sta = c.createStatement();
            rs = sta.executeQuery(Sql);
            while (rs.next()){
                String ename =rs.getString("ename");
                String sal =rs.getString("sal");
                System.out.println(ename + "\t" + sal);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            dbc.closeCon();
        }
    }

}
