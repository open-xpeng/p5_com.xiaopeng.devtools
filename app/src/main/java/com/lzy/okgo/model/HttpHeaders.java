package com.lzy.okgo.model;

import android.os.Build;
import android.text.TextUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes11.dex */
public class HttpHeaders implements Serializable {
    public static final TimeZone mm = TimeZone.getTimeZone("GMT");
    private static String mn = null;
    private static final long serialVersionUID = 8458647755751403873L;
    private static String userAgent;
    public LinkedHashMap<String, String> headersMap;

    private void init() {
        this.headersMap = new LinkedHashMap<>();
    }

    public HttpHeaders() {
        init();
    }

    public void put(String str, String str2) {
        if (str != null && str2 != null) {
            this.headersMap.put(str, str2);
        }
    }

    public void b(HttpHeaders httpHeaders) {
        if (httpHeaders == null || httpHeaders.headersMap == null || httpHeaders.headersMap.isEmpty()) {
            return;
        }
        this.headersMap.putAll(httpHeaders.headersMap);
    }

    public String get(String str) {
        return this.headersMap.get(str);
    }

    public String aI(String str) {
        return this.headersMap.remove(str);
    }

    public void clear() {
        this.headersMap.clear();
    }

    public static long aJ(String str) {
        try {
            return aM(str);
        } catch (ParseException e) {
            return 0L;
        }
    }

    public static long aK(String str) {
        try {
            return aM(str);
        } catch (ParseException e) {
            return -1L;
        }
    }

    public static long aL(String str) {
        try {
            return aM(str);
        } catch (ParseException e) {
            return 0L;
        }
    }

    public static String s(String str, String str2) {
        if (str != null) {
            return str;
        }
        if (str2 != null) {
            return str2;
        }
        return null;
    }

    public static String db() {
        if (TextUtils.isEmpty(mn)) {
            Locale locale = Locale.getDefault();
            String language = locale.getLanguage();
            String country = locale.getCountry();
            StringBuilder sb = new StringBuilder(language);
            if (!TextUtils.isEmpty(country)) {
                sb.append('-');
                sb.append(country);
                sb.append(',');
                sb.append(language);
                sb.append(";q=0.8");
            }
            mn = sb.toString();
            return mn;
        }
        return mn;
    }

    public static String dc() {
        if (TextUtils.isEmpty(userAgent)) {
            String str = null;
            try {
                str = com.lzy.okgo.a.cE().getContext().getString(((Integer) Class.forName("com.android.internal.R$string").getDeclaredField("web_user_agent").get(null)).intValue());
            } catch (Exception e) {
            }
            if (TextUtils.isEmpty(str)) {
                str = "okhttp-okgo/jeasonlzy";
            }
            Locale locale = Locale.getDefault();
            StringBuffer stringBuffer = new StringBuffer();
            String str2 = Build.VERSION.RELEASE;
            if (str2.length() > 0) {
                stringBuffer.append(str2);
            } else {
                stringBuffer.append("1.0");
            }
            stringBuffer.append("; ");
            String language = locale.getLanguage();
            if (language != null) {
                stringBuffer.append(language.toLowerCase(locale));
                String country = locale.getCountry();
                if (!TextUtils.isEmpty(country)) {
                    stringBuffer.append("-");
                    stringBuffer.append(country.toLowerCase(locale));
                }
            } else {
                stringBuffer.append("en");
            }
            if ("REL".equals(Build.VERSION.CODENAME)) {
                String str3 = Build.MODEL;
                if (str3.length() > 0) {
                    stringBuffer.append("; ");
                    stringBuffer.append(str3);
                }
            }
            String str4 = Build.ID;
            if (str4.length() > 0) {
                stringBuffer.append(" Build/");
                stringBuffer.append(str4);
            }
            userAgent = String.format(str, stringBuffer, "Mobile ");
            return userAgent;
        }
        return userAgent;
    }

    public static long aM(String str) throws ParseException {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM y HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(mm);
        return simpleDateFormat.parse(str).getTime();
    }

    public static String f(long j) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM y HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(mm);
        return simpleDateFormat.format(date);
    }

    public String toString() {
        return "HttpHeaders{headersMap=" + this.headersMap + '}';
    }
}
