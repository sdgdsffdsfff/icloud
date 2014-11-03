/*     */ package com.easytrack.commons.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ public class CookieUtils
/*     */ {
/*     */   public static final String RESOURCE_VIEW = "ET_ResourceView";
/*     */   public static final String PORTFOLIO_PROJECT_COST = "ET_PortfolioprojectCost";
/*     */   public static final String RESOURCE_ACTUAL_HOUR = "ET_ResourceActualHour";
/*     */   public static final String RESOURCE_PLAN_HOUR = "ET_ResourcePlanHour";
/*     */   public static final String RESOURCE_ALLOCATION = "ET_ResourceAllocation";
/*     */   public static final String RESOURCE_ACTUAL_MONITOR = "ET_ResourceActualMonitor";
/*     */   public static final String RESOURCE_ACTUAL_MONITOR_DETAIL = "ET_ResourceActualMonitorDetail";
/*     */   public static final String RESOURCE_PLAN_MONITOR = "ET_ResourcePlanMonitor";
/*     */   public static final String RESOURCE_PLAN_MONITOR_DETAIL = "ET_ResourcePlanMonitorDetail";
/*     */   public static final String RESOURCE_TASK_MONITOR = "ET_ResourceTaskMonitor";
/*     */   public static final String RESOURCE_NON_PROJECT = "ET_ResourceNonProject";
/*     */   public static final String RESOURCE_TREE = "ET_ResourceTree";
/*     */   public static final String RESOURCE_CAPACITY = "ET_ResourceCapacity";
/*     */   public static final String WORKITEM_ACTUAL = "ET_WorkitemActual";
/*     */   public static final String DEPARTMENT_TREE = "ET_Department";
/*     */   public static final String RESOURCE_MONITOR_VIEW = "ET_ResourceMonitorView";
/*     */   public static final String PROJECT_USER_ROLE = "ET_ProjectUserRole";
/*     */   public static final String RESOURCE_USER_ROLE = "ET_ResourceUserRole";
/*     */   public static final String PORTFOLIO_USER_ROLE = "ET_PortfolioUserRole";
/*     */   public static final String SCHEDULE_TASK = "ET_ProjectTask";
/*     */   public static final String SYSTEMLOG_TASKWORKLOADSTAT = "ET_TaskWorkloadStat";
/*     */   public static final String RESOURCESELECT_TREE = "ET_ResourceSelectTree";
/*     */ 
/*     */   private static Cookie getCookie(HttpServletRequest request, String name)
/*     */   {
/* 339 */     Cookie[] cookies = request.getCookies();
/* 340 */     for (int i = 0; i < cookies.length; i++) {
/* 341 */       if (name.equals(cookies[i].getName())) {
/* 342 */         return cookies[i];
/*     */       }
/*     */     }
/* 345 */     return null;
/*     */   }
/*     */ 
/*     */   public static Set manageTreeCookie(HttpServletRequest request, HttpServletResponse response, String name)
/*     */   {
/* 355 */     Cookie cookie = getCookie(request, name);
/* 356 */     if ((cookie == null) || ("".equals(cookie.getValue().trim()))) {
/* 357 */       return new HashSet();
/*     */     }
/* 359 */     String value = cookie.getValue();
/* 360 */     String[] ids = value.split("\\.");
/* 361 */     Set returnSet = new HashSet();
/* 362 */     for (int i = 0; i < ids.length; i++) {
/* 363 */       String sID = ids[i];
/*     */       try {
/* 365 */         int id = Integer.parseInt(sID);
/* 366 */         returnSet.add(new Integer(id));
/*     */       } catch (NumberFormatException e) {
/*     */       }
/*     */     }
/* 370 */     StringBuffer sb = new StringBuffer();
/* 371 */     Iterator iterator = returnSet.iterator();
/* 372 */     while (iterator.hasNext()) {
/* 373 */       Integer id = (Integer)iterator.next();
/* 374 */       sb.append(id);
/* 375 */       if (iterator.hasNext()) {
/* 376 */         sb.append(".");
/*     */       }
/*     */     }
/*     */ 
/* 380 */     cookie.setValue(sb.toString());
/* 381 */     response.addCookie(cookie);
/* 382 */     return returnSet;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 387 */     String a = "";
/* 388 */     Integer.parseInt(a);
/* 389 */     String[] aa = a.split(",");
/* 390 */     System.out.print(aa.length);
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.CookieUtils
 * JD-Core Version:    0.6.2
 */