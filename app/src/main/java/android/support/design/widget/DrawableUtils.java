package android.support.design.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
class DrawableUtils {
    private static final String LOG_TAG = "DrawableUtils";
    private static Method setConstantStateMethod;
    private static boolean setConstantStateMethodFetched;

    private DrawableUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean setContainerConstantState(DrawableContainer drawableContainer, Drawable.ConstantState constantState) {
        return setContainerConstantStateV9(drawableContainer, constantState);
    }

    private static boolean setContainerConstantStateV9(DrawableContainer drawableContainer, Drawable.ConstantState constantState) {
        if (!setConstantStateMethodFetched) {
            try {
                setConstantStateMethod = DrawableContainer.class.getDeclaredMethod("setConstantState", DrawableContainer.DrawableContainerState.class);
                setConstantStateMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.e(LOG_TAG, "Could not fetch setConstantState(). Oh well.");
            }
            setConstantStateMethodFetched = true;
        }
        if (setConstantStateMethod != null) {
            try {
                setConstantStateMethod.invoke(drawableContainer, constantState);
                return true;
            } catch (Exception e2) {
                Log.e(LOG_TAG, "Could not invoke setConstantState(). Oh well.");
            }
        }
        return false;
    }
}
