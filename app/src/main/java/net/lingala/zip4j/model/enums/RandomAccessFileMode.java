package net.lingala.zip4j.model.enums;

/* loaded from: classes13.dex */
public enum RandomAccessFileMode {
    READ("r"),
    WRITE("rw");
    
    private String value;

    RandomAccessFileMode(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
