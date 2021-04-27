import javax.xml.transform.Result;
import java.sql.*;
import java.util.ResourceBundle;

/*
            处理查询结果集
 */
public class test4 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement sta = null;
        ResultSet res = null;
        //  使用资源绑定器，绑定属性配置文件。
        ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
        String driver = resourceBundle.getString("driver");
        String url = resourceBundle.getString("url");
        String uesr = resourceBundle.getString("user");
        String password = resourceBundle.getString("password");
        try {

            Class.forName(driver);//注册驱动
            conn = DriverManager.getConnection(url,uesr,password);//获取数据库操作对象
            System.out.println(conn + "连接成功");
            sta = conn.createStatement();//执行SQL
            String selectSql = "select * from dept";
            res = sta.executeQuery(selectSql);//处理查询结果集
            /*
            boolean b1 = res.next();
            System.out.println(b1);//true 查询结果中有数据。
            if (b1){//true 取出数据
                String deptno = res.getString(1);
                String dname = res.getString(2);
                String loc = res.getString(3);//这里的1，2，3指的是列数。
                System.out.println(deptno + "   " + dname + "   " + loc);
            }
             */
            //修改程序如下
            while (res.next()){
                String deptno = res.getString(1);
                String dname = res.getString(2);
                String loc = res.getString(3);//这里的1，2，3指的是列数。
                System.out.println(deptno + "   " + dname + "   " + loc);
            }
            String selectSql2 = "select ename,job as '工作',sal  from emp";
            res = sta.executeQuery(selectSql2);

            //以列名取数据
            while (res.next()){
                String ename = res.getString("ename");
                String job = res.getString("工作");//列名必须是结果集的列名
                double sal = res.getDouble("sal");//可以指定数据类型取数据
                System.out.println(ename + "   " + job + "   " + sal);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //释放资源
        finally {
            if ( res !=null){
                try {
                    res.close();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }
            if ( sta !=null){
                try {
                    sta.close();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }
            if ( conn !=null){
                try {
                    conn.close();
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
