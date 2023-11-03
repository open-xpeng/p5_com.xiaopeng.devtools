package com.xiaopeng.devtools.model.c.a.g;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import com.xiaopeng.devtools.utils.g;
import java.io.File;
import java.io.IOException;

/* compiled from: RecordModel.java */
/* loaded from: classes12.dex */
public class b implements a {
    private static final String tA = Environment.getExternalStorageDirectory() + "/record/xpTest.amr";
    private static final String tB = Environment.getExternalStorageDirectory() + "/record/";
    Context mContext;
    File mFile;
    private MediaPlayer mPlayer = null;
    private MediaRecorder sU;
    private MediaPlayer.OnCompletionListener tC;
    private MediaRecorder.OnInfoListener tD;

    public b(Context context) {
        this.mContext = null;
        this.mFile = null;
        this.mContext = context;
        this.mFile = new File(tB);
        if (!this.mFile.exists()) {
            this.mFile.mkdirs();
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.g.a
    public boolean gN() {
        return g.aY(tA);
    }

    @Override // com.xiaopeng.devtools.model.c.a.g.a
    public void a(MediaPlayer.OnCompletionListener onCompletionListener, MediaRecorder.OnInfoListener onInfoListener) {
        this.tC = onCompletionListener;
        this.tD = onInfoListener;
    }

    @Override // com.xiaopeng.devtools.model.c.a.g.a
    public void startRecord() {
        if (this.sU == null) {
            this.sU = new MediaRecorder();
        }
        try {
            this.sU.setAudioSource(0);
            this.sU.setOutputFormat(4);
            this.sU.setAudioEncoder(2);
            this.sU.setOutputFile(tA);
            this.sU.setMaxDuration(600000);
            this.sU.setOnInfoListener(this.tD);
            this.sU.prepare();
            this.sU.start();
        } catch (IOException e) {
            Log.i("RecordModel", "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
        } catch (IllegalStateException e2) {
            Log.i("RecordModel", "call startAmr(File mRecAudioFile) failed!" + e2.getMessage());
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.g.a
    public void stopRecord() {
        if (this.sU == null) {
            return;
        }
        this.sU.stop();
        this.sU.reset();
        this.sU.release();
        this.sU = null;
    }

    @Override // com.xiaopeng.devtools.model.c.a.g.a
    public void play() {
        if (this.mPlayer == null) {
            this.mPlayer = new MediaPlayer();
            try {
                this.mPlayer.setDataSource(tA);
                this.mPlayer.setOnCompletionListener(this.tC);
                this.mPlayer.prepare();
                this.mPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.g.a
    public void stopPlay() {
        if (this.mPlayer != null) {
            this.mPlayer.stop();
            this.mPlayer.release();
            this.mPlayer = null;
        }
    }

    @Override // com.xiaopeng.devtools.model.c.a.g.a
    public void releaseAll() {
        if (this.sU != null) {
            this.sU.stop();
            this.sU.release();
        }
        if (this.mPlayer != null) {
            this.mPlayer.stop();
            this.mPlayer.release();
        }
        g.aX(tA);
    }
}
