package com.activeandroid.serializer;

import java.util.Date;

/* loaded from: classes11.dex */
public final class UtilDateSerializer extends TypeSerializer {
    @Override // com.activeandroid.serializer.TypeSerializer
    public Class<?> getDeserializedType() {
        return Date.class;
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public Class<?> getSerializedType() {
        return Long.TYPE;
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public Long serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return Long.valueOf(((Date) obj).getTime());
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public Date deserialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return new Date(((Long) obj).longValue());
    }
}
