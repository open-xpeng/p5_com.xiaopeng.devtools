package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: Utils.java */
/* loaded from: classes11.dex */
public class m {
    static String a;

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            try {
                byteArrayOutputStream.write(new byte[]{0});
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        int length = str.length();
        if (length > 255) {
            length = 255;
        }
        a(byteArrayOutputStream, (byte) length, a(str));
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(bArr);
        }
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        try {
            byteArrayOutputStream.write(new byte[]{b});
            int i = b & 255;
            if (i < 255 && i > 0) {
                byteArrayOutputStream.write(bArr);
            } else if (i == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (IOException e) {
            o.a(e, "Utils", "writeField");
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String b = i.b(a(str));
        return ((char) ((b.length() % 26) + 65)) + b;
    }

    public static String c(String str) {
        if (str.length() < 2) {
            return "";
        }
        return i.a(str.substring(1));
    }

    public static boolean b(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static byte[] a() {
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            String[] split2 = new StringBuffer(new String(i.b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split2.length];
            for (int i2 = 0; i2 < split2.length; i2++) {
                bArr2[i2] = Byte.parseByte(split2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            o.a(th, "Utils", "getIV");
            return new byte[16];
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String a(java.lang.Throwable r3) {
        /*
            r0 = 0
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch: java.lang.Throwable -> L43
            r1.<init>()     // Catch: java.lang.Throwable -> L43
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L3c
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L3c
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L37
            java.lang.Throwable r3 = r3.getCause()     // Catch: java.lang.Throwable -> L37
        L14:
            if (r3 == 0) goto L1e
            r3.printStackTrace(r2)     // Catch: java.lang.Throwable -> L37
            java.lang.Throwable r3 = r3.getCause()     // Catch: java.lang.Throwable -> L37
            goto L14
        L1e:
            java.lang.String r3 = r1.toString()     // Catch: java.lang.Throwable -> L37
            r1.close()     // Catch: java.lang.Throwable -> L28
            goto L2c
        L28:
            r0 = move-exception
            r0.printStackTrace()
        L2c:
            r2.close()     // Catch: java.lang.Throwable -> L31
            goto L35
        L31:
            r0 = move-exception
            r0.printStackTrace()
        L35:
            return r3
        L37:
            r3 = move-exception
            goto L46
        L39:
            r3 = move-exception
            r2 = r0
            goto L62
        L3c:
            r3 = move-exception
            r2 = r0
            goto L46
        L3f:
            r3 = move-exception
            r1 = r0
            r2 = r1
            goto L62
        L43:
            r3 = move-exception
            r1 = r0
            r2 = r1
        L46:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L61
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.lang.Throwable -> L50
            goto L54
        L50:
            r3 = move-exception
            r3.printStackTrace()
        L54:
            if (r2 == 0) goto L5e
            r2.close()     // Catch: java.lang.Throwable -> L5a
            goto L5e
        L5a:
            r3 = move-exception
            r3.printStackTrace()
        L5e:
            return r0
        L61:
            r3 = move-exception
        L62:
            if (r1 == 0) goto L6c
            r1.close()     // Catch: java.lang.Throwable -> L68
            goto L6c
        L68:
            r0 = move-exception
            r0.printStackTrace()
        L6c:
            if (r2 == 0) goto L76
            r2.close()     // Catch: java.lang.Throwable -> L72
            goto L76
        L72:
            r0 = move-exception
            r0.printStackTrace()
        L76:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.m.a(java.lang.Throwable):java.lang.String");
    }

    public static String a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (z) {
                    z = false;
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(entry.getValue());
                } else {
                    stringBuffer.append("&");
                    stringBuffer.append(entry.getKey());
                    stringBuffer.append("=");
                    stringBuffer.append(entry.getValue());
                }
            }
        } catch (Throwable th) {
            o.a(th, "Utils", "assembleParams");
        }
        return stringBuffer.toString();
    }

    public static byte[] o(byte[] bArr) {
        try {
            return r(bArr);
        } catch (Throwable th) {
            o.a(th, "Utils", "gZip");
            return new byte[0];
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static byte[] p(byte[] r5) {
        /*
            r0 = 0
            if (r5 == 0) goto L9f
            int r1 = r5.length
            if (r1 != 0) goto L8
            goto L9f
        L8:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L55
            r1.<init>()     // Catch: java.lang.Throwable -> L55
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L4e
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L4e
            java.util.zip.ZipEntry r3 = new java.util.zip.ZipEntry     // Catch: java.lang.Throwable -> L49
            java.lang.String r4 = "log"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L49
            r2.putNextEntry(r3)     // Catch: java.lang.Throwable -> L49
            r2.write(r5)     // Catch: java.lang.Throwable -> L49
            r2.closeEntry()     // Catch: java.lang.Throwable -> L49
            r2.finish()     // Catch: java.lang.Throwable -> L49
            byte[] r5 = r1.toByteArray()     // Catch: java.lang.Throwable -> L49
            r2.close()     // Catch: java.lang.Throwable -> L32
            goto L3a
        L32:
            r0 = move-exception
            java.lang.String r2 = "Utils"
            java.lang.String r3 = "zip1"
            com.amap.api.services.a.o.a(r0, r2, r3)
        L3a:
            r1.close()     // Catch: java.lang.Throwable -> L3f
            goto L47
        L3f:
            r0 = move-exception
            java.lang.String r1 = "Utils"
            java.lang.String r2 = "zip2"
            com.amap.api.services.a.o.a(r0, r1, r2)
        L47:
            goto L7f
        L49:
            r5 = move-exception
            goto L58
        L4b:
            r5 = move-exception
            r2 = r0
            goto L81
        L4e:
            r5 = move-exception
            r2 = r0
            goto L58
        L51:
            r5 = move-exception
            r1 = r0
            r2 = r1
            goto L81
        L55:
            r5 = move-exception
            r1 = r0
            r2 = r1
        L58:
            java.lang.String r3 = "Utils"
            java.lang.String r4 = "zip"
            com.amap.api.services.a.o.a(r5, r3, r4)     // Catch: java.lang.Throwable -> L80
            if (r2 == 0) goto L6e
            r2.close()     // Catch: java.lang.Throwable -> L66
            goto L6e
        L66:
            r5 = move-exception
            java.lang.String r2 = "Utils"
            java.lang.String r3 = "zip1"
            com.amap.api.services.a.o.a(r5, r2, r3)
        L6e:
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.lang.Throwable -> L74
            goto L7c
        L74:
            r5 = move-exception
            java.lang.String r1 = "Utils"
            java.lang.String r2 = "zip2"
            com.amap.api.services.a.o.a(r5, r1, r2)
        L7c:
            r5 = r0
        L7f:
            return r5
        L80:
            r5 = move-exception
        L81:
            if (r2 == 0) goto L8f
            r2.close()     // Catch: java.lang.Throwable -> L87
            goto L8f
        L87:
            r0 = move-exception
            java.lang.String r2 = "Utils"
            java.lang.String r3 = "zip1"
            com.amap.api.services.a.o.a(r0, r2, r3)
        L8f:
            if (r1 == 0) goto L9d
            r1.close()     // Catch: java.lang.Throwable -> L95
            goto L9d
        L95:
            r0 = move-exception
            java.lang.String r1 = "Utils"
            java.lang.String r2 = "zip2"
            com.amap.api.services.a.o.a(r0, r1, r2)
        L9d:
            throw r5
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.m.p(byte[]):byte[]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PublicKey I(Context context) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(i.b("MIICnjCCAgegAwIBAgIJAJ0Pdzos7ZfYMA0GCSqGSIb3DQEBBQUAMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjAeFw0xMzA4MTUwNzU2NTVaFw0yMzA4MTMwNzU2NTVaMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA8eWAyHbFPoFPfdx5AD+D4nYFq4dbJ1p7SIKt19Oz1oivF/6H43v5Fo7s50pD1UF8+Qu4JoUQxlAgOt8OCyQ8DYdkaeB74XKb1wxkIYg/foUwN1CMHPZ9O9ehgna6K4EJXZxR7Y7XVZnbjHZIVn3VpPU/Rdr2v37LjTw+qrABJxMCAwEAAaNQME4wHQYDVR0OBBYEFOM/MLGP8xpVFuVd+3qZkw7uBvOTMB8GA1UdIwQYMBaAFOM/MLGP8xpVFuVd+3qZkw7uBvOTMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEA4LY3g8aAD8JkxAOqUXDDyLuCCGOc2pTIhn0TwMNaVdH4hZlpTeC/wuRD5LJ0z3j+IQ0vLvuQA5uDjVyEOlBrvVIGwSem/1XGUo13DfzgAJ5k1161S5l+sFUo5TxpHOXr8Z5nqJMjieXmhnE/I99GFyHpQmw4cC6rhYUhdhtg+Zk="));
        } catch (Throwable th) {
            th = th;
            byteArrayInputStream = null;
        }
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Certificate generateCertificate = certificateFactory.generateCertificate(byteArrayInputStream);
            if (generateCertificate != null && keyFactory != null) {
                PublicKey generatePublic = keyFactory.generatePublic(new X509EncodedKeySpec(generateCertificate.getPublicKey().getEncoded()));
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
                return generatePublic;
            }
            try {
                byteArrayInputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            return null;
        } catch (Throwable th4) {
            th = th4;
            try {
                th.printStackTrace();
                return null;
            } finally {
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d(byte[] bArr) {
        try {
            return q(bArr);
        } catch (Throwable th) {
            o.a(th, "Utils", "HexString");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(byte[] bArr) {
        try {
            return q(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String q(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private static byte[] r(byte[] r3) throws java.io.IOException, java.lang.Throwable {
        /*
            r0 = 0
            if (r3 != 0) goto L7
            return r0
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L39
            r1.<init>()     // Catch: java.lang.Throwable -> L39
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L32
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L32
            r2.write(r3)     // Catch: java.lang.Throwable -> L2e
            r2.finish()     // Catch: java.lang.Throwable -> L2e
            byte[] r3 = r1.toByteArray()     // Catch: java.lang.Throwable -> L2e
            r2.close()     // Catch: java.lang.Throwable -> L21
            goto L23
        L21:
            r3 = move-exception
            throw r3
        L23:
            r1.close()     // Catch: java.lang.Throwable -> L28
            goto L2a
        L28:
            r3 = move-exception
            throw r3
        L2a:
            return r3
        L2c:
            r3 = move-exception
            goto L3e
        L2e:
            r3 = move-exception
            goto L34
        L30:
            r3 = move-exception
            goto L3f
        L32:
            r3 = move-exception
            r2 = r0
        L34:
            r0 = r1
            goto L3b
        L36:
            r3 = move-exception
            r1 = r0
            goto L3f
        L39:
            r3 = move-exception
            r2 = r0
        L3b:
            throw r3     // Catch: java.lang.Throwable -> L3c
        L3c:
            r3 = move-exception
            r1 = r0
        L3e:
            r0 = r2
        L3f:
            if (r0 == 0) goto L47
            r0.close()     // Catch: java.lang.Throwable -> L45
            goto L47
        L45:
            r3 = move-exception
            throw r3
        L47:
            if (r1 == 0) goto L4f
            r1.close()     // Catch: java.lang.Throwable -> L4d
            goto L4f
        L4d:
            r3 = move-exception
            throw r3
        L4f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.m.r(byte[]):byte[]");
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("=");
        }
        a = sb.toString();
    }

    public static void a(Context context, String str, String str2, String str3) {
        e(a);
        e("                                   鉴权错误信息                                  ");
        e(a);
        d("SHA1Package:" + d.e(context));
        d("key:" + d.f(context));
        d("csid:" + str);
        d("gsid:" + str2);
        d("json:" + str3);
        e("                                                                               ");
        e("请仔细检查 SHA1Package与Key申请信息是否对应，Key是否删除，平台是否匹配                ");
        e("如若确认无误，仍存在问题，请将全部log信息提交到工单系统，多谢合作                       ");
        e(a);
    }

    static void d(String str) {
        if (str.length() < 78) {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            sb.append(str);
            for (int i = 0; i < 78 - str.length(); i++) {
                sb.append(" ");
            }
            sb.append("|");
            e(sb.toString());
            return;
        }
        String substring = str.substring(0, 78);
        e("|" + substring + "|");
        d(str.substring(78));
    }

    private static void e(String str) {
        Log.i("authErrLog", str);
    }
}
