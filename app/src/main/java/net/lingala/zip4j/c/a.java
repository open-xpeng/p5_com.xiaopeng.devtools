package net.lingala.zip4j.c;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.b.b.h;
import net.lingala.zip4j.c.f;
import net.lingala.zip4j.c.g;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.model.i;
import net.lingala.zip4j.model.k;
import net.lingala.zip4j.model.p;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: AbstractAddFileToZipTask.java */
/* loaded from: classes13.dex */
public abstract class a<T> extends f<T> {
    private final p alm;
    private final char[] alp;
    private final net.lingala.zip4j.headers.d alq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(p pVar, char[] cArr, net.lingala.zip4j.headers.d dVar, f.a aVar) {
        super(aVar);
        this.alm = pVar;
        this.alp = cArr;
        this.alq = dVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(List<File> list, ProgressMonitor progressMonitor, ZipParameters zipParameters, k kVar) throws IOException {
        net.lingala.zip4j.d.c.a(list, zipParameters.tW());
        byte[] bArr = new byte[kVar.tq()];
        List<File> a = a(list, zipParameters, progressMonitor, kVar);
        h hVar = new h(this.alm.tF(), this.alm.sD());
        try {
            net.lingala.zip4j.b.b.k a2 = a(hVar, kVar);
            for (File file : a) {
                ug();
                ZipParameters a3 = a(zipParameters, file, progressMonitor);
                progressMonitor.setFileName(file.getAbsolutePath());
                if (net.lingala.zip4j.d.c.o(file) && g(a3)) {
                    a(file, a2, a3, hVar);
                    if (ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(a3.tW())) {
                    }
                }
                a(file, a2, a3, hVar, progressMonitor, bArr);
            }
            if (a2 != null) {
                a2.close();
            }
            hVar.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (th != null) {
                    try {
                        hVar.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                } else {
                    hVar.close();
                }
                throw th2;
            }
        }
    }

    private void a(File file, net.lingala.zip4j.b.b.k kVar, ZipParameters zipParameters, h hVar) throws IOException {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        zipParameters2.ft(at(zipParameters.tQ(), file.getName()));
        zipParameters2.bA(false);
        zipParameters2.a(CompressionMethod.STORE);
        kVar.b(zipParameters2);
        kVar.write(net.lingala.zip4j.d.c.p(file).getBytes());
        a(kVar, hVar, file, true);
    }

    private void a(File file, net.lingala.zip4j.b.b.k kVar, ZipParameters zipParameters, h hVar, ProgressMonitor progressMonitor, byte[] bArr) throws IOException {
        kVar.b(zipParameters);
        if (file.exists() && !file.isDirectory()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    kVar.write(bArr, 0, read);
                    progressMonitor.J(read);
                    ug();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (th != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        } else {
                            fileInputStream.close();
                        }
                        throw th2;
                    }
                }
            }
            fileInputStream.close();
        }
        a(kVar, hVar, file, false);
    }

    private void a(net.lingala.zip4j.b.b.k kVar, h hVar, File file, boolean z) throws IOException {
        i sH = kVar.sH();
        byte[] k = net.lingala.zip4j.d.c.k(file);
        if (!z) {
            k[3] = net.lingala.zip4j.d.a.c(k[3], 5);
        }
        sH.P(k);
        a(sH, hVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b(List<File> list, ZipParameters zipParameters) throws ZipException {
        long j = 0;
        for (File file : list) {
            if (file.exists()) {
                if (zipParameters.tJ() && zipParameters.sT() == EncryptionMethod.ZIP_STANDARD) {
                    j += file.length() * 2;
                } else {
                    j += file.length();
                }
                i a = net.lingala.zip4j.headers.c.a(uf(), net.lingala.zip4j.d.c.c(file, zipParameters));
                if (a != null) {
                    j += uf().tF().length() - a.getCompressedSize();
                }
            }
        }
        return j;
    }

    net.lingala.zip4j.b.b.k a(h hVar, k kVar) throws IOException {
        if (this.alm.tF().exists()) {
            hVar.seek(net.lingala.zip4j.headers.c.b(this.alm));
        }
        return new net.lingala.zip4j.b.b.k(hVar, this.alp, kVar, this.alm);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(ZipParameters zipParameters) throws ZipException {
        if (zipParameters == null) {
            throw new ZipException("cannot validate zip parameters");
        }
        if (zipParameters.sN() != CompressionMethod.STORE && zipParameters.sN() != CompressionMethod.DEFLATE) {
            throw new ZipException("unsupported compression type");
        }
        if (zipParameters.tJ()) {
            if (zipParameters.sT() == EncryptionMethod.NONE) {
                throw new ZipException("Encryption method has to be set, when encrypt files flag is set");
            }
            if (this.alp == null || this.alp.length <= 0) {
                throw new ZipException("input password is empty or null");
            }
            return;
        }
        zipParameters.a(EncryptionMethod.NONE);
    }

    void a(i iVar, h hVar) throws IOException {
        this.alq.a(iVar, uf(), hVar);
    }

    private ZipParameters a(ZipParameters zipParameters, File file, ProgressMonitor progressMonitor) throws IOException {
        ZipParameters zipParameters2 = new ZipParameters(zipParameters);
        zipParameters2.H(net.lingala.zip4j.d.f.M(file.lastModified()));
        if (file.isDirectory()) {
            zipParameters2.I(0L);
        } else {
            zipParameters2.I(file.length());
        }
        zipParameters2.bB(false);
        zipParameters2.H(file.lastModified());
        if (!net.lingala.zip4j.d.f.fy(zipParameters.tQ())) {
            zipParameters2.ft(net.lingala.zip4j.d.c.c(file, zipParameters));
        }
        if (file.isDirectory()) {
            zipParameters2.a(CompressionMethod.STORE);
            zipParameters2.a(EncryptionMethod.NONE);
            zipParameters2.bA(false);
        } else {
            if (zipParameters2.tJ() && zipParameters2.sT() == EncryptionMethod.ZIP_STANDARD) {
                progressMonitor.a(ProgressMonitor.Task.CALCULATE_CRC);
                zipParameters2.G(net.lingala.zip4j.d.b.a(file, progressMonitor));
                progressMonitor.a(ProgressMonitor.Task.ADD_ENTRY);
            }
            if (file.length() == 0) {
                zipParameters2.a(CompressionMethod.STORE);
            }
        }
        return zipParameters2;
    }

    private List<File> a(List<File> list, ZipParameters zipParameters, ProgressMonitor progressMonitor, k kVar) throws ZipException {
        ArrayList arrayList = new ArrayList(list);
        if (!this.alm.tF().exists()) {
            return arrayList;
        }
        for (File file : list) {
            if (!net.lingala.zip4j.d.f.fy(file.getName())) {
                arrayList.remove(file);
            }
            i a = net.lingala.zip4j.headers.c.a(this.alm, net.lingala.zip4j.d.c.c(file, zipParameters));
            if (a != null) {
                if (zipParameters.tU()) {
                    progressMonitor.a(ProgressMonitor.Task.REMOVE_ENTRY);
                    a(a, progressMonitor, kVar);
                    ug();
                    progressMonitor.a(ProgressMonitor.Task.ADD_ENTRY);
                } else {
                    arrayList.remove(file);
                }
            }
        }
        return arrayList;
    }

    void a(i iVar, ProgressMonitor progressMonitor, k kVar) throws ZipException {
        new g(this.alm, this.alq, new f.a(null, false, progressMonitor)).execute(new g.a(Collections.singletonList(iVar.getFileName()), kVar));
    }

    private String at(String str, String str2) {
        if (str.contains("/")) {
            return str.substring(0, str.lastIndexOf("/") + 1) + str2;
        }
        return str2;
    }

    private boolean g(ZipParameters zipParameters) {
        return ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY.equals(zipParameters.tW()) || ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE.equals(zipParameters.tW());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.c.f
    public ProgressMonitor.Task ue() {
        return ProgressMonitor.Task.ADD_ENTRY;
    }

    protected p uf() {
        return this.alm;
    }
}
