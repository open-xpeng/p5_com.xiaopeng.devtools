package android.pso;

import android.content.Context;
import android.pso.PsoCrypto;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

/* loaded from: classes.dex */
public class XpPso extends PsoAKS implements PsoCrypto {
    public static boolean stop = false;
    public static boolean isBleService = false;
    public static boolean logSW = true;
    private static int c = 28;
    private float d = 0.0f;
    private float e = 0.0f;
    private long[] f = {0};
    private long[] g = {0};
    private long[] h = {0};
    private boolean i = false;
    SecretKey b = null;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class E28OtaPackhead {
        byte[] cert_id;
        long datalen;
        byte[] datasha256;
        byte[] headsha256;
        byte[] key_id;
        byte[] sign;
        long timestamp;
        byte[] vin;

        private E28OtaPackhead() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class E28decKeybin {
        ArrayList<KeyInfo> CommKey;
        ArrayList<CertInfo> CommSignCert;
        ArrayList<KeyInfo> OwnKey;
        ArrayList<CertInfo> OwnSignCert;
        int result;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class CertInfo {
            byte[] CertPem;
            byte[] ID;
            byte[] PriPem;

            CertInfo() {
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public class KeyInfo {
            byte[] ID;
            byte[] KeyValue;

            KeyInfo() {
            }
        }

        private E28decKeybin() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class OtaEncBinInfo {
        int BINHeaderDubExVer;
        int BINType;
        int CheckSum;
        byte[] SignData;
        long dstBinLen;
        long dstBinOffset;
        int result;

        private OtaEncBinInfo() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PSOdecKeybin {
        ArrayList<byte[]> ExtAES;
        ArrayList<byte[]> ExtCert;
        ArrayList<Integer> ExtCertLen;
        ArrayList<byte[]> ExtCustomkey;
        ArrayList<byte[]> ExtPrivateKey;
        ArrayList<Integer> ExtPrivateKeyLen;
        ArrayList<byte[]> IntAES;
        ArrayList<byte[]> IntCert;
        ArrayList<Integer> IntCertLen;
        ArrayList<byte[]> IntCustomkey;
        ArrayList<byte[]> IntPrivateKey;
        ArrayList<Integer> IntPrivateKeyLen;
        int Version;
        int result;

        private PSOdecKeybin() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class PSOencKeybinData {
        int BINHeaderDubExVer;
        int BINType;
        byte[] EncData;
        byte[] SignData;
        int Version;
        int result;

        private PSOencKeybinData() {
        }
    }

    static {
        System.loadLibrary("XpPso");
    }

    public XpPso(Context context) {
        this.a = context.getApplicationContext();
        Diagnosis.init(this.a);
    }

    private native byte[] DoXor(int i, byte[] bArr);

    private native byte[] GetUDSSecurityLuaEncData();

    private native E28decKeybin KeyBinProcess(String str);

    public static byte[] PemStringFormat2der(String str) throws IOException {
        int i;
        int i2;
        byte[] bytes = str.replace(" ", "").replace("\r", "").replace("\n", "").getBytes();
        int length = bytes.length / 2;
        while (true) {
            if (length < 0) {
                i = 0;
                break;
            } else if (bytes[length] == 45) {
                i = length + 1;
                break;
            } else {
                length--;
            }
        }
        int length2 = bytes.length / 2;
        while (true) {
            if (length2 >= bytes.length) {
                i2 = 0;
                break;
            } else if (bytes[length2] == 45) {
                i2 = length2 - 1;
                break;
            } else {
                length2++;
            }
        }
        int i3 = i2 > i ? (i2 - i) + 1 : 0;
        byte[] bArr = new byte[i3];
        System.arraycopy(bytes, i, bArr, 0, i3);
        return Base64Util.decode(bArr);
    }

    public static byte[] PemStringFormat2der(byte[] bArr) throws IOException {
        return PemStringFormat2der(new String(bArr));
    }

    private String a(String str, int i) throws XpPsoException {
        String exc;
        String str2 = str + String.valueOf(i) + "nnnnaks";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacMD5");
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            byte[] doFinal = mac.doFinal(str2.getBytes());
            String d = d("AKa", 0);
            for (int i2 = 0; i2 < 100; i2++) {
                if (!a(d) && i2 >= 99) {
                    genAliasRootKey();
                } else if (a(d)) {
                    break;
                }
                Thread.sleep(5L);
            }
            return Base64Util.encodeToString(a(d, doFinal, getAesSpecKeyVector(0)));
        } catch (IOException e) {
            exc = e.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (InvalidAlgorithmParameterException e2) {
            exc = e2.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (InvalidKeyException e3) {
            exc = e3.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (KeyStoreException e4) {
            exc = e4.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (NoSuchAlgorithmException e5) {
            exc = e5.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (UnrecoverableKeyException e6) {
            exc = e6.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (CertificateException e7) {
            exc = e7.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (NoSuchPaddingException e8) {
            exc = e8.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (ShortBufferException e9) {
            exc = e9.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (Exception e10) {
            exc = e10.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        }
    }

    private boolean a(String str, String str2, int i) throws XpPsoException {
        int i2;
        int i3;
        if (new File(str).exists()) {
            long d = d(str);
            OtaEncBinInfo otaEncBinInfo = getOtaEncBinInfo(str, d > 0 ? d : 0L);
            if (otaEncBinInfo == null || otaEncBinInfo.dstBinLen <= 0) {
                throw new XpPsoException(this.a, "Invalid File...\r\n(parse head failed...)", -1);
            }
            this.h[0] = d;
            if (otaEncBinInfo.BINType > 2) {
                i3 = i;
                i2 = 2;
            } else {
                i2 = otaEncBinInfo.BINType;
                i3 = i;
            }
            Cipher a = i3 == 2 ? a(i2, "CBC", "NoPadding") : a(i2);
            PublicKey g = g();
            if (g != null) {
                File file = new File(str);
                File file2 = new File(str2);
                if (!file.exists() || file.length() <= 0) {
                    throw new XpPsoException(this.a, "ota File is not exists...", -6);
                }
                if (file2.exists()) {
                    file2.delete();
                }
                try {
                    file2.createNewFile();
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        fileInputStream.skip(otaEncBinInfo.dstBinOffset);
                        this.h[0] = otaEncBinInfo.dstBinOffset + d;
                        a(otaEncBinInfo, otaEncBinInfo.dstBinLen, (int) (d + otaEncBinInfo.dstBinOffset), fileInputStream, fileOutputStream, a, a(g), this.h);
                        try {
                            fileInputStream.close();
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        a(str2, otaEncBinInfo.dstBinLen, a(g), otaEncBinInfo.SignData);
                        this.h[0] = file.length();
                        return true;
                    } catch (IOException e2) {
                        throw new XpPsoException(this.a, "Invalid File...\r\n(" + e2.toString() + ")", -1);
                    }
                } catch (IOException e3) {
                    throw new XpPsoException(this.a, str2 + "create NewFile Fail...\r\n(" + e3.toString() + ")", -43);
                }
            }
            throw new XpPsoException(this.a, "No PublicKey", -14);
        }
        throw new XpPsoException(this.a, "ota File is not exists...", -6);
    }

    private String b(int i) {
        switch (i) {
            case 0:
                return "xxx";
            case 1:
                return "ppp";
            case 2:
                return "ni";
            case 3:
                return "hao";
            default:
                return "";
        }
    }

    private String b(String str, int i) throws XpPsoException {
        String exc;
        byte[] doFinal;
        String d;
        String str2 = str + String.valueOf(i) + "nnnnaks";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacMD5");
        String str3 = null;
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            doFinal = mac.doFinal(str2.getBytes());
            d = d("AKa", 0);
        } catch (InvalidKeyException e) {
            e = e;
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
        } catch (Exception e3) {
            e = e3;
        }
        try {
            return Base64Util.encodeToString(a(d, doFinal, getAesSpecKeyVector(0)));
        } catch (InvalidKeyException e4) {
            e = e4;
            str3 = d;
            exc = e.toString();
            throw new XpPsoException(this.a, "get Alias failed ...(" + str3 + ")\r\n(" + exc + ")", -7);
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            str3 = d;
            exc = e.toString();
            throw new XpPsoException(this.a, "get Alias failed ...(" + str3 + ")\r\n(" + exc + ")", -7);
        } catch (Exception e6) {
            e = e6;
            str3 = d;
            exc = e.toString();
            throw new XpPsoException(this.a, "get Alias failed ...(" + str3 + ")\r\n(" + exc + ")", -7);
        }
    }

    private byte[] b(String str, String str2, int i) throws XpPsoException {
        File file = new File(str);
        File file2 = new File(str2);
        int e28OtaPackheadLen = getE28OtaPackheadLen();
        if (!file.exists() || file.length() <= 0) {
            throw new XpPsoException(this.a, "ota File is not exists...", -6);
        }
        if (file2.exists()) {
            file2.delete();
        }
        try {
            file2.createNewFile();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[e28OtaPackheadLen];
                fileInputStream.read(bArr);
                E28OtaPackhead e28OtaPackhead = getE28OtaPackhead(bArr, file.length());
                if (e28OtaPackhead != null) {
                    this.h[0] = e28OtaPackheadLen;
                    Cipher a = i == 2 ? a(e28OtaPackhead.key_id, "CTR", "NoPadding") : j(e28OtaPackhead.key_id);
                    PublicKey g = g(e28OtaPackhead.cert_id);
                    Signature b = b(g);
                    byte[] b2 = b(e28OtaPackhead.datalen, e28OtaPackheadLen, fileInputStream, fileOutputStream, a, b, e28OtaPackhead.datasha256, this.h);
                    try {
                        if (b.verify(e28OtaPackhead.sign)) {
                            try {
                                fileInputStream.close();
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            boolean a2 = a(str2, e28OtaPackhead.datalen, b(g), e28OtaPackhead.sign);
                            this.h[0] = file.length();
                            if (a2) {
                                return b2;
                            }
                            throw new XpPsoException(this.a, "ota package decryptverify to file failed...", -42);
                        }
                        throw new XpPsoException(this.a, "bin Signed Data Verify  Fail...", -4);
                    } catch (SignatureException e2) {
                        Context context = this.a;
                        throw new XpPsoException(context, "bin Signed Data Verify  Fail...\r\n(" + e2.toString() + ")", -4);
                    }
                }
                throw new XpPsoException(this.a, "Invalid File...\r\n(Packhead null)", -1);
            } catch (IOException e3) {
                Context context2 = this.a;
                throw new XpPsoException(context2, "Invalid File...\r\n(" + e3.toString() + ")", -1);
            }
        } catch (IOException e4) {
            Context context3 = this.a;
            throw new XpPsoException(context3, str2 + "create NewFile Fail...\r\n(" + e4.toString() + ")", -43);
        }
    }

    private String c(String str, int i) throws NoSuchAlgorithmException, InvalidKeyException, XpPsoException, UnrecoverableKeyException, InvalidAlgorithmParameterException, KeyStoreException, ShortBufferException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        String str2 = str + String.valueOf(i) + "nnnnaks";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKeySpec);
        return Base64Util.encodeToString(b(d("AKa", 0), mac.doFinal(str2.getBytes()), getAesSpecKeyVector(0)));
    }

    private boolean c(String str, String str2, int i) throws XpPsoException {
        File file = new File(str);
        File file2 = new File(str2);
        int e28OtaPackheadLen = getE28OtaPackheadLen();
        if (!file.exists() || file.length() <= 0) {
            throw new XpPsoException(this.a, "ota File is not exists...", -6);
        }
        if (file2.exists()) {
            file2.delete();
        }
        try {
            file2.createNewFile();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[e28OtaPackheadLen];
                fileInputStream.read(bArr);
                E28OtaPackhead e28OtaPackhead = getE28OtaPackhead(bArr, file.length());
                if (e28OtaPackhead != null) {
                    this.h[0] = e28OtaPackheadLen;
                    Cipher a = i == 2 ? a(e28OtaPackhead.key_id, "CTR", "NoPadding") : j(e28OtaPackhead.key_id);
                    PublicKey g = g(e28OtaPackhead.cert_id);
                    Signature b = b(g);
                    a(e28OtaPackhead.datalen, e28OtaPackheadLen, fileInputStream, fileOutputStream, a, b, e28OtaPackhead.datasha256, this.h);
                    try {
                        if (b.verify(e28OtaPackhead.sign)) {
                            try {
                                fileInputStream.close();
                                fileOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            boolean a2 = a(str2, e28OtaPackhead.datalen, b(g), e28OtaPackhead.sign);
                            this.h[0] = file.length();
                            if (a2) {
                                return true;
                            }
                            throw new XpPsoException(this.a, "ota package decryptverify to file failed...", -42);
                        }
                        throw new XpPsoException(this.a, "bin Signed Data Verify  Fail...", -4);
                    } catch (SignatureException e2) {
                        Context context = this.a;
                        throw new XpPsoException(context, "bin Signed Data Verify  Fail...\r\n(" + e2.toString() + ")", -4);
                    }
                }
                throw new XpPsoException(this.a, "Invalid File...\r\n(Packhead null)", -1);
            } catch (IOException e3) {
                Context context2 = this.a;
                throw new XpPsoException(context2, "Invalid File...\r\n(" + e3.toString() + ")", -1);
            }
        } catch (IOException e4) {
            Context context3 = this.a;
            throw new XpPsoException(context3, str2 + "create NewFile Fail...\r\n(" + e4.toString() + ")", -43);
        }
    }

    private byte[] c(String str) throws NoSuchAlgorithmException, InvalidKeyException {
        String str2 = str + "XpIccid0Xdafedb";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKeySpec);
        return mac.doFinal(str2.getBytes());
    }

    private long d(String str) {
        File file = new File(str);
        if (file.exists()) {
            int length = (int) file.length();
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[length];
                fileInputStream.read(bArr, 0, length);
                byte[] bArr2 = new byte[100];
                System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
                String str2 = new String(bArr2);
                long lastIndexOf = str2.contains("\r\n\r\n") ? str2.lastIndexOf("\r\n\r\n") + "\r\n\r\n".length() : 0L;
                if (lastIndexOf < 0) {
                    return 0L;
                }
                return lastIndexOf;
            } catch (FileNotFoundException | IOException e) {
                return 0L;
            }
        }
        return 0L;
    }

    private String d(String str, int i) throws NoSuchAlgorithmException, InvalidKeyException {
        String str2 = str + String.valueOf(i) + "rrrkkkksssss";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKeySpec);
        return Base64Util.encodeToString(mac.doFinal(str2.getBytes()));
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x017c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean e(java.lang.String r7) throws android.pso.XpPsoException {
        /*
            Method dump skipped, instructions count: 422
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.pso.XpPso.e(java.lang.String):boolean");
    }

    private byte[] e(String str, int i) throws XpPsoException {
        int i2;
        int i3;
        File file = new File(str);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[(int) file.length()];
                fileInputStream.read(bArr);
                long l = l(bArr);
                OtaEncBinInfo otaEncBinInfoFormMem = getOtaEncBinInfoFormMem(bArr, l);
                if (otaEncBinInfoFormMem.result == 0) {
                    byte[] bArr2 = new byte[(int) otaEncBinInfoFormMem.dstBinLen];
                    System.arraycopy(bArr, (int) (otaEncBinInfoFormMem.dstBinOffset + l), bArr2, 0, (int) otaEncBinInfoFormMem.dstBinLen);
                    if (otaEncBinInfoFormMem == null || otaEncBinInfoFormMem.dstBinLen <= 0) {
                        throw new XpPsoException(this.a, "Invalid File...\r\n(parse head failed...)", -1);
                    }
                    this.g[0] = l;
                    if (otaEncBinInfoFormMem.BINType > 2) {
                        i3 = i;
                        i2 = 2;
                    } else {
                        i2 = otaEncBinInfoFormMem.BINType;
                        i3 = i;
                    }
                    Cipher a = i3 == 2 ? a(i2, "CBC", "NoPadding") : a(i2);
                    PublicKey g = g();
                    File file2 = new File(str);
                    if (!file2.exists() || file2.length() <= 0) {
                        throw new XpPsoException(this.a, "ota File is not exists...", -6);
                    }
                    this.g[0] = l + otaEncBinInfoFormMem.dstBinOffset;
                    byte[] a2 = a(otaEncBinInfoFormMem, (int) otaEncBinInfoFormMem.dstBinLen, (int) this.g[0], bArr2, a, a(g), this.g);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    a(a2, (int) otaEncBinInfoFormMem.dstBinLen, a(g), otaEncBinInfoFormMem.SignData);
                    this.g[0] = file2.length();
                    if (a2 != null) {
                        return a2;
                    }
                    throw new XpPsoException(this.a, "ota package decryptverify to mem failed...", -42);
                }
                throw new XpPsoException(this.a, "Invalid File...", -1);
            } catch (IOException e2) {
                throw new XpPsoException(this.a, "Invalid File...\r\n(" + e2.toString() + ")", -1);
            }
        }
        throw new XpPsoException(this.a, "ota File is not exists...", -6);
    }

    private void f() throws XpPsoException {
        String exc;
        boolean z = false;
        try {
            exc = null;
            z = a(d("AKa", 0));
        } catch (InvalidKeyException e) {
            exc = e.toString();
        } catch (NoSuchAlgorithmException e2) {
            exc = e2.toString();
        } catch (Exception e3) {
            exc = e3.toString();
        }
        if (z) {
            return;
        }
        throw new XpPsoException(this.a, "No Root Key...\r\n" + exc, -98);
    }

    private boolean f(String str) throws XpPsoException {
        if (new File(str).exists()) {
            E28decKeybin KeyBinProcess = KeyBinProcess(str);
            ArrayList<E28decKeybin.KeyInfo> arrayList = KeyBinProcess.CommKey;
            if (arrayList != null) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (a(c(arrayList.get(i).ID), arrayList.get(i).KeyValue, "CommKey " + String.valueOf(i))) {
                        Log.i("PSO", "S:CommKey " + String.valueOf(i) + " succeed!");
                    }
                    if (stop) {
                        return false;
                    }
                }
            }
            return KeyBinProcess.result == 0;
        }
        throw new XpPsoException(this.a, "keybinFile not exists", -6);
    }

    private byte[] f(String str, int i) throws XpPsoException {
        File file = new File(str);
        int e28OtaPackheadLen = getE28OtaPackheadLen();
        if (!file.exists() || file.length() <= 0) {
            throw new XpPsoException(this.a, "ota File is not exists...", -6);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[e28OtaPackheadLen];
            fileInputStream.read(bArr);
            E28OtaPackhead e28OtaPackhead = getE28OtaPackhead(bArr, file.length());
            if (e28OtaPackhead != null) {
                this.g[0] = e28OtaPackheadLen;
                byte[] bArr2 = new byte[(int) e28OtaPackhead.datalen];
                byte[] bArr3 = new byte[(int) e28OtaPackhead.datalen];
                try {
                    fileInputStream.read(bArr2);
                    Cipher a = i == 2 ? a(e28OtaPackhead.key_id, "CTR", "NoPadding") : j(e28OtaPackhead.key_id);
                    PublicKey g = g(e28OtaPackhead.cert_id);
                    Signature b = b(g);
                    a((int) e28OtaPackhead.datalen, e28OtaPackheadLen, bArr2, bArr3, a, b, e28OtaPackhead.datasha256, this.g);
                    try {
                        b.verify(e28OtaPackhead.sign);
                        if (a(bArr3, e28OtaPackhead.datalen, b(g), e28OtaPackhead.sign)) {
                            this.g[0] = file.length();
                            try {
                                fileInputStream.close();
                                return bArr3;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return bArr3;
                            }
                        }
                        throw new XpPsoException(this.a, "ota package verify failed...", -4);
                    } catch (SignatureException e2) {
                        Context context = this.a;
                        throw new XpPsoException(context, "bin Signed Data Verify  Fail...\r\n(" + e2.toString() + ")", -4);
                    }
                } catch (IOException e3) {
                    Context context2 = this.a;
                    throw new XpPsoException(context2, "Read File Failed\r\n(" + e3.toString() + ")", -22);
                }
            }
            throw new XpPsoException(this.a, "Invalid File...\r\n(Packhead==null)", -1);
        } catch (IOException e4) {
            Context context3 = this.a;
            throw new XpPsoException(context3, "Invalid File...\r\n(" + e4.toString() + ")", -1);
        }
    }

    private PublicKey g() throws XpPsoException {
        String exc;
        byte[] d21OtaEcuCert = getD21OtaEcuCert();
        if (d21OtaEcuCert != null) {
            try {
                return b(d21OtaEcuCert).getPublicKey();
            } catch (CertificateException e) {
                exc = e.toString();
                Context context = this.a;
                throw new XpPsoException(context, "No Cert...\r\n(" + exc + ")", -2);
            } catch (Exception e2) {
                exc = e2.toString();
                Context context2 = this.a;
                throw new XpPsoException(context2, "No Cert...\r\n(" + exc + ")", -2);
            }
        }
        throw new XpPsoException(this.a, "No Cert...\r\n(get D21 Ota Ecu Cert)", -2);
    }

    private native byte[] getAesSpecKeyVector(int i);

    private native int getCheckSum(byte[] bArr);

    private native byte[] getD21AesSpecKey(int i);

    private native byte[] getD21OtaEcuCert();

    private native byte[] getE28Cert(String str);

    private native E28OtaPackhead getE28OtaPackhead(byte[] bArr, long j);

    private native int getE28OtaPackheadLen();

    private native byte[] getEncAesSpecKey(int i);

    private native PSOencKeybinData getKeybinEncData(byte[] bArr);

    private native PSOdecKeybin getKeybinList(byte[] bArr, int i);

    private native byte[] getKeybinVerifyKey(int i);

    private native byte[] getMcuRtKey();

    private native OtaEncBinInfo getOtaEncBinInfo(String str, long j);

    private native OtaEncBinInfo getOtaEncBinInfoFormMem(byte[] bArr, long j);

    private long l(byte[] bArr) {
        byte[] bArr2 = new byte[100];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        String str = new String(bArr2);
        long lastIndexOf = str.contains("\r\n\r\n") ? str.lastIndexOf("\r\n\r\n") + "\r\n\r\n".length() : 0L;
        if (lastIndexOf < 0) {
            return 0L;
        }
        return lastIndexOf;
    }

    @Override // android.pso.PsoCrypto
    public boolean CertKeyPreset(String str) throws XpPsoException {
        b();
        return (c == 28 ? f(str) : e(str)) & e();
    }

    @Override // android.pso.PsoCrypto
    public boolean CertKeyVerify() throws XpPsoException {
        b();
        f();
        if (c == 21) {
            return D21CertKeyVerify();
        }
        throw new XpPsoException(this.a, "Can not Verify Cert Key ,Please user the input filename Api ...", PsoCrypto.Desc.CertKeyVerifyFailed);
    }

    @Override // android.pso.PsoCrypto
    public boolean CertKeyVerify(String str) throws XpPsoException {
        b();
        f();
        return (c == 21 ? D21CertKeyVerify() : E28CertKeyVerify(str)) & c();
    }

    public boolean D21CertKeyVerify() throws XpPsoException {
        for (int i = 0; i < 64; i++) {
            if (a(b("AK", i), "AK " + String.valueOf(i))) {
                Log.i("PSO", "T:AK " + String.valueOf(i) + " succeed!");
            }
            if (stop) {
                return false;
            }
        }
        for (int i2 = 0; i2 < 8; i2++) {
            if (a(b("AKe", i2), "AKe " + String.valueOf(i2))) {
                Log.i("PSO", "T:AKe " + String.valueOf(i2) + " succeed!");
            }
            if (stop) {
                return false;
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            if (i3 != 1) {
                byte[] encAesSpecKey = getEncAesSpecKey(i3);
                if (c(b("AKspec", i3), getAesSpecKeyVector(i3), "AKs " + String.valueOf(i3)) && encAesSpecKey != null) {
                    Log.i("PSO", "T:AKs " + String.valueOf(i3) + " succeed!");
                }
                if (stop) {
                    return false;
                }
            }
        }
        return !stop;
    }

    public boolean D21checkIfPreseted() throws XpPsoException {
        for (int i = 0; i < 4; i++) {
            if (i != 1) {
                byte[] encAesSpecKey = getEncAesSpecKey(i);
                if (!a(b("AKspec", i)) || encAesSpecKey == null) {
                    Log.e("PSO", "T:AKs " + String.valueOf(i) + " not Preseted!");
                    return false;
                }
                Log.i("PSO", "T:AKs " + String.valueOf(i) + " Preseted!");
                if (stop) {
                    return false;
                }
            }
        }
        return !stop;
    }

    public boolean E28CertKeyVerify(String str) throws XpPsoException {
        if (new File(str).exists()) {
            E28decKeybin KeyBinProcess = KeyBinProcess(str);
            ArrayList<E28decKeybin.CertInfo> arrayList = KeyBinProcess.CommSignCert;
            if (arrayList != null) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (a(arrayList.get(i).PriPem, arrayList.get(i).ID, "CommSignCert " + String.valueOf(i))) {
                        Log.i("PSO", "T:CommSignCert " + String.valueOf(i) + " succeed!");
                    }
                    if (stop) {
                        return false;
                    }
                }
            }
            ArrayList<E28decKeybin.KeyInfo> arrayList2 = KeyBinProcess.CommKey;
            if (arrayList2 != null) {
                for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                    byte[] e28enccKey = getE28enccKey(arrayList2.get(i2).ID);
                    if (b(d(arrayList2.get(i2).ID), "CommKey " + String.valueOf(i2)) && e28enccKey != null) {
                        Log.i("PSO", "T:CommKey " + String.valueOf(i2) + " succeed!");
                    }
                    if (stop) {
                        return false;
                    }
                }
            }
            return KeyBinProcess.result == 0;
        }
        throw new XpPsoException(this.a, "keybinFile not exists", -6);
    }

    public boolean E28checkIfPreseted() throws XpPsoException {
        String str = null;
        int i = 127;
        while (true) {
            if (i >= 128) {
                break;
            }
            try {
            } catch (InvalidAlgorithmParameterException e) {
                str = e.toString();
            } catch (InvalidKeyException e2) {
                str = e2.toString();
            } catch (KeyStoreException e3) {
                str = e3.toString();
            } catch (NoSuchAlgorithmException e4) {
                str = e4.toString();
            } catch (UnrecoverableKeyException e5) {
                str = e5.toString();
            } catch (BadPaddingException e6) {
                str = e6.toString();
            } catch (IllegalBlockSizeException e7) {
                str = e7.toString();
            } catch (NoSuchPaddingException e8) {
                str = e8.toString();
            } catch (ShortBufferException e9) {
                e9.printStackTrace();
            } catch (Exception e10) {
                str = e10.toString();
            }
            if (!a(e(getE28enccKeyId(i)))) {
                str = "E28checkIfPreseted get key failed";
                break;
            }
            Log.i("PSO", "T:CommKey " + String.valueOf(i) + " preseted!");
            i++;
        }
        if (str == null) {
            return true;
        }
        throw new XpPsoException(this.a, "E28checkIfPreseted  failed", -14);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0098 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0099  */
    @Override // android.pso.PsoCrypto
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String GetPSUID(java.lang.String r9) throws android.pso.XpPsoException {
        /*
            r8 = this;
            java.lang.String r0 = "HmacMD5"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            java.lang.String r2 = "A234567890123456"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = 0
            javax.crypto.spec.SecretKeySpec r3 = new javax.crypto.spec.SecretKeySpec     // Catch: java.lang.Exception -> L82 java.security.InvalidKeyException -> L89 java.security.NoSuchAlgorithmException -> L90
            byte[] r1 = r1.getBytes()     // Catch: java.lang.Exception -> L82 java.security.InvalidKeyException -> L89 java.security.NoSuchAlgorithmException -> L90
            r3.<init>(r1, r0)     // Catch: java.lang.Exception -> L82 java.security.InvalidKeyException -> L89 java.security.NoSuchAlgorithmException -> L90
            javax.crypto.Mac r0 = javax.crypto.Mac.getInstance(r0)     // Catch: java.lang.Exception -> L82 java.security.InvalidKeyException -> L89 java.security.NoSuchAlgorithmException -> L90
            r0.init(r3)     // Catch: java.lang.Exception -> L82 java.security.InvalidKeyException -> L89 java.security.NoSuchAlgorithmException -> L90
            byte[] r9 = r9.getBytes()     // Catch: java.lang.Exception -> L82 java.security.InvalidKeyException -> L89 java.security.NoSuchAlgorithmException -> L90
            byte[] r9 = r0.doFinal(r9)     // Catch: java.lang.Exception -> L82 java.security.InvalidKeyException -> L89 java.security.NoSuchAlgorithmException -> L90
            java.lang.String r0 = ""
            r1 = 0
            r3 = r0
            r0 = r1
        L31:
            int r4 = r9.length     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            if (r0 >= r4) goto L58
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            r4.<init>()     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            r4.append(r3)     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            java.lang.String r5 = "%02X"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            r7 = r9[r0]     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            r6[r1] = r7     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            r4.append(r5)     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            int r0 = r0 + 1
            r3 = r4
            goto L31
        L58:
            int r9 = r3.length()     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            r0 = 40
            if (r9 <= r0) goto L6a
            int r9 = r3.length()     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            int r9 = r9 % r0
            java.lang.String r9 = r3.substring(r9)     // Catch: java.lang.Exception -> L7c java.security.InvalidKeyException -> L7e java.security.NoSuchAlgorithmException -> L80
            goto L6b
        L6a:
            r9 = r3
        L6b:
            r9.length()     // Catch: java.lang.Exception -> L70 java.security.InvalidKeyException -> L74 java.security.NoSuchAlgorithmException -> L78
            r3 = r9
            goto L96
        L70:
            r0 = move-exception
            r3 = r9
            r9 = r0
            goto L84
        L74:
            r0 = move-exception
            r3 = r9
            r9 = r0
            goto L8b
        L78:
            r0 = move-exception
            r3 = r9
            r9 = r0
            goto L92
        L7c:
            r9 = move-exception
            goto L84
        L7e:
            r9 = move-exception
            goto L8b
        L80:
            r9 = move-exception
            goto L92
        L82:
            r9 = move-exception
            r3 = r2
        L84:
            java.lang.String r2 = r9.toString()
            goto L96
        L89:
            r9 = move-exception
            r3 = r2
        L8b:
            java.lang.String r2 = r9.toString()
            goto L96
        L90:
            r9 = move-exception
            r3 = r2
        L92:
            java.lang.String r2 = r9.toString()
        L96:
            if (r3 == 0) goto L99
            return r3
        L99:
            android.pso.XpPsoException r9 = new android.pso.XpPsoException
            android.content.Context r0 = r8.a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "Get PSUID Failed...\r\n("
            r1.append(r3)
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r2 = -71
            r9.<init>(r0, r1, r2)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: android.pso.XpPso.GetPSUID(java.lang.String):java.lang.String");
    }

    @Override // android.pso.PsoCrypto
    public boolean McuKeyPreset(String str) throws XpPsoException {
        b();
        f();
        return a(b("AKm", 1), getMcuRtKey());
    }

    protected Cipher a(int i) throws XpPsoException {
        String exc;
        f();
        try {
            return b(c("AKspec", i), getAesSpecKeyVector(i));
        } catch (InvalidAlgorithmParameterException e) {
            exc = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (InvalidKeyException e2) {
            exc = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (KeyStoreException e3) {
            exc = e3.toString();
            Context context22 = this.a;
            throw new XpPsoException(context22, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (NoSuchAlgorithmException e4) {
            exc = e4.toString();
            Context context222 = this.a;
            throw new XpPsoException(context222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (UnrecoverableKeyException e5) {
            exc = e5.toString();
            Context context2222 = this.a;
            throw new XpPsoException(context2222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (NoSuchPaddingException e6) {
            exc = e6.toString();
            Context context22222 = this.a;
            throw new XpPsoException(context22222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (ShortBufferException e7) {
            exc = e7.toString();
            Context context222222 = this.a;
            throw new XpPsoException(context222222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (Exception e8) {
            exc = e8.toString();
            Context context2222222 = this.a;
            throw new XpPsoException(context2222222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected javax.crypto.Cipher a(int r5, java.lang.String r6, java.lang.String r7) throws android.pso.XpPsoException {
        /*
            Method dump skipped, instructions count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.pso.XpPso.a(int, java.lang.String, java.lang.String):javax.crypto.Cipher");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00b6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected javax.crypto.Cipher a(byte[] r8, java.lang.String r9, java.lang.String r10) throws android.pso.XpPsoException {
        /*
            Method dump skipped, instructions count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.pso.XpPso.a(byte[], java.lang.String, java.lang.String):javax.crypto.Cipher");
    }

    protected boolean a(long j, int i, InputStream inputStream, OutputStream outputStream, Cipher cipher, Signature signature, byte[] bArr, long[] jArr) throws XpPsoException {
        OutputStream outputStream2;
        Cipher cipher2;
        int i2;
        int read;
        int i3;
        int i4;
        int update;
        int i5 = 10240 > j ? (int) j : PsoAKS.DecBlockSize;
        long j2 = i5;
        long j3 = (j / j2) + (j % j2 == 0 ? 0 : 1);
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] bArr2 = new byte[i5];
        byte[] bArr3 = new byte[i5];
        byte[] bArr4 = new byte[i5 * 3];
        if (j3 > 1) {
            int i6 = 0;
            int i7 = 0;
            boolean z = true;
            while (i6 < j3) {
                if (!this.i) {
                    int i8 = i7 + i5;
                    int i9 = i6;
                    if (i8 > j) {
                        break;
                    }
                    boolean z2 = !z;
                    if (z2) {
                        try {
                            read = inputStream.read(bArr2);
                        } catch (IOException e2) {
                            throw new XpPsoException(this.a, "Read File Failed\r\n(" + e2.toString() + ")", -22);
                        }
                    } else {
                        read = inputStream.read(bArr3);
                    }
                    if (read != i5) {
                        throw new XpPsoException(this.a, "Read File Failed\r\n(read len != blocksSize)", -22);
                    }
                    if (z2) {
                        try {
                            i3 = i8;
                            i4 = 0;
                            update = cipher.update(bArr2, 0, bArr2.length, bArr4);
                        } catch (Exception e3) {
                            throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e3.toString() + ")", -3);
                        }
                    } else {
                        i3 = i8;
                        i4 = 0;
                        update = cipher.update(bArr3, 0, bArr3.length, bArr4);
                    }
                    if (update <= 0) {
                        throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(out=null)", -3);
                    }
                    try {
                        messageDigest.update(bArr4, i4, update);
                        signature.update(bArr4, i4, update);
                        int i10 = i5;
                        try {
                            outputStream.write(bArr4, i4, update);
                            outputStream.flush();
                            if (bArr4.length > 0) {
                                i7 = i3;
                            }
                            jArr[0] = i + i7;
                            i6 = i9 + 1;
                            z = z2;
                            i5 = i10;
                        } catch (IOException e4) {
                            throw new XpPsoException(this.a, "write File Failed...\r\n(" + e4.toString() + ")", -44);
                        }
                    } catch (Exception e5) {
                        throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e5.toString() + ")", -4);
                    }
                } else {
                    throw new XpPsoException(this.a, "Decrypt Exit...", -33);
                }
            }
            outputStream2 = outputStream;
            cipher2 = cipher;
            i2 = i7;
        } else {
            outputStream2 = outputStream;
            cipher2 = cipher;
            i2 = 0;
        }
        int i11 = (int) (j - i2);
        if (i11 > 0) {
            byte[] bArr5 = new byte[i11];
            try {
                if (inputStream.read(bArr5) != i11) {
                    throw new XpPsoException(this.a, "Read File Failed\r\n(read len != lastLen)", -22);
                }
                try {
                    byte[] doFinal = cipher2.doFinal(bArr5);
                    if (doFinal != null) {
                        try {
                            messageDigest.update(doFinal);
                            signature.update(doFinal);
                        } catch (Exception e6) {
                            throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e6.toString() + ")", -4);
                        }
                    }
                    try {
                        outputStream2.write(doFinal);
                        outputStream.flush();
                    } catch (Exception e7) {
                        throw new XpPsoException(this.a, "write File Failed...\r\n(" + e7.toString() + ")", -44);
                    }
                } catch (BadPaddingException e8) {
                    throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e8.toString() + ")", -3);
                } catch (IllegalBlockSizeException e9) {
                    throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e9.toString() + ")", -3);
                }
            } catch (IOException e10) {
                throw new XpPsoException(this.a, "Read File Failed\r\n(" + e10.toString() + ")", -22);
            }
        }
        try {
            String i12 = i(messageDigest.digest());
            String i13 = i(bArr);
            Log.i("PSO", "sha256sum correct?=" + i13.contains(i12) + ",sha256result=" + i12);
            return true;
        } catch (Exception e11) {
            throw new XpPsoException(this.a, "sha256sum check Failed...\r\n(" + e11.toString() + ")", -4);
        }
    }

    protected boolean a(OtaEncBinInfo otaEncBinInfo, long j, int i, InputStream inputStream, OutputStream outputStream, Cipher cipher, Signature signature, long[] jArr) throws XpPsoException {
        OutputStream outputStream2;
        Cipher cipher2;
        int i2;
        long j2;
        int i3 = 10240 > j ? (int) j : PsoAKS.DecBlockSize;
        long j3 = i3;
        long j4 = (j / j3) + (j % j3 == 0 ? 0 : 1);
        byte[] bArr = new byte[i3];
        if (j4 > 1) {
            int i4 = 0;
            i2 = 0;
            j2 = 0;
            while (i4 < j4) {
                long j5 = j2 + j3;
                if (j5 > j) {
                    break;
                }
                try {
                    if (inputStream.read(bArr) != i3) {
                        throw new XpPsoException(this.a, "Read File Failed\r\n(readLen error)", -22);
                    }
                    int i5 = i3;
                    try {
                        byte[] update = cipher.update(DoXor(7, bArr));
                        if (update == null) {
                            throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(DecData=null)", -3);
                        }
                        i2 += getCheckSum(update);
                        try {
                            signature.update(update);
                            long j6 = j3;
                            try {
                                outputStream.write(update);
                                outputStream.flush();
                                this.h[0] = i + j5;
                                i4++;
                                j2 = j5;
                                i3 = i5;
                                j3 = j6;
                                j4 = j4;
                            } catch (IOException e) {
                                throw new XpPsoException(this.a, "write File Failed...\r\n(" + e.toString() + ")", -44);
                            }
                        } catch (SignatureException e2) {
                            throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e2.toString() + ")", -4);
                        }
                    } catch (Exception e3) {
                        throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e3.toString() + ")", -3);
                    }
                } catch (IOException e4) {
                    throw new XpPsoException(this.a, "Read File Failed\r\n(" + e4.toString() + ")", -22);
                }
            }
            outputStream2 = outputStream;
            cipher2 = cipher;
        } else {
            outputStream2 = outputStream;
            cipher2 = cipher;
            i2 = 0;
            j2 = 0;
        }
        int i6 = (int) (j - j2);
        if (i6 > 0) {
            byte[] bArr2 = new byte[i6];
            byte[] bArr3 = new byte[i6];
            try {
                if (inputStream.read(bArr3) != i6) {
                    throw new XpPsoException(this.a, "Read File Failed\r\n(len!=lastLen)", -22);
                }
                try {
                    byte[] doFinal = cipher2.doFinal(DoXor(7, bArr3));
                    if (doFinal == null) {
                        throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(DecData==null)", -3);
                    }
                    i2 += getCheckSum(doFinal);
                    try {
                        signature.update(doFinal);
                        try {
                            outputStream2.write(doFinal);
                            outputStream.flush();
                        } catch (IOException e5) {
                            throw new XpPsoException(this.a, "write File Failed...\r\n(" + e5.toString() + ")", -44);
                        }
                    } catch (SignatureException e6) {
                        throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e6.toString() + ")", -4);
                    }
                } catch (Exception e7) {
                    throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e7.toString() + ")", -3);
                }
            } catch (IOException e8) {
                throw new XpPsoException(this.a, "Read File Failed\r\n(" + e8.toString() + ")", -22);
            }
        }
        int i7 = i2;
        try {
            if (signature.verify(otaEncBinInfo.SignData)) {
                if (i7 == otaEncBinInfo.CheckSum) {
                    return true;
                }
                throw new XpPsoException(this.a, "ota package CheckSum Error...", -5);
            }
            throw new XpPsoException(this.a, "bin Signed Data Verify  Fail...", -4);
        } catch (SignatureException e9) {
            throw new XpPsoException(this.a, "bin Signed Data Verify  Fail...\r\n(" + e9.toString() + ")", -4);
        }
    }

    protected boolean a(String str, long j, Signature signature, byte[] bArr) throws XpPsoException {
        long j2;
        File file = new File(str);
        if (file.exists()) {
            if (file.length() == j) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    int i = 10240 > j ? (int) j : PsoAKS.DecBlockSize;
                    long j3 = i;
                    long j4 = (j / j3) + (j % j3 == 0 ? 0 : 1);
                    byte[] bArr2 = new byte[i];
                    if (j4 > 1) {
                        int i2 = 0;
                        j2 = 0;
                        while (i2 < j4) {
                            if (!this.i) {
                                long j5 = j2 + j3;
                                if (j5 > j) {
                                    break;
                                }
                                try {
                                    fileInputStream.read(bArr2);
                                    try {
                                        signature.update(bArr2);
                                        i2++;
                                        j2 = j5;
                                    } catch (SignatureException e) {
                                        throw new XpPsoException(this.a, "ota verify dec file data signature update Fail...\r\n(" + e.toString() + ")", -4);
                                    }
                                } catch (IOException e2) {
                                    throw new XpPsoException(this.a, "Read File Failed\r\n(" + e2.toString() + ")", -22);
                                }
                            } else {
                                throw new XpPsoException(this.a, "Decrypt Exit...", -33);
                            }
                        }
                    } else {
                        j2 = 0;
                    }
                    int i3 = (int) (j - j2);
                    if (i3 > 0) {
                        byte[] bArr3 = new byte[i3];
                        try {
                            fileInputStream.read(bArr3);
                            try {
                                signature.update(bArr3);
                            } catch (SignatureException e3) {
                                throw new XpPsoException(this.a, "ota verify dec file data signature update Fail...\r\n(" + e3.toString() + ")", -4);
                            }
                        } catch (IOException e4) {
                            throw new XpPsoException(this.a, "Read File Failed\r\n(" + e4.toString() + ")", -22);
                        }
                    }
                    try {
                        if (signature.verify(bArr)) {
                            return true;
                        }
                        throw new XpPsoException(this.a, "ota verify dec file failed...", -42);
                    } catch (SignatureException e5) {
                        throw new XpPsoException(this.a, "ota verify dec file failed...\r\n(" + e5.toString() + ")", -4);
                    }
                } catch (IOException e6) {
                    throw new XpPsoException(this.a, "create FileInputStream failed...\r\n(" + e6.toString() + ")", -1);
                }
            }
            throw new XpPsoException(this.a, "file length is not correct", -1);
        }
        throw new XpPsoException(this.a, "ota dec file Not Exists...", -6);
    }

    protected boolean a(byte[] bArr, long j, Signature signature, byte[] bArr2) throws XpPsoException {
        if (bArr.length == j) {
            try {
                signature.update(bArr);
                try {
                    if (signature.verify(bArr2)) {
                        return true;
                    }
                    throw new XpPsoException(this.a, "ota verify Dec Data failed...", -42);
                } catch (SignatureException e) {
                    Context context = this.a;
                    throw new XpPsoException(context, "ota verify Dec Data failed...\r\n(" + e.toString() + ")", -4);
                }
            } catch (SignatureException e2) {
                Context context2 = this.a;
                throw new XpPsoException(context2, "ota verify Dec Data signature update Fail...\r\n(" + e2.toString() + ")", -4);
            }
        }
        throw new XpPsoException(this.a, "Dec Data length is not correct", -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean a(byte[] r5, byte[] r6, java.lang.String r7) throws android.pso.XpPsoException {
        /*
            r4 = this;
            java.lang.String r0 = new java.lang.String
            r0.<init>(r5)
            r5 = 0
            byte[] r0 = PemStringFormat2der(r0)     // Catch: java.security.spec.InvalidKeySpecException -> L10 java.security.NoSuchAlgorithmException -> L16 java.io.IOException -> L1c
            java.security.PrivateKey r0 = r4.a(r0)     // Catch: java.security.spec.InvalidKeySpecException -> L10 java.security.NoSuchAlgorithmException -> L16 java.io.IOException -> L1c
            r1 = r5
            goto L23
        L10:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            goto L21
        L16:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
            goto L21
        L1c:
            r0 = move-exception
            java.lang.String r0 = r0.toString()
        L21:
            r1 = r0
            r0 = r5
        L23:
            if (r0 == 0) goto Lac
            java.lang.String r2 = new java.lang.String
            r2.<init>(r6)
            byte[] r2 = r4.getE28Cert(r2)
            if (r2 == 0) goto La1
            byte[] r2 = PemStringFormat2der(r2)     // Catch: java.io.IOException -> L3e java.security.cert.CertificateException -> L44
            java.security.cert.Certificate r2 = r4.b(r2)     // Catch: java.io.IOException -> L3e java.security.cert.CertificateException -> L44
            java.security.PublicKey r2 = r2.getPublicKey()     // Catch: java.io.IOException -> L3e java.security.cert.CertificateException -> L44
            r5 = r2
            goto L49
        L3e:
            r1 = move-exception
            java.lang.String r1 = r1.toString()
            goto L49
        L44:
            r1 = move-exception
            java.lang.String r1 = r1.toString()
        L49:
            if (r5 == 0) goto L95
            java.lang.String r2 = "tttttttPlanText"
            java.lang.String r0 = r4.a(r2, r0)     // Catch: java.lang.Exception -> L56
            boolean r5 = r4.a(r2, r0, r5)     // Catch: java.lang.Exception -> L56
            goto L5b
        L56:
            r5 = move-exception
            r5.printStackTrace()
            r5 = 0
        L5b:
            if (r5 == 0) goto L60
            if (r1 != 0) goto L60
            return r5
        L60:
            android.pso.XpPsoException r5 = new android.pso.XpPsoException
            android.content.Context r0 = r4.a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "RSA cert Key Test "
            r2.append(r3)
            r2.append(r7)
            java.lang.String r7 = ":"
            r2.append(r7)
            java.lang.String r7 = new java.lang.String
            r7.<init>(r6)
            r2.append(r7)
            java.lang.String r6 = " Failed\r\n("
            r2.append(r6)
            r2.append(r1)
            java.lang.String r6 = ")"
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            r7 = -122(0xffffffffffffff86, float:NaN)
            r5.<init>(r0, r6, r7)
            throw r5
        L95:
            android.pso.XpPsoException r5 = new android.pso.XpPsoException
            android.content.Context r6 = r4.a
            r7 = -14
            java.lang.String r0 = "No Public Key"
            r5.<init>(r6, r0, r7)
            throw r5
        La1:
            android.pso.XpPsoException r5 = new android.pso.XpPsoException
            android.content.Context r6 = r4.a
            r7 = -2
            java.lang.String r0 = "No Cert Bytes"
            r5.<init>(r6, r0, r7)
            throw r5
        Lac:
            android.pso.XpPsoException r5 = new android.pso.XpPsoException
            android.content.Context r6 = r4.a
            r7 = -23
            java.lang.String r0 = "No PrivateKey"
            r5.<init>(r6, r0, r7)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.pso.XpPso.a(byte[], byte[], java.lang.String):boolean");
    }

    protected byte[] a(int i, int i2, byte[] bArr, byte[] bArr2, Cipher cipher, Signature signature, byte[] bArr3, long[] jArr) throws XpPsoException {
        int i3;
        int i4;
        int i5;
        int i6 = 10240 > i ? i : 10240;
        int i7 = (i / i6) + (i % i6 == 0 ? 0 : 1);
        if (i7 > 1) {
            int i8 = 0;
            int i9 = 0;
            for (int i10 = 0; i10 < i7; i10++) {
                if (this.i) {
                    throw new XpPsoException(this.a, "Decrypt Exit...", -33);
                }
                int i11 = i9 + i6;
                if (i11 > i) {
                    break;
                }
                int i12 = i8;
                try {
                    int update = cipher.update(bArr, i9, i6, bArr2, i8);
                    if (update > 0) {
                        i5 = i12;
                        try {
                            signature.update(bArr2, i5, update);
                        } catch (SignatureException e) {
                            throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e.toString() + ")", -4);
                        }
                    } else {
                        i5 = i12;
                    }
                    if (update > 0) {
                        i8 = i5 + update;
                        jArr[0] = i2 + i11;
                        i9 = i11;
                    } else {
                        i8 = i5;
                    }
                } catch (ShortBufferException e2) {
                    throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e2.toString() + ")", -3);
                }
            }
            i4 = i8;
            i3 = i9;
        } else {
            i3 = 0;
            i4 = 0;
        }
        int i13 = i - i3;
        if (i13 > 0) {
            try {
                byte[] doFinal = cipher.doFinal(bArr, i3, i13);
                if (doFinal != null) {
                    System.arraycopy(doFinal, 0, bArr2, i4, doFinal.length);
                }
                if (doFinal != null) {
                    try {
                        signature.update(doFinal);
                    } catch (SignatureException e3) {
                        throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e3.toString() + ")", -4);
                    }
                }
            } catch (BadPaddingException e4) {
                throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e4.toString() + ")", -3);
            } catch (IllegalBlockSizeException e5) {
                throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e5.toString() + ")", -3);
            }
        }
        try {
            String i14 = i(h(bArr2));
            String i15 = i(bArr3);
            Log.i("PSO", "sha256sum correct?=" + i15.contains(i14) + ",sha256result=" + i14);
            return bArr2;
        } catch (Exception e6) {
            throw new XpPsoException(this.a, "sha256sum check Failed...\r\n(" + e6.toString() + ")", -4);
        }
    }

    protected byte[] a(OtaEncBinInfo otaEncBinInfo, int i, int i2, byte[] bArr, Cipher cipher, Signature signature, long[] jArr) throws XpPsoException {
        int i3;
        int i4;
        int i5;
        byte[] bArr2 = new byte[i];
        int i6 = PsoAKS.DecBlockSize;
        if (10240 > i) {
            i6 = i;
        }
        int i7 = (i / i6) + (i % i6 == 0 ? 0 : 1);
        byte[] bArr3 = new byte[i6];
        int i8 = 7;
        if (i7 > 1) {
            int i9 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            while (i9 < i7) {
                int i10 = i4 + i6;
                if (i10 > i) {
                    break;
                }
                int i11 = i6;
                System.arraycopy(bArr, i4, bArr3, 0, bArr3.length);
                try {
                    byte[] update = cipher.update(DoXor(i8, bArr3));
                    if (update == null) {
                        throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(out null)", -3);
                    }
                    System.arraycopy(update, 0, bArr2, i3, update.length);
                    byte[] bArr4 = new byte[update.length];
                    System.arraycopy(update, 0, bArr4, 0, bArr4.length);
                    i5 += getCheckSum(bArr4);
                    try {
                        signature.update(update);
                        int i12 = i7;
                        byte[] bArr5 = bArr3;
                        jArr[0] = i2 + i10;
                        if (update != null) {
                            i3 += update.length;
                        }
                        i9++;
                        i4 = i10;
                        i6 = i11;
                        i7 = i12;
                        bArr3 = bArr5;
                        i8 = 7;
                    } catch (SignatureException e) {
                        throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e.toString() + ")", -4);
                    }
                } catch (Exception e2) {
                    throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e2.toString() + ")", -3);
                }
            }
        } else {
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        int i13 = i - i4;
        if (i13 > 0) {
            byte[] bArr6 = new byte[i13];
            System.arraycopy(bArr, i4, bArr6, 0, bArr6.length);
            try {
                byte[] doFinal = cipher.doFinal(DoXor(7, bArr6));
                if (doFinal == null) {
                    throw new XpPsoException(this.a, "Decrypt doFinal Fail...\r\n(out null)", -3);
                }
                System.arraycopy(doFinal, 0, bArr2, i3, doFinal.length);
                byte[] bArr7 = new byte[doFinal.length];
                System.arraycopy(doFinal, 0, bArr7, 0, bArr7.length);
                i5 += getCheckSum(bArr7);
                try {
                    signature.update(doFinal);
                } catch (SignatureException e3) {
                    throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e3.toString() + ")", -4);
                }
            } catch (BadPaddingException e4) {
                throw new XpPsoException(this.a, "Decrypt doFinal Fail...\r\n(" + e4.toString() + ")", -3);
            } catch (IllegalBlockSizeException e5) {
                throw new XpPsoException(this.a, "Decrypt doFinal Fail...\r\n(" + e5.toString() + ")", -3);
            } catch (Exception e6) {
                throw new XpPsoException(this.a, "Decrypt doFinal Fail...\r\n(" + e6.toString() + ")", -3);
            }
        }
        int i14 = i5;
        try {
            if (signature.verify(otaEncBinInfo.SignData)) {
                if (i14 == otaEncBinInfo.CheckSum) {
                    return bArr2;
                }
                throw new XpPsoException(this.a, "ota package CheckSum Error...", -5);
            }
            throw new XpPsoException(this.a, "bin Signed Data Verify  Fail...", -4);
        } catch (SignatureException e7) {
            throw new XpPsoException(this.a, "bin Signed Data Verify  Fail...\r\n(" + e7.toString() + ")", -4);
        }
    }

    protected byte[] a(byte[] bArr, int i) throws XpPsoException {
        int e28OtaPackheadLen = getE28OtaPackheadLen();
        byte[] bArr2 = new byte[e28OtaPackheadLen];
        System.arraycopy(bArr, 0, bArr2, 0, e28OtaPackheadLen);
        E28OtaPackhead e28OtaPackhead = getE28OtaPackhead(bArr2, bArr.length);
        if (e28OtaPackhead != null) {
            this.f[0] = e28OtaPackheadLen;
            byte[] bArr3 = new byte[(int) e28OtaPackhead.datalen];
            byte[] bArr4 = new byte[(int) e28OtaPackhead.datalen];
            System.arraycopy(bArr, e28OtaPackheadLen, bArr3, 0, bArr3.length);
            Cipher a = i == 2 ? a(e28OtaPackhead.key_id, "CTR", "NoPadding") : j(e28OtaPackhead.key_id);
            PublicKey g = g(e28OtaPackhead.cert_id);
            Signature b = b(g);
            a((int) e28OtaPackhead.datalen, e28OtaPackheadLen, bArr3, bArr4, a, b, e28OtaPackhead.datasha256, this.f);
            try {
                b.verify(e28OtaPackhead.sign);
                if (a(bArr4, e28OtaPackhead.datalen, b(g), e28OtaPackhead.sign)) {
                    this.f[0] = bArr.length;
                    return bArr4;
                }
                throw new XpPsoException(this.a, "ota package verify failed...", -4);
            } catch (SignatureException e) {
                Context context = this.a;
                throw new XpPsoException(context, "bin Signed Data Verify  Fail...\r\n(" + e.toString() + ")", -4);
            }
        }
        throw new XpPsoException(this.a, "Invalid File...\r\n(Packhead==null)", -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected javax.crypto.Cipher b(byte[] r7, java.lang.String r8, java.lang.String r9) throws android.pso.XpPsoException {
        /*
            r6 = this;
            r6.f()
            r0 = 2
            r1 = 0
            javax.crypto.Cipher r2 = r6.a(r0)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            byte[] r7 = r6.getD22encKey(r7)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            byte[] r7 = r2.doFinal(r7)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            r2 = 16
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            r3 = 0
            java.util.Arrays.fill(r2, r3)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            javax.crypto.spec.SecretKeySpec r4 = new javax.crypto.spec.SecretKeySpec     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            java.lang.String r5 = "AES"
            r4.<init>(r7, r5)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            java.util.Arrays.fill(r7, r3)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            int r7 = r2.length     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            java.lang.System.arraycopy(r2, r3, r2, r3, r7)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            javax.crypto.spec.IvParameterSpec r7 = new javax.crypto.spec.IvParameterSpec     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            r7.<init>(r2)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            r2.<init>()     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            java.lang.String r3 = "AES/"
            r2.append(r3)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            r2.append(r8)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            java.lang.String r8 = "/"
            r2.append(r8)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            r2.append(r9)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            javax.crypto.Cipher r8 = javax.crypto.Cipher.getInstance(r8)     // Catch: java.lang.Exception -> L62 javax.crypto.IllegalBlockSizeException -> L68 javax.crypto.BadPaddingException -> L6e java.security.InvalidAlgorithmParameterException -> L74 java.security.InvalidKeyException -> L7a javax.crypto.NoSuchPaddingException -> L80 java.security.NoSuchAlgorithmException -> L86
            r8.init(r0, r4, r7)     // Catch: java.lang.Exception -> L4d javax.crypto.IllegalBlockSizeException -> L50 javax.crypto.BadPaddingException -> L53 java.security.InvalidAlgorithmParameterException -> L56 java.security.InvalidKeyException -> L59 javax.crypto.NoSuchPaddingException -> L5c java.security.NoSuchAlgorithmException -> L5f
            return r8
        L4d:
            r7 = move-exception
            r1 = r8
            goto L63
        L50:
            r7 = move-exception
            r1 = r8
            goto L69
        L53:
            r7 = move-exception
            r1 = r8
            goto L6f
        L56:
            r7 = move-exception
            r1 = r8
            goto L75
        L59:
            r7 = move-exception
            r1 = r8
            goto L7b
        L5c:
            r7 = move-exception
            r1 = r8
            goto L81
        L5f:
            r7 = move-exception
            r1 = r8
            goto L87
        L62:
            r7 = move-exception
        L63:
            java.lang.String r7 = r7.toString()
            goto L8b
        L68:
            r7 = move-exception
        L69:
            java.lang.String r7 = r7.toString()
            goto L8b
        L6e:
            r7 = move-exception
        L6f:
            java.lang.String r7 = r7.toString()
            goto L8b
        L74:
            r7 = move-exception
        L75:
            java.lang.String r7 = r7.toString()
            goto L8b
        L7a:
            r7 = move-exception
        L7b:
            java.lang.String r7 = r7.toString()
            goto L8b
        L80:
            r7 = move-exception
        L81:
            java.lang.String r7 = r7.toString()
            goto L8b
        L86:
            r7 = move-exception
        L87:
            java.lang.String r7 = r7.toString()
        L8b:
            if (r1 == 0) goto L8e
            return r1
        L8e:
            android.pso.XpPsoException r8 = new android.pso.XpPsoException
            android.content.Context r9 = r6.a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "get Aes Key Cipher Failed...maybe need to preset\r\n"
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            r0 = -15
            r8.<init>(r9, r7, r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: android.pso.XpPso.b(byte[], java.lang.String, java.lang.String):javax.crypto.Cipher");
    }

    protected byte[] b(long j, int i, InputStream inputStream, OutputStream outputStream, Cipher cipher, Signature signature, byte[] bArr, long[] jArr) throws XpPsoException {
        MessageDigest messageDigest;
        MessageDigest messageDigest2;
        MessageDigest messageDigest3;
        OutputStream outputStream2;
        long j2;
        int read;
        int update;
        int i2 = 10240 > j ? (int) j : PsoAKS.DecBlockSize;
        long j3 = i2;
        long j4 = (j / j3) + (j % j3 == 0 ? 0 : 1);
        MessageDigest messageDigest4 = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            try {
                messageDigest4 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
            }
        } catch (NoSuchAlgorithmException e2) {
            messageDigest = null;
        }
        byte[] bArr2 = new byte[i2];
        byte[] bArr3 = new byte[i2];
        byte[] bArr4 = new byte[i2 * 3];
        if (j4 > 1) {
            MessageDigest messageDigest5 = messageDigest4;
            MessageDigest messageDigest6 = messageDigest;
            int i3 = 0;
            j2 = 0;
            boolean z = true;
            while (i3 < j4) {
                if (!this.i) {
                    long j5 = j2 + j3;
                    if (j5 > j) {
                        break;
                    }
                    long j6 = j3;
                    boolean z2 = !z;
                    if (z2) {
                        try {
                            read = inputStream.read(bArr2);
                        } catch (IOException e3) {
                            throw new XpPsoException(this.a, "Read File Failed\r\n(" + e3.toString() + ")", -22);
                        }
                    } else {
                        read = inputStream.read(bArr3);
                    }
                    if (read != i2) {
                        throw new XpPsoException(this.a, "Read File Failed\r\n(read len error)", -22);
                    }
                    if (z2) {
                        try {
                            update = cipher.update(bArr2, 0, bArr2.length, bArr4, 0);
                        } catch (Exception e4) {
                            throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e4.toString() + ")", -3);
                        }
                    } else {
                        update = cipher.update(bArr3, 0, bArr3.length, bArr4, 0);
                    }
                    if (update <= 0) {
                        throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(out = null)", -3);
                    }
                    byte[] bArr5 = bArr2;
                    int i4 = i2;
                    MessageDigest messageDigest7 = messageDigest6;
                    try {
                        messageDigest7.update(bArr4, 0, update);
                        MessageDigest messageDigest8 = messageDigest5;
                        messageDigest8.update(bArr4, 0, update);
                        signature.update(bArr4, 0, update);
                        long j7 = j4;
                        try {
                            outputStream.write(bArr4, 0, update);
                            outputStream.flush();
                            jArr[0] = i + j5;
                            i3++;
                            messageDigest6 = messageDigest7;
                            messageDigest5 = messageDigest8;
                            j2 = j5;
                            j3 = j6;
                            bArr2 = bArr5;
                            i2 = i4;
                            z = z2;
                            j4 = j7;
                            bArr4 = bArr4;
                        } catch (IOException e5) {
                            throw new XpPsoException(this.a, "write File Failed...\r\n(" + e5.toString() + ")", -44);
                        }
                    } catch (Exception e6) {
                        throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e6.toString() + ")", -4);
                    }
                } else {
                    throw new XpPsoException(this.a, "Decrypt Exit...", -33);
                }
            }
            messageDigest3 = messageDigest6;
            messageDigest2 = messageDigest5;
            outputStream2 = outputStream;
        } else {
            messageDigest2 = messageDigest4;
            messageDigest3 = messageDigest;
            outputStream2 = outputStream;
            j2 = 0;
        }
        int i5 = (int) (j - j2);
        if (i5 > 0) {
            byte[] bArr6 = new byte[i5];
            try {
                if (inputStream.read(bArr6) != bArr6.length) {
                    throw new XpPsoException(this.a, "Read File Failed\r\n(read len error)", -22);
                }
                try {
                    byte[] doFinal = cipher.doFinal(bArr6);
                    if (doFinal == null) {
                        throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(out null)", -3);
                    }
                    try {
                        messageDigest3.update(doFinal);
                        messageDigest2.update(doFinal);
                        signature.update(doFinal);
                        try {
                            outputStream2.write(doFinal);
                            outputStream.flush();
                        } catch (Exception e7) {
                            throw new XpPsoException(this.a, "write File Failed...\r\n(" + e7.toString() + ")", -44);
                        }
                    } catch (Exception e8) {
                        throw new XpPsoException(this.a, "bin Signed Data Verify update Fail...\r\n(" + e8.toString() + ")", -4);
                    }
                } catch (Exception e9) {
                    throw new XpPsoException(this.a, "Decrypt update Fail...\r\n(" + e9.toString() + ")", -3);
                }
            } catch (IOException e10) {
                throw new XpPsoException(this.a, "Read File Failed\r\n(" + e10.toString() + ")", -22);
            }
        }
        try {
            byte[] digest = messageDigest3.digest();
            byte[] digest2 = messageDigest2.digest();
            String i6 = i(digest);
            String i7 = i(bArr);
            Log.i("PSO", "sha256sum correct?=" + i7.contains(i6) + ",sha256result=" + i6);
            return digest2;
        } catch (Exception e11) {
            throw new XpPsoException(this.a, "sha256sum check Failed...\r\n(" + e11.toString() + ")", -4);
        }
    }

    protected byte[] b(byte[] bArr, int i) throws XpPsoException {
        long l = l(bArr);
        OtaEncBinInfo otaEncBinInfoFormMem = getOtaEncBinInfoFormMem(bArr, l);
        if (otaEncBinInfoFormMem.result == 0) {
            byte[] bArr2 = new byte[(int) otaEncBinInfoFormMem.dstBinLen];
            System.arraycopy(bArr, (int) (otaEncBinInfoFormMem.dstBinOffset + l), bArr2, 0, (int) otaEncBinInfoFormMem.dstBinLen);
            this.f[0] = l;
            int i2 = otaEncBinInfoFormMem.BINType > 2 ? 2 : otaEncBinInfoFormMem.BINType;
            Cipher a = i == 2 ? a(i2, "CBC", "NoPadding") : a(i2);
            PublicKey g = g();
            this.f[0] = l + otaEncBinInfoFormMem.dstBinOffset;
            byte[] a2 = a(otaEncBinInfoFormMem, (int) otaEncBinInfoFormMem.dstBinLen, (int) this.f[0], bArr2, a, a(g), this.f);
            this.f[0] = bArr.length;
            a(a2, (int) otaEncBinInfoFormMem.dstBinLen, a(g), otaEncBinInfoFormMem.SignData);
            if (a2 != null) {
                return a2;
            }
            throw new XpPsoException(this.a, "ota package decryptverify to mem failed...", -42);
        }
        throw new XpPsoException(this.a, "Invalid File...", -1);
    }

    @Override // android.pso.PsoCrypto
    public byte[] ble_package_decryptverify_mtom(byte[] bArr, int i) throws XpPsoException {
        b();
        this.i = false;
        return i == 28 ? a(bArr, 2) : i == 22 ? f(bArr) : b(bArr, 2);
    }

    protected String c(byte[] bArr) throws XpPsoException {
        String exc;
        String str = new String(bArr) + "nnnnaks";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "HmacMD5");
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            byte[] doFinal = mac.doFinal(str.getBytes());
            String d = d("AKa", 0);
            for (int i = 0; i < 100; i++) {
                if (!a(d) && i >= 99) {
                    genAliasRootKey();
                } else if (a(d)) {
                    break;
                }
                Thread.sleep(5L);
            }
            return Base64Util.encodeToString(a(d, doFinal, getAesSpecKeyVector(0)));
        } catch (IOException e) {
            exc = e.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (InvalidAlgorithmParameterException e2) {
            exc = e2.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (InvalidKeyException e3) {
            exc = e3.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (KeyStoreException e4) {
            exc = e4.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (NoSuchAlgorithmException e5) {
            exc = e5.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (UnrecoverableKeyException e6) {
            exc = e6.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (CertificateException e7) {
            exc = e7.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (NoSuchPaddingException e8) {
            exc = e8.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (ShortBufferException e9) {
            exc = e9.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        } catch (Exception e10) {
            exc = e10.toString();
            throw new XpPsoException(this.a, "get Alias failed ...\r\n(" + exc + ")", -7);
        }
    }

    boolean c() throws XpPsoException {
        if (ota_gen_mcu_key() != null) {
            if ("12345679".equals(new String(ota_mcu_decrypt(ota_mcu_encrypt("12345679".getBytes()))))) {
                Log.d("PSO", "Mcu Key Test succeed!");
                return true;
            }
            throw new XpPsoException(this.a, "Mcu Key Test Failed...!", PsoCrypto.Desc.TestAesCbcKeyFailed);
        }
        throw new XpPsoException(this.a, "get Mcu Key Failed...!", PsoCrypto.Desc.TestAesCbcKeyFailed);
    }

    @Override // android.pso.PsoCrypto
    public boolean checkIfPreseted() throws XpPsoException {
        b();
        f();
        return c == 21 ? D21checkIfPreseted() && d() : E28checkIfPreseted() && d();
    }

    protected String d(byte[] bArr) throws XpPsoException {
        String exc;
        String str = new String(bArr) + "nnnnaks";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "HmacMD5");
        String str2 = null;
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
            byte[] doFinal = mac.doFinal(str.getBytes());
            String d = d("AKa", 0);
            try {
                return Base64Util.encodeToString(a(d, doFinal, getAesSpecKeyVector(0)));
            } catch (InvalidKeyException e) {
                e = e;
                str2 = d;
                exc = e.toString();
                throw new XpPsoException(this.a, "get Alias failed ...(" + str2 + ")\r\n(" + exc + ")", -7);
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                str2 = d;
                exc = e.toString();
                throw new XpPsoException(this.a, "get Alias failed ...(" + str2 + ")\r\n(" + exc + ")", -7);
            } catch (Exception e3) {
                e = e3;
                str2 = d;
                exc = e.toString();
                throw new XpPsoException(this.a, "get Alias failed ...(" + str2 + ")\r\n(" + exc + ")", -7);
            }
        } catch (InvalidKeyException e4) {
            e = e4;
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
        } catch (Exception e6) {
            e = e6;
        }
    }

    boolean d() throws XpPsoException {
        if (ota_gen_mcu_key() != null) {
            Log.d("PSO", "Mcu Key preseted!");
            return true;
        }
        throw new XpPsoException(this.a, "get Mcu Key Failed...!", PsoCrypto.Desc.TestAesCbcKeyFailed);
    }

    protected String e(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, UnrecoverableKeyException, KeyStoreException, ShortBufferException, XpPsoException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        String str = new String(bArr) + "nnnnaks";
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKeySpec);
        return Base64Util.encodeToString(b(d("AKa", 0), mac.doFinal(str.getBytes()), getAesSpecKeyVector(0)));
    }

    boolean e() throws XpPsoException {
        b();
        f();
        for (int i = 0; i < 4; i++) {
            if (b(a("AKspec", i), getD21AesSpecKey(i), "AKs " + String.valueOf(i))) {
                Log.i("PSO", "S:AKs " + String.valueOf(i) + " succeed!");
            }
            if (stop) {
                return false;
            }
        }
        return a(b("AKm", 1), getMcuRtKey());
    }

    @Override // android.pso.PsoCrypto
    public byte[] e28ota_package_decryptverify_tofile(String str, String str2) throws XpPsoException {
        b();
        this.i = false;
        return b(str, str2, 2);
    }

    protected byte[] f(byte[] bArr) throws XpPsoException {
        int e28OtaPackheadLen = getE28OtaPackheadLen();
        byte[] bArr2 = new byte[e28OtaPackheadLen];
        System.arraycopy(bArr, 0, bArr2, 0, e28OtaPackheadLen);
        E28OtaPackhead e28OtaPackhead = getE28OtaPackhead(bArr2, bArr.length);
        if (e28OtaPackhead != null) {
            this.f[0] = e28OtaPackheadLen;
            byte[] bArr3 = new byte[(int) e28OtaPackhead.datalen];
            byte[] bArr4 = new byte[(int) e28OtaPackhead.datalen];
            System.arraycopy(bArr, e28OtaPackheadLen, bArr3, 0, bArr3.length);
            Cipher b = b(e28OtaPackhead.key_id, "CTR", "NoPadding");
            PublicKey g = g(e28OtaPackhead.cert_id);
            Signature b2 = b(g);
            a((int) e28OtaPackhead.datalen, e28OtaPackheadLen, bArr3, bArr4, b, b2, e28OtaPackhead.datasha256, this.f);
            try {
                b2.verify(e28OtaPackhead.sign);
                if (a(bArr4, e28OtaPackhead.datalen, b(g), e28OtaPackhead.sign)) {
                    this.f[0] = bArr.length;
                    return bArr4;
                }
                throw new XpPsoException(this.a, "package verify failed...", -4);
            } catch (SignatureException e) {
                Context context = this.a;
                throw new XpPsoException(context, "Data Signed Verify  Fail...\r\n(" + e.toString() + ")", -4);
            }
        }
        throw new XpPsoException(this.a, "Invalid Input...\r\n(Packhead==null)", -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.security.PublicKey g(byte[] r6) throws android.pso.XpPsoException {
        /*
            r5 = this;
            java.lang.String r0 = new java.lang.String
            r0.<init>(r6)
            byte[] r6 = r5.getE28Cert(r0)
            r0 = -2
            if (r6 == 0) goto L77
            r1 = 0
            byte[] r2 = PemStringFormat2der(r6)     // Catch: java.lang.Exception -> L26 java.io.IOException -> L2c java.security.cert.CertificateException -> L32
            java.security.cert.Certificate r2 = r5.b(r2)     // Catch: java.lang.Exception -> L26 java.io.IOException -> L2c java.security.cert.CertificateException -> L32
            if (r6 == 0) goto L25
            java.security.PublicKey r1 = r2.getPublicKey()     // Catch: java.lang.Exception -> L1c java.io.IOException -> L1f java.security.cert.CertificateException -> L22
            return r1
        L1c:
            r6 = move-exception
            r1 = r2
            goto L27
        L1f:
            r6 = move-exception
            r1 = r2
            goto L2d
        L22:
            r6 = move-exception
            r1 = r2
            goto L33
        L25:
            return r1
        L26:
            r6 = move-exception
        L27:
            java.lang.String r6 = r6.toString()
            goto L37
        L2c:
            r6 = move-exception
        L2d:
            java.lang.String r6 = r6.toString()
            goto L37
        L32:
            r6 = move-exception
        L33:
            java.lang.String r6 = r6.toString()
        L37:
            if (r1 != 0) goto L57
            android.pso.XpPsoException r1 = new android.pso.XpPsoException
            android.content.Context r2 = r5.a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "No Cert\r\n("
            r3.append(r4)
            r3.append(r6)
            java.lang.String r6 = ")"
            r3.append(r6)
            java.lang.String r6 = r3.toString()
            r1.<init>(r2, r6, r0)
            throw r1
        L57:
            android.pso.XpPsoException r0 = new android.pso.XpPsoException
            android.content.Context r1 = r5.a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "No PublicKey\r\n("
            r2.append(r3)
            r2.append(r6)
            java.lang.String r6 = ")"
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            r2 = -14
            r0.<init>(r1, r6, r2)
            throw r0
        L77:
            android.pso.XpPsoException r6 = new android.pso.XpPsoException
            android.content.Context r1 = r5.a
            java.lang.String r2 = "No Cert(get E28 Cert pem)"
            r6.<init>(r1, r2, r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.pso.XpPso.g(byte[]):java.security.PublicKey");
    }

    public boolean genAliasRootKey() throws Exception {
        return b(d("AKa", 0));
    }

    protected native byte[] getD22encKey(byte[] bArr);

    protected native byte[] getE28enccKey(byte[] bArr);

    protected native byte[] getE28enccKeyId(int i);

    @Override // android.pso.PsoCrypto
    public int get_carType() {
        return c;
    }

    @Override // android.pso.PsoCrypto
    public long get_dec_mtom_processed_len() {
        return this.f[0];
    }

    @Override // android.pso.PsoCrypto
    public long get_dec_tofile_processed_len() {
        return this.h[0];
    }

    @Override // android.pso.PsoCrypto
    public long get_dec_tomem_processed_len() {
        return this.g[0];
    }

    protected byte[] h(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            return null;
        }
    }

    protected String i(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length; i++) {
            str = str + String.format("%02x", Byte.valueOf(bArr[i]));
        }
        return str;
    }

    protected Cipher j(byte[] bArr) throws XpPsoException {
        String exc;
        f();
        byte[] bArr2 = new byte[16];
        try {
            Arrays.fill(bArr2, (byte) 0);
            return c(e(bArr), bArr2, "CTR", "NoPadding");
        } catch (InvalidAlgorithmParameterException e) {
            exc = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (InvalidKeyException e2) {
            exc = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (KeyStoreException e3) {
            exc = e3.toString();
            Context context22 = this.a;
            throw new XpPsoException(context22, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (NoSuchAlgorithmException e4) {
            exc = e4.toString();
            Context context222 = this.a;
            throw new XpPsoException(context222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (UnrecoverableKeyException e5) {
            exc = e5.toString();
            Context context2222 = this.a;
            throw new XpPsoException(context2222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (NoSuchPaddingException e6) {
            exc = e6.toString();
            Context context22222 = this.a;
            throw new XpPsoException(context22222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (ShortBufferException e7) {
            exc = e7.toString();
            Context context222222 = this.a;
            throw new XpPsoException(context222222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        } catch (Exception e8) {
            exc = e8.toString();
            Context context2222222 = this.a;
            throw new XpPsoException(context2222222, "get Aes Key Cipher Failed...maybe need to preset\r\n" + exc, -15);
        }
    }

    protected byte[] k(byte[] bArr) throws XpPsoException {
        f();
        Cipher a = a(3);
        byte[] bArr2 = new byte[bArr.length + (a.getBlockSize() - (bArr.length % a.getBlockSize()))];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = new byte[bArr.length];
        try {
            byte[] a2 = a(a, bArr2);
            if (a2 != null) {
                System.arraycopy(a2, 0, bArr3, 0, bArr.length);
                return bArr3;
            }
        } catch (Exception e) {
            e.toString();
        }
        return bArr3;
    }

    @Override // android.pso.PsoCrypto
    public void ota_dec_stop() {
        if (c == 28) {
            this.i = true;
        }
    }

    @Override // android.pso.PsoCrypto
    public byte[] ota_gen_mcu_key() throws XpPsoException {
        String exc;
        byte[] b;
        b();
        f();
        byte[] bArr = null;
        try {
            String c2 = c("AKm", 1);
            String str = null;
            for (int i = 0; i < 4; i++) {
                str = str + b(i);
            }
            b = b(c2, c(str), getAesSpecKeyVector(0));
        } catch (InvalidAlgorithmParameterException e) {
            exc = e.toString();
        } catch (InvalidKeyException e2) {
            exc = e2.toString();
        } catch (KeyStoreException e3) {
            exc = e3.toString();
        } catch (NoSuchAlgorithmException e4) {
            exc = e4.toString();
        } catch (UnrecoverableKeyException e5) {
            exc = e5.toString();
        } catch (NoSuchPaddingException e6) {
            exc = e6.toString();
        } catch (ShortBufferException e7) {
            exc = e7.toString();
        } catch (Exception e8) {
            exc = e8.toString();
        }
        if (b != null) {
            return b;
        }
        exc = null;
        bArr = b;
        if (bArr != null) {
            return bArr;
        }
        throw new XpPsoException(this.a, "get MCU Key failed...maybe need to preset\r\n(" + exc + ")", -15);
    }

    @Override // android.pso.PsoCrypto
    public byte[] ota_mcu_decrypt(byte[] bArr) throws XpPsoException {
        if (this.b == null) {
            String str = null;
            try {
                this.b = new SecretKeySpec(ota_gen_mcu_key(), "AES");
            } catch (XpPsoException e) {
                str = e.toString();
            }
            if (this.b == null) {
                Context context = this.a;
                throw new XpPsoException(context, "get MCU Key failed ...maybe need to preset\r\n(" + str + ")", -15);
            }
        }
        return a(bArr, this.b, getAesSpecKeyVector(0));
    }

    @Override // android.pso.PsoCrypto
    public byte[] ota_mcu_encrypt(byte[] bArr) throws XpPsoException {
        String str;
        if (this.b == null) {
            try {
                this.b = new SecretKeySpec(ota_gen_mcu_key(), "AES");
            } catch (Exception e) {
                str = e.toString();
            }
        }
        str = null;
        if (this.b != null) {
            return b(bArr, this.b, getAesSpecKeyVector(0));
        }
        Context context = this.a;
        throw new XpPsoException(context, "get MCU Key failed ...maybe need to preset\r\n(" + str + ")", -15);
    }

    @Override // android.pso.PsoCrypto
    public byte[] ota_package_decryptverify_mtom(byte[] bArr) throws XpPsoException {
        b();
        this.i = false;
        return c == 28 ? a(bArr, 2) : b(bArr, 2);
    }

    @Override // android.pso.PsoCrypto
    public boolean ota_package_decryptverify_tofile(String str, String str2) throws XpPsoException {
        b();
        this.i = false;
        return c == 28 ? c(str, str2, 2) : a(str, str2, 2);
    }

    @Override // android.pso.PsoCrypto
    public byte[] ota_package_decryptverify_tomem(String str) throws XpPsoException {
        b();
        this.i = false;
        return c == 28 ? f(str, 2) : e(str, 2);
    }

    @Override // android.pso.PsoCrypto
    public int ota_security_access_key(int i, int i2, int i3) throws XpPsoException {
        b();
        String str = new String(k(GetUDSSecurityLuaEncData()));
        try {
            String str2 = String.format("%x", Integer.valueOf(i)) + "," + String.format("%x", Integer.valueOf(i2)) + "," + String.format("%x", Integer.valueOf(i3));
            Globals standardGlobals = JsePlatform.standardGlobals();
            standardGlobals.load(str).call(LuaValue.valueOf(str2));
            String luaValue = standardGlobals.get("re_key").toString();
            long longValue = luaValue != null ? Long.valueOf(luaValue).longValue() : 0L;
            if (longValue != 0) {
                return (int) longValue;
            }
            throw new XpPsoException(this.a, "get ota security access key failed...", -73);
        } catch (Exception e) {
            throw new XpPsoException(this.a, "get ota security access key failed...\r\n(" + e.toString() + ")", -73);
        }
    }

    @Override // android.pso.PsoCrypto
    public void set_carType(int i) {
        c = i;
    }
}
