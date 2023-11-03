package com.xiaopeng.devtools.model.g.b;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.devtools.bean.smartdrive.SmartDriveData;
import com.xiaopeng.lib.framework.moduleinterface.carcontroller.IScuController;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: SmartDriveModel.java */
/* loaded from: classes12.dex */
public class b implements com.xiaopeng.devtools.model.g.b.a {
    private a uK;

    /* compiled from: SmartDriveModel.java */
    /* loaded from: classes12.dex */
    public interface a {
        void a(int[] iArr);

        void b(int[] iArr);

        void c(int[] iArr);

        void s(List<SmartDriveData> list);
    }

    public b() {
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND)
    public void onReceiveFactoryScuTest322EventMsg(IScuController.FactoryScuTest322EventMsg factoryScuTest322EventMsg) {
        this.uK.a(factoryScuTest322EventMsg.getData());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND)
    public void onReceiveFactoryScuTest3FDEventMsg(IScuController.FactoryScuTest3FDEventMsg factoryScuTest3FDEventMsg) {
        this.uK.b(factoryScuTest3FDEventMsg.getData());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.BACKGROUND)
    public void onReceiveFactoryScuTest3FEEventMsg(IScuController.FactoryScuTest3FEEventMsg factoryScuTest3FEEventMsg) {
        this.uK.c(factoryScuTest3FEEventMsg.getData());
    }

    @Override // com.xiaopeng.devtools.model.g.b.a
    public void onCreate() {
        new AsyncTaskC0063b().execute(new Void[0]);
    }

    @Override // com.xiaopeng.devtools.model.g.b.a, com.xiaopeng.devtools.model.a
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override // com.xiaopeng.devtools.model.g.b.a
    public void a(SmartDriveData smartDriveData) {
    }

    @Override // com.xiaopeng.devtools.model.g.b.a
    public void a(a aVar) {
        this.uK = aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<SmartDriveData> bF(@NonNull String str) {
        return (List) new Gson().fromJson(str, new TypeToken<List<SmartDriveData>>() { // from class: com.xiaopeng.devtools.model.g.b.b.1
        }.getType());
    }

    /* compiled from: SmartDriveModel.java */
    /* renamed from: com.xiaopeng.devtools.model.g.b.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private class AsyncTaskC0063b extends AsyncTask<Void, Void, List<SmartDriveData>> {
        private AsyncTaskC0063b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.List<com.xiaopeng.devtools.bean.smartdrive.SmartDriveData> doInBackground(java.lang.Void... r6) {
            /*
                r5 = this;
                r6 = 0
                android.content.Context r0 = com.xiaopeng.devtools.MyApplication.getContext()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                android.content.res.Resources r0 = r0.getResources()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                r1 = 2131689473(0x7f0f0001, float:1.9007962E38)
                java.io.InputStream r0 = r0.openRawResource(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                int r1 = r0.available()     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                r0.read(r1)     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                java.lang.String r3 = "UTF-8"
                r2.<init>(r1, r3)     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                com.xiaopeng.devtools.model.g.b.b r1 = com.xiaopeng.devtools.model.g.b.b.this     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                java.util.List r1 = com.xiaopeng.devtools.model.g.b.b.a(r1, r2)     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                if (r0 == 0) goto L33
                r0.close()     // Catch: java.io.IOException -> L2e
            L2d:
                goto L33
            L2e:
                r6 = move-exception
                r6.printStackTrace()
                goto L2d
            L33:
                r6 = r1
                goto L4c
            L35:
                r1 = move-exception
                goto L3e
            L37:
                r0 = move-exception
                r4 = r0
                r0 = r6
                r6 = r4
                goto L4e
            L3c:
                r1 = move-exception
                r0 = r6
            L3e:
                r1.printStackTrace()     // Catch: java.lang.Throwable -> L4d
                if (r0 == 0) goto L4c
                r0.close()     // Catch: java.io.IOException -> L47
            L46:
                goto L4c
            L47:
                r0 = move-exception
                r0.printStackTrace()
                goto L46
            L4c:
                return r6
            L4d:
                r6 = move-exception
            L4e:
                if (r0 == 0) goto L58
                r0.close()     // Catch: java.io.IOException -> L54
                goto L58
            L54:
                r0 = move-exception
                r0.printStackTrace()
            L58:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.model.g.b.b.AsyncTaskC0063b.doInBackground(java.lang.Void[]):java.util.List");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: p */
        public void onPostExecute(List<SmartDriveData> list) {
            super.onPostExecute(list);
            if (list != null && b.this.uK != null) {
                b.this.uK.s(list);
            }
        }
    }
}
