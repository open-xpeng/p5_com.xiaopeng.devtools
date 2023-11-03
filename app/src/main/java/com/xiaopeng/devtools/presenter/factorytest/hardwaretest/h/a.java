package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.h;

import android.car.hardware.CarPropertyValue;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import com.google.gson.Gson;
import com.xiaopeng.devtools.bean.factorytest.RadioBean;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xpeng.test.callback.CarCallback;

/* compiled from: RadioPresenter.java */
/* loaded from: classes12.dex */
public class a {
    AudioManager mAudioManager;
    private Context mContext;
    private Handler mHandler;
    private com.xiaopeng.devtools.model.c.a.f.a xb;
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.a xc;
    private RadioBean xf;
    private int xd = 0;
    private int xe = -1;
    private boolean xg = false;
    private int xh = 256;
    private CarCallback xi = new CarCallback() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.h.a.1
        public void handleChangeEvent(CarPropertyValue carPropertyValue) {
            c.f("RadioPresenter", "mCarCallback handleChangeEvent = " + carPropertyValue.getValue());
            a.this.xf = (RadioBean) new Gson().fromJson((String) carPropertyValue.getValue(), (Class<Object>) RadioBean.class);
            if (a.this.xc != null) {
                a.this.updateView();
                a.this.jm();
            }
        }

        public void handleErrorEvent(int i, int i2) {
        }
    };

    public a(Context context, com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.a aVar) {
        this.mContext = null;
        this.xc = null;
        this.mAudioManager = null;
        this.mHandler = null;
        this.mContext = context;
        this.xb = new com.xiaopeng.devtools.model.c.a.f.a(this.mContext);
        this.xc = aVar;
        this.mHandler = new Handler();
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
    }

    public void jl() {
        this.xb.a(this.xi);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateView() {
        c.f("RadioPresenter", "updateView");
        this.mHandler.post(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.h.-$$Lambda$a$5VFoCHv-XvRjzmBQ7GX3VKLk0YU
            @Override // java.lang.Runnable
            public final void run() {
                a.this.jt();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void jt() {
        if (this.xf != null) {
            if (this.xf.getRadioCurrentBand().equals("1")) {
                this.xc.df(Integer.parseInt(this.xf.getRadioCurrentFreq()));
            } else {
                this.xc.dg(Integer.parseInt(this.xf.getRadioCurrentFreq()));
            }
            this.xc.di(this.xf.toString());
        }
        if (this.xg) {
            this.xg = false;
            setVolume(getCurrentVolume());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jm() {
        c.f("RadioPresenter", "updateVolumeView");
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.h.-$$Lambda$a$7dwlrPP44B2m7-em8G3T3Gqzf4k
            @Override // java.lang.Runnable
            public final void run() {
                a.this.js();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void js() {
        this.xc.dj(String.valueOf(getCurrentVolume()));
    }

    public void jn() {
        c.f("RadioPresenter", "openRadio");
        this.xg = true;
        this.xb.gK();
        this.xb.cf(100);
    }

    public void q(int i, int i2) {
        c.f("RadioPresenter", "search radioType = " + i + " ,channel = " + i2);
        this.xb.q(i, i2);
    }

    public boolean searchStationUp() {
        return this.xb.searchStationUp();
    }

    public boolean searchStationDown() {
        return this.xb.searchStationDown();
    }

    public void gM() {
        c.f("RadioPresenter", "scan");
        this.xb.gM();
    }

    public void stopScan() {
        c.f("RadioPresenter", "stopScan");
        this.xb.stopScan();
    }

    public void jo() {
        c.f("RadioPresenter", "releaseRadio");
        this.mAudioManager.setParameters("channels=0");
        this.xb.gL();
    }

    public void jp() {
        c.f("RadioPresenter", "changeBand");
        if (this.xd == 1) {
            this.xb.setRadioBand(0);
            this.xd = 0;
            this.xc.dh(0);
            return;
        }
        this.xb.setRadioBand(1);
        this.xd = 1;
        this.xc.dh(1);
    }

    public void jq() {
        setVolume(getCurrentVolume() + 1);
        jm();
    }

    public void jr() {
        setVolume(getCurrentVolume() - 1);
        jm();
    }

    public void setVolume(int i) {
        c.f("RadioPresenter", "setVolume volume = " + i);
        this.xb.setVolume(i);
    }

    public int getCurrentVolume() {
        return this.mAudioManager.getStreamVolume(3);
    }
}
