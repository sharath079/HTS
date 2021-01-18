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

import com.shoppingcart.bean.CartItem;
import com.shoppingcart.controller.forms.base.CartItemLabel;
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
public abstract class CartItemControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'UserCartId' 
	 *		 * 'ProductId' 
	 *		 * 'ProductQuantity' 
	 *		 * 'ProductUnitPrice' 
	 *		 * 'SubTotalAmount' 
	 *		 * 'GrandTotal' 
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
	protected CartItemLabel CartItemLabelLocal = new CartItemLabel();
	protected CartItem CartItemLocal = new CartItem();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public CartItemControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public CartItemControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public CartItemControllerBase()
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
		return "CartItem" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((CartItem)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((CartItem)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((CartItem)obj).getCartItemId();
	}
	public String getManagedBeanName()
    {
		return "CartItemBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "CartItemLabelBean";
    }
	protected Class<CartItem> setBeanClass()
	{
		return CartItem.class;
	}
	protected Class<CartItemLabel> setBeanLabelClass()
	{
		return CartItemLabel.class;
	}
	protected CartItem getLocalManagedBean()
    {
		return (CartItem)getManagedBean();
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
		/*CartItem cartItem = (CartItem)getManagedBean();
		String areChangesEffected = cartItem.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			cartItem.setAreChangesEffected("YES");			
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
		/*CartItem cartItem = (CartItem)getManagedBean();
		String areChangesEffected = cartItem.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			cartItem.setAreChangesEffected("NO");			
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
		/*CartItem cartItem = (CartItem)getManagedBean();
		String areChangesEffected = cartItem.getAreChangesEffected();
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
		CartItem cartItem = (CartItem)getManagedBean();
		String sCurrentStatus = cartItem.getVwTxnStatus();
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
		CartItem cartItem = (CartItem)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(cartItem, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterCartItemLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		CartItem cartItem = (CartItem)getManagedBean();  
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
			cartItem.setUserCartId(userCartId);
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
			cartItem.setProductId(productId);
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
			cartItem.setProductQuantity(productQuantity);
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
			cartItem.setProductUnitPrice(productUnitPrice);
		}if(initParamsInfo.has("subTotalAmount") && initParamsInfo.get("subTotalAmount").isJsonNull()==false)
		{
			Integer subTotalAmount = null;
			try
			{
			 	subTotalAmount = initParamsInfo.get("subTotalAmount").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			cartItem.setSubTotalAmount(subTotalAmount);
		}if(initParamsInfo.has("grandTotal") && initParamsInfo.get("grandTotal").isJsonNull()==false)
		{
			Integer grandTotal = null;
			try
			{
			 	grandTotal = initParamsInfo.get("grandTotal").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			cartItem.setGrandTotal(grandTotal);
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(cartItem, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		CartItem cartItem = (CartItem)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		CartItem cartItem = (CartItem)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(cartItem, attributeName);
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
			CartItem cartItem = (CartItem)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "CartItem", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() CartItemContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (CartItemSearch)getManagedBean("CartItemSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getUserCartId()))
			{
				searchParams.put(CartItemLabelLocal.getuserCartIdFieldName(),searchBeanLocal.getUserCartId());
			}	
			if (isExists(searchBeanLocal.getProductId()))
			{
				searchParams.put(CartItemLabelLocal.getproductIdFieldName(),searchBeanLocal.getProductId());
			}	
			if (isExists(searchBeanLocal.getProductQuantity()))
			{
				searchParams.put(CartItemLabelLocal.getproductQuantityFieldName(),searchBeanLocal.getProductQuantity());
			}	
			if (isExists(searchBeanLocal.getProductUnitPrice()))
			{
				searchParams.put(CartItemLabelLocal.getproductUnitPriceFieldName(),searchBeanLocal.getProductUnitPrice());
			}	
			if (isExists(searchBeanLocal.getSubTotalAmount()))
			{
				searchParams.put(CartItemLabelLocal.getsubTotalAmountFieldName(),searchBeanLocal.getSubTotalAmount());
			}	
			if (isExists(searchBeanLocal.getGrandTotal()))
			{
				searchParams.put(CartItemLabelLocal.getgrandTotalFieldName(),searchBeanLocal.getGrandTotal());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(CartItemLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() CartItemContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues CartItemContollerBase");
		targetBean = (CartItem)targetBean;((CartItem)targetBean).setCartItemId(((CartItem)sourceBean).getCartItemId());
				((CartItem)targetBean).setUserCartId(((CartItem)sourceBean).getUserCartId());
		((CartItem)targetBean).setProductId(((CartItem)sourceBean).getProductId());
		((CartItem)targetBean).setProductQuantity(((CartItem)sourceBean).getProductQuantity());
		((CartItem)targetBean).setProductUnitPrice(((CartItem)sourceBean).getProductUnitPrice());
		((CartItem)targetBean).setSubTotalAmount(((CartItem)sourceBean).getSubTotalAmount());
		((CartItem)targetBean).setGrandTotal(((CartItem)sourceBean).getGrandTotal());

		doAfterSetValues();
		debugCode("Out setValues CartItemContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.shoppingcart.bean.CartItem entityBean = (CartItem)entityObject;
	 	if (sFieldName.equalsIgnoreCase("cartItemId")) 
	 	{
			return entityBean.getCartItemId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("UserCartId")) 
	 	{
			return entityBean.getUserCartId();
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
	 	if (sFieldName.equalsIgnoreCase("SubTotalAmount")) 
	 	{
			return entityBean.getSubTotalAmount();
		}
	 	if (sFieldName.equalsIgnoreCase("GrandTotal")) 
	 	{
			return entityBean.getGrandTotal();
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
		debugCode("In doEnrichSystemValues(String sAction) CartItemControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((CartItem) localManagedBean).setVwLastModifiedDate(new Date());
		((CartItem) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((CartItem) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((CartItem) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((CartItem) localManagedBean).setVwTxnStatus("CREATED");
			((CartItem) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((CartItem) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((CartItem) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) CartItemControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) CartItemControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) CartItemControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations CartItemControllerBase");
		//NG: Important comment
		//managedBean = (CartItem) getManagedBean();
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
		debugCode("Out doValidations CartItemControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation CartItemControllerBase");
		debugCode("Out doAllowedDecimalValidation CartItemControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation CartItemControllerBase");debugCode("Out doAllowedValuesValidation CartItemControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation CartItemControllerBase");
		debugCode("Out doMandatoryValidation CartItemControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation CartItemControllerBase");
				
		Integer sFieldValue2 = ((CartItem) localManagedBean).getUserCartId();		Integer sFieldValue3 = ((CartItem) localManagedBean).getProductId();		Integer sFieldValue4 = ((CartItem) localManagedBean).getProductQuantity();		Integer sFieldValue5 = ((CartItem) localManagedBean).getProductUnitPrice();		Integer sFieldValue6 = ((CartItem) localManagedBean).getSubTotalAmount();		Integer sFieldValue7 = ((CartItem) localManagedBean).getGrandTotal();		
		if (!isLengthAllowed(sFieldValue2,"20")) addMaxLengthResponse(CartItemLabelLocal.getuserCartIdFieldName(),"20");
		if (!isLengthAllowed(sFieldValue3,"20")) addMaxLengthResponse(CartItemLabelLocal.getproductIdFieldName(),"20");
		if (!isLengthAllowed(sFieldValue4,"15")) addMaxLengthResponse(CartItemLabelLocal.getproductQuantityFieldName(),"15");
		if (!isLengthAllowed(sFieldValue5,"30")) addMaxLengthResponse(CartItemLabelLocal.getproductUnitPriceFieldName(),"30");
		if (!isLengthAllowed(sFieldValue6,"25")) addMaxLengthResponse(CartItemLabelLocal.getsubTotalAmountFieldName(),"25");
		if (!isLengthAllowed(sFieldValue7,"25")) addMaxLengthResponse(CartItemLabelLocal.getgrandTotalFieldName(),"25");
debugCode("Out doLengthValidation CartItemControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation CartItemControllerBase");
		debugCode("Out doDataTypeValidation CartItemControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation CartItemContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation CartItemContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel CartItemContollerBase");
		String sLabel = new CartItemLabel().getLabel(sResponseField); 
		debugCode("Out getLabel CartItemContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int cartItemId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("cartItemId", cartItemId);
			JsonObject responseData = retrieveCartItem(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'CartItem' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject cartItemDataObject = responseData.get("cartItemDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, cartItemDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'CartItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveCartItem(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer cartItemId = -1;
		if(requestParams.has("cartItemId"))
		{
			cartItemId = requestParams.get("cartItemId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("cartItemId", String.valueOf(cartItemId));
				String userCartId = "";
		if(requestParams.has("userCartId"))
		{
			paramsMap.put("userCartId", requestParams.get("userCartId").getAsString());
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
		String subTotalAmount = "";
		if(requestParams.has("subTotalAmount"))
		{
			paramsMap.put("subTotalAmount", requestParams.get("subTotalAmount").getAsString());
		}
		String grandTotal = "";
		if(requestParams.has("grandTotal"))
		{
			paramsMap.put("grandTotal", requestParams.get("grandTotal").getAsString());
		}

		JsonObject cartItemListObject = retrieveCartItemList(paramsMap);
		if(cartItemListObject!=null && cartItemListObject.has("success") && cartItemListObject.get("success").getAsInt()==1)
		{
			JsonArray cartItemList = cartItemListObject.get("cartItemList").getAsJsonArray();
			if(cartItemList.size()>0)
			{
				dataObject.add("cartItemDataObject", cartItemList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'CartItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getCartItem(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
			String subTotalAmount = "";
			if(searchParams.has("subTotalAmount"))
			{
				paramsMap.put("subTotalAmount", searchParams.get("subTotalAmount").getAsString());
			}
			String grandTotal = "";
			if(searchParams.has("grandTotal"))
			{
				paramsMap.put("grandTotal", searchParams.get("grandTotal").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject cartItemListObject = retrieveCartItemList(paramsMap);
			if(cartItemListObject!=null && cartItemListObject.has("success") && cartItemListObject.get("success").getAsInt()==1)
			{
				JsonArray cartItemList = cartItemListObject.get("cartItemList").getAsJsonArray();
				if(cartItemList.size()>0)
				{
					dataObject.add("cartItem", cartItemList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'CartItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getCartItemInJson(int cartItemId)
	{
		JsonObject CartItemData = null;
		List<Integer> cartItemIdsList = new ArrayList<>();
		cartItemIdsList.add(cartItemId);
		JsonArray cartItemList = getCartItemListInJson(cartItemIdsList);
		if(cartItemList!=null && cartItemList.size()>0)
		{
			CartItemData = cartItemList.get(0).getAsJsonObject();
		}
		return CartItemData;
	}
	public JsonArray getCartItemListInJson(List<Integer> cartItemIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray cartItemObjectsList = new JsonArray();
		JsonObject cartItemListObject = retrieveCartItemList(paramsMap, cartItemIdsList);
		if(cartItemListObject!=null && cartItemListObject.has("success") && cartItemListObject.get("success").getAsInt()==1)
		{
			JsonArray cartItemList = cartItemListObject.get("cartItemList").getAsJsonArray();
			for (int i =0; i< cartItemList.size(); i++)
			{
				JsonObject cartItemDataObject = cartItemList.get(i).getAsJsonObject();
				int cartItemId = cartItemDataObject.get("cartItemId").getAsInt();
				
				cartItemObjectsList.add(cartItemDataObject);
			}
		}
		return cartItemObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray cartItemList)
	{
		String errorMessage = "cartItemList: \n";
		for (int i =0; i< cartItemList.size(); i++)
		{
			JsonObject cartItemDataObject = cartItemList.get(i).getAsJsonObject();
			JsonObject cartItem = cartItemDataObject.get("dataObject").getAsJsonObject();if(!cartItemDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "cartItem could not be uploaded verify data \n"; 
			}
			else if(cartItemDataObject.has("isSuccessfullyUploaded") 
					&& cartItemDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += cartItemDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("CartItem".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND cartItemId = :cartItemId ";
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
		else if("CartItem".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("cartItemId", userId);
		}
		
	}
	public String getParentFilterClauseForCartItem(java.util.Map<String, String> paramsMap)
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
	public void setParentFilterClauseParamsForCartItem(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveCartItemList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> cartItemIdsList = new ArrayList<>();
		if(paramsMap.containsKey("cartItemId"))
		{
			int cartItemId = Integer.parseInt(paramsMap.get("cartItemId"));
			if(cartItemId>0)
			{
				cartItemIdsList.add(cartItemId);
			}
		}
		return retrieveCartItemList(paramsMap, cartItemIdsList);
	}
	public JsonObject retrieveCartItemList(java.util.Map<String, String> paramsMap, List<Integer> cartItemIdsList)
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
			String subTotalAmount = paramsMap.get("subTotalAmount");
			if(subTotalAmount==null)
			{
				subTotalAmount = "";
			}
			String grandTotal = paramsMap.get("grandTotal");
			if(grandTotal==null)
			{
				grandTotal = "";
			}
String hql = "FROM CartItem where 1 = 1 ";
			String orderByClauseText = "  ";
			String cartItemIdFilterClass = "";
			if (cartItemIdsList != null && cartItemIdsList.size() > 0)
			{
				cartItemIdFilterClass = " AND cartItemId in (:idsList) ";
			}
						String userCartIdFilterClass = "";
			if (userCartId.length() > 0)
			{			
				
				userCartIdFilterClass = " AND userCartId = :userCartId ";
				
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
			String subTotalAmountFilterClass = "";
			if (subTotalAmount.length() > 0)
			{			
				
				subTotalAmountFilterClass = " AND subTotalAmount = :subTotalAmount ";
				
			}
			String grandTotalFilterClass = "";
			if (grandTotal.length() > 0)
			{			
				
				grandTotalFilterClass = " AND grandTotal = :grandTotal ";
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForCartItem(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					cartItemIdFilterClass +
										userCartIdFilterClass +
					productIdFilterClass +
					productQuantityFilterClass +
					productUnitPriceFilterClass +
					subTotalAmountFilterClass +
					grandTotalFilterClass +

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
			if (cartItemIdsList != null && cartItemIdsList.size() > 0)
			{
				query.setParameterList("idsList", cartItemIdsList);
			}
						if (userCartId.length() > 0)
			{			
				
				query.setParameter("userCartId", Integer.parseInt(userCartId));
				
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
			if (subTotalAmount.length() > 0)
			{			
				
				query.setParameter("subTotalAmount", Integer.parseInt(subTotalAmount));
				
			}
			if (grandTotal.length() > 0)
			{			
				
				query.setParameter("grandTotal", Integer.parseInt(grandTotal));
				
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
	    	setParentFilterClauseParamsForCartItem(paramsMap, query);
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
			JsonArray cartItemList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				CartItem cartItem = (CartItem) it.next();
				JsonObject cartItemDataObject = cartItem.getDataObject(getDBSession());
				cartItemDataObject.addProperty("nextAction", getActionForCurrentStatus(cartItem.getVwTxnStatus()));
				cartItemList.add(cartItemDataObject);
				doAfterSearchResultRowAddedImpl(cartItemDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from CartItem where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (cartItemIdsList != null && cartItemIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", cartItemIdsList);
				}
								if (userCartId.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("userCartId", Integer.parseInt(userCartId));
					
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
				if (subTotalAmount.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("subTotalAmount", Integer.parseInt(subTotalAmount));
					
				}
				if (grandTotal.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("grandTotal", Integer.parseInt(grandTotal));
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForCartItem(paramsMap, countQuery);
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
			dataObject.add("cartItemList",   cartItemList);
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
				+ "   from CartItem E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from CartItem E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentCartItemList(java.util.Map<String, String> paramsMap)
	{
		return retrieveCartItemList(paramsMap);
	}
	public CartItem getCartItemByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from CartItem where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			CartItem cartItem = (CartItem) it.next();
			return cartItem;
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
		CartItem cartItem = (CartItem)getManagedBean();
		JsonObject cartItemDataObject = cartItem.getDataObject(getDBSession());copyCartItemFromJson(cartItem, cartItemDataObject);
		setManagedBean(cartItem);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData CartItemContollerBase");
			CartItem currentBean = (CartItem)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		currentBean.setUserCartId(1);currentBean.setProductId(1);currentBean.setProductQuantity(1);currentBean.setProductUnitPrice(1);currentBean.setSubTotalAmount(1);currentBean.setGrandTotal(1);

		setManagedBean(currentBean);
		debugCode("Out setTestData CartItemContollerBase");
	}
	// end Test Data
	public void copyCartItemFromJson(CartItem cartItem, JsonObject cartItemDataObject)
	{
		copyCartItemFromJson(cartItem, cartItemDataObject, false);
	}
	public void copyCartItemFromJson(CartItem cartItem, JsonObject cartItemDataObject, boolean excludePasswordFields)
	{	
				
		if(cartItemDataObject.has("userCartId"))
		{
			Integer userCartId = null;
			try
			{
			 	userCartId = cartItemDataObject.get("userCartId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(userCartId != null)
			{
				cartItem.setUserCartId(userCartId);
			}
		}if(cartItemDataObject.has("productId"))
		{
			Integer productId = null;
			try
			{
			 	productId = cartItemDataObject.get("productId").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(productId != null)
			{
				cartItem.setProductId(productId);
			}
		}if(cartItemDataObject.has("productQuantity"))
		{
			Integer productQuantity = null;
			try
			{
			 	productQuantity = cartItemDataObject.get("productQuantity").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(productQuantity != null)
			{
				cartItem.setProductQuantity(productQuantity);
			}
		}if(cartItemDataObject.has("productUnitPrice"))
		{
			Integer productUnitPrice = null;
			try
			{
			 	productUnitPrice = cartItemDataObject.get("productUnitPrice").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(productUnitPrice != null)
			{
				cartItem.setProductUnitPrice(productUnitPrice);
			}
		}if(cartItemDataObject.has("subTotalAmount"))
		{
			Integer subTotalAmount = null;
			try
			{
			 	subTotalAmount = cartItemDataObject.get("subTotalAmount").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(subTotalAmount != null)
			{
				cartItem.setSubTotalAmount(subTotalAmount);
			}
		}if(cartItemDataObject.has("grandTotal"))
		{
			Integer grandTotal = null;
			try
			{
			 	grandTotal = cartItemDataObject.get("grandTotal").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(grandTotal != null)
			{
				cartItem.setGrandTotal(grandTotal);
			}
		}
		
	}
		
	public JsonObject createCartItem(JsonObject cartItemDataObject) throws Exception
	{
		return createCartItem(cartItemDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject cartItemDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createCartItem(JsonObject cartItemDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		CartItem cartItem = new CartItem();
		setLoginBasedValues(paramsInfoObj, cartItemDataObject);
		Session session = getDBSession();				
		copyCartItemFromJson(cartItem, cartItemDataObject);
		if(cartItemDataObject.has("legacyRecordId"))
		{
			cartItem.setLegacyRecordId(cartItemDataObject.get("legacyRecordId").getAsInt());
		}
				cartItem.setVwCreatedBy(createdBy);
		cartItem.setVwCreationDate(new Date());
		setPrivateManagedBean(cartItem);
		setManagedBean("CartItemBean", cartItem);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		cartItem = (CartItem) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("cartItemId", cartItem.getCartItemId());
			JsonObject cartItemObj = cartItem.getDataObject(getDBSession());
			cartItemObj.addProperty("nextAction", getActionForCurrentStatus(cartItem.getVwTxnStatus()));
			dataObject.add("cartItemDataObject", cartItemObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateCartItem(JsonObject cartItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer cartItemId = cartItemDataObject.get("cartItemId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("cartItemId", cartItemId);
		JsonObject responseData = retrieveCartItem(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'CartItem' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		CartItem cartItem = (CartItem) session.get(CartItem.class, cartItemId);
		if(cartItem == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(cartItem.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromCartItem(cartItemDataObject);
		boolean excludePasswordFields = true;
		copyCartItemFromJson(cartItem, cartItemDataObject, excludePasswordFields);setPrivateManagedBean(cartItem);
		setManagedBean("CartItemBean", cartItem);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		cartItem.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("cartItemId", cartItemId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromCartItem(JsonObject cartItemDataObject) throws Exception
	{
	}
	public JsonObject deleteCartItem(JsonObject cartItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer cartItemId = cartItemDataObject.get("cartItemId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("cartItemId", cartItemId);
		JsonObject responseData = retrieveCartItem(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'CartItem' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		CartItem cartItem = (CartItem) session.get(CartItem.class, cartItemId);setPrivateManagedBean(cartItem);
		setManagedBean("CartItem", cartItem);
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
	public JsonObject fetchCartItemDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		CartItem cartItem = new CartItem();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(cartItem);
			setManagedBean("CartItem", cartItem);
			doAfterCartItemLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("cartItem", cartItem.getDataObject(getDBSession()));
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
	public JsonObject fetchCartItemTestData(int obj, JsonObject cartItemDataObject) throws Exception
	{
		Session session = getDBSession();
		CartItem cartItem = new CartItem();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyCartItemFromJson(cartItem, cartItemDataObject);
			setPrivateManagedBean(cartItem);
			setManagedBean("CartItem", cartItem);
			doAfterCartItemLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("cartItem", cartItem.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForCartItem(JsonObject cartItemDataObject) throws Exception
	{
		CartItem cartItem = new CartItem();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyCartItemFromJson(cartItem, cartItemDataObject);	String attributeName = cartItemDataObject.get("attributeName").getAsString();
			Integer attributeId = cartItemDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(cartItem);
			setManagedBean("CartItem", cartItem);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("cartItem", cartItem.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForCartItem(JsonObject cartItemDataObject) throws Exception
	{
		CartItem cartItem = new CartItem();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyCartItemFromJson(cartItem, cartItemDataObject);	
			String attributeName = cartItemDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(cartItem);
			setManagedBean("CartItem", cartItem);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("cartItem", cartItem.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject cartItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer cartItemId = cartItemDataObject.get("cartItemId").getAsInt();
			String customEventName = cartItemDataObject.get("customEventName").getAsString();
			CartItem cartItem = (CartItem) session.get(CartItem.class, cartItemId);
			setPrivateManagedBean(cartItem);
			setManagedBean("CartItemBean", cartItem);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("cartItem", cartItem.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnCartItem(JsonObject cartItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer cartItemId = cartItemDataObject.get("cartItemId").getAsInt();
			String currentStatus = cartItemDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(cartItemDataObject.has("currentAction"))
			{
				currentAction = cartItemDataObject.get("currentAction").getAsString();
			}
			CartItem cartItem = (CartItem) session.get(CartItem.class, cartItemId);
			setPrivateManagedBean(cartItem);
			setManagedBean("CartItemBean", cartItem);
			if(cartItem.getIsRequestUnderProcesss() == 1)
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
				executeAction(cartItem, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(cartItem, "ClassInfoBean", currentStatus);
			}
//			executeAction(cartItem, "CartItemBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedcartItemDataObject = cartItem.getDataObject(getDBSession());
				updatedcartItemDataObject.addProperty("nextAction", getActionForCurrentStatus(cartItem.getVwTxnStatus()));
				dataObject.add("cartItem", updatedcartItemDataObject);
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
		CartItem cartItem = (CartItem) getManagedBean();
		String currentStatus = cartItem.getVwTxnStatus();
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
	
	
	public JsonObject downloadCartItemData() throws Exception
	{
		return downloadCartItemData(null);
	}
	public JsonObject downloadCartItemData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "CartItem");
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
			headerName = "cartItemId";
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
			headerName = "subTotalAmount";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "grandTotal";
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
			String hql = "From CartItem ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				CartItem cartItem = (CartItem) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer cartItemId = cartItem.getCartItemId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(cartItemId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer userCartId = cartItem.getUserCartId();
				if(userCartId!=null)
				{
					cell.setCellValue(String.valueOf(userCartId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer productId = cartItem.getProductId();
				if(productId!=null)
				{
					cell.setCellValue(String.valueOf(productId));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer productQuantity = cartItem.getProductQuantity();
				if(productQuantity!=null)
				{
					cell.setCellValue(String.valueOf(productQuantity));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer productUnitPrice = cartItem.getProductUnitPrice();
				if(productUnitPrice!=null)
				{
					cell.setCellValue(String.valueOf(productUnitPrice));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer subTotalAmount = cartItem.getSubTotalAmount();
				if(subTotalAmount!=null)
				{
					cell.setCellValue(String.valueOf(subTotalAmount));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer grandTotal = cartItem.getGrandTotal();
				if(grandTotal!=null)
				{
					cell.setCellValue(String.valueOf(grandTotal));
				}

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "CartItem.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "CartItem.xls";
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
	public JsonObject uploadCartItemData(JsonObject cartItemDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = cartItemDataObject.get("fileId").getAsInt();
		String inputFilesZip = cartItemDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "CartItem Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		cartItemDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadCartItemData(workbook, cartItemDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "CartItem Data uploaded successfully.");
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
	public JsonObject uploadCartItemData(HSSFWorkbook workbook, JsonObject cartItemDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("CartItem");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.shoppingcart.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = cartItemDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = cartItemDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = cartItemDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "CartItem Data uploaded successfully.");
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
			JsonObject cartItem = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject cartItemListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray cartItemList = cartItemListObj.get("cartItemList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadCartItemList(cartItemList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(cartItemList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(cartItemList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray cartItemList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject cartItem = new JsonObject();
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
				JsonObject cartItemUploadObj	= new JsonObject();
				cartItemUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					cartItem = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("cartItemId"))
						{
							String cartItemId = row.getCell(cellIndex++).getStringCellValue();
							if(cartItemId != null && cartItemId.trim().length() > 0)
							{
								try
								{
									Integer iCartItemId = Integer.parseInt(cartItemId);
									if(areSourceDestinationSame == 1)
									{
										CartItem cartItemObj = (CartItem)session.get(CartItem.class, iCartItemId);
										if(cartItemObj != null)
										{ 
											cartItem.addProperty("cartItemId", iCartItemId);
										}
										else
										{
											cartItemUploadObj.addProperty("isDataFetched", 0);
											cartItemUploadObj.addProperty("msg", "This CartItem could not be uploaded as there appears to be some problem with the data(CartItem Id is not exist). ");
											continue;
										}
									}
									else
									{
										CartItem cartItemObj = getCartItemByLegacyRecordId(session, iCartItemId);
										if(cartItemObj != null)
										{ 
											cartItem.addProperty("cartItemId", cartItemObj.getCartItemId());
										}
										cartItem.addProperty("legacyRecordId", iCartItemId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									cartItemUploadObj.addProperty("isDataFetched", 0);
									cartItemUploadObj.addProperty("msg", "This CartItem could not be uploaded as there appears to be some problem with the data(CartItem Id). ");
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
							cartItem.addProperty(parameterName, parameterValue);
						}
					}
					cartItemUploadObj.add("dataObject", cartItem);		    
					cartItemList.add(cartItemUploadObj);
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
			dataObject.add("cartItemList", cartItemList);
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
		if(previousRetryCountMap.has("CartItem") && previousRetryCountMap.get("CartItem").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("CartItem").getAsInt();
		}
		if(retryCountMap.has("CartItem") && retryCountMap.get("CartItem").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("CartItem").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForCartItemList(JsonArray cartItemList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < cartItemList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject cartItemDataObject = cartItemList.get(i).getAsJsonObject();
			JsonObject cartItem = cartItemDataObject.get("dataObject").getAsJsonObject();
			if(cartItemDataObject.has("retryUpload") && cartItemDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = cartItemDataObject.get("retryUpload").getAsInt();
			}
			if(cartItemDataObject.has("retryChildEntitiesUpload") && cartItemDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = cartItemDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(cartItemDataObject.has("partialUploadUnderProcess") && cartItemDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = cartItemDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("CartItem", retryCount);
	}
	public JsonObject uploadCartItemList(JsonArray cartItemList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(cartItemList);
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
	public JsonObject updateStatusMsg(JsonArray cartItemList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < cartItemList.size(); i++)
			{
				JsonObject cartItemDataObject = cartItemList.get(i).getAsJsonObject();
				JsonObject cartItem = cartItemDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = cartItemDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = cartItemDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = cartItemDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray cartItemList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < cartItemList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject cartItemDataObject = cartItemList.get(i).getAsJsonObject();
				JsonObject cartItem = cartItemDataObject.get("dataObject").getAsJsonObject();
				cartItemDataObject.addProperty("retryUpload", 0);
				cartItemDataObject.addProperty("retryChildEntitiesUpload", 0);
				cartItemDataObject.addProperty("partialUploadUnderProcess", 0);
				com.shoppingcart.controller.forms.impl.CartItemControllerImpl cartItemImplObj = new com.shoppingcart.controller.forms.impl.CartItemControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = cartItemImplObj.getEntityInfoUpdatedWithLookupIds(session, cartItem, retValObject);
				if(areAllLookupsFound!=1)
				{
					cartItemDataObject.addProperty("retryUpload", 1);
					cartItemDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					cartItemDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					cartItemDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int cartItemId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !cartItem.has("cartItemId"))
				{
					cartItem.addProperty("attributeNamePrefix", "");
					
					cartItem.addProperty("attributeNamePrefix", "");
					JsonObject responseData = cartItemImplObj.getCartItemByLookupFields(session,  cartItem);
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
						JsonObject cartItemObject = responseData.get("cartItemDataObject").getAsJsonObject();
						cartItemId = cartItemObject.get("cartItemId").getAsInt();
						cartItem.addProperty("cartItemId", cartItemId);
						updateEntity = true;
					}
				}
				else if(cartItem.has("cartItemId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = cartItemImplObj.createCartItem(cartItem);
				}
				else
				{
					responseData = cartItemImplObj.updateCartItem(cartItem);
				}
				cartItemDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					cartItemId =-1;
					cartItemDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					cartItemDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				cartItemId = responseData.get("cartItemId").getAsInt();
				cartItemDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject cartItem, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getCartItemByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From CartItem where 1 = 1  ";
			String countHql = "select count(*)  from CartItem where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as CartItem could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				CartItem cartItem = (CartItem) it.next();
				JsonObject cartItemDataObject = cartItem.getDataObject(session);
				dataObject.add("cartItemDataObject", cartItemDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as CartItem could not be retrieved");
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
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'CartItem' could not be retrieved");
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
		else if(apiName.equals("getCartItemPropertyValue"))
			{
				JsonObject inputDataObject = getCartItemPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getCartItem"))
			{
				JsonObject inputDataObject = getCartItem(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForCartItem"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForCartItem"))
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
			Integer cartItemId = requestReceivedParametersInfo.get("cartItemId").getAsInt();
			CartItem cartItem = (CartItem) session.get(CartItem.class, cartItemId);
			cartItem.setIsRequestUnderProcesss(0);
			session.merge(cartItem);
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
	public JsonObject getCartItemPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer cartItemId = inputForGetAPI.get("cartItemId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		CartItem cartItem = (CartItem) session.get(CartItem.class, cartItemId);
		cartItem.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getCartItem(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer cartItemId = inputForGetAPI.get("cartItemId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("cartItemId", String.valueOf(cartItemId));
		JsonObject cartItemListObject = retrieveCartItemList(paramsMap);
		if(cartItemListObject!=null && cartItemListObject.has("success") && cartItemListObject.get("success").getAsInt()==1)
		{
			JsonArray cartItemList = cartItemListObject.get("cartItemList").getAsJsonArray();
			if(cartItemList.size()>0)
			{
				dataObject.add("cartItem", cartItemList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'CartItem' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject cartItemDataObject, CartItem cartItem);
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
	public abstract void doAfterLookupRowSelectedImpl(CartItem cartItem, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(CartItem cartItem, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(CartItem cartItem, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
	//public abstract void doAfterDeleteTransactionImpl(Session organisationSession);
}
