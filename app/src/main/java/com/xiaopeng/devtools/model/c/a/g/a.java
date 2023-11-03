package com.xiaopeng.devtools.model.c.a.g;

import android.media.MediaPlayer;
import android.media.MediaRecorder;

/* compiled from: IRecordModel.java */
/* loaded from: classes12.dex */
public interface a {
    void a(MediaPlayer.OnCompletionListener onCompletionListener, MediaRecorder.OnInfoListener onInfoListener);

    boolean gN();

    void play();

    void releaseAll();

    void startRecord();

    void stopPlay();

    void stopRecord();
}
