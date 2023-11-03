package com.xiaopeng.devtools.view.factorytest.audio;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.lib.utils.c;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AudioAdapter.java */
/* loaded from: classes12.dex */
public class a extends RecyclerView.Adapter<C0072a> {
    public static String mPath;
    private Context mContext;
    private MediaPlayer mPlayer;
    private List<String> mList = new ArrayList();
    private int mCurrentPosition = -1;

    public a(Context context, String str) {
        this.mContext = context;
        mPath = str;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public C0072a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C0072a(LayoutInflater.from(this.mContext).inflate(R.layout.item_audio_test, (ViewGroup) null));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(C0072a c0072a, final int i) {
        c0072a.Gk.setText(this.mList.get(i));
        c0072a.Gk.setTextColor(-1);
        if (this.mCurrentPosition == i) {
            c0072a.Gk.setTextColor(-65536);
        }
        c0072a.Gl.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.factorytest.audio.-$$Lambda$a$R9kr1ao5X5Ocx89ZLi313NNdR_I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                a.this.a(i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(int i, View view) {
        notifyDataSetChanged();
        if (this.mCurrentPosition == i) {
            stopPlay();
            return;
        }
        stopPlay();
        dg(mPath + this.mList.get(i));
        this.mCurrentPosition = i;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    public void x(List<String> list) {
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    /* compiled from: AudioAdapter.java */
    /* renamed from: com.xiaopeng.devtools.view.factorytest.audio.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0072a extends RecyclerView.ViewHolder {
        private TextView Gk;
        private RelativeLayout Gl;

        public C0072a(View view) {
            super(view);
            this.Gk = (TextView) view.findViewById(R.id.txt_item_audio);
            this.Gl = (RelativeLayout) view.findViewById(R.id.rl_item_audio);
        }
    }

    public void dg(String str) {
        c.f("AudioAdapter", "play=" + str);
        if (this.mPlayer == null) {
            c.f("AudioAdapter", "testTrack mMediaPlayer == null ");
            this.mPlayer = new MediaPlayer();
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.xiaopeng.devtools.view.factorytest.audio.-$$Lambda$a$LiZSJaJiUww1aQiA2n0Yf-y4VIE
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer) {
                    a.b(mediaPlayer);
                }
            });
            this.mPlayer.setLooping(true);
        } else {
            c.f("AudioAdapter", "testTrack mMediaPlayer != null ");
            this.mPlayer.reset();
        }
        try {
            this.mPlayer.setDataSource(str);
            this.mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(MediaPlayer mediaPlayer) {
        c.f("AudioAdapter", " mMediaPlayer onPrepared");
        mediaPlayer.start();
    }

    public void stopPlay() {
        c.f("AudioAdapter", "stopPlay");
        if (this.mPlayer != null) {
            try {
                this.mPlayer.stop();
                this.mPlayer.release();
                this.mPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
