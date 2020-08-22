import java.sql.*;
import javax.sql.*;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;

public class Students{
    private DB_Access Access = new DB_Access();
    // private int id; // アカウントID
    // private int std_id; // 学生番号
    // Connection conn = null;
    // Statement stmt = null;

    private Std_data std_data;
    private Connection conn = null;
    private Statement stmt = null;

    public Students(){
        std_data = new Std_data();
    }

    public Std_data verify(int std_id, String password){
        String result = "-1";
        String encrypted_pw ="a";
        try{
            conn = Access.return_conn();
            stmt = conn.createStatement();

            encrypted_pw = sha256(password);

            String sql = "SELECT * FROM users WHERE (std_id = " + std_id + ")AND(password ='"+encrypted_pw+"')";
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println(rs);
            rs.last();
            int number_of_row = rs.getRow(); // 行数(行がない場合は0, 正常時の最小値1)
            // rs.beforeFirst();

            if(number_of_row == 1){
                int id = rs.getInt("id");
                std_id = rs.getInt("std_id");
                result = "0";
                std_data.setData(id, std_id,result);
            }else{
                int id = -1;
                std_id = -1;
                result = "ERROR No.1: ";
                std_data.setData(id, std_id,result);
            }
        }catch(SQLException e){
            result = "SELECT * FROM users WHERE (std_id = " + std_id + ")AND(password ='"+encrypted_pw+"')";
            int id = -45;
            std_id = -45;
            std_data.setData(id, std_id,result);
        }finally {
			try {
				if (stmt != null) {
                    stmt.close();
                    result = "0";
				}
				if (conn != null) {
                    conn.close();
                    result = "0";
				}
			} catch (SQLException e) {
                result = "ERROR No.3: " + e.getMessage();
                int id = -2;
                std_id = -2;
                std_data.setData(id, std_id,result);
			}
        }
        return std_data;
    }

    public int registUser(int std_id, String password) {
        int result = -1;

        try{
            conn = Access.return_conn();
            stmt = conn.createStatement();

            String encrypted_pw = sha256(password);
			String sql = "SELECT * FROM users WHERE std_id=" + std_id;
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            if (rs.getRow()==0) {
                sql = "INSERT INTO users (std_id,password) VALUES (" + std_id + " ,'" + encrypted_pw + "')";
                int count = stmt.executeUpdate(sql);
                if(count == 0){
                    result = -1;
                }else{
                    result = 0;
                }
			}else{
                result = -1;
            }
        }catch(SQLException e){
            System.out.println("ERROR:"+e.getMessage());
            result = -1;
        }finally {
			try {

				if (stmt != null) {
                    stmt.close();
                    result = 0;
				}
				if (conn != null) {
                    conn.close();
                    result = 0;
				}
			} catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
                result = -1;
			}
        }
        return result;
    }

    public String sha256(String data){
        byte[] result = null;
        try{
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            result = sha256.digest(data.getBytes());
        }catch(NoSuchAlgorithmException e){

        }
        if(result == null){
            return "ERROR!!!";
        }
        return DatatypeConverter.printHexBinary(result);
    }
}