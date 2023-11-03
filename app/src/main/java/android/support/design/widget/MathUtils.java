package android.support.design.widget;

/* loaded from: classes5.dex */
class MathUtils {
    MathUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int constrain(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }
}
