package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    private static final int CHAIN_FIRST = 0;
    private static final int CHAIN_FIRST_VISIBLE = 2;
    private static final int CHAIN_LAST = 1;
    private static final int CHAIN_LAST_VISIBLE = 3;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final boolean DEBUG_OPTIMIZE = false;
    private static final int FLAG_CHAIN_DANGLING = 1;
    private static final int FLAG_RECOMPUTE_BOUNDS = 2;
    private static final int FLAG_USE_OPTIMIZE = 0;
    private static final int MAX_ITERATIONS = 8;
    public static final int OPTIMIZATION_ALL = 2;
    public static final int OPTIMIZATION_BASIC = 4;
    public static final int OPTIMIZATION_CHAIN = 8;
    public static final int OPTIMIZATION_NONE = 1;
    private static final boolean USE_THREAD = false;
    private boolean[] flags;
    protected LinearSystem mBackgroundSystem;
    private ConstraintWidget[] mChainEnds;
    private boolean mHeightMeasuredTooSmall;
    private ConstraintWidget[] mHorizontalChainsArray;
    private int mHorizontalChainsSize;
    private ConstraintWidget[] mMatchConstraintsChainedWidgets;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    private ConstraintWidget[] mVerticalChainsArray;
    private int mVerticalChainsSize;
    private boolean mWidthMeasuredTooSmall;
    int mWrapHeight;
    int mWrapWidth;
    private static final boolean USE_SNAPSHOT = true;
    static boolean ALLOW_ROOT_GROUP = USE_SNAPSHOT;

    public ConstraintWidgetContainer() {
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public String getType() {
        return "ConstraintLayout";
    }

    @Override // android.support.constraint.solver.widgets.WidgetContainer, android.support.constraint.solver.widgets.ConstraintWidget
    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public static ConstraintWidgetContainer createContainer(ConstraintWidgetContainer constraintWidgetContainer, String str, ArrayList<ConstraintWidget> arrayList, int i) {
        Rectangle bounds = getBounds(arrayList);
        if (bounds.width == 0 || bounds.height == 0) {
            return null;
        }
        if (i > 0) {
            int min = Math.min(bounds.x, bounds.y);
            if (i > min) {
                i = min;
            }
            bounds.grow(i, i);
        }
        constraintWidgetContainer.setOrigin(bounds.x, bounds.y);
        constraintWidgetContainer.setDimension(bounds.width, bounds.height);
        constraintWidgetContainer.setDebugName(str);
        ConstraintWidget parent = arrayList.get(0).getParent();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = arrayList.get(i2);
            if (constraintWidget.getParent() == parent) {
                constraintWidgetContainer.add(constraintWidget);
                constraintWidget.setX(constraintWidget.getX() - bounds.x);
                constraintWidget.setY(constraintWidget.getY() - bounds.y);
            }
        }
        return constraintWidgetContainer;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem, int i) {
        boolean z;
        addToSolver(linearSystem, i);
        int size = this.mChildren.size();
        if (this.mOptimizationLevel == 2 || this.mOptimizationLevel == 4) {
            if (optimize(linearSystem)) {
                return false;
            }
            z = false;
        } else {
            z = true;
        }
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i2);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.mHorizontalDimensionBehaviour;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = constraintWidget.mVerticalDimensionBehaviour;
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem, i);
                if (dimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                if (z) {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                }
                constraintWidget.addToSolver(linearSystem, i);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            applyHorizontalChain(linearSystem);
        }
        if (this.mVerticalChainsSize > 0) {
            applyVerticalChain(linearSystem);
        }
        return USE_SNAPSHOT;
    }

    private boolean optimize(LinearSystem linearSystem) {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            constraintWidget.mHorizontalResolution = -1;
            constraintWidget.mVerticalResolution = -1;
            if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                constraintWidget.mHorizontalResolution = 1;
                constraintWidget.mVerticalResolution = 1;
            }
            if (constraintWidget instanceof Barrier) {
                constraintWidget.mHorizontalResolution = 1;
                constraintWidget.mVerticalResolution = 1;
            }
        }
        boolean z = false;
        int i2 = 0;
        int i3 = 0;
        while (!z) {
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < size; i6++) {
                ConstraintWidget constraintWidget2 = this.mChildren.get(i6);
                if (constraintWidget2.mHorizontalResolution == -1) {
                    if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget2.mHorizontalResolution = 1;
                    } else {
                        Optimizer.checkHorizontalSimpleDependency(this, linearSystem, constraintWidget2);
                    }
                }
                if (constraintWidget2.mVerticalResolution == -1) {
                    if (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget2.mVerticalResolution = 1;
                    } else {
                        Optimizer.checkVerticalSimpleDependency(this, linearSystem, constraintWidget2);
                    }
                }
                if (constraintWidget2.mVerticalResolution == -1) {
                    i4++;
                }
                if (constraintWidget2.mHorizontalResolution == -1) {
                    i5++;
                }
            }
            if ((i4 == 0 && i5 == 0) || (i2 == i4 && i3 == i5)) {
                z = true;
            }
            i2 = i4;
            i3 = i5;
        }
        int i7 = 0;
        int i8 = 0;
        for (int i9 = 0; i9 < size; i9++) {
            ConstraintWidget constraintWidget3 = this.mChildren.get(i9);
            if (constraintWidget3.mHorizontalResolution == 1 || constraintWidget3.mHorizontalResolution == -1) {
                i7++;
            }
            if (constraintWidget3.mVerticalResolution == 1 || constraintWidget3.mVerticalResolution == -1) {
                i8++;
            }
        }
        if (i7 == 0 && i8 == 0) {
            return USE_SNAPSHOT;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:202:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x04e2 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void applyHorizontalChain(android.support.constraint.solver.LinearSystem r35) {
        /*
            Method dump skipped, instructions count: 1351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyHorizontalChain(android.support.constraint.solver.LinearSystem):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:212:0x0504  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0506 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void applyVerticalChain(android.support.constraint.solver.LinearSystem r35) {
        /*
            Method dump skipped, instructions count: 1387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyVerticalChain(android.support.constraint.solver.LinearSystem):void");
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, int i, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem, i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i2);
            constraintWidget.updateFromSolver(linearSystem, i);
            if (constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = USE_SNAPSHOT;
            }
            if (constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = USE_SNAPSHOT;
            }
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01cf  */
    @Override // android.support.constraint.solver.widgets.WidgetContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void layout() {
        /*
            Method dump skipped, instructions count: 641
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    static int setGroup(ConstraintAnchor constraintAnchor, int i) {
        int i2 = constraintAnchor.mGroup;
        if (constraintAnchor.mOwner.getParent() == null) {
            return i;
        }
        if (i2 <= i) {
            return i2;
        }
        constraintAnchor.mGroup = i;
        ConstraintAnchor opposite = constraintAnchor.getOpposite();
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (opposite != null) {
            i = setGroup(opposite, i);
        }
        if (constraintAnchor2 != null) {
            i = setGroup(constraintAnchor2, i);
        }
        if (opposite != null) {
            i = setGroup(opposite, i);
        }
        constraintAnchor.mGroup = i;
        return i;
    }

    public int layoutFindGroupsSimple() {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            constraintWidget.mLeft.mGroup = 0;
            constraintWidget.mRight.mGroup = 0;
            constraintWidget.mTop.mGroup = 1;
            constraintWidget.mBottom.mGroup = 1;
            constraintWidget.mBaseline.mGroup = 1;
        }
        return 2;
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x01f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void findHorizontalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget r8, boolean[] r9) {
        /*
            Method dump skipped, instructions count: 513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.findHorizontalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget, boolean[]):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:154:0x0246  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void findVerticalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget r9, boolean[] r10) {
        /*
            Method dump skipped, instructions count: 593
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.findVerticalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget, boolean[]):void");
    }

    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v12 */
    public void findWrapSize(ArrayList<ConstraintWidget> arrayList, boolean[] zArr) {
        int size = arrayList.size();
        ?? r5 = 0;
        zArr[0] = USE_SNAPSHOT;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i < size) {
            try {
                ConstraintWidget constraintWidget = arrayList.get(i);
                if (!constraintWidget.isRoot()) {
                    if (!constraintWidget.mHorizontalWrapVisited) {
                        findHorizontalWrapRecursive(constraintWidget, zArr);
                    }
                    if (!zArr[r5]) {
                        for (int i8 = r5; i8 < size; i8++) {
                            ConstraintWidget constraintWidget2 = arrayList.get(i8);
                            constraintWidget2.mHorizontalWrapVisited = r5;
                            constraintWidget2.mVerticalWrapVisited = r5;
                            constraintWidget2.mLeftHasCentered = r5;
                            constraintWidget2.mRightHasCentered = r5;
                            constraintWidget2.mTopHasCentered = r5;
                            constraintWidget2.mBottomHasCentered = r5;
                        }
                        return;
                    }
                    if (!constraintWidget.mVerticalWrapVisited) {
                        findVerticalWrapRecursive(constraintWidget, zArr);
                    }
                    if (!zArr[r5]) {
                        for (int i9 = r5; i9 < size; i9++) {
                            ConstraintWidget constraintWidget3 = arrayList.get(i9);
                            constraintWidget3.mHorizontalWrapVisited = r5;
                            constraintWidget3.mVerticalWrapVisited = r5;
                            constraintWidget3.mLeftHasCentered = r5;
                            constraintWidget3.mRightHasCentered = r5;
                            constraintWidget3.mTopHasCentered = r5;
                            constraintWidget3.mBottomHasCentered = r5;
                        }
                        return;
                    }
                    int width = (constraintWidget.mDistToLeft + constraintWidget.mDistToRight) - constraintWidget.getWidth();
                    int height = (constraintWidget.mDistToTop + constraintWidget.mDistToBottom) - constraintWidget.getHeight();
                    int width2 = constraintWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT ? constraintWidget.getWidth() + constraintWidget.mLeft.mMargin + constraintWidget.mRight.mMargin : width;
                    int height2 = constraintWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT ? constraintWidget.getHeight() + constraintWidget.mTop.mMargin + constraintWidget.mBottom.mMargin : height;
                    if (constraintWidget.getVisibility() == 8) {
                        width2 = 0;
                        height2 = 0;
                    }
                    i2 = Math.max(i2, constraintWidget.mDistToLeft);
                    i3 = Math.max(i3, constraintWidget.mDistToRight);
                    i6 = Math.max(i6, constraintWidget.mDistToBottom);
                    i5 = Math.max(i5, constraintWidget.mDistToTop);
                    int max = Math.max(i4, width2);
                    i7 = Math.max(i7, height2);
                    i4 = max;
                }
                i++;
                r5 = 0;
            } catch (Throwable th) {
                for (int i10 = 0; i10 < size; i10++) {
                    ConstraintWidget constraintWidget4 = arrayList.get(i10);
                    constraintWidget4.mHorizontalWrapVisited = false;
                    constraintWidget4.mVerticalWrapVisited = false;
                    constraintWidget4.mLeftHasCentered = false;
                    constraintWidget4.mRightHasCentered = false;
                    constraintWidget4.mTopHasCentered = false;
                    constraintWidget4.mBottomHasCentered = false;
                }
                throw th;
            }
        }
        this.mWrapWidth = Math.max(this.mMinWidth, Math.max(Math.max(i2, i3), i4));
        this.mWrapHeight = Math.max(this.mMinHeight, Math.max(Math.max(i5, i6), i7));
        for (int i11 = 0; i11 < size; i11++) {
            ConstraintWidget constraintWidget5 = arrayList.get(i11);
            constraintWidget5.mHorizontalWrapVisited = false;
            constraintWidget5.mVerticalWrapVisited = false;
            constraintWidget5.mLeftHasCentered = false;
            constraintWidget5.mRightHasCentered = false;
            constraintWidget5.mTopHasCentered = false;
            constraintWidget5.mBottomHasCentered = false;
        }
    }

    public int layoutFindGroups() {
        ConstraintAnchor.Type[] typeArr = {ConstraintAnchor.Type.LEFT, ConstraintAnchor.Type.RIGHT, ConstraintAnchor.Type.TOP, ConstraintAnchor.Type.BASELINE, ConstraintAnchor.Type.BOTTOM};
        int size = this.mChildren.size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i2);
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            if (constraintAnchor.mTarget != null) {
                if (setGroup(constraintAnchor, i) == i) {
                    i++;
                }
            } else {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            }
            ConstraintAnchor constraintAnchor2 = constraintWidget.mTop;
            if (constraintAnchor2.mTarget != null) {
                if (setGroup(constraintAnchor2, i) == i) {
                    i++;
                }
            } else {
                constraintAnchor2.mGroup = Integer.MAX_VALUE;
            }
            ConstraintAnchor constraintAnchor3 = constraintWidget.mRight;
            if (constraintAnchor3.mTarget != null) {
                if (setGroup(constraintAnchor3, i) == i) {
                    i++;
                }
            } else {
                constraintAnchor3.mGroup = Integer.MAX_VALUE;
            }
            ConstraintAnchor constraintAnchor4 = constraintWidget.mBottom;
            if (constraintAnchor4.mTarget != null) {
                if (setGroup(constraintAnchor4, i) == i) {
                    i++;
                }
            } else {
                constraintAnchor4.mGroup = Integer.MAX_VALUE;
            }
            ConstraintAnchor constraintAnchor5 = constraintWidget.mBaseline;
            if (constraintAnchor5.mTarget != null) {
                if (setGroup(constraintAnchor5, i) == i) {
                    i++;
                }
            } else {
                constraintAnchor5.mGroup = Integer.MAX_VALUE;
            }
        }
        boolean z = true;
        while (z) {
            int i3 = 0;
            boolean z2 = false;
            while (i3 < size) {
                ConstraintWidget constraintWidget2 = this.mChildren.get(i3);
                boolean z3 = z2;
                for (ConstraintAnchor.Type type : typeArr) {
                    ConstraintAnchor constraintAnchor6 = null;
                    switch (type) {
                        case LEFT:
                            constraintAnchor6 = constraintWidget2.mLeft;
                            break;
                        case TOP:
                            constraintAnchor6 = constraintWidget2.mTop;
                            break;
                        case RIGHT:
                            constraintAnchor6 = constraintWidget2.mRight;
                            break;
                        case BOTTOM:
                            constraintAnchor6 = constraintWidget2.mBottom;
                            break;
                        case BASELINE:
                            constraintAnchor6 = constraintWidget2.mBaseline;
                            break;
                    }
                    ConstraintAnchor constraintAnchor7 = constraintAnchor6.mTarget;
                    if (constraintAnchor7 != null) {
                        if (constraintAnchor7.mOwner.getParent() != null && constraintAnchor7.mGroup != constraintAnchor6.mGroup) {
                            int i4 = constraintAnchor6.mGroup > constraintAnchor7.mGroup ? constraintAnchor7.mGroup : constraintAnchor6.mGroup;
                            constraintAnchor6.mGroup = i4;
                            constraintAnchor7.mGroup = i4;
                            z3 = true;
                        }
                        ConstraintAnchor opposite = constraintAnchor7.getOpposite();
                        if (opposite != null && opposite.mGroup != constraintAnchor6.mGroup) {
                            int i5 = constraintAnchor6.mGroup > opposite.mGroup ? opposite.mGroup : constraintAnchor6.mGroup;
                            constraintAnchor6.mGroup = i5;
                            opposite.mGroup = i5;
                            z3 = true;
                        }
                    }
                }
                i3++;
                z2 = z3;
            }
            z = z2;
        }
        int[] iArr = new int[(this.mChildren.size() * typeArr.length) + 1];
        Arrays.fill(iArr, -1);
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            ConstraintWidget constraintWidget3 = this.mChildren.get(i7);
            ConstraintAnchor constraintAnchor8 = constraintWidget3.mLeft;
            if (constraintAnchor8.mGroup != Integer.MAX_VALUE) {
                int i8 = constraintAnchor8.mGroup;
                if (iArr[i8] == -1) {
                    iArr[i8] = i6;
                    i6++;
                }
                constraintAnchor8.mGroup = iArr[i8];
            }
            ConstraintAnchor constraintAnchor9 = constraintWidget3.mTop;
            if (constraintAnchor9.mGroup != Integer.MAX_VALUE) {
                int i9 = constraintAnchor9.mGroup;
                if (iArr[i9] == -1) {
                    iArr[i9] = i6;
                    i6++;
                }
                constraintAnchor9.mGroup = iArr[i9];
            }
            ConstraintAnchor constraintAnchor10 = constraintWidget3.mRight;
            if (constraintAnchor10.mGroup != Integer.MAX_VALUE) {
                int i10 = constraintAnchor10.mGroup;
                if (iArr[i10] == -1) {
                    iArr[i10] = i6;
                    i6++;
                }
                constraintAnchor10.mGroup = iArr[i10];
            }
            ConstraintAnchor constraintAnchor11 = constraintWidget3.mBottom;
            if (constraintAnchor11.mGroup != Integer.MAX_VALUE) {
                int i11 = constraintAnchor11.mGroup;
                if (iArr[i11] == -1) {
                    iArr[i11] = i6;
                    i6++;
                }
                constraintAnchor11.mGroup = iArr[i11];
            }
            ConstraintAnchor constraintAnchor12 = constraintWidget3.mBaseline;
            if (constraintAnchor12.mGroup != Integer.MAX_VALUE) {
                int i12 = constraintAnchor12.mGroup;
                if (iArr[i12] == -1) {
                    iArr[i12] = i6;
                    i6++;
                }
                constraintAnchor12.mGroup = iArr[i12];
            }
        }
        return i6;
    }

    public void layoutWithGroup(int i) {
        int i2 = this.mX;
        int i3 = this.mY;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            this.mX = 0;
            this.mY = 0;
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        int size = this.mChildren.size();
        for (int i4 = 0; i4 < size; i4++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i4);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        this.mLeft.mGroup = 0;
        this.mRight.mGroup = 0;
        this.mTop.mGroup = 1;
        this.mBottom.mGroup = 1;
        this.mSystem.reset();
        for (int i5 = 0; i5 < i; i5++) {
            try {
                addToSolver(this.mSystem, i5);
                this.mSystem.minimize();
                updateFromSolver(this.mSystem, i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateFromSolver(this.mSystem, -2);
        }
        if (this.mParent != null) {
            int width = getWidth();
            int height = getHeight();
            this.mSnapshot.applyTo(this);
            setWidth(width);
            setHeight(height);
        } else {
            this.mX = i2;
            this.mY = i3;
        }
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList<>();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            while (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintWidget.mLeft && constraintWidget.mLeft.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            while (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintWidget.mTop && constraintWidget.mTop.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mTop.mTarget.mOwner;
            }
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        for (int i = 0; i < this.mHorizontalChainsSize; i++) {
            if (this.mHorizontalChainsArray[i] == constraintWidget) {
                return;
            }
        }
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = constraintWidget;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        for (int i = 0; i < this.mVerticalChainsSize; i++) {
            if (this.mVerticalChainsArray[i] == constraintWidget) {
                return;
            }
        }
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = constraintWidget;
        this.mVerticalChainsSize++;
    }

    private int countMatchConstraintsChainedWidgets(LinearSystem linearSystem, ConstraintWidget[] constraintWidgetArr, ConstraintWidget constraintWidget, int i, boolean[] zArr) {
        int i2;
        char c;
        boolean z;
        ConstraintWidget constraintWidget2;
        char c2;
        zArr[0] = USE_SNAPSHOT;
        zArr[1] = false;
        ConstraintWidget constraintWidget3 = null;
        constraintWidgetArr[0] = null;
        constraintWidgetArr[2] = null;
        constraintWidgetArr[1] = null;
        constraintWidgetArr[3] = null;
        float f = 0.0f;
        int i3 = 5;
        int i4 = 8;
        if (i == 0) {
            if (constraintWidget.mLeft.mTarget == null || constraintWidget.mLeft.mTarget.mOwner == this) {
                z = true;
            } else {
                z = false;
            }
            constraintWidget.mHorizontalNextWidget = null;
            if (constraintWidget.getVisibility() == 8) {
                constraintWidget2 = null;
            } else {
                constraintWidget2 = constraintWidget;
            }
            i2 = 0;
            ConstraintWidget constraintWidget4 = null;
            ConstraintWidget constraintWidget5 = constraintWidget2;
            ConstraintWidget constraintWidget6 = constraintWidget5;
            ConstraintWidget constraintWidget7 = constraintWidget;
            while (constraintWidget7.mRight.mTarget != null) {
                constraintWidget7.mHorizontalNextWidget = constraintWidget3;
                if (constraintWidget7.getVisibility() != 8) {
                    if (constraintWidget6 == null) {
                        constraintWidget6 = constraintWidget7;
                    }
                    if (constraintWidget5 != null && constraintWidget5 != constraintWidget7) {
                        constraintWidget5.mHorizontalNextWidget = constraintWidget7;
                    }
                    constraintWidget5 = constraintWidget7;
                } else {
                    linearSystem.addEquality(constraintWidget7.mLeft.mSolverVariable, constraintWidget7.mLeft.mTarget.mSolverVariable, 0, 5);
                    linearSystem.addEquality(constraintWidget7.mRight.mSolverVariable, constraintWidget7.mLeft.mSolverVariable, 0, 5);
                }
                if (constraintWidget7.getVisibility() != 8 && constraintWidget7.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget7.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget7.mDimensionRatio <= f) {
                        zArr[0] = false;
                        int i5 = i2 + 1;
                        if (i5 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget7;
                        i2 = i5;
                    }
                }
                if (constraintWidget7.mRight.mTarget.mOwner.mLeft.mTarget == null || constraintWidget7.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != constraintWidget7 || constraintWidget7.mRight.mTarget.mOwner == constraintWidget7) {
                    break;
                }
                constraintWidget4 = constraintWidget7.mRight.mTarget.mOwner;
                constraintWidget7 = constraintWidget4;
                constraintWidget3 = null;
                f = 0.0f;
            }
            if (constraintWidget7.mRight.mTarget != null && constraintWidget7.mRight.mTarget.mOwner != this) {
                z = false;
            }
            if (constraintWidget.mLeft.mTarget == null || constraintWidget4.mRight.mTarget == null) {
                c2 = 1;
                zArr[1] = USE_SNAPSHOT;
            } else {
                c2 = 1;
            }
            constraintWidget.mHorizontalChainFixedPosition = z;
            constraintWidget4.mHorizontalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget6;
            constraintWidgetArr[c2] = constraintWidget4;
            constraintWidgetArr[3] = constraintWidget5;
        } else {
            boolean z2 = (constraintWidget.mTop.mTarget == null || constraintWidget.mTop.mTarget.mOwner == this) ? USE_SNAPSHOT : false;
            constraintWidget.mVerticalNextWidget = null;
            int i6 = 0;
            ConstraintWidget constraintWidget8 = constraintWidget.getVisibility() != 8 ? constraintWidget : null;
            ConstraintWidget constraintWidget9 = constraintWidget8;
            ConstraintWidget constraintWidget10 = null;
            ConstraintWidget constraintWidget11 = constraintWidget;
            while (constraintWidget11.mBottom.mTarget != null) {
                constraintWidget11.mVerticalNextWidget = null;
                if (constraintWidget11.getVisibility() != i4) {
                    if (constraintWidget8 == null) {
                        constraintWidget8 = constraintWidget11;
                    }
                    if (constraintWidget9 != null && constraintWidget9 != constraintWidget11) {
                        constraintWidget9.mVerticalNextWidget = constraintWidget11;
                    }
                    constraintWidget9 = constraintWidget11;
                } else {
                    linearSystem.addEquality(constraintWidget11.mTop.mSolverVariable, constraintWidget11.mTop.mTarget.mSolverVariable, 0, i3);
                    linearSystem.addEquality(constraintWidget11.mBottom.mSolverVariable, constraintWidget11.mTop.mSolverVariable, 0, i3);
                }
                if (constraintWidget11.getVisibility() != i4 && constraintWidget11.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget11.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget11.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        int i7 = i6 + 1;
                        if (i7 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        this.mMatchConstraintsChainedWidgets[i6] = constraintWidget11;
                        i6 = i7;
                    }
                }
                if (constraintWidget11.mBottom.mTarget.mOwner.mTop.mTarget == null || constraintWidget11.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != constraintWidget11 || constraintWidget11.mBottom.mTarget.mOwner == constraintWidget11) {
                    break;
                }
                constraintWidget10 = constraintWidget11.mBottom.mTarget.mOwner;
                constraintWidget11 = constraintWidget10;
                i3 = 5;
                i4 = 8;
            }
            i2 = i6;
            if (constraintWidget11.mBottom.mTarget != null && constraintWidget11.mBottom.mTarget.mOwner != this) {
                z2 = false;
            }
            if (constraintWidget.mTop.mTarget == null || constraintWidget10.mBottom.mTarget == null) {
                c = 1;
                zArr[1] = USE_SNAPSHOT;
            } else {
                c = 1;
            }
            constraintWidget.mVerticalChainFixedPosition = z2;
            constraintWidget10.mVerticalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget8;
            constraintWidgetArr[c] = constraintWidget10;
            constraintWidgetArr[3] = constraintWidget9;
        }
        return i2;
    }
}
