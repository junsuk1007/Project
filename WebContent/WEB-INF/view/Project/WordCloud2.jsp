<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8" />
<style>
text:hover {
	stroke: black;
}
</style>
 <script src="http://d3js.org/d3.v4.min.js"></script>
<script
	src="https://rawgit.com/jasondavies/d3-cloud/master/build/d3.layout.cloud.js"
	type="text/JavaScript">
	</script>  
</head>
<body>
  <div id="cloud" align="center"></div>
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
</body>
</html>
