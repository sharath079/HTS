package com.shoppingcart.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "OrderItemLabelBean")*/
public class OrderItemLabel extends RulesBean{
	public String getorderItemIdFieldName() {return "orderItemId";} public String orderItemId_LABEL_ENGLISH = "Primary Key";
		public String getorderIdFieldName() {return "orderId";} public String orderId_LABEL_ENGLISH = "OrderId";
	public String getproductIdFieldName() {return "productId";} public String productId_LABEL_ENGLISH = "ProductId";
	public String getproductQuantityFieldName() {return "productQuantity";} public String productQuantity_LABEL_ENGLISH = "ProductQuantity";
	public String getproductUnitPriceFieldName() {return "productUnitPrice";} public String productUnitPrice_LABEL_ENGLISH = "ProductUnitPrice";
	public String getsubTotalAmtFieldName() {return "subTotalAmt";} public String subTotalAmt_LABEL_ENGLISH = "SubTotalAmt";

	
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
		if (sBeanField.matches((getorderItemIdFieldName()))) sBeanFieldLabel = orderItemId_LABEL_ENGLISH;
				if (sBeanField.matches((getorderIdFieldName()))) sBeanFieldLabel = orderId_LABEL_ENGLISH;
		if (sBeanField.matches((getproductIdFieldName()))) sBeanFieldLabel = productId_LABEL_ENGLISH;
		if (sBeanField.matches((getproductQuantityFieldName()))) sBeanFieldLabel = productQuantity_LABEL_ENGLISH;
		if (sBeanField.matches((getproductUnitPriceFieldName()))) sBeanFieldLabel = productUnitPrice_LABEL_ENGLISH;
		if (sBeanField.matches((getsubTotalAmtFieldName()))) sBeanFieldLabel = subTotalAmt_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
