package com.shoppingcart.controller.reports.impl;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shoppingcart.controller.reports.base.UserCartControllerBase;
import java.sql.ResultSet;
import java.util.Map;
public class UserCartControllerImpl  extends UserCartControllerBase
{
	private static final Logger logger = Logger.getLogger(UserCartControllerImpl.class);
    public UserCartControllerImpl(JsonObject userSessionInfo, Session organisationSession)
    {
    	super(userSessionInfo, organisationSession);
    }
    public UserCartControllerImpl()
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
