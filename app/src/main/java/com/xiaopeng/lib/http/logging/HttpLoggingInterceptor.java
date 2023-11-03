package com.xiaopeng.lib.http.logging;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.xiaopeng.lib.http.logging.a;
import com.xiaopeng.lib.utils.c;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/* loaded from: classes12.dex */
public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Level UT = Level.NONE;
    private a UU = new a();
    private java.util.logging.Level mg;
    private final String tag;

    /* loaded from: classes12.dex */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public HttpLoggingInterceptor(String str) {
        this.tag = str;
    }

    public void a(Level level) {
        if (this.UT == null) {
            throw new NullPointerException("printLevel == null. Use Level.NONE instead.");
        }
        this.UT = level;
    }

    public void a(java.util.logging.Level level) {
        this.mg = level;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (this.UT == Level.NONE) {
            return chain.proceed(request);
        }
        a.c oe = this.UU.oe();
        a(request, chain.connection(), oe);
        long nanoTime = System.nanoTime();
        try {
            Response a = a(chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime), oe);
            oe.end();
            return a;
        } catch (Exception e) {
            oe.log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    private void a(Request request, Connection connection, a.c cVar) throws IOException {
        StringBuilder sb;
        boolean z = this.UT == Level.BODY;
        boolean z2 = this.UT == Level.BODY || this.UT == Level.HEADERS;
        RequestBody body = request.body();
        boolean z3 = body != null;
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        try {
            try {
                cVar.log("--> " + request.method() + ' ' + request.url() + ' ' + protocol);
                if (z2) {
                    if (z3) {
                        if (body.contentType() != null) {
                            cVar.log("\tContent-Type: " + body.contentType());
                        }
                        if (body.contentLength() != -1) {
                            cVar.log("\tContent-Length: " + body.contentLength());
                        }
                    }
                    Headers headers = request.headers();
                    int size = headers.size();
                    for (int i = 0; i < size; i++) {
                        String name = headers.name(i);
                        if (!"Content-Type".equalsIgnoreCase(name) && !HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(name)) {
                            cVar.log("\t" + name + ": " + headers.value(i));
                        }
                    }
                    cVar.log(" ");
                    if (z && z3) {
                        if (b(body.contentType())) {
                            a(request, cVar);
                        } else {
                            cVar.log("\tbody: maybe [binary body], omitted!");
                        }
                    }
                }
                sb = new StringBuilder();
            } catch (Exception e) {
                c.b(this.tag, "logForRequest error!", e);
                sb = new StringBuilder();
            }
            sb.append("--> END ");
            sb.append(request.method());
            cVar.log(sb.toString());
        } catch (Throwable th) {
            cVar.log("--> END " + request.method());
            throw th;
        }
    }

    private Response a(Response response, long j, a.c cVar) {
        Headers headers;
        Response build = response.newBuilder().build();
        ResponseBody body = build.body();
        boolean z = true;
        boolean z2 = this.UT == Level.BODY;
        if (this.UT != Level.BODY && this.UT != Level.HEADERS) {
            z = false;
        }
        try {
            try {
                int code = build.code();
                cVar.log("<-- " + code + ' ' + build.message() + ' ' + build.request().url() + " (" + j + "ms）");
                cVar.code(code);
                if (z) {
                    int size = build.headers().size();
                    for (int i = 0; i < size; i++) {
                        cVar.log("\t" + headers.name(i) + ": " + headers.value(i));
                    }
                    cVar.log(" ");
                    if (z2 && okhttp3.internal.http.HttpHeaders.hasBody(build)) {
                        if (body == null) {
                            return response;
                        }
                        if (b(body.contentType())) {
                            byte[] b = com.lzy.okgo.f.c.b(body.byteStream());
                            String str = new String(b, a(body.contentType()));
                            cVar.log("\tbody:" + str);
                            if (str.contains("\"code\":")) {
                                if (!str.contains("\"code\":0,") && !str.contains("\"code\":0}") && !str.contains("\"code\":200,") && !str.contains("\"code\":200}")) {
                                    if (code <= 299) {
                                        code = -1;
                                    }
                                    cVar.code(code);
                                }
                                code = 200;
                                cVar.code(code);
                            }
                            return response.newBuilder().body(ResponseBody.create(body.contentType(), b)).build();
                        }
                        cVar.log("\tbody: maybe [binary body], omitted!");
                    }
                }
            } catch (Exception e) {
                c.b(this.tag, "logForResponse error!", e);
            }
            return response;
        } finally {
            cVar.log("<-- END HTTP");
        }
    }

    private static Charset a(MediaType mediaType) {
        Charset charset = mediaType != null ? mediaType.charset(UTF8) : UTF8;
        return charset == null ? UTF8 : charset;
    }

    private static boolean b(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        if (mediaType.type() == null || !mediaType.type().equals("text")) {
            String subtype = mediaType.subtype();
            if (subtype != null) {
                String lowerCase = subtype.toLowerCase();
                if (lowerCase.contains("x-www-form-urlencoded") || lowerCase.contains("json") || lowerCase.contains("xml") || lowerCase.contains("html")) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private void a(Request request, a.c cVar) {
        try {
            RequestBody body = request.newBuilder().build().body();
            if (body == null) {
                return;
            }
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            Charset a = a(body.contentType());
            cVar.log("\tbody:" + buffer.readString(a));
        } catch (Exception e) {
            c.b(this.tag, "bodyToString error!", e);
        }
    }
}
