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
/*     */ public class DBAccessorSQLServerImpl extends DBAccessorImpl
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
/*  48 */           this.pst.setNull(i + 1, 12);
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
/*  95 */             this.pst.setNull(j + 1, 12);
/*  96 */           } else if ((o instanceof ByteArrayOutputStream)) {
/*  97 */             this.pst.setBytes(j + 1, ((ByteArrayOutputStream)o).toByteArray());
/*  98 */           } else if ((o instanceof Date)) {
/*  99 */             Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 100 */             this.pst.setTimestamp(j + 1, timestamp);
/* 101 */           } else if ((o instanceof LongBinary)) {
/* 102 */             LongBinary s = (LongBinary)o;
/* 103 */             this.pst.setBytes(j + 1, s.getValue());
/* 104 */           } else if ((o instanceof LongString)) {
/* 105 */             LongString s = (LongString)o;
/* 106 */             this.pst.setCharacterStream(j + 1, new StringReader(s.getValue()), s.getSize());
/*     */           } else {
/* 108 */             this.pst.setObject(j + 1, o);
/*     */           }
/*     */         }
/* 111 */         this.pst.addBatch();
/*     */       }
/* 113 */       iaRet = this.pst.executeBatch();
/*     */     } catch (SQLException se) {
/* 115 */       throw se;
/*     */     } finally {
/* 117 */       closeStatement(this.pst);
/*     */     }
/* 119 */     return iaRet;
/*     */   }
/*     */ 
/*     */   public DBRowSet getPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 134 */     if (page < 1) {
/* 135 */       return getNoPageData();
/*     */     }
/*     */ 
/* 138 */     DBRowSet data = null;
/* 139 */     this.page = page;
/*     */ 
/* 141 */     int iBegin = 0;
/* 142 */     int iEnd = 0;
/* 143 */     ResultSet rs2 = null;
/* 144 */     PreparedStatement pst2 = null;
/*     */     try
/*     */     {
/* 149 */       pst2 = this.conn.prepareStatement("SELECT COUNT(*) FROM (" + this.sql + ") a");
/*     */ 
/* 151 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 152 */         Object o = this.values.get(i);
/* 153 */         if ((o != null) && ((o instanceof Date))) {
/* 154 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 155 */           pst2.setObject(i + 1, timestamp);
/*     */         } else {
/* 157 */           pst2.setObject(i + 1, o);
/*     */         }
/*     */       }
/*     */ 
/* 161 */       rs2 = pst2.executeQuery();
/* 162 */       while (rs2.next()) {
/* 163 */         this.totalRows = rs2.getInt(1);
/*     */       }
/* 165 */       closeResultSet(rs2);
/* 166 */       closeStatement(pst2);
/*     */ 
/* 170 */       if ((this.totalRows == 0) || (this.rowCountPerPage <= 0)) {
/* 171 */         this.pageCount = 0;
/* 172 */         return new DBRowSet(this.totalRows, 0, 0, 1, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, new ArrayList());
/*     */       }
/* 174 */       if (this.rowCountPerPage > 0)
/*     */       {
/* 176 */         if (this.totalRows > this.rowCountPerPage)
/* 177 */           this.pageCount = ((this.totalRows + this.rowCountPerPage - 1) / this.rowCountPerPage);
/* 178 */         else if (this.totalRows <= this.rowCountPerPage) {
/* 179 */           this.pageCount = 1;
/*     */         }
/* 181 */         if (page > this.pageCount) {
/* 182 */           page = this.pageCount;
/*     */         }
/* 184 */         iBegin = (page - 1) * this.rowCountPerPage;
/* 185 */         iEnd = iBegin + this.rowCountPerPage + 1;
/*     */       }
/*     */ 
/* 190 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 191 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 192 */         Object o = this.values.get(i);
/* 193 */         if ((o != null) && ((o instanceof Date))) {
/* 194 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 195 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 197 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 200 */       this.rs = this.pst.executeQuery();
/*     */ 
/* 204 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 207 */       this.columnCount = this.metadata.getColumnCount();
/* 208 */       getColumnNames(this.originSql);
/*     */ 
/* 210 */       this.columnTypes = new String[this.columnCount];
/* 211 */       this.columnSqlTypes = new int[this.columnCount];
/* 212 */       this.dbColumnNames = new String[this.columnCount];
/* 213 */       for (int i = 0; i < this.columnCount; i++) {
/* 214 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 215 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 216 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/*     */ 
/* 223 */       ArrayList rows = new ArrayList();
/* 224 */       ArrayList row = null;
/* 225 */       int rowCount = 0;
/*     */ 
/* 227 */       if (iBegin == 0)
/* 228 */         this.rs.beforeFirst();
/*     */       else {
/* 230 */         this.rs.absolute(iBegin);
/*     */       }
/*     */ 
/* 233 */       while ((this.rs.next()) && (this.rs.getRow() < iEnd)) {
/* 234 */         row = new ArrayList();
/* 235 */         for (int i = 1; i <= this.columnCount; i++) {
/* 236 */           row.add(this.rs.getObject(i));
/*     */         }
/* 238 */         rows.add(row);
/* 239 */         rowCount++;
/*     */       }
/* 241 */       data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/*     */     } catch (SQLException e) {
/* 243 */       throw e;
/*     */     } finally {
/*     */       try {
/* 246 */         closeResultSet(rs2);
/* 247 */         closeStatement(pst2);
/* 248 */         closeResultSet(this.rs);
/* 249 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/* 251 */         throw se;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 257 */     return data;
/*     */   }
/*     */ 
/*     */   private DBRowSet getNoPageData()
/*     */     throws SQLException
/*     */   {
/* 268 */     DBRowSet data = null;
/*     */     try
/*     */     {
/* 272 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 273 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 274 */         Object o = this.values.get(i);
/* 275 */         if ((o != null) && ((o instanceof Date))) {
/* 276 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 277 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 279 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 282 */       this.rs = this.pst.executeQuery();
/* 283 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 286 */       this.columnCount = this.metadata.getColumnCount();
/* 287 */       getColumnNames(this.originSql);
/*     */ 
/* 289 */       this.columnTypes = new String[this.columnCount];
/* 290 */       this.columnSqlTypes = new int[this.columnCount];
/* 291 */       this.dbColumnNames = new String[this.columnCount];
/* 292 */       for (int i = 0; i < this.columnCount; i++) {
/* 293 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 294 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 295 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/*     */ 
/* 299 */       ArrayList rows = new ArrayList();
/* 300 */       ArrayList row = null;
/* 301 */       int rowCount = 0;
/* 302 */       while (this.rs.next()) {
/* 303 */         row = new ArrayList();
/* 304 */         for (int i = 1; i <= this.columnCount; i++) {
/* 305 */           if (this.columnSqlTypes[(i - 1)] == -4) {
/* 306 */             byte[] bytecontent = null;
/* 307 */             InputStream is = this.rs.getBinaryStream(i);
/* 308 */             if (is != null) {
/* 309 */               byte[] buffer = new byte[512];
/*     */               try {
/* 311 */                 ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 312 */                 int length = is.read(buffer);
/* 313 */                 while (length != -1) {
/* 314 */                   out.write(buffer, 0, length);
/* 315 */                   length = is.read(buffer);
/*     */                 }
/* 317 */                 bytecontent = out.toByteArray();
/* 318 */                 out.close();
/*     */               } catch (IOException e) {
/* 320 */                 throw new SQLException("Unkown Server error.");
/*     */               }
/*     */             }
/* 323 */             row.add(new LongBinary(bytecontent));
/*     */           } else {
/* 325 */             row.add(this.rs.getObject(i));
/*     */           }
/*     */         }
/* 328 */         rows.add(row);
/* 329 */         rowCount++;
/*     */       }
/* 331 */       data = new DBRowSet(rowCount, rowCount, this.columnCount, 1, 1, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/*     */     } catch (SQLException e) {
/* 333 */       throw e;
/*     */     } finally {
/*     */       try {
/* 336 */         closeResultSet(this.rs);
/* 337 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/* 339 */         throw se;
/*     */       }
/*     */     }
/* 342 */     return data;
/*     */   }
/*     */ 
/*     */   public DBRowSet getBatchPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 356 */     this.page = page;
/*     */ 
/* 359 */     if ((this.rs == null) || (this.first)) {
/* 360 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 361 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 362 */         this.pst.setObject(i + 1, this.values.get(i));
/*     */       }
/*     */ 
/* 365 */       this.rs = this.pst.executeQuery();
/* 366 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 369 */       this.columnCount = this.metadata.getColumnCount();
/* 370 */       getColumnNames(this.originSql);
/* 371 */       this.columnTypes = new String[this.columnCount];
/* 372 */       this.columnSqlTypes = new int[this.columnCount];
/* 373 */       this.dbColumnNames = new String[this.columnCount];
/* 374 */       for (int i = 0; i < this.columnCount; i++) {
/* 375 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 376 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 377 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/* 379 */       this.first = false;
/*     */     }
/*     */ 
/* 383 */     int rowPos = (page - 1) * this.rowCountPerPage;
/* 384 */     if (rowPos == 0)
/* 385 */       this.rs.beforeFirst();
/*     */     else {
/* 387 */       this.rs.absolute(rowPos);
/*     */     }
/*     */ 
/* 391 */     ArrayList rows = new ArrayList();
/* 392 */     ArrayList row = null;
/* 393 */     int rowCount = 0;
/* 394 */     for (int i = 0; (i < this.rowCountPerPage) && (this.rs.next()); i++) {
/* 395 */       row = new ArrayList();
/* 396 */       for (int j = 1; j <= this.columnCount; j++) {
/* 397 */         row.add(this.rs.getObject(j));
/*     */       }
/* 399 */       rows.add(row);
/* 400 */       rowCount++;
/*     */     }
/* 402 */     DBRowSet data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/* 403 */     return data;
/*     */   }
/*     */ 
/*     */   private void getColumnNames(String sql)
/*     */   {
/* 408 */     sql = sql.toUpperCase();
/* 409 */     int iSelectIdx = sql.indexOf("SELECT");
/* 410 */     int iFromIdx = sql.indexOf("FROM");
/* 411 */     String sCols = sql.substring(iSelectIdx + 6, iFromIdx);
/* 412 */     String sColName = "";
/* 413 */     int iAliasIdx = 0;
/* 414 */     StringTokenizer tokenizer = new StringTokenizer(sCols, ",");
/* 415 */     ArrayList al = new ArrayList();
/* 416 */     while (tokenizer.hasMoreTokens()) {
/* 417 */       sColName = tokenizer.nextToken().trim();
/*     */ 
/* 419 */       iAliasIdx = sColName.lastIndexOf(" AS ");
/* 420 */       if (iAliasIdx == -1) {
/* 421 */         iAliasIdx = sColName.lastIndexOf(" ");
/* 422 */         if (iAliasIdx != -1)
/* 423 */           sColName = sColName.substring(iAliasIdx + 1).trim();
/*     */       }
/*     */       else {
/* 426 */         sColName = sColName.substring(iAliasIdx + 4).trim();
/*     */       }
/* 428 */       al.add(sColName);
/*     */     }
/* 430 */     while (al.size() < this.columnCount) {
/* 431 */       al.add("");
/*     */     }
/* 433 */     this.columnNames = ((String[])al.toArray(new String[this.columnCount]));
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessorSQLServerImpl
 * JD-Core Version:    0.6.2
 */