package com.a.a;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: PropertyValuesHolder.java */
/* loaded from: classes11.dex */
public class g implements Cloneable {
    private static final h mW = new d();
    private static final h mZ = new b();
    private static Class[] na = {Float.TYPE, Float.class, Double.TYPE, Integer.TYPE, Double.class, Integer.class};
    private static Class[] nb = {Integer.TYPE, Integer.class, Float.TYPE, Double.TYPE, Float.class, Double.class};
    private static Class[] nc = {Double.TYPE, Double.class, Float.TYPE, Integer.TYPE, Float.class, Integer.class};
    private static final HashMap<Class, HashMap<String, Method>> nd = new HashMap<>();
    private static final HashMap<Class, HashMap<String, Method>> ne = new HashMap<>();
    String mPropertyName;
    private h mS;
    Method mSetter;
    protected com.a.b.a mT;
    final Object[] mTmpValueArray;
    private Method mU;
    f mV;
    Class mValueType;
    final ReentrantReadWriteLock nf;
    private Object ng;

    private g(String str) {
        this.mSetter = null;
        this.mU = null;
        this.mV = null;
        this.nf = new ReentrantReadWriteLock();
        this.mTmpValueArray = new Object[1];
        this.mPropertyName = str;
    }

    public static g a(String str, float... fArr) {
        return new a(str, fArr);
    }

    public void setFloatValues(float... fArr) {
        this.mValueType = Float.TYPE;
        this.mV = f.a(fArr);
    }

    @Override // 
    /* renamed from: dt */
    public g clone() {
        try {
            g gVar = (g) super.clone();
            gVar.mPropertyName = this.mPropertyName;
            gVar.mT = this.mT;
            gVar.mV = this.mV.clone();
            gVar.mS = this.mS;
            return gVar;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init() {
        if (this.mS == null) {
            this.mS = this.mValueType == Integer.class ? mW : this.mValueType == Float.class ? mZ : null;
        }
        if (this.mS != null) {
            this.mV.a(this.mS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void calculateValue(float f) {
        this.ng = this.mV.getValue(f);
    }

    public String getPropertyName() {
        return this.mPropertyName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getAnimatedValue() {
        return this.ng;
    }

    public String toString() {
        return this.mPropertyName + ": " + this.mV.toString();
    }

    /* compiled from: PropertyValuesHolder.java */
    /* loaded from: classes11.dex */
    static class a extends g {
        float mFloatAnimatedValue;
        c nh;

        public a(String str, float... fArr) {
            super(str);
            setFloatValues(fArr);
        }

        @Override // com.a.a.g
        public void setFloatValues(float... fArr) {
            super.setFloatValues(fArr);
            this.nh = (c) this.mV;
        }

        @Override // com.a.a.g
        void calculateValue(float f) {
            this.mFloatAnimatedValue = this.nh.getFloatValue(f);
        }

        @Override // com.a.a.g
        Object getAnimatedValue() {
            return Float.valueOf(this.mFloatAnimatedValue);
        }

        @Override // com.a.a.g
        /* renamed from: du */
        public a dt() {
            a aVar = (a) super.clone();
            aVar.nh = (c) aVar.mV;
            return aVar;
        }
    }
}
