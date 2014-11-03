/*     */ package com.easytrack.commons.config;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.XMLReader;
/*     */ import org.xml.sax.helpers.XMLReaderFactory;
/*     */ 
/*     */ public class Config
/*     */ {
/*     */   public static final String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
/*     */   public static final String HOME_DIR_KEY = "HOMEDIR";
/*  39 */   public static String HOME_DIR_VALUE = null;
/*     */ 
/*  43 */   private static String CONF_PATH = "config" + File.separator + "config.xml";
/*     */   private static final String URL_KEY = "URL";
/*     */   private static final String URL_HOME_KEY = "HOME";
/*     */   private static final String SEPARATOR = "$SEPARATOR$";
/*  59 */   private static Hashtable etConfig = null;
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*  65 */     System.out.println(System.getProperty("HOMEDIR"));
/*     */   }
/*     */ 
/*     */   public static String getHomeDir()
/*     */   {
/*  78 */     if (HOME_DIR_VALUE == null) {
/*  79 */       loadConfig();
/*     */     }
/*     */ 
/*  82 */     return HOME_DIR_VALUE;
/*     */   }
/*     */ 
/*     */   public static String getHomeURL()
/*     */   {
/*  91 */     return getConfig("URL", "HOME");
/*     */   }
/*     */ 
/*     */   public static String getConfig(String session, String key)
/*     */   {
/* 106 */     Hashtable h = getConfig(session);
/* 107 */     if ((h == null) || (h.get(key) == null)) {
/* 108 */       return null;
/*     */     }
/*     */ 
/* 111 */     return h.get(key).toString().trim();
/*     */   }
/*     */ 
/*     */   public static Hashtable getConfig(String session)
/*     */   {
/* 125 */     if (etConfig == null) {
/* 126 */       loadConfig();
/*     */     }
/*     */ 
/* 129 */     return (Hashtable)etConfig.get(session);
/*     */   }
/*     */ 
/*     */   public static boolean isDisable(String session, String key)
/*     */   {
/* 143 */     String value = getConfig(session, key);
/* 144 */     return isDisable(value);
/*     */   }
/*     */ 
/*     */   public static boolean isDisable(String value)
/*     */   {
/* 155 */     if (value == null) {
/* 156 */       return true;
/*     */     }
/* 158 */     if (value.trim().equalsIgnoreCase("DISABLE")) {
/* 159 */       return true;
/*     */     }
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */   public static synchronized void loadConfig()
/*     */   {
/* 170 */     String sHomeDir = System.getProperty("HOMEDIR");
/* 171 */     System.out.println("sHomeDir===================" + sHomeDir);
/* 172 */     if ((sHomeDir == null) || ("".equals(sHomeDir))) {
/* 173 */       sHomeDir = "D:\\EasyTrack";
/*     */     }
/*     */ 
/* 176 */     Hashtable sysConfig = new Hashtable();
/* 177 */     sysConfig.put("HOMEDIR", sHomeDir);
/* 178 */     ConfigSax handler = new ConfigSax();
/*     */     try
/*     */     {
/* 182 */       XMLReader xr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
/* 183 */       xr.setContentHandler(handler);
/* 184 */       xr.setErrorHandler(handler);
/*     */ 
/* 188 */       HOME_DIR_VALUE = sysConfig.get("HOMEDIR").toString() + File.separator;
/* 189 */       InputStreamReader r = new InputStreamReader(new FileInputStream(HOME_DIR_VALUE + CONF_PATH), "UTF-8");
/*     */ 
/* 193 */       xr.parse(new InputSource(r));
/* 194 */       r.close();
/*     */     }
/*     */     catch (FileNotFoundException ex) {
/* 197 */       System.err.println("Can't find the config.xml, please use -DHOMEDIR=homedir to set the homedir.");
/*     */     } catch (Exception e) {
/* 199 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 202 */     etConfig = handler.getNode();
/*     */ 
/* 204 */     if (etConfig == null) {
/* 205 */       System.err.println(" config file not found. only load the system properites");
/* 206 */       etConfig = new Hashtable();
/*     */     }
/*     */ 
/* 209 */     dumpNode(etConfig);
/*     */ 
/* 211 */     etConfig.put("SYSTEM", sysConfig);
/*     */   }
/*     */ 
/*     */   protected static StringBuffer variableReplace(StringBuffer in)
/*     */   {
/* 224 */     String var = in.toString();
/*     */     while (true)
/*     */     {
/* 227 */       int start = var.indexOf("{HOMEDIR}");
/* 228 */       if (start == -1) break;
/* 229 */       var = var.substring(0, start) + HOME_DIR_VALUE + var.substring(start + "HOMEDIR".length() + 2);
/*     */     }
/*     */ 
/* 234 */     var = replaceFileSeparator(var);
/*     */ 
/* 236 */     if (!var.equals(in)) {
/* 237 */       return new StringBuffer(var);
/*     */     }
/* 239 */     return null;
/*     */   }
/*     */ 
/*     */   protected static String replaceFileSeparator(String path)
/*     */   {
/* 250 */     String returnValue = path;
/* 251 */     String sep = System.getProperty("file.separator");
/*     */     while (true)
/*     */     {
/* 254 */       int i = returnValue.indexOf("$SEPARATOR$");
/* 255 */       if (i == -1) break;
/* 256 */       returnValue = returnValue.substring(0, i) + sep + returnValue.substring(i + "$SEPARATOR$".length(), returnValue.length());
/*     */     }
/*     */ 
/* 261 */     return returnValue;
/*     */   }
/*     */ 
/*     */   protected static void dumpNode(Hashtable node)
/*     */   {
/* 272 */     for (Enumeration e = node.keys(); e.hasMoreElements(); ) {
/* 273 */       String key = e.nextElement().toString();
/* 274 */       if ((node.get(key) instanceof Hashtable)) {
/* 275 */         dumpNode((Hashtable)node.get(key));
/*     */       } else {
/* 277 */         StringBuffer vr = variableReplace((StringBuffer)node.get(key));
/* 278 */         if (vr != null) {
/* 279 */           node.remove(key);
/* 280 */           node.put(key, vr);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.config.Config
 * JD-Core Version:    0.6.2
 */