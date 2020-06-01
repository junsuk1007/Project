<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	$(window).on("load", function() {

		//페이지 로딩 완료 후, 멜론 순위가져오기 함수 실행함 
		getTop50();
	});

	//멜론 순위가져오기
	function getTop50() {

		//Ajax 호출
		$.ajax({
			url : "/project/getTop50.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {

				var news_rank = "";

				for (var i = 0; i < json.length; i++) {
					news_rank += ((i+1)+"위 "+json[i].title+" : ");
					/* news_rank += (json[i].collect_time + "수집일 | "); */
					news_rank += (json[i].repeat + "회 <br>");

				}

				$('#news_rank').html(news_rank);
			}
		})

	}
</script>
</head>
<body>
	<h1>이 시각 많이 본 뉴스</h1>
	<hr />
	<div id="news_rank"></div>
	<br />
	<hr />
</body>
</html>


