package com.shoppingcart.controller.forms.base;
//import javax.faces.bean.ManagedBean;
import com.vw.runtime.RulesBean;
/**
*
 * @author  Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
* 
*/
/*@ManagedBean(name = "ProductsLabelBean")*/
public class ProductsLabel extends RulesBean{
	public String getproductsIdFieldName() {return "productsId";} public String productsId_LABEL_ENGLISH = "Primary Key";
		public String getproductNameFieldName() {return "productName";} public String productName_LABEL_ENGLISH = "ProductName";
	public String getproductDescriptionFieldName() {return "productDescription";} public String productDescription_LABEL_ENGLISH = "ProductDescription";
	public String getproductPriceFieldName() {return "productPrice";} public String productPrice_LABEL_ENGLISH = "ProductPrice";
	public String getproductUnitTypeFieldName() {return "productUnitType";} public String productUnitType_LABEL_ENGLISH = "ProductUnitType";

	
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
		if (sBeanField.matches((getproductsIdFieldName()))) sBeanFieldLabel = productsId_LABEL_ENGLISH;
				if (sBeanField.matches((getproductNameFieldName()))) sBeanFieldLabel = productName_LABEL_ENGLISH;
		if (sBeanField.matches((getproductDescriptionFieldName()))) sBeanFieldLabel = productDescription_LABEL_ENGLISH;
		if (sBeanField.matches((getproductPriceFieldName()))) sBeanFieldLabel = productPrice_LABEL_ENGLISH;
		if (sBeanField.matches((getproductUnitTypeFieldName()))) sBeanFieldLabel = productUnitType_LABEL_ENGLISH;

		return sBeanFieldLabel;
	}
}
