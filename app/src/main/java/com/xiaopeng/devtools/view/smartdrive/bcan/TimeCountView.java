package com.xiaopeng.devtools.view.smartdrive.bcan;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes12.dex */
public class TimeCountView extends TextView implements Runnable {
    private boolean Tq;
    private long Tr;
    private long Ts;
    private boolean Tt;
    private ExecutorService mExecutorService;
    Handler mHandler;

    public TimeCountView(Context context) {
        super(context);
        this.Tq = false;
        this.Ts = 0L;
        this.Tt = false;
        this.mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.smartdrive.bcan.TimeCountView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 0) {
                    TimeCountView.this.setText((String) message.obj);
                }
            }
        };
    }

    public TimeCountView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimeCountView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.Tq = false;
        this.Ts = 0L;
        this.Tt = false;
        this.mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.smartdrive.bcan.TimeCountView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 0) {
                    TimeCountView.this.setText((String) message.obj);
                }
            }
        };
    }

    public void nQ() {
        this.Tr = System.currentTimeMillis();
        if (this.mExecutorService == null) {
            this.mExecutorService = Executors.newSingleThreadExecutor();
            this.mExecutorService.execute(this);
            this.Ts = 0L;
        }
        setVisibility(0);
        this.Tt = false;
    }

    public void nR() {
        this.Tq = false;
        if (this.mHandler.hasMessages(0)) {
            this.mHandler.removeMessages(0);
        }
        setVisibility(8);
        setText("");
        if (this.mExecutorService != null) {
            this.mExecutorService.shutdownNow();
        }
        this.mExecutorService = null;
        this.Tt = false;
        this.Ts = 0L;
        this.Tr = 0L;
    }

    public void nS() {
        this.Tt = true;
        this.Ts += System.currentTimeMillis() - this.Tr;
        this.Tr = 0L;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.Tq = true;
        while (this.Tq) {
            long currentTimeMillis = System.currentTimeMillis();
            nT();
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            while (true) {
                if (currentTimeMillis2 <= 500 || this.Tt) {
                    currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
                    Thread.yield();
                }
            }
        }
    }

    private void nT() {
        StringBuilder sb;
        String str;
        StringBuilder sb2;
        String str2;
        StringBuilder sb3;
        String str3;
        if (this.Tt) {
            return;
        }
        long currentTimeMillis = ((System.currentTimeMillis() - this.Tr) + this.Ts) / 1000;
        int i = (int) (currentTimeMillis % 60);
        int i2 = (int) ((currentTimeMillis / 60) % 60);
        int i3 = (int) (currentTimeMillis / 3600);
        if (i3 >= 10) {
            sb = new StringBuilder();
            str = "";
        } else {
            sb = new StringBuilder();
            str = "0";
        }
        sb.append(str);
        sb.append(i3);
        String sb4 = sb.toString();
        if (i2 >= 10) {
            sb2 = new StringBuilder();
            str2 = "";
        } else {
            sb2 = new StringBuilder();
            str2 = "0";
        }
        sb2.append(str2);
        sb2.append(i2);
        String sb5 = sb2.toString();
        if (i >= 10) {
            sb3 = new StringBuilder();
            str3 = "";
        } else {
            sb3 = new StringBuilder();
            str3 = "0";
        }
        sb3.append(str3);
        sb3.append(i);
        String str4 = sb4 + ":" + sb5 + ":" + sb3.toString();
        Message obtainMessage = this.mHandler.obtainMessage(0);
        obtainMessage.obj = str4;
        this.mHandler.sendMessage(obtainMessage);
    }
}
