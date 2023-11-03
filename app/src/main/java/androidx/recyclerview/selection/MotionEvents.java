package androidx.recyclerview.selection;

import android.graphics.Point;
import android.view.MotionEvent;
import androidx.annotation.NonNull;

/* loaded from: classes10.dex */
final class MotionEvents {
    private MotionEvents() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isMouseEvent(@NonNull MotionEvent motionEvent) {
        return motionEvent.getToolType(0) == 3;
    }

    static boolean isTouchEvent(@NonNull MotionEvent motionEvent) {
        return motionEvent.getToolType(0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isActionMove(@NonNull MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 2;
    }

    static boolean isActionDown(@NonNull MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isActionUp(@NonNull MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isActionPointerUp(@NonNull MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 6;
    }

    static boolean isActionPointerDown(@NonNull MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isActionCancel(@NonNull MotionEvent motionEvent) {
        return motionEvent.getActionMasked() == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Point getOrigin(@NonNull MotionEvent motionEvent) {
        return new Point((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isPrimaryMouseButtonPressed(@NonNull MotionEvent motionEvent) {
        return isButtonPressed(motionEvent, 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSecondaryMouseButtonPressed(@NonNull MotionEvent motionEvent) {
        return isButtonPressed(motionEvent, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTertiaryMouseButtonPressed(@NonNull MotionEvent motionEvent) {
        return isButtonPressed(motionEvent, 4);
    }

    private static boolean isButtonPressed(MotionEvent motionEvent, int i) {
        return i != 0 && (motionEvent.getButtonState() & i) == i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isShiftKeyPressed(@NonNull MotionEvent motionEvent) {
        return hasBit(motionEvent.getMetaState(), 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isCtrlKeyPressed(@NonNull MotionEvent motionEvent) {
        return hasBit(motionEvent.getMetaState(), 4096);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAltKeyPressed(@NonNull MotionEvent motionEvent) {
        return hasBit(motionEvent.getMetaState(), 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTouchpadScroll(@NonNull MotionEvent motionEvent) {
        return isMouseEvent(motionEvent) && isActionMove(motionEvent) && motionEvent.getButtonState() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isPointerDragEvent(MotionEvent motionEvent) {
        return isPrimaryMouseButtonPressed(motionEvent) && isActionMove(motionEvent);
    }

    private static boolean hasBit(int i, int i2) {
        return (i & i2) != 0;
    }
}
