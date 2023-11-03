package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.IMediaSession2;
import android.support.v4.media.SessionToken2;
import android.text.TextUtils;
import java.util.List;

/* loaded from: classes7.dex */
final class SessionToken2ImplBase implements SessionToken2.SupportLibraryImpl {
    private final ComponentName mComponentName;
    private final IMediaSession2 mISession2;
    private final String mPackageName;
    private final String mServiceName;
    private final String mSessionId;
    private final int mType;
    private final int mUid;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SessionToken2ImplBase(@NonNull Context context, @NonNull ComponentName componentName) {
        this(context, componentName, -1);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    SessionToken2ImplBase(@NonNull Context context, @NonNull ComponentName componentName, int i) {
        if (componentName == null) {
            throw new IllegalArgumentException("serviceComponent shouldn't be null");
        }
        this.mComponentName = componentName;
        this.mPackageName = componentName.getPackageName();
        this.mServiceName = componentName.getClassName();
        PackageManager packageManager = context.getPackageManager();
        if (i == -1) {
            try {
                i = packageManager.getApplicationInfo(this.mPackageName, 0).uid;
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalArgumentException("Cannot find package " + this.mPackageName);
            }
        }
        this.mUid = i;
        String sessionIdFromService = getSessionIdFromService(packageManager, MediaLibraryService2.SERVICE_INTERFACE, componentName);
        if (sessionIdFromService != null) {
            this.mSessionId = sessionIdFromService;
            this.mType = 2;
        } else {
            this.mSessionId = getSessionIdFromService(packageManager, MediaSessionService2.SERVICE_INTERFACE, componentName);
            this.mType = 1;
        }
        if (this.mSessionId == null) {
            throw new IllegalArgumentException("service " + this.mServiceName + " doesn't implement session service nor library service. Use service's full name.");
        }
        this.mISession2 = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public SessionToken2ImplBase(int i, int i2, String str, String str2, String str3, IMediaSession2 iMediaSession2) {
        this.mUid = i;
        this.mType = i2;
        this.mPackageName = str;
        this.mServiceName = str2;
        this.mComponentName = this.mType == 0 ? null : new ComponentName(str, str2);
        this.mSessionId = str3;
        this.mISession2 = iMediaSession2;
    }

    public int hashCode() {
        return this.mType + (31 * (this.mUid + ((this.mPackageName.hashCode() + ((this.mSessionId.hashCode() + ((this.mServiceName != null ? this.mServiceName.hashCode() : 0) * 31)) * 31)) * 31)));
    }

    public boolean equals(Object obj) {
        if (obj instanceof SessionToken2ImplBase) {
            SessionToken2ImplBase sessionToken2ImplBase = (SessionToken2ImplBase) obj;
            return this.mUid == sessionToken2ImplBase.mUid && TextUtils.equals(this.mPackageName, sessionToken2ImplBase.mPackageName) && TextUtils.equals(this.mServiceName, sessionToken2ImplBase.mServiceName) && TextUtils.equals(this.mSessionId, sessionToken2ImplBase.mSessionId) && this.mType == sessionToken2ImplBase.mType && sessionBinderEquals(this.mISession2, sessionToken2ImplBase.mISession2);
        }
        return false;
    }

    private boolean sessionBinderEquals(IMediaSession2 iMediaSession2, IMediaSession2 iMediaSession22) {
        if (iMediaSession2 == null || iMediaSession22 == null) {
            return iMediaSession2 == iMediaSession22;
        }
        return iMediaSession2.asBinder().equals(iMediaSession22.asBinder());
    }

    public String toString() {
        return "SessionToken {pkg=" + this.mPackageName + " id=" + this.mSessionId + " type=" + this.mType + " service=" + this.mServiceName + " IMediaSession2=" + this.mISession2 + "}";
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    public int getUid() {
        return this.mUid;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    @NonNull
    public String getPackageName() {
        return this.mPackageName;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    @Nullable
    public String getServiceName() {
        return this.mServiceName;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ComponentName getComponentName() {
        return this.mComponentName;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    public String getSessionId() {
        return this.mSessionId;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    public int getType() {
        return this.mType;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("android.media.token.uid", this.mUid);
        bundle.putString("android.media.token.package_name", this.mPackageName);
        bundle.putString("android.media.token.service_name", this.mServiceName);
        bundle.putString("android.media.token.session_id", this.mSessionId);
        bundle.putInt("android.media.token.type", this.mType);
        if (this.mISession2 != null) {
            BundleCompat.putBinder(bundle, "android.media.token.session_binder", this.mISession2.asBinder());
        }
        return bundle;
    }

    @Override // android.support.v4.media.SessionToken2.SupportLibraryImpl
    public Object getBinder() {
        if (this.mISession2 == null) {
            return null;
        }
        return this.mISession2.asBinder();
    }

    public static SessionToken2ImplBase fromBundle(@NonNull Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        int i = bundle.getInt("android.media.token.uid");
        int i2 = bundle.getInt("android.media.token.type", -1);
        String string = bundle.getString("android.media.token.package_name");
        String string2 = bundle.getString("android.media.token.service_name");
        String string3 = bundle.getString("android.media.token.session_id");
        IMediaSession2 asInterface = IMediaSession2.Stub.asInterface(BundleCompat.getBinder(bundle, "android.media.token.session_binder"));
        switch (i2) {
            case 0:
                if (asInterface == null) {
                    throw new IllegalArgumentException("Unexpected token for session, binder=" + asInterface);
                }
                break;
            case 1:
            case 2:
                if (TextUtils.isEmpty(string2)) {
                    throw new IllegalArgumentException("Session service needs service name");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }
        if (TextUtils.isEmpty(string) || string3 == null) {
            throw new IllegalArgumentException("Package name nor ID cannot be null.");
        }
        return new SessionToken2ImplBase(i, i2, string, string2, string3, asInterface);
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
