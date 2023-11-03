package com.ta.utdid2.b.a;

import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AESUtils.java */
/* loaded from: classes11.dex */
public class a {
    public static byte[] a = {48, 48, 49, 55, 68, 67, 49, 66, TestResultItem.RESULT_ENTER, 50, 50, 53, 56, 53, 53, 52, 67, TestResultItem.RESULT_FAIL, 48, 50, 67, 53, 55, 66, 55, 56, TestResultItem.RESULT_ENTER, 55, 52, 48, 65, 53};

    public static String d(String str, String str2) {
        byte[] bArr;
        try {
            bArr = a(m72a(str.getBytes()), str2.getBytes());
        } catch (Exception e) {
            bArr = null;
        }
        if (bArr == null) {
            return null;
        }
        return a(bArr);
    }

    public static String e(String str, String str2) {
        try {
            return new String(b(m72a(str.getBytes()), a(str2)));
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    private static byte[] m72a(byte[] bArr) throws Exception {
        return a(new String(a, 0, 32));
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        return cipher.doFinal(bArr2);
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
        return cipher.doFinal(bArr2);
    }

    public static byte[] a(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = 2 * i;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(2 * bArr.length);
        for (byte b : bArr) {
            a(stringBuffer, b);
        }
        return stringBuffer.toString();
    }

    private static void a(StringBuffer stringBuffer, byte b) {
        stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
        stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
    }
}
