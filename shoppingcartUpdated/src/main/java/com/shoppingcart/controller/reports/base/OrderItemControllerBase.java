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
import com.shoppingcart.controller.reports.impl.OrderItemControllerImpl;
/**
 *
 * @author root
 */
public  abstract class OrderItemControllerBase
{
    private static final Logger logger = Logger.getLogger(OrderItemControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public OrderItemControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public OrderItemControllerBase()
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
          
            JsonObject orderItemObj = getOrderItem(organisationSession, paramsMap);
            if (orderItemObj.get("success").getAsInt() != 1)
            {
                return orderItemObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("orderItem",        
            orderItemObj.get("orderItem").getAsJsonObject());
        
        
                
        OrderItemControllerImpl orderItemControllerImpl = new OrderItemControllerImpl();
        orderItemControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getOrderItem(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer orderItemId = Integer.valueOf(paramsMap.get("orderItemId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select oi.orderItemId as oi_orderItemId , oi.orderId as oi_orderId , oi.productId as oi_productId , oi.productQuantity as oi_productQuantity , oi.productUnitPrice as oi_productUnitPrice , oi.subTotalAmt as oi_subTotalAmt  from OrderItem oi  Where 2>1  and oi.orderItemId = ?   ";
            
        	Query query;
        	String queryCode = "OrderItem";
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
	        	         
	        query.setParameter(paramsPosIndex, orderItemId);
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
				
            	String oi_orderItemId = String.valueOf(resultRowColumnsList[0]);
            	if(oi_orderItemId.equals("null"))
            	{
            		oi_orderItemId  = "";
            	}
            	
            	String oi_orderId = String.valueOf(resultRowColumnsList[1]);
            	if(oi_orderId.equals("null"))
            	{
            		oi_orderId  = "";
            	}
            	
            	String oi_productId = String.valueOf(resultRowColumnsList[2]);
            	if(oi_productId.equals("null"))
            	{
            		oi_productId  = "";
            	}
            	
            	String oi_productQuantity = String.valueOf(resultRowColumnsList[3]);
            	if(oi_productQuantity.equals("null"))
            	{
            		oi_productQuantity  = "";
            	}
            	
            	String oi_productUnitPrice = String.valueOf(resultRowColumnsList[4]);
            	if(oi_productUnitPrice.equals("null"))
            	{
            		oi_productUnitPrice  = "";
            	}
            	
            	String oi_subTotalAmt = String.valueOf(resultRowColumnsList[5]);
            	if(oi_subTotalAmt.equals("null"))
            	{
            		oi_subTotalAmt  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("orderId", oi_orderId);
                
                	
                    resultsDataObj.addProperty("productId", oi_productId);
                
                	
                    resultsDataObj.addProperty("productQuantity", oi_productQuantity);
                
                	
                    resultsDataObj.addProperty("productUnitPrice", oi_productUnitPrice);
                
                	
                    resultsDataObj.addProperty("subTotalAmt", oi_subTotalAmt);
                
                dataObject.addProperty("success", 1);
                dataObject.add("orderItem", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("orderItem information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "orderItem information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
