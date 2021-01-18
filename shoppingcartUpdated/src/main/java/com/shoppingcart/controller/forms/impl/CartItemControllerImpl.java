package com.shoppingcart.controller.forms.impl;

import org.hibernate.Session;
import java.util.HashMap;
import com.shoppingcart.bean.CartItem;
import com.shoppingcart.bean.UserCart;
import com.shoppingcart.util.*;
import java.math.BigDecimal;
import java.sql.Time;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.hibernate.Query;
import java.io.File;
import java.util.Arrays;
import java.util.Date;
import com.vw.runtime.VWResponseBean;
import com.shoppingcart.controller.forms.base.CartItemLabel;
import com.shoppingcart.controller.forms.base.CartItemControllerBase;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.SequenceGenerator;
import javax.sound.midi.Sequence;

import java.util.List;
import com.vw.runtime.IRulesFields;
import com.vw.runtime.RulesBean;
import com.vw.runtime.VWMastersBean;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import java.text.SimpleDateFormat;

@SuppressWarnings("unused")
/**
 * 
 * @author Srikanth Brahmadevara: Harivara Technology Solutions, CODE GENERATED
 * 
 */
/*
 * @ManagedBean(name = "CartItemControllerImpl")
 * 
 * @RequestScoped
 */
public class CartItemControllerImpl extends CartItemControllerBase {
	public CartItemControllerImpl(Session session) {
		super(session);
	}

	public CartItemControllerImpl(Session session, JsonObject standardReqParams) {
		super(session, standardReqParams);
	}

	public CartItemControllerImpl() {
		super();
	}

	public void doEnrichValuesImpl(JsonObject paramsInfoObj) {
		// Custom handling
	}

	public void doAfterEnrichValuesImpl() {
		// Custom handling
	}

	public void doValidationsImpl() {
		// Custom handling
	}

	public String getLcNoImpl() {
		return "";
	}

	public void doAfterSetValues() {
		// Custom handling
	}

	public void doAfterSelectRowImpl() {
		// Custom handling
	}

	public void doBeforeCreateTransactionImpl(JsonObject paramsInfoObj) {
		// Custom handling
		try {

			System.out.println("Inside Impl class doBeforeCreateImpl()");
			CartItem cartItem = (CartItem) getManagedBean();
			UserCart userCart = new UserCart();
			
			Session session = getDBSession();
			Map<String, String> paramsMap = new HashMap<>();

			UserCartControllerImpl userCartImpl = new UserCartControllerImpl(session);
			JsonObject userCartList = userCartImpl.retrieveUserCartList(paramsMap);
			JsonArray userCartArray = new JsonArray();
			userCartArray = userCartList.getAsJsonArray("userCartList");
			int size = userCartArray.size();

			// Creating UserCart Entry

			userCart.setCartCreationTime(java.sql.Time.valueOf(java.time.LocalTime.now()));
			JsonObject userCartDataObject = userCart.getDataObject(session);
			if (size == 0) {
				userCartImpl.createUserCart(userCartDataObject);
			}
			
			// Retrieving UserCart Details
			userCartList = userCartImpl.retrieveUserCartList(paramsMap);
			userCartArray = new JsonArray();
			userCartArray = userCartList.getAsJsonArray("userCartList");
			size = userCartArray.size();
			JsonObject userCartDataObj = (JsonObject) userCartArray.get(size - 1);
			int userCartId = userCartDataObj.get("userCartId").getAsInt();

			// setting "CartItem" UserId
			cartItem.setUserCartId(userCartId);

			int cartItem_productId = cartItem.getProductId();
			int cartItem_productQuantity = cartItem.getProductQuantity();
			System.out.println("ProductQuantity :: " + cartItem_productQuantity);

			// Getting Productprice from "Products" & setting to "cartItems"
			// productUnitPrice Attribute
			ProductsControllerImpl productsImpl = new ProductsControllerImpl(session);
			JsonObject productsList = productsImpl.retrieveProductsList(paramsMap);
			JsonArray productsArray = new JsonArray();
			productsArray = productsList.getAsJsonArray("productsList");

			int productPrice = 0;

			for (int i = 0; i < productsArray.size(); i++) {

				JsonObject productsDataObject = (JsonObject) productsArray.get(i);
				int productId = productsDataObject.get("productsId").getAsInt();
				if (productId == cartItem_productId) {
					productPrice = productsDataObject.get("productPrice").getAsInt();

				}
			}
			cartItem.setProductUnitPrice(productPrice);

			int subTotal = (cartItem_productQuantity) * (productPrice);
			cartItem.setSubTotalAmount(subTotal);
		} catch (Exception e) {
			CommonUtil.writeTolog(e);
			addCustomResponse("Verification token could not be generated to verify email id.");
			return;
		}
	}

	public void doAfterCreateTransactionImpl(JsonObject paramsInfoObj) {
		// Custom handling
		CartItem cartItem = (CartItem) getManagedBean();
		Map<String, String> paramsMap = new HashMap<>();

		JsonObject cartItemList = retrieveCartItemList(paramsMap);
		int grandTotal = 0;
		JsonArray cartItemArray = new JsonArray();
		cartItemArray = cartItemList.getAsJsonArray("cartItemList");
		for (int i = 0; i < cartItemArray.size(); i++) 
		{
			JsonObject cartItemDataObject = (JsonObject) cartItemArray.get(i);

			System.out.println("cartItemDataObj :: " + cartItemDataObject);
			int subTotal = cartItemDataObject.get("subTotalAmount").getAsInt();

			grandTotal = grandTotal + subTotal;
				
		}

		cartItem.setGrandTotal(grandTotal);
	}

	public void doBeforeUpdateTransactionImpl(JsonObject paramsInfoObj) {
		// Custom handling
	}

	public void doUpdateQueryWithParameterValuesImpl(Query query, java.util.Map<String, Object> paramsMap) {
		// Custom handling
	}

	public String doGetUpdatedQueryStringForSearchImpl(String queryString) {
		// Custom handling
		return "";
	}

	public void doAfterCreateTransactionCommittedImpl() {
		// Custom handling
	}

	public void doAfterUpdateTransactionCommittedImpl() {
		// Custom handling
	}

	public void doAfterUpdateTransactionImpl(JsonObject paramsInfoObj) {
		// Custom handling
	}

	public void doBeforeDeleteTransactionImpl() {
		// Custom handling
		
	}

	
	public void doAfterDeleteTransactionImpl()
	{
		Map<String, String> paramsMap = new HashMap<>();
		Session session=getDBSession();
		CartItem cartItem = (CartItem) getManagedBean();
		
        int cartItem_Id =0;
          Integer grandTotal=0;    
		  JsonObject cartItemList = retrieveCartItemList(paramsMap); 
		  JsonArray cartItemArray = new JsonArray(); 
		  cartItemArray =cartItemList.getAsJsonArray("cartItemList"); 
		  
		  for (int i = 0; i <cartItemArray.size(); i++) 
		  { 
		  JsonObject cartItemDataObject = (JsonObject)cartItemArray.get(i); 
		  cartItem_Id =cartItemDataObject.get("cartItemId").getAsInt(); 
		  		 
          // updating the next record based on "cartItem_Id"
          CartItem cartItem_session=(CartItem) session.get(CartItem.class,cartItem_Id);
          Integer subTotal=cartItem_session.getSubTotalAmount();
          grandTotal=subTotal+grandTotal;
          cartItem_session.setGrandTotal(grandTotal);
        
        try {
			updateCartItem(cartItem_session.getDataObject(session));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	    }
        
	}	
	
		
	public void doAfterEntityLoadedImpl(CartItem cartItem, JsonObject initParamsInfo) {
		// Custom handling
	}

	public JsonObject doExecuteAPIRequestImpl(String apiName, JsonObject cartItemDataObject, CartItem cartItem) {
		// Custom handling
		JsonObject dataObject = new JsonObject();
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataObject.addProperty("alert", "This API (" + apiName + ") has no implementation !!");
		dataObject.addProperty("success", 0);
		return dataObject;
	}

	public void doExecuteCustomAPIImpl(String customAPIMessage) {
		// Custom handling
	}

	public void doAfterLookupRowSelectedImpl(CartItem cartItem, String attributeName) {
		// Custom handling
	}

	public void doAfterSelectOptionChangedImpl(CartItem cartItem, String attributeName) {
		// Custom handling
	}

	public void doBeforeLookupEntitiesRetrievedImpl(String callingEntityName, String callingAttributeName,
			HashMap customSearchParams) {
		// Custom handling
	}

	public void verifyIfTransactionIsDeletableImpl() {
		// Custom handling
	}

	public void verifyIfTransactionIsUpdatableImpl() {
		// Custom handling
	}

	public void doBeforeTransactionApprovedImpl() {
		// Custom handling
	}

	public void doAfterTransactionApprovedImpl() {
		// Custom handling
	}

	public void doBeforeTransactionRolledbackImpl() {
		// Custom handling
	}

	public void doRefreshFromUIImpl() {
		// Custom handling
	}

	public void doValidateRepeatLineImpl(String sRepeatTagName, String string, int iCurrIndex) {
		// Custom handling
	}

	// Custom code

	public void doAfterSearchResultRowAddedImpl(JsonObject rowInfoObj, java.util.Map<String, Object> paramsMap) {
		// Custom handling
	}

	public String doGetOrderByClauseSearchImpl() {
		// Custom handling
		String orderByClause = "  ";
		return orderByClause;
	}

}
