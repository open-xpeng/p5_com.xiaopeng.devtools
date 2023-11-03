package com.xiaopeng.devtools.model.c.a.d;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.b;
import com.amap.api.services.geocoder.d;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.model.c.a.d.c;
import com.xiaopeng.devtools.utils.t;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: GPSModel.java */
/* loaded from: classes12.dex */
public class a implements b.a, com.xiaopeng.devtools.model.c.a.d.b {
    private Context mContext;
    private LocationManager mLocationManager;
    private int sY;
    private c sZ;
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.a ta;
    private List<GpsSatellite> tb;
    private b tc;
    private com.amap.api.services.geocoder.b td;
    private StringBuilder te;
    private Location tf;
    private C0058a tg;
    private com.xiaopeng.devtools.model.c.a.d.c th;
    private c.a ti;

    public a(com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.a aVar) {
        this.sY = -1;
        this.te = new StringBuilder();
        this.ti = new c.a() { // from class: com.xiaopeng.devtools.model.c.a.d.a.1
            @Override // com.xiaopeng.devtools.model.c.a.d.c.a
            public void a(float[] fArr, long j) {
                a.this.ta.a(fArr, j);
            }

            @Override // com.xiaopeng.devtools.model.c.a.d.c.a
            public void a(float[] fArr, long j, float f) {
                a.this.ta.a(fArr, j, f);
            }

            @Override // com.xiaopeng.devtools.model.c.a.d.c.a
            public void o(int i, int i2) {
                a.this.ta.o(i, i2);
            }
        };
        this.mContext = MyApplication.getContext();
        this.ta = aVar;
        this.mLocationManager = (LocationManager) this.mContext.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        this.td = new com.amap.api.services.geocoder.b(this.mContext);
        this.td.a(this);
        this.sY = gx();
        ce(3);
        gx();
        this.tb = new ArrayList();
        this.sZ = new c(this.ta);
        this.mLocationManager.requestLocationUpdates("gps", 1000L, 1.0f, this.sZ);
        this.tc = new b(this.ta);
        this.mLocationManager.addGpsStatusListener(this.tc);
        this.th = com.xiaopeng.devtools.model.c.a.d.c.gE();
    }

    public a() {
        this.sY = -1;
        this.te = new StringBuilder();
        this.ti = new c.a() { // from class: com.xiaopeng.devtools.model.c.a.d.a.1
            @Override // com.xiaopeng.devtools.model.c.a.d.c.a
            public void a(float[] fArr, long j) {
                a.this.ta.a(fArr, j);
            }

            @Override // com.xiaopeng.devtools.model.c.a.d.c.a
            public void a(float[] fArr, long j, float f) {
                a.this.ta.a(fArr, j, f);
            }

            @Override // com.xiaopeng.devtools.model.c.a.d.c.a
            public void o(int i, int i2) {
                a.this.ta.o(i, i2);
            }
        };
        this.mContext = MyApplication.getContext();
        this.mLocationManager = (LocationManager) this.mContext.getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        this.sY = gx();
        this.tb = new ArrayList();
        if (this.tg == null) {
            this.tg = new C0058a();
            this.tg.start();
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public void gw() {
        com.xiaopeng.lib.utils.c.f("GPSModel", "setGPSEnable ");
        if (!this.mLocationManager.isProviderEnabled("gps") && gA() && this.sY != 3) {
            Settings.Secure.putInt(this.mContext.getContentResolver(), "location_mode", 3);
        }
    }

    public boolean ce(int i) {
        com.xiaopeng.lib.utils.c.f("GPSModel", "setGPSmode mode = " + i);
        if (i != -1 && i != gx()) {
            Settings.Secure.putInt(this.mContext.getContentResolver(), "location_mode", i);
            return true;
        }
        return false;
    }

    public int gx() {
        int i;
        try {
            i = Settings.Secure.getInt(this.mContext.getContentResolver(), "location_mode");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            i = 0;
        }
        com.xiaopeng.lib.utils.c.f("GPSModel", "getCurrentGPSmode mode = " + i);
        return i;
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public Location getLastKnownLocation() {
        return this.mLocationManager.getLastKnownLocation("gps");
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public void a(Location location) {
        if (location != null) {
            this.tf = location;
            b(location);
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public void gy() {
        com.xiaopeng.lib.utils.c.f("GPSModel", "destory , currentMode = " + this.sY);
        if (this.sY != -1) {
            Settings.Secure.putInt(this.mContext.getContentResolver(), "location_mode", this.sY);
        }
        if (this.sZ != null) {
            this.mLocationManager.removeUpdates(this.sZ);
        }
        if (this.tg != null) {
            this.tg.interrupt();
            this.tg = null;
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public boolean gz() {
        boolean z;
        if (gA()) {
            z = this.mLocationManager.isProviderEnabled("gps");
        } else {
            z = false;
        }
        com.xiaopeng.lib.utils.c.f("GPSModel", "isGPSEnabled = " + z);
        return z;
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public boolean gA() {
        boolean hasSystemFeature = this.mContext.getPackageManager().hasSystemFeature("android.hardware.location.gps");
        com.xiaopeng.lib.utils.c.f("GPSModel", "hasGPSFeature withFeature = " + hasSystemFeature);
        return hasSystemFeature;
    }

    private void b(Location location) {
        this.td.b(new com.amap.api.services.geocoder.c(new LatLonPoint(location.getLatitude(), location.getLongitude()), 200.0f, "gps"));
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public void gB() {
        this.th.open();
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public void gC() {
        this.th.close();
    }

    @Override // com.xiaopeng.devtools.model.c.a.d.b
    public void gD() {
        this.th.a(this.ti);
    }

    @Override // com.amap.api.services.geocoder.b.a
    public void a(d dVar, int i) {
        this.te.setLength(0);
        if (this.tf != null) {
            this.tf.getTime();
            String cL = t.cL("yyyy-MM-dd HH:mm:ss");
            StringBuilder sb = this.te;
            sb.append("经度: ");
            sb.append(this.tf.getLongitude());
            sb.append("\n");
            sb.append("纬度: ");
            sb.append(this.tf.getLatitude());
            sb.append("\n");
            sb.append("海拔高度: ");
            sb.append(this.tf.getAltitude());
            sb.append("\n");
            sb.append("时间: ");
            sb.append(cL);
            sb.append("\n");
        }
        if (i == 1000 && dVar != null && dVar.bM() != null && dVar.bM().bJ() != null) {
            String bJ = dVar.bM().bJ();
            if (!TextUtils.isEmpty(bJ)) {
                StringBuilder sb2 = this.te;
                sb2.append("详细地址: ");
                sb2.append(bJ);
            }
        }
        this.ta.bX(this.te.toString());
    }

    @Override // com.amap.api.services.geocoder.b.a
    public void a(com.amap.api.services.geocoder.a aVar, int i) {
    }

    /* compiled from: GPSModel.java */
    /* renamed from: com.xiaopeng.devtools.model.c.a.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private final class C0058a extends Thread {
        private C0058a() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            a.this.tc = new b();
            a.this.mLocationManager.addGpsStatusListener(a.this.tc);
            Looper.loop();
        }
    }

    /* compiled from: GPSModel.java */
    /* loaded from: classes12.dex */
    private final class b implements GpsStatus.Listener {
        private WeakReference<com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.a> sv;

        public b(com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.a aVar) {
            this.sv = new WeakReference<>(aVar);
        }

        public b() {
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            a(i, a.this.mLocationManager.getGpsStatus(null));
        }

        private void a(int i, GpsStatus gpsStatus) {
            if (i == 4) {
                int maxSatellites = gpsStatus.getMaxSatellites();
                Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
                a.this.tb.clear();
                int i2 = 0;
                while (it.hasNext() && i2 <= maxSatellites) {
                    a.this.tb.add(it.next());
                    i2++;
                }
                if (this.sv != null && this.sv.get() != null) {
                    this.sv.get().a(i2, a.this.tb);
                }
            }
        }
    }

    /* compiled from: GPSModel.java */
    /* loaded from: classes12.dex */
    private final class c implements LocationListener {
        private WeakReference<com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.a> sv;

        public c(com.xiaopeng.devtools.presenter.factorytest.hardwaretest.d.a aVar) {
            this.sv = new WeakReference<>(aVar);
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            a.this.a(location);
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            if (this.sv.get() != null) {
                this.sv.get().iX();
                a.this.a(a.this.mLocationManager.getLastKnownLocation(str));
            }
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }
}
