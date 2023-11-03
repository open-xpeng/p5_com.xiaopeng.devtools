package com.xiaopeng.lib.utils.b;

import android.text.TextUtils;
import android.util.Base64;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: AESUtils.java */
/* loaded from: classes12.dex */
public class a {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.OutputStream] */
    public static boolean a(File e, File file, String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    Cipher v = v(str, 1);
                    fileInputStream = new FileInputStream((File) e);
                    try {
                        e = new FileOutputStream(file);
                        try {
                            CipherOutputStream cipherOutputStream = new CipherOutputStream(e, v);
                            byte[] bArr = new byte[1048576];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read < 0) {
                                    break;
                                }
                                cipherOutputStream.write(bArr, 0, read);
                            }
                            cipherOutputStream.close();
                            try {
                                fileInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            try {
                                e.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            return true;
                        } catch (Exception e4) {
                            e = e4;
                            fileInputStream2 = fileInputStream;
                            e = e;
                            e.printStackTrace();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (e != 0) {
                                e.close();
                                e = e;
                            }
                            return false;
                        } catch (OutOfMemoryError e6) {
                            e = e6;
                            fileInputStream2 = fileInputStream;
                            e = e;
                            e.printStackTrace();
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                            }
                            if (e != 0) {
                                e.close();
                                e = e;
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            if (e != 0) {
                                try {
                                    e.close();
                                } catch (IOException e9) {
                                    e9.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e10) {
                        e = e10;
                        e = 0;
                    } catch (OutOfMemoryError e11) {
                        e = e11;
                        e = 0;
                    } catch (Throwable th2) {
                        th = th2;
                        e = 0;
                    }
                } catch (Exception e12) {
                    e = e12;
                    e = 0;
                } catch (OutOfMemoryError e13) {
                    e = e13;
                    e = 0;
                } catch (Throwable th3) {
                    th = th3;
                    e = 0;
                    fileInputStream = null;
                }
            } catch (Throwable th4) {
                th = th4;
                fileInputStream = fileInputStream2;
            }
        } catch (IOException e14) {
            e = e14;
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.FileInputStream, java.io.InputStream] */
    public static boolean r(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return false;
        }
        CipherInputStream cipherInputStream = null;
        try {
            try {
                File file = new File((String) str3);
                if (!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdir();
                    }
                    file.createNewFile();
                }
                Cipher v = v(str, 1);
                str3 = new FileInputStream((String) str2);
                try {
                    str2 = new FileOutputStream(file);
                    try {
                        CipherInputStream cipherInputStream2 = new CipherInputStream(str3, v);
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = cipherInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                str2.write(bArr, 0, read);
                            }
                            str2.flush();
                            str2.getFD().sync();
                            try {
                                cipherInputStream2.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                str2.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            try {
                                str3.close();
                                return true;
                            } catch (IOException e3) {
                                e3.printStackTrace();
                                return true;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            cipherInputStream = cipherInputStream2;
                            e.printStackTrace();
                            if (cipherInputStream != null) {
                                try {
                                    cipherInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (str2 != 0) {
                                try {
                                    str2.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            if (str3 != 0) {
                                try {
                                    str3.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            cipherInputStream = cipherInputStream2;
                            if (cipherInputStream != null) {
                                try {
                                    cipherInputStream.close();
                                } catch (IOException e8) {
                                    e8.printStackTrace();
                                }
                            }
                            if (str2 != 0) {
                                try {
                                    str2.close();
                                } catch (IOException e9) {
                                    e9.printStackTrace();
                                }
                            }
                            if (str3 != 0) {
                                try {
                                    str3.close();
                                } catch (IOException e10) {
                                    e10.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e11) {
                        e = e11;
                    }
                } catch (Exception e12) {
                    e = e12;
                    str2 = 0;
                } catch (Throwable th2) {
                    th = th2;
                    str2 = 0;
                }
            } catch (Exception e13) {
                e = e13;
                str2 = 0;
                str3 = 0;
            } catch (Throwable th3) {
                th = th3;
                str2 = 0;
                str3 = 0;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static byte[] dD(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = 2 * i;
            bArr[i] = Integer.valueOf(str.substring(i2, i2 + 2), 16).byteValue();
        }
        return bArr;
    }

    public static String Z(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(Charset.forName("UTF-8")), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(dD(str)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Cipher v(String str, int i) {
        Cipher cipher;
        SecretKeySpec secretKeySpec;
        try {
            secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
            cipher = Cipher.getInstance("AES");
        } catch (InvalidKeyException e) {
            e = e;
            cipher = null;
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            cipher = null;
        } catch (NoSuchPaddingException e3) {
            e = e3;
            cipher = null;
        }
        try {
            cipher.init(i, secretKeySpec);
        } catch (InvalidKeyException e4) {
            e = e4;
            e.printStackTrace();
            return cipher;
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            e.printStackTrace();
            return cipher;
        } catch (NoSuchPaddingException e6) {
            e = e6;
            e.printStackTrace();
            return cipher;
        }
        return cipher;
    }

    public static String aa(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(Charset.forName("UTF-8")), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(Base64.decode(str, 0)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
