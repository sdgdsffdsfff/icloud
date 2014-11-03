/*     */ package com.easytrack.commons.object;
/*     */ 
/*     */ import com.easytrack.commons.cache.CacheKey;
/*     */ import com.easytrack.commons.cache.CacheObject;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class BatchObject
/*     */   implements Serializable, CacheObject
/*     */ {
/*     */   private static final long serialVersionUID = -4730794302586583486L;
/*     */   private boolean isNew;
/*     */   private boolean deleted;
/*     */   private boolean modified;
/*     */   public int id;
/*     */   public int companyID;
/*     */ 
/*     */   public CacheKey getKey()
/*     */   {
/*  54 */     return new CacheKey(getId(), getCompanyID());
/*     */   }
/*     */ 
/*     */   public boolean isNew()
/*     */   {
/*  61 */     return this.isNew;
/*     */   }
/*     */ 
/*     */   public void setNew(boolean isNew)
/*     */   {
/*  68 */     this.isNew = isNew;
/*     */   }
/*     */ 
/*     */   public boolean isDeleted()
/*     */   {
/*  75 */     return this.deleted;
/*     */   }
/*     */ 
/*     */   public void setDeleted(boolean deleted)
/*     */   {
/*  82 */     this.deleted = deleted;
/*     */   }
/*     */ 
/*     */   public boolean isModified()
/*     */   {
/*  89 */     return this.modified;
/*     */   }
/*     */ 
/*     */   public void setModified(boolean modified)
/*     */   {
/*  96 */     this.modified = modified;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/* 103 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(int id)
/*     */   {
/* 110 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public int getCompanyID()
/*     */   {
/* 117 */     return this.companyID;
/*     */   }
/*     */ 
/*     */   public void setCompanyID(int companyID)
/*     */   {
/* 124 */     this.companyID = companyID;
/*     */   }
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 131 */     Object o = null;
/*     */     try {
/* 133 */       o = super.clone();
/*     */     } catch (Exception e) {
/*     */     }
/* 136 */     return o;
/*     */   }
/*     */ 
/*     */   public static List getNewList(List list)
/*     */   {
/* 145 */     List newList = new ArrayList();
/* 146 */     for (int i = 0; i < list.size(); i++) {
/* 147 */       BatchObject object = (BatchObject)list.get(i);
/* 148 */       if (object.isNew()) {
/* 149 */         newList.add(object);
/*     */       }
/*     */     }
/* 152 */     return newList;
/*     */   }
/*     */ 
/*     */   public static List getDeleteList(List list)
/*     */   {
/* 161 */     List newList = new ArrayList();
/* 162 */     for (int i = 0; i < list.size(); i++) {
/* 163 */       BatchObject object = (BatchObject)list.get(i);
/* 164 */       if (object.isDeleted()) {
/* 165 */         newList.add(object);
/*     */       }
/*     */     }
/* 168 */     return newList;
/*     */   }
/*     */ 
/*     */   public static List getNormalList(List list)
/*     */   {
/* 176 */     List newList = new ArrayList();
/* 177 */     for (int i = 0; i < list.size(); i++) {
/* 178 */       BatchObject object = (BatchObject)list.get(i);
/* 179 */       if ((!object.isNew()) && (!object.isDeleted())) {
/* 180 */         newList.add(object);
/*     */       }
/*     */     }
/* 183 */     return newList;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.object.BatchObject
 * JD-Core Version:    0.6.2
 */