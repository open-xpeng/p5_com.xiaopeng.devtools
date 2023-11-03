package android.support.v4.media;

import android.media.PlaybackParams;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes7.dex */
public final class PlaybackParams2 {
    public static final int AUDIO_FALLBACK_MODE_DEFAULT = 0;
    public static final int AUDIO_FALLBACK_MODE_FAIL = 2;
    public static final int AUDIO_FALLBACK_MODE_MUTE = 1;
    private Integer mAudioFallbackMode;
    private Float mPitch;
    private PlaybackParams mPlaybackParams;
    private Float mSpeed;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes7.dex */
    public @interface AudioFallbackMode {
    }

    private PlaybackParams2(Integer num, Float f, Float f2) {
        this.mAudioFallbackMode = num;
        this.mPitch = f;
        this.mSpeed = f2;
    }

    @RequiresApi(23)
    private PlaybackParams2(PlaybackParams playbackParams) {
        this.mPlaybackParams = playbackParams;
    }

    public Integer getAudioFallbackMode() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                return Integer.valueOf(this.mPlaybackParams.getAudioFallbackMode());
            } catch (IllegalStateException e) {
                return null;
            }
        }
        return this.mAudioFallbackMode;
    }

    public Float getPitch() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                return Float.valueOf(this.mPlaybackParams.getPitch());
            } catch (IllegalStateException e) {
                return null;
            }
        }
        return this.mPitch;
    }

    public Float getSpeed() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                return Float.valueOf(this.mPlaybackParams.getSpeed());
            } catch (IllegalStateException e) {
                return null;
            }
        }
        return this.mPitch;
    }

    @RequiresApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PlaybackParams getPlaybackParams() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.mPlaybackParams;
        }
        return null;
    }

    /* loaded from: classes7.dex */
    public static final class Builder {
        private Integer mAudioFallbackMode;
        private Float mPitch;
        private PlaybackParams mPlaybackParams;
        private Float mSpeed;

        public Builder() {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mPlaybackParams = new PlaybackParams();
            }
        }

        @RequiresApi(23)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder(PlaybackParams playbackParams) {
            this.mPlaybackParams = playbackParams;
        }

        public Builder setAudioFallbackMode(int i) {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mPlaybackParams.setAudioFallbackMode(i);
            } else {
                this.mAudioFallbackMode = Integer.valueOf(i);
            }
            return this;
        }

        public Builder setPitch(float f) {
            if (f < 0.0f) {
                throw new IllegalArgumentException("pitch must not be negative");
            }
            if (Build.VERSION.SDK_INT >= 23) {
                this.mPlaybackParams.setPitch(f);
            } else {
                this.mPitch = Float.valueOf(f);
            }
            return this;
        }

        public Builder setSpeed(float f) {
            if (Build.VERSION.SDK_INT >= 23) {
                this.mPlaybackParams.setSpeed(f);
            } else {
                this.mSpeed = Float.valueOf(f);
            }
            return this;
        }

        public PlaybackParams2 build() {
            if (Build.VERSION.SDK_INT >= 23) {
                return new PlaybackParams2(this.mPlaybackParams);
            }
            return new PlaybackParams2(this.mAudioFallbackMode, this.mPitch, this.mSpeed);
        }
    }
}
