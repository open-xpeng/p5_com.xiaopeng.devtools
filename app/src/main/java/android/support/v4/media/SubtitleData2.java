package android.support.v4.media;

import android.annotation.TargetApi;
import android.media.SubtitleData;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;

/* loaded from: classes7.dex */
public final class SubtitleData2 {
    public static final String MIMETYPE_TEXT_CEA_608 = "text/cea-608";
    public static final String MIMETYPE_TEXT_CEA_708 = "text/cea-708";
    public static final String MIMETYPE_TEXT_VTT = "text/vtt";
    private static final String TAG = "SubtitleData2";
    private byte[] mData;
    private long mDurationUs;
    private long mStartTimeUs;
    private int mTrackIndex;

    @TargetApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public SubtitleData2(SubtitleData subtitleData) {
        this.mTrackIndex = subtitleData.getTrackIndex();
        this.mStartTimeUs = subtitleData.getStartTimeUs();
        this.mDurationUs = subtitleData.getDurationUs();
        this.mData = subtitleData.getData();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public SubtitleData2(int i, long j, long j2, byte[] bArr) {
        this.mTrackIndex = i;
        this.mStartTimeUs = j;
        this.mDurationUs = j2;
        this.mData = bArr;
    }

    public int getTrackIndex() {
        return this.mTrackIndex;
    }

    public long getStartTimeUs() {
        return this.mStartTimeUs;
    }

    public long getDurationUs() {
        return this.mDurationUs;
    }

    @NonNull
    public byte[] getData() {
        return this.mData;
    }
}
