package com.xiaopeng.devtools.bean.event.oled;

/* loaded from: classes12.dex */
public class OledEvent {
    public static final int MUSIC_COMPLETE = 2;
    public static final int MUSIC_ERROR = 3;
    public static final int MUSIC_PLAY = 1;
    public int mMusicPlayStatus;

    public OledEvent(int i) {
        this.mMusicPlayStatus = i;
    }
}
