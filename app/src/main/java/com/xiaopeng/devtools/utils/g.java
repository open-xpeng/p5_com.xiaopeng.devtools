package com.xiaopeng.devtools.utils;

import android.annotation.SuppressLint;
import android.app.usage.StorageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Environment;
import android.os.StatFs;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.xiaopeng.devtools.bean.event.CopyFileTestResult;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* compiled from: FileUtil.java */
/* loaded from: classes12.dex */
public class g {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.Closeable] */
    public static void a(Context context, String str, int i) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception e;
        ?? sb = new StringBuilder();
        sb.append(Environment.getRootDirectory().getParent());
        sb.append(str);
        File file = new File(sb.toString());
        if (!file.exists()) {
            InputStream openRawResource = context.getResources().openRawResource(i);
            byte[] bArr = new byte[1024];
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    while (true) {
                        try {
                            int read = openRawResource.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, read);
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            closeQuietly(fileOutputStream);
                            closeQuietly(openRawResource);
                        }
                    }
                    fileOutputStream.flush();
                } catch (Exception e3) {
                    fileOutputStream = null;
                    e = e3;
                } catch (Throwable th2) {
                    sb = 0;
                    th = th2;
                    closeQuietly(sb);
                    closeQuietly(openRawResource);
                    throw th;
                }
                closeQuietly(fileOutputStream);
                closeQuietly(openRawResource);
            } catch (Throwable th3) {
                th = th3;
                closeQuietly(sb);
                closeQuietly(openRawResource);
                throw th;
            }
        }
    }

    public static boolean F(String str, String str2) {
        boolean z;
        FileWriter fileWriter = null;
        try {
            try {
                FileWriter fileWriter2 = new FileWriter(str);
                try {
                    fileWriter2.write(str2);
                    closeQuietly(fileWriter2);
                    z = true;
                } catch (Exception e) {
                    e = e;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    closeQuietly(fileWriter);
                    z = false;
                    com.xiaopeng.lib.utils.c.f("FileUtil", "write path=" + str + ", value=" + str2 + ", res=" + z);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    closeQuietly(fileWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "write path=" + str + ", value=" + str2 + ", res=" + z);
        return z;
    }

    public static boolean c(String str, byte[] bArr) {
        File file = new File(str);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    fileOutputStream2.write(bArr);
                    fileOutputStream2.flush();
                    fileOutputStream2.getFD().sync();
                    closeQuietly(fileOutputStream2);
                    return true;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    closeQuietly(fileOutputStream);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    closeQuietly(fileOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean u(String str, String str2) {
        boolean z = false;
        FileWriter fileWriter = null;
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileWriter fileWriter2 = new FileWriter(str);
                try {
                    fileWriter2.write(str2);
                    fileWriter2.close();
                    z = true;
                } catch (Exception e2) {
                    e = e2;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    com.xiaopeng.lib.utils.c.f("FileUtil", "writeFile path=" + str + ", value=" + str2 + "res=" + z);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
            }
            com.xiaopeng.lib.utils.c.f("FileUtil", "writeFile path=" + str + ", value=" + str2 + "res=" + z);
            return z;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String cs(String str) {
        String str2;
        String str3;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(str));
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
            str2 = null;
        }
        try {
            try {
                str2 = bufferedReader.readLine();
                if (str2 != null) {
                    try {
                        str3 = str2.trim();
                    } catch (Exception e2) {
                        e = e2;
                        bufferedReader2 = bufferedReader;
                        e.printStackTrace();
                        closeQuietly(bufferedReader2);
                        str3 = str2;
                        com.xiaopeng.lib.utils.c.f("FileUtil", "read path=" + str + ", value=" + str3);
                        return str3;
                    }
                } else {
                    str3 = str2;
                }
                closeQuietly(bufferedReader);
            } catch (Exception e3) {
                str2 = null;
                bufferedReader2 = bufferedReader;
                e = e3;
            }
            com.xiaopeng.lib.utils.c.f("FileUtil", "read path=" + str + ", value=" + str3);
            return str3;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            closeQuietly(bufferedReader2);
            throw th;
        }
    }

    public static String G(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(str2);
                        sb.append(readLine);
                    } catch (Exception e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        closeQuietly(bufferedReader);
                        com.xiaopeng.lib.utils.c.f("FileUtil", "readAll path=" + str + ", value=" + ((Object) sb));
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        closeQuietly(bufferedReader);
                        throw th;
                    }
                }
                closeQuietly(bufferedReader2);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "readAll path=" + str + ", value=" + ((Object) sb));
        return sb.toString();
    }

    public static String aV(String str) {
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader2 = null;
        try {
            try {
                try {
                    bufferedReader = new BufferedReader(new FileReader(str));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine + "\n");
                        } catch (Exception e) {
                            e = e;
                            bufferedReader2 = bufferedReader;
                            e.printStackTrace();
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = bufferedReader2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return sb.toString();
    }

    public static byte[] ct(String str) {
        byte[] bArr;
        FileInputStream fileInputStream;
        byte[] bArr2 = new byte[0];
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(new File(Environment.getRootDirectory().getParent() + str));
            } catch (FileNotFoundException e) {
                e = e;
                bArr = bArr2;
            } catch (IOException e2) {
                e = e2;
                bArr = bArr2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                byte[] bArr3 = new byte[4096];
                int read = fileInputStream.read(bArr3);
                bArr = new byte[read];
                for (int i = 0; i < read; i++) {
                    try {
                        bArr[i] = bArr3[i];
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        fileInputStream2 = fileInputStream;
                        e.printStackTrace();
                        closeQuietly(fileInputStream2);
                        return bArr;
                    } catch (IOException e4) {
                        e = e4;
                        fileInputStream2 = fileInputStream;
                        e.printStackTrace();
                        closeQuietly(fileInputStream2);
                        return bArr;
                    }
                }
                closeQuietly(fileInputStream);
            } catch (FileNotFoundException e5) {
                e = e5;
                bArr = bArr2;
            } catch (IOException e6) {
                e = e6;
                bArr = bArr2;
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            closeQuietly(fileInputStream2);
            throw th;
        }
    }

    public static int cu(String str) {
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                try {
                    String readLine = bufferedReader2.readLine();
                    r0 = readLine != null ? Integer.parseInt(readLine.trim()) : -1;
                    closeQuietly(bufferedReader2);
                } catch (Exception e) {
                    e = e;
                    bufferedReader = bufferedReader2;
                    e.printStackTrace();
                    closeQuietly(bufferedReader);
                    com.xiaopeng.lib.utils.c.f("FileUtil", "readVal path=" + str + ", retVal=" + r0);
                    return r0;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    closeQuietly(bufferedReader);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "readVal path=" + str + ", retVal=" + r0);
        return r0;
    }

    public static boolean deleteFile(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.delete()) {
            return false;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "delete file " + str + " successful");
        return true;
    }

    public static boolean aW(String str) {
        boolean z = false;
        if (str != null && str.length() > 0) {
            File file = new File(str.substring(0, str.lastIndexOf("/")));
            z = !file.exists() ? file.mkdirs() : true;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "mkDir path=" + str + ", res=" + z);
        return z;
    }

    public static void aX(String str) {
        File[] listFiles;
        if (!TextUtils.isEmpty(str)) {
            try {
                File file = new File(str);
                if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                    for (File file2 : listFiles) {
                        aX(file2.getAbsolutePath());
                    }
                }
                if (!file.isDirectory()) {
                    file.delete();
                    return;
                }
                File[] listFiles2 = file.listFiles();
                if (listFiles2 != null && listFiles2.length == 0) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void b(String str, String str2, boolean z) {
        File[] listFiles;
        File[] listFiles2;
        if (!TextUtils.isEmpty(str) && !str.equals(str2)) {
            try {
                File file = new File(str);
                if (file.isDirectory() && (listFiles2 = file.listFiles()) != null) {
                    for (File file2 : listFiles2) {
                        b(file2.getAbsolutePath(), str2, true);
                    }
                }
                if (!file.isDirectory()) {
                    file.delete();
                } else if (z && (listFiles = file.listFiles()) != null && listFiles.length == 0) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(String str, String[] strArr, boolean z) {
        File[] listFiles;
        File[] listFiles2;
        if (!TextUtils.isEmpty(str) && !d.c(strArr, str)) {
            try {
                File file = new File(str);
                if (file.isDirectory() && (listFiles2 = file.listFiles()) != null) {
                    for (File file2 : listFiles2) {
                        a(file2.getAbsolutePath(), strArr, true);
                    }
                }
                if (!file.isDirectory()) {
                    file.delete();
                } else if (z && (listFiles = file.listFiles()) != null && listFiles.length == 0) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void a(String[] strArr, String[] strArr2, boolean z) {
        File[] listFiles;
        File[] listFiles2;
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str) && !d.c(strArr2, str)) {
                try {
                    File file = new File(str);
                    if (file.isDirectory() && (listFiles2 = file.listFiles()) != null) {
                        for (File file2 : listFiles2) {
                            a(file2.getAbsolutePath(), strArr2, true);
                        }
                    }
                    if (!file.isDirectory()) {
                        file.delete();
                    } else if (z && (listFiles = file.listFiles()) != null && listFiles.length == 0) {
                        file.delete();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String H(String str, String str2) {
        File file = new File(str);
        String str3 = null;
        if (!file.exists()) {
            com.xiaopeng.lib.utils.c.i("FileUtil", "getFileNameWithSuffix filePath " + str + " not exist");
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    if (!listFiles[i].isDirectory() && listFiles[i].getName().endsWith(str2)) {
                        str3 = listFiles[i].getName();
                    }
                }
            }
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "getFileNameWithSuffix name " + str3);
        return str3;
    }

    public static ArrayList<String> a(String str, final String str2, final String str3, long j, long j2) {
        ArrayList<String> arrayList = new ArrayList<>();
        File[] listFiles = new File(str).listFiles(new FilenameFilter() { // from class: com.xiaopeng.devtools.utils.-$$Lambda$g$TsQunxzs_FbPoV5rGfo2qriP-fc
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str4) {
                boolean b;
                b = g.b(str3, file, str4);
                return b;
            }
        });
        Arrays.sort(listFiles, new Comparator<File>() { // from class: com.xiaopeng.devtools.utils.g.1
            @Override // java.util.Comparator
            public int compare(File file, File file2) {
                long j3;
                try {
                    j3 = new File(file.getAbsolutePath() + File.separator + str2).lastModified() - new File(file2.getAbsolutePath() + File.separator + str2).lastModified();
                } catch (Exception e) {
                    e.printStackTrace();
                    j3 = 0;
                }
                int i = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
                if (i > 0) {
                    return 1;
                }
                if (i == 0) {
                    return 0;
                }
                return -1;
            }

            @Override // java.util.Comparator
            public boolean equals(Object obj) {
                return true;
            }
        });
        for (File file : listFiles) {
            try {
                String str4 = file.getAbsolutePath() + File.separator + str2;
                if (aY(str4)) {
                    long lastModified = new File(str4).lastModified();
                    if (lastModified >= j) {
                        arrayList.add(file.getAbsolutePath());
                    }
                    if (lastModified > j2) {
                        break;
                    }
                } else {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean b(String str, File file, String str2) {
        return str2.matches(str);
    }

    public static boolean I(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            com.xiaopeng.lib.utils.c.i("FileUtil", "isFileTypeExist filePath " + str + " not exist");
            return false;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "isFileTypeExist filePath " + str + " exist");
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                com.xiaopeng.lib.utils.c.f("FileUtil", "isFileTypeExist filesList[i].getName() =  " + listFiles[i].getName());
                if (listFiles[i].isDirectory()) {
                    if (I(listFiles[i].getPath(), str2)) {
                        return true;
                    }
                } else if (listFiles[i].getName().endsWith(str2)) {
                    com.xiaopeng.lib.utils.c.f("FileUtil", "isFileTypeExist i find file endswith " + str2);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean aY(String str) {
        boolean z;
        if (str != null) {
            z = new File(str).exists();
        } else {
            z = false;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", str + " isExistFilePath = " + z);
        return z;
    }

    public static boolean cv(String str) {
        boolean z;
        if (str != null) {
            z = new File(str).getParentFile().exists();
        } else {
            z = false;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", str + " isExistFileParentPath = " + z);
        return z;
    }

    public static List<String> cw(String str) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        File file = new File(str);
        if (file.exists() && file.isDirectory() && file.listFiles() != null) {
            for (File file2 : file.listFiles()) {
                arrayList.add(file2.getName());
                com.xiaopeng.lib.utils.c.f("FileUtil", "filepath=" + file2.getName());
            }
        }
        return arrayList;
    }

    public static String[] J(String str, final String str2) {
        return new File(str).list(new FilenameFilter() { // from class: com.xiaopeng.devtools.utils.-$$Lambda$g$76pJJVasRM-_Ap4FQqziDpas8qY
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str3) {
                boolean d;
                d = g.d(str2, file, str3);
                return d;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean d(String str, File file, String str2) {
        return str2.contains(str);
    }

    public static File[] K(String str, final String str2) {
        return new File(str).listFiles(new FilenameFilter() { // from class: com.xiaopeng.devtools.utils.-$$Lambda$g$RavoUKBIHT2PwkRq7fLNQwNmZeI
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str3) {
                boolean c;
                c = g.c(str2, file, str3);
                return c;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean c(String str, File file, String str2) {
        return str2.contains(str);
    }

    public static File[] a(File[] fileArr) {
        if (fileArr == null) {
            return fileArr;
        }
        for (int i = 0; i < fileArr.length; i++) {
            com.xiaopeng.lib.utils.c.f("FileUtil", "Before sort : " + fileArr[i].getName());
        }
        for (int i2 = 0; i2 < fileArr.length - 1; i2++) {
            int i3 = 0;
            while (i3 < (fileArr.length - 1) - i2) {
                int i4 = i3 + 1;
                if (fileArr[i3].lastModified() > fileArr[i4].lastModified()) {
                    File file = fileArr[i3];
                    fileArr[i3] = fileArr[i4];
                    fileArr[i4] = file;
                }
                i3 = i4;
            }
        }
        for (int i5 = 0; i5 < fileArr.length; i5++) {
            com.xiaopeng.lib.utils.c.f("FileUtil", "After sort : " + fileArr[i5].getName());
        }
        return fileArr;
    }

    public static int cx(String str) {
        File file = new File(str);
        com.xiaopeng.lib.utils.c.f("FileUtil", "getFilesSize path = " + str);
        File[] listFiles = file.listFiles();
        int i = 0;
        if (listFiles != null) {
            int i2 = 0;
            while (i < listFiles.length) {
                com.xiaopeng.lib.utils.c.f("FileUtil", "getFilesSize filesList[i].getName() =  " + listFiles[i].getName());
                if (listFiles[i].isDirectory()) {
                    i2 += cx(listFiles[i].getPath());
                } else {
                    i2++;
                }
                i++;
            }
            i = i2;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "getFilesSize path = " + str + ", filesum =  " + i);
        return i;
    }

    public static long cy(String str) {
        long j;
        try {
            j = new File(str).length();
            try {
                com.xiaopeng.lib.utils.c.f("FileUtil", "getFileLength path = " + str + ", fileLength =  " + j);
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return j;
            }
        } catch (Exception e2) {
            e = e2;
            j = 0;
        }
        return j;
    }

    public static CopyFileTestResult L(String str, String str2) {
        long j;
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        FileChannel channel;
        FileChannel channel2;
        CopyFileTestResult copyFileTestResult = new CopyFileTestResult(0L, false);
        long length = new File(str).length();
        long currentTimeMillis = System.currentTimeMillis();
        com.xiaopeng.lib.utils.c.f("FileUtil", "copyFileNGetUsedTime beginTime = " + currentTimeMillis + "srcPath = " + str + " destPath = " + str2 + " size = " + length);
        RandomAccessFile randomAccessFile3 = null;
        try {
            randomAccessFile2 = new RandomAccessFile(str, "r");
        } catch (Exception e) {
            e = e;
            j = currentTimeMillis;
            randomAccessFile2 = null;
        } catch (Throwable th) {
            th = th;
            j = currentTimeMillis;
            randomAccessFile = null;
            randomAccessFile2 = null;
        }
        try {
            RandomAccessFile randomAccessFile4 = new RandomAccessFile(str2, "rw");
            try {
                randomAccessFile2.seek(0L);
                randomAccessFile4.seek(0L);
                channel = randomAccessFile2.getChannel();
                channel2 = randomAccessFile4.getChannel();
                j = currentTimeMillis;
                randomAccessFile = randomAccessFile4;
            } catch (Exception e2) {
                e = e2;
                j = currentTimeMillis;
                randomAccessFile = randomAccessFile4;
            } catch (Throwable th2) {
                th = th2;
                j = currentTimeMillis;
                randomAccessFile = randomAccessFile4;
            }
            try {
                FileLock lock = channel2.lock(0L, length, false);
                channel.transferTo(0L, length, channel2);
                randomAccessFile.getFD().sync();
                lock.release();
                copyFileTestResult.mPass = true;
                long currentTimeMillis2 = System.currentTimeMillis();
                com.xiaopeng.lib.utils.c.f("FileUtil", "copyFileNGetUsedTime endTime = " + currentTimeMillis2);
                copyFileTestResult.mUsedTime = currentTimeMillis2 - j;
                closeQuietly(randomAccessFile);
            } catch (Exception e3) {
                e = e3;
                randomAccessFile3 = randomAccessFile;
                try {
                    copyFileTestResult.mPass = false;
                    e.printStackTrace();
                    long currentTimeMillis3 = System.currentTimeMillis();
                    com.xiaopeng.lib.utils.c.f("FileUtil", "copyFileNGetUsedTime endTime = " + currentTimeMillis3);
                    copyFileTestResult.mUsedTime = currentTimeMillis3 - j;
                    closeQuietly(randomAccessFile3);
                    closeQuietly(randomAccessFile2);
                    return copyFileTestResult;
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = randomAccessFile3;
                    long currentTimeMillis4 = System.currentTimeMillis();
                    com.xiaopeng.lib.utils.c.f("FileUtil", "copyFileNGetUsedTime endTime = " + currentTimeMillis4);
                    copyFileTestResult.mUsedTime = currentTimeMillis4 - j;
                    closeQuietly(randomAccessFile);
                    closeQuietly(randomAccessFile2);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                long currentTimeMillis42 = System.currentTimeMillis();
                com.xiaopeng.lib.utils.c.f("FileUtil", "copyFileNGetUsedTime endTime = " + currentTimeMillis42);
                copyFileTestResult.mUsedTime = currentTimeMillis42 - j;
                closeQuietly(randomAccessFile);
                closeQuietly(randomAccessFile2);
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            j = currentTimeMillis;
        } catch (Throwable th5) {
            th = th5;
            j = currentTimeMillis;
            randomAccessFile = randomAccessFile3;
            long currentTimeMillis422 = System.currentTimeMillis();
            com.xiaopeng.lib.utils.c.f("FileUtil", "copyFileNGetUsedTime endTime = " + currentTimeMillis422);
            copyFileTestResult.mUsedTime = currentTimeMillis422 - j;
            closeQuietly(randomAccessFile);
            closeQuietly(randomAccessFile2);
            throw th;
        }
        closeQuietly(randomAccessFile2);
        return copyFileTestResult;
    }

    public static FileWriter f(String str, boolean z) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(str, z);
        } catch (Exception e) {
            e.printStackTrace();
            fileWriter = null;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "getFileWriter path=" + str + ", append=" + z + ", writer=" + fileWriter);
        return fileWriter;
    }

    public static void a(FileWriter fileWriter) {
        if (fileWriter != null) {
            try {
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.close();
                com.xiaopeng.lib.utils.c.g("FileUtil", "finish closeFileWriter");
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    @SuppressLint({"NewApi"})
    public static String ad(Context context) {
        StorageVolume[] volumeList;
        StorageManager from = StorageManager.from(context);
        for (StorageVolume storageVolume : from.getVolumeList()) {
            com.xiaopeng.lib.utils.c.f("FileUtil", "checkTFCardState storageVolume.getPath() " + storageVolume.getPath());
            if (storageVolume.getDescription(context).toUpperCase().contains("SD")) {
                String volumeState = from.getVolumeState(storageVolume.getPath());
                com.xiaopeng.lib.utils.c.f("FileUtil", "checkTFCardState storageVolume.getPath() = " + storageVolume.getPath() + " state = " + volumeState);
                return volumeState;
            }
        }
        return "unknown";
    }

    public static void A(String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            com.xiaopeng.lib.utils.c.f("FileUtil", "srcFile  " + str + "is not exist");
            return;
        }
        com.xiaopeng.lib.utils.j.execute(new com.xiaopeng.devtools.system.b.a(str, str2, 0L, file.length()));
    }

    public static int M(String str, String str2) {
        File file = new File(str);
        if (!aW(str2)) {
            com.xiaopeng.lib.utils.c.h("FileUtil", "copyPath mkDir fail ");
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "copyPath srcPath = " + str + " destPath = " + str2);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < listFiles.length; i2++) {
            com.xiaopeng.lib.utils.c.f("FileUtil", "copyPath filesList[i].getName() =  " + listFiles[i2].getName());
            if (listFiles[i2].isDirectory()) {
                i += M(listFiles[i2].getPath(), str2 + listFiles[i2].getName() + File.separator);
            } else {
                i++;
                com.xiaopeng.lib.utils.j.execute(new com.xiaopeng.devtools.system.b.a(listFiles[i2].getPath(), str2 + listFiles[i2].getName(), 0L, listFiles[i2].length()));
            }
        }
        return i;
    }

    public static long cz(String str) {
        long g;
        File file = new File(str);
        try {
            if (file.isDirectory()) {
                g = h(file);
            } else {
                g = g(file);
            }
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            com.xiaopeng.lib.utils.c.i("FileUtils", "getFileSize error : " + str);
            return 0L;
        }
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
            com.xiaopeng.lib.utils.c.i("FileUtils", "File not exist :" + file.getPath());
        }
        return j;
    }

    @RequiresApi(api = 26)
    public static long h(Context context, String str) {
        long j;
        try {
            j = ((StorageStatsManager) context.getSystemService("storagestats")).queryStatsForPackage(StorageManager.UUID_DEFAULT, str, UserHandle.of(UserHandle.myUserId())).getCacheBytes();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            j = 0;
            com.xiaopeng.lib.utils.c.f("FileUtil", "getCacheSize mPackageName: " + str + ", size : " + j);
            return j;
        } catch (IOException e2) {
            e2.printStackTrace();
            j = 0;
            com.xiaopeng.lib.utils.c.f("FileUtil", "getCacheSize mPackageName: " + str + ", size : " + j);
            return j;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "getCacheSize mPackageName: " + str + ", size : " + j);
        return j;
    }

    public static String aU(String str) {
        return new File(str).getName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00ad A[Catch: IOException -> 0x00b1, TRY_ENTER, TryCatch #2 {IOException -> 0x00b1, blocks: (B:49:0x00ad, B:53:0x00b5, B:55:0x00ba, B:57:0x00bf, B:30:0x0086, B:32:0x008b, B:33:0x008e), top: B:76:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b5 A[Catch: IOException -> 0x00b1, TryCatch #2 {IOException -> 0x00b1, blocks: (B:49:0x00ad, B:53:0x00b5, B:55:0x00ba, B:57:0x00bf, B:30:0x0086, B:32:0x008b, B:33:0x008e), top: B:76:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ba A[Catch: IOException -> 0x00b1, TryCatch #2 {IOException -> 0x00b1, blocks: (B:49:0x00ad, B:53:0x00b5, B:55:0x00ba, B:57:0x00bf, B:30:0x0086, B:32:0x008b, B:33:0x008e), top: B:76:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00bf A[Catch: IOException -> 0x00b1, TRY_LEAVE, TryCatch #2 {IOException -> 0x00b1, blocks: (B:49:0x00ad, B:53:0x00b5, B:55:0x00ba, B:57:0x00bf, B:30:0x0086, B:32:0x008b, B:33:0x008e), top: B:76:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d5 A[Catch: IOException -> 0x00d1, TryCatch #3 {IOException -> 0x00d1, blocks: (B:64:0x00cd, B:68:0x00d5, B:70:0x00da, B:72:0x00df), top: B:77:0x00cd }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00da A[Catch: IOException -> 0x00d1, TryCatch #3 {IOException -> 0x00d1, blocks: (B:64:0x00cd, B:68:0x00d5, B:70:0x00da, B:72:0x00df), top: B:77:0x00cd }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00df A[Catch: IOException -> 0x00d1, TRY_LEAVE, TryCatch #3 {IOException -> 0x00d1, blocks: (B:64:0x00cd, B:68:0x00d5, B:70:0x00da, B:72:0x00df), top: B:77:0x00cd }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00cd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void x(java.lang.String r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 233
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.utils.g.x(java.lang.String, java.lang.String):void");
    }

    @SuppressLint({"NewApi"})
    public static StorageVolume i(Context context, String str) {
        StorageVolume[] volumeList;
        for (StorageVolume storageVolume : StorageManager.from(context).getVolumeList()) {
            if (storageVolume.getDescription(context).toUpperCase().contains(str)) {
                return storageVolume;
            }
        }
        return null;
    }

    public static String V(Context context) {
        for (VolumeInfo volumeInfo : StorageManager.from(context).getVolumes()) {
            com.xiaopeng.lib.utils.c.g("FileUtil", "volumeInfo = " + volumeInfo + " , volumeInfo.getDisk() = " + volumeInfo.getDisk());
            if (volumeInfo.getDisk() != null && volumeInfo.getDisk().isUsb()) {
                File internalPath = volumeInfo.getInternalPath();
                if (internalPath != null) {
                    return internalPath.getPath();
                }
                return null;
            }
        }
        return null;
    }

    public static String kZ() {
        if ("mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().toString();
        }
        return null;
    }

    public static int la() {
        return (int) (lb() / 1073741824);
    }

    public static long lb() {
        return cA(Environment.getDataDirectory().getPath());
    }

    public static long cA(String str) {
        long j;
        try {
            j = new StatFs(str).getAvailableBytes();
        } catch (Exception e) {
            com.xiaopeng.lib.utils.c.i("FileUtil", e.toString());
            j = 0;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "getAvailableBytes path:" + str + ", availableSize:" + j);
        return j;
    }

    @SuppressLint({"NewApi"})
    public static void a(StorageVolume storageVolume, Context context) {
        com.xiaopeng.lib.utils.c.f("FileUtil", "runFormat " + storageVolume.getPath());
        ComponentName componentName = new ComponentName("android", "com.android.internal.os.storage.ExternalStorageFormatter");
        Intent intent = new Intent("com.android.internal.os.storage.FORMAT_ONLY");
        intent.setComponent(componentName);
        intent.putExtra("android.os.storage.extra.STORAGE_VOLUME", storageVolume);
        context.startService(intent);
    }

    public static boolean a(Context context, int i, int i2) {
        boolean z = false;
        for (Map.Entry<String, UsbDevice> entry : ((UsbManager) context.getSystemService("usb")).getDeviceList().entrySet()) {
            UsbDevice value = entry.getValue();
            com.xiaopeng.lib.utils.c.f("FileUtil", "isUsbDeviceAttached: " + value);
            if (value.getVendorId() == i && value.getProductId() == i2) {
                z = true;
            }
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "isUsbDeviceAttached vid:" + i + ", pid:" + i2 + ", res:" + z);
        return z;
    }

    public static String cB(String str) {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                File file = !TextUtils.isEmpty(str) ? new File(str) : null;
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                if (file == null || !file.exists()) {
                    return "";
                }
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
                try {
                    byte[] bArr = new byte[10485760];
                    while (true) {
                        int read = randomAccessFile2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        messageDigest.update(bArr, 0, read);
                    }
                    String bigInteger = new BigInteger(1, messageDigest.digest()).toString(16);
                    while (bigInteger.length() < 32) {
                        bigInteger = "0" + bigInteger;
                    }
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return bigInteger;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    randomAccessFile = randomAccessFile2;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return "";
                } catch (IOException e4) {
                    e = e4;
                    randomAccessFile = randomAccessFile2;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return "";
                } catch (NoSuchAlgorithmException e6) {
                    e = e6;
                    randomAccessFile = randomAccessFile2;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                    }
                    return "";
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile = randomAccessFile2;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e9) {
                e = e9;
            } catch (IOException e10) {
                e = e10;
            } catch (NoSuchAlgorithmException e11) {
                e = e11;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
