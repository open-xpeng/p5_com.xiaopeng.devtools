package com.xiaopeng.devtools.devdebug.drivemode;

/* compiled from: DriveModePresent.java */
/* loaded from: classes12.dex */
public class b {
    private c qB;
    private a qC = new a();

    public b(c cVar) {
        this.qB = cVar;
    }

    public int getMode() {
        return this.qC.getMode();
    }

    public void bQ(int i) {
        com.xiaopeng.lib.utils.c.f("DriveModePresenter", "controlMode mode = " + i);
        this.qC.bQ(i);
        if (this.qB != null) {
            this.qB.bO(i);
        }
    }

    public void fh() {
        if (this.qB != null) {
            this.qB.bO(getMode());
        }
    }

    public void onDestroy() {
        this.qC.onDestroy();
        this.qC = null;
        this.qB = null;
    }
}
