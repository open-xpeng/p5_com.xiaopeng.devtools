package com.lzy.okgo.interceptor;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.lzy.okgo.f.c;
import com.lzy.okgo.f.d;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
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

/* loaded from: classes11.dex */
public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private Logger logger;
    private volatile Level mf = Level.NONE;
    private java.util.logging.Level mg;

    /* loaded from: classes11.dex */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public HttpLoggingInterceptor(String str) {
        this.logger = Logger.getLogger(str);
    }

    public void a(Level level) {
        if (this.mf == null) {
            throw new NullPointerException("printLevel == null. Use Level.NONE instead.");
        }
        this.mf = level;
    }

    public void a(java.util.logging.Level level) {
        this.mg = level;
    }

    private void log(String str) {
        this.logger.log(this.mg, str);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (this.mf == Level.NONE) {
            return chain.proceed(request);
        }
        a(request, chain.connection());
        long nanoTime = System.nanoTime();
        try {
            return a(chain.proceed(request), TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
        } catch (Exception e) {
            log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    private void a(Request request, Connection connection) throws IOException {
        StringBuilder sb;
        boolean z = this.mf == Level.BODY;
        boolean z2 = this.mf == Level.BODY || this.mf == Level.HEADERS;
        RequestBody body = request.body();
        boolean z3 = body != null;
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
        try {
            try {
                log("--> " + request.method() + ' ' + request.url() + ' ' + protocol);
                if (z2) {
                    if (z3) {
                        if (body.contentType() != null) {
                            log("\tContent-Type: " + body.contentType());
                        }
                        if (body.contentLength() != -1) {
                            log("\tContent-Length: " + body.contentLength());
                        }
                    }
                    Headers headers = request.headers();
                    int size = headers.size();
                    for (int i = 0; i < size; i++) {
                        String name = headers.name(i);
                        if (!"Content-Type".equalsIgnoreCase(name) && !HttpHeaders.CONTENT_LENGTH.equalsIgnoreCase(name)) {
                            log("\t" + name + ": " + headers.value(i));
                        }
                    }
                    log(" ");
                    if (z && z3) {
                        if (b(body.contentType())) {
                            a(request);
                        } else {
                            log("\tbody: maybe [binary body], omitted!");
                        }
                    }
                }
                sb = new StringBuilder();
            } catch (Exception e) {
                d.f(e);
                sb = new StringBuilder();
            }
            sb.append("--> END ");
            sb.append(request.method());
            log(sb.toString());
        } catch (Throwable th) {
            log("--> END " + request.method());
            throw th;
        }
    }

    private Response a(Response response, long j) {
        Headers headers;
        Response build = response.newBuilder().build();
        ResponseBody body = build.body();
        boolean z = true;
        boolean z2 = this.mf == Level.BODY;
        if (this.mf != Level.BODY && this.mf != Level.HEADERS) {
            z = false;
        }
        try {
            try {
                log("<-- " + build.code() + ' ' + build.message() + ' ' + build.request().url() + " (" + j + "ms）");
                if (z) {
                    int size = build.headers().size();
                    for (int i = 0; i < size; i++) {
                        log("\t" + headers.name(i) + ": " + headers.value(i));
                    }
                    log(" ");
                    if (z2 && okhttp3.internal.http.HttpHeaders.hasBody(build)) {
                        if (body == null) {
                            return response;
                        }
                        if (b(body.contentType())) {
                            byte[] b = c.b(body.byteStream());
                            log("\tbody:" + new String(b, a(body.contentType())));
                            return response.newBuilder().body(ResponseBody.create(body.contentType(), b)).build();
                        }
                        log("\tbody: maybe [binary body], omitted!");
                    }
                }
            } catch (Exception e) {
                d.f(e);
            }
            return response;
        } finally {
            log("<-- END HTTP");
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

    private void a(Request request) {
        try {
            RequestBody body = request.newBuilder().build().body();
            if (body == null) {
                return;
            }
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            Charset a = a(body.contentType());
            log("\tbody:" + buffer.readString(a));
        } catch (Exception e) {
            d.f(e);
        }
    }
}
