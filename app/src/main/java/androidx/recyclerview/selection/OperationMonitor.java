package androidx.recyclerview.selection;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes10.dex */
public final class OperationMonitor {
    private static final String TAG = "OperationMonitor";
    private int mNumOps = 0;
    private List<OnChangeListener> mListeners = new ArrayList();

    /* loaded from: classes10.dex */
    public interface OnChangeListener {
        void onChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    public synchronized void start() {
        this.mNumOps++;
        if (this.mNumOps == 1) {
            for (OnChangeListener onChangeListener : this.mListeners) {
                onChangeListener.onChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    public synchronized void stop() {
        Preconditions.checkState(this.mNumOps > 0);
        this.mNumOps--;
        if (this.mNumOps == 0) {
            for (OnChangeListener onChangeListener : this.mListeners) {
                onChangeListener.onChanged();
            }
        }
    }

    public synchronized boolean isStarted() {
        return this.mNumOps > 0;
    }

    public void addListener(@NonNull OnChangeListener onChangeListener) {
        Preconditions.checkArgument(onChangeListener != null);
        this.mListeners.add(onChangeListener);
    }

    public void removeListener(@NonNull OnChangeListener onChangeListener) {
        Preconditions.checkArgument(onChangeListener != null);
        this.mListeners.remove(onChangeListener);
    }

    void checkStarted() {
        Preconditions.checkState(this.mNumOps > 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkStopped() {
        Preconditions.checkState(this.mNumOps == 0);
    }
}
