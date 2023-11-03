package com.xiaopeng.xui.view.c;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import com.xiaopeng.xui.d.f;

/* compiled from: XTouchTargetUtils.java */
/* loaded from: classes13.dex */
public class c {
    private static final Rect abA = new Rect();

    public static void a(final View view, @Nullable final View view2, final int i, final int i2, final int i3, final int i4) {
        if (view == null || view2 == null) {
            return;
        }
        view2.post(new Runnable() { // from class: com.xiaopeng.xui.view.c.c.1
            @Override // java.lang.Runnable
            public void run() {
                if (view.isAttachedToWindow()) {
                    view.getHitRect(c.abA);
                    if (c.abA.width() == 0 || c.abA.height() == 0) {
                        c.log(" width or height == 0 " + hashCode());
                        a aVar = new a(view2, i, i2, i3, i4);
                        view.addOnLayoutChangeListener(aVar);
                        view.addOnAttachStateChangeListener(aVar);
                        return;
                    }
                    Rect rect = new Rect();
                    rect.set(c.abA);
                    ViewParent parent = view.getParent();
                    while (parent != view2) {
                        if (parent instanceof View) {
                            View view3 = (View) parent;
                            view3.getHitRect(c.abA);
                            rect.offset(c.abA.left, c.abA.top);
                            parent = view3.getParent();
                        } else {
                            return;
                        }
                    }
                    rect.left -= i;
                    rect.top -= i2;
                    rect.right += i3;
                    rect.bottom += i4;
                    com.xiaopeng.xui.view.c.a aVar2 = new com.xiaopeng.xui.view.c.a(rect, view);
                    com.xiaopeng.xui.view.c.b k = c.k(view2);
                    k.a(aVar2);
                    view2.setTouchDelegate(k);
                    view.addOnAttachStateChangeListener(new View$OnAttachStateChangeListenerC0086c(aVar2, k));
                    return;
                }
                c.log("not isAttachedToWindow " + hashCode());
            }
        });
    }

    /* compiled from: XTouchTargetUtils.java */
    /* loaded from: classes13.dex */
    private static class a implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {
        private static int abI;
        private View abH;
        int bottom;
        int left;
        int right;
        int top;

        a(View view, int i, int i2, int i3, int i4) {
            this.abH = view;
            this.left = i;
            this.top = i2;
            this.right = i3;
            this.bottom = i4;
            abI++;
        }

        protected void finalize() throws Throwable {
            super.finalize();
            abI--;
            if (abI == 0) {
                f.f("xpui-touch", " LayoutAttachStateChangeListener  finalize " + abI);
            }
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (view.getWidth() > 0 && view.getHeight() > 0) {
                c.a(view, this.abH, this.left, this.top, this.right, this.bottom);
                this.abH = null;
                view.removeOnLayoutChangeListener(this);
                view.removeOnAttachStateChangeListener(this);
                c.log(" LayoutAttachStateChangeListener  onLayoutChange ");
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            view.removeOnLayoutChangeListener(this);
            view.removeOnAttachStateChangeListener(this);
            c.log(" LayoutAttachStateChangeListener  onViewDetachedFromWindow ");
        }
    }

    public static void a(final View view, final ViewGroup viewGroup) {
        if (view == null || viewGroup == null) {
            return;
        }
        view.post(new Runnable() { // from class: com.xiaopeng.xui.view.c.c.2
            @Override // java.lang.Runnable
            public void run() {
                if (!view.isAttachedToWindow()) {
                    c.log(" as not isAttachedToWindow " + hashCode());
                } else if (viewGroup.getWidth() == 0 || viewGroup.getHeight() == 0) {
                    c.log(" as width or height == 0 " + hashCode());
                    b bVar = new b(viewGroup);
                    view.addOnLayoutChangeListener(bVar);
                    view.addOnAttachStateChangeListener(bVar);
                } else {
                    com.xiaopeng.xui.view.c.a aVar = new com.xiaopeng.xui.view.c.a(new Rect(0, 0, viewGroup.getWidth(), viewGroup.getHeight()), view);
                    com.xiaopeng.xui.view.c.b k = c.k(viewGroup);
                    k.a(aVar);
                    viewGroup.setTouchDelegate(k);
                    view.addOnAttachStateChangeListener(new View$OnAttachStateChangeListenerC0086c(aVar, k));
                }
            }
        });
    }

    /* compiled from: XTouchTargetUtils.java */
    /* loaded from: classes13.dex */
    private static class b implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {
        private static int abI;
        private ViewGroup abJ;

        b(ViewGroup viewGroup) {
            this.abJ = viewGroup;
            abI++;
        }

        protected void finalize() throws Throwable {
            super.finalize();
            abI--;
            if (abI == 0) {
                f.f("xpui-touch", " LayoutAttachStateChangeListener2  finalize " + abI);
            }
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (view.getWidth() > 0 && view.getHeight() > 0) {
                c.a(view, this.abJ);
                this.abJ = null;
                view.removeOnLayoutChangeListener(this);
                view.removeOnAttachStateChangeListener(this);
                c.log(" LayoutAttachStateChangeListener2  onLayoutChange ");
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            view.removeOnLayoutChangeListener(this);
            view.removeOnAttachStateChangeListener(this);
            c.log(" LayoutAttachStateChangeListener2  onViewDetachedFromWindow ");
        }
    }

    /* compiled from: XTouchTargetUtils.java */
    /* renamed from: com.xiaopeng.xui.view.c.c$c  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    private static class View$OnAttachStateChangeListenerC0086c implements View.OnAttachStateChangeListener {
        private static int abI;
        private com.xiaopeng.xui.view.c.a abK;
        private com.xiaopeng.xui.view.c.b abL;

        View$OnAttachStateChangeListenerC0086c(com.xiaopeng.xui.view.c.a aVar, com.xiaopeng.xui.view.c.b bVar) {
            this.abK = aVar;
            this.abL = bVar;
            abI++;
        }

        protected void finalize() throws Throwable {
            super.finalize();
            abI--;
            if (abI == 0) {
                f.f("xpui-touch", " MyStateChangeListener finalize " + abI);
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            view.removeOnAttachStateChangeListener(this);
            this.abL.a((TouchDelegate) this.abK);
            c.log("  MyStateChangeListener onViewDetachedFromWindow " + view.hashCode());
            this.abL = null;
            this.abK = null;
        }
    }

    public static com.xiaopeng.xui.view.c.b k(View view) {
        TouchDelegate touchDelegate = view.getTouchDelegate();
        if (touchDelegate != null) {
            if (touchDelegate instanceof com.xiaopeng.xui.view.c.b) {
                return (com.xiaopeng.xui.view.c.b) touchDelegate;
            }
            com.xiaopeng.xui.view.c.b bVar = new com.xiaopeng.xui.view.c.b(view);
            if (touchDelegate instanceof com.xiaopeng.xui.view.c.a) {
                bVar.a((com.xiaopeng.xui.view.c.a) touchDelegate);
            }
            return bVar;
        }
        return new com.xiaopeng.xui.view.c.b(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
    }
}
