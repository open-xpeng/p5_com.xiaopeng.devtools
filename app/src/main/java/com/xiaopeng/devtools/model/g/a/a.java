package com.xiaopeng.devtools.model.g.a;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.xiaopeng.devtools.bean.event.FileEvent;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import org.greenrobot.eventbus.EventBus;

/* compiled from: AdasCollectModel.java */
/* loaded from: classes12.dex */
public class a {
    private HandlerThread mHandlerThread = new HandlerThread("adas_collect");
    private b uH;

    public a() {
        this.mHandlerThread.start();
        this.uH = new b(this.mHandlerThread.getLooper());
    }

    public void hF() {
        Message obtainMessage = this.uH.obtainMessage(1);
        obtainMessage.arg1 = 2;
        this.uH.sendMessage(obtainMessage);
    }

    public void hG() {
        new AsyncTaskC0062a().execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AdasCollectModel.java */
    /* renamed from: com.xiaopeng.devtools.model.g.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class AsyncTaskC0062a extends AsyncTask<Void, Void, Void> {
        private AsyncTaskC0062a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            File[] listFiles;
            File file = new File(com.xiaopeng.devtools.presenter.f.a.a.nx[0]);
            if (file.isDirectory() && file.exists() && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
                Arrays.sort(listFiles);
                for (File file2 : listFiles) {
                    f(file2);
                }
                return null;
            }
            return null;
        }

        private void f(File file) {
            if (file != null) {
                if (file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        f(file2);
                    }
                    file.delete();
                    return;
                }
                file.delete();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r3) {
            super.onPostExecute((AsyncTaskC0062a) r3);
            EventBus.getDefault().post(new FileEvent(1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AdasCollectModel.java */
    /* loaded from: classes12.dex */
    public class b extends Handler {
        private int mPosition;
        private byte[] uJ;

        public b(Looper looper) {
            super(looper);
            this.uJ = new byte[1024];
            this.mPosition = 0;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                if (message.arg1 == 1) {
                    byte[] bytes = ((String) message.obj).getBytes();
                    if (bytes.length + this.mPosition < 1024) {
                        System.arraycopy(bytes, 0, this.uJ, this.mPosition, bytes.length);
                        this.mPosition += bytes.length;
                        return;
                    }
                    w(this.uJ);
                    this.mPosition = 0;
                    this.uJ = new byte[1024];
                    if (bytes.length + this.mPosition < this.uJ.length) {
                        System.arraycopy(bytes, 0, this.uJ, this.mPosition, bytes.length);
                        this.mPosition += bytes.length;
                    }
                } else if (message.arg1 == 2 && this.mPosition != 0 && this.uJ != null) {
                    w(this.uJ);
                    this.mPosition = 0;
                    this.uJ = new byte[1024];
                }
            }
        }

        private void w(byte[] bArr) {
            RandomAccessFile randomAccessFile = null;
            try {
                try {
                    try {
                        File file = new File(com.xiaopeng.devtools.presenter.f.a.a.zt);
                        if (!file.exists()) {
                            file.getParentFile().mkdir();
                            file.createNewFile();
                        }
                        RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                        try {
                            randomAccessFile2.seek(randomAccessFile2.length());
                            randomAccessFile2.write(bArr, 0, this.mPosition);
                            randomAccessFile2.close();
                        } catch (IOException e) {
                            e = e;
                            randomAccessFile = randomAccessFile2;
                            e.printStackTrace();
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile = randomAccessFile2;
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }
}
