package com.shoppingcart.request.util;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lowagie.text.Paragraph;
import com.shoppingcart.bean.PrivilegeGroup;
import com.shoppingcart.controller.forms.impl.PrivilegeGroupControllerImpl;
import com.shoppingcart.request.util.AjaxRequestHandler;
import com.shoppingcart.controller.forms.impl.OrganisationsControllerImpl;

import com.shoppingcart.controller.forms.impl.UserInfoControllerImpl;

import com.shoppingcart.controller.forms.impl.PrivilegeGroupControllerImpl;

import com.shoppingcart.controller.forms.impl.PrivilegeGroupItemsControllerImpl;

import com.shoppingcart.controller.forms.impl.EmployeeRolesControllerImpl;

import com.shoppingcart.controller.forms.impl.LoginSessionInfoControllerImpl;

import com.shoppingcart.controller.forms.impl.ConfigPropertiesControllerImpl;

import com.shoppingcart.controller.forms.impl.TaskInfoControllerImpl;

import com.shoppingcart.controller.forms.impl.TaskExecutionInfoControllerImpl;

import com.shoppingcart.controller.forms.impl.TaskLayoutParametersControllerImpl;

import com.shoppingcart.controller.forms.impl.EmailAttachmentLayoutControllerImpl;

import com.shoppingcart.controller.forms.impl.TaskScheduleInfoControllerImpl;

import com.shoppingcart.controller.forms.impl.TaskSentInfoControllerImpl;

import com.shoppingcart.controller.forms.impl.UserCartControllerImpl;

import com.shoppingcart.controller.forms.impl.CartItemControllerImpl;

import com.shoppingcart.controller.forms.impl.ProductsControllerImpl;

import com.shoppingcart.controller.forms.impl.OrdersControllerImpl;

import com.shoppingcart.controller.forms.impl.OrderItemControllerImpl;
import com.shoppingcart.util.CartItemUtil;
import com.shoppingcart.util.CommonUtil;
import com.shoppingcart.util.UserCartUtil;
public class EntityRequestHandler
{
	protected JsonObject handleGetSearchResultsAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				JsonArray parameterNameAndValuesList = paramsInfo.get("searchInputParamsList").getAsJsonArray();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
				int isUserLoggedInVal =  userSessionInfo.get("isUserLoggedIn").getAsInt();
				int userId =  userSessionInfo.get("userId").getAsInt();
				int loggedInEmployeeId = userSessionInfo.get("loggedInEmployeeId").getAsInt();
				paramsMap.put("loggedInEmployeeId", String.valueOf(loggedInEmployeeId));
				paramsMap.put("isUserLoggedIn", String.valueOf(isUserLoggedInVal));
				paramsMap.put("loginEntityType", loginEntityType);
				paramsMap.put("userId", String.valueOf(userId));
				
				for (int i = 0; i < parameterNameAndValuesList.size(); i++)
				{
					JsonObject parameterNameAndvalueInfoObj = parameterNameAndValuesList.get(i).getAsJsonObject();
					String parameterName = parameterNameAndvalueInfoObj.get("parameterName").getAsString();
					String userInputValue = parameterNameAndvalueInfoObj.get("userInputValue").getAsString();
					paramsMap.put(parameterName, userInputValue);
				}
				if (1>2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.retrieveOrganisationsList(paramsMap);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.retrieveUserInfoList(paramsMap);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.retrievePrivilegeGroupList(paramsMap);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.retrievePrivilegeGroupItemsList(paramsMap);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.retrieveEmployeeRolesList(paramsMap);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.retrieveLoginSessionInfoList(paramsMap);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.retrieveConfigPropertiesList(paramsMap);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.retrieveTaskInfoList(paramsMap);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.retrieveTaskExecutionInfoList(paramsMap);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.retrieveTaskLayoutParametersList(paramsMap);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.retrieveEmailAttachmentLayoutList(paramsMap);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.retrieveTaskScheduleInfoList(paramsMap);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.retrieveTaskSentInfoList(paramsMap);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.retrieveUserCartList(paramsMap);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.retrieveCartItemList(paramsMap);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.retrieveProductsList(paramsMap);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.retrieveOrdersList(paramsMap);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.retrieveOrderItemList(paramsMap);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as search results could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleAPIRequestAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession, String requestType)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				if(AjaxRequestHandler.coreEntitiesList.contains(objectType))
				{
					organisationSession = masterSession;
				}
				String requestURI = requestInfo.get("requestURI").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				if (1>2)
				{
				}
				else if ("/updatePrivilegeGroupItemsList".equalsIgnoreCase(requestURI))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(organisationSession, userSessionInfo);
					Integer privilegeGroupId = paramsInfo.get("privilegeGroupId").getAsInt();
					PrivilegeGroup privilegeGroup = (PrivilegeGroup) organisationSession.get(PrivilegeGroup.class, privilegeGroupId);
					dataObject = privilegeGroupControllerImpl.doExecuteAPIRequestImpl("updatePrivilegeGroupItemsList", paramsInfo, privilegeGroup);
				}
				
				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/information could not be saved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleSaveAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				JsonObject paramsInfoObj = new JsonObject();
				if(paramsInfo.has("additionalParamsInfo"))
				{
					paramsInfoObj = paramsInfo.get("additionalParamsInfo").getAsJsonObject();
				}
				
				int employeeId = userSessionInfo.get("loggedInEmployeeId").getAsInt();
				if (1>2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.createOrganisations(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.createUserInfo(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.createPrivilegeGroup(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.createPrivilegeGroupItems(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.createEmployeeRoles(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.createLoginSessionInfo(paramsInfo, employeeId, paramsInfoObj);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.createConfigProperties(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.createTaskInfo(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.createTaskExecutionInfo(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.createTaskLayoutParameters(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.createEmailAttachmentLayout(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.createTaskScheduleInfo(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.createTaskSentInfo(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.createUserCart(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.createCartItem(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.createProducts(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.createOrders(paramsInfo, employeeId, paramsInfoObj);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.createOrderItem(paramsInfo, employeeId, paramsInfoObj);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/information could not be saved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleUpdateAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				if (1>2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.updateOrganisations(paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.updateUserInfo(paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.updatePrivilegeGroup(paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.updatePrivilegeGroupItems(paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.updateEmployeeRoles(paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.updateLoginSessionInfo(paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.updateConfigProperties(paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.updateTaskInfo(paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.updateTaskExecutionInfo(paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.updateTaskLayoutParameters(paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.updateEmailAttachmentLayout(paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.updateTaskScheduleInfo(paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.updateTaskSentInfo(paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.updateUserCart(paramsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.updateCartItem(paramsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.updateProducts(paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.updateOrders(paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.updateOrderItem(paramsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/information could not be updated.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleUpdatePasswordAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				if (1>2)
				{
				}
				
				
				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/information could not be updated.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleDeleteAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				if (1>2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.deleteOrganisations(paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.deleteUserInfo(paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.deletePrivilegeGroup(paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.deletePrivilegeGroupItems(paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.deleteEmployeeRoles(paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.deleteLoginSessionInfo(paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.deleteConfigProperties(paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.deleteTaskInfo(paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.deleteTaskExecutionInfo(paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.deleteTaskLayoutParameters(paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.deleteEmailAttachmentLayout(paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.deleteTaskScheduleInfo(paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.deleteTaskSentInfo(paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.deleteUserCart(paramsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.deleteCartItem(paramsInfo);
					// custom Handling
					//cartItemControllerImpl.doAfterDeleteTransactionImpl(organisationSession);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.deleteProducts(paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.deleteOrders(paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.deleteOrderItem(paramsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/information could not be deleted.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleGetEntityInfoAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				
				if (1>2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.retrieveOrganisations(paramsInfo, userSessionInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.retrieveUserInfo(paramsInfo, userSessionInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.retrievePrivilegeGroup(paramsInfo, userSessionInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.retrievePrivilegeGroupItems(paramsInfo, userSessionInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.retrieveEmployeeRoles(paramsInfo, userSessionInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.retrieveLoginSessionInfo(paramsInfo, userSessionInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.retrieveConfigProperties(paramsInfo, userSessionInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.retrieveTaskInfo(paramsInfo, userSessionInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.retrieveTaskExecutionInfo(paramsInfo, userSessionInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.retrieveTaskLayoutParameters(paramsInfo, userSessionInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.retrieveEmailAttachmentLayout(paramsInfo, userSessionInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.retrieveTaskScheduleInfo(paramsInfo, userSessionInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.retrieveTaskSentInfo(paramsInfo, userSessionInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.retrieveUserCart(paramsInfo, userSessionInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.retrieveCartItem(paramsInfo, userSessionInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.retrieveProducts(paramsInfo, userSessionInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.retrieveOrders(paramsInfo, userSessionInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.retrieveOrderItem(paramsInfo, userSessionInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleGetChildEntityListAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.retrieveDependentOrganisationsList(paramsMap);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.retrieveDependentUserInfoList(paramsMap);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.retrieveDependentPrivilegeGroupList(paramsMap);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					String privilegeGroupId = paramsInfo.get("privilegeGroupId").getAsString();
					paramsMap.put("privilegeGroupId", privilegeGroupId);
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.retrieveDependentPrivilegeGroupItemsList(paramsMap);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					String userInfoId = paramsInfo.get("userInfoId").getAsString();
					paramsMap.put("userInfoId", userInfoId);
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.retrieveDependentEmployeeRolesList(paramsMap);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.retrieveDependentLoginSessionInfoList(paramsMap);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.retrieveDependentConfigPropertiesList(paramsMap);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.retrieveDependentTaskInfoList(paramsMap);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					String taskInfoId = paramsInfo.get("taskInfoId").getAsString();
					paramsMap.put("taskInfoId", taskInfoId);
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.retrieveDependentTaskExecutionInfoList(paramsMap);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					String taskInfoId = paramsInfo.get("taskInfoId").getAsString();
					paramsMap.put("taskInfoId", taskInfoId);
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.retrieveDependentTaskLayoutParametersList(paramsMap);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					String taskInfoId = paramsInfo.get("taskInfoId").getAsString();
					paramsMap.put("taskInfoId", taskInfoId);
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.retrieveDependentEmailAttachmentLayoutList(paramsMap);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.retrieveDependentTaskScheduleInfoList(paramsMap);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.retrieveDependentTaskSentInfoList(paramsMap);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.retrieveDependentUserCartList(paramsMap);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.retrieveDependentCartItemList(paramsMap);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.retrieveDependentProductsList(paramsMap);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.retrieveDependentOrdersList(paramsMap);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					String Id = paramsInfo.get("Id").getAsString();
					paramsMap.put("Id", Id);
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.retrieveDependentOrderItemList(paramsMap);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as dependent transactions/entities information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleLoadDefaultDataAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				int loginBranchId = requestInfo.get("loginBranchId").getAsInt();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				JsonObject initParamsInfo = new JsonObject();
				if(paramsInfo.has("initParams"))
				{
					initParamsInfo = paramsInfo.get("initParams").getAsJsonObject();
				}
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.fetchOrganisationsDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.fetchUserInfoDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.fetchPrivilegeGroupDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.fetchPrivilegeGroupItemsDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.fetchEmployeeRolesDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.fetchLoginSessionInfoDefaultData(loginBranchId, initParamsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.fetchConfigPropertiesDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.fetchTaskInfoDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.fetchTaskExecutionInfoDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.fetchTaskLayoutParametersDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.fetchEmailAttachmentLayoutDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.fetchTaskScheduleInfoDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.fetchTaskSentInfoDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.fetchUserCartDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.fetchCartItemDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.fetchProductsDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.fetchOrdersDefaultData(loginBranchId, initParamsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.fetchOrderItemDefaultData(loginBranchId, initParamsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as dependent onload default information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleLoadTestDataAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				SessionUtil sessionUtil = new SessionUtil();
				String objectType = requestInfo.get("objectType").getAsString();
				int loginBranchId = requestInfo.get("loginBranchId").getAsInt();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.fetchOrganisationsTestData(loginBranchId, paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.fetchUserInfoTestData(loginBranchId, paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.fetchPrivilegeGroupTestData(loginBranchId, paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.fetchPrivilegeGroupItemsTestData(loginBranchId, paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.fetchEmployeeRolesTestData(loginBranchId, paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.fetchLoginSessionInfoTestData(loginBranchId, paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.fetchConfigPropertiesTestData(loginBranchId, paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.fetchTaskInfoTestData(loginBranchId, paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.fetchTaskExecutionInfoTestData(loginBranchId, paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.fetchTaskLayoutParametersTestData(loginBranchId, paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.fetchEmailAttachmentLayoutTestData(loginBranchId, paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.fetchTaskScheduleInfoTestData(loginBranchId, paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.fetchTaskSentInfoTestData(loginBranchId, paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.fetchUserCartTestData(loginBranchId, paramsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.fetchCartItemTestData(loginBranchId, paramsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.fetchProductsTestData(loginBranchId, paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.fetchOrdersTestData(loginBranchId, paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.fetchOrderItemTestData(loginBranchId, paramsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as dependent test data information could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleLookupRowSelectedAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				SessionUtil sessionUtil = new SessionUtil();
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.lookupRowSelectedForOrganisations(paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.lookupRowSelectedForUserInfo(paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.lookupRowSelectedForPrivilegeGroup(paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.lookupRowSelectedForPrivilegeGroupItems(paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.lookupRowSelectedForEmployeeRoles(paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.lookupRowSelectedForLoginSessionInfo(paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.lookupRowSelectedForConfigProperties(paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.lookupRowSelectedForTaskInfo(paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.lookupRowSelectedForTaskExecutionInfo(paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.lookupRowSelectedForTaskLayoutParameters(paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.lookupRowSelectedForEmailAttachmentLayout(paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.lookupRowSelectedForTaskScheduleInfo(paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.lookupRowSelectedForTaskSentInfo(paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.lookupRowSelectedForUserCart(paramsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.lookupRowSelectedForCartItem(paramsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.lookupRowSelectedForProducts(paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.lookupRowSelectedForOrders(paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.lookupRowSelectedForOrderItem(paramsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/entity could not be updated with selected information.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleSelectOptionChangedAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				SessionUtil sessionUtil = new SessionUtil();
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.selectOptionChangedForOrganisations(paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.selectOptionChangedForUserInfo(paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.selectOptionChangedForPrivilegeGroup(paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.selectOptionChangedForPrivilegeGroupItems(paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.selectOptionChangedForEmployeeRoles(paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.selectOptionChangedForLoginSessionInfo(paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.selectOptionChangedForConfigProperties(paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.selectOptionChangedForTaskInfo(paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.selectOptionChangedForTaskExecutionInfo(paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.selectOptionChangedForTaskLayoutParameters(paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.selectOptionChangedForEmailAttachmentLayout(paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.selectOptionChangedForTaskScheduleInfo(paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.selectOptionChangedForTaskSentInfo(paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.selectOptionChangedForUserCart(paramsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.selectOptionChangedForCartItem(paramsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.selectOptionChangedForProducts(paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.selectOptionChangedForOrders(paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.selectOptionChangedForOrderItem(paramsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/entity could not be updated with selected information.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleCustomAPIAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				SessionUtil sessionUtil = new SessionUtil();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.doExecuteCustomAPI(paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					
					//UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					//dataObject = userCartControllerImpl.doExecuteCustomAPI(paramsInfo);
				    UserCartUtil userCartUtil=new UserCartUtil();
				    dataObject=userCartUtil.doValidateUserCart(organisationSession);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.doExecuteCustomAPI(paramsInfo);
				}
				// Handling API Request
		        else if ("CartItem".equalsIgnoreCase(objectType))
				{
					 //CartItemUtil cartUtil =new CartItemUtil();
					CartItemControllerImpl cartItemImpl=new CartItemControllerImpl();
					// use same Session obj "organisationSession" and pass to your function as a argument
					dataObject = cartItemImpl.doExecuteCustomAPI(paramsInfo);
				
				}
				

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/entity could not be updated.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleExecuteActionAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				SessionUtil sessionUtil = new SessionUtil();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.executeAuthorisationOnOrganisations(paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.executeAuthorisationOnUserInfo(paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.executeAuthorisationOnPrivilegeGroup(paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.executeAuthorisationOnPrivilegeGroupItems(paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.executeAuthorisationOnEmployeeRoles(paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.executeAuthorisationOnLoginSessionInfo(paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.executeAuthorisationOnConfigProperties(paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.executeAuthorisationOnTaskInfo(paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.executeAuthorisationOnTaskExecutionInfo(paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.executeAuthorisationOnTaskLayoutParameters(paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.executeAuthorisationOnEmailAttachmentLayout(paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.executeAuthorisationOnTaskScheduleInfo(paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.executeAuthorisationOnTaskSentInfo(paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.executeAuthorisationOnUserCart(paramsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.executeAuthorisationOnCartItem(paramsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.executeAuthorisationOnProducts(paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.executeAuthorisationOnOrders(paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.executeAuthorisationOnOrderItem(paramsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/entity could not be updated.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleDownloadDataAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				SessionUtil sessionUtil = new SessionUtil();
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.downloadOrganisationsData();
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.downloadUserInfoData();
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.downloadPrivilegeGroupData();
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.downloadPrivilegeGroupItemsData();
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.downloadEmployeeRolesData();
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.downloadLoginSessionInfoData();
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.downloadConfigPropertiesData();
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.downloadTaskInfoData();
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.downloadTaskExecutionInfoData();
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.downloadTaskLayoutParametersData();
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.downloadEmailAttachmentLayoutData();
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.downloadTaskScheduleInfoData();
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.downloadTaskSentInfoData();
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.downloadUserCartData();
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.downloadCartItemData();
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.downloadProductsData();
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.downloadOrdersData();
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.downloadOrderItemData();
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/entity could not be downloaded.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleUploadDataAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		Session instantMasterSession = null; 
		Session instantOrganisationSession = null; 
		try
		{
				instantMasterSession = CommonUtil.getNewMasterSession(masterSession, userSessionInfo);
				instantOrganisationSession = instantMasterSession;
				
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				SessionUtil sessionUtil = new SessionUtil();
				HashMap<String, String> paramsMap = new HashMap<String, String>();
				if (1 > 2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(instantMasterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.uploadOrganisationsData(paramsInfo);
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(instantMasterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.uploadUserInfoData(paramsInfo);
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(instantMasterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.uploadPrivilegeGroupData(paramsInfo);
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(instantMasterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.uploadPrivilegeGroupItemsData(paramsInfo);
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(instantMasterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.uploadEmployeeRolesData(paramsInfo);
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(instantMasterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.uploadLoginSessionInfoData(paramsInfo);
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.uploadConfigPropertiesData(paramsInfo);
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.uploadTaskInfoData(paramsInfo);
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.uploadTaskExecutionInfoData(paramsInfo);
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.uploadTaskLayoutParametersData(paramsInfo);
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.uploadEmailAttachmentLayoutData(paramsInfo);
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.uploadTaskScheduleInfoData(paramsInfo);
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.uploadTaskSentInfoData(paramsInfo);
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.uploadUserCartData(paramsInfo);
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.uploadCartItemData(paramsInfo);
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = productsControllerImpl.uploadProductsData(paramsInfo);
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.uploadOrdersData(paramsInfo);
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(instantOrganisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.uploadOrderItemData(paramsInfo);
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		finally 
		{
			if(instantMasterSession != null)
			{
				instantMasterSession.close();
			}
			
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as transaction/entity could not be uploaded.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	protected JsonObject handleDashboardDataAjaxRequest(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		int isRequestHandled = 1;
		try
		{
				String objectType = requestInfo.get("objectType").getAsString();
				JsonObject paramsInfo = requestInfo.get("paramsInfo").getAsJsonObject();
				SessionUtil sessionUtil = new SessionUtil();
				if (1>2)
				{
				}
								else if ("Organisations".equalsIgnoreCase(objectType))
				{
					OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
					dataObject = organisationsControllerImpl.getDashboardGraphsData();
				}
				else if ("UserInfo".equalsIgnoreCase(objectType))
				{
					UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = userInfoControllerImpl.getDashboardGraphsData();
				}
				else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupControllerImpl.getDashboardGraphsData();
				}
				else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
				{
					PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
					dataObject = privilegeGroupItemsControllerImpl.getDashboardGraphsData();
				}
				else if ("EmployeeRoles".equalsIgnoreCase(objectType))
				{
					EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
					dataObject = employeeRolesControllerImpl.getDashboardGraphsData();
				}
				else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
				{
					LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
					dataObject = loginSessionInfoControllerImpl.getDashboardGraphsData();
				}

								else if ("ConfigProperties".equalsIgnoreCase(objectType))
				{
					ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
					dataObject = configPropertiesControllerImpl.getDashboardGraphsData();
				}
				else if ("TaskInfo".equalsIgnoreCase(objectType))
				{
					TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskInfoControllerImpl.getDashboardGraphsData();
				}
				else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
				{
					TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskExecutionInfoControllerImpl.getDashboardGraphsData();
				}
				else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
				{
					TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskLayoutParametersControllerImpl.getDashboardGraphsData();
				}
				else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
				{
					EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
					dataObject = emailAttachmentLayoutControllerImpl.getDashboardGraphsData();
				}
				else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
				{
					TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskScheduleInfoControllerImpl.getDashboardGraphsData();
				}
				else if ("TaskSentInfo".equalsIgnoreCase(objectType))
				{
					TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
					dataObject = taskSentInfoControllerImpl.getDashboardGraphsData();
				}
				else if ("UserCart".equalsIgnoreCase(objectType))
				{
					UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
					dataObject = userCartControllerImpl.getDashboardGraphsData();
				}
				else if ("CartItem".equalsIgnoreCase(objectType))
				{
					CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = cartItemControllerImpl.getDashboardGraphsData();
				}
				else if ("Products".equalsIgnoreCase(objectType))
				{
					ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
					dataObject = productsControllerImpl.getDashboardGraphsData();
				}
				else if ("Orders".equalsIgnoreCase(objectType))
				{
					OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
					dataObject = ordersControllerImpl.getDashboardGraphsData();
				}
				else if ("OrderItem".equalsIgnoreCase(objectType))
				{
					OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
					dataObject = orderItemControllerImpl.getDashboardGraphsData();
				}

				dataObject.addProperty("isRequestHandled", isRequestHandled);
				return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("msg", "Error while handling the request !!");
		dataObject.addProperty("alert", "Your request could not be processed as Dashboard results could not be retrieved.");
		dataObject.addProperty("success", 0);
		dataObject.addProperty("isRequestHandled", isRequestHandled);
		return dataObject;
	}
	public static JsonObject getEntityAttributeValue(JsonObject userSessionInfo, JsonObject requestInfo, Session masterSession, Session organisationSession)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			String objectType = requestInfo.get("objectType").getAsString();
			if (1>2)
			{
			}
						else if ("Organisations".equalsIgnoreCase(objectType))
			{
				OrganisationsControllerImpl organisationsControllerImpl = new OrganisationsControllerImpl(masterSession, userSessionInfo);
				dataObject = organisationsControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("UserInfo".equalsIgnoreCase(objectType))
			{
				UserInfoControllerImpl userInfoControllerImpl = new UserInfoControllerImpl(masterSession, userSessionInfo);
				dataObject = userInfoControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("PrivilegeGroup".equalsIgnoreCase(objectType))
			{
				PrivilegeGroupControllerImpl privilegeGroupControllerImpl = new PrivilegeGroupControllerImpl(masterSession, userSessionInfo);
				dataObject = privilegeGroupControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("PrivilegeGroupItems".equalsIgnoreCase(objectType))
			{
				PrivilegeGroupItemsControllerImpl privilegeGroupItemsControllerImpl = new PrivilegeGroupItemsControllerImpl(masterSession, userSessionInfo);
				dataObject = privilegeGroupItemsControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("EmployeeRoles".equalsIgnoreCase(objectType))
			{
				EmployeeRolesControllerImpl employeeRolesControllerImpl = new EmployeeRolesControllerImpl(masterSession, userSessionInfo);
				dataObject = employeeRolesControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("LoginSessionInfo".equalsIgnoreCase(objectType))
			{
				LoginSessionInfoControllerImpl loginSessionInfoControllerImpl = new LoginSessionInfoControllerImpl(masterSession, userSessionInfo);
				dataObject = loginSessionInfoControllerImpl.getEntityAttributeValue(requestInfo);
			}

						else if ("ConfigProperties".equalsIgnoreCase(objectType))
			{
				ConfigPropertiesControllerImpl configPropertiesControllerImpl = new ConfigPropertiesControllerImpl(organisationSession, userSessionInfo);
				dataObject = configPropertiesControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("TaskInfo".equalsIgnoreCase(objectType))
			{
				TaskInfoControllerImpl taskInfoControllerImpl = new TaskInfoControllerImpl(organisationSession, userSessionInfo);
				dataObject = taskInfoControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("TaskExecutionInfo".equalsIgnoreCase(objectType))
			{
				TaskExecutionInfoControllerImpl taskExecutionInfoControllerImpl = new TaskExecutionInfoControllerImpl(organisationSession, userSessionInfo);
				dataObject = taskExecutionInfoControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("TaskLayoutParameters".equalsIgnoreCase(objectType))
			{
				TaskLayoutParametersControllerImpl taskLayoutParametersControllerImpl = new TaskLayoutParametersControllerImpl(organisationSession, userSessionInfo);
				dataObject = taskLayoutParametersControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("EmailAttachmentLayout".equalsIgnoreCase(objectType))
			{
				EmailAttachmentLayoutControllerImpl emailAttachmentLayoutControllerImpl = new EmailAttachmentLayoutControllerImpl(organisationSession, userSessionInfo);
				dataObject = emailAttachmentLayoutControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("TaskScheduleInfo".equalsIgnoreCase(objectType))
			{
				TaskScheduleInfoControllerImpl taskScheduleInfoControllerImpl = new TaskScheduleInfoControllerImpl(organisationSession, userSessionInfo);
				dataObject = taskScheduleInfoControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("TaskSentInfo".equalsIgnoreCase(objectType))
			{
				TaskSentInfoControllerImpl taskSentInfoControllerImpl = new TaskSentInfoControllerImpl(organisationSession, userSessionInfo);
				dataObject = taskSentInfoControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("UserCart".equalsIgnoreCase(objectType))
			{
				UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl(organisationSession, userSessionInfo);
				dataObject = userCartControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("CartItem".equalsIgnoreCase(objectType))
			{
				CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl(organisationSession, userSessionInfo);
				dataObject = cartItemControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("Products".equalsIgnoreCase(objectType))
			{
				ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl(organisationSession, userSessionInfo);
				dataObject = productsControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("Orders".equalsIgnoreCase(objectType))
			{
				OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl(organisationSession, userSessionInfo);
				dataObject = ordersControllerImpl.getEntityAttributeValue(requestInfo);
			}
			else if ("OrderItem".equalsIgnoreCase(objectType))
			{
				OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl(organisationSession, userSessionInfo);
				dataObject = orderItemControllerImpl.getEntityAttributeValue(requestInfo);
			}

			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as file information could not be retrieved.");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
}
