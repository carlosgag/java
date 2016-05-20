var chartwin = Titanium.UI.currentWindow;

Titanium.include('charts/g.raphael-min.lib');
Titanium.include('charts/g.raphael-min.lib');

var chartTitleInterest = 'Total Interest: $' + chartWin.totalInterest;
var chartTitleRepayments = 'Total Repayments: $' + chartWin.totalRepayments;

var chartHTML =  
'<html><head> '+
'<title>RaphaelJS Chart</title>'+
'<meta name="viewport" content="width=device-width, initialscale=1.0"/>'+ 
'<script src="charts/raphael.js.lib" type="text/javascript" charset="utf-8"></script>'+
'<script src="charts/g.raphael-min.lib" type="text/javascript" charset="utf-8">'+
'</script> <script src="charts/g.pie-min.lib" type="text/javascript" charset="utf-8"></script>'+
'<script type="text/javascript" charset="utf-8"> '+
'window.onload = function() { '+
'var r = Raphael("chartDiv"); '+
'r.g.txtattr.font= "12px Verdana, Tahoma, sans-serif"; '+
'r.g.text(150, 10, "';
chartHTML = chartHTML + chartTitleInterest + '").attr({"fontsize":14}); '+
'r.g.text(150, 30, "' + chartTitleRepayments + '").attr({"font-size": 14});';
chartHTML = chartHTML + ' r.g.piechart(150, 180, 130, [' + 
	Math.round(chartWin.totalInterest) + ',' + 
	Math.round(chartWin.principalRepayments) + 
	']); }; '+
'</script> </head>'+
'<body>'+
'<div id="chartDiv" style="width:320px; height: 320px; margin: 0"></div>'+
'</body></html>';

var webview = Titanium.UI.createWebView({
	width: 320,
	height: 367,
	top: 0,
	html: chartHTML
});
chartWin.add(webview);