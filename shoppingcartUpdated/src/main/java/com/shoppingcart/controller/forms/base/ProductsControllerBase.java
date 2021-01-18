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

import com.shoppingcart.bean.Products;
import com.shoppingcart.controller.forms.base.ProductsLabel;
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
public abstract class ProductsControllerBase extends BusinessRulesController
{
	/*
	 * Entity attribute names
	 *		 * 'ProductName' 
	 *		 * 'ProductDescription' 
	 *		 * 'ProductPrice' 
	 *		 * 'ProductUnitType' 
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
	protected ProductsLabel ProductsLabelLocal = new ProductsLabel();
	protected Products ProductsLocal = new Products();
	protected Object localManagedBean = null;
	protected VWMastersBean localMasters = new VWMastersBean();
	protected VWSessionObject vwSessionObject = (VWSessionObject)getSessionObject();
	public ProductsControllerBase(Session session)
	{
		super(session);
		userSessionInfo.addProperty("userId", -1);
		userSessionInfo.addProperty("loginEntityType", "");
	}
	public ProductsControllerBase(Session session, JsonObject userLoggedInfo)
	{
		super(session);
		userSessionInfo = userLoggedInfo;
	}
	public ProductsControllerBase()
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
		return "Products" + "Id";
	}
    protected String getTxnStatus()
	{
		return ((Products)getManagedBean()).getVwTxnStatus();
	}
	public String getLcNo()
	{
		return getLcNoImpl();
	}
	public void setTxnStatus(String sStatus)
	{
		((Products)getManagedBean()).setVwTxnStatus(sStatus);
	}
	public Integer getPKValue()
	{
		return iPKValue;
	}
	protected void setPKValue(Object obj)
	{
		iPKValue=((Products)obj).getProductsId();
	}
	public String getManagedBeanName()
    {
		return "ProductsBean";
    }
    protected String getManagedBeanNameLabel()
    {
		return "ProductsLabelBean";
    }
	protected Class<Products> setBeanClass()
	{
		return Products.class;
	}
	protected Class<ProductsLabel> setBeanLabelClass()
	{
		return ProductsLabel.class;
	}
	protected Products getLocalManagedBean()
    {
		return (Products)getManagedBean();
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
		/*Products products = (Products)getManagedBean();
		String areChangesEffected = products.getAreChangesEffected();
		if("YES".equalsIgnoreCase(areChangesEffected))
		{
			addCustomResponse("Changes already applied to this transaction !!");
		}
		else
		{
			products.setAreChangesEffected("YES");			
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
		/*Products products = (Products)getManagedBean();
		String areChangesEffected = products.getAreChangesEffected();
		if(!("YES".equalsIgnoreCase(areChangesEffected)))
		{
			addCustomResponse("There are no changes to roll back !!");
		}
		else
		{
			products.setAreChangesEffected("NO");			
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
		/*Products products = (Products)getManagedBean();
		String areChangesEffected = products.getAreChangesEffected();
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
		Products products = (Products)getManagedBean();
		String sCurrentStatus = products.getVwTxnStatus();
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
		Products products = (Products)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}//doAfterSelectOptionChangedImpl(products, attributeName);
		setHasTransactionFailed(false);
	}
	public void doAfterProductsLoaded(int obj, JsonObject initParamsInfo)
	{
		if(initParamsInfo==null)
		{
			initParamsInfo = new JsonObject();
		}
		JsonObject dataObject = new JsonObject();
		Products products = (Products)getManagedBean();  
		/*
		 * Load data from initParamsInfo
		 */
				
		if(initParamsInfo.has("productName") && initParamsInfo.get("productName").isJsonNull()==false)
		{
			String productName = initParamsInfo.get("productName").getAsString();
			products.setProductName(productName);			
		}if(initParamsInfo.has("productDescription") && initParamsInfo.get("productDescription").isJsonNull()==false)
		{
			String productDescription = initParamsInfo.get("productDescription").getAsString();
			products.setProductDescription(productDescription);			
		}if(initParamsInfo.has("productPrice") && initParamsInfo.get("productPrice").isJsonNull()==false)
		{
			Integer productPrice = null;
			try
			{
			 	productPrice = initParamsInfo.get("productPrice").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			products.setProductPrice(productPrice);
		}if(initParamsInfo.has("productUnitType") && initParamsInfo.get("productUnitType").isJsonNull()==false)
		{
			String productUnitType = initParamsInfo.get("productUnitType").getAsString();
			products.setProductUnitType(productUnitType);			
		}

		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterEntityLoadedImpl(products, initParamsInfo);
		if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
	}
	public void doExecuteCustomAPI(String customAPIMessage)
	{
		Products products = (Products)getManagedBean();
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}		  
		doExecuteCustomAPIImpl(customAPIMessage);
	}
	public void doAfterLookupRowSelected(String attributeName, Integer attributeId)
	{
		Products products = (Products)getManagedBean();  
		JsonObject dataObject = new JsonObject();		
		if(1>2)
		{
		}if(getHasTransactionFailed()==true)
		{
			addCustomResponse(getTransactionFailureMessage());
		}
		setHasTransactionFailed(false);
		doAfterLookupRowSelectedImpl(products, attributeName);
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
			Products products = (Products)getPrivateManagedBean();
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
							Integer iMqEntityId = mqservice.writeToQueue("CREATED", messageQueue.getMyBankBIC(), messageQueue.getTheirBankBIC(), messageQueue.getMsgFromChannel(), messageQueue.getMsgToChannel(), "Products", generateMGUID(), messageQueue.getMsgFormat(), "", sMsgGroupName, sMsgCategory, sMsgDirection, "", "", "", messageQueue.getMsgAppId(), messageQueue.getMsgServiceId(), sCurrentLCNo, sEntityId, (String)getAuthAmtCcy(), (BigDecimal)getAuthAmt());
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
		debugCode("In getSearchParams() ProductsContollerBase");
		HashMap<String,Object> searchParams = new HashMap<String,Object>();
		/*searchBeanLocal = (ProductsSearch)getManagedBean("ProductsSearchBean");
		if (isExists(searchBeanLocal))
		{
						if (isExists(searchBeanLocal.getProductName()))
			{
				searchParams.put(ProductsLabelLocal.getproductNameFieldName(),searchBeanLocal.getProductName());
			}	
			if (isExists(searchBeanLocal.getProductDescription()))
			{
				searchParams.put(ProductsLabelLocal.getproductDescriptionFieldName(),searchBeanLocal.getProductDescription());
			}	
			if (isExists(searchBeanLocal.getProductPrice()))
			{
				searchParams.put(ProductsLabelLocal.getproductPriceFieldName(),searchBeanLocal.getProductPrice());
			}	
			if (isExists(searchBeanLocal.getProductUnitType()))
			{
				searchParams.put(ProductsLabelLocal.getproductUnitTypeFieldName(),searchBeanLocal.getProductUnitType());
			}	

			if (isExists(searchBeanLocal.getVwTxnStatus()))
			{
				searchParams.put(ProductsLabelLocal.getvwTxnStatusFieldName(),searchBeanLocal.getVwTxnStatus());
			}	
		}
		*/
		debugCode("Out getSearchParams() ProductsContollerBase");
        return searchParams;
	}
	public void setValues(Object sourceBean,Object targetBean,Boolean bSetAsManagedBean)
	{
		debugCode("In setValues ProductsContollerBase");
		targetBean = (Products)targetBean;((Products)targetBean).setProductsId(((Products)sourceBean).getProductsId());
				((Products)targetBean).setProductName(((Products)sourceBean).getProductName());
		((Products)targetBean).setProductDescription(((Products)sourceBean).getProductDescription());
		((Products)targetBean).setProductPrice(((Products)sourceBean).getProductPrice());
		((Products)targetBean).setProductUnitType(((Products)sourceBean).getProductUnitType());

		doAfterSetValues();
		debugCode("Out setValues ProductsContollerBase");
	}
	public Object getFieldValue(Object entityObject,String sFieldName)
	{
		com.shoppingcart.bean.Products entityBean = (Products)entityObject;
	 	if (sFieldName.equalsIgnoreCase("productsId")) 
	 	{
			return entityBean.getProductsId();
		}
	 	 	if (sFieldName.equalsIgnoreCase("ProductName")) 
	 	{
			return entityBean.getProductName();
		}
	 	if (sFieldName.equalsIgnoreCase("ProductDescription")) 
	 	{
			return entityBean.getProductDescription();
		}
	 	if (sFieldName.equalsIgnoreCase("ProductPrice")) 
	 	{
			return entityBean.getProductPrice();
		}
	 	if (sFieldName.equalsIgnoreCase("ProductUnitType")) 
	 	{
			return entityBean.getProductUnitType();
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
		debugCode("In doEnrichSystemValues(String sAction) ProductsControllerBase");
		localManagedBean = getLocalManagedBean();
		String sActionBy = "AUTO";
		((Products) localManagedBean).setVwLastModifiedDate(new Date());
		((Products) localManagedBean).setVwLastModifiedTime(getCurrentTime());
		((Products) localManagedBean).setVwLastAction(sAction);
		if (isExists(sNextStatus)) 
		{
			((Products) localManagedBean).setVwTxnStatus(sNextStatus);
		}
		else if (sAction.matches(ACTION_CREATE)) 
		{
			((Products) localManagedBean).setVwTxnStatus("CREATED");
			((Products) localManagedBean).setIsRequestUnderProcesss(0);
		}
		else if (sAction.matches(ACTION_UPDATE)) 
		{
			//((Products) localManagedBean).setVwTxnStatus("MODIFIED");
		}
		if (isActionFromUI())
		{
			sActionBy = getLoggedInUser();
		}	
		((Products) localManagedBean).setVwModifiedBy(sActionBy);
		//doEnrichCustomLKValues();
		debugCode("Out doEnrichSystemValues(String sAction) ProductsControllerBase");
	}
	protected void doEnrichAuditValues(String sAction)
	{
		debugCode("In doEnrichAuditValues(String sAction) ProductsControllerBase");
		debugCode("Out doEnrichAuditValues(String sAction) ProductsControllerBase");
	}
	protected void validateRepeatLine(String sRepeatTagName, String string, int iCurrIndex)
	{
		doValidateRepeatLineImpl(sRepeatTagName, string, iCurrIndex);
	}
		
	
	
	
	
	
	
		
	
	
	public void doValidations() 
	{
		debugCode("In doValidations ProductsControllerBase");
		//NG: Important comment
		//managedBean = (Products) getManagedBean();
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
		debugCode("Out doValidations ProductsControllerBase");
	}
	private void doAllowedDecimalValidation()
	{
		debugCode("In doAllowedDecimalValidation ProductsControllerBase");
		debugCode("Out doAllowedDecimalValidation ProductsControllerBase");
	}
	private void doAllowedValuesValidation()
	{
		debugCode("In doAllowedValuesValidation ProductsControllerBase");debugCode("Out doAllowedValuesValidation ProductsControllerBase");
	}
	private void doMandatoryValidation()
	{
		debugCode("In doMandatoryValidation ProductsControllerBase");
		debugCode("Out doMandatoryValidation ProductsControllerBase");
	}
	private void doLengthValidation()
	{
		debugCode("In doLengthValidation ProductsControllerBase");
				
		String sFieldValue2 = ((Products) localManagedBean).getProductName();String sFieldValue3 = ((Products) localManagedBean).getProductDescription();Integer sFieldValue4 = ((Products) localManagedBean).getProductPrice();		String sFieldValue5 = ((Products) localManagedBean).getProductUnitType();
		if (!isLengthAllowed(sFieldValue2,"50")) addMaxLengthResponse(ProductsLabelLocal.getproductNameFieldName(),"50");
		if (!isLengthAllowed(sFieldValue3,"50")) addMaxLengthResponse(ProductsLabelLocal.getproductDescriptionFieldName(),"50");
		if (!isLengthAllowed(sFieldValue4,"20")) addMaxLengthResponse(ProductsLabelLocal.getproductPriceFieldName(),"20");
		if (!isLengthAllowed(sFieldValue5,"50")) addMaxLengthResponse(ProductsLabelLocal.getproductUnitTypeFieldName(),"50");
debugCode("Out doLengthValidation ProductsControllerBase");
	}
	private void doDataTypeValidation()
	{
		debugCode("In doDataTypeValidation ProductsControllerBase");
		debugCode("Out doDataTypeValidation ProductsControllerBase");
	}
	private void doUniqueValidation()
	{
	 	debugCode("In doUniqueValidation ProductsContollerBase");
	 	if (getCurrentAction().matches(ACTION_CREATE))
	 	{
		 	Integer iFieldValueFK = 0;
			
		}	
		debugCode("In doUniqueValidation ProductsContollerBase");
	}
	public String getLabel(String sResponseField)
	{
		debugCode("IN getLabel ProductsContollerBase");
		String sLabel = new ProductsLabel().getLabel(sResponseField); 
		debugCode("Out getLabel ProductsContollerBase");
		return sLabel;
	}	
	public JsonObject getEntityAttributeValue(JsonObject requestInfo) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			int productsId = requestInfo.get("objectId").getAsInt();
			JsonObject searchParams = new JsonObject();
			searchParams.addProperty("productsId", productsId);
			JsonObject responseData = retrieveProducts(searchParams, new JsonObject());
			if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
			{
				dataObject.addProperty("alert", "'Products' could not be retrieved !!");			
				dataObject.addProperty("success", 0);			
				return dataObject;
			}
			String attributeName = requestInfo.get("attributeName").getAsString();
			JsonObject productsDataObject = responseData.get("productsDataObject").getAsJsonObject();
			dataObject.addProperty(attributeName, productsDataObject.get(attributeName).getAsString());
			dataObject.addProperty("success", 1);
			return dataObject;
		} 
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "'Products' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject retrieveProducts(JsonObject requestParams, JsonObject paramsInfoObj) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer productsId = -1;
		if(requestParams.has("productsId"))
		{
			productsId = requestParams.get("productsId").getAsInt();
		}
		Map<String, String> paramsMap = new HashMap<String, String>();paramsMap.put("productsId", String.valueOf(productsId));
				String productName = "";
		if(requestParams.has("productName"))
		{
			paramsMap.put("productName", requestParams.get("productName").getAsString());
		}
		String productDescription = "";
		if(requestParams.has("productDescription"))
		{
			paramsMap.put("productDescription", requestParams.get("productDescription").getAsString());
		}
		String productPrice = "";
		if(requestParams.has("productPrice"))
		{
			paramsMap.put("productPrice", requestParams.get("productPrice").getAsString());
		}
		String productUnitType = "";
		if(requestParams.has("productUnitType"))
		{
			paramsMap.put("productUnitType", requestParams.get("productUnitType").getAsString());
		}

		JsonObject productsListObject = retrieveProductsList(paramsMap);
		if(productsListObject!=null && productsListObject.has("success") && productsListObject.get("success").getAsInt()==1)
		{
			JsonArray productsList = productsListObject.get("productsList").getAsJsonArray();
			if(productsList.size()>0)
			{
				dataObject.add("productsDataObject", productsList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Products' could not be retrieved !!");			
		dataObject.addProperty("success", 0);			
		return dataObject;
	}
	public JsonObject getProducts(Session session, JsonObject searchParams, String overrideWhereClause, String whereClause, String orderByClause)
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
						String productName = "";
			if(searchParams.has("productName"))
			{
				paramsMap.put("productName", searchParams.get("productName").getAsString());
			}
			String productDescription = "";
			if(searchParams.has("productDescription"))
			{
				paramsMap.put("productDescription", searchParams.get("productDescription").getAsString());
			}
			String productPrice = "";
			if(searchParams.has("productPrice"))
			{
				paramsMap.put("productPrice", searchParams.get("productPrice").getAsString());
			}
			String productUnitType = "";
			if(searchParams.has("productUnitType"))
			{
				paramsMap.put("productUnitType", searchParams.get("productUnitType").getAsString());
			}

			paramsMap.put("overrideWhereClause", overrideWhereClause);
			paramsMap.put("whereClause", whereClause);
			paramsMap.put("orderByClause", orderByClause);
			paramsMap.put("overrideOrderByClause", "Yes");
			JsonObject productsListObject = retrieveProductsList(paramsMap);
			if(productsListObject!=null && productsListObject.has("success") && productsListObject.get("success").getAsInt()==1)
			{
				JsonArray productsList = productsListObject.get("productsList").getAsJsonArray();
				if(productsList.size()>0)
				{
					dataObject.add("products", productsList.get(0).getAsJsonObject());
					dataObject.addProperty("success", 1);
					return dataObject;				
				}
			}
		}
		catch (Exception e)
		{
			writeToLog(CommonUtil.getStackTrace(e));
		}
		dataObject.addProperty("alert", "Information of 'Products' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;
	}
	public JsonObject getProductsInJson(int productsId)
	{
		JsonObject ProductsData = null;
		List<Integer> productsIdsList = new ArrayList<>();
		productsIdsList.add(productsId);
		JsonArray productsList = getProductsListInJson(productsIdsList);
		if(productsList!=null && productsList.size()>0)
		{
			ProductsData = productsList.get(0).getAsJsonObject();
		}
		return ProductsData;
	}
	public JsonArray getProductsListInJson(List<Integer> productsIdsList)
	{
		Map<String, String> paramsMap = new HashMap<String, String>();
		JsonArray productsObjectsList = new JsonArray();
		JsonObject productsListObject = retrieveProductsList(paramsMap, productsIdsList);
		if(productsListObject!=null && productsListObject.has("success") && productsListObject.get("success").getAsInt()==1)
		{
			JsonArray productsList = productsListObject.get("productsList").getAsJsonArray();
			for (int i =0; i< productsList.size(); i++)
			{
				JsonObject productsDataObject = productsList.get(i).getAsJsonObject();
				int productsId = productsDataObject.get("productsId").getAsInt();
				
				productsObjectsList.add(productsDataObject);
			}
		}
		return productsObjectsList;
	}
		
	public String getUploadedDataErrorMessage(Session session, JsonArray productsList)
	{
		String errorMessage = "productsList: \n";
		for (int i =0; i< productsList.size(); i++)
		{
			JsonObject productsDataObject = productsList.get(i).getAsJsonObject();
			JsonObject products = productsDataObject.get("dataObject").getAsJsonObject();if(!productsDataObject.has("isSuccessfullyUploaded"))
			{
				errorMessage += "products could not be uploaded verify data \n"; 
			}
			else if(productsDataObject.has("isSuccessfullyUploaded") 
					&& productsDataObject.get("isSuccessfullyUploaded").getAsInt() == 0)
			{
				errorMessage += productsDataObject.get("errorMessage").getAsString() +"\n"; 
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
		else if("Products".equalsIgnoreCase(loginEntityType))
		{
			loginBasedWhereClause = " AND productsId = :productsId ";
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
		else if("Products".equalsIgnoreCase(loginEntityType))
		{
			query.setParameter("productsId", userId);
		}
		
	}
	public String getParentFilterClauseForProducts(java.util.Map<String, String> paramsMap)
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
	public void setParentFilterClauseParamsForProducts(java.util.Map<String, String> paramsMap, Query query)
	{
	}
	public JsonObject retrieveProductsList(java.util.Map<String, String> paramsMap)
	{
		List<Integer> productsIdsList = new ArrayList<>();
		if(paramsMap.containsKey("productsId"))
		{
			int productsId = Integer.parseInt(paramsMap.get("productsId"));
			if(productsId>0)
			{
				productsIdsList.add(productsId);
			}
		}
		return retrieveProductsList(paramsMap, productsIdsList);
	}
	public JsonObject retrieveProductsList(java.util.Map<String, String> paramsMap, List<Integer> productsIdsList)
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
						String productName = paramsMap.get("productName");
			if(productName==null)
			{
				productName = "";
			}
			String productDescription = paramsMap.get("productDescription");
			if(productDescription==null)
			{
				productDescription = "";
			}
			String productPrice = paramsMap.get("productPrice");
			if(productPrice==null)
			{
				productPrice = "";
			}
			String productUnitType = paramsMap.get("productUnitType");
			if(productUnitType==null)
			{
				productUnitType = "";
			}
String hql = "FROM Products where 1 = 1 ";
			String orderByClauseText = "  ";
			String productsIdFilterClass = "";
			if (productsIdsList != null && productsIdsList.size() > 0)
			{
				productsIdFilterClass = " AND productsId in (:idsList) ";
			}
						String productNameFilterClass = "";
			if (productName.length() > 0)
			{		
				
				productNameFilterClass = " AND productName LIKE :productName ";	
				
			}
			String productDescriptionFilterClass = "";
			if (productDescription.length() > 0)
			{		
				
				productDescriptionFilterClass = " AND productDescription LIKE :productDescription ";	
				
			}
			String productPriceFilterClass = "";
			if (productPrice.length() > 0)
			{			
				
				productPriceFilterClass = " AND productPrice = :productPrice ";
				
			}
			String productUnitTypeFilterClass = "";
			if (productUnitType.length() > 0)
			{		
				
				productUnitTypeFilterClass = " AND productUnitType LIKE :productUnitType ";	
				
			}
String txnStatusFilterClass = "";
			if (txnStatus.length() > 0)
			{
				txnStatusFilterClass = " AND vwTxnStatus = :txnStatus";
			}
	        orderByClauseText = doGetOrderByClauseSearchImpl();
			String parentFilterClause  = getParentFilterClauseForProducts(paramsMap);	        
			String lookupDisplayFilterClause  = getLookupDisplayFilterClause();
			if(lookupDisplayPrefix.length() == 0)
			{
				lookupDisplayFilterClause = "";
			}
			String attributesFilterClause = 
					productsIdFilterClass +
										productNameFilterClass +
					productDescriptionFilterClass +
					productPriceFilterClass +
					productUnitTypeFilterClass +

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
			if (productsIdsList != null && productsIdsList.size() > 0)
			{
				query.setParameterList("idsList", productsIdsList);
			}
						if (productName.length() > 0)
			{		
				
				query.setParameter("productName", productName);	
				
			}
			if (productDescription.length() > 0)
			{		
				
				query.setParameter("productDescription", productDescription);	
				
			}
			if (productPrice.length() > 0)
			{			
				
				query.setParameter("productPrice", Integer.parseInt(productPrice));
				
			}
			if (productUnitType.length() > 0)
			{		
				
				query.setParameter("productUnitType", productUnitType);	
				
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
	    	setParentFilterClauseParamsForProducts(paramsMap, query);
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
			JsonArray productsList = new JsonArray();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Products products = (Products) it.next();
				JsonObject productsDataObject = products.getDataObject(getDBSession());
				productsDataObject.addProperty("nextAction", getActionForCurrentStatus(products.getVwTxnStatus()));
				productsList.add(productsDataObject);
				doAfterSearchResultRowAddedImpl(productsDataObject, queryParamsMap);
			}
			if (noOfRecordsAlreadyFetched == 0)
			{
				String countHql = "select count(*)  from Products where 1 = 1 ";
				countHql +=  whereClauseText;
				Query countQuery = getDBSession().createQuery(countHql);
				if (productsIdsList != null && productsIdsList.size() > 0)
				{
					countQuery.setParameterList("idsList", productsIdsList);
				}
								if (productName.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("productName", productName);
					
					
					
					
				}
				if (productDescription.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("productDescription", productDescription);
					
					
					
					
				}
				if (productPrice.length() > 0)
				{
					
					
					
					
					
					
					
					
					
					
					countQuery.setParameter("productPrice", Integer.parseInt(productPrice));
					
				}
				if (productUnitType.length() > 0)
				{
					
					
					
					
					
					
					
					countQuery.setParameter("productUnitType", productUnitType);
					
					
					
					
				}

				
				setLoginBasedWhereClauseParams(paramsMap, countQuery);
		    	setParentFilterClauseParamsForProducts(paramsMap, countQuery);
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
			dataObject.add("productsList",   productsList);
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
				+ "   from Products E GROUP BY year(E.vwCreationDate), month(E.vwCreationDate) ";
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
				+ "   from Products E GROUP BY E.vwTxnStatus ";
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
	public JsonObject retrieveDependentProductsList(java.util.Map<String, String> paramsMap)
	{
		return retrieveProductsList(paramsMap);
	}
	public Products getProductsByLegacyRecordId(Session session, Integer legacyRecordId)
	{
		String hql = "from Products where legacyRecordId = :legacyRecordId ";
		Query query = getDBSession().createQuery(hql);
		query.setParameter("legacyRecordId", legacyRecordId);
		List resultsList = query.list();
		for (Iterator it = resultsList.iterator(); it.hasNext();)
		{
			Products products = (Products) it.next();
			return products;
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
		Products products = (Products)getManagedBean();
		JsonObject productsDataObject = products.getDataObject(getDBSession());copyProductsFromJson(products, productsDataObject);
		setManagedBean(products);
		//setUpdatedBean(); 
	}	
	// Begin Test Data
	public void setTestData()
	{
		debugCode("In setTestData ProductsContollerBase");
			Products currentBean = (Products)getManagedBean();
		int iFieldLength = 0;
		int iFieldCounter = 1;
		String sFieldLength;
		String sStringTestData;
				
		iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "ProductName".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setProductName(sStringTestData);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "ProductDescription".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setProductDescription(sStringTestData);currentBean.setProductPrice(1);iFieldLength = 0;
		sFieldLength = "50";
		sStringTestData = "ProductUnitType".toUpperCase() + iFieldCounter;
		if (sFieldLength!=null && sFieldLength.trim().length()>0)
		{
			iFieldLength = Integer.parseInt(sFieldLength);
			if (sStringTestData.length() > iFieldLength)
			{
				sStringTestData = sStringTestData.substring(0, iFieldLength);
			}
		}		
		currentBean.setProductUnitType(sStringTestData);

		setManagedBean(currentBean);
		debugCode("Out setTestData ProductsContollerBase");
	}
	// end Test Data
	public void copyProductsFromJson(Products products, JsonObject productsDataObject)
	{
		copyProductsFromJson(products, productsDataObject, false);
	}
	public void copyProductsFromJson(Products products, JsonObject productsDataObject, boolean excludePasswordFields)
	{	
				
		if(productsDataObject.has("productName"))
		{
			String productName = productsDataObject.get("productName").getAsString();
			products.setProductName(productName);
		}if(productsDataObject.has("productDescription"))
		{
			String productDescription = productsDataObject.get("productDescription").getAsString();
			products.setProductDescription(productDescription);
		}if(productsDataObject.has("productPrice"))
		{
			Integer productPrice = null;
			try
			{
			 	productPrice = productsDataObject.get("productPrice").getAsInt();
			}
			catch (Exception e)
			{
				// TODO: handle exception
			}
			if(productPrice != null)
			{
				products.setProductPrice(productPrice);
			}
		}if(productsDataObject.has("productUnitType"))
		{
			String productUnitType = productsDataObject.get("productUnitType").getAsString();
			products.setProductUnitType(productUnitType);
		}
		
	}
		
	public JsonObject createProducts(JsonObject productsDataObject) throws Exception
	{
		return createProducts(productsDataObject, -1, null);
	}
	public void setLoginBasedValues(JsonObject paramsInfoObj, JsonObject productsDataObject)
	{
		JsonObject userSessionInfo = getUserSessionInfo();			
		int userId = userSessionInfo.get("userId").getAsInt();
		String loginEntityType = userSessionInfo.get("loginEntityType").getAsString();
		String loginBasedWhereClause = "";
		if(1>2)
		{
		}
		
	}
	public JsonObject createProducts(JsonObject productsDataObject, int createdBy, JsonObject paramsInfoObj) throws Exception
	{
		Products products = new Products();
		setLoginBasedValues(paramsInfoObj, productsDataObject);
		Session session = getDBSession();				
		copyProductsFromJson(products, productsDataObject);
		if(productsDataObject.has("legacyRecordId"))
		{
			products.setLegacyRecordId(productsDataObject.get("legacyRecordId").getAsInt());
		}
				products.setVwCreatedBy(createdBy);
		products.setVwCreationDate(new Date());
		setPrivateManagedBean(products);
		setManagedBean("ProductsBean", products);
		if (getHasTransactionFailed() == false)
		{
			create(paramsInfoObj);
		}
		products = (Products) getManagedBean();
		JsonObject dataObject = new JsonObject();
		if (getHasTransactionFailed() == true)
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
		}
		else
		{
			dataObject.addProperty("alert", "Transaction created Successfully");
			dataObject.addProperty("productsId", products.getProductsId());
			JsonObject productsObj = products.getDataObject(getDBSession());
			productsObj.addProperty("nextAction", getActionForCurrentStatus(products.getVwTxnStatus()));
			dataObject.add("productsDataObject", productsObj);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public JsonObject updateProducts(JsonObject productsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer productsId = productsDataObject.get("productsId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("productsId", productsId);
		JsonObject responseData = retrieveProducts(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Products' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Products products = (Products) session.get(Products.class, productsId);
		if(products == null)
		{
			setTransactionFailureMessage("Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("alert", "Your request could not be processed as selected entity/transaction didn't exist.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		if(products.getIsRequestUnderProcesss() == 1)
		{
			setTransactionFailureMessage("Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("alert", "Your request could not be processed as pending request under process for this transaction.");
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		removeUpdateDisabledFromProducts(productsDataObject);
		boolean excludePasswordFields = true;
		copyProductsFromJson(products, productsDataObject, excludePasswordFields);setPrivateManagedBean(products);
		setManagedBean("ProductsBean", products);
		if(!isActionAllowedOnTransaction(ACTION_UPDATE))
		{
			dataObject.addProperty("alert", getTransactionFailureMessage());
			dataObject.addProperty("success", 0);
			return dataObject;
		}
		products.setVwTxnStatus("MODIFIED");if (getHasTransactionFailed() == false)
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
			dataObject.addProperty("productsId", productsId);
			dataObject.addProperty("success", 1);
		}
		return dataObject; 
	}
	public void removeUpdateDisabledFromProducts(JsonObject productsDataObject) throws Exception
	{
	}
	public JsonObject deleteProducts(JsonObject productsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		Integer productsId = productsDataObject.get("productsId").getAsInt();
		JsonObject searchParams = new JsonObject();
		searchParams.addProperty("productsId", productsId);
		JsonObject responseData = retrieveProducts(searchParams, new JsonObject());
		if(!responseData.has("success") || responseData.get("success").getAsInt() !=1)
		{
			dataObject.addProperty("alert", "Your request could not be processed as, selected 'Products' could not be found!!");			
			dataObject.addProperty("success", 0);			
			return dataObject;
		}
		Products products = (Products) session.get(Products.class, productsId);setPrivateManagedBean(products);
		setManagedBean("Products", products);
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
	public JsonObject fetchProductsDefaultData(int obj, JsonObject initParams) throws Exception
	{
		Session session = getDBSession();
		Products products = new Products();
		JsonObject dataObject = new JsonObject();
		try
		{
			setPrivateManagedBean(products);
			setManagedBean("Products", products);
			doAfterProductsLoaded(obj, initParams);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("products", products.getDataObject(getDBSession()));
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
	public JsonObject fetchProductsTestData(int obj, JsonObject productsDataObject) throws Exception
	{
		Session session = getDBSession();
		Products products = new Products();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyProductsFromJson(products, productsDataObject);
			setPrivateManagedBean(products);
			setManagedBean("Products", products);
			doAfterProductsLoaded(obj, null);
			setTestData();			
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("products", products.getDataObject(getDBSession()));
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
	public JsonObject lookupRowSelectedForProducts(JsonObject productsDataObject) throws Exception
	{
		Products products = new Products();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyProductsFromJson(products, productsDataObject);	String attributeName = productsDataObject.get("attributeName").getAsString();
			Integer attributeId = productsDataObject.get("attributeId").getAsInt();
			setPrivateManagedBean(products);
			setManagedBean("Products", products);
			doAfterLookupRowSelected(attributeName, attributeId);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("products", products.getDataObject(getDBSession()));
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
	public JsonObject selectOptionChangedForProducts(JsonObject productsDataObject) throws Exception
	{
		Products products = new Products();
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			copyProductsFromJson(products, productsDataObject);	
			String attributeName = productsDataObject.get("attributeName").getAsString();
			setPrivateManagedBean(products);
			setManagedBean("Products", products);
			doAfterSelectOptionChanged(attributeName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("products", products.getDataObject(getDBSession()));
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
	public JsonObject doExecuteCustomAPI(JsonObject productsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer productsId = productsDataObject.get("productsId").getAsInt();
			String customEventName = productsDataObject.get("customEventName").getAsString();
			Products products = (Products) session.get(Products.class, productsId);
			setPrivateManagedBean(products);
			setManagedBean("ProductsBean", products);
			beginTransaction();
			doExecuteCustomAPI(customEventName);
			if (getHasTransactionFailed() == false)
			{
				dataObject.add("products", products.getDataObject(getDBSession()));
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
	public JsonObject executeAuthorisationOnProducts(JsonObject productsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			Integer productsId = productsDataObject.get("productsId").getAsInt();
			String currentStatus = productsDataObject.get("currentStatus").getAsString();
			String currentAction = "";
			if(productsDataObject.has("currentAction"))
			{
				currentAction = productsDataObject.get("currentAction").getAsString();
			}
			Products products = (Products) session.get(Products.class, productsId);
			setPrivateManagedBean(products);
			setManagedBean("ProductsBean", products);
			if(products.getIsRequestUnderProcesss() == 1)
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
				executeAction(products, "ClassInfoBean", currentStatus, currentAction);
			}
			else
			{
				executeAction(products, "ClassInfoBean", currentStatus);
			}
//			executeAction(products, "ProductsBean", currentStatus);
			if (getHasTransactionFailed() == false)
			{
				JsonObject updatedproductsDataObject = products.getDataObject(getDBSession());
				updatedproductsDataObject.addProperty("nextAction", getActionForCurrentStatus(products.getVwTxnStatus()));
				dataObject.add("products", updatedproductsDataObject);
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
		Products products = (Products) getManagedBean();
		String currentStatus = products.getVwTxnStatus();
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
	
	
	public JsonObject downloadProductsData() throws Exception
	{
		return downloadProductsData(null);
	}
	public JsonObject downloadProductsData(HSSFWorkbook workbook) throws Exception
	
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
			workbook.setSheetName(workbook.getSheetIndex(sheet), "Products");
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
			headerName = "productsId";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);
						
			cell = row.createCell(headerCellCount++);
			headerName = "productName";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "productDescription";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "productPrice";
			columnWidth = headerName.length() * 320;
			cell.setCellValue(headerName);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(headerCellCount, columnWidth);cell = row.createCell(headerCellCount++);
			headerName = "productUnitType";
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
			String hql = "From Products ";
						
			Query query = getDBSession().createQuery(hql);
						
			List resultsList = query.list();
			Integer recordNo = 1;
			boolean entriesExist = false;
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				entriesExist = true;
				Products products = (Products) it.next();
				row = sheet.createRow(currentRowPosition++);
				int dataCellCount = entityDataCellIndex;
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(recordNo++));
				Integer productsId = products.getProductsId();
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(String.valueOf(productsId));
								cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String productName = products.getProductName();
				cell.setCellValue(productName);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String productDescription = products.getProductDescription();
				cell.setCellValue(productDescription);
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);			Integer productPrice = products.getProductPrice();
				if(productPrice!=null)
				{
					cell.setCellValue(String.valueOf(productPrice));
				}
				cell = row.createCell(dataCellCount++);
				cell.setCellStyle(dataStyle);
				
				String productUnitType = products.getProductUnitType();
				cell.setCellValue(productUnitType);

				rowColumnIndexObject.addProperty("currentRowPosition", currentRowPosition);
			    
			    currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			}
			if(saveWorkbook == true)
			{
				workbook.removeSheetAt(0);
				String fileName = CommonUtil.getFileNameByCurrentTime() + "_" + "Products.xls";
				String savedFileName = filePath + CommonUtil.getFileNameByCurrentTime() + "_" + "Products.xls";
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
	public JsonObject uploadProductsData(JsonObject productsDataObject) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		Integer fileId = productsDataObject.get("fileId").getAsInt();
		String inputFilesZip = productsDataObject.get("inputFilesZip").getAsString();
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
	    		dataObject.addProperty("alert", "Products Data could not be uploaded as input files zip could not be extracted.");
	    		dataObject.addProperty("success", 0);
	    		return dataObject;
	        }
	        inputFilesPath = extractedZipFilePath;
		}
		productsDataObject.addProperty("inputFilesPath", inputFilesPath);
		JsonObject responseData = uploadProductsData(workbook, productsDataObject);
		if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
		{
			return responseData; 
		}
		FileOutputStream out = new FileOutputStream(new File(savedFileName));
		workbook.write(out);
		out.close();
		dataObject.addProperty("alert", "Products Data uploaded successfully.");
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
	public JsonObject uploadProductsData(HSSFWorkbook workbook, JsonObject productsDataObject) throws Exception
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		try
		{
			HSSFSheet sheet = workbook.getSheet("Products");
			if(sheet==null)
			{
				dataObject.addProperty("success", 1);
				return dataObject;
			}
			String filePath = com.shoppingcart.util.SettingsUtil.getProjectFilesPath();
			boolean saveWorkbook = false;
			String savedFileName = "" ;
			Integer fileId = -1;
			Integer areSourceDestinationSame = productsDataObject.get("areSourceDestinationSame").getAsInt();
			String inputFilesPath = productsDataObject.get("inputFilesPath").getAsString();
			if(workbook == null)
			{
				fileId = productsDataObject.get("fileId").getAsInt();
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
				dataObject.addProperty("alert", "Products Data uploaded successfully.");
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
			JsonObject products = new JsonObject();
			int totalRetryCount = 0;
			while (currentRowPosition < totalDefinedRowsInSheet)
			{
				String rowFirstCellValue = "";
				String dependentEntityName = "";
				JsonObject productsListObj = fetchData(workbook, sheet, totalDefinedRowsInSheet, batchsize, rowColumnIndexObject, cellIndexParameterMap, areSourceDestinationSame, inputFilesPath);
				JsonArray productsList = productsListObj.get("productsList").getAsJsonArray();
				currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
				JsonObject responseData = uploadProductsList(productsList);
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					dataObject.addProperty("alert", responseData.get("alert").getAsString());
					dataObject.addProperty("success", 0);
					return dataObject;
				}
				int currentRetryCount = responseData.get("retryCount").getAsInt();
				totalRetryCount += currentRetryCount;
				JsonArray dataObjectsListToRetry = UploadDownloadUtil.getDataToRetry(productsList);
				dataListToRetry.addAll(dataObjectsListToRetry);
				updateStatusMsg(productsList, sheet, successCellStyle, errorCellStyle, statusCellIndex);				
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
			JsonArray productsList = new JsonArray();
			//currentRowPosition shall point to the row which shall have data to be read next in the sequence
			int currentRowPosition = rowColumnIndexObject.get("currentRowPosition").getAsInt();
			int entityDataCellIndex = rowColumnIndexObject.get("entityDataCellIndex").getAsInt();
			HashMap<Integer, String> childEntityCellIndexParameterMap;
			Row row = null;
			int pendingRecordsCount = 0;
			JsonObject products = new JsonObject();
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
				JsonObject productsUploadObj	= new JsonObject();
				productsUploadObj.addProperty("dataRowIndex", currentRowPosition);		    
				row = sheet.getRow(currentRowPosition++);
				int cellIndex = entityDataCellIndex+1;
				try
				{
					products = new JsonObject();
					for (int i = 0; i < cellIndexParameterMap.size(); i++)
					{
						String parameterName = cellIndexParameterMap.get(cellIndex);
						if (parameterName.equalsIgnoreCase("productsId"))
						{
							String productsId = row.getCell(cellIndex++).getStringCellValue();
							if(productsId != null && productsId.trim().length() > 0)
							{
								try
								{
									Integer iProductsId = Integer.parseInt(productsId);
									if(areSourceDestinationSame == 1)
									{
										Products productsObj = (Products)session.get(Products.class, iProductsId);
										if(productsObj != null)
										{ 
											products.addProperty("productsId", iProductsId);
										}
										else
										{
											productsUploadObj.addProperty("isDataFetched", 0);
											productsUploadObj.addProperty("msg", "This Products could not be uploaded as there appears to be some problem with the data(Products Id is not exist). ");
											continue;
										}
									}
									else
									{
										Products productsObj = getProductsByLegacyRecordId(session, iProductsId);
										if(productsObj != null)
										{ 
											products.addProperty("productsId", productsObj.getProductsId());
										}
										products.addProperty("legacyRecordId", iProductsId);
									}
								}
								catch (Exception e)
								{
									writeToLog(CommonUtil.getStackTrace(e));
									productsUploadObj.addProperty("isDataFetched", 0);
									productsUploadObj.addProperty("msg", "This Products could not be uploaded as there appears to be some problem with the data(Products Id). ");
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
							products.addProperty(parameterName, parameterValue);
						}
					}
					productsUploadObj.add("dataObject", products);		    
					productsList.add(productsUploadObj);
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
			dataObject.add("productsList", productsList);
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
		if(previousRetryCountMap.has("Products") && previousRetryCountMap.get("Products").isJsonNull()==false)
		{
			previousRetryCount = previousRetryCountMap.get("Products").getAsInt();
		}
		if(retryCountMap.has("Products") && retryCountMap.get("Products").isJsonNull()==false)
		{
			retryCount = retryCountMap.get("Products").getAsInt();
		}
		if(retryCount<previousRetryCount)
		{
			return 1;
		}
	    
		return 0;
	}
	public void updateRetryCountMapForProductsList(JsonArray productsList, JsonObject retryCountMap) throws Exception
	{
		int retryCount = 0;
		for (int i= 0; i < productsList.size(); i++)
		{
			int retryUpload = 0;
			int retryChildEntitiesUpload = 0;
			int partialUploadUnderProcess = 0;
			JsonObject productsDataObject = productsList.get(i).getAsJsonObject();
			JsonObject products = productsDataObject.get("dataObject").getAsJsonObject();
			if(productsDataObject.has("retryUpload") && productsDataObject.get("retryUpload").isJsonNull()==false)
			{
				retryUpload = productsDataObject.get("retryUpload").getAsInt();
			}
			if(productsDataObject.has("retryChildEntitiesUpload") && productsDataObject.get("retryChildEntitiesUpload").isJsonNull()==false)
			{
				retryChildEntitiesUpload = productsDataObject.get("retryChildEntitiesUpload").getAsInt();
			}
			if(productsDataObject.has("partialUploadUnderProcess") && productsDataObject.get("partialUploadUnderProcess").isJsonNull()==false)
			{
				partialUploadUnderProcess = productsDataObject.get("partialUploadUnderProcess").getAsInt();
			}
			if(retryUpload==1 || retryChildEntitiesUpload==1 || partialUploadUnderProcess==1)
			{
				retryCount++;
			}
		    
		}
	    retryCountMap.addProperty("Products", retryCount);
	}
	public JsonObject uploadProductsList(JsonArray productsList) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			JsonObject responseData;
			responseData = uploadData(productsList);
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
	public JsonObject updateStatusMsg(JsonArray productsList, HSSFSheet sheet, HSSFCellStyle successCellStyle, HSSFCellStyle errorCellStyle, int statusCellIndex) throws Exception
	{
		JsonObject dataObject = new JsonObject();
		try
		{
			Cell lastCell = null;
			Row row = null;
			JsonObject responseData;
			for (int i= 0; i < productsList.size(); i++)
			{
				JsonObject productsDataObject = productsList.get(i).getAsJsonObject();
				JsonObject products = productsDataObject.get("dataObject").getAsJsonObject();
				int isSuccessfullyUploaded = productsDataObject.get("isSuccessfullyUploaded").getAsInt();
				int dataRowIndex = productsDataObject.get("dataRowIndex").getAsInt();
				String errorMessage = productsDataObject.get("errorMessage").getAsString();
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
	public JsonObject uploadData(JsonArray productsList) throws Exception
	
	{
		Session session = getDBSession();
		JsonObject dataObject = new JsonObject();
		int successfullyUploadedCount = 0;
		int retryCount = 0;
		JsonObject retValObject = new JsonObject();
		try
		{
			for (int i =0; i < productsList.size(); i++)
			{
				int partialUploadUnderProcess = 0;
				JsonObject productsDataObject = productsList.get(i).getAsJsonObject();
				JsonObject products = productsDataObject.get("dataObject").getAsJsonObject();
				productsDataObject.addProperty("retryUpload", 0);
				productsDataObject.addProperty("retryChildEntitiesUpload", 0);
				productsDataObject.addProperty("partialUploadUnderProcess", 0);
				com.shoppingcart.controller.forms.impl.ProductsControllerImpl productsImplObj = new com.shoppingcart.controller.forms.impl.ProductsControllerImpl(session, getUserSessionInfo());				
				int areAllLookupsFound = productsImplObj.getEntityInfoUpdatedWithLookupIds(session, products, retValObject);
				if(areAllLookupsFound!=1)
				{
					productsDataObject.addProperty("retryUpload", 1);
					productsDataObject.addProperty("errorMessage", "Selected lookup entities information not available while uploading this row.");				
					productsDataObject.addProperty("isSuccessfullyUploaded", 0);				
					retryCount++;
					continue;
				}
				if(retValObject.has("partialUploadUnderProcess") && retValObject.get("partialUploadUnderProcess").isJsonNull()==false)
				{
					partialUploadUnderProcess = retValObject.get("partialUploadUnderProcess").getAsInt();					
				}
				if(partialUploadUnderProcess==1)
				{
					productsDataObject.addProperty("partialUploadUnderProcess", partialUploadUnderProcess);					
				}
				Boolean updateEntity = false;
				int isEntityPresent = 0;
				int productsId = -1;
				int keyColumnsCount = 0;
				
				if(keyColumnsCount > 0 && !products.has("productsId"))
				{
					products.addProperty("attributeNamePrefix", "");
					
					products.addProperty("attributeNamePrefix", "");
					JsonObject responseData = productsImplObj.getProductsByLookupFields(session,  products);
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
						JsonObject productsObject = responseData.get("productsDataObject").getAsJsonObject();
						productsId = productsObject.get("productsId").getAsInt();
						products.addProperty("productsId", productsId);
						updateEntity = true;
					}
				}
				else if(products.has("productsId"))
				{
					updateEntity = true;
				}
				
				JsonObject responseData;
				if(updateEntity == false)
				{
					responseData = productsImplObj.createProducts(products);
				}
				else
				{
					responseData = productsImplObj.updateProducts(products);
				}
				productsDataObject.addProperty("isSuccessfullyUploaded", 1);				
				Transaction tx = session.getTransaction();
				if (responseData == null || !responseData.has("success") || responseData.get("success").getAsInt() != 1)
				{
					productsId =-1;
					productsDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
					productsDataObject.addProperty("isSuccessfullyUploaded", 0);
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
				productsId = responseData.get("productsId").getAsInt();
				productsDataObject.addProperty("errorMessage", responseData.get("alert").getAsString());				
			    
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
	public int getEntityInfoUpdatedWithLookupIds(Session session, JsonObject products, JsonObject retValObject)
	{
		JsonObject requestParams = new JsonObject();
		JsonObject dataObject = new JsonObject();
		int hasParamsForLookup = 0;return 1;		
	}
	public JsonObject getProductsByLookupFields(Session session, JsonObject requestParams)
	{
		JsonObject dataObject = new JsonObject();
		try
		{String hql = "From Products where 1 = 1  ";
			String countHql = "select count(*)  from Products where 1 = 1 ";
			Query countQuery = session.createQuery(countHql);Long resultsCount = (Long) countQuery.uniqueResult();
			if(resultsCount != 1)
			{
				dataObject.addProperty("alert", "Your request could not be processed as Products could not be retrieved");
				dataObject.addProperty("success", 0);
				dataObject.addProperty("resultsCount", 0);
				return dataObject;
			}
			Query query = session.createQuery(hql);List resultsList = query.list();
			for (Iterator it = resultsList.iterator(); it.hasNext();)
			{
				Products products = (Products) it.next();
				JsonObject productsDataObject = products.getDataObject(session);
				dataObject.add("productsDataObject", productsDataObject);
				dataObject.addProperty("success", 1);
				return dataObject;
			}
		}
		catch(Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		dataObject.addProperty("alert", "Your request could not be processed as Products could not be retrieved");
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
		dataObject.addProperty("alert", "Your request could not be processed as lookup information for 'Products' could not be retrieved");
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
		else if(apiName.equals("getProductsPropertyValue"))
			{
				JsonObject inputDataObject = getProductsPropertyValue(session, requestParametersInfo);
				inputDataObject.addProperty("success", 1);
				return inputDataObject;
			}
			else if(apiName.equals("getProducts"))
			{
				JsonObject inputDataObject = getProducts(session, requestParametersInfo);
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
			else if (apiName.equals("doBeforeTransactionApprovedForProducts"))
			{
				isAPIExecuted = 1;
				dataObject = updateAPIStatus(session, requestReceived);
			}
			else if (apiName.equals("doBeforeTransactionRolledbackForProducts"))
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
			Integer productsId = requestReceivedParametersInfo.get("productsId").getAsInt();
			Products products = (Products) session.get(Products.class, productsId);
			products.setIsRequestUnderProcesss(0);
			session.merge(products);
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
	public JsonObject getProductsPropertyValue(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer productsId = inputForGetAPI.get("productsId").getAsInt();
		String popertyNameEL = inputForGetAPI.get("popertyNameEL").getAsString();
		Products products = (Products) session.get(Products.class, productsId);
		products.getPropertyValue(session, popertyNameEL, dataObject);
		return dataObject;
	}
	public JsonObject getProducts(Session session, JsonObject paramsInfo)
	{
		JsonObject dataObject = new JsonObject();
		JsonObject inputForGetAPI = paramsInfo.get("inputForGetAPI").getAsJsonObject();
		Integer productsId = inputForGetAPI.get("productsId").getAsInt();
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("productsId", String.valueOf(productsId));
		JsonObject productsListObject = retrieveProductsList(paramsMap);
		if(productsListObject!=null && productsListObject.has("success") && productsListObject.get("success").getAsInt()==1)
		{
			JsonArray productsList = productsListObject.get("productsList").getAsJsonArray();
			if(productsList.size()>0)
			{
				dataObject.add("products", productsList.get(0).getAsJsonObject());
				dataObject.addProperty("success", 1);
				return dataObject;				
			}
		}
		dataObject.addProperty("alert", "Information of 'Products' could not be retrieved !!");			
		dataObject.addProperty("success", 0);
		return dataObject;		
	}
	public abstract JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject productsDataObject, Products products);
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
	public abstract void doAfterLookupRowSelectedImpl(Products products, String attributeName);
	public abstract void doAfterSelectOptionChangedImpl(Products products, String attributeName);
	public abstract void verifyIfTransactionIsUpdatableImpl();
	public abstract void verifyIfTransactionIsDeletableImpl();
	public abstract void doBeforeTransactionApprovedImpl();
	public abstract void doAfterTransactionApprovedImpl();
	public abstract void doBeforeTransactionRolledbackImpl();
	public abstract void doAfterEntityLoadedImpl(Products products, JsonObject initParamsInfo);
	public abstract void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName, HashMap customSearchParams);
	public abstract void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap);
	public abstract void doRefreshFromUIImpl();	
	public abstract String getLcNoImpl();
}
