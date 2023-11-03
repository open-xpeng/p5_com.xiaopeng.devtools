package com.alibaba.sdk.android.man.crashreporter.global;

import java.io.Serializable;

/* loaded from: classes11.dex */
public class CrashReportDataForSave implements Serializable {
    private static final long serialVersionUID = -4732403896979420756L;
    public String content;
    public String fileName;
    public String hashCode;
    public String metaDataBase64;
    public String nativeCrashPath;
    public String path;
    public Integer times;
    public String toUTCrashMsg;
    public Long triggeredTime;
    public Integer type;
    public String utPage;
}
