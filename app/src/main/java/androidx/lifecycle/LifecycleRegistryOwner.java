package androidx.lifecycle;

import androidx.annotation.NonNull;

@Deprecated
/* loaded from: classes9.dex */
public interface LifecycleRegistryOwner extends LifecycleOwner {
    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    LifecycleRegistry getLifecycle();
}
