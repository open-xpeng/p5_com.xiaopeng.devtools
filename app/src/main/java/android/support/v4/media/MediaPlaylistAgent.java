package android.support.v4.media;

import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public abstract class MediaPlaylistAgent {
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_GROUP = 3;
    public static final int REPEAT_MODE_NONE = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int SHUFFLE_MODE_ALL = 1;
    public static final int SHUFFLE_MODE_GROUP = 2;
    public static final int SHUFFLE_MODE_NONE = 0;
    private static final String TAG = "MediaPlaylistAgent";
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private final SimpleArrayMap<PlaylistEventCallback, Executor> mCallbacks = new SimpleArrayMap<>();

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes7.dex */
    public @interface RepeatMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes7.dex */
    public @interface ShuffleMode {
    }

    public abstract void addPlaylistItem(int i, @NonNull MediaItem2 mediaItem2);

    public abstract MediaItem2 getCurrentMediaItem();

    @Nullable
    public abstract List<MediaItem2> getPlaylist();

    @Nullable
    public abstract MediaMetadata2 getPlaylistMetadata();

    public abstract int getRepeatMode();

    public abstract int getShuffleMode();

    public abstract void removePlaylistItem(@NonNull MediaItem2 mediaItem2);

    public abstract void replacePlaylistItem(int i, @NonNull MediaItem2 mediaItem2);

    public abstract void setPlaylist(@NonNull List<MediaItem2> list, @Nullable MediaMetadata2 mediaMetadata2);

    public abstract void setRepeatMode(int i);

    public abstract void setShuffleMode(int i);

    public abstract void skipToNextItem();

    public abstract void skipToPlaylistItem(@NonNull MediaItem2 mediaItem2);

    public abstract void skipToPreviousItem();

    public abstract void updatePlaylistMetadata(@Nullable MediaMetadata2 mediaMetadata2);

    public final void registerPlaylistEventCallback(@NonNull Executor executor, @NonNull PlaylistEventCallback playlistEventCallback) {
        if (executor == null) {
            throw new IllegalArgumentException("executor shouldn't be null");
        }
        if (playlistEventCallback == null) {
            throw new IllegalArgumentException("callback shouldn't be null");
        }
        synchronized (this.mLock) {
            if (this.mCallbacks.get(playlistEventCallback) != null) {
                Log.w(TAG, "callback is already added. Ignoring.");
            } else {
                this.mCallbacks.put(playlistEventCallback, executor);
            }
        }
    }

    public final void unregisterPlaylistEventCallback(@NonNull PlaylistEventCallback playlistEventCallback) {
        if (playlistEventCallback == null) {
            throw new IllegalArgumentException("callback shouldn't be null");
        }
        synchronized (this.mLock) {
            this.mCallbacks.remove(playlistEventCallback);
        }
    }

    public final void notifyPlaylistChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        final List<MediaItem2> playlist = getPlaylist();
        final MediaMetadata2 playlistMetadata = getPlaylistMetadata();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() { // from class: android.support.v4.media.MediaPlaylistAgent.1
                @Override // java.lang.Runnable
                public void run() {
                    keyAt.onPlaylistChanged(MediaPlaylistAgent.this, playlist, playlistMetadata);
                }
            });
        }
    }

    public final void notifyPlaylistMetadataChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() { // from class: android.support.v4.media.MediaPlaylistAgent.2
                @Override // java.lang.Runnable
                public void run() {
                    keyAt.onPlaylistMetadataChanged(MediaPlaylistAgent.this, MediaPlaylistAgent.this.getPlaylistMetadata());
                }
            });
        }
    }

    public final void notifyShuffleModeChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() { // from class: android.support.v4.media.MediaPlaylistAgent.3
                @Override // java.lang.Runnable
                public void run() {
                    keyAt.onShuffleModeChanged(MediaPlaylistAgent.this, MediaPlaylistAgent.this.getShuffleMode());
                }
            });
        }
    }

    public final void notifyRepeatModeChanged() {
        SimpleArrayMap<PlaylistEventCallback, Executor> callbacks = getCallbacks();
        for (int i = 0; i < callbacks.size(); i++) {
            final PlaylistEventCallback keyAt = callbacks.keyAt(i);
            callbacks.valueAt(i).execute(new Runnable() { // from class: android.support.v4.media.MediaPlaylistAgent.4
                @Override // java.lang.Runnable
                public void run() {
                    keyAt.onRepeatModeChanged(MediaPlaylistAgent.this, MediaPlaylistAgent.this.getRepeatMode());
                }
            });
        }
    }

    @Nullable
    public MediaItem2 getMediaItem(@NonNull DataSourceDesc dataSourceDesc) {
        if (dataSourceDesc == null) {
            throw new IllegalArgumentException("dsd shouldn't be null");
        }
        List<MediaItem2> playlist = getPlaylist();
        if (playlist == null) {
            return null;
        }
        for (int i = 0; i < playlist.size(); i++) {
            MediaItem2 mediaItem2 = playlist.get(i);
            if (mediaItem2 != null && mediaItem2.getDataSourceDesc().equals(dataSourceDesc)) {
                return mediaItem2;
            }
        }
        return null;
    }

    private SimpleArrayMap<PlaylistEventCallback, Executor> getCallbacks() {
        SimpleArrayMap<PlaylistEventCallback, Executor> simpleArrayMap = new SimpleArrayMap<>();
        synchronized (this.mLock) {
            simpleArrayMap.putAll(this.mCallbacks);
        }
        return simpleArrayMap;
    }

    /* loaded from: classes7.dex */
    public static abstract class PlaylistEventCallback {
        public void onPlaylistChanged(@NonNull MediaPlaylistAgent mediaPlaylistAgent, @NonNull List<MediaItem2> list, @Nullable MediaMetadata2 mediaMetadata2) {
        }

        public void onPlaylistMetadataChanged(@NonNull MediaPlaylistAgent mediaPlaylistAgent, @Nullable MediaMetadata2 mediaMetadata2) {
        }

        public void onShuffleModeChanged(@NonNull MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }

        public void onRepeatModeChanged(@NonNull MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }
    }
}
