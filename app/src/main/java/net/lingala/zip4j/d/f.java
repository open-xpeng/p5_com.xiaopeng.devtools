package net.lingala.zip4j.d;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Calendar;

/* compiled from: Zip4jUtil.java */
/* loaded from: classes13.dex */
public class f {
    public static boolean fy(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static long M(long j) {
        if (j < 0) {
            return 2162688L;
        }
        long N = N(j);
        if (N != 2162688) {
            return N + ((j % 2000) << 32);
        }
        return 2162688L;
    }

    private static long N(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(1);
        if (i < 1980) {
            return 2162688L;
        }
        return (calendar.get(13) >> 1) | ((i - 1980) << 25) | ((calendar.get(2) + 1) << 21) | (calendar.get(5) << 16) | (calendar.get(11) << 11) | (calendar.get(12) << 5);
    }

    public static byte[] b(char[] cArr) {
        try {
            ByteBuffer encode = d.apQ.encode(CharBuffer.wrap(cArr));
            byte[] bArr = new byte[encode.limit()];
            encode.get(bArr);
            return bArr;
        } catch (Exception e) {
            byte[] bArr2 = new byte[cArr.length];
            for (int i = 0; i < cArr.length; i++) {
                bArr2[i] = (byte) cArr[i];
            }
            return bArr2;
        }
    }
}
