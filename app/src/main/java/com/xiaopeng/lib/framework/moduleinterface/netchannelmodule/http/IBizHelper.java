package com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http;

import android.support.annotation.NonNull;
import java.util.Map;

/* loaded from: classes12.dex */
public interface IBizHelper {
    @NonNull
    IBizHelper appId(@NonNull String str);

    @NonNull
    IRequest build();

    @NonNull
    IRequest buildWithSecretKey(@NonNull String str);

    @NonNull
    IBizHelper customTokensForAuth(@NonNull String[] strArr);

    @NonNull
    @Deprecated
    IBizHelper enableIrdetoEncoding();

    @NonNull
    IBizHelper enableSecurityEncoding();

    @NonNull
    IBizHelper extendBizHeader(@NonNull String str, @NonNull String str2);

    @NonNull
    IBizHelper get(@NonNull String str);

    @NonNull
    IBizHelper needAuthorizationInfo();

    @NonNull
    IBizHelper needAuthorizationInfo(@NonNull Map<String, String> map);

    @NonNull
    IBizHelper post(@NonNull String str, @NonNull String str2);

    @NonNull
    IBizHelper uid(@NonNull String str);
}
