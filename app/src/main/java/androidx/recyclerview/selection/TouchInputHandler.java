package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;

/* loaded from: classes10.dex */
final class TouchInputHandler<K> extends MotionInputHandler<K> {
    private static final boolean DEBUG = false;
    private static final String TAG = "TouchInputDelegate";
    private final ItemDetailsLookup<K> mDetailsLookup;
    private final Runnable mGestureStarter;
    private final Runnable mHapticPerformer;
    private final OnDragInitiatedListener mOnDragInitiatedListener;
    private final OnItemActivatedListener<K> mOnItemActivatedListener;
    private final SelectionTracker.SelectionPredicate<K> mSelectionPredicate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TouchInputHandler(@NonNull SelectionTracker<K> selectionTracker, @NonNull ItemKeyProvider<K> itemKeyProvider, @NonNull ItemDetailsLookup<K> itemDetailsLookup, @NonNull SelectionTracker.SelectionPredicate<K> selectionPredicate, @NonNull Runnable runnable, @NonNull OnDragInitiatedListener onDragInitiatedListener, @NonNull OnItemActivatedListener<K> onItemActivatedListener, @NonNull FocusDelegate<K> focusDelegate, @NonNull Runnable runnable2) {
        super(selectionTracker, itemKeyProvider, focusDelegate);
        Preconditions.checkArgument(itemDetailsLookup != null);
        Preconditions.checkArgument(selectionPredicate != null);
        Preconditions.checkArgument(runnable != null);
        Preconditions.checkArgument(onItemActivatedListener != null);
        Preconditions.checkArgument(onDragInitiatedListener != null);
        Preconditions.checkArgument(runnable2 != null);
        this.mDetailsLookup = itemDetailsLookup;
        this.mSelectionPredicate = selectionPredicate;
        this.mGestureStarter = runnable;
        this.mOnItemActivatedListener = onItemActivatedListener;
        this.mOnDragInitiatedListener = onDragInitiatedListener;
        this.mHapticPerformer = runnable2;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        if (!this.mDetailsLookup.overItemWithSelectionKey(motionEvent)) {
            this.mSelectionTracker.clearSelection();
            return false;
        }
        ItemDetailsLookup.ItemDetails<K> itemDetails = this.mDetailsLookup.getItemDetails(motionEvent);
        if (itemDetails == null) {
            return false;
        }
        if (this.mSelectionTracker.hasSelection()) {
            if (isRangeExtension(motionEvent)) {
                extendSelectionRange(itemDetails);
                return true;
            } else if (this.mSelectionTracker.isSelected(itemDetails.getSelectionKey())) {
                this.mSelectionTracker.deselect(itemDetails.getSelectionKey());
                return true;
            } else {
                selectItem(itemDetails);
                return true;
            }
        } else if (itemDetails.inSelectionHotspot(motionEvent)) {
            return selectItem(itemDetails);
        } else {
            return this.mOnItemActivatedListener.onItemActivated(itemDetails, motionEvent);
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public void onLongPress(@NonNull MotionEvent motionEvent) {
        ItemDetailsLookup.ItemDetails<K> itemDetails;
        if (!this.mDetailsLookup.overItemWithSelectionKey(motionEvent) || (itemDetails = this.mDetailsLookup.getItemDetails(motionEvent)) == null) {
            return;
        }
        boolean z = true;
        if (isRangeExtension(motionEvent)) {
            extendSelectionRange(itemDetails);
        } else if (!this.mSelectionTracker.isSelected(itemDetails.getSelectionKey()) && this.mSelectionPredicate.canSetStateForKey(itemDetails.getSelectionKey(), true)) {
            if (selectItem(itemDetails)) {
                if (this.mSelectionPredicate.canSelectMultiple()) {
                    this.mGestureStarter.run();
                }
            } else {
                z = false;
            }
        } else {
            this.mOnDragInitiatedListener.onDragInitiated(motionEvent);
        }
        if (z) {
            this.mHapticPerformer.run();
        }
    }
}
