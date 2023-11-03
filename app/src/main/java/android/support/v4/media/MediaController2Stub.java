package android.support.v4.media;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.IMediaController2;
import android.support.v4.media.MediaController2;
import android.support.v4.media.MediaSession2;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class MediaController2Stub extends IMediaController2.Stub {
    private static final boolean DEBUG = true;
    private static final String TAG = "MediaController2Stub";
    private final WeakReference<MediaController2ImplBase> mController;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaController2Stub(MediaController2ImplBase mediaController2ImplBase) {
        this.mController = new WeakReference<>(mediaController2ImplBase);
    }

    @Override // android.support.v4.media.IMediaController2
    public void onCurrentMediaItemChanged(Bundle bundle) {
        try {
            getController().notifyCurrentMediaItemChanged(MediaItem2.fromBundle(bundle));
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onPlayerStateChanged(long j, long j2, int i) {
        try {
            getController().notifyPlayerStateChanges(j, j2, i);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onPlaybackSpeedChanged(long j, long j2, float f) {
        try {
            getController().notifyPlaybackSpeedChanges(j, j2, f);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onBufferingStateChanged(Bundle bundle, int i, long j) {
        try {
            getController().notifyBufferingStateChanged(MediaItem2.fromBundle(bundle), i, j);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onPlaylistChanged(List<Bundle> list, Bundle bundle) {
        try {
            MediaController2ImplBase controller = getController();
            if (list == null) {
                Log.w(TAG, "onPlaylistChanged(): Ignoring null playlist from " + controller);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (Bundle bundle2 : list) {
                MediaItem2 fromBundle = MediaItem2.fromBundle(bundle2);
                if (fromBundle == null) {
                    Log.w(TAG, "onPlaylistChanged(): Ignoring null item in playlist");
                } else {
                    arrayList.add(fromBundle);
                }
            }
            controller.notifyPlaylistChanges(arrayList, MediaMetadata2.fromBundle(bundle));
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onPlaylistMetadataChanged(Bundle bundle) throws RuntimeException {
        try {
            getController().notifyPlaylistMetadataChanges(MediaMetadata2.fromBundle(bundle));
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onRepeatModeChanged(int i) {
        try {
            getController().notifyRepeatModeChanges(i);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onShuffleModeChanged(int i) {
        try {
            getController().notifyShuffleModeChanges(i);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onPlaybackInfoChanged(Bundle bundle) throws RuntimeException {
        Log.d(TAG, "onPlaybackInfoChanged");
        try {
            MediaController2ImplBase controller = getController();
            MediaController2.PlaybackInfo fromBundle = MediaController2.PlaybackInfo.fromBundle(bundle);
            if (fromBundle == null) {
                Log.w(TAG, "onPlaybackInfoChanged(): Ignoring null playbackInfo");
            } else {
                controller.notifyPlaybackInfoChanges(fromBundle);
            }
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onSeekCompleted(long j, long j2, long j3) {
        try {
            getController().notifySeekCompleted(j, j2, j3);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onError(int i, Bundle bundle) {
        try {
            getController().notifyError(i, bundle);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onRoutesInfoChanged(List<Bundle> list) {
        try {
            getController().notifyRoutesInfoChanged(list);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onConnected(IMediaSession2 iMediaSession2, Bundle bundle, int i, Bundle bundle2, long j, long j2, float f, long j3, Bundle bundle3, int i2, int i3, List<Bundle> list, PendingIntent pendingIntent) {
        MediaController2ImplBase mediaController2ImplBase = this.mController.get();
        if (mediaController2ImplBase == null) {
            Log.d(TAG, "onConnected after MediaController2.close()");
            return;
        }
        ArrayList arrayList = null;
        if (list != null) {
            arrayList = new ArrayList();
            for (int i4 = 0; i4 < list.size(); i4++) {
                MediaItem2 fromBundle = MediaItem2.fromBundle(list.get(i4));
                if (fromBundle != null) {
                    arrayList.add(fromBundle);
                }
            }
        }
        mediaController2ImplBase.onConnectedNotLocked(iMediaSession2, SessionCommandGroup2.fromBundle(bundle), i, MediaItem2.fromBundle(bundle2), j, j2, f, j3, MediaController2.PlaybackInfo.fromBundle(bundle3), i3, i2, arrayList, pendingIntent);
    }

    @Override // android.support.v4.media.IMediaController2
    public void onDisconnected() {
        MediaController2ImplBase mediaController2ImplBase = this.mController.get();
        if (mediaController2ImplBase == null) {
            Log.d(TAG, "onDisconnected after MediaController2.close()");
        } else {
            mediaController2ImplBase.getInstance().close();
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onCustomLayoutChanged(List<Bundle> list) {
        if (list == null) {
            Log.w(TAG, "onCustomLayoutChanged(): Ignoring null commandButtonlist");
            return;
        }
        try {
            MediaController2ImplBase controller = getController();
            if (controller == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                MediaSession2.CommandButton fromBundle = MediaSession2.CommandButton.fromBundle(list.get(i));
                if (fromBundle != null) {
                    arrayList.add(fromBundle);
                }
            }
            controller.onCustomLayoutChanged(arrayList);
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onAllowedCommandsChanged(Bundle bundle) {
        try {
            MediaController2ImplBase controller = getController();
            if (controller == null) {
                return;
            }
            SessionCommandGroup2 fromBundle = SessionCommandGroup2.fromBundle(bundle);
            if (fromBundle == null) {
                Log.w(TAG, "onAllowedCommandsChanged(): Ignoring null commands");
            } else {
                controller.onAllowedCommandsChanged(fromBundle);
            }
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onCustomCommand(Bundle bundle, Bundle bundle2, ResultReceiver resultReceiver) {
        try {
            MediaController2ImplBase controller = getController();
            SessionCommand2 fromBundle = SessionCommand2.fromBundle(bundle);
            if (fromBundle == null) {
                Log.w(TAG, "onCustomCommand(): Ignoring null command");
            } else {
                controller.onCustomCommand(fromBundle, bundle2, resultReceiver);
            }
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onGetLibraryRootDone(final Bundle bundle, final String str, final Bundle bundle2) throws RuntimeException {
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser == null) {
                return;
            }
            browser.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaController2Stub.1
                @Override // java.lang.Runnable
                public void run() {
                    browser.getCallback().onGetLibraryRootDone(browser, bundle, str, bundle2);
                }
            });
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onGetItemDone(final String str, final Bundle bundle) throws RuntimeException {
        if (str == null) {
            Log.w(TAG, "onGetItemDone(): Ignoring null mediaId");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser == null) {
                return;
            }
            browser.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaController2Stub.2
                @Override // java.lang.Runnable
                public void run() {
                    browser.getCallback().onGetItemDone(browser, str, MediaItem2.fromBundle(bundle));
                }
            });
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onGetChildrenDone(final String str, final int i, final int i2, final List<Bundle> list, final Bundle bundle) throws RuntimeException {
        if (str == null) {
            Log.w(TAG, "onGetChildrenDone(): Ignoring null parentId");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser == null) {
                return;
            }
            browser.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaController2Stub.3
                @Override // java.lang.Runnable
                public void run() {
                    browser.getCallback().onGetChildrenDone(browser, str, i, i2, MediaUtils2.convertBundleListToMediaItem2List(list), bundle);
                }
            });
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onSearchResultChanged(final String str, final int i, final Bundle bundle) throws RuntimeException {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onSearchResultChanged(): Ignoring empty query");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser == null) {
                return;
            }
            browser.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaController2Stub.4
                @Override // java.lang.Runnable
                public void run() {
                    browser.getCallback().onSearchResultChanged(browser, str, i, bundle);
                }
            });
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onGetSearchResultDone(final String str, final int i, final int i2, final List<Bundle> list, final Bundle bundle) throws RuntimeException {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onGetSearchResultDone(): Ignoring empty query");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser == null) {
                return;
            }
            browser.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaController2Stub.5
                @Override // java.lang.Runnable
                public void run() {
                    browser.getCallback().onGetSearchResultDone(browser, str, i, i2, MediaUtils2.convertBundleListToMediaItem2List(list), bundle);
                }
            });
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    @Override // android.support.v4.media.IMediaController2
    public void onChildrenChanged(final String str, final int i, final Bundle bundle) {
        if (str == null) {
            Log.w(TAG, "onChildrenChanged(): Ignoring null parentId");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser == null) {
                return;
            }
            browser.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaController2Stub.6
                @Override // java.lang.Runnable
                public void run() {
                    browser.getCallback().onChildrenChanged(browser, str, i, bundle);
                }
            });
        } catch (IllegalStateException e) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void destroy() {
        this.mController.clear();
    }

    private MediaController2ImplBase getController() throws IllegalStateException {
        MediaController2ImplBase mediaController2ImplBase = this.mController.get();
        if (mediaController2ImplBase == null) {
            throw new IllegalStateException("Controller is released");
        }
        return mediaController2ImplBase;
    }

    private MediaBrowser2 getBrowser() throws IllegalStateException {
        MediaController2ImplBase controller = getController();
        if (controller.getInstance() instanceof MediaBrowser2) {
            return (MediaBrowser2) controller.getInstance();
        }
        return null;
    }
}
