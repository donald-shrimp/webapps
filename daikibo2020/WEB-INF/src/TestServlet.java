import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import java.sql.*;
import javax.sql.*;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import jdk.nashorn.internal.runtime.RewriteException;

public class TestServlet extends HttpServlet{
    
    private Classes classes;    // 時間割を管理するオブジェクト
    private Students Students;              // アカウント管理用オブジェクト
    private Std_data login_data;            // ログインユーザー用オブジェクト
    private Class_data class_data;

    public TestServlet() {  /* Subjectを初期化する */
        classes = new Classes();
        Students = new Students();
        login_data = new Std_data();
        class_data = new Class_data();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
            response.setContentType("text/html;charset=utf-8");        
            request.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            
        // ルーティング用
        String command = request.getParameter("mode");
        
        //パラメータ"address"の値を取得する（配列値の場合はgetParameterValuesを使用）
        
        if(command == null){
            // パラメーターがない場合はログイン画面へリダイレクトさせる
            response.sendRedirect("./index.html");
        }
        
        //新規登録
        if(command.equals("get_account")){
            String std_id_str = request.getParameter("std_id");
            String password = request.getParameter("password");
            int std_id = 0;
            try{
                std_id = Integer.parseInt(std_id_str);
            }catch(NumberFormatException e){
                RequestDispatcher disp = request.getRequestDispatcher("/error.html");
            }
            
            if(Students.registUser(std_id, password) == 0){
                String error = "成功";
                request.setAttribute("error", error);
                RequestDispatcher disp = request.getRequestDispatcher("/index.html");
                disp.forward(request, response);
            }else{
                RequestDispatcher disp = request.getRequestDispatcher("/error.html");
            }
        }
        
        //時間割を登録する
        if(command.equals("get_timetable")){
            int std_id = 0;
            try {
                std_id = Integer.parseInt(request.getParameter("std_id"));
            } catch (NumberFormatException e) {
                
            }
            String[] arr = request.getParameterValues("schedule");
            Model tt = new Model(); //時間割を取得
            
            //送信するものを作成
            String[][][] timetable = tt.getTimetable(arr,std_id);
            //リクエストスコープにセット
            request.setAttribute("tmp", timetable);
            
            //フォワード
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/jikanwari.jsp");
            rd.forward(request, response);
        }
        
        //ログイン
        if(command.equals("login")){
            String std_id_str = request.getParameter("std_id");
            String password = request.getParameter("password");
            int std_id = 0;
            try{
                std_id = Integer.parseInt(std_id_str);
            }catch (NumberFormatException e){ 
                RequestDispatcher disp = request.getRequestDispatcher("./error.html");
            }

            // out.println(std_id_str);
            Std_data login_data = Students.verify(std_id, password);
            Class_data class_data = classes.todo(std_id);
            
            // out.println(login_data.getResult());
            if(login_data.getResult().equals("0")){
                // エラーがなければセッションに格納
                HttpSession accountSession = request.getSession(true);
                
                int id_session = login_data.getId();
                int std_id_session = login_data.getStd_id();
                String[] todolist = new String[6];
                todolist = class_data.getNames();
                request.setAttribute("todo", todolist);
                accountSession.setAttribute("std_id", std_id_session);
                RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/index.jsp");
                dispatcher.forward(request, response);
            }else{

             RequestDispatcher disp = request.getRequestDispatcher("./error.html");
                
            }
          }

          if(command.equals("last_month")){
            String std_id_str = request.getParameter("std_id");
            int std_id = 0;
            try{
                std_id = Integer.parseInt(std_id_str);
            }catch (NumberFormatException e){ 
                RequestDispatcher disp = request.getRequestDispatcher("./error.html");
            }
            Class_data class_data = classes.todo(std_id);
            int month=0;
            int year=2020;
            try {
                month = Integer.parseInt(request.getParameter("last_month"));
            } catch (NumberFormatException e) {
                
            }
            try {
                year = Integer.parseInt(request.getParameter("year"));
            } catch (NumberFormatException e) {
                
            }
            String[] todolist = class_data.getNames();
            request.setAttribute("todo", todolist);
            request.setAttribute("last_month", month);
            request.setAttribute("year", year);
            RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/index.jsp");

            //デバッグ用
            // out.println(todo[0]);
            // out.println("month="+month);
            // out.println("year="+year);

            dispatcher.forward(request, response);
          }

          if(command.equals("next_month")){
            String std_id_str = request.getParameter("std_id");
            String[] todo = request.getParameterValues("todo");
            int std_id = 0;
            try{
                std_id = Integer.parseInt(std_id_str);
            }catch (NumberFormatException e){ 
                RequestDispatcher disp = request.getRequestDispatcher("./error.html");
            }
            Class_data class_data = classes.todo(std_id);
            int month=0;
            int year=2020;
            try {
                month = Integer.parseInt(request.getParameter("next_month"));
            } catch (NumberFormatException e) {
                
            }
            try {
                year = Integer.parseInt(request.getParameter("year"));
            } catch (NumberFormatException e) {
                
            }
            String[] todolist = class_data.getNames();
            request.setAttribute("todo", todolist);
            request.setAttribute("next_month", month);
            request.setAttribute("year", year);
            
            //デバッグ用
            // out.println(todo[0]);
            // out.println("month="+month);
            // out.println("year="+year);

            RequestDispatcher dispatcher = request.getRequestDispatcher("./jsp/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
