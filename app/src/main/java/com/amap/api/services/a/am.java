package com.amap.api.services.a;

import android.content.Context;
import java.lang.reflect.Constructor;

/* compiled from: InstanceFactory.java */
/* loaded from: classes11.dex */
public class am {
    public static <T> T a(Context context, l lVar, String str, Class cls, Class[] clsArr, Object[] objArr) throws au {
        an anVar;
        Class<?> loadClass;
        try {
            anVar = ao.bq().d(context, lVar);
        } catch (Throwable th) {
            aw.a(th, "InstanceFactory", "getInstance1");
            anVar = null;
        }
        if (anVar != null) {
            try {
                if (anVar.a() && anVar.d && (loadClass = anVar.loadClass(str)) != null) {
                    Constructor<?> declaredConstructor = loadClass.getDeclaredConstructor(clsArr);
                    declaredConstructor.setAccessible(true);
                    return (T) declaredConstructor.newInstance(objArr);
                }
            } catch (Throwable th2) {
                aw.a(th2, "InstanceFactory", "getInstance1()");
            }
        }
        if (cls == null) {
            return null;
        }
        try {
            Constructor<T> declaredConstructor2 = cls.getDeclaredConstructor(clsArr);
            declaredConstructor2.setAccessible(true);
            return declaredConstructor2.newInstance(objArr);
        } catch (Throwable th3) {
            aw.a(th3, "InstanceFactory", "getInstance2()");
            throw new au("获取对象错误");
        }
    }
}
