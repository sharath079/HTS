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

import com.shoppingcart.bean.OrderItem;
import com.shoppingcart.controller.forms.base.OrderItemLabel;
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
public abstract class OrderItemControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'OrderId' 
	 *		 * 'ProductId' 
	 *		 * 'ProductQuantity' 
	 *		 * 'ProductUnitPrice' 
	 *		 * 'SubTotalAmt' 
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
	protected OrderItemLabel OrderItemLabelLocal = new OrderItemLabel();
	protected OrderItem OrderItemLocal = new OrderItem();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public OrderItemControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public OrderItemControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public OrderItemControllerBase()
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
		return "OrderItem" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((OrderItem)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((OrderItem)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((OrderItem)obj).getOrderItemId();
	}
	public String getManagedBeanName()
    {
		return "OrderItemBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "OrderItemLabelBean";
    }
	protected Class<OrderItem> setBeanClass()
	{
		return OrderItem.class;
	}
	protected Class<OrderItemLabel> setBeanLabelClass()
	{
		return OrderItemLabel.class;
	}
	protected OrderItem getLocalManagedBean()
    {
		return (OrderItem)getManagedBean();
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
		/*OrderItem orderItem = (OrderItem)getManagedBean();
		String areChangesEffected = orderItem.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			orderItem.setAreChangesEffected("YES");			
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
		/*OrderItem orderItem = (OrderItem)getManagedBean();
		String areChangesEffected = orderItem.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			orderItem.setAreChangesEffected("NO");			
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
		/*OrderItem orderItem = (OrderItem)getManagedBean();
		String areChangesEffected = orderItem.getAreChangesEffected();
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
		OrderItem orderItem = (OrderItem)getManagedBean();
		String sCurrentStatus = orderItem.getVwTxnStatus();
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
		OrderItem orderItem = (OrderItem)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(orderItem, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterOrderItemLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		OrderItem orderItem = (OrderItem)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("orderId") && initParamsInfo.get("orderId").isJsonNull()==false)
		{
			Integer orderId = null;
			try
			{
			 	orderId = initParamsInfo.get("orderId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orderItem.setOrderId(orderId);
		}if(initParamsInfo.has("productId") && initParamsInfo.get("productId").isJsonNull()==false)
		{
			Integer productId = null;
			try
			{
			 	productId = initParamsInfo.get("productId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orderItem.setProductId(productId);
		}if(initParamsInfo.has("productQuantity") && initParamsInfo.get("productQuantity").isJsonNull()==false)
		{
			Integer productQuantity = null;
			try
			{
			 	productQuantity = initParamsInfo.get("productQuantity").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orderItem.setProductQuantity(productQuantity);
		}if(initParamsInfo.has("productUnitPrice") && initParamsInfo.get("productUnitPrice").isJsonNull()==false)
		{
			Integer productUnitPrice = null;
			try
			{
			 	productUnitPrice = initParamsInfo.get("productUnitPrice").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orderItem.setProductUnitPrice(productUnitPrice);
		}if(initParamsInfo.has("subTotalAmt") && initParamsInfo.get("subTotalAmt").isJsonNull()==false)
		{
			Integer subTotalAmt = null;
			try
			{
			 	subTotalAmt = initParamsInfo.get("subTotalAmt").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			orderItem.setSubTotalAmt(subTotalAmt);
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(orderItem, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		OrderItem orderItem = (OrderItem)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		OrderItem orderItem = (OrderItem)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(orderItem, attributeName);
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
			OrderItem orderItem = (OrderItem)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "OrderItem", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() OrderItemContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (OrderItemSearch)getManagedBean("OrderItemSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getOrderId()))
			{
				searchParams.put(OrderItemLabelLocal.getorderIdFieldName(),searchBeanLocal.getOrderId());
			}	
			if (isExists(searchBeanLocal.getProductId()))
			{
				searchParams.put(OrderItemLabelLocal.getproductIdFieldName(),searchBeanLocal.getProductId());
			}	
			if (isExists(searchBeanLocal.getProductQuantity()))
			{
				searchParams.put(OrderItemLabelLocal.getproductQuantityFieldName(),searchBeanLocal.getProductQuantity());
			}	
			if (isExists(searchBeanLocal.getProductUnitPrice()))
			{
				searchParams.put(OrderItemLabelLocal.getproductUnitPriceFieldName(),searchBeanLocal.getProductUnitPrice());
			}	
			if (isExists(searchBeanLocal.getSubTotalAmt()))
			{
				searchParams.put(OrderItemLabelLocal.getsubTotalAmtFieldName(),searchBeanLocal.getSubTotalAmt());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(OrderItemLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() OrderItemContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues OrderItemContollerBase");
		targetBean = (OrderItem)targetBean;((OrderItem)targetBean).setOrderItemId(((OrderItem)sourceBean).getOrderItemId());
				((OrderItem)targetBean).setOrderId(((OrderItem)sourceBean).getOrderId());
		((OrderItem)targetBean).setProductId(((OrderItem)sourceBean).getProductId());
		((OrderItem)targetBean).setProductQuantity(((OrderItem)sourceBean).getProductQuantity());
		((OrderItem)targetBean).setProductUnitPrice(((OrderItem)sourceBean).getProductUnitPrice());
		((OrderItem)targetBean).setSubTotalAmt(((OrderItem)sourceBean).getSubTotalAmt());

		doAfterSetValues();
		debugCode("Out setValues OrderItemContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.shoppingcart.bean.OrderItem entityBean = (OrderItem)entityObject;
	 	if (sFieldName.equalsIgnoreCase("orderItemId")) 
	 	{
			return entityBean.getOrderItemId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("OrderId")) 
	 	{
			return entityBean.getOrderId();
		}
	 	if (sFieldName.equalsIgnoreCase("ProductId")) 
	 	{
			return entityBean.getProductId();
		}
	 	if (sFieldName.equalsIgnoreCase("ProductQuantity")) 
	 	{
			return entityBean.getProductQuantity();
		}
	 	if (sFieldName.equalsIgnoreCase("ProductUnitPrice")) 
	 	{
			return entityBean.getProductUnitPrice();
		}
	 	if (sFieldName.equalsIgnoreCase("SubTotalAmt")) 
	 	{
			return entityBean.getSubTotalAmt();
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
		debugCode("In doEnrichSystemValues(String sAction) OrderItemControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((OrderItem) localManagedBean).setVwLastModifiedDate(new Date());
		((OrderItem) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((OrderItem) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((OrderItem) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((OrderItem) localManagedBean).setVwTxnStatus("CREATED");
			((OrderItem) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((OrderItem) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((OrderItem) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) OrderItemControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) OrderItemControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) OrderItemControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations OrderItemControllerBase");
		//NG: Important comment
		//managedBean = (OrderItem) getManagedBean();
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
		debugCode("Out doValidations OrderItemControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation OrderItemControllerBase");
		debugCode("Out doAllowedDecimalValidation OrderItemControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation OrderItemControllerBase");debugCode("Out doAllowedValuesValidation OrderItemControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation OrderItemControllerBase");
		debugCode("Out doMandatoryValidation OrderItemControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation OrderItemControllerBase");
				
		Integer sFieldValue2 = ((OrderItem) localManagedBean).getOrderId();		Integer sFieldValue3 = ((OrderItem) localManagedBean).getProductId();		Integer sFieldValue4 = ((OrderItem) localManagedBean).getProductQuantity();		Integer sFieldValue5 = ((OrderItem) localManagedBean).getProductUnitPrice();		Integer sFieldValue6 = ((OrderItem) localManagedBean).getSubTotalAmt();		
		if (!isLengthAllowed(sFieldValue2,"15")) addMaxLengthResponse(OrderItemLabelLocal.getorderIdFieldName(),"15");
		if (!isLengthAllowed(sFieldValue3,"20")) addMaxLengthResponse(OrderItemLabelLocal.getproductIdFieldName(),"20");
		if (!isLengthAllowed(sFieldValue4,"15")) addMaxLengthResponse(OrderItemLabelLocal.getproductQuantityFieldName(),"15");
		if (!isLengthAllowed(sFieldValue5,"15")) addMaxLengthResponse(OrderItemLabelLocal.getproductUnitPriceFieldName(),"15");
		if (!isLengthAllowed(sFieldValue6,"20")) addMaxLengthResponse(OrderItemLabelLocal.getsubTotalAmtFieldName(),"20");
debugCode("Out doLengthValidation OrderItemControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation OrderItemControllerBase");
		debugCode("Out doDataTypeValidation OrderItemControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation OrderItemContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation OrderItemContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel OrderItemContollerBase");
		String sLabel = new OrderItemLabel().getLabel(sResponseField); 
		debugCode("Out getLabel OrderItemContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int orderItemId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("orderItemId", orderItemId);
			JsonObject responseData = retrieveOrderItem(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'OrderItem' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject orderItemDataObject = responseData.get("orderItemDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, orderItemDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'OrderItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveOrderItem(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer orderItemId = -1;
		if(requestParams.has("orderItemId"))
		{
			orderItemId = requestParams.get("orderItemId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("orderItemId", String.valueOf(orderItemId));
				String orderId = "";
		if(requestParams.has("orderId"))
		{
			paramsMap.put("orderId", requestParams.get("orderId").getAsString());
		}
		String productId = "";
		if(requestParams.has("productId"))
		{
			paramsMap.put("productId", requestParams.get("productId").getAsString());
		}
		String productQuantity = "";
		if(requestParams.has("productQuantity"))
		{
			paramsMap.put("productQuantity", requestParams.get("productQuantity").getAsString());
		}
		String productUnitPrice = "";
		if(requestParams.has("productUnitPrice"))
		{
			paramsMap.put("productUnitPrice", requestParams.get("productUnitPrice").getAsString());
		}
		String subTotalAmt = "";
		if(requestParams.has("subTotalAmt"))
		{
			paramsMap.put("subTotalAmt", requestParams.get("subTotalAmt").getAsString());
		}

		JsonObject orderItemListObject = retrieveOrderItemList(paramsMap);
		if(orderItemListObject!=null && orderItemListObject.has("success") && orderItemListObject.get("success").getAsInt()==1)
		{
			JsonArray orderItemList = orderItemListObject.get("orderItemList").getAsJsonArray();
			if(orderItemList.size()>0)
			{
				dataObject.add("orderItemDataObject", orderItemList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'OrderItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getOrderItem(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String orderId = "";
			if(searchParams.has("orderId"))
			{
				paramsMap.put("orderId", searchParams.get("orderId").getAsString());
			}
			String productId = "";
			if(searchParams.has("productId"))
			{
				paramsMap.put("productId", searchParams.get("productId").getAsString());
			}
			String productQuantity = "";
			if(searchParams.has("productQuantity"))
			{
				paramsMap.put("productQuantity", searchParams.get("productQuantity").getAsString());
			}
			String productUnitPrice = "";
			if(searchParams.has("productUnitPrice"))
			{
				paramsMap.put("productUnitPrice", searchParams.get("productUnitPrice").getAsString());
			}
			String subTotalAmt = "";
			if(searchParams.has("subTotalAmt"))
			{
				paramsMap.put("subTotalAmt", searchParams.get("subTotalAmt").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject orderItemListObject = retrieveOrderItemList(paramsMap);
			if(orderItemListObject!=null && orderItemListObject.has("success") && orderItemListObject.get("success").getAsInt()==1)
			{
				JsonArray orderItemList = orderItemListObject.get("orderItemList").getAsJsonArray();
				if(orderItemList.size()>0)
				{
					dataObject.add("orderItem", orderItemList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'OrderItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getOrderItemInJson(int orderItemId)
	{
		JsonObject OrderItemData = null;
		List<Integer> orderItemIdsList = new ArrayList<>();
		orderItemIdsList.add(orderItemId);
		JsonArray orderItemList = getOrderItemListInJson(orderItemIdsList);
		if(orderItemList!=null && orderItemList.size()>0)
		{
			OrderItemData = orderItemList.get(0).getAsJsonObject();
		}
		return OrderItemData;
	}
	public JsonArray getOrderItemListInJson(List<Integer> orderItemIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray orderItemObjectsList = new JsonArray();
		JsonObject orderItemListObject = retrieveOrderItemList(paramsMap, orderItemIdsList);
		if(orderItemListObject!=null && orderItemListObject.has("success") && orderItemListObject.get("success").getAsInt()==1)
		{
			JsonArray orderItemList = orderItemListObject.get("orderItemList").getAsJsonArray();
			for (int i =0; i< orderItemList.size(); i++)
			{
				JsonObject orderItemDataObject = orderItemList.get(i).getAsJsonObject();
				int orderItemId = orderItemDataObject.get("orderItemId").getAsInt();
				
				orderItemObjectsList.add(orderItemDataObject);
			}
		}
		return orderItemObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray orderItemList)
	{
		String errorMessage = "orderItemList: \n";
		for (int i =0; i< orderItemList.size(); i++)
		{
			JsonObject orderItemDataObject = orderItemList.get(i).getAsJsonObject();
			JsonObject orderItem = orderItemDataObject.get("dataObject").getAsJsonObject();if(!orderItemDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "orderItem could not be uploaded verify data \n"; 
			}
			else if(orderItemDataObject.has("isSuccessfullyUploaded") 
					&& orderItemDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += orderItemDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("OrderItem".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND orderItemId = :orderItemId ";
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
		else if("OrderItem".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("orderItemId", userId);
		}
		
	}
	public String getParentFilterClauseForOrderItem(java.util.Map<String, String> paramsMap)
	{
		String parentFilterClause  = "";return parentFilterClause;
	}
	public String getLookupDisplayFilterClause()
	{
		String lookupDisplayFilterClause = "";
		String lookupDisplayQueryColumn = " AND concat(";
		int i= 0;
		 
		lookupDisplayQueryColumn +=") LIKE :lookupDisplayPrefix ";
		if(i > 0)
		{
			lookupDisplayFilterClause = lookupDisplayQueryColumn; 
		}
		return lookupDisplayFilterClause;
	}
	public void setParentFilterClauseParamsForOrderItem(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveOrderItemList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> orderItemIdsList = new ArrayList<>();
		if(paramsMap.containsKey("orderItemId"))
		{
			int orderItemId = Integer.parseInt(paramsMap.get("orderItemId"));
			if(orderItemId>0)
			{
				orderItemIdsList.add(orderItemId);
			}
		}
		return retrieveOrderItemList(paramsMap, orderItemIdsList);
	}
	public JsonObject retrieveOrderItemList(java.util.Map<String, String> paramsMap, List<Integer> orderItemIdsList)
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
						String orderId = paramsMap.get("orderId");
			if(orderId==null)
			{
				orderId = "";
			}
			String productId = paramsMap.get("productId");
			if(productId==null)
			{
				productId = "";
			}
			String productQuantity = paramsMap.get("productQuantity");
			if(productQuantity==null)
			{
				productQuantity = "";
			}
			String productUnitPrice = paramsMap.get("productUnitPrice");
			if(productUnitPrice==null)
			{
				productUnitPrice = "";
			}
			String subTotalAmt = paramsMap.get("subTotalAmt");
			if(subTotalAmt==null)
			{
				subTotalAmt = "";
			}
String hql = "FROM OrderItem where 1 = 1 ";
			String orderByClauseText = "  ";
			String orderItemIdFilterClass = "";
			if (orderItemIdsList != null && orderItemIdsList.size() > 0)
			{
				orderItemIdFilterClass = " AND orderItemId in (:idsList) ";
			}
						String orderIdFilterClass = "";
			if (orderId.length() > 0)
			{			
				
				orderIdFilterClass = " AND orderId = :orderId ";
				
			}
			String productIdFilterClass = "";
			if (productId.length() > 0)
			{			
				
				productIdFilterClass = " AND productId = :productId ";
				
			}
			String productQuantityFilterClass = "";
			if (productQuantity.length() > 0)
			{			
				
				productQuantityFilterClass = " AND productQuantity = :productQuantity ";
				
			}
			String productUnitPriceFilterClass = "";
			if (productUnitPrice.length() > 0)
			{			
				
				productUnitPriceFilterClass = " AND productUnitPrice = :productUnitPrice ";
				
			}
			String subTotalAmtFilterClass = "";
			if (subTotalAmt.length() > 0)
			{			
				
				subTotalAmtFilterClass = " AND subTotalAmt = :subTotalAmt ";
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForOrderItem(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					orderItemIdFilterClass +
										orderIdFilterClass +
					productIdFilterClass +
					productQuantityFilterClass +
					productUnitPriceFilterClass +
					subTotalAmtFilterClass +

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
			if (orderItemIdsList != null && orderItemIdsList.size() > 0)
			{
				query.setParameterList("idsList", orderItemIdsList);
			}
						if (orderId.length() > 0)
			{			
				
				query.setParameter("orderId", Integer.parseInt(orderId));
				
			}
			if (productId.length() > 0)
			{			
				
				query.setParameter("productId", Integer.parseInt(productId));
				
			}
			if (productQuantity.length() > 0)
			{			
				
				query.setParameter("productQuantity", Integer.parseInt(productQuantity));
				
			}
			if (productUnitPrice.length() > 0)
			{			
				
				query.setParameter("productUnitPrice", Integer.parseInt(productUnitPrice));
				
			}
			if (subTotalAmt.length() > 0)
			{			
				
				query.setParameter("subTotalAmt", Integer.parseInt(subTotalAmt));
				
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
	    	setParentFilterClauseParamsForOrderItem(paramsMap, query);
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
			JsonArray orderItemList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				OrderItem orderItem = (OrderItem) it.next();
				JsonObject orderItemDataObject = orderItem.getDataObject(getDBSession());
				orderItemDataObject.addProperty("nextAction", getActionForCurrentStatus(orderItem.getVwTxnStatus()));
				orderItemList.add(orderItemDataObject);
				doAfterSearchResultRowAddedImpl(orderItemDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from OrderItem where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (orderItemIdsList != null && orderItemIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", orderItemIdsList);
				}
								if (orderId.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("orderId", Integer.parseInt(orderId));
					
				}
				if (productId.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("productId", Integer.parseInt(productId));
					
				}
				if (productQuantity.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("productQuantity", Integer.parseInt(productQuantity));
					
				}
				if (productUnitPrice.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("productUnitPrice", Integer.parseInt(productUnitPrice));
					
				}
				if (subTotalAmt.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("subTotalAmt", Integer.parseInt(subTotalAmt));
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForOrderItem(paramsMap, countQuery);
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
			dataObject.add("orderItemList",   orderItemList);
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
				+ "   from OrderItem E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from OrderItem E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentOrderItemList(java.util.Map<String, String> paramsMap)
	{
		return retrieveOrderItemList(paramsMap);
	}
	public OrderItem getOrderItemByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from OrderItem where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			OrderItem orderItem = (OrderItem) it.next();
			return orderItem;
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
		OrderItem orderItem = (OrderItem)getManagedBean();
		JsonObject orderItemDataObject = orderItem.getDataObject(getDBSession());copyOrderItemFromJson(orderItem, orderItemDataObject);
		setManagedBean(orderItem);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData OrderItemContollerBase");
			OrderItem currentBean = (OrderItem)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		currentBean.setOrderId(1);currentBean.setProductId(1);currentBean.setProductQuantity(1);currentBean.setProductUnitPrice(1);currentBean.setSubTotalAmt(1);

		setManagedBean(currentBean);
		debugCode("Out setTestData OrderItemContollerBase");
	}
	// end Test Data
	public void copyOrderItemFromJson(OrderItem orderItem, JsonObject orderItemDataObject)
	{
		copyOrderItemFromJson(orderItem, orderItemDataObject, false);
	}
	public void copyOrderItemFromJson(OrderItem orderItem, JsonObject orderItemDataObject, boolean excludePasswordFields)
	{	
				
		if(orderItemDataObject.has("orderId"))
		{
			Integer orderId = null;
			try
			{
			 	orderId = orderItemDataObject.get("orderId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(orderId != null)
			{
				orderItem.setOrderId(orderId);
			}
		}if(orderItemDataObject.has("productId"))
		{
			Integer productId = null;
			try
			{
			 	productId = orderItemDataObject.get("productId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(productId != null)
			{
				orderItem.setProductId(productId);
			}
		}if(orderItemDataObject.has("productQuantity"))
		{
			Integer productQuantity = null;
			try
			{
			 	productQuantity = orderItemDataObject.get("productQuantity").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(productQuantity != null)
			{
				orderItem.setProductQuantity(productQuantity);
			}
		}if(orderItemDataObject.has("productUnitPrice"))
		{
			Integer productUnitPrice = null;
			try
			{
			 	productUnitPrice = orderItemDataObject.get("productUnitPrice").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(productUnitPrice != null)
			{
				orderItem.setProductUnitPrice(productUnitPrice);
			}
		}if(orderItemDataObject.has("subTotalAmt"))
		{
			Integer subTotalAmt = null;
			try
			{
			 	subTotalAmt = orderItemDataObject.get("subTotalAmt").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(subTotalAmt != null)
			{
				orderItem.setSubTotalAmt(subTotalAmt);
			}
		}
		
	}
		
	public JsonObject createOrderItem(JsonObject orderItemDataObject) throws Exception
	{
		return createOrderItem(orderItemDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject orderItemDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createOrderItem(JsonObject orderItemDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		OrderItem orderItem = new OrderItem();
		setLoginBasedValues(paramsInfoObj, orderItemDataObject);
		Session session = getDBSession();				
		copyOrderItemFromJson(orderItem, orderItemDataObject);
		if(orderItemDataObject.has("legacyRecordId"))
		{
			orderItem.setLegacyRecordId(orderItemDataObject.get("legacyRecordId").getAsInt());
		}
				orderItem.setVwCreatedBy(createdBy);
		orderItem.setVwCreationDate(new Date());
		setPrivateManagedBean(orderItem);
		setManagedBean("OrderItemBean", orderItem);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		orderItem = (OrderItem) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("orderItemId", orderItem.getOrderItemId());
			JsonObject orderItemObj = orderItem.getDataObject(getDBSession());
			orderItemObj.addProperty("nextAction", getActionForCurrentStatus(orderItem.getVwTxnStatus()));
			dataObject.add("orderItemDataObject", orderItemObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateOrderItem(JsonObject orderItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer orderItemId = orderItemDataObject.get("orderItemId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("orderItemId", orderItemId);
		JsonObject responseData = retrieveOrderItem(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'OrderItem' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		OrderItem orderItem = (OrderItem) session.get(OrderItem.class, orderItemId);
		if(orderItem == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(orderItem.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromOrderItem(orderItemDataObject);
		boolean excludePasswordFields = true;
		copyOrderItemFromJson(orderItem, orderItemDataObject, excludePasswordFields);setPrivateManagedBean(orderItem);
		setManagedBean("OrderItemBean", orderItem);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		orderItem.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("orderItemId", orderItemId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromOrderItem(JsonObject orderItemDataObject) throws Exception
	{
	}
	public JsonObject deleteOrderItem(JsonObject orderItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer orderItemId = orderItemDataObject.get("orderItemId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("orderItemId", orderItemId);
		JsonObject responseData = retrieveOrderItem(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'OrderItem' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		OrderItem orderItem = (OrderItem) session.get(OrderItem.class, orderItemId);setPrivateManagedBean(orderItem);
		setManagedBean("OrderItem", orderItem);
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
	public JsonObject fetchOrderItemDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		OrderItem orderItem = new OrderItem();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(orderItem);
			setManagedBean("OrderItem", orderItem);
			doAfterOrderItemLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orderItem", orderItem.getDataObject(getDBSession()));
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
	public JsonObject fetchOrderItemTestData(int obj, JsonObject orderItemDataObject) throws Exception
	{
		Session session = getDBSession();
		OrderItem orderItem = new OrderItem();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrderItemFromJson(orderItem, orderItemDataObject);
			setPrivateManagedBean(orderItem);
			setManagedBean("OrderItem", orderItem);
			doAfterOrderItemLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orderItem", orderItem.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForOrderItem(JsonObject orderItemDataObject) throws Exception
	{
		OrderItem orderItem = new OrderItem();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrderItemFromJson(orderItem, orderItemDataObject);	String attributeName = orderItemDataObject.get("attributeName").getAsString();
			Integer attributeId = orderItemDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(orderItem);
			setManagedBean("OrderItem", orderItem);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orderItem", orderItem.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForOrderItem(JsonObject orderItemDataObject) throws Exception
	{
		OrderItem orderItem = new OrderItem();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyOrderItemFromJson(orderItem, orderItemDataObject);	
			String attributeName = orderItemDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(orderItem);
			setManagedBean("OrderItem", orderItem);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orderItem", orderItem.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject orderItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer orderItemId = orderItemDataObject.get("orderItemId").getAsInt();
			String customEventName = orderItemDataObject.get("customEventName").getAsString();
			OrderItem orderItem = (OrderItem) session.get(OrderItem.class, orderItemId);
			setPrivateManagedBean(orderItem);
			setManagedBean("OrderItemBean", orderItem);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("orderItem", orderItem.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnOrderItem(JsonObject orderItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer orderItemId = orderItemDataObject.get("orderItemId").getAsInt();
			String currentStatus = orderItemDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(orderItemDataObject.has("currentAction"))
			{
				currentAction = orderItemDataObject.get("currentAction").getAsString();
			}
			OrderItem orderItem = (OrderItem) session.get(OrderItem.class, orderItemId);
			setPrivateManagedBean(orderItem);
			setManagedBean("OrderItemBean", orderItem);
			if(orderItem.getIsRequestUnderProcesss() == 1)
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
				executeAction(orderItem, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(orderItem, "ClassInfoBean", currentStatus);
			}
//			executeAction(orderItem, "OrderItemBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedorderItemDataObject = orderItem.getDataObject(getDBSession());
				updatedorderItemDataObject.addProperty("nextAction", getActionForCurrentStatus(orderItem.getVwTxnStatus()));
				dataObject.add("orderItem", updatedorderItemDataObject);
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
		OrderItem orderItem = (OrderItem) getManagedBean();
		String currentStatus = orderItem.getVwTxnStatus();
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
	
	
	public JsonObject downloadOrderItemData() throws Exception
	{
		return downloadOrderItemData(null);
	}
	public JsonObject downloadOrderItemData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "OrderItem");
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
			headerName = "orderItemId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "orderId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "productId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "productQuantity";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "productUnitPrice";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "subTotalAmt";
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
			String hql = "From OrderItem ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				OrderItem orderItem = (OrderItem) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer orderItemId = orderItem.getOrderItemId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(orderItemId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer orderId = orderItem.getOrderId();
				if(orderId!=null)
				{
					cell.setCellValue(String.valueOf(orderId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer productId = orderItem.getProductId();
				if(productId!=null)
				{
					cell.setCellValue(String.valueOf(productId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer productQuantity = orderItem.getProductQuantity();
				if(productQuantity!=null)
				{
					cell.setCellValue(String.valueOf(productQuantity));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer productUnitPrice = orderItem.getProductUnitPrice();
				if(productUnitPrice!=null)
				{
					cell.setCellValue(String.valueOf(productUnitPrice));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer subTotalAmt = orderItem.getSubTotalAmt();
				if(subTotalAmt!=null)
				{
					cell.setCellValue(String.valueOf(subTotalAmt));
				}

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "OrderItem.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "OrderItem.xls";
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
	public JsonObject uploadOrderItemData(JsonObject orderItemDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = orderItemDataObject.get("fileId").getAsInt();
		String inputFilesZip = orderItemDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "OrderItem Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		orderItemDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadOrderItemData(workbook, orderItemDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "OrderItem Data uploaded successfully.");
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
	public JsonObject uploadOrderItemData(HSSFWorkbook workbook, JsonObject orderItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("OrderItem");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.shoppingcart.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = orderItemDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = orderItemDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = orderItemDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "OrderItem Data uploaded successfully.");
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
			JsonObject orderItem = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject orderItemListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray orderItemList = orderItemListObj.get("orderItemList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadOrderItemList(orderItemList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(orderItemList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(orderItemList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray orderItemList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject orderItem = new JsonObject();
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
				JsonObject orderItemUploadObj	= new JsonObject();
				orderItemUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					orderItem = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("orderItemId"))
						{
							String orderItemId = row.getCell(cellIndex++).getStringCellValue();
							if(orderItemId != null && orderItemId.trim().length() > 0)
							{
								try
								{
									Integer iOrderItemId = Integer.parseInt(orderItemId);
									if(areSourceDestinationSame == 1)
									{
										OrderItem orderItemObj = (OrderItem)session.get(OrderItem.class, iOrderItemId);
										if(orderItemObj != null)
										{ 
											orderItem.addProperty("orderItemId", iOrderItemId);
										}
										else
										{
											orderItemUploadObj.addProperty("isDataFetched", 0);
											orderItemUploadObj.addProperty("msg", "This OrderItem could not be uploaded as there appears to be some problem with the data(OrderItem Id is not exist). ");
											continue;
										}
									}
									else
									{
										OrderItem orderItemObj = getOrderItemByLegacyRecordId(session, iOrderItemId);
										if(orderItemObj != null)
										{ 
											orderItem.addProperty("orderItemId", orderItemObj.getOrderItemId());
										}
										orderItem.addProperty("legacyRecordId", iOrderItemId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									orderItemUploadObj.addProperty("isDataFetched", 0);
									orderItemUploadObj.addProperty("msg", "This OrderItem could not be uploaded as there appears to be some problem with the data(OrderItem Id). ");
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
							orderItem.addProperty(parameterName, parameterValue);
						}
					}
					orderItemUploadObj.add("dataObject", orderItem);		    
					orderItemList.add(orderItemUploadObj);
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
			dataObject.add("orderItemList", orderItemList);
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
		if(previousRetryCountMap.has("OrderItem") && previousRetryCountMap.get("OrderItem").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("OrderItem").getAsInt();
		}
		if(retryCountMap.has("OrderItem") && retryCountMap.get("OrderItem").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("OrderItem").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForOrderItemList(JsonArray orderItemList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < orderItemList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject orderItemDataObject = orderItemList.get(i).getAsJsonObject();
			JsonObject orderItem = orderItemDataObject.get("dataObject").getAsJsonObject();
			if(orderItemDataObject.has("retryUpload") && orderItemDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = orderItemDataObject.get("retryUpload").getAsInt();
			}
			if(orderItemDataObject.has("retryChildEntitiesUpload") && orderItemDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = orderItemDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(orderItemDataObject.has("partialUploadUnderProcess") && orderItemDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = orderItemDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("OrderItem", retryCount);
	}
	public JsonObject uploadOrderItemList(JsonArray orderItemList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(orderItemList);
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
	public JsonObject updateStatusMsg(JsonArray orderItemList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < orderItemList.size(); i++)
			{
				JsonObject orderItemDataObject = orderItemList.get(i).getAsJsonObject();
				JsonObject orderItem = orderItemDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = orderItemDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = orderItemDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = orderItemDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray orderItemList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < orderItemList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject orderItemDataObject = orderItemList.get(i).getAsJsonObject();
				JsonObject orderItem = orderItemDataObject.get("dataObject").getAsJsonObject();
				orderItemDataObject.addProperty("retryUpload", 0);
				orderItemDataObject.addProperty("retryChildEntitiesUpload", 0);
				orderItemDataObject.addProperty("partialUploadUnderProcess", 0);
				com.shoppingcart.controller.forms.impl.OrderItemControllerImpl orderItemImplObj = new com.shoppingcart.controller.forms.impl.OrderItemControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = orderItemImplObj.getEntityInfoUpdatedWithLookupIds(session, orderItem, retValObject);
				if(areAllLookupsFound!=1)
				{
					orderItemDataObject.addProperty("retryUpload", 1);
					orderItemDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					orderItemDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					orderItemDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int orderItemId = -1;
				int keyColumnsCount = 0;
								keyColumnsCount++;

				if(keyColumnsCount > 0 && !orderItem.has("orderItemId"))
				{
					orderItem.addProperty("attributeNamePrefix", "");
					
					orderItem.addProperty("attributeNamePrefix", "");
					JsonObject responseData = orderItemImplObj.getOrderItemByLookupFields(session,  orderItem);
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
						JsonObject orderItemObject = responseData.get("orderItemDataObject").getAsJsonObject();
						orderItemId = orderItemObject.get("orderItemId").getAsInt();
						orderItem.addProperty("orderItemId", orderItemId);
						updateEntity = true;
					}
				}
				else if(orderItem.has("orderItemId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = orderItemImplObj.createOrderItem(orderItem);
				}
				else
				{
					responseData = orderItemImplObj.updateOrderItem(orderItem);
				}
				orderItemDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					orderItemId =-1;
					orderItemDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					orderItemDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				orderItemId = responseData.get("orderItemId").getAsInt();
				orderItemDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject orderItem, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getOrderItemByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From OrderItem where 1 = 1  ";
			String countHql = "select count(*)  from OrderItem where 1 = 1 ";
						
			Integer productQuantity = requestParams.get("productQuantity").getAsInt();
			hql += " and productQuantity = :productQuantity ";
			countHql += " and productQuantity = :productQuantity ";
Query countQuery = session.createQuery(countHql);			countQuery.setParameter("productQuantity", productQuantity);
Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as OrderItem could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);			query.setParameter("productQuantity", productQuantity);
List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				OrderItem orderItem = (OrderItem) it.next();
				JsonObject orderItemDataObject = orderItem.getDataObject(session);
				dataObject.add("orderItemDataObject", orderItemDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as OrderItem could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static JsonObject getQueryParamsForLookupSearch(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			Integer productQuantity = searchObject.get(attributeNamePrefix + "_" + "productQuantity").getAsInt();
			requestParams.addProperty("productQuantity", productQuantity);
dataObject.add("requestParams", requestParams);
			dataObject.addProperty("success", 1);
			return dataObject;
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'OrderItem' could not be retrieved");
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public static int hasParamsForLookup(JsonObject searchObject, String attributeNamePrefix)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		try
		{
						
			if(searchObject.has(attributeNamePrefix + "_" + "productQuantity"))
			{
				return 1;
			}

			return 0;
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
		else if(apiName.equals("getOrderItemPropertyValue"))
			{
				JsonObject inputDataObject = getOrderItemPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getOrderItem"))
			{
				JsonObject inputDataObject = getOrderItem(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForOrderItem"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForOrderItem"))
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
			Integer orderItemId = requestReceivedParametersInfo.get("orderItemId").getAsInt();
			OrderItem orderItem = (OrderItem) session.get(OrderItem.class, orderItemId);
			orderItem.setIsRequestUnderProcesss(0);
			session.merge(orderItem);
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
	public JsonObject getOrderItemPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer orderItemId = inputForGetAPI.get("orderItemId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		OrderItem orderItem = (OrderItem) session.get(OrderItem.class, orderItemId);
		orderItem.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getOrderItem(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer orderItemId = inputForGetAPI.get("orderItemId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("orderItemId", String.valueOf(orderItemId));
		JsonObject orderItemListObject = retrieveOrderItemList(paramsMap);
		if(orderItemListObject!=null && orderItemListObject.has("success") && orderItemListObject.get("success").getAsInt()==1)
		{
			JsonArray orderItemList = orderItemListObject.get("orderItemList").getAsJsonArray();
			if(orderItemList.size()>0)
			{
				dataObject.add("orderItem", orderItemList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'OrderItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject orderItemDataObject, OrderItem orderItem);
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
	public abstract void doAfterLookupRowSelectedImpl(OrderItem orderItem, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(OrderItem orderItem, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(OrderItem orderItem, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
