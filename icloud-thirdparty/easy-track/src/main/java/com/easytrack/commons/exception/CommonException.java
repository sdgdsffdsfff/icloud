/*     */ package com.easytrack.commons.exception;
/*     */ 
/*     */ import com.easytrack.commons.util.MessageUtils;
/*     */ import java.io.PrintStream;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class CommonException extends Exception
/*     */ {
/*     */   private static final long serialVersionUID = 4521708373384130965L;
/*     */   private int type;
/*     */   private String code;
/*  22 */   private List params = new ArrayList();
/*     */   private Object errorObject;
/*     */   private Throwable cause;
/*     */ 
/*     */   public CommonException(Throwable cause, String code)
/*     */   {
/*  37 */     this(cause == null ? "" : cause.getMessage(), cause, code);
/*     */   }
/*     */ 
/*     */   public CommonException(String s, Throwable cause, String code)
/*     */   {
/*  46 */     this(s, code);
/*  47 */     this.cause = cause;
/*     */   }
/*     */ 
/*     */   public CommonException(Throwable cause)
/*     */   {
/*  54 */     this(cause == null ? "" : cause.getMessage(), cause);
/*     */   }
/*     */ 
/*     */   public CommonException(String s, Throwable cause)
/*     */   {
/*  62 */     this(s, "");
/*  63 */     this.cause = cause;
/*     */   }
/*     */ 
/*     */   public Throwable getCause()
/*     */   {
/*  71 */     return this.cause;
/*     */   }
/*     */ 
/*     */   public CommonException(String aCode)
/*     */   {
/*  78 */     this.code = aCode;
/*     */   }
/*     */ 
/*     */   public CommonException(String aCode, List aParams)
/*     */   {
/*  87 */     this.code = aCode;
/*  88 */     this.params = aParams;
/*     */   }
/*     */ 
/*     */   public CommonException(String s, String code)
/*     */   {
/*  96 */     super(s);
/*  97 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public CommonException(Object o, String s, String code)
/*     */   {
/* 106 */     super(s);
/* 107 */     this.code = code;
/* 108 */     this.errorObject = o;
/*     */   }
/*     */ 
/*     */   public Object getErrorObject()
/*     */   {
/* 115 */     return this.errorObject;
/*     */   }
/*     */ 
/*     */   public void setErrorObject(Object errorObject)
/*     */   {
/* 122 */     this.errorObject = errorObject;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 129 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 136 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public int getType()
/*     */   {
/* 143 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void addParam(String aParam)
/*     */   {
/* 150 */     if (this.params == null)
/* 151 */       this.params = new ArrayList();
/* 152 */     this.params.add(aParam);
/* 153 */     System.out.println("param: " + aParam);
/*     */   }
/*     */ 
/*     */   public static CommonException handleSQLException(Exception e)
/*     */   {
/* 162 */     CommonException eRet = null;
/* 163 */     if ((e instanceof SQLException)) {
/* 164 */       String errorMsg = e.getMessage();
/* 165 */       if ((errorMsg.toLowerCase().indexOf("duplicate key") > 0) || (errorMsg.toLowerCase().indexOf("unique constraint") > 0))
/*     */       {
/* 167 */         eRet = new CommonException("Failed when insert duplicate key.", "XPC-09008");
/* 168 */       } else if (errorMsg.toLowerCase().indexOf("delete statement") > 0)
/* 169 */         eRet = new CommonException("Failed when delete object.", "XPC-00010");
/*     */       else
/* 171 */         eRet = new CommonException("Unkown Server error", "XPC-09009");
/*     */     }
/* 173 */     else if ((e instanceof NullPointerException)) {
/* 174 */       eRet = new CommonException("The Object is not found", "XPC-09011");
/* 175 */     } else if ((e instanceof CommonException)) {
/* 176 */       eRet = (CommonException)e;
/*     */     } else {
/* 178 */       eRet = new CommonException("Unkown Server error", "XPC-09009");
/*     */     }
/* 180 */     return eRet;
/*     */   }
/*     */ 
/*     */   public String getLocalMsg(Locale locale)
/*     */   {
/* 188 */     this.code = (this.code == null ? "" : this.code);
/* 189 */     String errorMsg = MessageUtils.getMessage(locale, this.code, this.params.toArray());
/* 190 */     if ("".equals(errorMsg)) {
/* 191 */       errorMsg = getMessage();
/*     */     }
/* 193 */     return errorMsg;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.exception.CommonException
 * JD-Core Version:    0.6.2
 */