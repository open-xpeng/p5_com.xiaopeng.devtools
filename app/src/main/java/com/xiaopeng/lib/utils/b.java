package com.xiaopeng.lib.utils;

import android.os.Environment;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

/* compiled from: FileUtils.java */
/* loaded from: classes12.dex */
public class b {
    public static double u(String str, int i) {
        long j;
        File file = new File(str);
        try {
            if (file.isDirectory()) {
                j = h(file);
            } else {
                j = g(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            c.i("FileUtils", "getFileSize error!");
            j = 0;
        }
        return b(j, i);
    }

    private static long g(File file) throws FileNotFoundException {
        if (file != null && file.exists()) {
            return file.length();
        }
        throw new FileNotFoundException();
    }

    private static long h(File file) throws Exception {
        long g;
        File[] listFiles = file.listFiles();
        long j = 0;
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    g = h(listFiles[i]);
                } else {
                    g = g(listFiles[i]);
                }
                j += g;
            }
        } else {
            c.i("FileUtils", "File not exist");
        }
        return j;
    }

    private static double b(long j, int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        switch (i) {
            case 1:
                return Double.valueOf(decimalFormat.format(j)).doubleValue();
            case 2:
                return Double.valueOf(decimalFormat.format(j / 1024.0d)).doubleValue();
            case 3:
                return Double.valueOf(decimalFormat.format(j / 1048576.0d)).doubleValue();
            case 4:
                return Double.valueOf(decimalFormat.format(j / 1.073741824E9d)).doubleValue();
            default:
                return 0.0d;
        }
    }

    private static boolean oK() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return false;
        }
        return true;
    }

    public static String oL() {
        if (oK()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        }
        return Environment.getDataDirectory().getAbsolutePath() + "/data/";
    }

    public static File X(String str, String str2) {
        File file;
        try {
            File file2 = new File(str);
            if (!file2.exists()) {
                file2.mkdirs();
            }
        } catch (Exception e) {
        }
        try {
            file = new File(str + str2);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return file;
            }
        } catch (Exception e3) {
            e = e3;
            file = null;
        }
        return file;
    }

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
                new File(str).delete();
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
