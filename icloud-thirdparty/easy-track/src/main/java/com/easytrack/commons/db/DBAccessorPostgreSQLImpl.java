/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import com.easytrack.commons.util.TypeUtils;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.StringReader;
/*     */ import java.sql.Connection;
/*     */ import java.sql.ParameterMetaData;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.jboss.resource.adapter.jdbc.WrappedConnection;
/*     */ import org.postgresql.copy.CopyManager;
/*     */ import org.postgresql.jdbc2.AbstractJdbc2Connection;
/*     */ 
/*     */ public class DBAccessorPostgreSQLImpl extends DBAccessorImpl
/*     */ {
/*     */   public int executeUpdate()
/*     */     throws SQLException
/*     */   {
/*  48 */     int iRet = 0;
/*     */     try {
/*  50 */       this.pst = this.conn.prepareStatement(this.sql);
/*  51 */       ParameterMetaData params = this.pst.getParameterMetaData();
/*  52 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/*  53 */         Object o = this.values.get(i);
/*  54 */         if ((o == null) || (o.toString().equals(""))) {
/*  55 */           this.pst.setNull(i + 1, params.getParameterType(i + 1));
/*     */         }
/*  57 */         else if ((o instanceof ByteArrayOutputStream)) {
/*  58 */           this.pst.setBytes(i + 1, ((ByteArrayOutputStream)o).toByteArray());
/*  59 */         } else if ((o instanceof LongBinary)) {
/*  60 */           LongBinary s = (LongBinary)o;
/*  61 */           this.pst.setBytes(i + 1, s.getValue());
/*  62 */         } else if ((o instanceof LongString)) {
/*  63 */           LongString s = (LongString)o;
/*  64 */           this.pst.setCharacterStream(i + 1, new StringReader(s.getValue()), s.getSize());
/*  65 */         } else if ((o instanceof Date)) {
/*  66 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/*  67 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/*  69 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/*     */ 
/*  73 */       iRet = this.pst.executeUpdate();
/*     */     } catch (SQLException se) {
/*  75 */       throw se;
/*     */     } finally {
/*  77 */       closeStatement(this.pst);
/*     */     }
/*  79 */     return iRet;
/*     */   }
/*     */ 
/*     */   public int[] executeBatchUpdate()
/*     */     throws SQLException
/*     */   {
/*  92 */     int[] iaRet = new int[0];
/*     */     try
/*     */     {
/* 107 */       this.pst = this.conn.prepareStatement(this.sql);
/* 108 */       ParameterMetaData params = this.pst.getParameterMetaData();
/* 109 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 110 */         List lRow = (List)this.values.get(i);
/* 111 */         for (int j = 0; (lRow != null) && (j < lRow.size()); j++) {
/* 112 */           Object o = lRow.get(j);
/* 113 */           if ((o == null) || (o.toString().equals(""))) {
/* 114 */             this.pst.setNull(j + 1, params.getParameterType(j + 1));
/* 115 */           } else if ((o instanceof ByteArrayOutputStream)) {
/* 116 */             this.pst.setBytes(j + 1, ((ByteArrayOutputStream)o).toByteArray());
/* 117 */           } else if ((o instanceof Date)) {
/* 118 */             Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 119 */             this.pst.setTimestamp(j + 1, timestamp);
/* 120 */           } else if ((o instanceof LongString)) {
/* 121 */             LongString s = (LongString)o;
/* 122 */             this.pst.setCharacterStream(j + 1, new StringReader(s.getValue()), s.getSize());
/*     */           } else {
/* 124 */             this.pst.setObject(j + 1, o);
/*     */           }
/*     */         }
/* 127 */         this.pst.addBatch();
/*     */       }
/* 129 */       iaRet = this.pst.executeBatch();
/*     */     } catch (SQLException se) {
/* 131 */       throw se;
/*     */     } finally {
/* 133 */       closeStatement(this.pst);
/*     */     }
/* 135 */     return iaRet;
/*     */   }
/*     */ 
/*     */   public void executeCopy(String tableName) throws SQLException {
/* 139 */     String sSQL = "COPY " + tableName + "FROM stdin";
/*     */ 
/* 141 */     Connection c = ((WrappedConnection)this.conn).getUnderlyingConnection();
/* 142 */     CopyManager copyAPI = ((AbstractJdbc2Connection)c).getCopyAPI();
/* 143 */     ByteArrayOutputStream sb = new ByteArrayOutputStream();
/*     */     try
/*     */     {
/* 146 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 147 */         List lRow = (List)this.values.get(i);
/* 148 */         for (int j = 0; (lRow != null) && (j < lRow.size()); j++) {
/* 149 */           Object o = lRow.get(j);
/* 150 */           if ((o == null) || (o.toString().equals(""))) {
/* 151 */             sb.write("\\N".getBytes());
/* 152 */           } else if ((o instanceof ByteArrayOutputStream)) {
/* 153 */             sb.write(((ByteArrayOutputStream)o).toByteArray());
/* 154 */           } else if ((o instanceof LongString)) {
/* 155 */             LongString s = (LongString)o;
/* 156 */             sb.write(s.getValue().getBytes());
/* 157 */           } else if ((o instanceof Date)) {
/* 158 */             Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 159 */             String sTime = TypeUtils.date2String(timestamp, "yyyy-MM-dd HH:mm:ss.SSS");
/* 160 */             sb.write(sTime.getBytes());
/*     */           } else {
/* 162 */             sb.write(o.toString().getBytes());
/*     */           }
/* 164 */           if (j < lRow.size() - 1)
/* 165 */             sb.write(9);
/*     */           else {
/* 167 */             sb.write(10);
/*     */           }
/*     */         }
/*     */       }
/* 171 */       copyAPI.copyIntoDB(sSQL, new ByteArrayInputStream(sb.toByteArray()));
/*     */     } catch (IOException e) {
/* 173 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public DBRowSet getPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 191 */     if (page < 1) {
/* 192 */       return getNoPageData();
/*     */     }
/*     */ 
/* 195 */     DBRowSet data = null;
/* 196 */     this.page = page;
/*     */ 
/* 198 */     int iBegin = 0;
/* 199 */     int iEnd = 0;
/* 200 */     ResultSet rs2 = null;
/* 201 */     PreparedStatement pst2 = null;
/*     */     try
/*     */     {
/* 206 */       pst2 = this.conn.prepareStatement("SELECT COUNT(*) FROM (" + this.sql + ") a");
/* 207 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 208 */         Object o = this.values.get(i);
/* 209 */         if ((o != null) && ((o instanceof Date))) {
/* 210 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 211 */           pst2.setObject(i + 1, timestamp);
/*     */         } else {
/* 213 */           pst2.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 216 */       rs2 = pst2.executeQuery();
/* 217 */       while (rs2.next()) {
/* 218 */         this.totalRows = rs2.getInt(1);
/*     */       }
/* 220 */       closeResultSet(rs2);
/* 221 */       closeStatement(pst2);
/*     */ 
/* 225 */       if ((this.totalRows == 0) || (this.rowCountPerPage <= 0)) {
/* 226 */         this.pageCount = 0;
/* 227 */         return new DBRowSet(this.totalRows, 0, 0, 1, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, new ArrayList());
/*     */       }
/* 229 */       if (this.rowCountPerPage > 0)
/*     */       {
/* 231 */         if (this.totalRows > this.rowCountPerPage)
/* 232 */           this.pageCount = ((this.totalRows + this.rowCountPerPage - 1) / this.rowCountPerPage);
/* 233 */         else if (this.totalRows <= this.rowCountPerPage) {
/* 234 */           this.pageCount = 1;
/*     */         }
/* 236 */         if (page > this.pageCount) {
/* 237 */           page = this.pageCount;
/*     */         }
/* 239 */         iBegin = (page - 1) * this.rowCountPerPage;
/* 240 */         iEnd = iBegin + this.rowCountPerPage + 1;
/*     */       }
/*     */ 
/* 245 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 246 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 247 */         Object o = this.values.get(i);
/* 248 */         if ((o != null) && ((o instanceof Date))) {
/* 249 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 250 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 252 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 255 */       this.rs = this.pst.executeQuery();
/*     */ 
/* 259 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 262 */       this.columnCount = this.metadata.getColumnCount();
/* 263 */       getColumnNames(this.originSql);
/*     */ 
/* 265 */       this.columnTypes = new String[this.columnCount];
/* 266 */       this.columnSqlTypes = new int[this.columnCount];
/* 267 */       this.dbColumnNames = new String[this.columnCount];
/* 268 */       for (int i = 0; i < this.columnCount; i++) {
/* 269 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 270 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 271 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/*     */ 
/* 278 */       ArrayList rows = new ArrayList();
/* 279 */       ArrayList row = null;
/* 280 */       int rowCount = 0;
/*     */ 
/* 282 */       if (iBegin == 0)
/* 283 */         this.rs.beforeFirst();
/*     */       else {
/* 285 */         this.rs.absolute(iBegin);
/*     */       }
/*     */ 
/* 288 */       while ((this.rs.next()) && (this.rs.getRow() < iEnd)) {
/* 289 */         row = new ArrayList();
/* 290 */         for (int i = 1; i <= this.columnCount; i++) {
/* 291 */           row.add(this.rs.getObject(i));
/*     */         }
/* 293 */         rows.add(row);
/* 294 */         rowCount++;
/*     */       }
/* 296 */       data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/*     */     } catch (SQLException e) {
/* 298 */       throw e;
/*     */     } finally {
/*     */       try {
/* 301 */         closeResultSet(rs2);
/* 302 */         closeStatement(pst2);
/* 303 */         closeResultSet(this.rs);
/* 304 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/* 306 */         throw se;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 312 */     return data;
/*     */   }
/*     */ 
/*     */   private DBRowSet getNoPageData()
/*     */     throws SQLException
/*     */   {
/* 323 */     DBRowSet data = null;
/*     */     try
/*     */     {
/* 327 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 328 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 329 */         Object o = this.values.get(i);
/* 330 */         if ((o != null) && ((o instanceof Date))) {
/* 331 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 332 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 334 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 337 */       this.rs = this.pst.executeQuery();
/* 338 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 341 */       this.columnCount = this.metadata.getColumnCount();
/* 342 */       getColumnNames(this.originSql);
/*     */ 
/* 344 */       this.columnTypes = new String[this.columnCount];
/* 345 */       this.columnSqlTypes = new int[this.columnCount];
/* 346 */       this.dbColumnNames = new String[this.columnCount];
/* 347 */       for (int i = 0; i < this.columnCount; i++) {
/* 348 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 349 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 350 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/*     */ 
/* 354 */       ArrayList rows = new ArrayList();
/* 355 */       ArrayList row = null;
/* 356 */       int rowCount = 0;
/* 357 */       while (this.rs.next()) {
/* 358 */         row = new ArrayList();
/* 359 */         for (int i = 1; i <= this.columnCount; i++) {
/* 360 */           if (this.columnSqlTypes[(i - 1)] == -4) {
/* 361 */             byte[] bytecontent = null;
/* 362 */             InputStream is = this.rs.getBinaryStream(i);
/* 363 */             if (is != null) {
/* 364 */               byte[] buffer = new byte[512];
/*     */               try {
/* 366 */                 ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 367 */                 int length = is.read(buffer);
/* 368 */                 while (length != -1) {
/* 369 */                   out.write(buffer, 0, length);
/* 370 */                   length = is.read(buffer);
/*     */                 }
/* 372 */                 bytecontent = out.toByteArray();
/* 373 */                 out.close();
/*     */               } catch (IOException e) {
/* 375 */                 throw new SQLException("Unkown Server error.");
/*     */               }
/*     */             }
/* 378 */             row.add(new LongBinary(bytecontent));
/*     */           } else {
/* 380 */             row.add(this.rs.getObject(i));
/*     */           }
/*     */         }
/* 383 */         rows.add(row);
/* 384 */         rowCount++;
/*     */       }
/* 386 */       data = new DBRowSet(rowCount, rowCount, this.columnCount, 1, 1, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/*     */     } catch (SQLException e) {
/* 388 */       throw e;
/*     */     } finally {
/*     */       try {
/* 391 */         closeResultSet(this.rs);
/* 392 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/* 394 */         throw se;
/*     */       }
/*     */     }
/* 397 */     return data;
/*     */   }
/*     */ 
/*     */   public DBRowSet getBatchPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 411 */     this.page = page;
/*     */ 
/* 414 */     if ((this.rs == null) || (this.first)) {
/* 415 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 416 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 417 */         Object o = this.values.get(i);
/* 418 */         if ((o != null) && ((o instanceof Date))) {
/* 419 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 420 */           this.pst.setObject(i + 1, timestamp);
/*     */         } else {
/* 422 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/*     */ 
/* 426 */       this.rs = this.pst.executeQuery();
/* 427 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 430 */       this.columnCount = this.metadata.getColumnCount();
/* 431 */       getColumnNames(this.originSql);
/* 432 */       this.columnTypes = new String[this.columnCount];
/* 433 */       this.columnSqlTypes = new int[this.columnCount];
/* 434 */       this.dbColumnNames = new String[this.columnCount];
/* 435 */       for (int i = 0; i < this.columnCount; i++) {
/* 436 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 437 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 438 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/* 440 */       this.first = false;
/*     */     }
/*     */ 
/* 444 */     int rowPos = (page - 1) * this.rowCountPerPage;
/* 445 */     if (rowPos == 0)
/* 446 */       this.rs.beforeFirst();
/*     */     else {
/* 448 */       this.rs.absolute(rowPos);
/*     */     }
/*     */ 
/* 452 */     ArrayList rows = new ArrayList();
/* 453 */     ArrayList row = null;
/* 454 */     int rowCount = 0;
/* 455 */     for (int i = 0; (i < this.rowCountPerPage) && (this.rs.next()); i++) {
/* 456 */       row = new ArrayList();
/* 457 */       for (int j = 1; j <= this.columnCount; j++) {
/* 458 */         row.add(this.rs.getObject(j));
/*     */       }
/* 460 */       rows.add(row);
/* 461 */       rowCount++;
/*     */     }
/* 463 */     DBRowSet data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/* 464 */     return data;
/*     */   }
/*     */ 
/*     */   private void getColumnNames(String sql)
/*     */   {
/* 469 */     sql = sql.toUpperCase();
/* 470 */     int iSelectIdx = sql.indexOf("SELECT");
/* 471 */     int iFromIdx = sql.indexOf("FROM");
/* 472 */     String sCols = sql.substring(iSelectIdx + 6, iFromIdx);
/* 473 */     String sColName = "";
/* 474 */     int iAliasIdx = 0;
/* 475 */     StringTokenizer tokenizer = new StringTokenizer(sCols, ",");
/* 476 */     ArrayList al = new ArrayList();
/* 477 */     while (tokenizer.hasMoreTokens()) {
/* 478 */       sColName = tokenizer.nextToken().trim();
/*     */ 
/* 480 */       iAliasIdx = sColName.lastIndexOf(" AS ");
/* 481 */       if (iAliasIdx == -1) {
/* 482 */         iAliasIdx = sColName.lastIndexOf(" ");
/* 483 */         if (iAliasIdx != -1)
/* 484 */           sColName = sColName.substring(iAliasIdx + 1).trim();
/*     */       }
/*     */       else {
/* 487 */         sColName = sColName.substring(iAliasIdx + 4).trim();
/*     */       }
/* 489 */       al.add(sColName);
/*     */     }
/* 491 */     while (al.size() < this.columnCount) {
/* 492 */       al.add("");
/*     */     }
/* 494 */     this.columnNames = ((String[])al.toArray(new String[this.columnCount]));
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessorPostgreSQLImpl
 * JD-Core Version:    0.6.2
 */