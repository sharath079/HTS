package com.shoppingcart.controller.forms.impl;
import org.hibernate.Session;
import java.util.HashMap;
import com.shoppingcart.bean.TaskScheduleInfo;
import com.shoppingcart.util.*;
import java.math.BigDecimal;
import com.google.gson.JsonObject;
import org.hibernate.Query;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import com.vw.runtime.VWResponseBean;
import com.shoppingcart.controller.forms.base.TaskScheduleInfoLabel;
import com.shoppingcart.controller.forms.base.TaskScheduleInfoControllerBase;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.vw.runtime.IRulesFields;
import com.vw.runtime.RulesBean;
import com.vw.runtime.VWMastersBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;



@SuppressWarnings("unused")
/**
 * 
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 * 
 */
/*@ManagedBean(name = "TaskScheduleInfoControllerImpl")
@RequestScoped*/
public class TaskScheduleInfoControllerImpl extends TaskScheduleInfoControllerBase 
{
	public TaskScheduleInfoControllerImpl(Session session)
	{
		super(session);
	}
	public TaskScheduleInfoControllerImpl(Session session, JsonObject standardReqParams)
	{
		super(session, standardReqParams);
	}
	public TaskScheduleInfoControllerImpl()
	{
		super();
	}
	
	
	public void doEnrichValuesImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	
	
	public void doAfterEnrichValuesImpl()
	{
		//Custom handling
	}
	
	
	
	public void doValidationsImpl()
	{
		//Custom handling
	}
	
	
	
	public String getLcNoImpl()
	{
		return "";
	}
	
	
	
	public void doAfterSetValues()
	{
		//Custom handling
	}		
	
	
	
	public void doAfterSelectRowImpl()
	{
		//Custom handling
	}
	
	
	
	public void doBeforeCreateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
		try
		{
			
		} 
		catch (Exception e)
		{
        	CommonUtil.writeTolog(e);
			addCustomResponse("Verification token could not be generated to verify email id.");
			return;
		}
	}
	
	
	
	public void doAfterCreateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	
	
	public void doBeforeUpdateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	public void doUpdateQueryWithParameterValuesImpl(Query query, java.util.Map<String, Object> paramsMap)
	{
		//Custom handling
	}
	public String doGetUpdatedQueryStringForSearchImpl(String queryString)
	{
		//Custom handling
		return "";
	}
	
	
	public void doAfterCreateTransactionCommittedImpl()
	{
		//Custom handling
	}
	
	public void doAfterUpdateTransactionCommittedImpl()
	{
		//Custom handling
	}
	
	
	public void doAfterUpdateTransactionImpl(JsonObject paramsInfoObj)
	{
		//Custom handling
	}
	
	
	
	public void doBeforeDeleteTransactionImpl()
	{
		//Custom handling	
	}
	
	
	
	public void doAfterDeleteTransactionImpl()
	{
		//Custom handling	
	}
	
	
	
	public void doAfterEntityLoadedImpl(TaskScheduleInfo taskScheduleInfo, JsonObject initParamsInfo)
	{
		//Custom handling	
	}
	
	
	
	public JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject taskScheduleInfoDataObject, TaskScheduleInfo taskScheduleInfo)
	{
		// Custom handling
		JsonObject dataObject = new JsonObject();
		try
		{
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		dataObject.addProperty("alert", "This API (" + apiName + ") has no implementation !!");
		dataObject.addProperty("success", 0);
		return dataObject; 		
	}
	
	
	
	public void doExecuteCustomAPIImpl(String customAPIMessage)
	{
		// Custom handling
	}
	
	public void doAfterLookupRowSelectedImpl(TaskScheduleInfo taskScheduleInfo, String attributeName)
	{
		//Custom handling	
	}
	public void doAfterSelectOptionChangedImpl(TaskScheduleInfo taskScheduleInfo, String attributeName)
	{
		//Custom handling	
	}
	public void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams)
	{
		//Custom handling	
	}
	public void verifyIfTransactionIsDeletableImpl()
	{
		//Custom handling
	}
	public void verifyIfTransactionIsUpdatableImpl()
	{
		//Custom handling
	}
	
	
	public void doBeforeTransactionApprovedImpl()
	{
		//Custom handling
	}
	
	public void doAfterTransactionApprovedImpl()
	{
		//Custom handling
	}
	
	
	public void doBeforeTransactionRolledbackImpl()
	{
		//Custom handling
	}
	
	
	
	public void doRefreshFromUIImpl()
	{
		//Custom handling
	}
	
	
	
	public void doValidateRepeatLineImpl(String sRepeatTagName, String string, int iCurrIndex)
	{
		//Custom handling
	}
	
	
	
	//Custom code
	
	
	
	public void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap)
	{
		//Custom handling
	}
	
	
	
	public String doGetOrderByClauseSearchImpl()
	{
		//Custom handling
		String orderByClause = "  ";
		return orderByClause;
	}
	
}
