/*function getCartItemList(responseData)
{
	var cartItemList;
	cartItemList=responseData['cartItemList'];
	alert("callback fuct.."+cartItemList);
	//var cartItemList=cartItemList;
	return cartItemList;
}*/


function cartItemRetrieve()
{
  alert("retrieve function...");
  
  var cartItemList;
  var urlContextPath = ""; 
  var searchInputParamsList =[{"parameterName":"additionalParamsList","userInputValue":"[]"},
                             {"parameterName":"noOfRecordsAlreadyFetched","userInputValue":0},
                             {"parameterName":"noOfRecordsToFetch","userInputValue":"100"}];
 var searchData = { 'objectType': "CartItem", 'paramsInfo': JSON.stringify({'searchInputParamsList':searchInputParamsList})};

$.ajax({
        error: function (responseData)
        {
            //closePopUp();
            alert(this.errorMessage);
        },
        async: false,
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/retrieveEntityList',
        data: searchData,
        success: function (responseData)
        {
            //closePopUp();
            if (responseData['alert'])
            {
                alert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
                cartItemList = responseData['cartItemList'];
                           
            }
            //getCartItemList(cartItemList);           
        }

});
return cartItemList;
}


function ordersRetrieve()
{
  alert("orders function...");
  
  var ordersList;
  var urlContextPath = "";
  
var searchInputParamsList =[{"parameterName":"additionalParamsList","userInputValue":"[]"},
                           {"parameterName":"noOfRecordsAlreadyFetched","userInputValue":0},
                           {"parameterName":"noOfRecordsToFetch","userInputValue":"100"}]; 
  
var searchData = { 'objectType': "Orders", 'paramsInfo': JSON.stringify({'searchInputParamsList':searchInputParamsList}) }
      
$.ajax({
        error: function (responseData)
        {
            alert(this.errorMessage);
        },
        async: false,
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/retrieveEntityList',
        data: searchData,
        success: function (responseData)
        {
            //closePopUp();
            if (responseData['alert'])
            {
                alert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
                ordersList = responseData['ordersList'];           
            }
           
        }
        
});
return ordersList; 	
}

