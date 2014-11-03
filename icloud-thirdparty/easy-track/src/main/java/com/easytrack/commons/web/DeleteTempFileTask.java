/*    */ package com.easytrack.commons.web;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.TimerTask;
/*    */ import javax.servlet.ServletConfig;
/*    */ import javax.servlet.ServletContext;
/*    */ 
/*    */ public class DeleteTempFileTask extends TimerTask
/*    */ {
/*    */   private ServletConfig config;
/*    */ 
/*    */   public DeleteTempFileTask(ServletConfig c)
/*    */   {
/* 14 */     this.config = c;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/*    */     try {
/* 20 */       String basePath = this.config.getServletContext().getRealPath("/");
/* 21 */       File f = new File(basePath + "temp/");
/* 22 */       File[] files = f.listFiles();
/* 23 */       for (int i = 0; i < files.length; i++)
/*    */         try {
/* 25 */           files[i].delete();
/*    */         } catch (Exception ee) {
/* 27 */           ee.printStackTrace();
/*    */         }
/*    */     }
/*    */     catch (Exception e) {
/* 31 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.web.DeleteTempFileTask
 * JD-Core Version:    0.6.2
 */