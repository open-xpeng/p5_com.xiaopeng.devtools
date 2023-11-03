package com.xiaopeng.devtools.model.g;

import android.os.AsyncTask;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.g;
import java.io.UnsupportedEncodingException;

/* compiled from: ControlPanelModel.java */
/* loaded from: classes12.dex */
public class b implements d {
    private com.xiaopeng.devtools.presenter.f.d uF;

    public b(com.xiaopeng.devtools.presenter.f.d dVar) {
        this.uF = dVar;
    }

    @Override // com.xiaopeng.devtools.model.g.d
    public void hE() {
        new a().execute(new Void[0]);
    }

    /* compiled from: ControlPanelModel.java */
    /* loaded from: classes12.dex */
    private class a extends AsyncTask<Void, Void, String> {
        private a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: d */
        public String doInBackground(Void... voidArr) {
            g.a(MyApplication.getContext(), "sdcard/data.json", (int) R.raw.data);
            try {
                return new String(g.ct("sdcard/data.json"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: bE */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            b.this.uF.cj(str);
        }
    }
}
