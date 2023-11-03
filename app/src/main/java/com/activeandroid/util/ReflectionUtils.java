package com.activeandroid.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.serializer.TypeSerializer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes11.dex */
public final class ReflectionUtils {
    public static boolean isModel(Class<?> cls) {
        return isSubclassOf(cls, Model.class) && !Modifier.isAbstract(cls.getModifiers());
    }

    public static boolean isTypeSerializer(Class<?> cls) {
        return isSubclassOf(cls, TypeSerializer.class);
    }

    public static <T> T getMetaData(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                return (T) applicationInfo.metaData.get(str);
            }
            return null;
        } catch (Exception e) {
            Log.w("Couldn't find meta-data: " + str);
            return null;
        }
    }

    public static Set<Field> getDeclaredColumnFields(Class<?> cls) {
        Set<Field> emptySet = Collections.emptySet();
        if (isSubclassOf(cls, Model.class) || Model.class.equals(cls)) {
            emptySet = new LinkedHashSet<>();
            Field[] declaredFields = cls.getDeclaredFields();
            Arrays.sort(declaredFields, new Comparator<Field>() { // from class: com.activeandroid.util.ReflectionUtils.1
                @Override // java.util.Comparator
                public int compare(Field field, Field field2) {
                    return field2.getName().compareTo(field.getName());
                }
            });
            for (Field field : declaredFields) {
                if (field.isAnnotationPresent(Column.class)) {
                    emptySet.add(field);
                }
            }
            Class<? super Object> superclass = cls.getSuperclass();
            if (superclass != null) {
                emptySet.addAll(getDeclaredColumnFields(superclass));
            }
        }
        return emptySet;
    }

    public static boolean isSubclassOf(Class<?> cls, Class<?> cls2) {
        if (cls.getSuperclass() != null) {
            if (cls.getSuperclass().equals(cls2)) {
                return true;
            }
            return isSubclassOf(cls.getSuperclass(), cls2);
        }
        return false;
    }
}
