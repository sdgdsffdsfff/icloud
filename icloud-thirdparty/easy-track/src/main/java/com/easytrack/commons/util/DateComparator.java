/*    */ package com.easytrack.commons.util;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DateComparator
/*    */   implements Comparator
/*    */ {
/*    */   public int compare(Object o1, Object o2)
/*    */   {
/* 21 */     Date date1 = (Date)o1;
/* 22 */     Date date2 = (Date)o2;
/* 23 */     if (date2.after(date1))
/* 24 */       return -1;
/* 25 */     if (date2.before(date1)) {
/* 26 */       return 1;
/*    */     }
/* 28 */     return 0;
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.DateComparator
 * JD-Core Version:    0.6.2
 */