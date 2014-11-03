/*     */ package com.easytrack.commons.util;
/*     */ 
/*     */ import com.easytrack.commons.config.Config;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ import javax.sql.DataSource;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class ServiceLocator
/*     */ {
/*  24 */   private static Map _datasources = new HashMap();
/*     */ 
/*  28 */   private static Map _connections = Collections.synchronizedMap(new HashMap());
/*     */ 
/*  32 */   private static Log log = LogFactory.getLog(ServiceLocator.class);
/*     */ 
/*  36 */   private static String appServer = "JBOSS";
/*     */ 
/*  40 */   private static String dsPrefix = "";
/*     */ 
/*  44 */   private static String ejbPrefix = "";
/*     */   private static InitialContext ic;
/*  74 */   private static final String DATASOURCE = dsPrefix + "ETDataSource";
/*     */ 
/*     */   public static Object getEJB(String name)
/*     */     throws Exception
/*     */   {
/*  82 */     return ic.lookup(ejbPrefix + name);
/*     */   }
/*     */ 
/*     */   public static Object lookup(String name)
/*     */     throws Exception
/*     */   {
/*  91 */     return ic.lookup(name);
/*     */   }
/*     */ 
/*     */   public static DataSource getDataSource()
/*     */     throws Exception
/*     */   {
/* 101 */     DataSource ds = (DataSource)_datasources.get(DATASOURCE);
/* 102 */     if (ds == null) {
/* 103 */       ds = (DataSource)ic.lookup(DATASOURCE);
/*     */     }
/* 105 */     return ds;
/*     */   }
/*     */ 
/*     */   public static DataSource getDataSource(String dsName)
/*     */     throws Exception
/*     */   {
/* 114 */     DataSource ds = (DataSource)_datasources.get(dsPrefix + dsName);
/* 115 */     if (ds == null) {
/* 116 */       ds = (DataSource)ic.lookup(dsPrefix + dsName);
/*     */     }
/* 118 */     return ds;
/*     */   }
/*     */ 
/*     */   public static Connection getConnection(Class clazz)
/*     */     throws Exception
/*     */   {
/* 127 */     return getConnection(clazz.getName());
/*     */   }
/*     */ 
/*     */   public static Connection getConnection(String className)
/*     */     throws Exception
/*     */   {
/* 136 */     Connection conn = getDataSource().getConnection();
/* 137 */     synchronized (_connections) {
/* 138 */       ConnectionCount cc = (ConnectionCount)_connections.get(className);
/* 139 */       if (cc == null) {
/* 140 */         _connections.put(className, new ConnectionCount(1, 0));
/*     */       } else {
/* 142 */         cc.increaseRetrieveCount();
/* 143 */         _connections.put(className, cc);
/*     */       }
/*     */     }
/* 146 */     return conn;
/*     */   }
/*     */ 
/*     */   public static Connection getConnection()
/*     */     throws Exception
/*     */   {
/* 154 */     return getDataSource().getConnection();
/*     */   }
/*     */ 
/*     */   public static Connection getConnection(String dsName, String className)
/*     */     throws Exception
/*     */   {
/* 164 */     Connection conn = getDataSource(dsName).getConnection();
/* 165 */     synchronized (_connections) {
/* 166 */       ConnectionCount cc = (ConnectionCount)_connections.get(className);
/* 167 */       if (cc == null) {
/* 168 */         _connections.put(className, new ConnectionCount(1, 0));
/*     */       } else {
/* 170 */         cc.increaseRetrieveCount();
/* 171 */         _connections.put(className, cc);
/*     */       }
/*     */     }
/* 174 */     return conn;
/*     */   }
/*     */ 
/*     */   public static void closeConnection(Class clazz, Connection conn)
/*     */   {
/* 182 */     closeConnection(clazz.getName(), conn);
/*     */   }
/*     */ 
/*     */   public static void closeConnection(String className, Connection conn)
/*     */   {
/*     */     try
/*     */     {
/* 192 */       if ((conn == null) || (conn.isClosed())) {
/* 193 */         log.error(className + " : the conection is null or has been closed.");
/* 194 */         return;
/*     */       }
/* 196 */       synchronized (_connections) {
/* 197 */         ConnectionCount cc = (ConnectionCount)_connections.get(className);
/* 198 */         if (cc != null) {
/* 199 */           cc.increaseCloseCount();
/* 200 */           _connections.put(className, cc);
/*     */         }
/*     */       }
/* 203 */       conn.close();
/*     */     } catch (SQLException e) {
/* 205 */       log.error(className + " failed to close database connection!", e);
/* 206 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Map getConnectionCount()
/*     */   {
/* 215 */     return _connections;
/*     */   }
/*     */ 
/*     */   public static String getAppServerName() {
/* 219 */     return appServer;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  54 */       ic = new InitialContext();
/*  55 */       appServer = Config.getConfig("CONFIG", "APP-SERVER");
/*  56 */       if (appServer.equalsIgnoreCase("JBOSS")) {
/*  57 */         ejbPrefix = "";
/*  58 */         dsPrefix = "java:/";
/*  59 */       } else if (appServer.equalsIgnoreCase("WEBSPHERE")) {
/*  60 */         ejbPrefix = "java:comp/env/";
/*  61 */         dsPrefix = "java:comp/env/";
/*  62 */       } else if (appServer.equalsIgnoreCase("WEBLOGIC")) {
/*  63 */         ejbPrefix = "";
/*  64 */         dsPrefix = "";
/*     */       }
/*     */     } catch (NamingException e) {
/*  67 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.ServiceLocator
 * JD-Core Version:    0.6.2
 */