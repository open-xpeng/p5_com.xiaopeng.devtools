package androidx.recyclerview.selection;

import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes10.dex */
public abstract class BandPredicate {
    public abstract boolean canInitiate(MotionEvent motionEvent);

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean hasSupportedLayoutManager(@NonNull RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        return (layoutManager instanceof GridLayoutManager) || (layoutManager instanceof LinearLayoutManager);
    }

    /* loaded from: classes10.dex */
    public static final class EmptyArea extends BandPredicate {
        private final RecyclerView mRecyclerView;

        public EmptyArea(@NonNull RecyclerView recyclerView) {
            Preconditions.checkArgument(recyclerView != null);
            this.mRecyclerView = recyclerView;
        }

        @Override // androidx.recyclerview.selection.BandPredicate
        public boolean canInitiate(@NonNull MotionEvent motionEvent) {
            int i;
            if (!BandPredicate.hasSupportedLayoutManager(this.mRecyclerView) || this.mRecyclerView.hasPendingAdapterUpdates()) {
                return false;
            }
            View findChildViewUnder = this.mRecyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (findChildViewUnder != null) {
                i = this.mRecyclerView.getChildAdapterPosition(findChildViewUnder);
            } else {
                i = -1;
            }
            return i == -1;
        }
    }

    /* loaded from: classes10.dex */
    public static final class NonDraggableArea extends BandPredicate {
        private final ItemDetailsLookup mDetailsLookup;
        private final RecyclerView mRecyclerView;

        public NonDraggableArea(@NonNull RecyclerView recyclerView, @NonNull ItemDetailsLookup itemDetailsLookup) {
            Preconditions.checkArgument(recyclerView != null);
            Preconditions.checkArgument(itemDetailsLookup != null);
            this.mRecyclerView = recyclerView;
            this.mDetailsLookup = itemDetailsLookup;
        }

        @Override // androidx.recyclerview.selection.BandPredicate
        public boolean canInitiate(@NonNull MotionEvent motionEvent) {
            if (!BandPredicate.hasSupportedLayoutManager(this.mRecyclerView) || this.mRecyclerView.hasPendingAdapterUpdates()) {
                return false;
            }
            ItemDetailsLookup.ItemDetails itemDetails = this.mDetailsLookup.getItemDetails(motionEvent);
            return itemDetails == null || !itemDetails.inDragRegion(motionEvent);
        }
    }
}
