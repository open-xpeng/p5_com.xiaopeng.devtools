package androidx.recyclerview.selection;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.GridModel;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public final class DefaultBandHost<K> extends GridModel.GridHost<K> {
    private static final Rect NILL_RECT = new Rect(0, 0, 0, 0);
    private final Drawable mBand;
    private final ItemKeyProvider<K> mKeyProvider;
    private final RecyclerView mRecyclerView;
    private final SelectionTracker.SelectionPredicate<K> mSelectionPredicate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultBandHost(@NonNull RecyclerView recyclerView, @DrawableRes int i, @NonNull ItemKeyProvider<K> itemKeyProvider, @NonNull SelectionTracker.SelectionPredicate<K> selectionPredicate) {
        Preconditions.checkArgument(recyclerView != null);
        this.mRecyclerView = recyclerView;
        this.mBand = this.mRecyclerView.getContext().getResources().getDrawable(i);
        Preconditions.checkArgument(this.mBand != null);
        Preconditions.checkArgument(itemKeyProvider != null);
        Preconditions.checkArgument(selectionPredicate != null);
        this.mKeyProvider = itemKeyProvider;
        this.mSelectionPredicate = selectionPredicate;
        this.mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: androidx.recyclerview.selection.DefaultBandHost.1
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDrawOver(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                DefaultBandHost.this.onDrawBand(canvas);
            }
        });
    }

    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    GridModel<K> createGridModel() {
        return new GridModel<>(this, this.mKeyProvider, this.mSelectionPredicate);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    int getAdapterPositionAt(int i) {
        return this.mRecyclerView.getChildAdapterPosition(this.mRecyclerView.getChildAt(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    public void addOnScrollListener(@NonNull RecyclerView.OnScrollListener onScrollListener) {
        this.mRecyclerView.addOnScrollListener(onScrollListener);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    void removeOnScrollListener(@NonNull RecyclerView.OnScrollListener onScrollListener) {
        this.mRecyclerView.removeOnScrollListener(onScrollListener);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    Point createAbsolutePoint(@NonNull Point point) {
        return new Point(point.x + this.mRecyclerView.computeHorizontalScrollOffset(), point.y + this.mRecyclerView.computeVerticalScrollOffset());
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    Rect getAbsoluteRectForChildViewAt(int i) {
        View childAt = this.mRecyclerView.getChildAt(i);
        Rect rect = new Rect();
        childAt.getHitRect(rect);
        rect.left += this.mRecyclerView.computeHorizontalScrollOffset();
        rect.right += this.mRecyclerView.computeHorizontalScrollOffset();
        rect.top += this.mRecyclerView.computeVerticalScrollOffset();
        rect.bottom += this.mRecyclerView.computeVerticalScrollOffset();
        return rect;
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    int getVisibleChildCount() {
        return this.mRecyclerView.getChildCount();
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    int getColumnCount() {
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }

    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    void showBand(@NonNull Rect rect) {
        this.mBand.setBounds(rect);
        this.mRecyclerView.invalidate();
    }

    @Override // androidx.recyclerview.selection.BandSelectionHelper.BandHost
    void hideBand() {
        this.mBand.setBounds(NILL_RECT);
        this.mRecyclerView.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDrawBand(@NonNull Canvas canvas) {
        this.mBand.draw(canvas);
    }

    @Override // androidx.recyclerview.selection.GridModel.GridHost
    boolean hasView(int i) {
        return this.mRecyclerView.findViewHolderForAdapterPosition(i) != null;
    }
}
