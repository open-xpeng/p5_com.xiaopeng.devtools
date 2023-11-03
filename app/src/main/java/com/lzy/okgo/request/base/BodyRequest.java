package com.lzy.okgo.request.base;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.lzy.okgo.f.b;
import com.lzy.okgo.f.d;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.base.BodyRequest;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/* loaded from: classes11.dex */
public abstract class BodyRequest<T, R extends BodyRequest> extends Request<T, R> {
    private static final long serialVersionUID = -6459175248476927501L;
    protected byte[] bs;
    protected String content;
    protected transient File file;
    protected boolean isMultipart;
    protected boolean isSpliceUrl;
    protected transient MediaType my;
    protected RequestBody requestBody;

    public BodyRequest(String str) {
        super(str);
        this.isMultipart = false;
        this.isSpliceUrl = false;
    }

    public R y(boolean z) {
        this.isMultipart = z;
        return this;
    }

    public R b(String str, File file) {
        this.params.a(str, file);
        return this;
    }

    public R aO(String str) {
        this.content = str;
        this.my = HttpParams.mp;
        return this;
    }

    public R d(File file) {
        this.file = file;
        this.my = b.aS(file.getName());
        return this;
    }

    @Override // com.lzy.okgo.request.base.Request
    public RequestBody df() {
        if (this.isSpliceUrl) {
            this.url = b.b(this.baseUrl, this.params.urlParamsMap);
        }
        return this.requestBody != null ? this.requestBody : (this.content == null || this.my == null) ? (this.bs == null || this.my == null) ? (this.file == null || this.my == null) ? b.a(this.params, this.isMultipart) : RequestBody.create(this.my, this.file) : RequestBody.create(this.my, this.bs) : RequestBody.create(this.my, this.content);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Request.Builder b(RequestBody requestBody) {
        try {
            t(HttpHeaders.CONTENT_LENGTH, String.valueOf(requestBody.contentLength()));
        } catch (IOException e) {
            d.f(e);
        }
        return b.a(new Request.Builder(), this.headers);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.my == null ? "" : this.my.toString());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (!TextUtils.isEmpty(str)) {
            this.my = MediaType.parse(str);
        }
    }
}
