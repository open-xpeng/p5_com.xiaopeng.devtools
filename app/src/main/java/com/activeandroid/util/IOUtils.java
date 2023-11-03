package com.activeandroid.util;

import android.database.Cursor;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes11.dex */
public class IOUtils {
    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            Log.e("Couldn't close closeable.", e);
        }
    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        try {
            cursor.close();
        } catch (Exception e) {
            Log.e("Couldn't close cursor.", e);
        }
    }
}
