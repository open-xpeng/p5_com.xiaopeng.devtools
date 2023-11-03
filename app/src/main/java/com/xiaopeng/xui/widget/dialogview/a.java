package com.xiaopeng.xui.widget.dialogview;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: XCountDown.java */
/* loaded from: classes13.dex */
public class a extends Handler {
    private InterfaceC0089a ait;
    private int count;
    private int index;
    private String mTag;

    /* compiled from: XCountDown.java */
    /* renamed from: com.xiaopeng.xui.widget.dialogview.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public interface InterfaceC0089a {
        void fm(String str);

        void fn(String str);

        void h(String str, int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(InterfaceC0089a interfaceC0089a) {
        this.ait = interfaceC0089a;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (this.ait != null) {
            this.ait.h(this.mTag, this.count, this.index);
        }
        if (this.index == 0 && this.ait != null) {
            this.ait.fm(this.mTag);
        }
        if (this.index > 0) {
            this.index--;
            sendEmptyMessageDelayed(0, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void F(String str, int i) {
        this.mTag = str;
        stop();
        this.count = i;
        this.index = i;
        sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() {
        removeMessages(0);
        if (this.ait != null) {
            this.ait.fn(this.mTag);
        }
    }
}
