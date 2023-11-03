package net.lingala.zip4j.c;

import java.io.File;
import java.io.IOException;
import java.util.List;
import net.lingala.zip4j.c.f;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.k;
import net.lingala.zip4j.model.p;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: AddFilesToZipTask.java */
/* loaded from: classes13.dex */
public class d extends net.lingala.zip4j.c.a<a> {
    public d(p pVar, char[] cArr, net.lingala.zip4j.headers.d dVar, f.a aVar) {
        super(pVar, cArr, dVar, aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.c.f
    public void a(a aVar, ProgressMonitor progressMonitor) throws IOException {
        e(aVar.apH);
        a(aVar.apG, progressMonitor, aVar.apH, aVar.anb);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.c.f
    /* renamed from: a */
    public long q(a aVar) throws ZipException {
        return b(aVar.apG, aVar.apH);
    }

    @Override // net.lingala.zip4j.c.a, net.lingala.zip4j.c.f
    protected ProgressMonitor.Task ue() {
        return super.ue();
    }

    /* compiled from: AddFilesToZipTask.java */
    /* loaded from: classes13.dex */
    public static class a extends c {
        private final List<File> apG;
        private final ZipParameters apH;

        public a(List<File> list, ZipParameters zipParameters, k kVar) {
            super(kVar);
            this.apG = list;
            this.apH = zipParameters;
        }
    }
}
