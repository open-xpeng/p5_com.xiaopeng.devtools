package com.xiaopeng.lib.framework.moduleinterface.locationmodule;

import android.os.Parcelable;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;

@Keep
/* loaded from: classes12.dex */
public interface ILocation extends Parcelable {

    /* loaded from: classes12.dex */
    public enum Category {
        GPS_LOCATION,
        DR_LOCATION
    }

    int accuracy();

    ILocation accuracy(int i);

    ILocation adCode(String str);

    @Nullable
    String adCode();

    int altitude();

    ILocation altitude(int i);

    int angle();

    ILocation angle(int i);

    Category category();

    ILocation category(Category category);

    ILocation city(String str);

    @Nullable
    String city();

    float latitude();

    ILocation latitude(float f);

    float longitude();

    ILocation longitude(float f);

    float rawLatitude();

    ILocation rawLatitude(float f);

    float rawLongitude();

    ILocation rawLongitude(float f);

    int satellites();

    ILocation satellites(int i);

    int sourceType();

    ILocation sourceType(int i);

    float speed();

    ILocation speed(float f);

    long time();

    ILocation time(long j);
}
