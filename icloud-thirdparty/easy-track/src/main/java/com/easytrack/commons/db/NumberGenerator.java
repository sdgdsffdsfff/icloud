/*     */ package com.easytrack.commons.db;
/*     */ 
/*     */ import com.easytrack.commons.util.ServiceLocator;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.ResultSetMetaData;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class NumberGenerator extends BaseDAO
/*     */ {
/*  32 */   private PreparedStatement pst = null;
/*     */ 
/*  36 */   private ResultSet rs = null;
/*     */ 
/*  40 */   private boolean isNewConn = true;
/*     */ 
/*  56 */   private static String[] chars = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
/*     */ 
/*     */   public NumberGenerator()
/*     */   {
/*  45 */     this.isNewConn = true;
/*     */   }
/*     */ 
/*     */   public NumberGenerator(Connection conn)
/*     */   {
/*  52 */     this.isNewConn = false;
/*  53 */     super.setConnection(conn);
/*     */   }
/*     */ 
/*     */   public List getSequenceSetting(int referID, int companyID)
/*     */     throws SQLException
/*     */   {
/*  66 */     ArrayList row = new ArrayList();
/*  67 */     Connection c = null;
/*     */     try {
/*  69 */       if (this.isNewConn)
/*  70 */         c = ServiceLocator.getConnection(getClass().getName());
/*     */       else {
/*  72 */         c = getConnection();
/*     */       }
/*  74 */       setConnection(c);
/*     */ 
/*  76 */       String sSQL = "select currentNumber, minValue, maxValue, valueLength, prefix, suffix from ST_FormSequence where name = ? and companyID = ? ";
/*     */ 
/*  78 */       this.pst = c.prepareStatement(sSQL);
/*  79 */       this.pst.setObject(1, referID + "");
/*  80 */       this.pst.setObject(2, new Integer(companyID));
/*  81 */       this.rs = this.pst.executeQuery();
/*     */ 
/*  84 */       while (this.rs.next()) {
/*  85 */         row = new ArrayList();
/*  86 */         for (i = 1; i <= this.rs.getMetaData().getColumnCount(); i++)
/*  87 */           row.add(this.rs.getObject(i));
/*     */       }
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*     */       int i;
/*  91 */       e.printStackTrace();
/*  92 */       return null;
/*     */     } finally {
/*  94 */       if (this.isNewConn)
/*     */         try {
/*  96 */           this.rs.close();
/*  97 */           this.pst.close();
/*  98 */           ServiceLocator.closeConnection(getClass().getName(), c);
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/*     */         }
/*     */     }
/* 104 */     return row;
/*     */   }
/*     */ 
/*     */   public String updateSequenceSetting(int referID, String length, String prefix, String suffix, int companyID) throws SQLException
/*     */   {
/* 109 */     String sRet = "";
/* 110 */     Connection c = null;
/*     */     try {
/* 112 */       if (this.isNewConn)
/* 113 */         c = ServiceLocator.getConnection(getClass().getName());
/*     */       else {
/* 115 */         c = getConnection();
/*     */       }
/* 117 */       setConnection(c);
/* 118 */       boolean bAutoCommit = true;
/* 119 */       if (this.isNewConn) {
/* 120 */         bAutoCommit = c.getAutoCommit();
/* 121 */         c.setAutoCommit(false);
/*     */       }
/*     */ 
/* 124 */       String sSQL = "select currentNumber from  ST_FormSequence where name = ? and companyID = ?";
/* 125 */       ArrayList oValues = new ArrayList();
/* 126 */       oValues.add(referID + "");
/* 127 */       oValues.add(new Integer(companyID));
/* 128 */       DBRowSet data = search(sSQL, oValues);
/*     */ 
/* 130 */       if (data.getRowCount() == 0) {
/* 131 */         sSQL = "insert into ST_FormSequence(name, companyID, currentNumber, minValue, maxValue, valueLength, currentYear, currentChar, prefix, suffix) values(?,?,?,?,?,?,?,?,?,?)";
/*     */ 
/* 134 */         oValues.add(new Integer(1));
/* 135 */         oValues.add(new Integer(1));
/* 136 */         oValues.add(new Integer(999999));
/* 137 */         oValues.add(new Integer(length));
/* 138 */         oValues.add(getCurrentYear());
/* 139 */         oValues.add(chars[0]);
/* 140 */         oValues.add(prefix);
/* 141 */         oValues.add(suffix);
/* 142 */         insert(sSQL, oValues);
/*     */       } else {
/* 144 */         sSQL = "update ST_FormSequence set valueLength = ?, prefix = ?, suffix = ? where name = ? and companyID = ? ";
/* 145 */         oValues = new ArrayList();
/* 146 */         oValues.add(new Integer(length));
/* 147 */         oValues.add(prefix);
/* 148 */         oValues.add(suffix);
/* 149 */         oValues.add(referID + "");
/* 150 */         oValues.add(new Integer(companyID));
/* 151 */         update(sSQL, oValues);
/*     */       }
/* 153 */       if (this.isNewConn) {
/* 154 */         c.commit();
/* 155 */         c.setAutoCommit(bAutoCommit);
/*     */       }
/*     */     } catch (Exception e) {
/* 158 */       if (this.isNewConn)
/*     */         try {
/* 160 */           c.rollback();
/*     */         }
/*     */         catch (Exception ee) {
/*     */         }
/* 164 */       e.printStackTrace();
/* 165 */       return null;
/*     */     } finally {
/* 167 */       if (this.isNewConn)
/*     */         try {
/* 169 */           ServiceLocator.closeConnection(getClass().getName(), c);
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/*     */         }
/*     */     }
/* 175 */     return sRet;
/*     */   }
/*     */ 
/*     */   private String getCurrentYear()
/*     */   {
/* 180 */     SimpleDateFormat formatter = new SimpleDateFormat("yy");
/* 181 */     Date currentTime = new Date();
/* 182 */     return formatter.format(currentTime);
/*     */   }
/*     */ 
/*     */   public String getSequenceNo(int referID, int companyID) throws SQLException
/*     */   {
/* 187 */     String sRet = "";
/* 188 */     Connection c = null;
/*     */     try {
/* 190 */       if (this.isNewConn)
/* 191 */         c = ServiceLocator.getConnection(getClass().getName());
/*     */       else {
/* 193 */         c = getConnection();
/*     */       }
/* 195 */       setConnection(c);
/* 196 */       boolean bAutoCommint = true;
/* 197 */       if (this.isNewConn) {
/* 198 */         bAutoCommint = c.getAutoCommit();
/* 199 */         c.setAutoCommit(false);
/*     */       }
/*     */ 
/* 203 */       String sSQL = "select currentNumber, minValue, maxValue, valueLength, prefix, suffix from  ST_FormSequence where name = ? and companyID = ? ";
/*     */ 
/* 205 */       this.pst = c.prepareStatement(sSQL);
/* 206 */       this.pst.setObject(1, referID + "");
/* 207 */       this.pst.setObject(2, new Integer(companyID));
/* 208 */       this.rs = this.pst.executeQuery();
/*     */ 
/* 211 */       ArrayList rows = new ArrayList();
/* 212 */       ArrayList row = null;
/* 213 */       int rowCount = 0;
/* 214 */       while (this.rs.next()) {
/* 215 */         row = new ArrayList();
/* 216 */         for (int i = 1; i <= this.rs.getMetaData().getColumnCount(); i++) {
/* 217 */           row.add(this.rs.getObject(i));
/*     */         }
/* 219 */         rows.add(row);
/* 220 */         rowCount++;
/*     */       }
/*     */ 
/* 223 */       ArrayList oValues = new ArrayList();
/* 224 */       if (rowCount == 0) {
/* 225 */         sSQL = "insert into ST_FormSequence(name, companyID, currentNumber, minValue, maxValue, valueLength, currentYear, currentChar, prefix, suffix) values(?,?,?,?,?,?,?,?,?,?)";
/*     */ 
/* 227 */         oValues.add(referID + "");
/* 228 */         oValues.add(new Integer(companyID));
/* 229 */         oValues.add(new Integer(2));
/* 230 */         oValues.add(new Integer(1));
/* 231 */         oValues.add(new Integer(999999));
/* 232 */         oValues.add(new Integer(6));
/* 233 */         oValues.add(getCurrentYear());
/* 234 */         oValues.add(chars[0]);
/* 235 */         oValues.add("");
/* 236 */         oValues.add("");
/* 237 */         sRet = "000001";
/* 238 */         insert(sSQL, oValues);
/* 239 */       } else if (rowCount == 1) {
/* 240 */         int iCurrentNo = getInt(0, 0, rows);
/* 241 */         int iValueLength = getInt(0, 3, rows);
/* 242 */         String sPrefix = getString(0, 4, rows);
/* 243 */         String sSuffix = getString(0, 5, rows);
/*     */ 
/* 246 */         sSQL = "update ST_FormSequence set  currentNumber = ?  where name = ? and companyID = ?";
/* 247 */         oValues = new ArrayList();
/* 248 */         oValues.add(new Integer(iCurrentNo + 1));
/* 249 */         oValues.add(referID + "");
/* 250 */         oValues.add(new Integer(companyID));
/* 251 */         update(sSQL, oValues);
/*     */ 
/* 253 */         String sSeq = "" + iCurrentNo;
/* 254 */         int iLen = sSeq.length();
/* 255 */         if (iLen < iValueLength) {
/* 256 */           for (int i = 0; i < iValueLength - iLen; i++) {
/* 257 */             sSeq = "0" + sSeq;
/*     */           }
/*     */         }
/* 260 */         sRet = sPrefix + sSeq + sSuffix;
/*     */       }
/* 262 */       if (this.isNewConn) {
/* 263 */         c.commit();
/* 264 */         c.setAutoCommit(bAutoCommint);
/*     */       }
/*     */     } catch (Exception e) {
/* 267 */       if (this.isNewConn)
/*     */         try {
/* 269 */           c.rollback();
/*     */         }
/*     */         catch (Exception ee) {
/*     */         }
/* 273 */       e.printStackTrace();
/* 274 */       return null;
/*     */     } finally {
/* 276 */       if (this.isNewConn)
/*     */         try {
/* 278 */           this.rs.close();
/* 279 */           this.pst.close();
/* 280 */           ServiceLocator.closeConnection(getClass().getName(), c);
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/*     */         }
/*     */     }
/* 286 */     return sRet;
/*     */   }
/*     */ 
/*     */   public String getString(int row, int column, List rows) {
/* 290 */     Object ob = ((List)rows.get(row)).get(column);
/* 291 */     return ob == null ? "" : ob.toString();
/*     */   }
/*     */ 
/*     */   public int getInt(int row, int column, List rows) {
/* 295 */     Object ob = ((List)rows.get(row)).get(column);
/* 296 */     return ob == null ? 0 : new BigDecimal(ob.toString()).intValue();
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.db.NumberGenerator
 * JD-Core Version:    0.6.2
 */