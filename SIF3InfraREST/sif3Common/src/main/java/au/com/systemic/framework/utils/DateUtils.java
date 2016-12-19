/*
 * DateUtils.java
 *
 * Copyright 2003-2014 Systemic Pty Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License 
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package au.com.systemic.framework.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joerg Huber
 */
public class DateUtils
{
  public static final long DAYS_PER_YEAR          = 365;
  public static final long HOURS_PER_DAY          = 24;
  public static final long MINUTES_PER_HOUR       = 60;
  public static final long SECONDS_PER_MINUTE     = 60;
  public static final long SECONDS_PER_HOUR       = MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
  public static final long MILLI_SECOND           = 1000;
  public static final long MINUTE_IN_MILLI_SECOND = SECONDS_PER_MINUTE * MILLI_SECOND;
  public static final long HOUR_IN_MILLI_SECOND   = MINUTES_PER_HOUR * MINUTE_IN_MILLI_SECOND;
  public static final long DAY_IN_MILLI_SECOND    = HOURS_PER_DAY * HOUR_IN_MILLI_SECOND;
  public static final long DAY_IN_SECONDS         = HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE;
  public static final long YEAR_IN_MILLI_SECOND   = DAYS_PER_YEAR * DAY_IN_MILLI_SECOND;

  /*
   * The following thre date formatters represent date, time and date-time formats as
   * used in the DB. They are often used for conversions from Date to String.
   */
  public static final SimpleDateFormat DB_DATE      = new SimpleDateFormat("yyyyMMdd");
  public static final SimpleDateFormat DB_TIME      = new SimpleDateFormat("HHmmss");
  public static final SimpleDateFormat DB_DATE_TIME = new SimpleDateFormat("yyyyMMddHHmmss");

  public static final String DAYS     = "DAYS";
  public static final String HOURS    = "HOURS";
  public static final String MINUTES  = "MINUTES";

  /*
   * A few formats that can be used to display dates to end users.
   */
  public static final SimpleDateFormat DISP_LONG_YEAR           = new SimpleDateFormat("dd/MM/yyyy");
  public static final SimpleDateFormat DISP_SHORT_YEAR          = new SimpleDateFormat("dd/MM/yy");
  public static final SimpleDateFormat DISP_LONG_MONTH          = new SimpleDateFormat("dd MMMMMMMMM yyyy");
  public static final SimpleDateFormat DISP_DATE_TIME_SEC       = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
  public static final SimpleDateFormat DISP_DATE_TIME_NO_SEC    = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  public static final SimpleDateFormat DISP_DATE_TIME_NO_SEC_US = new SimpleDateFormat("MM/dd/yyyy HH:mm");
  public static final SimpleDateFormat DISP_LONG_YEAR_US        = new SimpleDateFormat("MM/dd/yyyy");
  public static final SimpleDateFormat DISP_TIME_SEC            = new SimpleDateFormat("HH:mm:ss");
  public static final SimpleDateFormat DISP_TIME_NO_SEC         = new SimpleDateFormat("HH:mm");
  
  /*
   * ISO Standards
   */
  public static final SimpleDateFormat ISO_8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  public static final SimpleDateFormat ISO_8601_SECFRACT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

  public static final String DATE_FORMAT_dd_SLASH_mm_SLASH_yyyy = "dd/MM/yyyy";
  public static final String DATE_FORMAT_dd_DOT_mm_DOT_yyyy = "dd.MM.yyyy";
  public static final String DATE_FORMAT_mm_SLAST_dd_SLASH_yyyy = "MM/dd/yyyy";
  public static final String DATE_FORMAT_mm_DOT_dd_DOT_yyyy = "MM.dd.yyyy";
  public static final String DATE_FORMAT_dd_mm_yyyy = "dd-MM-yyyy";
  public static final String DATE_FORMAT_yyyy_mm_dd = "yyyy-MM-dd";
  public static final String DATE_FORMAT_yyyy_mm_dd_HH_mm = "yyyy-MM-dd HH:mm";

  /**
   * Transforms the given string into a date. The string must follow the
   * format as presented by the given format parameter. If the string has another
   * format then ParseException is raised.
   *
   * @param strDate The string to convert to a date.
   * @param format  A SimpleDateFormat object that describes the strDate.
   *
   * @return The date corresponding to the given <code>strDate</code> or null
   *         if the <code>strDate</code> is null.
   *
   * @throws ParseException If the strDate doesn't match the format as defined
   *                        by the 'format' parameter.
   */
  public static Date stringToDate(String strDate, DateFormat format)
  throws ParseException
  {
     if (strDate == null) return null;

     format.setLenient(false);
     return format.parse(strDate);
  }


  /**
   * Transforms the given string into a date. The string must follow the
   * format as presented by the given format parameter. If the string has another
   * format then ParseException is raised.
   * This method is simply a convenience method.
   *
   * @param strDate The string to convert to a date.
   * @param format  A string that describes the strDate.
   *
   * @return Date
   *
   * @throws ParseException If the strDate doesn't match the format as defined
   *                        by the 'format' parameter.
   */
  public static Date stringToDate(String strDate, String format) throws ParseException
  {
    return stringToDate(strDate, new SimpleDateFormat(format));
  }


  /**
   * This method converts a date into a string according to a given format.
   *
   * @param date
   * @param format
   *
   * @return String representation of the given <code>date</code> object or
   * null if the <code>date</code> is null.
   */
  public static String dateToString(Date date, DateFormat format)
  {
    return (date==null ? null : format.format(date));
  }


  /**
   * This method convers a date into a string according to a given format.
   *
   * @param date
   * @param format
   *
   * @return String representation of the given date object.
   */
  public static String dateToString(Date date, String format)
  {
    return dateToString(date, new SimpleDateFormat(format));
  }


  /**
   * Returns a date xxx milliseconds before the given date.
   * @exception NullPointerException if the given date is null.
   */
  public static Date dateBefore(Date date, long milliSeconds)
  {
     Date theDate = new Date();
     theDate.setTime(date.getTime() - milliSeconds);
     return theDate;
  }

  /**
   * Returns a date xxx milliseconds after the given date.
   * @exception NullPointerException if the given date is null.
   */
  public static Date dateAfter(Date date, long milliSeconds)
  {
     Date theDate = new Date();
     theDate.setTime(date.getTime() + milliSeconds);
     return theDate;
  }

  /**
   * Returns the difference in days between two calendars.
   * @exception NullPointerException if any of the given Calendars is null.
   */
  public static int getDaysDiff(Calendar cal1, Calendar cal2)
  {
     // Get difference in milliseconds
     long diffMillis = cal1.getTimeInMillis() - cal2.getTimeInMillis();
     long diffDays = diffMillis / DAY_IN_MILLI_SECOND;

     return (int)diffDays;

  }

  /**
   * Returns the difference in seconds between two calendars.
   * @exception NullPointerException if any of the given Calendars is null.
   */
  public static int getSecsDiff(Calendar cal1, Calendar cal2)
  {
     // Get difference in milliseconds
     long diffMillis = cal1.getTimeInMillis() - cal2.getTimeInMillis();
     long diffSecs = diffMillis / MILLI_SECOND;

     return (int)diffSecs;

  }

  /**
   * Returns the difference in seconds between two dates.
   * @exception NullPointerException if any of the given date is null.
   */
  public static int getSecsDiff(Date d1, Date d2)
  {
     // Get difference in milliseconds
     long diffMillis = d1.getTime() - d2.getTime();
     long diffSecs = diffMillis / MILLI_SECOND;

     return (int)diffSecs;

  }

  /**
   * Returns the difference in hours, minutes and seconds between two dates.
   * @exception NullPointerException if any of the given date is null.
   */
  public static Map<String, Integer> getTimeDiff(Date d1, Date d2)
  {
     Map<String, Integer> timeMap = new HashMap<String, Integer>();
     timeMap.put(DateUtils.DAYS, new Integer(0));
     timeMap.put(DateUtils.HOURS, new Integer(0));
     timeMap.put(DateUtils.MINUTES, new Integer(0));
     
     int days = DateUtils.getNumberOfDaysDiff(d1, d2);
     if(days < 1)
     {
        //Calculate no of hours
        int sec = DateUtils.getSecsDiff(d2, d1);
        int hours = Math.round(sec / (MINUTES_PER_HOUR * SECONDS_PER_MINUTE));
        int minutes = Math.round(sec / (MINUTES_PER_HOUR));

        if(hours < 1.0)
        {
           
           timeMap.put(DateUtils.MINUTES, new Integer(minutes));
        }
        else if(hours > 24.0) 
        {
           timeMap.put(DateUtils.DAYS, new Integer(days));
        }
        else         
        {
           timeMap.put(DateUtils.HOURS, new Integer(hours));
        }
     }
     else
     {
        timeMap.put(DateUtils.DAYS, new Integer(days));
     }
     return timeMap;      
  }



  /**
   * Returns true if the given <code>hours</code>, when added to the current
   * time, does not carry over to the next day; false otherwise.
   */
  public static boolean isEnoughHoursOfToday(int hours)
  {
     Date nowDate = new Date(System.currentTimeMillis());

     Calendar sysTimeCalendar = new GregorianCalendar();
     sysTimeCalendar.setTime(nowDate);

     Calendar futureCalendar = new GregorianCalendar();
     futureCalendar.setTime(nowDate);
     futureCalendar.add(Calendar.HOUR_OF_DAY, hours);

     if(sysTimeCalendar.get(Calendar.DATE) != futureCalendar.get(Calendar.DATE))
        return false;
     else
        return true;
  }

  /**
   * Returns a new date with added days
   */
  public static Date getDateWithAddedDays(Date date, int days)
  {
     Calendar futureCalendar = new GregorianCalendar();
     futureCalendar.setTime(date);
     futureCalendar.add(Calendar.DATE, days);
     return futureCalendar.getTime();
  }

  /**
   * Returns a new date with added hrs
   */
  public static Date getDateWithAddedHours(Date date, int hours)
  {
     Calendar futureCalendar = new GregorianCalendar();
     futureCalendar.setTime(date);
     futureCalendar.add(Calendar.HOUR, hours);
     return futureCalendar.getTime();
  }


  /**
   * Returns a new date with added minutes
   */
  public static Date getDateWithAddedMinutes(Date date, int minutes)
  {
     Calendar futureCalendar = new GregorianCalendar();
     futureCalendar.setTime(date);
     futureCalendar.add(Calendar.MINUTE, minutes);
     return futureCalendar.getTime();
  }

  /**
   * Returns a new date with subtracted minutes
   */
  public static Date getDateWithSubtractedMinutes(Date date, int minutes)
  {
     Calendar futureCalendar = new GregorianCalendar();
     futureCalendar.setTime(date);
     futureCalendar.add(Calendar.MINUTE, minutes * -1);
     return futureCalendar.getTime();
  }

  /**
   * Returns true if the given dates have the same day; false otherwise.
   * @exception NullPointerException if any of the given Dates is null.
   */
  public static boolean isSameDay(Date date1, Date date2)
  {
     Calendar calendar1 = new GregorianCalendar();
     calendar1.setTime(date1);

     Calendar calendar2 = new GregorianCalendar();
     calendar2.setTime(date2);

     if(calendar1.get(Calendar.DATE) != calendar2.get(Calendar.DATE))
        return false;
     else
        return true;
  }

  public static int getNumberOfDaysDiff(Date date1, Date date2)
  {
     Calendar calendar1 = new GregorianCalendar();
     calendar1.setTime(date1);

     Calendar calendar2 = new GregorianCalendar();
     calendar2.setTime(date2);

     int diff = calendar2.get(Calendar.DAY_OF_YEAR) - calendar1.get(Calendar.DAY_OF_YEAR);

     return diff;
  }

  /**
   * This method converts a UNIX Timstamp to a locallized Date object. 
   * 
   * @param  gmtTstamp UNIX Timestamp (in seconds from Epoch).
   * @return UNIX timestamp as a new Date in the local timezone
   */
  public static Date convertUnixTstampDate(long gmtTstamp)
  {
     return new Date(gmtTstamp * 1000);
  }
  
  public static String nowAsISO8601()
  {
	 // TimeZone tz = TimeZone.getTimeZone("UTC");
	 //df.setTimeZone(tz);
	 return ISO_8601.format(new Date()); 
  }

  public static String getISO8601(Date date)
  {
	  return ISO_8601.format(date); 
  }
  
  public static String nowAsISO8601withSecFraction()
  {
	 // TimeZone tz = TimeZone.getTimeZone("UTC");
	 //df.setTimeZone(tz);
	 return ISO_8601_SECFRACT.format(new Date()); 
  }
  
  public static String getISO8601withSecFraction(Date date)
  {
	  return ISO_8601_SECFRACT.format(date); 
  }
}
