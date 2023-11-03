package androidx.core.os;

import android.os.Parcel;

@Deprecated
/* loaded from: classes8.dex */
public interface ParcelableCompatCreatorCallbacks<T> {
    T createFromParcel(Parcel parcel, ClassLoader classLoader);

    T[] newArray(int i);
}
