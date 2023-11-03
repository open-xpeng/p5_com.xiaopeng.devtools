package com.xiaopeng.commonfunc.utils;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

/* compiled from: FileUtil.java */
/* loaded from: classes11.dex */
public class i {
    public static String aU(String str) {
        try {
            return new File(str).getName();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
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
                    fileWriter2.flush();
                    fileWriter2.close();
                    z = true;
                } catch (Exception e2) {
                    e = e2;
                    fileWriter = fileWriter2;
                    e.printStackTrace();
                    if (fileWriter != null) {
                        fileWriter.flush();
                        fileWriter.close();
                    }
                    com.xiaopeng.lib.utils.c.f("FileUtil", "writeFile path=" + str + ", value=" + str2 + "res=" + z);
                    return z;
                } catch (Throwable th) {
                    th = th;
                    fileWriter = fileWriter2;
                    if (fileWriter != null) {
                        try {
                            fileWriter.flush();
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

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (android.text.TextUtils.isEmpty(r11) == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002e, code lost:
        r5 = new java.lang.String[]{r4};
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        r5 = r4.split(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0037, code lost:
        r4 = r5.length;
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
        if (r6 >= r4) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003b, code lost:
        r7 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
        if (r7.contains(r10) == false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
        r0 = r7.replace(r10, "");
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        r6 = r6 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String g(java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r0 = 0
            r1 = 0
            r2 = 1
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L55
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L55
            r4.<init>(r9)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L55
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L52 java.lang.Exception -> L55
        L11:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            if (r4 != 0) goto L18
            goto L5a
        L18:
            boolean r5 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            if (r5 == 0) goto L22
        L20:
            r0 = r4
            goto L5a
        L22:
            boolean r5 = r4.contains(r10)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            if (r5 == 0) goto L11
            boolean r5 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            if (r5 == 0) goto L33
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            r5[r1] = r4     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            goto L37
        L33:
            java.lang.String[] r5 = r4.split(r11)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
        L37:
            int r4 = r5.length     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            r6 = r1
        L39:
            if (r6 >= r4) goto L4f
            r7 = r5[r6]     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            boolean r8 = r7.contains(r10)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            if (r8 == 0) goto L4c
            java.lang.String r4 = ""
            java.lang.String r4 = r7.replace(r10, r4)     // Catch: java.lang.Exception -> L50 java.lang.Throwable -> L73
            r0 = r4
            goto L4f
        L4c:
            int r6 = r6 + 1
            goto L39
        L4f:
            goto L5a
        L50:
            r4 = move-exception
            goto L57
        L52:
            r9 = move-exception
            r3 = r0
            goto L74
        L55:
            r4 = move-exception
            r3 = r0
        L57:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L73
        L5a:
            closeQuietly(r3)
            java.lang.String r3 = "FileUtil"
            java.lang.String r4 = "read path[%s] tag[%s] split[%s] value[%s]"
            r5 = 4
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r1] = r9
            r5[r2] = r10
            r9 = 2
            r5[r9] = r11
            r9 = 3
            r5[r9] = r0
            com.xiaopeng.lib.utils.c.i(r3, r4, r5)
            return r0
        L73:
            r9 = move-exception
        L74:
            closeQuietly(r3)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.commonfunc.utils.i.g(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static String aV(String str) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new FileReader(str));
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                            sb.append("\n");
                        } catch (Exception e) {
                            e = e;
                            bufferedReader = bufferedReader2;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
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
                    bufferedReader2.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
        return sb.toString();
    }

    public static boolean deleteFile(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.delete()) {
            return false;
        }
        com.xiaopeng.lib.utils.c.g("FileUtil", "delete file " + str + " successful");
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

    public static String h(String str, String str2, String str3) {
        File file = new File(str);
        String str4 = null;
        if (!file.exists()) {
            com.xiaopeng.lib.utils.c.i("FileUtil", "getFileNameWithSuffix filePath " + str + " not exist");
        } else {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    if (!listFiles[i].isDirectory() && listFiles[i].getName().endsWith(str2) && listFiles[i].getName().contains(str3)) {
                        str4 = listFiles[i].getName();
                    }
                }
            }
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", "getFileNameWithSuffix name " + str4);
        return str4;
    }

    public static ArrayList<String> a(String str, String str2, final String str3, boolean z, long j, long j2) {
        ArrayList<String> arrayList = new ArrayList<>();
        File[] listFiles = new File(str).listFiles(new FilenameFilter() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$i$NvJGBCK4WbGY2NVpeMppyfkclyw
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str4) {
                boolean b;
                b = i.b(str3, file, str4);
                return b;
            }
        });
        if (z) {
            Arrays.sort(listFiles, new Comparator() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$i$MqMEEsohFVSIDGbUROpSv0w6Gw8
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int b;
                    b = i.b((File) obj, (File) obj2);
                    return b;
                }
            });
        } else {
            Arrays.sort(listFiles, new Comparator() { // from class: com.xiaopeng.commonfunc.utils.-$$Lambda$i$WN86bzIrtWgiBc2PNXGQuUUmG7I
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int a;
                    a = i.a((File) obj, (File) obj2);
                    return a;
                }
            });
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int b(File file, File file2) {
        return file.getName().compareTo(file2.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int a(File file, File file2) {
        return file2.getName().compareTo(file.getName());
    }

    public static boolean aY(String str) {
        boolean z;
        if (!TextUtils.isEmpty(str)) {
            z = new File(str).exists();
        } else {
            z = false;
        }
        com.xiaopeng.lib.utils.c.f("FileUtil", str + " isExistFilePath = " + z);
        return z;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cf A[Catch: IOException -> 0x00d3, TRY_ENTER, TryCatch #4 {IOException -> 0x00d3, blocks: (B:49:0x00cf, B:53:0x00d7, B:55:0x00dc, B:57:0x00e1, B:30:0x00a8, B:32:0x00ad, B:33:0x00b0), top: B:76:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d7 A[Catch: IOException -> 0x00d3, TryCatch #4 {IOException -> 0x00d3, blocks: (B:49:0x00cf, B:53:0x00d7, B:55:0x00dc, B:57:0x00e1, B:30:0x00a8, B:32:0x00ad, B:33:0x00b0), top: B:76:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00dc A[Catch: IOException -> 0x00d3, TryCatch #4 {IOException -> 0x00d3, blocks: (B:49:0x00cf, B:53:0x00d7, B:55:0x00dc, B:57:0x00e1, B:30:0x00a8, B:32:0x00ad, B:33:0x00b0), top: B:76:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e1 A[Catch: IOException -> 0x00d3, TRY_LEAVE, TryCatch #4 {IOException -> 0x00d3, blocks: (B:49:0x00cf, B:53:0x00d7, B:55:0x00dc, B:57:0x00e1, B:30:0x00a8, B:32:0x00ad, B:33:0x00b0), top: B:76:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f7 A[Catch: IOException -> 0x00f3, TryCatch #5 {IOException -> 0x00f3, blocks: (B:64:0x00ef, B:68:0x00f7, B:70:0x00fc, B:72:0x0101), top: B:77:0x00ef }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00fc A[Catch: IOException -> 0x00f3, TryCatch #5 {IOException -> 0x00f3, blocks: (B:64:0x00ef, B:68:0x00f7, B:70:0x00fc, B:72:0x0101), top: B:77:0x00ef }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0101 A[Catch: IOException -> 0x00f3, TRY_LEAVE, TryCatch #5 {IOException -> 0x00f3, blocks: (B:64:0x00ef, B:68:0x00f7, B:70:0x00fc, B:72:0x0101), top: B:77:0x00ef }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void x(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 267
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.commonfunc.utils.i.x(java.lang.String, java.lang.String):void");
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

    public static String W(Context context) {
        String str;
        Iterator it = StorageManager.from(context).getVolumes().iterator();
        while (true) {
            if (it.hasNext()) {
                VolumeInfo volumeInfo = (VolumeInfo) it.next();
                com.xiaopeng.lib.utils.c.g("FileUtil", "volumeInfo = " + volumeInfo + " , volumeInfo.getDisk() = " + volumeInfo.getDisk());
                if (volumeInfo.getDisk() != null && volumeInfo.getDisk().isUsb()) {
                    if (volumeInfo.getState() != 1) {
                        str = volumeInfo.fsType;
                    } else {
                        com.xiaopeng.commonfunc.system.a.a.sleep(2000L);
                        str = W(context);
                    }
                }
            } else {
                str = null;
                break;
            }
        }
        com.xiaopeng.lib.utils.c.i("FileUtil", "getUDiskFsType [%s]", str);
        return str;
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
}
