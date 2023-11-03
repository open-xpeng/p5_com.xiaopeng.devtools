package com.amap.api.services.weather;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class LocalWeatherForecast implements Parcelable {
    public static final Parcelable.Creator<LocalWeatherForecast> CREATOR = new Parcelable.Creator<LocalWeatherForecast>() { // from class: com.amap.api.services.weather.LocalWeatherForecast.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: av */
        public LocalWeatherForecast createFromParcel(Parcel parcel) {
            return new LocalWeatherForecast(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: aY */
        public LocalWeatherForecast[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private List<LocalDayWeatherForecast> bo;
    private String c;
    private String d;

    public LocalWeatherForecast() {
        this.bo = new ArrayList();
    }

    public LocalWeatherForecast(Parcel parcel) {
        this.bo = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.bo = parcel.readArrayList(LocalWeatherForecast.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeList(this.bo);
    }
}
