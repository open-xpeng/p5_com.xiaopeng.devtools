package android.support.v4.media;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaLibraryService2;
import android.support.v4.media.MediaSession2;
import android.support.v4.media.MediaSessionManager;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class MediaLibraryService2LegacyStub extends MediaBrowserServiceCompat {
    private final MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl mLibrarySession;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaLibraryService2LegacyStub(MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl supportLibraryImpl) {
        this.mLibrarySession = supportLibraryImpl;
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        if (MediaUtils2.isDefaultLibraryRootHint(bundle)) {
            return MediaUtils2.sDefaultBrowserRoot;
        }
        MediaLibraryService2.LibraryRoot onGetLibraryRoot = this.mLibrarySession.getCallback().onGetLibraryRoot(this.mLibrarySession.getInstance(), getController(), bundle);
        if (onGetLibraryRoot == null) {
            return null;
        }
        return new MediaBrowserServiceCompat.BrowserRoot(onGetLibraryRoot.getRootId(), onGetLibraryRoot.getExtras());
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat
    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        onLoadChildren(str, result, null);
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat
    public void onLoadChildren(final String str, final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, final Bundle bundle) {
        result.detach();
        final MediaSession2.ControllerInfo controller = getController();
        this.mLibrarySession.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaLibraryService2LegacyStub.1
            @Override // java.lang.Runnable
            public void run() {
                if (bundle != null) {
                    bundle.setClassLoader(MediaLibraryService2LegacyStub.this.mLibrarySession.getContext().getClassLoader());
                    try {
                        int i = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE);
                        int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE);
                        if (i > 0 && i2 > 0) {
                            result.sendResult(MediaUtils2.convertToMediaItemList(MediaLibraryService2LegacyStub.this.mLibrarySession.getCallback().onGetChildren(MediaLibraryService2LegacyStub.this.mLibrarySession.getInstance(), controller, str, i, i2, bundle)));
                            return;
                        }
                    } catch (BadParcelableException e) {
                    }
                }
                result.sendResult(MediaUtils2.convertToMediaItemList(MediaLibraryService2LegacyStub.this.mLibrarySession.getCallback().onGetChildren(MediaLibraryService2LegacyStub.this.mLibrarySession.getInstance(), controller, str, 1, Integer.MAX_VALUE, null)));
            }
        });
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat
    public void onLoadItem(final String str, final MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result) {
        result.detach();
        final MediaSession2.ControllerInfo controller = getController();
        this.mLibrarySession.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaLibraryService2LegacyStub.2
            @Override // java.lang.Runnable
            public void run() {
                MediaItem2 onGetItem = MediaLibraryService2LegacyStub.this.mLibrarySession.getCallback().onGetItem(MediaLibraryService2LegacyStub.this.mLibrarySession.getInstance(), controller, str);
                if (onGetItem == null) {
                    result.sendResult(null);
                } else {
                    result.sendResult(MediaUtils2.convertToMediaItem(onGetItem));
                }
            }
        });
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat
    public void onSearch(final String str, final Bundle bundle, final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.detach();
        final MediaSession2.ControllerInfo controller = getController();
        bundle.setClassLoader(this.mLibrarySession.getContext().getClassLoader());
        try {
            final int i = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE);
            final int i2 = bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE);
            if (i > 0 && i2 > 0) {
                this.mLibrarySession.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaLibraryService2LegacyStub.3
                    @Override // java.lang.Runnable
                    public void run() {
                        List<MediaItem2> onGetSearchResult = MediaLibraryService2LegacyStub.this.mLibrarySession.getCallback().onGetSearchResult(MediaLibraryService2LegacyStub.this.mLibrarySession.getInstance(), controller, str, i, i2, bundle);
                        if (onGetSearchResult == null) {
                            result.sendResult(null);
                        } else {
                            result.sendResult(MediaUtils2.convertToMediaItemList(onGetSearchResult));
                        }
                    }
                });
            } else {
                this.mLibrarySession.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaLibraryService2LegacyStub.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaLibraryService2LegacyStub.this.mLibrarySession.getCallback().onSearch(MediaLibraryService2LegacyStub.this.mLibrarySession.getInstance(), controller, str, bundle);
                    }
                });
            }
        } catch (BadParcelableException e) {
        }
    }

    @Override // android.support.v4.media.MediaBrowserServiceCompat
    public void onCustomAction(String str, Bundle bundle, MediaBrowserServiceCompat.Result<Bundle> result) {
    }

    private MediaSession2.ControllerInfo getController() {
        List<MediaSession2.ControllerInfo> connectedControllers = this.mLibrarySession.getConnectedControllers();
        MediaSessionManager.RemoteUserInfo currentBrowserInfo = getCurrentBrowserInfo();
        if (currentBrowserInfo == null) {
            return null;
        }
        for (int i = 0; i < connectedControllers.size(); i++) {
            MediaSession2.ControllerInfo controllerInfo = connectedControllers.get(i);
            if (controllerInfo.getPackageName().equals(currentBrowserInfo.getPackageName()) && controllerInfo.getUid() == currentBrowserInfo.getUid()) {
                return controllerInfo;
            }
        }
        return null;
    }
}
