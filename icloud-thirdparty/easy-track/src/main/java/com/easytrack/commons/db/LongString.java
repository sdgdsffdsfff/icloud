/*    */ package com.easytrack.commons.db;
/*    */ 
/*    */ public class LongString
/*    */ {
/*    */   private String value;
/*    */   private int size;
/*    */ 
/*    */   public LongString(String value)
/*    */   {
/* 29 */     if (value == null) {
/* 30 */       this.size = 0;
/* 31 */       this.value = "";
/*    */     } else {
/* 33 */       this.size = value.length();
/* 34 */       this.value = value;
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getSize()
/*    */   {
/* 43 */     return this.size;
/*    */   }
/*    */ 
/*    */   public void setSize(int size)
/*    */   {
/* 51 */     this.size = size;
/*    */   }
/*    */ 
/*    */   public String getValue()
/*    */   {
/* 58 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(String value)
/*    */   {
/* 66 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 73 */     return this.value;
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.LongString
 * JD-Core Version:    0.6.2
 */