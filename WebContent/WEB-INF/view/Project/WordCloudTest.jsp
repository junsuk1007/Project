<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<meta charset="utf-8" />
<script src="http://d3js.org/d3.v3.min.js"></script>
<script
	src="https://rawgit.com/jasondavies/d3-cloud/master/build/d3.layout.cloud.js"
	type="text/JavaScript">
	</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
.legend {
	border: 1px solid #555555;
	border-radius: 5px 5px 5px 5px;
	font-size: 0.8em;
	margin: 10px;
	padding: 8px 8px 8px 8px;
}

.bld {
	font-weight: bold;
}
</style>

</head>
<body>



	<div id="cloud" align="center"></div>
	<div style="text-align: -webkit-center;">
		<div class="legend" align="center" style="width: 50%;">
			빈도수가 높은 단어는 크지만 흐립니다. 빈도수가 낮은 단어는 작지만 진합니다.<br> Commonly used
			words are larger and slightly faded in color. Less common words are
			smaller and darker.
		</div>
	</div>
</body>
<script>
		var weight = 3,   // change me
		width = 1200,
		height = 600;
		
		$.get({
			url : "/project/getTop50.do"
		})
		.done(function(json) {
			console.log(json);
			var fill = d3.scale.category20();
			
			const group = [];
			json.forEach(function(d) {
				const data = new Object();
				data.text = d.title;
				data.size = d.repeat;
				group.push(data);
			})
			
			d3.layout.cloud().size([width, height]).words(group)
		.rotate(function(d){return 0;})
		.font("Impact")
		.padding(1)
		.fontSize(function(d) { return d.size; })
		.text(function(d) { 
			console.log("바이바이");
			return d.text; 
		})
		.on("end", draw)
		.start();
		
		
		function draw(words) {
			 d3.select("#cloud").append("svg")
			.attr("width", width)
            .attr("height", height)
			.attr("class", "wordcloud")
			.append("g")
			.attr("transform", "translate(" + width/2 + "," + height/2 + ")")
			.selectAll("text")
			.data(group)
			.enter().append("text")
            .style("font-size", function(d) { 
            	console.log("하이하이");
            	return d.size*weight + "px"; })
            .style("font-family", "Impact")
            .style("fill", function(d, i) { return fill(i); })
            .attr("text-anchor", "middle")
            .attr("transform", function(d) {
              return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
            })
          .text(function(d) { return d.text; });
		}
		})
		
		
		
		
	
	</script>

</html>