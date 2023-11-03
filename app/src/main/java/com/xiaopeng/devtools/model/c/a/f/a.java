package com.xiaopeng.devtools.model.c.a.f;

import android.content.Context;
import com.xpeng.test.AudioTest;
import com.xpeng.test.RadioTest;
import com.xpeng.test.callback.CarCallback;

/* compiled from: RadioModel.java */
/* loaded from: classes12.dex */
public class a {
    private Context mContext;
    private RadioTest ty;
    private AudioTest tz;

    public a(Context context) {
        this.mContext = null;
        this.mContext = context;
        this.ty = new RadioTest(this.mContext);
        this.tz = new AudioTest(this.mContext);
    }

    public void a(CarCallback carCallback) {
        this.ty.registerCallback(carCallback);
    }

    public void gK() {
        this.ty.openFm();
    }

    public void gL() {
        this.ty.closeFm();
    }

    public void q(int i, int i2) {
        this.ty.setRadioFrequency(i, i2);
    }

    public boolean searchStationUp() {
        return this.ty.searchStationUp();
    }

    public boolean searchStationDown() {
        return this.ty.searchStationDown();
    }

    public void setRadioBand(int i) {
        this.ty.setRadioBand(i);
    }

    public void gM() {
        this.ty.setStartFullBandScan();
    }

    public void stopScan() {
        this.ty.setStopFullBandScan();
    }

    public void setVolume(int i) {
        this.tz.setMediaVolume(i);
    }

    public void cf(int i) {
        this.ty.setRadioVolumeAutoFocus(i);
    }
}
