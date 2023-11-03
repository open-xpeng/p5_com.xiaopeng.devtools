package android.arch.lifecycle;

import android.support.annotation.NonNull;

/* loaded from: classes.dex */
public interface LifecycleOwner {
    @NonNull
    Lifecycle getLifecycle();
}
