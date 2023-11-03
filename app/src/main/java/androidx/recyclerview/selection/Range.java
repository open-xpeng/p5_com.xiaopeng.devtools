package androidx.recyclerview.selection;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

/* loaded from: classes10.dex */
final class Range {
    private static final String TAG = "Range";
    static final int TYPE_PRIMARY = 0;
    static final int TYPE_PROVISIONAL = 1;
    private final int mBegin;
    private final Callbacks mCallbacks;
    private int mEnd = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static abstract class Callbacks {
        abstract void updateForRange(int i, int i2, boolean z, int i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Range(int i, @NonNull Callbacks callbacks) {
        this.mBegin = i;
        this.mCallbacks = callbacks;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void extendRange(int i, int i2) {
        Preconditions.checkArgument(i != -1, "Position cannot be NO_POSITION.");
        if (this.mEnd == -1 || this.mEnd == this.mBegin) {
            this.mEnd = -1;
            establishRange(i, i2);
            return;
        }
        reviseRange(i, i2);
    }

    private void establishRange(int i, int i2) {
        Preconditions.checkArgument(this.mEnd == -1, "End has already been set.");
        this.mEnd = i;
        if (i > this.mBegin) {
            updateRange(this.mBegin + 1, i, true, i2);
        } else if (i < this.mBegin) {
            updateRange(i, this.mBegin - 1, true, i2);
        }
    }

    private void reviseRange(int i, int i2) {
        Preconditions.checkArgument(this.mEnd != -1, "End must already be set.");
        Preconditions.checkArgument(this.mBegin != this.mEnd, "Beging and end point to same position.");
        int i3 = this.mEnd;
        if (this.mEnd > this.mBegin) {
            reviseAscending(i, i2);
        } else if (this.mEnd < this.mBegin) {
            reviseDescending(i, i2);
        }
        this.mEnd = i;
    }

    private void reviseAscending(int i, int i2) {
        if (i < this.mEnd) {
            if (i < this.mBegin) {
                updateRange(this.mBegin + 1, this.mEnd, false, i2);
                updateRange(i, this.mBegin - 1, true, i2);
                return;
            }
            updateRange(i + 1, this.mEnd, false, i2);
        } else if (i > this.mEnd) {
            updateRange(this.mEnd + 1, i, true, i2);
        }
    }

    private void reviseDescending(int i, int i2) {
        if (i > this.mEnd) {
            if (i > this.mBegin) {
                updateRange(this.mEnd, this.mBegin - 1, false, i2);
                updateRange(this.mBegin + 1, i, true, i2);
                return;
            }
            updateRange(this.mEnd, i - 1, false, i2);
        } else if (i < this.mEnd) {
            updateRange(i, this.mEnd - 1, true, i2);
        }
    }

    private void updateRange(int i, int i2, boolean z, int i3) {
        this.mCallbacks.updateForRange(i, i2, z, i3);
    }

    public String toString() {
        return "Range{begin=" + this.mBegin + ", end=" + this.mEnd + "}";
    }

    private void log(int i, String str) {
        String str2 = i == 0 ? "PRIMARY" : "PROVISIONAL";
        Log.d("Range", String.valueOf(this) + ": " + str + " (" + str2 + ")");
    }
}
