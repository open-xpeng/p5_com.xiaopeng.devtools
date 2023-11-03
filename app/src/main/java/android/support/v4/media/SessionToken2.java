package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: classes7.dex */
public final class SessionToken2 {
    static final String KEY_PACKAGE_NAME = "android.media.token.package_name";
    static final String KEY_SERVICE_NAME = "android.media.token.service_name";
    static final String KEY_SESSION_BINDER = "android.media.token.session_binder";
    static final String KEY_SESSION_ID = "android.media.token.session_id";
    static final String KEY_TOKEN_LEGACY = "android.media.token.LEGACY";
    static final String KEY_TYPE = "android.media.token.type";
    static final String KEY_UID = "android.media.token.uid";
    private static final String TAG = "SessionToken2";
    public static final int TYPE_LIBRARY_SERVICE = 2;
    public static final int TYPE_SESSION = 0;
    static final int TYPE_SESSION_LEGACY = 100;
    public static final int TYPE_SESSION_SERVICE = 1;
    static final int UID_UNKNOWN = -1;
    private static final long WAIT_TIME_MS_FOR_SESSION_READY = 300;
    private final SupportLibraryImpl mImpl;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes7.dex */
    public interface OnSessionToken2CreatedListener {
        void onSessionToken2Created(MediaSessionCompat.Token token, SessionToken2 sessionToken2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface SupportLibraryImpl {
        Object getBinder();

        @Nullable
        ComponentName getComponentName();

        @NonNull
        String getPackageName();

        @Nullable
        String getServiceName();

        String getSessionId();

        int getType();

        int getUid();

        Bundle toBundle();
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes7.dex */
    public @interface TokenType {
    }

    public SessionToken2(@NonNull Context context, @NonNull ComponentName componentName) {
        this.mImpl = new SessionToken2ImplBase(context, componentName);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public SessionToken2(SupportLibraryImpl supportLibraryImpl) {
        this.mImpl = supportLibraryImpl;
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken2)) {
            return false;
        }
        return this.mImpl.equals(((SessionToken2) obj).mImpl);
    }

    public String toString() {
        return this.mImpl.toString();
    }

    public int getUid() {
        return this.mImpl.getUid();
    }

    @NonNull
    public String getPackageName() {
        return this.mImpl.getPackageName();
    }

    @Nullable
    public String getServiceName() {
        return this.mImpl.getServiceName();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ComponentName getComponentName() {
        return this.mImpl.getComponentName();
    }

    public String getId() {
        return this.mImpl.getSessionId();
    }

    public int getType() {
        return this.mImpl.getType();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isLegacySession() {
        return this.mImpl instanceof SessionToken2ImplLegacy;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Object getBinder() {
        return this.mImpl.getBinder();
    }

    public Bundle toBundle() {
        return this.mImpl.toBundle();
    }

    public static SessionToken2 fromBundle(@NonNull Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (bundle.getInt(KEY_TYPE, -1) == 100) {
            return new SessionToken2(SessionToken2ImplLegacy.fromBundle(bundle));
        }
        return new SessionToken2(SessionToken2ImplBase.fromBundle(bundle));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void createSessionToken2(@NonNull final Context context, @NonNull final MediaSessionCompat.Token token, @NonNull Executor executor, @NonNull final OnSessionToken2CreatedListener onSessionToken2CreatedListener) {
        if (context == null) {
            throw new IllegalArgumentException("context shouldn't be null");
        }
        if (token == null) {
            throw new IllegalArgumentException("token shouldn't be null");
        }
        if (executor == null) {
            throw new IllegalArgumentException("executor shouldn't be null");
        }
        if (onSessionToken2CreatedListener == null) {
            throw new IllegalArgumentException("listener shouldn't be null");
        }
        executor.execute(new Runnable() { // from class: android.support.v4.media.SessionToken2.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    final MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(context, token);
                    mediaControllerCompat.registerCallback(new MediaControllerCompat.Callback() { // from class: android.support.v4.media.SessionToken2.1.1
                        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
                        public void onSessionReady() {
                            synchronized (onSessionToken2CreatedListener) {
                                onSessionToken2CreatedListener.onSessionToken2Created(token, mediaControllerCompat.getSessionToken2());
                                onSessionToken2CreatedListener.notify();
                            }
                        }
                    });
                    if (mediaControllerCompat.isSessionReady()) {
                        onSessionToken2CreatedListener.onSessionToken2Created(token, mediaControllerCompat.getSessionToken2());
                    }
                    synchronized (onSessionToken2CreatedListener) {
                        onSessionToken2CreatedListener.wait(SessionToken2.WAIT_TIME_MS_FOR_SESSION_READY);
                        if (!mediaControllerCompat.isSessionReady()) {
                            SessionToken2 sessionToken2 = new SessionToken2(new SessionToken2ImplLegacy(token));
                            token.setSessionToken2(sessionToken2);
                            onSessionToken2CreatedListener.onSessionToken2Created(token, sessionToken2);
                        }
                    }
                } catch (RemoteException e) {
                    Log.e(SessionToken2.TAG, "Failed to create session token2.", e);
                } catch (InterruptedException e2) {
                    Log.e(SessionToken2.TAG, "Failed to create session token2.", e2);
                }
            }
        });
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static String getSessionId(ResolveInfo resolveInfo) {
        if (resolveInfo == null || resolveInfo.serviceInfo == null) {
            return null;
        }
        if (resolveInfo.serviceInfo.metaData == null) {
            return "";
        }
        return resolveInfo.serviceInfo.metaData.getString(MediaSessionService2.SERVICE_META_DATA, "");
    }

    private static String getSessionIdFromService(PackageManager packageManager, String str, ComponentName componentName) {
        Intent intent = new Intent(str);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (queryIntentServices != null) {
            for (int i = 0; i < queryIntentServices.size(); i++) {
                ResolveInfo resolveInfo = queryIntentServices.get(i);
                if (resolveInfo != null && resolveInfo.serviceInfo != null && TextUtils.equals(resolveInfo.serviceInfo.name, componentName.getClassName())) {
                    return getSessionId(resolveInfo);
                }
            }
            return null;
        }
        return null;
    }
}
