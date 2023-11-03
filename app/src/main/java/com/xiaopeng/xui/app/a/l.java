package com.xiaopeng.xui.app.a;

import android.os.Handler;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: XTimeLogs.java */
/* loaded from: classes13.dex */
class l implements Runnable {
    private long aaM;
    private long mEndTime;
    private String mName;
    private long mStartTime;
    private Handler mHandler = new Handler();
    private LinkedHashMap<String, Long> aaL = new LinkedHashMap<>();

    private l() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static l qJ() {
        return new l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start(String str) {
        this.mName = str;
        this.mStartTime = System.nanoTime();
        this.aaM = this.mStartTime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fi(String str) {
        this.aaL.put(str, Long.valueOf(System.nanoTime() - this.aaM));
        this.aaM = System.nanoTime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void end() {
        this.mEndTime = System.nanoTime() - this.mStartTime;
        this.mHandler.post(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        Set<Map.Entry<String, Long>> entrySet = this.aaL.entrySet();
        StringBuilder sb = new StringBuilder();
        sb.append("total:");
        sb.append(this.mEndTime / 1000);
        sb.append("μs");
        for (Map.Entry<String, Long> entry : entrySet) {
            sb.append(", ");
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue().longValue() / 1000);
        }
        this.aaL.clear();
        com.xiaopeng.xui.d.f.f(this.mName, sb.toString());
    }
}
