/*    */ package com.easytrack.commons.exception;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DocumentException extends Exception
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 2820040849097392626L;
/*    */ 
/*    */   public DocumentException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DocumentException(String message, Throwable cause)
/*    */   {
/* 25 */     super(message, cause);
/*    */   }
/*    */ 
/*    */   public DocumentException(String message)
/*    */   {
/* 33 */     super(message);
/*    */   }
/*    */ 
/*    */   public DocumentException(Throwable cause)
/*    */   {
/* 41 */     super(cause);
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.exception.DocumentException
 * JD-Core Version:    0.6.2
 */