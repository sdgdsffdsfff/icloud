/*     */ package com.easytrack.commons.config;
/*     */ 
/*     */ import java.io.FileReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import org.xml.sax.Attributes;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.DefaultHandler;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
/*     */ 
/*     */ public class ConfigSax extends DefaultHandler
/*     */ {
/*     */   protected static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
/*     */   private static final int ROOT_LEVEL = 2;
/*     */   private static final int LEAVE_LEVEL = 3;
/*  29 */   private int level = 0;
/*     */   private Hashtable rootNode;
/*     */   private Hashtable leaveNode;
/*     */   private String leaveKey;
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/*  45 */     XMLReader xr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
/*  46 */     ConfigSax handler = new ConfigSax();
/*  47 */     xr.setContentHandler(handler);
/*  48 */     xr.setErrorHandler(handler);
/*     */ 
/*  51 */     for (int i = 0; i < args.length; i++) {
/*  52 */       FileReader r = new FileReader(args[i]);
/*  53 */       xr.parse(new InputSource(r));
/*     */     }
/*  55 */     handler.dumpNode();
/*  56 */     Hashtable h = (Hashtable)handler.getNode().get("poem");
/*  57 */     System.out.println(h.get("b").toString());
/*     */   }
/*     */ 
/*     */   public void startDocument()
/*     */   {
/*  64 */     this.rootNode = new Hashtable();
/*     */   }
/*     */ 
/*     */   public void endDocument()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void startElement(String uri, String name, String qName, Attributes atts)
/*     */   {
/*  75 */     this.level += 1;
/*     */ 
/*  77 */     if (this.level == 2) {
/*  78 */       this.leaveNode = new Hashtable();
/*  79 */       this.rootNode.put(qName, this.leaveNode);
/*  80 */     } else if (this.level == 3) {
/*  81 */       this.leaveKey = qName;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void endElement(String uri, String name, String qName)
/*     */   {
/*  87 */     this.level -= 1;
/*     */   }
/*     */ 
/*     */   public void characters(char[] ch, int start, int length)
/*     */   {
/*  94 */     if (this.level == 3) {
/*  95 */       StringBuffer leaveValue = new StringBuffer();
/*  96 */       for (int i = start; i < start + length; i++) {
/*  97 */         switch (ch[i])
/*     */         {
/*     */         case '\\':
/* 100 */           break;
/*     */         case '"':
/* 103 */           break;
/*     */         case '\n':
/* 106 */           break;
/*     */         case '\r':
/* 109 */           break;
/*     */         case '\t':
/* 112 */           break;
/*     */         default:
/* 114 */           leaveValue.append(ch[i]);
/*     */         }
/*     */       }
/*     */ 
/* 118 */       this.leaveNode.put(this.leaveKey, leaveValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Hashtable getNode()
/*     */   {
/* 126 */     return this.rootNode;
/*     */   }
/*     */ 
/*     */   public void dumpNode()
/*     */   {
/* 132 */     dumpNode(this.rootNode);
/*     */   }
/*     */ 
/*     */   private void dumpNode(Hashtable node) {
/* 136 */     for (Enumeration e = node.keys(); e.hasMoreElements(); ) {
/* 137 */       String key = e.nextElement().toString();
/* 138 */       if ((node.get(key) instanceof Hashtable))
/* 139 */         dumpNode((Hashtable)node.get(key));
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.config.ConfigSax
 * JD-Core Version:    0.6.2
 */