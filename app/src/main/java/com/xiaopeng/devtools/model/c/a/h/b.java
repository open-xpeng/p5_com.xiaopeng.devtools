package com.xiaopeng.devtools.model.c.a.h;

import android.content.Context;
import android.net.Uri;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.lib.utils.c;
import com.xpeng.test.AudioTest;

/* compiled from: SoundModel.java */
/* loaded from: classes12.dex */
public class b implements a {
    private Context mContext = MyApplication.getContext();
    private AudioTest tE = new AudioTest(this.mContext, Uri.parse("android.resource://" + this.mContext.getPackageName() + "/" + R.raw.media_volume));

    @Override // com.xiaopeng.devtools.model.c.a.h.a
    public int getCurrentMediaVolume() {
        return this.tE.getCurrentMediaVolume();
    }

    @Override // com.xiaopeng.devtools.model.c.a.h.a
    public int getMediaMaxVolume() {
        return this.tE.getMediaMaxVolume();
    }

    @Override // com.xiaopeng.devtools.model.c.a.h.a
    public String gO() {
        String G = g.G(a.c.ec("PATH_AUDIO_EXTERNAL_PA"), "   ");
        c.f("SoundModel", "getAudioPaStatus " + G);
        return G;
    }

    @Override // com.xiaopeng.devtools.model.c.a.h.a
    public void onVolumeChanged(int i, int i2) {
        c.f("SoundModel", "onVolumeChanged progress = " + i + " minVol = " + i2);
        this.tE.onVolumeChanged(i, i2);
    }

    @Override // com.xiaopeng.devtools.model.c.a.h.a
    public void cg(int i) {
        this.tE.testTrack(Uri.parse("android.resource://" + this.mContext.getPackageName() + "/" + i));
    }

    @Override // com.xiaopeng.devtools.model.c.a.h.a
    public void destroy(int i) {
        this.tE.destroy(i);
    }
}
