/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'UserCartId' : 'FormWBEntity:CartItem_userCartId' 
 *	 * 'ProductId' : 'FormWBEntity:CartItem_productId' 
 *	 * 'ProductQuantity' : 'FormWBEntity:CartItem_productQuantity' 
 *	 * 'ProductUnitPrice' : 'FormWBEntity:CartItem_productUnitPrice' 
 *	 * 'SubTotalAmount' : 'FormWBEntity:CartItem_subTotalAmount' 
 *	 * 'GrandTotal' : 'FormWBEntity:CartItem_grandTotal' 
 *	
 */
var gInitParamsObjForCartItem = {};
var gCartItemRequestUnderProcess = 0;
function selectThisCartItemForEdit(cartItemRowElement)
{
    var cartItemDataObject  = $(cartItemRowElement).data('cartItem');
    var cartItemList = $('[data-name="cartItemSearchResults"]').find('[data-name="cartItemRow"]');
	cartItemList.data("is-selected", 0);	
	$(cartItemRowElement).data("is-selected", 1);
	setCartItemInViewPage(cartItemDataObject);
}

function setCartItemInViewPage(cartItemDataObject, paramsMap)
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
	var cartItemId = cartItemDataObject['cartItemId'];
	$('#'+prefix+'idValueForCartItem').val(cartItemId);
		
	//Input
	if(cartItemDataObject.hasOwnProperty('userCartId'))
	{
		var userCartId = cartItemDataObject['userCartId'];            		
		$('#'+prefix+'CartItem_userCartId').val(userCartId);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('productId'))
	{
		var productId = cartItemDataObject['productId'];            		
		$('#'+prefix+'CartItem_productId').val(productId);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('productQuantity'))
	{
		var productQuantity = cartItemDataObject['productQuantity'];            		
		$('#'+prefix+'CartItem_productQuantity').val(productQuantity);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('productUnitPrice'))
	{
		var productUnitPrice = cartItemDataObject['productUnitPrice'];            		
		$('#'+prefix+'CartItem_productUnitPrice').val(productUnitPrice);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('subTotalAmount'))
	{
		var subTotalAmount = cartItemDataObject['subTotalAmount'];            		
		$('#'+prefix+'CartItem_subTotalAmount').val(subTotalAmount);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('grandTotal'))
	{
		var grandTotal = cartItemDataObject['grandTotal'];            		
		$('#'+prefix+'CartItem_grandTotal').val(grandTotal);
	}

	if(cartItemDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = cartItemDataObject['vwTxnStatus'];
		$('[data-name="cartItemActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnCartItem(this)" >' +cartItemDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="cartItemActionButtonDiv"]').append(buttonElement);
		$('[data-name="cartItemActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="cartItemActionButtonDiv"]').hide();
	}
	$('[data-name="cartItemCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="cartItemViewFormButtonsDiv"]').css("display", "inline");
	//loadCartItemViewData(cartItemDataObject);
	disbaleCartItemUpdateDisallowedFields(paramsMap);
	doAfterCartItemPanelRefreshed();
    
    
}
function disbaleCartItemUpdateDisallowedFields(paramsMap)
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
function loadCartItemViewData(cartItemDataObject, paramsMap)
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
	var cartItemId = cartItemDataObject['cartItemId'];
	$('#'+prefix+'idValueForCartItem').val(cartItemId);
		
	if(cartItemDataObject.hasOwnProperty('userCartId'))
	{
		var userCartId = cartItemDataObject['userCartId'];            		
		parentElement.find('[data-name="'+prefix+'CartItem_userCartId"]').text(userCartId);
	}
	
	if(cartItemDataObject.hasOwnProperty('productId'))
	{
		var productId = cartItemDataObject['productId'];            		
		parentElement.find('[data-name="'+prefix+'CartItem_productId"]').text(productId);
	}
	
	if(cartItemDataObject.hasOwnProperty('productQuantity'))
	{
		var productQuantity = cartItemDataObject['productQuantity'];            		
		parentElement.find('[data-name="'+prefix+'CartItem_productQuantity"]').text(productQuantity);
	}
	
	if(cartItemDataObject.hasOwnProperty('productUnitPrice'))
	{
		var productUnitPrice = cartItemDataObject['productUnitPrice'];            		
		parentElement.find('[data-name="'+prefix+'CartItem_productUnitPrice"]').text(productUnitPrice);
	}
	
	if(cartItemDataObject.hasOwnProperty('subTotalAmount'))
	{
		var subTotalAmount = cartItemDataObject['subTotalAmount'];            		
		parentElement.find('[data-name="'+prefix+'CartItem_subTotalAmount"]').text(subTotalAmount);
	}
	
	if(cartItemDataObject.hasOwnProperty('grandTotal'))
	{
		var grandTotal = cartItemDataObject['grandTotal'];            		
		parentElement.find('[data-name="'+prefix+'CartItem_grandTotal"]').text(grandTotal);
	}

}
function ajaxDemoForCartItem()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForCartItem').textContent;	
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
			refreshPanelForCartItem();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForCartItem(msg)
{
	var executeCustomAPIButtonForCartItem = document.getElementById("FormWBEntity:executeCustomAPIButtonForCartItem");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:CartItem_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForCartItem.click();
}
function refreshPanelForCartItem()
{
	var demoRefreshButtonForCartItem = document.getElementById("FormWBEntity:demoRefreshButtonCartItem");
	demoRefreshButtonForCartItem.click();
}
function initializePanelOnLoadForCreateCartItem(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForCartItem = initParamsObj;
	initializeCartItemPage();	
	doAfterCartItemPanelRefreshed();
	initializeCartItemLookupFields(initParamsObj);
	doAfterPanelInitializedForCartItemExt();
	fetchCartItemDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="cartItemCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForCartItem = 0;
function initializePanelOnLoadForSearchCartItem()
{
	initializeCartItemPage();	
	initializeCartItemLookupFields();
	doAfterPanelInitializedForCartItemExt();
	gLoadDashboardResultsForCartItem =1;
	//retrieveCartItemList();
}
function initializePanelOnLoadForViewCartItem(urlParams)
{
	initializeCartItemPage();	
	doAfterCartItemPanelRefreshed();
	initializeCartItemLookupFields(urlParams);
	doAfterPanelInitializedForCartItemExt();
	retrieveCartItem(urlParams);
	initDatePicker();
	$('[data-name="cartItemViewFormButtonsDiv"]').css("display", "inline");
}
function initializeCartItemPage()
{
	initializePageOnload();	
	//initializeCartItemTemplateVariables();
}
function initializeCartItemTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="cartItemSearchResults"]').find('[data-name="cartItemRow"]');
	if(searchResultsRowObj.length > 0 && gCartItemSearchResultsTableRowTemplate.length == 0)
	{
		gCartItemSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveCartItem(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var cartItemId = paramsMap['cartItemId'];
	var cartItem = {};
	cartItem['cartItemId'] = cartItemId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				cartItem[key] = paramsMap[key];
			}
	}
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
		url : urlContextPath + '/retrieveEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
        data: cartItemJsonData,
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
                	var cartItemDataObject = responseData['cartItemDataObject'];
    				setCartItemInViewPage(cartItemDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForCartItem()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchCartItemDefaultData();
}
function fetchCartItemDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'CartItem', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var cartItem = responseData['cartItem'];
            	document.getElementById('idValueForCartItem').value = '';
			    
            	setCartItemData(cartItem);
            }
        }
    });	
}
function fetchCartItemTestData() 
{
	var cartItem = getDataForCartItem();
    var searchJsonData = {'objectType' : 'CartItem', 'paramsInfo' : JSON.stringify(cartItem)};
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
            	var cartItem = responseData['cartItem'];
            	document.getElementById('idValueForCartItem').value = '';
			    
            	setCartItemData(cartItem);
            }
        }
    });	
}
function setCartItemData(cartItemDataObject)
{
	var prefix = "";
		
	//Input
	if(cartItemDataObject.hasOwnProperty('userCartId'))
	{
		var userCartId = cartItemDataObject['userCartId'];            		
		$('#'+prefix+'CartItem_userCartId').val(userCartId);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('productId'))
	{
		var productId = cartItemDataObject['productId'];            		
		$('#'+prefix+'CartItem_productId').val(productId);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('productQuantity'))
	{
		var productQuantity = cartItemDataObject['productQuantity'];            		
		$('#'+prefix+'CartItem_productQuantity').val(productQuantity);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('productUnitPrice'))
	{
		var productUnitPrice = cartItemDataObject['productUnitPrice'];            		
		$('#'+prefix+'CartItem_productUnitPrice').val(productUnitPrice);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('subTotalAmount'))
	{
		var subTotalAmount = cartItemDataObject['subTotalAmount'];            		
		$('#'+prefix+'CartItem_subTotalAmount').val(subTotalAmount);
	}
	
	//Input
	if(cartItemDataObject.hasOwnProperty('grandTotal'))
	{
		var grandTotal = cartItemDataObject['grandTotal'];            		
		$('#'+prefix+'CartItem_grandTotal').val(grandTotal);
	}

	$('[data-name="cartItemActionButtonDiv"]').hide();
}
function initializeCartItemLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="cartItemSearchResultsDiv"]');
	
    
}

function doAfterCartItemPanelRefreshed()
{
    //doAfterPanelRefreshedForCartItemExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForCartItem(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForCartItemExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForCartItem(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForCartItemExt(fieldName)
}
function getEntityIdForCartItem()
{
	var idValue = document.getElementById('FormWBEntity:idValueForCartItem').textContent;	
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
function openPrintPageForCartItem()
{
	var entityId = getEntityIdForCartItem();
	if(entityId>0)
	{
	    window.open("/reports/generated/CartItem.jsp?cartItemId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForCartItem(paramsMap)
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
	var cartItem = {};
		
	//Input
	if($("#"+prefix+"CartItem_userCartId").length == 1)
	{
		var userCartId = $('#'+prefix+'CartItem_userCartId').val();
		cartItem['userCartId'] = userCartId;
	}
	
	//Input
	if($("#"+prefix+"CartItem_productId").length == 1)
	{
		var productId = $('#'+prefix+'CartItem_productId').val();
		cartItem['productId'] = productId;
	}
	
	//Input
	if($("#"+prefix+"CartItem_productQuantity").length == 1)
	{
		var productQuantity = $('#'+prefix+'CartItem_productQuantity').val();
		cartItem['productQuantity'] = productQuantity;
	}
	
	//Input
	if($("#"+prefix+"CartItem_productUnitPrice").length == 1)
	{
		var productUnitPrice = $('#'+prefix+'CartItem_productUnitPrice').val();
		cartItem['productUnitPrice'] = productUnitPrice;
	}
	
	//Input
	if($("#"+prefix+"CartItem_subTotalAmount").length == 1)
	{
		var subTotalAmount = $('#'+prefix+'CartItem_subTotalAmount').val();
		cartItem['subTotalAmount'] = subTotalAmount;
	}
	
	//Input
	if($("#"+prefix+"CartItem_grandTotal").length == 1)
	{
		var grandTotal = $('#'+prefix+'CartItem_grandTotal').val();
		cartItem['grandTotal'] = grandTotal;
	}

	
	return cartItem;
}
function createCartItem(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var cartItem = getDataForCartItem(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				cartItem[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForCartItem)
	{
		paramsInfo[key] = gInitParamsObjForCartItem[key];
	}
	var validationObject = doCartItemValidation(cartItem, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	cartItem['additionalParamsInfo'] = paramsInfo;
    var cartItemJsonData = {'paramsInfo': JSON.stringify(cartItem), 'objectType' : 'CartItem'};
	var urlContextPath = "";//getContextPath();
	if(gCartItemRequestUnderProcess == 1)
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
            	gCartItemRequestUnderProcess = 0;
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
        data: cartItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gCartItemRequestUnderProcess = 0;
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
            	var cartItemId = responseData['cartItemId'];
            	var popupElement = $('[data-name="CartItemPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var cartItemDataObject = responseData['cartItemDataObject'];
    				setCartItemInViewPage(cartItemDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewCartItem.html?cartItemId="+cartItemId; 
        		}
				
            }
        }
    });
}
function resetTableForCartItem()
{
	var cartItemListElement = $("[data-name='cartItemList']");
	var previousDataRowList  = cartItemListElement.find('[data-name="cartItemRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForCartItem(paramsMap)
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
	$('#'+prefix+'idValueForCartItem').val('');
		
	//Input
	$('#'+prefix+'CartItem_userCartId').val('');
	
	//Input
	$('#'+prefix+'CartItem_productId').val('');
	
	//Input
	$('#'+prefix+'CartItem_productQuantity').val('');
	
	//Input
	$('#'+prefix+'CartItem_productUnitPrice').val('');
	
	//Input
	$('#'+prefix+'CartItem_subTotalAmount').val('');
	
	//Input
	$('#'+prefix+'CartItem_grandTotal').val('');

	$('[data-name="cartItemCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="cartItemViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="cartItemActionButtonDiv"]').hide();
	enableCartItemUpdateDisallowedFields(paramsMap);
    
}
function enableCartItemUpdateDisallowedFields(paramsMap)
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
function updateCartItem(paramsMap)
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
	var cartItem = getDataForCartItem(paramsMap)
	if($("#"+prefix+"idValueForCartItem").length == 1)
	{
		var cartItemId = $("#"+prefix+"idValueForCartItem").val();
		cartItem['cartItemId'] = cartItemId;
	}
	var validationObject = doCartItemValidation(cartItem, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var cartItemJsonData = {'paramsInfo': JSON.stringify(cartItem), 'objectType' : 'CartItem'};
	var urlContextPath = "";//getContextPath();
	if(gCartItemRequestUnderProcess == 1)
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
                    	gCartItemRequestUnderProcess = 0;
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
        data: cartItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gCartItemRequestUnderProcess = 0;
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
function deleteCartItem(paramsMap)
{		
	var cartItemId = document.getElementById('idValueForCartItem').value;
	deleteSelectedCartItem(cartItemId, paramsMap);
}
function deleteSelectedCartItem(cartItemId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
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
		url : urlContextPath + '/deleteEntity'+'?loginbranchid='+getCookie("loginbranchid")+'&employeeid='+getCookie("employeeid")+'&issuperuser='+getCookie("issuperuser"),
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
}
function loadSearchResultsForCartItem()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'CartItem'};
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
            	var cartItemSearchResultsElement = $("[data-name='cartItemSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var cartItemDataObject = searchResultObjectsList[i];
					            		var userCartId = cartItemDataObject['userCartId'];            		
            		var productId = cartItemDataObject['productId'];            		
            		var productQuantity = cartItemDataObject['productQuantity'];            		
            		var productUnitPrice = cartItemDataObject['productUnitPrice'];            		
            		var subTotalAmount = cartItemDataObject['subTotalAmount'];            		
            		var grandTotal = cartItemDataObject['grandTotal'];            		

            		var resultRowTemnplate = $(gCartItemSearchResultsTableRowTemplate);
					            		var userCartId = cartItemDataObject['userCartId'];            		
            	    resultRowTemnplate.find("[data-name='userCartId']").text(userCartId);
            		var productId = cartItemDataObject['productId'];            		
            	    resultRowTemnplate.find("[data-name='productId']").text(productId);
            		var productQuantity = cartItemDataObject['productQuantity'];            		
            	    resultRowTemnplate.find("[data-name='productQuantity']").text(productQuantity);
            		var productUnitPrice = cartItemDataObject['productUnitPrice'];            		
            	    resultRowTemnplate.find("[data-name='productUnitPrice']").text(productUnitPrice);
            		var subTotalAmount = cartItemDataObject['subTotalAmount'];            		
            	    resultRowTemnplate.find("[data-name='subTotalAmount']").text(subTotalAmount);
            		var grandTotal = cartItemDataObject['grandTotal'];            		
            	    resultRowTemnplate.find("[data-name='grandTotal']").text(grandTotal);

            	    resultRowTemnplate.data('cartItem', cartItemDataObject);
            	    cartItemSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gCartItemSearchResultsTableRowTemplate = ""; 
function openViewPageForCartItemForEdit(cartItemLinkElement)
{
	var cartItemRowElement = $(cartItemLinkElement).parents('[data-name="cartItemRow"]');
    var cartItemDataObject  = cartItemRowElement.data('cartItem');
	var cartItemId = cartItemDataObject['cartItemId'];
	var cartItemPopupElement = $('[data-name="CartItemPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setCartItemInViewPage(cartItemDataObject);
	    $("#CartItem-tab").trigger("click");
	}
	else if(cartItemPopupElement.length > 0)
	{
	    setCartItemInViewPage(cartItemDataObject);
		$('[data-name="CartItemPopup"]').find('[data-name="cartItemCreateFormButtonsDiv"]').hide();
		$('[data-name="CartItemPopup"]').find('[data-name="cartItemViewFormButtonsDiv"]').show();
	    showPopup('CartItemPopup', cartItemLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewCartItem.html?cartItemId="+cartItemId;
		window.open(viewLink, "_blank"); 	
	}
}
function openCartItemCreatePageForNew(linkElement)
{
	var cartItemPopupElement = $('[data-name="CartItemPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForCartItem();
	    $("#CartItem-tab").trigger("click");
    }
	else if(cartItemPopupElement.length > 0)
	{
		resetFormForCartItem();
	    showPopup('CartItemPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateCartItem.html";
    }
}
function showCartItemPopupForEdit(cartItemLinkElement)
{
	var cartItemRowElement = $(cartItemLinkElement).parents('[data-name="cartItemRow"]');
    var cartItemDataObject  = cartItemRowElement.data('cartItem');
    setCartItemInViewPage(cartItemDataObject);
    showPopup('CartItemPopup', cartItemLinkElement, 1);
    $("#CartItem-tab").trigger("click");
}
function showCartItemPopupForNew(buttonElement)
{
	resetFormForCartItem();
    showPopup('CartItemPopup', buttonElement, 1);
    $("#CartItem-tab").trigger("click");
}
function deleteThisCartItem(cartItemLinkElement, paramsMap)
{
	var cartItemRowElement = $(cartItemLinkElement).parents('[data-name="cartItemRow"]');
    var cartItemDataObject  = cartItemRowElement.data('cartItem');
	var cartItemId = cartItemDataObject['cartItemId'];
	deleteSelectedCartItem(cartItemId, paramsMap);
}
function displayCartItemList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + cartItemSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var cartItemSearchResults = searchResultsDiv.find('[data-name="cartItemSearchResults"]');
	//cartItemSearchResults.find('[data-name="cartItemRow"]').remove();
	var previousDataRowList  = cartItemSearchResults.find('[data-name="cartItemRow"]');
	cartItemSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = cartItemSearchResults.find('[data-name="cartItemRow"]');
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
        var cartItemDataObject = searchResultObjectsList[i];
        loadCartItemViewData(cartItemDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = cartItemDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='cartItemVwTxnStatus']").text(vwTxnStatus);
		if(cartItemDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="cartItemActionButton"]').text(cartItemDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="cartItemActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="cartItemActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="cartItemRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="cartItemRejectButton"]').show();
		}
	    resultRowTemplate.data('cartItem', cartItemDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    cartItemSearchResults.append(resultRowTemplate);
	    processResultRowForCartItemExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForCartItem == 1)
	{
    	getDashboardResultsForCartItem();
	}
}
var cartItemSearchResultsDivName = "cartItemSearchResultsDiv";
var gCartItemSearchInputParamsList = [];
function retrieveCartItemList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + cartItemSearchResultsDivName + '"]');
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
    fetchCartItemSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchCartItemSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getCartItemSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = cartItemSearchResultsDivName; 
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
        'objectType': "CartItem"
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
            	this.showPreviousRecords = "showPreviousCartItemRecords";
            	this.showCurrentPageRecords = "showCurrentPageCartItemRecords";
            	this.showNextRecords = "showNextCartItemRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchCartItemResults";
                var cartItemList = responseData['cartItemList'];
                var currContextObj = this; 
                var successCallbackFunction = displayCartItemList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'cartItemList', 'matchingSearchResultsCount', this.searchResultsDivName, 'cartItemSearchResults', 'cartItemRow', setCartItemSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getCartItemSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + cartItemSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="cartItemDB_userCartId"]').length == 1)
		{
		    var userCartId = searchDiv.find('[data-name="cartItemDB_userCartId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'userCartId', 'userInputValue':userCartId});
		}
		
		//Input
		if(searchDiv.find('[data-name="cartItemDB_productId"]').length == 1)
		{
		    var productId = searchDiv.find('[data-name="cartItemDB_productId"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productId', 'userInputValue':productId});
		}
		
		//Input
		if(searchDiv.find('[data-name="cartItemDB_productQuantity"]').length == 1)
		{
		    var productQuantity = searchDiv.find('[data-name="cartItemDB_productQuantity"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productQuantity', 'userInputValue':productQuantity});
		}
		
		//Input
		if(searchDiv.find('[data-name="cartItemDB_productUnitPrice"]').length == 1)
		{
		    var productUnitPrice = searchDiv.find('[data-name="cartItemDB_productUnitPrice"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productUnitPrice', 'userInputValue':productUnitPrice});
		}
		
		//Input
		if(searchDiv.find('[data-name="cartItemDB_subTotalAmount"]').length == 1)
		{
		    var subTotalAmount = searchDiv.find('[data-name="cartItemDB_subTotalAmount"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'subTotalAmount', 'userInputValue':subTotalAmount});
		}
		
		//Input
		if(searchDiv.find('[data-name="cartItemDB_grandTotal"]').length == 1)
		{
		    var grandTotal = searchDiv.find('[data-name="cartItemDB_grandTotal"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'grandTotal', 'userInputValue':grandTotal});
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
        gCartItemSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gCartItemSearchInputParamsList;
    }
    return searchInputParams;
}
function setCartItemSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + cartItemSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gCartItemSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gCartItemSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageCartItemRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = cartItemSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchCartItemSearchResultsList);
}
function showPreviousCartItemRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = cartItemSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchCartItemSearchResultsList);
}
function showNextCartItemRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = cartItemSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchCartItemSearchResultsList);
}
function showPrevOrNextSearchCartItemResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousCartItemRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextCartItemRecords(e);
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
function lookupRowSelectedForCartItem(attributeName, attributeId)
{
	var cartItem = getDataForCartItem();
	cartItem['attributeName'] = attributeName;
	cartItem['attributeId'] = attributeId;
    var cartItemJsonData = {'paramsInfo': JSON.stringify(cartItem), 'objectType' : 'CartItem'};
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
        data: cartItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var cartItem = responseData['cartItem'];
            	setCartItemData(cartItem);
            }
        }
    });	
}
function selectOptionChangedForCartItem(attributeName)
{
	var cartItem = getDataForCartItem();
	cartItem['attributeName'] = attributeName;
    var cartItemJsonData = {'paramsInfo': JSON.stringify(cartItem), 'objectType' : 'CartItem'};
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
        data: cartItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var cartItem = responseData['cartItem'];
            	setCartItemData(cartItem);
            	doAfterCartItemPanelRefreshed();
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
function retrieveDependentCartItemList(paramsMap)
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
    var searchJsonData = {'objectType' : 'CartItem', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var cartItemList = responseData['cartItemList'];
            	var cartItemListElement = $("[data-name='cartItemList']");
            	var previousDataRowList  = cartItemListElement.find('[data-name="cartItemRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = cartItemListElement.find('[data-name="cartItemRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//cartItemListElement.empty();
            	for(var i=0; i<cartItemList.length; i++)
        		{
            		var cartItemDataObject = cartItemList[i];
            		//var resultRowTemplate = $(gCartItemSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var userCartId = cartItemDataObject['userCartId'];            		
				    resultRowTemplate.find("[data-name='userCartId']").text(userCartId);
					var productId = cartItemDataObject['productId'];            		
				    resultRowTemplate.find("[data-name='productId']").text(productId);
					var productQuantity = cartItemDataObject['productQuantity'];            		
				    resultRowTemplate.find("[data-name='productQuantity']").text(productQuantity);
					var productUnitPrice = cartItemDataObject['productUnitPrice'];            		
				    resultRowTemplate.find("[data-name='productUnitPrice']").text(productUnitPrice);
					var subTotalAmount = cartItemDataObject['subTotalAmount'];            		
				    resultRowTemplate.find("[data-name='subTotalAmount']").text(subTotalAmount);
					var grandTotal = cartItemDataObject['grandTotal'];            		
				    resultRowTemplate.find("[data-name='grandTotal']").text(grandTotal);

					
					
            	    resultRowTemplate.data('cartItem', cartItemDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    cartItemListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForCartItem(customEventName)
{
	var cartItemId = document.getElementById('idValueForCartItem').value;
	var cartItem = {'cartItemId':cartItemId, 'customEventName':customEventName};
    var cartItemJsonData = {'paramsInfo':JSON.stringify(cartItem), 'objectType' : 'CartItem'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForCartItemExt(customEventName);
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
        data: cartItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var cartItem = responseData['cartItem'];
        		setCartItemInViewPage(cartItem);
            }
    		doAfterExecuteCustomAPIForCartItemExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnCartItem(e, paramsMap)
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
	var cartItemId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var cartItemDataObject  = rowObj.data('cartItem');
    	cartItemId = cartItemDataObject['cartItemId'];
	}
    else
	{
    	cartItemId = document.getElementById('idValueForCartItem').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var cartItemSearchParams = {'cartItemId':cartItemId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	cartItemSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var cartItemJsonData = {'paramsInfo':JSON.stringify(cartItemSearchParams),  'objectType' : 'CartItem'};
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
        data: cartItemJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var cartItem = responseData['cartItem'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="cartItemRowActionButtonDiv"]').hide();
            		if(cartItem.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = cartItem['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(cartItem['vwTxnStatus']);
            			selectedRowObj.find('[data-name="cartItemActionButton"]').text(cartItem["nextAction"]);
            			selectedRowObj.find('[data-name="cartItemActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setCartItemInViewPage(cartItem);
    			}
            }
        }
    });	
}
function downloadCartItemData()
{		
	var cartItem = {};
    var cartItemJsonData = {'objectType' : 'CartItem'};
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
        data: cartItemJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=CartItem");
            }
        }
    });
}
function uploadCartItemData(fileInfo)
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
	var cartItem = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var cartItemJsonData = {'paramsInfo':JSON.stringify(cartItem),  'objectType' : 'CartItem'};
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
        data: cartItemJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=CartItem");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForCartItem()
{
    var cartItemJsonData = {'objectType' : 'CartItem'};
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
        data: cartItemJsonData,
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



function doCartItemLengthValidation(cartItemDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(cartItemDataObject['userCartId'], 20))
	{
		alertMessage += "\n UserCartId length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(cartItemDataObject['productId'], 20))
	{
		alertMessage += "\n ProductId length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(cartItemDataObject['productQuantity'], 15))
	{
		alertMessage += "\n ProductQuantity length is more than allowed(15)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(cartItemDataObject['productUnitPrice'], 30))
	{
		alertMessage += "\n ProductUnitPrice length is more than allowed(30)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(cartItemDataObject['subTotalAmount'], 25))
	{
		alertMessage += "\n SubTotalAmount length is more than allowed(25)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(cartItemDataObject['grandTotal'], 25))
	{
		alertMessage += "\n GrandTotal length is more than allowed(25)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doCartItemManadatoryValidation(cartItemDataObject, paramsMap)
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
function doCartItemValidation(cartItemDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doCartItemLengthValidation(cartItemDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doCartItemManadatoryValidation(cartItemDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
