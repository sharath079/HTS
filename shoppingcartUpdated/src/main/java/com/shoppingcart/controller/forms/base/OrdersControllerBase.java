package com.shoppingcart.controller.forms.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.math.BigDecimal;
import java.sql.Time;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import javax.servlet.http.HttpServletRequest;
import com.shoppingcart.request.service.HttpUtil;
import com.shoppingcart.request.service.ServiceAPIUtil;
import com.shoppingcart.request.service.RequestReceived;
	
//

import com.shoppingcart.bean.Orders;
import com.shoppingcart.controller.forms.base.OrdersLabel;
import com.vw.runtime.BusinessRulesController;
import com.vw.runtime.VWViewObject;
import com.shoppingcart.util.ZipExtraction;
import com.vw.runtime.VWSessionObject;
import com.vw.runtime.VWMastersBean;
import com.shoppingcart.util.CommonUtil;
import com.shoppingcart.util.UploadDownloadUtil;
@SuppressWarnings({"unused","unchecked"})
/**
 *
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 *
 */
public abstract class OrdersControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'UserCartId' 
	 *		 * 'OrderNo' 
	 *		 * 'OrderDate' 
	 *		 * 'OrderAmount' 
	 *	
	 *
	 */
	/*
	 * userSessionInfo Parameters:
	 * ================
	 * loggedInEmployeeId
	 * isUserLoggedIn
	 * loginEntityType 
	 * userId;
	 */	
	Integer iPKValue=0;
	protected JsonObject userSessionInfo = new JsonObject();
	protected JsonObject additionalInfo = new JsonObject();
	protected boolean isTransactionUpdatable;
	protected boolean isDeletionAllowed;
	protected OrdersLabel OrdersLabelLocal = new OrdersLabel();
	protected Orders OrdersLocal = new Orders();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public OrdersControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public OrdersControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public OrdersControllerBase()
	{
		super();
	}
	public JsonObject getUserSessionInfo() 
	{
		return userSessionInfo;
	}
	public JsonObject getAdditionalInfo() 
	{
		return additionalInfo;
	}
	public void writeToLog(String logMessage) 
	{
		System.out.println(logMessage);
	}
 	protected String getPkFieldName()
	{
		return "Orders" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((Orders)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((Orders)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((Orders)obj).getOrdersId();
	}
	public String getManagedBeanName()
    {
		return "OrdersBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "OrdersLabelBean";
    }
	protected Class<Orders> setBeanClass()
	{
		return Orders.class;
	}
	protected Class<OrdersLabel> setBeanLabelClass()
	{
		return OrdersLabel.class;
	}
	protected Orders getLocalManagedBean()
    {
		return (Orders)getManagedBean();
    }
	
	protected String getAuthAmtCcy()
	{
		return "INR";
	}
	protected BigDecimal getAuthAmt()
	{
		return BigDecimal.ZERO;
	}
	public void refreshFromUI()
	{
		doRefreshFromUIImpl();
	}
	public void doBeforeTransactionApproved()
	{
		/*
		 * Use below code as required in impl
		 */
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		/*Orders orders = (Orders)getManagedBean();
		String areChangesEffected = orders.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			orders.setAreChangesEffected("YES");			
		}*/
		JsonObject dataObject = new JsonObject();		
			
		int generatedRequestId = -1;
			
		doBeforeTransactionApprovedImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doAfterTransactionApproved()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterTransactionApprovedImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);		
	}
	public void doBeforeTransactionRolledback()
	{
		/*
		 * Use below code as required in impl
		 */
		/*Orders orders = (Orders)getManagedBean();
		String areChangesEffected = orders.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			orders.setAreChangesEffected("NO");			
		}*/
		int generatedRequestId = -1;
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();	doBeforeTransactionRolledbackImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isAuthorizationEnabled()
	{
		return true;		
	}
	public boolean isRollbackRequired()
	{
		return true;
	}	
	public boolean isSubmitRequired()
	{
		return true;
	}	
	public boolean isTransactionUpdatable()
	{
		/*
		 * Use below code as required in impl
		 */		
		/*Orders orders = (Orders)getManagedBean();
		String areChangesEffected = orders.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			return false;
		}
		else
		{
			return true;			
		}*/		
		isTransactionUpdatable = true;
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		verifyIfTransactionIsUpdatableImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		return isTransactionUpdatable;
	}
	public boolean isActionAllowedOnCurrentStatus(String sAction)
	{
		Orders orders = (Orders)getManagedBean();
		String sCurrentStatus = orders.getVwTxnStatus();
		if(isRollbackRequired()==true)
		{
			if("COMPLETED".equalsIgnoreCase(sCurrentStatus) && sAction.matches(ACTION_UPDATE))
			{
				if(!("ROLLBACK".equalsIgnoreCase(sAction)))
				{
					String sParams[] = new String[1];
					sParams[0] = sAction;
					addApplicationResponse("VWT", "ERR0016", sParams);
					handleResponse(); // GDN: do not move it outside
					return false;			
				}
			}			
		}
		return true;		
	}
	/*
	 * Business logic where clause parameters can be added based on the entity and the attribute 
	 * on which the lookup popup is opened. 
	 */
	public HashMap doBeforeLookupEntitiesRetrieved(String callingEntityName, String callingAttributeName)
	{
		HashMap customSearchParams = new HashMap();
	    /*
	     * Sample handling in impl
	     */
		/*if("SetOfBooksInfo".equalsIgnoreCase(callingEntityName))
		{
			if("ParentAccountGroup".equalsIgnoreCase(callingEntityName))
			{
				SetOfBooksInfo setOfBooksInfo = ((AccountGroups)getManagedBean()).getSetOfBooksInfo();
				customSearchParams.put("SetOfBooksInfo", setOfBooksInfo);				
			}
		}*/
		doBeforeLookupEntitiesRetrievedImpl(callingEntityName, callingAttributeName, customSearchParams);
		return customSearchParams;
	}
	public void doAfterSelectOptionChanged(String attributeName)
	{
		Orders orders = (Orders)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(orders, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterOrdersLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		Orders orders = (Orders)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("userCartId") && initParamsInfo.get("userCartId").isJsonNull()==false)
		{
			Integer userCartId = null;
			try
			{
			 	userCartId = initParamsInfo.get("userCartId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orders.setUserCartId(userCartId);
		}if(initParamsInfo.has("orderNo") && initParamsInfo.get("orderNo").isJsonNull()==false)
		{
			Integer orderNo = null;
			try
			{
			 	orderNo = initParamsInfo.get("orderNo").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orders.setOrderNo(orderNo);
		}if(initParamsInfo.has("orderDate") && initParamsInfo.get("orderDate").isJsonNull()==false)
		{
			String orderDate = initParamsInfo.get("orderDate").getAsString();
			if(orderDate.length() > 0)
			{
				try
				{
					orders.setOrderDate(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(orderDate));
				}
				catch(Exception e)
				{
				}
			}
		}if(initParamsInfo.has("orderAmount") && initParamsInfo.get("orderAmount").isJsonNull()==false)
		{
			Integer orderAmount = null;
			try
			{
			 	orderAmount = initParamsInfo.get("orderAmount").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orders.setOrderAmount(orderAmount);
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(orders, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		Orders orders = (Orders)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		Orders orders = (Orders)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(orders, attributeName);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public boolean isDeleteAllowed()
	{
		isTransactionUpdatable = isTransactionUpdatable();
		if(isTransactionUpdatable==false)
		{
			return false;
		}
		isDeletionAllowed = true;
		/*
		 * set "isDeletionAllowed" flag to false in impl 
		 * if deletion has to be prevented
		 */
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		verifyIfTransactionIsDeletableImpl();
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isDeletionAllowed==false)
		{
			addCustomResponse("This transaction cannot be deleted !!");
		}
		return isDeletionAllowed;
	}	
	public void beforeDeleteTransaction(boolean clearSession)
	{
		try
		{JsonObject dataObject = new JsonObject();		if(isTransactionUpdatable()==false)
			{
				addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
			}
			Orders orders = (Orders)getPrivateManagedBean();
			Session session = getDBSession();
			if(clearSession==true)
			{
				session.clear();				
			}
			// code to be revisited
			/*
			*/
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	    doBeforeDeleteTransactionImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void afterDeleteTransaction()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	    doAfterDeleteTransactionImpl();				
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void beforeCreateTransaction(JsonObject paramsInfoObj)
	{
		if(isTransactionUpdatable()==false)
		{
			addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();		
			
		doBeforeCreateTransactionImpl(paramsInfoObj);		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		//setHasTransactionFailed(false);
	}
	protected void afterCreateTransaction(JsonObject paramsInfoObj)
	{
		/*
		if (isActionFromUI())
		{
			try
			{
				if (!isEntityAuditType(getManagedBeanName()))
				{
					// Refetch MQ Record as the session does not contain MessageQueueBean
					String sCurrentLCNo = getLcNoImpl();
					com.shoppingcart.bean.MessageQueue messageQueue = new com.shoppingcart.bean.MessageQueue();
						if (isExists(sCurrentLCNo))
						{
							com.shoppingcart.controller.forms.impl.MessageQueueControllerImpl messageController = new com.shoppingcart.controller.forms.impl.MessageQueueControllerImpl();
							List<?> lst = messageController.retrieveUniqueTransaction("LcNo", sCurrentLCNo);
							messageQueue = (com.shoppingcart.bean.MessageQueue) lst.get(0);
							// Fetch newly created entity id 
							String sEntityId = getPKValue().toString();
							// Create a new MQ record 
							com.vw.runtime.rest.MessageQueueService mqservice = new com.vw.runtime.rest.MessageQueueService();
							String sMsgGroupName=getGroupName();
							if (!isExists(sMsgGroupName))
							{
								if (!isExists(messageQueue.getMsgGroup()))
								{
									sMsgGroupName="EXPORT";
								}
							}
							String sMsgDirection="I";
							String sMsgCategory="";
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "Orders", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
						}
				}
			}
			catch (Exception e)
			{
				writeToLog(CommonUtil.getStackTrace(e));
			}
		}
		*/
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterCreateTransactionImpl(paramsInfoObj);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isAuthorizationEnabled()==false)
		{
			doBeforeTransactionApproved();
		}
	}
	protected void afterCreateTransactionCommitted()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();
		doAfterCreateTransactionCommittedImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void beforeUpdateTransaction(JsonObject paramsInfoObject)
	{
		if(isTransactionUpdatable()==false)
		{
			addCustomResponse("This transaction cannot be updated. Check if there are any changes that need to be rolled back !!");			
		}
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();		
			
		//doBeforeUpdateTransactionImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected void afterUpdateTransaction(JsonObject paramsInfoObject)
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterUpdateTransactionImpl(paramsInfoObject);		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		if(isAuthorizationEnabled()==false)
		{
			doBeforeTransactionApproved();
		}
	}
	protected void afterUpdateTransactionCommitted()
	{
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		JsonObject dataObject = new JsonObject();
		doAfterUpdateTransactionCommittedImpl();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	protected HashMap<String, Object> getSearchParams()
	{
		debugCode("In getSearchParams() OrdersContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (OrdersSearch)getManagedBean("OrdersSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getUserCartId()))
			{
				searchParams.put(OrdersLabelLocal.getuserCartIdFieldName(),searchBeanLocal.getUserCartId());
			}	
			if (isExists(searchBeanLocal.getOrderNo()))
			{
				searchParams.put(OrdersLabelLocal.getorderNoFieldName(),searchBeanLocal.getOrderNo());
			}	
			if (isExists(searchBeanLocal.getOrderDate()))
			{
				searchParams.put(OrdersLabelLocal.getorderDateFieldName(),searchBeanLocal.getOrderDate());
			}	
			if (isExists(searchBeanLocal.getOrderAmount()))
			{
				searchParams.put(OrdersLabelLocal.getorderAmountFieldName(),searchBeanLocal.getOrderAmount());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(OrdersLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() OrdersContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues OrdersContollerBase");
		targetBean = (Orders)targetBean;((Orders)targetBean).setOrdersId(((Orders)sourceBean).getOrdersId());
				((Orders)targetBean).setUserCartId(((Orders)sourceBean).getUserCartId());
		((Orders)targetBean).setOrderNo(((Orders)sourceBean).getOrderNo());
		((Orders)targetBean).setOrderDate(((Orders)sourceBean).getOrderDate());
		((Orders)targetBean).setOrderAmount(((Orders)sourceBean).getOrderAmount());

		doAfterSetValues();
		debugCode("Out setValues OrdersContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.shoppingcart.bean.Orders entityBean = (Orders)entityObject;
	 	if (sFieldName.equalsIgnoreCase("ordersId")) 
	 	{
			return entityBean.getOrdersId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("UserCartId")) 
	 	{
			return entityBean.getUserCartId();
		}
	 	if (sFieldName.equalsIgnoreCase("OrderNo")) 
	 	{
			return entityBean.getOrderNo();
		}
	 	if (sFieldName.equalsIgnoreCase("OrderDate")) 
	 	{
			return entityBean.getOrderDate();
		}
	 	if (sFieldName.equalsIgnoreCase("OrderAmount")) 
	 	{
			return entityBean.getOrderAmount();
		}

	 return null;
	}
	
	
	protected void doEnrichValues(Boolean doUpdateRules)
	{
		JsonObject paramsInfoObject  = new JsonObject();
		doEnrichValues(doUpdateRules, paramsInfoObject);
	}
	protected void doEnrichValues(Boolean doUpdateRules, JsonObject paramsInfoObject)
	{
		if (doUpdateRules)
		{
			callBusinessUpdateRules();
		}	
		doEnrichValuesImpl(paramsInfoObject);
	}
	protected void doAfterEnrichValues()
	{
		doAfterEnrichValuesImpl();
		doCreateAuditRecord();
	}
	private void doCreateAuditRecord()
	{
		
	}	
	protected void doAfterSelectRow()
	{
		doAfterSelectRowImpl();
	}
	protected void doEnrichSystemValues(String sAction,String sNextStatus)
	{
		debugCode("In doEnrichSystemValues(String sAction) OrdersControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((Orders) localManagedBean).setVwLastModifiedDate(new Date());
		((Orders) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((Orders) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((Orders) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((Orders) localManagedBean).setVwTxnStatus("CREATED");
			((Orders) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((Orders) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((Orders) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) OrdersControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) OrdersControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) OrdersControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations OrdersControllerBase");
		//NG: Important comment
		//managedBean = (Orders) getManagedBean();
		localManagedBean = getLocalManagedBean();
		setPrivateManagedBean(localManagedBean);
		doDataTypeValidation();
		doLengthValidation();
		doMandatoryValidation();
		doAllowedValuesValidation();
		doAllowedDecimalValidation();
		if (!isFromChild())
		{
			doUniqueValidation();
		}	
		callBusinessValidateRules();
		doValidationsImpl();
		debugCode("Out doValidations OrdersControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation OrdersControllerBase");
		debugCode("Out doAllowedDecimalValidation OrdersControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation OrdersControllerBase");debugCode("Out doAllowedValuesValidation OrdersControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation OrdersControllerBase");
		debugCode("Out doMandatoryValidation OrdersControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation OrdersControllerBase");
				
		Integer sFieldValue2 = ((Orders) localManagedBean).getUserCartId();		Integer sFieldValue3 = ((Orders) localManagedBean).getOrderNo();		Date sFieldValue4 = ((Orders) localManagedBean).getOrderDate();Integer sFieldValue5 = ((Orders) localManagedBean).getOrderAmount();		
		if (!isLengthAllowed(sFieldValue2,"20")) addMaxLengthResponse(OrdersLabelLocal.getuserCartIdFieldName(),"20");
		if (!isLengthAllowed(sFieldValue3,"15")) addMaxLengthResponse(OrdersLabelLocal.getorderNoFieldName(),"15");
		if (!isLengthAllowed(sFieldValue4,"10")) addMaxLengthResponse(OrdersLabelLocal.getorderDateFieldName(),"10");
		if (!isLengthAllowed(sFieldValue5,"50")) addMaxLengthResponse(OrdersLabelLocal.getorderAmountFieldName(),"50");
debugCode("Out doLengthValidation OrdersControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation OrdersControllerBase");
		debugCode("Out doDataTypeValidation OrdersControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation OrdersContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation OrdersContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel OrdersContollerBase");
		String sLabel = new OrdersLabel().getLabel(sResponseField); 
		debugCode("Out getLabel OrdersContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int ordersId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("ordersId", ordersId);
			JsonObject responseData = retrieveOrders(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Orders' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject ordersDataObject = responseData.get("ordersDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, ordersDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Orders' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveOrders(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer ordersId = -1;
		if(requestParams.has("ordersId"))
		{
			ordersId = requestParams.get("ordersId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("ordersId", String.valueOf(ordersId));
				String userCartId = "";
		if(requestParams.has("userCartId"))
		{
			paramsMap.put("userCartId", requestParams.get("userCartId").getAsString());
		}
		String orderNo = "";
		if(requestParams.has("orderNo"))
		{
			paramsMap.put("orderNo", requestParams.get("orderNo").getAsString());
		}
		String orderDate = "";
		if(requestParams.has("orderDate"))
		{
			paramsMap.put("orderDate", requestParams.get("orderDate").getAsString());
		}
		String orderAmount = "";
		if(requestParams.has("orderAmount"))
		{
			paramsMap.put("orderAmount", requestParams.get("orderAmount").getAsString());
		}

		JsonObject ordersListObject = retrieveOrdersList(paramsMap);
		if(ordersListObject!=null && ordersListObject.has("success") && ordersListObject.get("success").getAsInt()==1)
		{
			JsonArray ordersList = ordersListObject.get("ordersList").getAsJsonArray();
			if(ordersList.size()>0)
			{
				dataObject.add("ordersDataObject", ordersList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Orders' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getOrders(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			Map<String, String> paramsMap = new HashMap<String, String>();
						String userCartId = "";
			if(searchParams.has("userCartId"))
			{
				paramsMap.put("userCartId", searchParams.get("userCartId").getAsString());
			}
			String orderNo = "";
			if(searchParams.has("orderNo"))
			{
				paramsMap.put("orderNo", searchParams.get("orderNo").getAsString());
			}
			String orderDate = "";
			if(searchParams.has("orderDate"))
			{
				paramsMap.put("orderDate", searchParams.get("orderDate").getAsString());
			}
			String orderAmount = "";
			if(searchParams.has("orderAmount"))
			{
				paramsMap.put("orderAmount", searchParams.get("orderAmount").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject ordersListObject = retrieveOrdersList(paramsMap);
			if(ordersListObject!=null && ordersListObject.has("success") && ordersListObject.get("success").getAsInt()==1)
			{
				JsonArray ordersList = ordersListObject.get("ordersList").getAsJsonArray();
				if(ordersList.size()>0)
				{
					dataObject.add("orders", ordersList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Orders' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getOrdersInJson(int ordersId)
	{
		JsonObject OrdersData = null;
		List<Integer> ordersIdsList = new ArrayList<>();
		ordersIdsList.add(ordersId);
		JsonArray ordersList = getOrdersListInJson(ordersIdsList);
		if(ordersList!=null && ordersList.size()>0)
		{
			OrdersData = ordersList.get(0).getAsJsonObject();
		}
		return OrdersData;
	}
	public JsonArray getOrdersListInJson(List<Integer> ordersIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray ordersObjectsList = new JsonArray();
		JsonObject ordersListObject = retrieveOrdersList(paramsMap, ordersIdsList);
		if(ordersListObject!=null && ordersListObject.has("success") && ordersListObject.get("success").getAsInt()==1)
		{
			JsonArray ordersList = ordersListObject.get("ordersList").getAsJsonArray();
			for (int i =0; i< ordersList.size(); i++)
			{
				JsonObject ordersDataObject = ordersList.get(i).getAsJsonObject();
				int ordersId = ordersDataObject.get("ordersId").getAsInt();
				
				ordersObjectsList.add(ordersDataObject);
			}
		}
		return ordersObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray ordersList)
	{
		String errorMessage = "ordersList: \n";
		for (int i =0; i< ordersList.size(); i++)
		{
			JsonObject ordersDataObject = ordersList.get(i).getAsJsonObject();
			JsonObject orders = ordersDataObject.get("dataObject").getAsJsonObject();if(!ordersDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "orders could not be uploaded verify data \n"; 
			}
			else if(ordersDataObject.has("isSuccessfullyUploaded") 
					&& ordersDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += ordersDataObject.get("errorMessage").getAsString() +"\n"; 
			}
		    
		}
		return errorMessage;		
	}
	public String getLoginBasedWhereClause(java.util.Map<String, String> paramsMap)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		else if("Orders".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND ordersId = :ordersId ";
			return loginBasedWhereClause;
		}return "";		
	}
	public void setLoginBasedWhereClauseParams(java.util.Map<String, String> paramsMap, Query query)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		else if("Orders".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("ordersId", userId);
		}
		
	}
	public String getParentFilterClauseForOrders(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";return parentFilterClause;
	}
	public String getLookupDisplayFilterClause()
	{
		String lookupDisplayFilterClause = "";
		String lookupDisplayQueryColumn = " AND concat(";
		int i= 0;
				if(i > 0)
		{
			lookupDisplayQueryColumn +=" ,";
		}
		lookupDisplayQueryColumn += "userCartId";
		i++;
 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForOrders(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveOrdersList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> ordersIdsList = new ArrayList<>();
		if(paramsMap.containsKey("ordersId"))
		{
			int ordersId = Integer.parseInt(paramsMap.get("ordersId"));
			if(ordersId>0)
			{
				ordersIdsList.add(ordersId);
			}
		}
		return retrieveOrdersList(paramsMap, ordersIdsList);
	}
	public JsonObject retrieveOrdersList(java.util.Map<String, String> paramsMap, List<Integer> ordersIdsList)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Transaction tx = getDBSession().getTransaction();
			if (!tx.isActive())
			{
				tx.begin();
			}
			int noOfRecordsAlreadyFetched = 0;
			int noOfRecordsToFetch = 0;
			String overrideWhereClause="";
			String whereClause ="";
			String orderByClause = "";
			String overrideOrderByClause="";
			String lookupDisplayPrefix="";
			String txnStatus="";
			JsonArray additionalParamsList = new JsonArray();
			if(paramsMap.containsKey("noOfRecordsAlreadyFetched"))
			{
				noOfRecordsAlreadyFetched = Integer.parseInt(paramsMap.get("noOfRecordsAlreadyFetched"));				
			}
			if(paramsMap.containsKey("noOfRecordsToFetch"))
			{
				noOfRecordsToFetch = Integer.parseInt(paramsMap.get("noOfRecordsToFetch"));				
			}
			if(paramsMap.containsKey("overrideWhereClause"))
			{
				overrideWhereClause = paramsMap.get("overrideWhereClause");				
			}
			if(paramsMap.containsKey("whereClause"))
			{
				whereClause = paramsMap.get("whereClause");				
			}
			if(paramsMap.containsKey("orderByClause"))
			{
				orderByClause = paramsMap.get("orderByClause");				
			}
			if(paramsMap.containsKey("overrideOrderByClause"))
			{
				overrideOrderByClause = paramsMap.get("overrideOrderByClause");				
			}
			if(paramsMap.containsKey("lookupDisplayPrefix"))
			{
				lookupDisplayPrefix = paramsMap.get("lookupDisplayPrefix");
			}
			if(paramsMap.containsKey("additionalParamsList"))
			{
				additionalParamsList = new Gson().fromJson(paramsMap.get("additionalParamsList"), JsonArray.class);
			}
			if(paramsMap.containsKey("txnStatus"))
			{
				txnStatus = paramsMap.get("txnStatus");
			}
						String userCartId = paramsMap.get("userCartId");
			if(userCartId==null)
			{
				userCartId = "";
			}
			String orderNo = paramsMap.get("orderNo");
			if(orderNo==null)
			{
				orderNo = "";
			}
			String orderDate = paramsMap.get("orderDate");
			if(orderDate==null)
			{
				orderDate = "";
			}
			String orderAmount = paramsMap.get("orderAmount");
			if(orderAmount==null)
			{
				orderAmount = "";
			}
String hql = "FROM Orders where 1 = 1 ";
			String orderByClauseText = "  ";
			String ordersIdFilterClass = "";
			if (ordersIdsList != null && ordersIdsList.size() > 0)
			{
				ordersIdFilterClass = " AND ordersId in (:idsList) ";
			}
						String userCartIdFilterClass = "";
			if (userCartId.length() > 0)
			{			
				
				userCartIdFilterClass = " AND userCartId = :userCartId ";
				
			}
			String orderNoFilterClass = "";
			if (orderNo.length() > 0)
			{			
				
				orderNoFilterClass = " AND orderNo = :orderNo ";
				
			}
			String orderDateFilterClass = "";
			if (orderDate.length() > 0)
			{
				
				orderDateFilterClass = " AND orderDate = :orderDate ";			
				
			}
			String orderAmountFilterClass = "";
			if (orderAmount.length() > 0)
			{			
				
				orderAmountFilterClass = " AND orderAmount = :orderAmount ";
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForOrders(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					ordersIdFilterClass +
										userCartIdFilterClass +
					orderNoFilterClass +
					orderDateFilterClass +
					orderAmountFilterClass +

					lookupDisplayFilterClause+
					txnStatusFilterClass;
			if(overrideWhereClause.equalsIgnoreCase("Yes"))
			{
				attributesFilterClause +=  whereClause;
			}
			String whereClauseText = 
			getLoginBasedWhereClause(paramsMap) +
			attributesFilterClause;
			if(overrideOrderByClause.equalsIgnoreCase("Yes"))
			{
				orderByClauseText =  orderByClause;
			}
			hql += whereClauseText +
			doGetUpdatedQueryStringForSearchImpl(hql) +
			orderByClauseText;
			Query query = getDBSession().createQuery(hql);
			if (ordersIdsList != null && ordersIdsList.size() > 0)
			{
				query.setParameterList("idsList", ordersIdsList);
			}
						if (userCartId.length() > 0)
			{			
				
				query.setParameter("userCartId", Integer.parseInt(userCartId));
				
			}
			if (orderNo.length() > 0)
			{			
				
				query.setParameter("orderNo", Integer.parseInt(orderNo));
				
			}
			if (orderDate.length() > 0)
			{
				
				query.setParameter("orderDate", CommonUtil.getDBFormattedDate(orderDate));			
				
			}
			if (orderAmount.length() > 0)
			{			
				
				query.setParameter("orderAmount", Integer.parseInt(orderAmount));
				
			}
if(lookupDisplayPrefix.length() > 0)
			{
				lookupDisplayPrefix = "%"+lookupDisplayPrefix+"%";
				query.setParameter("lookupDisplayPrefix", lookupDisplayPrefix);
			}
			if(txnStatus.length() > 0)
			{
				query.setParameter("txnStatus", txnStatus);
			}
	        for(int i =0; i< additionalParamsList.size(); i++)
	        {
	        	JsonObject additonalParamInfo = additionalParamsList.get(i).getAsJsonObject();
	        	String additionalParameterName = additonalParamInfo.get("parameterName").getAsString();
				query.setParameter(additionalParameterName, paramsMap.get(additionalParameterName));
	        }
			setLoginBasedWhereClauseParams(paramsMap, query);
	    	setParentFilterClauseParamsForOrders(paramsMap, query);
			java.util.Map<String, Object> queryParamsMap = new java.util.HashMap<String, Object>(); 
			JsonObject userSessionInfo = getUserSessionInfo();			
			String loggedInEmployeeId = "-1";//userSessionInfo.get("loggedInEmployeeId").getAsString();			
			Object loggedInEmployeeIdObj= loggedInEmployeeId;  
			queryParamsMap.put("loggedInEmployeeId", loggedInEmployeeIdObj);
			doUpdateQueryWithParameterValuesImpl(query, queryParamsMap);			
			if(noOfRecordsToFetch>0)
			{
				query.setMaxResults(noOfRecordsToFetch);
				query.setFirstResult(noOfRecordsAlreadyFetched);				
			}
			List resultsList = query.list();
			JsonArray ordersList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Orders orders = (Orders) it.next();
				JsonObject ordersDataObject = orders.getDataObject(getDBSession());
				ordersDataObject.addProperty("nextAction", getActionForCurrentStatus(orders.getVwTxnStatus()));
				ordersList.add(ordersDataObject);
				doAfterSearchResultRowAddedImpl(ordersDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from Orders where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (ordersIdsList != null && ordersIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", ordersIdsList);
				}
								if (userCartId.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("userCartId", Integer.parseInt(userCartId));
					
				}
				if (orderNo.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("orderNo", Integer.parseInt(orderNo));
					
				}
				if (orderDate.length() > 0)
				{
					
					countQuery.setParameter("orderDate", CommonUtil.getDBFormattedDate(orderDate));
					
					
					
					
					
					
					
					
					
					
				}
				if (orderAmount.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("orderAmount", Integer.parseInt(orderAmount));
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForOrders(paramsMap, countQuery);
		        for(int i =0; i< additionalParamsList.size(); i++)
		        {
		        	JsonObject additonalParamInfo = additionalParamsList.get(i).getAsJsonObject();
		        	String additionalParameterName = additonalParamInfo.get("parameterName").getAsString();
		        	countQuery.setParameter(additionalParameterName, paramsMap.get(additionalParameterName));
		        }
				if(txnStatus.length() > 0)
				{
					countQuery.setParameter("txnStatus", txnStatus);
				}
				Long matchingSearchResultsCount = (Long) countQuery.uniqueResult();
				dataObject.addProperty("matchingSearchResultsCount", matchingSearchResultsCount);
			}
			dataObject.add("ordersList",   ordersList);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject getDashboardGraphsData()
	{
		JsonObject dataObject = new JsonObject();
		Transaction tx = getDBSession().getTransaction();
		if (!tx.isActive())
		{
			tx.begin();
		}
		String hql = " SELECT concat(month(E.vwCreationDate), '/', year(E.vwCreationDate)), count(*) "
				+ "   from Orders E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
		Query query = getDBSession().createQuery(hql);
		List resultsList = query.list();
		JsonArray monthlyTransactionList = new JsonArray();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Object[] row = (Object[]) it.next();
			JsonObject monthlyTransactionInfo = new JsonObject();
			monthlyTransactionInfo.addProperty("monthName", (String)row[0]);
			monthlyTransactionInfo.addProperty("transactionCount", (Long)row[1]);
			monthlyTransactionList.add(monthlyTransactionInfo);
		}
		dataObject.add("monthlyTransactionList", monthlyTransactionList);
		hql = " SELECT E.vwTxnStatus, count(*) "
				+ "   from Orders E GROUP BY E.vwTxnStatus ";
		query = getDBSession().createQuery(hql);
		resultsList = query.list();
		JsonArray transactionStatusList = new JsonArray();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Object[] row = (Object[]) it.next();
			JsonObject monthlyTransactionInfo = new JsonObject();
			monthlyTransactionInfo.addProperty("statusDescription", (String)row[0]);
			monthlyTransactionInfo.addProperty("transactionCount", (Long)row[1]);
			transactionStatusList.add(monthlyTransactionInfo);
		}
		dataObject.add("transactionStatusList", transactionStatusList);
		//dataObject.add("monthlyTransactionList", new JsonArray());
		//dataObject.add("transactionStatusList", new JsonArray());
		dataObject.addProperty("success", 1);
		return dataObject;
	}
	public JsonObject retrieveDependentOrdersList(java.util.Map<String, String> paramsMap)
	{
		return retrieveOrdersList(paramsMap);
	}
	public Orders getOrdersByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from Orders where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Orders orders = (Orders) it.next();
			return orders;
		}
		return null;
	}
	private void callBusinessValidateRules()
	{
		loadInitObjects();
	
	}	
	private void callBusinessUpdateRules()
	{
		loadInitObjects();
		//$$INCLUDE_UPDATE_RULES$$
		Orders orders = (Orders)getManagedBean();
		JsonObject ordersDataObject = orders.getDataObject(getDBSession());copyOrdersFromJson(orders, ordersDataObject);
		setManagedBean(orders);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData OrdersContollerBase");
			Orders currentBean = (Orders)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		currentBean.setUserCartId(1);currentBean.setOrderNo(1);currentBean.setOrderDate(new Date());currentBean.setOrderAmount(1);

		setManagedBean(currentBean);
		debugCode("Out setTestData OrdersContollerBase");
	}
	// end Test Data
	public void copyOrdersFromJson(Orders orders, JsonObject ordersDataObject)
	{
		copyOrdersFromJson(orders, ordersDataObject, false);
	}
	public void copyOrdersFromJson(Orders orders, JsonObject ordersDataObject, boolean excludePasswordFields)
	{	
				
		if(ordersDataObject.has("userCartId"))
		{
			Integer userCartId = null;
			try
			{
			 	userCartId = ordersDataObject.get("userCartId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(userCartId != null)
			{
				orders.setUserCartId(userCartId);
			}
		}if(ordersDataObject.has("orderNo"))
		{
			Integer orderNo = null;
			try
			{
			 	orderNo = ordersDataObject.get("orderNo").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(orderNo != null)
			{
				orders.setOrderNo(orderNo);
			}
		}if(ordersDataObject.has("orderDate"))
		{
			String orderDate = ordersDataObject.get("orderDate").getAsString();
			if(orderDate.length() > 0)
			{
				try
				{
					orders.setOrderDate(new java.text.SimpleDateFormat("dd/MM/yyyy").parse(orderDate));
				}
				catch (Exception e)
				{
					setTransactionFailureMessage("Your request could not be processed as enter valid OderDate");
				}
			}
		}if(ordersDataObject.has("orderAmount"))
		{
			Integer orderAmount = null;
			try
			{
			 	orderAmount = ordersDataObject.get("orderAmount").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(orderAmount != null)
			{
				orders.setOrderAmount(orderAmount);
			}
		}
		
	}
		
	public JsonObject createOrders(JsonObject ordersDataObject) throws Exception
	{
		return createOrders(ordersDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject ordersDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createOrders(JsonObject ordersDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		Orders orders = new Orders();
		setLoginBasedValues(paramsInfoObj, ordersDataObject);
		Session session = getDBSession();				
		copyOrdersFromJson(orders, ordersDataObject);
		if(ordersDataObject.has("legacyRecordId"))
		{
			orders.setLegacyRecordId(ordersDataObject.get("legacyRecordId").getAsInt());
		}
				orders.setVwCreatedBy(createdBy);
		orders.setVwCreationDate(new Date());
		setPrivateManagedBean(orders);
		setManagedBean("OrdersBean", orders);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		orders = (Orders) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("ordersId", orders.getOrdersId());
			JsonObject ordersObj = orders.getDataObject(getDBSession());
			ordersObj.addProperty("nextAction", getActionForCurrentStatus(orders.getVwTxnStatus()));
			dataObject.add("ordersDataObject", ordersObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateOrders(JsonObject ordersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer ordersId = ordersDataObject.get("ordersId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("ordersId", ordersId);
		JsonObject responseData = retrieveOrders(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Orders' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Orders orders = (Orders) session.get(Orders.class, ordersId);
		if(orders == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(orders.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromOrders(ordersDataObject);
		boolean excludePasswordFields = true;
		copyOrdersFromJson(orders, ordersDataObject, excludePasswordFields);setPrivateManagedBean(orders);
		setManagedBean("OrdersBean", orders);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		orders.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
		{
			update();
		}
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction updated Successfully");
			dataObject.addProperty("ordersId", ordersId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromOrders(JsonObject ordersDataObject) throws Exception
	{
	}
	public JsonObject deleteOrders(JsonObject ordersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer ordersId = ordersDataObject.get("ordersId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("ordersId", ordersId);
		JsonObject responseData = retrieveOrders(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Orders' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Orders orders = (Orders) session.get(Orders.class, ordersId);setPrivateManagedBean(orders);
		setManagedBean("Orders", orders);
		if (getHasTransactionFailed() == false)
		{
			delete();
		}
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction deleted Successfully");
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject fetchOrdersDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		Orders orders = new Orders();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(orders);
			setManagedBean("Orders", orders);
			doAfterOrdersLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orders", orders.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Default information could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject fetchOrdersTestData(int obj, JsonObject ordersDataObject) throws Exception
	{
		Session session = getDBSession();
		Orders orders = new Orders();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrdersFromJson(orders, ordersDataObject);
			setPrivateManagedBean(orders);
			setManagedBean("Orders", orders);
			doAfterOrdersLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orders", orders.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Default information could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject lookupRowSelectedForOrders(JsonObject ordersDataObject) throws Exception
	{
		Orders orders = new Orders();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrdersFromJson(orders, ordersDataObject);	String attributeName = ordersDataObject.get("attributeName").getAsString();
			Integer attributeId = ordersDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(orders);
			setManagedBean("Orders", orders);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orders", orders.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Default information could not be loaded.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Lookup nformation could not be loaded.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}		
	}
	public JsonObject selectOptionChangedForOrders(JsonObject ordersDataObject) throws Exception
	{
		Orders orders = new Orders();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrdersFromJson(orders, ordersDataObject);	
			String attributeName = ordersDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(orders);
			setManagedBean("Orders", orders);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orders", orders.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", "Information could not be refreshed.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Information could not be refreshed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}		
	}
	public JsonObject doExecuteCustomAPI(JsonObject ordersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer ordersId = ordersDataObject.get("ordersId").getAsInt();
			String customEventName = ordersDataObject.get("customEventName").getAsString();
			Orders orders = (Orders) session.get(Orders.class, ordersId);
			setPrivateManagedBean(orders);
			setManagedBean("OrdersBean", orders);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orders", orders.getDataObject(getDBSession()));
				dataObject.addProperty("success", 1);
				dataObject.addProperty("alert", customEventName + " processed successfully.");
				dataObject.add("additionalInfo", getAdditionalInfo());
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.add("additionalInfo", getAdditionalInfo());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processsed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		finally
		{
			finalizeTransaction();
		}
	}
	public JsonObject executeAuthorisationOnOrders(JsonObject ordersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer ordersId = ordersDataObject.get("ordersId").getAsInt();
			String currentStatus = ordersDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(ordersDataObject.has("currentAction"))
			{
				currentAction = ordersDataObject.get("currentAction").getAsString();
			}
			Orders orders = (Orders) session.get(Orders.class, ordersId);
			setPrivateManagedBean(orders);
			setManagedBean("OrdersBean", orders);
			if(orders.getIsRequestUnderProcesss() == 1)
			{
				setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
				dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			if(!isActionAllowedOnTransaction(currentAction))
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			if(currentAction.equalsIgnoreCase(ACTION_REJECT))
			{
				executeAction(orders, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(orders, "ClassInfoBean", currentStatus);
			}
//			executeAction(orders, "OrdersBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedordersDataObject = orders.getDataObject(getDBSession());
				updatedordersDataObject.addProperty("nextAction", getActionForCurrentStatus(orders.getVwTxnStatus()));
				dataObject.add("orders", updatedordersDataObject);
				if(!currentAction.equalsIgnoreCase(ACTION_REJECT))
				{
					currentAction = getActionForCurrentStatus(currentStatus);
				}
				dataObject.addProperty("alert", "Transaction "+ currentAction + " successfully.");
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			else
			{
				dataObject.addProperty("alert", getTransactionFailureMessage());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processsed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public boolean isActionAllowedOnTransaction(String currentAction)
	{
		Orders orders = (Orders) getManagedBean();
		String currentStatus = orders.getVwTxnStatus();
		if(currentStatus.equalsIgnoreCase("REJECTED"))
		{
			setTransactionFailureMessage("Your request could not be processed as transaction already rejected.");
			return false;
		}
		if(currentAction.equalsIgnoreCase(ACTION_REJECT))
		{
			if(!(currentStatus.equalsIgnoreCase("CREATED")
					||currentStatus.equalsIgnoreCase("MODIFIED")))
			{
				setTransactionFailureMessage("Your request could not be processed as transaction already rejected.");
				return false;
			}
		}
		return true;
	}
	
	
	public JsonObject downloadOrdersData() throws Exception
	{
		return downloadOrdersData(null);
	}
	public JsonObject downloadOrdersData(HSSFWorkbook workbook) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		String filePath = com.shoppingcart.util.SettingsUtil.getProjectFilesPath();
		try
		{
			
			boolean saveWorkbook = false;
			HSSFSheet sheet = null;
			if(workbook == null)
			{
				String projectTemplatesPath = com.shoppingcart.util.SettingsUtil.getProjectTemplatesPath();
				FileInputStream file = new FileInputStream(new File(projectTemplatesPath+"DownloadTemplate.xls"));
				workbook = new HSSFWorkbook(file);
				saveWorkbook = true;
			}
			sheet = workbook.cloneSheet(0);
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Orders");
			HSSFFont defaultFont = workbook.createFont();
			defaultFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			HSSFFont boldfont = workbook.createFont();
			boldfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			CellStyle dataStyle = workbook.createCellStyle(); // this style is used for the cells where bold text is needed other than header
			dataStyle.setFont(defaultFont);
			dataStyle.setWrapText(true);
			CellStyle headerStyle = sheet.getRow(0).getCell(0).getCellStyle();
			Cell cell;
			Row row;
			
			JsonObject rowColumnIndexObject = new JsonObject();
			rowColumnIndexObject.addProperty("currentRowPosition", 0);
			rowColumnIndexObject.addProperty("entityDataCellIndex", 0);
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			int headerCellCount = entityDataCellIndex;
			int columnWidth = 3000;
			String headerName = "";
			row = sheet.createRow(currentRowPosition++);cell = row.createCell(headerCellCount++);
			headerName = "S.No";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
			cell = row.createCell(headerCellCount++);
			headerName = "ordersId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "userCartId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "orderNo";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "orderDate";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "orderAmount";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);

			
			headerStyle = workbook.createCellStyle(); // this style is used for table header in the report
			headerStyle.setFont(boldfont);
			headerStyle.setWrapText(true);
			Transaction tx = getDBSession().getTransaction();
			if(!tx.isActive())
			{
				tx.begin();
			}
			String hql = "From Orders ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				Orders orders = (Orders) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer ordersId = orders.getOrdersId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(ordersId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer userCartId = orders.getUserCartId();
				if(userCartId!=null)
				{
					cell.setCellValue(String.valueOf(userCartId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer orderNo = orders.getOrderNo();
				if(orderNo!=null)
				{
					cell.setCellValue(String.valueOf(orderNo));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);	
				Date orderDate = orders.getOrderDate();
				if(orderDate!=null)
				{
					cell.setCellValue(CommonUtil.getDateInRegularDateFormat(orderDate));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer orderAmount = orders.getOrderAmount();
				if(orderAmount!=null)
				{
					cell.setCellValue(String.valueOf(orderAmount));
				}

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "Orders.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "Orders.xls";
				FileOutputStream out = new FileOutputStream(new File(savedFileName));
				workbook.write(out);
				out.close();
				int fileId = CommonUtil.saveFile(fileName, savedFileName, session);
				if(fileId < 0)
				{
					dataObject.addProperty("alert", "Your request could not be processed.");
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				dataObject.addProperty("fileId", fileId);
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject uploadOrdersData(JsonObject ordersDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = ordersDataObject.get("fileId").getAsInt();
		String inputFilesZip = ordersDataObject.get("inputFilesZip").getAsString();
		String savedFileName = CommonUtil.getFilePath("", fileId, getDBSession());
		FileInputStream file = new FileInputStream(new File(savedFileName));
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		String inputFilesPath = "";
		if(inputFilesZip.length() > 0)
		{
			String zipFilePath = CommonUtil.getFilePath(inputFilesZip, -1, getDBSession());
	        String extractedZipFilePath = FilenameUtils.removeExtension(zipFilePath);
	        int isExtracted = ZipExtraction.extractZipFile(extractedZipFilePath, zipFilePath);
	        if(isExtracted != 1)
	        {
	    		dataObject.addProperty("alert", "Orders Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		ordersDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadOrdersData(workbook, ordersDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Orders Data uploaded successfully.");
		dataObject.addProperty("success", 1);
		dataObject.addProperty("fileId", fileId);
		return dataObject;
	}
	public HashMap<Integer, String> getHeaderRowColumnNamesMap(Row headerRow, int entityDataCellIndex)
	{
		HashMap<Integer, String> cellIndexParameterMap = new HashMap<Integer, String>();
		boolean continueReadingRow = true;
		int headerRowCellIndex = entityDataCellIndex + 1;
		while (continueReadingRow)
		{
			Cell headerCell = headerRow.getCell(headerRowCellIndex);
			boolean cellExist = false;
			if(headerCell != null)
			{
				String parameterKeyName = headerCell.getStringCellValue();
				if(parameterKeyName != null && parameterKeyName.length() > 0)
				{
					cellIndexParameterMap.put(headerRowCellIndex, parameterKeyName);
					headerRowCellIndex++;
					cellExist = true;
				}
			}
			if(cellExist == false)
			{
				continueReadingRow = false;
			}
		}
		return cellIndexParameterMap;		
	}
	public JsonObject uploadOrdersData(HSSFWorkbook workbook, JsonObject ordersDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("Orders");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.shoppingcart.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = ordersDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = ordersDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = ordersDataObject.get("fileId").getAsInt();
				//savedFileName = CommonUtil.fileIDAndNamesMap.get(fileId);
				savedFileName = CommonUtil.getFilePath("", fileId, session);
				FileInputStream file = new FileInputStream(new File(savedFileName));
				workbook = new HSSFWorkbook(file);
				saveWorkbook = true;
			}
			HSSFCellStyle successCellStyle = workbook.createCellStyle();
			successCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
			successCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont successFontColor = workbook.createFont();
			successFontColor.setColor(HSSFColor.GREEN.index);
			successCellStyle.setFont(successFontColor);
			HSSFCellStyle errorCellStyle = workbook.createCellStyle();
			errorCellStyle.setFillForegroundColor(HSSFColor.LIME.index);
			errorCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			HSSFFont errorFontColor = workbook.createFont();
			errorFontColor.setColor(HSSFColor.RED.index);
			errorCellStyle.setFont(errorFontColor);
			JsonObject dataUploadInfo = processExcelSheetData(workbook, sheet, successCellStyle, errorCellStyle, areSourceDestinationSame, inputFilesPath);			
			if(dataUploadInfo!=null && dataUploadInfo.has("dataListToRetry") && dataUploadInfo.get("dataListToRetry").isJsonNull()==false)
			{
				int retryCount = 0;
				JsonArray dataListToRetry = dataUploadInfo.get("dataListToRetry").getAsJsonArray();
				dataObject.add("dataListToRetry", dataListToRetry);			
				if(dataUploadInfo.has("retryCount") && dataUploadInfo.get("retryCount").isJsonNull()==false)
				{
					retryCount = dataUploadInfo.get("retryCount").getAsInt();
				}				
				dataObject.addProperty("retryCount", retryCount);
			}
			if(saveWorkbook == true)
			{
				FileOutputStream out = new FileOutputStream(new File(savedFileName));
				workbook.write(out);
				out.close();
				dataObject.addProperty("alert", "Orders Data uploaded successfully.");
				dataObject.addProperty("fileId", fileId);
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject processExcelSheetData(HSSFWorkbook workbook, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, Integer areSourceDestinationSame, String inputFilesPath) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			int statusCellIndex = 50;
			int errorCount = 0;
			Cell lastCell = null;
			Row row = null;
			int totalDefinedRowsInSheet = sheet.getLastRowNum() + 1;
			int currentRowPosition = 0;
			int entityDataCellIndex = 0;
			int pendingRecordsCount = 0;
			int batchsize = 100;
			Row headerRow = sheet.getRow(currentRowPosition);
			HashMap<Integer, String> cellIndexParameterMap = getHeaderRowColumnNamesMap(headerRow, entityDataCellIndex);
			currentRowPosition++;
			JsonObject rowColumnIndexObject = new JsonObject();
			rowColumnIndexObject.addProperty("entityDataCellIndex", entityDataCellIndex);
			rowColumnIndexObject.addProperty("areSourceDestinationSame", areSourceDestinationSame);
			rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			JsonArray dataListToRetry = new JsonArray();
			JsonObject orders = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject ordersListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray ordersList = ordersListObj.get("ordersList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadOrdersList(ordersList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(ordersList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(ordersList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
			}
			dataObject.addProperty("success", 1);
			dataObject.add("dataListToRetry", dataListToRetry);			
			dataObject.addProperty("retryCount", totalRetryCount);			
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject fetchData(HSSFWorkbook workbook, HSSFSheet sheet, int totalDefinedRowsInSheet, int batchsize, JsonObject rowColumnIndexObject, HashMap<Integer, String> cellIndexParameterMap, Integer areSourceDestinationSame,String inputFilesPath) throws Exception	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{			
			HSSFCellStyle successCellStyle = workbook.createCellStyle();
			HSSFCellStyle errorCellStyle = workbook.createCellStyle();
			int statusCellIndex = 50;
			JsonArray ordersList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject orders = new JsonObject();
			Row headerRow = null;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				row = sheet.getRow(currentRowPosition);
				rowFirstCellValue = row.getCell(entityDataCellIndex).getStringCellValue();
				dependentEntityName = row.getCell(entityDataCellIndex+1).getStringCellValue();
			    
				if(pendingRecordsCount == batchsize && batchsize>0)
				{
					break;
				}
				JsonObject ordersUploadObj	= new JsonObject();
				ordersUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					orders = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("ordersId"))
						{
							String ordersId = row.getCell(cellIndex++).getStringCellValue();
							if(ordersId != null && ordersId.trim().length() > 0)
							{
								try
								{
									Integer iOrdersId = Integer.parseInt(ordersId);
									if(areSourceDestinationSame == 1)
									{
										Orders ordersObj = (Orders)session.get(Orders.class, iOrdersId);
										if(ordersObj != null)
										{ 
											orders.addProperty("ordersId", iOrdersId);
										}
										else
										{
											ordersUploadObj.addProperty("isDataFetched", 0);
											ordersUploadObj.addProperty("msg", "This Orders could not be uploaded as there appears to be some problem with the data(Orders Id is not exist). ");
											continue;
										}
									}
									else
									{
										Orders ordersObj = getOrdersByLegacyRecordId(session, iOrdersId);
										if(ordersObj != null)
										{ 
											orders.addProperty("ordersId", ordersObj.getOrdersId());
										}
										orders.addProperty("legacyRecordId", iOrdersId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									ordersUploadObj.addProperty("isDataFetched", 0);
									ordersUploadObj.addProperty("msg", "This Orders could not be uploaded as there appears to be some problem with the data(Orders Id). ");
									continue;
								}
							}
						}
						else
						{
							String parameterValue = row.getCell(cellIndex++).getStringCellValue();
							if(inputFilesPath.length() > 0)
							{
								
							}
							orders.addProperty(parameterName, parameterValue);
						}
					}
					ordersUploadObj.add("dataObject", orders);		    
					ordersList.add(ordersUploadObj);
					pendingRecordsCount++;
					
				}
				catch (Exception e)
				{
					writeToLog(CommonUtil.getStackTrace(e));
					rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
					continue;
				}
			}
			rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			dataObject.add("ordersList", ordersList);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public int  areSomeRecordsUploaded(JsonObject previousRetryCountMap, JsonObject retryCountMap) throws Exception
	{
		int previousRetryCount = 0;
		int retryCount = 0;
		int areSomeRecordsUploaded = 0;
		if(previousRetryCountMap.has("Orders") && previousRetryCountMap.get("Orders").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("Orders").getAsInt();
		}
		if(retryCountMap.has("Orders") && retryCountMap.get("Orders").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("Orders").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForOrdersList(JsonArray ordersList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < ordersList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject ordersDataObject = ordersList.get(i).getAsJsonObject();
			JsonObject orders = ordersDataObject.get("dataObject").getAsJsonObject();
			if(ordersDataObject.has("retryUpload") && ordersDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = ordersDataObject.get("retryUpload").getAsInt();
			}
			if(ordersDataObject.has("retryChildEntitiesUpload") && ordersDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = ordersDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(ordersDataObject.has("partialUploadUnderProcess") && ordersDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = ordersDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("Orders", retryCount);
	}
	public JsonObject uploadOrdersList(JsonArray ordersList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(ordersList);
			if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
			{
				dataObject.addProperty("alert", responseData.get("alert").getAsString());
				dataObject.addProperty("success", 0);
				return dataObject;
			}
			int retryCount = responseData.get("retryCount").getAsInt();
			dataObject.addProperty("success", 1);
			dataObject.addProperty("retryCount", retryCount);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject updateStatusMsg(JsonArray ordersList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < ordersList.size(); i++)
			{
				JsonObject ordersDataObject = ordersList.get(i).getAsJsonObject();
				JsonObject orders = ordersDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = ordersDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = ordersDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = ordersDataObject.get("errorMessage").getAsString();
				row = sheet.getRow(dataRowIndex);
				lastCell = row.createCell(statusCellIndex);
				if(isSuccessfullyUploaded == 1)
				{
					lastCell.setCellStyle(successCellStyle);
				}
				else
				{
					lastCell.setCellStyle(errorCellStyle);
				} 
				lastCell.setCellValue(errorMessage);
			    
			}
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject uploadData(JsonArray ordersList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < ordersList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject ordersDataObject = ordersList.get(i).getAsJsonObject();
				JsonObject orders = ordersDataObject.get("dataObject").getAsJsonObject();
				ordersDataObject.addProperty("retryUpload", 0);
				ordersDataObject.addProperty("retryChildEntitiesUpload", 0);
				ordersDataObject.addProperty("partialUploadUnderProcess", 0);
				com.shoppingcart.controller.forms.impl.OrdersControllerImpl ordersImplObj = new com.shoppingcart.controller.forms.impl.OrdersControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = ordersImplObj.getEntityInfoUpdatedWithLookupIds(session, orders, retValObject);
				if(areAllLookupsFound!=1)
				{
					ordersDataObject.addProperty("retryUpload", 1);
					ordersDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					ordersDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					ordersDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int ordersId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !orders.has("ordersId"))
				{
					orders.addProperty("attributeNamePrefix", "");
					
					orders.addProperty("attributeNamePrefix", "");
					JsonObject responseData = ordersImplObj.getOrdersByLookupFields(session,  orders);
					if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
					{
						if(responseData.has("resultsCount")==false)
						{
							continue;							
						}
					}
					else
					{
						isEntityPresent = 1;
					}
					if(isEntityPresent==1)
					{
						JsonObject ordersObject = responseData.get("ordersDataObject").getAsJsonObject();
						ordersId = ordersObject.get("ordersId").getAsInt();
						orders.addProperty("ordersId", ordersId);
						updateEntity = true;
					}
				}
				else if(orders.has("ordersId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = ordersImplObj.createOrders(orders);
				}
				else
				{
					responseData = ordersImplObj.updateOrders(orders);
				}
				ordersDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					ordersId =-1;
					ordersDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					ordersDataObject.addProperty("isSuccessfullyUploaded", 0);
					if (tx.isActive())
					{
						tx.rollback();						
					}
					continue;
				}
				else
				{
					if (tx.isActive())
					{
						tx.commit();						
					}
				}
				ordersId = responseData.get("ordersId").getAsInt();
				ordersDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
			}
			dataObject.addProperty("retryCount", retryCount);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
			dataObject.addProperty("alert", "Your request could not be processed.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject orders, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getOrdersByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From Orders where 1 = 1  ";
			String countHql = "select count(*)  from Orders where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Orders could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Orders orders = (Orders) it.next();
				JsonObject ordersDataObject = orders.getDataObject(session);
				dataObject.add("ordersDataObject", ordersDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Orders could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
			dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Orders' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{return 0;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return 0;
	}
	
	public int executeAPI(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived)session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		/*if(!ServiceAPIUtil.REQUEST_STATUS_PENDING.equalsIgnoreCase(currentRequestStatus))
		{
			isAPIExecuted = 1;
			return isAPIExecuted;
		}*/		
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		String apiName = requestReceived.getApiName();
		isAPIExecuted = executeAPI(session, requestParametersInfo, requestId, apiName, retValObject);
		if (isAPIExecuted == 1)
		{
			if (retValObject.get("isRequestProcessed").getAsInt() == 1)
			{
				ServiceAPIUtil.updateRequestReceivedStatus(session, ServiceAPIUtil.REQUEST_STATUS_SUCCESSFULL, requestId);
			}
			else
			{
				ServiceAPIUtil.updateRequestReceivedStatus(session, ServiceAPIUtil.REQUEST_STATUS_FAILED, requestId);
			}
		}
		return isAPIExecuted;
	}
    	public int executeAPI(Session session, JsonObject requestParametersInfo, int requestId, String apiName, JsonObject retValObject)
	{
		JsonObject dataObject = new JsonObject();
		int isAPIExecuted = 0;
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		try
		{
			if(1 > 2)
			{
			}else if(apiName.equals("userLogin"))
			{
				com.shoppingcart.request.util.SessionUtil loginObject = new com.shoppingcart.request.util.SessionUtil();
				//dataObject = loginObject.userLogin(requestParametersInfo);
				isAPIExecuted = 1;
			}
			else if(apiName.equals("userLoginWithBranch"))
			{
				com.shoppingcart.request.util.SessionUtil loginObject = new com.shoppingcart.request.util.SessionUtil();
				//dataObject = loginObject.userLoginWithBranch(requestParametersInfo);
				isAPIExecuted = 1;
			}
			if(isAPIExecuted == 1)
			{
				retValObject.add("responseData", dataObject);
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					retValObject.addProperty("isRequestProcessed", 1);
				}
				else
				{
					retValObject.addProperty("isRequestProcessed", 0);
				}
				retValObject.addProperty("success", dataObject.get("success").getAsInt());
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}
	public int executeRollbackAPI(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived)session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		int obj = -1;
		if(requestParametersInfo.has("objectId") && requestParametersInfo.get("objectId").isJsonNull()==false)
		{
			obj = requestParametersInfo.get("objectId").getAsInt();
		}
		String apiName = requestReceived.getRollbackAPIName();
		JsonObject dataObject = new JsonObject();
		try
		{
			//String apiName  = inputDataObject.get("apiName").getAsString();  
			if(1 > 2)
			{
			}if (isAPIExecuted == 1)
			{
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					ServiceAPIUtil.updateRequestReceivedInfoRolledback(session,  requestId);
				}
				retValObject.addProperty("success", dataObject.get("success").getAsInt());
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}

	public JsonObject getAPIData(Session session, String apiName, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		try
		{
			if(1 > 2)
			{
			}
		else if(apiName.equals("getOrdersPropertyValue"))
			{
				JsonObject inputDataObject = getOrdersPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getOrders"))
			{
				JsonObject inputDataObject = getOrders(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}		dataObject.addProperty("success", 0);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public int updateAPIStatus(Session session, int requestId, JsonObject retValObject)
	{
		RequestReceived requestReceived = (RequestReceived) session.get(RequestReceived.class, requestId);
		String currentRequestStatus = requestReceived.getCurrentRequestStatus();
		int isAPIExecuted = 0;
		String paramsInfoText = requestReceived.getParamsInfo();
		JsonObject paramsInfo = new Gson().fromJson(paramsInfoText, JsonObject.class);
		JsonObject requestParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
		String apiName = requestReceived.getApiName();
		JsonObject dataObject = new JsonObject();
		try
		{
			if (1 > 2)
			{
			}
			else if (apiName.equals("doBeforeTransactionApprovedForOrders"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForOrders"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			if (isAPIExecuted == 1)
			{
				if (dataObject != null && dataObject.has("success") && dataObject.get("success").getAsInt() == 1)
				{
					retValObject.addProperty("success", dataObject.get("success").getAsInt());
				}
				else
				{
					retValObject.addProperty("success", 0);
				}
			}
			return isAPIExecuted;
		}
		catch (Exception e)
		{
			retValObject.addProperty("success", 0);
			CommonUtil.writeTolog(e);
		}
		return isAPIExecuted;
	}
	
	public JsonObject updateAPIStatus(Session session, RequestReceived requestReceived)
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int requestId = requestReceived.getRequestReceivedId();
			JsonObject paramsInfo = new Gson().fromJson(requestReceived.getParamsInfo(), JsonObject.class);
			JsonObject requestReceivedParametersInfo = paramsInfo.get("requestParametersInfo").getAsJsonObject();
			Integer ordersId = requestReceivedParametersInfo.get("ordersId").getAsInt();
			Orders orders = (Orders) session.get(Orders.class, ordersId);
			orders.setIsRequestUnderProcesss(0);
			session.merge(orders);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
			dataObject.addProperty("alert", "Your request could not be processsed as request status could not be updated.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
	}
	public JsonObject getOrdersPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer ordersId = inputForGetAPI.get("ordersId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		Orders orders = (Orders) session.get(Orders.class, ordersId);
		orders.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getOrders(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer ordersId = inputForGetAPI.get("ordersId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("ordersId", String.valueOf(ordersId));
		JsonObject ordersListObject = retrieveOrdersList(paramsMap);
		if(ordersListObject!=null && ordersListObject.has("success") && ordersListObject.get("success").getAsInt()==1)
		{
			JsonArray ordersList = ordersListObject.get("ordersList").getAsJsonArray();
			if(ordersList.size()>0)
			{
				dataObject.add("orders", ordersList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Orders' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject ordersDataObject, Orders orders);
	public abstract void doExecuteCustomAPIImpl(String customAPIMessage);
	public abstract void doEnrichValuesImpl(JsonObject paramsInfoObject);
	public abstract void doAfterEnrichValuesImpl();
	public abstract void doValidationsImpl();
	public abstract void doValidateRepeatLineImpl(String sRepeatTagName, String string, int iCurrIndex);
	public abstract void doAfterSetValues();
	public abstract void doAfterSelectRowImpl();
	public abstract void doAfterCreateTransactionImpl(JsonObject paramsInfoObj);
	public abstract void doBeforeCreateTransactionImpl(JsonObject paramsInfoObj);	
	public abstract void doBeforeUpdateTransactionImpl(JsonObject paramsInfoObject);
	public abstract void doAfterCreateTransactionCommittedImpl();
	public abstract void doAfterUpdateTransactionCommittedImpl();
	public abstract String doGetUpdatedQueryStringForSearchImpl(String queryString);
	public abstract void doUpdateQueryWithParameterValuesImpl(Query query, java.util.Map<String, Object> paramsMap);
	public abstract String doGetOrderByClauseSearchImpl();
	public abstract void doAfterUpdateTransactionImpl(JsonObject paramsInfoObject);
	public abstract void doBeforeDeleteTransactionImpl();
	public abstract void doAfterDeleteTransactionImpl();
	public abstract void doAfterLookupRowSelectedImpl(Orders orders, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(Orders orders, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(Orders orders, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
