package com.xiaopeng.devtools.presenter.aftersales;

import android.content.pm.IPackageDataObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.lib.utils.j;

/* compiled from: StorageMemoryPresenter.java */
/* loaded from: classes12.dex */
public class f {
    private com.xiaopeng.devtools.view.aftersales.cdu.b vW;
    private final String vU = "clear";
    private a vV = new a();
    private PackageManager mPackageManager = MyApplication.getContext().getPackageManager();
    private AudioManager mAudioManager = (AudioManager) MyApplication.getContext().getSystemService("audio");

    public f(com.xiaopeng.devtools.view.aftersales.cdu.b bVar) {
        this.vW = bVar;
    }

    public void iq() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$f$SLWBJkYHQyf95CJbUEYXCRlwBEs
            @Override // java.lang.Runnable
            public final void run() {
                f.this.iu();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void iu() {
        long j = 0;
        for (PackageInfo packageInfo : this.mPackageManager.getInstalledPackages(8192)) {
            j += com.xiaopeng.devtools.utils.g.h(MyApplication.getContext(), packageInfo.packageName);
        }
        for (int i = 0; i < com.xiaopeng.devtools.a.nG.length; i++) {
            j += com.xiaopeng.devtools.utils.g.cz(com.xiaopeng.devtools.a.nG[i]);
            com.xiaopeng.lib.utils.c.f("StorageMemoryPresenter", "checkRemovableSize " + com.xiaopeng.devtools.a.nG[i] + " " + j);
        }
        if (this.vW != null) {
            this.vW.de(com.xiaopeng.devtools.utils.d.k(j));
        }
    }

    public void ir() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$f$MoXhoJ9NmyJtVIfhcYbdRyzTwk4
            @Override // java.lang.Runnable
            public final void run() {
                f.this.it();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void it() {
        for (PackageInfo packageInfo : this.mPackageManager.getInstalledPackages(8192)) {
            this.mPackageManager.deleteApplicationCacheFiles(packageInfo.packageName, this.vV);
        }
        is();
        for (String str : com.xiaopeng.devtools.a.nG) {
            com.xiaopeng.devtools.utils.g.b(str, "/data/Log/log0", false);
        }
        com.xiaopeng.devtools.utils.b.recordRepairModeAction("free storage", "triggered");
        if (this.vW != null) {
            this.vW.mj();
        }
    }

    private void is() {
        this.mAudioManager.setParameters("pcm_dump=clear");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StorageMemoryPresenter.java */
    /* loaded from: classes12.dex */
    public class a extends IPackageDataObserver.Stub {
        a() {
        }

        public void onRemoveCompleted(String str, boolean z) {
            com.xiaopeng.lib.utils.c.f("StorageMemoryPresenter", "onRemoveCompleted packageName: " + str + ", succeeded: " + z);
        }
    }
}
