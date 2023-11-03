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

/* compiled from: AddFolderToZipTask.java */
/* loaded from: classes13.dex */
public class e extends net.lingala.zip4j.c.a<a> {
    public e(p pVar, char[] cArr, net.lingala.zip4j.headers.d dVar, f.a aVar) {
        super(pVar, cArr, dVar, aVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.c.f
    public void a(a aVar, ProgressMonitor progressMonitor) throws IOException {
        List<File> c = c(aVar);
        b(aVar);
        a(c, progressMonitor, aVar.apH, aVar.anb);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.c.f
    /* renamed from: a */
    public long q(a aVar) throws ZipException {
        List<File> a2 = net.lingala.zip4j.d.c.a(aVar.apI, aVar.apH.tL(), aVar.apH.tM(), aVar.apH.tX());
        if (aVar.apH.tN()) {
            a2.add(aVar.apI);
        }
        return b(a2, aVar.apH);
    }

    private void b(a aVar) throws IOException {
        String canonicalPath;
        File file = aVar.apI;
        if (aVar.apH.tN()) {
            if (file.getCanonicalFile().getParentFile() == null) {
                canonicalPath = file.getCanonicalPath();
            } else {
                canonicalPath = file.getCanonicalFile().getParentFile().getCanonicalPath();
            }
        } else {
            canonicalPath = file.getCanonicalPath();
        }
        aVar.apH.fs(canonicalPath);
    }

    private List<File> c(a aVar) throws ZipException {
        List<File> a2 = net.lingala.zip4j.d.c.a(aVar.apI, aVar.apH.tL(), aVar.apH.tM(), aVar.apH.tX());
        if (aVar.apH.tN()) {
            a2.add(aVar.apI);
        }
        return a2;
    }

    /* compiled from: AddFolderToZipTask.java */
    /* loaded from: classes13.dex */
    public static class a extends c {
        private final ZipParameters apH;
        private final File apI;

        public a(File file, ZipParameters zipParameters, k kVar) {
            super(kVar);
            this.apI = file;
            this.apH = zipParameters;
        }
    }
}
