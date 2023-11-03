package com.activeandroid.serializer;

import java.io.File;

/* loaded from: classes11.dex */
public final class FileSerializer extends TypeSerializer {
    @Override // com.activeandroid.serializer.TypeSerializer
    public Class<?> getDeserializedType() {
        return File.class;
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
        return ((File) obj).toString();
    }

    @Override // com.activeandroid.serializer.TypeSerializer
    public File deserialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return new File((String) obj);
    }
}
