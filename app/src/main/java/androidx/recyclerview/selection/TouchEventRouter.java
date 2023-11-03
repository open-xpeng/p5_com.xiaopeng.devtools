package androidx.recyclerview.selection;

import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes10.dex */
final class TouchEventRouter implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "TouchEventRouter";
    private final ToolHandlerRegistry<RecyclerView.OnItemTouchListener> mDelegates;
    private final GestureDetector mDetector;

    TouchEventRouter(@NonNull GestureDetector gestureDetector, @NonNull RecyclerView.OnItemTouchListener onItemTouchListener) {
        Preconditions.checkArgument(gestureDetector != null);
        Preconditions.checkArgument(onItemTouchListener != null);
        this.mDetector = gestureDetector;
        this.mDelegates = new ToolHandlerRegistry<>(onItemTouchListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TouchEventRouter(@NonNull GestureDetector gestureDetector) {
        this(gestureDetector, new RecyclerView.OnItemTouchListener() { // from class: androidx.recyclerview.selection.TouchEventRouter.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return false;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void register(int i, @NonNull RecyclerView.OnItemTouchListener onItemTouchListener) {
        Preconditions.checkArgument(onItemTouchListener != null);
        this.mDelegates.set(i, onItemTouchListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        return this.mDelegates.get(motionEvent).onInterceptTouchEvent(recyclerView, motionEvent) | this.mDetector.onTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        this.mDelegates.get(motionEvent).onTouchEvent(recyclerView, motionEvent);
        this.mDetector.onTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }
}
