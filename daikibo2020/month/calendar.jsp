<!-- 306024海老原毅史 -->

<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.time.LocalDate" %>
<%
String months = request.getParameter("month");
String years = request.getParameter("year");
int month=2;
int year = 0;
try{
	month = Integer.parseInt(months);
	year = Integer.parseInt(years);
}catch(NumberFormatException e){
	out.println("<p>不正な値が入力されました</p>");
	return;
}
if(month < 0||month > 12){
	out.println("<p>不正な値が入力されました</p>");
	return;
}
LocalDate date = LocalDate.of(year,month,1); //2019年5月1日を表すオブジェクトを作成する
int len = date.lengthOfMonth();  // 2019年5月の日数を求める
int youbi = date.getDayOfWeek().getValue(); // 2019年5月1日の曜日を求める 月曜日が1で，日曜日が7
int i=0,j=0,day=0,daycount=1,weekcount=0,num=0;

%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="content-type" content="text/html" charset="utf-8">
	<title>cal</title>
</head>

<body>
	<h1><%=year%>年<%=month%>月</h1>
	<table>
		<tr>
			<td>
				<font color=red>日</font>
			</td>
			<td>月</td>
			<td>火</td>
			<td>水</td>
			<td>木</td>
			<td>金</td>
			<td>土</td>
		</tr>


		<%
			out.println("<tr>");
			if(youbi != 7){
				for(i = 0 ; i < youbi ; i++ ){
					out.println("<td></td>");
				}
			}
			

			while(i <=6 ){
				day++;
				if(youbi==7&&daycount==1){
					out.println("<td><font color='red'>" + day + "</font></td>");
					daycount++;
				}else{
					out.println("<td>" + day + "</td>");
				}
				i++;
			}

			out.println("</tr>");
			
			num=len-day-28;

			if(num>0){
				weekcount=5;
			}else{
				weekcount=4;
			}

			for(j = 0;j < weekcount;j++){

				daycount=1;
				out.println("<tr>");

				for(i = 0; i < 7;i++){
					day++;
					if(daycount==1){
						out.println("<td><font color='red'>" + day + "</font></td>");
					}else if(day>len){
						out.println("<td></td>");
					}else{
						out.println("<td>" + day + "</td>");
					}
					daycount++;
				}

				out.println("</tr>");

			}
			%>



	</table>

	<br />
	<form action="index.html">
		<input type=submit value="戻る">
	</form>
</body>

</html>