package com.xiaopeng.devtools.system.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.xiaopeng.devtools.utils.q;

/* loaded from: classes12.dex */
public class CollectionService extends Service {
    private a AW = new a();
    private boolean AX = false;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.AX = q.lg().getBoolean("k_s_d");
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return this.AW;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    /* loaded from: classes12.dex */
    public class a extends Binder {
        public a() {
        }

        public CollectionService kR() {
            return CollectionService.this;
        }
    }

    public void kQ() {
    }

    public void R(boolean z) {
        this.AX = z;
    }
}
