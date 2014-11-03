/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import com.easytrack.commons.util.ServiceLocator;
/*     */ import java.sql.Connection;
/*     */ 
/*     */ public class SQLUtils
/*     */ {
/*     */   public static final int STR_UNION = 1;
/*     */   public static final int SELECT = 2;
/*     */   public static final int DISTINCT_SELECT = 3;
/*     */   public static final int VARCHAR = 4;
/*     */   public static final int SQLSERVER = 0;
/*     */   public static final int ORACLE = 1;
/*     */   public static final int MYSQL = 2;
/*     */   public static final int DB2 = 3;
/*     */   public static final int PGSQL = 4;
/*  83 */   private static int DBType = 0;
/*     */ 
/*  92 */   private static String[][] sqls = { { "", "+", "select top 1000000 ", "select distinct top 1000000 ", "nvarchar" }, { "", "||", "select ", "select distinct ", "nvarchar2" }, { "", "||", "select ", "select distinct ", "nvarchar" }, { "", "||", "select ", "select distinct ", "varchar" }, { "", "||", "select ", "select distinct ", "varchar" } };
/*     */ 
/*     */   public static String getSQL(int key)
/*     */   {
/* 125 */     return sqls[DBType][key];
/*     */   }
/*     */ 
/*     */   public static String getDateSQL(String field)
/*     */   {
/* 136 */     switch (DBType) {
/*     */     case 0:
/* 138 */       return "convert(datetime, convert( varchar, " + field + ", 102), 102)";
/*     */     case 2:
/* 140 */       return "date(" + field + ")";
/*     */     case 1:
/* 142 */       return "to_date(to_char(" + field + ", 'YY.MM.DD'), 'YY.MM.DD')";
/*     */     case 3:
/* 144 */       return "date(" + field + ")";
/*     */     case 4:
/* 146 */       return "date(" + field + ")";
/*     */     }
/* 148 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getLikeSQL(String field, String value) {
/* 152 */     StringBuffer sql = new StringBuffer();
/*     */ 
/* 154 */     switch (DBType) {
/*     */     case 0:
/*     */     case 1:
/*     */     case 2:
/*     */     case 4:
/* 159 */       sql.append(" (',' ");
/* 160 */       sql.append(getSQL(1));
/* 161 */       sql.append(field);
/* 162 */       sql.append(getSQL(1));
/* 163 */       sql.append(" ',') like ('%,' ");
/* 164 */       sql.append(getSQL(1));
/* 165 */       sql.append(value);
/* 166 */       sql.append(getSQL(1));
/* 167 */       sql.append(" ',%')");
/* 168 */       return sql.toString();
/*     */     case 3:
/* 170 */       sql.append(" locate('");
/* 171 */       sql.append(value);
/* 172 */       sql.append("', ");
/* 173 */       sql.append(field);
/* 174 */       sql.append(" ) > 0");
/* 175 */       return sql.toString();
/*     */     }
/* 177 */     return "";
/*     */   }
/*     */ 
/*     */   public static String[] getSequence(String tableName, int companyID, int size)
/*     */   {
/* 192 */     String[] oRet = null;
/* 193 */     Connection conn = null;
/*     */     try {
/* 195 */       conn = ServiceLocator.getConnection(SQLUtils.class);
/* 196 */       BaseDAO dao = new BaseDAO();
/* 197 */       dao.setConnection(conn);
/* 198 */       oRet = dao.getSequence(tableName, companyID, size);
/*     */     } catch (Exception e) {
/* 200 */       e.printStackTrace();
/* 201 */       return null;
/*     */     } finally {
/* 203 */       ServiceLocator.closeConnection(SQLUtils.class, conn);
/*     */     }
/* 205 */     return oRet;
/*     */   }
/*     */ 
/*     */   public static String getSequence(String tableName, int companyID)
/*     */   {
/* 218 */     String oRet = null;
/* 219 */     Connection conn = null;
/*     */     try {
/* 221 */       conn = ServiceLocator.getConnection(SQLUtils.class);
/* 222 */       BaseDAO dao = new BaseDAO();
/* 223 */       dao.setConnection(conn);
/* 224 */       oRet = dao.getSequence(tableName, companyID);
/*     */     } catch (Exception e) {
/* 226 */       e.printStackTrace();
/* 227 */       return null;
/*     */     } finally {
/* 229 */       ServiceLocator.closeConnection(SQLUtils.class, conn);
/*     */     }
/* 231 */     return oRet;
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/* 100 */     String dbType = DBAccessorFactory.DBType;
/* 101 */     if (dbType.equalsIgnoreCase("ORACLE"))
/* 102 */       DBType = 1;
/* 103 */     else if (dbType.equalsIgnoreCase("MySQL"))
/* 104 */       DBType = 2;
/* 105 */     else if (dbType.equalsIgnoreCase("DB2"))
/* 106 */       DBType = 3;
/* 107 */     else if (dbType.equalsIgnoreCase("SQLSERVER"))
/* 108 */       DBType = 0;
/* 109 */     else if (dbType.equalsIgnoreCase("PostgreSQL"))
/* 110 */       DBType = 4;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.SQLUtils
 * JD-Core Version:    0.6.2
 */