/**
 * 
 */
function test()
{
	alert("inside customJS file....");
}

function createCartItem_product_1() {
	
	 var urlContextPath = "";
	 var productQty=$('#quantity').val();
	 var params={ 'productId' : 1,'productQuantity' :productQty}

	 //alert("custom function..."+productQty);
	 var searchJsonData = { 'objectType': 'CartItem','paramsInfo' : JSON.stringify(params)};
	
	 $.ajax({
	 context: {
	 'errorMessage': "ERROR"
	 },
	 error: function(responseData) {
	 alert(this.errorMessage);
	 },
	 dataType: 'json',
	 type: 'GET',
	 url: urlContextPath + '/createEntity',
	 data: searchJsonData,
	 success: function(responseData) {
	
	 alert("AJAX call is Success");
	 }
	 });	
	
	 }
	 
function createCartItem_product_2() {
	
	
	var urlContextPath = "";
	var productQty=$('#quantity').val();
	var params={ 'productId' : 4,'productQuantity' :productQty}

	//alert("custom function..."+productQty);
	var searchJsonData = { 'objectType': 'CartItem','paramsInfo' : JSON.stringify(params)};
    	
	$.ajax({
		context: {
			'errorMessage': "abcd"
		},
		error: function(responseData) {
			//closePopUp();
			//showAlert(this.errorMessage);
		},
		dataType: 'json',
		type: 'GET',
		url: urlContextPath + '/createEntity',
		data: searchJsonData,
		success: function(responseData) {
		
           alert("AJAX call is Success");
		}
	});	
	
}

function createCartItem_product_3() {
		
	var urlContextPath = "";
	var productQty=$('#quantity').val();
	var params={ 'productId' : 2,'productQuantity' :productQty}

	alert("custom function..."+productQty);
	var searchJsonData = { 'objectType': 'CartItem','paramsInfo' : JSON.stringify(params)};
    	
	$.ajax({
		context: {
			'errorMessage': "abcd"
		},
		error: function(responseData) {
			//closePopUp();
			//showAlert(this.errorMessage);
		},
		dataType: 'json',
		type: 'GET',
		url: urlContextPath + '/createEntity',
		data: searchJsonData,
		success: function(responseData) {
		
           alert("AJAX call is Success");
		}
	});	
	
}

function createCartItem_product_4() {
	
	
	var urlContextPath = "";
	var productQty=$('#quantity').val();
	var params={ 'productId' : 3,'productQuantity' :productQty}

	//alert("custom function..."+productQty);
	var searchJsonData = { 'objectType': 'CartItem','paramsInfo' : JSON.stringify(params)};
    	
	$.ajax({
		context: {
			'errorMessage': "abcd"
		},
		error: function(responseData) {
			//closePopUp();
			//showAlert(this.errorMessage);
		},
		dataType: 'json',
		type: 'GET',
		url: urlContextPath + '/createEntity',
		data: searchJsonData,
		success: function(responseData) {
		
           alert("AJAX call is Success");
		}
	});	
	
}

function deleteCartItem_byProductId(productId)
{
	alert("deleteCartItem_byProductId()..."+productId);
	
	var cartItemList=cartItemRetrieve();
	var cartItemList_productId;
	var cartItemId;
	for(var i=0;i<cartItemList.length;i++)
		{
			cartItemList_productId=cartItemList[i].productId;
			if(cartItemList_productId==productId)
			{
				cartItemId=cartItemList[i].cartItemId;
			}
		}
	var paramsMap= {};
	//deleteSelectedCartItem(cartItemId, paramsMap);
	
	var cartItem = {};
	cartItem['cartItemId'] = cartItemId;	
    var cartItemJsonData = {'paramsInfo': JSON.stringify(cartItem), 'objectType' : 'CartItem'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'paramsMap':paramsMap
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/deleteEntity',
        data: cartItemJsonData,
        success: function (responseData)
        {
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            closePopUp();
            if (responseData['success'] == 1)
            {
            }
        }
    });
	
	location.reload();
}