package com.a.a;

import android.view.animation.Interpolator;
import com.a.a.e;
import java.util.ArrayList;

/* compiled from: FloatKeyframeSet.java */
/* loaded from: classes11.dex */
class c extends f {
    private float mL;
    private float mM;
    private float mN;
    private boolean mO;

    public c(e.a... aVarArr) {
        super(aVarArr);
        this.mO = true;
    }

    @Override // com.a.a.f
    public Object getValue(float f) {
        return Float.valueOf(getFloatValue(f));
    }

    @Override // com.a.a.f
    /* renamed from: dp */
    public c dq() {
        ArrayList<e> arrayList = this.mR;
        int size = this.mR.size();
        e.a[] aVarArr = new e.a[size];
        for (int i = 0; i < size; i++) {
            aVarArr[i] = (e.a) arrayList.get(i).clone();
        }
        return new c(aVarArr);
    }

    public float getFloatValue(float f) {
        if (this.mNumKeyframes == 2) {
            if (this.mO) {
                this.mO = false;
                this.mL = ((e.a) this.mR.get(0)).getFloatValue();
                this.mM = ((e.a) this.mR.get(1)).getFloatValue();
                this.mN = this.mM - this.mL;
            }
            if (this.mInterpolator != null) {
                f = this.mInterpolator.getInterpolation(f);
            }
            if (this.mS == null) {
                return this.mL + (f * this.mN);
            }
            return ((Number) this.mS.evaluate(f, Float.valueOf(this.mL), Float.valueOf(this.mM))).floatValue();
        } else if (f <= 0.0f) {
            e.a aVar = (e.a) this.mR.get(0);
            e.a aVar2 = (e.a) this.mR.get(1);
            float floatValue = aVar.getFloatValue();
            float floatValue2 = aVar2.getFloatValue();
            float fraction = aVar.getFraction();
            float fraction2 = aVar2.getFraction();
            Interpolator interpolator = aVar2.getInterpolator();
            if (interpolator != null) {
                f = interpolator.getInterpolation(f);
            }
            float f2 = (f - fraction) / (fraction2 - fraction);
            return this.mS == null ? floatValue + (f2 * (floatValue2 - floatValue)) : ((Number) this.mS.evaluate(f2, Float.valueOf(floatValue), Float.valueOf(floatValue2))).floatValue();
        } else if (f >= 1.0f) {
            e.a aVar3 = (e.a) this.mR.get(this.mNumKeyframes - 2);
            e.a aVar4 = (e.a) this.mR.get(this.mNumKeyframes - 1);
            float floatValue3 = aVar3.getFloatValue();
            float floatValue4 = aVar4.getFloatValue();
            float fraction3 = aVar3.getFraction();
            float fraction4 = aVar4.getFraction();
            Interpolator interpolator2 = aVar4.getInterpolator();
            if (interpolator2 != null) {
                f = interpolator2.getInterpolation(f);
            }
            float f3 = (f - fraction3) / (fraction4 - fraction3);
            return this.mS == null ? floatValue3 + (f3 * (floatValue4 - floatValue3)) : ((Number) this.mS.evaluate(f3, Float.valueOf(floatValue3), Float.valueOf(floatValue4))).floatValue();
        } else {
            e.a aVar5 = (e.a) this.mR.get(0);
            int i = 1;
            while (i < this.mNumKeyframes) {
                e.a aVar6 = (e.a) this.mR.get(i);
                if (f >= aVar6.getFraction()) {
                    i++;
                    aVar5 = aVar6;
                } else {
                    Interpolator interpolator3 = aVar6.getInterpolator();
                    if (interpolator3 != null) {
                        f = interpolator3.getInterpolation(f);
                    }
                    float fraction5 = (f - aVar5.getFraction()) / (aVar6.getFraction() - aVar5.getFraction());
                    float floatValue5 = aVar5.getFloatValue();
                    float floatValue6 = aVar6.getFloatValue();
                    return this.mS == null ? floatValue5 + (fraction5 * (floatValue6 - floatValue5)) : ((Number) this.mS.evaluate(fraction5, Float.valueOf(floatValue5), Float.valueOf(floatValue6))).floatValue();
                }
            }
            return ((Number) this.mR.get(this.mNumKeyframes - 1).getValue()).floatValue();
        }
    }
}
