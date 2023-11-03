package android.arch.lifecycle;

import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public interface Observer<T> {
    void onChanged(@Nullable T t);
}
