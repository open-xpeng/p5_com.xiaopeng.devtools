package net.lingala.zip4j.model.enums;

/* loaded from: classes13.dex */
public enum AesVersion {
    ONE(1),
    TWO(2);
    
    private int versionNumber;

    AesVersion(int i) {
        this.versionNumber = i;
    }

    public int cq() {
        return this.versionNumber;
    }

    public static AesVersion fs(int i) {
        AesVersion[] values;
        for (AesVersion aesVersion : values()) {
            if (aesVersion.versionNumber == i) {
                return aesVersion;
            }
        }
        throw new IllegalArgumentException("Unsupported Aes version");
    }
}
