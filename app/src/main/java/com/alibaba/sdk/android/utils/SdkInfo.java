package com.alibaba.sdk.android.utils;

import android.text.TextUtils;
import java.util.Map;

/* loaded from: classes11.dex */
public class SdkInfo {
    String c;
    String d;
    String e;
    Map<String, String> g;

    public SdkInfo setSdkId(String str) {
        this.c = str;
        return this;
    }

    public SdkInfo setSdkVersion(String str) {
        this.d = str;
        return this;
    }

    public SdkInfo setAppKey(String str) {
        this.e = str;
        return this;
    }

    public SdkInfo setExt(Map<String, String> map) {
        this.g = map;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b() {
        if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.d)) {
            return null;
        }
        return this.c + "_" + this.d;
    }
}
