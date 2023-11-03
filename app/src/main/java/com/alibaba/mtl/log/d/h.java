package com.alibaba.mtl.log.d;

import android.text.TextUtils;
import com.alibaba.mtl.log.model.LogField;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LogAssemble.java */
/* loaded from: classes11.dex */
public class h {
    public static String a(Map<String, String> map) {
        boolean z;
        LogField logField;
        StringBuilder sb = new StringBuilder();
        LogField[] values = LogField.values();
        int length = values.length;
        int i = 0;
        while (true) {
            String str = null;
            if (i >= length || (logField = values[i]) == LogField.ARGS) {
                break;
            }
            if (map.containsKey(logField.toString())) {
                str = map.get(logField.toString()) + "";
                map.remove(logField.toString());
            }
            sb.append(b(str));
            sb.append("||");
            i++;
        }
        if (map.containsKey(LogField.ARGS.toString())) {
            sb.append(b(map.get(LogField.ARGS.toString()) + ""));
            map.remove(LogField.ARGS.toString());
            z = false;
        } else {
            z = true;
        }
        for (String str2 : map.keySet()) {
            String str3 = map.containsKey(str2) ? map.get(str2) + "" : null;
            if (z) {
                if ("StackTrace".equals(str2)) {
                    sb.append("StackTrace=====>");
                    sb.append(str3);
                } else {
                    sb.append(b(str2));
                    sb.append("=");
                    sb.append(str3);
                }
                z = false;
            } else if ("StackTrace".equals(str2)) {
                sb.append(",");
                sb.append("StackTrace=====>");
                sb.append(str3);
            } else {
                sb.append(",");
                sb.append(b(str2));
                sb.append("=");
                sb.append(str3);
            }
        }
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(sb2) && sb2.endsWith("||")) {
            return sb2 + "-";
        }
        return sb2;
    }

    public static String b(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            k(map);
            return a(map);
        }
        return null;
    }

    public static Map<String, String> k(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        try {
            String aG = b.aG();
            if (!TextUtils.isEmpty(aG) && !map.containsKey(LogField.USERNICK.toString())) {
                map.put(LogField.USERNICK.toString(), aG);
            }
            String k = b.k();
            if (!TextUtils.isEmpty(k) && !map.containsKey(LogField.LL_USERNICK.toString())) {
                map.put(LogField.LL_USERNICK.toString(), k);
            }
            String aH = b.aH();
            if (!TextUtils.isEmpty(aH) && !map.containsKey(LogField.USERID.toString())) {
                map.put(LogField.USERID.toString(), aH);
            }
            String aE = b.aE();
            if (!TextUtils.isEmpty(aE) && !map.containsKey(LogField.LL_USERID.toString())) {
                map.put(LogField.LL_USERID.toString(), aE);
            }
            String valueOf = String.valueOf(System.currentTimeMillis());
            if (!map.containsKey(LogField.RECORD_TIMESTAMP.toString())) {
                map.put(LogField.RECORD_TIMESTAMP.toString(), valueOf);
            }
            if (!map.containsKey(LogField.START_SESSION_TIMESTAMP.toString())) {
                map.put(LogField.START_SESSION_TIMESTAMP.toString(), String.valueOf(com.alibaba.mtl.log.a.bJ));
            }
            Map<String, String> a = d.a(com.alibaba.mtl.log.a.getContext());
            if (a != null) {
                for (String str : a.keySet()) {
                    String str2 = a.get(str);
                    if (!TextUtils.isEmpty(str2) && !map.containsKey(str) && !map.containsKey(str)) {
                        map.put(str, str2);
                    }
                }
            }
            String c = c(map);
            if (!TextUtils.isEmpty(c) && !map.containsKey(LogField.RESERVES.toString())) {
                map.put(LogField.RESERVES.toString(), c);
            }
        } catch (Throwable th) {
        }
        return map;
    }

    private static String c(Map<String, String> map) {
        String str = "_ap=1";
        if ("y".equalsIgnoreCase(map.get(LogField.OS.toString()))) {
            String aM = d.aM();
            if (!TextUtils.isEmpty(aM)) {
                str = "_ap=1,_did=" + aM;
            }
        }
        String str2 = map.get(LogField.APPKEY.toString());
        if (!TextUtils.isEmpty(b.getAppkey()) && !TextUtils.isEmpty(str2) && !b.getAppkey().equalsIgnoreCase(str2)) {
            return str + ",_mak=" + b.getAppkey();
        }
        return str;
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "-";
        }
        return str;
    }

    public static String a(String str, String str2, String str3, String str4, String str5, Map<String, String> map, String str6, String str7) {
        HashMap hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        if (!TextUtils.isEmpty(str)) {
            hashMap.put(LogField.PAGE.toString(), str);
        }
        hashMap.put(LogField.EVENTID.toString(), str2);
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put(LogField.ARG1.toString(), str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put(LogField.ARG2.toString(), str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            hashMap.put(LogField.ARG3.toString(), str5);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put(LogField.RECORD_TIMESTAMP.toString(), str7);
        }
        if (!TextUtils.isEmpty(str6)) {
            hashMap.put(LogField.RESERVE3.toString(), str6);
        }
        return b(hashMap);
    }
}
