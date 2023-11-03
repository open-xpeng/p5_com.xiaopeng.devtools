package net.lingala.zip4j.model.enums;

import net.lingala.zip4j.exception.ZipException;

/* loaded from: classes13.dex */
public enum CompressionMethod {
    STORE(0),
    DEFLATE(8),
    AES_INTERNAL_ONLY(99);
    
    private int code;

    CompressionMethod(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public static CompressionMethod ft(int i) throws ZipException {
        CompressionMethod[] values;
        for (CompressionMethod compressionMethod : values()) {
            if (compressionMethod.getCode() == i) {
                return compressionMethod;
            }
        }
        throw new ZipException("Unknown compression method", ZipException.Type.UNKNOWN_COMPRESSION_METHOD);
    }
}
