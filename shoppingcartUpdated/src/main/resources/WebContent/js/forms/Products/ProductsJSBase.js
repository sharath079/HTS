/*
 * Custom javascript code goes here for handling business rules.
 */
/*
 * Entity attribute names and corresponding ids generated
 *	 * 'ProductName' : 'FormWBEntity:Products_productName' 
 *	 * 'ProductDescription' : 'FormWBEntity:Products_productDescription' 
 *	 * 'ProductPrice' : 'FormWBEntity:Products_productPrice' 
 *	 * 'ProductUnitType' : 'FormWBEntity:Products_productUnitType' 
 *	
 */
var gInitParamsObjForProducts = {};
var gProductsRequestUnderProcess = 0;
function selectThisProductsForEdit(productsRowElement)
{
    var productsDataObject  = $(productsRowElement).data('products');
    var productsList = $('[data-name="productsSearchResults"]').find('[data-name="productsRow"]');
	productsList.data("is-selected", 0);	
	$(productsRowElement).data("is-selected", 1);
	setProductsInViewPage(productsDataObject);
}

function setProductsInViewPage(productsDataObject, paramsMap)
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
	var productsId = productsDataObject['productsId'];
	$('#'+prefix+'idValueForProducts').val(productsId);
		
	//Input
	if(productsDataObject.hasOwnProperty('productName'))
	{
		var productName = productsDataObject['productName'];            		
		$('#'+prefix+'Products_productName').val(productName);
	}
	
	//Input
	if(productsDataObject.hasOwnProperty('productDescription'))
	{
		var productDescription = productsDataObject['productDescription'];            		
		$('#'+prefix+'Products_productDescription').val(productDescription);
	}
	
	//Input
	if(productsDataObject.hasOwnProperty('productPrice'))
	{
		var productPrice = productsDataObject['productPrice'];            		
		$('#'+prefix+'Products_productPrice').val(productPrice);
	}
	
	//Input
	if(productsDataObject.hasOwnProperty('productUnitType'))
	{
		var productUnitType = productsDataObject['productUnitType'];            		
		$('#'+prefix+'Products_productUnitType').val(productUnitType);
	}

	if(productsDataObject.hasOwnProperty('nextAction'))
	{
		var vwTxnStatus = productsDataObject['vwTxnStatus'];
		$('[data-name="productsActionButtonDiv"]').empty();
		var buttonElement = $('<button type="submit" class="btn btn-outline-primary   btn-xs  text-sm" onclick="executeAuthorisationOnProducts(this)" >' +productsDataObject["nextAction"]+ '</button>');
		buttonElement.data("vw-txn-status", vwTxnStatus);
		$('[data-name="productsActionButtonDiv"]').append(buttonElement);
		$('[data-name="productsActionButtonDiv"]').show();
	}
	else
	{
		$('[data-name="productsActionButtonDiv"]').hide();
	}
	$('[data-name="productsCreateFormButtonsDiv"]').css("display", "none");
	$('[data-name="productsViewFormButtonsDiv"]').css("display", "inline");
	//loadProductsViewData(productsDataObject);
	disbaleProductsUpdateDisallowedFields(paramsMap);
	doAfterProductsPanelRefreshed();
    
    
}
function disbaleProductsUpdateDisallowedFields(paramsMap)
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
function loadProductsViewData(productsDataObject, paramsMap)
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
	var productsId = productsDataObject['productsId'];
	$('#'+prefix+'idValueForProducts').val(productsId);
		
	if(productsDataObject.hasOwnProperty('productName'))
	{
		var productName = productsDataObject['productName'];            		
		parentElement.find('[data-name="'+prefix+'Products_productName"]').text(productName);
	}
	
	if(productsDataObject.hasOwnProperty('productDescription'))
	{
		var productDescription = productsDataObject['productDescription'];            		
		parentElement.find('[data-name="'+prefix+'Products_productDescription"]').text(productDescription);
	}
	
	if(productsDataObject.hasOwnProperty('productPrice'))
	{
		var productPrice = productsDataObject['productPrice'];            		
		parentElement.find('[data-name="'+prefix+'Products_productPrice"]').text(productPrice);
	}
	
	if(productsDataObject.hasOwnProperty('productUnitType'))
	{
		var productUnitType = productsDataObject['productUnitType'];            		
		parentElement.find('[data-name="'+prefix+'Products_productUnitType"]').text(productUnitType);
	}

}
function ajaxDemoForProducts()
{
	var urlContextPath = "";//getContextPath();
	alert('ajax demo button clicked !!');
	var idValue = document.getElementById('FormWBEntity:idValueForProducts').textContent;	
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
			refreshPanelForProducts();
			alert('Panel refreshed !!');
		},
		error : function(responseText) {
			alert(responseText);
		}
	});	
}
function executeCustomAPIForProducts(msg)
{
	var executeCustomAPIButtonForProducts = document.getElementById("FormWBEntity:executeCustomAPIButtonForProducts");	
	var customAPIMessageElement = document.getElementById("FormWBEntity:Products_vwCustomAPIMessage");	
	customAPIMessageElement.value = msg;	
	executeCustomAPIButtonForProducts.click();
}
function refreshPanelForProducts()
{
	var demoRefreshButtonForProducts = document.getElementById("FormWBEntity:demoRefreshButtonProducts");
	demoRefreshButtonForProducts.click();
}
function initializePanelOnLoadForCreateProducts(initParamsObj)
{
	if(!initParamsObj)
	{
		initParamsObj = getQueryParams();	
	}
	gInitParamsObjForProducts = initParamsObj;
	initializeProductsPage();	
	doAfterProductsPanelRefreshed();
	initializeProductsLookupFields(initParamsObj);
	doAfterPanelInitializedForProductsExt();
	fetchProductsDefaultData(initParamsObj);
	initDatePicker();
	$('[data-name="productsCreateFormButtonsDiv"]').css("display", "inline");
}
var gLoadDashboardResultsForProducts = 0;
function initializePanelOnLoadForSearchProducts()
{
	initializeProductsPage();	
	initializeProductsLookupFields();
	doAfterPanelInitializedForProductsExt();
	gLoadDashboardResultsForProducts =1;
	//retrieveProductsList();
}
function initializePanelOnLoadForViewProducts(urlParams)
{
	initializeProductsPage();	
	doAfterProductsPanelRefreshed();
	initializeProductsLookupFields(urlParams);
	doAfterPanelInitializedForProductsExt();
	retrieveProducts(urlParams);
	initDatePicker();
	$('[data-name="productsViewFormButtonsDiv"]').css("display", "inline");
}
function initializeProductsPage()
{
	initializePageOnload();	
	//initializeProductsTemplateVariables();
}
function initializeProductsTemplateVariables()
{	
	
	var searchResultsRowObj = $('[data-name="productsSearchResults"]').find('[data-name="productsRow"]');
	if(searchResultsRowObj.length > 0 && gProductsSearchResultsTableRowTemplate.length == 0)
	{
		gProductsSearchResultsTableRowTemplate = searchResultsRowObj[0].outerHTML;
		//searchResultsRowObj.remove();
	}
	
	
	
}
function retrieveProducts(paramsMap)
{		
	if(!paramsMap)
	{
		paramsMap = getQueryParams();
	}
	var productsId = paramsMap['productsId'];
	var products = {};
	products['productsId'] = productsId;	
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				products[key] = paramsMap[key];
			}
	}
    var productsJsonData = {'paramsInfo': JSON.stringify(products), 'objectType' : 'Products'};
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
        data: productsJsonData,
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
                	var productsDataObject = responseData['productsDataObject'];
    				setProductsInViewPage(productsDataObject, this.paramsMap);            
                }
        	}
        }
    });
}
function initializeNewObjectForProducts()
{
    var proceed = confirm("Do you really want a New Page?");
    if (proceed == false)
    {
        return;
    }
    fetchProductsDefaultData();
}
function fetchProductsDefaultData(initParamsObj) 
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
    var searchJsonData = {'objectType' : 'Products', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var products = responseData['products'];
            	document.getElementById('idValueForProducts').value = '';
			    
            	setProductsData(products);
            }
        }
    });	
}
function fetchProductsTestData() 
{
	var products = getDataForProducts();
    var searchJsonData = {'objectType' : 'Products', 'paramsInfo' : JSON.stringify(products)};
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
            	var products = responseData['products'];
            	document.getElementById('idValueForProducts').value = '';
			    
            	setProductsData(products);
            }
        }
    });	
}
function setProductsData(productsDataObject)
{
	var prefix = "";
		
	//Input
	if(productsDataObject.hasOwnProperty('productName'))
	{
		var productName = productsDataObject['productName'];            		
		$('#'+prefix+'Products_productName').val(productName);
	}
	
	//Input
	if(productsDataObject.hasOwnProperty('productDescription'))
	{
		var productDescription = productsDataObject['productDescription'];            		
		$('#'+prefix+'Products_productDescription').val(productDescription);
	}
	
	//Input
	if(productsDataObject.hasOwnProperty('productPrice'))
	{
		var productPrice = productsDataObject['productPrice'];            		
		$('#'+prefix+'Products_productPrice').val(productPrice);
	}
	
	//Input
	if(productsDataObject.hasOwnProperty('productUnitType'))
	{
		var productUnitType = productsDataObject['productUnitType'];            		
		$('#'+prefix+'Products_productUnitType').val(productUnitType);
	}

	$('[data-name="productsActionButtonDiv"]').hide();
}
function initializeProductsLookupFields(paramsMap) 
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
    
    var searchDiv = $('[data-name="productsSearchResultsDiv"]');
	
    
}

function doAfterProductsPanelRefreshed()
{
    //doAfterPanelRefreshedForProductsExt();
	    
}
/*
 * This function is called after completion of the 
 * ajax request raised for select option fields
 */
function doAfterSelectOptionChangedForProducts(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterSelectOptionChangedForProductsExt(fieldName);
}
/*
 * This function is called after completion of the 
 * ajax request raised after selecting lookup row for 
 * any of the fields
 */
function doAfterLookupRowChangedForProducts(fieldName)
{
	if(1>2)
	{
	}
	
	doAfterLookupRowChangedForProductsExt(fieldName)
}
function getEntityIdForProducts()
{
	var idValue = document.getElementById('FormWBEntity:idValueForProducts').textContent;	
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
function openPrintPageForProducts()
{
	var entityId = getEntityIdForProducts();
	if(entityId>0)
	{
	    window.open("/reports/generated/Products.jsp?productsId=" + entityId, '_blank');		
	}
	else
	{
		alert('Select a record to print !!');
	}
}



function getDataForProducts(paramsMap)
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
	var products = {};
		
	//Input
	if($("#"+prefix+"Products_productName").length == 1)
	{
		var productName = $('#'+prefix+'Products_productName').val();
		products['productName'] = productName;
	}
	
	//Input
	if($("#"+prefix+"Products_productDescription").length == 1)
	{
		var productDescription = $('#'+prefix+'Products_productDescription').val();
		products['productDescription'] = productDescription;
	}
	
	//Input
	if($("#"+prefix+"Products_productPrice").length == 1)
	{
		var productPrice = $('#'+prefix+'Products_productPrice').val();
		products['productPrice'] = productPrice;
	}
	
	//Input
	if($("#"+prefix+"Products_productUnitType").length == 1)
	{
		var productUnitType = $('#'+prefix+'Products_productUnitType').val();
		products['productUnitType'] = productUnitType;
	}

	
	return products;
}
function createProducts(paramsMap)
{	
    if(!paramsMap)
	{
    	paramsMap = {};
	}
    var paramsInfo = {};
	var products = getDataForProducts(paramsMap)
	for (var key in paramsMap)
	{
		if(key != "errorCallbackFunction"
			&& key != "successCallbackFunction")
			{
				paramsInfo[key] = paramsMap[key];
				products[key] = paramsMap[key];
			}
	}
	for (var key in gInitParamsObjForProducts)
	{
		paramsInfo[key] = gInitParamsObjForProducts[key];
	}
	var validationObject = doProductsValidation(products, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
	products['additionalParamsInfo'] = paramsInfo;
    var productsJsonData = {'paramsInfo': JSON.stringify(products), 'objectType' : 'Products'};
	var urlContextPath = "";//getContextPath();
	if(gProductsRequestUnderProcess == 1)
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
            	gProductsRequestUnderProcess = 0;
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
        data: productsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
            {
            	gProductsRequestUnderProcess = 0;
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
            	var productsId = responseData['productsId'];
            	var popupElement = $('[data-name="ProductsPopup"]');
            	
            	if(gLayoutType == "XACADProTabbedLinear"
            		|| gLayoutType == "XACADProTabbed")
            	{
                	var productsDataObject = responseData['productsDataObject'];
    				setProductsInViewPage(productsDataObject);            
            	}
            	else if(popupElement.length > 0)
            	{
            		popupElement.hide();
            		var lastSelectedElement = popupElement.data("last-selected-element");
            		lastSelectedElement.focus();
            	}
            	else
        		{
	            	location.href = "/entity/ViewProducts.html?productsId="+productsId; 
        		}
				
            }
        }
    });
}
function resetTableForProducts()
{
	var productsListElement = $("[data-name='productsList']");
	var previousDataRowList  = productsListElement.find('[data-name="productsRow"]');
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
}
function resetFormForProducts(paramsMap)
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
	$('#'+prefix+'idValueForProducts').val('');
		
	//Input
	$('#'+prefix+'Products_productName').val('');
	
	//Input
	$('#'+prefix+'Products_productDescription').val('');
	
	//Input
	$('#'+prefix+'Products_productPrice').val('');
	
	//Input
	$('#'+prefix+'Products_productUnitType').val('');

	$('[data-name="productsCreateFormButtonsDiv"]').css("display", "inline");
	$('[data-name="productsViewFormButtonsDiv"]').css("display", "none");
	$('[data-name="productsActionButtonDiv"]').hide();
	enableProductsUpdateDisallowedFields(paramsMap);
    
}
function enableProductsUpdateDisallowedFields(paramsMap)
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
function updateProducts(paramsMap)
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
	var products = getDataForProducts(paramsMap)
	if($("#"+prefix+"idValueForProducts").length == 1)
	{
		var productsId = $("#"+prefix+"idValueForProducts").val();
		products['productsId'] = productsId;
	}
	var validationObject = doProductsValidation(products, paramsMap);
	if(validationObject['validationPassed'] == false)
	{
        showAlert(validationObject['alertMessage']);
        return;
	}
    var productsJsonData = {'paramsInfo': JSON.stringify(products), 'objectType' : 'Products'};
	var urlContextPath = "";//getContextPath();
	if(gProductsRequestUnderProcess == 1)
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
                    	gProductsRequestUnderProcess = 0;
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
        data: productsJsonData,
        success: function (responseData)
        {
            closePopUp();
            setTimeout(function ()
                    {
                    	gProductsRequestUnderProcess = 0;
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
function deleteProducts(paramsMap)
{		
	var productsId = document.getElementById('idValueForProducts').value;
	deleteSelectedProducts(productsId, paramsMap);
}
function deleteSelectedProducts(productsId, paramsMap)
{		
    if(!paramsMap)
	{
    	paramsMap = {};
	}
	var products = {};
	products['productsId'] = productsId;	
    var productsJsonData = {'paramsInfo': JSON.stringify(products), 'objectType' : 'Products'};
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
        data: productsJsonData,
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
function loadSearchResultsForProducts()
{
    var searchJsonData = {'requestType' : 'getSearchResults', 'objectType' : 'Products'};
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
            	var productsSearchResultsElement = $("[data-name='productsSearchResults']"); 
            	for(var i=0; i<searchResultObjectsList.length; i++)
        		{
            		var productsDataObject = searchResultObjectsList[i];
					            		var productName = productsDataObject['productName'];            		
            		var productDescription = productsDataObject['productDescription'];            		
            		var productPrice = productsDataObject['productPrice'];            		
            		var productUnitType = productsDataObject['productUnitType'];            		

            		var resultRowTemnplate = $(gProductsSearchResultsTableRowTemplate);
					            		var productName = productsDataObject['productName'];            		
            	    resultRowTemnplate.find("[data-name='productName']").text(productName);
            		var productDescription = productsDataObject['productDescription'];            		
            	    resultRowTemnplate.find("[data-name='productDescription']").text(productDescription);
            		var productPrice = productsDataObject['productPrice'];            		
            	    resultRowTemnplate.find("[data-name='productPrice']").text(productPrice);
            		var productUnitType = productsDataObject['productUnitType'];            		
            	    resultRowTemnplate.find("[data-name='productUnitType']").text(productUnitType);

            	    resultRowTemnplate.data('products', productsDataObject);
            	    productsSearchResultsElement.append(resultRowTemnplate);            	    
        		}
            }
        }
    });
}
var gProductsSearchResultsTableRowTemplate = ""; 
function openViewPageForProductsForEdit(productsLinkElement)
{
	var productsRowElement = $(productsLinkElement).parents('[data-name="productsRow"]');
    var productsDataObject  = productsRowElement.data('products');
	var productsId = productsDataObject['productsId'];
	var productsPopupElement = $('[data-name="ProductsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
	    setProductsInViewPage(productsDataObject);
	    $("#Products-tab").trigger("click");
	}
	else if(productsPopupElement.length > 0)
	{
	    setProductsInViewPage(productsDataObject);
		$('[data-name="ProductsPopup"]').find('[data-name="productsCreateFormButtonsDiv"]').hide();
		$('[data-name="ProductsPopup"]').find('[data-name="productsViewFormButtonsDiv"]').show();
	    showPopup('ProductsPopup', productsLinkElement, 1);
	}
	else
	{
		var viewLink = "/entity/ViewProducts.html?productsId="+productsId;
		window.open(viewLink, "_blank"); 	
	}
}
function openProductsCreatePageForNew(linkElement)
{
	var productsPopupElement = $('[data-name="ProductsPopup"]');
	if(gLayoutType == "XACADProTabbedLinear"
		|| gLayoutType == "XACADProTabbed")
	{
		resetFormForProducts();
	    $("#Products-tab").trigger("click");
    }
	else if(productsPopupElement.length > 0)
	{
		resetFormForProducts();
	    showPopup('ProductsPopup', linkElement, 1);
	}
    else
    {
		location.href = "/entity/CreateProducts.html";
    }
}
function showProductsPopupForEdit(productsLinkElement)
{
	var productsRowElement = $(productsLinkElement).parents('[data-name="productsRow"]');
    var productsDataObject  = productsRowElement.data('products');
    setProductsInViewPage(productsDataObject);
    showPopup('ProductsPopup', productsLinkElement, 1);
    $("#Products-tab").trigger("click");
}
function showProductsPopupForNew(buttonElement)
{
	resetFormForProducts();
    showPopup('ProductsPopup', buttonElement, 1);
    $("#Products-tab").trigger("click");
}
function deleteThisProducts(productsLinkElement, paramsMap)
{
	var productsRowElement = $(productsLinkElement).parents('[data-name="productsRow"]');
    var productsDataObject  = productsRowElement.data('products');
	var productsId = productsDataObject['productsId'];
	deleteSelectedProducts(productsId, paramsMap);
}
function displayProductsList(searchResultObjectsList, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + productsSearchResultsDivName + '"]');
    if(searchResultsDivName)
	{
        searchResultsDiv = $('[data-name="' + searchResultsDivName + '"]');
	}
    var productsSearchResults = searchResultsDiv.find('[data-name="productsSearchResults"]');
	//productsSearchResults.find('[data-name="productsRow"]').remove();
	var previousDataRowList  = productsSearchResults.find('[data-name="productsRow"]');
	productsSearchResults.show();
	for(var i =0; i < previousDataRowList.length; i++)
	{
		var rowObj = $(previousDataRowList[i]);
		if(rowObj.data("is-data-row") == "1")
		{
			rowObj.remove();
		}
	}
	var searchResultsTableRowTemplateObj = productsSearchResults.find('[data-name="productsRow"]');
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
        var productsDataObject = searchResultObjectsList[i];
        loadProductsViewData(productsDataObject, {'parentElement':resultRowTemplate});
	    resultRowTemplate.find("[data-name='recordNo']").text(currentPageStartingRecordNo++);
	    
		var vwTxnStatus = productsDataObject['vwTxnStatus'];
	    resultRowTemplate.find("[data-name='productsVwTxnStatus']").text(vwTxnStatus);
		if(productsDataObject.hasOwnProperty('nextAction'))
		{
			resultRowTemplate.find('[data-name="productsActionButton"]').text(productsDataObject["nextAction"]);
			resultRowTemplate.find('[data-name="productsActionButton"]').data("vw-txn-status", vwTxnStatus);
		}
		else
		{
			resultRowTemplate.find('[data-name="productsActionButton"]').hide();
		}
		resultRowTemplate.find('[data-name="productsRejectButton"]').data("vw-txn-status", vwTxnStatus);
		if(vwTxnStatus == 'CREATED' || vwTxnStatus == 'MODIFIED')
		{
			resultRowTemplate.find('[data-name="productsRejectButton"]').show();
		}
	    resultRowTemplate.data('products', productsDataObject);
		resultRowTemplate.data("is-selected", 0);
		resultRowTemplate.show();
		resultRowTemplate.data("is-data-row", "1");
	    productsSearchResults.append(resultRowTemplate);
	    processResultRowForProductsExt(resultRowTemplate);
    }
    if(gLoadDashboardResultsForProducts == 1)
	{
    	getDashboardResultsForProducts();
	}
}
var productsSearchResultsDivName = "productsSearchResultsDiv";
var gProductsSearchInputParamsList = [];
function retrieveProductsList(customParamsMap, searchResultsDivName)
{
	if(!customParamsMap)
	{
		customParamsMap = {};
	}
    var searchResultsDiv = $('[data-name="' + productsSearchResultsDivName + '"]');
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
    fetchProductsSearchResultsList(1, noOfRecordsAlreadyFetched, noOfRecordsToFetch, 1, 1, customParamsMap, searchResultsDivName);
}
function fetchProductsSearchResultsList(nextCurrentPageIndex, noOfRecordsAlreadyFetched, noOfRecordsToFetch, refreshIndex, refreshStartIndex, customParamsMap, searchResultsDivName)
{
	var urlContextPath = "";//getContextPath();
    var searchInputParamsList = getProductsSearchInputParams(customParamsMap, searchResultsDivName);
	if(!searchResultsDivName)
	{
		searchResultsDivName = productsSearchResultsDivName; 
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
        'objectType': "Products"
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
            	this.showPreviousRecords = "showPreviousProductsRecords";
            	this.showCurrentPageRecords = "showCurrentPageProductsRecords";
            	this.showNextRecords = "showNextProductsRecords";
            	this.showPrevOrNextSearchResults = "showPrevOrNextSearchProductsResults";
                var productsList = responseData['productsList'];
                var currContextObj = this; 
                var successCallbackFunction = displayProductsList; 
                if(currContextObj.hasOwnProperty('successCallbackFunction'))
            	{
                	successCallbackFunction = currContextObj['successCallbackFunction'];
            	}
        		handleSearchResultsResponse(this, responseData, 'productsList', 'matchingSearchResultsCount', this.searchResultsDivName, 'productsSearchResults', 'productsRow', setProductsSearchInputParams, successCallbackFunction);
            }
        }
    });
}
function getProductsSearchInputParams(customParamsMap, searchResultsDivName)
{
    var searchResultsDiv = $('[data-name="' + productsSearchResultsDivName + '"]');
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
		if(searchDiv.find('[data-name="productsDB_productName"]').length == 1)
		{
		    var productName = searchDiv.find('[data-name="productsDB_productName"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productName', 'userInputValue':productName});
		}
		
		//Input
		if(searchDiv.find('[data-name="productsDB_productDescription"]').length == 1)
		{
		    var productDescription = searchDiv.find('[data-name="productsDB_productDescription"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productDescription', 'userInputValue':productDescription});
		}
		
		//Input
		if(searchDiv.find('[data-name="productsDB_productPrice"]').length == 1)
		{
		    var productPrice = searchDiv.find('[data-name="productsDB_productPrice"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productPrice', 'userInputValue':productPrice});
		}
		
		//Input
		if(searchDiv.find('[data-name="productsDB_productUnitType"]').length == 1)
		{
		    var productUnitType = searchDiv.find('[data-name="productsDB_productUnitType"]').val();
		    parameterNameAndValuesList.push({ 'parameterName':'productUnitType', 'userInputValue':productUnitType});
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
        gProductsSearchInputParamsList = parameterNameAndValuesList;
        searchInputParams = parameterNameAndValuesList;
    }
    else
    {        
        searchInputParams = gProductsSearchInputParamsList;
    }
    return searchInputParams;
}
function setProductsSearchInputParams(contextObject)
{
    var searchResultsDiv = $('[data-name="' + productsSearchResultsDivName + '"]');
    var isSearched = searchResultsDiv.data("is-searched");
    if (isSearched == 0)
    {
        for (var i = 0; i < gProductsSearchInputParamsList.length; i++)
        {
            var searchInputParamsInfo = gProductsSearchInputParamsList[i];
            var parameterName = searchInputParamsInfo.parameterName;
            var userInputValue = searchInputParamsInfo.userInputValue;
            searchResultsDiv.data(parameterName, contextObject.parameterName);
        }
    }
}
function showCurrentPageProductsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = productsSearchResultsDivName;
	}
    getCurrentPageSearchResults(e, searchResultsDivName, fetchProductsSearchResultsList);
}
function showPreviousProductsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = productsSearchResultsDivName;
	}
    getPreviousPageSearchResults(searchResultsDivName, fetchProductsSearchResultsList);
}
function showNextProductsRecords(e)
{
	var searchResultsDivName = $(e).parents('[data-name="recordCountAndPaginationBlock"]').data("search-results-div-name");
	if(!searchResultsDivName)
	{
		searchResultsDivName = productsSearchResultsDivName;
	}
    getNextPageSearchResults(searchResultsDivName, fetchProductsSearchResultsList);
}
function showPrevOrNextSearchProductsResults(event, e)
{
    stopEventPropagation(event);
    if (event.keyCode == 37)
    {
        showPreviousProductsRecords(e);
    }
    else if (event.keyCode == 39)
    {
        showNextProductsRecords(e);
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
function lookupRowSelectedForProducts(attributeName, attributeId)
{
	var products = getDataForProducts();
	products['attributeName'] = attributeName;
	products['attributeId'] = attributeId;
    var productsJsonData = {'paramsInfo': JSON.stringify(products), 'objectType' : 'Products'};
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
        data: productsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var products = responseData['products'];
            	setProductsData(products);
            }
        }
    });	
}
function selectOptionChangedForProducts(attributeName)
{
	var products = getDataForProducts();
	products['attributeName'] = attributeName;
    var productsJsonData = {'paramsInfo': JSON.stringify(products), 'objectType' : 'Products'};
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
        data: productsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var products = responseData['products'];
            	setProductsData(products);
            	doAfterProductsPanelRefreshed();
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
function retrieveDependentProductsList(paramsMap)
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
    var searchJsonData = {'objectType' : 'Products', 'paramsInfo': JSON.stringify(paramsInfo)};
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
            	var productsList = responseData['productsList'];
            	var productsListElement = $("[data-name='productsList']");
            	var previousDataRowList  = productsListElement.find('[data-name="productsRow"]');
            	for(var i =0; i < previousDataRowList.length; i++)
            	{
            		var rowObj = $(previousDataRowList[i]);
            		if(rowObj.data("is-data-row") == "1")
            		{
            			rowObj.remove();
            		}
            	}
            	var resultsTableRowTemplateObj = productsListElement.find('[data-name="productsRow"]');
            	if(resultsTableRowTemplateObj.length == 0)
        		{
            		return;
        		}
            	var resultsTableRowTemplate = resultsTableRowTemplateObj[0].outerHTML;
            	//productsListElement.empty();
            	for(var i=0; i<productsList.length; i++)
        		{
            		var productsDataObject = productsList[i];
            		//var resultRowTemplate = $(gProductsSearchResultsTableRowTemplate);
            		var resultRowTemplate = $(resultsTableRowTemplate);
										var productName = productsDataObject['productName'];            		
				    resultRowTemplate.find("[data-name='productName']").text(productName);
					var productDescription = productsDataObject['productDescription'];            		
				    resultRowTemplate.find("[data-name='productDescription']").text(productDescription);
					var productPrice = productsDataObject['productPrice'];            		
				    resultRowTemplate.find("[data-name='productPrice']").text(productPrice);
					var productUnitType = productsDataObject['productUnitType'];            		
				    resultRowTemplate.find("[data-name='productUnitType']").text(productUnitType);

					
					
            	    resultRowTemplate.data('products', productsDataObject);
            	    resultRowTemplate.data("is-data-row", 1);
            	    resultRowTemplate.show();
            	    productsListElement.append(resultRowTemplate);            	    
        		}
            }
        }
    });
}
function doExecuteCustomAPIForProducts(customEventName)
{
	var productsId = document.getElementById('idValueForProducts').value;
	var products = {'productsId':productsId, 'customEventName':customEventName};
    var productsJsonData = {'paramsInfo':JSON.stringify(products), 'objectType' : 'Products'};
	var urlContextPath = "";//getContextPath();
	doBeforeExecuteCustomAPIForProductsExt(customEventName);
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
        data: productsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var products = responseData['products'];
        		setProductsInViewPage(products);
            }
    		doAfterExecuteCustomAPIForProductsExt(responseData, this.customEventName);
        }
    });	
}
function executeAuthorisationOnProducts(e, paramsMap)
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
	var productsId = -1;
	var rowObj;
    if(isSearchResult == 1)
	{
    	rowObj = $(e).parents('tr');
	    var productsDataObject  = rowObj.data('products');
    	productsId = productsDataObject['productsId'];
	}
    else
	{
    	productsId = document.getElementById('idValueForProducts').value;
	}
	var currentStatus = $(e).data("vw-txn-status");
	var productsSearchParams = {'productsId':productsId, 'currentStatus':currentStatus};
	//below code for Reject Functionality
    if(paramsMap.hasOwnProperty("currentAction"))
	{
    	productsSearchParams['currentAction'] = paramsMap['currentAction'];
	}
    var productsJsonData = {'paramsInfo':JSON.stringify(productsSearchParams),  'objectType' : 'Products'};
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
        data: productsJsonData,
        success: function (responseData)
        {
            closePopUp();
            if (responseData['alert'])
            {
                showAlert(responseData['alert']);
            }
            if (responseData['success'] == 1)
            {
            	var products = responseData['products'];
        		if(this.isSearchResult == 1)
    			{
        			var selectedRowObj = this.rowObj;
        			//selectedRowObj.find('[data-name="productsRowActionButtonDiv"]').hide();
            		if(products.hasOwnProperty('nextAction'))
            		{
            			var vwTxnStatus = products['vwTxnStatus'];
            			selectedRowObj.find("[data-name='messageQueueVwTxnStatus']").text(products['vwTxnStatus']);
            			selectedRowObj.find('[data-name="productsActionButton"]').text(products["nextAction"]);
            			selectedRowObj.find('[data-name="productsActionButton"]').data("vw-txn-status", vwTxnStatus);
            		}
    			}
        		else
    			{
            		setProductsInViewPage(products);
    			}
            }
        }
    });	
}
function downloadProductsData()
{		
	var products = {};
    var productsJsonData = {'objectType' : 'Products'};
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
        data: productsJsonData,
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
    			window.open("/download?fileId=" + fileId+"&objectType=Products");
            }
        }
    });
}
function uploadProductsData(fileInfo)
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
	var products = {'fileId':fileId, 'areSourceDestinationSame':areSourceDestinationSameVal, 'inputFilesZip':inputFilesZip};
    var productsJsonData = {'paramsInfo':JSON.stringify(products),  'objectType' : 'Products'};
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
        data: productsJsonData,
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
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').attr("href", "/download?fileId=" + fileId+"&objectType=Products");
            	$('[data-name="fileUploadPopup"]').find('[data-name="uploadResultsLink"]').show();
            }
        }
    });	
}

function getDashboardResultsForProducts()
{
    var productsJsonData = {'objectType' : 'Products'};
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
        data: productsJsonData,
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



function doProductsLengthValidation(productsDataObject)
{
    var alertMessage = "";
    var validationPassed = true;
		
	if(!isFieldLengthAllowed(productsDataObject['productName'], 50))
	{
		alertMessage += "\n ProductName length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(productsDataObject['productDescription'], 50))
	{
		alertMessage += "\n ProductDescription length is more than allowed(50)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(productsDataObject['productPrice'], 20))
	{
		alertMessage += "\n ProductPrice length is more than allowed(20)";
	    validationPassed = false;
	}
	
	if(!isFieldLengthAllowed(productsDataObject['productUnitType'], 50))
	{
		alertMessage += "\n ProductUnitType length is more than allowed(50)";
	    validationPassed = false;
	}

	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
function doProductsManadatoryValidation(productsDataObject, paramsMap)
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
function doProductsValidation(productsDataObject, paramsMap)
{
	var alertMessage = "";
	var validationPassed = true;
	var lengthValidationResponse =  doProductsLengthValidation(productsDataObject);
	var lengthValidationPassed = lengthValidationResponse['validationPassed'];
	if(lengthValidationPassed == false)
	{
		alertMessage += lengthValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var mandatoryValidationResponse =  doProductsManadatoryValidation(productsDataObject, paramsMap);
	var mandatoryValidationPassed = mandatoryValidationResponse['validationPassed'];
	if(mandatoryValidationPassed == false)
	{
		alertMessage += mandatoryValidationResponse['alertMessage'];
		validationPassed = false;
	}
	var validationObject = {'alertMessage':alertMessage,'validationPassed':validationPassed};
	return validationObject;
}
