package com.lzy.okgo.f;

import android.text.TextUtils;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.request.base.Request;
import java.util.Locale;
import java.util.StringTokenizer;
import okhttp3.Headers;

/* compiled from: HeaderParser.java */
/* loaded from: classes11.dex */
public class a {
    public static <T> CacheEntity<T> a(Headers headers, T t, CacheMode cacheMode, String str) {
        long currentTimeMillis;
        long j;
        if (cacheMode == CacheMode.DEFAULT) {
            long aJ = HttpHeaders.aJ(headers.get(com.alibaba.sdk.android.oss.common.utils.HttpHeaders.DATE));
            currentTimeMillis = HttpHeaders.aK(headers.get(com.alibaba.sdk.android.oss.common.utils.HttpHeaders.EXPIRES));
            String s = HttpHeaders.s(headers.get(com.alibaba.sdk.android.oss.common.utils.HttpHeaders.CACHE_CONTROL), headers.get("Pragma"));
            if (TextUtils.isEmpty(s) && currentTimeMillis <= 0) {
                return null;
            }
            if (!TextUtils.isEmpty(s)) {
                StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
                j = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    String lowerCase = stringTokenizer.nextToken().trim().toLowerCase(Locale.getDefault());
                    if (lowerCase.equals("no-cache") || lowerCase.equals("no-store")) {
                        return null;
                    }
                    if (lowerCase.startsWith("max-age=")) {
                        try {
                            long parseLong = Long.parseLong(lowerCase.substring(8));
                            if (parseLong <= 0) {
                                return null;
                            }
                            j = parseLong;
                        } catch (Exception e) {
                            d.f(e);
                        }
                    }
                }
            } else {
                j = 0;
            }
            long currentTimeMillis2 = System.currentTimeMillis();
            if (aJ <= 0) {
                aJ = currentTimeMillis2;
            }
            if (j > 0) {
                currentTimeMillis = aJ + (j * 1000);
            } else if (currentTimeMillis < 0) {
                currentTimeMillis = 0;
            }
        } else {
            currentTimeMillis = System.currentTimeMillis();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        for (String str2 : headers.names()) {
            httpHeaders.put(str2, headers.get(str2));
        }
        CacheEntity<T> cacheEntity = new CacheEntity<>();
        cacheEntity.setKey(str);
        cacheEntity.setData(t);
        cacheEntity.e(currentTimeMillis);
        cacheEntity.a(httpHeaders);
        return cacheEntity;
    }

    public static <T> void a(Request request, CacheEntity<T> cacheEntity, CacheMode cacheMode) {
        HttpHeaders cP;
        if (cacheEntity != null && cacheMode == CacheMode.DEFAULT && (cP = cacheEntity.cP()) != null) {
            String str = cP.get(com.alibaba.sdk.android.oss.common.utils.HttpHeaders.ETAG);
            if (str != null) {
                request.t("If-None-Match", str);
            }
            long aL = HttpHeaders.aL(cP.get(com.alibaba.sdk.android.oss.common.utils.HttpHeaders.LAST_MODIFIED));
            if (aL > 0) {
                request.t("If-Modified-Since", HttpHeaders.f(aL));
            }
        }
    }
}
