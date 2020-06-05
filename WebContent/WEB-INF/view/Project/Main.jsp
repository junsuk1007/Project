<%@page import="poly.dto.NewsDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="poly.dto.NewsTitleDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<NewsTitleDTO> nList = (List<NewsTitleDTO>) request.getAttribute("nList");
	List<NewsDTO> rList = (List<NewsDTO>) request.getAttribute("rList");

	if (nList == null) {
		nList = new ArrayList<>();
	} else if (rList == null) {
		rList = new ArrayList<>();
	}

	NewsTitleDTO nDTO = new NewsTitleDTO();
%>

<!doctype html>
<html>
<head>


<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Author Meta -->
<meta name="author" content="colorlib">
<!-- Meta Description -->
<meta name="description" content="">
<!-- Meta Keyword -->
<meta name="keywords" content="">
<!-- meta character set -->
<meta charset="UTF-8">
<!-- Site Title -->
<title>Interior</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700"
	rel="stylesheet">
<!--
    CSS
    ============================================= -->
<link rel="stylesheet" href="/theme/css/linearicons.css">
<link rel="stylesheet" href="/theme/css/font-awesome.min.css">
<!-- <link rel="stylesheet" href="/theme/css/bootstrap.css"> -->
<link rel="stylesheet" href="/theme/css/magnific-popup.css">
<link rel="stylesheet" href="/theme/css/nice-select.css">
<link rel="stylesheet" href="/theme/css/animate.min.css">
<link rel="stylesheet" href="/theme/css/owl.carousel.css">
<link rel="stylesheet" href="/theme/css/main.css">


<script src="http://d3js.org/d3.v4.min.js"></script>
<script
	src="https://rawgit.com/jasondavies/d3-cloud/master/build/d3.layout.cloud.js"
	type="text/JavaScript">
	</script>
<script type="text/javascript">
	
/* 	$('#exampleModal0').click(function() {

		//페이지 로딩 완료 후, 뉴스 순위가져오기 함수 실행함 
		getOpinion();
	}); */

	//오피니언마이닝 가져오기
	function getOpinion(elem) {

		//Ajax 호출
		$.ajax({
			url : "/project/getOpinion.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {
				var opn = json[elem]
				console.log(json);									
				
				<% for (int i=0;i<10;i++){ %>
				$('#optext<%=i%>').html(opn+"%");
				<% } %>			
				
				d(opn, elem);				
				
			}
		})

	}
</script>
<script type="text/javascript">
	window.onload = function() {
		setTimeout (function() {
			scrollTo(0,0);
		},100);
	}
</script>
<style>
#svg .cir {
	stroke-dasharray: 628.3185307179587;
	stroke-dashoffset: 628.3185307179587;
	transition: stroke-dashoffset 1s linear;
}

#svg {
	transform: rotate(-90deg);
}
</style>
</head>
<body>
	<header id="header" id="home2">
		<div class="header-top">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-sm-6 col-4 header-top-left no-padding">
					</div>

				</div>
			</div>
		</div>
		<div class="container main-menu">
			<div class="row align-items-center justify-content-between d-flex">
				<!-- <div id="logo">
					<a href="" style="color: white; font-size: 20px;"><i>FROM
							WAY</i><br> <i>DOWNTOWN</i></a>
				</div> -->
				<nav id="nav-menu-container">
					<ul class="nav-menu">
						<li class="menu-active"><a href="#home">Home</a></li>
						<li class="menu-has-children"><a href="">핫 워드</a>
							<ul>
								<li><a href="#window1" id="btn2">오늘의 핫 워드</a></li>
								<li><a href="#window2" id="btn3">누적 핫 워드</a></li>
							</ul></li>
					</ul>
				</nav>
				<!-- #nav-menu-container -->
			</div>
		</div>
	</header>
	<!-- #header -->

	<!-- start banner Area -->
	<div style="background-color: rgba(4, 9, 30, 0.7);">
		<section class="banner-area relativ" id="home">
			<div class="container">
				<div
					class="row fullscreen d-flex justify-content-center align-items-center">
					<div class="banner-content col-md-12 justify-content-center"
						style="margin-top: 35%;">
						<h1>
							<i>FROM WAY DOWNTOWN</i> <br>
						</h1>
						<p class="text-white mx-auto">네이버 뉴스에 자주 실리는 키워드를 한눈에 찾아볼 수
							있습니다.</p>

					</div>
				</div>
			</div>
		</section>
	</div>

	<section class="feature-area section-gap" id="window1">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-md-12 pb-40 header-text text-center">
					<h1 class="pb-10" style="color: #777;">오늘의 핫 뉴스</h1>
					<p class="" style="color: #777;">가장 많은 관심을 받은 뉴스입니다. 제목을 클릭 시
						감정분석 결과를 확인할 수 있습니다.</p>
				</div>
			</div>
			<div class="row text-white"
				style="align-items: center; justify-content: center;">
				<div id="news_rank1" style="text-align: center;">
					<%
						for (int i = 0; i < 10; i++) {
					%>
					<h4>					
						<a data-toggle="modal" data-target="#exampleModal<%=i%>" class="abc" onclick="getOp(<%=i %>)">
							 <%	if (i == 0) { %>
							  <img alt="" src="/theme/img/gold.png"> &nbsp; 
							  <%} else if (i == 1) { %>
							   <img alt="" src="/theme/img/silver.png"> &nbsp; 
							  <% } else if (i == 2) { %>
							   <img alt="" src="/theme/img/bronze.png"> &nbsp;
							    <% } %>
							     &lt;<%=i + 1%>위&gt;<%=CmmUtil.nvl(rList.get(i).getTitle())%></a>
					</h4>
					<br>
					<%
						}
					%>
				</div>
			</div>
		</div>


		<%
			for (int i = 0; i < 10; i++) {
		%>
		<!-- Modal -->
		<div class="modal fade MODAL<%=i%>" id="exampleModal<%=i%>" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" data-backdrop="static" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">오피니언 마이닝 결과</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close" onclick="zero()">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						&lt;<%=CmmUtil.nvl(rList.get(i).getTitle())%>&gt;<br>의 감정분석
						결과입니다.<br>100%에 가까울 수록 긍정, 0%에 가까울수록 부정문이며 50%는 평서문입니다.
					</div>
					<div style="text-align: center;" >
						<svg viewPort="0 0 320 320" width="320" height="320" id="svg"
							xmlns="http://www.w3.org/2000/svg">
  							<circle class="cir" cx="160" cy="160" r="100" 
								fill="none" stroke-width="10" stroke-linecap="round" id="cir" />
  							<text class="tex1" x="50%" y="57%" text-anchor="middle"
								 font-size="60px"
								style="transform: rotate(90deg);transform-origin: center;" id="optext<%=i%>"></text>
  							
						</svg>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal" onclick="zero()">Close</button>						
					</div>
				</div>
			</div>
		</div>

		<!-- 모달끝 -->
		<%
			}
		%>

		<script>
		
		 function getOp(elem) {
			setTimeout(function() {
				getOpinion(elem);
				}, 100);
		}
		
		function d(opn, idx){
			console.log('abc');
		    var val = opn;
		    console.log(document.getElementsByClassName('cir')[idx]);
		    if (val == 1){
		    	document.getElementsByClassName('cir')[idx].setAttribute('stroke', "#000000");
		    	$('.tex1').attr("fill","#000000");		    	
		    }else if(val == 15){
		    	document.getElementsByClassName('cir')[idx].setAttribute('stroke', "#ff5c2b");
		    	$('.tex1').attr("fill","#ff5c2b");		    	
		    }else if(val == 40){
		    	document.getElementsByClassName('cir')[idx].setAttribute('stroke', "#ffca2b");
		    	$('.tex1').attr("fill","#ffca2b");	
		    }else if(val ==50){
		    	document.getElementsByClassName('cir')[idx].setAttribute('stroke', "#fcff5c");
		    	$('.tex1').attr("fill","#fcff5c");	
		    }	    
		    else if(val == 60){
		    	document.getElementsByClassName('cir')[idx].setAttribute('stroke', "#90e072");
		    	$('.tex1').attr("fill","#90e072");	
		    }else if(val ==75){
		    	document.getElementsByClassName('cir')[idx].setAttribute('stroke', "#5cfffa");
		    	$('.tex1').attr("fill","#5cfffa2");	
		    }else{
		    	document.getElementsByClassName('cir')[idx].setAttribute('stroke', "#3b96ff");
		    	$('.tex1').attr("fill","#3b96ff");	
		    }

		    var $circle = $('#svg .cir');
		    var r = $circle.attr('r');
		    var per = ((100 - val) / 100) * Math.PI * r * 2;

		    $circle.css({
		      strokeDashoffset: per
		    });
		}
		
		function zero(elem){
			$('#cir').removeAttr("stroke");
	    	$('.tex1').removeAttr("fill")	
			var $circle = $('#svg .cir');
		    $circle.css({
		      strokeDashoffset: 628.3185307179587
		      
		    });
		}
		</script>

	</section>

	<hr>
	<!-- End feature Area -->

	<section class="feature-area" id="window2" style="padding-top: 120px;">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="col-md-12 pb-40 header-text text-center">
					<h1 class="pb-10" style="color: #777;">누적 핫 워드</h1>
					<p class="" style="color: #777;">농구기사에서 가장 많이 사용된 단어들을 보여줍니다.</p>
				</div>
			</div>
		</div>
	</section>

	<!-- Start callto-action Area -->
	<section class="callto-action-area">
		<div class="container">
			<div class="row justify-content-center">
				<div class="callto-action-wrap col-lg-12 relative">
					<div class="content" id="cloud"
						style="display: grid; align-content: center; justify-content: center;">

						<script type="text/javascript">
    var weight = 3,   // change me
        width = 960,
        height = 500;

    var fill = d3.scaleOrdinal(d3.schemeCategory20);
    d3.csv("/theme/csv/wordcloud.csv", function(d) {
        return {
          text: d.word,
          size: +d.freq*weight
        }
      },
      function(data) {
        d3.layout.cloud().size([width, height]).words(data)
          //.rotate(function() { return ~~(Math.random() * 2) * 90; })
          .rotate(0)
          .font("Impact")
          .fontSize(function(d) { return d.size; })
          .on("end", draw)
          .start();

        function draw(words) {
          d3.select("#cloud").append("svg")
              .attr("width", width)
              .attr("height", height)
            .append("g")
              .attr("transform", "translate(" + width/2 + "," + height/2 + ")")
            .selectAll("text")
              .data(words)
            .enter().append("text")
              .style("font-size", function(d) { return d.size + "px"; })
              .style("font-family", "Impact")
              .style("fill", function(d, i) { return fill(i); })
              .attr("text-anchor", "middle")
              .attr("transform", function(d) {
                return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
              })
            .text(function(d) { return d.text; });
        }
      });
  </script>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End callto-action Area -->
	<hr>
	<%
		if (session.getAttribute("SS_ADMIN_CODE") != null) {
	%>
	<section class="feature-area section-gap" id="window3">
		<div class="container" style="text-align: -webkit-center;">
			<div class="row d-flex justify-content-center">
				<div class="col-md-12 pb-40 header-text text-center">
					<h1 class="pb-10" style="color: #777;">핫 워드 삭제</h1>
					<p class="" style="color: #777;">부적절한 단어를 삭제합니다.</p>
				</div>
			</div>
			<div class="row"
				style="align-items: center; justify-content: center; display: grid; width: 800px; height: 400px; overflow: scroll;">
				<div class="row" style="width: 750px;">
					<div class="col-lg-2 col-md-2 col-sm-2  lg-9-st">단어</div>
					<div class="col-lg-2 col-md-2 col-sm-2 lg-9-st">반복 횟수</div>
					<div class="col-lg-2 col-md-2 col-sm-2 lg-9-st"></div>
				</div>
				<%
					for (NewsTitleDTO rDTO : nList) {
				%>
				<div class="row" style="width: 750px;">
					<div class="col-lg-2 col-md-2 col-sm-2  lg-9-st"><%=CmmUtil.nvl(rDTO.getTitle())%></div>
					<div class="col-lg-2 col-md-2 col-sm-2 lg-9-st"><%=rDTO.getRepeat()%></div>
					<div class="col-lg-2 col-md-2 col-sm-2 lg-9-st">
						<a
							href="/project/titleDelete.do?title=<%=CmmUtil.nvl(rDTO.getTitle())%>">삭제</a>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</section>
	<hr>
	<%
		}
	%>

	<!-- start footer Area -->
	<footer class="footer-area section-gap">
		<div class="container">
			<div class="row">
				<div class="col-lg-5 col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>About Us</h6>
						<p>If you own an Iphone, you’ve probably already worked out
							how much fun it is to use it to watch movies-it has that.</p>
						<p class="footer-text">
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>document.write(new Date().getFullYear());</script>
							All rights reserved | This template is made with <i
								class="fa fa-heart-o" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a> and
							distributed by <a href="https://themewagon.com/" target="_blank">ThemeWagon</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
				</div>
				<%
					if (session.getAttribute("SS_ADMIN_CODE") == null) {
				%>
				<div class="col-lg-5  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>관리</h6>
						<p>코드를 입력하세요.</p>
						<div class="" id="mc_embed_signup">
							<form target="_blank" action="/AdminCheck.do" method="post"
								class="form-inline">
								<input class="form-control" id="code" name="admin_code"
									placeholder="Access Code" type="password">
								<button class="click-btn btn btn-default" type="submit"
									id="code_check" formtarget="_self">
									<i class="lnr lnr-arrow-right" aria-hidden="true"></i>
								</button>
								<script type="text/javascript">
									$(function(){
										$('#code_check').click(function(){
											if($('#code').val()==""){
												alert("권한이 없습니다.");
												return false;
											}
										});
									});
								</script>

							</form>
						</div>
					</div>
				</div>
				<%
					} else {
				%>
				<div class="col-lg-5  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<a href="/logOut.do"><h6>로그아웃</h6></a>
					</div>
				</div>
				<%
					}
				%>
				<div class="col-lg-2 col-md-6 col-sm-6 social-widget">
					<div class="single-footer-widget">
						<h6>Follow Us</h6>
						<p>Let us be social</p>
						<div class="footer-social d-flex align-items-center">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-dribbble"></i></a> <a href="#"><i
								class="fa fa-behance"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>


	</footer>
	<!-- End footer Area -->

	<!-- <script src="/theme/js/vendor/jquery-2.2.4.min.js"></script> -->
	<!-- <script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper./theme/js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script> -->

	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="/theme/js/easing.min.js"></script>
	<script src="/theme/js/hoverIntent.js"></script>
	<script src="/theme/js/superfish.min.js"></script>
	<!-- <script src="/theme/js/jquery.ajaxchimp.min.js"></script> -->
	<script src="/theme/js/jquery.magnific-popup.min.js"></script>
	<script src="/theme/js/owl.carousel.min.js"></script>
	<script src="/theme/js/jquery.nice-select.min.js"></script>
	<script src="/theme/js/mail-script.js"></script>
	<script src="/theme/js/main.js"></script>
</body>

</html>