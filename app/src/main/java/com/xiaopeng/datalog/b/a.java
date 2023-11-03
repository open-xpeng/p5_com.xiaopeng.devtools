package com.xiaopeng.datalog.b;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.d;
import com.xiaopeng.lib.utils.i;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import java.io.FilenameFilter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: DataBackupHelper.java */
/* loaded from: classes11.dex */
public class a {
    private static volatile a qs;

    private a() {
    }

    public static a eW() {
        if (qs == null) {
            synchronized (a.class) {
                if (qs == null) {
                    qs = new a();
                }
            }
        }
        return qs;
    }

    public void a(final String str, final String str2, final Context context) {
        j.a(0, new Runnable() { // from class: com.xiaopeng.datalog.b.a.1
            @Override // java.lang.Runnable
            public void run() {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("vehicleId", String.valueOf(i.lp()));
                jsonObject.addProperty("message", str2);
                jsonObject.addProperty("md5", d.dH(str2));
                byte[] bytes = new Gson().toJson((JsonElement) jsonObject).getBytes();
                a.this.a(str, context);
                a.this.b(str, context);
                a.this.a(str, bytes, context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Context context) {
        File file = new File("/sdcard/Log/" + str + "/" + context.getPackageName());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, Context context) {
        File file = new File("/sdcard/Log/" + str + "/" + context.getPackageName());
        if (!file.exists()) {
            file.mkdirs();
        }
        String[] list = file.list(new FilenameFilter() { // from class: com.xiaopeng.datalog.b.a.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str2) {
                return str2.contains(str) && str2.endsWith(".log");
            }
        });
        if (list == null) {
            return;
        }
        Arrays.sort(list);
        ArrayList arrayList = new ArrayList();
        for (String str2 : list) {
            if (bj(str2)) {
                arrayList.add(str2);
                boolean delete = new File("/sdcard/Log/" + str + "/" + context.getPackageName() + "/" + str2).delete();
                StringBuilder sb = new StringBuilder();
                sb.append("file: ");
                sb.append(str2);
                sb.append(" is expired, deleted ");
                sb.append(delete);
                c.f("DataBackupHelper", sb.toString());
            }
        }
        if (arrayList.size() > 0) {
            a(arrayList, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, byte[] bArr, Context context) {
        RandomAccessFile randomAccessFile;
        File c = c(str, context);
        File file = new File(bk(c.getPath()));
        if (file.exists()) {
            c.g("DataBackupHelper", "zipFile exists, delete .zip and write .log");
            file.delete();
        }
        RandomAccessFile randomAccessFile2 = null;
        try {
            try {
                try {
                    randomAccessFile = new RandomAccessFile(c, "rw");
                    try {
                        randomAccessFile.seek(randomAccessFile.length());
                        randomAccessFile.write(bArr);
                        randomAccessFile.writeBytes("\n");
                        randomAccessFile.getFD().sync();
                        c.g("DataBackupHelper", "write " + c + " success, data.length:" + bArr.length);
                        randomAccessFile.close();
                    } catch (Exception e) {
                        e = e;
                        randomAccessFile2 = randomAccessFile;
                        c.b("DataBackupHelper", "write file fail!", e);
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (Exception e2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile = randomAccessFile2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Exception e4) {
        }
    }

    private boolean bj(String str) {
        int lastIndexOf = str.lastIndexOf("_");
        String str2 = str.substring(lastIndexOf - 8, lastIndexOf) + "_000000";
        String p = com.xiaopeng.lib.utils.a.p(System.currentTimeMillis());
        if (com.xiaopeng.lib.utils.a.dG(p.substring(0, 8) + "_000000") - com.xiaopeng.lib.utils.a.dG(str2) > 604800000) {
            c.f("DataBackupHelper", str + " EXPIRED!");
            return true;
        }
        return false;
    }

    private File c(final String str, Context context) {
        File[] listFiles = new File("/sdcard/Log/" + str + "/" + context.getPackageName()).listFiles(new FilenameFilter() { // from class: com.xiaopeng.datalog.b.a.3
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str2) {
                return str2.contains(str) && str2.contains(".log");
            }
        });
        if (listFiles == null || listFiles.length == 0) {
            c.f("DataBackupHelper", "create a new File");
            return d(str, context);
        }
        Arrays.sort(listFiles);
        File file = listFiles[listFiles.length - 1];
        if (!e(file) || file.length() >= 10485760) {
            return d(str, context);
        }
        return file;
    }

    private boolean e(File file) {
        String name = file.getName();
        String p = com.xiaopeng.lib.utils.a.p(System.currentTimeMillis());
        return name.contains(p.substring(0, p.indexOf("_")));
    }

    @NonNull
    private File d(String str, Context context) {
        String p = com.xiaopeng.lib.utils.a.p(System.currentTimeMillis());
        return new File("/sdcard/Log/" + str + "/" + context.getPackageName() + "/" + str + "_" + p + ".log");
    }

    private String bk(String str) {
        return str.replace(".log", ".zip");
    }

    private void a(List<String> list, String[] strArr) {
        String bl;
        int size = list.size();
        String bl2 = bl(list.get(0));
        if (strArr.length > size) {
            bl = bl(strArr[size]);
        } else {
            bl = bl(list.get(size - 1));
        }
        if (!com.xiaopeng.lib.utils.c.c.pq()) {
            IDataLog iDataLog = (IDataLog) Module.get(com.xiaopeng.datalog.a.class).get(IDataLog.class);
            iDataLog.sendStatData(iDataLog.buildStat().setEventName("data_expire").setProperty("startTime", bl2).setProperty("endTime", bl).build());
        }
    }

    private String bl(String str) {
        return str.substring(str.lastIndexOf("_") - 8, str.lastIndexOf("."));
    }
}
