/*    */ package com.easytrack.commons.config;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class ConfigRoot
/*    */ {
/*    */   private HashMap configs;
/*    */ 
/*    */   public ConfigRoot()
/*    */   {
/* 14 */     this.configs = new HashMap();
/*    */   }
/*    */ 
/*    */   public void addConfig(CommonConfig config) {
/* 18 */     this.configs.put(config.getId(), config);
/*    */   }
/*    */ 
/*    */   public CommonConfig getConfig(String id) {
/* 22 */     return (CommonConfig)this.configs.get(id);
/*    */   }
/*    */ 
/*    */   public void print() {
/* 26 */     Iterator key = this.configs.keySet().iterator();
/* 27 */     while (key.hasNext()) {
/* 28 */       CommonConfig config = (CommonConfig)this.configs.get(key.next());
/* 29 */       System.out.println(config.getId() + " " + config.getValue());
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.config.ConfigRoot
 * JD-Core Version:    0.6.2
 */