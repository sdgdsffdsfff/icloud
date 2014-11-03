/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import com.easytrack.commons.config.Config;
/*     */ import java.sql.Connection;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class BaseDAO
/*     */   implements IBaseDAO
/*     */ {
/*  29 */   private Connection conn = null;
/*  30 */   private static boolean printLog = true;
/*  31 */   private static boolean debugBatchValues = false;
/*     */   private DBAccessor dbAccessor;
/*     */   private SequenceUtils sequenceUtils;
/*  34 */   private static Log log = LogFactory.getLog(BaseDAO.class.getName());
/*     */ 
/*     */   public BaseDAO()
/*     */   {
/*  50 */     this.dbAccessor = DBAccessorFactory.createDBAccessor();
/*  51 */     this.sequenceUtils = new SequenceUtils();
/*     */   }
/*     */ 
/*     */   public BaseDAO(DBAccessor dbAccessor)
/*     */   {
/*  60 */     this.dbAccessor = dbAccessor;
/*  61 */     this.sequenceUtils = new SequenceUtils();
/*     */   }
/*     */ 
/*     */   public void initSequence(int companyID) throws Exception {
/*  65 */     this.sequenceUtils.initSequence(companyID);
/*     */   }
/*     */ 
/*     */   public boolean isPrintLog()
/*     */   {
/*  72 */     return printLog;
/*     */   }
/*     */ 
/*     */   public void setPrintLog(boolean printLog)
/*     */   {
/*  80 */     printLog = printLog;
/*     */   }
/*     */ 
/*     */   public int insert(String sql, List values)
/*     */     throws SQLException
/*     */   {
/*  95 */     debugSQL(sql, values);
/*  96 */     this.dbAccessor.setSql(sql);
/*  97 */     this.dbAccessor.setUpdateValues(values);
/*  98 */     return this.dbAccessor.executeUpdate();
/*     */   }
/*     */ 
/*     */   public int insert1(String sql, List values) throws SQLException {
/* 102 */     this.dbAccessor.setSql(sql);
/* 103 */     this.dbAccessor.setUpdateValues(values);
/* 104 */     return this.dbAccessor.executeUpdate();
/*     */   }
/*     */ 
/*     */   public int[] batchInsert(String sql, List values)
/*     */     throws SQLException
/*     */   {
/* 120 */     debugSQL(sql, values, debugBatchValues);
/* 121 */     this.dbAccessor.setSql(sql);
/* 122 */     this.dbAccessor.setUpdateValues(values);
/* 123 */     return this.dbAccessor.executeBatchUpdate();
/*     */   }
/*     */ 
/*     */   public int update(String sql, List values)
/*     */     throws SQLException
/*     */   {
/* 138 */     debugSQL(sql, values);
/* 139 */     this.dbAccessor.setSql(sql);
/* 140 */     this.dbAccessor.setUpdateValues(values);
/* 141 */     return this.dbAccessor.executeUpdate();
/*     */   }
/*     */ 
/*     */   public int[] batchUpdate(String sql, List values)
/*     */     throws SQLException
/*     */   {
/* 157 */     debugSQL(sql, values, debugBatchValues);
/* 158 */     this.dbAccessor.setSql(sql);
/* 159 */     this.dbAccessor.setUpdateValues(values);
/* 160 */     return this.dbAccessor.executeBatchUpdate();
/*     */   }
/*     */ 
/*     */   public int delete(String sql, List values)
/*     */     throws SQLException
/*     */   {
/* 175 */     debugSQL(sql, values);
/* 176 */     this.dbAccessor.setSql(sql);
/* 177 */     this.dbAccessor.setUpdateValues(values);
/* 178 */     return this.dbAccessor.executeUpdate();
/*     */   }
/*     */ 
/*     */   public DBRowSet search(String sql, List values, int rowsPerPage, int page)
/*     */     throws SQLException
/*     */   {
/* 197 */     debugSQL(sql, values);
/* 198 */     this.dbAccessor.setSql(sql);
/* 199 */     this.dbAccessor.setConditionValues(values);
/* 200 */     this.dbAccessor.setRowCountPerPage(rowsPerPage);
/* 201 */     return this.dbAccessor.getPageData(page);
/*     */   }
/*     */ 
/*     */   public DBRowSet search(String sql, List values)
/*     */     throws SQLException
/*     */   {
/* 216 */     return search(sql, values, -1, -1);
/*     */   }
/*     */ 
/*     */   public DBRowSet batchSearch(String sql, List values, int rowsPerPage, int page)
/*     */     throws SQLException
/*     */   {
/* 237 */     debugSQL(sql, values);
/* 238 */     this.dbAccessor.setSql(sql);
/* 239 */     this.dbAccessor.setConditionValues(values);
/* 240 */     this.dbAccessor.setRowCountPerPage(rowsPerPage);
/* 241 */     return this.dbAccessor.getBatchPageData(page);
/*     */   }
/*     */ 
/*     */   public String getSequence(String tableName, int companyID)
/*     */     throws SQLException
/*     */   {
/* 254 */     return this.sequenceUtils.getSequence(tableName, companyID);
/*     */   }
/*     */ 
/*     */   public String[] getSequence(String tableName, int companyID, int iCount)
/*     */     throws SQLException
/*     */   {
/* 267 */     return this.sequenceUtils.getSequence(tableName, companyID, iCount);
/*     */   }
/*     */ 
/*     */   public void setConnection(Connection conn)
/*     */   {
/* 278 */     this.conn = conn;
/* 279 */     this.dbAccessor.setConnection(conn);
/* 280 */     this.sequenceUtils.setConnection(conn);
/*     */   }
/*     */ 
/*     */   public Connection getConnection()
/*     */   {
/* 289 */     return this.conn;
/*     */   }
/*     */ 
/*     */   private void debugSQL(String sSql, List aValues) {
/* 293 */     if ((isPrintLog()) && (log.isDebugEnabled())) {
/* 294 */       log.debug("-----------------------------------");
/* 295 */       log.debug(sSql);
/* 296 */       log.debug(listCondition(aValues));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void debugSQL(String sSql, List aValues, boolean logValues) {
/* 301 */     if ((isPrintLog()) && (log.isDebugEnabled())) {
/* 302 */       log.debug("-----------------------------------");
/* 303 */       log.debug(sSql);
/* 304 */       if (logValues)
/* 305 */         log.debug(listCondition(aValues));
/*     */     }
/*     */   }
/*     */ 
/*     */   private static String listCondition(List aValues)
/*     */   {
/* 311 */     StringBuffer sRet = new StringBuffer();
/* 312 */     if (aValues == null)
/* 313 */       aValues = new ArrayList();
/* 314 */     for (int i = 0; i < aValues.size(); i++) {
/* 315 */       Object oValue = aValues.get(i);
/* 316 */       sRet.append("[");
/* 317 */       sRet.append(oValue == null ? "" : oValue.toString());
/* 318 */       sRet.append("],");
/*     */     }
/* 320 */     return sRet.toString();
/*     */   }
/*     */ 
/*     */   public String getSQL(int key) {
/* 324 */     return SQLUtils.getSQL(key);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  36 */     String dbDebug = Config.getConfig("CONFIG", "DB-DEBUG");
/*  37 */     String dbDebugBatchValue = Config.getConfig("CONFIG", "DB-DEBUG-BATCH-VALUES");
/*  38 */     if ("false".equalsIgnoreCase(dbDebug)) {
/*  39 */       printLog = false;
/*     */     }
/*  41 */     if ("true".equalsIgnoreCase(dbDebugBatchValue))
/*  42 */       debugBatchValues = true;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.BaseDAO
 * JD-Core Version:    0.6.2
 */