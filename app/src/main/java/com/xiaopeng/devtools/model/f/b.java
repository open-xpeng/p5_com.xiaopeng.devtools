package com.xiaopeng.devtools.model.f;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.bean.event.signal.GpsLocationEvent;
import com.xiaopeng.devtools.bean.event.signal.GpsNumSnrEvent;
import com.xiaopeng.devtools.bean.event.signal.GpsSpeedEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.EventBus;

/* compiled from: GpsPerformanceModel.java */
/* loaded from: classes12.dex */
public class b {
    private static volatile b uc;
    private Location tf;
    private String ua;
    private int ub;
    private a ue;
    private StringBuilder te = new StringBuilder();
    private StringBuilder tY = new StringBuilder();
    private StringBuilder tZ = new StringBuilder();
    private SimpleDateFormat tX = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ExecutorService uf = Executors.newSingleThreadExecutor();
    private LocationManager mLocationManager = (LocationManager) MyApplication.getContext().getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
    private C0061b ud = new C0061b();

    private b() {
        this.mLocationManager.requestLocationUpdates("gps", 1000L, 1.0f, this.ud);
        this.ue = new a();
        this.mLocationManager.addGpsStatusListener(this.ue);
        a(getLastKnownLocation());
    }

    public static b hj() {
        if (uc == null) {
            synchronized (b.class) {
                if (uc == null) {
                    uc = new b();
                }
            }
        }
        return uc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Location location) {
        this.te.setLength(0);
        this.tZ.setLength(0);
        if (location != null) {
            this.ua = this.tX.format(new Date(location.getTime()));
            StringBuilder sb = this.te;
            sb.append("经度: ");
            sb.append(location.getLongitude());
            sb.append("  ");
            sb.append("纬度: ");
            sb.append(location.getLatitude());
            sb.append("  ");
            sb.append("海拔高度: ");
            sb.append(location.getAltitude());
            this.tZ.append(location.getSpeed());
        } else {
            this.ua = "N/A";
            StringBuilder sb2 = this.te;
            sb2.append("经度: ");
            sb2.append("N/A");
            sb2.append("  ");
            sb2.append("纬度: ");
            sb2.append("N/A");
            sb2.append("  ");
            sb2.append("海拔高度: ");
            sb2.append("N/A");
            this.tZ.append("N/A");
        }
        EventBus.getDefault().post(new GpsLocationEvent(this.ua, this.te.toString()));
        EventBus.getDefault().post(new GpsSpeedEvent(this.tZ.toString()));
    }

    public String getLocationTime() {
        return this.ua;
    }

    public String hk() {
        return this.tY.toString();
    }

    public String hl() {
        return this.te.toString();
    }

    public String getGpsSpeed() {
        return this.tZ.toString();
    }

    public int hm() {
        return this.ub;
    }

    private Location getLastKnownLocation() {
        Location lastKnownLocation = this.mLocationManager.getLastKnownLocation("gps");
        this.tf = lastKnownLocation;
        return lastKnownLocation;
    }

    /* compiled from: GpsPerformanceModel.java */
    /* loaded from: classes12.dex */
    private final class a implements GpsStatus.Listener {
        private a() {
        }

        @Override // android.location.GpsStatus.Listener
        public void onGpsStatusChanged(int i) {
            a(i, b.this.mLocationManager.getGpsStatus(null));
        }

        private void a(int i, GpsStatus gpsStatus) {
            if (i == 4) {
                int maxSatellites = gpsStatus.getMaxSatellites();
                Iterator<GpsSatellite> it = gpsStatus.getSatellites().iterator();
                int i2 = 0;
                b.this.tY.setLength(0);
                b.this.tY.append("{");
                while (it.hasNext() && i2 <= maxSatellites) {
                    b.this.tY.append(it.next().getSnr());
                    if (it.hasNext() && i2 <= maxSatellites) {
                        b.this.tY.append(", ");
                    }
                    i2++;
                }
                b.this.tY.append("}");
                b.this.ub = i2;
                EventBus.getDefault().post(new GpsNumSnrEvent(i2, b.this.tY.toString()));
            }
        }
    }

    /* compiled from: GpsPerformanceModel.java */
    /* renamed from: com.xiaopeng.devtools.model.f.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private final class C0061b implements LocationListener {
        private C0061b() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(final Location location) {
            b.this.uf.submit(new Runnable() { // from class: com.xiaopeng.devtools.model.f.b.b.1
                @Override // java.lang.Runnable
                public void run() {
                    b.this.a(location);
                }
            });
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(final String str) {
            b.this.uf.submit(new Runnable() { // from class: com.xiaopeng.devtools.model.f.b.b.2
                @Override // java.lang.Runnable
                public void run() {
                    b.this.a(b.this.mLocationManager.getLastKnownLocation(str));
                }
            });
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }
}
