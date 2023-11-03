package com.xiaopeng.xui.b;

import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.AndroidRuntimeException;
import androidx.annotation.NonNull;
import com.xiaopeng.xui.b.e;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SoundEffectPool.java */
/* loaded from: classes13.dex */
public class c extends com.xiaopeng.xui.b.a {
    private int abe;
    private int abf;
    private SoundPool abg;
    private e.a abh;
    private boolean abi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(@NonNull d dVar) {
        super(dVar);
        log("constructor");
        switch (dVar.getLocation()) {
            case 0:
                this.abh = new a();
                break;
            case 1:
                this.abh = new b();
                break;
        }
        init();
    }

    private void init() {
        log("init");
        this.abg = qM();
        load();
    }

    @Override // com.xiaopeng.xui.b.e
    public void play() {
        log("play");
        if (this.abg != null) {
            this.abi = true;
            if (this.abe == 0) {
                load();
                return;
            } else {
                this.abf = this.abg.play(this.abe, 1.0f, 1.0f, 1, 0, 1.0f);
                return;
            }
        }
        init();
    }

    @Override // com.xiaopeng.xui.b.e
    public void release() {
        log("release");
        if (this.abg != null) {
            this.abg.release();
            this.abg = null;
        }
    }

    private SoundPool qM() {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(1);
        AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
        builder2.setLegacyStreamType(this.abb.getStreamType());
        log("createSoundPool mSoundID : " + this.abe + ",StreamType " + this.abb.getStreamType());
        builder.setAudioAttributes(builder2.build());
        return builder.build();
    }

    private void load() {
        if (this.abh != null) {
            this.abe = this.abh.a(this.abb);
            log("load mSoundID : " + this.abe);
        } else {
            log("load mISoundLoader is null");
        }
        this.abg.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.xiaopeng.xui.b.-$$Lambda$c$-vAgW4ZqFbop80RuDt3Rry1pR4o
            @Override // android.media.SoundPool.OnLoadCompleteListener
            public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
                c.this.a(soundPool, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(SoundPool soundPool, int i, int i2) {
        log("load onLoadComplete--,sampleId:" + i + ",status:" + i2);
        if (this.abi) {
            this.abi = false;
            play();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        com.xiaopeng.xui.d.f.f("xpui-SoundEffectPool", str);
    }

    /* compiled from: SoundEffectPool.java */
    /* loaded from: classes13.dex */
    private class a implements e.a {
        private a() {
        }

        @Override // com.xiaopeng.xui.b.e.a
        public int a(d dVar) {
            AssetFileDescriptor assetFileDescriptor;
            IOException e;
            if (c.this.abg != null) {
                try {
                    assetFileDescriptor = com.xiaopeng.xui.a.getContext().getAssets().openFd(c.this.abb.getPath());
                } catch (IOException e2) {
                    assetFileDescriptor = null;
                    e = e2;
                }
                try {
                    try {
                        c.this.log("LoaderAssets--resource:" + dVar + ",path:" + c.this.abb.getPath() + ", is success");
                    } catch (IOException e3) {
                        e = e3;
                        c.this.log("LoaderAssets--resource:" + dVar + ",path:" + c.this.abb.getPath() + ", is error");
                        e.printStackTrace();
                        return c.this.abg.load(assetFileDescriptor, 1);
                    }
                    return c.this.abg.load(assetFileDescriptor, 1);
                } catch (AndroidRuntimeException e4) {
                    e4.printStackTrace();
                    return 0;
                }
            }
            c.this.log("LoaderAssets--resource:" + dVar + ",mSoundPool is null");
            return 0;
        }
    }

    /* compiled from: SoundEffectPool.java */
    /* loaded from: classes13.dex */
    private class b implements e.a {
        private b() {
        }

        @Override // com.xiaopeng.xui.b.e.a
        public int a(d dVar) {
            if (c.this.abg != null) {
                return c.this.abg.load(c.this.abb.getPath(), 1);
            }
            c cVar = c.this;
            cVar.log("LoaderSystem--resource:" + dVar + ",mSoundPool is null");
            return 0;
        }
    }
}
