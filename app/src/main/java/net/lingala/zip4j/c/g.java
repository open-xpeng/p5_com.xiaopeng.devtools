package net.lingala.zip4j.c;

import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.c.f;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.i;
import net.lingala.zip4j.model.k;
import net.lingala.zip4j.model.p;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: RemoveFilesFromZipTask.java */
/* loaded from: classes13.dex */
public class g extends b<a> {
    private final p alm;
    private final net.lingala.zip4j.headers.d alq;

    public g(p pVar, net.lingala.zip4j.headers.d dVar, f.a aVar) {
        super(aVar);
        this.alm = pVar;
        this.alq = dVar;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.c.f
    public void a(net.lingala.zip4j.c.g.a r24, net.lingala.zip4j.progress.ProgressMonitor r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.c.g.a(net.lingala.zip4j.c.g$a, net.lingala.zip4j.progress.ProgressMonitor):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.c.f
    /* renamed from: a */
    public long q(a aVar) {
        return this.alm.tF().length();
    }

    private List<String> F(List<String> list) throws ZipException {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (net.lingala.zip4j.headers.c.a(this.alm, str) != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(net.lingala.zip4j.model.i r4, java.util.List<java.lang.String> r5) {
        /*
            r3 = this;
            java.util.Iterator r5 = r5.iterator()
        L4:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L30
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "/"
            boolean r1 = r0.endsWith(r1)
            r2 = 1
            if (r1 == 0) goto L24
            java.lang.String r1 = r4.getFileName()
            boolean r1 = r1.startsWith(r0)
            if (r1 == 0) goto L24
            return r2
        L24:
            java.lang.String r1 = r4.getFileName()
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L2f
            return r2
        L2f:
            goto L4
        L30:
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: net.lingala.zip4j.c.g.a(net.lingala.zip4j.model.i, java.util.List):boolean");
    }

    private void a(List<i> list, i iVar, long j) throws ZipException {
        a(list, this.alm, iVar, L(j));
        net.lingala.zip4j.model.f tD = this.alm.tD();
        tD.v(tD.tf() - j);
        tD.fj(tD.te() - 1);
        if (tD.td() > 0) {
            tD.fi(tD.td() - 1);
        }
        if (this.alm.tI()) {
            this.alm.tH().E(this.alm.tH().tz() - j);
            this.alm.tH().B(this.alm.tH().tx() - 1);
            this.alm.tG().z(this.alm.tG().ts() - j);
        }
    }

    private long L(long j) {
        if (j == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }
        return -j;
    }

    @Override // net.lingala.zip4j.c.f
    protected ProgressMonitor.Task ue() {
        return ProgressMonitor.Task.REMOVE_ENTRY;
    }

    /* compiled from: RemoveFilesFromZipTask.java */
    /* loaded from: classes13.dex */
    public static class a extends c {
        private final List<String> apL;

        static /* synthetic */ List b(a aVar) {
            return aVar.apL;
        }

        public a(List<String> list, k kVar) {
            super(kVar);
            this.apL = list;
        }
    }
}
