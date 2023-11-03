package com.alibaba.sdk.android.httpdns.probe;

/* loaded from: classes11.dex */
public interface IPProbeService {

    /* loaded from: classes11.dex */
    public enum a {
        NO_PROBING,
        PROBING
    }

    a getProbeStatus(String str);

    void launchIPProbeTask(String str, int i, String[] strArr);

    void setIPListUpdateCallback(b bVar);

    boolean stopIPProbeTask(String str);
}
