package androidx.recyclerview.selection;

import android.graphics.Point;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public abstract class AutoScroller {
    public abstract void reset();

    public abstract void scroll(@NonNull Point point);
}
