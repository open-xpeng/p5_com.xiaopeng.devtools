package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.Headers;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSource;

/* loaded from: classes13.dex */
public final class Response implements Closeable {
    @Nullable
    final ResponseBody body;
    private volatile CacheControl cacheControl;
    @Nullable
    final Response cacheResponse;
    final int code;
    @Nullable
    final Handshake handshake;
    final Headers headers;
    final String message;
    @Nullable
    final Response networkResponse;
    @Nullable
    final Response priorResponse;
    final Protocol protocol;
    final long receivedResponseAtMillis;
    final Request request;
    final long sentRequestAtMillis;

    Response(Builder builder) {
        this.request = builder.request;
        this.protocol = builder.protocol;
        this.code = builder.code;
        this.message = builder.message;
        this.handshake = builder.handshake;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.networkResponse = builder.networkResponse;
        this.cacheResponse = builder.cacheResponse;
        this.priorResponse = builder.priorResponse;
        this.sentRequestAtMillis = builder.sentRequestAtMillis;
        this.receivedResponseAtMillis = builder.receivedResponseAtMillis;
    }

    public Request request() {
        return this.request;
    }

    public Protocol protocol() {
        return this.protocol;
    }

    public int code() {
        return this.code;
    }

    public boolean isSuccessful() {
        return this.code >= 200 && this.code < 300;
    }

    public String message() {
        return this.message;
    }

    public Handshake handshake() {
        return this.handshake;
    }

    public List<String> headers(String str) {
        return this.headers.values(str);
    }

    @Nullable
    public String header(String str) {
        return header(str, null);
    }

    @Nullable
    public String header(String str, @Nullable String str2) {
        String str3 = this.headers.get(str);
        return str3 != null ? str3 : str2;
    }

    public Headers headers() {
        return this.headers;
    }

    public ResponseBody peekBody(long j) throws IOException {
        BufferedSource source = this.body.source();
        source.request(j);
        Buffer clone = source.buffer().clone();
        if (clone.size() > j) {
            Buffer buffer = new Buffer();
            buffer.write(clone, j);
            clone.clear();
            clone = buffer;
        }
        return ResponseBody.create(this.body.contentType(), clone.size(), clone);
    }

    @Nullable
    public ResponseBody body() {
        return this.body;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public boolean isRedirect() {
        switch (this.code) {
            case 300:
            case 301:
            case 302:
            case 303:
            case StatusLine.HTTP_TEMP_REDIRECT /* 307 */:
            case StatusLine.HTTP_PERM_REDIRECT /* 308 */:
                return true;
            case 304:
            case 305:
            case 306:
            default:
                return false;
        }
    }

    @Nullable
    public Response networkResponse() {
        return this.networkResponse;
    }

    @Nullable
    public Response cacheResponse() {
        return this.cacheResponse;
    }

    @Nullable
    public Response priorResponse() {
        return this.priorResponse;
    }

    public List<Challenge> challenges() {
        String str;
        if (this.code == 401) {
            str = "WWW-Authenticate";
        } else if (this.code == 407) {
            str = "Proxy-Authenticate";
        } else {
            return Collections.emptyList();
        }
        return HttpHeaders.parseChallenges(headers(), str);
    }

    public CacheControl cacheControl() {
        CacheControl cacheControl = this.cacheControl;
        if (cacheControl != null) {
            return cacheControl;
        }
        CacheControl parse = CacheControl.parse(this.headers);
        this.cacheControl = parse;
        return parse;
    }

    public long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.body == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        this.body.close();
    }

    public String toString() {
        return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
    }

    /* loaded from: classes13.dex */
    public static class Builder {
        ResponseBody body;
        Response cacheResponse;
        int code;
        @Nullable
        Handshake handshake;
        Headers.Builder headers;
        String message;
        Response networkResponse;
        Response priorResponse;
        Protocol protocol;
        long receivedResponseAtMillis;
        Request request;
        long sentRequestAtMillis;

        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }

        Builder(Response response) {
            this.code = -1;
            this.request = response.request;
            this.protocol = response.protocol;
            this.code = response.code;
            this.message = response.message;
            this.handshake = response.handshake;
            this.headers = response.headers.newBuilder();
            this.body = response.body;
            this.networkResponse = response.networkResponse;
            this.cacheResponse = response.cacheResponse;
            this.priorResponse = response.priorResponse;
            this.sentRequestAtMillis = response.sentRequestAtMillis;
            this.receivedResponseAtMillis = response.receivedResponseAtMillis;
        }

        public Builder request(Request request) {
            this.request = request;
            return this;
        }

        public Builder protocol(Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder code(int i) {
            this.code = i;
            return this;
        }

        public Builder message(String str) {
            this.message = str;
            return this;
        }

        public Builder handshake(@Nullable Handshake handshake) {
            this.handshake = handshake;
            return this;
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder headers(Headers headers) {
            this.headers = headers.newBuilder();
            return this;
        }

        public Builder body(@Nullable ResponseBody responseBody) {
            this.body = responseBody;
            return this;
        }

        public Builder networkResponse(@Nullable Response response) {
            if (response != null) {
                checkSupportResponse("networkResponse", response);
            }
            this.networkResponse = response;
            return this;
        }

        public Builder cacheResponse(@Nullable Response response) {
            if (response != null) {
                checkSupportResponse("cacheResponse", response);
            }
            this.cacheResponse = response;
            return this;
        }

        private void checkSupportResponse(String str, Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException(str + ".body != null");
            } else if (response.networkResponse != null) {
                throw new IllegalArgumentException(str + ".networkResponse != null");
            } else if (response.cacheResponse != null) {
                throw new IllegalArgumentException(str + ".cacheResponse != null");
            } else if (response.priorResponse != null) {
                throw new IllegalArgumentException(str + ".priorResponse != null");
            }
        }

        public Builder priorResponse(@Nullable Response response) {
            if (response != null) {
                checkPriorResponse(response);
            }
            this.priorResponse = response;
            return this;
        }

        private void checkPriorResponse(Response response) {
            if (response.body != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public Builder sentRequestAtMillis(long j) {
            this.sentRequestAtMillis = j;
            return this;
        }

        public Builder receivedResponseAtMillis(long j) {
            this.receivedResponseAtMillis = j;
            return this;
        }

        public Response build() {
            if (this.request == null) {
                throw new IllegalStateException("request == null");
            }
            if (this.protocol == null) {
                throw new IllegalStateException("protocol == null");
            }
            if (this.code < 0) {
                throw new IllegalStateException("code < 0: " + this.code);
            } else if (this.message == null) {
                throw new IllegalStateException("message == null");
            } else {
                return new Response(this);
            }
        }
    }
}
