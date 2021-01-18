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
import com.shoppingcart.controller.reports.impl.ProductsControllerImpl;
/**
 *
 * @author root
 */
public  abstract class ProductsControllerBase
{
    private static final Logger logger = Logger.getLogger(ProductsControllerBase.class);
    private  JsonObject mUserSessionInfo;
    private Session mOrganisationSession;
    public JsonObject getUserSessionInfo()
    {
    	return mUserSessionInfo;
    }
    public ProductsControllerBase(JsonObject userSessionInfo, Session organisationSession)
    {
    	mUserSessionInfo = userSessionInfo;
    	mOrganisationSession = organisationSession;
    }
    public ProductsControllerBase()
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
          
            JsonObject productsObj = getProducts(organisationSession, paramsMap);
            if (productsObj.get("success").getAsInt() != 1)
            {
                return productsObj;
            }
            fieldsDataWithoutOverrideWhereClause.add("products",        
            productsObj.get("products").getAsJsonObject());
        
        
                
        ProductsControllerImpl productsControllerImpl = new ProductsControllerImpl();
        productsControllerImpl.populateCustomDataFields(organisationSession, paramsMap, layoutCustomDataFieldsObject, fieldsDataWithOverrideWhereClause);
	    
		
        dataObject.add("fieldsDataWithoutOverrideWhereClause", fieldsDataWithoutOverrideWhereClause);
        dataObject.add("fieldsDataWithOverrideWhereClause", fieldsDataWithOverrideWhereClause);      
        dataObject.add("layoutCustomDataFieldsObject", layoutCustomDataFieldsObject);     
	    dataObject.add("tablesData", tablesData);
	    dataObject.add("graphsData", graphsData);       
        dataObject.addProperty("success", 1);
        return dataObject;
    }
	
    public static JsonObject getProducts(Session organisationSession, Map<String, String> paramsMap) throws Exception
    {
        JsonObject dataObject = new JsonObject();
        try
        {
            
	            
				
				
				Integer productsId = Integer.valueOf(paramsMap.get("productsId"));							
								
				
				
				
				
				
				
				
				
				
            
            String selectQuery = "select p.productsId as p_productsId , p.productName as p_productName , p.productDescription as p_productDescription , p.productPrice as p_productPrice , p.productUnitType as p_productUnitType  from Products p  Where 2>1  and p.productsId = ?   ";
            
        	Query query;
        	String queryCode = "Products";
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
	        	         
	        query.setParameter(paramsPosIndex, productsId);
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
				
            	String p_productsId = String.valueOf(resultRowColumnsList[0]);
            	if(p_productsId.equals("null"))
            	{
            		p_productsId  = "";
            	}
            	
            	String p_productName = String.valueOf(resultRowColumnsList[1]);
            	if(p_productName.equals("null"))
            	{
            		p_productName  = "";
            	}
            	
            	String p_productDescription = String.valueOf(resultRowColumnsList[2]);
            	if(p_productDescription.equals("null"))
            	{
            		p_productDescription  = "";
            	}
            	
            	String p_productPrice = String.valueOf(resultRowColumnsList[3]);
            	if(p_productPrice.equals("null"))
            	{
            		p_productPrice  = "";
            	}
            	
            	String p_productUnitType = String.valueOf(resultRowColumnsList[4]);
            	if(p_productUnitType.equals("null"))
            	{
            		p_productUnitType  = "";
            	}
            	
                JsonObject resultsDataObj = new JsonObject();
                
                	
                    resultsDataObj.addProperty("productName", p_productName);
                
                	
                    resultsDataObj.addProperty("productDescription", p_productDescription);
                
                	
                    resultsDataObj.addProperty("productPrice", p_productPrice);
                
                	
                    resultsDataObj.addProperty("productUnitType", p_productUnitType);
                
                dataObject.addProperty("success", 1);
                dataObject.add("products", resultsDataObj);
                return dataObject;
            }
        }
        catch (Exception e)
        {
            logger.error(CommonUtil.getStackTrace(e));
            logger.debug("products information could not be retrieved.");
        }
        finally
        {
        }
        dataObject.addProperty("success", 0);
        dataObject.addProperty("alert", "products information could not be retrieved.");
        return dataObject;
    }

	
    
    

	public abstract void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName);
	public abstract void processQueryResultList(JsonArray resultList, String tableName);
	public abstract void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause);
}
