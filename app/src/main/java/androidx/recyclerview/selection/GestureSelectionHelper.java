package androidx.recyclerview.selection;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes10.dex */
final class GestureSelectionHelper implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "GestureSelectionHelper";
    private final ItemDetailsLookup<?> mDetailsLookup;
    private Point mLastInterceptedPoint;
    private final OperationMonitor mLock;
    private final AutoScroller mScroller;
    private final SelectionTracker<?> mSelectionMgr;
    private final ViewDelegate mView;
    private int mLastStartedItemPos = -1;
    private boolean mStarted = false;

    GestureSelectionHelper(@NonNull SelectionTracker<?> selectionTracker, @NonNull ItemDetailsLookup<?> itemDetailsLookup, @NonNull ViewDelegate viewDelegate, @NonNull AutoScroller autoScroller, @NonNull OperationMonitor operationMonitor) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (selectionTracker == null) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z);
        if (itemDetailsLookup == null) {
            z2 = false;
        } else {
            z2 = true;
        }
        Preconditions.checkArgument(z2);
        if (viewDelegate == null) {
            z3 = false;
        } else {
            z3 = true;
        }
        Preconditions.checkArgument(z3);
        if (autoScroller == null) {
            z4 = false;
        } else {
            z4 = true;
        }
        Preconditions.checkArgument(z4);
        Preconditions.checkArgument(operationMonitor != null);
        this.mSelectionMgr = selectionTracker;
        this.mDetailsLookup = itemDetailsLookup;
        this.mView = viewDelegate;
        this.mScroller = autoScroller;
        this.mLock = operationMonitor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void start() {
        Preconditions.checkState(!this.mStarted);
        if (this.mLastStartedItemPos < 0) {
            Log.w(TAG, "Illegal state. Can't start without valid mLastStartedItemPos.");
            return;
        }
        Preconditions.checkState(this.mSelectionMgr.isRangeActive());
        this.mLock.checkStopped();
        this.mStarted = true;
        this.mLock.start();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        MotionEvents.isMouseEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 2) {
                return this.mStarted;
            }
            return false;
        }
        return handleInterceptedDownEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        Preconditions.checkState(this.mStarted);
        switch (motionEvent.getActionMasked()) {
            case 1:
                handleUpEvent(motionEvent);
                return;
            case 2:
                handleMoveEvent(motionEvent);
                return;
            case 3:
                handleCancelEvent(motionEvent);
                return;
            default:
                return;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    private boolean handleInterceptedDownEvent(@NonNull MotionEvent motionEvent) {
        if (this.mDetailsLookup.getItemDetails(motionEvent) == null) {
            return false;
        }
        this.mLastStartedItemPos = this.mView.getItemUnder(motionEvent);
        return this.mLastStartedItemPos != -1;
    }

    private void handleUpEvent(@NonNull MotionEvent motionEvent) {
        this.mSelectionMgr.mergeProvisionalSelection();
        endSelection();
        if (this.mLastStartedItemPos > -1) {
            this.mSelectionMgr.startRange(this.mLastStartedItemPos);
        }
    }

    private void handleCancelEvent(@NonNull MotionEvent motionEvent) {
        this.mSelectionMgr.clearProvisionalSelection();
        endSelection();
    }

    private void endSelection() {
        Preconditions.checkState(this.mStarted);
        this.mLastStartedItemPos = -1;
        this.mStarted = false;
        this.mScroller.reset();
        this.mLock.stop();
    }

    private void handleMoveEvent(@NonNull MotionEvent motionEvent) {
        this.mLastInterceptedPoint = MotionEvents.getOrigin(motionEvent);
        int lastGlidedItemPosition = this.mView.getLastGlidedItemPosition(motionEvent);
        if (lastGlidedItemPosition != -1) {
            extendSelection(lastGlidedItemPosition);
        }
        this.mScroller.scroll(this.mLastInterceptedPoint);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float getInboundY(float f, float f2) {
        if (f2 < 0.0f) {
            return 0.0f;
        }
        if (f2 > f) {
            return f;
        }
        return f2;
    }

    private void extendSelection(int i) {
        this.mSelectionMgr.extendProvisionalRange(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GestureSelectionHelper create(@NonNull SelectionTracker<?> selectionTracker, @NonNull ItemDetailsLookup<?> itemDetailsLookup, @NonNull RecyclerView recyclerView, @NonNull AutoScroller autoScroller, @NonNull OperationMonitor operationMonitor) {
        return new GestureSelectionHelper(selectionTracker, itemDetailsLookup, new RecyclerViewDelegate(recyclerView), autoScroller, operationMonitor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes10.dex */
    public static abstract class ViewDelegate {
        abstract int getHeight();

        abstract int getItemUnder(@NonNull MotionEvent motionEvent);

        abstract int getLastGlidedItemPosition(@NonNull MotionEvent motionEvent);

        ViewDelegate() {
        }
    }

    @VisibleForTesting
    /* loaded from: classes10.dex */
    static final class RecyclerViewDelegate extends ViewDelegate {
        private final RecyclerView mRecyclerView;

        RecyclerViewDelegate(@NonNull RecyclerView recyclerView) {
            Preconditions.checkArgument(recyclerView != null);
            this.mRecyclerView = recyclerView;
        }

        @Override // androidx.recyclerview.selection.GestureSelectionHelper.ViewDelegate
        int getHeight() {
            return this.mRecyclerView.getHeight();
        }

        @Override // androidx.recyclerview.selection.GestureSelectionHelper.ViewDelegate
        int getItemUnder(@NonNull MotionEvent motionEvent) {
            View findChildViewUnder = this.mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder != null) {
                return this.mRecyclerView.getChildAdapterPosition(findChildViewUnder);
            }
            return -1;
        }

        @Override // androidx.recyclerview.selection.GestureSelectionHelper.ViewDelegate
        int getLastGlidedItemPosition(@NonNull MotionEvent motionEvent) {
            View childAt = this.mRecyclerView.getLayoutManager().getChildAt(this.mRecyclerView.getLayoutManager().getChildCount() - 1);
            return isPastLastItem(childAt.getTop(), childAt.getLeft(), childAt.getRight(), motionEvent, ViewCompat.getLayoutDirection(this.mRecyclerView)) ? this.mRecyclerView.getAdapter().getItemCount() - 1 : this.mRecyclerView.getChildAdapterPosition(this.mRecyclerView.findChildViewUnder(motionEvent.getX(), GestureSelectionHelper.getInboundY(this.mRecyclerView.getHeight(), motionEvent.getY())));
        }

        @VisibleForTesting
        static boolean isPastLastItem(int i, int i2, int i3, @NonNull MotionEvent motionEvent, int i4) {
            return i4 == 0 ? motionEvent.getX() > ((float) i3) && motionEvent.getY() > ((float) i) : motionEvent.getX() < ((float) i2) && motionEvent.getY() > ((float) i);
        }
    }
}
