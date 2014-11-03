/*     */ package com.easytrack.commons.object;
/*     */ 
/*     */ import com.easytrack.commons.cache.CacheObject;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ETObject extends BatchObject
/*     */   implements Serializable, CacheObject
/*     */ {
/*     */   private static final long serialVersionUID = 6547861051229051125L;
/*     */   public int createBy;
/*     */   public int lastUpdateBy;
/*     */   public Date createTime;
/*     */   public Date lastUpdateTime;
/*     */   public int owner;
/*     */   public int objectType;
/*     */   public int detailType;
/*     */   public int projectID;
/*     */   public int permission;
/*     */ 
/*     */   public static ETObject getObject(int id, List list)
/*     */   {
/*  79 */     if (list != null) {
/*  80 */       for (int i = 0; i < list.size(); i++) {
/*  81 */         ETObject obj1 = (ETObject)list.get(i);
/*  82 */         if (obj1.getId() == id) {
/*  83 */           return obj1;
/*     */         }
/*     */       }
/*     */     }
/*  87 */     return null;
/*     */   }
/*     */ 
/*     */   public int getPermission()
/*     */   {
/*  94 */     return this.permission;
/*     */   }
/*     */ 
/*     */   public void setPermission(int permission)
/*     */   {
/* 101 */     this.permission = permission;
/*     */   }
/*     */ 
/*     */   public int getOID()
/*     */   {
/* 108 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setOID(int oid)
/*     */   {
/* 115 */     this.id = oid;
/*     */   }
/*     */ 
/*     */   public String getID()
/*     */   {
/* 121 */     return "" + this.id;
/*     */   }
/*     */ 
/*     */   public void setID(String id)
/*     */   {
/* 128 */     this.id = Integer.parseInt(id);
/*     */   }
/*     */ 
/*     */   public Date getCreateTime()
/*     */   {
/* 135 */     return this.createTime;
/*     */   }
/*     */ 
/*     */   public void setCreateTime(Date createTime)
/*     */   {
/* 142 */     this.createTime = createTime;
/*     */   }
/*     */ 
/*     */   public int getDetailType()
/*     */   {
/* 149 */     return this.detailType;
/*     */   }
/*     */ 
/*     */   public void setDetailType(int detailType)
/*     */   {
/* 156 */     this.detailType = detailType;
/*     */   }
/*     */ 
/*     */   public Date getLastUpdateTime()
/*     */   {
/* 163 */     return this.lastUpdateTime;
/*     */   }
/*     */ 
/*     */   public void setLastUpdateTime(Date lastUpdateTime)
/*     */   {
/* 170 */     this.lastUpdateTime = lastUpdateTime;
/*     */   }
/*     */ 
/*     */   public int getObjectType()
/*     */   {
/* 177 */     return this.objectType;
/*     */   }
/*     */ 
/*     */   public void setObjectType(int objectType)
/*     */   {
/* 184 */     this.objectType = objectType;
/*     */   }
/*     */ 
/*     */   public int getOwner()
/*     */   {
/* 191 */     return this.owner;
/*     */   }
/*     */ 
/*     */   public void setOwner(int owner)
/*     */   {
/* 198 */     this.owner = owner;
/*     */   }
/*     */ 
/*     */   public int getProjectID()
/*     */   {
/* 205 */     return this.projectID;
/*     */   }
/*     */ 
/*     */   public void setProjectID(int projectID)
/*     */   {
/* 212 */     this.projectID = projectID;
/*     */   }
/*     */ 
/*     */   public int getCreateBy()
/*     */   {
/* 219 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(int createBy)
/*     */   {
/* 226 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public int getLastUpdateBy()
/*     */   {
/* 233 */     return this.lastUpdateBy;
/*     */   }
/*     */ 
/*     */   public void setLastUpdateBy(int lastUpdateBy)
/*     */   {
/* 240 */     this.lastUpdateBy = lastUpdateBy;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.object.ETObject
 * JD-Core Version:    0.6.2
 */