function checkOut() {

alert("Inside checkOut() function...");

var urlContextPath = "";

var searchJsonData = { 'objectType': 'UserCart'};

$.ajax({
		context: {
			'errorMessage': "abcd"
		},
		error: function(responseData) {
			closePopUp();
			showAlert(this.errorMessage);
		},
		dataType: 'json',
		type: 'GET',
		url: urlContextPath + '/UserCartCheckOut',
		data: searchJsonData,
		success: function(responseData) {
		
           alert("AJAX call is Success");
           location.reload();
		}
	});
}