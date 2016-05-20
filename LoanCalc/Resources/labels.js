var labelSwitch = Titanium.UI.createLabel({
	width : 'auto',
	height : 30,
	top : 20,
	color : '#000000',
	left : 20,
	font : {
		fontSize : 14,
		fontFamily : 'Helvetica',
		fontWeight : 'bold'
	}
});
labelSwitch.setText('Auto Show Chart?');

var labelAmount = Titanium.UI.createLabel({
	id : 'labelAmount',
	width : 'auto',
	height : 30,
	top : 100,
	color : '#000000',
	left : 20,
	font : {
		fontSize : 14,
		fontFamily : 'Helvetica',
		fontWeight : 'bold'
	}
});
labelAmount.setText('Loan amount:   $');

var labelInterestRate = Titanium.UI.createLabel({
	id : 'labelInterestRate',
	width : 'auto',
	height : 30,
	top : 150,
	color : '#000000',
	left : 20,
	font : {
		fontSize : 14,
		fontFamily : 'Helvetica',
		fontWeight : 'bold'
	}
});
labelInterestRate.setText('Interest Rate:   $');

var labelLoanLength = Titanium.UI.createLabel({
	id : 'labelLoanLength',
	width : 100,
	height : 'auto',
	top : 200,
	color : '#000000',
	left : 20,
	font : {
		fontSize : 14,
		fontFamily : 'Helvetica',
		fontWeight : 'bold'
	}
});

//var _logoMarginLeft = (view.width - 253) / 2;

var labelLogo = Titanium.UI.createLabel({
	width : 'auto',
	height : 'auto',
	top : 0,
	color : '#000000',
	left : 20,
	font : {
		fontSize : 14,
		fontFamily : 'Helvetica',
		fontWeight : 'bold'
	}
});
labelLogo.setText('Loan calculator');