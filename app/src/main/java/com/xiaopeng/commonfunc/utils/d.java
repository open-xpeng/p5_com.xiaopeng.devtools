package com.xiaopeng.commonfunc.utils;

import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: DataHelp.java */
/* loaded from: classes11.dex */
public class d {
    public static final Charset pb = StandardCharsets.US_ASCII;
    public static final int[] pc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 12, 16, 20, 24, 32, 48, 64};

    public static byte bH(int i) {
        return (byte) i;
    }

    public static int b(byte b) {
        return b & 255;
    }

    public static int t(byte[] bArr) {
        return ((bArr[1] & 255) << 8) | (bArr[0] & 255);
    }

    public static byte[] c(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static String a(byte[] bArr, boolean z) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(2 * bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append("0123456789ABCDEF".charAt((bArr[i] >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(bArr[i] & 15));
            if (z) {
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    public static com.xiaopeng.lib.http.a.a a(IResponse iResponse) {
        String body = iResponse.body();
        com.xiaopeng.lib.utils.c.g("CommonUtils", "response data. response: " + body);
        try {
            JSONObject jSONObject = new JSONObject(body);
            com.xiaopeng.lib.http.a.a aVar = new com.xiaopeng.lib.http.a.a();
            aVar.setCode(jSONObject.getInt("code"));
            try {
                aVar.setData(jSONObject.getString("data"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                aVar.dB(jSONObject.getString("msg"));
                return aVar;
            } catch (Exception e2) {
                e2.printStackTrace();
                return aVar;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String bI(int i) {
        int length = "defkSTUB345opqCDElxyzA26cQRF8IYZ0mJabKLMuPvwNOGHVWXnrst179".length();
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("defkSTUB345opqCDElxyzA26cQRF8IYZ0mJabKLMuPvwNOGHVWXnrst179".charAt(random.nextInt(length)));
        }
        return stringBuffer.toString();
    }

    private static String a(Charset charset, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        CharBuffer decode = charset.decode(ByteBuffer.wrap(bArr));
        return new String(decode.array(), 0, decode.length());
    }

    public static String a(BitSet bitSet, int i, int i2) {
        return a(pb, b(bitSet, i, i2));
    }

    private static byte[] b(BitSet bitSet, int i, int i2) {
        byte[] bArr = new byte[((i2 - i) + 7) / 8];
        int i3 = i;
        while (i3 < i2) {
            int i4 = i3 + 8;
            bArr[(i3 - i) / 8] = (byte) c(bitSet, i3, i4);
            i3 = i4;
        }
        return bArr;
    }

    public static int c(BitSet bitSet, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i2; i4++) {
            if (bitSet.get(i4)) {
                i3 = (int) (i3 + Math.pow(2.0d, i4 - i));
            }
        }
        return i3;
    }

    public static long d(BitSet bitSet, int i, int i2) {
        long j = 0;
        for (int i3 = i; i3 < i2; i3++) {
            if (bitSet.get(i3)) {
                j = (long) (j + Math.pow(2.0d, i3 - i));
            }
        }
        return j;
    }

    public static String e(BitSet bitSet, int i, int i2) {
        return a(b(bitSet, i, i2), false);
    }
}
