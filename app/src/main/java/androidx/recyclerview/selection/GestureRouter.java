package androidx.recyclerview.selection;

import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;

/* loaded from: classes10.dex */
final class GestureRouter<T extends GestureDetector.OnGestureListener & GestureDetector.OnDoubleTapListener> implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {
    private final ToolHandlerRegistry<T> mDelegates;

    GestureRouter(@NonNull T t) {
        Preconditions.checkArgument(t != null);
        this.mDelegates = new ToolHandlerRegistry<>(t);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GestureRouter() {
        this(new GestureDetector.SimpleOnGestureListener());
    }

    public void register(int i, @Nullable T t) {
        this.mDelegates.set(i, t);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onSingleTapConfirmed(@NonNull MotionEvent motionEvent) {
        return this.mDelegates.get(motionEvent).onSingleTapConfirmed(motionEvent);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTap(@NonNull MotionEvent motionEvent) {
        return this.mDelegates.get(motionEvent).onDoubleTap(motionEvent);
    }

    @Override // android.view.GestureDetector.OnDoubleTapListener
    public boolean onDoubleTapEvent(@NonNull MotionEvent motionEvent) {
        return this.mDelegates.get(motionEvent).onDoubleTapEvent(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        return this.mDelegates.get(motionEvent).onDown(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onShowPress(@NonNull MotionEvent motionEvent) {
        this.mDelegates.get(motionEvent).onShowPress(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        return this.mDelegates.get(motionEvent).onSingleTapUp(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent2, float f, float f2) {
        return this.mDelegates.get(motionEvent2).onScroll(motionEvent, motionEvent2, f, f2);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public void onLongPress(@NonNull MotionEvent motionEvent) {
        this.mDelegates.get(motionEvent).onLongPress(motionEvent);
    }

    @Override // android.view.GestureDetector.OnGestureListener
    public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent2, float f, float f2) {
        return this.mDelegates.get(motionEvent2).onFling(motionEvent, motionEvent2, f, f2);
    }
}
