package android.support.v4.media;

import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowser2;
import android.support.v4.media.MediaBrowserCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class MediaBrowser2ImplLegacy extends MediaController2ImplLegacy implements MediaBrowser2.SupportLibraryImpl {
    public static final String EXTRA_ITEM_COUNT = "android.media.browse.extra.ITEM_COUNT";
    @GuardedBy("mLock")
    private final HashMap<Bundle, MediaBrowserCompat> mBrowserCompats;
    @GuardedBy("mLock")
    private final HashMap<String, List<SubscribeCallback>> mSubscribeCallbacks;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaBrowser2ImplLegacy(@NonNull Context context, MediaBrowser2 mediaBrowser2, @NonNull SessionToken2 sessionToken2, @NonNull Executor executor, @NonNull MediaBrowser2.BrowserCallback browserCallback) {
        super(context, mediaBrowser2, sessionToken2, executor, browserCallback);
        this.mBrowserCompats = new HashMap<>();
        this.mSubscribeCallbacks = new HashMap<>();
    }

    @Override // android.support.v4.media.MediaController2ImplLegacy, android.support.v4.media.MediaController2.SupportLibraryImpl
    public MediaBrowser2 getInstance() {
        return (MediaBrowser2) super.getInstance();
    }

    @Override // android.support.v4.media.MediaController2ImplLegacy, java.lang.AutoCloseable
    public void close() {
        synchronized (this.mLock) {
            for (MediaBrowserCompat mediaBrowserCompat : this.mBrowserCompats.values()) {
                mediaBrowserCompat.disconnect();
            }
            this.mBrowserCompats.clear();
            super.close();
        }
    }

    @Override // android.support.v4.media.MediaBrowser2.SupportLibraryImpl
    public void getLibraryRoot(@Nullable final Bundle bundle) {
        final MediaBrowserCompat browserCompat = getBrowserCompat(bundle);
        if (browserCompat != null) {
            getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.1
                @Override // java.lang.Runnable
                public void run() {
                    MediaBrowser2ImplLegacy.this.getCallback().onGetLibraryRootDone(MediaBrowser2ImplLegacy.this.getInstance(), bundle, browserCompat.getRoot(), browserCompat.getExtras());
                }
            });
        } else {
            getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.2
                @Override // java.lang.Runnable
                public void run() {
                    MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(MediaBrowser2ImplLegacy.this.getContext(), MediaBrowser2ImplLegacy.this.getSessionToken().getComponentName(), new GetLibraryRootCallback(bundle), bundle);
                    synchronized (MediaBrowser2ImplLegacy.this.mLock) {
                        MediaBrowser2ImplLegacy.this.mBrowserCompats.put(bundle, mediaBrowserCompat);
                    }
                    mediaBrowserCompat.connect();
                }
            });
        }
    }

    @Override // android.support.v4.media.MediaBrowser2.SupportLibraryImpl
    public void subscribe(@NonNull String str, @Nullable Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("parentId shouldn't be null");
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return;
        }
        SubscribeCallback subscribeCallback = new SubscribeCallback();
        synchronized (this.mLock) {
            List<SubscribeCallback> list = this.mSubscribeCallbacks.get(str);
            if (list == null) {
                list = new ArrayList<>();
                this.mSubscribeCallbacks.put(str, list);
            }
            list.add(subscribeCallback);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("android.support.v4.media.argument.EXTRAS", bundle);
        browserCompat.subscribe(str, bundle2, subscribeCallback);
    }

    @Override // android.support.v4.media.MediaBrowser2.SupportLibraryImpl
    public void unsubscribe(@NonNull String str) {
        if (str == null) {
            throw new IllegalArgumentException("parentId shouldn't be null");
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return;
        }
        synchronized (this.mLock) {
            List<SubscribeCallback> list = this.mSubscribeCallbacks.get(str);
            if (list == null) {
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                browserCompat.unsubscribe(str, list.get(i));
            }
        }
    }

    @Override // android.support.v4.media.MediaBrowser2.SupportLibraryImpl
    public void getChildren(@NonNull String str, int i, int i2, @Nullable Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("parentId shouldn't be null");
        }
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("Neither page nor pageSize should be less than 1");
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return;
        }
        Bundle createBundle = MediaUtils2.createBundle(bundle);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE, i);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i2);
        browserCompat.subscribe(str, createBundle, new GetChildrenCallback(str, i, i2));
    }

    @Override // android.support.v4.media.MediaBrowser2.SupportLibraryImpl
    public void getItem(@NonNull final String str) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return;
        }
        browserCompat.getItem(str, new MediaBrowserCompat.ItemCallback() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.3
            @Override // android.support.v4.media.MediaBrowserCompat.ItemCallback
            public void onItemLoaded(final MediaBrowserCompat.MediaItem mediaItem) {
                MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaBrowser2ImplLegacy.this.getCallback().onGetItemDone(MediaBrowser2ImplLegacy.this.getInstance(), str, MediaUtils2.convertToMediaItem2(mediaItem));
                    }
                });
            }

            @Override // android.support.v4.media.MediaBrowserCompat.ItemCallback
            public void onError(String str2) {
                MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaBrowser2ImplLegacy.this.getCallback().onGetItemDone(MediaBrowser2ImplLegacy.this.getInstance(), str, null);
                    }
                });
            }
        });
    }

    @Override // android.support.v4.media.MediaBrowser2.SupportLibraryImpl
    public void search(@NonNull String str, @Nullable Bundle bundle) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return;
        }
        browserCompat.search(str, bundle, new MediaBrowserCompat.SearchCallback() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.4
            @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
            public void onSearchResult(final String str2, final Bundle bundle2, final List<MediaBrowserCompat.MediaItem> list) {
                MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaBrowser2ImplLegacy.this.getCallback().onSearchResultChanged(MediaBrowser2ImplLegacy.this.getInstance(), str2, list.size(), bundle2);
                    }
                });
            }

            @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
            public void onError(String str2, Bundle bundle2) {
            }
        });
    }

    @Override // android.support.v4.media.MediaBrowser2.SupportLibraryImpl
    public void getSearchResult(@NonNull String str, final int i, final int i2, @Nullable final Bundle bundle) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return;
        }
        Bundle createBundle = MediaUtils2.createBundle(bundle);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE, i);
        createBundle.putInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, i2);
        browserCompat.search(str, createBundle, new MediaBrowserCompat.SearchCallback() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.5
            @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
            public void onSearchResult(final String str2, Bundle bundle2, final List<MediaBrowserCompat.MediaItem> list) {
                MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaBrowser2ImplLegacy.this.getCallback().onGetSearchResultDone(MediaBrowser2ImplLegacy.this.getInstance(), str2, i, i2, MediaUtils2.convertMediaItemListToMediaItem2List(list), bundle);
                    }
                });
            }

            @Override // android.support.v4.media.MediaBrowserCompat.SearchCallback
            public void onError(final String str2, Bundle bundle2) {
                MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.5.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaBrowser2ImplLegacy.this.getCallback().onGetSearchResultDone(MediaBrowser2ImplLegacy.this.getInstance(), str2, i, i2, null, bundle);
                    }
                });
            }
        });
    }

    @Override // android.support.v4.media.MediaController2ImplLegacy, android.support.v4.media.MediaController2.SupportLibraryImpl
    public MediaBrowser2.BrowserCallback getCallback() {
        return (MediaBrowser2.BrowserCallback) super.getCallback();
    }

    private MediaBrowserCompat getBrowserCompat(Bundle bundle) {
        MediaBrowserCompat mediaBrowserCompat;
        synchronized (this.mLock) {
            mediaBrowserCompat = this.mBrowserCompats.get(bundle);
        }
        return mediaBrowserCompat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle getExtrasWithoutPagination(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(getContext().getClassLoader());
        try {
            bundle.remove(MediaBrowserCompat.EXTRA_PAGE);
            bundle.remove(MediaBrowserCompat.EXTRA_PAGE_SIZE);
        } catch (BadParcelableException e) {
        }
        return bundle;
    }

    /* loaded from: classes7.dex */
    private class GetLibraryRootCallback extends MediaBrowserCompat.ConnectionCallback {
        private final Bundle mExtras;

        GetLibraryRootCallback(Bundle bundle) {
            this.mExtras = bundle;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnected() {
            MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.GetLibraryRootCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    MediaBrowserCompat mediaBrowserCompat;
                    synchronized (MediaBrowser2ImplLegacy.this.mLock) {
                        mediaBrowserCompat = (MediaBrowserCompat) MediaBrowser2ImplLegacy.this.mBrowserCompats.get(GetLibraryRootCallback.this.mExtras);
                    }
                    if (mediaBrowserCompat == null) {
                        return;
                    }
                    MediaBrowser2ImplLegacy.this.getCallback().onGetLibraryRootDone(MediaBrowser2ImplLegacy.this.getInstance(), GetLibraryRootCallback.this.mExtras, mediaBrowserCompat.getRoot(), mediaBrowserCompat.getExtras());
                }
            });
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnectionSuspended() {
            MediaBrowser2ImplLegacy.this.close();
        }

        @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
        public void onConnectionFailed() {
            MediaBrowser2ImplLegacy.this.close();
        }
    }

    /* loaded from: classes7.dex */
    private class SubscribeCallback extends MediaBrowserCompat.SubscriptionCallback {
        private SubscribeCallback() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onChildrenLoaded(str, null, null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onChildrenLoaded(str, null, bundle);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoaded(str, list, null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(final String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            final int size;
            if (bundle != null && bundle.containsKey(MediaBrowser2ImplLegacy.EXTRA_ITEM_COUNT)) {
                size = bundle.getInt(MediaBrowser2ImplLegacy.EXTRA_ITEM_COUNT);
            } else if (list != null) {
                size = list.size();
            } else {
                return;
            }
            final Bundle notifyChildrenChangedOptions = MediaBrowser2ImplLegacy.this.getBrowserCompat().getNotifyChildrenChangedOptions();
            MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.SubscribeCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    MediaBrowser2ImplLegacy.this.getCallback().onChildrenChanged(MediaBrowser2ImplLegacy.this.getInstance(), str, size, notifyChildrenChangedOptions);
                }
            });
        }
    }

    /* loaded from: classes7.dex */
    private class GetChildrenCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final int mPage;
        private final int mPageSize;
        private final String mParentId;

        GetChildrenCallback(String str, int i, int i2) {
            this.mParentId = str;
            this.mPage = i;
            this.mPageSize = i2;
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str) {
            onChildrenLoaded(str, null, null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onError(String str, Bundle bundle) {
            onChildrenLoaded(str, null, bundle);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoaded(str, list, null);
        }

        @Override // android.support.v4.media.MediaBrowserCompat.SubscriptionCallback
        public void onChildrenLoaded(final String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            final ArrayList arrayList;
            if (list == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    arrayList2.add(MediaUtils2.convertToMediaItem2(list.get(i)));
                }
                arrayList = arrayList2;
            }
            final Bundle extrasWithoutPagination = MediaBrowser2ImplLegacy.this.getExtrasWithoutPagination(bundle);
            MediaBrowser2ImplLegacy.this.getCallbackExecutor().execute(new Runnable() { // from class: android.support.v4.media.MediaBrowser2ImplLegacy.GetChildrenCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    MediaBrowserCompat browserCompat = MediaBrowser2ImplLegacy.this.getBrowserCompat();
                    if (browserCompat != null) {
                        MediaBrowser2ImplLegacy.this.getCallback().onGetChildrenDone(MediaBrowser2ImplLegacy.this.getInstance(), str, GetChildrenCallback.this.mPage, GetChildrenCallback.this.mPageSize, arrayList, extrasWithoutPagination);
                        browserCompat.unsubscribe(GetChildrenCallback.this.mParentId, GetChildrenCallback.this);
                    }
                }
            });
        }
    }
}
