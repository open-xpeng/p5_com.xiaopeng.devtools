package android.support.v4.media;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.mediacompat.Rating2;
import android.support.v4.media.IMediaSession2;
import android.support.v4.media.MediaController2;
import android.support.v4.media.MediaSession2;
import android.util.Log;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class MediaController2ImplBase implements MediaController2.SupportLibraryImpl {
    @GuardedBy("mLock")
    private SessionCommandGroup2 mAllowedCommands;
    @GuardedBy("mLock")
    private long mBufferedPositionMs;
    @GuardedBy("mLock")
    private int mBufferingState;
    private final MediaController2.ControllerCallback mCallback;
    private final Executor mCallbackExecutor;
    private final Context mContext;
    final MediaController2Stub mControllerStub;
    @GuardedBy("mLock")
    private MediaItem2 mCurrentMediaItem;
    private final IBinder.DeathRecipient mDeathRecipient;
    @GuardedBy("mLock")
    private volatile IMediaSession2 mISession2;
    private final MediaController2 mInstance;
    @GuardedBy("mLock")
    private boolean mIsReleased;
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private MediaController2.PlaybackInfo mPlaybackInfo;
    @GuardedBy("mLock")
    private float mPlaybackSpeed;
    @GuardedBy("mLock")
    private int mPlayerState;
    @GuardedBy("mLock")
    private List<MediaItem2> mPlaylist;
    @GuardedBy("mLock")
    private MediaMetadata2 mPlaylistMetadata;
    @GuardedBy("mLock")
    private long mPositionEventTimeMs;
    @GuardedBy("mLock")
    private long mPositionMs;
    @GuardedBy("mLock")
    private int mRepeatMode;
    @GuardedBy("mLock")
    private SessionServiceConnection mServiceConnection;
    @GuardedBy("mLock")
    private PendingIntent mSessionActivity;
    @GuardedBy("mLock")
    private int mShuffleMode;
    private final SessionToken2 mToken;
    static final String TAG = "MC2ImplBase";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaController2ImplBase(Context context, MediaController2 mediaController2, SessionToken2 sessionToken2, Executor executor, MediaController2.ControllerCallback controllerCallback) {
        this.mInstance = mediaController2;
        if (context == null) {
            throw new IllegalArgumentException("context shouldn't be null");
        }
        if (sessionToken2 == null) {
            throw new IllegalArgumentException("token shouldn't be null");
        }
        if (controllerCallback == null) {
            throw new IllegalArgumentException("callback shouldn't be null");
        }
        if (executor == null) {
            throw new IllegalArgumentException("executor shouldn't be null");
        }
        this.mContext = context;
        this.mControllerStub = new MediaController2Stub(this);
        this.mToken = sessionToken2;
        this.mCallback = controllerCallback;
        this.mCallbackExecutor = executor;
        this.mDeathRecipient = new IBinder.DeathRecipient() { // from class: android.support.v4.media.MediaController2ImplBase.1
            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                MediaController2ImplBase.this.mInstance.close();
            }
        };
        IMediaSession2 asInterface = IMediaSession2.Stub.asInterface((IBinder) this.mToken.getBinder());
        if (this.mToken.getType() == 0) {
            this.mServiceConnection = null;
            connectToSession(asInterface);
            return;
        }
        this.mServiceConnection = new SessionServiceConnection();
        connectToService();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (DEBUG) {
            Log.d(TAG, "release from " + this.mToken);
        }
        synchronized (this.mLock) {
            IMediaSession2 iMediaSession2 = this.mISession2;
            if (this.mIsReleased) {
                return;
            }
            this.mIsReleased = true;
            if (this.mServiceConnection != null) {
                this.mContext.unbindService(this.mServiceConnection);
                this.mServiceConnection = null;
            }
            this.mISession2 = null;
            this.mControllerStub.destroy();
            if (iMediaSession2 != null) {
                try {
                    iMediaSession2.asBinder().unlinkToDeath(this.mDeathRecipient, 0);
                    iMediaSession2.release(this.mControllerStub);
                } catch (RemoteException e) {
                }
            }
            this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.2
                @Override // java.lang.Runnable
                public void run() {
                    MediaController2ImplBase.this.mCallback.onDisconnected(MediaController2ImplBase.this.mInstance);
                }
            });
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public SessionToken2 getSessionToken() {
        return this.mToken;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mISession2 != null;
        }
        return z;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void play() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(1);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.play(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void pause() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(2);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.pause(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void reset() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(3);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.reset(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void prepare() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(6);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.prepare(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void fastForward() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(7);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.fastForward(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void rewind() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(8);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.rewind(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void seekTo(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("position shouldn't be negative");
        }
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(9);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.seekTo(this.mControllerStub, j);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void skipForward() {
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void skipBackward() {
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void playFromMediaId(@NonNull String str, @Nullable Bundle bundle) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(22);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.playFromMediaId(this.mControllerStub, str, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void playFromSearch(@NonNull String str, @Nullable Bundle bundle) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(24);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.playFromSearch(this.mControllerStub, str, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void playFromUri(Uri uri, Bundle bundle) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(23);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.playFromUri(this.mControllerStub, uri, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void prepareFromMediaId(@NonNull String str, @Nullable Bundle bundle) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(25);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.prepareFromMediaId(this.mControllerStub, str, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void prepareFromSearch(@NonNull String str, @Nullable Bundle bundle) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(27);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.prepareFromSearch(this.mControllerStub, str, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void prepareFromUri(@NonNull Uri uri, @Nullable Bundle bundle) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(26);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.prepareFromUri(this.mControllerStub, uri, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void setVolumeTo(int i, int i2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(10);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.setVolumeTo(this.mControllerStub, i, i2);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void adjustVolume(int i, int i2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(11);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.adjustVolume(this.mControllerStub, i, i2);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public PendingIntent getSessionActivity() {
        PendingIntent pendingIntent;
        synchronized (this.mLock) {
            pendingIntent = this.mSessionActivity;
        }
        return pendingIntent;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public int getPlayerState() {
        int i;
        synchronized (this.mLock) {
            i = this.mPlayerState;
        }
        return i;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public long getDuration() {
        synchronized (this.mLock) {
            MediaMetadata2 metadata = this.mCurrentMediaItem.getMetadata();
            if (metadata != null && metadata.containsKey("android.media.metadata.DURATION")) {
                return metadata.getLong("android.media.metadata.DURATION");
            }
            return -1L;
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public long getCurrentPosition() {
        synchronized (this.mLock) {
            if (this.mISession2 == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return -1L;
            }
            return Math.max(0L, this.mPositionMs + (this.mPlaybackSpeed * ((float) (this.mInstance.mTimeDiff != null ? this.mInstance.mTimeDiff.longValue() : SystemClock.elapsedRealtime() - this.mPositionEventTimeMs))));
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public float getPlaybackSpeed() {
        synchronized (this.mLock) {
            if (this.mISession2 == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return 0.0f;
            }
            return this.mPlaybackSpeed;
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void setPlaybackSpeed(float f) {
        synchronized (this.mLock) {
            IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(39);
            if (sessionInterfaceIfAble != null) {
                try {
                    sessionInterfaceIfAble.setPlaybackSpeed(this.mControllerStub, f);
                } catch (RemoteException e) {
                    Log.w(TAG, "Cannot connect to the service or the session is gone", e);
                }
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public int getBufferingState() {
        synchronized (this.mLock) {
            if (this.mISession2 == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return 0;
            }
            return this.mBufferingState;
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public long getBufferedPosition() {
        synchronized (this.mLock) {
            if (this.mISession2 == null) {
                Log.w(TAG, "Session isn't active", new IllegalStateException());
                return -1L;
            }
            return this.mBufferedPositionMs;
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public MediaController2.PlaybackInfo getPlaybackInfo() {
        MediaController2.PlaybackInfo playbackInfo;
        synchronized (this.mLock) {
            playbackInfo = this.mPlaybackInfo;
        }
        return playbackInfo;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void setRating(@NonNull String str, @NonNull Rating2 rating2) {
        IMediaSession2 iMediaSession2;
        synchronized (this.mLock) {
            iMediaSession2 = this.mISession2;
        }
        if (iMediaSession2 != null) {
            try {
                iMediaSession2.setRating(this.mControllerStub, str, rating2.toBundle());
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void sendCustomCommand(@NonNull SessionCommand2 sessionCommand2, Bundle bundle, @Nullable ResultReceiver resultReceiver) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(sessionCommand2);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.sendCustomCommand(this.mControllerStub, sessionCommand2.toBundle(), bundle, resultReceiver);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public List<MediaItem2> getPlaylist() {
        List<MediaItem2> list;
        synchronized (this.mLock) {
            list = this.mPlaylist;
        }
        return list;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void setPlaylist(@NonNull List<MediaItem2> list, @Nullable MediaMetadata2 mediaMetadata2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(19);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.setPlaylist(this.mControllerStub, MediaUtils2.convertMediaItem2ListToBundleList(list), mediaMetadata2 == null ? null : mediaMetadata2.toBundle());
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void updatePlaylistMetadata(@Nullable MediaMetadata2 mediaMetadata2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(21);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.updatePlaylistMetadata(this.mControllerStub, mediaMetadata2 == null ? null : mediaMetadata2.toBundle());
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public MediaMetadata2 getPlaylistMetadata() {
        MediaMetadata2 mediaMetadata2;
        synchronized (this.mLock) {
            mediaMetadata2 = this.mPlaylistMetadata;
        }
        return mediaMetadata2;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void addPlaylistItem(int i, @NonNull MediaItem2 mediaItem2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(15);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.addPlaylistItem(this.mControllerStub, i, mediaItem2.toBundle());
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void removePlaylistItem(@NonNull MediaItem2 mediaItem2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(16);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.removePlaylistItem(this.mControllerStub, mediaItem2.toBundle());
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void replacePlaylistItem(int i, @NonNull MediaItem2 mediaItem2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(17);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.replacePlaylistItem(this.mControllerStub, i, mediaItem2.toBundle());
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public MediaItem2 getCurrentMediaItem() {
        MediaItem2 mediaItem2;
        synchronized (this.mLock) {
            mediaItem2 = this.mCurrentMediaItem;
        }
        return mediaItem2;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void skipToPreviousItem() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(5);
        synchronized (this.mLock) {
            if (sessionInterfaceIfAble != null) {
                try {
                    sessionInterfaceIfAble.skipToPreviousItem(this.mControllerStub);
                } catch (RemoteException e) {
                    Log.w(TAG, "Cannot connect to the service or the session is gone", e);
                }
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void skipToNextItem() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(4);
        synchronized (this.mLock) {
            if (sessionInterfaceIfAble != null) {
                try {
                    this.mISession2.skipToNextItem(this.mControllerStub);
                } catch (RemoteException e) {
                    Log.w(TAG, "Cannot connect to the service or the session is gone", e);
                }
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void skipToPlaylistItem(@NonNull MediaItem2 mediaItem2) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(12);
        synchronized (this.mLock) {
            if (sessionInterfaceIfAble != null) {
                try {
                    this.mISession2.skipToPlaylistItem(this.mControllerStub, mediaItem2.toBundle());
                } catch (RemoteException e) {
                    Log.w(TAG, "Cannot connect to the service or the session is gone", e);
                }
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public int getRepeatMode() {
        int i;
        synchronized (this.mLock) {
            i = this.mRepeatMode;
        }
        return i;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void setRepeatMode(int i) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(14);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.setRepeatMode(this.mControllerStub, i);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public int getShuffleMode() {
        int i;
        synchronized (this.mLock) {
            i = this.mShuffleMode;
        }
        return i;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void setShuffleMode(int i) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(13);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.setShuffleMode(this.mControllerStub, i);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void subscribeRoutesInfo() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(36);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.subscribeRoutesInfo(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void unsubscribeRoutesInfo() {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(37);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.unsubscribeRoutesInfo(this.mControllerStub);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    public void selectRoute(@NonNull Bundle bundle) {
        IMediaSession2 sessionInterfaceIfAble = getSessionInterfaceIfAble(38);
        if (sessionInterfaceIfAble != null) {
            try {
                sessionInterfaceIfAble.selectRoute(this.mControllerStub, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Cannot connect to the service or the session is gone", e);
            }
        }
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    @NonNull
    public MediaController2.ControllerCallback getCallback() {
        return this.mCallback;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    @NonNull
    public Executor getCallbackExecutor() {
        return this.mCallbackExecutor;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    @Nullable
    public MediaBrowserCompat getBrowserCompat() {
        return null;
    }

    @Override // android.support.v4.media.MediaController2.SupportLibraryImpl
    @NonNull
    public MediaController2 getInstance() {
        return this.mInstance;
    }

    private void connectToService() {
        Intent intent = new Intent(MediaSessionService2.SERVICE_INTERFACE);
        intent.setClassName(this.mToken.getPackageName(), this.mToken.getServiceName());
        synchronized (this.mLock) {
            if (!this.mContext.bindService(intent, this.mServiceConnection, 1)) {
                Log.w(TAG, "bind to " + this.mToken + " failed");
            } else if (DEBUG) {
                Log.d(TAG, "bind to " + this.mToken + " success");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToSession(IMediaSession2 iMediaSession2) {
        try {
            iMediaSession2.connect(this.mControllerStub, this.mContext.getPackageName());
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to call connection request. Framework will retry automatically");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IMediaSession2 getSessionInterfaceIfAble(int i) {
        synchronized (this.mLock) {
            if (!this.mAllowedCommands.hasCommand(i)) {
                Log.w(TAG, "Controller isn't allowed to call command, commandCode=" + i);
                return null;
            }
            return this.mISession2;
        }
    }

    IMediaSession2 getSessionInterfaceIfAble(SessionCommand2 sessionCommand2) {
        synchronized (this.mLock) {
            if (!this.mAllowedCommands.hasCommand(sessionCommand2)) {
                Log.w(TAG, "Controller isn't allowed to call command, command=" + sessionCommand2);
                return null;
            }
            return this.mISession2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyCurrentMediaItemChanged(final MediaItem2 mediaItem2) {
        synchronized (this.mLock) {
            this.mCurrentMediaItem = mediaItem2;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.3
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onCurrentMediaItemChanged(MediaController2ImplBase.this.mInstance, mediaItem2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyPlayerStateChanges(long j, long j2, final int i) {
        synchronized (this.mLock) {
            this.mPositionEventTimeMs = j;
            this.mPositionMs = j2;
            this.mPlayerState = i;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.4
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onPlayerStateChanged(MediaController2ImplBase.this.mInstance, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyPlaybackSpeedChanges(long j, long j2, final float f) {
        synchronized (this.mLock) {
            this.mPositionEventTimeMs = j;
            this.mPositionMs = j2;
            this.mPlaybackSpeed = f;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.5
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onPlaybackSpeedChanged(MediaController2ImplBase.this.mInstance, f);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyBufferingStateChanged(final MediaItem2 mediaItem2, final int i, long j) {
        synchronized (this.mLock) {
            this.mBufferingState = i;
            this.mBufferedPositionMs = j;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.6
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onBufferingStateChanged(MediaController2ImplBase.this.mInstance, mediaItem2, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyPlaylistChanges(final List<MediaItem2> list, final MediaMetadata2 mediaMetadata2) {
        synchronized (this.mLock) {
            this.mPlaylist = list;
            this.mPlaylistMetadata = mediaMetadata2;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.7
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onPlaylistChanged(MediaController2ImplBase.this.mInstance, list, mediaMetadata2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyPlaylistMetadataChanges(final MediaMetadata2 mediaMetadata2) {
        synchronized (this.mLock) {
            this.mPlaylistMetadata = mediaMetadata2;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.8
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onPlaylistMetadataChanged(MediaController2ImplBase.this.mInstance, mediaMetadata2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyPlaybackInfoChanges(final MediaController2.PlaybackInfo playbackInfo) {
        synchronized (this.mLock) {
            this.mPlaybackInfo = playbackInfo;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.9
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onPlaybackInfoChanged(MediaController2ImplBase.this.mInstance, playbackInfo);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyRepeatModeChanges(final int i) {
        synchronized (this.mLock) {
            this.mRepeatMode = i;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.10
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onRepeatModeChanged(MediaController2ImplBase.this.mInstance, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyShuffleModeChanges(final int i) {
        synchronized (this.mLock) {
            this.mShuffleMode = i;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.11
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onShuffleModeChanged(MediaController2ImplBase.this.mInstance, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifySeekCompleted(long j, long j2, final long j3) {
        synchronized (this.mLock) {
            this.mPositionEventTimeMs = j;
            this.mPositionMs = j2;
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.12
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onSeekCompleted(MediaController2ImplBase.this.mInstance, j3);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyError(final int i, final Bundle bundle) {
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.13
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onError(MediaController2ImplBase.this.mInstance, i, bundle);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyRoutesInfoChanged(final List<Bundle> list) {
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.14
            @Override // java.lang.Runnable
            public void run() {
                if (MediaController2ImplBase.this.mInstance.isConnected()) {
                    MediaController2ImplBase.this.mCallback.onRoutesInfoChanged(MediaController2ImplBase.this.mInstance, list);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onConnectedNotLocked(IMediaSession2 iMediaSession2, final SessionCommandGroup2 sessionCommandGroup2, int i, MediaItem2 mediaItem2, long j, long j2, float f, long j3, MediaController2.PlaybackInfo playbackInfo, int i2, int i3, List<MediaItem2> list, PendingIntent pendingIntent) {
        if (DEBUG) {
            Log.d(TAG, "onConnectedNotLocked sessionBinder=" + iMediaSession2 + ", allowedCommands=" + sessionCommandGroup2);
        }
        if (iMediaSession2 == null || sessionCommandGroup2 == null) {
            this.mInstance.close();
            return;
        }
        try {
            synchronized (this.mLock) {
                try {
                    if (this.mIsReleased) {
                        return;
                    }
                    try {
                        if (this.mISession2 != null) {
                            Log.e(TAG, "Cannot be notified about the connection result many times. Probably a bug or malicious app.");
                            this.mInstance.close();
                            return;
                        }
                        this.mAllowedCommands = sessionCommandGroup2;
                        this.mPlayerState = i;
                        this.mCurrentMediaItem = mediaItem2;
                        this.mPositionEventTimeMs = j;
                        this.mPositionMs = j2;
                        this.mPlaybackSpeed = f;
                        this.mBufferedPositionMs = j3;
                        this.mPlaybackInfo = playbackInfo;
                        this.mRepeatMode = i2;
                        this.mShuffleMode = i3;
                        this.mPlaylist = list;
                        this.mSessionActivity = pendingIntent;
                        this.mISession2 = iMediaSession2;
                        try {
                            this.mISession2.asBinder().linkToDeath(this.mDeathRecipient, 0);
                            this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.15
                                @Override // java.lang.Runnable
                                public void run() {
                                    MediaController2ImplBase.this.mCallback.onConnected(MediaController2ImplBase.this.mInstance, sessionCommandGroup2);
                                }
                            });
                        } catch (RemoteException e) {
                            if (DEBUG) {
                                Log.d(TAG, "Session died too early.", e);
                            }
                            this.mInstance.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            if (0 != 0) {
                this.mInstance.close();
            }
            throw th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCustomCommand(final SessionCommand2 sessionCommand2, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (DEBUG) {
            Log.d(TAG, "onCustomCommand cmd=" + sessionCommand2);
        }
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.16
            @Override // java.lang.Runnable
            public void run() {
                MediaController2ImplBase.this.mCallback.onCustomCommand(MediaController2ImplBase.this.mInstance, sessionCommand2, bundle, resultReceiver);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onAllowedCommandsChanged(final SessionCommandGroup2 sessionCommandGroup2) {
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.17
            @Override // java.lang.Runnable
            public void run() {
                MediaController2ImplBase.this.mCallback.onAllowedCommandsChanged(MediaController2ImplBase.this.mInstance, sessionCommandGroup2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCustomLayoutChanged(final List<MediaSession2.CommandButton> list) {
        this.mCallbackExecutor.execute(new Runnable() { // from class: android.support.v4.media.MediaController2ImplBase.18
            @Override // java.lang.Runnable
            public void run() {
                MediaController2ImplBase.this.mCallback.onCustomLayoutChanged(MediaController2ImplBase.this.mInstance, list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public class SessionServiceConnection implements ServiceConnection {
        private SessionServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (MediaController2ImplBase.DEBUG) {
                Log.d(MediaController2ImplBase.TAG, "onServiceConnected " + componentName + " " + this);
            }
            if (!MediaController2ImplBase.this.mToken.getPackageName().equals(componentName.getPackageName())) {
                Log.wtf(MediaController2ImplBase.TAG, componentName + " was connected, but expected pkg=" + MediaController2ImplBase.this.mToken.getPackageName() + " with id=" + MediaController2ImplBase.this.mToken.getId());
                return;
            }
            MediaController2ImplBase.this.connectToSession(IMediaSession2.Stub.asInterface(iBinder));
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (MediaController2ImplBase.DEBUG) {
                Log.w(MediaController2ImplBase.TAG, "Session service " + componentName + " is disconnected.");
            }
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(ComponentName componentName) {
            MediaController2ImplBase.this.close();
        }
    }
}
