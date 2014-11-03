/*      */ package com.easytrack.commons.util;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URLDecoder;
/*      */ import java.net.URLEncoder;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Locale;
/*      */ 
/*      */ public class TypeUtils
/*      */ {
/*      */   public static final int HOUR_UNIT = 1;
/*      */   public static final int DAY_UNIT = 2;
/*      */   public static final int WEEK_UNIT = 3;
/*      */   public static final int MONTH_UNIT = 4;
/*      */   public static final int BY_TASK = 1;
/*      */   public static final int BY_RESOURCE = 2;
/*      */   public static final int BY_RESOURCE_TYPE = 3;
/*      */   public static final int BY_PROJECT = 4;
/*      */   public static final int BY_NON_PROJECT_TYPE = 5;
/*      */   public static final int BY_EXPENSE_TYPE = 6;
/*      */   public static final int BY_BENEFIT_TYPE = 7;
/*      */   public static final int BY_PHASE = 8;
/*      */   public static final int BY_REPORT_BY = 9;
/*      */   public static final int BY_OUTLINE = 10;
/*      */   public static final int BY_TYPE = 11;
/*      */   public static final int BY_PRODUCT = 12;
/*      */   public static final int NONE = 1;
/*      */   public static final int GREEN = 2;
/*      */   public static final int YELLOW = 3;
/*      */   public static final int RED = 4;
/*      */   public static final int COMPLETED = -1;
/*  299 */   private static long current = System.currentTimeMillis();
/*      */ 
/*  667 */   public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
/*      */ 
/*  676 */   public static String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
/*      */ 
/*  685 */   public static String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm";
/*      */ 
/*  694 */   public static String DEFAULT_HOUR_FORMAT = "yyyy-MM-dd hha";
/*      */ 
/*  908 */   private static String FORMAT_PATTERN_DEFAULT = "#0.00";
/*      */ 
/* 1004 */   public static String DEFAULT_DECIMAL_FORMAT = "##############0.00";
/*      */ 
/* 1012 */   public static String DEFAULT_WORKTIME_FORMAT = "##############0.0";
/*      */ 
/* 1020 */   public static String DEFAULT_PERCENT_FORMAT = "##############0";
/*      */ 
/*      */   public static synchronized long getUniqueID()
/*      */   {
/*  305 */     return current++;
/*      */   }
/*      */ 
/*      */   public static String nullToString(String inString)
/*      */   {
/*  317 */     return (inString == null) || ("null".equalsIgnoreCase(inString.trim())) ? "" : inString.trim();
/*      */   }
/*      */ 
/*      */   public static String nullToString(String inString, String defaultString)
/*      */   {
/*  331 */     return (inString == null) || ("null".equalsIgnoreCase(inString.trim())) ? defaultString : inString.trim();
/*      */   }
/*      */ 
/*      */   public static String nullToString(Object inObject)
/*      */   {
/*  343 */     return (inObject == null) || ("null".equalsIgnoreCase(inObject.toString().trim())) ? "" : inObject.toString();
/*      */   }
/*      */ 
/*      */   public static String joinString(String[] ss, String split)
/*      */   {
/*  356 */     StringBuffer sb = new StringBuffer();
/*  357 */     if ((ss != null) && (ss.length > 0)) {
/*  358 */       for (String s : ss) {
/*  359 */         if (sb.length() > 0) {
/*  360 */           sb.append(split);
/*      */         }
/*  362 */         sb.append(s);
/*      */       }
/*      */     }
/*  365 */     return sb.toString();
/*      */   }
/*      */ 
/*      */   public static String getUTFString(Object inObject)
/*      */   {
/*      */     try
/*      */     {
/*  377 */       return (inObject == null) || ("null".equalsIgnoreCase(inObject.toString().trim())) ? "" : new String(inObject.toString().getBytes("ISO8859-1"));
/*      */     }
/*      */     catch (Exception e) {
/*      */     }
/*  381 */     return "";
/*      */   }
/*      */ 
/*      */   public static int nullToInt(Object inObject)
/*      */   {
/*  392 */     int iRet = 0;
/*  393 */     if (inObject != null) {
/*      */       try {
/*  395 */         Double temp = new Double(inObject.toString());
/*  396 */         iRet = temp.intValue();
/*      */       } catch (Exception e) {
/*  398 */         iRet = 0;
/*      */       }
/*      */     }
/*  401 */     return iRet;
/*      */   }
/*      */ 
/*      */   public static String getDownloadFileName(String s)
/*      */     throws Exception
/*      */   {
/*  412 */     String sRet = s.replace(' ', '\r');
/*  413 */     sRet = URLEncoder.encode(sRet, "UTF-8");
/*  414 */     return sRet.replaceAll("%0D", " ");
/*      */   }
/*      */ 
/*      */   public static String strReplace(String destStr, String oldStr, String newStr)
/*      */   {
/*  430 */     if (destStr == null)
/*  431 */       return "";
/*  432 */     String tmpStr = destStr;
/*  433 */     int foundPos = tmpStr.indexOf(oldStr);
/*  434 */     while (foundPos >= 0) {
/*  435 */       tmpStr = tmpStr.substring(0, foundPos) + newStr + tmpStr.substring(foundPos + oldStr.length(), tmpStr.length());
/*      */ 
/*  437 */       foundPos = tmpStr.indexOf(oldStr, foundPos + newStr.length());
/*      */     }
/*  439 */     return tmpStr;
/*      */   }
/*      */ 
/*      */   public static void printBytes(byte[] array, String name)
/*      */   {
/*  451 */     for (int k = 0; k < array.length; k++)
/*  452 */       System.out.println(name + "[" + k + "] = " + "0x" + byteToHex(array[k]));
/*      */   }
/*      */ 
/*      */   public static String byteToHex(byte b)
/*      */   {
/*  465 */     char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*  466 */     char[] array = { hexDigit[(b >> 4 & 0xF)], hexDigit[(b & 0xF)] };
/*  467 */     return new String(array);
/*      */   }
/*      */ 
/*      */   public static String charToHex(char c)
/*      */   {
/*  479 */     byte hi = (byte)(c >>> '\b');
/*  480 */     byte lo = (byte)(c & 0xFF);
/*  481 */     return byteToHex(hi) + byteToHex(lo);
/*      */   }
/*      */ 
/*      */   public static String htmlEncoder4Print(Object obj)
/*      */   {
/*  492 */     String sRet = htmlEncoder(obj);
/*  493 */     sRet = strReplace(sRet, "\r\n", "<br>");
/*  494 */     sRet = strReplace(sRet, "\r", "<br>");
/*  495 */     sRet = strReplace(sRet, "\n", "<br>");
/*  496 */     return sRet;
/*      */   }
/*      */ 
/*      */   public static String htmlEncoder(Object obj)
/*      */   {
/*  508 */     if (obj == null)
/*  509 */       return "";
/*  510 */     String value = obj.toString();
/*      */ 
/*  512 */     char[] content = new char[value.length()];
/*  513 */     value.getChars(0, value.length(), content, 0);
/*  514 */     StringBuffer result = new StringBuffer(content.length + 50);
/*  515 */     for (int i = 0; i < content.length; i++) {
/*  516 */       switch (content[i]) {
/*      */       case '<':
/*  518 */         result.append("&lt;");
/*  519 */         break;
/*      */       case '>':
/*  521 */         result.append("&gt;");
/*  522 */         break;
/*      */       case '\'':
/*  524 */         result.append("&#039;");
/*  525 */         break;
/*      */       case '"':
/*  530 */         result.append("&quot;");
/*  531 */         break;
/*      */       default:
/*  533 */         result.append(content[i]);
/*      */       }
/*      */     }
/*  536 */     return result.toString();
/*      */   }
/*      */ 
/*      */   public static String urlEncoder(String str)
/*      */     throws UnsupportedEncodingException
/*      */   {
/*  549 */     return URLEncoder.encode(str, "UTF-8");
/*      */   }
/*      */ 
/*      */   public static String urlDecoder(String str)
/*      */     throws UnsupportedEncodingException
/*      */   {
/*  562 */     return URLDecoder.decode(str, "UTF-8");
/*      */   }
/*      */ 
/*      */   public static String xmlEncoder(String str)
/*      */   {
/*  574 */     if ((str == null) || (str.equals(""))) {
/*  575 */       return "";
/*      */     }
/*  577 */     String res_str = strReplace(str, "&", "&amp;");
/*  578 */     res_str = strReplace(res_str, "<", "&lt;");
/*  579 */     res_str = strReplace(res_str, ">", "&gt;");
/*  580 */     res_str = strReplace(res_str, "\"", "&quot;");
/*  581 */     res_str = strReplace(res_str, "'", "&acute;");
/*  582 */     return res_str;
/*      */   }
/*      */ 
/*      */   public static String xmlDecoder(String str)
/*      */   {
/*  594 */     if ((str == null) || (str.equals(""))) {
/*  595 */       return "";
/*      */     }
/*  597 */     String res_str = strReplace(str, "&amp;", "&");
/*  598 */     res_str = strReplace(res_str, "&lt;", "<");
/*  599 */     res_str = strReplace(res_str, "&gt;", ">");
/*  600 */     res_str = strReplace(res_str, "&quot;", "\"");
/*  601 */     res_str = strReplace(res_str, "&acute;", "'");
/*  602 */     return res_str;
/*      */   }
/*      */ 
/*      */   public static String xmlEncoderForIE(String str)
/*      */   {
/*  614 */     if ((str == null) || (str.equals(""))) {
/*  615 */       return "";
/*      */     }
/*  617 */     String res_str = strReplace(str, "&", "&amp;");
/*  618 */     res_str = strReplace(res_str, "<", "&lt;");
/*  619 */     res_str = strReplace(res_str, ">", "&gt;");
/*  620 */     res_str = strReplace(res_str, "\"", "&quot;");
/*      */ 
/*  622 */     return res_str;
/*      */   }
/*      */ 
/*      */   public static String sqlEncoder(String str)
/*      */   {
/*  632 */     if ((str == null) || (str.equals(""))) {
/*  633 */       return "";
/*      */     }
/*  635 */     String res_str = strReplace(str, "'", "''");
/*  636 */     res_str = strReplace(res_str, "&", "'||'&'||'");
/*  637 */     return res_str;
/*      */   }
/*      */ 
/*      */   public static String arrayToString(String[] values, String delim)
/*      */   {
/*  650 */     String sRet = "";
/*  651 */     for (int i = 0; i < values.length; i++) {
/*  652 */       sRet = sRet + delim + values[i];
/*      */     }
/*  654 */     if (values.length > 0)
/*  655 */       sRet = sRet.substring(delim.length());
/*  656 */     return sRet;
/*      */   }
/*      */ 
/*      */   public static Calendar setCalendar(Calendar c, int year, int month, int day)
/*      */   {
/*  723 */     c.set(1, year);
/*  724 */     c.set(2, month);
/*  725 */     c.set(5, day);
/*  726 */     c.set(11, 0);
/*  727 */     c.set(12, 0);
/*  728 */     c.set(13, 0);
/*  729 */     c.set(14, 0);
/*  730 */     return c;
/*      */   }
/*      */ 
/*      */   public static Calendar setCalendar(Calendar c, Date date)
/*      */   {
/*  743 */     c.setTime(date);
/*  744 */     c.set(11, 0);
/*  745 */     c.set(12, 0);
/*  746 */     c.set(13, 0);
/*  747 */     c.set(14, 0);
/*  748 */     return c;
/*      */   }
/*      */ 
/*      */   public static String date2String(Date dateValue, String dateFormat)
/*      */   {
/*  765 */     String sResult = "";
/*  766 */     if (dateValue != null) {
/*  767 */       SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
/*  768 */       sResult = formatter.format(dateValue);
/*      */     }
/*  770 */     return sResult;
/*      */   }
/*      */ 
/*      */   public static String date2String(Date dateValue, int dateFormat)
/*      */   {
/*  785 */     String f = DEFAULT_DATE_FORMAT;
/*  786 */     if (dateFormat == 0) {
/*  787 */       f = DEFAULT_TIME_FORMAT;
/*      */     }
/*  789 */     return date2String(dateValue, f);
/*      */   }
/*      */ 
/*      */   public static String date2String(Date dateValue)
/*      */   {
/*  800 */     return date2String(dateValue, DEFAULT_TIME_FORMAT);
/*      */   }
/*      */ 
/*      */   public static Date string2Date(String sDate)
/*      */     throws ParseException
/*      */   {
/*  814 */     return string2Date(sDate, DEFAULT_DATE_FORMAT);
/*      */   }
/*      */ 
/*      */   public static Date string2Date(String sDate, String dateFormat)
/*      */     throws ParseException
/*      */   {
/*  833 */     Date tmp = null;
/*  834 */     if ((sDate != null) && (!sDate.equals(""))) {
/*  835 */       SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
/*  836 */       formatter.setLenient(true);
/*  837 */       tmp = formatter.parse(sDate);
/*      */     }
/*  839 */     return tmp;
/*      */   }
/*      */ 
/*      */   public static Date string2Date(String sDate, int dateFormat)
/*      */     throws ParseException
/*      */   {
/*  854 */     String f = DEFAULT_DATE_FORMAT;
/*  855 */     if (dateFormat == 0) {
/*  856 */       f = DEFAULT_TIME_FORMAT;
/*      */     }
/*  858 */     return string2Date(sDate, f);
/*      */   }
/*      */ 
/*      */   public static int getIntFromString(String s)
/*      */   {
/*  869 */     int iRet = 0;
/*  870 */     if ((s != null) && (!"".equals(s)) && (!"null".equals(s))) {
/*  871 */       iRet = Integer.parseInt(s);
/*      */     }
/*  873 */     return iRet;
/*      */   }
/*      */ 
/*      */   public static long getLongFromString(String s)
/*      */   {
/*  884 */     long iRet = 0L;
/*  885 */     if ((s != null) && (!"".equals(s))) {
/*  886 */       iRet = Long.parseLong(s);
/*      */     }
/*  888 */     return iRet;
/*      */   }
/*      */ 
/*      */   public static Integer getInteger(String s)
/*      */   {
/*  899 */     if ((s == null) || ("".equals(s))) {
/*  900 */       return null;
/*      */     }
/*  902 */     return new Integer(s);
/*      */   }
/*      */ 
/*      */   public static String formatNumber(String input, String pattern)
/*      */   {
/*  920 */     if ((input == null) || (input.trim().equals("")))
/*  921 */       return "";
/*  922 */     DecimalFormat format = new DecimalFormat();
/*  923 */     format.applyLocalizedPattern(pattern);
/*  924 */     double d = Double.parseDouble(input);
/*  925 */     return format.format(d);
/*      */   }
/*      */ 
/*      */   public static String formatNumber(Object input, String pattern)
/*      */   {
/*  938 */     if (input == null)
/*  939 */       return "";
/*  940 */     return formatNumber(input.toString(), pattern);
/*      */   }
/*      */ 
/*      */   public static String formatNumber(String input)
/*      */   {
/*  952 */     return formatNumber(input, FORMAT_PATTERN_DEFAULT);
/*      */   }
/*      */ 
/*      */   public static String formatNumber(Object input)
/*      */   {
/*  964 */     if (input == null)
/*  965 */       return "";
/*  966 */     return formatNumber(input.toString());
/*      */   }
/*      */ 
/*      */   public static String formatNumber(double d, int scale)
/*      */   {
/*  980 */     DecimalFormat df = new DecimalFormat(FORMAT_PATTERN_DEFAULT);
/*  981 */     df.setMaximumFractionDigits(scale);
/*  982 */     return df.format(d);
/*      */   }
/*      */ 
/*      */   public static String formatNumber(double d)
/*      */   {
/*  992 */     DecimalFormat df = new DecimalFormat(FORMAT_PATTERN_DEFAULT);
/*  993 */     df.setMaximumFractionDigits(2);
/*  994 */     return df.format(d);
/*      */   }
/*      */ 
/*      */   public static String double2String(double d)
/*      */   {
/* 1039 */     DecimalFormat decimalFormatter = new DecimalFormat(DEFAULT_DECIMAL_FORMAT);
/* 1040 */     return decimalFormatter.format(d).toString();
/*      */   }
/*      */ 
/*      */   public static String percent2String(double d)
/*      */   {
/* 1052 */     DecimalFormat percentFormatter = new DecimalFormat(DEFAULT_WORKTIME_FORMAT);
/* 1053 */     return percentFormatter.format(d).toString();
/*      */   }
/*      */ 
/*      */   public static String worktime2String(double d)
/*      */   {
/* 1065 */     DecimalFormat workTimeFormatter = new DecimalFormat(DEFAULT_WORKTIME_FORMAT);
/* 1066 */     return workTimeFormatter.format(d).toString();
/*      */   }
/*      */ 
/*      */   public static double string2Worktime(String sDouble)
/*      */   {
/*      */     try
/*      */     {
/* 1079 */       if ((sDouble != null) && (!"".equals(sDouble))) {
/* 1080 */         DecimalFormat workTimeFormatter = new DecimalFormat(DEFAULT_WORKTIME_FORMAT);
/* 1081 */         return workTimeFormatter.parse(sDouble).doubleValue();
/*      */       }
/*      */     } catch (Exception e) {
/*      */     }
/* 1085 */     return 0.0D;
/*      */   }
/*      */ 
/*      */   public static double string2Double(String sDouble)
/*      */   {
/*      */     try
/*      */     {
/* 1098 */       if ((sDouble != null) && (!"".equals(sDouble))) {
/* 1099 */         DecimalFormat decimalFormatter = new DecimalFormat(DEFAULT_DECIMAL_FORMAT);
/* 1100 */         return decimalFormatter.parse(sDouble).doubleValue();
/*      */       }
/*      */     } catch (Exception e) {
/*      */     }
/* 1104 */     return 0.0D;
/*      */   }
/*      */ 
/*      */   public static String getLimitText(int limit, Object s)
/*      */     throws Exception
/*      */   {
/* 1116 */     String result = "";
/* 1117 */     String value = nullToString(s);
/* 1118 */     if ((limit == 0) || (value.length() <= limit))
/* 1119 */       result = value;
/*      */     else {
/* 1121 */       result = value.substring(0, limit) + "...";
/*      */     }
/* 1123 */     return result;
/*      */   }
/*      */ 
/*      */   public static String javaString2JavascriptString(String s)
/*      */   {
/* 1134 */     if (s == null) {
/* 1135 */       return null;
/*      */     }
/* 1137 */     s = s.replace("\\", "\\\\");
/* 1138 */     s = s.replace("'", "\\'");
/* 1139 */     s = s.replace("\"", "\\\"");
/* 1140 */     return s;
/*      */   }
/*      */ 
/*      */   public static String getCurrency(String currencyType, String value)
/*      */     throws Exception
/*      */   {
/* 1152 */     String result = "";
/* 1153 */     if ((value != null) && (!value.equals(""))) {
/* 1154 */       DecimalFormat decimalFormatter = new DecimalFormat(DEFAULT_DECIMAL_FORMAT);
/* 1155 */       if (currencyType.equals("1"))
/* 1156 */         result = "￥" + decimalFormatter.format(string2Double(value)).toString();
/* 1157 */       else if (currencyType.equals("2")) {
/* 1158 */         result = "$" + decimalFormatter.format(string2Double(value)).toString();
/*      */       }
/*      */     }
/* 1161 */     return result;
/*      */   }
/*      */ 
/*      */   public static String getCurrency(String currencyType, double value)
/*      */     throws Exception
/*      */   {
/* 1173 */     String result = "";
/* 1174 */     DecimalFormat decimalFormatter = new DecimalFormat(DEFAULT_DECIMAL_FORMAT);
/* 1175 */     if (currencyType.equals("1"))
/* 1176 */       result = "￥" + decimalFormatter.format(value);
/* 1177 */     else if (currencyType.equals("2")) {
/* 1178 */       result = "$" + decimalFormatter.format(value);
/*      */     }
/* 1180 */     return result;
/*      */   }
/*      */ 
/*      */   public static String getCurrencyFlag(String currencyType)
/*      */     throws Exception
/*      */   {
/* 1191 */     String result = "";
/* 1192 */     if (currencyType.equals("1"))
/* 1193 */       result = "￥";
/* 1194 */     else if (currencyType.equals("2")) {
/* 1195 */       result = "$";
/*      */     }
/* 1197 */     return result;
/*      */   }
/*      */ 
/*      */   public static double round(double value, int afterPoint)
/*      */   {
/* 1210 */     BigDecimal bd = new BigDecimal(value);
/* 1211 */     BigDecimal bd1 = bd.setScale(afterPoint, 4);
/* 1212 */     return bd1.doubleValue();
/*      */   }
/*      */ 
/*      */   public static String roundToString(double value, int afterPoint)
/*      */   {
/* 1225 */     BigDecimal bd = new BigDecimal(value);
/* 1226 */     BigDecimal bd1 = bd.setScale(afterPoint, 4);
/* 1227 */     NumberFormat formatter = NumberFormat.getNumberInstance();
/* 1228 */     formatter.setMinimumFractionDigits(afterPoint);
/* 1229 */     formatter.setMaximumFractionDigits(afterPoint);
/* 1230 */     String rtnValue = formatter.format(bd1.doubleValue());
/* 1231 */     return rtnValue;
/*      */   }
/*      */ 
/*      */   public static String noShowYear(Date date)
/*      */   {
/* 1242 */     SimpleDateFormat format = new SimpleDateFormat("MM-dd");
/* 1243 */     return format.format(date);
/*      */   }
/*      */ 
/*      */   public static String showYearAndMonth(Locale locale, Date date)
/*      */   {
/* 1250 */     Calendar calendar = Calendar.getInstance();
/* 1251 */     calendar.setTime(date);
/* 1252 */     int month = calendar.get(2);
/* 1253 */     int year = calendar.get(1);
/* 1254 */     String monthKey = getMonthKey(month);
/* 1255 */     return MessageUtils.getMessage(locale, monthKey) + "," + year;
/*      */   }
/*      */ 
/*      */   public static String getMonthName(Locale locale, int month)
/*      */   {
/* 1268 */     String monthKey = getMonthKey(month);
/* 1269 */     return MessageUtils.getMessage(locale, monthKey);
/*      */   }
/*      */ 
/*      */   public static String showMonth(Date date, Locale locale)
/*      */   {
/* 1282 */     Calendar calendar = Calendar.getInstance();
/* 1283 */     calendar.setTime(date);
/* 1284 */     int month = calendar.get(2);
/* 1285 */     String key = getMonthKey(month);
/* 1286 */     return MessageUtils.getMessage(locale, key);
/*      */   }
/*      */ 
/*      */   private static String getMonthKey(int month)
/*      */   {
/* 1297 */     String key = null;
/* 1298 */     switch (month) {
/*      */     case 0:
/* 1300 */       key = "JAN";
/* 1301 */       break;
/*      */     case 1:
/* 1303 */       key = "FEB";
/* 1304 */       break;
/*      */     case 2:
/* 1306 */       key = "MAR";
/* 1307 */       break;
/*      */     case 3:
/* 1309 */       key = "APR";
/* 1310 */       break;
/*      */     case 4:
/* 1312 */       key = "MAY";
/* 1313 */       break;
/*      */     case 5:
/* 1315 */       key = "JUN";
/* 1316 */       break;
/*      */     case 6:
/* 1318 */       key = "JUL";
/* 1319 */       break;
/*      */     case 7:
/* 1321 */       key = "AUG";
/* 1322 */       break;
/*      */     case 8:
/* 1324 */       key = "SEP";
/* 1325 */       break;
/*      */     case 9:
/* 1327 */       key = "OCT";
/* 1328 */       break;
/*      */     case 10:
/* 1330 */       key = "NOV";
/* 1331 */       break;
/*      */     case 11:
/* 1333 */       key = "DEC";
/*      */     }
/*      */ 
/* 1336 */     return key;
/*      */   }
/*      */ 
/*      */   public static String getUnitName(Locale locale, int unitType)
/*      */   {
/* 1353 */     String unitName = "";
/* 1354 */     switch (unitType) {
/*      */     case 1:
/* 1356 */       unitName = MessageUtils.getMessage(locale, "hourUnit");
/* 1357 */       break;
/*      */     case 2:
/* 1359 */       unitName = MessageUtils.getMessage(locale, "dayUnit");
/* 1360 */       break;
/*      */     case 3:
/* 1362 */       unitName = MessageUtils.getMessage(locale, "weekUnit");
/* 1363 */       break;
/*      */     case 4:
/* 1365 */       unitName = MessageUtils.getMessage(locale, "monthUnit");
/*      */     }
/*      */ 
/* 1368 */     return unitName;
/*      */   }
/*      */ 
/*      */   public static String getGroupName(Locale locale, int groupType)
/*      */     throws Exception
/*      */   {
/* 1392 */     String groupName = "";
/* 1393 */     switch (groupType) {
/*      */     case 1:
/* 1395 */       groupName = MessageUtils.getMessage(locale, "groupByTask");
/* 1396 */       break;
/*      */     case 2:
/* 1398 */       groupName = MessageUtils.getMessage(locale, "groupByResource");
/* 1399 */       break;
/*      */     case 3:
/* 1401 */       groupName = MessageUtils.getMessage(locale, "groupByResourceType");
/* 1402 */       break;
/*      */     case 4:
/* 1404 */       groupName = MessageUtils.getMessage(locale, "GroupByProject");
/* 1405 */       break;
/*      */     case 5:
/* 1407 */       groupName = MessageUtils.getMessage(locale, "groupByNonProjectTime");
/* 1408 */       break;
/*      */     case 6:
/* 1410 */       groupName = MessageUtils.getMessage(locale, "groupByExpenseType");
/* 1411 */       break;
/*      */     case 7:
/* 1413 */       groupName = MessageUtils.getMessage(locale, "groupByBenefitType");
/* 1414 */       break;
/*      */     case 8:
/* 1416 */       groupName = MessageUtils.getMessage(locale, "groupByPhase");
/* 1417 */       break;
/*      */     case 9:
/* 1419 */       groupName = MessageUtils.getMessage(locale, "groupByReportBy");
/*      */     }
/*      */ 
/* 1422 */     return groupName;
/*      */   }
/*      */ 
/*      */   public static String getWeekName(Locale locale, int day)
/*      */   {
/* 1436 */     String key = "";
/* 1437 */     switch (day) {
/*      */     case 1:
/* 1439 */       key = "Sunday";
/* 1440 */       break;
/*      */     case 2:
/* 1442 */       key = "Monday";
/* 1443 */       break;
/*      */     case 3:
/* 1445 */       key = "Tuesday";
/* 1446 */       break;
/*      */     case 4:
/* 1448 */       key = "Wednesday";
/* 1449 */       break;
/*      */     case 5:
/* 1451 */       key = "Thursday";
/* 1452 */       break;
/*      */     case 6:
/* 1454 */       key = "Friday";
/* 1455 */       break;
/*      */     case 7:
/* 1457 */       key = "Saturday";
/*      */     }
/*      */ 
/* 1460 */     return MessageUtils.getMessage(locale, key);
/*      */   }
/*      */ 
/*      */   public static String getInMonthStringByQuarter(int quarter)
/*      */   {
/* 1471 */     switch (quarter) {
/*      */     case 1:
/* 1473 */       return " (0,1,2)";
/*      */     case 2:
/* 1475 */       return " (3,4,5)";
/*      */     case 3:
/* 1477 */       return " (6,7,8)";
/*      */     case 4:
/* 1479 */       return " (9,10,11)";
/*      */     }
/* 1481 */     return null;
/*      */   }
/*      */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.TypeUtils
 * JD-Core Version:    0.6.2
 */