package com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import com.xiaopeng.lib.utils.c;

/* compiled from: RecordPresenter.java */
/* loaded from: classes12.dex */
public class b implements a {
    private Context mContext;
    private com.xiaopeng.devtools.view.factorytest.hardwaretest.record.a xk;
    private com.xiaopeng.devtools.model.c.a.g.a xl;
    private MediaRecorder.OnInfoListener tD = new MediaRecorder.OnInfoListener() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.-$$Lambda$b$b8J0PjqHUK5AZBuVL4C9i_Sftfw
        @Override // android.media.MediaRecorder.OnInfoListener
        public final void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
            b.this.a(mediaRecorder, i, i2);
        }
    };
    private MediaPlayer.OnCompletionListener tC = new MediaPlayer.OnCompletionListener() { // from class: com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.-$$Lambda$b$u25xa7Os2Udrg8GnbERFvPGAjws
        @Override // android.media.MediaPlayer.OnCompletionListener
        public final void onCompletion(MediaPlayer mediaPlayer) {
            b.this.a(mediaPlayer);
        }
    };

    public b(Context context, com.xiaopeng.devtools.view.factorytest.hardwaretest.record.a aVar) {
        this.mContext = null;
        this.xk = null;
        this.xl = null;
        this.mContext = context;
        this.xk = aVar;
        this.xl = new com.xiaopeng.devtools.model.c.a.g.b(context);
        this.xl.a(this.tC, this.tD);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(MediaRecorder mediaRecorder, int i, int i2) {
        c.i("RecordPresenter", "MediaRecorder.OnInfoListener [%d]", Integer.valueOf(i));
        if (i == 800 && this.xk != null) {
            this.xk.dl(2);
            stopRecord();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(MediaPlayer mediaPlayer) {
        c.g("RecordPresenter", "MediaPlayer.OnCompletionListener");
        if (this.xk != null) {
            this.xk.dl(4);
            stopPlay();
        }
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.a
    public boolean gN() {
        return this.xl.gN();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.a
    public void startRecord() {
        this.xl.startRecord();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.a
    public void stopRecord() {
        this.xl.stopRecord();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.a
    public void play() {
        this.xl.play();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.a
    public void stopPlay() {
        this.xl.stopPlay();
    }

    @Override // com.xiaopeng.devtools.presenter.factorytest.hardwaretest.i.a
    public void releaseAll() {
        this.xl.releaseAll();
    }
}
