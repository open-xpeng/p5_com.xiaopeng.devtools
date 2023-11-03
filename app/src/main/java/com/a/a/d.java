package com.a.a;

/* compiled from: IntEvaluator.java */
/* loaded from: classes11.dex */
public class d implements h<Integer> {
    @Override // com.a.a.h
    public Integer evaluate(float f, Integer num, Integer num2) {
        int intValue = num.intValue();
        return Integer.valueOf((int) (intValue + (f * (num2.intValue() - intValue))));
    }
}
