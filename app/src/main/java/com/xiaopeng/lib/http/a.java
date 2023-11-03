package com.xiaopeng.lib.http;

import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/* compiled from: FileUtils.java */
/* loaded from: classes12.dex */
public class a {
    public static void bn(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file.exists()) {
                if (file.isFile()) {
                    file.delete();
                    return;
                }
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (int i = 0; i < listFiles.length; i++) {
                        bn(listFiles[i].getAbsolutePath());
                        if (listFiles[i].isDirectory()) {
                            listFiles[i].delete();
                        }
                    }
                }
            }
        }
    }

    public static String readTextFile(File file, int i, String str) throws IOException {
        int read;
        int read2;
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        try {
            long length = file.length();
            if (i <= 0 && (length <= 0 || i != 0)) {
                if (i >= 0) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    do {
                        read = bufferedInputStream.read(bArr);
                        if (read > 0) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                    } while (read == bArr.length);
                    return byteArrayOutputStream.toString();
                }
                byte[] bArr2 = null;
                boolean z = false;
                byte[] bArr3 = null;
                while (true) {
                    if (bArr2 != null) {
                        z = true;
                    }
                    if (bArr2 == null) {
                        bArr2 = new byte[-i];
                    }
                    read2 = bufferedInputStream.read(bArr2);
                    if (read2 != bArr2.length) {
                        break;
                    }
                    byte[] bArr4 = bArr3;
                    bArr3 = bArr2;
                    bArr2 = bArr4;
                }
                if (bArr3 != null || read2 > 0) {
                    if (bArr3 == null) {
                        return new String(bArr2, 0, read2);
                    }
                    if (read2 > 0) {
                        System.arraycopy(bArr3, read2, bArr3, 0, bArr3.length - read2);
                        System.arraycopy(bArr2, 0, bArr3, bArr3.length - read2, read2);
                        z = true;
                    }
                    if (str != null && z) {
                        return str + new String(bArr3);
                    }
                    return new String(bArr3);
                }
                return "";
            }
            if (length > 0 && (i == 0 || length < i)) {
                i = (int) length;
            }
            byte[] bArr5 = new byte[i + 1];
            int read3 = bufferedInputStream.read(bArr5);
            if (read3 <= 0) {
                return "";
            }
            if (read3 <= i) {
                return new String(bArr5, 0, read3);
            }
            if (str == null) {
                return new String(bArr5, 0, i);
            }
            return new String(bArr5, 0, i) + str;
        } finally {
            bufferedInputStream.close();
            fileInputStream.close();
        }
    }

    public static void stringToFile(String str, String str2) throws IOException {
        FileWriter fileWriter = new FileWriter(str);
        try {
            fileWriter.write(str2);
        } finally {
            fileWriter.close();
        }
    }
}
