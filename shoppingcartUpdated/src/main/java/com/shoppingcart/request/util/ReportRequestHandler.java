package com.shoppingcart.request.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.shoppingcart.controller.reports.base.OrganisationsControllerBase;
import com.shoppingcart.controller.reports.impl.OrganisationsControllerImpl;
import com.shoppingcart.controller.reports.base.UserInfoControllerBase;
import com.shoppingcart.controller.reports.impl.UserInfoControllerImpl;
import com.shoppingcart.controller.reports.base.PrivilegeGroupControllerBase;
import com.shoppingcart.controller.reports.impl.PrivilegeGroupControllerImpl;
import com.shoppingcart.controller.reports.base.LoginSessionInfoControllerBase;
import com.shoppingcart.controller.reports.impl.LoginSessionInfoControllerImpl;
import com.shoppingcart.controller.reports.base.ConfigPropertiesControllerBase;
import com.shoppingcart.controller.reports.impl.ConfigPropertiesControllerImpl;
import com.shoppingcart.controller.reports.base.TaskInfoControllerBase;
import com.shoppingcart.controller.reports.impl.TaskInfoControllerImpl;
import com.shoppingcart.controller.reports.base.TaskScheduleInfoControllerBase;
import com.shoppingcart.controller.reports.impl.TaskScheduleInfoControllerImpl;
import com.shoppingcart.controller.reports.base.TaskSentInfoControllerBase;
import com.shoppingcart.controller.reports.impl.TaskSentInfoControllerImpl;
import com.shoppingcart.controller.reports.base.UserCartControllerBase;
import com.shoppingcart.controller.reports.impl.UserCartControllerImpl;
import com.shoppingcart.controller.reports.base.CartItemControllerBase;
import com.shoppingcart.controller.reports.impl.CartItemControllerImpl;
import com.shoppingcart.controller.reports.base.ProductsControllerBase;
import com.shoppingcart.controller.reports.impl.ProductsControllerImpl;
import com.shoppingcart.controller.reports.base.OrdersControllerBase;
import com.shoppingcart.controller.reports.impl.OrdersControllerImpl;
import com.shoppingcart.controller.reports.base.OrderItemControllerBase;
import com.shoppingcart.controller.reports.impl.OrderItemControllerImpl;
import com.shoppingcart.util.CommonUtil;
public class ReportRequestHandler
{
	protected JsonObject handleAjaxRequest(HttpServletRequest request, HttpServletResponse response, JsonObject requestInfo, Session organisationSession, JsonObject  userSessionInfo)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int isRequestHandled = 0;
			String requestURI = requestInfo.get("requestURI").getAsString();
			String objectType = requestInfo.get("objectType").getAsString();
			if(requestURI==null)
			{
				isRequestHandled = 1;
				dataObject.addProperty("success", 0);
				dataObject.addProperty("alert", "Request URI is null");
			}
			else if("/getReportInfo".equalsIgnoreCase(requestURI))
			{				
				return getReportInfo(objectType, request, organisationSession, userSessionInfo);
			}
			else if("/getSearchResults".equalsIgnoreCase(requestURI))
			{
				return getSearchResults(objectType, request, organisationSession, userSessionInfo);
			}
		    else if("/getLookupResults".equalsIgnoreCase(requestURI))
			{				
				return getLookupResults(objectType, request, organisationSession);						
			}
			else
			{
				dataObject.addProperty("isRequestHandled", isRequestHandled);
				dataObject.addProperty("success", 0);
			}					
			dataObject.addProperty("isRequestHandled", isRequestHandled);
			return dataObject;			
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}		
		dataObject.addProperty("msg", "Error while handling the request !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	private JsonObject getReportInfo(String objectType, HttpServletRequest request, Session organisationSession, JsonObject  userSessionInfo)
	{
		JsonObject dataObject = new JsonObject();	
		try
		{
			
				if (objectType.equalsIgnoreCase("Organisations"))
				{			
					dataObject = getOrganisationsInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("UserInfo"))
				{			
					dataObject = getUserInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("PrivilegeGroup"))
				{			
					dataObject = getPrivilegeGroupInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("LoginSessionInfo"))
				{			
					dataObject = getLoginSessionInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("ConfigProperties"))
				{			
					dataObject = getConfigPropertiesInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("TaskInfo"))
				{			
					dataObject = getTaskInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("TaskScheduleInfo"))
				{			
					dataObject = getTaskScheduleInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("TaskSentInfo"))
				{			
					dataObject = getTaskSentInfoInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("UserCart"))
				{			
					dataObject = getUserCartInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("CartItem"))
				{			
					dataObject = getCartItemInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("Products"))
				{			
					dataObject = getProductsInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("Orders"))
				{			
					dataObject = getOrdersInfo(request, organisationSession);			
					return dataObject;
				}		
				if (objectType.equalsIgnoreCase("OrderItem"))
				{			
					dataObject = getOrderItemInfo(request, organisationSession);			
					return dataObject;
				}
			dataObject.addProperty("isRequestHandled", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}			
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as report information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", 0);
		return dataObject;
	}
	private JsonObject getSearchResults(String objectType, HttpServletRequest request, Session organisationSession, JsonObject  userSessionInfo)
	{
		JsonObject dataObject = new JsonObject();	
		try
		{
			String searchFormName = request.getParameter("searchFormName").trim();
			if (1>2)
			{
			}
			
			dataObject.addProperty("isRequestHandled", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}			
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as report information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", 0);
		return dataObject;
	}
	private JsonObject getLookupResults(String objectType, HttpServletRequest request, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();	
		try
		{
			String searchFormName = request.getParameter("searchFormName").trim();
			String lookupEntityName = request.getParameter("lookupEntityName").trim();
			if (1>2)
			{
			}
			
			dataObject.addProperty("isRequestHandled", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}			
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as report information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", 0);
		return dataObject;
	}	
	
		private JsonObject getOrganisationsInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String organisationsId = "";
			try {
			organisationsId = request.getParameter("organisationsId").trim();	}
			catch (Exception e) {
				organisationsId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("organisationsId", organisationsId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = OrganisationsControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getUserInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String userInfoId = "";
			try {
			userInfoId = request.getParameter("userInfoId").trim();	}
			catch (Exception e) {
				userInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("userInfoId", userInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = UserInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getPrivilegeGroupInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String privilegeGroupId = "";
			try {
			privilegeGroupId = request.getParameter("privilegeGroupId").trim();	}
			catch (Exception e) {
				privilegeGroupId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("privilegeGroupId", privilegeGroupId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = PrivilegeGroupControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getLoginSessionInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String loginSessionInfoId = "";
			try {
			loginSessionInfoId = request.getParameter("loginSessionInfoId").trim();	}
			catch (Exception e) {
				loginSessionInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("loginSessionInfoId", loginSessionInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = LoginSessionInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getConfigPropertiesInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String configPropertiesId = "";
			try {
			configPropertiesId = request.getParameter("configPropertiesId").trim();	}
			catch (Exception e) {
				configPropertiesId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("configPropertiesId", configPropertiesId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = ConfigPropertiesControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getTaskInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String taskInfoId = "";
			try {
			taskInfoId = request.getParameter("taskInfoId").trim();	}
			catch (Exception e) {
				taskInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("taskInfoId", taskInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = TaskInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getTaskScheduleInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String taskScheduleInfoId = "";
			try {
			taskScheduleInfoId = request.getParameter("taskScheduleInfoId").trim();	}
			catch (Exception e) {
				taskScheduleInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("taskScheduleInfoId", taskScheduleInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = TaskScheduleInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getTaskSentInfoInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String taskSentInfoId = "";
			try {
			taskSentInfoId = request.getParameter("taskSentInfoId").trim();	}
			catch (Exception e) {
				taskSentInfoId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("taskSentInfoId", taskSentInfoId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = TaskSentInfoControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getUserCartInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String userCartId = "";
			try {
			userCartId = request.getParameter("userCartId").trim();	}
			catch (Exception e) {
				userCartId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("userCartId", userCartId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = UserCartControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getCartItemInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String cartItemId = "";
			try {
			cartItemId = request.getParameter("cartItemId").trim();	}
			catch (Exception e) {
				cartItemId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("cartItemId", cartItemId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = CartItemControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getProductsInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String productsId = "";
			try {
			productsId = request.getParameter("productsId").trim();	}
			catch (Exception e) {
				productsId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("productsId", productsId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = ProductsControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getOrdersInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String ordersId = "";
			try {
			ordersId = request.getParameter("ordersId").trim();	}
			catch (Exception e) {
				ordersId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("ordersId", ordersId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = OrdersControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
			private JsonObject getOrderItemInfo(HttpServletRequest request, Session organisationSession)throws Exception
		{
			JsonObject dataObject = new JsonObject();	
			
			String orderItemId = "";
			try {
			orderItemId = request.getParameter("orderItemId").trim();	}
			catch (Exception e) {
				orderItemId = "";
			}				
			
			String noOfRecordsAlreadyFetched = request.getParameter("noOfRecordsAlreadyFetched").trim();	
			String noOfRecordsToFetch = request.getParameter("noOfRecordsToFetch").trim();	
			HashMap<String, String> paramsMap = new HashMap<String, String>();
            
            paramsMap.put("orderItemId", orderItemId);	            
            				
			paramsMap.put("noOfRecordsAlreadyFetched", noOfRecordsAlreadyFetched);
			paramsMap.put("noOfRecordsToFetch", noOfRecordsToFetch);
			dataObject = OrderItemControllerBase.getPageData(organisationSession, paramsMap);	
			if(dataObject.get("success").getAsInt()!=1)
			{
				dataObject.addProperty("isRequestHandled", 1);
				return dataObject;																			
			}
			dataObject.addProperty("isRequestHandled", 1);
			return dataObject;
		}
public static void populateCustomDataFields(Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject,  String layoutName, Session organisationSession, JsonObject fieldsDataWithOverrideWhereClause)
{	
	
	if(layoutName.equalsIgnoreCase("Organisations"))
	{
		OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl();
		organisationsControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("UserInfo"))
	{
		UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl();
		userInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("PrivilegeGroup"))
	{
		PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl();
		privilegeGroupControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("LoginSessionInfo"))
	{
		LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl();
		loginSessionInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("ConfigProperties"))
	{
		ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl();
		configPropertiesControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("TaskInfo"))
	{
		TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl();
		taskInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("TaskScheduleInfo"))
	{
		TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl();
		taskScheduleInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("TaskSentInfo"))
	{
		TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl();
		taskSentInfoControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("UserCart"))
	{
		UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl();
		userCartControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("CartItem"))
	{
		CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl();
		cartItemControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("Products"))
	{
		ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl();
		productsControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("Orders"))
	{
		OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl();
		ordersControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
	if(layoutName.equalsIgnoreCase("OrderItem"))
	{
		OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl();
		orderItemControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	}
}			
}
