package com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter;

import android.support.annotation.Nullable;
import com.lzy.okgo.model.a;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.http.statistic.HttpCounter;
import com.xiaopeng.lib.framework.netchannelmodule.http.traffic.CountingInputStream;
import com.xiaopeng.lib.framework.netchannelmodule.http.traffic.ICollector;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import okhttp3.Response;

/* loaded from: classes12.dex */
public class ResponseAdapter implements IResponse {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static volatile boolean mEnableStat;
    private ICollector mCollector;
    private String mDomain;
    private a<String> mInternalResponse;
    private Response mRawResponse;

    public ResponseAdapter(a aVar) {
        this.mCollector = new ICollector() { // from class: com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.ResponseAdapter.1
            @Override // com.xiaopeng.lib.framework.netchannelmodule.http.traffic.ICollector
            public String getDomain() {
                return ResponseAdapter.this.mDomain;
            }
        };
        this.mInternalResponse = aVar;
        this.mRawResponse = null;
        this.mDomain = getDomain(this.mRawResponse);
    }

    public ResponseAdapter(Response response) {
        this.mCollector = new ICollector() { // from class: com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.ResponseAdapter.1
            @Override // com.xiaopeng.lib.framework.netchannelmodule.http.traffic.ICollector
            public String getDomain() {
                return ResponseAdapter.this.mDomain;
            }
        };
        this.mRawResponse = response;
        this.mInternalResponse = new a<>();
        this.mInternalResponse.a(response);
        this.mDomain = getDomain(this.mRawResponse);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    public int code() {
        if (this.mRawResponse != null) {
            return this.mRawResponse.code();
        }
        return this.mInternalResponse.code();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    @Nullable
    public String body() {
        if (this.mRawResponse == null) {
            String dd = this.mInternalResponse.dd();
            if (dd != null && mEnableStat) {
                HttpCounter.getInstance().addReceivedSize(this.mDomain, dd.length());
            }
            return dd;
        } else if (this.mRawResponse.body() == null) {
            return null;
        } else {
            try {
                String string = this.mRawResponse.body().string();
                try {
                    if (mEnableStat) {
                        HttpCounter.getInstance().addReceivedSize(this.mDomain, string.length());
                        return string;
                    }
                    return string;
                } catch (Exception e) {
                    return string;
                }
            } catch (Exception e2) {
                return null;
            }
        }
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    @Nullable
    public InputStream byteStream() {
        if (this.mRawResponse == null || this.mRawResponse.body() == null) {
            return null;
        }
        try {
            CountingInputStream countingInputStream = new CountingInputStream(this.mCollector, this.mRawResponse.body().byteStream());
            try {
                countingInputStream.setStatisticCounter(HttpCounter.getInstance());
                return countingInputStream;
            } catch (Exception e) {
                return countingInputStream;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    @Nullable
    public String header(String str) {
        if (this.mRawResponse == null) {
            return null;
        }
        return this.mRawResponse.header(str);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    @Nullable
    public String message() {
        if (this.mRawResponse == null) {
            return this.mInternalResponse.message();
        }
        return this.mRawResponse.message();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    @Nullable
    public Throwable getException() {
        return this.mInternalResponse.getException();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    @Nullable
    public Response getRawResponse() {
        if (this.mRawResponse != null) {
            return this.mRawResponse;
        }
        return this.mInternalResponse.getRawResponse();
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse
    @Nullable
    public Map<String, List<String>> headers() {
        Response rawResponse = getRawResponse();
        if (rawResponse == null || rawResponse.headers() == null) {
            return null;
        }
        return rawResponse.headers().toMultimap();
    }

    @Nullable
    private String getDomain(Response response) {
        if (response != null && response.isSuccessful() && response.request() != null) {
            return response.request().url().host();
        }
        return null;
    }

    public static void enableStat(boolean z) {
        mEnableStat = z;
    }
}
