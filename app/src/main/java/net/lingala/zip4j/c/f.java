package net.lingala.zip4j.c;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: AsyncZipTask.java */
/* loaded from: classes13.dex */
public abstract class f<T> {
    private final ProgressMonitor aln;
    private final boolean alo;
    private final ExecutorService executorService;

    protected abstract void a(T t, ProgressMonitor progressMonitor) throws IOException;

    protected abstract long q(T t) throws ZipException;

    protected abstract ProgressMonitor.Task ue();

    public f(a aVar) {
        this.aln = aVar.aln;
        this.alo = aVar.alo;
        this.executorService = aVar.executorService;
    }

    public void execute(final T t) throws ZipException {
        if (this.alo && ProgressMonitor.State.BUSY.equals(this.aln.uc())) {
            throw new ZipException("invalid operation - Zip4j is in busy state");
        }
        uh();
        if (this.alo) {
            this.aln.K(q(t));
            this.executorService.execute(new Runnable() { // from class: net.lingala.zip4j.c.f.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        f.this.b(t, f.this.aln);
                    } catch (ZipException e) {
                    } catch (Throwable th) {
                        f.this.executorService.shutdown();
                        throw th;
                    }
                    f.this.executorService.shutdown();
                }
            });
            return;
        }
        b(t, this.aln);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(T t, ProgressMonitor progressMonitor) throws ZipException {
        try {
            a(t, progressMonitor);
            progressMonitor.ua();
        } catch (ZipException e) {
            progressMonitor.e(e);
            throw e;
        } catch (Exception e2) {
            progressMonitor.e(e2);
            throw new ZipException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ug() throws ZipException {
        if (!this.aln.ud()) {
            return;
        }
        this.aln.a(ProgressMonitor.Result.CANCELLED);
        this.aln.a(ProgressMonitor.State.READY);
        throw new ZipException("Task cancelled", ZipException.Type.TASK_CANCELLED_EXCEPTION);
    }

    private void uh() {
        this.aln.ub();
        this.aln.a(ProgressMonitor.State.BUSY);
        this.aln.a(ue());
    }

    /* compiled from: AsyncZipTask.java */
    /* loaded from: classes13.dex */
    public static class a {
        private final ProgressMonitor aln;
        private final boolean alo;
        private final ExecutorService executorService;

        public a(ExecutorService executorService, boolean z, ProgressMonitor progressMonitor) {
            this.executorService = executorService;
            this.alo = z;
            this.aln = progressMonitor;
        }
    }
}
