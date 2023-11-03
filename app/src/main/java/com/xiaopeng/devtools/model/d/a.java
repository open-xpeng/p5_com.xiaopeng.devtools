package com.xiaopeng.devtools.model.d;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.bean.event.oled.OledEvent;
import com.xiaopeng.devtools.bean.oled.CustomOledMusicBook;
import com.xiaopeng.devtools.bean.oled.OledData;
import com.xiaopeng.devtools.presenter.oled.CANMsg387;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* compiled from: OledModel.java */
/* loaded from: classes12.dex */
public class a implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private c tG;
    private MediaPlayer tH;

    /* compiled from: OledModel.java */
    /* loaded from: classes12.dex */
    public interface b<T> {
        void m(T t);
    }

    /* compiled from: OledModel.java */
    /* loaded from: classes12.dex */
    public interface c {
        void q(List<OledData> list);
    }

    private a() {
    }

    public static a gZ() {
        return d.tL;
    }

    public void onCreate() {
    }

    public void a(c cVar) {
        this.tG = cVar;
        new e().execute(new Void[0]);
    }

    public void a(b bVar) {
        new AsyncTaskC0059a(bVar, new TypeToken<List<CustomOledMusicBook>>() { // from class: com.xiaopeng.devtools.model.d.a.1
        }.getType()).execute("oled/sound_of_music/sound_of_music.json");
    }

    public void b(b bVar) {
        new AsyncTaskC0059a(bVar, new TypeToken<List<CANMsg387>>() { // from class: com.xiaopeng.devtools.model.d.a.2
        }.getType()).execute("oled/oled_custom_default.json");
    }

    public void onDestroy() {
    }

    public void a(OledData oledData) {
    }

    public void a(CANMsg387 cANMsg387) {
    }

    public void bC(String str) {
        this.tH = new MediaPlayer();
        try {
            AssetFileDescriptor openFd = MyApplication.getContext().getAssets().openFd(str);
            this.tH.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.tH.setOnPreparedListener(this);
            this.tH.setOnCompletionListener(this);
            this.tH.setOnErrorListener(this);
            this.tH.prepareAsync();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void ha() {
        if (this.tH != null) {
            this.tH.release();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<OledData> bD(@NonNull String str) {
        List<OledData> list = (List) new Gson().fromJson(str, new TypeToken<List<OledData>>() { // from class: com.xiaopeng.devtools.model.d.a.3
        }.getType());
        Iterator<OledData> it = list.iterator();
        while (it.hasNext()) {
            com.xiaopeng.lib.utils.c.f("OledModel", "getCduOledDynamicMode = " + it.next().getCduOledDynamicMode());
        }
        return list;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        EventBus.getDefault().post(new OledEvent(1));
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
        this.tH = null;
        EventBus.getDefault().post(new OledEvent(2));
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.tH.release();
        this.tH = null;
        EventBus.getDefault().post(new OledEvent(3));
        return false;
    }

    /* compiled from: OledModel.java */
    /* loaded from: classes12.dex */
    private class e extends AsyncTask<Void, Void, List<OledData>> {
        private e() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0050 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.List<com.xiaopeng.devtools.bean.oled.OledData> doInBackground(java.lang.Void... r6) {
            /*
                r5 = this;
                r6 = 0
                android.content.Context r0 = com.xiaopeng.devtools.MyApplication.getContext()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                android.content.res.Resources r0 = r0.getResources()     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                r1 = 2131689475(0x7f0f0003, float:1.9007966E38)
                java.io.InputStream r0 = r0.openRawResource(r1)     // Catch: java.lang.Throwable -> L37 java.io.IOException -> L3c
                int r1 = r0.available()     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                r0.read(r1)     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                java.lang.String r3 = "UTF-8"
                r2.<init>(r1, r3)     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                com.xiaopeng.devtools.model.d.a r1 = com.xiaopeng.devtools.model.d.a.this     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
                java.util.List r1 = com.xiaopeng.devtools.model.d.a.a(r1, r2)     // Catch: java.io.IOException -> L35 java.lang.Throwable -> L4d
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
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.model.d.a.e.doInBackground(java.lang.Void[]):java.util.List");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: p */
        public void onPostExecute(List<OledData> list) {
            super.onPostExecute(list);
            if (list != null && a.this.tG != null) {
                a.this.tG.q(list);
            }
        }
    }

    /* compiled from: OledModel.java */
    /* renamed from: com.xiaopeng.devtools.model.d.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private class AsyncTaskC0059a<T> extends AsyncTask<String, Void, List<T>> {
        private b<List<T>> tJ;
        private Type tK;

        public AsyncTaskC0059a(b bVar, Type type) {
            this.tJ = bVar;
            this.tK = type;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r6v12 */
        /* JADX WARN: Type inference failed for: r6v13 */
        /* JADX WARN: Type inference failed for: r6v14 */
        /* JADX WARN: Type inference failed for: r6v15 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v4, types: [java.io.InputStream] */
        /* JADX WARN: Type inference failed for: r6v6 */
        @Override // android.os.AsyncTask
        /* renamed from: e */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.util.List<T> doInBackground(java.lang.String... r6) {
            /*
                r5 = this;
                r0 = 0
                android.content.Context r1 = com.xiaopeng.devtools.MyApplication.getContext()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L43
                android.content.res.AssetManager r1 = r1.getAssets()     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L43
                r2 = 0
                r6 = r6[r2]     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L43
                java.io.InputStream r6 = r1.open(r6)     // Catch: java.lang.Throwable -> L3e java.io.IOException -> L43
                int r1 = r6.available()     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                byte[] r1 = new byte[r1]     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                r6.read(r1)     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                java.lang.String r3 = "UTF-8"
                r2.<init>(r1, r3)     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                com.google.gson.Gson r1 = new com.google.gson.Gson     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                r1.<init>()     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                java.lang.reflect.Type r3 = r5.tK     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                java.lang.Object r1 = r1.fromJson(r2, r3)     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                java.util.List r1 = (java.util.List) r1     // Catch: java.io.IOException -> L3c java.lang.Throwable -> L54
                if (r6 == 0) goto L3a
                r6.close()     // Catch: java.io.IOException -> L35
            L34:
                goto L3a
            L35:
                r6 = move-exception
                r6.printStackTrace()
                goto L34
            L3a:
                r0 = r1
                goto L53
            L3c:
                r1 = move-exception
                goto L45
            L3e:
                r6 = move-exception
                r4 = r0
                r0 = r6
                r6 = r4
                goto L55
            L43:
                r1 = move-exception
                r6 = r0
            L45:
                r1.printStackTrace()     // Catch: java.lang.Throwable -> L54
                if (r6 == 0) goto L53
                r6.close()     // Catch: java.io.IOException -> L4e
            L4d:
                goto L53
            L4e:
                r6 = move-exception
                r6.printStackTrace()
                goto L4d
            L53:
                return r0
            L54:
                r0 = move-exception
            L55:
                if (r6 == 0) goto L5f
                r6.close()     // Catch: java.io.IOException -> L5b
                goto L5f
            L5b:
                r6 = move-exception
                r6.printStackTrace()
            L5f:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.model.d.a.AsyncTaskC0059a.doInBackground(java.lang.String[]):java.util.List");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: p */
        public void onPostExecute(List<T> list) {
            super.onPostExecute(list);
            if (this.tJ != null && list != null) {
                this.tJ.m(list);
            }
        }
    }

    /* compiled from: OledModel.java */
    /* loaded from: classes12.dex */
    private static class d {
        private static final a tL = new a();
    }
}
