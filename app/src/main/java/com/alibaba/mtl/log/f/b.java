package com.alibaba.mtl.log.f;

import com.alibaba.mtl.log.d.a;
import com.alibaba.mtl.log.d.e;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.l;
import com.alibaba.mtl.log.d.n;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* compiled from: UploadTask.java */
/* loaded from: classes11.dex */
public abstract class b implements Runnable {
    private static volatile boolean ee = false;
    private static boolean eg = false;
    static int B = 0;
    int C = -1;
    float eh = 200.0f;
    int D = 0;

    public abstract void bc();

    public abstract void bd();

    @Override // java.lang.Runnable
    public void run() {
        try {
            be();
            bc();
        } catch (Throwable th) {
        }
    }

    public static boolean isRunning() {
        return ee;
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x0200, code lost:
        com.alibaba.mtl.log.f.b.ee = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0203, code lost:
        com.alibaba.mtl.log.f.b.ee = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void be() {
        /*
            Method dump skipped, instructions count: 525
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.mtl.log.f.b.be():void");
    }

    private int e(List<com.alibaba.mtl.log.model.a> list) {
        if (list == null) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = list.get(i2).X;
            if (str != null && "6005".equalsIgnoreCase(str.toString())) {
                i++;
            }
        }
        return i;
    }

    private int bf() {
        if (this.C == -1) {
            String aS = l.aS();
            if ("wifi".equalsIgnoreCase(aS)) {
                this.C = 20;
            } else if ("4G".equalsIgnoreCase(aS)) {
                this.C = 16;
            } else if ("3G".equalsIgnoreCase(aS)) {
                this.C = 12;
            } else {
                this.C = 8;
            }
        }
        return this.C;
    }

    private a.C0014a a(String str, Map<String, Object> map) {
        if (str != null) {
            byte[] bArr = e.a(2, str, map, false).data;
            i.a("UploadTask", "url:", str);
            if (bArr != null) {
                String str2 = null;
                try {
                    str2 = new String(bArr, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (str2 != null) {
                    i.a("UploadTask", "result:", str2);
                    return com.alibaba.mtl.log.d.a.y(str2);
                }
            }
        }
        return a.C0014a.cS;
    }

    private int a(Boolean bool, long j) {
        if (j < 0) {
            return this.C;
        }
        float f = this.D / ((float) j);
        if (bool.booleanValue()) {
            if (j > 45000) {
                return this.C;
            }
            this.C = (int) (((45000.0f * f) / this.eh) - B);
        } else {
            this.C /= 2;
            B++;
        }
        if (this.C < 1) {
            this.C = 1;
            B = 0;
        } else if (this.C > 350) {
            this.C = 350;
        }
        i.a("UploadTask", "winsize:", Integer.valueOf(this.C));
        return this.C;
    }

    private Map<String, Object> f(List<com.alibaba.mtl.log.model.a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            List<String> b = b(list.get(i));
            if (b != null) {
                for (int i2 = 0; i2 < b.size(); i2++) {
                    StringBuilder sb = (StringBuilder) hashMap.get(b.get(i2));
                    if (sb == null) {
                        sb = new StringBuilder();
                        hashMap.put(b.get(i2), sb);
                    } else {
                        sb.append("\n");
                    }
                    sb.append(list.get(i).i());
                }
            }
        }
        HashMap hashMap2 = new HashMap();
        this.D = 0;
        for (String str : hashMap.keySet()) {
            byte[] a = a(((StringBuilder) hashMap.get(str)).toString());
            hashMap2.put(str, a);
            this.D += a.length;
        }
        this.eh = this.D / list.size();
        i.a("UploadTask", "averagePackageSize:", Float.valueOf(this.eh));
        return hashMap2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] a(String str) {
        GZIPOutputStream gZIPOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String str2 = null;
        GZIPOutputStream gZIPOutputStream2 = null;
        try {
            try {
                try {
                    gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream.write(str.getBytes("UTF-8"));
                        gZIPOutputStream.flush();
                        gZIPOutputStream.close();
                    } catch (IOException e) {
                        e = e;
                        gZIPOutputStream2 = gZIPOutputStream;
                        e.printStackTrace();
                        if (gZIPOutputStream2 != null) {
                            gZIPOutputStream2.close();
                        }
                        str2 = "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK";
                        byte[] b = n.b(byteArrayOutputStream.toByteArray(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK");
                        byteArrayOutputStream.close();
                        return b;
                    } catch (Throwable th) {
                        th = th;
                        if (gZIPOutputStream != null) {
                            try {
                                gZIPOutputStream.close();
                            } catch (Exception e2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (Exception e4) {
            }
            str2 = "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK";
            byte[] b2 = n.b(byteArrayOutputStream.toByteArray(), "QrMgt8GGYI6T52ZY5AnhtxkLzb8egpFn3j5JELI8H6wtACbUnZ5cc3aYTsTRbmkAkRJeYbtx92LPBWm7nBO9UIl7y5i5MQNmUZNf5QENurR5tGyo7yJ2G0MBjWvy6iAtlAbacKP0SwOUeUWx5dsBdyhxa7Id1APtybSdDgicBDuNjI0mlZFUzZSS9dmN8lBD0WTVOMz0pRZbR3cysomRXOO1ghqjJdTcyDIxzpNAEszN8RMGjrzyU7Hjbmwi6YNK");
            try {
                byteArrayOutputStream.close();
            } catch (Exception e5) {
            }
            return b2;
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = str2;
        }
    }

    private List<String> b(com.alibaba.mtl.log.model.a aVar) {
        return com.alibaba.mtl.log.a.a.s(aVar.X);
    }
}
