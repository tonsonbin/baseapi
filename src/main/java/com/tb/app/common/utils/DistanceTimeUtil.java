package com.tb.app.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description 逝去时间
 * @Author Benjamin
 * @CreateDate 2019-01-11 11:25
 **/
public class DistanceTimeUtil {
    /**
     * 仿QQ，微信聊天时间格式化
     *
     * @param time 时间戳
     * @return
     */
    public static String praseTime(long time) {
        Date date = new Date(time);
        //同一年 显示MM-dd HH:mm
        if (isSameYear(date)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
            if (isSameDay(date)) {
                //同一天 显示HH:mm
                int minute = minutesAgo(time);
                //1小时之内 显示n分钟前
                if (minute < 60) {
                    //一分钟之内，显示刚刚
                    if (minute <= 1) {
                        return "刚刚";
                    } else {
                        return minute + "分钟前";
                    }
                } else {
                    return simpleDateFormat.format(date);
                }
            } else {
                if (isYesterday(date)) {
                    //昨天，显示昨天+HH:mm
                    return "昨天 " + simpleDateFormat.format(date);
                } else if (isSameWeek(date)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    //本周,显示周几+HH:mm
                    String weekday = null;
                    int day = calendar.get(Calendar.DAY_OF_WEEK);
                    switch (day) {
                        case 0:
                            weekday = "周日";
                            break;
                        case 1:
                            weekday = "周一";
                            break;
                        case 2:
                            weekday = "周二";
                            break;
                        case 3:
                            weekday = "周三";
                            break;
                        case 4:
                            weekday = "周四";
                            break;
                        case 5:
                            weekday = "周五";
                            break;
                        case 6:
                            weekday = "周六";
                            break;
                    }
                    return weekday + " " + simpleDateFormat.format(date);
                } else {
                    //同一年 显示MM-dd HH:mm
                    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA);
                    return sdf.format(date);
                }
            }
        } else {
            //不是同一年 显示完整日期yyyy-MM-dd HH:mm
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            return sdf.format(date);
        }
    }

    /**
     * 几分钟前
     *
     * @param time
     * @return
     */
    public static int minutesAgo(long time) {
        return (int) ((System.currentTimeMillis() - time) / (60000));
    }


    /**
     * 与当前时间是否在同一周
     * 先判断是否在同一年，然后根据Calendar.DAY_OF_YEAR判断所得的周数是否一致
     *
     * @param date
     * @return
     */
    public static boolean isSameWeek(Date date) {
        if (isSameYear(date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int a = calendar.get(Calendar.DAY_OF_YEAR);

            Date now = new Date();
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(now);
            int b = calendar1.get(Calendar.DAY_OF_WEEK);
            return a == b;
        } else {
            return false;
        }
    }

    /**
     * 是否是当前时间的昨天
     * 获取指定时间的后一天的日期，判断与当前日期是否是同一天
     *
     * @param date
     * @return
     */
    public static boolean isYesterday(Date date) {
        Date yesterday = getNextDay(date, 1);
        return isSameDay(yesterday);
    }

    /**
     * 判断与当前日期是否是同一天
     *
     * @param date
     * @return
     */
    public static boolean isSameDay(Date date) {
        return isEquals(date, "yyyy-MM-dd");
    }

    /**
     * 判断与当前日期是否是同一月
     *
     * @param date
     * @return
     */
    public static boolean isSameMonth(Date date) {
        return isEquals(date, "yyyy-MM");
    }

    /**
     * 判断与当前日期是否是同一年
     *
     * @param date
     * @return
     */
    public static boolean isSameYear(Date date) {
        return isEquals(date, "yyyy");
    }


    /**
     * 格式化Date，判断是否相等
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    private static boolean isEquals(Date date, String format) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(format);
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    /**
     * 获取某个date第n天的日期
     * n<0 表示前n天
     * n=0 表示当天
     * n>1 表示后n天
     *
     * @param date
     * @param n
     * @return
     */
    public static Date getNextDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        date = calendar.getTime();
        return date;
    }

    /**
     * 添加秒
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static Date getDayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }
}
