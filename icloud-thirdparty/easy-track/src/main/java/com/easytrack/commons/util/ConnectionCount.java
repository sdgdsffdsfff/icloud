/*    */ package com.easytrack.commons.util;
/*    */ 
/*    */ public class ConnectionCount
/*    */ {
/* 10 */   private int retrieveCount = 0;
/*    */ 
/* 14 */   private int closeCount = 0;
/*    */ 
/*    */   public ConnectionCount()
/*    */   {
/* 19 */     this.retrieveCount = 0;
/* 20 */     this.closeCount = 0;
/*    */   }
/*    */ 
/*    */   public ConnectionCount(int retrieveCount, int closeCount)
/*    */   {
/* 28 */     this.retrieveCount = retrieveCount;
/* 29 */     this.closeCount = closeCount;
/*    */   }
/*    */ 
/*    */   public int getRetrieveCount()
/*    */   {
/* 36 */     return this.retrieveCount;
/*    */   }
/*    */ 
/*    */   public void setRetrieveCount(int retrieveCount)
/*    */   {
/* 43 */     this.retrieveCount = retrieveCount;
/*    */   }
/*    */ 
/*    */   public void increaseRetrieveCount()
/*    */   {
/* 49 */     this.retrieveCount += 1;
/*    */   }
/*    */ 
/*    */   public int getCloseCount()
/*    */   {
/* 56 */     return this.closeCount;
/*    */   }
/*    */ 
/*    */   public void setCloseCount(int closeCount)
/*    */   {
/* 63 */     this.closeCount = closeCount;
/*    */   }
/*    */ 
/*    */   public void increaseCloseCount()
/*    */   {
/* 69 */     this.closeCount += 1;
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.ConnectionCount
 * JD-Core Version:    0.6.2
 */