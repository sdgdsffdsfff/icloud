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
/*     */ public class DBAccessorDB2Impl extends DBAccessorImpl
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
/*  52 */         } else if ((o instanceof LongString)) {
/*  53 */           LongString longString = (LongString)o;
/*  54 */           this.pst.setCharacterStream(i + 1, new StringReader(longString.getValue()), longString.getSize());
/*  55 */         } else if ((o instanceof LongBinary)) {
/*  56 */           LongBinary longBinary = (LongBinary)o;
/*  57 */           this.pst.setBytes(i + 1, longBinary.getValue());
/*  58 */         } else if ((o instanceof Date)) {
/*  59 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/*  60 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/*  62 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*  67 */       iRet = this.pst.executeUpdate();
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/*  77 */       SQLException e = se.getNextException();
/*  78 */       if (e != null)
/*  79 */         e.printStackTrace();
/*     */       else {
/*  81 */         se.printStackTrace();
/*     */       }
/*  83 */       throw se;
/*     */     } finally {
/*  85 */       closeStatement(this.pst);
/*     */     }
/*  87 */     return iRet;
/*     */   }
/*     */ 
/*     */   public int[] executeBatchUpdate()
/*     */     throws SQLException
/*     */   {
/*     */     int[] iaRet;
/*     */     try
/*     */     {
/* 103 */       this.pst = this.conn.prepareStatement(this.sql);
/* 104 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 105 */         List lRow = (List)this.values.get(i);
/* 106 */         for (int j = 0; (lRow != null) && (j < lRow.size()); j++) {
/* 107 */           Object o = lRow.get(j);
/* 108 */           if ((o == null) || (o.toString().equals(""))) {
/* 109 */             this.pst.setNull(j + 1, 2);
/* 110 */           } else if ((o instanceof ByteArrayOutputStream)) {
/* 111 */             this.pst.setBytes(j + 1, ((ByteArrayOutputStream)o).toByteArray());
/* 112 */           } else if ((o instanceof Date)) {
/* 113 */             Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 114 */             this.pst.setTimestamp(j + 1, timestamp);
/* 115 */           } else if ((o instanceof LongString)) {
/* 116 */             LongString longString = (LongString)o;
/* 117 */             this.pst.setCharacterStream(j + 1, new StringReader(longString.getValue()), longString.getSize());
/* 118 */           } else if ((o instanceof LongBinary)) {
/* 119 */             LongBinary longBinary = (LongBinary)o;
/* 120 */             this.pst.setBytes(j + 1, longBinary.getValue());
/*     */           } else {
/* 122 */             this.pst.setObject(j + 1, o);
/*     */           }
/*     */         }
/* 125 */         this.pst.addBatch();
/*     */       }
/* 127 */       iaRet = this.pst.executeBatch();
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/* 137 */       SQLException e = se.getNextException();
/* 138 */       if (e != null)
/* 139 */         e.printStackTrace();
/*     */       else {
/* 141 */         se.printStackTrace();
/*     */       }
/* 143 */       throw se;
/*     */     } finally {
/* 145 */       closeStatement(this.pst);
/*     */     }
/* 147 */     return iaRet;
/*     */   }
/*     */ 
/*     */   public DBRowSet getPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 161 */     if (page < 1) {
/* 162 */       return getNoPageData();
/*     */     }
/*     */ 
/* 165 */     DBRowSet data = null;
/* 166 */     this.page = page;
/*     */ 
/* 168 */     int iBegin = 0;
/* 169 */     int iEnd = 0;
/* 170 */     ResultSet rs2 = null;
/* 171 */     PreparedStatement pst2 = null;
/*     */     try
/*     */     {
/* 176 */       pst2 = this.conn.prepareStatement("SELECT COUNT(*) DB_COUNT FROM (" + this.sql + ") a");
/* 177 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 178 */         Object o = this.values.get(i);
/* 179 */         if ((o != null) && ((o instanceof Date))) {
/* 180 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 181 */           pst2.setObject(i + 1, timestamp);
/*     */         } else {
/* 183 */           pst2.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 186 */       rs2 = pst2.executeQuery();
/* 187 */       while (rs2.next()) {
/* 188 */         this.totalRows = rs2.getInt(1);
/*     */       }
/* 190 */       closeResultSet(rs2);
/* 191 */       closeStatement(pst2);
/*     */ 
/* 195 */       if ((this.totalRows == 0) || (this.rowCountPerPage <= 0)) {
/* 196 */         this.pageCount = 0;
/* 197 */         return new DBRowSet(this.totalRows, 0, 0, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, new ArrayList());
/*     */       }
/*     */ 
/* 200 */       if (this.rowCountPerPage > 0)
/*     */       {
/* 202 */         if (this.totalRows > this.rowCountPerPage) {
/* 203 */           this.pageCount = ((this.totalRows + this.rowCountPerPage - 1) / this.rowCountPerPage);
/* 204 */           if (page < 1) {
/* 205 */             page = 1;
/*     */           }
/* 207 */           if (page > this.pageCount) {
/* 208 */             page = this.pageCount;
/*     */           }
/* 210 */           iBegin = (page - 1) * this.rowCountPerPage + 1;
/* 211 */           iEnd = iBegin + this.rowCountPerPage;
/*     */ 
/* 213 */           this.sql = ("SELECT * FROM (SELECT originTable.*, ROW_NUMBER() OVER() AS rn FROM (" + this.sql + ")" + " originTable) WHERE rn >=" + iBegin);
/*     */ 
/* 215 */           if (iEnd <= this.totalRows)
/* 216 */             this.sql = (this.sql + " and rn <" + iEnd);
/*     */         }
/*     */         else {
/* 219 */           this.pageCount = 1;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 224 */       this.pst = this.conn.prepareStatement(this.sql);
/* 225 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 226 */         Object o = this.values.get(i);
/* 227 */         if ((o != null) && ((o instanceof Date))) {
/* 228 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 229 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 231 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 234 */       this.rs = this.pst.executeQuery();
/*     */ 
/* 238 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 241 */       this.columnCount = this.metadata.getColumnCount();
/* 242 */       getColumnNames(this.originSql);
/*     */ 
/* 244 */       this.columnTypes = new String[this.columnCount];
/* 245 */       this.columnSqlTypes = new int[this.columnCount];
/* 246 */       this.dbColumnNames = new String[this.columnCount];
/* 247 */       this.columnPrecisions = new int[this.columnCount];
/* 248 */       this.columnScales = new int[this.columnCount];
/* 249 */       for (int i = 0; i < this.columnCount; i++) {
/* 250 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 251 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 252 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/* 253 */         this.columnPrecisions[i] = this.metadata.getPrecision(i + 1);
/* 254 */         this.columnScales[i] = this.metadata.getScale(i + 1);
/*     */       }
/*     */ 
/* 261 */       ArrayList rows = new ArrayList();
/* 262 */       ArrayList row = null;
/* 263 */       int rowCount = 0;
/*     */ 
/* 265 */       while (this.rs.next()) {
/* 266 */         row = new ArrayList();
/* 267 */         for (int i = 1; i <= this.columnCount; i++) {
/* 268 */           Object o = this.rs.getObject(i);
/* 269 */           if ((this.columnSqlTypes[(i - 1)] == 2005) || (this.columnSqlTypes[(i - 1)] == -1)) {
/* 270 */             row.add(this.rs.getString(i));
/* 271 */           } else if ((this.columnSqlTypes[(i - 1)] == 2004) || (this.columnSqlTypes[(i - 1)] == -4)) {
/* 272 */             byte[] bytecontent = null;
/* 273 */             InputStream is = this.rs.getBinaryStream(i);
/* 274 */             if (is != null) {
/* 275 */               byte[] buffer = new byte[512];
/*     */               try {
/* 277 */                 ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 278 */                 int length = is.read(buffer);
/* 279 */                 while (length != -1) {
/* 280 */                   out.write(buffer, 0, length);
/* 281 */                   length = is.read(buffer);
/*     */                 }
/* 283 */                 bytecontent = out.toByteArray();
/* 284 */                 out.close();
/*     */               } catch (IOException e) {
/* 286 */                 throw new SQLException("Unkown Server error.");
/*     */               }
/*     */             }
/* 289 */             row.add(new LongBinary(bytecontent));
/*     */           } else {
/* 291 */             row.add(o);
/*     */           }
/*     */         }
/* 294 */         rows.add(row);
/* 295 */         rowCount++;
/*     */       }
/* 297 */       data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows, this.columnPrecisions, this.columnScales);
/*     */     }
/*     */     catch (SQLException e)
/*     */     {
/* 307 */       e.printStackTrace();
/* 308 */       throw e;
/*     */     } finally {
/*     */       try {
/* 311 */         closeResultSet(rs2);
/* 312 */         closeStatement(pst2);
/* 313 */         closeResultSet(this.rs);
/* 314 */         closeStatement(this.pst);
/*     */       }
/*     */       catch (SQLException se)
/*     */       {
/* 324 */         se.printStackTrace();
/* 325 */         throw se;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 331 */     return data;
/*     */   }
/*     */ 
/*     */   private DBRowSet getNoPageData()
/*     */     throws SQLException
/*     */   {
/* 342 */     DBRowSet data = null;
/*     */     try
/*     */     {
/* 346 */       this.pst = this.conn.prepareStatement(this.sql);
/* 347 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 348 */         Object o = this.values.get(i);
/* 349 */         if ((o != null) && ((o instanceof Date))) {
/* 350 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 351 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 353 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 356 */       this.rs = this.pst.executeQuery();
/* 357 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 360 */       this.columnCount = this.metadata.getColumnCount();
/* 361 */       getColumnNames(this.originSql);
/*     */ 
/* 363 */       this.columnTypes = new String[this.columnCount];
/* 364 */       this.columnSqlTypes = new int[this.columnCount];
/* 365 */       this.dbColumnNames = new String[this.columnCount];
/* 366 */       this.columnPrecisions = new int[this.columnCount];
/* 367 */       this.columnScales = new int[this.columnCount];
/* 368 */       for (int i = 0; i < this.columnCount; i++) {
/* 369 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 370 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 371 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/* 372 */         this.columnPrecisions[i] = this.metadata.getPrecision(i + 1);
/* 373 */         this.columnScales[i] = this.metadata.getScale(i + 1);
/*     */       }
/*     */ 
/* 377 */       ArrayList rows = new ArrayList();
/* 378 */       ArrayList row = null;
/* 379 */       int rowCount = 0;
/* 380 */       while (this.rs.next()) {
/* 381 */         row = new ArrayList();
/* 382 */         for (int i = 1; i <= this.columnCount; i++) {
/* 383 */           Object o = this.rs.getObject(i);
/* 384 */           if ((this.columnSqlTypes[(i - 1)] == 2005) || (this.columnSqlTypes[(i - 1)] == -1)) {
/* 385 */             row.add(this.rs.getString(i));
/* 386 */           } else if ((this.columnSqlTypes[(i - 1)] == 2004) || (this.columnSqlTypes[(i - 1)] == -4)) {
/* 387 */             byte[] bytecontent = null;
/* 388 */             InputStream is = this.rs.getBinaryStream(i);
/* 389 */             if (is != null) {
/* 390 */               byte[] buffer = new byte[512];
/*     */               try {
/* 392 */                 ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 393 */                 int length = is.read(buffer);
/* 394 */                 while (length != -1) {
/* 395 */                   out.write(buffer, 0, length);
/* 396 */                   length = is.read(buffer);
/*     */                 }
/* 398 */                 bytecontent = out.toByteArray();
/* 399 */                 out.close();
/*     */               } catch (IOException e) {
/* 401 */                 throw new SQLException("Unkown Server error.");
/*     */               }
/*     */             }
/* 404 */             row.add(new LongBinary(bytecontent));
/*     */           } else {
/* 406 */             row.add(o);
/*     */           }
/*     */         }
/* 409 */         rows.add(row);
/* 410 */         rowCount++;
/*     */       }
/* 412 */       data = new DBRowSet(rowCount, rowCount, this.columnCount, 1, 1, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows, this.columnPrecisions, this.columnScales);
/*     */     }
/*     */     catch (SQLException se)
/*     */     {
/* 421 */       SQLException e = se.getNextException();
/* 422 */       if (e != null)
/* 423 */         e.printStackTrace();
/*     */       else {
/* 425 */         se.printStackTrace();
/*     */       }
/* 427 */       throw se;
/*     */     } finally {
/*     */       try {
/* 430 */         closeResultSet(this.rs);
/* 431 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/* 433 */         throw se;
/*     */       }
/*     */     }
/* 436 */     return data;
/*     */   }
/*     */ 
/*     */   public DBRowSet getBatchPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 449 */     this.page = page;
/*     */ 
/* 452 */     if ((this.rs == null) || (this.first)) {
/* 453 */       this.pst = this.conn.prepareStatement(this.sql);
/* 454 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 455 */         Object o = this.values.get(i);
/* 456 */         if ((o != null) && ((o instanceof Date))) {
/* 457 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 458 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 460 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/*     */ 
/* 464 */       this.rs = this.pst.executeQuery();
/* 465 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 468 */       this.columnCount = this.metadata.getColumnCount();
/* 469 */       getColumnNames(this.originSql);
/* 470 */       this.columnTypes = new String[this.columnCount];
/* 471 */       this.columnSqlTypes = new int[this.columnCount];
/* 472 */       this.dbColumnNames = new String[this.columnCount];
/* 473 */       this.columnPrecisions = new int[this.columnCount];
/* 474 */       this.columnScales = new int[this.columnCount];
/* 475 */       for (int i = 0; i < this.columnCount; i++) {
/* 476 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 477 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 478 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/* 479 */         this.columnPrecisions[i] = this.metadata.getPrecision(i + 1);
/* 480 */         this.columnScales[i] = this.metadata.getScale(i + 1);
/*     */       }
/* 482 */       this.first = false;
/*     */     }
/*     */ 
/* 486 */     int rowPos = (page - 1) * this.rowCountPerPage;
/* 487 */     if (rowPos == 0)
/* 488 */       this.rs.beforeFirst();
/*     */     else {
/* 490 */       this.rs.absolute(rowPos);
/*     */     }
/*     */ 
/* 494 */     ArrayList rows = new ArrayList();
/* 495 */     ArrayList row = null;
/* 496 */     int rowCount = 0;
/* 497 */     for (int i = 0; (i < this.rowCountPerPage) && (this.rs.next()); i++) {
/* 498 */       row = new ArrayList();
/* 499 */       for (int j = 1; j <= this.columnCount; j++) {
/* 500 */         row.add(this.rs.getObject(j));
/*     */       }
/* 502 */       rows.add(row);
/* 503 */       rowCount++;
/*     */     }
/* 505 */     DBRowSet data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows, this.columnPrecisions, this.columnScales);
/* 506 */     return data;
/*     */   }
/*     */ 
/*     */   private void getColumnNames(String sql)
/*     */   {
/* 511 */     sql = sql.toUpperCase();
/* 512 */     int iSelectIdx = sql.indexOf("SELECT");
/* 513 */     int iFromIdx = sql.indexOf("FROM");
/* 514 */     String sCols = sql.substring(iSelectIdx + 6, iFromIdx);
/* 515 */     String sColName = "";
/* 516 */     int iAliasIdx = 0;
/* 517 */     StringTokenizer tokenizer = new StringTokenizer(sCols, ",");
/* 518 */     ArrayList al = new ArrayList();
/* 519 */     while (tokenizer.hasMoreTokens()) {
/* 520 */       sColName = tokenizer.nextToken().trim();
/*     */ 
/* 522 */       iAliasIdx = sColName.lastIndexOf(" AS ");
/* 523 */       if (iAliasIdx == -1) {
/* 524 */         iAliasIdx = sColName.lastIndexOf(" ");
/* 525 */         if (iAliasIdx != -1)
/* 526 */           sColName = sColName.substring(iAliasIdx + 1).trim();
/*     */       }
/*     */       else {
/* 529 */         sColName = sColName.substring(iAliasIdx + 4).trim();
/*     */       }
/* 531 */       al.add(sColName);
/*     */     }
/* 533 */     while (al.size() < this.columnCount) {
/* 534 */       al.add("");
/*     */     }
/* 536 */     this.columnNames = ((String[])al.toArray(new String[this.columnCount]));
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessorDB2Impl
 * JD-Core Version:    0.6.2
 */