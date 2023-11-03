package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.system.c.c;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.b;
import com.xiaopeng.lib.utils.j;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes12.dex */
public class XpuImagePresenter {
    private static final byte[] xD = {1, 2, 11, 0, 0, 0, 0, 0, 0, 0, 0};
    private final b xI;
    private final byte[] xG = {1, 2, 11, 0, 0, 0, 0, 0, 0, 0, 0};
    private final byte[] xH = new byte[2097152];
    private boolean xJ = false;
    private final Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.XpuImagePresenter.1
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            if (message.what == 100001 && XpuImagePresenter.this.xN) {
                XpuImagePresenter.this.jz();
            }
        }
    };
    private int xK = 0;
    private int xL = 0;
    private final c.a xM = new c.a() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.XpuImagePresenter.2
        @Override // com.xiaopeng.devtools.system.c.c.a
        public void jC() {
            com.xiaopeng.lib.utils.c.g("XpuImagePresenter", "onConnectSuccess");
        }

        @Override // com.xiaopeng.devtools.system.c.c.a
        public void jD() {
            com.xiaopeng.lib.utils.c.i("XpuImagePresenter", "onConnectFail");
            u.bd(MyApplication.getContext().getString(R.string.xpu_connection_fail));
        }

        @Override // com.xiaopeng.devtools.system.c.c.a
        public void jE() {
            com.xiaopeng.lib.utils.c.i("XpuImagePresenter", "onReadException");
            u.bd(MyApplication.getContext().getString(R.string.xpu_read_fail));
            XpuImagePresenter.this.jz();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0205  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x021d  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0163 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:51:? A[RETURN, SYNTHETIC] */
        @Override // com.xiaopeng.devtools.system.c.c.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void d(byte[] r7, int r8) {
            /*
                Method dump skipped, instructions count: 582
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.XpuImagePresenter.AnonymousClass2.d(byte[], int):void");
        }
    };
    private boolean xN = true;
    private int xO = 0;
    private final LinkedBlockingQueue<byte[]> xE = new LinkedBlockingQueue<>();
    private final c xF = new c();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum INDEX {
        SRC,
        DEST,
        LENGTH_LOW,
        LENGTH_HIGH,
        FRAMECNT_4,
        FRAMECNT_3,
        FRAMECNT_2,
        FRAMECNT_1,
        BLKCNT_LOW,
        BLKCNT_HIGH,
        CMD,
        PAYLOAD_START
    }

    public XpuImagePresenter(b bVar) {
        this.xI = bVar;
        this.xF.a(this.xM);
        this.xF.connect("172.20.1.22", 30555);
    }

    public void jy() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.-$$Lambda$XpuImagePresenter$qDLQ0tNF7DmqCNtK_xyN3n7SfeU
            @Override // java.lang.Runnable
            public final void run() {
                XpuImagePresenter.this.jB();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void jB() {
        byte[] poll;
        while (this.xN) {
            if (!this.xE.isEmpty() && (poll = this.xE.poll()) != null) {
                int i = ((poll[INDEX.LENGTH_HIGH.ordinal()] & 255) * 256) + (poll[INDEX.LENGTH_LOW.ordinal()] & 255);
                byte b = poll[INDEX.CMD.ordinal()];
                if (b == 2) {
                    this.xO = 0;
                    System.arraycopy(poll, INDEX.PAYLOAD_START.ordinal(), this.xH, this.xO, i - INDEX.PAYLOAD_START.ordinal());
                    this.xO += i - INDEX.PAYLOAD_START.ordinal();
                } else if (b == 4) {
                    System.arraycopy(poll, INDEX.PAYLOAD_START.ordinal(), this.xH, this.xO, i - INDEX.PAYLOAD_START.ordinal());
                    this.xO += i - INDEX.PAYLOAD_START.ordinal();
                } else if (b == 6) {
                    System.arraycopy(poll, INDEX.PAYLOAD_START.ordinal(), this.xH, this.xO, i - INDEX.PAYLOAD_START.ordinal());
                    this.xO += i - INDEX.PAYLOAD_START.ordinal();
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(this.xH, 0, this.xO);
                    if (this.xI != null) {
                        this.xI.e(decodeByteArray);
                    }
                }
                this.xF.y(poll);
            }
        }
    }

    public void jz() {
        this.xK = 0;
        this.xL = 0;
        this.xO = 0;
        this.xJ = false;
        this.xF.z(xD);
        if (this.mHandler.hasMessages(100001)) {
            this.mHandler.removeMessages(100001);
        }
        this.mHandler.sendEmptyMessageDelayed(100001, 500L);
    }

    public void jA() {
        this.xN = false;
        this.xF.a((c.a) null);
        if (this.mHandler.hasMessages(100001)) {
            this.mHandler.removeMessages(100001);
        }
        this.xF.T(true);
        this.xE.clear();
    }
}
