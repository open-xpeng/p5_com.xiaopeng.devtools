package android.support.v4.media;

import android.content.Intent;
import android.os.IBinder;
import android.support.v4.media.MediaLibraryService2;

/* loaded from: classes7.dex */
class MediaLibraryService2ImplBase extends MediaSessionService2ImplBase {
    @Override // android.support.v4.media.MediaSessionService2ImplBase, android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public IBinder onBind(Intent intent) {
        char c;
        String action = intent.getAction();
        int hashCode = action.hashCode();
        if (hashCode != 901933117) {
            if (hashCode == 1665850838 && action.equals(MediaBrowserServiceCompat.SERVICE_INTERFACE)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (action.equals(MediaLibraryService2.SERVICE_INTERFACE)) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return getSession().getSessionBinder();
            case 1:
                return getSession().getImpl().getLegacySessionBinder();
            default:
                return super.onBind(intent);
        }
    }

    @Override // android.support.v4.media.MediaSessionService2ImplBase, android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public MediaLibraryService2.MediaLibrarySession getSession() {
        return (MediaLibraryService2.MediaLibrarySession) super.getSession();
    }

    @Override // android.support.v4.media.MediaSessionService2ImplBase, android.support.v4.media.MediaSessionService2.SupportLibraryImpl
    public int getSessionType() {
        return 2;
    }
}
