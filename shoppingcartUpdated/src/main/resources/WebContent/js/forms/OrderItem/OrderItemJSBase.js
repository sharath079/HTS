/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'OrderId' : 'FormWBEntity:OrderItem_orderId' 
 *	 * 'ProductId' : 'FormWBEntity:OrderItem_productId' 
 *	 * 'ProductQuantity' : 'FormWBEntity:OrderItem_productQuantity' 
 *	 * 'ProductUnitPrice' : 'FormWBEntity:OrderItem_productUnitPrice' 
 *	 * 'SubTotalAmt' : 'FormWBEntity:OrderItem_subTotalAmt' 
 *	
 */
var gInitParamsObjForOrderItem = {};
var gOrderItemRequestUnderProcess = 0;
function selectThisOrderItemForEdit(orderItemRowElement)
{
    var orderItemDataObject  = $(orderItemRowElement).data('orderItem');
    var orderItemList = $('[data-name="orderItemSearchResults"]').find('[data-name="orderItemRow"]');
	orderItemList.data("is-selected", 0);	
	$(orderItemRowElement).data("is-selected", 1);
	setOrderItemInViewPage(orderItemDataObject);
}

function setOrderItemInViewPage(orderItemDataObject, paramsMap)
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
	var orderItemId = orderItemDataObject['orderItemId'];
	$('#'+prefix+'idValueForOrderItem').val(orderItemId);
		
	//Input
	if(orderItemDataObject.hasOwnProperty('orderId'))
	{
		var orderId = orderItemDataObject['orderId'];            		
		$('#'+prefix+'OrderItem_orderId').val(orderId);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('productId'))
	{
		var productId = orderItemDataObject['productId'];            		
		$('#'+prefix+'OrderItem_productId').val(productId);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('productQuantity'))
	{
		var productQuantity = orderItemDataObject['productQuantity'];            		
		$('#'+prefix+'OrderItem_productQuantity').val(productQuantity);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('productUnitPrice'))
	{
		var productUnitPrice = orderItemDataObject['productUnitPrice'];            		
		$('#'+prefix+'OrderItem_productUnitPrice').val(productUnitPrice);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('subTotalAmt'))
	{
		var subTotalAmt = orderItemDataObject['subTotalAmt'];            		
		$('#'+prefix+'OrderItem_subTotalAmt').val(subTotalAmt);
	}

	if(orderItemDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = orderItemDataObject['vwTxnStatus'];
		$('[data-name="orderItemActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnOrderItem(this)" >' +orderItemDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="orderItemActionButtonDiv"]').append(buttonElement);
		$('[data-name="orderItemActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="orderItemActionButtonDiv"]').hide();
	}
	$('[data-name="orderItemCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="orderItemViewFormButtonsDiv"]').css("display", "inline");
	//loadOrderItemViewData(orderItemDataObject);
	disbaleOrderItemUpdateDisallowedFields(paramsMap);
	doAfterOrderItemPanelRefreshed();
    
    
}
function disbaleOrderItemUpdateDisallowedFields(paramsMap)
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
function loadOrderItemViewData(orderItemDataObject, paramsMap)
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
	var orderItemId = orderItemDataObject['orderItemId'];
	$('#'+prefix+'idValueForOrderItem').val(orderItemId);
		
	if(orderItemDataObject.hasOwnProperty('orderId'))
	{
		var orderId = orderItemDataObject['orderId'];            		
		parentElement.find('[data-name="'+prefix+'OrderItem_orderId"]').text(orderId);
	}
	
	if(orderItemDataObject.hasOwnProperty('productId'))
	{
		var productId = orderItemDataObject['productId'];            		
		parentElement.find('[data-name="'+prefix+'OrderItem_productId"]').text(productId);
	}
	
	if(orderItemDataObject.hasOwnProperty('productQuantity'))
	{
		var productQuantity = orderItemDataObject['productQuantity'];            		
		parentElement.find('[data-name="'+prefix+'OrderItem_productQuantity"]').text(productQuantity);
	}
	
	if(orderItemDataObject.hasOwnProperty('productUnitPrice'))
	{
		var productUnitPrice = orderItemDataObject['productUnitPrice'];            		
		parentElement.find('[data-name="'+prefix+'OrderItem_productUnitPrice"]').text(productUnitPrice);
	}
	
	if(orderItemDataObject.hasOwnProperty('subTotalAmt'))
	{
		var subTotalAmt = orderItemDataObject['subTotalAmt'];            		
		parentElement.find('[data-name="'+prefix+'OrderItem_subTotalAmt"]').text(subTotalAmt);
	}

}
function ajaxDemoForOrderItem()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForOrderItem').textContent;	
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
			refreshPanelForOrderItem();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForOrderItem(msg)
{
	var executeCustomAPIButtonForOrderItem = document.getElementById("FormWBEntity:executeCustomAPIButtonForOrderItem");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:OrderItem_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForOrderItem.click();
}
function refreshPanelForOrderItem()
{
	var demoRefreshButtonForOrderItem = document.getElementById("FormWBEntity:demoRefreshButtonOrderItem");
	demoRefreshButtonForOrderItem.click();
}
function initializePanelOnLoadForCreateOrderItem(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForOrderItem = initParamsObj;
	initializeOrderItemPage();	
	doAfterOrderItemPanelRefreshed();
	initializeOrderItemLookupFields(initParamsObj);
	doAfterPanelInitializedForOrderItemExt();
	fetchOrderItemDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="orderItemCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForOrderItem = 0;
function initializePanelOnLoadForSearchOrderItem()
{
	initializeOrderItemPage();	
	initializeOrderItemLookupFields();
	doAfterPanelInitializedForOrderItemExt();
	gLoadDashboardResultsForOrderItem =1;
	//retrieveOrderItemList();
}
function initializePanelOnLoadForViewOrderItem(urlParams)
{
	initializeOrderItemPage();	
	doAfterOrderItemPanelRefreshed();
	initializeOrderItemLookupFields(urlParams);
	doAfterPanelInitializedForOrderItemExt();
	retrieveOrderItem(urlParams);
	initDatePicker();
	$('[data-name="orderItemViewFormButtonsDiv"]').css("display", "inline");
}
function initializeOrderItemPage()
{
	initializePageOnload();	
	//initializeOrderItemTemplateVariables();
}
function initializeOrderItemTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="orderItemSearchResults"]').find('[data-name="orderItemRow"]');
	if(searchResultsRowObj.length > 0 && gOrderItemSearchResultsTableRowTemplate.length == 0)
	{
		gOrderItemSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveOrderItem(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var orderItemId = paramsMap['orderItemId'];
	var orderItem = {};
	orderItem['orderItemId'] = orderItemId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				orderItem[key] = paramsMap[key];
			}
	}
    var orderItemJsonData = {'paramsInfo': JSON.stringify(orderItem), 'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
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
                	var orderItemDataObject = responseData['orderItemDataObject'];
    				setOrderItemInViewPage(orderItemDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForOrderItem()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchOrderItemDefaultData();
}
function fetchOrderItemDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'OrderItem', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var orderItem = responseData['orderItem'];
            	document.getElementById('idValueForOrderItem').value = '';
			    
            	setOrderItemData(orderItem);
            }
        }
    });	
}
function fetchOrderItemTestData() 
{
	var orderItem = getDataForOrderItem();
    var searchJsonData = {'objectType' : 'OrderItem', 'paramsInfo' : JSON.stringify(orderItem)};
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
            	var orderItem = responseData['orderItem'];
            	document.getElementById('idValueForOrderItem').value = '';
			    
            	setOrderItemData(orderItem);
            }
        }
    });	
}
function setOrderItemData(orderItemDataObject)
{
	var prefix = "";
		
	//Input
	if(orderItemDataObject.hasOwnProperty('orderId'))
	{
		var orderId = orderItemDataObject['orderId'];            		
		$('#'+prefix+'OrderItem_orderId').val(orderId);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('productId'))
	{
		var productId = orderItemDataObject['productId'];            		
		$('#'+prefix+'OrderItem_productId').val(productId);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('productQuantity'))
	{
		var productQuantity = orderItemDataObject['productQuantity'];            		
		$('#'+prefix+'OrderItem_productQuantity').val(productQuantity);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('productUnitPrice'))
	{
		var productUnitPrice = orderItemDataObject['productUnitPrice'];            		
		$('#'+prefix+'OrderItem_productUnitPrice').val(productUnitPrice);
	}
	
	//Input
	if(orderItemDataObject.hasOwnProperty('subTotalAmt'))
	{
		var subTotalAmt = orderItemDataObject['subTotalAmt'];            		
		$('#'+prefix+'OrderItem_subTotalAmt').val(subTotalAmt);
	}

	$('[data-name="orderItemActionButtonDiv"]').hide();
}
function initializeOrderItemLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="orderItemSearchResultsDiv"]');
	
    
}

function doAfterOrderItemPanelRefreshed()
{
    //doAfterPanelRefreshedForOrderItemExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForOrderItem(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForOrderItemExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForOrderItem(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForOrderItemExt(fieldName)
}
function getEntityIdForOrderItem()
{
	var idValue = document.getElementById('FormWBEntity:idValueForOrderItem').textContent;	
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
function openPrintPageForOrderItem()
{
	var entityId = getEntityIdForOrderItem();
	if(entityId>0)
	{
	    window.open("/reports/generated/OrderItem.jsp?orderItemId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForOrderItem(paramsMap)
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
	var orderItem = {};
		
	//Input
	if($("#"+prefix+"OrderItem_orderId").length == 1)
	{
		var orderId = $('#'+prefix+'OrderItem_orderId').val();
		orderItem['orderId'] = orderId;
	}
	
	//Input
	if($("#"+prefix+"OrderItem_productId").length == 1)
	{
		var productId = $('#'+prefix+'OrderItem_productId').val();
		orderItem['productId'] = productId;
	}
	
	//Input
	if($("#"+prefix+"OrderItem_productQuantity").length == 1)
	{
		var productQuantity = $('#'+prefix+'OrderItem_productQuantity').val();
		orderItem['productQuantity'] = productQuantity;
	}
	
	//Input
	if($("#"+prefix+"OrderItem_productUnitPrice").length == 1)
	{
		var productUnitPrice = $('#'+prefix+'OrderItem_productUnitPrice').val();
		orderItem['productUnitPrice'] = productUnitPrice;
	}
	
	//Input
	if($("#"+prefix+"OrderItem_subTotalAmt").length == 1)
	{
		var subTotalAmt = $('#'+prefix+'OrderItem_subTotalAmt').val();
		orderItem['subTotalAmt'] = subTotalAmt;
	}

	
	return orderItem;
}
function createOrderItem(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var orderItem = getDataForOrderItem(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				orderItem[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForOrderItem)
	{
		paramsInfo[key] = gInitParamsObjForOrderItem[key];
	}
	var validationObject = doOrderItemValidation(orderItem, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	orderItem['additionalParamsInfo'] = paramsInfo;
    var orderItemJsonData = {'paramsInfo': JSON.stringify(orderItem), 'objectType' : 'OrderItem'};
	var urlContextPath = "";//getContextPath();
	if(gOrderItemRequestUnderProcess == 1)
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
            	gOrderItemRequestUnderProcess = 0;
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
        data: orderItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gOrderItemRequestUnderProcess = 0;
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
            	var orderItemId = responseData['orderItemId'];
            	var popupElement = $('[data-name="OrderItemPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var orderItemDataObject = responseData['orderItemDataObject'];
    				setOrderItemInViewPage(orderItemDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewOrderItem.html?orderItemId="+orderItemId; 
        		}
				
            }
        }
    });
}
function resetTableForOrderItem()
{
	var orderItemListElement = $("[data-name='orderItemList']");
	var previousDataRowList  = orderItemListElement.find('[data-name="orderItemRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForOrderItem(paramsMap)
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
	$('#'+prefix+'idValueForOrderItem').val('');
		
	//Input
	$('#'+prefix+'OrderItem_orderId').val('');
	
	//Input
	$('#'+prefix+'OrderItem_productId').val('');
	
	//Input
	$('#'+prefix+'OrderItem_productQuantity').val('');
	
	//Input
	$('#'+prefix+'OrderItem_productUnitPrice').val('');
	
	//Input
	$('#'+prefix+'OrderItem_subTotalAmt').val('');

	$('[data-name="orderItemCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="orderItemViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="orderItemActionButtonDiv"]').hide();
	enableOrderItemUpdateDisallowedFields(paramsMap);
    
}
function enableOrderItemUpdateDisallowedFields(paramsMap)
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
function updateOrderItem(paramsMap)
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
	var orderItem = getDataForOrderItem(paramsMap)
	if($("#"+prefix+"idValueForOrderItem").length == 1)
	{
		var orderItemId = $("#"+prefix+"idValueForOrderItem").val();
		orderItem['orderItemId'] = orderItemId;
	}
	var validationObject = doOrderItemValidation(orderItem, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var orderItemJsonData = {'paramsInfo': JSON.stringify(orderItem), 'objectType' : 'OrderItem'};
	var urlContextPath = "";//getContextPath();
	if(gOrderItemRequestUnderProcess == 1)
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
                    	gOrderItemRequestUnderProcess = 0;
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
        data: orderItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gOrderItemRequestUnderProcess = 0;
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
function deleteOrderItem(paramsMap)
{		
	var orderItemId = document.getElementById('idValueForOrderItem').value;
	deleteSelectedOrderItem(orderItemId, paramsMap);
}
function deleteSelectedOrderItem(orderItemId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var orderItem = {};
	orderItem['orderItemId'] = orderItemId;	
    var orderItemJsonData = {'paramsInfo': JSON.stringify(orderItem), 'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
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
function loadSearchResultsForOrderItem()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'OrderItem'};
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
            	var orderItemSearchResultsElement = $("[data-name='orderItemSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var orderItemDataObject = searchResultObjectsList[i];
					            		var orderId = orderItemDataObject['orderId'];            		
            		var productId = orderItemDataObject['productId'];            		
            		var productQuantity = orderItemDataObject['productQuantity'];            		
            		var productUnitPrice = orderItemDataObject['productUnitPrice'];            		
            		var subTotalAmt = orderItemDataObject['subTotalAmt'];            		

            		var resultRowTemnplate = $(gOrderItemSearchResultsTableRowTemplate);
					            		var orderId = orderItemDataObject['orderId'];            		
            	    resultRowTemnplate.find("[data-name='orderId']").text(orderId);
            		var productId = orderItemDataObject['productId'];            		
            	    resultRowTemnplate.find("[data-name='productId']").text(productId);
            		var productQuantity = orderItemDataObject['productQuantity'];            		
            	    resultRowTemnplate.find("[data-name='productQuantity']").text(productQuantity);
            		var productUnitPrice = orderItemDataObject['productUnitPrice'];            		
            	    resultRowTemnplate.find("[data-name='productUnitPrice']").text(productUnitPrice);
            		var subTotalAmt = orderItemDataObject['subTotalAmt'];            		
            	    resultRowTemnplate.find("[data-name='subTotalAmt']").text(subTotalAmt);

            	    resultRowTemnplate.data('orderItem', orderItemDataObject);
            	    orderItemSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gOrderItemSearchResultsTableRowTemplate = ""; 
function openViewPageForOrderItemForEdit(orderItemLinkElement)
{
	var orderItemRowElement = $(orderItemLinkElement).parents('[data-name="orderItemRow"]');
    var orderItemDataObject  = orderItemRowElement.data('orderItem');
	var orderItemId = orderItemDataObject['orderItemId'];
	var orderItemPopupElement = $('[data-name="OrderItemPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setOrderItemInViewPage(orderItemDataObject);
	    $("#OrderItem-tab").trigger("click");
	}
	else if(orderItemPopupElement.length > 0)
	{
	    setOrderItemInViewPage(orderItemDataObject);
		$('[data-name="OrderItemPopup"]').find('[data-name="orderItemCreateFormButtonsDiv"]').hide();
		$('[data-name="OrderItemPopup"]').find('[data-name="orderItemViewFormButtonsDiv"]').show();
	    showPopup('OrderItemPopup', orderItemLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewOrderItem.html?orderItemId="+orderItemId;
		window.open(viewLink, "_blank"); 	
	}
}
function openOrderItemCreatePageForNew(linkElement)
{
	var orderItemPopupElement = $('[data-name="OrderItemPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForOrderItem();
	    $("#OrderItem-tab").trigger("click");
    }
	else if(orderItemPopupElement.length > 0)
	{
		resetFormForOrderItem();
	    showPopup('OrderItemPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateOrderItem.html";
    }
}
function showOrderItemPopupForEdit(orderItemLinkElement)
{
	var orderItemRowElement = $(orderItemLinkElement).parents('[data-name="orderItemRow"]');
    var orderItemDataObject  = orderItemRowElement.data('orderItem');
    setOrderItemInViewPage(orderItemDataObject);
    showPopup('OrderItemPopup', orderItemLinkElement, 1);
    $("#OrderItem-tab").trigger("click");
}
function showOrderItemPopupForNew(buttonElement)
{
	resetFormForOrderItem();
    showPopup('OrderItemPopup', buttonElement, 1);
    $("#OrderItem-tab").trigger("click");
}
function deleteThisOrderItem(orderItemLinkElement, paramsMap)
{
	var orderItemRowElement = $(orderItemLinkElement).parents('[data-name="orderItemRow"]');
    var orderItemDataObject  = orderItemRowElement.data('orderItem');
	var orderItemId = orderItemDataObject['orderItemId'];
	deleteSelectedOrderItem(orderItemId, paramsMap);
}
function displayOrderItemList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + orderItemSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var orderItemSearchResults = searchResultsDiv.find('[data-name="orderItemSearchResults"]');
	//orderItemSearchResults.find('[data-name="orderItemRow"]').remove();
	var previousDataRowList  = orderItemSearchResults.find('[data-name="orderItemRow"]');
	orderItemSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = orderItemSearchResults.find('[data-name="orderItemRow"]');
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
        var orderItemDataObject = searchResultObjectsList[i];
        loadOrderItemViewData(orderItemDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = orderItemDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='orderItemVwTxnStatus']").text(vwTxnStatus);
		if(orderItemDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="orderItemActionButton"]').text(orderItemDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="orderItemActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="orderItemActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="orderItemRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="orderItemRejectButton"]').show();
		}
	    resultRowTemplate.data('orderItem', orderItemDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    orderItemSearchResults.append(resultRowTemplate);
	    processResultRowForOrderItemExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForOrderItem == 1)
	{
    	getDashboardResultsForOrderItem();
	}
}
var orderItemSearchResultsDivName = "orderItemSearchResultsDiv";
var gOrderItemSearchInputParamsList = [];
function retrieveOrderItemList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + orderItemSearchResultsDivName + '"]');
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
    fetchOrderItemSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchOrderItemSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getOrderItemSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = orderItemSearchResultsDivName; 
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
        'objectType': "OrderItem"
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
            	this.showPreviousRecords = "showPreviousOrderItemRecords";
            	this.showCurrentPageRecords = "showCurrentPageOrderItemRecords";
            	this.showNextRecords = "showNextOrderItemRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchOrderItemResults";
                var orderItemList = responseData['orderItemList'];
                var currContextObj = this; 
                var successCallbackFunction = displayOrderItemList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'orderItemList', 'matchingSearchResultsCount', this.searchResultsDivName, 'orderItemSearchResults', 'orderItemRow', setOrderItemSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getOrderItemSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + orderItemSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="orderItemDB_orderId"]').length == 1)
		{
		    var orderId = searchDiv.find('[data-name="orderItemDB_orderId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'orderId', 'userInputValue':orderId});
		}
		
		//Input
		if(searchDiv.find('[data-name="orderItemDB_productId"]').length == 1)
		{
		    var productId = searchDiv.find('[data-name="orderItemDB_productId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productId', 'userInputValue':productId});
		}
		
		//Input
		if(searchDiv.find('[data-name="orderItemDB_productQuantity"]').length == 1)
		{
		    var productQuantity = searchDiv.find('[data-name="orderItemDB_productQuantity"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productQuantity', 'userInputValue':productQuantity});
		}
		
		//Input
		if(searchDiv.find('[data-name="orderItemDB_productUnitPrice"]').length == 1)
		{
		    var productUnitPrice = searchDiv.find('[data-name="orderItemDB_productUnitPrice"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productUnitPrice', 'userInputValue':productUnitPrice});
		}
		
		//Input
		if(searchDiv.find('[data-name="orderItemDB_subTotalAmt"]').length == 1)
		{
		    var subTotalAmt = searchDiv.find('[data-name="orderItemDB_subTotalAmt"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'subTotalAmt', 'userInputValue':subTotalAmt});
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
        gOrderItemSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gOrderItemSearchInputParamsList;
    }
    return searchInputParams;
}
function setOrderItemSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + orderItemSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gOrderItemSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gOrderItemSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageOrderItemRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = orderItemSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchOrderItemSearchResultsList);
}
function showPreviousOrderItemRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = orderItemSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchOrderItemSearchResultsList);
}
function showNextOrderItemRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = orderItemSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchOrderItemSearchResultsList);
}
function showPrevOrNextSearchOrderItemResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousOrderItemRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextOrderItemRecords(e);
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
function lookupRowSelectedForOrderItem(attributeName, attributeId)
{
	var orderItem = getDataForOrderItem();
	orderItem['attributeName'] = attributeName;
	orderItem['attributeId'] = attributeId;
    var orderItemJsonData = {'paramsInfo': JSON.stringify(orderItem), 'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orderItem = responseData['orderItem'];
            	setOrderItemData(orderItem);
            }
        }
    });	
}
function selectOptionChangedForOrderItem(attributeName)
{
	var orderItem = getDataForOrderItem();
	orderItem['attributeName'] = attributeName;
    var orderItemJsonData = {'paramsInfo': JSON.stringify(orderItem), 'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orderItem = responseData['orderItem'];
            	setOrderItemData(orderItem);
            	doAfterOrderItemPanelRefreshed();
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
function retrieveDependentOrderItemList(paramsMap)
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
    var searchJsonData = {'objectType' : 'OrderItem', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var orderItemList = responseData['orderItemList'];
            	var orderItemListElement = $("[data-name='orderItemList']");
            	var previousDataRowList  = orderItemListElement.find('[data-name="orderItemRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = orderItemListElement.find('[data-name="orderItemRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//orderItemListElement.empty();
            	for(var i=0; i<orderItemList.length; i++)
        		{
            		var orderItemDataObject = orderItemList[i];
            		//var resultRowTemplate = $(gOrderItemSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var orderId = orderItemDataObject['orderId'];            		
				    resultRowTemplate.find("[data-name='orderId']").text(orderId);
					var productId = orderItemDataObject['productId'];            		
				    resultRowTemplate.find("[data-name='productId']").text(productId);
					var productQuantity = orderItemDataObject['productQuantity'];            		
				    resultRowTemplate.find("[data-name='productQuantity']").text(productQuantity);
					var productUnitPrice = orderItemDataObject['productUnitPrice'];            		
				    resultRowTemplate.find("[data-name='productUnitPrice']").text(productUnitPrice);
					var subTotalAmt = orderItemDataObject['subTotalAmt'];            		
				    resultRowTemplate.find("[data-name='subTotalAmt']").text(subTotalAmt);

					
					
            	    resultRowTemplate.data('orderItem', orderItemDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    orderItemListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForOrderItem(customEventName)
{
	var orderItemId = document.getElementById('idValueForOrderItem').value;
	var orderItem = {'orderItemId':orderItemId, 'customEventName':customEventName};
    var orderItemJsonData = {'paramsInfo':JSON.stringify(orderItem), 'objectType' : 'OrderItem'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForOrderItemExt(customEventName);
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
        data: orderItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orderItem = responseData['orderItem'];
        		setOrderItemInViewPage(orderItem);
            }
    		doAfterExecuteCustomAPIForOrderItemExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnOrderItem(e, paramsMap)
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
	var orderItemId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var orderItemDataObject  = rowObj.data('orderItem');
    	orderItemId = orderItemDataObject['orderItemId'];
	}
    else
	{
    	orderItemId = document.getElementById('idValueForOrderItem').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var orderItemSearchParams = {'orderItemId':orderItemId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	orderItemSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var orderItemJsonData = {'paramsInfo':JSON.stringify(orderItemSearchParams),  'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var orderItem = responseData['orderItem'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="orderItemRowActionButtonDiv"]').hide();
            		if(orderItem.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = orderItem['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(orderItem['vwTxnStatus']);
            			selectedRowObj.find('[data-name="orderItemActionButton"]').text(orderItem["nextAction"]);
            			selectedRowObj.find('[data-name="orderItemActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setOrderItemInViewPage(orderItem);
    			}
            }
        }
    });	
}
function downloadOrderItemData()
{		
	var orderItem = {};
    var orderItemJsonData = {'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=OrderItem");
            }
        }
    });
}
function uploadOrderItemData(fileInfo)
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
	var orderItem = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var orderItemJsonData = {'paramsInfo':JSON.stringify(orderItem),  'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=OrderItem");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForOrderItem()
{
    var orderItemJsonData = {'objectType' : 'OrderItem'};
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
        data: orderItemJsonData,
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



function doOrderItemLengthValidation(orderItemDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(orderItemDataObject['orderId'], 15))
	{
		alertMessage += "\n OrderId length is more than allowed(15)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(orderItemDataObject['productId'], 20))
	{
		alertMessage += "\n ProductId length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(orderItemDataObject['productQuantity'], 15))
	{
		alertMessage += "\n ProductQuantity length is more than allowed(15)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(orderItemDataObject['productUnitPrice'], 15))
	{
		alertMessage += "\n ProductUnitPrice length is more than allowed(15)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(orderItemDataObject['subTotalAmt'], 20))
	{
		alertMessage += "\n SubTotalAmt length is more than allowed(20)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doOrderItemManadatoryValidation(orderItemDataObject, paramsMap)
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
function doOrderItemValidation(orderItemDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doOrderItemLengthValidation(orderItemDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doOrderItemManadatoryValidation(orderItemDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
