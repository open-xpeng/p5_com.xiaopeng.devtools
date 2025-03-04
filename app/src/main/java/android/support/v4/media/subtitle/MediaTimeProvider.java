package android.support.v4.media.subtitle;

import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes7.dex */
public interface MediaTimeProvider {
    public static final long NO_TIME = -1;

    /* loaded from: classes7.dex */
    public interface OnMediaTimeListener {
        void onSeek(long j);

        void onStop();

        void onTimedEvent(long j);
    }

    void cancelNotifications(OnMediaTimeListener onMediaTimeListener);

    long getCurrentTimeUs(boolean z, boolean z2) throws IllegalStateException;

    void notifyAt(long j, OnMediaTimeListener onMediaTimeListener);

    void scheduleUpdate(OnMediaTimeListener onMediaTimeListener);
}
