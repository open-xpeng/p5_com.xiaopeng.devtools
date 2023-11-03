package com.xiaopeng.lib.framework.moduleinterface.locationmodule;

import android.content.Context;
import android.support.annotation.NonNull;
import com.xiaopeng.lib.framework.moduleinterface.locationmodule.ILocation;

/* loaded from: classes12.dex */
public interface ILocationConsumer {
    boolean connected();

    ILocation getLocation() throws ILocationServiceException;

    void init(@NonNull Context context) throws ILocationServiceException;

    void init(@NonNull Context context, @NonNull String str) throws ILocationServiceException;

    void subscribe(ILocation.Category category) throws ILocationServiceException;

    void unsubscribe() throws ILocationServiceException;
}
