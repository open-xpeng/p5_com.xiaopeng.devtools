package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.a.av;
import java.io.File;
import java.util.List;

/* compiled from: DexFileManager.java */
/* loaded from: classes11.dex */
public class aq {

    /* compiled from: DexFileManager.java */
    /* loaded from: classes11.dex */
    public static class a {
        public static void a(x xVar, av avVar, String str) {
            xVar.c(avVar, str);
        }

        public static av a(x xVar, String str) {
            List b = xVar.b(av.b(str), av.class);
            if (b != null && b.size() > 0) {
                return (av) b.get(0);
            }
            return null;
        }

        public static List<av> a(x xVar, String str, String str2) {
            return xVar.b(av.b(str, str2), av.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str) {
        return str + ".o";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context, String str, String str2) {
        String w = g.w(context);
        return j.b(str + str2 + w) + ".png";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(Context context, String str, String str2) {
        return e(context, a(context, str, str2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, String str) {
        x xVar = new x(context, at.br());
        List<av> a2 = a.a(xVar, str, "copy");
        a(a2);
        if (a2 != null) {
            if (a2.size() > 1) {
                int size = a2.size();
                for (int i = 1; i < size; i++) {
                    b(context, xVar, a2.get(i).a());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(Context context, String str) {
        return a(context) + File.separator + str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context) {
        return context.getFilesDir().getAbsolutePath() + File.separator + "pngex";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(String str, String str2) {
        String a2 = j.a(str);
        if (a2 == null || !a2.equalsIgnoreCase(str2)) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, x xVar, String str) {
        b(context, xVar, str);
        b(context, xVar, a(str));
    }

    static void b(Context context, x xVar, String str) {
        File file = new File(e(context, str));
        if (file.exists()) {
            file.delete();
        }
        xVar.a(av.b(str), av.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.amap.api.services.a.aq$1] */
    public static void d(final Context context, final String str, final String str2) {
        new Thread() { // from class: com.amap.api.services.a.aq.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    x xVar = new x(context, at.br());
                    List<av> b = xVar.b(av.a(str), av.class);
                    if (b != null && b.size() > 0) {
                        for (av avVar : b) {
                            if (!str2.equalsIgnoreCase(avVar.c())) {
                                aq.b(context, xVar, avVar.a());
                            }
                        }
                    }
                } catch (Throwable th) {
                    o.a(th, "DexFileManager", "clearUnSuitableVersion");
                }
            }
        }.start();
    }

    public static void a(x xVar, Context context, String str) {
        List<av> a2 = a.a(xVar, str, "used");
        if (a2 != null && a2.size() > 0) {
            for (av avVar : a2) {
                if (avVar != null) {
                    a(context, xVar, avVar.a());
                    List b = xVar.b(av.a(str, avVar.d()), av.class);
                    if (b != null && b.size() > 0) {
                        av avVar2 = (av) b.get(0);
                        avVar2.c("errorstatus");
                        a.a(xVar, avVar2, av.b(avVar2.a()));
                        File file = new File(e(context, avVar2.a()));
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    static void a(android.content.Context r3, com.amap.api.services.a.x r4, com.amap.api.services.a.l r5, com.amap.api.services.a.av r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String r1 = r5.a()     // Catch: java.lang.Throwable -> L7b
            java.lang.String r2 = r6.a()     // Catch: java.lang.Throwable -> L7b
            a(r3, r4, r2)     // Catch: java.lang.Throwable -> L7b
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L7b
            r2.<init>(r7)     // Catch: java.lang.Throwable -> L7b
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L7b
            r7.<init>(r2)     // Catch: java.lang.Throwable -> L7b
            r2 = 32
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L74
            r7.read(r2)     // Catch: java.lang.Throwable -> L74
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L74
            java.lang.String r5 = r5.b()     // Catch: java.lang.Throwable -> L74
            java.lang.String r3 = c(r3, r1, r5)     // Catch: java.lang.Throwable -> L74
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L74
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L74
            r5 = 1
            r3.<init>(r2, r5)     // Catch: java.lang.Throwable -> L74
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L70
        L38:
            int r0 = r7.read(r5)     // Catch: java.lang.Throwable -> L70
            if (r0 <= 0) goto L43
            r1 = 0
            r3.write(r5, r1, r0)     // Catch: java.lang.Throwable -> L70
            goto L38
        L43:
            java.lang.String r5 = r2.getAbsolutePath()     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = com.amap.api.services.a.j.a(r5)     // Catch: java.lang.Throwable -> L70
            r6.d(r5)     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = r6.a()     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = com.amap.api.services.a.av.b(r5)     // Catch: java.lang.Throwable -> L70
            com.amap.api.services.a.aq.a.a(r4, r6, r5)     // Catch: java.lang.Throwable -> L70
            r7.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r4 = move-exception
            r4.printStackTrace()
        L63:
            r3.close()     // Catch: java.io.IOException -> L68
        L67:
            goto L6d
        L68:
            r3 = move-exception
            r3.printStackTrace()
            goto L67
        L6d:
            return
        L6e:
            r4 = move-exception
            goto L80
        L70:
            r4 = move-exception
            goto L76
        L72:
            r4 = move-exception
            goto L81
        L74:
            r4 = move-exception
            r3 = r0
        L76:
            r0 = r7
            goto L7d
        L78:
            r4 = move-exception
            r7 = r0
            goto L81
        L7b:
            r4 = move-exception
            r3 = r0
        L7d:
            throw r4     // Catch: java.lang.Throwable -> L7e
        L7e:
            r4 = move-exception
            r7 = r0
        L80:
            r0 = r3
        L81:
            if (r7 == 0) goto L8b
            r7.close()     // Catch: java.io.IOException -> L87
            goto L8b
        L87:
            r3 = move-exception
            r3.printStackTrace()
        L8b:
            if (r0 == 0) goto L95
            r0.close()     // Catch: java.io.IOException -> L91
            goto L95
        L91:
            r3 = move-exception
            r3.printStackTrace()
        L95:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.aq.a(android.content.Context, com.amap.api.services.a.x, com.amap.api.services.a.l, com.amap.api.services.a.av, java.lang.String):void");
    }

    static boolean a(Context context, x xVar, String str, l lVar) {
        return a(xVar, str, e(context, str), lVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(x xVar, String str, String str2, l lVar) {
        av a2 = a.a(xVar, str);
        if (a2 == null || !lVar.b().equals(a2.c()) || !a(str2, a2.b())) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context, x xVar, l lVar) {
        List b = xVar.b(av.b(lVar.a(), "copy"), av.class);
        if (b == null || b.size() == 0) {
            return null;
        }
        a(b);
        for (int i = 0; i < b.size(); i++) {
            av avVar = (av) b.get(i);
            if (a(context, xVar, avVar.a(), lVar)) {
                try {
                    a(context, xVar, lVar, new av.a(a(context, lVar.a(), lVar.b()), avVar.b(), lVar.a(), lVar.b(), avVar.d()).J("used").bt(), e(context, avVar.a()));
                    return avVar.d();
                } catch (Throwable th) {
                    o.a(th, "DexFileManager", "loadAvailableDynamicSDKFile");
                }
            } else {
                b(context, xVar, avVar.a());
            }
        }
        return null;
    }

    static void a(List<av> list) {
        int i = 0;
        while (i < list.size() - 1) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                av avVar = list.get(i);
                av avVar2 = list.get(i3);
                if (aw.q(avVar2.d(), avVar.d()) > 0) {
                    list.set(i, avVar2);
                    list.set(i3, avVar);
                }
            }
            i = i2;
        }
    }
}
