package com.shoppingcart.controller.reports.impl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shoppingcart.controller.reports.base.OrderItemControllerBase;
import java.sql.ResultSet;
import java.util.Map;
public class OrderItemControllerImpl  extends OrderItemControllerBase
{
	private static final Logger logger = Logger.getLogger(OrderItemControllerImpl.class);
    public OrderItemControllerImpl(JsonObject userSessionInfo, Session organisationSession)
    {
    	super(userSessionInfo, organisationSession);
    }
    public OrderItemControllerImpl()
    {
    	super();
    }
    public Map<String, String>  getUpdatedSearchParams(String formName, Map<String, String> paramsMap)
	{		
		return paramsMap;		
	}
	public  void processQueryResultItemDataObject(Object[] resultRowColumnsList, JsonObject dataObject, String tableName)
	{
	}
	public  void processQueryResultList(JsonArray resultList, String tableName)
	{
	}
	public  void populateCustomDataFields(Session organisationSession, Map<String, String> paramsMap, JsonObject layoutCustomDataFieldsObject, JsonObject fieldsDataWithOverrideWhereClause)
	{
	}
}
