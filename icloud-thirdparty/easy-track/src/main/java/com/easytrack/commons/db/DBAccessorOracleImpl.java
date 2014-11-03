/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
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
/*     */ import oracle.sql.TIMESTAMP;
/*     */ 
/*     */ public class DBAccessorOracleImpl extends DBAccessorImpl
/*     */ {
/*     */   public int executeUpdate()
/*     */     throws SQLException
/*     */   {
/*  44 */     int iRet = 0;
/*     */     try {
/*  46 */       this.pst = this.conn.prepareStatement(this.sql);
/*  47 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/*  48 */         Object o = this.values.get(i);
/*  49 */         if (o == null) {
/*  50 */           this.pst.setObject(i + 1, "");
/*  51 */         } else if ((o instanceof ByteArrayOutputStream)) {
/*  52 */           this.pst.setBytes(i + 1, ((ByteArrayOutputStream)o).toByteArray());
/*  53 */         } else if ((o instanceof LongString)) {
/*  54 */           LongString s = (LongString)o;
/*  55 */           this.pst.setCharacterStream(i + 1, new StringReader(s.getValue()), s.getSize());
/*  56 */         } else if ((o instanceof LongBinary)) {
/*  57 */           LongBinary s = (LongBinary)o;
/*  58 */           this.pst.setBinaryStream(i + 1, new ByteArrayInputStream(s.getValue()), s.getSize());
/*  59 */         } else if ((o instanceof Date)) {
/*  60 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/*  61 */           this.pst.setTimestamp(i + 1, timestamp);
/*     */         } else {
/*  63 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
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
/*  85 */     int[] iaRet = null;
/*     */ 
/*  87 */     boolean executeBatch = false;
/*     */     try
/*     */     {
/*  90 */       this.pst = this.conn.prepareStatement(this.sql);
/*  91 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/*  92 */         List lRow = (List)this.values.get(i);
/*  93 */         for (int j = 0; (lRow != null) && (j < lRow.size()); j++) {
/*  94 */           Object o = lRow.get(j);
/*  95 */           if (o == null) {
/*  96 */             this.pst.setObject(j + 1, "");
/*  97 */           } else if ((o instanceof ByteArrayOutputStream)) {
/*  98 */             this.pst.setBytes(j + 1, ((ByteArrayOutputStream)o).toByteArray());
/*  99 */           } else if ((o instanceof LongString)) {
/* 100 */             LongString s = (LongString)o;
/* 101 */             this.pst.setCharacterStream(j + 1, new StringReader(s.getValue()), s.getSize());
/* 102 */           } else if ((o instanceof LongBinary)) {
/* 103 */             LongBinary s = (LongBinary)o;
/* 104 */             this.pst.setBinaryStream(j + 1, new ByteArrayInputStream(s.getValue()), s.getSize());
/* 105 */           } else if ((o instanceof Date)) {
/* 106 */             Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 107 */             this.pst.setTimestamp(j + 1, timestamp);
/*     */           } else {
/* 109 */             this.pst.setObject(j + 1, o);
/*     */           }
/*     */         }
/* 112 */         this.pst.addBatch();
/* 113 */         executeBatch = true;
/*     */       }
/* 115 */       if (executeBatch)
/* 116 */         iaRet = this.pst.executeBatch();
/*     */     }
/*     */     catch (SQLException se) {
/* 119 */       throw se;
/*     */     } finally {
/* 121 */       closeStatement(this.pst);
/*     */     }
/* 123 */     return iaRet;
/*     */   }
/*     */ 
/*     */   public DBRowSet getPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 137 */     DBRowSet data = null;
/* 138 */     this.page = page;
/* 139 */     int iParseStatus = parseSQL();
/* 140 */     if (iParseStatus == -1) {
/* 141 */       this.pageCount = 0;
/* 142 */       return new DBRowSet(this.totalRows, 0, 0, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, new ArrayList());
/* 143 */     }if (iParseStatus == 0) {
/* 144 */       throw new SQLException("Sql parsing failed");
/*     */     }
/*     */     try
/*     */     {
/* 148 */       this.rs = this.pst.executeQuery();
/* 149 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 152 */       this.columnCount = this.metadata.getColumnCount();
/*     */ 
/* 154 */       if ((this.rowCountPerPage > 0) && (this.totalRows > this.rowCountPerPage)) {
/* 155 */         this.columnCount -= 1;
/*     */       }
/* 157 */       getColumnNames(this.originSql);
/*     */ 
/* 159 */       this.columnTypes = new String[this.columnCount];
/* 160 */       this.columnSqlTypes = new int[this.columnCount];
/* 161 */       this.dbColumnNames = new String[this.columnCount];
/* 162 */       for (int i = 0; i < this.columnCount; i++) {
/* 163 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 164 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 165 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/*     */ 
/* 169 */       ArrayList rows = new ArrayList();
/* 170 */       ArrayList row = null;
/* 171 */       int rowCount = 0;
/* 172 */       while (this.rs.next()) {
/* 173 */         row = new ArrayList();
/* 174 */         for (int i = 1; i <= this.columnCount; i++) {
/* 175 */           Object o = this.rs.getObject(i);
/* 176 */           if ((o instanceof TIMESTAMP)) {
/* 177 */             TIMESTAMP oTime = (TIMESTAMP)o;
/* 178 */             Timestamp oRet = null;
/* 179 */             if (oTime != null) {
/* 180 */               oRet = new Timestamp(oTime.timestampValue().getTime());
/*     */             }
/* 182 */             row.add(oRet);
/* 183 */           } else if (this.columnSqlTypes[(i - 1)] == 2005) {
/* 184 */             row.add(this.rs.getString(i));
/* 185 */           } else if (this.columnSqlTypes[(i - 1)] == 2004) {
/* 186 */             byte[] bytecontent = null;
/* 187 */             InputStream is = this.rs.getBinaryStream(i);
/* 188 */             if (is != null) {
/* 189 */               byte[] buffer = new byte[512];
/*     */               try {
/* 191 */                 ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 192 */                 int length = is.read(buffer);
/* 193 */                 while (length != -1) {
/* 194 */                   out.write(buffer, 0, length);
/* 195 */                   length = is.read(buffer);
/*     */                 }
/* 197 */                 bytecontent = out.toByteArray();
/* 198 */                 out.close();
/*     */               } catch (IOException e) {
/* 200 */                 throw new SQLException("Unkown Server error.");
/*     */               }
/*     */             }
/* 203 */             row.add(new LongBinary(bytecontent));
/*     */           } else {
/* 205 */             row.add(this.rs.getObject(i));
/*     */           }
/*     */         }
/* 208 */         rows.add(row);
/* 209 */         rowCount++;
/*     */       }
/* 211 */       data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/*     */     } catch (SQLException e) {
/* 213 */       throw e;
/*     */     } finally {
/*     */       try {
/* 216 */         closeResultSet(this.rs);
/* 217 */         closeStatement(this.pst);
/*     */       } catch (SQLException se) {
/*     */       }
/*     */     }
/* 221 */     return data;
/*     */   }
/*     */ 
/*     */   public DBRowSet getBatchPageData(int page)
/*     */     throws SQLException
/*     */   {
/* 235 */     this.page = page;
/*     */ 
/* 238 */     if ((this.rs == null) || (this.first)) {
/* 239 */       this.pst = this.conn.prepareStatement(this.sql, 1004, 1007);
/* 240 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 241 */         this.pst.setObject(i + 1, this.values.get(i));
/*     */       }
/*     */ 
/* 244 */       this.rs = this.pst.executeQuery();
/* 245 */       this.metadata = this.rs.getMetaData();
/*     */ 
/* 248 */       this.columnCount = this.metadata.getColumnCount();
/* 249 */       getColumnNames(this.originSql);
/* 250 */       this.columnTypes = new String[this.columnCount];
/* 251 */       this.columnSqlTypes = new int[this.columnCount];
/* 252 */       this.dbColumnNames = new String[this.columnCount];
/* 253 */       for (int i = 0; i < this.columnCount; i++) {
/* 254 */         this.columnTypes[i] = this.metadata.getColumnTypeName(i + 1);
/* 255 */         this.columnSqlTypes[i] = this.metadata.getColumnType(i + 1);
/* 256 */         this.dbColumnNames[i] = this.metadata.getColumnName(i + 1);
/*     */       }
/* 258 */       this.first = false;
/*     */     }
/*     */ 
/* 262 */     int rowPos = (page - 1) * this.rowCountPerPage;
/* 263 */     if (rowPos == 0)
/* 264 */       this.rs.beforeFirst();
/*     */     else {
/* 266 */       this.rs.absolute(rowPos);
/*     */     }
/*     */ 
/* 270 */     ArrayList rows = new ArrayList();
/* 271 */     ArrayList row = null;
/* 272 */     int rowCount = 0;
/* 273 */     for (int i = 0; (i < this.rowCountPerPage) && (this.rs.next()); i++) {
/* 274 */       row = new ArrayList();
/* 275 */       for (int j = 1; j <= this.columnCount; j++) {
/* 276 */         row.add(this.rs.getObject(j));
/*     */       }
/* 278 */       rows.add(row);
/* 279 */       rowCount++;
/*     */     }
/* 281 */     DBRowSet data = new DBRowSet(this.totalRows, rowCount, this.columnCount, page, this.pageCount, this.columnNames, this.dbColumnNames, this.columnTypes, this.columnSqlTypes, rows);
/* 282 */     return data;
/*     */   }
/*     */ 
/*     */   private int parseSQL()
/*     */   {
/* 291 */     int iBegin = 0;
/* 292 */     int iEnd = 0;
/* 293 */     int iRet = 0;
/* 294 */     ResultSet rs2 = null;
/* 295 */     PreparedStatement pst2 = null;
/*     */     try
/*     */     {
/* 299 */       pst2 = this.conn.prepareStatement("SELECT COUNT(*) FROM (" + this.sql + ")");
/* 300 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 301 */         Object o = this.values.get(i);
/* 302 */         if (o == null) {
/* 303 */           pst2.setObject(i + 1, "");
/* 304 */         } else if ((o instanceof ByteArrayOutputStream)) {
/* 305 */           pst2.setBytes(i + 1, ((ByteArrayOutputStream)o).toByteArray());
/* 306 */         } else if ((o instanceof LongString)) {
/* 307 */           LongString s = (LongString)o;
/* 308 */           pst2.setCharacterStream(i + 1, new StringReader(s.getValue()), s.getSize());
/* 309 */         } else if ((o instanceof LongBinary)) {
/* 310 */           LongBinary s = (LongBinary)o;
/* 311 */           pst2.setBinaryStream(i + 1, new ByteArrayInputStream(s.getValue()), s.getSize());
/* 312 */         } else if ((o instanceof Date)) {
/* 313 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 314 */           pst2.setTimestamp(i + 1, timestamp);
/*     */         } else {
/* 316 */           pst2.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 319 */       rs2 = pst2.executeQuery();
/* 320 */       while (rs2.next()) {
/* 321 */         this.totalRows = rs2.getInt(1);
/*     */       }
/*     */ 
/* 324 */       if (this.totalRows == 0) {
/* 325 */         iRet = -1;
/* 326 */         return iRet;
/*     */       }
/*     */ 
/* 329 */       if (this.rowCountPerPage > 0)
/*     */       {
/* 331 */         if (this.totalRows > this.rowCountPerPage) {
/* 332 */           this.pageCount = ((this.totalRows + this.rowCountPerPage - 1) / this.rowCountPerPage);
/* 333 */           if (this.page < 1) {
/* 334 */             this.page = 1;
/*     */           }
/* 336 */           if (this.page > this.pageCount) {
/* 337 */             this.page = this.pageCount;
/*     */           }
/* 339 */           iBegin = (this.page - 1) * this.rowCountPerPage + 1;
/* 340 */           iEnd = iBegin + this.rowCountPerPage;
/* 341 */           this.sql = ("SELECT * FROM (SELECT originTable.*, ROWNUM rn FROM (" + this.sql + ")" + " originTable) WHERE rn >=" + iBegin);
/*     */ 
/* 343 */           if (iEnd <= this.totalRows)
/* 344 */             this.sql = (this.sql + " and rn <" + iEnd);
/*     */         }
/*     */         else {
/* 347 */           if (this.page < 1) {
/* 348 */             this.page = 1;
/*     */           }
/* 350 */           if (this.page > this.pageCount) {
/* 351 */             this.page = this.pageCount;
/*     */           }
/* 353 */           this.pageCount = 1;
/*     */         }
/*     */       }
/*     */ 
/* 357 */       this.pst = this.conn.prepareStatement(this.sql);
/* 358 */       for (int i = 0; (this.values != null) && (i < this.values.size()); i++) {
/* 359 */         Object o = this.values.get(i);
/* 360 */         if (o == null) {
/* 361 */           this.pst.setObject(i + 1, "");
/* 362 */         } else if ((o instanceof ByteArrayOutputStream)) {
/* 363 */           this.pst.setBytes(i + 1, ((ByteArrayOutputStream)o).toByteArray());
/* 364 */         } else if ((o instanceof LongString)) {
/* 365 */           LongString s = (LongString)o;
/* 366 */           this.pst.setCharacterStream(i + 1, new StringReader(s.getValue()), s.getSize());
/* 367 */         } else if ((o instanceof LongBinary)) {
/* 368 */           LongBinary s = (LongBinary)o;
/* 369 */           this.pst.setBinaryStream(i + 1, new ByteArrayInputStream(s.getValue()), s.getSize());
/* 370 */         } else if ((o instanceof Date)) {
/* 371 */           Timestamp timestamp = new Timestamp(((Date)o).getTime());
/* 372 */           this.pst.setTimestamp(i + 1, timestamp);
/*     */         } else {
/* 374 */           this.pst.setObject(i + 1, o);
/*     */         }
/*     */       }
/* 377 */       iRet = 1;
/*     */     } catch (SQLException se) {
/* 379 */       se.printStackTrace();
/* 380 */       iRet = 0;
/*     */     } finally {
/*     */       try {
/* 383 */         closeResultSet(rs2);
/* 384 */         closeStatement(pst2);
/*     */       } catch (SQLException e) {
/*     */       }
/*     */     }
/* 388 */     return iRet;
/*     */   }
/*     */ 
/*     */   private void getColumnNames(String sql)
/*     */   {
/* 393 */     sql = sql.toUpperCase();
/* 394 */     int iSelectIdx = sql.indexOf("SELECT");
/* 395 */     int iFromIdx = sql.indexOf("FROM");
/* 396 */     String sCols = sql.substring(iSelectIdx + 6, iFromIdx);
/* 397 */     String sColName = "";
/* 398 */     int iAliasIdx = 0;
/* 399 */     StringTokenizer tokenizer = new StringTokenizer(sCols, ",");
/* 400 */     ArrayList al = new ArrayList();
/* 401 */     while (tokenizer.hasMoreTokens()) {
/* 402 */       sColName = tokenizer.nextToken().trim();
/*     */ 
/* 404 */       iAliasIdx = sColName.lastIndexOf(" AS ");
/* 405 */       if (iAliasIdx == -1) {
/* 406 */         iAliasIdx = sColName.lastIndexOf(" ");
/* 407 */         if (iAliasIdx != -1)
/* 408 */           sColName = sColName.substring(iAliasIdx + 1).trim();
/*     */       }
/*     */       else {
/* 411 */         sColName = sColName.substring(iAliasIdx + 4).trim();
/*     */       }
/* 413 */       al.add(sColName);
/*     */     }
/* 415 */     while (al.size() < this.columnCount) {
/* 416 */       al.add("");
/*     */     }
/* 418 */     this.columnNames = ((String[])al.toArray(new String[this.columnCount]));
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBAccessorOracleImpl
 * JD-Core Version:    0.6.2
 */