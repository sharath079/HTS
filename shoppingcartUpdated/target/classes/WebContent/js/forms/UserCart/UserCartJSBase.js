/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'CartCreationTime' : 'FormWBEntity:UserCart_cartCreationTime' 
 *	 * 'CartCheckoutTime' : 'FormWBEntity:UserCart_cartCheckoutTime' 
 *	
 */
var gInitParamsObjForUserCart = {};
var gUserCartRequestUnderProcess = 0;
function selectThisUserCartForEdit(userCartRowElement)
{
    var userCartDataObject  = $(userCartRowElement).data('userCart');
    var userCartList = $('[data-name="userCartSearchResults"]').find('[data-name="userCartRow"]');
	userCartList.data("is-selected", 0);	
	$(userCartRowElement).data("is-selected", 1);
	setUserCartInViewPage(userCartDataObject);
}

function setUserCartInViewPage(userCartDataObject, paramsMap)
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
	var userCartId = userCartDataObject['userCartId'];
	$('#'+prefix+'idValueForUserCart').val(userCartId);
		
	//TimeStamp
	if(userCartDataObject.hasOwnProperty('cartCreationTime'))
	{
		var cartCreationTime = userCartDataObject['cartCreationTime'];            		
		$('#'+prefix+'UserCart_cartCreationTime').val(cartCreationTime)
	}
	
	//TimeStamp
	if(userCartDataObject.hasOwnProperty('cartCheckoutTime'))
	{
		var cartCheckoutTime = userCartDataObject['cartCheckoutTime'];            		
		$('#'+prefix+'UserCart_cartCheckoutTime').val(cartCheckoutTime)
	}

	if(userCartDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = userCartDataObject['vwTxnStatus'];
		$('[data-name="userCartActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnUserCart(this)" >' +userCartDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="userCartActionButtonDiv"]').append(buttonElement);
		$('[data-name="userCartActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="userCartActionButtonDiv"]').hide();
	}
	$('[data-name="userCartCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="userCartViewFormButtonsDiv"]').css("display", "inline");
	//loadUserCartViewData(userCartDataObject);
	disbaleUserCartUpdateDisallowedFields(paramsMap);
	doAfterUserCartPanelRefreshed();
    
    
}
function disbaleUserCartUpdateDisallowedFields(paramsMap)
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
function loadUserCartViewData(userCartDataObject, paramsMap)
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
	var userCartId = userCartDataObject['userCartId'];
	$('#'+prefix+'idValueForUserCart').val(userCartId);
		
	var cartCreationTime = userCartDataObject['cartCreationTime'];            		
	parentElement.find('[data-name="'+prefix+'UserCart_cartCreationTime"]').text(cartCreationTime);
	
	var cartCheckoutTime = userCartDataObject['cartCheckoutTime'];            		
	parentElement.find('[data-name="'+prefix+'UserCart_cartCheckoutTime"]').text(cartCheckoutTime);

}
function ajaxDemoForUserCart()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForUserCart').textContent;	
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
			refreshPanelForUserCart();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForUserCart(msg)
{
	var executeCustomAPIButtonForUserCart = document.getElementById("FormWBEntity:executeCustomAPIButtonForUserCart");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:UserCart_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForUserCart.click();
}
function refreshPanelForUserCart()
{
	var demoRefreshButtonForUserCart = document.getElementById("FormWBEntity:demoRefreshButtonUserCart");
	demoRefreshButtonForUserCart.click();
}
function initializePanelOnLoadForCreateUserCart(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForUserCart = initParamsObj;
	initializeUserCartPage();	
	doAfterUserCartPanelRefreshed();
	initializeUserCartLookupFields(initParamsObj);
	doAfterPanelInitializedForUserCartExt();
	fetchUserCartDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="userCartCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForUserCart = 0;
function initializePanelOnLoadForSearchUserCart()
{
	initializeUserCartPage();	
	initializeUserCartLookupFields();
	doAfterPanelInitializedForUserCartExt();
	gLoadDashboardResultsForUserCart =1;
	//retrieveUserCartList();
}
function initializePanelOnLoadForViewUserCart(urlParams)
{
	initializeUserCartPage();	
	doAfterUserCartPanelRefreshed();
	initializeUserCartLookupFields(urlParams);
	doAfterPanelInitializedForUserCartExt();
	retrieveUserCart(urlParams);
	initDatePicker();
	$('[data-name="userCartViewFormButtonsDiv"]').css("display", "inline");
}
function initializeUserCartPage()
{
	initializePageOnload();	
	//initializeUserCartTemplateVariables();
}
function initializeUserCartTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="userCartSearchResults"]').find('[data-name="userCartRow"]');
	if(searchResultsRowObj.length > 0 && gUserCartSearchResultsTableRowTemplate.length == 0)
	{
		gUserCartSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveUserCart(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var userCartId = paramsMap['userCartId'];
	var userCart = {};
	userCart['userCartId'] = userCartId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				userCart[key] = paramsMap[key];
			}
	}
    var userCartJsonData = {'paramsInfo': JSON.stringify(userCart), 'objectType' : 'UserCart'};
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
        data: userCartJsonData,
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
                	var userCartDataObject = responseData['userCartDataObject'];
    				setUserCartInViewPage(userCartDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForUserCart()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchUserCartDefaultData();
}
function fetchUserCartDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'UserCart', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var userCart = responseData['userCart'];
            	document.getElementById('idValueForUserCart').value = '';
			    
            	setUserCartData(userCart);
            }
        }
    });	
}
function fetchUserCartTestData() 
{
	var userCart = getDataForUserCart();
    var searchJsonData = {'objectType' : 'UserCart', 'paramsInfo' : JSON.stringify(userCart)};
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
            	var userCart = responseData['userCart'];
            	document.getElementById('idValueForUserCart').value = '';
			    
            	setUserCartData(userCart);
            }
        }
    });	
}
function setUserCartData(userCartDataObject)
{
	var prefix = "";
		
	//TimeStamp
	if(userCartDataObject.hasOwnProperty('cartCreationTime'))
	{
		var cartCreationTime = userCartDataObject['cartCreationTime'];            		
		$('#'+prefix+'UserCart_cartCreationTime').val(cartCreationTime);
	}
	
	//TimeStamp
	if(userCartDataObject.hasOwnProperty('cartCheckoutTime'))
	{
		var cartCheckoutTime = userCartDataObject['cartCheckoutTime'];            		
		$('#'+prefix+'UserCart_cartCheckoutTime').val(cartCheckoutTime);
	}

	$('[data-name="userCartActionButtonDiv"]').hide();
}
function initializeUserCartLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="userCartSearchResultsDiv"]');
	
    
}

function doAfterUserCartPanelRefreshed()
{
    //doAfterPanelRefreshedForUserCartExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForUserCart(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForUserCartExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForUserCart(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForUserCartExt(fieldName)
}
function getEntityIdForUserCart()
{
	var idValue = document.getElementById('FormWBEntity:idValueForUserCart').textContent;	
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
function openPrintPageForUserCart()
{
	var entityId = getEntityIdForUserCart();
	if(entityId>0)
	{
	    window.open("/reports/generated/UserCart.jsp?userCartId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForUserCart(paramsMap)
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
	var userCart = {};
		
	//TimeStamp
	if($("#"+prefix+"UserCart_cartCreationTime").length == 1)
	{
		var cartCreationTime = $('#'+prefix+'UserCart_cartCreationTime').val();
		userCart['cartCreationTime'] = cartCreationTime;
	}
	
	//TimeStamp
	if($("#"+prefix+"UserCart_cartCheckoutTime").length == 1)
	{
		var cartCheckoutTime = $('#'+prefix+'UserCart_cartCheckoutTime').val();
		userCart['cartCheckoutTime'] = cartCheckoutTime;
	}

	
	return userCart;
}
function createUserCart(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var userCart = getDataForUserCart(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				userCart[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForUserCart)
	{
		paramsInfo[key] = gInitParamsObjForUserCart[key];
	}
	var validationObject = doUserCartValidation(userCart, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	userCart['additionalParamsInfo'] = paramsInfo;
    var userCartJsonData = {'paramsInfo': JSON.stringify(userCart), 'objectType' : 'UserCart'};
	var urlContextPath = "";//getContextPath();
	if(gUserCartRequestUnderProcess == 1)
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
            	gUserCartRequestUnderProcess = 0;
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
        data: userCartJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gUserCartRequestUnderProcess = 0;
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
            	var userCartId = responseData['userCartId'];
            	var popupElement = $('[data-name="UserCartPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var userCartDataObject = responseData['userCartDataObject'];
    				setUserCartInViewPage(userCartDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewUserCart.html?userCartId="+userCartId; 
        		}
				
            }
        }
    });
}
function resetTableForUserCart()
{
	var userCartListElement = $("[data-name='userCartList']");
	var previousDataRowList  = userCartListElement.find('[data-name="userCartRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForUserCart(paramsMap)
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
	$('#'+prefix+'idValueForUserCart').val('');
		
	//TimeStamp
	$('#'+prefix+'UserCart_cartCreationTime').val('');
	
	//TimeStamp
	$('#'+prefix+'UserCart_cartCheckoutTime').val('');

	$('[data-name="userCartCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="userCartViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="userCartActionButtonDiv"]').hide();
	enableUserCartUpdateDisallowedFields(paramsMap);
    
}
function enableUserCartUpdateDisallowedFields(paramsMap)
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
function updateUserCart(paramsMap)
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
	var userCart = getDataForUserCart(paramsMap)
	if($("#"+prefix+"idValueForUserCart").length == 1)
	{
		var userCartId = $("#"+prefix+"idValueForUserCart").val();
		userCart['userCartId'] = userCartId;
	}
	var validationObject = doUserCartValidation(userCart, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var userCartJsonData = {'paramsInfo': JSON.stringify(userCart), 'objectType' : 'UserCart'};
	var urlContextPath = "";//getContextPath();
	if(gUserCartRequestUnderProcess == 1)
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
                    	gUserCartRequestUnderProcess = 0;
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
        data: userCartJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gUserCartRequestUnderProcess = 0;
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
function deleteUserCart(paramsMap)
{		
	var userCartId = document.getElementById('idValueForUserCart').value;
	deleteSelectedUserCart(userCartId, paramsMap);
}
function deleteSelectedUserCart(userCartId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var userCart = {};
	userCart['userCartId'] = userCartId;	
    var userCartJsonData = {'paramsInfo': JSON.stringify(userCart), 'objectType' : 'UserCart'};
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
        data: userCartJsonData,
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
function loadSearchResultsForUserCart()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'UserCart'};
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
            	var userCartSearchResultsElement = $("[data-name='userCartSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var userCartDataObject = searchResultObjectsList[i];
					            		var cartCreationTime = userCartDataObject['cartCreationTime'];            		
            		var cartCheckoutTime = userCartDataObject['cartCheckoutTime'];            		

            		var resultRowTemnplate = $(gUserCartSearchResultsTableRowTemplate);
					            		var cartCreationTime = userCartDataObject['cartCreationTime'];            		
            	    resultRowTemnplate.find("[data-name='cartCreationTime']").text(cartCreationTime);
            		var cartCheckoutTime = userCartDataObject['cartCheckoutTime'];            		
            	    resultRowTemnplate.find("[data-name='cartCheckoutTime']").text(cartCheckoutTime);

            	    resultRowTemnplate.data('userCart', userCartDataObject);
            	    userCartSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gUserCartSearchResultsTableRowTemplate = ""; 
function openViewPageForUserCartForEdit(userCartLinkElement)
{
	var userCartRowElement = $(userCartLinkElement).parents('[data-name="userCartRow"]');
    var userCartDataObject  = userCartRowElement.data('userCart');
	var userCartId = userCartDataObject['userCartId'];
	var userCartPopupElement = $('[data-name="UserCartPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setUserCartInViewPage(userCartDataObject);
	    $("#UserCart-tab").trigger("click");
	}
	else if(userCartPopupElement.length > 0)
	{
	    setUserCartInViewPage(userCartDataObject);
		$('[data-name="UserCartPopup"]').find('[data-name="userCartCreateFormButtonsDiv"]').hide();
		$('[data-name="UserCartPopup"]').find('[data-name="userCartViewFormButtonsDiv"]').show();
	    showPopup('UserCartPopup', userCartLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewUserCart.html?userCartId="+userCartId;
		window.open(viewLink, "_blank"); 	
	}
}
function openUserCartCreatePageForNew(linkElement)
{
	var userCartPopupElement = $('[data-name="UserCartPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForUserCart();
	    $("#UserCart-tab").trigger("click");
    }
	else if(userCartPopupElement.length > 0)
	{
		resetFormForUserCart();
	    showPopup('UserCartPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateUserCart.html";
    }
}
function showUserCartPopupForEdit(userCartLinkElement)
{
	var userCartRowElement = $(userCartLinkElement).parents('[data-name="userCartRow"]');
    var userCartDataObject  = userCartRowElement.data('userCart');
    setUserCartInViewPage(userCartDataObject);
    showPopup('UserCartPopup', userCartLinkElement, 1);
    $("#UserCart-tab").trigger("click");
}
function showUserCartPopupForNew(buttonElement)
{
	resetFormForUserCart();
    showPopup('UserCartPopup', buttonElement, 1);
    $("#UserCart-tab").trigger("click");
}
function deleteThisUserCart(userCartLinkElement, paramsMap)
{
	var userCartRowElement = $(userCartLinkElement).parents('[data-name="userCartRow"]');
    var userCartDataObject  = userCartRowElement.data('userCart');
	var userCartId = userCartDataObject['userCartId'];
	deleteSelectedUserCart(userCartId, paramsMap);
}
function displayUserCartList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + userCartSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var userCartSearchResults = searchResultsDiv.find('[data-name="userCartSearchResults"]');
	//userCartSearchResults.find('[data-name="userCartRow"]').remove();
	var previousDataRowList  = userCartSearchResults.find('[data-name="userCartRow"]');
	userCartSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = userCartSearchResults.find('[data-name="userCartRow"]');
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
        var userCartDataObject = searchResultObjectsList[i];
        loadUserCartViewData(userCartDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = userCartDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='userCartVwTxnStatus']").text(vwTxnStatus);
		if(userCartDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="userCartActionButton"]').text(userCartDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="userCartActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="userCartActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="userCartRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="userCartRejectButton"]').show();
		}
	    resultRowTemplate.data('userCart', userCartDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    userCartSearchResults.append(resultRowTemplate);
	    processResultRowForUserCartExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForUserCart == 1)
	{
    	getDashboardResultsForUserCart();
	}
}
var userCartSearchResultsDivName = "userCartSearchResultsDiv";
var gUserCartSearchInputParamsList = [];
function retrieveUserCartList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + userCartSearchResultsDivName + '"]');
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
    fetchUserCartSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchUserCartSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getUserCartSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = userCartSearchResultsDivName; 
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
        'objectType': "UserCart"
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
            	this.showPreviousRecords = "showPreviousUserCartRecords";
            	this.showCurrentPageRecords = "showCurrentPageUserCartRecords";
            	this.showNextRecords = "showNextUserCartRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchUserCartResults";
                var userCartList = responseData['userCartList'];
                var currContextObj = this; 
                var successCallbackFunction = displayUserCartList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'userCartList', 'matchingSearchResultsCount', this.searchResultsDivName, 'userCartSearchResults', 'userCartRow', setUserCartSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getUserCartSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + userCartSearchResultsDivName + '"]');
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
				
		//TimeStamp
		if(searchDiv.find('[data-name="userCartDB_cartCreationTime"]').length == 1)
		{
		    var cartCreationTime = searchDiv.find('[data-name="userCartDB_cartCreationTime"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'cartCreationTime', 'userInputValue':cartCreationTime});
		}
		
		//TimeStamp
		if(searchDiv.find('[data-name="userCartDB_cartCheckoutTime"]').length == 1)
		{
		    var cartCheckoutTime = searchDiv.find('[data-name="userCartDB_cartCheckoutTime"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'cartCheckoutTime', 'userInputValue':cartCheckoutTime});
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
        gUserCartSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gUserCartSearchInputParamsList;
    }
    return searchInputParams;
}
function setUserCartSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + userCartSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gUserCartSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gUserCartSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageUserCartRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = userCartSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchUserCartSearchResultsList);
}
function showPreviousUserCartRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = userCartSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchUserCartSearchResultsList);
}
function showNextUserCartRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = userCartSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchUserCartSearchResultsList);
}
function showPrevOrNextSearchUserCartResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousUserCartRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextUserCartRecords(e);
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
function lookupRowSelectedForUserCart(attributeName, attributeId)
{
	var userCart = getDataForUserCart();
	userCart['attributeName'] = attributeName;
	userCart['attributeId'] = attributeId;
    var userCartJsonData = {'paramsInfo': JSON.stringify(userCart), 'objectType' : 'UserCart'};
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
        data: userCartJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userCart = responseData['userCart'];
            	setUserCartData(userCart);
            }
        }
    });	
}
function selectOptionChangedForUserCart(attributeName)
{
	var userCart = getDataForUserCart();
	userCart['attributeName'] = attributeName;
    var userCartJsonData = {'paramsInfo': JSON.stringify(userCart), 'objectType' : 'UserCart'};
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
        data: userCartJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userCart = responseData['userCart'];
            	setUserCartData(userCart);
            	doAfterUserCartPanelRefreshed();
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
function retrieveDependentUserCartList(paramsMap)
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
    var searchJsonData = {'objectType' : 'UserCart', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var userCartList = responseData['userCartList'];
            	var userCartListElement = $("[data-name='userCartList']");
            	var previousDataRowList  = userCartListElement.find('[data-name="userCartRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = userCartListElement.find('[data-name="userCartRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//userCartListElement.empty();
            	for(var i=0; i<userCartList.length; i++)
        		{
            		var userCartDataObject = userCartList[i];
            		//var resultRowTemplate = $(gUserCartSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var cartCreationTime = userCartDataObject['cartCreationTime'];            		
				    resultRowTemplate.find("[data-name='cartCreationTime']").text(cartCreationTime);
					var cartCheckoutTime = userCartDataObject['cartCheckoutTime'];            		
				    resultRowTemplate.find("[data-name='cartCheckoutTime']").text(cartCheckoutTime);

					
					
            	    resultRowTemplate.data('userCart', userCartDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    userCartListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForUserCart(customEventName)
{
	var userCartId = document.getElementById('idValueForUserCart').value;
	var userCart = {'userCartId':userCartId, 'customEventName':customEventName};
    var userCartJsonData = {'paramsInfo':JSON.stringify(userCart), 'objectType' : 'UserCart'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForUserCartExt(customEventName);
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
        data: userCartJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userCart = responseData['userCart'];
        		setUserCartInViewPage(userCart);
            }
    		doAfterExecuteCustomAPIForUserCartExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnUserCart(e, paramsMap)
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
	var userCartId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var userCartDataObject  = rowObj.data('userCart');
    	userCartId = userCartDataObject['userCartId'];
	}
    else
	{
    	userCartId = document.getElementById('idValueForUserCart').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var userCartSearchParams = {'userCartId':userCartId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	userCartSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var userCartJsonData = {'paramsInfo':JSON.stringify(userCartSearchParams),  'objectType' : 'UserCart'};
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
        data: userCartJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var userCart = responseData['userCart'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="userCartRowActionButtonDiv"]').hide();
            		if(userCart.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = userCart['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(userCart['vwTxnStatus']);
            			selectedRowObj.find('[data-name="userCartActionButton"]').text(userCart["nextAction"]);
            			selectedRowObj.find('[data-name="userCartActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setUserCartInViewPage(userCart);
    			}
            }
        }
    });	
}
function downloadUserCartData()
{		
	var userCart = {};
    var userCartJsonData = {'objectType' : 'UserCart'};
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
        data: userCartJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=UserCart");
            }
        }
    });
}
function uploadUserCartData(fileInfo)
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
	var userCart = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var userCartJsonData = {'paramsInfo':JSON.stringify(userCart),  'objectType' : 'UserCart'};
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
        data: userCartJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=UserCart");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForUserCart()
{
    var userCartJsonData = {'objectType' : 'UserCart'};
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
        data: userCartJsonData,
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



function doUserCartLengthValidation(userCartDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(userCartDataObject['cartCreationTime'], 20))
	{
		alertMessage += "\n CartCreationTime length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(userCartDataObject['cartCheckoutTime'], 20))
	{
		alertMessage += "\n CartCheckoutTime length is more than allowed(20)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doUserCartManadatoryValidation(userCartDataObject, paramsMap)
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
function doUserCartValidation(userCartDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doUserCartLengthValidation(userCartDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doUserCartManadatoryValidation(userCartDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
