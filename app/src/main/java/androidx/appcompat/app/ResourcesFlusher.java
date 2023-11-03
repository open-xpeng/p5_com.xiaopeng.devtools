package androidx.appcompat.app;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.LongSparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.os.BuildCompat;
import java.lang.reflect.Field;
import java.util.Map;

/* loaded from: classes8.dex */
class ResourcesFlusher {
    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void flush(@NonNull Resources resources) {
        if (BuildCompat.isAtLeastP()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            flushNougats(resources);
        } else if (Build.VERSION.SDK_INT >= 23) {
            flushMarshmallows(resources);
        } else if (Build.VERSION.SDK_INT >= 21) {
            flushLollipops(resources);
        }
    }

    @RequiresApi(21)
    private static void flushLollipops(@NonNull Resources resources) {
        Map map;
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField != null) {
            try {
                map = (Map) sDrawableCacheField.get(resources);
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", e2);
                map = null;
            }
            if (map != null) {
                map.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0036 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0037  */
    @androidx.annotation.RequiresApi(23)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void flushMarshmallows(@androidx.annotation.NonNull android.content.res.Resources r4) {
        /*
            boolean r0 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheFieldFetched
            if (r0 != 0) goto L1f
            r0 = 1
            java.lang.Class<android.content.res.Resources> r1 = android.content.res.Resources.class
            java.lang.String r2 = "mDrawableCache"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch: java.lang.NoSuchFieldException -> L15
            androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField = r1     // Catch: java.lang.NoSuchFieldException -> L15
            java.lang.reflect.Field r1 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField     // Catch: java.lang.NoSuchFieldException -> L15
            r1.setAccessible(r0)     // Catch: java.lang.NoSuchFieldException -> L15
            goto L1d
        L15:
            r1 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve Resources#mDrawableCache field"
            android.util.Log.e(r2, r3, r1)
        L1d:
            androidx.appcompat.app.ResourcesFlusher.sDrawableCacheFieldFetched = r0
        L1f:
            r0 = 0
            java.lang.reflect.Field r1 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField
            if (r1 == 0) goto L33
            java.lang.reflect.Field r1 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField     // Catch: java.lang.IllegalAccessException -> L2b
            java.lang.Object r4 = r1.get(r4)     // Catch: java.lang.IllegalAccessException -> L2b
            goto L34
        L2b:
            r4 = move-exception
            java.lang.String r1 = "ResourcesFlusher"
            java.lang.String r2 = "Could not retrieve value from Resources#mDrawableCache"
            android.util.Log.e(r1, r2, r4)
        L33:
            r4 = r0
        L34:
            if (r4 != 0) goto L37
            return
        L37:
            flushThemedResourcesCache(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.ResourcesFlusher.flushMarshmallows(android.content.res.Resources):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    @androidx.annotation.RequiresApi(24)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void flushNougats(@androidx.annotation.NonNull android.content.res.Resources r5) {
        /*
            boolean r0 = androidx.appcompat.app.ResourcesFlusher.sResourcesImplFieldFetched
            r1 = 1
            if (r0 != 0) goto L1f
            java.lang.Class<android.content.res.Resources> r0 = android.content.res.Resources.class
            java.lang.String r2 = "mResourcesImpl"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r2)     // Catch: java.lang.NoSuchFieldException -> L15
            androidx.appcompat.app.ResourcesFlusher.sResourcesImplField = r0     // Catch: java.lang.NoSuchFieldException -> L15
            java.lang.reflect.Field r0 = androidx.appcompat.app.ResourcesFlusher.sResourcesImplField     // Catch: java.lang.NoSuchFieldException -> L15
            r0.setAccessible(r1)     // Catch: java.lang.NoSuchFieldException -> L15
            goto L1d
        L15:
            r0 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve Resources#mResourcesImpl field"
            android.util.Log.e(r2, r3, r0)
        L1d:
            androidx.appcompat.app.ResourcesFlusher.sResourcesImplFieldFetched = r1
        L1f:
            java.lang.reflect.Field r0 = androidx.appcompat.app.ResourcesFlusher.sResourcesImplField
            if (r0 != 0) goto L24
            return
        L24:
            r0 = 0
            java.lang.reflect.Field r2 = androidx.appcompat.app.ResourcesFlusher.sResourcesImplField     // Catch: java.lang.IllegalAccessException -> L2d
            java.lang.Object r5 = r2.get(r5)     // Catch: java.lang.IllegalAccessException -> L2d
            goto L36
        L2d:
            r5 = move-exception
            java.lang.String r2 = "ResourcesFlusher"
            java.lang.String r3 = "Could not retrieve value from Resources#mResourcesImpl"
            android.util.Log.e(r2, r3, r5)
            r5 = r0
        L36:
            if (r5 != 0) goto L39
            return
        L39:
            boolean r2 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheFieldFetched
            if (r2 != 0) goto L59
            java.lang.Class r2 = r5.getClass()     // Catch: java.lang.NoSuchFieldException -> L4f
            java.lang.String r3 = "mDrawableCache"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch: java.lang.NoSuchFieldException -> L4f
            androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField = r2     // Catch: java.lang.NoSuchFieldException -> L4f
            java.lang.reflect.Field r2 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField     // Catch: java.lang.NoSuchFieldException -> L4f
            r2.setAccessible(r1)     // Catch: java.lang.NoSuchFieldException -> L4f
            goto L57
        L4f:
            r2 = move-exception
            java.lang.String r3 = "ResourcesFlusher"
            java.lang.String r4 = "Could not retrieve ResourcesImpl#mDrawableCache field"
            android.util.Log.e(r3, r4, r2)
        L57:
            androidx.appcompat.app.ResourcesFlusher.sDrawableCacheFieldFetched = r1
        L59:
            java.lang.reflect.Field r1 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField
            if (r1 == 0) goto L6d
            java.lang.reflect.Field r1 = androidx.appcompat.app.ResourcesFlusher.sDrawableCacheField     // Catch: java.lang.IllegalAccessException -> L65
            java.lang.Object r5 = r1.get(r5)     // Catch: java.lang.IllegalAccessException -> L65
            goto L6e
        L65:
            r5 = move-exception
            java.lang.String r1 = "ResourcesFlusher"
            java.lang.String r2 = "Could not retrieve value from ResourcesImpl#mDrawableCache"
            android.util.Log.e(r1, r2, r5)
        L6d:
            r5 = r0
        L6e:
            if (r5 == 0) goto L73
            flushThemedResourcesCache(r5)
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.ResourcesFlusher.flushNougats(android.content.res.Resources):void");
    }

    @RequiresApi(16)
    private static void flushThemedResourcesCache(@NonNull Object obj) {
        LongSparseArray longSparseArray;
        if (!sThemedResourceCacheClazzFetched) {
            try {
                sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "Could not find ThemedResourceCache class", e);
            }
            sThemedResourceCacheClazzFetched = true;
        }
        if (sThemedResourceCacheClazz == null) {
            return;
        }
        if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
            try {
                sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
                sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e(TAG, "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e2);
            }
            sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
        }
        if (sThemedResourceCache_mUnthemedEntriesField == null) {
            return;
        }
        try {
            longSparseArray = (LongSparseArray) sThemedResourceCache_mUnthemedEntriesField.get(obj);
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e3);
            longSparseArray = null;
        }
        if (longSparseArray != null) {
            longSparseArray.clear();
        }
    }

    private ResourcesFlusher() {
    }
}
