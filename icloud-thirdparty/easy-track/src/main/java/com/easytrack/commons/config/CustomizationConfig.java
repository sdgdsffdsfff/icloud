/*    */ package com.easytrack.commons.config;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import org.apache.commons.digester.Digester;
/*    */ 
/*    */ public class CustomizationConfig
/*    */ {
/*    */   private static ConfigRoot config;
/* 20 */   private static String CONF_PATH = "config" + File.separator + "customization.xml";
/*    */ 
/*    */   public static CommonConfig getConfig(String id)
/*    */   {
/* 11 */     if (config == null) {
/* 12 */       loadConfig();
/*    */     }
/* 14 */     return config.getConfig(id);
/*    */   }
/*    */ 
/*    */   private static synchronized void loadConfig()
/*    */   {
/*    */     try
/*    */     {
/* 24 */       File f = new File(System.getProperty("HOMEDIR") + File.separator + CONF_PATH);
/* 25 */       Digester digester = new Digester();
/* 26 */       digester.setValidating(false);
/* 27 */       config = new ConfigRoot();
/* 28 */       digester.push(config);
/* 29 */       digester.addObjectCreate("config/CommonConfig", CommonConfig.class);
/* 30 */       digester.addSetProperties("config/CommonConfig");
/* 31 */       digester.addBeanPropertySetter("config/CommonConfig/id", "id");
/* 32 */       digester.addBeanPropertySetter("config/CommonConfig/name", "name");
/* 33 */       digester.addBeanPropertySetter("config/CommonConfig/value", "value");
/* 34 */       digester.addBeanPropertySetter("config/CommonConfig/description", "description");
/* 35 */       digester.addSetNext("config/CommonConfig", "addConfig");
/* 36 */       digester.parse(f);
/*    */     } catch (Exception e) {
/* 38 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) throws Exception
/*    */   {
/* 44 */     CommonConfig config = getConfig("1100");
/* 45 */     System.out.println(config.getValue());
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.config.CustomizationConfig
 * JD-Core Version:    0.6.2
 */