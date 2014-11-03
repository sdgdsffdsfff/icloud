/*     */ package com.easytrack.commons.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.Provider;
/*     */ import java.security.SecureRandom;
/*     */ import java.security.Security;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.KeyGenerator;
/*     */ import javax.crypto.SecretKey;
/*     */ import sun.misc.BASE64Decoder;
/*     */ import sun.misc.BASE64Encoder;
/*     */ 
/*     */ public class CipherUtils
/*     */ {
/*  30 */   private static CipherUtils cu = new CipherUtils();
/*     */ 
/*  34 */   private String algorithm = "DES";
/*     */   private MessageDigest md;
/*     */   private KeyGenerator keygen;
/*     */   private SecretKey deskey;
/*     */   private Cipher c;
/*     */   private byte[] cipherByte;
/*  56 */   private BASE64Encoder encoder = new BASE64Encoder();
/*     */ 
/*  60 */   private BASE64Decoder decoder = new BASE64Decoder();
/*     */ 
/*  66 */   private String seed = "ceruleansoft1234%^";
/*     */ 
/*     */   private CipherUtils()
/*     */   {
/*  73 */     long before = System.currentTimeMillis();
/*  74 */     init();
/*  75 */     long after = System.currentTimeMillis();
/*  76 */     System.out.println("initialization takes: " + (after - before) + " ms");
/*     */   }
/*     */ 
/*     */   public static CipherUtils getInstance()
/*     */   {
/*  85 */     if (cu == null) {
/*  86 */       cu = new CipherUtils();
/*     */     }
/*  88 */     return cu;
/*     */   }
/*     */ 
/*     */   private void init()
/*     */   {
/*     */     try
/*     */     {
/*  96 */       String className = "com.sun.crypto.provider.SunJCE";
/*  97 */       String appServer = ServiceLocator.getAppServerName();
/*  98 */       if (appServer.equalsIgnoreCase("WEBSPHERE")) {
/*  99 */         className = "com.ibm.crypto.provider.IBMJCE";
/*     */       }
/* 101 */       Provider provider = (Provider)Class.forName(className).newInstance();
/* 102 */       Security.addProvider(provider);
/* 103 */       this.keygen = KeyGenerator.getInstance(this.algorithm);
/* 104 */       this.keygen.init(new SecureRandom(this.seed.getBytes()));
/* 105 */       this.deskey = this.keygen.generateKey();
/* 106 */       this.c = Cipher.getInstance(this.algorithm);
/*     */ 
/* 108 */       this.md = MessageDigest.getInstance("MD5");
/*     */     } catch (Exception ex) {
/* 110 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setSeed(String seed)
/*     */   {
/* 118 */     this.seed = seed;
/* 119 */     this.keygen.init(new SecureRandom(seed.getBytes()));
/* 120 */     this.deskey = this.keygen.generateKey();
/*     */   }
/*     */ 
/*     */   public SecretKey getDeskey()
/*     */   {
/* 127 */     return this.deskey;
/*     */   }
/*     */ 
/*     */   public void setDeskey(SecretKey deskey)
/*     */   {
/* 134 */     this.deskey = deskey;
/*     */   }
/*     */ 
/*     */   public synchronized String encrypt(String s)
/*     */   {
/*     */     try
/*     */     {
/* 143 */       this.c.init(1, this.deskey);
/* 144 */       this.cipherByte = this.c.doFinal(s.getBytes());
/*     */     } catch (Exception ex) {
/* 146 */       ex.printStackTrace();
/*     */     }
/* 148 */     return this.encoder.encode(this.cipherByte);
/*     */   }
/*     */ 
/*     */   public synchronized String decrypt(String s)
/*     */     throws Exception
/*     */   {
/*     */     try
/*     */     {
/* 165 */       byte[] ba = this.decoder.decodeBuffer(s);
/* 166 */       this.c.init(2, this.deskey);
/* 167 */       this.cipherByte = this.c.doFinal(ba);
/*     */     } catch (Exception ex) {
/* 169 */       ex.printStackTrace();
/* 170 */       throw ex;
/*     */     }
/* 172 */     return new String(this.cipherByte);
/*     */   }
/*     */ 
/*     */   public String digest(String s)
/*     */   {
/* 181 */     this.md.update(s.getBytes());
/* 182 */     return byte2Hex(this.md.digest());
/*     */   }
/*     */ 
/*     */   private static String byte2Hex(byte[] b)
/*     */   {
/* 190 */     StringBuffer hexString = new StringBuffer();
/*     */ 
/* 193 */     for (int i = 0; i < b.length; i++) {
/* 194 */       String tmp = Integer.toHexString(0xFF & b[i]);
/* 195 */       if (tmp.length() == 1)
/* 196 */         hexString.append("0");
/* 197 */       hexString.append(tmp);
/*     */     }
/* 199 */     return hexString.toString();
/*     */   }
/*     */ 
/*     */   public static String toMD5(String source)
/*     */   {
/* 207 */     String md5String = null;
/*     */     try {
/* 209 */       MessageDigest md = MessageDigest.getInstance("MD5");
/* 210 */       md.update(source.getBytes());
/* 211 */       md5String = byte2Hex(md.digest());
/*     */     } catch (Exception ex) {
/*     */     }
/* 214 */     return md5String;
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.CipherUtils
 * JD-Core Version:    0.6.2
 */