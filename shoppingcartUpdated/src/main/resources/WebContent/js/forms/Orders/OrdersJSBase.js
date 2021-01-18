/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'UserCartId' : 'FormWBEntity:Orders_userCartId' 
 *	 * 'OrderNo' : 'FormWBEntity:Orders_orderNo' 
 *	 * 'OrderDate' : 'FormWBEntity:Orders_orderDate' 
 *	 * 'OrderAmount' : 'FormWBEntity:Orders_orderAmount' 
 *	
 */
var gInitParamsObjForOrders = {};
var gOrdersRequestUnderProcess = 0;
function selectThisOrdersForEdit(ordersRowElement)
{
    var ordersDataObject  = $(ordersRowElement).data('orders');
    var ordersList = $('[data-name="ordersSearchResults"]').find('[data-name="ordersRow"]');
	ordersList.data("is-selected", 0);	
	$(ordersRowElement).data("is-selected", 1);
	setOrdersInViewPage(ordersDataObject);
}

function setOrdersInViewPage(ordersDataObject, paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var ordersId = ordersDataObject['ordersId'];
	$('#'+prefix+'idValueForOrders').val(ordersId);
		
	//Input
	if(ordersDataObject.hasOwnProperty('userCartId'))
	{
		var userCartId = ordersDataObject['userCartId'];            		
		$('#'+prefix+'Orders_userCartId').val(userCartId);
	}
	
	//Input
	if(ordersDataObject.hasOwnProperty('orderNo'))
	{
		var orderNo = ordersDataObject['orderNo'];            		
		$('#'+prefix+'Orders_orderNo').val(orderNo);
	}
	
	//Calendar
	if(ordersDataObject.hasOwnProperty('orderDate'))
	{
		var orderDate = ordersDataObject['orderDate'];            		
		$('#'+prefix+'Orders_orderDate').val(orderDate);
	}
	
	//Input
	if(ordersDataObject.hasOwnProperty('orderAmount'))
	{
		var orderAmount = ordersDataObject['orderAmount'];            		
		$('#'+prefix+'Orders_orderAmount').val(orderAmount);
	}

	if(ordersDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = ordersDataObject['vwTxnStatus'];
		$('[data-name="ordersActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnOrders(this)" >' +ordersDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="ordersActionButtonDiv"]').append(buttonElement);
		$('[data-name="ordersActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="ordersActionButtonDiv"]').hide();
	}
	$('[data-name="ordersCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="ordersViewFormButtonsDiv"]').css("display", "inline");
	//loadOrdersViewData(ordersDataObject);
	disbaleOrdersUpdateDisallowedFields(paramsMap);
	doAfterOrdersPanelRefreshed();
    
    
}
function disbaleOrdersUpdateDisallowedFields(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
	var prefix = "";
	if(paramsMap.hasOwnProperty("attributeNamePrefix"))
	{
		prefix = paramsMap['attributeNamePrefix'];
	}
	var parentElement =$('body');
	if(paramsMap.hasOwnProperty("parentElement"))
	{
		parentElement = paramsMap['parentElement'];
	}
	
}
function loadOrdersViewData(ordersDataObject, paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = {};
	}
	var prefix = "";
	if(paramsMap.hasOwnProperty("attributeNamePrefix"))
	{
		prefix = paramsMap['attributeNamePrefix'];
	}
	var parentElement =$('body');
	if(paramsMap.hasOwnProperty("parentElement"))
	{
		parentElement = paramsMap['parentElement'];
	}
	var ordersId = ordersDataObject['ordersId'];
	$('#'+prefix+'idValueForOrders').val(ordersId);
		
	if(ordersDataObject.hasOwnProperty('userCartId'))
	{
		var userCartId = ordersDataObject['userCartId'];            		
		parentElement.find('[data-name="'+prefix+'Orders_userCartId"]').text(userCartId);
	}
	
	if(ordersDataObject.hasOwnProperty('orderNo'))
	{
		var orderNo = ordersDataObject['orderNo'];            		
		parentElement.find('[data-name="'+prefix+'Orders_orderNo"]').text(orderNo);
	}
	
	if(ordersDataObject.hasOwnProperty('orderDate'))
	{
		var orderDate = ordersDataObject['orderDate'];            		
		parentElement.find('[data-name="'+prefix+'Orders_orderDate"]').text(orderDate);
	}
	
	if(ordersDataObject.hasOwnProperty('orderAmount'))
	{
		var orderAmount = ordersDataObject['orderAmount'];            		
		parentElement.find('[data-name="'+prefix+'Orders_orderAmount"]').text(orderAmount);
	}

}
function ajaxDemoForOrders()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForOrders').textContent;	
	alert('Entity id : ' + idValue);
	$.ajax({
		url : urlContextPath + '/AjaxServlet' +'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
		data : {
			param1 : 'param1 Val',
			param2 : 'param2 Val',
			requestType : 'ajaxDemo'
		},
		success : function(responseText) {
			alert('Response message : ' + responseText['msg']);
			refreshPanelForOrders();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForOrders(msg)
{
	var executeCustomAPIButtonForOrders = document.getElementById("FormWBEntity:executeCustomAPIButtonForOrders");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:Orders_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForOrders.click();
}
function refreshPanelForOrders()
{
	var demoRefreshButtonForOrders = document.getElementById("FormWBEntity:demoRefreshButtonOrders");
	demoRefreshButtonForOrders.click();
}
function initializePanelOnLoadForCreateOrders(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForOrders = initParamsObj;
	initializeOrdersPage();	
	doAfterOrdersPanelRefreshed();
	initializeOrdersLookupFields(initParamsObj);
	doAfterPanelInitializedForOrdersExt();
	fetchOrdersDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="ordersCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForOrders = 0;
function initializePanelOnLoadForSearchOrders()
{
	initializeOrdersPage();	
	initializeOrdersLookupFields();
	doAfterPanelInitializedForOrdersExt();
	gLoadDashboardResultsForOrders =1;
	//retrieveOrdersList();
}
function initializePanelOnLoadForViewOrders(urlParams)
{
	initializeOrdersPage();	
	doAfterOrdersPanelRefreshed();
	initializeOrdersLookupFields(urlParams);
	doAfterPanelInitializedForOrdersExt();
	retrieveOrders(urlParams);
	initDatePicker();
	$('[data-name="ordersViewFormButtonsDiv"]').css("display", "inline");
}
function initializeOrdersPage()
{
	initializePageOnload();	
	//initializeOrdersTemplateVariables();
}
function initializeOrdersTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="ordersSearchResults"]').find('[data-name="ordersRow"]');
	if(searchResultsRowObj.length > 0 && gOrdersSearchResultsTableRowTemplate.length == 0)
	{
		gOrdersSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveOrders(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var ordersId = paramsMap['ordersId'];
	var orders = {};
	orders['ordersId'] = ordersId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				orders[key] = paramsMap[key];
			}
	}
    var ordersJsonData = {'paramsInfo': JSON.stringify(orders), 'objectType' : 'Orders'};
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
		url : urlContextPath + '/retrieveEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            else
        	{
                if (responseData['alert'])
                {
                    showAlert(responseData['alert']);
                }
                if (responseData['success'] == 1)
                {
                	var ordersDataObject = responseData['ordersDataObject'];
    				setOrdersInViewPage(ordersDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForOrders()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchOrdersDefaultData();
}
function fetchOrdersDefaultData(initParamsObj) 
{
	if(!initParamsObj)
	{
		initParamsObj = {};
	}
	var paramsMap = {};
	for (var key in initParamsObj)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsMap[key] = initParamsObj[key];
			}
	}
	var paramsInfo = {'initParams' : paramsMap};
    var searchJsonData = {'objectType' : 'Orders', 'paramsInfo': JSON.stringify(paramsInfo)};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        	'paramsMap':initParamsObj
        },
        error: function (responseData)
        {
            closePopUp();
            if(this.paramsMap.hasOwnProperty('errorCallbackFunction'))
        	{
                var errorCallbackFunction = this.paramsMap['errorCallbackFunction'];
                errorCallbackFunction(responseData);
                return;
        	}
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/fetchEntityDefaultData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
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
            if (responseData['success'] == 1)
            {
            	var orders = responseData['orders'];
            	document.getElementById('idValueForOrders').value = '';
			    
            	setOrdersData(orders);
            }
        }
    });	
}
function fetchOrdersTestData() 
{
	var orders = getDataForOrders();
    var searchJsonData = {'objectType' : 'Orders', 'paramsInfo' : JSON.stringify(orders)};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/fetchEntityTestData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orders = responseData['orders'];
            	document.getElementById('idValueForOrders').value = '';
			    
            	setOrdersData(orders);
            }
        }
    });	
}
function setOrdersData(ordersDataObject)
{
	var prefix = "";
		
	//Input
	if(ordersDataObject.hasOwnProperty('userCartId'))
	{
		var userCartId = ordersDataObject['userCartId'];            		
		$('#'+prefix+'Orders_userCartId').val(userCartId);
	}
	
	//Input
	if(ordersDataObject.hasOwnProperty('orderNo'))
	{
		var orderNo = ordersDataObject['orderNo'];            		
		$('#'+prefix+'Orders_orderNo').val(orderNo);
	}
	
	//Calendar
	if(ordersDataObject.hasOwnProperty('orderDate'))
	{
		var orderDate = ordersDataObject['orderDate'];            		
		$('#'+prefix+'Orders_orderDate').val(orderDate);
	}
	
	//Input
	if(ordersDataObject.hasOwnProperty('orderAmount'))
	{
		var orderAmount = ordersDataObject['orderAmount'];            		
		$('#'+prefix+'Orders_orderAmount').val(orderAmount);
	}

	$('[data-name="ordersActionButtonDiv"]').hide();
}
function initializeOrdersLookupFields(paramsMap) 
{
	
	var elementsList = $('[data-is-lookup-select="1"]');
	for(var i =0; i< elementsList.length ; i++)
	{
		var attributeSelectElement = $(elementsList[i]);
		var attributeName = attributeSelectElement.data("attribute-name");
		if(1 > 2)
		{
		}
		
	}
    
    var searchDiv = $('[data-name="ordersSearchResultsDiv"]');
	
    
}

function doAfterOrdersPanelRefreshed()
{
    //doAfterPanelRefreshedForOrdersExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForOrders(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForOrdersExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForOrders(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForOrdersExt(fieldName)
}
function getEntityIdForOrders()
{
	var idValue = document.getElementById('FormWBEntity:idValueForOrders').textContent;	
	if(idValue && idValue.length>0)
	{
		var idNum = Number(idValue);	
		return idNum;
	}
	else
	{
		return -1;	
	}
}
function openPrintPageForOrders()
{
	var entityId = getEntityIdForOrders();
	if(entityId>0)
	{
	    window.open("/reports/generated/Orders.jsp?ordersId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForOrders(paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var orders = {};
		
	//Input
	if($("#"+prefix+"Orders_userCartId").length == 1)
	{
		var userCartId = $('#'+prefix+'Orders_userCartId').val();
		orders['userCartId'] = userCartId;
	}
	
	//Input
	if($("#"+prefix+"Orders_orderNo").length == 1)
	{
		var orderNo = $('#'+prefix+'Orders_orderNo').val();
		orders['orderNo'] = orderNo;
	}
	
	//Calendar
	if($("#"+prefix+"Orders_orderDate").length == 1)
	{
		var orderDate = $('#'+prefix+'Orders_orderDate').val();
		orders['orderDate'] = orderDate;
	}
	
	//Input
	if($("#"+prefix+"Orders_orderAmount").length == 1)
	{
		var orderAmount = $('#'+prefix+'Orders_orderAmount').val();
		orders['orderAmount'] = orderAmount;
	}

	
	return orders;
}
function createOrders(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var orders = getDataForOrders(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				orders[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForOrders)
	{
		paramsInfo[key] = gInitParamsObjForOrders[key];
	}
	var validationObject = doOrdersValidation(orders, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	orders['additionalParamsInfo'] = paramsInfo;
    var ordersJsonData = {'paramsInfo': JSON.stringify(orders), 'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
	if(gOrdersRequestUnderProcess == 1)
	{
        showAlert("There is already a pending request being processed by server.");
        return;
	}
	showSpinnerPopUp();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        	'paramsMap':paramsMap
        },
        error: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gOrdersRequestUnderProcess = 0;
            }, 1000);
            if(this.paramsMap.hasOwnProperty('errorCallbackFunction'))
        	{
                var errorCallbackFunction = this.paramsMap['errorCallbackFunction'];
                errorCallbackFunction(responseData);
                return;
        	}
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/createEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gOrdersRequestUnderProcess = 0;
            }, 1000);
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var ordersId = responseData['ordersId'];
            	var popupElement = $('[data-name="OrdersPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var ordersDataObject = responseData['ordersDataObject'];
    				setOrdersInViewPage(ordersDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewOrders.html?ordersId="+ordersId; 
        		}
				
            }
        }
    });
}
function resetTableForOrders()
{
	var ordersListElement = $("[data-name='ordersList']");
	var previousDataRowList  = ordersListElement.find('[data-name="ordersRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForOrders(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	$('#'+prefix+'idValueForOrders').val('');
		
	//Input
	$('#'+prefix+'Orders_userCartId').val('');
	
	//Input
	$('#'+prefix+'Orders_orderNo').val('');
	
	//Calendar
	$('#'+prefix+'Orders_orderDate').val('');
	
	//Input
	$('#'+prefix+'Orders_orderAmount').val('');

	$('[data-name="ordersCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="ordersViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="ordersActionButtonDiv"]').hide();
	enableOrdersUpdateDisallowedFields(paramsMap);
    
}
function enableOrdersUpdateDisallowedFields(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = {};
	}
	var prefix = "";
	if(paramsMap.hasOwnProperty("prefix"))
	{
		prefix = paramsMap['prefix'];
	}
	
}
function updateOrders(paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var orders = getDataForOrders(paramsMap)
	if($("#"+prefix+"idValueForOrders").length == 1)
	{
		var ordersId = $("#"+prefix+"idValueForOrders").val();
		orders['ordersId'] = ordersId;
	}
	var validationObject = doOrdersValidation(orders, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var ordersJsonData = {'paramsInfo': JSON.stringify(orders), 'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
	if(gOrdersRequestUnderProcess == 1)
	{
        showAlert("There is already a pending request being processed by server.");
        return;
	}
	showSpinnerPopUp();
    $.ajax({
        context: {
            'errorMessage': "abcd",
        	'paramsMap':paramsMap
        },
        error: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gOrdersRequestUnderProcess = 0;
                    }, 1000);
            if(this.paramsMap.hasOwnProperty('errorCallbackFunction'))
        	{
                var errorCallbackFunction = this.paramsMap['errorCallbackFunction'];
                errorCallbackFunction(responseData);
                return;
        	}
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/updateEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gOrdersRequestUnderProcess = 0;
                    }, 1000);
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
            if (responseData['success'] == 1)
            {
				
            }
        }
    });
}
function deleteOrders(paramsMap)
{		
	var ordersId = document.getElementById('idValueForOrders').value;
	deleteSelectedOrders(ordersId, paramsMap);
}
function deleteSelectedOrders(ordersId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var orders = {};
	orders['ordersId'] = ordersId;	
    var ordersJsonData = {'paramsInfo': JSON.stringify(orders), 'objectType' : 'Orders'};
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
		url : urlContextPath + '/deleteEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
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
}
function loadSearchResultsForOrders()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/AjaxServlet'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var searchResultObjectsList = responseData['searchResultObjectsList'];
            	var ordersSearchResultsElement = $("[data-name='ordersSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var ordersDataObject = searchResultObjectsList[i];
					            		var userCartId = ordersDataObject['userCartId'];            		
            		var orderNo = ordersDataObject['orderNo'];            		
            		var orderDate = ordersDataObject['orderDate'];            		
            		var orderAmount = ordersDataObject['orderAmount'];            		

            		var resultRowTemnplate = $(gOrdersSearchResultsTableRowTemplate);
					            		var userCartId = ordersDataObject['userCartId'];            		
            	    resultRowTemnplate.find("[data-name='userCartId']").text(userCartId);
            		var orderNo = ordersDataObject['orderNo'];            		
            	    resultRowTemnplate.find("[data-name='orderNo']").text(orderNo);
            		var orderDate = ordersDataObject['orderDate'];            		
            	    resultRowTemnplate.find("[data-name='orderDate']").text(orderDate);
            		var orderAmount = ordersDataObject['orderAmount'];            		
            	    resultRowTemnplate.find("[data-name='orderAmount']").text(orderAmount);

            	    resultRowTemnplate.data('orders', ordersDataObject);
            	    ordersSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gOrdersSearchResultsTableRowTemplate = ""; 
function openViewPageForOrdersForEdit(ordersLinkElement)
{
	var ordersRowElement = $(ordersLinkElement).parents('[data-name="ordersRow"]');
    var ordersDataObject  = ordersRowElement.data('orders');
	var ordersId = ordersDataObject['ordersId'];
	var ordersPopupElement = $('[data-name="OrdersPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setOrdersInViewPage(ordersDataObject);
	    $("#Orders-tab").trigger("click");
	}
	else if(ordersPopupElement.length > 0)
	{
	    setOrdersInViewPage(ordersDataObject);
		$('[data-name="OrdersPopup"]').find('[data-name="ordersCreateFormButtonsDiv"]').hide();
		$('[data-name="OrdersPopup"]').find('[data-name="ordersViewFormButtonsDiv"]').show();
	    showPopup('OrdersPopup', ordersLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewOrders.html?ordersId="+ordersId;
		window.open(viewLink, "_blank"); 	
	}
}
function openOrdersCreatePageForNew(linkElement)
{
	var ordersPopupElement = $('[data-name="OrdersPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForOrders();
	    $("#Orders-tab").trigger("click");
    }
	else if(ordersPopupElement.length > 0)
	{
		resetFormForOrders();
	    showPopup('OrdersPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateOrders.html";
    }
}
function showOrdersPopupForEdit(ordersLinkElement)
{
	var ordersRowElement = $(ordersLinkElement).parents('[data-name="ordersRow"]');
    var ordersDataObject  = ordersRowElement.data('orders');
    setOrdersInViewPage(ordersDataObject);
    showPopup('OrdersPopup', ordersLinkElement, 1);
    $("#Orders-tab").trigger("click");
}
function showOrdersPopupForNew(buttonElement)
{
	resetFormForOrders();
    showPopup('OrdersPopup', buttonElement, 1);
    $("#Orders-tab").trigger("click");
}
function deleteThisOrders(ordersLinkElement, paramsMap)
{
	var ordersRowElement = $(ordersLinkElement).parents('[data-name="ordersRow"]');
    var ordersDataObject  = ordersRowElement.data('orders');
	var ordersId = ordersDataObject['ordersId'];
	deleteSelectedOrders(ordersId, paramsMap);
}
function displayOrdersList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + ordersSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var ordersSearchResults = searchResultsDiv.find('[data-name="ordersSearchResults"]');
	//ordersSearchResults.find('[data-name="ordersRow"]').remove();
	var previousDataRowList  = ordersSearchResults.find('[data-name="ordersRow"]');
	ordersSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = ordersSearchResults.find('[data-name="ordersRow"]');
	var searchResultsTableRowTemplate = searchResultsTableRowTemplateObj[0].outerHTML;
    var currentPageIndex = 1;
	var noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
    if(searchResultsDiv.length == 1)
    {
		currentPageIndex = searchResultsDiv.data("selected-page-index");
		noOfRecordsToFetch = searchResultsDiv.data("no-of-records-to-fetch");
    }
	var currentPageStartingRecordNo = ((currentPageIndex - 1) * noOfRecordsToFetch) + 1;
    for (var i = 0; i < searchResultObjectsList.length; i++)
    {
		var resultRowTemplate = $(searchResultsTableRowTemplate);
        var ordersDataObject = searchResultObjectsList[i];
        loadOrdersViewData(ordersDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = ordersDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='ordersVwTxnStatus']").text(vwTxnStatus);
		if(ordersDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="ordersActionButton"]').text(ordersDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="ordersActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="ordersActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="ordersRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="ordersRejectButton"]').show();
		}
	    resultRowTemplate.data('orders', ordersDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    ordersSearchResults.append(resultRowTemplate);
	    processResultRowForOrdersExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForOrders == 1)
	{
    	getDashboardResultsForOrders();
	}
}
var ordersSearchResultsDivName = "ordersSearchResultsDiv";
var gOrdersSearchInputParamsList = [];
function retrieveOrdersList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + ordersSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var noOfRecordsAlreadyFetched = 0;
    var noOfRecordsToFetch = 0;
    if(searchResultsDiv.find('[data-name="rowsPerPage"]').length>0)
	{
        noOfRecordsToFetch = searchResultsDiv.find('[data-name="rowsPerPage"]').val();
	}
    if(customParamsMap.hasOwnProperty('noOfRecordsToFetch'))
	{
    	noOfRecordsToFetch = customParamsMap['noOfRecordsToFetch'];
	}
    searchResultsDiv.data("is-searched", 0);
    fetchOrdersSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchOrdersSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getOrdersSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = ordersSearchResultsDivName; 
	}
	if(customParamsMap && customParamsMap.hasOwnProperty("overrideWhereClause"))
	{
		searchInputParamsList['overrideWhereClause'] = customParamsMap['overrideWhereClause']; 
		searchInputParamsList['whereClause'] = customParamsMap['whereClause']; 
	}
    var contextObj = {
		'nextCurrentPageIndex': nextCurrentPageIndex,
        'noOfRecordsToFetch': noOfRecordsToFetch,
        'refreshIndex': refreshIndex,
        'refreshStartIndex': refreshStartIndex,
        'replacePaginationFunctions':1,
        'searchResultsDivName':searchResultsDivName,        
        'errorMessage': "abcd"
        };
    if(customParamsMap && customParamsMap.hasOwnProperty('successCallbackFunction'))
	{
        contextObj['successCallbackFunction'] = customParamsMap['successCallbackFunction'];
	}
    if(customParamsMap && customParamsMap.hasOwnProperty('suppressAlert'))
	{
        contextObj['suppressAlert'] = customParamsMap['suppressAlert'];
	}
    for (var i = 0; i < searchInputParamsList.length; i++)
    {
        var searchInputParamsInfoObj = searchInputParamsList[i];
        var parameterName = searchInputParamsInfoObj.parameterName;
        var userInputValue = searchInputParamsInfoObj.userInputValue;
        contextObj[parameterName] = userInputValue;
    }
    searchInputParamsList.push({'parameterName': 'noOfRecordsAlreadyFetched', 'userInputValue': noOfRecordsAlreadyFetched});
    searchInputParamsList.push({'parameterName': 'noOfRecordsToFetch', 'userInputValue': noOfRecordsToFetch})
    var searchData = {
        'paramsInfo': JSON.stringify({'searchInputParamsList':searchInputParamsList}),
        'objectType': "Orders"
    };    
    $.ajax({
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/retrieveEntityList'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchData,
        context: contextObj,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
                var loadAllTablesData =this.loadAllTablesData;
            	this.showPreviousRecords = "showPreviousOrdersRecords";
            	this.showCurrentPageRecords = "showCurrentPageOrdersRecords";
            	this.showNextRecords = "showNextOrdersRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchOrdersResults";
                var ordersList = responseData['ordersList'];
                var currContextObj = this; 
                var successCallbackFunction = displayOrdersList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'ordersList', 'matchingSearchResultsCount', this.searchResultsDivName, 'ordersSearchResults', 'ordersRow', setOrdersSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getOrdersSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + ordersSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var isSearched = searchResultsDiv.data("is-searched");
    var searchInputParams = [];
    if (isSearched == 0)
    {
        var searchDiv = searchResultsDiv;
        var parameterNameAndValuesList = [];
        var additionalParamsList = customParamsMap['additionalParamsList'];
        if(!additionalParamsList)
    	{
        	additionalParamsList = [];
    	}
				
		//Input
		if(searchDiv.find('[data-name="ordersDB_userCartId"]').length == 1)
		{
		    var userCartId = searchDiv.find('[data-name="ordersDB_userCartId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'userCartId', 'userInputValue':userCartId});
		}
		
		//Input
		if(searchDiv.find('[data-name="ordersDB_orderNo"]').length == 1)
		{
		    var orderNo = searchDiv.find('[data-name="ordersDB_orderNo"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'orderNo', 'userInputValue':orderNo});
		}
		
		//Calendar
		if(searchDiv.find('[data-name="ordersDB_orderDate"]').length == 1)
		{
		    var orderDate = searchDiv.find('[data-name="ordersDB_orderDate"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'orderDate', 'userInputValue':orderDate});
		}
		
		//Input
		if(searchDiv.find('[data-name="ordersDB_orderAmount"]').length == 1)
		{
		    var orderAmount = searchDiv.find('[data-name="ordersDB_orderAmount"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'orderAmount', 'userInputValue':orderAmount});
		}

	    
	    for(var key in customParamsMap)
    	{
			if(key != "errorCallbackFunction"
				&& key != "successCallbackFunction")
				{
					parameterNameAndValuesList.push({ 'parameterName':key, 'userInputValue':customParamsMap[key]});
				}
    	}
	    parameterNameAndValuesList.push({'parameterName':'additionalParamsList', 'userInputValue':JSON.stringify(additionalParamsList)});
        gOrdersSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gOrdersSearchInputParamsList;
    }
    return searchInputParams;
}
function setOrdersSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + ordersSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gOrdersSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gOrdersSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageOrdersRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = ordersSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchOrdersSearchResultsList);
}
function showPreviousOrdersRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = ordersSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchOrdersSearchResultsList);
}
function showNextOrdersRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = ordersSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchOrdersSearchResultsList);
}
function showPrevOrNextSearchOrdersResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousOrdersRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextOrdersRecords(e);
    }
}


function lookupRowSelected(fieldName)
{
	if(fieldName == 'customerAccountInformationCustomerName')
	{
		customerAccountInformationCustomerNameSelected();
	}
	//else if
}
function lookupRowSelectedForOrders(attributeName, attributeId)
{
	var orders = getDataForOrders();
	orders['attributeName'] = attributeName;
	orders['attributeId'] = attributeId;
    var ordersJsonData = {'paramsInfo': JSON.stringify(orders), 'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/entityLookupRowSelected'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orders = responseData['orders'];
            	setOrdersData(orders);
            }
        }
    });	
}
function selectOptionChangedForOrders(attributeName)
{
	var orders = getDataForOrders();
	orders['attributeName'] = attributeName;
    var ordersJsonData = {'paramsInfo': JSON.stringify(orders), 'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/selectOptionChanged'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orders = responseData['orders'];
            	setOrdersData(orders);
            	doAfterOrdersPanelRefreshed();
            }
        }
    });	
}
function updateLookupDisplayFieldsForTestProductInfo(lookupLinkElement, selectedLookupObject)
{
}
function lookupRowSelected(fieldName)
{
	if(fieldName == 'customerAccountInformationCustomerName')
	{
		customerAccountInformationCustomerNameSelected();
	}
	//else if
}
function retrieveDependentOrdersList(paramsMap)
{
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
	var Id = $('#'+prefix+'idValueFor').val();
	if(paramsMap.hasOwnProperty('Id'))
	{
		Id = paramsMap['Id'];
	}
	var paramsInfo = {'Id':Id};
    var searchJsonData = {'objectType' : 'Orders', 'paramsInfo': JSON.stringify(paramsInfo)};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'paramsMap': paramsMap,
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
    	url : urlContextPath + '/retrieveDependentEntityList'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: searchJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if(this.paramsMap.hasOwnProperty('successCallbackFunction'))
        	{
                var successCallbackFunction = this.paramsMap['successCallbackFunction'];
                successCallbackFunction(responseData);
                return;
        	}
            if (responseData['success'] == 1)
            {
            	var ordersList = responseData['ordersList'];
            	var ordersListElement = $("[data-name='ordersList']");
            	var previousDataRowList  = ordersListElement.find('[data-name="ordersRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = ordersListElement.find('[data-name="ordersRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//ordersListElement.empty();
            	for(var i=0; i<ordersList.length; i++)
        		{
            		var ordersDataObject = ordersList[i];
            		//var resultRowTemplate = $(gOrdersSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var userCartId = ordersDataObject['userCartId'];            		
				    resultRowTemplate.find("[data-name='userCartId']").text(userCartId);
					var orderNo = ordersDataObject['orderNo'];            		
				    resultRowTemplate.find("[data-name='orderNo']").text(orderNo);
					var orderDate = ordersDataObject['orderDate'];            		
				    resultRowTemplate.find("[data-name='orderDate']").text(orderDate);
					var orderAmount = ordersDataObject['orderAmount'];            		
				    resultRowTemplate.find("[data-name='orderAmount']").text(orderAmount);

					
					
            	    resultRowTemplate.data('orders', ordersDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    ordersListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForOrders(customEventName)
{
	var ordersId = document.getElementById('idValueForOrders').value;
	var orders = {'ordersId':ordersId, 'customEventName':customEventName};
    var ordersJsonData = {'paramsInfo':JSON.stringify(orders), 'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForOrdersExt(customEventName);
	showSpinnerPopUp("Your request is being processed.");
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'customEventName': customEventName
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/executeCustomAPI'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orders = responseData['orders'];
        		setOrdersInViewPage(orders);
            }
    		doAfterExecuteCustomAPIForOrdersExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnOrders(e, paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var isSearchResult = 0;
    if(paramsMap.hasOwnProperty("isSearchResult"))
	{
    	isSearchResult = paramsMap['isSearchResult'];
	}
	var ordersId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var ordersDataObject  = rowObj.data('orders');
    	ordersId = ordersDataObject['ordersId'];
	}
    else
	{
    	ordersId = document.getElementById('idValueForOrders').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var ordersSearchParams = {'ordersId':ordersId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	ordersSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var ordersJsonData = {'paramsInfo':JSON.stringify(ordersSearchParams),  'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd",
            'isSearchResult':isSearchResult,
            'rowObj':rowObj
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/executeEntityAuthorisationAction'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orders = responseData['orders'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="ordersRowActionButtonDiv"]').hide();
            		if(orders.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = orders['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(orders['vwTxnStatus']);
            			selectedRowObj.find('[data-name="ordersActionButton"]').text(orders["nextAction"]);
            			selectedRowObj.find('[data-name="ordersActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setOrdersInViewPage(orders);
    			}
            }
        }
    });	
}
function downloadOrdersData()
{		
	var orders = {};
    var ordersJsonData = {'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/downloadEntityData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var fileId = responseData['fileId'];
    			window.open("/download?fileId=" + fileId+"&objectType=Orders");
            }
        }
    });
}
function uploadOrdersData(fileInfo)
{
	var fileId = fileInfo['fileId'];
	var areSourceDestinationSameVal = 0;
    var areSourceDestinationSame = $('[data-name="fileUploadPopup"]').find('[data-name="areSourceDestinationSame"]').is(":checked");
	if(areSourceDestinationSame == true)
	{
		areSourceDestinationSameVal = 1;
	}
    var inputFilesZip = $('[data-name="fileUploadPopup"]').find('[data-name="upoadDataInputFilesZip"]').data("inputFilesZip");
    if(!inputFilesZip)
	{
    	inputFilesZip = "";
	}
	var orders = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var ordersJsonData = {'paramsInfo':JSON.stringify(orders),  'objectType' : 'Orders'};
	var urlContextPath = "";//getContextPath();
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/uploadEntityData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var fileId = responseData['fileId'];
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=Orders");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForOrders()
{
    var ordersJsonData = {'objectType' : 'Orders'};
	var urlContextPath = "";
	return;
    $.ajax({
        context: {
            'errorMessage': "abcd"
        },
        error: function (responseData)
        {
            closePopUp();
            showAlert(this.errorMessage);
        },
        dataType: 'json',
        type: 'GET',
		url : urlContextPath + '/getDashboardGraphsData'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: ordersJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var monthlyTransactionList = responseData['monthlyTransactionList'];
            	//alert(JSON.stringify(monthlyTransactionList));
            	var transactionStatusList = responseData['transactionStatusList'];
            	//alert(JSON.stringify(transactionStatusList));
            }
        }
    });	
}



function doOrdersLengthValidation(ordersDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(ordersDataObject['userCartId'], 20))
	{
		alertMessage += "\n UserCartId length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(ordersDataObject['orderNo'], 15))
	{
		alertMessage += "\n OrderNo length is more than allowed(15)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(ordersDataObject['orderDate'], 10))
	{
		alertMessage += "\n OderDate length is more than allowed(10)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(ordersDataObject['orderAmount'], 50))
	{
		alertMessage += "\n OrderAmount length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doOrdersManadatoryValidation(ordersDataObject, paramsMap)
{
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var prefix = "";
    if(paramsMap.hasOwnProperty("prefix"))
	{
    	prefix = paramsMap['prefix'];
	}
    var considerExistingFieldsOnly = false;
    if(paramsMap.hasOwnProperty("considerExistingFieldsOnly"))
	{
    	considerExistingFieldsOnly = paramsMap['considerExistingFieldsOnly'];
	}
    var alertMessage = "";
    var validationPassed = true;
	
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doOrdersValidation(ordersDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doOrdersLengthValidation(ordersDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doOrdersManadatoryValidation(ordersDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
