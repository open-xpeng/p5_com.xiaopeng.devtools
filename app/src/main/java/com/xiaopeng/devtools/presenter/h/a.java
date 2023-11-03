package com.xiaopeng.devtools.presenter.h;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaopeng.commonfunc.bean.car.EcuUpdateResult;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.m;
import com.xiaopeng.lib.utils.j;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: FastbootUpdatePresenter.java */
/* loaded from: classes12.dex */
public final class a extends Handler {
    private static String zD = "/data/tbox/";
    private Context mContext;
    private String mResult;
    private com.xiaopeng.devtools.view.update.d zE;
    private String zF;
    private boolean zG;
    private int zH;
    private int zI;

    public a(Context context, Looper looper, com.xiaopeng.devtools.view.update.d dVar) {
        super(looper);
        this.mContext = context;
        this.zE = dVar;
        this.zG = false;
        this.mResult = "unkown";
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                kj();
                return;
            case 2:
            case 3:
            case 4:
                M(message.what == 4);
                if (message.what == 2) {
                    sendEmptyMessageDelayed(2, 3000L);
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Integer num) {
        switch (num.intValue()) {
            case 1:
                this.zI++;
                if (this.zI == this.zH) {
                    kk();
                    return;
                }
                return;
            case 2:
                kl();
                return;
            default:
                return;
        }
    }

    public boolean kg() {
        this.zF = g.V(this.mContext) + "/tbox/fastboot/";
        return new File(this.zF).exists();
    }

    public void kh() {
        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "startUpdate()");
        if (!this.zG) {
            sendEmptyMessage(1);
            this.zG = true;
        }
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public String ki() {
        return this.mResult;
    }

    private void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    private void kj() {
        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "doUpdate()");
        if (kg()) {
            this.zI = 0;
            this.zH = 0;
            cv(0);
            iv();
            g.aX(zD);
            g.aW(zD);
            this.zH = g.M(this.zF, zD);
            return;
        }
        this.zG = false;
        this.mResult = "no file to update";
        cv(2);
    }

    private void kk() {
        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "handleFileCopyDone()");
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.h.-$$Lambda$a$hcMj8dTXRJBEBRjM3lOf4IZuTSQ
            @Override // java.lang.Runnable
            public final void run() {
                a.this.ko();
            }
        });
        sendEmptyMessageDelayed(4, 300000L);
        kn();
    }

    private void kl() {
        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "handleCopyFileError()");
        this.mResult = "file copy error";
        cv(2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    private void M(boolean z) {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        ?? r10;
        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "handleCheckResult()");
        File file = new File(zD + "result");
        if (!file.exists()) {
            if (z) {
                this.zG = false;
                this.mResult = "timeout";
                cv(2);
                return;
            }
            return;
        }
        BufferedReader bufferedReader2 = null;
        try {
            try {
                fileReader = new FileReader(file);
                try {
                    bufferedReader = new BufferedReader(fileReader);
                    try {
                        String readLine = bufferedReader.readLine();
                        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "udpate result = " + readLine);
                        if (Integer.parseInt(readLine) == 100) {
                            this.mResult = EcuUpdateResult.RESULT_SUCCESS;
                            r10 = 1;
                            cv(1);
                        } else {
                            this.mResult = "flash error";
                            cv(2);
                            r10 = "flash error";
                        }
                        this.zG = false;
                        g.closeQuietly(bufferedReader);
                        bufferedReader2 = r10;
                    } catch (Exception e) {
                        bufferedReader2 = bufferedReader;
                        com.xiaopeng.lib.utils.c.i("FastbootUpdater", "fail to get update result");
                        g.closeQuietly(bufferedReader2);
                        bufferedReader2 = bufferedReader2;
                        g.closeQuietly(fileReader);
                        removeMessages(4);
                    } catch (Throwable th2) {
                        th = th2;
                        g.closeQuietly(bufferedReader);
                        g.closeQuietly(fileReader);
                        removeMessages(4);
                        throw th;
                    }
                } catch (Exception e2) {
                }
            } catch (Exception e3) {
                fileReader = null;
            } catch (Throwable th3) {
                fileReader = null;
                th = th3;
                bufferedReader = null;
            }
            g.closeQuietly(fileReader);
            removeMessages(4);
        } catch (Throwable th4) {
            bufferedReader = bufferedReader2;
            th = th4;
        }
    }

    private void cv(int i) {
        if (this.zE != null) {
            this.zE.dC(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: km */
    public void ko() {
        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "doFastboot()");
        String str = zD + "fastboot.sh";
        new File(str).setExecutable(true, false);
        m.ba(str);
    }

    private void kn() {
        com.xiaopeng.lib.utils.c.f("FastbootUpdater", "watchUpdateResult()");
        try {
            FileSystem fileSystem = FileSystems.getDefault();
            WatchService newWatchService = fileSystem.newWatchService();
            fileSystem.getPath(zD, new String[0]).register(newWatchService, StandardWatchEventKinds.ENTRY_CREATE);
            while (true) {
                WatchKey poll = newWatchService.poll(3000L, TimeUnit.MILLISECONDS);
                if (poll != null) {
                    for (WatchEvent<?> watchEvent : poll.pollEvents()) {
                        if (watchEvent.kind() != StandardWatchEventKinds.OVERFLOW) {
                            Path path = (Path) watchEvent.context();
                            com.xiaopeng.lib.utils.c.f("FastbootUpdater", path + " is created");
                            if (path.endsWith("result")) {
                                com.xiaopeng.lib.utils.c.f("FastbootUpdater", "result is created");
                                removeMessages(4);
                                sendEmptyMessageDelayed(3, 1000L);
                            }
                            if (!poll.reset()) {
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            com.xiaopeng.lib.utils.c.i("FastbootUpdater", "fail to watch update result: " + e);
            sendEmptyMessageDelayed(2, 3000L);
        }
    }
}
