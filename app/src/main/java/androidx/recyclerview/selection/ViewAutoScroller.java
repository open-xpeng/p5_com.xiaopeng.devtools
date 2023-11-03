package androidx.recyclerview.selection;

import android.graphics.Point;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes10.dex */
final class ViewAutoScroller extends AutoScroller {
    private static final float DEFAULT_SCROLL_THRESHOLD_RATIO = 0.125f;
    private static final int MAX_SCROLL_STEP = 70;
    private static final String TAG = "ViewAutoScroller";
    private final ScrollHost mHost;
    @Nullable
    private Point mLastLocation;
    @Nullable
    private Point mOrigin;
    private boolean mPassedInitialMotionThreshold;
    private final Runnable mRunner;
    private final float mScrollThresholdRatio;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewAutoScroller(@NonNull ScrollHost scrollHost) {
        this(scrollHost, DEFAULT_SCROLL_THRESHOLD_RATIO);
    }

    @VisibleForTesting
    ViewAutoScroller(@NonNull ScrollHost scrollHost, float f) {
        Preconditions.checkArgument(scrollHost != null);
        this.mHost = scrollHost;
        this.mScrollThresholdRatio = f;
        this.mRunner = new Runnable() { // from class: androidx.recyclerview.selection.ViewAutoScroller.1
            @Override // java.lang.Runnable
            public void run() {
                ViewAutoScroller.this.runScroll();
            }
        };
    }

    @Override // androidx.recyclerview.selection.AutoScroller
    public void reset() {
        this.mHost.removeCallback(this.mRunner);
        this.mOrigin = null;
        this.mLastLocation = null;
        this.mPassedInitialMotionThreshold = false;
    }

    @Override // androidx.recyclerview.selection.AutoScroller
    public void scroll(@NonNull Point point) {
        this.mLastLocation = point;
        if (this.mOrigin == null) {
            this.mOrigin = point;
        }
        this.mHost.runAtNextFrame(this.mRunner);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runScroll() {
        int i;
        int viewHeight = (int) (this.mHost.getViewHeight() * this.mScrollThresholdRatio);
        if (this.mLastLocation.y <= viewHeight) {
            i = this.mLastLocation.y - viewHeight;
        } else if (this.mLastLocation.y >= this.mHost.getViewHeight() - viewHeight) {
            i = (this.mLastLocation.y - this.mHost.getViewHeight()) + viewHeight;
        } else {
            i = 0;
        }
        if (i == 0) {
            return;
        }
        if (!this.mPassedInitialMotionThreshold && !aboveMotionThreshold(this.mLastLocation)) {
            return;
        }
        this.mPassedInitialMotionThreshold = true;
        if (i <= viewHeight) {
            viewHeight = i;
        }
        this.mHost.scrollBy(computeScrollDistance(viewHeight));
        this.mHost.removeCallback(this.mRunner);
        this.mHost.runAtNextFrame(this.mRunner);
    }

    private boolean aboveMotionThreshold(@NonNull Point point) {
        return Math.abs(this.mOrigin.y - point.y) >= ((int) ((((float) this.mHost.getViewHeight()) * this.mScrollThresholdRatio) * (this.mScrollThresholdRatio * 2.0f)));
    }

    @VisibleForTesting
    int computeScrollDistance(int i) {
        int signum = (int) Math.signum(i);
        int smoothOutOfBoundsRatio = (int) (signum * 70 * smoothOutOfBoundsRatio(Math.min(1.0f, Math.abs(i) / ((int) (this.mHost.getViewHeight() * this.mScrollThresholdRatio)))));
        return smoothOutOfBoundsRatio != 0 ? smoothOutOfBoundsRatio : signum;
    }

    private float smoothOutOfBoundsRatio(float f) {
        return (float) Math.pow(f, 10.0d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static abstract class ScrollHost {
        abstract int getViewHeight();

        abstract void removeCallback(@NonNull Runnable runnable);

        abstract void runAtNextFrame(@NonNull Runnable runnable);

        abstract void scrollBy(int i);

        ScrollHost() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ScrollHost createScrollHost(RecyclerView recyclerView) {
        return new RuntimeHost(recyclerView);
    }

    /* loaded from: classes10.dex */
    private static final class RuntimeHost extends ScrollHost {
        private final RecyclerView mRecyclerView;

        RuntimeHost(@NonNull RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        void runAtNextFrame(@NonNull Runnable runnable) {
            ViewCompat.postOnAnimation(this.mRecyclerView, runnable);
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        void removeCallback(@NonNull Runnable runnable) {
            this.mRecyclerView.removeCallbacks(runnable);
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        void scrollBy(int i) {
            this.mRecyclerView.scrollBy(0, i);
        }

        @Override // androidx.recyclerview.selection.ViewAutoScroller.ScrollHost
        int getViewHeight() {
            return this.mRecyclerView.getHeight();
        }
    }
}
