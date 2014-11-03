/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import com.easytrack.commons.cache.CacheManager;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class SequenceUtils
/*     */ {
/*  22 */   private CacheManager seqs = CacheManager.getCacheManager("SequenceCacheMgr");
/*     */   private Connection conn;
/*  30 */   private static final String[] systemTables = { "PM_Company", "PM_UserLog", "ST_SystemLog" };
/*     */   private static SequenceTable st;
/*     */ 
/*     */   public SequenceUtils()
/*     */   {
/*  39 */     if (st == null)
/*     */       try {
/*  41 */         st = (SequenceTable)Class.forName("com.easytrack.framework.ApplicationSequenceTable").newInstance();
/*     */       } catch (Exception e) {
/*  43 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public Connection getConnection()
/*     */   {
/*  54 */     return this.conn;
/*     */   }
/*     */ 
/*     */   public void setConnection(Connection conn)
/*     */   {
/*  64 */     this.conn = conn;
/*     */   }
/*     */ 
/*     */   public void initSequence(int companyID)
/*     */     throws Exception
/*     */   {
/*  76 */     PreparedStatement pst = null;
/*  77 */     ResultSet rs = null;
/*     */     try
/*     */     {
/*  80 */       for (int i = 0; i < systemTables.length; i++) {
/*  81 */         String sql = "select max(id) from " + systemTables[i];
/*  82 */         pst = this.conn.prepareStatement(sql, 1004, 1007);
/*  83 */         rs = pst.executeQuery();
/*  84 */         SequenceKey key = new SequenceKey(systemTables[i], 1);
/*  85 */         while (rs.next()) {
/*  86 */           int count = rs.getInt(1) + 1;
/*  87 */           if (count < 1000) {
/*  88 */             count = 1000;
/*     */           }
/*  90 */           Integer c = (Integer)this.seqs.getObject(key);
/*  91 */           if ((c == null) || (c.intValue() < count))
/*  92 */             this.seqs.addObject(key, new Integer(count));
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (SQLException e) {
/*  97 */       throw e;
/*     */     } finally {
/*  99 */       rs.close();
/* 100 */       pst.close();
/*     */     }
/*     */ 
/* 103 */     String[] tables = st.getTables();
/* 104 */     for (int i = 0; i < tables.length; i++) {
/* 105 */       String sql = "select companyID, max(id) from " + tables[i];
/* 106 */       ArrayList values = new ArrayList();
/* 107 */       if (companyID > 0) {
/* 108 */         sql = sql + " where companyID = ?";
/* 109 */         values.add(new Integer(companyID));
/*     */       }
/* 111 */       sql = sql + " group by companyID";
/* 112 */       System.out.println(sql);
/*     */       try {
/* 114 */         pst = this.conn.prepareStatement(sql, 1004, 1007);
/* 115 */         for (int j = 0; (values != null) && (j < values.size()); j++) {
/* 116 */           pst.setObject(j + 1, values.get(j));
/*     */         }
/* 118 */         rs = pst.executeQuery();
/*     */ 
/* 120 */         int ii = 1000;
/* 121 */         while (rs.next()) {
/* 122 */           int compID = rs.getInt(1);
/* 123 */           int count = rs.getInt(2) + 1;
/* 124 */           if (count < 1000) {
/* 125 */             count = 1000;
/*     */           }
/* 127 */           if (st.isGlobeUnique()) {
/* 128 */             if (count > ii)
/* 129 */               ii = count;
/*     */           }
/*     */           else {
/* 132 */             SequenceKey key = new SequenceKey(tables[i], compID);
/* 133 */             this.seqs.addObject(key, new Integer(count));
/*     */           }
/*     */         }
/* 136 */         if (st.isGlobeUnique()) {
/* 137 */           SequenceKey key = new SequenceKey(tables[i], 1);
/* 138 */           this.seqs.addObject(key, new Integer(ii));
/*     */         }
/*     */       } catch (SQLException e) {
/* 141 */         throw e;
/*     */       } finally {
/* 143 */         rs.close();
/* 144 */         pst.close();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 149 */     ArrayList codeTables = new ArrayList();
/*     */     try {
/* 151 */       String sql = "select distinct tableName from ST_CodeTable";
/* 152 */       ArrayList values = new ArrayList();
/* 153 */       if (companyID > 0) {
/* 154 */         sql = sql + " where companyID = ?";
/* 155 */         values.add(new Integer(companyID));
/*     */       }
/* 157 */       pst = this.conn.prepareStatement(sql, 1004, 1007);
/* 158 */       for (int j = 0; (values != null) && (j < values.size()); j++) {
/* 159 */         pst.setObject(j + 1, values.get(j));
/*     */       }
/* 161 */       rs = pst.executeQuery();
/* 162 */       while (rs.next())
/* 163 */         codeTables.add(rs.getString(1));
/*     */     }
/*     */     catch (SQLException e) {
/* 166 */       throw e;
/*     */     } finally {
/* 168 */       rs.close();
/* 169 */       pst.close();
/*     */     }
/* 171 */     int ii = 1000;
/* 172 */     for (int i = 0; i < codeTables.size(); i++) {
/* 173 */       String tableName = (String)codeTables.get(i);
/* 174 */       String sql = "select companyID, max(id) from " + tableName;
/* 175 */       ArrayList values = new ArrayList();
/* 176 */       if (companyID > 0) {
/* 177 */         sql = sql + " where companyID = ?";
/* 178 */         values.add(new Integer(companyID));
/*     */       }
/* 180 */       sql = sql + " group by companyID";
/*     */       try {
/* 182 */         pst = this.conn.prepareStatement(sql, 1004, 1007);
/* 183 */         for (int j = 0; (values != null) && (j < values.size()); j++) {
/* 184 */           pst.setObject(j + 1, values.get(j));
/*     */         }
/* 186 */         rs = pst.executeQuery();
/* 187 */         while (rs.next()) {
/* 188 */           int compID = rs.getInt(1);
/* 189 */           int count = rs.getInt(2) + 1;
/* 190 */           if (count < 1000) {
/* 191 */             count = 1000;
/*     */           }
/* 193 */           if (st.isGlobeUnique()) {
/* 194 */             if (count > ii)
/* 195 */               ii = count;
/*     */           }
/*     */           else {
/* 198 */             SequenceKey key = new SequenceKey("ST_CommonCode", compID);
/* 199 */             Integer c = (Integer)this.seqs.getObject(key);
/* 200 */             if ((c == null) || (c.intValue() < count))
/* 201 */               this.seqs.addObject(key, new Integer(count));
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (SQLException e) {
/* 206 */         throw e;
/*     */       } finally {
/* 208 */         rs.close();
/* 209 */         pst.close();
/*     */       }
/*     */     }
/* 212 */     if (st.isGlobeUnique()) {
/* 213 */       SequenceKey key = new SequenceKey("ST_CommonCode", 1);
/* 214 */       this.seqs.addObject(key, new Integer(ii));
/*     */     }
/*     */ 
/* 217 */     ii = 1000;
/* 218 */     for (int i = 0; i < st.getOIDTables().length; i++) {
/* 219 */       String sql = "select companyID, max(id) from " + st.getOIDTables()[i];
/* 220 */       ArrayList values = new ArrayList();
/* 221 */       if (companyID > 0) {
/* 222 */         sql = sql + " where companyID = ?";
/* 223 */         values.add(new Integer(companyID));
/*     */       }
/* 225 */       sql = sql + " group by companyID";
/*     */       try {
/* 227 */         pst = this.conn.prepareStatement(sql, 1004, 1007);
/* 228 */         for (int j = 0; (values != null) && (j < values.size()); j++) {
/* 229 */           pst.setObject(j + 1, values.get(j));
/*     */         }
/* 231 */         rs = pst.executeQuery();
/* 232 */         while (rs.next()) {
/* 233 */           int compID = rs.getInt(1);
/* 234 */           int count = rs.getInt(2) + 1;
/* 235 */           if (count < 1000) {
/* 236 */             count = 1000;
/*     */           }
/* 238 */           if (st.isGlobeUnique()) {
/* 239 */             if (count > ii)
/* 240 */               ii = count;
/*     */           }
/*     */           else {
/* 243 */             SequenceKey key = new SequenceKey("OID", compID);
/* 244 */             Integer c = (Integer)this.seqs.getObject(key);
/* 245 */             if ((c == null) || (c.intValue() < count))
/* 246 */               this.seqs.addObject(key, new Integer(count));
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (SQLException e) {
/* 251 */         throw e;
/*     */       } finally {
/* 253 */         rs.close();
/* 254 */         pst.close();
/*     */       }
/*     */     }
/* 257 */     if (st.isGlobeUnique()) {
/* 258 */       SequenceKey key = new SequenceKey("OID", 1);
/* 259 */       this.seqs.addObject(key, new Integer(ii));
/*     */     }
/*     */ 
/* 262 */     ii = 1000;
/* 263 */     for (int i = 0; i < st.getFormValueTables().length; i++) {
/* 264 */       String sql = "select companyID, max(id) from " + st.getFormValueTables()[i];
/* 265 */       System.out.println(st.getFormValueTables()[i]);
/* 266 */       ArrayList values = new ArrayList();
/* 267 */       if (companyID > 0) {
/* 268 */         sql = sql + " where companyID = ?";
/* 269 */         values.add(new Integer(companyID));
/*     */       }
/* 271 */       sql = sql + " group by companyID";
/*     */       try {
/* 273 */         pst = this.conn.prepareStatement(sql, 1004, 1007);
/* 274 */         for (int j = 0; (values != null) && (j < values.size()); j++) {
/* 275 */           pst.setObject(j + 1, values.get(j));
/*     */         }
/* 277 */         rs = pst.executeQuery();
/* 278 */         while (rs.next()) {
/* 279 */           int compID = rs.getInt(1);
/* 280 */           int count = rs.getInt(2) + 1;
/* 281 */           if (count < 1000) {
/* 282 */             count = 1000;
/*     */           }
/* 284 */           if (st.isGlobeUnique()) {
/* 285 */             if (count > ii)
/* 286 */               ii = count;
/*     */           }
/*     */           else {
/* 289 */             SequenceKey key = new SequenceKey("FM_FormValue", compID);
/* 290 */             Integer c = (Integer)this.seqs.getObject(key);
/* 291 */             if ((c == null) || (c.intValue() < count))
/* 292 */               this.seqs.addObject(key, new Integer(count));
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (SQLException e) {
/* 297 */         throw e;
/*     */       } finally {
/* 299 */         rs.close();
/* 300 */         pst.close();
/*     */       }
/*     */     }
/* 303 */     if (st.isGlobeUnique()) {
/* 304 */       SequenceKey key = new SequenceKey("FM_FormValue", 1);
/* 305 */       this.seqs.addObject(key, new Integer(ii));
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized String getSequence(String tableName, int companyID)
/*     */   {
/* 320 */     synchronized (this.seqs) {
/* 321 */       int compID = companyID;
/* 322 */       if (st.isGlobeUnique()) {
/* 323 */         compID = 1;
/*     */       }
/* 325 */       SequenceKey key = new SequenceKey(tableName, compID);
/* 326 */       Integer c = (Integer)this.seqs.getObject(key);
/* 327 */       int iRet = 1000;
/* 328 */       if (c != null) {
/* 329 */         iRet = c.intValue();
/*     */       }
/* 331 */       this.seqs.addObject(key, new Integer(iRet + 1));
/* 332 */       return String.valueOf(iRet);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized String[] getSequence(String tableName, int companyID, int iCount)
/*     */   {
/* 348 */     synchronized (this.seqs) {
/* 349 */       int compID = companyID;
/* 350 */       if (st.isGlobeUnique()) {
/* 351 */         compID = 1;
/*     */       }
/* 353 */       SequenceKey key = new SequenceKey(tableName, compID);
/* 354 */       Integer c = (Integer)this.seqs.getObject(key);
/* 355 */       int iRet = 1000;
/* 356 */       if (c != null) {
/* 357 */         iRet = c.intValue();
/*     */       }
/* 359 */       String[] sRet = new String[iCount];
/* 360 */       for (int i = 0; i < iCount; i++) {
/* 361 */         sRet[i] = String.valueOf(iRet);
/* 362 */         iRet++;
/*     */       }
/* 364 */       this.seqs.addObject(key, new Integer(iRet));
/* 365 */       return sRet;
/*     */     }
/*     */   }
/*     */ 
/*     */   class SequenceKey
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = -4063270440928270620L;
/*     */     private String name;
/*     */     private int companyID;
/*     */ 
/*     */     public SequenceKey(String name, int companyID)
/*     */     {
/* 394 */       this.name = name.toLowerCase();
/* 395 */       this.companyID = companyID;
/*     */     }
/*     */ 
/*     */     public String getName()
/*     */     {
/* 404 */       return this.name;
/*     */     }
/*     */ 
/*     */     public void setName(String name)
/*     */     {
/* 414 */       this.name = name;
/*     */     }
/*     */ 
/*     */     public int getCompanyID()
/*     */     {
/* 423 */       return this.companyID;
/*     */     }
/*     */ 
/*     */     public void setCompanyID(int companyID)
/*     */     {
/* 433 */       this.companyID = companyID;
/*     */     }
/*     */ 
/*     */     public boolean equals(Object obj)
/*     */     {
/* 440 */       SequenceKey o = (SequenceKey)obj;
/* 441 */       return (o.getName().equals(this.name)) && (o.getCompanyID() == this.companyID);
/*     */     }
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 448 */       int hash = 1;
/* 449 */       hash = hash * 31 + this.name.hashCode();
/* 450 */       hash = hash * 31 + new Integer(this.companyID).hashCode();
/* 451 */       return hash;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.SequenceUtils
 * JD-Core Version:    0.6.2
 */