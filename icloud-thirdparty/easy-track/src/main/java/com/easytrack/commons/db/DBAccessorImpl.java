/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Statement;
/*     */ import java.util.List;
/*     */ 
/*     */ public abstract class DBAccessorImpl
/*     */   implements DBAccessor
/*     */ {
/*     */   protected Connection conn;
/*  38 */   protected String sql = null;
/*  39 */   protected String originSql = null;
/*     */ 
/*  43 */   protected PreparedStatement pst = null;
/*     */ 
/*  47 */   protected ResultSet rs = null;
/*  48 */   protected ResultSetMetaData metadata = null;
/*     */ 
/*  52 */   protected String[] columnNames = new String[0];
/*     */ 
/*  56 */   protected String[] dbColumnNames = new String[0];
/*     */ 
/*  60 */   protected String[] columnTypes = new String[0];
/*     */ 
/*  64 */   protected int[] columnSqlTypes = new int[0];
/*     */   protected int[] columnPrecisions;
/*     */   protected int[] columnScales;
/*  76 */   protected List values = null;
/*     */ 
/*  80 */   protected int totalRows = 0;
/*     */ 
/*  84 */   protected int page = -1;
/*     */ 
/*  88 */   protected int pageCount = -1;
/*     */ 
/*  92 */   protected int rowCountPerPage = -1;
/*     */ 
/*  96 */   protected int pageType = 0;
/*     */ 
/* 100 */   protected int columnCount = 0;
/*     */ 
/* 104 */   protected boolean first = true;
/*     */ 
/*     */   public void setSql(String sql)
/*     */   {
/* 111 */     this.sql = sql;
/* 112 */     this.originSql = sql;
/*     */   }
/*     */ 
/*     */   public void setUpdateValues(List values)
/*     */   {
/* 120 */     this.values = values;
/*     */   }
/*     */ 
/*     */   public void setConditionValues(List values)
/*     */   {
/* 129 */     this.values = values;
/*     */   }
/*     */ 
/*     */   public abstract int executeUpdate()
/*     */     throws SQLException;
/*     */ 
/*     */   public abstract int[] executeBatchUpdate()
/*     */     throws SQLException;
/*     */ 
/*     */   public void setRowCountPerPage(int rowCount)
/*     */   {
/* 159 */     this.rowCountPerPage = rowCount;
/*     */   }
/*     */ 
/*     */   public abstract DBRowSet getPageData(int paramInt)
/*     */     throws SQLException;
/*     */ 
/*     */   public abstract DBRowSet getBatchPageData(int paramInt)
/*     */     throws SQLException;
/*     */ 
/*     */   public synchronized String getSequence(String tableName, int companyID)
/*     */     throws SQLException
/*     */   {
/* 187 */     CallableStatement cst = null;
/*     */     try {
/* 189 */       cst = this.conn.prepareCall("{call returnSeqs2(?, ?, ?, ?, ?, ?)}");
/* 190 */       cst.setString(1, tableName);
/* 191 */       cst.setInt(2, companyID);
/* 192 */       cst.setInt(3, 1);
/* 193 */       cst.registerOutParameter(4, 2);
/* 194 */       cst.registerOutParameter(5, 2);
/* 195 */       cst.registerOutParameter(6, 2);
/* 196 */       cst.execute();
/* 197 */       return "" + cst.getBigDecimal(4).intValue();
/*     */     } catch (SQLException se) {
/* 199 */       throw se;
/*     */     } finally {
/* 201 */       closeStatement(cst);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized String[] getSequence(String tableName, int companyID, int iCount)
/*     */     throws SQLException
/*     */   {
/* 214 */     CallableStatement cst = null;
/*     */     try
/*     */     {
/*     */       int beginSeq;
/* 217 */       if (iCount > 1) {
/* 218 */         cst = this.conn.prepareCall("{call returnSeqs2(?, ?, ?, ?, ?, ?)}");
/* 219 */         cst.setString(1, tableName);
/* 220 */         cst.setInt(2, companyID);
/* 221 */         cst.setInt(3, iCount);
/* 222 */         cst.registerOutParameter(4, 2);
/* 223 */         cst.registerOutParameter(5, 2);
/* 224 */         cst.registerOutParameter(6, 2);
/* 225 */         cst.execute();
/*     */ 
/* 228 */         beginSeq = cst.getBigDecimal(4).intValue();
/* 229 */         int endSeq = cst.getBigDecimal(5).intValue();
/* 230 */         int incrBy = cst.getBigDecimal(6).intValue();
/*     */ 
/* 232 */         if (endSeq - beginSeq != (iCount - 1) * incrBy) {
/* 233 */           throw new SQLException("Get Sequence  for " + tableName + " encount some error.");
/*     */         }
/*     */ 
/* 236 */         String[] sSeq = new String[iCount];
/*     */ 
/* 238 */         for (int ind = 0; ind < iCount; ind++) {
/* 239 */           sSeq[ind] = String.valueOf(beginSeq + ind * incrBy);
/*     */         }
/* 241 */         return sSeq;
/* 242 */       }if (iCount == 1) {
/* 243 */         return new String[] { getSequence(tableName, companyID) };
/*     */       }
/* 245 */       return new String[0];
/*     */     }
/*     */     catch (SQLException se) {
/* 248 */       throw se;
/*     */     } finally {
/* 250 */       closeStatement(cst);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Connection getConnection()
/*     */   {
/* 259 */     return this.conn;
/*     */   }
/*     */ 
/*     */   public void setConnection(Connection conn)
/*     */   {
/* 267 */     this.conn = conn;
/*     */   }
/*     */ 
/*     */   protected void closeResultSet(ResultSet result)
/*     */     throws SQLException
/*     */   {
/* 276 */     if (result != null)
/*     */       try {
/* 278 */         result.close();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */   }
/*     */ 
/*     */   protected void closeStatement(Statement st)
/*     */     throws SQLException
/*     */   {
/* 290 */     if (st != null)
/*     */       try {
/* 292 */         st.close();
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*     */       }
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessorImpl
 * JD-Core Version:    0.6.2
 */