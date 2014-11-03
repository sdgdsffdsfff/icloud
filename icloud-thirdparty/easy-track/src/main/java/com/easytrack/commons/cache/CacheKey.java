/*     */ package com.easytrack.commons.cache;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class CacheKey
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8489543549593332658L;
/*     */   private int id;
/*     */   private int id1;
/*     */   private int companyID;
/*     */ 
/*     */   public CacheKey(int id, int companyID)
/*     */   {
/*  36 */     this.id = id;
/*  37 */     this.companyID = companyID;
/*  38 */     this.id1 = 0;
/*     */   }
/*     */ 
/*     */   public CacheKey(int id, int id1, int companyID)
/*     */   {
/*  53 */     this.id = id;
/*  54 */     this.id1 = id1;
/*  55 */     this.companyID = companyID;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  64 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(int id)
/*     */   {
/*  74 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public int getId1()
/*     */   {
/*  83 */     return this.id1;
/*     */   }
/*     */ 
/*     */   public void setId1(int id1)
/*     */   {
/*  93 */     this.id1 = id1;
/*     */   }
/*     */ 
/*     */   public int getCompanyID()
/*     */   {
/* 102 */     return this.companyID;
/*     */   }
/*     */ 
/*     */   public void setCompanyID(int companyID)
/*     */   {
/* 112 */     this.companyID = companyID;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 120 */     CacheKey o = (CacheKey)obj;
/* 121 */     return (o.getId() == this.id) && (o.getCompanyID() == this.companyID) && (o.getId1() == this.id1);
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 129 */     int hash = 1;
/* 130 */     hash = hash * 31 + new Integer(this.id).hashCode();
/* 131 */     hash = hash * 31 + new Integer(this.id1).hashCode();
/* 132 */     hash = hash * 31 + new Integer(this.companyID).hashCode();
/* 133 */     return hash;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.cache.CacheKey
 * JD-Core Version:    0.6.2
 */