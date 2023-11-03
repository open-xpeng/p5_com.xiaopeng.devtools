package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: FTPListParseEngine.java */
/* loaded from: classes13.dex */
public class i {
    private final boolean aro;
    private List<String> asQ = new LinkedList();
    private ListIterator<String> asR = this.asQ.listIterator();
    private final e asS;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(e eVar, d dVar) {
        this.asS = eVar;
        if (dVar != null) {
            this.aro = dVar.uZ();
        } else {
            this.aro = false;
        }
    }

    public void b(InputStream inputStream, String str) throws IOException {
        this.asQ = new LinkedList();
        c(inputStream, str);
        this.asS.G(this.asQ);
        vc();
    }

    private void c(InputStream inputStream, String str) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, org.apache.commons.net.util.a.ga(str)));
        String a = this.asS.a(bufferedReader);
        while (a != null) {
            this.asQ.add(a);
            a = this.asS.a(bufferedReader);
        }
        bufferedReader.close();
    }

    public FTPFile[] vb() throws IOException {
        return a(h.asO);
    }

    public FTPFile[] a(g gVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (String str : this.asQ) {
            FTPFile fV = this.asS.fV(str);
            if (fV == null && this.aro) {
                fV = new FTPFile(str);
            }
            if (gVar.a(fV)) {
                arrayList.add(fV);
            }
        }
        return (FTPFile[]) arrayList.toArray(new FTPFile[arrayList.size()]);
    }

    public void vc() {
        this.asR = this.asQ.listIterator();
    }
}
