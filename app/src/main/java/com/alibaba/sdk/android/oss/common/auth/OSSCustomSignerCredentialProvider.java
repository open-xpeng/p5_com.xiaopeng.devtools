package com.alibaba.sdk.android.oss.common.auth;

/* loaded from: classes11.dex */
public abstract class OSSCustomSignerCredentialProvider implements OSSCredentialProvider {
    public abstract String signContent(String str);

    @Override // com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
    public OSSFederationToken getFederationToken() {
        return null;
    }
}
