/*     */ package com.easytrack.commons.util;
/*     */ 
/*     */ import java.sql.Timestamp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class CalendarUtils
/*     */ {
/*     */   public static final int BY_MONTH = 1;
/*     */   public static final int BY_WEEK = 2;
/*     */   public static final int BY_DAY = 3;
/*     */   public static final int BY_YEAR = 4;
/*     */   public static final int BY_QUARTER = 5;
/*     */   public static final int ONLY_ONE = 6;
/*     */   public static final long ONE_SECOND = 1000L;
/*     */   public static final long ONE_MINUTE = 60000L;
/*     */   public static final long ONE_HOUR = 3600000L;
/*     */   public static final long ONE_DAY = 86400000L;
/*     */   public static final long ONE_WEEK = 604800000L;
/*     */ 
/*     */   public static String getCycleName(Locale locale, int cycleType)
/*     */   {
/* 135 */     String cycleName = "";
/* 136 */     switch (cycleType) {
/*     */     case 2:
/* 138 */       cycleName = MessageUtils.getMessage(locale, "byWeek");
/* 139 */       break;
/*     */     case 1:
/* 141 */       cycleName = MessageUtils.getMessage(locale, "byMonth");
/* 142 */       break;
/*     */     case 3:
/* 144 */       cycleName = MessageUtils.getMessage(locale, "byDay");
/*     */     }
/*     */ 
/* 147 */     return cycleName;
/*     */   }
/*     */ 
/*     */   public static List<Date> getDateList(int cycleType, int year, int quarter, int month)
/*     */   {
/* 165 */     List dateList = new ArrayList();
/* 166 */     if (cycleType == 1)
/*     */     {
/* 170 */       for (int i = 0; i <= 11; i++) {
/* 171 */         Date date = getMonthStartDay(year, i);
/* 172 */         dateList.add(date);
/*     */       }
/* 174 */     } else if (cycleType == 2)
/*     */     {
/* 178 */       Calendar calendar = Calendar.getInstance();
/* 179 */       Date firstSun = getFirstSunByQuarter(year, quarter);
/* 180 */       calendar.setTime(firstSun);
/* 181 */       int lastMonth = getLastMonthByQuarter(quarter);
/* 182 */       while ((calendar.get(2) <= lastMonth) && (calendar.get(1) == year)) {
/* 183 */         dateList.add(calendar.getTime());
/* 184 */         calendar.add(5, 7);
/*     */       }
/* 186 */     } else if (cycleType == 3)
/*     */     {
/* 190 */       Calendar calendar = Calendar.getInstance();
/* 191 */       Date startDay = getMonthStartDay(year, month);
/* 192 */       calendar.setTime(startDay);
/* 193 */       while (calendar.get(2) == month) {
/* 194 */         Date date = calendar.getTime();
/* 195 */         dateList.add(date);
/* 196 */         calendar.add(5, 1);
/*     */       }
/*     */     }
/* 199 */     return dateList;
/*     */   }
/*     */ 
/*     */   public static Date addOneDay(Date date)
/*     */   {
/* 207 */     Calendar c = Calendar.getInstance();
/* 208 */     c.setTime(date);
/* 209 */     c.add(5, 1);
/* 210 */     Date endDate = c.getTime();
/* 211 */     return endDate;
/*     */   }
/*     */ 
/*     */   public static Date getIntegralDate(Date date)
/*     */   {
/* 219 */     Calendar calendar = Calendar.getInstance();
/* 220 */     calendar.setTime(date);
/* 221 */     clearCalendar(calendar);
/* 222 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getDateEndOfYearAndWeek(int year, int week)
/*     */   {
/* 232 */     Date date = getCurrentDay();
/* 233 */     Calendar calendar = Calendar.getInstance();
/* 234 */     calendar.setTime(date);
/* 235 */     calendar.set(1, year);
/* 236 */     calendar.set(3, week);
/* 237 */     calendar.set(7, 7);
/* 238 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static int getYearByDate(Date date)
/*     */   {
/* 247 */     int year = 0;
/* 248 */     Calendar calendar = Calendar.getInstance();
/* 249 */     calendar.setTime(date);
/* 250 */     year = calendar.get(1);
/* 251 */     return year;
/*     */   }
/*     */ 
/*     */   public static int getMonthByDate(Date date)
/*     */   {
/* 259 */     int month = 0;
/* 260 */     Calendar calendar = Calendar.getInstance();
/* 261 */     calendar.setTime(date);
/* 262 */     month = calendar.get(2);
/* 263 */     return month;
/*     */   }
/*     */ 
/*     */   public static int getDayOfYearByDate(Date date)
/*     */   {
/* 272 */     int result = 0;
/* 273 */     Calendar calendar = Calendar.getInstance();
/* 274 */     calendar.setTime(date);
/* 275 */     result = calendar.get(6);
/* 276 */     return result;
/*     */   }
/*     */ 
/*     */   public static int getDayOfWeeyByDate(Date date)
/*     */   {
/* 284 */     int result = 0;
/* 285 */     Calendar calendar = Calendar.getInstance();
/* 286 */     calendar.setTime(date);
/* 287 */     result = calendar.get(7);
/* 288 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean isAtTheSameDay(Date d1, Date d2)
/*     */   {
/* 297 */     Calendar c1 = Calendar.getInstance();
/* 298 */     c1.setTime(d1);
/* 299 */     Calendar c2 = Calendar.getInstance();
/* 300 */     c2.setTime(d2);
/* 301 */     return (c1.get(1) == c2.get(1)) && (c1.get(2) == c2.get(2)) && (c1.get(5) == c2.get(5));
/*     */   }
/*     */ 
/*     */   public static int getDayDateByDate(Date date)
/*     */   {
/* 309 */     int dayDate = 1;
/* 310 */     Calendar calendar = Calendar.getInstance();
/* 311 */     calendar.setTime(date);
/* 312 */     dayDate = calendar.get(5);
/* 313 */     return dayDate;
/*     */   }
/*     */ 
/*     */   public static int getWeekOfYearByDate(Date date)
/*     */   {
/* 321 */     int week = 0;
/* 322 */     Calendar calendar = Calendar.getInstance();
/* 323 */     calendar.setTime(date);
/* 324 */     week = calendar.get(3);
/* 325 */     return week;
/*     */   }
/*     */ 
/*     */   public static Date getMonthStartDay(int year, int month)
/*     */   {
/* 334 */     Date date = getCurrentDay();
/* 335 */     Calendar calendar = Calendar.getInstance();
/* 336 */     calendar.setTime(date);
/* 337 */     calendar.set(1, year);
/* 338 */     calendar.set(2, month);
/* 339 */     calendar.set(5, 1);
/* 340 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static int getQuarter(Calendar calendar)
/*     */   {
/* 349 */     int month = calendar.get(2);
/* 350 */     return month / 3 + 1;
/*     */   }
/*     */ 
/*     */   public static int getMonth(Calendar calendar)
/*     */   {
/* 359 */     int month = calendar.get(2);
/* 360 */     return month;
/*     */   }
/*     */ 
/*     */   public static Date getQuarterStartDay(int year, int quarter)
/*     */   {
/* 369 */     Calendar calendar = Calendar.getInstance();
/* 370 */     clearCalendar(calendar);
/* 371 */     calendar.set(1, year);
/* 372 */     calendar.set(2, (quarter - 1) * 3);
/* 373 */     calendar.set(5, 1);
/* 374 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getQuarterEndDay(int year, int quarter)
/*     */   {
/* 384 */     Calendar calendar = Calendar.getInstance();
/* 385 */     clearCalendar(calendar);
/* 386 */     calendar.set(1, year);
/* 387 */     calendar.set(2, quarter * 3);
/* 388 */     calendar.set(5, 1);
/* 389 */     calendar.add(5, -1);
/* 390 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getFirstSunByQuarter(int year, int quarter)
/*     */   {
/* 399 */     int firstMonth = getFirstMonthByQuarter(quarter);
/* 400 */     Date date = getMonthStartDay(year, firstMonth);
/* 401 */     Calendar calendar = Calendar.getInstance();
/* 402 */     calendar.setTime(date);
/* 403 */     if (calendar.get(7) != 1) {
/* 404 */       calendar.add(5, 8 - calendar.get(7));
/*     */     }
/* 406 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getFirstSunByYearAndMonth(int year, int month)
/*     */   {
/* 415 */     Date date = getMonthStartDay(year, month);
/* 416 */     Calendar calendar = Calendar.getInstance();
/* 417 */     calendar.setTime(date);
/* 418 */     if (calendar.get(7) != 1) {
/* 419 */       calendar.add(5, 8 - calendar.get(7));
/*     */     }
/* 421 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getLastSatByQuarter(int year, int quarter)
/*     */   {
/* 430 */     int lastMonth = getLastMonthByQuarter(quarter);
/* 431 */     Date date = getMonthEndDay(year, lastMonth);
/* 432 */     Calendar calendar = Calendar.getInstance();
/* 433 */     calendar.setTime(date);
/* 434 */     if (calendar.get(7) != 7) {
/* 435 */       calendar.add(5, 7 - calendar.get(7));
/*     */     }
/* 437 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getLastSatByYearAndMonth(int year, int month)
/*     */   {
/* 447 */     Date date = getMonthEndDay(year, month);
/* 448 */     Calendar calendar = Calendar.getInstance();
/* 449 */     calendar.setTime(date);
/* 450 */     if (calendar.get(7) != 7) {
/* 451 */       calendar.add(5, 7 - calendar.get(7));
/*     */     }
/* 453 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getDayByYear(int year)
/*     */   {
/* 463 */     Date date = getCurrentDay();
/* 464 */     Calendar c = Calendar.getInstance();
/* 465 */     c.setTime(date);
/* 466 */     c.set(1, year);
/* 467 */     date = c.getTime();
/* 468 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date getFirstDayOfMonth(Date date)
/*     */   {
/* 477 */     Calendar c = Calendar.getInstance();
/* 478 */     c.setTime(date);
/* 479 */     c.set(5, 1);
/* 480 */     return c.getTime();
/*     */   }
/*     */ 
/*     */   public static int getWeekYear(Calendar calendar)
/*     */   {
/* 489 */     int month = calendar.get(2);
/* 490 */     int weekOfYear = calendar.get(3);
/* 491 */     int year = calendar.get(1);
/* 492 */     int weekYear = year;
/* 493 */     if ((month == 0) && ((weekOfYear == 52) || (weekOfYear == 53)))
/*     */     {
/* 497 */       weekYear--;
/* 498 */     } else if ((month == 11) && (weekOfYear == 1))
/*     */     {
/* 502 */       weekYear++;
/*     */     }
/* 504 */     return weekYear;
/*     */   }
/*     */ 
/*     */   public static int getWeekYear(Date date)
/*     */   {
/* 513 */     Calendar calendar = Calendar.getInstance();
/* 514 */     calendar.setTime(date);
/* 515 */     int month = calendar.get(2);
/* 516 */     int weekOfYear = calendar.get(3);
/* 517 */     int year = calendar.get(1);
/* 518 */     int weekYear = year;
/* 519 */     if ((month == 0) && ((weekOfYear == 52) || (weekOfYear == 53)))
/* 520 */       weekYear--;
/* 521 */     else if ((month == 11) && (weekOfYear == 1)) {
/* 522 */       weekYear++;
/*     */     }
/* 524 */     return weekYear;
/*     */   }
/*     */ 
/*     */   public static Date getMonthEndDay(int year, int month)
/*     */   {
/* 533 */     Calendar calendar = Calendar.getInstance();
/* 534 */     clearCalendar(calendar);
/* 535 */     calendar.set(1, year);
/* 536 */     calendar.set(2, month + 1);
/* 537 */     calendar.set(5, 1);
/* 538 */     calendar.add(5, -1);
/* 539 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static void clearCalendar(Calendar c)
/*     */   {
/* 547 */     c.set(11, 0);
/* 548 */     c.set(12, 0);
/* 549 */     c.set(13, 0);
/* 550 */     c.set(14, 0);
/*     */   }
/*     */ 
/*     */   public static Date getYesterday()
/*     */   {
/* 558 */     Calendar c = Calendar.getInstance();
/* 559 */     clearCalendar(c);
/* 560 */     c.add(5, -1);
/* 561 */     return c.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getCurrentDay()
/*     */   {
/* 569 */     Calendar c = Calendar.getInstance();
/* 570 */     clearCalendar(c);
/* 571 */     return c.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getLastSaturday()
/*     */   {
/* 578 */     Calendar c = Calendar.getInstance();
/* 579 */     clearCalendar(c);
/* 580 */     c.add(5, -c.get(7));
/* 581 */     return c.getTime();
/*     */   }
/*     */ 
/*     */   public static int getFirstMonthByQuarter(int quarter)
/*     */   {
/* 590 */     int firstMonth = 0;
/* 591 */     switch (quarter) {
/*     */     case 1:
/* 593 */       firstMonth = 0;
/* 594 */       break;
/*     */     case 2:
/* 596 */       firstMonth = 3;
/* 597 */       break;
/*     */     case 3:
/* 599 */       firstMonth = 6;
/* 600 */       break;
/*     */     case 4:
/* 602 */       firstMonth = 9;
/*     */     }
/*     */ 
/* 605 */     return firstMonth;
/*     */   }
/*     */ 
/*     */   public static int getLastMonthByQuarter(int quarter)
/*     */   {
/* 613 */     int lastMonth = 0;
/* 614 */     switch (quarter) {
/*     */     case 1:
/* 616 */       lastMonth = 2;
/* 617 */       break;
/*     */     case 2:
/* 619 */       lastMonth = 5;
/* 620 */       break;
/*     */     case 3:
/* 622 */       lastMonth = 8;
/* 623 */       break;
/*     */     case 4:
/* 625 */       lastMonth = 11;
/*     */     }
/*     */ 
/* 628 */     return lastMonth;
/*     */   }
/*     */ 
/*     */   public static Date getDateStartOfYearAndWeek(int year, int week)
/*     */   {
/* 639 */     Date date = getCurrentDay();
/* 640 */     Calendar calendar = Calendar.getInstance();
/* 641 */     calendar.setTime(date);
/* 642 */     calendar.set(1, year);
/* 643 */     calendar.set(3, week);
/* 644 */     calendar.set(7, 1);
/* 645 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getThisYearLastDay()
/*     */   {
/* 653 */     Calendar calendar = Calendar.getInstance();
/* 654 */     clearCalendar(calendar);
/* 655 */     calendar.set(2, 11);
/* 656 */     calendar.set(5, 31);
/* 657 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getThisYearFirstDay()
/*     */   {
/* 664 */     Calendar calendar = Calendar.getInstance();
/* 665 */     clearCalendar(calendar);
/* 666 */     calendar.set(2, 0);
/* 667 */     calendar.set(5, 1);
/* 668 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static boolean judgeBissextile(int year)
/*     */   {
/* 677 */     boolean result = false;
/* 678 */     if ((year % 4 == 0) && (year % 100 != 0))
/* 679 */       result = true;
/* 680 */     else if ((year % 4 == 0) && (year % 100 == 0) && (year % 400 == 0)) {
/* 681 */       result = true;
/*     */     }
/* 683 */     return result;
/*     */   }
/*     */ 
/*     */   public static Date getThisSunday(Date date)
/*     */   {
/* 693 */     Calendar calendar = Calendar.getInstance();
/* 694 */     calendar.setTime(date);
/* 695 */     clearCalendar(calendar);
/* 696 */     calendar.set(7, 1);
/* 697 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getThisSaturday(Date date)
/*     */   {
/* 707 */     Calendar calendar = Calendar.getInstance();
/* 708 */     calendar.setTime(date);
/* 709 */     clearCalendar(calendar);
/* 710 */     calendar.set(7, 7);
/* 711 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getLastDayOfTheYear(int year)
/*     */   {
/* 721 */     Calendar calendar = Calendar.getInstance();
/* 722 */     clearCalendar(calendar);
/* 723 */     calendar.set(1, year);
/* 724 */     calendar.set(2, 11);
/* 725 */     calendar.set(5, 31);
/* 726 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getFirstDayOfTheYear(int year)
/*     */   {
/* 734 */     Calendar calendar = Calendar.getInstance();
/* 735 */     clearCalendar(calendar);
/* 736 */     calendar.set(1, year);
/* 737 */     calendar.set(2, 0);
/* 738 */     calendar.set(5, 1);
/* 739 */     return calendar.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getCurrentDate()
/*     */   {
/* 747 */     return new Timestamp(System.currentTimeMillis());
/*     */   }
/*     */ 
/*     */   public static int getDateRangeDay(Date start, Date end)
/*     */   {
/* 757 */     double range = 0.0D;
/* 758 */     range = (end.getTime() - start.getTime()) / 86400000L + 1L;
/* 759 */     return new Double(range).intValue();
/*     */   }
/*     */ }

/* Location:           D:\copy\EasyTrack\jboss\server\default\deploy\et.ear\lib\et-commons-6.4.0.jar
 * Qualified Name:     com.easytrack.commons.util.CalendarUtils
 * JD-Core Version:    0.6.2
 */