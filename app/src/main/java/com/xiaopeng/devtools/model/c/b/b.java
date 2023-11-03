package com.xiaopeng.devtools.model.c.b;

import android.os.SystemProperties;

/* compiled from: NavTestModel.java */
/* loaded from: classes12.dex */
public class b implements a {
    @Override // com.xiaopeng.devtools.model.c.b.a
    public boolean gV() {
        return SystemProperties.getBoolean("sys.gps.dump", false);
    }

    @Override // com.xiaopeng.devtools.model.c.b.a
    public void gW() {
        SystemProperties.set("sys.gps.dump", "true");
    }

    @Override // com.xiaopeng.devtools.model.c.b.a
    public void gX() {
        SystemProperties.set("sys.gps.dump", "false");
    }

    @Override // com.xiaopeng.devtools.model.c.b.a
    public String gY() {
        return SystemProperties.get("sys.gps.version", "Unknown");
    }
}
