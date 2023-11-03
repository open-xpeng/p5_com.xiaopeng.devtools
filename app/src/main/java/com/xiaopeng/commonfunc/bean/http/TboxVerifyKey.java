package com.xiaopeng.commonfunc.bean.http;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class TboxVerifyKey {
    @SerializedName("certkeys")
    private CertKey[] mCertKeys;
    @SerializedName("cas")
    private TboxCaCert mTboxCaCert;

    public TboxVerifyKey(CertKey[] certKeyArr, TboxCaCert tboxCaCert) {
        this.mCertKeys = certKeyArr;
        this.mTboxCaCert = tboxCaCert;
    }

    public CertKey[] getCertKeys() {
        return this.mCertKeys;
    }

    public void setCertKeys(CertKey[] certKeyArr) {
        this.mCertKeys = certKeyArr;
    }

    public TboxCaCert getTboxCaCert() {
        return this.mTboxCaCert;
    }

    public void setTboxCaCert(TboxCaCert tboxCaCert) {
        this.mTboxCaCert = tboxCaCert;
    }
}
