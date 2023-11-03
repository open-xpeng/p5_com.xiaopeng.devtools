package com.activeandroid.serializer;

import java.math.BigDecimal;

/* loaded from: classes11.dex */
public final class BigDecimalSerializer extends TypeSerializer {
    @Override // com.activeandroid.serializer.TypeSerializer
    public Class<?> getDeserializedType() {
        return BigDecimal.class;
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public Class<?> getSerializedType() {
        return String.class;
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public String serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return ((BigDecimal) obj).toString();
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public BigDecimal deserialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return new BigDecimal((String) obj);
    }
}
