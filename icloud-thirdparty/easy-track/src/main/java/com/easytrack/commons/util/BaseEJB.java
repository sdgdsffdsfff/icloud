/*    */ package com.easytrack.commons.util;
/*    */ 
/*    */ import java.rmi.RemoteException;
/*    */ import javax.ejb.CreateException;
/*    */ import javax.ejb.EJBException;
/*    */ import javax.ejb.SessionBean;
/*    */ import javax.ejb.SessionContext;
/*    */ 
/*    */ public class BaseEJB
/*    */   implements SessionBean
/*    */ {
/*    */   private static final long serialVersionUID = -8586465400961463032L;
/*    */   protected SessionContext context;
/*    */ 
/*    */   public void ejbCreate()
/*    */     throws CreateException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void ejbRemove()
/*    */     throws EJBException, RemoteException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void ejbActivate()
/*    */     throws EJBException, RemoteException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void ejbPassivate()
/*    */     throws EJBException, RemoteException
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setSessionContext(SessionContext newContext)
/*    */     throws EJBException
/*    */   {
/* 69 */     this.context = newContext;
/*    */   }
/*    */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.BaseEJB
 * JD-Core Version:    0.6.2
 */