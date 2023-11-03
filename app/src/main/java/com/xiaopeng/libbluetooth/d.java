package com.xiaopeng.libbluetooth;

import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: AbstractConnection.java */
/* loaded from: classes12.dex */
public abstract class d {
    private LinkedList<Runnable> Xi = new LinkedList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void b(com.xiaopeng.b.a aVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void onDisconnected();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void pu();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(com.xiaopeng.b.a aVar) {
        b(aVar);
        pt();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pt() {
        Iterator<Runnable> it = this.Xi.iterator();
        while (it.hasNext()) {
            it.next().run();
            it.remove();
        }
    }
}
