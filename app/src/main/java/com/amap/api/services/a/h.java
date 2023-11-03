package com.amap.api.services.a;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BasicLBSRestHandler.java */
/* loaded from: classes11.dex */
public abstract class h<T, V> extends a<T, V> {
    protected abstract String e();

    public h(Context context, T t) {
        super(context, t);
    }

    @Override // com.amap.api.services.a.bh
    public byte[] bk() {
        try {
            String e = e();
            String d = d(e);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(e);
            String a = f.a();
            stringBuffer.append("&ts=" + a);
            stringBuffer.append("&scode=" + f.a(this.ei, a, d));
            return stringBuffer.toString().getBytes("utf-8");
        } catch (Throwable th) {
            br.a(th, "ProtocalHandler", "getEntity");
            return null;
        }
    }

    @Override // com.amap.api.services.a.a, com.amap.api.services.a.bh
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.services.a.a, com.amap.api.services.a.bh
    public Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Accept-Encoding", "gzip");
        hashMap.put(HttpHeaders.USER_AGENT, "AMAP SDK Android Search 3.5.0");
        hashMap.put("X-INFO", f.a(this.ei, bt.eN, null, false));
        hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", "3.5.0", "sea"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    @Override // com.amap.api.services.a.a
    protected V bh() {
        return null;
    }

    private String d(String str) {
        String[] split = str.split("&");
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(c(str2));
            stringBuffer.append("&");
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
        }
        return str;
    }

    protected String c(String str) {
        if (str == null) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            br.a(e, "ProtocalHandler", "strReEncoder");
            return "";
        } catch (Exception e2) {
            br.a(e2, "ProtocalHandler", "strReEncoderException");
            return "";
        }
    }
}
