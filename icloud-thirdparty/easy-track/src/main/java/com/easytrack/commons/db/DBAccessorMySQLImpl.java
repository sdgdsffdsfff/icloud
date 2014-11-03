/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.StringReader;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class DBAccessorMySQLImpl extends DBAccessorImpl
/*     */ {
/*     */   public int executeUpdate()
/*     */     throws SQLException
/*     */   {
/*  42 */     int iRet = 0;
/*     */     try {
/*  44 */       this.pst = this.conn.prepareStatement(this.sql);
/*  45 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/*  46 */         Object o = this.values.get(i);
/*  47 */         if ((o == null) || (o.toString().equals(""))) {
/*  48 */           this.pst.setNull(i + 1, 2);
/*     */         }
/*  50 */         else if ((o instanceof ByteArrayOutputStream)) {
/*  51 */           this.pst.setBytes(i + 1, ((ByteArrayOutputStream)o).toByteArray());
/*  52 */         } else if ((o instanceof LongBinary)) {
/*  53 */           LongBinary s = (LongBinary)o;
/*  54 */           this.pst.setBytes(i + 1, s.getValue());
/*  55 */         } else if ((o instanceof LongString)) {
/*  56 */           LongString s = (LongString)o;
/*  57 */           this.pst.setCharacterStream(i + 1, new StringReader(s.getValue()), s.getSize());
/*  58 */         } else if ((o instanceof Date)) {
/*  59 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/*  60 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/*  62 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/*     */ 
/*  66 */       iRet = this.pst.executeUpdate();
/*     */     } catch (SQLException se) {
/*  68 */       throw se;
/*     */     } finally {
/*  70 */       closeStatement(this.pst);
/*     */     }
/*  72 */     return iRet;
/*     */   }
/*     */ 
/*     */   public int[] executeBatchUpdate()
/*     */     throws SQLException
/*     */   {
/*     */     int[] iaRet;
/*     */     try
/*     */     {
/*  89 */       this.pst = this.conn.prepareStatement(this.sql);
/*  90 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/*  91 */         List lRow = (List)this.values.get(i);
/*  92 */         for (int j = 0; (lRow != null) && (j < lRow.size()); j++) {
/*  93 */           Object o = lRow.get(j);
/*  94 */           if ((o == null) || (o.toString().equals(""))) {
/*  95 */             this.pst.setNull(j + 1, 2);
/*  96 */           } else if ((o instanceof ByteArrayOutputStream)) {
/*  97 */             this.pst.setBytes(j + 1, ((ByteArrayOutputStream)o).toByteArray());
/*  98 */           } else if ((o instanceof Date)) {
/*  99 */             Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 100 */             this.pst.setTimestamp(j + 1, timestamp);
/*     */           } else {
/* 102 */             this.pst.setObject(j + 1, o);
/*     */           }
/*     */         }
/* 105 */         this.pst.addBatch();
/*     */       }
/* 107 */       iaRet = this.pst.executeBatch();
/*     */     } catch (SQLException se) {
/* 109 */       throw se;
/*     */     } finally {
/* 111 */       closeStatement(this.pst);
/*     */     }
/* 113 */     return iaRet;
/*     */   }
/*     */ 
/*     */   public DBRowSet getPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 128 */     if (page < 1) {
/* 129 */       return getNoPageData();
/*     */     }
/*     */ 
/* 132 */     DBRowSet data = null;
/* 133 */     this.page = page;
/*     */ 
/* 135 */     int iBegin = 0;
/* 136 */     int iEnd = 0;
/* 137 */     ResultSet rs2 = null;
/* 138 */     PreparedStatement pst2 = null;
/*     */     try
/*     */     {
/* 143 */       pst2 = this.conn.prepareStatement("SELECT COUNT(*) FROM (" + this.sql + ") a");
/* 144 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 145 */         pst2.setObject(i + 1, this.values.get(i));
/*     */       }
/* 147 */       rs2 = pst2.executeQuery();
/* 148 */       while (rs2.next()) {
/* 149 */         this.totalRows = rs2.getInt(1);
/*     */       }
/* 151 */       closeResultSet(rs2);
/* 152 */       closeStatement(pst2);
/*     */ 
/* 156 */       if ((this.totalRows == 0) || (this.rowCountPerPage <= 0)) {
/* 157 */         this.pageCount = 0;
/* 158 */         return new DBRowSet(this.totalRows, 0, 0, 1, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, new ArrayList());
/*     */       }
/* 160 */       if (this.rowCountPerPage > 0)
/*     */       {
/* 162 */         if (this.totalRows > this.rowCountPerPage)
/* 163 */           this.pageCount = ((this.totalRows + this.rowCountPerPage - 1) / this.rowCountPerPage);
/* 164 */         else if (this.totalRows <= this.rowCountPerPage) {
/* 165 */           this.pageCount = 1;
/*     */         }
/* 167 */         if (page > this.pageCount) {
/* 168 */           page = this.pageCount;
/*     */         }
/* 170 */         iBegin = (page - 1) * this.rowCountPerPage;
/* 171 */         iEnd = iBegin + this.rowCountPerPage + 1;
/*     */       }
/*     */ 
/* 176 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 177 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 178 */         this.pst.setObject(i + 1, this.values.get(i));
/*     */       }
/* 180 */       this.rs = this.pst.executeQuery();
/*     */ 
/* 184 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 187 */       this.columnCount = this.metadata.getColumnCount();
/* 188 */       getColumnNames(this.originSql);
/*     */ 
/* 190 */       this.columnTypes = new String[this.columnCount];
/* 191 */       this.columnSqlTypes = new int[this.columnCount];
/* 192 */       this.dbColumnNames = new String[this.columnCount];
/* 193 */       for (int i = 0; i < this.columnCount; i++) {
/* 194 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 195 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 196 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/*     */ 
/* 203 */       ArrayList rows = new ArrayList();
/* 204 */       ArrayList row = null;
/* 205 */       int rowCount = 0;
/*     */ 
/* 207 */       if (iBegin == 0)
/* 208 */         this.rs.beforeFirst();
/*     */       else {
/* 210 */         this.rs.absolute(iBegin);
/*     */       }
/*     */ 
/* 213 */       while ((this.rs.next()) && (this.rs.getRow() < iEnd)) {
/* 214 */         row = new ArrayList();
/* 215 */         for (int i = 1; i <= this.columnCount; i++) {
/* 216 */           row.add(this.rs.getObject(i));
/*     */         }
/* 218 */         rows.add(row);
/* 219 */         rowCount++;
/*     */       }
/* 221 */       data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/*     */     } catch (SQLException e) {
/* 223 */       throw e;
/*     */     } finally {
/*     */       try {
/* 226 */         closeResultSet(rs2);
/* 227 */         closeStatement(pst2);
/* 228 */         closeResultSet(this.rs);
/* 229 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/* 231 */         throw se;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 237 */     return data;
/*     */   }
/*     */ 
/*     */   private DBRowSet getNoPageData()
/*     */     throws SQLException
/*     */   {
/* 248 */     DBRowSet data = null;
/*     */     try
/*     */     {
/* 252 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/*     */ 
/* 254 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 255 */         this.pst.setObject(i + 1, this.values.get(i));
/*     */       }
/* 257 */       this.rs = this.pst.executeQuery();
/* 258 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 261 */       this.columnCount = this.metadata.getColumnCount();
/* 262 */       getColumnNames(this.originSql);
/*     */ 
/* 264 */       this.columnTypes = new String[this.columnCount];
/* 265 */       this.columnSqlTypes = new int[this.columnCount];
/* 266 */       this.dbColumnNames = new String[this.columnCount];
/* 267 */       for (int i = 0; i < this.columnCount; i++) {
/* 268 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 269 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 270 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/*     */ 
/* 274 */       ArrayList rows = new ArrayList();
/* 275 */       ArrayList row = null;
/* 276 */       int rowCount = 0;
/* 277 */       while (this.rs.next()) {
/* 278 */         row = new ArrayList();
/* 279 */         for (int i = 1; i <= this.columnCount; i++) {
/* 280 */           if (this.columnSqlTypes[(i - 1)] == -4) {
/* 281 */             byte[] bytecontent = null;
/* 282 */             InputStream is = this.rs.getBinaryStream(i);
/* 283 */             if (is != null) {
/* 284 */               byte[] buffer = new byte[512];
/*     */               try {
/* 286 */                 ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 287 */                 int length = is.read(buffer);
/* 288 */                 while (length != -1) {
/* 289 */                   out.write(buffer, 0, length);
/* 290 */                   length = is.read(buffer);
/*     */                 }
/* 292 */                 bytecontent = out.toByteArray();
/* 293 */                 out.close();
/*     */               } catch (IOException e) {
/* 295 */                 throw new SQLException("Unkown Server error.");
/*     */               }
/*     */             }
/* 298 */             row.add(new LongBinary(bytecontent));
/*     */           } else {
/* 300 */             row.add(this.rs.getObject(i));
/*     */           }
/*     */         }
/* 303 */         rows.add(row);
/* 304 */         rowCount++;
/*     */       }
/* 306 */       data = new DBRowSet(rowCount, rowCount, this.columnCount, 1, 1, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/*     */     } catch (SQLException e) {
/* 308 */       throw e;
/*     */     } finally {
/*     */       try {
/* 311 */         closeResultSet(this.rs);
/* 312 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/* 314 */         throw se;
/*     */       }
/*     */     }
/* 317 */     return data;
/*     */   }
/*     */ 
/*     */   public DBRowSet getBatchPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 331 */     this.page = page;
/*     */ 
/* 334 */     if ((this.rs == null) || (this.first)) {
/* 335 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 336 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 337 */         this.pst.setObject(i + 1, this.values.get(i));
/*     */       }
/*     */ 
/* 340 */       this.rs = this.pst.executeQuery();
/* 341 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 344 */       this.columnCount = this.metadata.getColumnCount();
/* 345 */       getColumnNames(this.originSql);
/* 346 */       this.columnTypes = new String[this.columnCount];
/* 347 */       this.columnSqlTypes = new int[this.columnCount];
/* 348 */       this.dbColumnNames = new String[this.columnCount];
/* 349 */       for (int i = 0; i < this.columnCount; i++) {
/* 350 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 351 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 352 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/* 354 */       this.first = false;
/*     */     }
/*     */ 
/* 358 */     int rowPos = (page - 1) * this.rowCountPerPage;
/* 359 */     if (rowPos == 0)
/* 360 */       this.rs.beforeFirst();
/*     */     else {
/* 362 */       this.rs.absolute(rowPos);
/*     */     }
/*     */ 
/* 366 */     ArrayList rows = new ArrayList();
/* 367 */     ArrayList row = null;
/* 368 */     int rowCount = 0;
/* 369 */     for (int i = 0; (i < this.rowCountPerPage) && (this.rs.next()); i++) {
/* 370 */       row = new ArrayList();
/* 371 */       for (int j = 1; j <= this.columnCount; j++) {
/* 372 */         row.add(this.rs.getObject(j));
/*     */       }
/* 374 */       rows.add(row);
/* 375 */       rowCount++;
/*     */     }
/* 377 */     DBRowSet data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/* 378 */     return data;
/*     */   }
/*     */ 
/*     */   private void getColumnNames(String sql)
/*     */   {
/* 383 */     sql = sql.toUpperCase();
/* 384 */     int iSelectIdx = sql.indexOf("SELECT");
/* 385 */     int iFromIdx = sql.indexOf("FROM");
/* 386 */     String sCols = sql.substring(iSelectIdx + 6, iFromIdx);
/* 387 */     String sColName = "";
/* 388 */     int iAliasIdx = 0;
/* 389 */     StringTokenizer tokenizer = new StringTokenizer(sCols, ",");
/* 390 */     ArrayList al = new ArrayList();
/* 391 */     while (tokenizer.hasMoreTokens()) {
/* 392 */       sColName = tokenizer.nextToken().trim();
/*     */ 
/* 394 */       iAliasIdx = sColName.lastIndexOf(" AS ");
/* 395 */       if (iAliasIdx == -1) {
/* 396 */         iAliasIdx = sColName.lastIndexOf(" ");
/* 397 */         if (iAliasIdx != -1)
/* 398 */           sColName = sColName.substring(iAliasIdx + 1).trim();
/*     */       }
/*     */       else {
/* 401 */         sColName = sColName.substring(iAliasIdx + 4).trim();
/*     */       }
/* 403 */       al.add(sColName);
/*     */     }
/* 405 */     while (al.size() < this.columnCount) {
/* 406 */       al.add("");
/*     */     }
/* 408 */     this.columnNames = ((String[])al.toArray(new String[this.columnCount]));
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessorMySQLImpl
 * JD-Core Version:    0.6.2
 */