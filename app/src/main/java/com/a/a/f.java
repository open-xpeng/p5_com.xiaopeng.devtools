package com.a.a;

import android.view.animation.Interpolator;
import com.a.a.e;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: KeyframeSet.java */
/* loaded from: classes11.dex */
public class f {
    Interpolator mInterpolator;
    int mNumKeyframes;
    e mP;
    e mQ;
    ArrayList<e> mR = new ArrayList<>();
    h mS;

    public f(e... eVarArr) {
        this.mNumKeyframes = eVarArr.length;
        this.mR.addAll(Arrays.asList(eVarArr));
        this.mP = this.mR.get(0);
        this.mQ = this.mR.get(this.mNumKeyframes - 1);
        this.mInterpolator = this.mQ.getInterpolator();
    }

    public static f a(float... fArr) {
        int length = fArr.length;
        e.a[] aVarArr = new e.a[Math.max(length, 2)];
        if (length != 1) {
            aVarArr[0] = (e.a) e.b(0.0f, fArr[0]);
            for (int i = 1; i < length; i++) {
                aVarArr[i] = (e.a) e.b(i / (length - 1), fArr[i]);
            }
        } else {
            aVarArr[0] = (e.a) e.b(0.0f);
            aVarArr[1] = (e.a) e.b(1.0f, fArr[0]);
        }
        return new c(aVarArr);
    }

    public void a(h hVar) {
        this.mS = hVar;
    }

    @Override // 
    /* renamed from: dq */
    public f clone() {
        ArrayList<e> arrayList = this.mR;
        int size = this.mR.size();
        e[] eVarArr = new e[size];
        for (int i = 0; i < size; i++) {
            eVarArr[i] = arrayList.get(i).clone();
        }
        return new f(eVarArr);
    }

    public Object getValue(float f) {
        if (this.mNumKeyframes == 2) {
            if (this.mInterpolator != null) {
                f = this.mInterpolator.getInterpolation(f);
            }
            return this.mS.evaluate(f, this.mP.getValue(), this.mQ.getValue());
        }
        int i = 1;
        if (f <= 0.0f) {
            e eVar = this.mR.get(1);
            Interpolator interpolator = eVar.getInterpolator();
            if (interpolator != null) {
                f = interpolator.getInterpolation(f);
            }
            float fraction = this.mP.getFraction();
            return this.mS.evaluate((f - fraction) / (eVar.getFraction() - fraction), this.mP.getValue(), eVar.getValue());
        } else if (f >= 1.0f) {
            e eVar2 = this.mR.get(this.mNumKeyframes - 2);
            Interpolator interpolator2 = this.mQ.getInterpolator();
            if (interpolator2 != null) {
                f = interpolator2.getInterpolation(f);
            }
            float fraction2 = eVar2.getFraction();
            return this.mS.evaluate((f - fraction2) / (this.mQ.getFraction() - fraction2), eVar2.getValue(), this.mQ.getValue());
        } else {
            e eVar3 = this.mP;
            while (i < this.mNumKeyframes) {
                e eVar4 = this.mR.get(i);
                if (f >= eVar4.getFraction()) {
                    i++;
                    eVar3 = eVar4;
                } else {
                    Interpolator interpolator3 = eVar4.getInterpolator();
                    if (interpolator3 != null) {
                        f = interpolator3.getInterpolation(f);
                    }
                    float fraction3 = eVar3.getFraction();
                    return this.mS.evaluate((f - fraction3) / (eVar4.getFraction() - fraction3), eVar3.getValue(), eVar4.getValue());
                }
            }
            return this.mQ.getValue();
        }
    }

    public String toString() {
        String str = " ";
        for (int i = 0; i < this.mNumKeyframes; i++) {
            str = str + this.mR.get(i).getValue() + "  ";
        }
        return str;
    }
}
