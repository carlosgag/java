//reference the current window
var win2 = Titanium.UI.createWindow({
	width : 320,
	height : 480,
	top : 0,
	left : 0,
	backgroundColor : '#ff0000'
});
win2.setTitle("Settings");

var view2 = Titanium.UI.createView({
	width : 300,
	height : 70,
	left : 10,
	top : 10,
	backgroundColor : '#fff',
	borderRadius : 5
});
view2.add(labelSwitch);
//create the switch object
var switchChartOption = Titanium.UI.createSwitch({
	right : 20,
	top : 20,
	value : false
});
switchChartOption.addEventListener('change',function(e){
	win2.autoShowChart =  switchChartOption.value;
});
view2.add(switchChartOption);
win2.add(view2); 