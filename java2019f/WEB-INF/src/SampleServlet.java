//306024
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class SampleServlet extends HttpServlet {
public void doGet(HttpServletRequest request,
 HttpServletResponse response)
 throws ServletException, IOException {
response.setContentType("text/html; charset=UTF-8");
PrintWriter pw = response.getWriter();
pw.println("<HTML>");
pw.println ("<HEAD><TITLE>sample</TITLE></HEAD>");
pw.println ("<BODY> 306024 海老原毅史</BODY>");
pw.println ("</HTML>");
}
} 