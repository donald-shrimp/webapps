import java.sql.*;
import java.lang.ClassNotFoundException;

public class DB_Access {
    private Connection conn = null;
    private String user = "u306024";// ユーザ名
    private String password = "p306024";// パスワード
    private String dsn = "jdbc:mysql://localhost/db_u306024?useUnicode=true&characterEncoding=utf8";// データベースのurl

    public Connection return_conn() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        conn = DriverManager.getConnection(dsn, user, password);
        return conn;
    }
}