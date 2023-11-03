package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.MediaTimestamp;
import android.support.annotation.RestrictTo;

/* loaded from: classes7.dex */
public final class MediaTimestamp2 {
    public static final MediaTimestamp2 TIMESTAMP_UNKNOWN = new MediaTimestamp2(-1, -1, 0.0f);
    private final float mClockRate;
    private final long mMediaTimeUs;
    private final long mNanoTime;

    public long getAnchorMediaTimeUs() {
        return this.mMediaTimeUs;
    }

    public long getAnchorSytemNanoTime() {
        return this.mNanoTime;
    }

    public float getMediaClockRate() {
        return this.mClockRate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(23)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MediaTimestamp2(MediaTimestamp mediaTimestamp) {
        this.mMediaTimeUs = mediaTimestamp.getAnchorMediaTimeUs();
        this.mNanoTime = mediaTimestamp.getAnchorSytemNanoTime();
        this.mClockRate = mediaTimestamp.getMediaClockRate();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    MediaTimestamp2(long j, long j2, float f) {
        this.mMediaTimeUs = j;
        this.mNanoTime = j2;
        this.mClockRate = f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    MediaTimestamp2() {
        this.mMediaTimeUs = 0L;
        this.mNanoTime = 0L;
        this.mClockRate = 1.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaTimestamp2 mediaTimestamp2 = (MediaTimestamp2) obj;
        if (this.mMediaTimeUs == mediaTimestamp2.mMediaTimeUs && this.mNanoTime == mediaTimestamp2.mNanoTime && this.mClockRate == mediaTimestamp2.mClockRate) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) ((31 * ((int) ((Long.valueOf(this.mMediaTimeUs).hashCode() * 31) + this.mNanoTime))) + this.mClockRate);
    }

    public String toString() {
        return getClass().getName() + "{AnchorMediaTimeUs=" + this.mMediaTimeUs + " AnchorSystemNanoTime=" + this.mNanoTime + " ClockRate=" + this.mClockRate + "}";
    }
}
