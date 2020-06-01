<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<!-- Mobile Specific Meta -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Favicon-->
<link rel="shortcut icon" href="/theme/img/fav.png">
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

<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700"
	rel="stylesheet">
<!--
    CSS
    ============================================= -->
<link rel="stylesheet" href="/theme/css/linearicons.css">
<link rel="stylesheet" href="/theme/css/font-awesome.min.css">
<link rel="stylesheet" href="/theme/css/bootstrap.css">
<link rel="stylesheet" href="/theme/css/magnific-popup.css">
<link rel="stylesheet" href="/theme/css/nice-select.css">
<link rel="stylesheet" href="/theme/css/animate.min.css">
<link rel="stylesheet" href="/theme/css/owl.carousel.css">
<link rel="stylesheet" href="/theme/css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://d3js.org/d3.v4.min.js"></script>
<script
	src="https://rawgit.com/jasondavies/d3-cloud/master/build/d3.layout.cloud.js"
	type="text/JavaScript">
	</script>
<script type="text/javascript">
	$(window).on("load", function() {

		//페이지 로딩 완료 후, 뉴스 순위가져오기 함수 실행함 
		getNews();
	});

	//뉴스 순위가져오기
	function getNews() {

		//Ajax 호출
		$.ajax({
			url : "/project/getNews.do",
			type : "post",
			dataType : "JSON",
			contentType : "application/json; charset=UTF-8",
			success : function(json) {

				var news_rank = "";

				for (var i = 0; i < json.length; i++) {
					news_rank += ("<h4>"+json[i].seq + "위 | ");
					/* news_rank += (json[i].collect_time + "수집일 | "); */
					news_rank += (json[i].title + "</h4><br>");

				}

				$('#news_rank').html(news_rank);
				
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
				<div id="logo">
					<a href="" style="color: white; font-size: 20px;"><i>FROM
							WAY</i><br> <i>DOWNTOWN</i></a>
				</div>
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
			<div class="overlay"></div>
			<div class="container">
				<div
					class="row fullscreen d-flex justify-content-center align-items-center">
					<div
						class="banner-content col-lg-9 col-md-12 justify-content-center ">
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
					<h1 class="pb-10" style="color: #777;">오늘의 핫 워드</h1>
					<p class="" style="color: #777;">Who are in extremely love with
						eco friendly system.</p>
				</div>
			</div>
			<div class="row text-white" style="align-items: center;justify-content: center;">
				<div id="news_rank" style="text-align:center;"></div>

			</div>
		</div>
	</section>
	<hr>
	<!-- End feature Area -->

	<!-- Start testimonial Area -->
	<section class="testimonial-area pt-120" id="window2">
		<div class="container">
			<div class="row d-flex justify-content-center">
				<div class="menu-content col-lg-8">
					<div class="title text-center">
						<h1 class="mb-10" style="color: #777;">누적 핫 워드</h1>
						<p>Who are in extremely love with eco friendly system.</p>
					</div>
				</div>
			</div>

		</div>
	</section>
	<!-- End testimonial Area -->

	<!-- Start callto-action Area -->
	<section class="callto-action-area">
		<div class="container">
			<div class="row justify-content-center">
				<div class="callto-action-wrap col-lg-12 relative">
					<div class="content" id="cloud"
						style="display: flex; align-content: center; justify-content: center;">

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
				<div class="col-lg-5  col-md-6 col-sm-6">
					<div class="single-footer-widget">
						<h6>Newsletter</h6>
						<p>Stay update with our latest</p>
						<div class="" id="mc_embed_signup">
							<form target="_blank" novalidate="true"
								action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
								method="get" class="form-inline">
								<input class="form-control" name="EMAIL"
									placeholder="Email Address" onfocus="this.placeholder = ''"
									onblur="this.placeholder = 'Email Address'" required=""
									type="email">
								<button class="click-btn btn btn-default">
									<i class="lnr lnr-arrow-right" aria-hidden="true"></i>
								</button>
								<div style="position: absolute; left: -5000px;">
									<input name="b_36c4fd991d266f23781ded980_aefe40901a"
										tabindex="-1" value="" type="text">
								</div>
								<div class="info"></div>
							</form>
						</div>
					</div>
				</div>
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

	<script src="/theme/js/vendor/jquery-2.2.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper./theme/js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script src="/theme/js/vendor/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBhOdIF3Y9382fqJYt5I_sswSrEw5eihAA"></script>
	<script src="/theme/js/easing.min.js"></script>
	<script src="/theme/js/hoverIntent.js"></script>
	<script src="/theme/js/superfish.min.js"></script>
	<script src="/theme/js/jquery.ajaxchimp.min.js"></script>
	<script src="/theme/js/jquery.magnific-popup.min.js"></script>
	<script src="/theme/js/owl.carousel.min.js"></script>
	<script src="/theme/js/jquery.nice-select.min.js"></script>
	<script src="/theme/js/mail-script.js"></script>
	<script src="/theme/js/main.js"></script>
</body>

</html>