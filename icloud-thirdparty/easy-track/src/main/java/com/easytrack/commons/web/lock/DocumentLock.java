/*     */ package com.easytrack.commons.web.lock;
/*     */ 
/*     */ import com.easytrack.commons.cache.CacheKey;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ 
/*     */ public final class DocumentLock
/*     */ {
/*  15 */   private static Map locks = Collections.synchronizedMap(new HashMap());
/*     */ 
/*  17 */   private static Map loginSessions = Collections.synchronizedMap(new HashMap());
/*     */ 
/*     */   public static boolean isLogined(int companyID, int userID) {
/*     */     try {
/*  21 */       synchronized (loginSessions) {
/*  22 */         return loginSessions.containsKey(new CacheKey(userID, companyID));
/*     */       }
/*     */     } catch (Exception e) {
/*     */     }
/*  26 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isSessionLogined(int companyID, int userID, HttpSession session)
/*     */   {
/*     */     try
/*     */     {
/*     */       Iterator it;
/*  31 */       synchronized (loginSessions) {
/*  32 */         for (it = loginSessions.keySet().iterator(); it.hasNext(); ) {
/*  33 */           CacheKey key = (CacheKey)it.next();
/*  34 */           HttpSession value = (HttpSession)loginSessions.get(key);
/*  35 */           if ((value != null) && (value.getId() == session.getId())) {
/*  36 */             if ((key.getCompanyID() == companyID) && (key.getId() == userID)) break;
/*  37 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*  45 */     return false;
/*     */   }
/*     */ 
/*     */   public static void removeLoginUser(int companyID, int userID, HttpSession session) {
/*     */     try {
/*  50 */       System.out.println("loginSessions.size():" + loginSessions.size());
/*  51 */       synchronized (loginSessions) {
/*     */         try {
/*  53 */           unlockAll(companyID, userID);
/*  54 */           HttpSession s = (HttpSession)loginSessions.get(new CacheKey(userID, companyID));
/*  55 */           if (s != null) {
/*  56 */             loginSessions.remove(new CacheKey(userID, companyID));
/*  57 */             s.invalidate();
/*     */           }
/*     */         } catch (Exception e) {
/*     */         }
/*  61 */         System.out.println("Destory login user" + userID + " login session = " + session.getId());
/*     */       }
/*  63 */       System.out.println("locks.size():" + locks.size());
/*     */     } catch (Exception e) {
/*  65 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void removeUser(HttpSession session)
/*     */   {
/*     */     try {
/*  72 */       synchronized (loginSessions) {
/*  73 */         System.out.println("loginSessions.size():" + loginSessions.size());
/*  74 */         for (Iterator it = loginSessions.keySet().iterator(); it.hasNext(); ) {
/*  75 */           CacheKey key = (CacheKey)it.next();
/*  76 */           HttpSession value = (HttpSession)loginSessions.get(key);
/*  77 */           if ((value != null) && (value.getId() == session.getId())) {
/*  78 */             loginSessions.remove(key);
/*  79 */             unlockAll(key.getCompanyID(), key.getId());
/*  80 */             break;
/*     */           }
/*     */         }
/*  83 */         System.out.println("locks.size():" + locks.size());
/*     */       }
/*     */     } catch (Exception e) {
/*  86 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void addLoginUser(int companyID, int userID, HttpServletRequest request) {
/*     */     try {
/*  92 */       HttpSession session = request.getSession();
/*  93 */       loginSessions.put(new CacheKey(userID, companyID), session);
/*  94 */       System.out.println("add user" + userID + " session  = " + session);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static CacheKey lock(int companyID, int userID, int objectID, int objectType)
/*     */   {
/*     */     try
/*     */     {
/* 110 */       synchronized (locks) {
/* 111 */         locks.put(new CacheKey(objectID, objectType, companyID), new CacheKey(userID, companyID));
/*     */       }
/*     */     } catch (Exception e) {
/*     */     }
/* 115 */     return null;
/*     */   }
/*     */ 
/*     */   public static void unlockAll(int companyID, int userID)
/*     */   {
/*     */     try
/*     */     {
/*     */       Iterator it;
/* 128 */       synchronized (locks) {
/* 129 */         for (it = locks.keySet().iterator(); it.hasNext(); ) {
/* 130 */           CacheKey key = (CacheKey)it.next();
/* 131 */           CacheKey value = (CacheKey)locks.get(key);
/* 132 */           if ((value != null) && (value.getId() == userID) && (value.getCompanyID() == companyID))
/* 133 */             locks.remove(key);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void unlockAll(int companyID, int userID, int objectType) {
/*     */     Iterator it;
/*     */     try {
/* 144 */       for (it = locks.keySet().iterator(); it.hasNext(); ) {
/* 145 */         CacheKey key = (CacheKey)it.next();
/* 146 */         CacheKey value = (CacheKey)locks.get(key);
/* 147 */         if ((value != null) && (key.getId1() == objectType) && (value.getId() == userID) && (value.getCompanyID() == companyID))
/*     */         {
/* 149 */           locks.remove(key);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void unlock(int companyID, int objectID, int objectType)
/*     */   {
/*     */     try
/*     */     {
/* 168 */       synchronized (locks) {
/* 169 */         locks.remove(new CacheKey(objectID, objectType, companyID));
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public static CacheKey lockedBy(int companyID, int objectID, int objectType)
/*     */   {
/*     */     try
/*     */     {
/* 188 */       synchronized (locks) {
/* 189 */         return (CacheKey)locks.get(new CacheKey(objectID, objectType, companyID));
/*     */       }
/*     */     } catch (Exception e) {
/*     */     }
/* 193 */     return null;
/*     */   }
/*     */ 
/*     */   public static void listAllUsers()
/*     */   {
/*     */   }
/*     */ 
/*     */   public static Map getLocks()
/*     */   {
/* 203 */     return locks;
/*     */   }
/*     */ 
/*     */   public static CacheKey getOnlineuser(int companyID, int userID) {
/* 207 */     for (Iterator it = locks.keySet().iterator(); it.hasNext(); ) {
/* 208 */       CacheKey key = (CacheKey)it.next();
/* 209 */       CacheKey value = (CacheKey)locks.get(key);
/* 210 */       if ((value != null) && (userID == value.getId()) && (companyID == value.getCompanyID())) {
/* 211 */         return value;
/*     */       }
/*     */     }
/* 214 */     return null;
/*     */   }
/*     */ 
/*     */   public static Map getLoginSessions() {
/* 218 */     return loginSessions;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.web.lock.DocumentLock
 * JD-Core Version:    0.6.2
 */