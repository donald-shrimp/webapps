import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BankServlet extends HttpServlet {
    private Bank bank; /* 口座の管理をするオブジェクト */

    public BankServlet() { /* bankを初期化する */
        bank = new Bank();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /* 口座開設処理 */
        // フォームデータの取得
        String name = request.getParameter("name");
        String command = request.getParameter("command");
        String amount = request.getParameter("amount");
        // HTML文書の書き出し
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();

        // 口座開設・口座閉鎖用
        if (command.equals("open") || command.equals("close")) {
            pw.println("<html lang=\"ja\">");
            pw.println("<!--306024　海老原毅史-->");
            pw.println("<meta http-equiv=\"refresh\" content=\"3;URL=./index.html \">");
            pw.println("<head>");
            pw.println("<meta charset=\"utf-8\" />");
            pw.println("<link rel=\"shortcut icon\" href=\"favicon.ico\">");
            pw.println("<title>わたし銀行(十日で五割)</title>");
            pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            pw.println("</head>");

            pw.println("<body>");
            pw.println("<center>");
            pw.println("<table border=\"6\" bordercolor=\"red\" width=\"300\">");
            pw.println("<td>");
            pw.println("<marquee>");

            if (command.equals("open")) {
                // 口座開設
                int judge = bank.open(name);
                if (name.length() == 0) {
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println("口座開設に失敗しました。名前が正しく入力されていません。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                } else if (judge == 0) {/* 口座開設成功のHTML生成 */
                    pw.println("<span class=\"rainbow\">成功しました！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　口座開設に成功しました。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                } else if (judge == -7) { /* 口座開設失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　口座開設に失敗しました。既に名前が登録されています。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                } else { /* 口座開設失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様");
                    pw.println("　不明なエラー");
                    pw.println("により失敗しました。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                }
            } else if (command.equals("close")) {
                // 口座解約
                int judge = bank.close(name);
                if (name.length() == 0) {
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println("口座解約に失敗しました。名前が正しく入力されていません。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                } else if (judge == 0) {/* 口座解約成功のHTML生成 */
                    pw.println("<span class=\"rainbow\">成功しました！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　口座解約に成功しました。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                } else if (judge == -1) { /* 口座解約失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　口座解約に失敗しました。残高が０円ではありません。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                } else if (judge == -7) { /* 口座解約失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　口座解約に失敗しました。口座リストに氏名が存在しません。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                } else { /* 口座解約失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様");
                    pw.println("　不明なエラー");
                    pw.println("により失敗しました。3秒後にリダイレクトします。");
                    pw.println("</strong>");
                }
            }
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
        } else if (command.equals("withdraw") || command.equals("deposit") || command.equals("balance")) {
            // その他三つ用・リダイレクトなし
            pw.println("<html lang=\"ja\">");
            pw.println("<!--306024　海老原毅史-->");
            pw.println("<head>");
            pw.println("<meta charset=\"utf-8\" />");
            pw.println("<link rel=\"shortcut icon\" href=\"favicon.ico\">");
            pw.println("<title>わたし銀行(十日で五割)</title>");
            pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<center>");
            pw.println("<table border=\"6\" bordercolor=\"red\" width=\"300\">");
            pw.println("<td>");
            pw.println("<marquee>");
            if (command.equals("withdraw")) {
                // 引き出し
                int judge = bank.withdraw(name, amount);
                int money = bank.showBalance(name);
                if (name.length() == 0 || amount.length() == 0) {
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println("出金に失敗しました。名前または金額が正しく入力されていません。");
                    pw.println("</strong>");
                } else if (judge == 0) {/* 引き出し成功のHTML生成 */
                    pw.println("<span class=\"rainbow\">成功しました！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の引き出しに成功しました。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else if (judge == -1) { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の引き出しに失敗しました。残高が足りません。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else if (judge == -3) { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の引き出しに失敗しました。金額が不正です。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else if (judge == -4) { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の引き出しに失敗しました。金額が整数ではありません。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else if (judge == -7) { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の引き出しに失敗しました。口座リストに氏名が存在しません。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　不明なエラーにより出金に失敗しました。");
                    pw.println("</strong>");
                }

            } else if (command.equals("deposit")) {
                // 預金
                int judge = bank.deposit(name, amount);
                int money = bank.showBalance(name);
                if (name.length() == 0 || amount.length() == 0) {
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println("預金に失敗しました。名前または金額が正しく入力されていません。");
                    pw.println("</strong>");
                } else if (judge == 0) {/* 引き出し成功のHTML生成 */
                    pw.println("<span class=\"rainbow\">成功しました！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の預金に成功しました。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else if (judge == -3) { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の預金に失敗しました。金額が不正です。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else if (judge == -4) { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の預金に失敗しました。金額が整数ではありません。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else if (judge == -7) { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様 " + amount + "円の預金に失敗しました。口座リストに氏名が存在しません。残高は" + money + "円です。");
                    pw.println("</strong>");
                } else { /* 引き出し失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　不明なエラーにより預金に失敗しました。");
                    pw.println("</strong>");
                }

            } else if (command.equals("balance")) {
                // 残高紹介
                int money = bank.showBalance(name);
                if (name.length() == 0) {
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println("残高照会に失敗しました。名前が正しく入力されていません。");
                    pw.println("</strong>");
                } else if (money >= 0) {/* 口座開設成功のHTML生成 */
                    pw.println("<span class=\"rainbow\">成功しました！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　残高照会に成功しました。残高は" + money + "円です");
                    pw.println("</strong>");
                } else if (money == -7) {/* 口座開設成功のHTML生成 */
                    pw.println("<span class=\"rainbow\">成功しました！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　残高紹介に失敗しました。口座リストに氏名が存在しません。");
                    pw.println("</strong>");
                } else { /* 口座開設失敗のHTML生成 */
                    pw.println("<span class=\"rainbow\">処理失敗！</span>");
                    pw.println("</marquee>");
                    pw.println("</td>");
                    pw.println("</table>");
                    pw.println("</center>");
                    pw.println("<div class=\"box3\">");
                    pw.println("<strong>");
                    pw.println(name + "　様　不明なエラーにより失敗しました。");
                    pw.println("</strong>");
                }
            }
            pw.println("<br>");
            pw.println("<input type=\"button\" value=\"戻る\" onclick=\"location.href='index.html'\">");
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }
}