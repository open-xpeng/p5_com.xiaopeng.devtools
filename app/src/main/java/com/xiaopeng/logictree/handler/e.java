package com.xiaopeng.logictree.handler;

import android.annotation.SuppressLint;
import android.app.Application;
import android.car.hardware.CarPropertyValue;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.OnNmeaMessageListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.MediaPlayer2;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.xiaopeng.commonfunc.bean.GpsSensorData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* compiled from: GpsInfo.java */
/* loaded from: classes12.dex */
public class e extends h {
    private com.xiaopeng.commonfunc.b.a.d Ym;
    private int Yn;
    private List<GpsSensorData> Yo;
    private LinkedList<String> Yp;
    private final LocationListener Yq;
    @SuppressLint({"NewApi"})
    private final OnNmeaMessageListener Yr;
    @SuppressLint({"HandlerLeak"})
    private final Handler mHandler;
    private LocationManager mLocationManager;
    private final com.xiaopeng.commonfunc.b.a.a nM;

    static /* synthetic */ int h(e eVar) {
        int i = eVar.Yn;
        eVar.Yn = i + 1;
        return i;
    }

    public e(Application application) {
        super(application);
        this.mHandler = new Handler(com.xiaopeng.lib.utils.j.dR(0)) { // from class: com.xiaopeng.logictree.handler.e.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                switch (message.what) {
                    case 1001:
                        if (e.this.mLocationManager != null) {
                            e.this.mLocationManager.removeUpdates(e.this.Yq);
                        }
                        com.xiaopeng.logictree.d.a(Integer.valueOf(e.this.Yn));
                        return;
                    case 1002:
                        if (e.this.mLocationManager != null) {
                            e.this.mLocationManager.removeNmeaListener(e.this.Yr);
                        }
                        e.this.qh();
                        return;
                    case 1003:
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(560018957);
                        e.this.Ym.b(arrayList);
                        e.this.qg();
                        return;
                    case 1004:
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(560018959);
                        e.this.Ym.b(arrayList2);
                        e.this.qg();
                        return;
                    case MediaPlayer2.MEDIAPLAYER2_STATE_ERROR /* 1005 */:
                        ArrayList arrayList3 = new ArrayList();
                        arrayList3.add(560018958);
                        e.this.Ym.b(arrayList3);
                        e.this.qg();
                        return;
                    default:
                        return;
                }
            }
        };
        this.Yq = new LocationListener() { // from class: com.xiaopeng.logictree.handler.e.2
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
                com.xiaopeng.lib.utils.c.g(e.this.CLASS_NAME, location.toString());
                e.h(e.this);
            }

            @Override // android.location.LocationListener
            public void onStatusChanged(String str, int i, Bundle bundle) {
            }

            @Override // android.location.LocationListener
            public void onProviderEnabled(String str) {
            }

            @Override // android.location.LocationListener
            public void onProviderDisabled(String str) {
            }
        };
        this.Yr = new OnNmeaMessageListener() { // from class: com.xiaopeng.logictree.handler.e.3
            @Override // android.location.OnNmeaMessageListener
            public void onNmeaMessage(String str, long j) {
                com.xiaopeng.lib.utils.c.g(e.this.CLASS_NAME, str);
                e.this.Yp.add(str);
            }
        };
        this.nM = new com.xiaopeng.commonfunc.b.a.a() { // from class: com.xiaopeng.logictree.handler.-$$Lambda$e$gZSs97N3ZE15OwSBtf91GK1ydF0
            @Override // com.xiaopeng.commonfunc.b.a.a
            public final void onChangeEvent(CarPropertyValue carPropertyValue) {
                e.this.b(carPropertyValue);
            }
        };
        this.CLASS_NAME = "GpsInfo";
        this.mLocationManager = (LocationManager) this.context.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        this.Ym = new com.xiaopeng.commonfunc.b.a.d(this.CLASS_NAME);
        this.Yo = new LinkedList();
        this.Yp = new LinkedList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(CarPropertyValue carPropertyValue) {
        Float[] fArr;
        Float[] fArr2;
        Float[] fArr3;
        int propertyId = carPropertyValue.getPropertyId();
        Object value = carPropertyValue.getValue();
        switch (propertyId) {
            case 560018957:
                if ((value instanceof Float[]) && (fArr = (Float[]) value) != null && fArr.length > 36) {
                    this.Yo.add(new GpsSensorData(fArr[18].floatValue(), fArr[22].floatValue(), fArr[26].floatValue(), fArr[20].floatValue(), fArr[24].floatValue(), fArr[28].floatValue()));
                    return;
                }
                return;
            case 560018958:
                if ((value instanceof Float[]) && (fArr2 = (Float[]) value) != null && fArr2.length > 6) {
                    this.Yo.add(new GpsSensorData(fArr2[1].floatValue(), fArr2[2].floatValue(), fArr2[3].floatValue(), fArr2[4].floatValue(), fArr2[5].floatValue(), fArr2[6].floatValue()));
                    return;
                }
                return;
            case 560018959:
                if ((value instanceof Float[]) && (fArr3 = (Float[]) value) != null && fArr3.length > 9) {
                    this.Yo.add(new GpsSensorData(fArr3[1].floatValue(), fArr3[2].floatValue(), fArr3[3].floatValue(), fArr3[4].floatValue(), fArr3[5].floatValue(), fArr3[6].floatValue()));
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            this.Yn = 0;
            this.mLocationManager.requestLocationUpdates("gps", 500L, 0.0f, this.Yq, com.xiaopeng.lib.utils.j.dR(0));
            this.mHandler.sendEmptyMessageDelayed(1001, 10000L);
        } else if (a(this.YA, new String[]{"2"})) {
            if (!this.Yp.isEmpty()) {
                this.Yp.clear();
            }
            this.mLocationManager.addNmeaListener(this.Yr, this.mHandler);
            this.mHandler.sendEmptyMessageDelayed(1002, 10000L);
        } else if (a(this.YA, new String[]{"3"})) {
            this.Yo.clear();
            ArrayList arrayList = new ArrayList();
            arrayList.add(560018957);
            this.Ym.a(arrayList, this.nM);
            this.mHandler.sendEmptyMessageDelayed(1003, 10000L);
        } else if (a(this.YA, new String[]{"4"})) {
            this.Yo.clear();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(560018959);
            this.Ym.a(arrayList2, this.nM);
            this.mHandler.sendEmptyMessageDelayed(1004, 10000L);
        } else if (a(this.YA, new String[]{"5"})) {
            this.Yo.clear();
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(560018958);
            this.Ym.a(arrayList3, this.nM);
            this.mHandler.sendEmptyMessageDelayed(MediaPlayer2.MEDIAPLAYER2_STATE_ERROR, 10000L);
        }
        return null;
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
        removeMessage();
        this.mLocationManager.removeNmeaListener(this.Yr);
        this.mLocationManager.removeUpdates(this.Yq);
        this.Ym.onDestroy();
        this.Yo.clear();
        if (!this.Yp.isEmpty()) {
            this.Yp.clear();
        }
    }

    private void removeMessage() {
        if (this.mHandler.hasMessages(1001)) {
            this.mHandler.removeMessages(1001);
        }
        if (this.mHandler.hasMessages(1002)) {
            this.mHandler.removeMessages(1002);
        }
        if (this.mHandler.hasMessages(1003)) {
            this.mHandler.removeMessages(1003);
        }
        if (this.mHandler.hasMessages(1004)) {
            this.mHandler.removeMessages(1004);
        }
        if (this.mHandler.hasMessages(MediaPlayer2.MEDIAPLAYER2_STATE_ERROR)) {
            this.mHandler.removeMessages(MediaPlayer2.MEDIAPLAYER2_STATE_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void qg() {
        /*
            r9 = this;
            java.lang.String[] r0 = r9.YA
            int r0 = r0.length
            r1 = 4
            if (r0 <= r1) goto Lab
            java.util.List<com.xiaopeng.commonfunc.bean.GpsSensorData> r0 = r9.Yo
            int r0 = r0.size()
            java.lang.String[] r2 = r9.YA
            r3 = 1
            r2 = r2[r3]
            int r2 = java.lang.Integer.parseInt(r2)
            if (r0 <= r2) goto Lab
            java.util.List<com.xiaopeng.commonfunc.bean.GpsSensorData> r0 = r9.Yo
            java.util.Iterator r0 = r0.iterator()
        L1d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto La7
            java.lang.Object r2 = r0.next()
            com.xiaopeng.commonfunc.bean.GpsSensorData r2 = (com.xiaopeng.commonfunc.bean.GpsSensorData) r2
            double r3 = r2.getGyro_X()
            double r3 = java.lang.Math.abs(r3)
            java.lang.String[] r5 = r9.YA
            r6 = 2
            r5 = r5[r6]
            double r7 = java.lang.Double.parseDouble(r5)
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 > 0) goto La3
            double r3 = r2.getGyro_Y()
            double r3 = java.lang.Math.abs(r3)
            java.lang.String[] r5 = r9.YA
            r5 = r5[r6]
            double r7 = java.lang.Double.parseDouble(r5)
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 > 0) goto La3
            double r3 = r2.getGyro_Z()
            double r3 = java.lang.Math.abs(r3)
            java.lang.String[] r5 = r9.YA
            r5 = r5[r6]
            double r5 = java.lang.Double.parseDouble(r5)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto La3
            double r3 = r2.getAcc_X()
            r5 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r3 = java.lang.Math.pow(r3, r5)
            double r7 = r2.getAcc_Y()
            double r7 = java.lang.Math.pow(r7, r5)
            double r3 = r3 + r7
            double r7 = r2.getAcc_Z()
            double r5 = java.lang.Math.pow(r7, r5)
            double r3 = r3 + r5
            double r2 = java.lang.Math.sqrt(r3)
            java.lang.String[] r4 = r9.YA
            r5 = 3
            r4 = r4[r5]
            double r4 = java.lang.Double.parseDouble(r4)
            double r2 = r2 - r4
            double r2 = java.lang.Math.abs(r2)
            java.lang.String[] r4 = r9.YA
            r4 = r4[r1]
            double r4 = java.lang.Double.parseDouble(r4)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto La1
            goto La3
        La1:
            goto L1d
        La3:
            com.xiaopeng.logictree.d.pO()
            return
        La7:
            com.xiaopeng.logictree.d.pP()
            goto Lba
        Lab:
            java.util.List<com.xiaopeng.commonfunc.bean.GpsSensorData> r0 = r9.Yo
            int r0 = r0.size()
            if (r0 != 0) goto Lb7
            com.xiaopeng.logictree.d.pQ()
            goto Lba
        Lb7:
            com.xiaopeng.logictree.d.pO()
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.logictree.handler.e.qg():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void qh() {
        LinkedList<Integer> linkedList = new LinkedList();
        if (!this.Yp.isEmpty() || this.YA.length <= 1) {
            int parseInt = Integer.parseInt(this.YA[1]);
            int i = 0;
            int i2 = 1;
            int i3 = 0;
            while (!this.Yp.isEmpty()) {
                String remove = this.Yp.remove();
                if (remove.contains("$GNGSV,")) {
                    String[] split = remove.substring(remove.indexOf("$GNGSV,")).split(",");
                    try {
                        if (split.length > 1 && Integer.parseInt(split[1]) == i2) {
                            if (split.length > 6 && Integer.parseInt(split[6]) > parseInt) {
                                i3++;
                            }
                            if (split.length > 10 && Integer.parseInt(split[10]) > parseInt) {
                                i3++;
                            }
                            if (split.length > 14 && Integer.parseInt(split[14]) > parseInt) {
                                i3++;
                            }
                            if (split.length > 18 && Integer.parseInt(split[18]) > parseInt) {
                                i3++;
                            }
                            if (Integer.parseInt(split[0]) == i2) {
                                linkedList.add(Integer.valueOf(i3));
                            } else {
                                i2++;
                            }
                        }
                        i2 = 1;
                        i3 = 0;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (linkedList.size() > 0) {
                for (Integer num : linkedList) {
                    i += num.intValue();
                }
                com.xiaopeng.logictree.d.a(Integer.valueOf(i / linkedList.size()));
                return;
            }
            com.xiaopeng.logictree.d.a((Integer) 0);
            return;
        }
        com.xiaopeng.logictree.d.pQ();
    }
}
