package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.flexbox.c;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class FlexboxLayout extends ViewGroup implements a {
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    private int mAlignContent;
    private int mAlignItems;
    @Nullable
    private Drawable mDividerDrawableHorizontal;
    @Nullable
    private Drawable mDividerDrawableVertical;
    private int mDividerHorizontalHeight;
    private int mDividerVerticalWidth;
    private int mFlexDirection;
    private List<b> mFlexLines;
    private c.a mFlexLinesResult;
    private int mFlexWrap;
    private c mFlexboxHelper;
    private int mJustifyContent;
    private SparseIntArray mOrderCache;
    private int[] mReorderedIndices;
    private int mShowDividerHorizontal;
    private int mShowDividerVertical;

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFlexboxHelper = new c(this);
        this.mFlexLines = new ArrayList();
        this.mFlexLinesResult = new c.a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout, i, 0);
        this.mFlexDirection = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexDirection, 0);
        this.mFlexWrap = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_flexWrap, 0);
        this.mJustifyContent = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_justifyContent, 0);
        this.mAlignItems = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignItems, 4);
        this.mAlignContent = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_alignContent, 5);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDivider, 0);
        if (i2 != 0) {
            this.mShowDividerVertical = i2;
            this.mShowDividerHorizontal = i2;
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (i3 != 0) {
            this.mShowDividerVertical = i3;
        }
        int i4 = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (i4 != 0) {
            this.mShowDividerHorizontal = i4;
        }
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        if (this.mFlexboxHelper.b(this.mOrderCache)) {
            this.mReorderedIndices = this.mFlexboxHelper.a(this.mOrderCache);
        }
        switch (this.mFlexDirection) {
            case 0:
            case 1:
                measureHorizontal(i, i2);
                return;
            case 2:
            case 3:
                measureVertical(i, i2);
                return;
            default:
                throw new IllegalStateException("Invalid value for the flex direction is set: " + this.mFlexDirection);
        }
    }

    @Override // com.google.android.flexbox.a
    public int getFlexItemCount() {
        return getChildCount();
    }

    @Override // com.google.android.flexbox.a
    public View getFlexItemAt(int i) {
        return getChildAt(i);
    }

    public View getReorderedChildAt(int i) {
        if (i < 0 || i >= this.mReorderedIndices.length) {
            return null;
        }
        return getChildAt(this.mReorderedIndices[i]);
    }

    @Override // com.google.android.flexbox.a
    public View getReorderedFlexItemAt(int i) {
        return getReorderedChildAt(i);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.mOrderCache == null) {
            this.mOrderCache = new SparseIntArray(getChildCount());
        }
        this.mReorderedIndices = this.mFlexboxHelper.a(view, i, layoutParams, this.mOrderCache);
        super.addView(view, i, layoutParams);
    }

    private void measureHorizontal(int i, int i2) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.a(this.mFlexLinesResult, i, i2);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.c(i, i2);
        if (this.mAlignItems == 3) {
            for (b bVar : this.mFlexLines) {
                int i3 = Integer.MIN_VALUE;
                for (int i4 = 0; i4 < bVar.mItemCount; i4++) {
                    View reorderedChildAt = getReorderedChildAt(bVar.hy + i4);
                    if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        if (this.mFlexWrap != 2) {
                            i3 = Math.max(i3, reorderedChildAt.getMeasuredHeight() + Math.max(bVar.hv - reorderedChildAt.getBaseline(), layoutParams.topMargin) + layoutParams.bottomMargin);
                        } else {
                            i3 = Math.max(i3, reorderedChildAt.getMeasuredHeight() + layoutParams.topMargin + Math.max((bVar.hv - reorderedChildAt.getMeasuredHeight()) + reorderedChildAt.getBaseline(), layoutParams.bottomMargin));
                        }
                    }
                }
                bVar.hr = i3;
            }
        }
        this.mFlexboxHelper.b(i, i2, getPaddingTop() + getPaddingBottom());
        this.mFlexboxHelper.cd();
        setMeasuredDimensionForFlex(this.mFlexDirection, i, i2, this.mFlexLinesResult.hF);
    }

    private void measureVertical(int i, int i2) {
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        this.mFlexboxHelper.b(this.mFlexLinesResult, i, i2);
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.c(i, i2);
        this.mFlexboxHelper.b(i, i2, getPaddingLeft() + getPaddingRight());
        this.mFlexboxHelper.cd();
        setMeasuredDimensionForFlex(this.mFlexDirection, i, i2, this.mFlexLinesResult.hF);
    }

    private void setMeasuredDimensionForFlex(int i, int i2, int i3, int i4) {
        int sumOfCrossSize;
        int largestMainSize;
        int resolveSizeAndState;
        int resolveSizeAndState2;
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        switch (i) {
            case 0:
            case 1:
                sumOfCrossSize = getSumOfCrossSize() + getPaddingTop() + getPaddingBottom();
                largestMainSize = getLargestMainSize();
                break;
            case 2:
            case 3:
                sumOfCrossSize = getLargestMainSize();
                largestMainSize = getSumOfCrossSize() + getPaddingLeft() + getPaddingRight();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + i);
        }
        if (mode == Integer.MIN_VALUE) {
            if (size < largestMainSize) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            } else {
                size = largestMainSize;
            }
            resolveSizeAndState = View.resolveSizeAndState(size, i2, i4);
        } else if (mode == 0) {
            resolveSizeAndState = View.resolveSizeAndState(largestMainSize, i2, i4);
        } else if (mode == 1073741824) {
            if (size < largestMainSize) {
                i4 = View.combineMeasuredStates(i4, 16777216);
            }
            resolveSizeAndState = View.resolveSizeAndState(size, i2, i4);
        } else {
            throw new IllegalStateException("Unknown width mode is set: " + mode);
        }
        if (mode2 == Integer.MIN_VALUE) {
            if (size2 < sumOfCrossSize) {
                i4 = View.combineMeasuredStates(i4, 256);
                sumOfCrossSize = size2;
            }
            resolveSizeAndState2 = View.resolveSizeAndState(sumOfCrossSize, i3, i4);
        } else if (mode2 == 0) {
            resolveSizeAndState2 = View.resolveSizeAndState(sumOfCrossSize, i3, i4);
        } else if (mode2 == 1073741824) {
            if (size2 < sumOfCrossSize) {
                i4 = View.combineMeasuredStates(i4, 256);
            }
            resolveSizeAndState2 = View.resolveSizeAndState(size2, i3, i4);
        } else {
            throw new IllegalStateException("Unknown height mode is set: " + mode2);
        }
        setMeasuredDimension(resolveSizeAndState, resolveSizeAndState2);
    }

    @Override // com.google.android.flexbox.a
    public int getLargestMainSize() {
        int i = Integer.MIN_VALUE;
        for (b bVar : this.mFlexLines) {
            i = Math.max(i, bVar.hp);
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.mFlexLines.get(i2);
            if (hasDividerBeforeFlexLine(i2)) {
                if (isMainAxisDirectionHorizontal()) {
                    i += this.mDividerHorizontalHeight;
                } else {
                    i += this.mDividerVerticalWidth;
                }
            }
            if (hasEndDividerAfterFlexLine(i2)) {
                if (isMainAxisDirectionHorizontal()) {
                    i += this.mDividerHorizontalHeight;
                } else {
                    i += this.mDividerVerticalWidth;
                }
            }
            i += bVar.hr;
        }
        return i;
    }

    @Override // com.google.android.flexbox.a
    public boolean isMainAxisDirectionHorizontal() {
        return this.mFlexDirection == 0 || this.mFlexDirection == 1;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        switch (this.mFlexDirection) {
            case 0:
                layoutHorizontal(layoutDirection == 1, i, i2, i3, i4);
                return;
            case 1:
                layoutHorizontal(layoutDirection != 1, i, i2, i3, i4);
                return;
            case 2:
                z2 = layoutDirection == 1;
                layoutVertical(this.mFlexWrap == 2 ? !z2 : z2, false, i, i2, i3, i4);
                return;
            case 3:
                z2 = layoutDirection == 1;
                layoutVertical(this.mFlexWrap == 2 ? !z2 : z2, true, i, i2, i3, i4);
                return;
            default:
                throw new IllegalStateException("Invalid flex direction is set: " + this.mFlexDirection);
        }
    }

    private void layoutHorizontal(boolean z, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int cc;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z2;
        float f5;
        float f6;
        int i10;
        float f7;
        View view;
        float f8;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i11 = i3 - i;
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        int paddingTop = getPaddingTop();
        int size = this.mFlexLines.size();
        int i12 = paddingTop;
        int i13 = paddingBottom;
        int i14 = 0;
        while (i14 < size) {
            b bVar = this.mFlexLines.get(i14);
            if (hasDividerBeforeFlexLine(i14)) {
                i13 -= this.mDividerHorizontalHeight;
                i12 += this.mDividerHorizontalHeight;
            }
            switch (this.mJustifyContent) {
                case 0:
                    f = paddingLeft;
                    f2 = i11 - paddingRight;
                    f3 = f2;
                    f4 = 0.0f;
                    break;
                case 1:
                    f = (i11 - bVar.hp) + paddingRight;
                    f2 = bVar.hp - paddingLeft;
                    f3 = f2;
                    f4 = 0.0f;
                    break;
                case 2:
                    f = paddingLeft + ((i11 - bVar.hp) / 2.0f);
                    f2 = (i11 - paddingRight) - ((i11 - bVar.hp) / 2.0f);
                    f3 = f2;
                    f4 = 0.0f;
                    break;
                case 3:
                    f = paddingLeft;
                    f4 = (i11 - bVar.hp) / (bVar.cc() != 1 ? cc - 1 : 1.0f);
                    f3 = i11 - paddingRight;
                    break;
                case 4:
                    int cc2 = bVar.cc();
                    if (cc2 != 0) {
                        f8 = (i11 - bVar.hp) / cc2;
                    } else {
                        f8 = 0.0f;
                    }
                    float f9 = f8 / 2.0f;
                    f = paddingLeft + f9;
                    float f10 = f8;
                    f3 = (i11 - paddingRight) - f9;
                    f4 = f10;
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            float max = Math.max(f4, 0.0f);
            float f11 = f3;
            float f12 = f;
            int i15 = 0;
            while (i15 < bVar.mItemCount) {
                int i16 = bVar.hy + i15;
                View reorderedChildAt = getReorderedChildAt(i16);
                if (reorderedChildAt != null) {
                    i5 = paddingLeft;
                    i6 = paddingRight;
                    if (reorderedChildAt.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        float f13 = f12 + layoutParams.leftMargin;
                        float f14 = f11 - layoutParams.rightMargin;
                        if (hasDividerBeforeChildAtAlongMainAxis(i16, i15)) {
                            int i17 = this.mDividerVerticalWidth;
                            float f15 = i17;
                            float f16 = f14 - f15;
                            i10 = i17;
                            f5 = f13 + f15;
                            f6 = f16;
                        } else {
                            f5 = f13;
                            f6 = f14;
                            i10 = 0;
                        }
                        int i18 = (i15 != bVar.mItemCount + (-1) || (this.mShowDividerVertical & 4) <= 0) ? 0 : this.mDividerVerticalWidth;
                        if (this.mFlexWrap == 2) {
                            if (z) {
                                i7 = i11;
                                f7 = f5;
                                i9 = i15;
                                i8 = size;
                                view = reorderedChildAt;
                                z2 = true;
                                this.mFlexboxHelper.a(reorderedChildAt, bVar, Math.round(f6) - reorderedChildAt.getMeasuredWidth(), i13 - reorderedChildAt.getMeasuredHeight(), Math.round(f6), i13);
                            } else {
                                i7 = i11;
                                i8 = size;
                                f7 = f5;
                                i9 = i15;
                                view = reorderedChildAt;
                                z2 = true;
                                this.mFlexboxHelper.a(view, bVar, Math.round(f7), i13 - view.getMeasuredHeight(), Math.round(f7) + view.getMeasuredWidth(), i13);
                            }
                        } else {
                            i7 = i11;
                            i8 = size;
                            f7 = f5;
                            i9 = i15;
                            view = reorderedChildAt;
                            z2 = true;
                            if (z) {
                                this.mFlexboxHelper.a(view, bVar, Math.round(f6) - view.getMeasuredWidth(), i12, Math.round(f6), i12 + view.getMeasuredHeight());
                            } else {
                                this.mFlexboxHelper.a(view, bVar, Math.round(f7), i12, Math.round(f7) + view.getMeasuredWidth(), i12 + view.getMeasuredHeight());
                            }
                        }
                        float measuredWidth = f7 + view.getMeasuredWidth() + max + layoutParams.rightMargin;
                        float measuredWidth2 = f6 - ((view.getMeasuredWidth() + max) + layoutParams.leftMargin);
                        if (z) {
                            bVar.a(view, i18, 0, i10, 0);
                        } else {
                            bVar.a(view, i10, 0, i18, 0);
                        }
                        f11 = measuredWidth2;
                        f12 = measuredWidth;
                        i15 = i9 + 1;
                        paddingLeft = i5;
                        paddingRight = i6;
                        i11 = i7;
                        size = i8;
                    }
                } else {
                    i5 = paddingLeft;
                    i6 = paddingRight;
                }
                i7 = i11;
                i8 = size;
                i9 = i15;
                z2 = true;
                i15 = i9 + 1;
                paddingLeft = i5;
                paddingRight = i6;
                i11 = i7;
                size = i8;
            }
            i12 += bVar.hr;
            i13 -= bVar.hr;
            i14++;
            paddingLeft = paddingLeft;
        }
    }

    private void layoutVertical(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int cc;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z3;
        float f5;
        float f6;
        int i9;
        float f7;
        View view;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int i10 = i4 - i2;
        int i11 = (i3 - i) - paddingRight;
        int size = this.mFlexLines.size();
        int i12 = i11;
        int i13 = paddingLeft;
        int i14 = 0;
        while (i14 < size) {
            b bVar = this.mFlexLines.get(i14);
            if (hasDividerBeforeFlexLine(i14)) {
                i13 += this.mDividerVerticalWidth;
                i12 -= this.mDividerVerticalWidth;
            }
            switch (this.mJustifyContent) {
                case 0:
                    f = paddingTop;
                    f2 = i10 - paddingBottom;
                    f3 = f2;
                    f4 = 0.0f;
                    break;
                case 1:
                    f = (i10 - bVar.hp) + paddingBottom;
                    f2 = bVar.hp - paddingTop;
                    f3 = f2;
                    f4 = 0.0f;
                    break;
                case 2:
                    f = paddingTop + ((i10 - bVar.hp) / 2.0f);
                    f2 = (i10 - paddingBottom) - ((i10 - bVar.hp) / 2.0f);
                    f3 = f2;
                    f4 = 0.0f;
                    break;
                case 3:
                    f = paddingTop;
                    f4 = (i10 - bVar.hp) / (bVar.cc() != 1 ? cc - 1 : 1.0f);
                    f3 = i10 - paddingBottom;
                    break;
                case 4:
                    int cc2 = bVar.cc();
                    float f8 = cc2 != 0 ? (i10 - bVar.hp) / cc2 : 0.0f;
                    float f9 = f8 / 2.0f;
                    f = paddingTop + f9;
                    float f10 = f8;
                    f3 = (i10 - paddingBottom) - f9;
                    f4 = f10;
                    break;
                default:
                    throw new IllegalStateException("Invalid justifyContent is set: " + this.mJustifyContent);
            }
            float max = Math.max(f4, 0.0f);
            float f11 = f3;
            float f12 = f;
            int i15 = 0;
            while (i15 < bVar.mItemCount) {
                int i16 = bVar.hy + i15;
                View reorderedChildAt = getReorderedChildAt(i16);
                if (reorderedChildAt != null) {
                    i5 = paddingTop;
                    i6 = paddingBottom;
                    if (reorderedChildAt.getVisibility() != 8) {
                        LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                        float f13 = f12 + layoutParams.topMargin;
                        float f14 = f11 - layoutParams.bottomMargin;
                        if (hasDividerBeforeChildAtAlongMainAxis(i16, i15)) {
                            int i17 = this.mDividerHorizontalHeight;
                            float f15 = i17;
                            float f16 = f14 - f15;
                            i9 = i17;
                            f5 = f13 + f15;
                            f6 = f16;
                        } else {
                            f5 = f13;
                            f6 = f14;
                            i9 = 0;
                        }
                        int i18 = (i15 != bVar.mItemCount + (-1) || (this.mShowDividerHorizontal & 4) <= 0) ? 0 : this.mDividerHorizontalHeight;
                        if (!z) {
                            i7 = size;
                            f7 = f5;
                            i8 = i15;
                            z3 = true;
                            if (z2) {
                                this.mFlexboxHelper.a(reorderedChildAt, bVar, false, i13, Math.round(f6) - reorderedChildAt.getMeasuredHeight(), i13 + reorderedChildAt.getMeasuredWidth(), Math.round(f6));
                                view = reorderedChildAt;
                            } else {
                                view = reorderedChildAt;
                                this.mFlexboxHelper.a(reorderedChildAt, bVar, false, i13, Math.round(f7), i13 + reorderedChildAt.getMeasuredWidth(), Math.round(f7) + reorderedChildAt.getMeasuredHeight());
                            }
                        } else if (z2) {
                            i7 = size;
                            f7 = f5;
                            i8 = i15;
                            z3 = true;
                            this.mFlexboxHelper.a(reorderedChildAt, bVar, true, i12 - reorderedChildAt.getMeasuredWidth(), Math.round(f6) - reorderedChildAt.getMeasuredHeight(), i12, Math.round(f6));
                            view = reorderedChildAt;
                        } else {
                            i7 = size;
                            f7 = f5;
                            i8 = i15;
                            z3 = true;
                            this.mFlexboxHelper.a(reorderedChildAt, bVar, true, i12 - reorderedChildAt.getMeasuredWidth(), Math.round(f7), i12, Math.round(f7) + reorderedChildAt.getMeasuredHeight());
                            view = reorderedChildAt;
                        }
                        View view2 = view;
                        float measuredHeight = f7 + view2.getMeasuredHeight() + max + layoutParams.bottomMargin;
                        float measuredHeight2 = f6 - ((view2.getMeasuredHeight() + max) + layoutParams.topMargin);
                        if (z2) {
                            bVar.a(view2, 0, i18, 0, i9);
                        } else {
                            bVar.a(view2, 0, i9, 0, i18);
                        }
                        f11 = measuredHeight2;
                        f12 = measuredHeight;
                        i15 = i8 + 1;
                        paddingTop = i5;
                        paddingBottom = i6;
                        size = i7;
                    }
                } else {
                    i5 = paddingTop;
                    i6 = paddingBottom;
                }
                i7 = size;
                i8 = i15;
                z3 = true;
                i15 = i8 + 1;
                paddingTop = i5;
                paddingBottom = i6;
                size = i7;
            }
            i13 += bVar.hr;
            i12 -= bVar.hr;
            i14++;
            paddingTop = paddingTop;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDividerDrawableVertical == null && this.mDividerDrawableHorizontal == null) {
            return;
        }
        if (this.mShowDividerHorizontal == 0 && this.mShowDividerVertical == 0) {
            return;
        }
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        switch (this.mFlexDirection) {
            case 0:
                drawDividersHorizontal(canvas, layoutDirection == 1, this.mFlexWrap == 2);
                return;
            case 1:
                drawDividersHorizontal(canvas, layoutDirection != 1, this.mFlexWrap == 2);
                return;
            case 2:
                boolean z = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    z = !z;
                }
                drawDividersVertical(canvas, z, false);
                return;
            case 3:
                boolean z2 = layoutDirection == 1;
                if (this.mFlexWrap == 2) {
                    z2 = !z2;
                }
                drawDividersVertical(canvas, z2, true);
                return;
            default:
                return;
        }
    }

    private void drawDividersHorizontal(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int right;
        int left;
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.mFlexLines.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.mFlexLines.get(i3);
            for (int i4 = 0; i4 < bVar.mItemCount; i4++) {
                int i5 = bVar.hy + i4;
                View reorderedChildAt = getReorderedChildAt(i5);
                if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(i5, i4)) {
                        if (z) {
                            left = reorderedChildAt.getRight() + layoutParams.rightMargin;
                        } else {
                            left = (reorderedChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerVerticalWidth;
                        }
                        drawVerticalDivider(canvas, left, bVar.mTop, bVar.hr);
                    }
                    if (i4 == bVar.mItemCount - 1 && (this.mShowDividerVertical & 4) > 0) {
                        if (z) {
                            right = (reorderedChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerVerticalWidth;
                        } else {
                            right = reorderedChildAt.getRight() + layoutParams.rightMargin;
                        }
                        drawVerticalDivider(canvas, right, bVar.mTop, bVar.hr);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i3)) {
                if (z2) {
                    i2 = bVar.mBottom;
                } else {
                    i2 = bVar.mTop - this.mDividerHorizontalHeight;
                }
                drawHorizontalDivider(canvas, paddingLeft, i2, max);
            }
            if (hasEndDividerAfterFlexLine(i3) && (this.mShowDividerHorizontal & 4) > 0) {
                if (z2) {
                    i = bVar.mTop - this.mDividerHorizontalHeight;
                } else {
                    i = bVar.mBottom;
                }
                drawHorizontalDivider(canvas, paddingLeft, i, max);
            }
        }
    }

    private void drawDividersVertical(Canvas canvas, boolean z, boolean z2) {
        int i;
        int i2;
        int bottom;
        int top;
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.mFlexLines.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.mFlexLines.get(i3);
            for (int i4 = 0; i4 < bVar.mItemCount; i4++) {
                int i5 = bVar.hy + i4;
                View reorderedChildAt = getReorderedChildAt(i5);
                if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                    LayoutParams layoutParams = (LayoutParams) reorderedChildAt.getLayoutParams();
                    if (hasDividerBeforeChildAtAlongMainAxis(i5, i4)) {
                        if (z2) {
                            top = reorderedChildAt.getBottom() + layoutParams.bottomMargin;
                        } else {
                            top = (reorderedChildAt.getTop() - layoutParams.topMargin) - this.mDividerHorizontalHeight;
                        }
                        drawHorizontalDivider(canvas, bVar.mLeft, top, bVar.hr);
                    }
                    if (i4 == bVar.mItemCount - 1 && (this.mShowDividerHorizontal & 4) > 0) {
                        if (z2) {
                            bottom = (reorderedChildAt.getTop() - layoutParams.topMargin) - this.mDividerHorizontalHeight;
                        } else {
                            bottom = reorderedChildAt.getBottom() + layoutParams.bottomMargin;
                        }
                        drawHorizontalDivider(canvas, bVar.mLeft, bottom, bVar.hr);
                    }
                }
            }
            if (hasDividerBeforeFlexLine(i3)) {
                if (z) {
                    i2 = bVar.mRight;
                } else {
                    i2 = bVar.mLeft - this.mDividerVerticalWidth;
                }
                drawVerticalDivider(canvas, i2, paddingTop, max);
            }
            if (hasEndDividerAfterFlexLine(i3) && (this.mShowDividerVertical & 4) > 0) {
                if (z) {
                    i = bVar.mLeft - this.mDividerVerticalWidth;
                } else {
                    i = bVar.mRight;
                }
                drawVerticalDivider(canvas, i, paddingTop, max);
            }
        }
    }

    private void drawVerticalDivider(Canvas canvas, int i, int i2, int i3) {
        if (this.mDividerDrawableVertical == null) {
            return;
        }
        this.mDividerDrawableVertical.setBounds(i, i2, this.mDividerVerticalWidth + i, i3 + i2);
        this.mDividerDrawableVertical.draw(canvas);
    }

    private void drawHorizontalDivider(Canvas canvas, int i, int i2, int i3) {
        if (this.mDividerDrawableHorizontal == null) {
            return;
        }
        this.mDividerDrawableHorizontal.setBounds(i, i2, i3 + i, this.mDividerHorizontalHeight + i2);
        this.mDividerDrawableHorizontal.draw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // com.google.android.flexbox.a
    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    public void setFlexDirection(int i) {
        if (this.mFlexDirection != i) {
            this.mFlexDirection = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    public void setFlexWrap(int i) {
        if (this.mFlexWrap != i) {
            this.mFlexWrap = i;
            requestLayout();
        }
    }

    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    public void setJustifyContent(int i) {
        if (this.mJustifyContent != i) {
            this.mJustifyContent = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignItems() {
        return this.mAlignItems;
    }

    public void setAlignItems(int i) {
        if (this.mAlignItems != i) {
            this.mAlignItems = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.a
    public int getAlignContent() {
        return this.mAlignContent;
    }

    public void setAlignContent(int i) {
        if (this.mAlignContent != i) {
            this.mAlignContent = i;
            requestLayout();
        }
    }

    public List<b> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        for (b bVar : this.mFlexLines) {
            if (bVar.cc() != 0) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        int i3;
        if (isMainAxisDirectionHorizontal()) {
            i3 = hasDividerBeforeChildAtAlongMainAxis(i, i2) ? 0 + this.mDividerVerticalWidth : 0;
            if ((this.mShowDividerVertical & 4) > 0) {
                return i3 + this.mDividerVerticalWidth;
            }
            return i3;
        }
        i3 = hasDividerBeforeChildAtAlongMainAxis(i, i2) ? 0 + this.mDividerHorizontalHeight : 0;
        if ((this.mShowDividerHorizontal & 4) > 0) {
            return i3 + this.mDividerHorizontalHeight;
        }
        return i3;
    }

    @Override // com.google.android.flexbox.a
    public int getDecorationLengthCrossAxis(View view) {
        return 0;
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexLineAdded(b bVar) {
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerVertical & 4) > 0) {
                bVar.hp += this.mDividerVerticalWidth;
                bVar.hq += this.mDividerVerticalWidth;
            }
        } else if ((this.mShowDividerHorizontal & 4) > 0) {
            bVar.hp += this.mDividerHorizontalHeight;
            bVar.hq += this.mDividerHorizontalHeight;
        }
    }

    @Override // com.google.android.flexbox.a
    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(i, i2, i3);
    }

    @Override // com.google.android.flexbox.a
    public void onNewFlexItemAdded(View view, int i, int i2, b bVar) {
        if (hasDividerBeforeChildAtAlongMainAxis(i, i2)) {
            if (isMainAxisDirectionHorizontal()) {
                bVar.hp += this.mDividerVerticalWidth;
                bVar.hq += this.mDividerVerticalWidth;
                return;
            }
            bVar.hp += this.mDividerHorizontalHeight;
            bVar.hq += this.mDividerHorizontalHeight;
        }
    }

    @Override // com.google.android.flexbox.a
    public void setFlexLines(List<b> list) {
        this.mFlexLines = list;
    }

    @Override // com.google.android.flexbox.a
    public List<b> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    @Override // com.google.android.flexbox.a
    public void updateViewCache(int i, View view) {
    }

    @Nullable
    public Drawable getDividerDrawableHorizontal() {
        return this.mDividerDrawableHorizontal;
    }

    @Nullable
    public Drawable getDividerDrawableVertical() {
        return this.mDividerDrawableVertical;
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(@Nullable Drawable drawable) {
        if (drawable == this.mDividerDrawableHorizontal) {
            return;
        }
        this.mDividerDrawableHorizontal = drawable;
        if (drawable != null) {
            this.mDividerHorizontalHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerHorizontalHeight = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public void setDividerDrawableVertical(@Nullable Drawable drawable) {
        if (drawable == this.mDividerDrawableVertical) {
            return;
        }
        this.mDividerDrawableVertical = drawable;
        if (drawable != null) {
            this.mDividerVerticalWidth = drawable.getIntrinsicWidth();
        } else {
            this.mDividerVerticalWidth = 0;
        }
        setWillNotDrawFlag();
        requestLayout();
    }

    public int getShowDividerVertical() {
        return this.mShowDividerVertical;
    }

    public int getShowDividerHorizontal() {
        return this.mShowDividerHorizontal;
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerVertical(int i) {
        if (i != this.mShowDividerVertical) {
            this.mShowDividerVertical = i;
            requestLayout();
        }
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.mShowDividerHorizontal) {
            this.mShowDividerHorizontal = i;
            requestLayout();
        }
    }

    private void setWillNotDrawFlag() {
        if (this.mDividerDrawableHorizontal == null && this.mDividerDrawableVertical == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
    }

    private boolean hasDividerBeforeChildAtAlongMainAxis(int i, int i2) {
        return allViewsAreGoneBefore(i, i2) ? isMainAxisDirectionHorizontal() ? (this.mShowDividerVertical & 1) != 0 : (this.mShowDividerHorizontal & 1) != 0 : isMainAxisDirectionHorizontal() ? (this.mShowDividerVertical & 2) != 0 : (this.mShowDividerHorizontal & 2) != 0;
    }

    private boolean allViewsAreGoneBefore(int i, int i2) {
        for (int i3 = 1; i3 <= i2; i3++) {
            View reorderedChildAt = getReorderedChildAt(i - i3);
            if (reorderedChildAt != null && reorderedChildAt.getVisibility() != 8) {
                return false;
            }
        }
        return true;
    }

    private boolean hasDividerBeforeFlexLine(int i) {
        if (i < 0 || i >= this.mFlexLines.size()) {
            return false;
        }
        return allFlexLinesAreDummyBefore(i) ? isMainAxisDirectionHorizontal() ? (this.mShowDividerHorizontal & 1) != 0 : (this.mShowDividerVertical & 1) != 0 : isMainAxisDirectionHorizontal() ? (this.mShowDividerHorizontal & 2) != 0 : (this.mShowDividerVertical & 2) != 0;
    }

    private boolean allFlexLinesAreDummyBefore(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (this.mFlexLines.get(i2).cc() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean hasEndDividerAfterFlexLine(int i) {
        if (i < 0 || i >= this.mFlexLines.size()) {
            return false;
        }
        for (int i2 = i + 1; i2 < this.mFlexLines.size(); i2++) {
            if (this.mFlexLines.get(i2).cc() > 0) {
                return false;
            }
        }
        if (isMainAxisDirectionHorizontal()) {
            if ((this.mShowDividerHorizontal & 4) == 0) {
                return false;
            }
            return true;
        } else if ((this.mShowDividerVertical & 4) == 0) {
            return false;
        } else {
            return true;
        }
    }

    /* loaded from: classes11.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() { // from class: com.google.android.flexbox.FlexboxLayout.LayoutParams.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: ax */
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: bg */
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
        private int mOrder;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mOrder = 1;
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FlexboxLayout_Layout);
            this.mOrder = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.hG = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.hH = obtainStyledAttributes.getFloat(R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.hI = obtainStyledAttributes.getInt(R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.hJ = obtainStyledAttributes.getFraction(R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.mMinHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.mMaxWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.mMaxHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.hK = obtainStyledAttributes.getBoolean(R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.mOrder = 1;
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            this.mOrder = layoutParams.mOrder;
            this.hG = layoutParams.hG;
            this.hH = layoutParams.hH;
            this.hI = layoutParams.hI;
            this.hJ = layoutParams.hJ;
            this.mMinWidth = layoutParams.mMinWidth;
            this.mMinHeight = layoutParams.mMinHeight;
            this.mMaxWidth = layoutParams.mMaxWidth;
            this.mMaxHeight = layoutParams.mMaxHeight;
            this.hK = layoutParams.hK;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mOrder = 1;
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mOrder = 1;
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return this.width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return this.height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return this.mOrder;
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

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mOrder);
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
            super(0, 0);
            this.mOrder = 1;
            this.hG = 0.0f;
            this.hH = 1.0f;
            this.hI = -1;
            this.hJ = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            this.mOrder = parcel.readInt();
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
}
