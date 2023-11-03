package com.xiaopeng.devtools.model.h;

import android.os.AsyncTask;
import android.os.SystemProperties;
import android.text.TextUtils;

/* compiled from: EcuVersionModel.java */
/* loaded from: classes12.dex */
public class a {

    /* compiled from: EcuVersionModel.java */
    /* loaded from: classes12.dex */
    public interface c {
        void onSuccess(String str);
    }

    private a() {
    }

    public static a hH() {
        return b.uO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String bG(String str) {
        String str2;
        try {
            str2 = SystemProperties.get(str, "Unknown");
            try {
                com.xiaopeng.lib.utils.c.f("VersionModel", str + "_version = " + str2);
            } catch (Exception e) {
                e = e;
                com.xiaopeng.lib.utils.c.i("VersionModel", e.getMessage());
                return str2;
            }
        } catch (Exception e2) {
            e = e2;
            str2 = "Unknown";
        }
        return str2;
    }

    public void a(c cVar, String[]... strArr) {
        if (strArr.length < 2) {
            return;
        }
        new AsyncTaskC0064a(cVar).execute(strArr[0], strArr[1]);
    }

    /* compiled from: EcuVersionModel.java */
    /* renamed from: com.xiaopeng.devtools.model.h.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private class AsyncTaskC0064a extends AsyncTask<String[], Void, String> {
        private c uM;

        public AsyncTaskC0064a(c cVar) {
            this.uM = cVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public String doInBackground(String[]... strArr) {
            if (strArr.length < 2) {
                cancel(true);
                return null;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < strArr[0].length; i++) {
                sb.append(strArr[0][i]);
                sb.append("  ");
                sb.append(a.this.bG(strArr[1][i]));
                if (i != strArr[0].length - 1) {
                    sb.append("\n\n");
                }
            }
            return sb.toString();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: bE */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (this.uM != null && !TextUtils.isEmpty(str)) {
                this.uM.onSuccess(str);
            }
        }
    }

    /* compiled from: EcuVersionModel.java */
    /* loaded from: classes12.dex */
    private static class b {
        private static final a uO = new a();
    }
}
