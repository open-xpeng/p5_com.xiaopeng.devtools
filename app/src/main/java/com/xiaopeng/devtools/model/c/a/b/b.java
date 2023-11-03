package com.xiaopeng.devtools.model.c.a.b;

import com.xiaopeng.devtools.bean.factorytest.ScanDevice;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ScanDeviceLab.java */
/* loaded from: classes12.dex */
public class b {
    private static b sQ;
    private List<ScanDevice> sR = new ArrayList();

    private b() {
    }

    public static b gn() {
        if (sQ == null) {
            sQ = new b();
        }
        return sQ;
    }

    public void a(ScanDevice scanDevice) {
        this.sR.add(scanDevice);
    }

    public List<ScanDevice> getItems() {
        return this.sR;
    }

    public ScanDevice bB(String str) {
        ScanDevice scanDevice = null;
        for (ScanDevice scanDevice2 : this.sR) {
            if (scanDevice2.getAddress().equals(str)) {
                scanDevice = scanDevice2;
            }
        }
        return scanDevice;
    }

    public void deleteAll() {
        this.sR.clear();
    }
}
