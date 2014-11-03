/*    */ package com.easytrack.commons.db;
/*    */ 
/*    */ import com.easytrack.commons.config.Config;
/*    */ import java.sql.Connection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DBAccessorFactory
/*    */ {
/* 46 */   private static String DBAccessorClassName = "com.easytrack.commons.db.DBAccessor" + DBType + "Impl";
/*    */ 
/* 31 */   public static String DBType = "";
/*    */ 
/*    */   public static DBAccessor createDBAccessor()
/*    */   {
/* 53 */     DBAccessor impl = null;
/*    */     try {
/* 55 */       impl = (DBAccessor)Class.forName(DBAccessorClassName).newInstance();
/*    */     } catch (Exception e) {
/*    */     }
/* 58 */     return impl;
/*    */   }
/*    */ 
/*    */   public static DBAccessor createDBAccessor(Connection connection, String sql, List values)
/*    */   {
/* 68 */     DBAccessor impl = null;
/*    */     try {
/* 70 */       impl = (DBAccessor)Class.forName(DBAccessorClassName).newInstance();
/*    */     } catch (Exception e) {
/*    */     }
/* 73 */     impl.setConnection(connection);
/* 74 */     impl.setSql(sql);
/* 75 */     impl.setConditionValues(values);
/* 76 */     return impl;
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 34 */     String dbType = Config.getConfig("CONFIG", "DB-TYPE");
/* 35 */     if (dbType.equalsIgnoreCase("ORACLE"))
/* 36 */       DBType = "Oracle";
/* 37 */     else if (dbType.equalsIgnoreCase("MySQL"))
/* 38 */       DBType = "MySQL";
/* 39 */     else if (dbType.equalsIgnoreCase("DB2"))
/* 40 */       DBType = "DB2";
/* 41 */     else if (dbType.equalsIgnoreCase("SQLSERVER"))
/* 42 */       DBType = "SQLServer";
/* 43 */     else if (dbType.equalsIgnoreCase("PostgreSQL"))
/* 44 */       DBType = "PostgreSQL";
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessorFactory
 * JD-Core Version:    0.6.2
 */