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
import com.shoppingcart.controller.reports.impl.OrdersControllerImpl;
/**
 *
 * @author root
 */
public  abstract class OrdersControllerBase
{
    private static final Logger logger = Logger.getLogger(OrdersControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public OrdersControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public OrdersControllerBase()
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
          
            JsonObject ordersObj = getOrders(organisationSession, paramsMap);
            if (ordersObj.get("success").getAsInt() != 1)
            {
                return ordersObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("orders",        
            ordersObj.get("orders").getAsJsonObject());
        
        
                
        OrdersControllerImpl ordersControllerImpl = new OrdersControllerImpl();
        ordersControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getOrders(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer ordersId = Integer.valueOf(paramsMap.get("ordersId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select o.ordersId as o_ordersId , o.userCartId as o_userCartId , o.orderNo as o_orderNo , o.orderDate as o_orderDate , o.orderAmount as o_orderAmount  from Orders o  Where 2>1  and o.ordersId = ?   ";
            
        	Query query;
        	String queryCode = "Orders";
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
	        	         
	        query.setParameter(paramsPosIndex, ordersId);
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
				
            	String o_ordersId = String.valueOf(resultRowColumnsList[0]);
            	if(o_ordersId.equals("null"))
            	{
            		o_ordersId  = "";
            	}
            	
            	String o_userCartId = String.valueOf(resultRowColumnsList[1]);
            	if(o_userCartId.equals("null"))
            	{
            		o_userCartId  = "";
            	}
            	
            	String o_orderNo = String.valueOf(resultRowColumnsList[2]);
            	if(o_orderNo.equals("null"))
            	{
            		o_orderNo  = "";
            	}
            	
            	String o_orderDate = String.valueOf(resultRowColumnsList[3]);
            	if(o_orderDate.equals("null"))
            	{
            		o_orderDate  = "";
            	}
            	
            	String o_orderAmount = String.valueOf(resultRowColumnsList[4]);
            	if(o_orderAmount.equals("null"))
            	{
            		o_orderAmount  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("userCartId", o_userCartId);
                
                	
                    resultsDataObj.addProperty("orderNo", o_orderNo);
                
                	
                    resultsDataObj.addProperty("orderDate", o_orderDate);
                
                	
                    resultsDataObj.addProperty("orderAmount", o_orderAmount);
                
                dataObject.addProperty("success", 1);
                dataObject.add("orders", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("orders information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "orders information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
