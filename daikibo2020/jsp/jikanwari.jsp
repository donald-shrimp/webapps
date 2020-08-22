
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

    request.setCharacterEncoding("utf-8");
    String[][][] timetable =  (String[][][]) request.getAttribute("tmp");
%>
<!DOCTYPE html>
<html lang="ja">
<!--
        306024　海老原毅史
　　γ⌒ヽ
　　/ ･◇･)
　∈ノ　／
　＿_Ｗ<br>~＿

-->

<head>
  <meta charset="utf-8" />
  <link rel="shortcut icon" href="favicon.ico" />
  <title>NU-Calender</title>
  <meta name="viewport" content="device-height">
  <link rel="stylesheet" type="text/css" href="./css/cal.css" />
  <script src="./js/jquery-3.5.1.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
  <script src="./js/unti.js"></script>
</head>
<body>
    <header>
    <h1><span class="under">NU-Calender</span></h1>
    <!-- <div class="clearfix"></div> -->
    </header>
    <div id="wrapper">
    <% 
    
    
     %>

    <p class="title">&nbsp;&nbsp;&nbsp;&nbsp;登録完了</p>
              
                <%
                

                int zenkou = 0,i=0,j=0;
                for(zenkou = 0; zenkou < 2 ; zenkou++){
                  out.println("<table><thead><caption>");
                  if(zenkou==0){
                    out.println("前期");
                  }else if(zenkou==1){
                    out.println("後期");
                  }
                  out.println("</caption><tr><th></th><th>月</th><th>火</th><th>水</th><th>木</th><th>金</th><th>土</th></tr></thead><tbody>");
                  
                  for( i = 1; i < 7 ; i++){
                    out.println("<tr>");
                    out.println("<td>"+i+"</td>");
                    for( j = 0; j < 6 ;j++){
                    %>
                    <td>
                      <%out.println(timetable[zenkou][i-1][j]);%>
                    </td>
                    <%
                    }
                    out.println("</tr>");
                  }
                  out.println("</tbody></table>");
                }
                %>
              </div>
</body>