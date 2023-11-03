package android.support.v4.media;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.GuardedBy;
import android.support.v4.media.MediaLibraryService2;
import android.support.v4.media.MediaSession2;
import android.support.v4.media.MediaSession2ImplBase;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

@TargetApi(19)
/* loaded from: classes7.dex */
class MediaLibrarySessionImplBase extends MediaSession2ImplBase implements MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl {
    private final MediaBrowserServiceCompat mBrowserServiceLegacyStub;
    @GuardedBy("mLock")
    private final ArrayMap<MediaSession2.ControllerInfo, Set<String>> mSubscriptions;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaLibrarySessionImplBase(MediaLibraryService2.MediaLibrarySession mediaLibrarySession, Context context, String str, BaseMediaPlayer baseMediaPlayer, MediaPlaylistAgent mediaPlaylistAgent, VolumeProviderCompat volumeProviderCompat, PendingIntent pendingIntent, Executor executor, MediaSession2.SessionCallback sessionCallback) {
        super(mediaLibrarySession, context, str, baseMediaPlayer, mediaPlaylistAgent, volumeProviderCompat, pendingIntent, executor, sessionCallback);
        this.mSubscriptions = new ArrayMap<>();
        this.mBrowserServiceLegacyStub = new MediaLibraryService2LegacyStub(this);
        this.mBrowserServiceLegacyStub.attachToBaseContext(context);
        this.mBrowserServiceLegacyStub.onCreate();
    }

    @Override // android.support.v4.media.MediaSession2ImplBase, android.support.v4.media.MediaSession2.SupportLibraryImpl
    public MediaLibraryService2.MediaLibrarySession getInstance() {
        return (MediaLibraryService2.MediaLibrarySession) super.getInstance();
    }

    @Override // android.support.v4.media.MediaSession2ImplBase, android.support.v4.media.MediaSession2.SupportLibraryImpl
    public MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionCallback getCallback() {
        return (MediaLibraryService2.MediaLibrarySession.MediaLibrarySessionCallback) super.getCallback();
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public IBinder getLegacySessionBinder() {
        return this.mBrowserServiceLegacyStub.onBind(new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE));
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void notifyChildrenChanged(final String str, final int i, final Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        }
        if (i < 0) {
            throw new IllegalArgumentException("itemCount shouldn't be negative");
        }
        List<MediaSession2.ControllerInfo> connectedControllers = getConnectedControllers();
        MediaSession2ImplBase.NotifyRunnable notifyRunnable = new MediaSession2ImplBase.NotifyRunnable() { // from class: android.support.v4.media.MediaLibrarySessionImplBase.1
            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onChildrenChanged(str, i, bundle);
            }
        };
        for (int i2 = 0; i2 < connectedControllers.size(); i2++) {
            if (isSubscribed(connectedControllers.get(i2), str)) {
                notifyToController(connectedControllers.get(i2), notifyRunnable);
            }
        }
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void notifyChildrenChanged(final MediaSession2.ControllerInfo controllerInfo, final String str, final int i, final Bundle bundle) {
        if (controllerInfo == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        }
        if (i < 0) {
            throw new IllegalArgumentException("itemCount shouldn't be negative");
        }
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() { // from class: android.support.v4.media.MediaLibrarySessionImplBase.2
            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                if (!MediaLibrarySessionImplBase.this.isSubscribed(controllerInfo, str)) {
                    if (MediaSession2ImplBase.DEBUG) {
                        Log.d("MS2ImplBase", "Skipping notifyChildrenChanged() to " + controllerInfo + " because it hasn't subscribed");
                        MediaLibrarySessionImplBase.this.dumpSubscription();
                        return;
                    }
                    return;
                }
                controllerCb.onChildrenChanged(str, i, bundle);
            }
        });
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void notifySearchResultChanged(MediaSession2.ControllerInfo controllerInfo, final String str, final int i, final Bundle bundle) {
        if (controllerInfo == null) {
            throw new IllegalArgumentException("controller shouldn't be null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        }
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() { // from class: android.support.v4.media.MediaLibrarySessionImplBase.3
            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onSearchResultChanged(str, i, bundle);
            }
        });
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void onGetLibraryRootOnExecutor(MediaSession2.ControllerInfo controllerInfo, final Bundle bundle) {
        final MediaLibraryService2.LibraryRoot onGetLibraryRoot = getCallback().onGetLibraryRoot(getInstance(), controllerInfo, bundle);
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() { // from class: android.support.v4.media.MediaLibrarySessionImplBase.4
            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onGetLibraryRootDone(bundle, onGetLibraryRoot == null ? null : onGetLibraryRoot.getRootId(), onGetLibraryRoot != null ? onGetLibraryRoot.getExtras() : null);
            }
        });
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void onGetItemOnExecutor(MediaSession2.ControllerInfo controllerInfo, final String str) {
        final MediaItem2 onGetItem = getCallback().onGetItem(getInstance(), controllerInfo, str);
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() { // from class: android.support.v4.media.MediaLibrarySessionImplBase.5
            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onGetItemDone(str, onGetItem);
            }
        });
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void onGetChildrenOnExecutor(MediaSession2.ControllerInfo controllerInfo, final String str, final int i, final int i2, final Bundle bundle) {
        final List<MediaItem2> onGetChildren = getCallback().onGetChildren(getInstance(), controllerInfo, str, i, i2, bundle);
        if (onGetChildren != null && onGetChildren.size() > i2) {
            throw new IllegalArgumentException("onGetChildren() shouldn't return media items more than pageSize. result.size()=" + onGetChildren.size() + " pageSize=" + i2);
        }
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() { // from class: android.support.v4.media.MediaLibrarySessionImplBase.6
            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onGetChildrenDone(str, i, i2, onGetChildren, bundle);
            }
        });
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void onSubscribeOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle) {
        synchronized (this.mLock) {
            Set<String> set = this.mSubscriptions.get(controllerInfo);
            if (set == null) {
                set = new HashSet<>();
                this.mSubscriptions.put(controllerInfo, set);
            }
            set.add(str);
        }
        getCallback().onSubscribe(getInstance(), controllerInfo, str, bundle);
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void onUnsubscribeOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str) {
        getCallback().onUnsubscribe(getInstance(), controllerInfo, str);
        synchronized (this.mLock) {
            this.mSubscriptions.remove(controllerInfo);
        }
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void onSearchOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle) {
        getCallback().onSearch(getInstance(), controllerInfo, str, bundle);
    }

    @Override // android.support.v4.media.MediaLibraryService2.MediaLibrarySession.SupportLibraryImpl
    public void onGetSearchResultOnExecutor(MediaSession2.ControllerInfo controllerInfo, final String str, final int i, final int i2, final Bundle bundle) {
        final List<MediaItem2> onGetSearchResult = getCallback().onGetSearchResult(getInstance(), controllerInfo, str, i, i2, bundle);
        if (onGetSearchResult != null && onGetSearchResult.size() > i2) {
            throw new IllegalArgumentException("onGetSearchResult() shouldn't return media items more than pageSize. result.size()=" + onGetSearchResult.size() + " pageSize=" + i2);
        }
        notifyToController(controllerInfo, new MediaSession2ImplBase.NotifyRunnable() { // from class: android.support.v4.media.MediaLibrarySessionImplBase.7
            @Override // android.support.v4.media.MediaSession2ImplBase.NotifyRunnable
            public void run(MediaSession2.ControllerCb controllerCb) throws RemoteException {
                controllerCb.onGetSearchResultDone(str, i, i2, onGetSearchResult, bundle);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSubscribed(MediaSession2.ControllerInfo controllerInfo, String str) {
        synchronized (this.mLock) {
            Set<String> set = this.mSubscriptions.get(controllerInfo);
            if (set != null && set.contains(str)) {
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dumpSubscription() {
        if (!DEBUG) {
            return;
        }
        synchronized (this.mLock) {
            Log.d("MS2ImplBase", "Dumping subscription, controller sz=" + this.mSubscriptions.size());
            for (int i = 0; i < this.mSubscriptions.size(); i++) {
                Log.d("MS2ImplBase", "  controller " + this.mSubscriptions.valueAt(i));
                Iterator<String> it = this.mSubscriptions.valueAt(i).iterator();
                while (it.hasNext()) {
                    Log.d("MS2ImplBase", "  - " + it.next());
                }
            }
        }
    }
}
