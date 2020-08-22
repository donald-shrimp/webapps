import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.sql.*;
import javax.xml.bind.DatatypeConverter;

public class Model {
  private DB_Access Access = new DB_Access();
  String[][][] timetable;
  Connection conn = null;
  Statement stmt = null;

  public Model() {
    timetable = new String[2][6][6];
  }

  public String[][][] getTimetable(String[] arr, int std_id) {
    try {
      conn = Access.return_conn();
      stmt = conn.createStatement();
    } catch (SQLException e) {
      //TODO: handle exception
    }

    int season = 0, row = 0, column = 0, arrcount = 0;
    String sql = null;
    //配列値の場合
    for (season = 0; season < 2; season++) {
      for (column = 0; column < 6; column++) {//列
        for (row = 0; row < 6; row++) {//行
          timetable[season][column][row] = arr[arrcount];
          int week = row;
          int period = column;
          try {
            sql =
              "SELECT * FROM timetable_kari WHERE std_id = " +
              std_id +
              " AND period = " +
              period +
              " AND weekday = " +
              week +
              " AND season = " +
              season;
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            int rowcount = rs.getRow();
            rs.first();
            if (rowcount == 1) {
              sql =
                "UPDATE timetable_kari SET class_name='" +
                arr[arrcount] +
                "' WHERE std_id = " +
                std_id +
                " AND period = " +
              period +
              " AND weekday = " +
              week +
              " AND season = " +
              season;
            } else {
              sql =
                "INSERT INTO timetable_kari (std_id,class_name,weekday,period,season) VALUES (" +
                std_id +
                " ,'" +
                arr[arrcount] +
                "'," +
                week +
                "," +
                period +
                "," +
                season +
                ")";
            }
            int count = stmt.executeUpdate(sql);
          } catch (SQLException e) {
            timetable[season][row][column] = e.getMessage();
          }
          arrcount++;
        }
      }
    }
    try {
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
    }
    return timetable;
  }
}
