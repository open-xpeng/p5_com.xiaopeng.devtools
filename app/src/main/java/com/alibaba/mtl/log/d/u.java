package com.alibaba.mtl.log.d;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.mtl.log.model.LogField;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/* compiled from: UrlWrapper.java */
/* loaded from: classes11.dex */
public class u {
    private static final String TAG = u.class.getSimpleName();

    public static String a(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String a;
        String str2;
        String str3 = "";
        if (map2 != null && map2.size() > 0) {
            Set<String> keySet = map2.keySet();
            String[] strArr = new String[keySet.size()];
            keySet.toArray(strArr);
            for (String str4 : g.aP().a(strArr, true)) {
                str3 = str3 + str4 + j.b((byte[]) map2.get(str4));
            }
        }
        try {
            a = a(str, null, null, str3);
        } catch (Throwable th) {
            a = a(com.alibaba.mtl.log.a.a.g(), null, null, str3);
        }
        if (!TextUtils.isEmpty(com.alibaba.mtl.log.a.a.S)) {
            return a + "&dk=" + URLEncoder.encode(str2, "UTF-8");
        }
        return a;
    }

    private static String a(String str, String str2, String str3, String str4) throws Exception {
        Context context = com.alibaba.mtl.log.a.getContext();
        String appkey = b.getAppkey();
        String aF = b.aF();
        if (aF == null) {
            aF = "";
        }
        String str5 = d.a(context).get(LogField.APPVERSION.toString());
        String str6 = d.a(context).get(LogField.OS.toString());
        String str7 = d.a(context).get(LogField.UTDID.toString());
        String valueOf = String.valueOf(System.currentTimeMillis());
        com.alibaba.mtl.log.e.b ac = com.alibaba.mtl.log.a.ac();
        String str8 = "0";
        String str9 = "0";
        if (ac instanceof com.alibaba.mtl.log.e.c) {
            str9 = "1";
        } else if (ac instanceof com.alibaba.mtl.log.e.a) {
            str8 = ((com.alibaba.mtl.log.e.a) ac).isEncode() ? "1" : "0";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(appkey);
        sb.append(aF);
        sb.append(str5);
        sb.append(str6);
        sb.append("2.6.4.10_for_bc");
        sb.append(str7);
        sb.append(valueOf);
        sb.append("3.0");
        sb.append(str9);
        if (str3 == null) {
            str3 = "";
        }
        sb.append(str3);
        if (str4 == null) {
            str4 = "";
        }
        sb.append(str4);
        String sign = ac.getSign(j.b(sb.toString().getBytes()));
        String str10 = "";
        if (!TextUtils.isEmpty(str2)) {
            str10 = str2 + "&";
        }
        return String.format("%s?%sak=%s&av=%s&c=%s&v=%s&s=%s&d=%s&sv=%s&p=%s&t=%s&u=%s&is=%s&k=%s", str, str10, c(appkey), c(str5), c(aF), c("3.0"), c(sign), c(str7), "2.6.4.10_for_bc", str6, valueOf, "", str9, str8);
    }

    private static String c(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
