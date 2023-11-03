package com.lzy.okgo.request.base;

import com.lzy.okgo.f.d;
import com.lzy.okgo.model.Progress;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* compiled from: ProgressRequestBody.java */
/* loaded from: classes11.dex */
public class a<T> extends RequestBody {
    private b mA;
    private com.lzy.okgo.b.b<T> mz;
    private RequestBody requestBody;

    /* compiled from: ProgressRequestBody.java */
    /* loaded from: classes11.dex */
    public interface b {
        void uploadProgress(Progress progress);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(RequestBody requestBody, com.lzy.okgo.b.b<T> bVar) {
        this.requestBody = requestBody;
        this.mz = bVar;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.requestBody.contentType();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() {
        try {
            return this.requestBody.contentLength();
        } catch (IOException e) {
            d.f(e);
            return -1L;
        }
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        BufferedSink buffer = Okio.buffer(new C0037a(bufferedSink));
        this.requestBody.writeTo(buffer);
        buffer.flush();
    }

    /* compiled from: ProgressRequestBody.java */
    /* renamed from: com.lzy.okgo.request.base.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    private final class C0037a extends ForwardingSink {
        private Progress mD;

        C0037a(Sink sink) {
            super(sink);
            this.mD = new Progress();
            this.mD.totalSize = a.this.contentLength();
        }

        @Override // okio.ForwardingSink, okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            super.write(buffer, j);
            Progress.a(this.mD, j, new Progress.a() { // from class: com.lzy.okgo.request.base.a.a.1
                @Override // com.lzy.okgo.model.Progress.a
                public void a(Progress progress) {
                    if (a.this.mA != null) {
                        a.this.mA.uploadProgress(progress);
                    } else {
                        a.this.b(progress);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Progress progress) {
        com.lzy.okgo.f.b.runOnUiThread(new Runnable() { // from class: com.lzy.okgo.request.base.a.1
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.mz != null) {
                    a.this.mz.uploadProgress(progress);
                }
            }
        });
    }

    public void a(b bVar) {
        this.mA = bVar;
    }
}
