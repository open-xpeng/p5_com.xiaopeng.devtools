package com.xiaopeng.commonfunc.b.b;

import com.xiaopeng.commonfunc.utils.d;
import com.xiaopeng.commonfunc.utils.e;
import com.xiaopeng.lib.utils.c;
import java.util.BitSet;

/* compiled from: DidModel.java */
/* loaded from: classes11.dex */
public class b {
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T a(int i, int i2, int i3, String str) {
        BitSet bitSet;
        Object obj;
        byte[] n = e.n(i, i2);
        char c = 2;
        T t = null;
        if (n == null || n.length < i3) {
            c.a("DidModel", "value read from DID[%d : %d] fail: length not correct", Integer.valueOf(i), Integer.valueOf(i2));
            bitSet = null;
        } else {
            bitSet = BitSet.valueOf(n);
        }
        try {
            int indexOf = str.indexOf("~");
            int indexOf2 = str.indexOf("(");
            int indexOf3 = str.indexOf(")");
            int parseInt = Integer.parseInt(str.substring(0, indexOf));
            int parseInt2 = Integer.parseInt(str.substring(indexOf + 1, indexOf2));
            String[] split = str.substring(indexOf2 + 1, indexOf3).split("@");
            if (bitSet != null) {
                String str2 = split[0];
                switch (str2.hashCode()) {
                    case -15964427:
                        if (str2.equals("unsigned")) {
                            break;
                        }
                        c = 65535;
                        break;
                    case 103195:
                        if (str2.equals("hex")) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 3118337:
                        if (str2.equals("enum")) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 93106001:
                        if (str2.equals("ascii")) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1142467313:
                        if (str2.equals("unsignedlong")) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                switch (c) {
                    case 0:
                        obj = d.a(bitSet, parseInt, parseInt2 + 1);
                        t = obj;
                        break;
                    case 1:
                        int c2 = d.c(bitSet, parseInt, parseInt2 + 1);
                        Object[] split2 = split[1].split(";");
                        if (c2 < split2.length) {
                            obj = split2[c2];
                            t = obj;
                            break;
                        }
                        break;
                    case 2:
                        obj = Integer.valueOf(d.c(bitSet, parseInt, parseInt2 + 1));
                        t = obj;
                        break;
                    case 3:
                        obj = Long.valueOf(d.d(bitSet, parseInt, parseInt2 + 1));
                        t = obj;
                        break;
                    case 4:
                        obj = d.e(bitSet, parseInt, parseInt2 + 1);
                        t = obj;
                        break;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        c.i("DidModel", "res [%s]", t);
        return t;
    }
}
