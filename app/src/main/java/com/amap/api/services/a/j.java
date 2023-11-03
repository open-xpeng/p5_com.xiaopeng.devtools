package com.amap.api.services.a;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5.java */
/* loaded from: classes11.dex */
public class j {
    public static String a(String str) {
        FileInputStream fileInputStream;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            File file = new File(str);
            if (file.isFile() && file.exists()) {
                byte[] bArr = new byte[2048];
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                fileInputStream = new FileInputStream(file);
                while (true) {
                    try {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    } catch (Throwable th) {
                        th = th;
                        try {
                            o.a(th, "MD5", "getMd5FromFile");
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    o.a(e, "MD5", "getMd5FromFile");
                                }
                            }
                            return null;
                        } catch (Throwable th2) {
                            FileInputStream fileInputStream2 = fileInputStream;
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2) {
                                    o.a(e2, "MD5", "getMd5FromFile");
                                }
                            }
                            throw th2;
                        }
                    }
                }
                String d = m.d(messageDigest.digest());
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    o.a(e3, "MD5", "getMd5FromFile");
                }
                return d;
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        return m.d(E(str));
    }

    public static String a(byte[] bArr) {
        return m.d(o(bArr));
    }

    public static String c(String str) {
        return m.e(F(str));
    }

    public static byte[] b(byte[] bArr, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Throwable th) {
            o.a(th, "MD5", "getMd5Bytes1");
            return null;
        }
    }

    private static byte[] o(byte[] bArr) {
        return b(bArr, "MD5");
    }

    public static byte[] E(String str) {
        try {
            return G(str);
        } catch (Throwable th) {
            o.a(th, "MD5", "getMd5Bytes");
            return new byte[0];
        }
    }

    private static byte[] F(String str) {
        try {
            return G(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] G(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(m.a(str));
        return messageDigest.digest();
    }
}
