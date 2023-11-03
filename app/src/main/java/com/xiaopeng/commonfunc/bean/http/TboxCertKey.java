package com.xiaopeng.commonfunc.bean.http;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class TboxCertKey {
    @SerializedName("bsk")
    private CertKey mBLeSecKey;
    @SerializedName("certkeys")
    private CertKey[] mCertKeys;

    public TboxCertKey(CertKey[] certKeyArr, CertKey certKey) {
        this.mCertKeys = certKeyArr;
        this.mBLeSecKey = certKey;
    }

    public CertKey[] getCertKeys() {
        return this.mCertKeys;
    }

    public void setCertKeys(CertKey[] certKeyArr) {
        this.mCertKeys = certKeyArr;
    }

    public CertKey getBLeSecKey() {
        return this.mBLeSecKey;
    }

    public void setBLeSecKey(CertKey certKey) {
        this.mBLeSecKey = certKey;
    }
}
