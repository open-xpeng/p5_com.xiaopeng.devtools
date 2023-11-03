package com.activeandroid.serializer;

/* loaded from: classes11.dex */
public abstract class TypeSerializer {
    public abstract Object deserialize(Object obj);

    public abstract Class<?> getDeserializedType();

    public abstract Class<?> getSerializedType();

    public abstract Object serialize(Object obj);
}
