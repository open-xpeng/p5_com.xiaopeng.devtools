package net.lingala.zip4j.c;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import net.lingala.zip4j.c.f;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.i;
import net.lingala.zip4j.model.p;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: AbstractModifyFileTask.java */
/* loaded from: classes13.dex */
abstract class b<T> extends f<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public b(f.a aVar) {
        super(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public File fu(String str) {
        Random random = new Random();
        File file = new File(str + random.nextInt(com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r));
        while (file.exists()) {
            file = new File(str + random.nextInt(com.alibaba.sdk.android.man.crashreporter.a.a.a.b.a.r));
        }
        return file;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(List<i> list, p pVar, i iVar, long j) throws ZipException {
        int a = a(list, iVar);
        if (a == -1) {
            throw new ZipException("Could not locate modified file header in zipModel");
        }
        while (true) {
            a++;
            if (a < list.size()) {
                i iVar2 = list.get(a);
                iVar2.y(iVar2.tl() + j);
                if (pVar.tI() && iVar2.sV() != null && iVar2.sV().tl() != -1) {
                    iVar2.sV().y(iVar2.sV().tl() + j);
                }
            } else {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z, File file, File file2) throws ZipException {
        if (z) {
            c(file, file2);
        } else if (!file2.delete()) {
            throw new ZipException("Could not delete temporary file");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor, int i) throws IOException {
        net.lingala.zip4j.d.c.b(randomAccessFile, outputStream, j, j + j2, progressMonitor, i);
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<i> E(List<i> list) {
        ArrayList arrayList = new ArrayList(list);
        Collections.sort(arrayList, new Comparator<i>() { // from class: net.lingala.zip4j.c.b.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(i iVar, i iVar2) {
                if (iVar.getFileName().equals(iVar2.getFileName())) {
                    return 0;
                }
                return iVar.tl() < iVar2.tl() ? -1 : 1;
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a(List<i> list, i iVar, p pVar) throws ZipException {
        int a = a(list, iVar);
        if (a == list.size() - 1) {
            return net.lingala.zip4j.headers.c.b(pVar);
        }
        return list.get(a + 1).tl();
    }

    private void c(File file, File file2) throws ZipException {
        if (file.delete()) {
            if (!file2.renameTo(file)) {
                throw new ZipException("cannot rename modified zip file");
            }
            return;
        }
        throw new ZipException("cannot delete old zip file");
    }

    private int a(List<i> list, i iVar) throws ZipException {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(iVar)) {
                return i;
            }
        }
        throw new ZipException("Could not find file header in list of central directory file headers");
    }
}
