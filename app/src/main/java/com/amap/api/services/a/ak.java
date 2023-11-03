package com.amap.api.services.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.amap.api.services.a.aq;
import com.amap.api.services.a.av;
import com.amap.api.services.a.bd;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.List;

/* compiled from: DexDownLoad.java */
/* loaded from: classes11.dex */
public class ak extends Thread implements bd.a {
    private String d;
    private al eX;
    private bd eY;
    private l eZ;
    private RandomAccessFile fa;
    private Context fb;

    private void a(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.clear();
        edit.commit();
    }

    public ak(Context context, al alVar, l lVar) {
        try {
            this.fb = context.getApplicationContext();
            this.eZ = lVar;
            if (alVar == null) {
                return;
            }
            this.eX = alVar;
            this.eY = new bd(new ap(this.eX));
            this.d = aq.e(context, this.eX.a);
        } catch (Throwable th) {
            aw.a(th, "DexDownLoad", "DexDownLoad()");
        }
    }

    public void a() {
        try {
            start();
        } catch (Throwable th) {
            aw.a(th, "DexDownLoad", "startDownload()");
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            if (g()) {
                this.eY.a(this);
            }
        } catch (Throwable th) {
            aw.a(th, "DexDownLoad", "run()");
        }
    }

    private boolean a(x xVar, av avVar, al alVar) {
        String str = alVar.b;
        String str2 = alVar.c;
        String str3 = alVar.d;
        String str4 = alVar.e;
        if ("errorstatus".equals(avVar.e())) {
            if (new File(aq.c(this.fb, this.eZ.a(), this.eZ.b())).exists() || TextUtils.isEmpty(aq.a(this.fb, xVar, this.eZ))) {
                return true;
            }
            try {
                ao.bq().e(this.fb, this.eZ);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return true;
        } else if (!new File(this.d).exists()) {
            return false;
        } else {
            List b = xVar.b(av.a(aq.a(this.fb, str, str2), str, str2, str3), av.class);
            if (b == null || b.size() <= 0) {
                try {
                    aq.a(this.fb, xVar, this.eZ, new av.a(aq.a(this.fb, str, this.eZ.b()), str4, str, str2, str3).J("used").bt(), this.d);
                    ao.bq().e(this.fb, this.eZ);
                } catch (Throwable th2) {
                    aw.a(th2, "DexDownLoad", "processDownloadedFile()");
                }
                return true;
            }
            return true;
        }
    }

    private boolean d() {
        x xVar = new x(this.fb, at.br());
        try {
            List<av> a = aq.a.a(xVar, this.eX.b, "used");
            if (a != null && a.size() > 0) {
                if (aw.q(a.get(0).d(), this.eX.d) > 0) {
                    return true;
                }
            }
        } catch (Throwable th) {
            aw.a(th, "DexDownLoad", "isDownloaded()");
        }
        av a2 = aq.a.a(xVar, this.eX.a);
        if (a2 == null) {
            return false;
        }
        return a(xVar, a2, this.eX);
    }

    private boolean e() {
        return this.eZ != null && this.eZ.a().equals(this.eX.b) && this.eZ.b().equals(this.eX.c);
    }

    private boolean f() {
        return Build.VERSION.SDK_INT >= this.eX.g && Build.VERSION.SDK_INT <= this.eX.f;
    }

    private boolean a(Context context) {
        return g.s(context) == 1;
    }

    private boolean g() {
        try {
            if (!e() || d() || !f() || !a(this.fb)) {
                return false;
            }
            aq.a(this.fb, this.eZ.a());
            return true;
        } catch (Throwable th) {
            aw.a(th, "DexDownLoad", "isNeedDownload()");
            return false;
        }
    }

    @Override // com.amap.api.services.a.bd.a
    public void a(byte[] bArr, long j) {
        try {
            if (this.fa == null) {
                File file = new File(this.d);
                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                this.fa = new RandomAccessFile(file, "rw");
            }
            this.fa.seek(j);
            this.fa.write(bArr);
        } catch (Throwable th) {
            aw.a(th, "DexDownLoad", "onDownload()");
        }
    }

    @Override // com.amap.api.services.a.bd.a
    public void a(Throwable th) {
        try {
            if (this.fa == null) {
                return;
            }
            this.fa.close();
        } catch (Throwable th2) {
            aw.a(th2, "DexDownLoad", "onException()");
        }
    }

    @Override // com.amap.api.services.a.bd.a
    public void b() {
        try {
            if (this.fa == null) {
                return;
            }
            this.fa.close();
            String b = this.eX.b();
            if (aq.a(this.d, b)) {
                String c = this.eX.c();
                x xVar = new x(this.fb, at.br());
                aq.a.a(xVar, new av.a(this.eX.a, b, this.eX.b, c, this.eX.d).J("copy").bt(), av.a(this.eX.a, this.eX.b, c, this.eX.d));
                a(this.fb, this.eX.b);
                aq.a(this.fb, xVar, this.eZ, new av.a(aq.a(this.fb, this.eX.b, this.eZ.b()), b, this.eX.b, c, this.eX.d).J("used").bt(), this.d);
                ao.bq().e(this.fb, this.eZ);
                return;
            }
            new File(this.d).delete();
        } catch (Throwable th) {
            aw.a(th, "DexDownLoad", "onFinish()");
        }
    }

    @Override // com.amap.api.services.a.bd.a
    public void c() {
    }
}
