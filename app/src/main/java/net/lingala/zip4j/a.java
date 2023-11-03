package net.lingala.zip4j;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.lingala.zip4j.c.d;
import net.lingala.zip4j.c.e;
import net.lingala.zip4j.c.f;
import net.lingala.zip4j.d.c;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.b;
import net.lingala.zip4j.headers.d;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.RandomAccessFileMode;
import net.lingala.zip4j.model.k;
import net.lingala.zip4j.model.p;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: ZipFile.java */
/* loaded from: classes13.dex */
public class a implements Closeable {
    private File alk;
    private p alm;
    private ProgressMonitor aln;
    private boolean alo;
    private char[] alp;
    private d alq;
    private ThreadFactory alr;
    private int als;
    private List<InputStream> alt;
    private Charset charset;
    private ExecutorService executorService;

    public a(String str) {
        this(new File(str), (char[]) null);
    }

    public a(String str, char[] cArr) {
        this(new File(str), cArr);
    }

    public a(File file, char[] cArr) {
        this.alq = new d();
        this.charset = null;
        this.als = 4096;
        this.alt = new ArrayList();
        if (file == null) {
            throw new IllegalArgumentException("input zip file parameter is null");
        }
        this.alk = file;
        this.alp = cArr;
        this.alo = false;
        this.aln = new ProgressMonitor();
    }

    public void a(File file, ZipParameters zipParameters) throws ZipException {
        a(Collections.singletonList(file), zipParameters);
    }

    public void a(List<File> list, ZipParameters zipParameters) throws ZipException {
        if (list == null || list.size() == 0) {
            throw new ZipException("input file List is null or empty");
        }
        if (zipParameters == null) {
            throw new ZipException("input parameters are null");
        }
        sk();
        if (this.alm == null) {
            throw new ZipException("internal error: zip model is null");
        }
        if (this.alk.exists() && this.alm.tE()) {
            throw new ZipException("Zip file already exists. Zip file format does not allow updating split/spanned files");
        }
        new net.lingala.zip4j.c.d(this.alm, this.alp, this.alq, sn()).execute(new d.a(list, zipParameters, so()));
    }

    public void b(File file, ZipParameters zipParameters) throws ZipException {
        if (file == null) {
            throw new ZipException("input path is null, cannot add folder to zip file");
        }
        if (!file.exists()) {
            throw new ZipException("folder does not exist");
        }
        if (!file.isDirectory()) {
            throw new ZipException("input folder is not a directory");
        }
        if (!file.canRead()) {
            throw new ZipException("cannot read input folder");
        }
        if (zipParameters == null) {
            throw new ZipException("input parameters are null, cannot add folder to zip file");
        }
        a(file, zipParameters, true);
    }

    private void a(File file, ZipParameters zipParameters, boolean z) throws ZipException {
        sk();
        if (this.alm == null) {
            throw new ZipException("internal error: zip model is null");
        }
        if (z && this.alm.tE()) {
            throw new ZipException("This is a split archive. Zip file format does not allow updating split/spanned files");
        }
        new e(this.alm, this.alp, this.alq, sn()).execute(new e.a(file, zipParameters, so()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        for (InputStream inputStream : this.alt) {
            inputStream.close();
        }
        this.alt.clear();
    }

    private void sk() throws ZipException {
        if (this.alm != null) {
            return;
        }
        if (!this.alk.exists()) {
            sl();
        } else if (!this.alk.canRead()) {
            throw new ZipException("no read access for the input zip file");
        } else {
            try {
                RandomAccessFile sm = sm();
                try {
                    this.alm = new b().a(sm, so());
                    this.alm.j(this.alk);
                    if (sm != null) {
                        sm.close();
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (sm != null) {
                            if (th != null) {
                                try {
                                    sm.close();
                                } catch (Throwable th3) {
                                    th.addSuppressed(th3);
                                }
                            } else {
                                sm.close();
                            }
                        }
                        throw th2;
                    }
                }
            } catch (ZipException e) {
                throw e;
            } catch (IOException e2) {
                throw new ZipException(e2);
            }
        }
    }

    private void sl() {
        this.alm = new p();
        this.alm.j(this.alk);
    }

    private RandomAccessFile sm() throws IOException {
        if (c.l(this.alk)) {
            net.lingala.zip4j.b.a.a aVar = new net.lingala.zip4j.b.a.a(this.alk, RandomAccessFileMode.READ.getValue(), c.n(this.alk));
            aVar.sy();
            return aVar;
        }
        return new RandomAccessFile(this.alk, RandomAccessFileMode.READ.getValue());
    }

    private f.a sn() {
        if (this.alo) {
            if (this.alr == null) {
                this.alr = Executors.defaultThreadFactory();
            }
            this.executorService = Executors.newSingleThreadExecutor(this.alr);
        }
        return new f.a(this.executorService, this.alo, this.aln);
    }

    public String toString() {
        return this.alk.toString();
    }

    private k so() {
        return new k(this.charset, this.als);
    }
}
