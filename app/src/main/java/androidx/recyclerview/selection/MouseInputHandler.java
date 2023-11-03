package androidx.recyclerview.selection;

import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.ItemDetailsLookup;

/* loaded from: classes10.dex */
final class MouseInputHandler<K> extends MotionInputHandler<K> {
    private static final String TAG = "MouseInputDelegate";
    private final ItemDetailsLookup<K> mDetailsLookup;
    private final FocusDelegate<K> mFocusDelegate;
    private boolean mHandledOnDown;
    private boolean mHandledTapUp;
    private final OnContextClickListener mOnContextClickListener;
    private final OnItemActivatedListener<K> mOnItemActivatedListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MouseInputHandler(@NonNull SelectionTracker<K> selectionTracker, @NonNull ItemKeyProvider<K> itemKeyProvider, @NonNull ItemDetailsLookup<K> itemDetailsLookup, @NonNull OnContextClickListener onContextClickListener, @NonNull OnItemActivatedListener<K> onItemActivatedListener, @NonNull FocusDelegate<K> focusDelegate) {
        super(selectionTracker, itemKeyProvider, focusDelegate);
        Preconditions.checkArgument(itemDetailsLookup != null);
        Preconditions.checkArgument(onContextClickListener != null);
        Preconditions.checkArgument(onItemActivatedListener != null);
        this.mDetailsLookup = itemDetailsLookup;
        this.mOnContextClickListener = onContextClickListener;
        this.mOnItemActivatedListener = onItemActivatedListener;
        this.mFocusDelegate = focusDelegate;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        if ((MotionEvents.isAltKeyPressed(motionEvent) && MotionEvents.isPrimaryMouseButtonPressed(motionEvent)) || MotionEvents.isSecondaryMouseButtonPressed(motionEvent)) {
            this.mHandledOnDown = true;
            return onRightClick(motionEvent);
        }
        return false;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent2, float f, float f2) {
        return !MotionEvents.isTouchpadScroll(motionEvent2);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        if (this.mHandledOnDown) {
            this.mHandledOnDown = false;
            return false;
        } else if (!this.mDetailsLookup.overItemWithSelectionKey(motionEvent)) {
            this.mSelectionTracker.clearSelection();
            this.mFocusDelegate.clearFocus();
            return false;
        } else if (!MotionEvents.isTertiaryMouseButtonPressed(motionEvent) && this.mSelectionTracker.hasSelection()) {
            onItemClick(motionEvent, this.mDetailsLookup.getItemDetails(motionEvent));
            this.mHandledTapUp = true;
            return true;
        } else {
            return false;
        }
    }

    private void onItemClick(@NonNull MotionEvent motionEvent, @NonNull ItemDetailsLookup.ItemDetails<K> itemDetails) {
        Preconditions.checkState(this.mSelectionTracker.hasSelection());
        Preconditions.checkArgument(itemDetails != null);
        if (isRangeExtension(motionEvent)) {
            extendSelectionRange(itemDetails);
            return;
        }
        if (shouldClearSelection(motionEvent, itemDetails)) {
            this.mSelectionTracker.clearSelection();
        }
        if (this.mSelectionTracker.isSelected(itemDetails.getSelectionKey())) {
            if (this.mSelectionTracker.deselect(itemDetails.getSelectionKey())) {
                this.mFocusDelegate.clearFocus();
                return;
            }
            return;
        }
        selectOrFocusItem(itemDetails, motionEvent);
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        ItemDetailsLookup.ItemDetails<K> itemDetails;
        if (this.mHandledTapUp) {
            this.mHandledTapUp = false;
            return false;
        } else if (this.mSelectionTracker.hasSelection() || !this.mDetailsLookup.overItem(motionEvent) || MotionEvents.isTertiaryMouseButtonPressed(motionEvent) || (itemDetails = this.mDetailsLookup.getItemDetails(motionEvent)) == null || !itemDetails.hasSelectionKey()) {
            return false;
        } else {
            if (this.mFocusDelegate.hasFocusedItem() && MotionEvents.isShiftKeyPressed(motionEvent)) {
                this.mSelectionTracker.startRange(this.mFocusDelegate.getFocusedPosition());
                this.mSelectionTracker.extendRange(itemDetails.getPosition());
                return true;
            }
            selectOrFocusItem(itemDetails, motionEvent);
            return true;
        }
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        ItemDetailsLookup.ItemDetails<K> itemDetails;
        this.mHandledTapUp = false;
        return this.mDetailsLookup.overItemWithSelectionKey(motionEvent) && !MotionEvents.isTertiaryMouseButtonPressed(motionEvent) && (itemDetails = this.mDetailsLookup.getItemDetails(motionEvent)) != null && this.mOnItemActivatedListener.onItemActivated(itemDetails, motionEvent);
    }

    private boolean onRightClick(@NonNull MotionEvent motionEvent) {
        ItemDetailsLookup.ItemDetails<K> itemDetails;
        if (this.mDetailsLookup.overItemWithSelectionKey(motionEvent) && (itemDetails = this.mDetailsLookup.getItemDetails(motionEvent)) != null && !this.mSelectionTracker.isSelected(itemDetails.getSelectionKey())) {
            this.mSelectionTracker.clearSelection();
            selectItem(itemDetails);
        }
        return this.mOnContextClickListener.onContextClick(motionEvent);
    }

    private void selectOrFocusItem(@NonNull ItemDetailsLookup.ItemDetails<K> itemDetails, @NonNull MotionEvent motionEvent) {
        if (itemDetails.inSelectionHotspot(motionEvent) || MotionEvents.isCtrlKeyPressed(motionEvent)) {
            selectItem(itemDetails);
        } else {
            focusItem(itemDetails);
        }
    }
}
