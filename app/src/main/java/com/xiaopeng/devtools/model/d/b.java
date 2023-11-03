package com.xiaopeng.devtools.model.d;

import android.os.AsyncTask;
import com.xiaopeng.devtools.presenter.oled.CANMsg387;
import java.util.List;

/* compiled from: OledMusicBookModel.java */
/* loaded from: classes12.dex */
public class b {

    /* compiled from: OledMusicBookModel.java */
    /* loaded from: classes12.dex */
    public interface a {
        void r(List<CANMsg387> list);
    }

    private b() {
    }

    public static b hc() {
        return c.tP;
    }

    public void a(a aVar) {
        new AsyncTaskC0060b(aVar).execute("oled/oled_custom_default.json");
    }

    /* compiled from: OledMusicBookModel.java */
    /* renamed from: com.xiaopeng.devtools.model.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private class AsyncTaskC0060b extends AsyncTask<String, Void, List<CANMsg387>> {
        private a tM;

        public AsyncTaskC0060b(a aVar) {
            this.tM = aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:35:0x005e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: e */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.List<com.xiaopeng.devtools.presenter.oled.CANMsg387> doInBackground(java.lang.String... r6) {
            /*
                r5 = this;
                r0 = 0
                android.content.Context r1 = com.xiaopeng.devtools.MyApplication.getContext()     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
                android.content.res.AssetManager r1 = r1.getAssets()     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
                r2 = 0
                r6 = r6[r2]     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
                java.io.InputStream r6 = r1.open(r6)     // Catch: java.lang.Throwable -> L45 java.io.IOException -> L4a
                int r1 = r6.available()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                r6.read(r1)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                java.lang.String r3 = "UTF-8"
                r2.<init>(r1, r3)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                r1.<init>()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                com.xiaopeng.devtools.model.d.b$b$1 r3 = new com.xiaopeng.devtools.model.d.b$b$1     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                r3.<init>()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                java.lang.reflect.Type r3 = r3.getType()     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                java.lang.Object r1 = r1.fromJson(r2, r3)     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                java.util.List r1 = (java.util.List) r1     // Catch: java.io.IOException -> L43 java.lang.Throwable -> L5b
                if (r6 == 0) goto L41
                r6.close()     // Catch: java.io.IOException -> L3c
            L3b:
                goto L41
            L3c:
                r6 = move-exception
                r6.printStackTrace()
                goto L3b
            L41:
                r0 = r1
                goto L5a
            L43:
                r1 = move-exception
                goto L4c
            L45:
                r6 = move-exception
                r4 = r0
                r0 = r6
                r6 = r4
                goto L5c
            L4a:
                r1 = move-exception
                r6 = r0
            L4c:
                r1.printStackTrace()     // Catch: java.lang.Throwable -> L5b
                if (r6 == 0) goto L5a
                r6.close()     // Catch: java.io.IOException -> L55
            L54:
                goto L5a
            L55:
                r6 = move-exception
                r6.printStackTrace()
                goto L54
            L5a:
                return r0
            L5b:
                r0 = move-exception
            L5c:
                if (r6 == 0) goto L66
                r6.close()     // Catch: java.io.IOException -> L62
                goto L66
            L62:
                r6 = move-exception
                r6.printStackTrace()
            L66:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.model.d.b.AsyncTaskC0060b.doInBackground(java.lang.String[]):java.util.List");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: p */
        public void onPostExecute(List<CANMsg387> list) {
            super.onPostExecute(list);
            if (this.tM != null && list != null) {
                this.tM.r(list);
            }
        }
    }

    /* compiled from: OledMusicBookModel.java */
    /* loaded from: classes12.dex */
    private static class c {
        private static final b tP = new b();
    }
}
