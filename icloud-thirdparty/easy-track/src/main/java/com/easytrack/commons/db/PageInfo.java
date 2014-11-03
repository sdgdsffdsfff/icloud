/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ 
/*     */ public class PageInfo
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6267193571245317064L;
/*  19 */   public static String ResourceApply = "resource_apply";
/*     */   private String id;
/*  28 */   private boolean paged = false;
/*     */ 
/*  32 */   private int pageSize = 0;
/*     */ 
/*  36 */   private int pageCount = 1;
/*     */ 
/*  40 */   private int currentPage = 1;
/*     */ 
/*  44 */   private int totalRows = 0;
/*     */ 
/*  48 */   private int rowCount = 0;
/*     */ 
/*  52 */   private int columnCount = 0;
/*     */ 
/*  56 */   private Properties conditions = new Properties();
/*     */ 
/*  60 */   private HashMap filters = new HashMap();
/*     */ 
/*  64 */   private boolean ordered = false;
/*     */ 
/*  68 */   private String orderType = "";
/*     */ 
/*  72 */   private String orderBy = "";
/*     */   private List items;
/*     */ 
/*     */   public PageInfo()
/*     */   {
/*     */   }
/*     */ 
/*     */   public PageInfo(String id, int currentPage, String orderType, String orderBy, Properties conditions)
/*     */   {
/* 102 */     this.id = id;
/* 103 */     this.currentPage = currentPage;
/* 104 */     this.conditions = conditions;
/* 105 */     this.orderType = orderType;
/* 106 */     this.orderBy = orderBy;
/*     */   }
/*     */ 
/*     */   public String getOrderBySQL()
/*     */   {
/* 115 */     if ((this.orderBy != null) && (this.orderBy.length() > 0)) {
/* 116 */       return " order by " + this.orderBy + " " + this.orderType;
/*     */     }
/* 118 */     return "";
/*     */   }
/*     */ 
/*     */   public void setRowSet(DBRowSet rowSet)
/*     */   {
/* 129 */     setPageCount(rowSet.getPageCount());
/* 130 */     setCurrentPage(rowSet.getCurrentPage());
/* 131 */     setTotalRows(rowSet.getTotalRows());
/* 132 */     setRowCount(rowSet.getRowCount());
/* 133 */     setColumnCount(rowSet.getColumnCount());
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/* 142 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/* 152 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public boolean isPaged()
/*     */   {
/* 161 */     return this.paged;
/*     */   }
/*     */ 
/*     */   public void setPaged(boolean paged)
/*     */   {
/* 171 */     this.paged = paged;
/*     */   }
/*     */ 
/*     */   public boolean isOrdered()
/*     */   {
/* 180 */     return this.ordered;
/*     */   }
/*     */ 
/*     */   public void setOrdered(boolean ordered)
/*     */   {
/* 190 */     this.ordered = ordered;
/*     */   }
/*     */ 
/*     */   public int getPageSize()
/*     */   {
/* 199 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize)
/*     */   {
/* 209 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getPageCount()
/*     */   {
/* 218 */     return this.pageCount;
/*     */   }
/*     */ 
/*     */   public void setPageCount(int pageCount)
/*     */   {
/* 228 */     this.pageCount = pageCount;
/*     */   }
/*     */ 
/*     */   public int getCurrentPage()
/*     */   {
/* 237 */     return this.currentPage;
/*     */   }
/*     */ 
/*     */   public void setCurrentPage(int currentPage)
/*     */   {
/* 247 */     this.currentPage = currentPage;
/*     */   }
/*     */ 
/*     */   public int getTotalRows()
/*     */   {
/* 256 */     return this.totalRows;
/*     */   }
/*     */ 
/*     */   public void setTotalRows(int totalRows)
/*     */   {
/* 266 */     this.totalRows = totalRows;
/*     */   }
/*     */ 
/*     */   public int getRowCount()
/*     */   {
/* 275 */     return this.rowCount;
/*     */   }
/*     */ 
/*     */   public void setRowCount(int rowCount)
/*     */   {
/* 285 */     this.rowCount = rowCount;
/*     */   }
/*     */ 
/*     */   public int getColumnCount()
/*     */   {
/* 294 */     return this.columnCount;
/*     */   }
/*     */ 
/*     */   public void setColumnCount(int columnCount)
/*     */   {
/* 304 */     this.columnCount = columnCount;
/*     */   }
/*     */ 
/*     */   public Properties getConditions()
/*     */   {
/* 313 */     return this.conditions;
/*     */   }
/*     */ 
/*     */   public void setConditions(Properties conditions)
/*     */   {
/* 322 */     this.conditions = conditions;
/*     */   }
/*     */ 
/*     */   public HashMap getFilters()
/*     */   {
/* 331 */     return this.filters;
/*     */   }
/*     */ 
/*     */   public void setFilters(HashMap filters)
/*     */   {
/* 340 */     this.filters = filters;
/*     */   }
/*     */ 
/*     */   public String getOrderType()
/*     */   {
/* 349 */     return this.orderType;
/*     */   }
/*     */ 
/*     */   public void setOrderType(String orderType)
/*     */   {
/* 359 */     this.orderType = orderType;
/*     */   }
/*     */ 
/*     */   public String getOrderBy()
/*     */   {
/* 368 */     return this.orderBy;
/*     */   }
/*     */ 
/*     */   public void setOrderBy(String orderBy)
/*     */   {
/* 378 */     this.orderBy = orderBy;
/*     */   }
/*     */ 
/*     */   public List getItems()
/*     */   {
/* 387 */     return this.items;
/*     */   }
/*     */ 
/*     */   public void setItems(List items)
/*     */   {
/* 397 */     this.items = items;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.PageInfo
 * JD-Core Version:    0.6.2
 */