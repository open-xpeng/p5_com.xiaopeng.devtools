package com.xiaopeng.lib.framework.moduleinterface.appresourcemodule;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.reactivex.Observable;

/* loaded from: classes12.dex */
public interface IAppResourceManager {

    /* loaded from: classes12.dex */
    public interface IResourceObserver {
        void onChange(@NonNull String str, @NonNull AppResourceResponse appResourceResponse);
    }

    boolean addResource(@NonNull AppResourceRequest appResourceRequest);

    Observable<AppResourceResponse> addResourceObSource(@NonNull AppResourceRequest appResourceRequest);

    void checkUpdate(@NonNull String str);

    Observable<AppResourceResponse> checkUpdateObSource(@NonNull String str);

    Observable<AppResourceResponse> clearResourceObSource();

    void clearResources();

    boolean deleteResource(@NonNull String str);

    Observable<AppResourceResponse> deleteResourceObSource(@NonNull String str);

    @Nullable
    AssetFileDescriptor getFileDescriptor(@NonNull String str);

    Observable<AssetFileDescriptor> getFileDescriptorObSource(@NonNull String str);

    @Nullable
    Uri getFullUri(String str);

    Observable<Uri> getFullUrlObSource(@NonNull String str);

    @NonNull
    Status getStatus(@NonNull String str);

    Observable<Status> getStatusObSource(@NonNull String str);

    void subscribeChanges(@NonNull IResourceObserver iResourceObserver);

    void unSubscribeChanges(@NonNull IResourceObserver iResourceObserver);

    /* loaded from: classes12.dex */
    public enum UpdatePolicy {
        LOCAL(0),
        SYNC(1),
        ASYNC(2);
        
        final int mId;

        public int id() {
            return this.mId;
        }

        UpdatePolicy(int i) {
            this.mId = i;
        }
    }

    /* loaded from: classes12.dex */
    public enum Status {
        NOT_REGISTER(0),
        NOT_AUTH(1),
        NOT_AVAILABLE(2),
        OK(3),
        ERROR(4);
        
        final int mId;

        public int id() {
            return this.mId;
        }

        Status(int i) {
            this.mId = i;
        }
    }
}
