package com.xiaopeng.devtools.model.b;

import com.xiaopeng.a.a;
import com.xiaopeng.devtools.utils.d;
import com.xiaopeng.devtools.utils.e;
import com.xiaopeng.lib.utils.c;
import java.util.ArrayList;
import java.util.BitSet;

/* compiled from: DidModel.java */
/* loaded from: classes12.dex */
public class a {
    public static void a(String str, ArrayList<String> arrayList) {
        char c;
        if (!a.C0041a.dX(str)) {
            return;
        }
        int ea = a.C0041a.ea(str);
        int dY = a.C0041a.dY(str);
        int dZ = a.C0041a.dZ(str);
        String[] split = a.C0041a.eb(str).replace("         ", "").split(",");
        byte[] n = e.n(dY, dZ);
        BitSet bitSet = null;
        if (n == null || n.length < ea) {
            c.a("DidModel", "value read from DID[%d : %d] fail: length not correct", Integer.valueOf(dY), Integer.valueOf(dZ));
        } else {
            bitSet = BitSet.valueOf(n);
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            try {
                int indexOf = str2.indexOf("~");
                int indexOf2 = str2.indexOf("(");
                int indexOf3 = str2.indexOf(")");
                int parseInt = Integer.parseInt(str2.substring(0, indexOf));
                int parseInt2 = Integer.parseInt(str2.substring(indexOf + 1, indexOf2));
                String[] split2 = str2.substring(indexOf2 + 1, indexOf3).split("@");
                sb.setLength(0);
                sb.append(split2[0]);
                sb.append(" ");
                sb.append(":");
                sb.append(" ");
                if (bitSet != null) {
                    String str3 = split2[1];
                    switch (str3.hashCode()) {
                        case -15964427:
                            if (str3.equals("unsigned")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 103195:
                            if (str3.equals("hex")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 3118337:
                            if (str3.equals("enum")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 93106001:
                            if (str3.equals("ascii")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1142467313:
                            if (str3.equals("unsignedlong")) {
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
                            sb.append(d.a(bitSet, parseInt, parseInt2 + 1));
                            break;
                        case 1:
                            int c2 = d.c(bitSet, parseInt, parseInt2 + 1);
                            String[] split3 = split2[2].split(";");
                            if (c2 < split3.length) {
                                sb.append(split3[c2]);
                                break;
                            } else {
                                sb.append("UNKNOWN");
                                break;
                            }
                        case 2:
                            sb.append(d.c(bitSet, parseInt, parseInt2 + 1));
                            if (split2.length > 2) {
                                sb.append(split2[2]);
                                break;
                            }
                            break;
                        case 3:
                            sb.append(d.d(bitSet, parseInt, parseInt2 + 1));
                            if (split2.length > 2) {
                                sb.append(split2[2]);
                                break;
                            }
                            break;
                        case 4:
                            sb.append(d.e(bitSet, parseInt, parseInt2 + 1));
                            break;
                    }
                }
                arrayList.add(sb.toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
}
