package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.services.a.aq;
import dalvik.system.DexFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseClassLoader.java */
/* loaded from: classes11.dex */
public abstract class an extends ClassLoader {
    protected final Context a;
    protected final Map<String, Class<?>> b;
    volatile boolean d;
    protected String f;
    protected DexFile fc;
    protected l fd;

    abstract void a(File file, String str, String str2, x xVar);

    public an(Context context, l lVar, boolean z) {
        super(context.getClassLoader());
        this.b = new HashMap();
        this.fc = null;
        this.d = true;
        this.a = context;
        this.fd = lVar;
    }

    public boolean a() {
        return this.fc != null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Context context, l lVar) {
        String c = aq.c(context, lVar.a(), lVar.b());
        String a = aq.a(context);
        if (TextUtils.isEmpty(c) || TextUtils.isEmpty(a)) {
            return;
        }
        as.f(context, lVar);
        try {
            File file = new File(c);
            File parentFile = file.getParentFile();
            if (!file.exists()) {
                if (parentFile != null && parentFile.exists()) {
                    aq.d(context, lVar.a(), lVar.b());
                    return;
                }
                return;
            }
            String str = a + File.separator + aq.a(file.getName());
            DexFile loadDex = DexFile.loadDex(c, str, 0);
            if (loadDex != null) {
                av a2 = aq.a.a(new x(context, at.br()), file.getName());
                if (a2 != null) {
                    this.f = a2.d();
                }
                loadDex.close();
                a(new File(str), str, this.f, new x(context, at.br()));
            }
        } catch (Throwable th) {
            aw.a(th, "BaseClassLoader", "getInstanceByThread()");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        try {
            this.b.clear();
            if (this.fc != null) {
                this.fc.close();
            }
        } catch (Throwable th) {
            aw.a(th, "BaseClassLoader", "releaseDexFile()");
        }
    }
}
