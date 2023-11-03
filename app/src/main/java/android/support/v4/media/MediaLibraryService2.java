package android.support.v4.media;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaSession2;
import android.support.v4.media.MediaSessionService2;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public abstract class MediaLibraryService2 extends MediaSessionService2 {
    public static final String SERVICE_INTERFACE = "android.media.MediaLibraryService2";

    @Override // android.support.v4.media.MediaSessionService2
    @NonNull
    public abstract MediaLibrarySession onCreateSession(String str);

    /* loaded from: classes7.dex */
    public static final class MediaLibrarySession extends MediaSession2 {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes7.dex */
        public interface SupportLibraryImpl extends MediaSession2.SupportLibraryImpl {
            @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
            MediaLibrarySessionCallback getCallback();

            @Override // android.support.v4.media.MediaSession2.SupportLibraryImpl
            MediaLibrarySession getInstance();

            IBinder getLegacySessionBinder();

            void notifyChildrenChanged(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, @Nullable Bundle bundle);

            void notifyChildrenChanged(@NonNull String str, int i, @Nullable Bundle bundle);

            void notifySearchResultChanged(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, @Nullable Bundle bundle);

            void onGetChildrenOnExecutor(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, int i2, @Nullable Bundle bundle);

            void onGetItemOnExecutor(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str);

            void onGetLibraryRootOnExecutor(@NonNull MediaSession2.ControllerInfo controllerInfo, @Nullable Bundle bundle);

            void onGetSearchResultOnExecutor(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, int i2, @Nullable Bundle bundle);

            void onSearchOnExecutor(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle);

            void onSubscribeOnExecutor(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle);

            void onUnsubscribeOnExecutor(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str);
        }

        /* loaded from: classes7.dex */
        public static class MediaLibrarySessionCallback extends MediaSession2.SessionCallback {
            @Nullable
            public LibraryRoot onGetLibraryRoot(@NonNull MediaLibrarySession mediaLibrarySession, @NonNull MediaSession2.ControllerInfo controllerInfo, @Nullable Bundle bundle) {
                return null;
            }

            @Nullable
            public MediaItem2 onGetItem(@NonNull MediaLibrarySession mediaLibrarySession, @NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str) {
                return null;
            }

            @Nullable
            public List<MediaItem2> onGetChildren(@NonNull MediaLibrarySession mediaLibrarySession, @NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, int i2, @Nullable Bundle bundle) {
                return null;
            }

            public void onSubscribe(@NonNull MediaLibrarySession mediaLibrarySession, @NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle) {
            }

            public void onUnsubscribe(@NonNull MediaLibrarySession mediaLibrarySession, @NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str) {
            }

            public void onSearch(@NonNull MediaLibrarySession mediaLibrarySession, @NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, @Nullable Bundle bundle) {
            }

            @Nullable
            public List<MediaItem2> onGetSearchResult(@NonNull MediaLibrarySession mediaLibrarySession, @NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, int i2, @Nullable Bundle bundle) {
                return null;
            }
        }

        /* loaded from: classes7.dex */
        public static final class Builder extends MediaSession2.BuilderBase<MediaLibrarySession, Builder, MediaLibrarySessionCallback> {
            public Builder(@NonNull MediaLibraryService2 mediaLibraryService2, @NonNull Executor executor, @NonNull MediaLibrarySessionCallback mediaLibrarySessionCallback) {
                super(mediaLibraryService2);
                setSessionCallback(executor, mediaLibrarySessionCallback);
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
            public MediaLibrarySession build() {
                if (this.mCallbackExecutor == null) {
                    this.mCallbackExecutor = new MediaSession2.MainHandlerExecutor(this.mContext);
                }
                if (this.mCallback == 0) {
                    this.mCallback = new MediaLibrarySessionCallback() { // from class: android.support.v4.media.MediaLibraryService2.MediaLibrarySession.Builder.1
                    };
                }
                return new MediaLibrarySession(this.mContext, this.mId, this.mPlayer, this.mPlaylistAgent, this.mVolumeProvider, this.mSessionActivity, this.mCallbackExecutor, this.mCallback);
            }
        }

        MediaLibrarySession(Context context, String str, BaseMediaPlayer baseMediaPlayer, MediaPlaylistAgent mediaPlaylistAgent, VolumeProviderCompat volumeProviderCompat, PendingIntent pendingIntent, Executor executor, MediaSession2.SessionCallback sessionCallback) {
            super(context, str, baseMediaPlayer, mediaPlaylistAgent, volumeProviderCompat, pendingIntent, executor, sessionCallback);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2
        public SupportLibraryImpl createImpl(Context context, String str, BaseMediaPlayer baseMediaPlayer, MediaPlaylistAgent mediaPlaylistAgent, VolumeProviderCompat volumeProviderCompat, PendingIntent pendingIntent, Executor executor, MediaSession2.SessionCallback sessionCallback) {
            return new MediaLibrarySessionImplBase(this, context, str, baseMediaPlayer, mediaPlaylistAgent, volumeProviderCompat, pendingIntent, executor, sessionCallback);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2
        public SupportLibraryImpl getImpl() {
            return (SupportLibraryImpl) super.getImpl();
        }

        public void notifyChildrenChanged(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, @Nullable Bundle bundle) {
            getImpl().notifyChildrenChanged(controllerInfo, str, i, bundle);
        }

        public void notifyChildrenChanged(@NonNull String str, int i, @Nullable Bundle bundle) {
            getImpl().notifyChildrenChanged(str, i, bundle);
        }

        public void notifySearchResultChanged(@NonNull MediaSession2.ControllerInfo controllerInfo, @NonNull String str, int i, @Nullable Bundle bundle) {
            getImpl().notifySearchResultChanged(controllerInfo, str, i, bundle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // android.support.v4.media.MediaSession2
        public MediaLibrarySessionCallback getCallback() {
            return (MediaLibrarySessionCallback) super.getCallback();
        }
    }

    @Override // android.support.v4.media.MediaSessionService2
    MediaSessionService2.SupportLibraryImpl createImpl() {
        return new MediaLibraryService2ImplBase();
    }

    @Override // android.support.v4.media.MediaSessionService2, android.app.Service
    public void onCreate() {
        super.onCreate();
        if (!(getSession() instanceof MediaLibrarySession)) {
            throw new RuntimeException("Expected MediaLibrarySession, but returned MediaSession2");
        }
    }

    @Override // android.support.v4.media.MediaSessionService2, android.app.Service
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    /* loaded from: classes7.dex */
    public static final class LibraryRoot {
        public static final String EXTRA_OFFLINE = "android.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.media.extra.SUGGESTED";
        private final Bundle mExtras;
        private final String mRootId;

        public LibraryRoot(@NonNull String str, @Nullable Bundle bundle) {
            if (str == null) {
                throw new IllegalArgumentException("rootId shouldn't be null");
            }
            this.mRootId = str;
            this.mExtras = bundle;
        }

        public String getRootId() {
            return this.mRootId;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }
    }
}
