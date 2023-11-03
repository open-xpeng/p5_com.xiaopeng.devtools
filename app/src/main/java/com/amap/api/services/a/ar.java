package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.a.aq;
import com.amap.api.services.a.av;
import dalvik.system.DexFile;
import java.io.File;
import java.util.Date;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DynamicClassLoader.java */
/* loaded from: classes11.dex */
public class ar extends an {
    public void a(String str, String str2) throws Exception {
        try {
            if (this.fc != null) {
                return;
            }
            b();
            this.fc = DexFile.loadDex(str, str2, 0);
        } catch (Exception e) {
            aw.a(e, "DynamicClassLoader", "loadDexFile()");
            throw new Exception("load dex fail");
        }
    }

    private boolean a(x xVar, l lVar, String str) {
        return aq.a(xVar, aq.a(this.a, lVar.a(), lVar.b()), str, lVar);
    }

    private boolean b(x xVar, String str, String str2) {
        String e = aq.e(this.a, str);
        if (aq.a(xVar, str, e, this.fd)) {
            return true;
        }
        if (aq.a.a(xVar, str) != null) {
            return false;
        }
        if (!TextUtils.isEmpty(this.f)) {
            aq.a.a(xVar, new av.a(str, j.a(e), this.fd.a(), this.fd.b(), str2).J("useod").bt(), av.b(str));
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r7v4, types: [com.amap.api.services.a.ar$1] */
    public ar(final Context context, l lVar, boolean z) throws Exception {
        super(context, lVar, z);
        final String c = aq.c(context, lVar.a(), lVar.b());
        final String a = aq.a(context);
        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(a)) {
            throw new Exception("dexPath or dexOutputDir is null.");
        }
        File file = new File(c);
        File parentFile = file.getParentFile();
        if (!file.exists()) {
            if (parentFile != null && parentFile.exists()) {
                aq.d(context, lVar.a(), lVar.b());
            }
            throw new Exception("file not exist!");
        } else if (z) {
            a(c, a + File.separator + aq.a(file.getName()));
            new Thread() { // from class: com.amap.api.services.a.ar.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        ar.this.b(context, c, a);
                    } catch (Throwable th) {
                        aw.a(th, "DynamicClassLoader", "run()");
                    }
                }
            }.start();
        }
    }

    @Override // java.lang.ClassLoader
    protected Class<?> findClass(String str) throws ClassNotFoundException {
        try {
            if (this.fc == null) {
                throw new ClassNotFoundException(str);
            }
            Class<?> cls = this.b.get(str);
            if (cls != null) {
                return cls;
            }
            Class<?> loadClass = this.fc.loadClass(str, this);
            if (loadClass == null) {
                throw new ClassNotFoundException(str);
            }
            this.b.put(str, loadClass);
            return loadClass;
        } catch (Throwable th) {
            aw.a(th, "DynamicClassLoader", "findCl");
            throw new ClassNotFoundException(str);
        }
    }

    void b(Context context, String str, String str2) throws Exception {
        x xVar;
        File file;
        new Date().getTime();
        try {
            xVar = new x(context, at.br());
            file = new File(str);
            av a = aq.a.a(xVar, file.getName());
            if (a != null) {
                this.f = a.d();
            }
            if (!a(xVar, this.fd, file.getAbsolutePath())) {
                this.d = false;
                aq.a(this.a, xVar, file.getName());
                String a2 = aq.a(this.a, xVar, this.fd);
                if (!TextUtils.isEmpty(a2)) {
                    this.f = a2;
                    c(this.a, this.fd);
                }
            }
        } catch (Throwable th) {
            aw.a(th, "DynamicClassLoader", "verifyDynamicSDK()");
        }
        if (!file.exists()) {
            return;
        }
        if (new File(str2 + File.separator + aq.a(file.getName())).exists() && !b(xVar, aq.a(file.getName()), this.f)) {
            c(this.a, this.fd);
        }
        new Date().getTime();
    }

    @Override // com.amap.api.services.a.an
    protected void a(File file, String str, String str2, x xVar) {
        if (!TextUtils.isEmpty(this.f) || !file.exists()) {
            String a = j.a(str);
            String name = file.getName();
            aq.a.a(xVar, new av.a(name, a, this.fd.a(), this.fd.b(), str2).J("useod").bt(), av.b(name));
        }
    }
}
