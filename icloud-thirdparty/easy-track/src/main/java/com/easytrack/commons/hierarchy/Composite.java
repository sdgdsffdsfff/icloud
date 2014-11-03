/*     */ package com.easytrack.commons.hierarchy;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class Composite
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7104162277282276094L;
/*  11 */   private Hierarchyable node = null;
/*     */ 
/*  15 */   private ArrayList<Composite> childs = new ArrayList();
/*     */   private int level;
/*     */ 
/*     */   public Composite(Hierarchyable obj)
/*     */   {
/*  28 */     this.node = obj;
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/*  37 */     return this.node.getId();
/*     */   }
/*     */ 
/*     */   public int getParentID()
/*     */   {
/*  46 */     return this.node.getParentID();
/*     */   }
/*     */ 
/*     */   public void setParentID(int parentID)
/*     */   {
/*  56 */     this.node.setParentID(parentID);
/*     */   }
/*     */ 
/*     */   public int getLevel()
/*     */   {
/*  65 */     return this.level;
/*     */   }
/*     */ 
/*     */   public void setLevel(int level)
/*     */   {
/*  75 */     this.level = level;
/*     */   }
/*     */ 
/*     */   public boolean hasChild()
/*     */   {
/*  84 */     return this.childs.size() == 0;
/*     */   }
/*     */ 
/*     */   public int getChildCount()
/*     */   {
/*  93 */     return this.childs.size();
/*     */   }
/*     */ 
/*     */   public ArrayList<Composite> getChilds()
/*     */   {
/* 100 */     return this.childs;
/*     */   }
/*     */ 
/*     */   public void setChilds(ArrayList<Composite> childs)
/*     */   {
/* 108 */     this.childs = childs;
/*     */   }
/*     */ 
/*     */   public Hierarchyable getNode()
/*     */   {
/* 117 */     return this.node;
/*     */   }
/*     */ 
/*     */   public void setNode(Hierarchyable node)
/*     */   {
/* 125 */     this.node = node;
/*     */   }
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 135 */     Composite o = (Composite)obj;
/* 136 */     return getId() == o.getId();
/*     */   }
/*     */ 
/*     */   public void print() {
/* 140 */     StringBuilder sb = new StringBuilder();
/* 141 */     sb.append(getId());
/* 142 */     sb.append(":[");
/* 143 */     for (Composite c : this.childs) {
/* 144 */       sb.append(",");
/* 145 */       sb.append(c.getId());
/*     */     }
/* 147 */     sb.append("]");
/* 148 */     System.out.println(sb.toString());
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.hierarchy.Composite
 * JD-Core Version:    0.6.2
 */