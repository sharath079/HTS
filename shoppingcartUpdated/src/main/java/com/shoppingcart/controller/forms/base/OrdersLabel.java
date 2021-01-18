package com.shoppingcart.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "OrdersLabelBean")*/
public class OrdersLabel extends RulesBean{
	public String getordersIdFieldName() {return "ordersId";} public String ordersId_LABEL_ENGLISH = "Primary Key";
		public String getuserCartIdFieldName() {return "userCartId";} public String userCartId_LABEL_ENGLISH = "UserCartId";
	public String getorderNoFieldName() {return "orderNo";} public String orderNo_LABEL_ENGLISH = "OrderNo";
	public String getorderDateFieldName() {return "orderDate";} public String orderDate_LABEL_ENGLISH = "OderDate";
	public String getorderAmountFieldName() {return "orderAmount";} public String orderAmount_LABEL_ENGLISH = "OrderAmount";

	
	public String getvwLastModifiedDateFieldName() {return "vwLastModifiedDate";} public String vwLastModifiedDate_LABEL_ENGLISH = "Update Date";
	public String getvwLastModifiedTimeFieldName() {return "vwLastModifiedTime";} public String vwLastModifiedTime_LABEL_ENGLISH = "Update Time";
	public String getvwLastActionFieldName() {return "vwLastAction";} public String vwLastAction_LABEL_ENGLISH = "Last Action";
	public String getvwModifiedByFieldName() {return "vwModifiedBy";} public String vwModifiedBy_LABEL_ENGLISH = "Modified By";
	public String getvwTxnRemarksFieldName() {return "vwTxnRemarks";} public String vwTxnRemarks_LABEL_ENGLISH = "Remarks";
	public String getvwTxnStatusFieldName() {return "vwTxnStatus";} public String vwTxnStatus_LABEL_ENGLISH = "Status";
	public String LANG_ENGLISH = "ENGLISH";
	public String getLabel(String sBeanField)
	{
		return getLabel(sBeanField, LANG_ENGLISH);
	}
	public String getLabel(String sBeanField, String sLang)
	{
		String sBeanFieldLabel = "<<No Label Found !!!>>";
		if (sBeanField.matches((getordersIdFieldName()))) sBeanFieldLabel = ordersId_LABEL_ENGLISH;
				if (sBeanField.matches((getuserCartIdFieldName()))) sBeanFieldLabel = userCartId_LABEL_ENGLISH;
		if (sBeanField.matches((getorderNoFieldName()))) sBeanFieldLabel = orderNo_LABEL_ENGLISH;
		if (sBeanField.matches((getorderDateFieldName()))) sBeanFieldLabel = orderDate_LABEL_ENGLISH;
		if (sBeanField.matches((getorderAmountFieldName()))) sBeanFieldLabel = orderAmount_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
