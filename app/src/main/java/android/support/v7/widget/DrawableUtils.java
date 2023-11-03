package android.support.v7.widget;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.WrappedDrawable;
import android.support.v7.graphics.drawable.DrawableWrapper;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes8.dex */
public class DrawableUtils {
    public static final Rect INSETS_NONE = new Rect();
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class<?> sInsetsClazz;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                sInsetsClazz = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException e) {
            }
        }
    }

    private DrawableUtils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0079 A[Catch: Exception -> 0x009a, TryCatch #0 {Exception -> 0x009a, blocks: (B:4:0x0004, B:6:0x001d, B:8:0x002c, B:30:0x0075, B:32:0x0079, B:33:0x0080, B:34:0x0087, B:35:0x008e, B:17:0x004c, B:20:0x0056, B:23:0x0060, B:26:0x006a), top: B:43:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0080 A[Catch: Exception -> 0x009a, TryCatch #0 {Exception -> 0x009a, blocks: (B:4:0x0004, B:6:0x001d, B:8:0x002c, B:30:0x0075, B:32:0x0079, B:33:0x0080, B:34:0x0087, B:35:0x008e, B:17:0x004c, B:20:0x0056, B:23:0x0060, B:26:0x006a), top: B:43:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0087 A[Catch: Exception -> 0x009a, TryCatch #0 {Exception -> 0x009a, blocks: (B:4:0x0004, B:6:0x001d, B:8:0x002c, B:30:0x0075, B:32:0x0079, B:33:0x0080, B:34:0x0087, B:35:0x008e, B:17:0x004c, B:20:0x0056, B:23:0x0060, B:26:0x006a), top: B:43:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008e A[Catch: Exception -> 0x009a, TRY_LEAVE, TryCatch #0 {Exception -> 0x009a, blocks: (B:4:0x0004, B:6:0x001d, B:8:0x002c, B:30:0x0075, B:32:0x0079, B:33:0x0080, B:34:0x0087, B:35:0x008e, B:17:0x004c, B:20:0x0056, B:23:0x0060, B:26:0x006a), top: B:43:0x0004 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Rect getOpticalBounds(android.graphics.drawable.Drawable r10) {
        /*
            java.lang.Class<?> r0 = android.support.v7.widget.DrawableUtils.sInsetsClazz
            if (r0 == 0) goto La2
            android.graphics.drawable.Drawable r10 = android.support.v4.graphics.drawable.DrawableCompat.unwrap(r10)     // Catch: java.lang.Exception -> L9a
            java.lang.Class r0 = r10.getClass()     // Catch: java.lang.Exception -> L9a
            java.lang.String r1 = "getOpticalInsets"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L9a
            java.lang.reflect.Method r0 = r0.getMethod(r1, r3)     // Catch: java.lang.Exception -> L9a
            java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L9a
            java.lang.Object r10 = r0.invoke(r10, r1)     // Catch: java.lang.Exception -> L9a
            if (r10 == 0) goto L99
            android.graphics.Rect r0 = new android.graphics.Rect     // Catch: java.lang.Exception -> L9a
            r0.<init>()     // Catch: java.lang.Exception -> L9a
            java.lang.Class<?> r1 = android.support.v7.widget.DrawableUtils.sInsetsClazz     // Catch: java.lang.Exception -> L9a
            java.lang.reflect.Field[] r1 = r1.getFields()     // Catch: java.lang.Exception -> L9a
            int r3 = r1.length     // Catch: java.lang.Exception -> L9a
            r4 = r2
        L2a:
            if (r4 >= r3) goto L98
            r5 = r1[r4]     // Catch: java.lang.Exception -> L9a
            java.lang.String r6 = r5.getName()     // Catch: java.lang.Exception -> L9a
            r7 = -1
            int r8 = r6.hashCode()     // Catch: java.lang.Exception -> L9a
            r9 = -1383228885(0xffffffffad8d9a2b, float:-1.6098308E-11)
            if (r8 == r9) goto L6a
            r9 = 115029(0x1c155, float:1.6119E-40)
            if (r8 == r9) goto L60
            r9 = 3317767(0x32a007, float:4.649182E-39)
            if (r8 == r9) goto L56
            r9 = 108511772(0x677c21c, float:4.6598146E-35)
            if (r8 == r9) goto L4c
            goto L74
        L4c:
            java.lang.String r8 = "right"
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L9a
            if (r6 == 0) goto L74
            r6 = 2
            goto L75
        L56:
            java.lang.String r8 = "left"
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L9a
            if (r6 == 0) goto L74
            r6 = r2
            goto L75
        L60:
            java.lang.String r8 = "top"
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L9a
            if (r6 == 0) goto L74
            r6 = 1
            goto L75
        L6a:
            java.lang.String r8 = "bottom"
            boolean r6 = r6.equals(r8)     // Catch: java.lang.Exception -> L9a
            if (r6 == 0) goto L74
            r6 = 3
            goto L75
        L74:
            r6 = r7
        L75:
            switch(r6) {
                case 0: goto L8e;
                case 1: goto L87;
                case 2: goto L80;
                case 3: goto L79;
                default: goto L78;
            }     // Catch: java.lang.Exception -> L9a
        L78:
            goto L95
        L79:
            int r5 = r5.getInt(r10)     // Catch: java.lang.Exception -> L9a
            r0.bottom = r5     // Catch: java.lang.Exception -> L9a
            goto L95
        L80:
            int r5 = r5.getInt(r10)     // Catch: java.lang.Exception -> L9a
            r0.right = r5     // Catch: java.lang.Exception -> L9a
            goto L95
        L87:
            int r5 = r5.getInt(r10)     // Catch: java.lang.Exception -> L9a
            r0.top = r5     // Catch: java.lang.Exception -> L9a
            goto L95
        L8e:
            int r5 = r5.getInt(r10)     // Catch: java.lang.Exception -> L9a
            r0.left = r5     // Catch: java.lang.Exception -> L9a
        L95:
            int r4 = r4 + 1
            goto L2a
        L98:
            return r0
        L99:
            goto La2
        L9a:
            r10 = move-exception
            java.lang.String r10 = "DrawableUtils"
            java.lang.String r0 = "Couldn't obtain the optical insets. Ignoring."
            android.util.Log.e(r10, r0)
        La2:
            android.graphics.Rect r10 = android.support.v7.widget.DrawableUtils.INSETS_NONE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.DrawableUtils.getOpticalBounds(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fixDrawable(@NonNull Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && VECTOR_DRAWABLE_CLAZZ_NAME.equals(drawable.getClass().getName())) {
            fixVectorDrawableTinting(drawable);
        }
    }

    public static boolean canSafelyMutateDrawable(@NonNull Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 15 || !(drawable instanceof InsetDrawable)) {
            if (Build.VERSION.SDK_INT >= 15 || !(drawable instanceof GradientDrawable)) {
                if (Build.VERSION.SDK_INT >= 17 || !(drawable instanceof LayerDrawable)) {
                    if (!(drawable instanceof DrawableContainer)) {
                        if (drawable instanceof WrappedDrawable) {
                            return canSafelyMutateDrawable(((WrappedDrawable) drawable).getWrappedDrawable());
                        }
                        if (drawable instanceof DrawableWrapper) {
                            return canSafelyMutateDrawable(((DrawableWrapper) drawable).getWrappedDrawable());
                        }
                        if (drawable instanceof ScaleDrawable) {
                            return canSafelyMutateDrawable(((ScaleDrawable) drawable).getDrawable());
                        }
                        return true;
                    }
                    Drawable.ConstantState constantState = drawable.getConstantState();
                    if (constantState instanceof DrawableContainer.DrawableContainerState) {
                        for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
                            if (!canSafelyMutateDrawable(drawable2)) {
                                return false;
                            }
                        }
                        return true;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    private static void fixVectorDrawableTinting(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(ThemeUtils.CHECKED_STATE_SET);
        } else {
            drawable.setState(ThemeUtils.EMPTY_STATE_SET);
        }
        drawable.setState(state);
    }

    public static PorterDuff.Mode parseTintMode(int i, PorterDuff.Mode mode) {
        if (i != 3) {
            if (i != 5) {
                if (i == 9) {
                    return PorterDuff.Mode.SRC_ATOP;
                }
                switch (i) {
                    case 14:
                        return PorterDuff.Mode.MULTIPLY;
                    case 15:
                        return PorterDuff.Mode.SCREEN;
                    case 16:
                        return PorterDuff.Mode.ADD;
                    default:
                        return mode;
                }
            }
            return PorterDuff.Mode.SRC_IN;
        }
        return PorterDuff.Mode.SRC_OVER;
    }
}
