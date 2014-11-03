/*    */ package com.easytrack.commons.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class MapUtils
/*    */ {
/*    */   public static Map sortMapByValue(Map map, Comparator comparator)
/*    */   {
/* 17 */     Map newMap = new LinkedHashMap();
/* 18 */     List list = new ArrayList();
/* 19 */     Iterator iterator = map.keySet().iterator();
/* 20 */     long m1 = System.currentTimeMillis();
/* 21 */     while (iterator.hasNext()) {
/* 22 */       Object key = iterator.next();
/* 23 */       Object value = map.get(key);
/* 24 */       Object[] key_value = new Object[2];
/* 25 */       key_value[0] = key;
/* 26 */       key_value[1] = value;
/* 27 */       list.add(key_value);
/*    */     }
/* 29 */     long m2 = System.currentTimeMillis();
/* 30 */     Comparator valueComparator = new Comparator() {
/*    */       public int compare(Object o1, Object o2) {
/* 32 */         Object[] key_value1 = (Object[])o1;
/* 33 */         Object[] key_value2 = (Object[])o2;
/* 34 */         return this.val$comparator.compare(key_value1[1], key_value2[1]);
/*    */       }
/*    */     };
/* 38 */     Collections.sort(list, valueComparator);
/* 39 */     long m3 = System.currentTimeMillis();
/* 40 */     for (int i = 0; i < list.size(); i++) {
/* 41 */       Object[] key_value = (Object[])list.get(i);
/* 42 */       newMap.put(key_value[0], key_value[1]);
/*    */     }
/* 44 */     long m4 = System.currentTimeMillis();
/* 45 */     System.out.println(m2 - m1);
/* 46 */     System.out.println(m3 - m2);
/* 47 */     System.out.println(m4 - m3);
/* 48 */     return newMap;
/*    */   }
/*    */ 
/*    */   public static Map sortMapByDoubleValue(Map map)
/*    */   {
/* 56 */     return sortMapByValue(map, new Comparator() {
/*    */       public int compare(Object o1, Object o2) {
/* 58 */         double double1 = ((Double)o1).doubleValue();
/* 59 */         double double2 = ((Double)o2).doubleValue();
/* 60 */         if (double2 > double1)
/* 61 */           return -1;
/* 62 */         if (double2 < double1)
/* 63 */           return 1;
/* 64 */         return 0;
/*    */       } } );
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 69 */     Map map = new HashMap();
/* 70 */     for (int i = 0; i < 1000000; i++) {
/* 71 */       map.put(new Integer(i), new Double(Math.random()));
/*    */     }
/* 73 */     map = sortMapByDoubleValue(map);
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.MapUtils
 * JD-Core Version:    0.6.2
 */