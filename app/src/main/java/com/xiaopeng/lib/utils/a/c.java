package com.xiaopeng.lib.utils.a;

import com.xiaopeng.lib.utils.j;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/* compiled from: EnvConfigHelper.java */
/* loaded from: classes12.dex */
public class c {
    private static String pc() {
        return new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date(System.currentTimeMillis() + 43200000));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    static void a(java.util.Properties r3, java.lang.String r4) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L7
            return
        L7:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L2d
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L2d
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2d
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L2d
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L28
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L28
            java.lang.String r0 = "pre env file"
            r3.store(r1, r0)     // Catch: java.lang.Throwable -> L25
            com.xiaopeng.lib.utils.b.closeQuietly(r1)
            goto L3c
        L22:
            r3 = move-exception
            r0 = r1
            goto L42
        L25:
            r3 = move-exception
            r0 = r1
            goto L2f
        L28:
            r3 = move-exception
            goto L2f
        L2a:
            r3 = move-exception
            r4 = r0
            goto L42
        L2d:
            r3 = move-exception
            r4 = r0
        L2f:
            java.lang.String r1 = "EnvConfig"
            java.lang.String r2 = "EnvConfigHelper.saveToPath error!"
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L41
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L41
            com.xiaopeng.lib.utils.b.closeQuietly(r0)
        L3c:
            com.xiaopeng.lib.utils.b.closeQuietly(r4)
            return
        L41:
            r3 = move-exception
        L42:
            com.xiaopeng.lib.utils.b.closeQuietly(r0)
            com.xiaopeng.lib.utils.b.closeQuietly(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.lib.utils.a.c.a(java.util.Properties, java.lang.String):void");
    }

    public static void pd() {
        final Properties pa = b.pa();
        j.a(0, new Runnable() { // from class: com.xiaopeng.lib.utils.a.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.a(pa, "/sdcard/pre_env.ini");
            }
        });
    }

    public static void g(String str, boolean z) {
        if (b.dL(str)) {
            b.removeKey(str);
            if (z) {
                pd();
            }
        }
    }

    public static void pe() {
        b.setString("main_host", "https://xmart.deploy-test.xiaopeng.com");
        b.setString("expired_time", pc());
        pd();
    }

    public static void pf() {
        g("main_host", true);
    }
}
