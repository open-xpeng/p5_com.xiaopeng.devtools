package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes10.dex */
public abstract class ItemDetailsLookup<K> {
    @Nullable
    public abstract ItemDetails<K> getItemDetails(@NonNull MotionEvent motionEvent);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean overItem(@NonNull MotionEvent motionEvent) {
        return getItemPosition(motionEvent) != -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean overItemWithSelectionKey(@NonNull MotionEvent motionEvent) {
        return overItem(motionEvent) && hasSelectionKey(getItemDetails(motionEvent));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean inItemDragRegion(@NonNull MotionEvent motionEvent) {
        return overItem(motionEvent) && getItemDetails(motionEvent).inDragRegion(motionEvent);
    }

    final boolean inItemSelectRegion(@NonNull MotionEvent motionEvent) {
        return overItem(motionEvent) && getItemDetails(motionEvent).inSelectionHotspot(motionEvent);
    }

    final int getItemPosition(@NonNull MotionEvent motionEvent) {
        ItemDetails<K> itemDetails = getItemDetails(motionEvent);
        if (itemDetails != null) {
            return itemDetails.getPosition();
        }
        return -1;
    }

    private static boolean hasSelectionKey(@Nullable ItemDetails<?> itemDetails) {
        return (itemDetails == null || itemDetails.getSelectionKey() == null) ? false : true;
    }

    private static boolean hasPosition(@Nullable ItemDetails<?> itemDetails) {
        return (itemDetails == null || itemDetails.getPosition() == -1) ? false : true;
    }

    /* loaded from: classes10.dex */
    public static abstract class ItemDetails<K> {
        public abstract int getPosition();

        @Nullable
        public abstract K getSelectionKey();

        public boolean hasSelectionKey() {
            return getSelectionKey() != null;
        }

        public boolean inSelectionHotspot(@NonNull MotionEvent motionEvent) {
            return false;
        }

        public boolean inDragRegion(@NonNull MotionEvent motionEvent) {
            return false;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof ItemDetails) && isEqualTo((ItemDetails) obj);
        }

        private boolean isEqualTo(@NonNull ItemDetails itemDetails) {
            boolean equals;
            K selectionKey = getSelectionKey();
            if (selectionKey != null) {
                equals = selectionKey.equals(itemDetails.getSelectionKey());
            } else {
                equals = itemDetails.getSelectionKey() == null;
            }
            return equals && getPosition() == itemDetails.getPosition();
        }

        public int hashCode() {
            return getPosition() >>> 8;
        }
    }
}
