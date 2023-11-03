package com.a.a;

/* compiled from: FloatEvaluator.java */
/* loaded from: classes11.dex */
public class b implements h<Number> {
    @Override // com.a.a.h
    public Float evaluate(float f, Number number, Number number2) {
        float floatValue = number.floatValue();
        return Float.valueOf(floatValue + (f * (number2.floatValue() - floatValue)));
    }
}
