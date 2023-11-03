package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import androidx.annotation.NonNull;
import androidx.core.os.BuildCompat;

/* loaded from: classes8.dex */
public final class SQLiteCursorCompat {
    private SQLiteCursorCompat() {
    }

    public void setFillWindowForwardOnly(@NonNull SQLiteCursor sQLiteCursor, boolean z) {
        if (BuildCompat.isAtLeastP()) {
            sQLiteCursor.setFillWindowForwardOnly(z);
        }
    }
}
