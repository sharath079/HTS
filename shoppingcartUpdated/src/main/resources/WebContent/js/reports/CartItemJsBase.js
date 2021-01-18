$(document).ready(function() {
	initializeReportInfo();
	initializePageLevelData();
});

function initializePageLevelData()
{
	
	
	
}
function getContextPath()
{
    return "";
}
function initializeReportInfo()
{
	initializePageOnload();
	var paramsMap = getQueryParams();
	
	initDatePicker();
	//initMonthDatePicker();
	//initYearDatePicker();
	
    getPageDataInfo();
}
function getPageDataInfo()
{
    var urlContextPath = getContextPath();
	var dataObj = getQueryParams();	
	dataObj.noOfRecordsAlreadyFetched = 0;
	dataObj.noOfRecordsToFetch = 1000000;
	dataObj.objectType = "CartItem";
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData) {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',        
        url : urlContextPath + '/getReportInfo' +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: dataObj,
        success: function (responseData) {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
                var fieldsDataWithoutOverrideWhereClause = responseData['fieldsDataWithoutOverrideWhereClause'];                
                   
                	var cartItem = fieldsDataWithoutOverrideWhereClause['cartItem'];                
		                
		                
		                var userCartId = cartItem['userCartId'];
		                $('[data-name="UserCartId"]').text(userCartId);
		                
		                
		                var productId = cartItem['productId'];
		                $('[data-name="ProductId"]').text(productId);
		                
		                
		                var productQuantity = cartItem['productQuantity'];
		                $('[data-name="ProductQuantity"]').text(productQuantity);
		                
		                
		                var productUnitPrice = cartItem['productUnitPrice'];
		                $('[data-name="ProductUnitPrice"]').text(productUnitPrice);
		                
		                
		                var subTotalAmount = cartItem['subTotalAmount'];
		                $('[data-name="SubTotalAmount"]').text(subTotalAmount);
		                
		                
		                var grandTotal = cartItem['grandTotal'];
		                $('[data-name="GrandTotal"]').text(grandTotal);
		                                
                
                var fieldsDataWithOverrideWhereClause = responseData['fieldsDataWithOverrideWhereClause'];                
	                                
			    var layoutCustomDataFieldsObject = responseData['layoutCustomDataFieldsObject'];               
                
                var tablesData = responseData['tablesData'];
                
                var graphsData = responseData['graphsData'];
                
            }
        }
    });
}


function getParametersFromURLAndSearchFields()
{
	var paramsMap = getQueryParams();
	//Override URL parameters values With search field values if same parameter name exists
	var searchDiv = $('[data-name="BalanceSheetReportSearchDiv"]');
	var searchFieldsList = searchDiv.find('[data-is-search-field="1"]');
	for (var i = 0; i < searchFieldsList.length; i++)
	{
		var searchFieldDiv = $(searchFieldsList[i]);
		var parameterName = searchFieldDiv.data('parameter-name');
		var userInputValue = searchFieldDiv.find('[data-is-search-input-field ="1"]').val();
		if(paramsMap.hasOwnProperty(parameterName))
		{
			paramsMap.parameterName = userInputValue;
		}
		else
		{
			paramsMap[parameterName] = userInputValue;
		}
	}
	return paramsMap;	
}


