package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import com.google.android.flexbox.c;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider, com.google.android.flexbox.a {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Rect hL = new Rect();
    private boolean hM;
    private b hN;
    private a hO;
    private OrientationHelper hP;
    private SavedState hQ;
    private int hR;
    private int hS;
    private SparseArray<View> hT;
    private View hU;
    private int hV;
    private int mAlignItems;
    private final Context mContext;
    private int mFlexDirection;
    private List<com.google.android.flexbox.b> mFlexLines;
    private c.a mFlexLinesResult;
    private int mFlexWrap;
    private final c mFlexboxHelper;
    private boolean mIsRtl;
    private int mJustifyContent;
    private OrientationHelper mOrientationHelper;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private RecyclerView.Recycler mRecycler;
    private RecyclerView.State mState;

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public FlexboxLayoutManager(Context context, int i) {
        this(context, i, 1);
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new c(this);
        this.hO = new a();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.hR = Integer.MIN_VALUE;
        this.hS = Integer.MIN_VALUE;
        this.hT = new SparseArray<>();
        this.hV = -1;
        this.mFlexLinesResult = new c.a();
        setFlexDirection(i);
        setFlexWrap(i2);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new c(this);
        this.hO = new a();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.hR = Integer.MIN_VALUE;
        this.hS = Integer.MIN_VALUE;
        this.hT = new SparseArray<>();
        this.hV = -1;
        this.mFlexLinesResult = new c.a();
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attributeSet, i, i2);
        switch (properties.orientation) {
            case 0:
                if (properties.reverseLayout) {
                    setFlexDirection(1);
                    break;
                } else {
                    setFlexDirection(0);
                    break;
                }
            case 1:
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                    break;
                } else {
                    setFlexDirection(2);
                    break;
                }
        }
        setFlexWrap(1);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int i) {
        if (this.mFlexDirection != i) {
            removeAllViews();
            this.mFlexDirection = i;
            this.mOrientationHelper = null;
            this.hP = null;
            ch();
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int i) {
        if (i == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        if (this.mFlexWrap != i) {
            if (this.mFlexWrap == 0 || i == 0) {
                removeAllViews();
                ch();
            }
            this.mFlexWrap = i;
            this.mOrientationHelper = null;
            this.hP = null;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.mAlignItems;
    }

    public void setAlignItems(int i) {
        if (this.mAlignItems != i) {
            if (this.mAlignItems == 4 || i == 4) {
                removeAllViews();
                ch();
            }
            this.mAlignItems = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        if (isMainAxisDirectionHorizontal()) {
            return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
        }
        return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthCrossAxis(View view) {
        if (isMainAxisDirectionHorizontal()) {
            return getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        }
        return getLeftDecorationWidth(view) + getRightDecorationWidth(view);
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexItemAdded(View view, int i, int i2, com.google.android.flexbox.b bVar) {
        calculateItemDecorationsForChild(view, hL);
        if (isMainAxisDirectionHorizontal()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            bVar.hp += leftDecorationWidth;
            bVar.hq += leftDecorationWidth;
            return;
        }
        int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
        bVar.hp += topDecorationHeight;
        bVar.hq += topDecorationHeight;
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    @Override // com.google.android.flexbox.a
    public View getFlexItemAt(int i) {
        View view = this.hT.get(i);
        if (view != null) {
            return view;
        }
        return this.mRecycler.getViewForPosition(i);
    }

    @Override // com.google.android.flexbox.a
    public View getReorderedFlexItemAt(int i) {
        return getFlexItemAt(i);
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexLineAdded(com.google.android.flexbox.b bVar) {
    }

    @Override // com.google.android.flexbox.a
    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.a
    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.mFlexLines.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, this.mFlexLines.get(i2).hp);
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.mFlexLines.get(i2).hr;
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<com.google.android.flexbox.b> list) {
        this.mFlexLines = list;
    }

    @Override // com.google.android.flexbox.a
    public List<com.google.android.flexbox.b> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    @Override // com.google.android.flexbox.a
    public void updateViewCache(int i, View view) {
        this.hT.put(i, view);
    }

    @Override // android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        if (isMainAxisDirectionHorizontal()) {
            return new PointF(0.0f, i2);
        }
        return new PointF(i2, 0.0f);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.hQ != null) {
            return new SavedState(this.hQ);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            savedState.mAnchorPosition = getPosition(childClosestToStart);
            savedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            savedState.invalidateAnchor();
        }
        return savedState;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.hQ = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        bh(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        bh(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        bh(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        bh(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        bh(Math.min(i, i2));
    }

    private void bh(int i) {
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = findLastVisibleItemPosition();
        if (i >= findLastVisibleItemPosition) {
            return;
        }
        int childCount = getChildCount();
        this.mFlexboxHelper.be(childCount);
        this.mFlexboxHelper.bd(childCount);
        this.mFlexboxHelper.bf(childCount);
        if (i >= this.mFlexboxHelper.hC.length) {
            return;
        }
        this.hV = i;
        View childClosestToStart = getChildClosestToStart();
        if (childClosestToStart == null) {
            return;
        }
        if (findFirstVisibleItemPosition <= i && i <= findLastVisibleItemPosition) {
            return;
        }
        this.mPendingScrollPosition = getPosition(childClosestToStart);
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToStart) + this.mOrientationHelper.getEndPadding();
        } else {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        this.mRecycler = recycler;
        this.mState = state;
        int itemCount = state.getItemCount();
        if (itemCount == 0 && state.isPreLayout()) {
            return;
        }
        ce();
        cg();
        ensureLayoutState();
        this.mFlexboxHelper.be(itemCount);
        this.mFlexboxHelper.bd(itemCount);
        this.mFlexboxHelper.bf(itemCount);
        this.hN.ia = false;
        if (this.hQ != null && this.hQ.bn(itemCount)) {
            this.mPendingScrollPosition = this.hQ.mAnchorPosition;
        }
        if (!this.hO.mValid || this.mPendingScrollPosition != -1 || this.hQ != null) {
            this.hO.reset();
            a(state, this.hO);
            this.hO.mValid = true;
        }
        detachAndScrapAttachedViews(recycler);
        if (this.hO.mLayoutFromEnd) {
            b(this.hO, false, true);
        } else {
            a(this.hO, false, true);
        }
        bi(itemCount);
        if (this.hO.mLayoutFromEnd) {
            a(recycler, state, this.hN);
            i2 = this.hN.mOffset;
            a(this.hO, true, false);
            a(recycler, state, this.hN);
            i = this.hN.mOffset;
        } else {
            a(recycler, state, this.hN);
            i = this.hN.mOffset;
            b(this.hO, true, false);
            a(recycler, state, this.hN);
            i2 = this.hN.mOffset;
        }
        if (getChildCount() <= 0) {
            return;
        }
        if (this.hO.mLayoutFromEnd) {
            fixLayoutStartGap(i2 + fixLayoutEndGap(i, recycler, state, true), recycler, state, false);
        } else {
            fixLayoutEndGap(i + fixLayoutStartGap(i2, recycler, state, true), recycler, state, false);
        }
    }

    private int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int startAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = a(-endAfterPadding, recycler, state);
        } else {
            int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -a(startAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (z && (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) > 0) {
            this.mOrientationHelper.offsetChildren(-startAfterPadding);
            return i2 - startAfterPadding;
        }
        return i2;
    }

    private int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int endAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int startAfterPadding = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = a(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -a(-endAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (z && (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) > 0) {
            this.mOrientationHelper.offsetChildren(endAfterPadding);
            return endAfterPadding + i2;
        }
        return i2;
    }

    private void bi(int i) {
        int i2;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        boolean z = false;
        if (isMainAxisDirectionHorizontal()) {
            if (this.hR != Integer.MIN_VALUE && this.hR != width) {
                z = true;
            }
            i2 = this.hN.mInfinite ? this.mContext.getResources().getDisplayMetrics().heightPixels : this.hN.mAvailable;
        } else {
            if (this.hS != Integer.MIN_VALUE && this.hS != height) {
                z = true;
            }
            i2 = this.hN.mInfinite ? this.mContext.getResources().getDisplayMetrics().widthPixels : this.hN.mAvailable;
        }
        int i3 = i2;
        this.hR = width;
        this.hS = height;
        if (this.hV != -1 || (this.mPendingScrollPosition == -1 && !z)) {
            int min = this.hV != -1 ? Math.min(this.hV, this.hO.mPosition) : this.hO.mPosition;
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                if (this.mFlexLines.size() > 0) {
                    this.mFlexboxHelper.c(this.mFlexLines, min);
                    this.mFlexboxHelper.a(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i3, min, this.hO.mPosition, this.mFlexLines);
                } else {
                    this.mFlexboxHelper.bf(i);
                    this.mFlexboxHelper.a(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i3, 0, this.mFlexLines);
                }
            } else if (this.mFlexLines.size() > 0) {
                this.mFlexboxHelper.c(this.mFlexLines, min);
                this.mFlexboxHelper.a(this.mFlexLinesResult, makeMeasureSpec2, makeMeasureSpec, i3, min, this.hO.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.bf(i);
                this.mFlexboxHelper.c(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i3, 0, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.a(makeMeasureSpec, makeMeasureSpec2, min);
            this.mFlexboxHelper.bc(min);
        } else if (this.hO.mLayoutFromEnd) {
        } else {
            this.mFlexLines.clear();
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                this.mFlexboxHelper.b(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i3, this.hO.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.d(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i3, this.hO.mPosition, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.c(makeMeasureSpec, makeMeasureSpec2);
            this.mFlexboxHelper.cd();
            this.hO.hW = this.mFlexboxHelper.hC[this.hO.mPosition];
            this.hN.hW = this.hO.hW;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.hQ = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.hV = -1;
        this.hO.reset();
        this.hT.clear();
    }

    private void ce() {
        int layoutDirection = getLayoutDirection();
        switch (this.mFlexDirection) {
            case 0:
                this.mIsRtl = layoutDirection == 1;
                this.hM = this.mFlexWrap == 2;
                return;
            case 1:
                this.mIsRtl = layoutDirection != 1;
                this.hM = this.mFlexWrap == 2;
                return;
            case 2:
                this.mIsRtl = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !this.mIsRtl;
                }
                this.hM = false;
                return;
            case 3:
                this.mIsRtl = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    this.mIsRtl = !this.mIsRtl;
                }
                this.hM = true;
                return;
            default:
                this.mIsRtl = false;
                this.hM = false;
                return;
        }
    }

    private void a(RecyclerView.State state, a aVar) {
        if (a(state, aVar, this.hQ) || b(state, aVar)) {
            return;
        }
        aVar.assignCoordinateFromPadding();
        aVar.mPosition = 0;
        aVar.hW = 0;
    }

    private boolean a(RecyclerView.State state, a aVar, SavedState savedState) {
        int decoratedStart;
        if (state.isPreLayout() || this.mPendingScrollPosition == -1) {
            return false;
        }
        if (this.mPendingScrollPosition < 0 || this.mPendingScrollPosition >= state.getItemCount()) {
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            return false;
        }
        aVar.mPosition = this.mPendingScrollPosition;
        aVar.hW = this.mFlexboxHelper.hC[aVar.mPosition];
        if (this.hQ == null || !this.hQ.bn(state.getItemCount())) {
            if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                View findViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                if (findViewByPosition != null) {
                    if (this.mOrientationHelper.getDecoratedMeasurement(findViewByPosition) <= this.mOrientationHelper.getTotalSpace()) {
                        if (this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding() >= 0) {
                            if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition) < 0) {
                                aVar.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                                aVar.mLayoutFromEnd = true;
                                return true;
                            }
                            if (aVar.mLayoutFromEnd) {
                                decoratedStart = this.mOrientationHelper.getDecoratedEnd(findViewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
                            } else {
                                decoratedStart = this.mOrientationHelper.getDecoratedStart(findViewByPosition);
                            }
                            aVar.mCoordinate = decoratedStart;
                        } else {
                            aVar.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                            aVar.mLayoutFromEnd = false;
                            return true;
                        }
                    } else {
                        aVar.assignCoordinateFromPadding();
                        return true;
                    }
                } else {
                    if (getChildCount() > 0) {
                        aVar.mLayoutFromEnd = this.mPendingScrollPosition < getPosition(getChildAt(0));
                    }
                    aVar.assignCoordinateFromPadding();
                }
                return true;
            }
            if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                aVar.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
            } else {
                aVar.mCoordinate = this.mPendingScrollPositionOffset - this.mOrientationHelper.getEndPadding();
            }
            return true;
        }
        aVar.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + savedState.mAnchorOffset;
        aVar.hY = true;
        aVar.hW = -1;
        return true;
    }

    private boolean b(RecyclerView.State state, a aVar) {
        View bj;
        int startAfterPadding;
        boolean z = false;
        if (getChildCount() == 0) {
            return false;
        }
        if (aVar.mLayoutFromEnd) {
            bj = bk(state.getItemCount());
        } else {
            bj = bj(state.getItemCount());
        }
        if (bj != null) {
            aVar.assignFromView(bj);
            if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
                if (this.mOrientationHelper.getDecoratedStart(bj) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(bj) < this.mOrientationHelper.getStartAfterPadding()) {
                    z = true;
                }
                if (z) {
                    if (aVar.mLayoutFromEnd) {
                        startAfterPadding = this.mOrientationHelper.getEndAfterPadding();
                    } else {
                        startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                    }
                    aVar.mCoordinate = startAfterPadding;
                }
            }
            return true;
        }
        return false;
    }

    private View bj(int i) {
        View c = c(0, getChildCount(), i);
        if (c == null) {
            return null;
        }
        int i2 = this.mFlexboxHelper.hC[getPosition(c)];
        if (i2 == -1) {
            return null;
        }
        return a(c, this.mFlexLines.get(i2));
    }

    private View bk(int i) {
        View c = c(getChildCount() - 1, -1, i);
        if (c == null) {
            return null;
        }
        return b(c, this.mFlexLines.get(this.mFlexboxHelper.hC[getPosition(c)]));
    }

    private View c(int i, int i2, int i3) {
        cg();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.mOrientationHelper.getDecoratedStart(childAt) < startAfterPadding || this.mOrientationHelper.getDecoratedEnd(childAt) > endAfterPadding) {
                    if (view == null) {
                        view = childAt;
                    }
                } else {
                    return childAt;
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    private int a(RecyclerView.Recycler recycler, RecyclerView.State state, b bVar) {
        if (bVar.mScrollingOffset != Integer.MIN_VALUE) {
            if (bVar.mAvailable < 0) {
                bVar.mScrollingOffset += bVar.mAvailable;
            }
            a(recycler, bVar);
        }
        int i = bVar.mAvailable;
        int i2 = bVar.mAvailable;
        int i3 = 0;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        while (true) {
            if ((i2 > 0 || this.hN.mInfinite) && bVar.a(state, this.mFlexLines)) {
                com.google.android.flexbox.b bVar2 = this.mFlexLines.get(bVar.hW);
                bVar.mPosition = bVar2.hy;
                i3 += a(bVar2, bVar);
                if (isMainAxisDirectionHorizontal || !this.mIsRtl) {
                    bVar.mOffset += bVar2.cb() * bVar.mLayoutDirection;
                } else {
                    bVar.mOffset -= bVar2.cb() * bVar.mLayoutDirection;
                }
                i2 -= bVar2.cb();
            }
        }
        bVar.mAvailable -= i3;
        if (bVar.mScrollingOffset != Integer.MIN_VALUE) {
            bVar.mScrollingOffset += i3;
            if (bVar.mAvailable < 0) {
                bVar.mScrollingOffset += bVar.mAvailable;
            }
            a(recycler, bVar);
        }
        return i - bVar.mAvailable;
    }

    private void a(RecyclerView.Recycler recycler, b bVar) {
        if (!bVar.ia) {
            return;
        }
        if (bVar.mLayoutDirection == -1) {
            c(recycler, bVar);
        } else {
            b(recycler, bVar);
        }
    }

    private void b(RecyclerView.Recycler recycler, b bVar) {
        int childCount;
        if (bVar.mScrollingOffset < 0 || (childCount = getChildCount()) == 0) {
            return;
        }
        int i = this.mFlexboxHelper.hC[getPosition(getChildAt(0))];
        if (i == -1) {
            return;
        }
        com.google.android.flexbox.b bVar2 = this.mFlexLines.get(i);
        int i2 = -1;
        int i3 = i;
        int i4 = 0;
        while (i4 < childCount) {
            View childAt = getChildAt(i4);
            if (!b(childAt, bVar.mScrollingOffset)) {
                break;
            }
            if (bVar2.hz == getPosition(childAt)) {
                if (i3 >= this.mFlexLines.size() - 1) {
                    break;
                }
                i3 += bVar.mLayoutDirection;
                bVar2 = this.mFlexLines.get(i3);
                i2 = i4;
            }
            i4++;
        }
        i4 = i2;
        recycleChildren(recycler, 0, i4);
    }

    private boolean b(View view, int i) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedEnd(view) <= i : this.mOrientationHelper.getEnd() - this.mOrientationHelper.getDecoratedStart(view) <= i;
    }

    private void c(RecyclerView.Recycler recycler, b bVar) {
        if (bVar.mScrollingOffset < 0) {
            return;
        }
        this.mOrientationHelper.getEnd();
        int unused = bVar.mScrollingOffset;
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int i = childCount - 1;
        int i2 = this.mFlexboxHelper.hC[getPosition(getChildAt(i))];
        if (i2 == -1) {
            return;
        }
        com.google.android.flexbox.b bVar2 = this.mFlexLines.get(i2);
        int i3 = childCount;
        int i4 = i;
        while (i4 >= 0) {
            View childAt = getChildAt(i4);
            if (!c(childAt, bVar.mScrollingOffset)) {
                break;
            }
            if (bVar2.hy == getPosition(childAt)) {
                if (i2 <= 0) {
                    break;
                }
                i2 += bVar.mLayoutDirection;
                bVar2 = this.mFlexLines.get(i2);
                i3 = i4;
            }
            i4--;
        }
        i4 = i3;
        recycleChildren(recycler, i4, i);
    }

    private boolean c(View view, int i) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEnd() - i : this.mOrientationHelper.getDecoratedEnd(view) <= i;
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    private int a(com.google.android.flexbox.b bVar, b bVar2) {
        if (isMainAxisDirectionHorizontal()) {
            return b(bVar, bVar2);
        }
        return c(bVar, bVar2);
    }

    private int b(com.google.android.flexbox.b bVar, b bVar2) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        int i;
        LayoutParams layoutParams;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int width = getWidth();
        int i2 = bVar2.mOffset;
        if (bVar2.mLayoutDirection == -1) {
            i2 -= bVar.hr;
        }
        int i3 = i2;
        int i4 = bVar2.mPosition;
        int i5 = 1;
        switch (this.mJustifyContent) {
            case 0:
                f = paddingLeft;
                f2 = width - paddingRight;
                f3 = 0.0f;
                break;
            case 1:
                float f6 = (width - bVar.hp) + paddingRight;
                f3 = 0.0f;
                f2 = bVar.hp - paddingLeft;
                f = f6;
                break;
            case 2:
                f = paddingLeft + ((width - bVar.hp) / 2.0f);
                f2 = (width - paddingRight) - ((width - bVar.hp) / 2.0f);
                f3 = 0.0f;
                break;
            case 3:
                f = paddingLeft;
                f3 = (width - bVar.hp) / (bVar.mItemCount != 1 ? bVar.mItemCount - 1 : 1.0f);
                f2 = width - paddingRight;
                break;
            case 4:
                if (bVar.mItemCount != 0) {
                    f3 = (width - bVar.hp) / bVar.mItemCount;
                } else {
                    f3 = 0.0f;
                }
                float f7 = f3 / 2.0f;
                f = paddingLeft + f7;
                f2 = (width - paddingRight) - f7;
                break;
            default:
                throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
        }
        float f8 = f - this.hO.hX;
        float f9 = f2 - this.hO.hX;
        float max = Math.max(f3, 0.0f);
        int i6 = 0;
        int itemCount = bVar.getItemCount();
        int i7 = i4;
        while (i7 < i4 + itemCount) {
            View flexItemAt = getFlexItemAt(i7);
            if (flexItemAt == null) {
                i = i3;
            } else {
                if (bVar2.mLayoutDirection == i5) {
                    calculateItemDecorationsForChild(flexItemAt, hL);
                    addView(flexItemAt);
                } else {
                    calculateItemDecorationsForChild(flexItemAt, hL);
                    addView(flexItemAt, i6);
                    i6++;
                }
                int i8 = i6;
                long j = this.mFlexboxHelper.hD[i7];
                int c = this.mFlexboxHelper.c(j);
                int d = this.mFlexboxHelper.d(j);
                LayoutParams layoutParams2 = (LayoutParams) flexItemAt.getLayoutParams();
                if (shouldMeasureChild(flexItemAt, c, d, layoutParams2)) {
                    flexItemAt.measure(c, d);
                }
                float leftDecorationWidth = f8 + layoutParams2.leftMargin + getLeftDecorationWidth(flexItemAt);
                float rightDecorationWidth = f9 - (layoutParams2.rightMargin + getRightDecorationWidth(flexItemAt));
                int topDecorationHeight = i3 + getTopDecorationHeight(flexItemAt);
                if (this.mIsRtl) {
                    f4 = rightDecorationWidth;
                    f5 = leftDecorationWidth;
                    i = i3;
                    layoutParams = layoutParams2;
                    this.mFlexboxHelper.a(flexItemAt, bVar, Math.round(rightDecorationWidth) - flexItemAt.getMeasuredWidth(), topDecorationHeight, Math.round(rightDecorationWidth), topDecorationHeight + flexItemAt.getMeasuredHeight());
                } else {
                    f4 = rightDecorationWidth;
                    f5 = leftDecorationWidth;
                    i = i3;
                    layoutParams = layoutParams2;
                    this.mFlexboxHelper.a(flexItemAt, bVar, Math.round(f5), topDecorationHeight, Math.round(f5) + flexItemAt.getMeasuredWidth(), topDecorationHeight + flexItemAt.getMeasuredHeight());
                }
                f9 = f4 - (((flexItemAt.getMeasuredWidth() + layoutParams.leftMargin) + getLeftDecorationWidth(flexItemAt)) + max);
                f8 = f5 + flexItemAt.getMeasuredWidth() + layoutParams.rightMargin + getRightDecorationWidth(flexItemAt) + max;
                i6 = i8;
            }
            i7++;
            i3 = i;
            i5 = 1;
        }
        bVar2.hW += this.hN.mLayoutDirection;
        return bVar.cb();
    }

    private int c(com.google.android.flexbox.b bVar, b bVar2) {
        float f;
        float f2;
        float f3;
        LayoutParams layoutParams;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        float f4;
        float f5;
        View view;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i5 = bVar2.mOffset;
        int i6 = bVar2.mOffset;
        if (bVar2.mLayoutDirection == -1) {
            i5 -= bVar.hr;
            i6 += bVar.hr;
        }
        int i7 = i5;
        int i8 = i6;
        int i9 = bVar2.mPosition;
        boolean z2 = true;
        switch (this.mJustifyContent) {
            case 0:
                f = paddingTop;
                f2 = height - paddingBottom;
                f3 = 0.0f;
                break;
            case 1:
                float f6 = (height - bVar.hp) + paddingBottom;
                f3 = 0.0f;
                f2 = bVar.hp - paddingTop;
                f = f6;
                break;
            case 2:
                f = paddingTop + ((height - bVar.hp) / 2.0f);
                f2 = (height - paddingBottom) - ((height - bVar.hp) / 2.0f);
                f3 = 0.0f;
                break;
            case 3:
                f = paddingTop;
                f3 = (height - bVar.hp) / (bVar.mItemCount != 1 ? bVar.mItemCount - 1 : 1.0f);
                f2 = height - paddingBottom;
                break;
            case 4:
                if (bVar.mItemCount != 0) {
                    f3 = (height - bVar.hp) / bVar.mItemCount;
                } else {
                    f3 = 0.0f;
                }
                float f7 = f3 / 2.0f;
                f = paddingTop + f7;
                f2 = (height - paddingBottom) - f7;
                break;
            default:
                throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
        }
        float f8 = f - this.hO.hX;
        float f9 = f2 - this.hO.hX;
        float max = Math.max(f3, 0.0f);
        int i10 = 0;
        int itemCount = bVar.getItemCount();
        int i11 = i9;
        while (i11 < i9 + itemCount) {
            View flexItemAt = getFlexItemAt(i11);
            if (flexItemAt == null) {
                i = i11;
                i2 = i7;
                i3 = i8;
                i4 = i9;
                z = z2;
            } else {
                long j = this.mFlexboxHelper.hD[i11];
                int c = this.mFlexboxHelper.c(j);
                int d = this.mFlexboxHelper.d(j);
                if (shouldMeasureChild(flexItemAt, c, d, (LayoutParams) flexItemAt.getLayoutParams())) {
                    flexItemAt.measure(c, d);
                }
                float topDecorationHeight = f8 + layoutParams.topMargin + getTopDecorationHeight(flexItemAt);
                float bottomDecorationHeight = f9 - (layoutParams.rightMargin + getBottomDecorationHeight(flexItemAt));
                if (bVar2.mLayoutDirection == 1) {
                    calculateItemDecorationsForChild(flexItemAt, hL);
                    addView(flexItemAt);
                } else {
                    calculateItemDecorationsForChild(flexItemAt, hL);
                    addView(flexItemAt, i10);
                    i10++;
                }
                int i12 = i10;
                int leftDecorationWidth = i7 + getLeftDecorationWidth(flexItemAt);
                int rightDecorationWidth = i8 - getRightDecorationWidth(flexItemAt);
                if (this.mIsRtl) {
                    if (this.hM) {
                        z = true;
                        i2 = i7;
                        f4 = bottomDecorationHeight;
                        i3 = i8;
                        f5 = topDecorationHeight;
                        i4 = i9;
                        view = flexItemAt;
                        i = i11;
                        this.mFlexboxHelper.a(flexItemAt, bVar, this.mIsRtl, rightDecorationWidth - flexItemAt.getMeasuredWidth(), Math.round(bottomDecorationHeight) - flexItemAt.getMeasuredHeight(), rightDecorationWidth, Math.round(bottomDecorationHeight));
                    } else {
                        z = true;
                        i = i11;
                        i2 = i7;
                        i3 = i8;
                        i4 = i9;
                        f4 = bottomDecorationHeight;
                        f5 = topDecorationHeight;
                        view = flexItemAt;
                        this.mFlexboxHelper.a(view, bVar, this.mIsRtl, rightDecorationWidth - view.getMeasuredWidth(), Math.round(f5), rightDecorationWidth, Math.round(f5) + view.getMeasuredHeight());
                    }
                } else {
                    z = true;
                    i = i11;
                    i2 = i7;
                    i3 = i8;
                    i4 = i9;
                    f4 = bottomDecorationHeight;
                    f5 = topDecorationHeight;
                    view = flexItemAt;
                    if (this.hM) {
                        this.mFlexboxHelper.a(view, bVar, this.mIsRtl, leftDecorationWidth, Math.round(f4) - view.getMeasuredHeight(), leftDecorationWidth + view.getMeasuredWidth(), Math.round(f4));
                    } else {
                        this.mFlexboxHelper.a(view, bVar, this.mIsRtl, leftDecorationWidth, Math.round(f5), leftDecorationWidth + view.getMeasuredWidth(), Math.round(f5) + view.getMeasuredHeight());
                    }
                }
                f9 = f4 - (((view.getMeasuredHeight() + layoutParams.bottomMargin) + getTopDecorationHeight(view)) + max);
                f8 = f5 + view.getMeasuredHeight() + layoutParams.topMargin + getBottomDecorationHeight(view) + max;
                i10 = i12;
            }
            i11 = i + 1;
            z2 = z;
            i7 = i2;
            i8 = i3;
            i9 = i4;
        }
        bVar2.hW += this.hN.mLayoutDirection;
        return bVar.cb();
    }

    @Override // com.google.android.flexbox.a
    public boolean isMainAxisDirectionHorizontal() {
        return this.mFlexDirection == 0 || this.mFlexDirection == 1;
    }

    private void a(a aVar, boolean z, boolean z2) {
        if (z2) {
            cf();
        } else {
            this.hN.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.hN.mAvailable = aVar.mCoordinate - getPaddingRight();
        } else {
            this.hN.mAvailable = this.mOrientationHelper.getEndAfterPadding() - aVar.mCoordinate;
        }
        this.hN.mPosition = aVar.mPosition;
        this.hN.mItemDirection = 1;
        this.hN.mLayoutDirection = 1;
        this.hN.mOffset = aVar.mCoordinate;
        this.hN.mScrollingOffset = Integer.MIN_VALUE;
        this.hN.hW = aVar.hW;
        if (z && this.mFlexLines.size() > 1 && aVar.hW >= 0 && aVar.hW < this.mFlexLines.size() - 1) {
            b.i(this.hN);
            this.hN.mPosition += this.mFlexLines.get(aVar.hW).getItemCount();
        }
    }

    private void b(a aVar, boolean z, boolean z2) {
        if (z2) {
            cf();
        } else {
            this.hN.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.hN.mAvailable = (this.hU.getWidth() - aVar.mCoordinate) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.hN.mAvailable = aVar.mCoordinate - this.mOrientationHelper.getStartAfterPadding();
        }
        this.hN.mPosition = aVar.mPosition;
        this.hN.mItemDirection = 1;
        this.hN.mLayoutDirection = -1;
        this.hN.mOffset = aVar.mCoordinate;
        this.hN.mScrollingOffset = Integer.MIN_VALUE;
        this.hN.hW = aVar.hW;
        if (z && aVar.hW > 0 && this.mFlexLines.size() > aVar.hW) {
            b.j(this.hN);
            this.hN.mPosition -= this.mFlexLines.get(aVar.hW).getItemCount();
        }
    }

    private void cf() {
        int widthMode;
        if (isMainAxisDirectionHorizontal()) {
            widthMode = getHeightMode();
        } else {
            widthMode = getWidthMode();
        }
        this.hN.mInfinite = widthMode == 0 || widthMode == Integer.MIN_VALUE;
    }

    private void cg() {
        if (this.mOrientationHelper != null) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexWrap == 0) {
                this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                this.hP = OrientationHelper.createVerticalHelper(this);
                return;
            }
            this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
            this.hP = OrientationHelper.createHorizontalHelper(this);
        } else if (this.mFlexWrap == 0) {
            this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
            this.hP = OrientationHelper.createHorizontalHelper(this);
        } else {
            this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            this.hP = OrientationHelper.createVerticalHelper(this);
        }
    }

    private void ensureLayoutState() {
        if (this.hN == null) {
            this.hN = new b();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        if (this.hQ != null) {
            this.hQ.invalidateAnchor();
        }
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.hU = (View) recyclerView.getParent();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return !isMainAxisDirectionHorizontal() || getWidth() > this.hU.getWidth();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return isMainAxisDirectionHorizontal() || getHeight() > this.hU.getHeight();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isMainAxisDirectionHorizontal()) {
            int a2 = a(i, recycler, state);
            this.hT.clear();
            return a2;
        }
        int bl = bl(i);
        this.hO.hX += bl;
        this.hP.offsetChildren(-bl);
        return bl;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isMainAxisDirectionHorizontal()) {
            int a2 = a(i, recycler, state);
            this.hT.clear();
            return a2;
        }
        int bl = bl(i);
        this.hO.hX += bl;
        this.hP.offsetChildren(-bl);
        return bl;
    }

    private int a(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        cg();
        int i2 = 1;
        this.hN.ia = true;
        boolean z = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int abs = Math.abs(i);
        d(i2, abs);
        int a2 = this.hN.mScrollingOffset + a(recycler, state, this.hN);
        if (a2 < 0) {
            return 0;
        }
        if (z) {
            if (abs > a2) {
                i = (-i2) * a2;
            }
        } else if (abs > a2) {
            i = i2 * a2;
        }
        this.mOrientationHelper.offsetChildren(-i);
        this.hN.mLastScrollDelta = i;
        return i;
    }

    private int bl(int i) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        cg();
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int width = isMainAxisDirectionHorizontal ? this.hU.getWidth() : this.hU.getHeight();
        int width2 = isMainAxisDirectionHorizontal ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            int abs = Math.abs(i);
            if (i < 0) {
                return -Math.min((width2 + this.hO.hX) - width, abs);
            }
            if (this.hO.hX + i <= 0) {
                return i;
            }
            return -this.hO.hX;
        } else if (i > 0) {
            return Math.min((width2 - this.hO.hX) - width, i);
        } else {
            if (this.hO.hX + i >= 0) {
                return i;
            }
            return -this.hO.hX;
        }
    }

    private void d(int i, int i2) {
        this.hN.mLayoutDirection = i;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int i3 = 0;
        boolean z = !isMainAxisDirectionHorizontal && this.mIsRtl;
        if (i == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            this.hN.mOffset = this.mOrientationHelper.getDecoratedEnd(childAt);
            int position = getPosition(childAt);
            View b2 = b(childAt, this.mFlexLines.get(this.mFlexboxHelper.hC[position]));
            this.hN.mItemDirection = 1;
            this.hN.mPosition = position + this.hN.mItemDirection;
            if (this.mFlexboxHelper.hC.length <= this.hN.mPosition) {
                this.hN.hW = -1;
            } else {
                this.hN.hW = this.mFlexboxHelper.hC[this.hN.mPosition];
            }
            if (z) {
                this.hN.mOffset = this.mOrientationHelper.getDecoratedStart(b2);
                this.hN.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(b2)) + this.mOrientationHelper.getStartAfterPadding();
                b bVar = this.hN;
                if (this.hN.mScrollingOffset >= 0) {
                    i3 = this.hN.mScrollingOffset;
                }
                bVar.mScrollingOffset = i3;
            } else {
                this.hN.mOffset = this.mOrientationHelper.getDecoratedEnd(b2);
                this.hN.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(b2) - this.mOrientationHelper.getEndAfterPadding();
            }
            if ((this.hN.hW == -1 || this.hN.hW > this.mFlexLines.size() - 1) && this.hN.mPosition <= getFlexItemCount()) {
                int i4 = i2 - this.hN.mScrollingOffset;
                this.mFlexLinesResult.reset();
                if (i4 > 0) {
                    if (isMainAxisDirectionHorizontal) {
                        this.mFlexboxHelper.a(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i4, this.hN.mPosition, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.c(this.mFlexLinesResult, makeMeasureSpec, makeMeasureSpec2, i4, this.hN.mPosition, this.mFlexLines);
                    }
                    this.mFlexboxHelper.a(makeMeasureSpec, makeMeasureSpec2, this.hN.mPosition);
                    this.mFlexboxHelper.bc(this.hN.mPosition);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            this.hN.mOffset = this.mOrientationHelper.getDecoratedStart(childAt2);
            int position2 = getPosition(childAt2);
            View a2 = a(childAt2, this.mFlexLines.get(this.mFlexboxHelper.hC[position2]));
            this.hN.mItemDirection = 1;
            int i5 = this.mFlexboxHelper.hC[position2];
            if (i5 == -1) {
                i5 = 0;
            }
            if (i5 > 0) {
                this.hN.mPosition = position2 - this.mFlexLines.get(i5 - 1).getItemCount();
            } else {
                this.hN.mPosition = -1;
            }
            this.hN.hW = i5 > 0 ? i5 - 1 : 0;
            if (z) {
                this.hN.mOffset = this.mOrientationHelper.getDecoratedEnd(a2);
                this.hN.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(a2) - this.mOrientationHelper.getEndAfterPadding();
                b bVar2 = this.hN;
                if (this.hN.mScrollingOffset >= 0) {
                    i3 = this.hN.mScrollingOffset;
                }
                bVar2.mScrollingOffset = i3;
            } else {
                this.hN.mOffset = this.mOrientationHelper.getDecoratedStart(a2);
                this.hN.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(a2)) + this.mOrientationHelper.getStartAfterPadding();
            }
        }
        this.hN.mAvailable = i2 - this.hN.mScrollingOffset;
    }

    private View a(View view, com.google.android.flexbox.b bVar) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int i = bVar.mItemCount;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (this.mIsRtl && !isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    private View b(View view, com.google.android.flexbox.b bVar) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int childCount = (getChildCount() - bVar.mItemCount) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (this.mIsRtl && !isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        cg();
        View bj = bj(itemCount);
        View bk = bk(itemCount);
        if (state.getItemCount() == 0 || bj == null || bk == null) {
            return 0;
        }
        return Math.min(this.mOrientationHelper.getTotalSpace(), this.mOrientationHelper.getDecoratedEnd(bk) - this.mOrientationHelper.getDecoratedStart(bj));
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        computeScrollOffset(state);
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View bj = bj(itemCount);
        View bk = bk(itemCount);
        if (state.getItemCount() == 0 || bj == null || bk == null) {
            return 0;
        }
        int position = getPosition(bj);
        int position2 = getPosition(bk);
        int abs = Math.abs(this.mOrientationHelper.getDecoratedEnd(bk) - this.mOrientationHelper.getDecoratedStart(bj));
        int i = this.mFlexboxHelper.hC[position];
        if (i == 0 || i == -1) {
            return 0;
        }
        return Math.round((i * (abs / ((this.mFlexboxHelper.hC[position2] - i) + 1))) + (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(bj)));
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View bj = bj(itemCount);
        View bk = bk(itemCount);
        if (state.getItemCount() == 0 || bj == null || bk == null) {
            return 0;
        }
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((Math.abs(this.mOrientationHelper.getDecoratedEnd(bk) - this.mOrientationHelper.getDecoratedStart(bj)) / ((findLastVisibleItemPosition() - findFirstVisibleItemPosition) + 1)) * state.getItemCount());
    }

    private boolean shouldMeasureChild(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && isMeasurementUpToDate(view.getWidth(), i, layoutParams.width) && isMeasurementUpToDate(view.getHeight(), i2, layoutParams.height)) ? false : true;
    }

    private static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        } else if (mode != 0) {
            return mode == 1073741824 && size == i;
        } else {
            return true;
        }
    }

    private void ch() {
        this.mFlexLines.clear();
        this.hO.reset();
        this.hO.hX = 0;
    }

    private int a(View view) {
        return getDecoratedLeft(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).leftMargin;
    }

    private int b(View view) {
        return getDecoratedRight(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).rightMargin;
    }

    private int c(View view) {
        return getDecoratedTop(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).topMargin;
    }

    private int d(View view) {
        return getDecoratedBottom(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).bottomMargin;
    }

    private boolean c(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int a2 = a(view);
        int c = c(view);
        int b2 = b(view);
        int d = d(view);
        return z ? (paddingLeft <= a2 && width >= b2) && (paddingTop <= c && height >= d) : (a2 >= width || b2 >= paddingLeft) && (c >= height || d >= paddingTop);
    }

    public int findFirstVisibleItemPosition() {
        View a2 = a(0, getChildCount(), false);
        if (a2 == null) {
            return -1;
        }
        return getPosition(a2);
    }

    public int findLastVisibleItemPosition() {
        View a2 = a(getChildCount() - 1, -1, false);
        if (a2 == null) {
            return -1;
        }
        return getPosition(a2);
    }

    private View a(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (!c(childAt, z)) {
                i += i3;
            } else {
                return childAt;
            }
        }
        return null;
    }

    /* loaded from: classes11.dex */
    public static class LayoutParams extends RecyclerView.LayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.LayoutParams.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: ay */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: bm */
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        };
        private float hG;
        private float hH;
        private int hI;
        private float hJ;
        private boolean hK;
        private int mMaxHeight;
        private int mMaxWidth;
        private int mMinHeight;
        private int mMinWidth;

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return this.width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return this.height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float bS() {
            return this.hG;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float bT() {
            return this.hH;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int bU() {
            return this.hI;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinWidth() {
            return this.mMinWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinHeight() {
            return this.mMinHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean bV() {
            return this.hK;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float bW() {
            return this.hJ;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int bX() {
            return this.leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int bY() {
            return this.topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int bZ() {
            return this.rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int ca() {
            return this.bottomMargin;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return 1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.hG);
            parcel.writeFloat(this.hH);
            parcel.writeInt(this.hI);
            parcel.writeFloat(this.hJ);
            parcel.writeInt(this.mMinWidth);
            parcel.writeInt(this.mMinHeight);
            parcel.writeInt(this.mMaxWidth);
            parcel.writeInt(this.mMaxHeight);
            parcel.writeByte(this.hK ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        protected LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            this.hG = parcel.readFloat();
            this.hH = parcel.readFloat();
            this.hI = parcel.readInt();
            this.hJ = parcel.readFloat();
            this.mMinWidth = parcel.readInt();
            this.mMinHeight = parcel.readInt();
            this.mMaxWidth = parcel.readInt();
            this.mMaxHeight = parcel.readInt();
            this.hK = parcel.readByte() != 0;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class a {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int hW;
        private int hX;
        private boolean hY;
        private int mCoordinate;
        private boolean mLayoutFromEnd;
        private int mPosition;
        private boolean mValid;

        private a() {
            this.hX = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mPosition = -1;
            this.hW = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mValid = false;
            this.hY = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 1;
                } else {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
                }
            } else if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 3;
            } else {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignCoordinateFromPadding() {
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.mIsRtl) {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            } else {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignFromView(View view) {
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
                } else {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view);
                }
            } else if (this.mLayoutFromEnd) {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = FlexboxLayoutManager.this.getPosition(view);
            this.hY = false;
            int i = FlexboxLayoutManager.this.mFlexboxHelper.hC[this.mPosition];
            this.hW = i != -1 ? i : 0;
            if (FlexboxLayoutManager.this.mFlexLines.size() > this.hW) {
                this.mPosition = ((com.google.android.flexbox.b) FlexboxLayoutManager.this.mFlexLines.get(this.hW)).hy;
            }
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mFlexLinePosition=" + this.hW + ", mCoordinate=" + this.mCoordinate + ", mPerpendicularCoordinate=" + this.hX + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + ", mAssignedFromSavedState=" + this.hY + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class b {
        private int hW;
        private boolean ia;
        private int mAvailable;
        private boolean mInfinite;
        private int mItemDirection;
        private int mLastScrollDelta;
        private int mLayoutDirection;
        private int mOffset;
        private int mPosition;
        private int mScrollingOffset;

        private b() {
            this.mItemDirection = 1;
            this.mLayoutDirection = 1;
        }

        static /* synthetic */ int i(b bVar) {
            int i = bVar.hW;
            bVar.hW = i + 1;
            return i;
        }

        static /* synthetic */ int j(b bVar) {
            int i = bVar.hW;
            bVar.hW = i - 1;
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a(RecyclerView.State state, List<com.google.android.flexbox.b> list) {
            return this.mPosition >= 0 && this.mPosition < state.getItemCount() && this.hW >= 0 && this.hW < list.size();
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.mAvailable + ", mFlexLinePosition=" + this.hW + ", mPosition=" + this.mPosition + ", mOffset=" + this.mOffset + ", mScrollingOffset=" + this.mScrollingOffset + ", mLastScrollDelta=" + this.mLastScrollDelta + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: az */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: bo */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private int mAnchorOffset;
        private int mAnchorPosition;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
        }

        SavedState() {
        }

        private SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
        }

        private SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean bn(int i) {
            return this.mAnchorPosition >= 0 && this.mAnchorPosition < i;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.mAnchorPosition + ", mAnchorOffset=" + this.mAnchorOffset + '}';
        }
    }
}
