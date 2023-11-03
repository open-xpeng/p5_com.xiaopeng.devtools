package com.a.a;

import android.view.animation.Interpolator;

/* compiled from: Keyframe.java */
/* loaded from: classes11.dex */
public abstract class e implements Cloneable {
    float mFraction;
    Class mValueType;
    private Interpolator mInterpolator = null;
    boolean mHasValue = false;

    @Override // 
    /* renamed from: dr */
    public abstract e clone();

    public abstract Object getValue();

    public static e b(float f, float f2) {
        return new a(f, f2);
    }

    public static e b(float f) {
        return new a(f);
    }

    public float getFraction() {
        return this.mFraction;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.mInterpolator = interpolator;
    }

    /* compiled from: Keyframe.java */
    /* loaded from: classes11.dex */
    static class a extends e {
        float mValue;

        a(float f, float f2) {
            this.mFraction = f;
            this.mValue = f2;
            this.mValueType = Float.TYPE;
            this.mHasValue = true;
        }

        a(float f) {
            this.mFraction = f;
            this.mValueType = Float.TYPE;
        }

        public float getFloatValue() {
            return this.mValue;
        }

        @Override // com.a.a.e
        public Object getValue() {
            return Float.valueOf(this.mValue);
        }

        @Override // com.a.a.e
        /* renamed from: ds */
        public a dr() {
            a aVar = new a(getFraction(), this.mValue);
            aVar.setInterpolator(getInterpolator());
            return aVar;
        }
    }
}
