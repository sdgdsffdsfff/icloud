/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Date;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DBRowSet
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = 1644754665517621418L;
/*  24 */   private int totalRows = 0;
/*     */ 
/*  28 */   private int rowCount = 0;
/*     */ 
/*  32 */   private int columnCount = 0;
/*     */ 
/*  36 */   private int pageCount = 1;
/*     */ 
/*  40 */   private int currentPage = 1;
/*     */ 
/*  44 */   private String[] columnNames = new String[0];
/*     */ 
/*  48 */   private String[] dbColumnNames = new String[0];
/*     */ 
/*  52 */   private String[] columnTypeNames = new String[0];
/*     */ 
/*  56 */   private int[] columnTypes = new int[0];
/*     */   private int[] columnPrecisions;
/*     */   private int[] columnScales;
/*     */   private List rows;
/*     */ 
/*     */   public DBRowSet(int totalRows, int rowCount, int columnCount, int currentPage, int pageCount, String[] columnNames, String[] dbColumnNames, String[] columnTypeNames, int[] columnTypes, List rows)
/*     */   {
/*  85 */     this.totalRows = totalRows;
/*  86 */     this.rowCount = rowCount;
/*  87 */     this.columnCount = columnCount;
/*  88 */     this.currentPage = currentPage;
/*  89 */     this.pageCount = pageCount;
/*  90 */     this.columnNames = columnNames;
/*  91 */     this.dbColumnNames = dbColumnNames;
/*  92 */     this.columnTypeNames = columnTypeNames;
/*  93 */     this.columnTypes = columnTypes;
/*  94 */     this.rows = rows;
/*     */   }
/*     */ 
/*     */   public DBRowSet(int totalRows, int rowCount, int columnCount, int currentPage, int pageCount, String[] columnNames, String[] dbColumnNames, String[] columnTypeNames, int[] columnTypes, List rows, int[] columnPrecisions, int[] columnScales)
/*     */   {
/* 114 */     this.totalRows = totalRows;
/* 115 */     this.rowCount = rowCount;
/* 116 */     this.columnCount = columnCount;
/* 117 */     this.currentPage = currentPage;
/* 118 */     this.pageCount = pageCount;
/* 119 */     this.columnNames = columnNames;
/* 120 */     this.dbColumnNames = dbColumnNames;
/* 121 */     this.columnTypeNames = columnTypeNames;
/* 122 */     this.columnTypes = columnTypes;
/* 123 */     this.rows = rows;
/* 124 */     this.columnPrecisions = columnPrecisions;
/* 125 */     this.columnScales = columnScales;
/*     */   }
/*     */ 
/*     */   public int getPageCount()
/*     */   {
/* 133 */     return this.pageCount;
/*     */   }
/*     */ 
/*     */   public void setPageCount(int pageCount)
/*     */   {
/* 141 */     this.pageCount = pageCount;
/*     */   }
/*     */ 
/*     */   public int getCurrentPage()
/*     */   {
/* 149 */     return this.currentPage;
/*     */   }
/*     */ 
/*     */   public void setCurrentPage(int currentPage)
/*     */   {
/* 157 */     this.currentPage = currentPage;
/*     */   }
/*     */ 
/*     */   public int getTotalRows()
/*     */   {
/* 165 */     return this.totalRows;
/*     */   }
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 173 */     return this.rowCount;
/*     */   }
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 181 */     return this.columnCount;
/*     */   }
/*     */ 
/*     */   public String[] getColumnNames()
/*     */   {
/* 189 */     return this.columnNames;
/*     */   }
/*     */ 
/*     */   public String[] getDBColumnNames()
/*     */   {
/* 197 */     return this.dbColumnNames;
/*     */   }
/*     */ 
/*     */   public void setDBColumnNames(String[] dbColumnNames)
/*     */   {
/* 205 */     this.dbColumnNames = dbColumnNames;
/*     */   }
/*     */ 
/*     */   public String[] getColumnTypeNames()
/*     */   {
/* 213 */     return this.columnTypeNames;
/*     */   }
/*     */ 
/*     */   public int[] getColumnTypes()
/*     */   {
/* 221 */     return this.columnTypes;
/*     */   }
/*     */ 
/*     */   public int getColumnType(String columnName)
/*     */   {
/* 230 */     int idx = -1;
/* 231 */     for (int i = 0; i < this.columnCount; i++) {
/* 232 */       if ((this.columnNames[i].equals(columnName.toUpperCase())) || (this.dbColumnNames[i].toUpperCase().equals(columnName.toUpperCase())))
/*     */       {
/* 234 */         idx = i;
/* 235 */         break;
/*     */       }
/*     */     }
/* 238 */     if (idx == -1) {
/* 239 */       return -1;
/*     */     }
/* 241 */     return this.columnTypes[idx];
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int row, int column)
/*     */   {
/* 251 */     return getRow(row).get(column);
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int row, String columnName)
/*     */   {
/* 261 */     int idx = -1;
/* 262 */     for (int i = 0; i < this.columnCount; i++) {
/* 263 */       if (this.dbColumnNames[i].toUpperCase().equals(columnName.toUpperCase())) {
/* 264 */         idx = i;
/* 265 */         break;
/*     */       }
/*     */     }
/* 268 */     if (idx == -1) {
/* 269 */       return null;
/*     */     }
/* 271 */     return getRow(row).get(idx);
/*     */   }
/*     */ 
/*     */   public List getRow(int row)
/*     */   {
/* 280 */     return (List)this.rows.get(row);
/*     */   }
/*     */ 
/*     */   public List getRows()
/*     */   {
/* 288 */     return this.rows;
/*     */   }
/*     */ 
/*     */   public void setValueAt(String columnName, Object value)
/*     */   {
/* 297 */     setValueAt(-1, columnName, value);
/*     */   }
/*     */ 
/*     */   public void setValueAt(int row, String columnName, Object value)
/*     */   {
/* 307 */     int idx = -1;
/* 308 */     for (int i = 0; i < this.columnCount; i++) {
/* 309 */       if ((this.columnNames[i].toUpperCase().equals(columnName.toUpperCase())) || (this.dbColumnNames[i].toUpperCase().equals(columnName.toUpperCase())))
/*     */       {
/* 311 */         idx = i;
/* 312 */         break;
/*     */       }
/*     */     }
/* 315 */     if (idx == -1) {
/* 316 */       return;
/*     */     }
/* 318 */     setValueAt(row, idx, value);
/*     */   }
/*     */ 
/*     */   public void setValueAt(int column, Object value)
/*     */   {
/* 327 */     setValueAt(-1, column, value);
/*     */   }
/*     */ 
/*     */   public void setValueAt(int row, int column, Object value)
/*     */   {
/* 337 */     if (row == -1) {
/* 338 */       for (int i = 0; i < this.rowCount; i++)
/* 339 */         getRow(i).set(column, value);
/*     */     }
/*     */     else
/* 342 */       getRow(row).set(column, value);
/*     */   }
/*     */ 
/*     */   public void addColumn(String colName, String colType, int colSqlType)
/*     */   {
/* 354 */     this.columnCount += 1;
/* 355 */     String[] tempColTypes = new String[this.columnCount];
/* 356 */     String[] tempColNames = new String[this.columnCount];
/* 357 */     String[] tempDBColNames = new String[this.columnCount];
/* 358 */     int[] tempColSqlTypes = new int[this.columnCount];
/* 359 */     for (int i = 0; i < this.columnCount - 1; i++) {
/* 360 */       tempColTypes[i] = this.columnTypeNames[i];
/* 361 */       tempColNames[i] = this.columnNames[i];
/* 362 */       tempDBColNames[i] = this.dbColumnNames[i];
/* 363 */       tempColSqlTypes[i] = this.columnTypes[i];
/*     */     }
/* 365 */     tempColTypes[(this.columnCount - 1)] = colType.toUpperCase();
/* 366 */     tempColNames[(this.columnCount - 1)] = colName.toUpperCase();
/* 367 */     tempDBColNames[(this.columnCount - 1)] = colName.toUpperCase();
/* 368 */     tempColSqlTypes[(this.columnCount - 1)] = colSqlType;
/* 369 */     this.columnTypeNames = tempColTypes;
/* 370 */     this.columnNames = tempColNames;
/* 371 */     this.dbColumnNames = tempDBColNames;
/* 372 */     this.columnTypes = tempColSqlTypes;
/*     */ 
/* 375 */     for (int i = 0; i < this.rowCount; i++)
/* 376 */       getRow(i).add(null);
/*     */   }
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 386 */     DBRowSet dbRtn = (DBRowSet)super.clone();
/* 387 */     dbRtn.columnNames = ((String[])this.columnNames.clone());
/* 388 */     dbRtn.dbColumnNames = ((String[])this.dbColumnNames.clone());
/* 389 */     dbRtn.columnTypeNames = ((String[])this.columnTypeNames.clone());
/* 390 */     dbRtn.columnTypes = ((int[])this.columnTypes.clone());
/* 391 */     ArrayList al = new ArrayList();
/* 392 */     for (int i = 0; i < this.rowCount; i++) {
/* 393 */       al.add(((ArrayList)this.rows.get(i)).clone());
/*     */     }
/* 395 */     dbRtn.rows = al;
/* 396 */     return dbRtn;
/*     */   }
/*     */ 
/*     */   public String getString(int row, int column)
/*     */   {
/* 406 */     Object ob = getValueAt(row, column);
/* 407 */     return ob == null ? "" : ob.toString();
/*     */   }
/*     */ 
/*     */   public String getString(int row, String columnName)
/*     */   {
/* 416 */     Object ob = getValueAt(row, columnName);
/* 417 */     return ob == null ? "" : ob.toString();
/*     */   }
/*     */ 
/*     */   public int getInt(int row, int column)
/*     */   {
/* 427 */     Object ob = getValueAt(row, column);
/* 428 */     return ob == null ? 0 : new BigDecimal(ob.toString()).intValue();
/*     */   }
/*     */ 
/*     */   public int getInt(int row, String columnName)
/*     */   {
/* 437 */     Object ob = getValueAt(row, columnName);
/* 438 */     return ob == null ? 0 : new BigDecimal(ob.toString()).intValue();
/*     */   }
/*     */ 
/*     */   public boolean getBoolean(int row, int column)
/*     */   {
/* 448 */     return getInt(row, column) != 0;
/*     */   }
/*     */ 
/*     */   public boolean getBoolean(int row, String columnName)
/*     */   {
/* 457 */     return getInt(row, columnName) != 0;
/*     */   }
/*     */ 
/*     */   public long getLong(int row, int column)
/*     */   {
/* 467 */     Object ob = getValueAt(row, column);
/* 468 */     return ob == null ? 0L : new BigDecimal(ob.toString()).longValue();
/*     */   }
/*     */ 
/*     */   public long getLong(int row, String columnName)
/*     */   {
/* 477 */     Object ob = getValueAt(row, columnName);
/* 478 */     return ob == null ? 0L : new BigDecimal(ob.toString()).longValue();
/*     */   }
/*     */ 
/*     */   public double getDouble(int row, int column)
/*     */   {
/* 488 */     Object ob = getValueAt(row, column);
/* 489 */     return ob == null ? 0.0D : new BigDecimal(ob.toString()).doubleValue();
/*     */   }
/*     */ 
/*     */   public double getDouble(int row, String columnName)
/*     */   {
/* 498 */     Object ob = getValueAt(row, columnName);
/* 499 */     return ob == null ? 0.0D : new BigDecimal(ob.toString()).doubleValue();
/*     */   }
/*     */ 
/*     */   public Date getDate(int row, int column)
/*     */   {
/* 509 */     Date oRet = null;
/* 510 */     Object ob = getValueAt(row, column);
/* 511 */     if (ob != null) {
/* 512 */       Timestamp oTime = (Timestamp)ob;
/* 513 */       oRet = new Date(oTime.getTime());
/*     */     }
/* 515 */     return oRet;
/*     */   }
/*     */ 
/*     */   public Date getDate(int row, String columnName)
/*     */   {
/* 524 */     Date oRet = null;
/* 525 */     Object ob = getValueAt(row, columnName);
/* 526 */     if (ob != null) {
/* 527 */       Timestamp oTime = (Timestamp)ob;
/* 528 */       oRet = new Date(oTime.getTime());
/*     */     }
/* 530 */     return oRet;
/*     */   }
/*     */ 
/*     */   public float getFloat(int row, int column)
/*     */   {
/* 540 */     Object ob = getValueAt(row, column);
/* 541 */     return ob == null ? 0.0F : new BigDecimal(ob.toString()).floatValue();
/*     */   }
/*     */ 
/*     */   public float getFloat(int row, String columnName)
/*     */   {
/* 550 */     Object ob = getValueAt(row, columnName);
/* 551 */     return ob == null ? 0.0F : new BigDecimal(ob.toString()).floatValue();
/*     */   }
/*     */ 
/*     */   public int getPrecision(String columnName)
/*     */   {
/* 560 */     int idx = -1;
/* 561 */     for (int i = 0; i < this.columnCount; i++) {
/* 562 */       if ((this.columnNames[i].equals(columnName.toUpperCase())) || (this.dbColumnNames[i].toUpperCase().equals(columnName.toUpperCase())))
/*     */       {
/* 564 */         idx = i;
/* 565 */         break;
/*     */       }
/*     */     }
/* 568 */     if (idx == -1) {
/* 569 */       return -1;
/*     */     }
/* 571 */     return this.columnPrecisions[idx];
/*     */   }
/*     */ 
/*     */   public int getScale(String columnName)
/*     */   {
/* 580 */     int idx = -1;
/* 581 */     for (int i = 0; i < this.columnCount; i++) {
/* 582 */       if ((this.columnNames[i].equals(columnName.toUpperCase())) || (this.dbColumnNames[i].toUpperCase().equals(columnName.toUpperCase())))
/*     */       {
/* 584 */         idx = i;
/* 585 */         break;
/*     */       }
/*     */     }
/* 588 */     if (idx == -1) {
/* 589 */       return -1;
/*     */     }
/* 591 */     return this.columnScales[idx];
/*     */   }
/*     */ 
/*     */   public Timestamp getTimestamp(int row, int column)
/*     */   {
/* 601 */     Timestamp oRet = null;
/* 602 */     Object ob = getValueAt(row, column);
/* 603 */     if (ob != null) {
/* 604 */       Date oTime = (Date)ob;
/* 605 */       oRet = new Timestamp(oTime.getTime());
/*     */     }
/* 607 */     return oRet;
/*     */   }
/*     */ 
/*     */   public Timestamp getTimestamp(int row, String columnName)
/*     */   {
/* 616 */     Timestamp oRet = null;
/* 617 */     Object ob = getValueAt(row, columnName);
/* 618 */     if (ob != null) {
/* 619 */       Date oTime = (Date)ob;
/*     */ 
/* 621 */       oRet = new Timestamp(oTime.getTime());
/*     */     }
/* 623 */     return oRet;
/*     */   }
/*     */ 
/*     */   public Integer getInteger(int row, int column)
/*     */   {
/* 632 */     Object ob = getValueAt(row, column);
/* 633 */     Integer result = null;
/* 634 */     if (null != ob) {
/* 635 */       result = new Integer(new BigDecimal(ob.toString()).intValue());
/*     */     }
/* 637 */     return result;
/*     */   }
/*     */ 
/*     */   public Number getNumber(int row, int column)
/*     */   {
/* 647 */     Object ob = getValueAt(row, column);
/* 648 */     BigDecimal result = null;
/* 649 */     if (null != ob) {
/* 650 */       result = new BigDecimal(ob.toString());
/*     */     }
/* 652 */     return result;
/*     */   }
/*     */ 
/*     */   public Integer getInteger(int row, String columnName)
/*     */   {
/* 662 */     Object ob = getValueAt(row, columnName);
/* 663 */     Integer result = null;
/* 664 */     if (null != ob) {
/* 665 */       result = new Integer(new BigDecimal(ob.toString()).intValue());
/*     */     }
/* 667 */     return result;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.DBRowSet
 * JD-Core Version:    0.6.2
 */