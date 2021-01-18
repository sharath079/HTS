package com.shoppingcart.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.hibernate.Session;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shoppingcart.bean.OrderItem;
import com.shoppingcart.bean.Orders;
import com.shoppingcart.controller.forms.impl.CartItemControllerImpl;
import com.shoppingcart.controller.forms.impl.OrderItemControllerImpl;
import com.shoppingcart.controller.forms.impl.OrdersControllerImpl;
import com.shoppingcart.controller.forms.impl.UserCartControllerImpl;

public class CartItemUtil {
	
	/*
	 * public JsonObject doValidateCartItem(Session organisationSession) throws
	 * Exception { System.out.println("Inside doValidationCartItem()......");
	 * JsonObject dataObject = new JsonObject(); Map<String, String> paramsMap = new
	 * HashMap<>();
	 * 
	 * Session ses = organisationSession;
	 * 
	 * int orderAmt = 0;
	 * 
	 * // Orders Entity Details
	 * 
	 * Orders orders = new Orders(); Random randomn = new Random();
	 * 
	 * // Retrieving UserCart Details
	 * 
	 * UserCartControllerImpl userCartImpl = new UserCartControllerImpl(ses);
	 * JsonObject userCartList = userCartImpl.retrieveUserCartList(paramsMap);
	 * JsonArray userCartArray = new JsonArray(); userCartArray =
	 * userCartList.getAsJsonArray("userCartList");
	 * 
	 * int size=userCartArray.size()-1; JsonObject userCartDataObject = (JsonObject)
	 * userCartArray.get(size); int userCartId =
	 * userCartDataObject.get("userCartId").getAsInt();
	 * 
	 * // setting Entry to Orders Entity orders.setUserCartId(userCartId);;
	 * orders.setOrderNo(randomn.nextInt(1000)); orders.setOrderDate(new Date());
	 * 
	 * // Retrieving CartItemList CartItemControllerImpl cartItemImpl=new
	 * CartItemControllerImpl(ses); JsonObject cartItemList =
	 * cartItemImpl.retrieveCartItemList(paramsMap);
	 * 
	 * System.out.println("JsonObject :: " + cartItemList);
	 * System.out.println("cartItemList :: " +
	 * cartItemList.getAsJsonArray("cartItemList"));
	 * 
	 * JsonArray cartItemArray = new JsonArray();
	 * 
	 * cartItemArray = cartItemList.getAsJsonArray("cartItemList");
	 * 
	 * for (int i = 0; i < cartItemArray.size(); i++) {
	 * 
	 * JsonObject cartItemDataObject = (JsonObject) cartItemArray.get(i);
	 * 
	 * // System.out.println("cartItemDataObj :: "+cartItemDataObject); int
	 * cartItem_productId = cartItemDataObject.get("productId").getAsInt(); int
	 * cartItem_productQty = cartItemDataObject.get("productQuantity").getAsInt();
	 * int cartItem_productUp =
	 * cartItemDataObject.get("productUnitPrice").getAsInt(); int cartItem_subtotal
	 * = cartItemDataObject.get("subTotalAmount").getAsInt();
	 * 
	 * System.out.println("PID :: " + cartItem_productId);
	 * System.out.println("Quantity :: " + cartItem_productQty);
	 * System.out.println("ProductUnitPrice :: " + cartItem_productUp);
	 * System.out.println("subtotal :: " + cartItem_subtotal);
	 * System.out.println("=========================================");
	 * 
	 * // Saving OrderItem Entity Details OrderItem orderItem = new OrderItem();
	 * 
	 * orderItem.setOrderId(orders.getOrderNo());
	 * orderItem.setProductId(cartItem_productId);
	 * orderItem.setProductQuantity(cartItem_productQty);
	 * orderItem.setProductUnitPrice(cartItem_productUp);
	 * orderItem.setSubTotalAmt(cartItem_subtotal);
	 * 
	 * orderAmt = orderAmt + cartItem_subtotal;
	 * 
	 * JsonObject orderItemDataObject = new JsonObject(); orderItemDataObject =
	 * orderItem.getDataObject(ses);
	 * 
	 * // System.out.println("orderItemDataObject :: "+orderItemDataObject);
	 * 
	 * OrderItemControllerImpl orderItemimpl = new OrderItemControllerImpl(ses);
	 * orderItemimpl.createOrderItem(orderItemDataObject);
	 * 
	 * cartItemImpl.deleteCartItem(cartItemDataObject); }
	 * 
	 * // setting OrderAmount Total orders.setOrderAmount(orderAmt);
	 * 
	 * JsonObject ordersDataObject = new JsonObject(); ordersDataObject =
	 * orders.getDataObject(ses);
	 * 
	 * OrdersControllerImpl ordersImpl = new OrdersControllerImpl(ses);
	 * ordersImpl.createOrders(ordersDataObject);
	 * 
	 * dataObject.addProperty("success", 1); return dataObject; }
	 */
}
