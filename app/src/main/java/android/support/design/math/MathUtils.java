package android.support.design.math;

/* loaded from: classes4.dex */
public final class MathUtils {
    public static final float DEFAULT_EPSILON = 1.0E-4f;
    private static final float DEG_TO_RAD = 0.017453292f;
    private static final float RAD_TO_DEG = 57.295784f;

    private MathUtils() {
    }

    public static float abs(float f) {
        return f > 0.0f ? f : -f;
    }

    public static int constrain(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public static long constrain(long j, long j2, long j3) {
        return j < j2 ? j2 : j > j3 ? j3 : j;
    }

    public static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public static int nearest(int i, int i2, int i3) {
        return abs((float) (i - i2)) < abs((float) (i - i3)) ? i2 : i3;
    }

    public static float nearest(float f, float f2, float f3) {
        return abs(f - f2) < abs(f - f3) ? f2 : f3;
    }

    public static float log(float f) {
        return (float) Math.log(f);
    }

    public static float exp(float f) {
        return (float) Math.exp(f);
    }

    public static float pow(float f, float f2) {
        return (float) Math.pow(f, f2);
    }

    public static float max(float f, float f2) {
        return f > f2 ? f : f2;
    }

    public static float max(int i, int i2) {
        return i > i2 ? i : i2;
    }

    public static float max(float f, float f2, float f3) {
        if (f > f2) {
            if (f > f3) {
                return f;
            }
        } else if (f2 > f3) {
            return f2;
        }
        return f3;
    }

    public static float max(int i, int i2, int i3) {
        if (i > i2) {
            if (i <= i3) {
                i = i3;
            }
            return i;
        }
        if (i2 <= i3) {
            i2 = i3;
        }
        return i2;
    }

    public static float max(float f, float f2, float f3, float f4) {
        return (f <= f2 || f <= f3 || f <= f4) ? (f2 <= f3 || f2 <= f4) ? f3 > f4 ? f3 : f4 : f2 : f;
    }

    public static float max(int i, int i2, int i3, int i4) {
        return (i <= i2 || i <= i3 || i <= i4) ? (i2 <= i3 || i2 <= i4) ? i3 > i4 ? i3 : i4 : i2 : i;
    }

    public static float min(float f, float f2) {
        return f < f2 ? f : f2;
    }

    public static float min(int i, int i2) {
        return i < i2 ? i : i2;
    }

    public static float min(float f, float f2, float f3) {
        if (f < f2) {
            if (f < f3) {
                return f;
            }
        } else if (f2 < f3) {
            return f2;
        }
        return f3;
    }

    public static float min(int i, int i2, int i3) {
        if (i < i2) {
            if (i >= i3) {
                i = i3;
            }
            return i;
        }
        if (i2 >= i3) {
            i2 = i3;
        }
        return i2;
    }

    public static float dist(float f, float f2, float f3, float f4) {
        return (float) Math.hypot(f3 - f, f4 - f2);
    }

    public static float dist(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = f4 - f;
        float f8 = f5 - f2;
        float f9 = f6 - f3;
        return (float) Math.sqrt((f7 * f7) + (f8 * f8) + (f9 * f9));
    }

    public static float mag(float f, float f2) {
        return (float) Math.hypot(f, f2);
    }

    public static float mag(float f, float f2, float f3) {
        return (float) Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
    }

    public static float sq(float f) {
        return f * f;
    }

    public static float sqrt(float f) {
        return (float) Math.sqrt(f);
    }

    public static float dot(float f, float f2, float f3, float f4) {
        return (f * f3) + (f2 * f4);
    }

    public static float cross(float f, float f2, float f3, float f4) {
        return (f * f4) - (f2 * f3);
    }

    public static float radians(float f) {
        return f * DEG_TO_RAD;
    }

    public static float degrees(float f) {
        return f * RAD_TO_DEG;
    }

    public static float acos(float f) {
        return (float) Math.acos(f);
    }

    public static float asin(float f) {
        return (float) Math.asin(f);
    }

    public static float atan(float f) {
        return (float) Math.atan(f);
    }

    public static float atan2(float f, float f2) {
        return (float) Math.atan2(f, f2);
    }

    public static float tan(float f) {
        return (float) Math.tan(f);
    }

    public static float lerp(float f, float f2, float f3) {
        return ((1.0f - f3) * f) + (f3 * f2);
    }

    public static float norm(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public static float map(float f, float f2, float f3, float f4, float f5) {
        return f3 + ((f3 - f4) * ((f5 - f) / (f2 - f)));
    }

    public static boolean leq(float f, float f2, float f3) {
        return f <= f2 + f3;
    }

    public static boolean geq(float f, float f2, float f3) {
        return f + f3 >= f2;
    }

    public static boolean eq(float f, float f2, float f3) {
        return abs(f - f2) <= f3;
    }

    public static boolean neq(float f, float f2, float f3) {
        return abs(f - f2) > f3;
    }

    public static float distanceToFurthestCorner(float f, float f2, float f3, float f4, float f5, float f6) {
        return max(dist(f, f2, f3, f4), dist(f, f2, f5, f4), dist(f, f2, f5, f6), dist(f, f2, f3, f6));
    }
}
