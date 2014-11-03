/*    */ package com.easytrack.commons.db;
/*    */ 
/*    */ public class LongBinary
/*    */ {
/*    */   private byte[] value;
/*    */   private int size;
/*    */ 
/*    */   public LongBinary(byte[] value)
/*    */   {
/* 28 */     if (value == null) {
/* 29 */       this.size = 0;
/* 30 */       this.value = new byte[0];
/*    */     } else {
/* 32 */       this.size = value.length;
/* 33 */       this.value = value;
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getSize()
/*    */   {
/* 42 */     return this.size;
/*    */   }
/*    */ 
/*    */   public void setSize(int size)
/*    */   {
/* 50 */     this.size = size;
/*    */   }
/*    */ 
/*    */   public byte[] getValue()
/*    */   {
/* 58 */     if (this.value == null) {
/* 59 */       this.size = 0;
/* 60 */       this.value = new byte[0];
/*    */     }
/* 62 */     return this.value;
/*    */   }
/*    */ 
/*    */   public void setValue(byte[] value)
/*    */   {
/* 70 */     this.value = value;
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.LongBinary
 * JD-Core Version:    0.6.2
 */