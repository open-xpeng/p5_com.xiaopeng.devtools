package com.amap.api.services.a;

/* compiled from: Utils.java */
/* loaded from: classes11.dex */
public class bn {
    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    static byte[] a(com.amap.api.services.a.ax r4, java.lang.String r5) {
        /*
            r0 = 0
            byte[] r1 = new byte[r0]
            r2 = 0
            com.amap.api.services.a.ax$b r3 = r4.K(r5)     // Catch: java.lang.Throwable -> L6e
            if (r3 != 0) goto L1b
        Le:
            if (r3 == 0) goto L19
            r3.close()     // Catch: java.lang.Throwable -> L14
            goto L19
        L14:
            r4 = move-exception
            r4.printStackTrace()
            goto L1a
        L19:
        L1a:
            return r1
        L1b:
            java.io.InputStream r0 = r3.K(r0)     // Catch: java.lang.Throwable -> L69
            if (r0 != 0) goto L3a
            if (r0 == 0) goto L2c
            r0.close()     // Catch: java.lang.Throwable -> L27
            goto L2c
        L27:
            r4 = move-exception
            r4.printStackTrace()
            goto L2d
        L2c:
        L2d:
            if (r3 == 0) goto L38
            r3.close()     // Catch: java.lang.Throwable -> L33
            goto L38
        L33:
            r4 = move-exception
            r4.printStackTrace()
            goto L39
        L38:
        L39:
            return r1
        L3a:
            int r2 = r0.available()     // Catch: java.lang.Throwable -> L66
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L66
            r0.read(r2)     // Catch: java.lang.Throwable -> L60
            r4.c(r5)     // Catch: java.lang.Throwable -> L60
            if (r0 == 0) goto L52
            r0.close()     // Catch: java.lang.Throwable -> L4d
            goto L52
        L4d:
            r4 = move-exception
            r4.printStackTrace()
            goto L53
        L52:
        L53:
            if (r3 == 0) goto L5e
            r3.close()     // Catch: java.lang.Throwable -> L59
            goto L5e
        L59:
            r4 = move-exception
            r4.printStackTrace()
            goto L5f
        L5e:
        L5f:
            return r2
        L60:
            r4 = move-exception
            r1 = r2
            goto L67
        L63:
            r4 = move-exception
            r2 = r0
            goto L93
        L66:
            r4 = move-exception
        L67:
            r2 = r0
            goto L70
        L69:
            r4 = move-exception
            goto L70
        L6b:
            r4 = move-exception
            r3 = r2
            goto L93
        L6e:
            r4 = move-exception
            r3 = r2
        L70:
            java.lang.String r5 = "Utils"
            java.lang.String r0 = "readSingleLog"
            com.amap.api.services.a.o.a(r4, r5, r0)     // Catch: java.lang.Throwable -> L92
            if (r2 == 0) goto L83
            r2.close()     // Catch: java.lang.Throwable -> L7e
            goto L83
        L7e:
            r4 = move-exception
            r4.printStackTrace()
            goto L84
        L83:
        L84:
            if (r3 == 0) goto L8f
            r3.close()     // Catch: java.lang.Throwable -> L8a
            goto L8f
        L8a:
            r4 = move-exception
            r4.printStackTrace()
            goto L90
        L8f:
        L90:
            return r1
        L92:
            r4 = move-exception
        L93:
            if (r2 == 0) goto L9e
            r2.close()     // Catch: java.lang.Throwable -> L99
            goto L9e
        L99:
            r5 = move-exception
            r5.printStackTrace()
            goto L9f
        L9e:
        L9f:
            if (r3 == 0) goto Laa
            r3.close()     // Catch: java.lang.Throwable -> La5
            goto Laa
        La5:
            r5 = move-exception
            r5.printStackTrace()
            goto Lab
        Laa:
        Lab:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bn.a(com.amap.api.services.a.ax, java.lang.String):byte[]");
    }
}
