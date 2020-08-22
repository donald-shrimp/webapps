import java.sql.*;
import javax.sql.*;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.time.LocalDate;

public class Classes{
    private DB_Access Access = new DB_Access();

    private Class_data class_data;
    private Connection conn = null;
    private Statement stmt = null;

    public Classes(){
        class_data = new Class_data();
    }

    public Class_data todo(int std_id){
        
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int today = cal.get(Calendar.DATE);
        LocalDate weekday = LocalDate.of(year,month,today); //今日を表すオブジェクトを作成する
        int today_weekday = weekday.getDayOfWeek().getValue()-1; // 今日の曜日を求める 月曜日が1で，日曜日が7
        int season = 0;
        String[] class_name = new String[6];
        if(month>8 || month<4){
            season = 1;
        }

        try {
            conn = Access.return_conn();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM timetable_kari WHERE (std_id=" + std_id + ")AND(season = " + season + ")AND(weekday = " + today_weekday + ")";
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int number_of_row = rs.getRow();
             rs.first();
            for(int i=0;i<number_of_row;i++){
                class_name[i] = rs.getString("class_name");
                rs.next();
            }
        } catch (SQLException e) {
            for(int i=0;i<6;i++){
                class_name[i] = "失敗した";
            }
        }finally {
			try {
				if (stmt != null) {
                    stmt.close();
				}
				if (conn != null) {
                    conn.close();
				}
			} catch (SQLException e) {
                int id = -2;
                std_id = -2;
			}
        }
        System.out.println("hoge");
        class_data.setData(std_id,class_name);
        return class_data;
        
    }
}