package com.a.a;

import java.util.ArrayList;

/* compiled from: Animator.java */
/* loaded from: classes11.dex */
public abstract class a implements Cloneable {
    ArrayList<InterfaceC0011a> mListeners = null;

    /* compiled from: Animator.java */
    /* renamed from: com.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public interface InterfaceC0011a {
        void a(a aVar);

        void b(a aVar);

        void c(a aVar);

        void d(a aVar);
    }

    public void start() {
    }

    public void cancel() {
    }

    public void a(InterfaceC0011a interfaceC0011a) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(interfaceC0011a);
    }

    @Override // 
    /* renamed from: do  reason: not valid java name */
    public a clone() {
        try {
            a aVar = (a) super.clone();
            if (this.mListeners != null) {
                ArrayList<InterfaceC0011a> arrayList = this.mListeners;
                aVar.mListeners = new ArrayList<>();
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    aVar.mListeners.add(arrayList.get(i));
                }
            }
            return aVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
