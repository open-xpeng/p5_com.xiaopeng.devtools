package androidx.recyclerview.selection;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;

/* loaded from: classes10.dex */
public abstract class FocusDelegate<K> {
    public abstract void clearFocus();

    public abstract void focusItem(@NonNull ItemDetailsLookup.ItemDetails<K> itemDetails);

    public abstract int getFocusedPosition();

    public abstract boolean hasFocusedItem();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> FocusDelegate<K> dummy() {
        return new FocusDelegate<K>() { // from class: androidx.recyclerview.selection.FocusDelegate.1
            @Override // androidx.recyclerview.selection.FocusDelegate
            public void focusItem(@NonNull ItemDetailsLookup.ItemDetails<K> itemDetails) {
            }

            @Override // androidx.recyclerview.selection.FocusDelegate
            public boolean hasFocusedItem() {
                return false;
            }

            @Override // androidx.recyclerview.selection.FocusDelegate
            public int getFocusedPosition() {
                return -1;
            }

            @Override // androidx.recyclerview.selection.FocusDelegate
            public void clearFocus() {
            }
        };
    }
}
