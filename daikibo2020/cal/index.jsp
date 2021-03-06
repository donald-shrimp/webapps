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
  <link rel="stylesheet" type="text/css" href="../css/main.css" />
  <script src="../js/jquery-3.5.1.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8/jquery.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
  <script src="../js/unti.js"></script>
</head>

<body>
  <!-- header ここから -->
  <header>
    <h1><span class="under">NU-Calender</span></h1>
    <!-- <div class="clearfix"></div> -->

    <button class="js-plus-modal-open ">
      <i class="fa fa-plus fa-1.3x "></i> 作成
    </button>

    <button class="js-login-modal-open ">
      <i class="fa fa-user-plus fa-1.3x fa-fw "></i>
    </button>
    <!--modal-->

  </header>
  <!-- header ここまで -->
  <%@ page language="java" contentType="text/html; charset=utf-8"
  pageEncoding="utf-8"%>
  <%@ page import="java.util.Calendar" %>
  <%@ page import="java.time.LocalDate" %>
  <%
  Calendar cal = Calendar.getInstance();
  int year = cal.get(Calendar.YEAR);
  int month = cal.get(Calendar.MONTH)+1;
  LocalDate date = LocalDate.of(year,month,1); //2019年5月1日を表すオブジェクトを作成する
  int len = date.lengthOfMonth();  // 2019年5月の日数を求める
  int youbi = date.getDayOfWeek().getValue(); // 2019年5月1日の曜日を求める 月曜日が1で，日曜日が7
  int i=0,j=0,day=0,daycount=1,weekcount=0,num=0;
  
  %>
  <!-- モーダル -->
  <div class="modal js-plus-modal ">
    <div class="modal__bg js-modal-close"></div>
    <div class="modal__content">
      <!-- タブ -->
      <div class="tab_wrap">
        <input id="tab1" type="radio" name="tab_btn" checked>
        <input id="tab2" type="radio" name="tab_btn">

        <div class="tab_area">
          <label class="tab1_label" for="tab1">新規予定</label>
          <label class="tab2_label" for="tab2">時間割登録</label>
        </div>
        <div class="panel_area">

          <!-- パネル1・新規予定 -->
          <div id="panel1" class="tab_panel">

            <div class="cp_iptxt">
              <input type="text" placeholder="タイトル">
              <i class="fa fa-user fa-lg fa-fw" aria-hidden="true"></i>
            </div>


            <div id="timeselect">
              日時/開始時刻/終了時刻<br>
              <input type="text" class="datetimepicker" name="entry_datetime" value="2020-01-01 12:30:00">
            </div>

            <div class="cp_iptxt">
              <input type="text" placeholder="コメント">
              <i class="fa fa-envelope fa-lg fa-fw" aria-hidden="true"></i>
            </div>

          </div>

          <!-- パネル2・時間割登録 -->
          <div id="panel2" class="tab_panel">

            時間割登録
              <form action="../test" method="post">
                <%
                int zenkou = 0;
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
                      <select name="schedule">
                      <option selected value="null"></option>
                      <option value="大規模ソフトウェア開発法">大規模ソフトウェア開発法</option>
                      <option value="こくご">こくご</option>
                      <option value="さんすう">さんすう</option>
                      </select>
                    </td>
                    <%
                    }
                    out.println("</tr>");
                  }
                  out.println("</tbody></table>");
                }
                %>
                <input type="submit" value="送信">
              </form>
          </div>

        </div>
      </div>
      <a class="js-modal-close" href="">閉じる</a>
    </div>
    <!--modal__inner-->
  </div>



  <div class="modal js-login-modal ">
    <div class="modal__bg js-modal-close"></div>
    <div class="modal__content">
      <!-- <div class="cp_iptxt"> -->
      <p class="title">ログイン・新規登録</p>

      <div class="cp_iptxt">
        <input type="text" placeholder="大学配布メールアドレス">
        <i class="fa fa-envelope fa-lg fa-fw" aria-hidden="true"></i>
      </div>

      <div class="cp_iptxt">
        <input type="passeord" placeholder="パスワード">
        <i class="fa fa-key fa-lg fa-fw" aria-hidden="true"></i>
      </div>
      *パスワードは半角英数字のみ、8文字以上で登録が可能です

      <div class="cp_iptxt">
        <input type="password" placeholder="パスワード(確認用)">
        <i class="fa fa-key fa-lg fa-fw" aria-hidden="true"></i>
      </div>

      <!-- </div> -->
      <a class="js-modal-close" href="">閉じる</a>
    </div>
    <!--modal__inner-->
  </div>

  <!-- wrapper ここから -->
  <div id="wrapper">
    <!-- left ここから -->
    <div id="left">
      <div class="selectdiv">
        <label>
          <select id="calselect">
            <!-- <option selected> Select Box </option> -->
            <option selected value="mon">月</option>
            <option value="week">週</option>
            <option value="day">日</option>
            <option value="sem">学期</option>
          </select>
          <div class="clearfix"></div>
        </label>
      </div>

      <div class="clearfix"></div>

      <div id="minical">
        <table>
          <caption>
            <button id="prev">＜</button>
            <span> 2020 June </span>
            <button id="next" onclick="<%%>">＞</button>
          </caption>
          <thead>
            <tr>
              <th class="red">日</th>
              <th>月</th>
              <th>火</th>
              <th>水</th>
              <th>木</th>
              <th>金</th>
              <th>土</th>
            </tr>
          </thead>
          <tbody>
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
                out.println("<td class='red'>" + day + "</td>");
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
                  out.println("<td class='red'>" + day + "</td>");
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
          </tbody>
        </table>
      </div>

      <div id="todo">
        <table>
          <caption>
            本日の予定
          </caption>

          <tr>
            <td class="time">9:00<br />~<br />10:30</td>
            <td class="jugyo"><a href="error.html">大規模ソフトウェア開発及び演習</a></td>
          </tr>
          <tr>
            <td class="time">10:40<br />~<br />12:10</td>
            <td class="jugyo">大規模ソフトウェア開発及び演習</td>
          </tr>
          <tr>
            <td class="time">10:40<br />~<br />12:10</td>
            <td class="jugyo">大規模ソフトウェア開発及び演習</td>
          </tr>
          <tr>
            <td class="time">10:40<br />~<br />12:10</td>
            <td class="jugyo">大規模ソフトウェア開発及び演習</td>
          </tr>

          <tr>
            <td class="time">10:40<br />~<br />12:10</td>
            <td class="jugyo">大規模ソフトウェア開発及び演習</td>
          </tr>
        </table>
        <p><a>その他n件の予定</a></p>
      </div>
    </div>
    <!-- left ここまで -->

    <!-- right ここから -->
    <div id="right">

      <section id="mon">
        <table>
          <caption>
            <button class="prev" 　>　＜　　　</button>
            <span> 2020 June </span>
            <button class="next">　　　＞　</button>
          </caption>
          <thead>
            <tr>
              <th class="red">日
              </th>
              <th>月</th>
              <th>火</th>
              <th>水</th>
              <th>木</th>
              <th>金</th>
              <th>土</th>
            </tr>
          </thead>
          <tbody>
            <%
			out.println("<tr>");
        i=0;j=0;day=0;daycount=1;weekcount=0;num=0;
			if(youbi != 7){
				for(i = 0 ; i < youbi ; i++ ){
					out.println("<td></td>");
				}
			}
			

			while(i <=6 ){
				day++;
				if(youbi==7&&daycount==1){
					out.println("<td class='red'>" + day + "</td>");
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
						out.println("<td class='red'>" + day + "</td>");
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
          </tbody>
        </table>
      </section>

      <section id="week">
        <table>
          <caption>
            <button class="prev">＜</button>
            <span> 2020 June </span>
            <button class="next">＞</button>
          </caption>
          <thead>
            <tr>
              <th class="red">日
              </th>
              <th>月</th>
              <th>火</th>
              <th>水</th>
              <th>木</th>
              <th>金</th>
              <th>土</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td></td>

              <td align="right">1</td>

              <td align="right">2</td>

              <td align="right">3</td>

              <td align="right">4</td>

              <td align="right">5</td>

              <td align="right">6</td>
            </tr>
          </tbody>
        </table>
      </section>

      <section id="day">
        <table>
          <caption>
            <button class="prev">＜</button>
            <span> 2020 June </span>
            <button class="next">＞</button>
          </caption>
          <thead>
            <th class="time"></th>
            <th class="plan">月</th>
          </thead>
          <tbody>
            <tr>
              <td class="time">9:00</td>
              <td class="plan"></td>
            </tr>

          </tbody>
        </table>
      </section>

      <section id="sem">
        <object data="../pdf/学年暦_5.9版.pdf" type="application/pdf" width="100%" height="100%">
          <p><b>表示されない時の表示</b>: <a href="pdf.pdf">PDF をダウンロード</a>.</p>
        </object>
      </section>

    </div>
    <div class="clearfix"></div>
    <!-- right ここまで -->
  </div>
  <!-- wrapper ここまで -->
</body>

</html>