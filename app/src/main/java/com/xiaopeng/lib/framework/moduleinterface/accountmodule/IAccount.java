package com.xiaopeng.lib.framework.moduleinterface.accountmodule;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* loaded from: classes12.dex */
public interface IAccount {
    @Nullable
    IUserInfo getUserInfo() throws AbsException;

    void init(@NonNull Application application, @Nullable String str) throws AbsException;

    void init(@NonNull Application application, @Nullable String str, @Nullable String str2) throws AbsException;

    void login() throws AbsException;

    void logout() throws AbsException;

    void requestOAuth(@Nullable ICallback<IAuthInfo, IError> iCallback);

    void requestOAuth(@NonNull String str, @Nullable ICallback<IAuthInfo, IError> iCallback);

    void requestOTP(@NonNull String str, @NonNull ICallback<IOTPInfo, IError> iCallback);
}
