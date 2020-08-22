$(function ($) {
  var val = $("#calselect option:selected").val();
  $("section#" + val).fadeIn();

  $("#calselect").change(function () {
    console.log("test");
    val = $("#calselect option:selected").val();
    console.log(val);
    // if (val == 'select') return;
    $("section").fadeOut();
    $("section#" + val).fadeIn();
  });
});

$(function () {
  $(".js-plus-modal-open").on("click", function () {
    $(".js-plus-modal").fadeIn();
    return false;
  });
  $(".js-modal-close").on("click", function () {
    $(".js-plus-modal").fadeOut();
    return false;
  });
});

$(function () {
  $(".js-login-modal-open").on("click", function () {
    $(".js-login-modal").fadeIn();
    return false;
  });
  $(".js-modal-close").on("click", function () {
    $(".js-login-modal").fadeOut();
    return false;
  });
});

// $(function(){
// 	$('.datetimepicker').each(function(){
// 		//日時はhiddenに切り替え。見た目は日付と時間入力に変更。
// 		var $dt = $('<input type="hidden">').attr('name', this.name).val(this.value);
// 		//見た目を調整するためのclassがあれば指定
// 		var $d = $('<input type="text">').addClass('calendar');
// 		var $t = $('<select></select>').addClass('input-small');
// 		$(this).after($dt, $d, ' ', $t);
// 		//日付の方はdatepickerにする
// 		$d.datepicker({dateFormat: 'yy-mm-dd','など、オプションを適当に':'指定してください'});
// 		//時刻の方は10分おきのプルダウンなどにする
// 		var hh = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'];
// 		var mm = ['00', '10', '15', '20', '25', '30', '35', '40', '45', '50', '55',];
// 		for (var i = 0; i < hh.length; ++i) {
// 			for (var j = 0; j < mm.length; ++j) {
// 				var $o = $('<option></option>')
// 							.attr('value', hh[i] + ':' + mm[j] + ':00')
// 							.text(hh[i] + ':' + mm[j]);
// 				$t.append($o);
// 			}
//     }
    
// 		//初期値があれば反映
// 		$d.val(this.value.replace(/^(\d\d\d\d-\d\d-\d\d).*$/, "$1"));
// 		$t.val(this.value.replace(/^.*(\d\d:\d\d:\d\d).*$/, "$1"));
// 		//日付変更時と時間変更時にhidden化した方へ値を反映する処理を仕込んでおく
// 		function x(){$dt.val($d.val() + ' ' + $t.val());}
// 		$d.datepicker('option', 'onSelect', x);
// 		$d.on('change', x);
// 		$t.on('change', x);
// 		//オリジナルはさようなら
// 		$(this).remove();
// 	});
// });
