/*     */ package com.easytrack.commons.util;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.apache.struts.util.MessageResources;
/*     */ 
/*     */ public class MessageUtils
/*     */ {
/*  25 */   private static MessageResources messages = MessageResources.getMessageResources("MessageResources");
/*     */ 
/*     */   public static String getMessage(String key)
/*     */   {
/*  33 */     return messages.getMessage((Locale)null, key, null);
/*     */   }
/*     */ 
/*     */   public static String getMessage(String key, Object[] args)
/*     */   {
/*  42 */     return messages.getMessage((Locale)null, key, args);
/*     */   }
/*     */ 
/*     */   public static String getMessage(String key, Object arg0)
/*     */   {
/*  53 */     return messages.getMessage((Locale)null, key, arg0);
/*     */   }
/*     */ 
/*     */   public static String getMessage(String key, Object arg0, Object arg1)
/*     */   {
/*  65 */     return messages.getMessage((Locale)null, key, arg0, arg1);
/*     */   }
/*     */ 
/*     */   public static String getMessage(String key, Object arg0, Object arg1, Object arg2)
/*     */   {
/*  78 */     return messages.getMessage((Locale)null, key, arg0, arg1, arg2);
/*     */   }
/*     */ 
/*     */   public static String getMessage(String key, Object arg0, Object arg1, Object arg2, Object arg3)
/*     */   {
/*  92 */     return messages.getMessage((Locale)null, key, arg0, arg1, arg2, arg3);
/*     */   }
/*     */ 
/*     */   public static String getMessage(Locale locale, String key)
/*     */   {
/* 102 */     return messages.getMessage(locale, key);
/*     */   }
/*     */ 
/*     */   public static String getMessage(Locale locale, String key, Object[] args)
/*     */   {
/* 113 */     return messages.getMessage(locale, key, args);
/*     */   }
/*     */ 
/*     */   public static String getMessage(Locale locale, String key, Object arg0)
/*     */   {
/* 124 */     return messages.getMessage(locale, key, new Object[] { arg0 });
/*     */   }
/*     */ 
/*     */   public static String getMessage(Locale locale, String key, Object arg0, Object arg1)
/*     */   {
/* 136 */     return messages.getMessage(locale, key, new Object[] { arg0, arg1 });
/*     */   }
/*     */ 
/*     */   public static String getMessage(Locale locale, String key, Object arg0, Object arg1, Object arg2)
/*     */   {
/* 149 */     return messages.getMessage(locale, key, new Object[] { arg0, arg1, arg2 });
/*     */   }
/*     */ 
/*     */   public static String getMessage(Locale locale, String key, Object arg0, Object arg1, Object arg2, Object arg3)
/*     */   {
/* 163 */     return messages.getMessage(locale, key, new Object[] { arg0, arg1, arg2, arg3 });
/*     */   }
/*     */ 
/*     */   public static boolean isPresent(String key)
/*     */   {
/* 172 */     return messages.isPresent(null, key);
/*     */   }
/*     */ 
/*     */   public static boolean isPresent(Locale locale, String key)
/*     */   {
/* 182 */     return messages.isPresent(locale, key);
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.MessageUtils
 * JD-Core Version:    0.6.2
 */