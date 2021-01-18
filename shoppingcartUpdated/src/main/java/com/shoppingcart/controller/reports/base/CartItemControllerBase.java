/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shoppingcart.controller.reports.base;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shoppingcart.util.layout.LayoutParserUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import com.shoppingcart.util.CommonUtil;
import com.shoppingcart.util.BusinessAPIs;
import com.shoppingcart.controller.reports.impl.CartItemControllerImpl;
/**
 *
 * @author root
 */
public  abstract class CartItemControllerBase
{
    private static final Logger logger = Logger.getLogger(CartItemControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public CartItemControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public CartItemControllerBase()
    {
    }
    public static JsonObject getPageData(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        JsonObject fieldsDataWithoutOverrideWhereClause = new JsonObject();
        JsonObject fieldsDataWithOverrideWhereClause = new JsonObject();   
        JsonObject layoutCustomDataFieldsObject = new JsonObject();     
    	JsonObject tablesData = new JsonObject();
    	JsonObject graphsData = new JsonObject();
          
            JsonObject cartItemObj = getCartItem(organisationSession, paramsMap);
            if (cartItemObj.get("success").getAsInt() != 1)
            {
                return cartItemObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("cartItem",        
            cartItemObj.get("cartItem").getAsJsonObject());
        
        
                
        CartItemControllerImpl cartItemControllerImpl = new CartItemControllerImpl();
        cartItemControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getCartItem(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer cartItemId = Integer.valueOf(paramsMap.get("cartItemId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select ci.cartItemId as ci_cartItemId , ci.userCartId as ci_userCartId , ci.productId as ci_productId , ci.productQuantity as ci_productQuantity , ci.productUnitPrice as ci_productUnitPrice , ci.subTotalAmount as ci_subTotalAmount , ci.grandTotal as ci_grandTotal  from CartItem ci  Where 2>1  and ci.cartItemId = ?   ";
            
        	Query query;
        	String queryCode = "CartItem";
        	String useNativeQuery = LayoutParserUtil.getUseNativeQueryStatus(organisationSession, queryCode);
        	if(useNativeQuery.equalsIgnoreCase("Yes"))
        	{
        		query = organisationSession.createSQLQuery(selectQuery);
        	}
        	else
        	{
        		query = organisationSession.createQuery(selectQuery);
        	}
            int paramsPosIndex = 0;
	        	         
	        query.setParameter(paramsPosIndex, cartItemId);
            paramsPosIndex++;                
	        
	        //based on conditional parameter
      		
 	         List resultRowsList = query.list();
	         Iterator iterator = resultRowsList.iterator(); 	    
	         int singleColumnStatus = CommonUtil.isSingleColumnQuery(resultRowsList);
	         if(iterator.hasNext())
	         {	        	 	        	 
	        	Object[] resultRowColumnsList;   	        		        
	        	if(singleColumnStatus == 0)
	        	{	        	
	        		resultRowColumnsList = (Object[]) iterator.next();
	        	}
	        	else
	        	{
	        		resultRowColumnsList = new Object[1];
	        		resultRowColumnsList[0] = (Object) iterator.next();
	        	}	   
				
            	String ci_cartItemId = String.valueOf(resultRowColumnsList[0]);
            	if(ci_cartItemId.equals("null"))
            	{
            		ci_cartItemId  = "";
            	}
            	
            	String ci_userCartId = String.valueOf(resultRowColumnsList[1]);
            	if(ci_userCartId.equals("null"))
            	{
            		ci_userCartId  = "";
            	}
            	
            	String ci_productId = String.valueOf(resultRowColumnsList[2]);
            	if(ci_productId.equals("null"))
            	{
            		ci_productId  = "";
            	}
            	
            	String ci_productQuantity = String.valueOf(resultRowColumnsList[3]);
            	if(ci_productQuantity.equals("null"))
            	{
            		ci_productQuantity  = "";
            	}
            	
            	String ci_productUnitPrice = String.valueOf(resultRowColumnsList[4]);
            	if(ci_productUnitPrice.equals("null"))
            	{
            		ci_productUnitPrice  = "";
            	}
            	
            	String ci_subTotalAmount = String.valueOf(resultRowColumnsList[5]);
            	if(ci_subTotalAmount.equals("null"))
            	{
            		ci_subTotalAmount  = "";
            	}
            	
            	String ci_grandTotal = String.valueOf(resultRowColumnsList[6]);
            	if(ci_grandTotal.equals("null"))
            	{
            		ci_grandTotal  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("userCartId", ci_userCartId);
                
                	
                    resultsDataObj.addProperty("productId", ci_productId);
                
                	
                    resultsDataObj.addProperty("productQuantity", ci_productQuantity);
                
                	
                    resultsDataObj.addProperty("productUnitPrice", ci_productUnitPrice);
                
                	
                    resultsDataObj.addProperty("subTotalAmount", ci_subTotalAmount);
                
                	
                    resultsDataObj.addProperty("grandTotal", ci_grandTotal);
                
                dataObject.addProperty("success", 1);
                dataObject.add("cartItem", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("cartItem information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "cartItem information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
