package net.lingala.zip4j.d;

/* compiled from: BitUtils.java */
/* loaded from: classes13.dex */
public class a {
    public static boolean a(byte b, int i) {
        return ((1 << i) & ((long) b)) != 0;
    }

    public static byte b(byte b, int i) {
        return (byte) (b | (1 << i));
    }

    public static byte c(byte b, int i) {
        return (byte) (b & (~(1 << i)));
    }
}
