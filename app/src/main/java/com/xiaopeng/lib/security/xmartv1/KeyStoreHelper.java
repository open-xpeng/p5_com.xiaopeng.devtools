package com.xiaopeng.lib.security.xmartv1;

import android.os.Build;
import android.security.keystore.KeyProtection;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Log;
import com.xiaopeng.lib.utils.b;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.d;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@VisibleForTesting
/* loaded from: classes12.dex */
public final class KeyStoreHelper {
    private static KeyStore VM;
    private static boolean VN;
    private static String TAG = "KeyStoreHelper";
    private static String PASSWORD = d.dH("changeit");
    private static String VL = "/private/sec/XpengAndroidKeyStore";
    private static HashMap<String, SecretKey> VO = new HashMap<>();

    public static boolean init() {
        if (VM != null) {
            return true;
        }
        boolean oF = oF();
        if (!oF) {
            return oG();
        }
        return oF;
    }

    public static boolean oC() {
        if (VM == null) {
            c.f(TAG, "KeyStore was not initialized!");
            return false;
        }
        for (int i = 0; i < com.xiaopeng.lib.security.xmartv1.a.VS; i++) {
            try {
                if (!VM.containsAlias(com.xiaopeng.lib.security.xmartv1.a.VT + i)) {
                    return false;
                }
            } catch (Exception e) {
                c.a(TAG, e);
                return false;
            }
        }
        return true;
    }

    public static boolean oD() throws KeyStoreException {
        if (VM == null) {
            c.f(TAG, "KeyStore was not initialized!");
            return false;
        }
        for (int i = 0; i < com.xiaopeng.lib.security.xmartv1.a.VS; i++) {
            try {
                KeyStore keyStore = VM;
                if (keyStore.containsAlias(com.xiaopeng.lib.security.xmartv1.a.VT + i)) {
                    KeyStore keyStore2 = VM;
                    keyStore2.deleteEntry(com.xiaopeng.lib.security.xmartv1.a.VT + i);
                }
            } catch (Exception e) {
                c.a(TAG, e);
                throw e;
            }
        }
        return true;
    }

    public static void o(Map<String, byte[]> map) throws Exception {
        if (VM == null) {
            init();
        }
        if (map.size() != com.xiaopeng.lib.security.xmartv1.a.VS) {
            throw new RuntimeException("Illegal security keys!!!!");
        }
        KeyProtection keyProtection = null;
        if (Build.VERSION.SDK_INT >= 23) {
            keyProtection = new KeyProtection.Builder(3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(false).build();
        }
        for (String str : map.keySet()) {
            VM.setEntry(str, new KeyStore.SecretKeyEntry(new SecretKeySpec(map.get(str), "AES")), keyProtection);
        }
    }

    @Nullable
    public static byte[] a(ByteBuffer byteBuffer, String str, byte[] bArr) {
        if (byteBuffer == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return a(new a(byteBuffer), str, bArr);
    }

    @Nullable
    private static byte[] a(InputStream inputStream, String str, byte[] bArr) {
        if (inputStream == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            SecretKey dF = dF(str);
            if (dF == null) {
                return null;
            }
            return a(inputStream, dF, bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static byte[] c(byte[] bArr, String str) {
        try {
            SecretKey dF = dF(str);
            if (dF == null) {
                return null;
            }
            return a(bArr, dF);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private static byte[] a(byte[] bArr, SecretKey secretKey) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, secretKey, H(com.xiaopeng.lib.security.xmartv1.a.VW));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            c.a(TAG, e);
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private static byte[] a(InputStream inputStream, SecretKey secretKey, byte[] bArr) {
        byte[] bArr2;
        if (inputStream == null || bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(2, secretKey, H(bArr));
            CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
            ArrayList arrayList = new ArrayList();
            while (true) {
                int read = cipherInputStream.read();
                if (read == -1) {
                    break;
                }
                arrayList.add(Byte.valueOf((byte) read));
            }
            bArr2 = new byte[arrayList.size()];
            for (int i = 0; i < bArr2.length; i++) {
                try {
                    bArr2[i] = ((Byte) arrayList.get(i)).byteValue();
                } catch (Exception e) {
                    e = e;
                    c.f(TAG, "Exception:" + e);
                    return bArr2;
                }
            }
        } catch (Exception e2) {
            e = e2;
            bArr2 = null;
        }
        return bArr2;
    }

    @Nullable
    public static byte[] a(byte[] bArr, String str, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || TextUtils.isEmpty(str) || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            SecretKey dF = dF(str);
            if (dF == null) {
                return null;
            }
            return c(bArr, dF, bArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void oE() {
        if (VN) {
            oH();
        }
    }

    @Nullable
    private static byte[] c(byte[] bArr, SecretKey secretKey, byte[] bArr2) {
        byte[] bArr3;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(1, secretKey, H(bArr2));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, cipher);
            cipherOutputStream.write(bArr);
            cipherOutputStream.close();
            bArr3 = byteArrayOutputStream.toByteArray();
            try {
                b.closeQuietly(byteArrayOutputStream);
            } catch (Exception e) {
                e = e;
                c.a(TAG, e);
                e.printStackTrace();
                return bArr3;
            }
        } catch (Exception e2) {
            e = e2;
            bArr3 = null;
        }
        return bArr3;
    }

    @Nullable
    private static SecretKey dF(String str) throws Exception {
        if (!VM.containsAlias(str)) {
            String str2 = TAG;
            c.f(str2, "Not found the key " + str);
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        SecretKey secretKey = VO.get(str);
        if (secretKey == null) {
            secretKey = (SecretKey) VM.getKey(str, null);
            VO.put(str, secretKey);
        }
        if (secretKey == null) {
            String str3 = TAG;
            c.f(str3, "Failed to load secret key:" + str);
            return null;
        }
        String str4 = TAG;
        Log.i(str4, "cost time:\t" + (System.currentTimeMillis() - currentTimeMillis));
        return secretKey;
    }

    /* loaded from: classes12.dex */
    private static final class a extends InputStream {
        ByteBuffer VP;

        public a(ByteBuffer byteBuffer) {
            this.VP = byteBuffer;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (!this.VP.hasRemaining()) {
                return -1;
            }
            return this.VP.get() & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (!this.VP.hasRemaining()) {
                return -1;
            }
            int min = Math.min(i2, this.VP.remaining());
            this.VP.get(bArr, i, min);
            return min;
        }
    }

    private static boolean oF() {
        boolean z = false;
        try {
            VM = KeyStore.getInstance("XpengAndroidKeyStore");
            VM.load(null);
            try {
                VN = false;
                return true;
            } catch (Exception e) {
                e = e;
                z = true;
                c.f(TAG, "Failed to load key store, reason:" + e);
                return z;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private static boolean oG() {
        FileInputStream fileInputStream = null;
        boolean z = false;
        try {
            try {
                VM = KeyStore.getInstance(KeyStore.getDefaultType());
                if (new File(VL).exists()) {
                    FileInputStream fileInputStream2 = new FileInputStream(VL);
                    try {
                        VM.load(fileInputStream2, PASSWORD.toCharArray());
                        fileInputStream = fileInputStream2;
                    } catch (Exception e) {
                        e = e;
                        fileInputStream = fileInputStream2;
                        c.f(TAG, "Failed to load default key store, reason:" + e);
                        b.closeQuietly(fileInputStream);
                        return z;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        b.closeQuietly(fileInputStream);
                        throw th;
                    }
                } else {
                    VM.load(null);
                }
                try {
                    VN = true;
                    b.closeQuietly(fileInputStream);
                    return true;
                } catch (Exception e2) {
                    z = true;
                    e = e2;
                    c.f(TAG, "Failed to load default key store, reason:" + e);
                    b.closeQuietly(fileInputStream);
                    return z;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean oH() {
        FileOutputStream fileOutputStream;
        Throwable th;
        try {
            fileOutputStream = new FileOutputStream(VL);
        } catch (Exception e) {
            fileOutputStream = null;
        } catch (Throwable th2) {
            fileOutputStream = null;
            th = th2;
        }
        try {
            VM.store(fileOutputStream, PASSWORD.toCharArray());
            b.closeQuietly(fileOutputStream);
            return true;
        } catch (Exception e2) {
            b.closeQuietly(fileOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            b.closeQuietly(fileOutputStream);
            throw th;
        }
    }

    private static AlgorithmParameterSpec H(byte[] bArr) {
        if (Build.VERSION.SDK_INT < 21) {
            return new IvParameterSpec(bArr, 0, bArr.length);
        }
        return new GCMParameterSpec(128, bArr);
    }
}
