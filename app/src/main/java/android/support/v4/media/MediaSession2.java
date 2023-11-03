package android.support.v4.media;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.mediacompat.Rating2;
import android.support.v4.media.MediaController2;
import android.support.v4.media.MediaInterface2;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

@TargetApi(19)
/* loaded from: classes7.dex */
public class MediaSession2 implements MediaInterface2.SessionPlayer, AutoCloseable {
    public static final int ERROR_CODE_ACTION_ABORTED = 10;
    public static final int ERROR_CODE_APP_ERROR = 1;
    public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
    public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
    public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
    public static final int ERROR_CODE_END_OF_QUEUE = 11;
    public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
    public static final int ERROR_CODE_NOT_SUPPORTED = 2;
    public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
    public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
    public static final int ERROR_CODE_SETUP_REQUIRED = 12;
    public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
    public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
    static final String TAG = "MediaSession2";
    private final SupportLibraryImpl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes7.dex */
    public @interface ErrorCode {
    }

    /* loaded from: classes7.dex */
    public interface OnDataSourceMissingHelper {
        @Nullable
        DataSourceDesc onDataSourceMissing(@NonNull MediaSession2 mediaSession2, @NonNull MediaItem2 mediaItem2);
    }

    /* loaded from: classes7.dex */
    interface SupportLibraryImpl extends MediaInterface2.SessionPlayer, AutoCloseable {
        AudioFocusHandler getAudioFocusHandler();

        SessionCallback getCallback();

        Executor getCallbackExecutor();

        @NonNull
        List<ControllerInfo> getConnectedControllers();

        Context getContext();

        MediaSession2 getInstance();

        MediaController2.PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackStateCompat();

        @NonNull
        BaseMediaPlayer getPlayer();

        @NonNull
        MediaPlaylistAgent getPlaylistAgent();

        PendingIntent getSessionActivity();

        IBinder getSessionBinder();

        MediaSessionCompat getSessionCompat();

        @NonNull
        SessionToken2 getToken();

        @Nullable
        VolumeProviderCompat getVolumeProvider();

        boolean isClosed();

        void notifyRoutesInfoChanged(@NonNull ControllerInfo controllerInfo, @Nullable List<Bundle> list);

        void sendCustomCommand(@NonNull ControllerInfo controllerInfo, @NonNull SessionCommand2 sessionCommand2, @Nullable Bundle bundle, @Nullable ResultReceiver resultReceiver);

        void sendCustomCommand(@NonNull SessionCommand2 sessionCommand2, @Nullable Bundle bundle);

        void setAllowedCommands(@NonNull ControllerInfo controllerInfo, @NonNull SessionCommandGroup2 sessionCommandGroup2);

        void setCustomLayout(@NonNull ControllerInfo controllerInfo, @NonNull List<CommandButton> list);

        void updatePlayer(@NonNull BaseMediaPlayer baseMediaPlayer, @Nullable MediaPlaylistAgent mediaPlaylistAgent, @Nullable VolumeProviderCompat volumeProviderCompat);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaSession2(Context context, String str, BaseMediaPlayer baseMediaPlayer, MediaPlaylistAgent mediaPlaylistAgent, VolumeProviderCompat volumeProviderCompat, PendingIntent pendingIntent, Executor executor, SessionCallback sessionCallback) {
        this.mImpl = createImpl(context, str, baseMediaPlayer, mediaPlaylistAgent, volumeProviderCompat, pendingIntent, executor, sessionCallback);
    }

    SupportLibraryImpl createImpl(Context context, String str, BaseMediaPlayer baseMediaPlayer, MediaPlaylistAgent mediaPlaylistAgent, VolumeProviderCompat volumeProviderCompat, PendingIntent pendingIntent, Executor executor, SessionCallback sessionCallback) {
        return new MediaSession2ImplBase(this, context, str, baseMediaPlayer, mediaPlaylistAgent, volumeProviderCompat, pendingIntent, executor, sessionCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SupportLibraryImpl getImpl() {
        return this.mImpl;
    }

    public void updatePlayer(@NonNull BaseMediaPlayer baseMediaPlayer, @Nullable MediaPlaylistAgent mediaPlaylistAgent, @Nullable VolumeProviderCompat volumeProviderCompat) {
        this.mImpl.updatePlayer(baseMediaPlayer, mediaPlaylistAgent, volumeProviderCompat);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        try {
            this.mImpl.close();
        } catch (Exception e) {
        }
    }

    @NonNull
    public BaseMediaPlayer getPlayer() {
        return this.mImpl.getPlayer();
    }

    @NonNull
    public MediaPlaylistAgent getPlaylistAgent() {
        return this.mImpl.getPlaylistAgent();
    }

    @Nullable
    public VolumeProviderCompat getVolumeProvider() {
        return this.mImpl.getVolumeProvider();
    }

    @NonNull
    public SessionToken2 getToken() {
        return this.mImpl.getToken();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Context getContext() {
        return this.mImpl.getContext();
    }

    @NonNull
    Executor getCallbackExecutor() {
        return this.mImpl.getCallbackExecutor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public SessionCallback getCallback() {
        return this.mImpl.getCallback();
    }

    @NonNull
    AudioFocusHandler getAudioFocusHandler() {
        return this.mImpl.getAudioFocusHandler();
    }

    @NonNull
    public List<ControllerInfo> getConnectedControllers() {
        return this.mImpl.getConnectedControllers();
    }

    public void setCustomLayout(@NonNull ControllerInfo controllerInfo, @NonNull List<CommandButton> list) {
        this.mImpl.setCustomLayout(controllerInfo, list);
    }

    public void setAllowedCommands(@NonNull ControllerInfo controllerInfo, @NonNull SessionCommandGroup2 sessionCommandGroup2) {
        this.mImpl.setAllowedCommands(controllerInfo, sessionCommandGroup2);
    }

    public void sendCustomCommand(@NonNull SessionCommand2 sessionCommand2, @Nullable Bundle bundle) {
        this.mImpl.sendCustomCommand(sessionCommand2, bundle);
    }

    public void sendCustomCommand(@NonNull ControllerInfo controllerInfo, @NonNull SessionCommand2 sessionCommand2, @Nullable Bundle bundle, @Nullable ResultReceiver resultReceiver) {
        this.mImpl.sendCustomCommand(controllerInfo, sessionCommand2, bundle, resultReceiver);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public void play() {
        this.mImpl.play();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public void pause() {
        this.mImpl.pause();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public void reset() {
        this.mImpl.reset();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public void prepare() {
        this.mImpl.prepare();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public void seekTo(long j) {
        this.mImpl.seekTo(j);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlayer
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void skipForward() {
        this.mImpl.skipForward();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlayer
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void skipBackward() {
        this.mImpl.skipBackward();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlayer
    public void notifyError(int i, @Nullable Bundle bundle) {
        this.mImpl.notifyError(i, bundle);
    }

    public void notifyRoutesInfoChanged(@NonNull ControllerInfo controllerInfo, @Nullable List<Bundle> list) {
        this.mImpl.notifyRoutesInfoChanged(controllerInfo, list);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public int getPlayerState() {
        return this.mImpl.getPlayerState();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public long getCurrentPosition() {
        return this.mImpl.getCurrentPosition();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public long getDuration() {
        return this.mImpl.getDuration();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public long getBufferedPosition() {
        return this.mImpl.getBufferedPosition();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public int getBufferingState() {
        return this.mImpl.getBufferingState();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public float getPlaybackSpeed() {
        return this.mImpl.getPlaybackSpeed();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaybackControl
    public void setPlaybackSpeed(float f) {
        this.mImpl.setPlaybackSpeed(f);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void setOnDataSourceMissingHelper(@NonNull OnDataSourceMissingHelper onDataSourceMissingHelper) {
        this.mImpl.setOnDataSourceMissingHelper(onDataSourceMissingHelper);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void clearOnDataSourceMissingHelper() {
        this.mImpl.clearOnDataSourceMissingHelper();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public List<MediaItem2> getPlaylist() {
        return this.mImpl.getPlaylist();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void setPlaylist(@NonNull List<MediaItem2> list, @Nullable MediaMetadata2 mediaMetadata2) {
        this.mImpl.setPlaylist(list, mediaMetadata2);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void skipToPlaylistItem(@NonNull MediaItem2 mediaItem2) {
        this.mImpl.skipToPlaylistItem(mediaItem2);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void skipToPreviousItem() {
        this.mImpl.skipToPreviousItem();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void skipToNextItem() {
        this.mImpl.skipToNextItem();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public MediaMetadata2 getPlaylistMetadata() {
        return this.mImpl.getPlaylistMetadata();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void addPlaylistItem(int i, @NonNull MediaItem2 mediaItem2) {
        this.mImpl.addPlaylistItem(i, mediaItem2);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void removePlaylistItem(@NonNull MediaItem2 mediaItem2) {
        this.mImpl.removePlaylistItem(mediaItem2);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void replacePlaylistItem(int i, @NonNull MediaItem2 mediaItem2) {
        this.mImpl.replacePlaylistItem(i, mediaItem2);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public MediaItem2 getCurrentMediaItem() {
        return this.mImpl.getCurrentMediaItem();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void updatePlaylistMetadata(@Nullable MediaMetadata2 mediaMetadata2) {
        this.mImpl.updatePlaylistMetadata(mediaMetadata2);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void setRepeatMode(int i) {
        this.mImpl.setRepeatMode(i);
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    @Override // android.support.v4.media.MediaInterface2.SessionPlaylistControl
    public void setShuffleMode(int i) {
        this.mImpl.setShuffleMode(i);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MediaSessionCompat getSessionCompat() {
        return this.mImpl.getSessionCompat();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IBinder getSessionBinder() {
        return this.mImpl.getSessionBinder();
    }

    /* loaded from: classes7.dex */
    public static abstract class SessionCallback {
        @Nullable
        public SessionCommandGroup2 onConnect(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo) {
            SessionCommandGroup2 sessionCommandGroup2 = new SessionCommandGroup2();
            sessionCommandGroup2.addAllPredefinedCommands();
            return sessionCommandGroup2;
        }

        public void onDisconnected(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo) {
        }

        public boolean onCommandRequest(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull SessionCommand2 sessionCommand2) {
            return true;
        }

        public void onSetRating(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull String str, @NonNull Rating2 rating2) {
        }

        public void onCustomCommand(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull SessionCommand2 sessionCommand2, @Nullable Bundle bundle, @Nullable ResultReceiver resultReceiver) {
        }

        public void onPlayFromMediaId(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle) {
        }

        public void onPlayFromSearch(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle) {
        }

        public void onPlayFromUri(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull Uri uri, @Nullable Bundle bundle) {
        }

        public void onPrepareFromMediaId(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle) {
        }

        public void onPrepareFromSearch(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle) {
        }

        public void onPrepareFromUri(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull Uri uri, @Nullable Bundle bundle) {
        }

        public void onFastForward(@NonNull MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
        }

        public void onRewind(@NonNull MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
        }

        public void onSubscribeRoutesInfo(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo) {
        }

        public void onUnsubscribeRoutesInfo(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo) {
        }

        public void onSelectRoute(@NonNull MediaSession2 mediaSession2, @NonNull ControllerInfo controllerInfo, @NonNull Bundle bundle) {
        }

        public void onCurrentMediaItemChanged(@NonNull MediaSession2 mediaSession2, @NonNull BaseMediaPlayer baseMediaPlayer, @Nullable MediaItem2 mediaItem2) {
        }

        public void onMediaPrepared(@NonNull MediaSession2 mediaSession2, @NonNull BaseMediaPlayer baseMediaPlayer, @NonNull MediaItem2 mediaItem2) {
        }

        public void onPlayerStateChanged(@NonNull MediaSession2 mediaSession2, @NonNull BaseMediaPlayer baseMediaPlayer, int i) {
        }

        public void onBufferingStateChanged(@NonNull MediaSession2 mediaSession2, @NonNull BaseMediaPlayer baseMediaPlayer, @NonNull MediaItem2 mediaItem2, int i) {
        }

        public void onPlaybackSpeedChanged(@NonNull MediaSession2 mediaSession2, @NonNull BaseMediaPlayer baseMediaPlayer, float f) {
        }

        public void onSeekCompleted(@NonNull MediaSession2 mediaSession2, @NonNull BaseMediaPlayer baseMediaPlayer, long j) {
        }

        public void onPlaylistChanged(@NonNull MediaSession2 mediaSession2, @NonNull MediaPlaylistAgent mediaPlaylistAgent, @NonNull List<MediaItem2> list, @Nullable MediaMetadata2 mediaMetadata2) {
        }

        public void onPlaylistMetadataChanged(@NonNull MediaSession2 mediaSession2, @NonNull MediaPlaylistAgent mediaPlaylistAgent, @Nullable MediaMetadata2 mediaMetadata2) {
        }

        public void onShuffleModeChanged(@NonNull MediaSession2 mediaSession2, @NonNull MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }

        public void onRepeatModeChanged(@NonNull MediaSession2 mediaSession2, @NonNull MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }
    }

    /* loaded from: classes7.dex */
    public static final class Builder extends BuilderBase<MediaSession2, Builder, SessionCallback> {
        public Builder(Context context) {
            super(context);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.support.v4.media.MediaSession2.BuilderBase
        @NonNull
        public Builder setPlayer(@NonNull BaseMediaPlayer baseMediaPlayer) {
            return (Builder) super.setPlayer(baseMediaPlayer);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.support.v4.media.MediaSession2.BuilderBase
        @NonNull
        public Builder setPlaylistAgent(@NonNull MediaPlaylistAgent mediaPlaylistAgent) {
            return (Builder) super.setPlaylistAgent(mediaPlaylistAgent);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.support.v4.media.MediaSession2.BuilderBase
        @NonNull
        public Builder setVolumeProvider(@Nullable VolumeProviderCompat volumeProviderCompat) {
            return (Builder) super.setVolumeProvider(volumeProviderCompat);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.support.v4.media.MediaSession2.BuilderBase
        @NonNull
        public Builder setSessionActivity(@Nullable PendingIntent pendingIntent) {
            return (Builder) super.setSessionActivity(pendingIntent);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.support.v4.media.MediaSession2.BuilderBase
        @NonNull
        public Builder setId(@NonNull String str) {
            return (Builder) super.setId(str);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.support.v4.media.MediaSession2.BuilderBase
        @NonNull
        public Builder setSessionCallback(@NonNull Executor executor, @NonNull SessionCallback sessionCallback) {
            return (Builder) super.setSessionCallback(executor, (Executor) sessionCallback);
        }

        /* JADX WARN: Type inference failed for: r0v3, types: [android.support.v4.media.MediaSession2$Builder$1, C extends android.support.v4.media.MediaSession2$SessionCallback] */
        @Override // android.support.v4.media.MediaSession2.BuilderBase
        @NonNull
        public MediaSession2 build() {
            if (this.mCallbackExecutor == null) {
                this.mCallbackExecutor = new MainHandlerExecutor(this.mContext);
            }
            if (this.mCallback == 0) {
                this.mCallback = new SessionCallback() { // from class: android.support.v4.media.MediaSession2.Builder.1
                };
            }
            return new MediaSession2(this.mContext, this.mId, this.mPlayer, this.mPlaylistAgent, this.mVolumeProvider, this.mSessionActivity, this.mCallbackExecutor, this.mCallback);
        }
    }

    /* loaded from: classes7.dex */
    public static final class ControllerInfo {
        private final ControllerCb mControllerCb;
        private final boolean mIsTrusted = false;
        private final String mPackageName;
        private final int mUid;

        /* JADX INFO: Access modifiers changed from: package-private */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ControllerInfo(@NonNull String str, int i, int i2, @NonNull ControllerCb controllerCb) {
            this.mUid = i2;
            this.mPackageName = str;
            this.mControllerCb = controllerCb;
        }

        @NonNull
        public String getPackageName() {
            return this.mPackageName;
        }

        public int getUid() {
            return this.mUid;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public boolean isTrusted() {
            return this.mIsTrusted;
        }

        public int hashCode() {
            return this.mControllerCb.hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ControllerInfo)) {
                return false;
            }
            return this.mControllerCb.equals(((ControllerInfo) obj).mControllerCb);
        }

        public String toString() {
            return "ControllerInfo {pkg=" + this.mPackageName + ", uid=" + this.mUid + "})";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public IBinder getId() {
            return this.mControllerCb.getId();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public ControllerCb getControllerCb() {
            return this.mControllerCb;
        }
    }

    /* loaded from: classes7.dex */
    public static final class CommandButton {
        private static final String KEY_COMMAND = "android.media.media_session2.command_button.command";
        private static final String KEY_DISPLAY_NAME = "android.media.media_session2.command_button.display_name";
        private static final String KEY_ENABLED = "android.media.media_session2.command_button.enabled";
        private static final String KEY_EXTRAS = "android.media.media_session2.command_button.extras";
        private static final String KEY_ICON_RES_ID = "android.media.media_session2.command_button.icon_res_id";
        private SessionCommand2 mCommand;
        private String mDisplayName;
        private boolean mEnabled;
        private Bundle mExtras;
        private int mIconResId;

        private CommandButton(@Nullable SessionCommand2 sessionCommand2, int i, @Nullable String str, Bundle bundle, boolean z) {
            this.mCommand = sessionCommand2;
            this.mIconResId = i;
            this.mDisplayName = str;
            this.mExtras = bundle;
            this.mEnabled = z;
        }

        @Nullable
        public SessionCommand2 getCommand() {
            return this.mCommand;
        }

        public int getIconResId() {
            return this.mIconResId;
        }

        @Nullable
        public String getDisplayName() {
            return this.mDisplayName;
        }

        @Nullable
        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle(KEY_COMMAND, this.mCommand.toBundle());
            bundle.putInt(KEY_ICON_RES_ID, this.mIconResId);
            bundle.putString(KEY_DISPLAY_NAME, this.mDisplayName);
            bundle.putBundle(KEY_EXTRAS, this.mExtras);
            bundle.putBoolean(KEY_ENABLED, this.mEnabled);
            return bundle;
        }

        @Nullable
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static CommandButton fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            Builder builder = new Builder();
            builder.setCommand(SessionCommand2.fromBundle(bundle.getBundle(KEY_COMMAND)));
            builder.setIconResId(bundle.getInt(KEY_ICON_RES_ID, 0));
            builder.setDisplayName(bundle.getString(KEY_DISPLAY_NAME));
            builder.setExtras(bundle.getBundle(KEY_EXTRAS));
            builder.setEnabled(bundle.getBoolean(KEY_ENABLED));
            try {
                return builder.build();
            } catch (IllegalStateException e) {
                return null;
            }
        }

        /* loaded from: classes7.dex */
        public static final class Builder {
            private SessionCommand2 mCommand;
            private String mDisplayName;
            private boolean mEnabled;
            private Bundle mExtras;
            private int mIconResId;

            @NonNull
            public Builder setCommand(@Nullable SessionCommand2 sessionCommand2) {
                this.mCommand = sessionCommand2;
                return this;
            }

            @NonNull
            public Builder setIconResId(int i) {
                this.mIconResId = i;
                return this;
            }

            @NonNull
            public Builder setDisplayName(@Nullable String str) {
                this.mDisplayName = str;
                return this;
            }

            @NonNull
            public Builder setEnabled(boolean z) {
                this.mEnabled = z;
                return this;
            }

            @NonNull
            public Builder setExtras(@Nullable Bundle bundle) {
                this.mExtras = bundle;
                return this;
            }

            @NonNull
            public CommandButton build() {
                return new CommandButton(this.mCommand, this.mIconResId, this.mDisplayName, this.mExtras, this.mEnabled);
            }
        }
    }

    /* loaded from: classes7.dex */
    static abstract class ControllerCb {
        @NonNull
        abstract IBinder getId();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onAllowedCommandsChanged(@NonNull SessionCommandGroup2 sessionCommandGroup2) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onBufferingStateChanged(@NonNull MediaItem2 mediaItem2, int i, long j) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onChildrenChanged(@NonNull String str, int i, @Nullable Bundle bundle) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onCurrentMediaItemChanged(@Nullable MediaItem2 mediaItem2) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onCustomCommand(@NonNull SessionCommand2 sessionCommand2, @Nullable Bundle bundle, @Nullable ResultReceiver resultReceiver) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onCustomLayoutChanged(@NonNull List<CommandButton> list) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onDisconnected() throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onError(int i, @Nullable Bundle bundle) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onGetChildrenDone(@NonNull String str, int i, int i2, @Nullable List<MediaItem2> list, @Nullable Bundle bundle) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onGetItemDone(@NonNull String str, @Nullable MediaItem2 mediaItem2) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onGetLibraryRootDone(@Nullable Bundle bundle, @Nullable String str, @Nullable Bundle bundle2) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onGetSearchResultDone(@NonNull String str, int i, int i2, @Nullable List<MediaItem2> list, @Nullable Bundle bundle) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onPlaybackInfoChanged(@NonNull MediaController2.PlaybackInfo playbackInfo) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onPlayerStateChanged(long j, long j2, int i) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onPlaylistChanged(@NonNull List<MediaItem2> list, @Nullable MediaMetadata2 mediaMetadata2) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onPlaylistMetadataChanged(@Nullable MediaMetadata2 mediaMetadata2) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onRepeatModeChanged(int i) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onRoutesInfoChanged(@Nullable List<Bundle> list) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onSearchResultChanged(@NonNull String str, int i, @Nullable Bundle bundle) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onSeekCompleted(long j, long j2, long j3) throws RemoteException;

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract void onShuffleModeChanged(int i) throws RemoteException;

        public int hashCode() {
            return getId().hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ControllerCb)) {
                return false;
            }
            return getId().equals(((ControllerCb) obj).getId());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes7.dex */
    public static abstract class BuilderBase<T extends MediaSession2, U extends BuilderBase<T, U, C>, C extends SessionCallback> {
        C mCallback;
        Executor mCallbackExecutor;
        final Context mContext;
        String mId;
        BaseMediaPlayer mPlayer;
        MediaPlaylistAgent mPlaylistAgent;
        PendingIntent mSessionActivity;
        VolumeProviderCompat mVolumeProvider;

        @NonNull
        abstract T build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public BuilderBase(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("context shouldn't be null");
            }
            this.mContext = context;
            this.mId = MediaSession2.TAG;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public U setPlayer(@NonNull BaseMediaPlayer baseMediaPlayer) {
            if (baseMediaPlayer == null) {
                throw new IllegalArgumentException("player shouldn't be null");
            }
            this.mPlayer = baseMediaPlayer;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public U setPlaylistAgent(@NonNull MediaPlaylistAgent mediaPlaylistAgent) {
            if (mediaPlaylistAgent == null) {
                throw new IllegalArgumentException("playlistAgent shouldn't be null");
            }
            this.mPlaylistAgent = mediaPlaylistAgent;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public U setVolumeProvider(@Nullable VolumeProviderCompat volumeProviderCompat) {
            this.mVolumeProvider = volumeProviderCompat;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public U setSessionActivity(@Nullable PendingIntent pendingIntent) {
            this.mSessionActivity = pendingIntent;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public U setId(@NonNull String str) {
            if (str == null) {
                throw new IllegalArgumentException("id shouldn't be null");
            }
            this.mId = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NonNull
        public U setSessionCallback(@NonNull Executor executor, @NonNull C c) {
            if (executor == null) {
                throw new IllegalArgumentException("executor shouldn't be null");
            }
            if (c == null) {
                throw new IllegalArgumentException("callback shouldn't be null");
            }
            this.mCallbackExecutor = executor;
            this.mCallback = c;
            return this;
        }
    }

    /* loaded from: classes7.dex */
    static class MainHandlerExecutor implements Executor {
        private final Handler mHandler;

        /* JADX INFO: Access modifiers changed from: package-private */
        public MainHandlerExecutor(Context context) {
            this.mHandler = new Handler(context.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            if (!this.mHandler.post(runnable)) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }
}
