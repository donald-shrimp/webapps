<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>


<%@ page import="java.util.Calendar" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <title>カレンダー３</title>

</head>

<body>



    <%
int intNen; //表示年
int intTuki; //表示月
String stWk1; //前月Formへの代入　年
String stWk2; //前月Formへの代入　月
String stWk3; //次月Formへの代入　年
String stWk4; //次月Formへの代入　月
int intWk1;
int intWk2;
int intWk3;
int intWk4;

Calendar cal = Calendar.getInstance();
int intYear = cal.get(Calendar.YEAR); //システムの年
int intMonth = cal.get(Calendar.MONTH); //システムの月
int intDate = cal.get(Calendar.DATE); //システムの日


//FORMから年、月を取得。nullならシステム年月を使う。
String nen = request.getParameter("nen1");
if (nen==null) {
intNen = intYear;
}else{
intNen =Integer.parseInt (nen);
}

String tuki = request.getParameter("tuki1");
if (tuki==null) {
intTuki = intMonth;
}else{
intTuki = Integer.parseInt (tuki);
}





cal.set(intNen, intTuki, 1); //表示月をセット
int intLastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //表示月の日数
int intFirstDay = cal.get(Calendar.DAY_OF_WEEK); //表示月の１日の曜日


//FORMデータを作成　　月は内部処理では実際の月マイナス１
intWk1 = intNen;
intWk2 = intTuki-1;
intWk3 = intNen;
intWk4 = intTuki+1;

if (intWk2 < 0 ){
intWk1--;
intWk2=11;
}

if (intWk4 > 11){
intWk3++;
intWk4 = 0;
}

//整数を文字に変換
stWk1 = Integer.toString (intWk1);
stWk2= Integer.toString (intWk2);
stWk3 = Integer.toString (intWk3);
stWk4= Integer.toString (intWk4);
%>

    <table cellpadding="10">
        <tr>
            <td valign="top">
                <form method="post" action="Calendar3.jsp">
                    <input type="hidden" name="nen1" value="<%=stWk1%>">
                    <input type="hidden" name="tuki1" value="<%=stWk2%>">
                    <input type="submit" value="<<">
                </form>
            </td>


            <td bgcolor="d2ffff">
                <%
out.println("<h2>   <u>"+intNen+"年"+(intTuki+1)+"月</u></h2>");
out.println("<font color=#FF0000>日</font>　月　火　水　木　金　<font color=#0000FF>土</font><br>");


int intIchi = 0; //出力数　カウント用

//１行目の空白出力
for (int i=1; i < intFirstDay; i++){
intIchi++;
out.println("   ");
}

// ７で割った余りが1なら赤、0なら青と改行
for (int i=1; i <=intLastDate ;i++){
intIchi++;

//曜日の色づけ
switch ( intIchi % 7) {
case 0:
out.println("<font color=#0000FF>");
break;
case 1:
out.println("<font color=#FF0000>");
break;
default:
out.println("<font color=#000000>");
}

//本日を太字
if (intNen ==intYear && intTuki==intMonth && i==intDate){
out.println("<b>");
}

//１桁のとき空白を前に挿入 　　出力
if (i < 10){
out.println(" "+i+" ");
}
else {
out.println( i+" ");
}


if (intNen ==intYear && intTuki==intMonth && i==intDate){
out.println("</b>");
}

out.println("</font>");

//土曜日なら改行
if (intIchi % 7 == 0) {
out.println("<br>");
}

}

%>
            </td>
            <td valign="top">
                <form method="post" action="Calendar3.jsp">
                    <input type="hidden" name="nen1" value="<%=stWk3 %>">
                    <input type="hidden" name="tuki1" value="<%=stWk4 %>">
                    <input type="submit" value=">>">
                </form>
            </td>
        </tr>
    </table>
</body>

</html>