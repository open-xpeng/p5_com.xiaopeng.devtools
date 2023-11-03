package android.support.v4.media;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaController2;
import android.util.Log;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public class MediaBrowser2 extends MediaController2 {
    static final String TAG = "MediaBrowser2";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface SupportLibraryImpl extends MediaController2.SupportLibraryImpl {
        void getChildren(@NonNull String str, int i, int i2, @Nullable Bundle bundle);

        void getItem(@NonNull String str);

        void getLibraryRoot(@Nullable Bundle bundle);

        void getSearchResult(@NonNull String str, int i, int i2, @Nullable Bundle bundle);

        void search(@NonNull String str, @Nullable Bundle bundle);

        void subscribe(@NonNull String str, @Nullable Bundle bundle);

        void unsubscribe(@NonNull String str);
    }

    /* loaded from: classes7.dex */
    public static class BrowserCallback extends MediaController2.ControllerCallback {
        public void onGetLibraryRootDone(@NonNull MediaBrowser2 mediaBrowser2, @Nullable Bundle bundle, @Nullable String str, @Nullable Bundle bundle2) {
        }

        public void onChildrenChanged(@NonNull MediaBrowser2 mediaBrowser2, @NonNull String str, int i, @Nullable Bundle bundle) {
        }

        public void onGetChildrenDone(@NonNull MediaBrowser2 mediaBrowser2, @NonNull String str, int i, int i2, @Nullable List<MediaItem2> list, @Nullable Bundle bundle) {
        }

        public void onGetItemDone(@NonNull MediaBrowser2 mediaBrowser2, @NonNull String str, @Nullable MediaItem2 mediaItem2) {
        }

        public void onSearchResultChanged(@NonNull MediaBrowser2 mediaBrowser2, @NonNull String str, int i, @Nullable Bundle bundle) {
        }

        public void onGetSearchResultDone(@NonNull MediaBrowser2 mediaBrowser2, @NonNull String str, int i, int i2, @Nullable List<MediaItem2> list, @Nullable Bundle bundle) {
        }
    }

    public MediaBrowser2(@NonNull Context context, @NonNull SessionToken2 sessionToken2, @NonNull Executor executor, @NonNull BrowserCallback browserCallback) {
        super(context, sessionToken2, executor, browserCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v4.media.MediaController2
    public SupportLibraryImpl createImpl(@NonNull Context context, @NonNull SessionToken2 sessionToken2, @NonNull Executor executor, @NonNull MediaController2.ControllerCallback controllerCallback) {
        if (sessionToken2.isLegacySession()) {
            return new MediaBrowser2ImplLegacy(context, this, sessionToken2, executor, (BrowserCallback) controllerCallback);
        }
        return new MediaBrowser2ImplBase(context, this, sessionToken2, executor, (BrowserCallback) controllerCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v4.media.MediaController2
    public SupportLibraryImpl getImpl() {
        return (SupportLibraryImpl) super.getImpl();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v4.media.MediaController2
    public BrowserCallback getCallback() {
        return (BrowserCallback) super.getCallback();
    }

    public void getLibraryRoot(@Nullable Bundle bundle) {
        getImpl().getLibraryRoot(bundle);
    }

    public void subscribe(@NonNull String str, @Nullable Bundle bundle) {
        getImpl().subscribe(str, bundle);
    }

    public void unsubscribe(@NonNull String str) {
        getImpl().unsubscribe(str);
    }

    public void getChildren(@NonNull String str, int i, int i2, @Nullable Bundle bundle) {
        getImpl().getChildren(str, i, i2, bundle);
    }

    public void getItem(@NonNull String str) {
        getImpl().getItem(str);
    }

    public void search(@NonNull String str, @Nullable Bundle bundle) {
        getImpl().search(str, bundle);
    }

    public void getSearchResult(@NonNull String str, int i, int i2, @Nullable Bundle bundle) {
        getImpl().getSearchResult(str, i, i2, bundle);
    }
}
