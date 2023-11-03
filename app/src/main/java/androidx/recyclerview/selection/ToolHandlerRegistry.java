package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes10.dex */
final class ToolHandlerRegistry<T> {
    private static final int NUM_INPUT_TYPES = 5;
    private final T mDefault;
    private final List<T> mHandlers = Arrays.asList(null, null, null, null, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ToolHandlerRegistry(@NonNull T t) {
        Preconditions.checkArgument(t != null);
        this.mDefault = t;
        for (int i = 0; i < 5; i++) {
            this.mHandlers.set(i, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void set(int i, @Nullable T t) {
        Preconditions.checkArgument(i >= 0 && i <= 4);
        Preconditions.checkState(this.mHandlers.get(i) == null);
        this.mHandlers.set(i, t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public T get(@NonNull MotionEvent motionEvent) {
        T t = this.mHandlers.get(motionEvent.getToolType(0));
        return t != null ? t : this.mDefault;
    }
}
