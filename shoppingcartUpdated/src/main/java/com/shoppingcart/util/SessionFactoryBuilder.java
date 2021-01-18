package com.shoppingcart.util;
import java.util.HashMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.shoppingcart.util.CommonUtil;
public class SessionFactoryBuilder
{
	private static HashMap<String, SessionFactory> sessionFactoryMap = new HashMap<String, SessionFactory>();
	public static Session getDBSession(String dbName)
	{
		return getDBSession(dbName, false);
	}
	public static Session getDBSession(String dbName, boolean createNewSession)
	{
		try
		{
			if (sessionFactoryMap.containsKey(dbName) && createNewSession == false)
			{
				SessionFactory sessionFactory = sessionFactoryMap.get(dbName);
				Session session = sessionFactory.openSession();
				return session;
			}
			Configuration config = new Configuration();
			config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			config.setProperty("hibernate.connection.url", "jdbc:mysql://" + CommonUtil.getPropertyValue("DB_HOST_NAME") + ":" + CommonUtil.getPropertyValue("DB_HOST_PORT") + "/" + dbName);
			config.setProperty("hibernate.connection.username", CommonUtil.getPropertyValue("DB_USER_NAME"));
			config.setProperty("hibernate.connection.password", CommonUtil.getPropertyValue("DB_PASSWORD"));
			config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
						config.addAnnotatedClass(com.shoppingcart.bean.Organisations.class);
			config.addAnnotatedClass(com.shoppingcart.bean.UserInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.PrivilegeGroup.class);
			config.addAnnotatedClass(com.shoppingcart.bean.PrivilegeGroupItems.class);
			config.addAnnotatedClass(com.shoppingcart.bean.EmployeeRoles.class);
			config.addAnnotatedClass(com.shoppingcart.bean.LoginSessionInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.ConfigProperties.class);
			config.addAnnotatedClass(com.shoppingcart.bean.TaskInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.TaskExecutionInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.TaskLayoutParameters.class);
			config.addAnnotatedClass(com.shoppingcart.bean.EmailAttachmentLayout.class);
			config.addAnnotatedClass(com.shoppingcart.bean.TaskScheduleInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.TaskSentInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.UserCart.class);
			config.addAnnotatedClass(com.shoppingcart.bean.CartItem.class);
			config.addAnnotatedClass(com.shoppingcart.bean.Products.class);
			config.addAnnotatedClass(com.shoppingcart.bean.Orders.class);
			config.addAnnotatedClass(com.shoppingcart.bean.OrderItem.class);

			config.addAnnotatedClass(com.shoppingcart.bean.DualTableInfo.class);
			config.addAnnotatedClass(com.shoppingcart.request.service.RequestReceived.class);
			config.addAnnotatedClass(com.shoppingcart.request.service.RequestSent.class);
			config.addAnnotatedClass(com.shoppingcart.bean.FileUpload.class);
			config.addAnnotatedClass(com.shoppingcart.bean.QueryInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.QueryTableInfo.class);
			config.addAnnotatedClass(com.shoppingcart.bean.QueryColumnInfo.class);
			SessionFactory sessionFactory = config.buildSessionFactory();
			Session session = sessionFactory.openSession();
			if(createNewSession == false)
			{
				sessionFactoryMap.put(dbName, sessionFactory);
			}
			return session;
		}
		catch (Exception e)
		{
			CommonUtil.writeTolog(e);
		}
		return null;
	}
}
