package com.google.android.flexbox;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FlexboxHelper.java */
/* loaded from: classes11.dex */
public class c {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final com.google.android.flexbox.a hA;
    private boolean[] hB;
    @Nullable
    int[] hC;
    @Nullable
    long[] hD;
    @Nullable
    private long[] hE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(com.google.android.flexbox.a aVar) {
        this.hA = aVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] a(View view, int i, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.hA.getFlexItemCount();
        List<b> ba = ba(flexItemCount);
        b bVar = new b();
        if (view != null && (layoutParams instanceof FlexItem)) {
            bVar.order = ((FlexItem) layoutParams).getOrder();
        } else {
            bVar.order = 1;
        }
        if (i == -1 || i == flexItemCount) {
            bVar.index = flexItemCount;
        } else if (i < this.hA.getFlexItemCount()) {
            bVar.index = i;
            while (i < flexItemCount) {
                ba.get(i).index++;
                i++;
            }
        } else {
            bVar.index = flexItemCount;
        }
        ba.add(bVar);
        return a(flexItemCount + 1, ba, sparseIntArray);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int[] a(SparseIntArray sparseIntArray) {
        int flexItemCount = this.hA.getFlexItemCount();
        return a(flexItemCount, ba(flexItemCount), sparseIntArray);
    }

    @NonNull
    private List<b> ba(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            b bVar = new b();
            bVar.order = ((FlexItem) this.hA.getFlexItemAt(i2).getLayoutParams()).getOrder();
            bVar.index = i2;
            arrayList.add(bVar);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(SparseIntArray sparseIntArray) {
        int flexItemCount = this.hA.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i = 0; i < flexItemCount; i++) {
            View flexItemAt = this.hA.getFlexItemAt(i);
            if (flexItemAt != null && ((FlexItem) flexItemAt.getLayoutParams()).getOrder() != sparseIntArray.get(i)) {
                return true;
            }
        }
        return false;
    }

    private int[] a(int i, List<b> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (b bVar : list) {
            iArr[i2] = bVar.index;
            sparseIntArray.append(bVar.index, bVar.order);
            i2++;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(a aVar, int i, int i2) {
        a(aVar, i, i2, Integer.MAX_VALUE, 0, -1, (List<com.google.android.flexbox.b>) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(a aVar, int i, int i2, int i3, int i4, @Nullable List<com.google.android.flexbox.b> list) {
        a(aVar, i, i2, i3, i4, -1, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(a aVar, int i, int i2, int i3, int i4, List<com.google.android.flexbox.b> list) {
        a(aVar, i, i2, i3, 0, i4, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(a aVar, int i, int i2) {
        a(aVar, i2, i, Integer.MAX_VALUE, 0, -1, (List<com.google.android.flexbox.b>) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(a aVar, int i, int i2, int i3, int i4, @Nullable List<com.google.android.flexbox.b> list) {
        a(aVar, i2, i, i3, i4, -1, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(a aVar, int i, int i2, int i3, int i4, List<com.google.android.flexbox.b> list) {
        a(aVar, i2, i, i3, 0, i4, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(a aVar, int i, int i2, int i3, int i4, int i5, @Nullable List<com.google.android.flexbox.b> list) {
        ArrayList arrayList;
        List<com.google.android.flexbox.b> list2;
        int i6;
        int i7;
        int i8;
        int i9;
        List<com.google.android.flexbox.b> list3;
        int i10;
        int i11;
        View view;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18 = i;
        int i19 = i5;
        boolean isMainAxisDirectionHorizontal = this.hA.isMainAxisDirectionHorizontal();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (list == null) {
            arrayList = new ArrayList();
        } else {
            arrayList = list;
        }
        aVar.mFlexLines = arrayList;
        int i20 = i19 == -1 ? 1 : 0;
        int q = q(isMainAxisDirectionHorizontal);
        int r = r(isMainAxisDirectionHorizontal);
        int s = s(isMainAxisDirectionHorizontal);
        int t = t(isMainAxisDirectionHorizontal);
        com.google.android.flexbox.b bVar = new com.google.android.flexbox.b();
        int i21 = i4;
        bVar.hy = i21;
        int i22 = r + q;
        bVar.hp = i22;
        int flexItemCount = this.hA.getFlexItemCount();
        int i23 = i20;
        int i24 = Integer.MIN_VALUE;
        int i25 = 0;
        int i26 = 0;
        int i27 = 0;
        while (i21 < flexItemCount) {
            View reorderedFlexItemAt = this.hA.getReorderedFlexItemAt(i21);
            if (reorderedFlexItemAt == null) {
                if (a(i21, flexItemCount, bVar)) {
                    a(arrayList, bVar, i21, i25);
                }
            } else if (reorderedFlexItemAt.getVisibility() == 8) {
                bVar.hs++;
                bVar.mItemCount++;
                if (a(i21, flexItemCount, bVar)) {
                    a(arrayList, bVar, i21, i25);
                }
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int i28 = flexItemCount;
                if (flexItem.bU() == 4) {
                    bVar.hx.add(Integer.valueOf(i21));
                }
                int a2 = a(flexItem, isMainAxisDirectionHorizontal);
                if (flexItem.bW() != -1.0f && mode == 1073741824) {
                    a2 = Math.round(size * flexItem.bW());
                }
                if (isMainAxisDirectionHorizontal) {
                    list2 = arrayList;
                    int childWidthMeasureSpec = this.hA.getChildWidthMeasureSpec(i18, i22 + c(flexItem, true) + d(flexItem, true), a2);
                    i6 = size;
                    int childHeightMeasureSpec = this.hA.getChildHeightMeasureSpec(i2, s + t + e(flexItem, true) + f(flexItem, true) + i25, b(flexItem, true));
                    reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                    a(i21, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                    i8 = childWidthMeasureSpec;
                    i7 = 0;
                } else {
                    list2 = arrayList;
                    i6 = size;
                    i7 = 0;
                    int childWidthMeasureSpec2 = this.hA.getChildWidthMeasureSpec(i2, s + t + e(flexItem, false) + f(flexItem, false) + i25, b(flexItem, false));
                    int childHeightMeasureSpec2 = this.hA.getChildHeightMeasureSpec(i18, c(flexItem, false) + i22 + d(flexItem, false), a2);
                    reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                    a(i21, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                    i8 = childHeightMeasureSpec2;
                }
                this.hA.updateViewCache(i21, reorderedFlexItemAt);
                a(reorderedFlexItemAt, i21);
                i26 = View.combineMeasuredStates(i26, reorderedFlexItemAt.getMeasuredState());
                int i29 = i25;
                int i30 = i22;
                int i31 = mode;
                i9 = mode;
                com.google.android.flexbox.b bVar2 = bVar;
                int i32 = i7;
                int i33 = i21;
                int i34 = i8;
                list3 = list2;
                i10 = i6;
                if (a(reorderedFlexItemAt, i31, i6, bVar.hp, d(flexItem, isMainAxisDirectionHorizontal) + a(reorderedFlexItemAt, isMainAxisDirectionHorizontal) + c(flexItem, isMainAxisDirectionHorizontal), flexItem, i33, i27)) {
                    if (bVar2.cc() > 0) {
                        i21 = i33;
                        a(list3, bVar2, i21 > 0 ? i21 - 1 : i32, i29);
                        i17 = bVar2.hr + i29;
                    } else {
                        i21 = i33;
                        i17 = i29;
                    }
                    if (!isMainAxisDirectionHorizontal) {
                        view = reorderedFlexItemAt;
                        if (flexItem.getWidth() == -1) {
                            view.measure(this.hA.getChildWidthMeasureSpec(i2, this.hA.getPaddingLeft() + this.hA.getPaddingRight() + flexItem.bX() + flexItem.bZ() + i17, flexItem.getWidth()), i34);
                            a(view, i21);
                        }
                    } else if (flexItem.getHeight() == -1) {
                        view = reorderedFlexItemAt;
                        view.measure(i34, this.hA.getChildHeightMeasureSpec(i2, this.hA.getPaddingTop() + this.hA.getPaddingBottom() + flexItem.bY() + flexItem.ca() + i17, flexItem.getHeight()));
                        a(view, i21);
                    } else {
                        view = reorderedFlexItemAt;
                    }
                    com.google.android.flexbox.b bVar3 = new com.google.android.flexbox.b();
                    i12 = 1;
                    bVar3.mItemCount = 1;
                    i11 = i30;
                    bVar3.hp = i11;
                    bVar3.hy = i21;
                    i29 = i17;
                    bVar2 = bVar3;
                    i14 = Integer.MIN_VALUE;
                    i13 = i32;
                } else {
                    i11 = i30;
                    i21 = i33;
                    view = reorderedFlexItemAt;
                    i12 = 1;
                    bVar2.mItemCount++;
                    i13 = i27 + 1;
                    i14 = i24;
                }
                if (this.hC != null) {
                    this.hC[i21] = list3.size();
                }
                bVar2.hp += a(view, isMainAxisDirectionHorizontal) + c(flexItem, isMainAxisDirectionHorizontal) + d(flexItem, isMainAxisDirectionHorizontal);
                bVar2.ht += flexItem.bS();
                bVar2.hu += flexItem.bT();
                this.hA.onNewFlexItemAdded(view, i21, i13, bVar2);
                int max = Math.max(i14, b(view, isMainAxisDirectionHorizontal) + e(flexItem, isMainAxisDirectionHorizontal) + f(flexItem, isMainAxisDirectionHorizontal) + this.hA.getDecorationLengthCrossAxis(view));
                bVar2.hr = Math.max(bVar2.hr, max);
                if (isMainAxisDirectionHorizontal) {
                    if (this.hA.getFlexWrap() != 2) {
                        bVar2.hv = Math.max(bVar2.hv, view.getBaseline() + flexItem.bY());
                    } else {
                        bVar2.hv = Math.max(bVar2.hv, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.ca());
                    }
                }
                i15 = i28;
                if (a(i21, i15, bVar2)) {
                    a(list3, bVar2, i21, i29);
                    i29 += bVar2.hr;
                }
                i16 = i5;
                if (i16 != -1 && list3.size() > 0 && list3.get(list3.size() - i12).hz >= i16 && i21 >= i16 && i23 == 0) {
                    i29 = -bVar2.cb();
                    i23 = i12;
                }
                if (i29 > i3 && i23 != 0) {
                    break;
                }
                i27 = i13;
                i24 = max;
                i25 = i29;
                bVar = bVar2;
                i21++;
                flexItemCount = i15;
                i22 = i11;
                size = i10;
                mode = i9;
                i18 = i;
                List<com.google.android.flexbox.b> list4 = list3;
                i19 = i16;
                arrayList = list4;
            }
            i11 = i22;
            i10 = size;
            i9 = mode;
            i15 = flexItemCount;
            int i35 = i19;
            list3 = arrayList;
            i16 = i35;
            i21++;
            flexItemCount = i15;
            i22 = i11;
            size = i10;
            mode = i9;
            i18 = i;
            List<com.google.android.flexbox.b> list42 = list3;
            i19 = i16;
            arrayList = list42;
        }
        aVar.hF = i26;
    }

    private int q(boolean z) {
        if (z) {
            return this.hA.getPaddingStart();
        }
        return this.hA.getPaddingTop();
    }

    private int r(boolean z) {
        if (z) {
            return this.hA.getPaddingEnd();
        }
        return this.hA.getPaddingBottom();
    }

    private int s(boolean z) {
        if (z) {
            return this.hA.getPaddingTop();
        }
        return this.hA.getPaddingStart();
    }

    private int t(boolean z) {
        if (z) {
            return this.hA.getPaddingBottom();
        }
        return this.hA.getPaddingEnd();
    }

    private int a(View view, boolean z) {
        if (z) {
            return view.getMeasuredWidth();
        }
        return view.getMeasuredHeight();
    }

    private int b(View view, boolean z) {
        if (z) {
            return view.getMeasuredHeight();
        }
        return view.getMeasuredWidth();
    }

    private int a(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getWidth();
        }
        return flexItem.getHeight();
    }

    private int b(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.getHeight();
        }
        return flexItem.getWidth();
    }

    private int c(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.bX();
        }
        return flexItem.bY();
    }

    private int d(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.bZ();
        }
        return flexItem.ca();
    }

    private int e(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.bY();
        }
        return flexItem.bX();
    }

    private int f(FlexItem flexItem, boolean z) {
        if (z) {
            return flexItem.ca();
        }
        return flexItem.bZ();
    }

    private boolean a(View view, int i, int i2, int i3, int i4, FlexItem flexItem, int i5, int i6) {
        if (this.hA.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.bV()) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        int decorationLengthMainAxis = this.hA.getDecorationLengthMainAxis(view, i5, i6);
        if (decorationLengthMainAxis > 0) {
            i4 += decorationLengthMainAxis;
        }
        return i2 < i3 + i4;
    }

    private boolean a(int i, int i2, com.google.android.flexbox.b bVar) {
        return i == i2 - 1 && bVar.cc() != 0;
    }

    private void a(List<com.google.android.flexbox.b> list, com.google.android.flexbox.b bVar, int i, int i2) {
        bVar.hw = i2;
        this.hA.onNewFlexLineAdded(bVar);
        bVar.hz = i;
        list.add(bVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.getMinWidth()
            r4 = 1
            if (r1 >= r3) goto L1d
        L17:
            int r1 = r0.getMinWidth()
        L1b:
            r3 = r4
            goto L2a
        L1d:
            int r3 = r0.getMaxWidth()
            if (r1 <= r3) goto L29
        L24:
            int r1 = r0.getMaxWidth()
            goto L1b
        L29:
            r3 = 0
        L2a:
            int r5 = r0.getMinHeight()
            if (r2 >= r5) goto L36
        L31:
            int r2 = r0.getMinHeight()
            goto L43
        L36:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L42
        L3d:
            int r2 = r0.getMaxHeight()
            goto L43
        L42:
            r4 = r3
        L43:
            if (r4 == 0) goto L5b
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.a(r8, r1, r0, r7)
            com.google.android.flexbox.a r0 = r6.hA
            r0.updateViewCache(r8, r7)
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.c.a(android.view.View, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(int i, int i2) {
        a(i, i2, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, int i2, int i3) {
        int size;
        int paddingLeft;
        bb(this.hA.getFlexItemCount());
        if (i3 >= this.hA.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.hA.getFlexDirection();
        switch (this.hA.getFlexDirection()) {
            case 0:
            case 1:
                int mode = View.MeasureSpec.getMode(i);
                size = View.MeasureSpec.getSize(i);
                if (mode != 1073741824) {
                    size = this.hA.getLargestMainSize();
                }
                paddingLeft = this.hA.getPaddingLeft() + this.hA.getPaddingRight();
                break;
            case 2:
            case 3:
                int mode2 = View.MeasureSpec.getMode(i2);
                size = View.MeasureSpec.getSize(i2);
                if (mode2 != 1073741824) {
                    size = this.hA.getLargestMainSize();
                }
                paddingLeft = this.hA.getPaddingTop() + this.hA.getPaddingBottom();
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        int i4 = 0;
        if (this.hC != null) {
            i4 = this.hC[i3];
        }
        List<com.google.android.flexbox.b> flexLinesInternal = this.hA.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i5 = i4; i5 < size2; i5++) {
            com.google.android.flexbox.b bVar = flexLinesInternal.get(i5);
            if (bVar.hp < size) {
                a(i, i2, bVar, size, paddingLeft, false);
            } else {
                b(i, i2, bVar, size, paddingLeft, false);
            }
        }
    }

    private void bb(int i) {
        if (this.hB == null) {
            if (i < 10) {
                i = 10;
            }
            this.hB = new boolean[i];
        } else if (this.hB.length < i) {
            int length = this.hB.length * 2;
            if (length >= i) {
                i = length;
            }
            this.hB = new boolean[i];
        } else {
            Arrays.fill(this.hB, false);
        }
    }

    private void a(int i, int i2, com.google.android.flexbox.b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int max;
        float f;
        float f2;
        if (bVar.ht <= 0.0f || i3 < bVar.hp) {
            return;
        }
        int i8 = bVar.hp;
        float f3 = (i3 - bVar.hp) / bVar.ht;
        bVar.hp = i4 + bVar.hq;
        if (!z) {
            bVar.hr = Integer.MIN_VALUE;
        }
        int i9 = 0;
        float f4 = 0.0f;
        boolean z2 = false;
        int i10 = 0;
        while (i9 < bVar.mItemCount) {
            int i11 = bVar.hy + i9;
            View reorderedFlexItemAt = this.hA.getReorderedFlexItemAt(i11);
            if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                i5 = i8;
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.hA.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i5 = i8;
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    if (this.hE != null) {
                        measuredWidth = c(this.hE[i11]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    if (this.hE != null) {
                        measuredHeight = d(this.hE[i11]);
                    }
                    if (this.hB[i11] || flexItem.bS() <= 0.0f) {
                        i6 = measuredWidth;
                        i7 = measuredHeight;
                    } else {
                        float bS = measuredWidth + (flexItem.bS() * f3);
                        if (i9 == bVar.mItemCount - 1) {
                            bS += f4;
                            f4 = 0.0f;
                        }
                        int round = Math.round(bS);
                        if (round > flexItem.getMaxWidth()) {
                            round = flexItem.getMaxWidth();
                            z2 = true;
                            this.hB[i11] = true;
                            bVar.ht -= flexItem.bS();
                        } else {
                            f4 += bS - round;
                            double d = f4;
                            if (d > 1.0d) {
                                round++;
                                f = (float) (d - 1.0d);
                            } else if (d < -1.0d) {
                                round--;
                                f = (float) (d + 1.0d);
                            }
                            f4 = f;
                        }
                        int b2 = b(i2, flexItem, bVar.hw);
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                        reorderedFlexItemAt.measure(makeMeasureSpec, b2);
                        i6 = reorderedFlexItemAt.getMeasuredWidth();
                        i7 = reorderedFlexItemAt.getMeasuredHeight();
                        a(i11, makeMeasureSpec, b2, reorderedFlexItemAt);
                        this.hA.updateViewCache(i11, reorderedFlexItemAt);
                    }
                    max = Math.max(i10, this.hA.getDecorationLengthCrossAxis(reorderedFlexItemAt) + i7 + flexItem.bY() + flexItem.ca());
                    bVar.hp += i6 + flexItem.bX() + flexItem.bZ();
                } else {
                    int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                    if (this.hE != null) {
                        measuredHeight2 = d(this.hE[i11]);
                    }
                    int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                    if (this.hE != null) {
                        i5 = i8;
                        measuredWidth2 = c(this.hE[i11]);
                    } else {
                        i5 = i8;
                    }
                    if (!this.hB[i11] && flexItem.bS() > 0.0f) {
                        float bS2 = measuredHeight2 + (flexItem.bS() * f3);
                        if (i9 == bVar.mItemCount - 1) {
                            bS2 += f4;
                            f4 = 0.0f;
                        }
                        int round2 = Math.round(bS2);
                        if (round2 > flexItem.getMaxHeight()) {
                            round2 = flexItem.getMaxHeight();
                            this.hB[i11] = true;
                            bVar.ht -= flexItem.bS();
                            z2 = true;
                        } else {
                            f4 += bS2 - round2;
                            double d2 = f4;
                            if (d2 > 1.0d) {
                                round2++;
                                f2 = (float) (d2 - 1.0d);
                            } else if (d2 < -1.0d) {
                                round2--;
                                f2 = (float) (d2 + 1.0d);
                            }
                            f4 = f2;
                        }
                        int a2 = a(i, flexItem, bVar.hw);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                        reorderedFlexItemAt.measure(a2, makeMeasureSpec2);
                        measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                        a(i11, a2, makeMeasureSpec2, reorderedFlexItemAt);
                        this.hA.updateViewCache(i11, reorderedFlexItemAt);
                        measuredHeight2 = measuredHeight3;
                    }
                    max = Math.max(i10, measuredWidth2 + flexItem.bX() + flexItem.bZ() + this.hA.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    bVar.hp += measuredHeight2 + flexItem.bY() + flexItem.ca();
                }
                bVar.hr = Math.max(bVar.hr, max);
                i10 = max;
            }
            i9++;
            i8 = i5;
        }
        int i12 = i8;
        if (z2 && i12 != bVar.hp) {
            a(i, i2, bVar, i3, i4, true);
        }
    }

    private void b(int i, int i2, com.google.android.flexbox.b bVar, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int max;
        int i7 = bVar.hp;
        if (bVar.hu <= 0.0f || i3 > bVar.hp) {
            return;
        }
        float f = (bVar.hp - i3) / bVar.hu;
        bVar.hp = i4 + bVar.hq;
        if (!z) {
            bVar.hr = Integer.MIN_VALUE;
        }
        float f2 = 0.0f;
        boolean z2 = false;
        int i8 = 0;
        for (int i9 = 0; i9 < bVar.mItemCount; i9++) {
            int i10 = bVar.hy + i9;
            View reorderedFlexItemAt = this.hA.getReorderedFlexItemAt(i10);
            if (reorderedFlexItemAt != null && reorderedFlexItemAt.getVisibility() != 8) {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.hA.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    if (this.hE != null) {
                        measuredWidth = c(this.hE[i10]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    if (this.hE != null) {
                        measuredHeight = d(this.hE[i10]);
                    }
                    if (this.hB[i10] || flexItem.bT() <= 0.0f) {
                        i5 = measuredWidth;
                        i6 = measuredHeight;
                    } else {
                        float bT = measuredWidth - (flexItem.bT() * f);
                        if (i9 == bVar.mItemCount - 1) {
                            bT += f2;
                            f2 = 0.0f;
                        }
                        int round = Math.round(bT);
                        if (round < flexItem.getMinWidth()) {
                            round = flexItem.getMinWidth();
                            z2 = true;
                            this.hB[i10] = true;
                            bVar.hu -= flexItem.bT();
                        } else {
                            f2 += bT - round;
                            double d = f2;
                            if (d > 1.0d) {
                                round++;
                                f2 -= 1.0f;
                            } else if (d < -1.0d) {
                                round--;
                                f2 += 1.0f;
                            }
                        }
                        int b2 = b(i2, flexItem, bVar.hw);
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                        reorderedFlexItemAt.measure(makeMeasureSpec, b2);
                        i5 = reorderedFlexItemAt.getMeasuredWidth();
                        i6 = reorderedFlexItemAt.getMeasuredHeight();
                        a(i10, makeMeasureSpec, b2, reorderedFlexItemAt);
                        this.hA.updateViewCache(i10, reorderedFlexItemAt);
                    }
                    max = Math.max(i8, this.hA.getDecorationLengthCrossAxis(reorderedFlexItemAt) + i6 + flexItem.bY() + flexItem.ca());
                    bVar.hp += i5 + flexItem.bX() + flexItem.bZ();
                } else {
                    int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                    if (this.hE != null) {
                        measuredHeight2 = d(this.hE[i10]);
                    }
                    int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                    if (this.hE != null) {
                        measuredWidth2 = c(this.hE[i10]);
                    }
                    if (!this.hB[i10] && flexItem.bT() > 0.0f) {
                        float bT2 = measuredHeight2 - (flexItem.bT() * f);
                        if (i9 == bVar.mItemCount - 1) {
                            bT2 += f2;
                            f2 = 0.0f;
                        }
                        int round2 = Math.round(bT2);
                        if (round2 < flexItem.getMinHeight()) {
                            round2 = flexItem.getMinHeight();
                            this.hB[i10] = true;
                            bVar.hu -= flexItem.bT();
                            z2 = true;
                        } else {
                            f2 += bT2 - round2;
                            double d2 = f2;
                            if (d2 > 1.0d) {
                                round2++;
                                f2 -= 1.0f;
                            } else if (d2 < -1.0d) {
                                round2--;
                                f2 += 1.0f;
                            }
                        }
                        int a2 = a(i, flexItem, bVar.hw);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                        reorderedFlexItemAt.measure(a2, makeMeasureSpec2);
                        measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                        a(i10, a2, makeMeasureSpec2, reorderedFlexItemAt);
                        this.hA.updateViewCache(i10, reorderedFlexItemAt);
                        measuredHeight2 = measuredHeight3;
                    }
                    max = Math.max(i8, measuredWidth2 + flexItem.bX() + flexItem.bZ() + this.hA.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    bVar.hp += measuredHeight2 + flexItem.bY() + flexItem.ca();
                }
                bVar.hr = Math.max(bVar.hr, max);
                i8 = max;
            }
        }
        if (z2 && i7 != bVar.hp) {
            b(i, i2, bVar, i3, i4, true);
        }
    }

    private int a(int i, FlexItem flexItem, int i2) {
        int childWidthMeasureSpec = this.hA.getChildWidthMeasureSpec(i, this.hA.getPaddingLeft() + this.hA.getPaddingRight() + flexItem.bX() + flexItem.bZ() + i2, flexItem.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        if (size > flexItem.getMaxWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        if (size < flexItem.getMinWidth()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec));
        }
        return childWidthMeasureSpec;
    }

    private int b(int i, FlexItem flexItem, int i2) {
        int childHeightMeasureSpec = this.hA.getChildHeightMeasureSpec(i, this.hA.getPaddingTop() + this.hA.getPaddingBottom() + flexItem.bY() + flexItem.ca() + i2, flexItem.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        if (size > flexItem.getMaxHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        if (size < flexItem.getMinHeight()) {
            return View.MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec));
        }
        return childHeightMeasureSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i, int i2, int i3) {
        int mode;
        int size;
        int flexDirection = this.hA.getFlexDirection();
        switch (flexDirection) {
            case 0:
            case 1:
                mode = View.MeasureSpec.getMode(i2);
                size = View.MeasureSpec.getSize(i2);
                break;
            case 2:
            case 3:
                int mode2 = View.MeasureSpec.getMode(i);
                size = View.MeasureSpec.getSize(i);
                mode = mode2;
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        List<com.google.android.flexbox.b> flexLinesInternal = this.hA.getFlexLinesInternal();
        if (mode == 1073741824) {
            int sumOfCrossSize = this.hA.getSumOfCrossSize() + i3;
            int i4 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).hr = size - i3;
            } else if (flexLinesInternal.size() >= 2) {
                switch (this.hA.getAlignContent()) {
                    case 1:
                        int i5 = size - sumOfCrossSize;
                        com.google.android.flexbox.b bVar = new com.google.android.flexbox.b();
                        bVar.hr = i5;
                        flexLinesInternal.add(0, bVar);
                        return;
                    case 2:
                        this.hA.setFlexLines(a(flexLinesInternal, size, sumOfCrossSize));
                        return;
                    case 3:
                        if (sumOfCrossSize < size) {
                            float size2 = (size - sumOfCrossSize) / (flexLinesInternal.size() - 1);
                            ArrayList arrayList = new ArrayList();
                            int size3 = flexLinesInternal.size();
                            float f = 0.0f;
                            while (i4 < size3) {
                                arrayList.add(flexLinesInternal.get(i4));
                                if (i4 != flexLinesInternal.size() - 1) {
                                    com.google.android.flexbox.b bVar2 = new com.google.android.flexbox.b();
                                    if (i4 == flexLinesInternal.size() - 2) {
                                        bVar2.hr = Math.round(f + size2);
                                        f = 0.0f;
                                    } else {
                                        bVar2.hr = Math.round(size2);
                                    }
                                    f += size2 - bVar2.hr;
                                    if (f > 1.0f) {
                                        bVar2.hr++;
                                        f -= 1.0f;
                                    } else if (f < -1.0f) {
                                        bVar2.hr--;
                                        f += 1.0f;
                                    }
                                    arrayList.add(bVar2);
                                }
                                i4++;
                            }
                            this.hA.setFlexLines(arrayList);
                            return;
                        }
                        return;
                    case 4:
                        if (sumOfCrossSize >= size) {
                            this.hA.setFlexLines(a(flexLinesInternal, size, sumOfCrossSize));
                            return;
                        }
                        int size4 = (size - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                        ArrayList arrayList2 = new ArrayList();
                        com.google.android.flexbox.b bVar3 = new com.google.android.flexbox.b();
                        bVar3.hr = size4;
                        for (com.google.android.flexbox.b bVar4 : flexLinesInternal) {
                            arrayList2.add(bVar3);
                            arrayList2.add(bVar4);
                            arrayList2.add(bVar3);
                        }
                        this.hA.setFlexLines(arrayList2);
                        return;
                    case 5:
                        if (sumOfCrossSize < size) {
                            float size5 = (size - sumOfCrossSize) / flexLinesInternal.size();
                            int size6 = flexLinesInternal.size();
                            float f2 = 0.0f;
                            while (i4 < size6) {
                                com.google.android.flexbox.b bVar5 = flexLinesInternal.get(i4);
                                float f3 = bVar5.hr + size5;
                                if (i4 == flexLinesInternal.size() - 1) {
                                    f3 += f2;
                                    f2 = 0.0f;
                                }
                                int round = Math.round(f3);
                                f2 += f3 - round;
                                if (f2 > 1.0f) {
                                    round++;
                                    f2 -= 1.0f;
                                } else if (f2 < -1.0f) {
                                    round--;
                                    f2 += 1.0f;
                                }
                                bVar5.hr = round;
                                i4++;
                            }
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private List<com.google.android.flexbox.b> a(List<com.google.android.flexbox.b> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        com.google.android.flexbox.b bVar = new com.google.android.flexbox.b();
        bVar.hr = (i - i2) / 2;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                arrayList.add(bVar);
            }
            arrayList.add(list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cd() {
        bc(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bc(int i) {
        int i2;
        View reorderedFlexItemAt;
        if (i >= this.hA.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.hA.getFlexDirection();
        if (this.hA.getAlignItems() == 4) {
            if (this.hC != null) {
                i2 = this.hC[i];
            } else {
                i2 = 0;
            }
            List<com.google.android.flexbox.b> flexLinesInternal = this.hA.getFlexLinesInternal();
            int size = flexLinesInternal.size();
            while (i2 < size) {
                com.google.android.flexbox.b bVar = flexLinesInternal.get(i2);
                int i3 = bVar.mItemCount;
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = bVar.hy + i4;
                    if (i4 < this.hA.getFlexItemCount() && (reorderedFlexItemAt = this.hA.getReorderedFlexItemAt(i5)) != null && reorderedFlexItemAt.getVisibility() != 8) {
                        FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                        if (flexItem.bU() == -1 || flexItem.bU() == 4) {
                            switch (flexDirection) {
                                case 0:
                                case 1:
                                    a(reorderedFlexItemAt, bVar.hr, i5);
                                    break;
                                case 2:
                                case 3:
                                    b(reorderedFlexItemAt, bVar.hr, i5);
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                            }
                        }
                    }
                }
                i2++;
            }
            return;
        }
        for (com.google.android.flexbox.b bVar2 : this.hA.getFlexLinesInternal()) {
            for (Integer num : bVar2.hx) {
                View reorderedFlexItemAt2 = this.hA.getReorderedFlexItemAt(num.intValue());
                switch (flexDirection) {
                    case 0:
                    case 1:
                        a(reorderedFlexItemAt2, bVar2.hr, num.intValue());
                        break;
                    case 2:
                    case 3:
                        b(reorderedFlexItemAt2, bVar2.hr, num.intValue());
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                }
            }
        }
    }

    private void a(View view, int i, int i2) {
        int measuredWidth;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.bY()) - flexItem.ca()) - this.hA.getDecorationLengthCrossAxis(view), flexItem.getMinHeight()), flexItem.getMaxHeight());
        if (this.hE != null) {
            measuredWidth = c(this.hE[i2]);
        } else {
            measuredWidth = view.getMeasuredWidth();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        a(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.hA.updateViewCache(i2, view);
    }

    private void b(View view, int i, int i2) {
        int measuredHeight;
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int min = Math.min(Math.max(((i - flexItem.bX()) - flexItem.bZ()) - this.hA.getDecorationLengthCrossAxis(view), flexItem.getMinWidth()), flexItem.getMaxWidth());
        if (this.hE != null) {
            measuredHeight = d(this.hE[i2]);
        } else {
            measuredHeight = view.getMeasuredHeight();
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        a(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.hA.updateViewCache(i2, view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, com.google.android.flexbox.b bVar, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.hA.getAlignItems();
        if (flexItem.bU() != -1) {
            alignItems = flexItem.bU();
        }
        int i5 = bVar.hr;
        switch (alignItems) {
            case 0:
            case 4:
                if (this.hA.getFlexWrap() != 2) {
                    view.layout(i, i2 + flexItem.bY(), i3, i4 + flexItem.bY());
                    return;
                } else {
                    view.layout(i, i2 - flexItem.ca(), i3, i4 - flexItem.ca());
                    return;
                }
            case 1:
                if (this.hA.getFlexWrap() != 2) {
                    int i6 = i2 + i5;
                    view.layout(i, (i6 - view.getMeasuredHeight()) - flexItem.ca(), i3, i6 - flexItem.ca());
                    return;
                }
                view.layout(i, (i2 - i5) + view.getMeasuredHeight() + flexItem.bY(), i3, (i4 - i5) + view.getMeasuredHeight() + flexItem.bY());
                return;
            case 2:
                int measuredHeight = (((i5 - view.getMeasuredHeight()) + flexItem.bY()) - flexItem.ca()) / 2;
                if (this.hA.getFlexWrap() != 2) {
                    int i7 = i2 + measuredHeight;
                    view.layout(i, i7, i3, view.getMeasuredHeight() + i7);
                    return;
                }
                int i8 = i2 - measuredHeight;
                view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                return;
            case 3:
                if (this.hA.getFlexWrap() != 2) {
                    int max = Math.max(bVar.hv - view.getBaseline(), flexItem.bY());
                    view.layout(i, i2 + max, i3, i4 + max);
                    return;
                }
                int max2 = Math.max((bVar.hv - view.getMeasuredHeight()) + view.getBaseline(), flexItem.ca());
                view.layout(i, i2 - max2, i3, i4 - max2);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, com.google.android.flexbox.b bVar, boolean z, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.hA.getAlignItems();
        if (flexItem.bU() != -1) {
            alignItems = flexItem.bU();
        }
        int i5 = bVar.hr;
        switch (alignItems) {
            case 0:
            case 3:
            case 4:
                if (!z) {
                    view.layout(i + flexItem.bX(), i2, i3 + flexItem.bX(), i4);
                    return;
                } else {
                    view.layout(i - flexItem.bZ(), i2, i3 - flexItem.bZ(), i4);
                    return;
                }
            case 1:
                if (!z) {
                    view.layout(((i + i5) - view.getMeasuredWidth()) - flexItem.bZ(), i2, ((i3 + i5) - view.getMeasuredWidth()) - flexItem.bZ(), i4);
                    return;
                } else {
                    view.layout((i - i5) + view.getMeasuredWidth() + flexItem.bX(), i2, (i3 - i5) + view.getMeasuredWidth() + flexItem.bX(), i4);
                    return;
                }
            case 2:
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int measuredWidth = (((i5 - view.getMeasuredWidth()) + MarginLayoutParamsCompat.getMarginStart(marginLayoutParams)) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (!z) {
                    view.layout(i + measuredWidth, i2, i3 + measuredWidth, i4);
                    return;
                } else {
                    view.layout(i - measuredWidth, i2, i3 - measuredWidth, i4);
                    return;
                }
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bd(int i) {
        if (this.hE == null) {
            if (i < 10) {
                i = 10;
            }
            this.hE = new long[i];
        } else if (this.hE.length < i) {
            int length = this.hE.length * 2;
            if (length >= i) {
                i = length;
            }
            this.hE = Arrays.copyOf(this.hE, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void be(int i) {
        if (this.hD == null) {
            if (i < 10) {
                i = 10;
            }
            this.hD = new long[i];
        } else if (this.hD.length < i) {
            int length = this.hD.length * 2;
            if (length >= i) {
                i = length;
            }
            this.hD = Arrays.copyOf(this.hD, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(long j) {
        return (int) j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(long j) {
        return (int) (j >> 32);
    }

    @VisibleForTesting
    long makeCombinedLong(int i, int i2) {
        return (i & 4294967295L) | (i2 << 32);
    }

    private void a(int i, int i2, int i3, View view) {
        if (this.hD != null) {
            this.hD[i] = makeCombinedLong(i2, i3);
        }
        if (this.hE != null) {
            this.hE[i] = makeCombinedLong(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void bf(int i) {
        if (this.hC == null) {
            if (i < 10) {
                i = 10;
            }
            this.hC = new int[i];
        } else if (this.hC.length < i) {
            int length = this.hC.length * 2;
            if (length >= i) {
                i = length;
            }
            this.hC = Arrays.copyOf(this.hC, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(List<com.google.android.flexbox.b> list, int i) {
        int i2 = this.hC[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int length = this.hC.length - 1;
        if (i > length) {
            Arrays.fill(this.hC, -1);
        } else {
            Arrays.fill(this.hC, i, length, -1);
        }
        int length2 = this.hD.length - 1;
        if (i > length2) {
            Arrays.fill(this.hD, 0L);
        } else {
            Arrays.fill(this.hD, i, length2, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FlexboxHelper.java */
    /* loaded from: classes11.dex */
    public static class b implements Comparable<b> {
        int index;
        int order;

        private b() {
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(@NonNull b bVar) {
            if (this.order != bVar.order) {
                return this.order - bVar.order;
            }
            return this.index - bVar.index;
        }

        public String toString() {
            return "Order{order=" + this.order + ", index=" + this.index + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlexboxHelper.java */
    /* loaded from: classes11.dex */
    public static class a {
        int hF;
        List<com.google.android.flexbox.b> mFlexLines;

        /* JADX INFO: Access modifiers changed from: package-private */
        public void reset() {
            this.mFlexLines = null;
            this.hF = 0;
        }
    }
}
