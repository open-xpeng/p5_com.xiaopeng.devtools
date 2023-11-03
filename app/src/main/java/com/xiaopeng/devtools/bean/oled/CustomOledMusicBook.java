package com.xiaopeng.devtools.bean.oled;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes12.dex */
public class CustomOledMusicBook {
    @SerializedName("duration")
    private int duration;
    @SerializedName("note")
    private int note;

    public int getNote() {
        return this.note;
    }

    public void setNote(int i) {
        this.note = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }
}
