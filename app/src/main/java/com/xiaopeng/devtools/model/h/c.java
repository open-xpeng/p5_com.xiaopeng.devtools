package com.xiaopeng.devtools.model.h;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.utils.r;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SystemInfoModel.java */
/* loaded from: classes12.dex */
public class c implements b {
    private Context mContext = MyApplication.getContext();
    private com.xiaopeng.devtools.presenter.g.c uP;

    public c(com.xiaopeng.devtools.presenter.g.c cVar) {
        this.uP = cVar;
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public String getMcuVersion() {
        return r.getMcuVersion();
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public String hJ() {
        return r.hJ();
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public String getSystemVersion() {
        return r.lq();
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public String fR() {
        String hardwareId = r.getHardwareId();
        return TextUtils.isEmpty(hardwareId) ? "Unknown" : hardwareId;
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public String getVehicleId() {
        String valueOf = String.valueOf(r.lp());
        return TextUtils.isEmpty(valueOf) ? "Unknown" : valueOf;
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public String getIccid() {
        return r.getIccid();
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public String eH() {
        return r.eH();
    }

    @Override // com.xiaopeng.devtools.model.h.b
    public void hK() {
        new a().execute(new Void[0]);
    }

    /* compiled from: SystemInfoModel.java */
    /* loaded from: classes12.dex */
    private class a extends AsyncTask<Void, Void, List<PackageInfo>> {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: c */
        public List<PackageInfo> doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            for (PackageInfo packageInfo : c.this.mContext.getPackageManager().getInstalledPackages(8192)) {
                String str = packageInfo.packageName;
                if (str.contains("com.xiaopeng.") || str.contains("com.aispeech.") || str.equals("com.android.settings") || str.equals("com.android.systemui")) {
                    arrayList.add(packageInfo);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: p */
        public void onPostExecute(List<PackageInfo> list) {
            super.onPostExecute(list);
            c.this.uP.u(list);
        }
    }
}
