package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.utils.d;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: ScreenSelftestPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.a wS;
    private int wT = 0;
    private ArrayList<ArrayList<String>> wU = new ArrayList<>();
    private Timer wV = new Timer();
    private TimerTask wW = new TimerTask() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e.a.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            c.g("ScreenSelftestPresenter", "run read and parse task ");
            String cs = g.cs(a.c.ec("SCREEN_DATA"));
            if (!TextUtils.isEmpty(cs)) {
                ArrayList bY = a.this.bY(cs);
                Message obtainMessage = a.this.mHandler.obtainMessage(1);
                obtainMessage.obj = bY;
                a.this.mHandler.sendMessage(obtainMessage);
                Message obtainMessage2 = a.this.mHandler.obtainMessage(2);
                obtainMessage2.obj = cs;
                a.this.mHandler.sendMessage(obtainMessage2);
                a.this.mHandler.sendMessage(a.this.mHandler.obtainMessage(3));
                return;
            }
            c.i("ScreenSelftestPresenter", "readString is empty ");
        }
    };
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e.a.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    a.this.wS.d((ArrayList) message.obj);
                    break;
                case 2:
                    a.this.wS.dh((String) message.obj);
                    break;
                case 3:
                    a.this.wS.cY(a.c(a.this));
                    break;
            }
            super.handleMessage(message);
        }
    };

    static /* synthetic */ int c(a aVar) {
        int i = aVar.wT + 1;
        aVar.wT = i;
        return i;
    }

    public a(com.xiaopeng.devtools.view.factorytest.hardwaretest.lcd.a aVar) {
        this.wS = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<ArrayList<String>> bY(String str) {
        boolean z;
        String[] split = str.split(";");
        String[] split2 = "ashell_val;live_val;mipi_val;sig1_val;sig2_val;sig3_val;sig4_val;".split(";");
        String o = com.xiaopeng.lib.utils.a.o(System.currentTimeMillis());
        c.g("ScreenSelftestPresenter", "data = " + Arrays.toString(split) + " signalNameArray = " + Arrays.toString(split2) + " currentTime =" + o);
        for (int i = 0; i < split.length; i++) {
            int parseInt = Integer.parseInt(split[i]);
            ArrayList<String> arrayList = null;
            if (this.wU.size() - 1 >= i) {
                arrayList = this.wU.get(i);
            }
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                arrayList.add(split2[i]);
                arrayList.add("0");
                arrayList.add("empty");
                this.wU.add(arrayList);
            }
            switch (i) {
                case 0:
                    if (((parseInt >> 2) & 1 & (parseInt >> 0) & 1) == 1) {
                        z = true;
                        break;
                    } else {
                        z = false;
                        break;
                    }
                case 1:
                    if (((parseInt >> 3) & 1 & (parseInt >> 1) & 1) == 1) {
                        z = true;
                        break;
                    } else {
                        z = false;
                        break;
                    }
                case 2:
                    if (((parseInt >> 4) & 1) == 1) {
                        z = true;
                        break;
                    } else {
                        z = false;
                        break;
                    }
                default:
                    if (parseInt != 0) {
                        z = true;
                        break;
                    } else {
                        z = false;
                        break;
                    }
            }
            if (z) {
                arrayList.set(1, (Integer.parseInt(arrayList.get(1)) + 1) + "");
                arrayList.set(2, o);
            }
        }
        return this.wU;
    }

    public void iY() {
        this.wV.schedule(this.wW, 0L, 60000L);
    }

    public void iZ() {
        this.wV.cancel();
        this.wV = null;
    }

    public void ja() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.e.-$$Lambda$a$8NezW04WoOwupbcUT6FIIijvEpA
            @Override // java.lang.Runnable
            public final void run() {
                a.this.jc();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void jc() {
        String cs = g.cs(a.c.ec("PATH_SCREEN_DIAGNOSIS"));
        int cq = d.cq(cs);
        if (this.wS != null) {
            if (cq == 0) {
                this.wS.a(true, null);
                return;
            }
            String str = cs + ":";
            if (((cq >> 3) & 1) != 0) {
                str = str + "Touch FAULT,";
            }
            if (((cq >> 4) & 1) != 0) {
                str = str + "BUCK B FAULT,";
            }
            if (((cq >> 5) & 1) != 0) {
                str = str + "BUCK A FAULT,";
            }
            if (((cq >> 6) & 1) != 0) {
                str = str + "Backlight FAULT,";
            }
            if (((cq >> 7) & 1) != 0) {
                str = str + "TCON FAULT,";
            }
            if (((cq >> 8) & 1) != 0) {
                str = str + "mipi signal error,";
            }
            this.wS.a(false, str);
        }
    }

    public boolean jb() {
        return g.cu(a.c.ec("PATH_CHANGE_LCD_DIS_MODE")) == 1;
    }

    public void K(boolean z) {
        g.F(a.c.ec("PATH_CHANGE_LCD_DIS_MODE"), z ? "dismode1" : "dismode0");
    }
}
