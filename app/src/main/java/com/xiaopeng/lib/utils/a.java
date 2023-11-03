package com.xiaopeng.lib.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* compiled from: DateUtils.java */
/* loaded from: classes12.dex */
public class a {
    private static SimpleDateFormat VX = new SimpleDateFormat("MM.dd");
    private static SimpleDateFormat VY = new SimpleDateFormat("yyyy.MM");
    private static SimpleDateFormat VZ = new SimpleDateFormat("MM.dd");
    private static SimpleDateFormat Wa = new SimpleDateFormat("MM月dd日");
    private static SimpleDateFormat Wb = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat Wc = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat Wd = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat We = new SimpleDateFormat("HH:mm");
    private static SimpleDateFormat Wf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat Wg = new SimpleDateFormat("yyMMddHHmmss");
    private static SimpleDateFormat Wh = new SimpleDateFormat("yyyy/MM/dd");
    private static SimpleDateFormat Wi = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    private static SimpleDateFormat Wj = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    private static SimpleDateFormat Wk = new SimpleDateFormat("yyyyMMdd_HHmmss");

    public static synchronized String l(long j) {
        synchronized (a.class) {
            if (isToday(j)) {
                return "今天";
            }
            if (q(j)) {
                return "昨天";
            }
            return Wb.format(Long.valueOf(j));
        }
    }

    public static synchronized String m(long j) {
        String format;
        synchronized (a.class) {
            format = Wg.format(Long.valueOf(j));
        }
        return format;
    }

    public static synchronized String n(long j) {
        String format;
        synchronized (a.class) {
            format = Wh.format(Long.valueOf(j));
        }
        return format;
    }

    public static synchronized String o(long j) {
        String format;
        synchronized (a.class) {
            format = Wi.format(Long.valueOf(j));
        }
        return format;
    }

    public static synchronized String p(long j) {
        String format;
        synchronized (a.class) {
            format = Wk.format(Long.valueOf(j));
        }
        return format;
    }

    public static synchronized long dG(String str) {
        long j;
        synchronized (a.class) {
            j = 0;
            try {
                j = Wk.parse(str).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return j;
    }

    public static boolean isToday(long j) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTimeInMillis(j);
        return calendar.get(1) == Calendar.getInstance().get(1) && calendar.get(6) == Calendar.getInstance().get(6);
    }

    public static boolean q(long j) {
        Calendar calendar = (Calendar) Calendar.getInstance().clone();
        calendar.setTimeInMillis(j);
        return calendar.get(1) == Calendar.getInstance().get(1) && calendar.get(6) == Calendar.getInstance().get(6) - 1;
    }
}
