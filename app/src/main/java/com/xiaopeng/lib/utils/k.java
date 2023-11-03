package com.xiaopeng.lib.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* compiled from: ZipUtils.java */
/* loaded from: classes12.dex */
public class k {
    private static byte[] WU = {TestResultItem.RESULT_PASS, 75, 3, 4};
    private static byte[] WV = {TestResultItem.RESULT_PASS, 75, 5, 6};
    private static int WW = 4;

    public static String f(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return Base64.encodeToString(bArr, i);
    }

    public static void a(ZipOutputStream zipOutputStream, File file, String str) throws IOException {
        a(zipOutputStream, file, str, true);
    }

    public static void a(ZipOutputStream zipOutputStream, File file, String str, boolean z) throws IOException {
        FileInputStream fileInputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (File file2 : listFiles) {
                        a(zipOutputStream, file2, str + file.getName() + "/", z);
                    }
                } else if (!z) {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getAbsolutePath() + "/"));
                    zipOutputStream.closeEntry();
                }
                fileInputStream = null;
            } else {
                byte[] bArr = new byte[4096];
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(fileInputStream);
                        try {
                            zipOutputStream.putNextEntry(new ZipEntry(str + file.getName()));
                            while (true) {
                                int read = bufferedInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                zipOutputStream.write(bArr, 0, read);
                            }
                            zipOutputStream.flush();
                            zipOutputStream.closeEntry();
                            bufferedInputStream = bufferedInputStream2;
                        } catch (Exception e) {
                            e = e;
                            bufferedInputStream = bufferedInputStream2;
                            e.printStackTrace();
                            b.closeQuietly(bufferedInputStream);
                            b.closeQuietly(fileInputStream);
                        } catch (Throwable th) {
                            th = th;
                            bufferedInputStream = bufferedInputStream2;
                            b.closeQuietly(bufferedInputStream);
                            b.closeQuietly(fileInputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
        b.closeQuietly(bufferedInputStream);
        b.closeQuietly(fileInputStream);
    }

    public static File b(String str, List<String> list) throws IOException {
        return a(str, list, true);
    }

    public static File a(String str, List<String> list, boolean z) throws IOException {
        try {
            return b(str, list, z);
        } catch (Exception e) {
            e.printStackTrace();
            return new File(str);
        }
    }

    public static File b(String str, List<String> list, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        ZipOutputStream zipOutputStream;
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            fileOutputStream = new FileOutputStream(new File(str));
            try {
                zipOutputStream = new ZipOutputStream(fileOutputStream);
            } catch (Throwable th) {
                th = th;
                zipOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
            zipOutputStream = null;
        }
        try {
            for (String str2 : list) {
                File file2 = new File(str2);
                String parent = file2.getParent();
                if (parent == null) {
                    parent = "";
                }
                a(zipOutputStream, file2, parent + "/", z);
            }
            zipOutputStream.flush();
            zipOutputStream.closeEntry();
            b.closeQuietly(zipOutputStream);
            b.closeQuietly(fileOutputStream);
            return new File(str);
        } catch (Throwable th3) {
            th = th3;
            b.closeQuietly(zipOutputStream);
            b.closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public static String dK(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return f(byteArray, 2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
