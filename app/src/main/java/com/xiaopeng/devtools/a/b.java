package com.xiaopeng.devtools.a;

import android.app.AlarmManager;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.system.service.PcCommandService;
import com.xiaopeng.lib.utils.c;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: ResponseWriter.java */
/* loaded from: classes12.dex */
public class b {
    private static boolean uW = true;
    private Handler mHandler;
    private BufferedWriter uV;
    private ConcurrentLinkedQueue<String> uX;
    private Socket uY;
    private String uZ;
    private AlarmManager va;
    private boolean vb = false;
    private AlarmManager.OnAlarmListener vc = $$Lambda$b$yStibVxW5G9o6nSAUGwk0pCehQ.INSTANCE;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void hU() {
        c.g("ResponseWriter", "onAlarm STOP PC COMMAND SERVICE");
        MyApplication.getContext().stopService(new Intent(MyApplication.getContext(), PcCommandService.class));
    }

    public void hQ() {
        this.va.cancel(this.vc);
    }

    public void hR() {
        hQ();
        if (!this.vb) {
            this.va.setExact(3, SystemClock.elapsedRealtime() + 1200000, "ResponseWriter", this.vc, this.mHandler);
        }
    }

    public void I(boolean z) {
        this.vb = z;
    }

    public synchronized boolean hS() {
        return uW;
    }

    public b(Socket socket) {
        c.f("ResponseWriter", "Create ResponseWriter");
        this.uX = new ConcurrentLinkedQueue<>();
        this.va = (AlarmManager) MyApplication.getContext().getSystemService("alarm");
        HandlerThread handlerThread = new HandlerThread("ResponseWriter");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
        a(socket);
    }

    public b() {
        c.f("ResponseWriter", "Create ResponseWriter without socket");
        this.uX = new ConcurrentLinkedQueue<>();
        this.va = (AlarmManager) MyApplication.getContext().getSystemService("alarm");
        HandlerThread handlerThread = new HandlerThread("ResponseWriter");
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper());
    }

    public void a(Socket socket) {
        this.uY = socket;
        if (socket != null && socket.isConnected()) {
            try {
                if (this.uV != null) {
                    this.uV.close();
                }
                this.uV = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.uZ = "";
        } else {
            c.f("ResponseWriter", "ResponseWriter Socket is closed");
        }
        hR();
    }

    public String hT() {
        return this.uZ;
    }

    public void bJ(String str) {
        this.uZ = str;
    }

    public synchronized boolean write(String str) {
        boolean z = false;
        if (this.uX == null) {
            c.f("ResponseWriter", "ResponseWriter write - Queue is null");
            return false;
        }
        if (str != null) {
            c.f("ResponseWriter", "write response :" + str);
            if (hS()) {
                while (!this.uX.isEmpty()) {
                    c.f("ResponseWriter", "ResponseWriter write - poll queue");
                    bK(this.uX.poll());
                }
                z = bK(str);
            } else {
                c.f("ResponseWriter", "ResponseWriter write - need to queueing");
                z = this.uX.add(str);
            }
        }
        hR();
        return z;
    }

    public boolean bK(String str) {
        if (this.uV != null && str != null) {
            try {
                this.uV.write(str);
                this.uV.flush();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            c.d("ResponseWriter", "write", "out is null");
        }
        return false;
    }

    public void close() {
        c.f("ResponseWriter", "close");
        if (this.uV != null) {
            try {
                this.uV.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.uV = null;
        }
    }
}
