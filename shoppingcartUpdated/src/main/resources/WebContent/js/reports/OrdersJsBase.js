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
	dataObj.objectType = "Orders";
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
                   
                	var orders = fieldsDataWithoutOverrideWhereClause['orders'];                
		                
		                
		                var userCartId = orders['userCartId'];
		                $('[data-name="UserCartId"]').text(userCartId);
		                
		                
		                var orderNo = orders['orderNo'];
		                $('[data-name="OrderNo"]').text(orderNo);
		                
		                
		                var orderDate = orders['orderDate'];
		                $('[data-name="OrderDate"]').text(orderDate);
		                
		                
		                var orderAmount = orders['orderAmount'];
		                $('[data-name="OrderAmount"]').text(orderAmount);
		                                
                
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


