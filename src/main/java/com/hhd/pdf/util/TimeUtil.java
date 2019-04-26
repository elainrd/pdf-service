package com.hhd.pdf.util;

import com.hhd.pdf.util.ValidateHelper;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * FileName: TimeUtil
 * Author:   胡侯东
 * Date:     2019-04-24  11:37
 * Description: hhd
 */
public class TimeUtil {
    public static final String PATTERN_1 = "yyyy-MM-dd";
    public static final String PATTERN_2 = "yyyy/MM/dd";
    public static final String PATTERN_3 = "yyyy.MM.dd";
    public static final String PATTERN_4 = "yyyyMMdd";
    public static final String PATTERN_5 = "yyyy.MM.dd HH:mm:ss";
    public static final String PATTERN_6 = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_7 = "yyyy/MM/dd HH:mm:ss";
    public static final String PATTERN_8 = "yyyyMMddHHmmss";
    public static final String PATTERN_9 = "yy/MM/dd";
    public static final String PATTERN_10 = "yy-MM-dd";
    public static final String PATTERN_11 = "yy.MM.dd";
    public static final String PATTERN_12 = "yyMMdd";
    public static final String PATTERN_13 = "yy.MM.dd HH:mm:ss";
    public static final String PATTERN_14 = "yy-MM-dd HH:mm:ss";
    public static final String PATTERN_15 = "yy/MM/dd HH:mm:ss";
    public static final String PATTERN_16 = "yyMMddHHmmss";
    public static final String PATTERN_17 = "yy年MM月dd日";

    public TimeUtil() {
    }

    public static String getNowTime() {
        return getNowTime("yyyy-MM-dd");
    }

    public static String getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);
        String formatedTime = dateFormat.format(now);
        return formatedTime;
    }

    public static Date getDayBefore(Date oldDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(5, -days);
        return calendar.getTime();
    }

    public static Date getDayAfter(Date oldDate, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(5, days);
        return calendar.getTime();
    }

    public static Date getDayBeforeByMonth(Date oldDate, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(2, -months);
        return calendar.getTime();
    }

    public static Date getFirstDayOfCurrMonth() {
        return getFirstDayOfMonth(new Date());
    }

    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        return formatDate(calendar.getTime(), "yyyy-MM-dd");
    }

    public static Date getLastDayOfCurrMonth() {
        return getLastDayOfMonth(new Date());
    }

    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, calendar.getActualMaximum(5));
        return formatDate(calendar.getTime(), "yyyy-MM-dd");
    }

    public static int daysBetween(Date startDate, Date endDate) {
        Date formatedStartDate = formatDate(startDate, "yyyy-MM-dd");
        Date formatedEndDate = formatDate(endDate, "yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatedStartDate);
        long startTime = calendar.getTimeInMillis();
        calendar.setTime(formatedEndDate);
        long endTime = calendar.getTimeInMillis();
        long days = (endTime - startTime) / 86400000L;
        return Long.valueOf(days).intValue();
    }

    public static Date getDate(Date date) {
        return format(format(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }

    public static String format(Date date, String format) {
        if (date != null && !ValidateHelper.isEmptyString(format)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            String formatedTime = dateFormat.format(date);
            return formatedTime;
        } else {
            return null;
        }
    }

    public static Date format(String dateStr, String format) {
        if (!ValidateHelper.isEmptyString(dateStr) && !ValidateHelper.isEmptyString(format)) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            ParsePosition pos = new ParsePosition(0);
            Date date = formatter.parse(dateStr, pos);
            return date;
        } else {
            return null;
        }
    }

    public static Date formatDate(Date date, String format) {
        return format(format(date, format), format);
    }

    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(1);
    }

    public static String getCurrentSimpleYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yy", Locale.CHINESE);
        return formatter.format(Calendar.getInstance().getTime());
    }

    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(2) + 1;
    }

    public static String getCurrentFullMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM", Locale.CHINESE);
        return formatter.format(Calendar.getInstance().getTime());
    }

    public static int getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(5);
    }

    public static String getCurrentFullDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd", Locale.CHINESE);
        return formatter.format(Calendar.getInstance().getTime());
    }

    public static int getAge(Date birthday) {
        int days = daysBetween(birthday, new Date());
        int age = days / 365;
        return age;
    }

    public static int getWeekDay() {
        Calendar now = Calendar.getInstance();
        boolean isFirstSunday = now.getFirstDayOfWeek() == 1;
        int weekDay = now.get(7);
        if (isFirstSunday) {
            --weekDay;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }

        return weekDay;
    }

    public static List<String> getFullDateByMonth(String yearOfMonth) {
        List<String> list = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        int year = Integer.parseInt(yearOfMonth.substring(0, 4));
        int month = Integer.parseInt(yearOfMonth.substring(5));
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        cal.set(5, 1);
        cal.set(5, cal.getActualMaximum(5));
        Date lastDate = cal.getTime();
        Calendar last = Calendar.getInstance();
        last.setTime(lastDate);
        cal.set(5, 1);
        list.add(formatter.format(cal.getTime()));

        while(cal.getTime().before(lastDate)) {
            cal.add(6, 1);
            list.add(formatter.format(cal.getTime()));
        }

        return list;
    }

    public static int getDayOfWeek(String dateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format(dateStr, "yyyy-MM-dd"));
        return calendar.get(7);
    }
}
