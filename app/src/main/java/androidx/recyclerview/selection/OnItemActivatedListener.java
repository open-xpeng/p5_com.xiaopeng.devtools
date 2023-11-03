package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;

/* loaded from: classes10.dex */
public interface OnItemActivatedListener<K> {
    boolean onItemActivated(@NonNull ItemDetailsLookup.ItemDetails<K> itemDetails, @NonNull MotionEvent motionEvent);
}
