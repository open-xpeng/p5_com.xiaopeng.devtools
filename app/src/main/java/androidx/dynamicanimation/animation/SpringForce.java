package androidx.dynamicanimation.animation;

import androidx.annotation.FloatRange;
import androidx.annotation.RestrictTo;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes9.dex */
public final class SpringForce implements Force {
    public static final float DAMPING_RATIO_HIGH_BOUNCY = 0.2f;
    public static final float DAMPING_RATIO_LOW_BOUNCY = 0.75f;
    public static final float DAMPING_RATIO_MEDIUM_BOUNCY = 0.5f;
    public static final float DAMPING_RATIO_NO_BOUNCY = 1.0f;
    public static final float STIFFNESS_HIGH = 10000.0f;
    public static final float STIFFNESS_LOW = 200.0f;
    public static final float STIFFNESS_MEDIUM = 1500.0f;
    public static final float STIFFNESS_VERY_LOW = 50.0f;
    private static final double UNSET = Double.MAX_VALUE;
    private static final double VELOCITY_THRESHOLD_MULTIPLIER = 62.5d;
    private double mDampedFreq;
    double mDampingRatio;
    private double mFinalPosition;
    private double mGammaMinus;
    private double mGammaPlus;
    private boolean mInitialized;
    private final DynamicAnimation.MassState mMassState;
    double mNaturalFreq;
    private double mValueThreshold;
    private double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = UNSET;
        this.mMassState = new DynamicAnimation.MassState();
    }

    public SpringForce(float f) {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = UNSET;
        this.mMassState = new DynamicAnimation.MassState();
        this.mFinalPosition = f;
    }

    public SpringForce setStiffness(@FloatRange(from = 0.0d, fromInclusive = false) float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.mNaturalFreq = Math.sqrt(f);
        this.mInitialized = false;
        return this;
    }

    public float getStiffness() {
        return (float) (this.mNaturalFreq * this.mNaturalFreq);
    }

    public SpringForce setDampingRatio(@FloatRange(from = 0.0d) float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.mDampingRatio = f;
        this.mInitialized = false;
        return this;
    }

    public float getDampingRatio() {
        return (float) this.mDampingRatio;
    }

    public SpringForce setFinalPosition(float f) {
        this.mFinalPosition = f;
        return this;
    }

    public float getFinalPosition() {
        return (float) this.mFinalPosition;
    }

    @Override // androidx.dynamicanimation.animation.Force
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public float getAcceleration(float f, float f2) {
        float finalPosition = f - getFinalPosition();
        return (float) (((-(this.mNaturalFreq * this.mNaturalFreq)) * finalPosition) - (((2.0d * this.mNaturalFreq) * this.mDampingRatio) * f2));
    }

    @Override // androidx.dynamicanimation.animation.Force
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean isAtEquilibrium(float f, float f2) {
        if (Math.abs(f2) < this.mVelocityThreshold && Math.abs(f - getFinalPosition()) < this.mValueThreshold) {
            return true;
        }
        return false;
    }

    private void init() {
        if (this.mInitialized) {
            return;
        }
        if (this.mFinalPosition == UNSET) {
            throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
        }
        if (this.mDampingRatio > 1.0d) {
            this.mGammaPlus = ((-this.mDampingRatio) * this.mNaturalFreq) + (this.mNaturalFreq * Math.sqrt((this.mDampingRatio * this.mDampingRatio) - 1.0d));
            this.mGammaMinus = ((-this.mDampingRatio) * this.mNaturalFreq) - (this.mNaturalFreq * Math.sqrt((this.mDampingRatio * this.mDampingRatio) - 1.0d));
        } else if (this.mDampingRatio >= 0.0d && this.mDampingRatio < 1.0d) {
            this.mDampedFreq = this.mNaturalFreq * Math.sqrt(1.0d - (this.mDampingRatio * this.mDampingRatio));
        }
        this.mInitialized = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DynamicAnimation.MassState updateValues(double d, double d2, long j) {
        double pow;
        double pow2;
        init();
        double d3 = j / 1000.0d;
        double d4 = d - this.mFinalPosition;
        if (this.mDampingRatio > 1.0d) {
            double d5 = d4 - (((this.mGammaMinus * d4) - d2) / (this.mGammaMinus - this.mGammaPlus));
            double d6 = ((this.mGammaMinus * d4) - d2) / (this.mGammaMinus - this.mGammaPlus);
            pow = (Math.pow(2.718281828459045d, this.mGammaMinus * d3) * d5) + (Math.pow(2.718281828459045d, this.mGammaPlus * d3) * d6);
            pow2 = (d5 * this.mGammaMinus * Math.pow(2.718281828459045d, this.mGammaMinus * d3)) + (d6 * this.mGammaPlus * Math.pow(2.718281828459045d, this.mGammaPlus * d3));
        } else if (this.mDampingRatio == 1.0d) {
            double d7 = d2 + (this.mNaturalFreq * d4);
            double d8 = d4 + (d7 * d3);
            double pow3 = Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d3) * d8;
            double pow4 = (d7 * Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d3)) + (d8 * Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d3) * (-this.mNaturalFreq));
            pow = pow3;
            pow2 = pow4;
        } else {
            double d9 = (1.0d / this.mDampedFreq) * ((this.mDampingRatio * this.mNaturalFreq * d4) + d2);
            pow = Math.pow(2.718281828459045d, (-this.mDampingRatio) * this.mNaturalFreq * d3) * ((Math.cos(this.mDampedFreq * d3) * d4) + (Math.sin(this.mDampedFreq * d3) * d9));
            pow2 = ((-this.mNaturalFreq) * pow * this.mDampingRatio) + (Math.pow(2.718281828459045d, (-this.mDampingRatio) * this.mNaturalFreq * d3) * (((-this.mDampedFreq) * d4 * Math.sin(this.mDampedFreq * d3)) + (this.mDampedFreq * d9 * Math.cos(this.mDampedFreq * d3))));
        }
        this.mMassState.mValue = (float) (pow + this.mFinalPosition);
        this.mMassState.mVelocity = (float) pow2;
        return this.mMassState;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValueThreshold(double d) {
        this.mValueThreshold = Math.abs(d);
        this.mVelocityThreshold = this.mValueThreshold * VELOCITY_THRESHOLD_MULTIPLIER;
    }
}
