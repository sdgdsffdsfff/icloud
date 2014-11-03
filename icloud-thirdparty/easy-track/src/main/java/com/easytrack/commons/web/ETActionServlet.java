/*    */ package com.easytrack.commons.web;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.struts.action.ActionServlet;
/*    */ import org.apache.struts.config.ModuleConfig;
/*    */ import org.apache.struts.util.MessageResources;
/*    */ 
/*    */ public class ETActionServlet extends ActionServlet
/*    */ {
/*    */   private static final long serialVersionUID = -8597852357145618657L;
/*    */ 
/*    */   protected void process(HttpServletRequest request, HttpServletResponse response)
/*    */     throws IOException, ServletException
/*    */   {
/* 32 */     response.setContentType("text/html;charset=UTF-8");
/* 33 */     request.setCharacterEncoding("UTF-8");
/*    */ 
/* 35 */     String sAction = processPath(request, response);
/* 36 */     String sOperation = request.getParameter("operation");
/* 37 */     System.out.println("Current Module Name: " + sAction + " operation = " + sOperation);
/*    */ 
/* 39 */     processLockDocument(request, response);
/*    */ 
/* 41 */     super.process(request, response);
/*    */   }
/*    */ 
/*    */   private void processLockDocument(HttpServletRequest request, HttpServletResponse response) throws IOException
/*    */   {
/*    */   }
/*    */ 
/*    */   protected String processPath(HttpServletRequest request, HttpServletResponse response) throws IOException
/*    */   {
/* 50 */     String path = null;
/*    */ 
/* 53 */     path = (String)request.getAttribute("javax.servlet.include.path_info");
/* 54 */     if (path == null) {
/* 55 */       path = request.getPathInfo();
/*    */     }
/* 57 */     if ((path != null) && (path.length() > 0)) {
/* 58 */       return path;
/*    */     }
/*    */ 
/* 62 */     path = (String)request.getAttribute("javax.servlet.include.servlet_path");
/* 63 */     if (path == null) {
/* 64 */       path = request.getServletPath();
/*    */     }
/* 66 */     String prefix = getModuleConfig(request).getPrefix();
/* 67 */     if (!path.startsWith(prefix)) {
/* 68 */       log.error(getInternal().getMessage("processPath", request.getRequestURI()));
/* 69 */       response.sendError(400, getInternal().getMessage("processPath", request.getRequestURI()));
/* 70 */       return null;
/*    */     }
/* 72 */     path = path.substring(prefix.length());
/* 73 */     int slash = path.lastIndexOf("/");
/* 74 */     int period = path.lastIndexOf(".");
/* 75 */     if ((period >= 0) && (period > slash)) {
/* 76 */       path = path.substring(1, period);
/*    */     }
/* 78 */     return path;
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.web.ETActionServlet
 * JD-Core Version:    0.6.2
 */