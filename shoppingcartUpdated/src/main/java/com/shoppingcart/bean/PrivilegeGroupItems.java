package com.shoppingcart.bean;
import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.shoppingcart.util.CommonUtil;
import com.vw.runtime.RulesBean;
import com.google.gson.JsonObject;
import org.hibernate.Session;
import org.apache.commons.lang.StringUtils;
import com.shoppingcart.request.service.ServiceAPIUtil;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("unused")
/**
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 */
@Entity
@Table(name = "PrivilegeGroupItems")
public class PrivilegeGroupItems extends RulesBean implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "privilegeGroupItemsId")
	private java.lang.Integer privilegeGroupItemsId;
		
	@Column(name = "privilegeGroupId")
	private java.lang.Integer privilegeGroupId;
	public Integer getPrivilegeGroupId()
	{
		return this.privilegeGroupId;
	}
	public void setPrivilegeGroupId(Integer privilegeGroupId)
	{
		this.privilegeGroupId = privilegeGroupId;
	}
	
			
		
	@Column(name = "privilegeActionType")
	private java.lang.String privilegeActionType;
	
	@Column(name = "privilegeObjectType")
	private java.lang.String privilegeObjectType;
	
	@Column(name = "privilegeObjectName")
	private java.lang.String privilegeObjectName;

	@Column(name = "vwLastModifiedDate")
	private Date vwLastModifiedDate;
	@Column(name = "vwLastModifiedTime")
	private java.lang.Integer vwLastModifiedTime;
	@Column(name = "vwLastAction")
	private java.lang.String vwLastAction;
	@Column(name = "vwModifiedBy")
	private java.lang.String vwModifiedBy;
	@Column(name = "vwTxnRemarks")
	private java.lang.String vwTxnRemarks;
	@Column(name = "vwTxnStatus")
	private java.lang.String vwTxnStatus;
	@Column(name = "isRequestUnderProcesss")
	private java.lang.Integer isRequestUnderProcesss;
	@Column(name = "legacyRecordId")
	private java.lang.Integer legacyRecordId;
	@Column(name = "vwCreatedBy")
	private java.lang.Integer vwCreatedBy;
	@Column(name = "vwCreationDate")
	private Date vwCreationDate;
	public Date getVwLastModifiedDate()
	{
		return this.vwLastModifiedDate;
	}
	public void setVwLastModifiedDate(Date vwLastModifiedDate)
	{
		this.vwLastModifiedDate = vwLastModifiedDate;
	}
	public Integer getVwLastModifiedTime()
	{
		return this.vwLastModifiedTime;
	}
	public void setVwLastModifiedTime(Integer vwLastModifiedTime)
	{
		this.vwLastModifiedTime = vwLastModifiedTime;
	}
	public String getVwLastAction()
	{
		return this.vwLastAction;
	}
	public void setVwLastAction(String vwLastAction)
	{
		this.vwLastAction = vwLastAction;
	}
	public String getVwModifiedBy()
	{
		return this.vwModifiedBy;
	}
	public void setVwModifiedBy(String vwModifiedBy)
	{
		this.vwModifiedBy = vwModifiedBy;
	}
	public String getVwTxnRemarks()
	{
		return this.vwTxnRemarks;
	}
	public void setVwTxnRemarks(String vwTxnRemarks)
	{
		this.vwTxnRemarks = vwTxnRemarks;
	}
	public String getVwTxnStatus()
	{
		return this.vwTxnStatus;
	}
	public void setVwTxnStatus(String vwTxnStatus)
	{
		this.vwTxnStatus = vwTxnStatus;
	}
	public Integer getIsRequestUnderProcesss()
	{
		return this.isRequestUnderProcesss;
	}
	public void setIsRequestUnderProcesss(Integer isRequestUnderProcesss)
	{
		this.isRequestUnderProcesss = isRequestUnderProcesss;
	}
	public Integer getLegacyRecordId()
	{
		return this.legacyRecordId;
	}
	public void setLegacyRecordId(Integer legacyRecordId)
	{
		this.legacyRecordId = legacyRecordId;
	}
	public Integer getVwCreatedBy()
	{
		return this.vwCreatedBy;
	}
	public void setVwCreatedBy(Integer vwCreatedBy)
	{
		this.vwCreatedBy = vwCreatedBy;
	}
	public Date getVwCreationDate()
	{
		return this.vwCreationDate;
	}
	public void setVwCreationDate(Date vwCreationDate)
	{
		this.vwCreationDate = vwCreationDate;
	}
	public PrivilegeGroupItems()
	{
	}
	public Integer getPrivilegeGroupItemsId()
	{
		return this.privilegeGroupItemsId;
	}
	public void setPrivilegeGroupItemsId(Integer privilegeGroupItemsId)
	{
		this.privilegeGroupItemsId = privilegeGroupItemsId;
	}
		
	public String getPrivilegeActionType()
	{
		return this.privilegeActionType;
	}
	public void setPrivilegeActionType(String privilegeActionType)
	{
		this.privilegeActionType = privilegeActionType;
	}
	
	public String getPrivilegeObjectType()
	{
		return this.privilegeObjectType;
	}
	public void setPrivilegeObjectType(String privilegeObjectType)
	{
		this.privilegeObjectType = privilegeObjectType;
	}
	
	public String getPrivilegeObjectName()
	{
		return this.privilegeObjectName;
	}
	public void setPrivilegeObjectName(String privilegeObjectName)
	{
		this.privilegeObjectName = privilegeObjectName;
	}

	public JsonObject getDataObject(Session session)
	{
		return getDataObject(false, session);
	}
	public JsonObject getDataObject(boolean fetchForLookup, Session session)
	{
		JsonObject dataObject = new JsonObject(); 				
		dataObject.addProperty("privilegeGroupItemsId", privilegeGroupItemsId);
				
		if(privilegeActionType!=null)
		{
			dataObject.addProperty("privilegeActionType", privilegeActionType);
		}
		else
		{
			dataObject.addProperty("privilegeActionType", "");
		}
		
		if(privilegeObjectType!=null)
		{
			dataObject.addProperty("privilegeObjectType", privilegeObjectType);
		}
		else
		{
			dataObject.addProperty("privilegeObjectType", "");
		}
		
		if(privilegeObjectName!=null)
		{
			dataObject.addProperty("privilegeObjectName", privilegeObjectName);
		}
		else
		{
			dataObject.addProperty("privilegeObjectName", "");
		}

		if (vwLastModifiedDate != null)
		{
			dataObject.addProperty("vwLastModifiedDate", CommonUtil.getDateInRegularDateFormat(vwLastModifiedDate));
		}
		else
		{
			dataObject.addProperty("vwLastModifiedDate", "");
		}
		if (vwLastModifiedTime != null)
		{
			dataObject.addProperty("vwLastModifiedTime", CommonUtil.getNumberToTime(vwLastModifiedTime));
		}
		else
		{
			dataObject.addProperty("vwLastModifiedTime", "");
		}
		if (vwLastAction != null)
		{
			dataObject.addProperty("vwLastAction", vwLastAction);
		}
		else
		{
			dataObject.addProperty("vwLastAction", "");
		}
		if (vwModifiedBy != null)
		{
			dataObject.addProperty("vwModifiedBy", vwModifiedBy);
		}
		else
		{
			dataObject.addProperty("vwModifiedBy", "");
		}
		if (vwTxnRemarks != null)
		{
			dataObject.addProperty("vwTxnRemarks", vwTxnRemarks);
		}
		else
		{
			dataObject.addProperty("vwTxnRemarks", "");
		}
		if (vwTxnStatus != null)
		{
			dataObject.addProperty("vwTxnStatus", vwTxnStatus);
		}
		else
		{
			dataObject.addProperty("vwTxnStatus", "");
		}
		dataObject.addProperty("isRequestUnderProcesss", isRequestUnderProcesss);
		dataObject.addProperty("lookupDisplayText", getLookupDisplayText(session));
			
		dataObject.addProperty("privilegeGroupId",privilegeGroupId);
		if(privilegeGroupId != null && privilegeGroupId > 0)
		{
			com.shoppingcart.bean.PrivilegeGroup  privilegeGroup = (com.shoppingcart.bean.PrivilegeGroup) session.get(com.shoppingcart.bean.PrivilegeGroup.class, privilegeGroupId);   
			JsonObject privilegeGroupObj = privilegeGroup.getDataObject(true, session);
			dataObject.addProperty("privilegeGroupDisplayVal", privilegeGroupObj.get("lookupDisplayText").getAsString());
			
		}
		
		return dataObject;
	}
	public String getLookupDisplayText(Session session)
	{
		String displayText = "";
		
		if(displayText.endsWith(" - "))
		{
			displayText = displayText.substring(0, displayText.length()-3);
		}
		return displayText;
	}
	public Object getPropertyValue(Session session, String popertyNameEL)
	{
		JsonObject dataObject = null;
		return getPropertyValue(session, popertyNameEL, dataObject);
	}
	public Object getPropertyValue(Session session, String popertyNameEL, JsonObject dataObject)
	{
		try
		{
			String[] propertyHierarchy = StringUtils.split(popertyNameEL, ".");
			if (propertyHierarchy.length > 1)
			{
				String propertyName = propertyHierarchy[0];
				if(1 > 2)
				{
				}
				
			}
			else
			{
				Method instanceMethod = PrivilegeGroupItems.class.getMethod("get" + popertyNameEL);
				return instanceMethod.invoke(this);
			}
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return null;
	}
}
