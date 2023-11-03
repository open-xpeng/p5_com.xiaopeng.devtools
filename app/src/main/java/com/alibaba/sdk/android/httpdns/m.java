package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class m implements Callable<String[]> {
    private static Context a;

    /* renamed from: a  reason: collision with other field name */
    private o f34a;
    private String hostName;
    private static d hostManager = d.a();

    /* renamed from: a  reason: collision with other field name */
    private static final Object f33a = new Object();
    private int d = 1;
    private String e = null;

    /* renamed from: e  reason: collision with other field name */
    private String[] f37e = f.d;

    /* renamed from: d  reason: collision with other field name */
    private boolean f36d = false;

    /* renamed from: d  reason: collision with other field name */
    private long f35d = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(String str, o oVar) {
        this.hostName = str;
        this.f34a = oVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setContext(Context context) {
        a = context;
    }

    public void a(int i) {
        if (i >= 0) {
            this.d = i;
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
    @Override // java.util.concurrent.Callable
    /* renamed from: b */
    public java.lang.String[] call() {
        /*
            Method dump skipped, instructions count: 1032
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.httpdns.m.call():java.lang.String[]");
    }
}
