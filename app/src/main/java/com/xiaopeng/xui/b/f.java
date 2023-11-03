package com.xiaopeng.xui.b;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: XSoundEffectManager.java */
/* loaded from: classes13.dex */
public class f {
    private ConcurrentHashMap<Integer, e> abl;
    private boolean abm;
    private ConcurrentHashMap<Integer, Boolean> abn;
    private ExecutorService mExecutorService;

    /* compiled from: XSoundEffectManager.java */
    /* loaded from: classes13.dex */
    private static class a {
        private static final f abo = new f();
    }

    private f() {
        this.abl = new ConcurrentHashMap<>();
        this.mExecutorService = Executors.newSingleThreadExecutor();
        this.abn = new ConcurrentHashMap<>();
    }

    public static f qN() {
        return a.abo;
    }

    public synchronized void play(final int i) {
        this.abm = false;
        this.abn.put(Integer.valueOf(i), false);
        this.mExecutorService.execute(new Runnable() { // from class: com.xiaopeng.xui.b.-$$Lambda$f$NAPh5ICIAzb0xgy001mJRgzwncU
            @Override // java.lang.Runnable
            public final void run() {
                f.this.ev(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void ev(int i) {
        if (this.abm) {
            log(String.format("play-not for destroy resource:%s", Integer.valueOf(i)));
            return;
        }
        Boolean bool = this.abn.get(Integer.valueOf(i));
        if (bool != null && bool.booleanValue()) {
            log(String.format("play-not for release resource:%s", Integer.valueOf(i)));
            return;
        }
        System.currentTimeMillis();
        e eVar = this.abl.get(Integer.valueOf(i));
        if (eVar == null) {
            eVar = b.et(i);
            this.abl.put(Integer.valueOf(i), eVar);
        }
        eVar.play();
    }

    public synchronized void release(final int i) {
        this.abn.put(Integer.valueOf(i), true);
        this.mExecutorService.execute(new Runnable() { // from class: com.xiaopeng.xui.b.-$$Lambda$f$qvfYch_ox2x_SNilcfeUwR8kSos
            @Override // java.lang.Runnable
            public final void run() {
                f.this.eu(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void eu(int i) {
        e eVar = this.abl.get(Integer.valueOf(i));
        if (eVar != null) {
            eVar.release();
        }
        log(String.format("release resource:%s", Integer.valueOf(i)));
    }

    private void log(String str) {
        com.xiaopeng.xui.d.f.f("xpui-XSoundManager", str);
    }
}
