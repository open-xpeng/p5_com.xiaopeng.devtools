package com.xiaopeng.lib.http.logging;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: LogCacher.java */
/* loaded from: classes12.dex */
public class a {
    private List<C0081a> Va = new LinkedList();
    private b Vb = new d();
    private int Vc = 5;

    /* compiled from: LogCacher.java */
    /* loaded from: classes12.dex */
    public interface b {
        void dA(String str);
    }

    /* compiled from: LogCacher.java */
    /* loaded from: classes12.dex */
    public interface c {
        void code(int i);

        void end();

        void log(String str);
    }

    public c oe() {
        return new C0081a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(C0081a c0081a) {
        this.Va.add(c0081a);
        if (this.Va.size() > this.Vc) {
            this.Va.remove(0);
        }
        if (c0081a.code > 299 || c0081a.code < 0) {
            Iterator<C0081a> it = this.Va.iterator();
            while (it.hasNext()) {
                for (String str : it.next().Vd) {
                    this.Vb.dA(str);
                }
                it.remove();
            }
        }
    }

    /* compiled from: LogCacher.java */
    /* loaded from: classes12.dex */
    private class d implements b {
        private d() {
        }

        @Override // com.xiaopeng.lib.http.logging.a.b
        public void dA(String str) {
            com.xiaopeng.lib.utils.c.f("HttpsUtils", str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LogCacher.java */
    /* renamed from: com.xiaopeng.lib.http.logging.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static class C0081a implements c {
        List<String> Vd = new ArrayList();
        a Ve;
        int code;

        public C0081a(a aVar) {
            this.Ve = aVar;
        }

        @Override // com.xiaopeng.lib.http.logging.a.c
        public void code(int i) {
            this.code = i;
        }

        @Override // com.xiaopeng.lib.http.logging.a.c
        public void log(String str) {
            this.Vd.add(str);
        }

        @Override // com.xiaopeng.lib.http.logging.a.c
        public void end() {
            this.Ve.a(this);
        }
    }
}
