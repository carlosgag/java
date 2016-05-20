Titanium.include('labels.js');
Titanium.include('window2.js');
// this sets the background color of the master UIView (when there are no windows/tab groups on it)
Titanium.UI.setBackgroundColor('#000');

var numberMonths = 36;
// loan length
var interestRate = 6.0;
// interest rate

var tabGroup = Titanium.UI.createTabGroup();

// create base UI tab and root window
var win1 = Titanium.UI.createWindow({
	width : 320,
	height : 480,
	top : 0,
	left : 0,
	backgroundColor : '#ff0000'
});
win1.setTitle("Loan Calculator");

var view1 = Titanium.UI.createView({
	width : 300,
	height : win1.height - 140,
	left : 10,
	top : 10,
	backgroundColor : '#fff',
	borderRadius : 5
});

view1.add(labelAmount);
view1.add(labelInterestRate);
view1.add(labelLoanLength);
labelLoanLength.setText('Loan length (' + numberMonths + ' months):');
view1.add(labelLogo);

var switchChartOption = Titanium.UI.createSwitch({
	right : 20,
	top : 20,
	value : false
});
view1.add(switchChartOption);

var lengthSlider = Titanium.UI.createSlider({
	width : 140,
	top : 200,
	right : 20,
	min : 12,
	max : 60,
	value : numberMonths//,
	//thumbImage:,
	//selectedThumbImage:,
	//highlightedThumbImage
});
lengthSlider.addEventListener('change', function(e) {
	Titanium.API.info(lengthSlider.value);
	numberMonths = Math.round(lengthSlider.value);
	labelLoanLength.text = 'Loan length (' + Math.round(numberMonths) + ' months):';
});
view1.add(lengthSlider);

var tfAmount = Titanium.UI.createTextField({
	id : 'tfAmount',
	width : 140,
	height : 30,
	top : 100,
	color : '#000000',
	right : 20,
	borderStyle : Titanium.UI.INPUT_BORDERSTYLE_ROUNDED,
	returnKeyType : Titanium.UI.RETURNKEY_DONE,
	hintText : '1000.00',
	keyboardToolbar : [flexSpace, buttonDone],
	keyboardType : Titanium.UI.KEYBOARD_PHONE_PAD
});
view1.add(tfAmount);

var tfInterestedRate = Titanium.UI.createTextField({
	id : 'tfInterestedRate',
	width : 140,
	height : 30,
	top : 150,
	color : '#000000',
	right : 20,
	borderStyle : Titanium.UI.INPUT_BORDERSTYLE_ROUNDED,
	returnKeyType : Titanium.UI.RETURNKEY_DONE,
	value : interestRate,
	keyboardToolbar : [flexSpace, buttonDone],
	keyboardType : Titanium.UI.KEYBOARD_PHONE_PAD
});
tfInterestedRate.addEventListener('focus', function(e) {
	if (Titanium.Platform.osname == 'iphone') {
		tfInterestedRate.top = 100;
		tfAmount.visible = false;
		labelInterestRate.top = 100;
		labelAmount.visible = false;
	}
});
view1.add(tfInterestedRate);

//flexible space for button bars
var flexSpace = Titanium.UI.createButton({
	systemButton : Titanium.UI.iPhone.SystemButton.FLEXIBLE_SPACE
});

var buttonDone = Titanium.UI.createButton({
	systemButton : Titanium.UI.iPhone.SystemButton.DONE
});
buttonDone.addEventListener('click', function(e) {
	tfAmount.blur();
	tfInterestedRate.blur();
	labelInterestRate.top = 150;
	interestRate = tfInterestedRate.value;
	tfAmount.visible = true;
	labelAmount.visible = true;
});

win1.add(view1);
//win1.open();

function calculateAndDisplayValue(e) {
	Titanium.API.info('Button id = ' + e.source.id);
	if (e.source.id == 1) {
		var totalInterest = (tfAmount.value * (interestRate / 100) * numberMonths) / 12;
		Titanium.API.info(totalInterest);
	} else {
		var totalRepayments = Math.round() + totalInterest;
		Titanium.API.info(totalRepayments);
	}
}

var buttonCalculateInterest = Titanium.UI.createButton({
	//image:,
	id : 1,
	top : 255,
	width : 252,
	height : 32,
	left : 23
});
buttonCalculateInterest.setTitle('Calculate total interest paid');
buttonCalculateInterest.addEventListener('click', calculateAndDisplayValue);
view1.add(buttonCalculateInterest);

var buttonCalculateRepayment = Titanium.UI.createButton({
	id : 2,
	top : 300,
	width : 252,
	height : 32,
	left : 23
});
buttonCalculateRepayment.setTitle('Calculate total loan repayments');
buttonCalculateRepayment.addEventListener('click', calculateAndDisplayValue);
view1.add(buttonCalculateRepayment);

var tab1 = Titanium.UI.createTab({
	id : 'tab1'
});
tab1.setTitle('Calculate');
tab1.setWindow(win1);


function openChartWindow(totalInterest, total) {
	//Interest (I) = Principal (P) times Rate Per Period (r)
	//times Number of Periods (n) / 12
	var totalInterest = (tfAmount.value * (interestRate / 100) * numberMonths) / 12;
	var totalRepayments = Math.round(tfAmount.value) + totalInterest;
	var chartWindow = Titanium.UI.createWindow({
		barColor : '#000',
		numberMonths : numberMonths,
		interestRate : interestRate,
		totalInterest : totalInterest,
		totalRepayments : totalRepayments,
		principalRepayments : (totalRepayments - totalInterest)
	});
	chartWindow.setUrl('chartwin.js');
	chartWindow.setTitle('Loan Pie Chart');
	tab1.open(chartWindow);
}


var win2 = Titanium.UI.createWindow({
	id : 'win2',
	width : 320,
	height : 480,
	top : 0,
	left : 0,
	backgroundColor : '#00ff00'
});
//win2.setUrl('window2.js');
win2.setTitle('Settings');
win2.autoShowChart = false;

var tab2 = Titanium.UI.createTab({
	id : 'tab2'
});
tab2.setTitle('Settings');
tab2.setWindow(win2);

tabGroup.addTab(tab1);
tabGroup.addTab(tab2);

tabGroup.open();
