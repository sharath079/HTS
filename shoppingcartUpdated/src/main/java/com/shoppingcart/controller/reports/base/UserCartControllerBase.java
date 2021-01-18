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
import com.shoppingcart.controller.reports.impl.UserCartControllerImpl;
/**
 *
 * @author root
 */
public  abstract class UserCartControllerBase
{
    private static final Logger logger = Logger.getLogger(UserCartControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public UserCartControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public UserCartControllerBase()
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
          
            JsonObject userCartObj = getUserCart(organisationSession, paramsMap);
            if (userCartObj.get("success").getAsInt() != 1)
            {
                return userCartObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("userCart",        
            userCartObj.get("userCart").getAsJsonObject());
        
        
                
        UserCartControllerImpl userCartControllerImpl = new UserCartControllerImpl();
        userCartControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getUserCart(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer userCartId = Integer.valueOf(paramsMap.get("userCartId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select uc.userCartId as uc_userCartId , uc.cartCreationTime as uc_cartCreationTime , uc.cartCheckoutTime as uc_cartCheckoutTime  from UserCart uc  Where 2>1  and uc.userCartId = ?   ";
            
        	Query query;
        	String queryCode = "UserCart";
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
	        	         
	        query.setParameter(paramsPosIndex, userCartId);
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
				
            	String uc_userCartId = String.valueOf(resultRowColumnsList[0]);
            	if(uc_userCartId.equals("null"))
            	{
            		uc_userCartId  = "";
            	}
            	
            	String uc_cartCreationTime = String.valueOf(resultRowColumnsList[1]);
            	if(uc_cartCreationTime.equals("null"))
            	{
            		uc_cartCreationTime  = "";
            	}
            	
            	String uc_cartCheckoutTime = String.valueOf(resultRowColumnsList[2]);
            	if(uc_cartCheckoutTime.equals("null"))
            	{
            		uc_cartCheckoutTime  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("cartCreationTime", uc_cartCreationTime);
                
                	
                    resultsDataObj.addProperty("cartCheckoutTime", uc_cartCheckoutTime);
                
                dataObject.addProperty("success", 1);
                dataObject.add("userCart", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("userCart information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "userCart information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
