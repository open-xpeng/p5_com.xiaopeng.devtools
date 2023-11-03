package com.lzy.okgo.f;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: HttpUtils.java */
/* loaded from: classes11.dex */
public class b {
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032 A[Catch: UnsupportedEncodingException -> 0x007a, TryCatch #0 {UnsupportedEncodingException -> 0x007a, blocks: (B:2:0x0000, B:4:0x0010, B:7:0x0019, B:9:0x0024, B:10:0x002c, B:12:0x0032, B:13:0x0042, B:15:0x0048, B:17:0x006c, B:8:0x001f), top: B:22:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.lang.String r5, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r6) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.io.UnsupportedEncodingException -> L7a
            r0.<init>()     // Catch: java.io.UnsupportedEncodingException -> L7a
            r0.append(r5)     // Catch: java.io.UnsupportedEncodingException -> L7a
            r1 = 38
            int r1 = r5.indexOf(r1)     // Catch: java.io.UnsupportedEncodingException -> L7a
            if (r1 > 0) goto L1f
            r1 = 63
            int r1 = r5.indexOf(r1)     // Catch: java.io.UnsupportedEncodingException -> L7a
            if (r1 <= 0) goto L19
            goto L1f
        L19:
            java.lang.String r1 = "?"
            r0.append(r1)     // Catch: java.io.UnsupportedEncodingException -> L7a
            goto L24
        L1f:
            java.lang.String r1 = "&"
            r0.append(r1)     // Catch: java.io.UnsupportedEncodingException -> L7a
        L24:
            java.util.Set r6 = r6.entrySet()     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.util.Iterator r6 = r6.iterator()     // Catch: java.io.UnsupportedEncodingException -> L7a
        L2c:
            boolean r1 = r6.hasNext()     // Catch: java.io.UnsupportedEncodingException -> L7a
            if (r1 == 0) goto L6c
            java.lang.Object r1 = r6.next()     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.Object r2 = r1.getValue()     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.util.List r2 = (java.util.List) r2     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.util.Iterator r2 = r2.iterator()     // Catch: java.io.UnsupportedEncodingException -> L7a
        L42:
            boolean r3 = r2.hasNext()     // Catch: java.io.UnsupportedEncodingException -> L7a
            if (r3 == 0) goto L6b
            java.lang.Object r3 = r2.next()     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.String r4 = "UTF-8"
            java.lang.String r3 = java.net.URLEncoder.encode(r3, r4)     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.Object r4 = r1.getKey()     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.io.UnsupportedEncodingException -> L7a
            r0.append(r4)     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.String r4 = "="
            r0.append(r4)     // Catch: java.io.UnsupportedEncodingException -> L7a
            r0.append(r3)     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.String r3 = "&"
            r0.append(r3)     // Catch: java.io.UnsupportedEncodingException -> L7a
            goto L42
        L6b:
            goto L2c
        L6c:
            int r6 = r0.length()     // Catch: java.io.UnsupportedEncodingException -> L7a
            int r6 = r6 + (-1)
            r0.deleteCharAt(r6)     // Catch: java.io.UnsupportedEncodingException -> L7a
            java.lang.String r6 = r0.toString()     // Catch: java.io.UnsupportedEncodingException -> L7a
            return r6
        L7a:
            r6 = move-exception
            com.lzy.okgo.f.d.f(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lzy.okgo.f.b.b(java.lang.String, java.util.Map):java.lang.String");
    }

    public static Request.Builder a(Request.Builder builder, HttpHeaders httpHeaders) {
        if (httpHeaders.headersMap.isEmpty()) {
            return builder;
        }
        Headers.Builder builder2 = new Headers.Builder();
        try {
            for (Map.Entry<String, String> entry : httpHeaders.headersMap.entrySet()) {
                builder2.add(entry.getKey(), entry.getValue());
            }
        } catch (Exception e) {
            d.f(e);
        }
        builder.headers(builder2.build());
        return builder;
    }

    public static RequestBody a(HttpParams httpParams, boolean z) {
        if (httpParams.fileParamsMap.isEmpty() && !z) {
            FormBody.Builder builder = new FormBody.Builder();
            for (String str : httpParams.urlParamsMap.keySet()) {
                for (String str2 : httpParams.urlParamsMap.get(str)) {
                    builder.add(str, str2);
                }
            }
            return builder.build();
        }
        MultipartBody.Builder type = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (!httpParams.urlParamsMap.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : httpParams.urlParamsMap.entrySet()) {
                for (String str3 : entry.getValue()) {
                    type.addFormDataPart(entry.getKey(), str3);
                }
            }
        }
        for (Map.Entry<String, List<HttpParams.FileWrapper>> entry2 : httpParams.fileParamsMap.entrySet()) {
            for (HttpParams.FileWrapper fileWrapper : entry2.getValue()) {
                type.addFormDataPart(entry2.getKey(), fileWrapper.fileName, RequestBody.create(fileWrapper.contentType, fileWrapper.file));
            }
        }
        return type.build();
    }

    public static MediaType aS(String str) {
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(str.replace("#", ""));
        if (contentTypeFor == null) {
            return HttpParams.mq;
        }
        return MediaType.parse(contentTypeFor);
    }

    public static <T> T d(T t, String str) {
        if (t == null) {
            throw new NullPointerException(str);
        }
        return t;
    }

    public static void runOnUiThread(Runnable runnable) {
        com.lzy.okgo.a.cE().cF().post(runnable);
    }
}
