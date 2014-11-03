/*     */ package com.easytrack.commons.web;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.servlet.Filter;
/*     */ import javax.servlet.FilterChain;
/*     */ import javax.servlet.FilterConfig;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class EventLogFilter
/*     */   implements Filter
/*     */ {
/*  38 */   protected FilterConfig filterConfig = null;
/*     */ 
/*  43 */   protected Log log = LogFactory.getLog(getClass());
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  52 */     this.filterConfig = null;
/*     */   }
/*     */ 
/*     */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*     */     throws IOException, ServletException
/*     */   {
/*  74 */     long before = System.currentTimeMillis();
/*     */ 
/*  84 */     chain.doFilter(request, response);
/*     */ 
/*  86 */     long after = System.currentTimeMillis();
/*  87 */     StringBuffer sb = new StringBuffer();
/*  88 */     sb.append("Request URI:" + ((HttpServletRequest)request).getRequestURI());
/*  89 */     sb.append(" Server response time:" + (after - before) + "ms.");
/*  90 */     this.log.info(sb.toString());
/*     */   }
/*     */ 
/*     */   public void init(FilterConfig filterConfig)
/*     */     throws ServletException
/*     */   {
/* 101 */     this.filterConfig = filterConfig;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.web.EventLogFilter
 * JD-Core Version:    0.6.2
 */