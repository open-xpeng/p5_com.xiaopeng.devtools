package org.apache.commons.net.io;

import java.util.EventListener;
import java.util.Iterator;
import org.apache.commons.net.util.ListenerList;

/* compiled from: CopyStreamAdapter.java */
/* loaded from: classes13.dex */
public class b implements c {
    private final ListenerList atj = new ListenerList();

    @Override // org.apache.commons.net.io.c
    public void a(long j, int i, long j2) {
        Iterator<EventListener> it = this.atj.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(j, i, j2);
        }
    }

    public void b(c cVar) {
        this.atj.addListener(cVar);
    }
}
