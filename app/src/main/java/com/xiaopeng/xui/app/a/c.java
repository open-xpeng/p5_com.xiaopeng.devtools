package com.xiaopeng.xui.app.a;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.xiaopeng.xui.app.a.e;
import com.xiaopeng.xui.app.a.g;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: XActivityDelegateImpl.java */
/* loaded from: classes13.dex */
public class c extends b implements e.d, g.a {
    private int ZV;
    private AppCompatActivity aas;
    private j aat;
    private k aau;
    private i aav;
    private g aaw;
    private f aax;
    private ArraySet<h> aay = new ArraySet<>();
    private Handler mHandler = new Handler();

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(AppCompatActivity appCompatActivity) {
        this.aas = appCompatActivity;
        init();
    }

    private void init() {
        l qJ = l.qJ();
        qJ.start("XActivityDelegate-init");
        int f = f(this.aas.getClass());
        qJ.fi("analysisAnnotation " + f);
        this.aat = new j(this.aas);
        qJ.fi("attributes");
        this.aau = new k(this.aas);
        qJ.fi("visible");
        this.aax = f.e(this.aas, this);
        qJ.fi("cause");
        this.aaw = g.b(this.aas);
        this.aaw.a(this);
        qJ.fi("dismiss");
        this.aav = i.b(this.aas, f);
        qJ.fi("template");
        this.aav.a(this.aaw);
        this.aav.a(this.aax);
        this.aav.a(this.aat);
        this.aav.a(this.aau);
        qJ.fi("template-init");
        this.aay.add(this.aau);
        this.aay.add(this.aaw);
        this.aay.add(this.aav);
        this.aay.add(this.aax);
        qJ.end();
    }

    private int f(Class<?> cls) {
        a aVar;
        if (cls.isAnnotationPresent(a.class) && (aVar = (a) cls.getAnnotation(a.class)) != null) {
            return aVar.value();
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && !superclass.getName().equals(AppCompatActivity.class.getName())) {
            return f(superclass);
        }
        return 0;
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onPostCreate(Bundle bundle) {
        this.aat.apply();
        TypedArray obtainStyledAttributes = this.aas.getTheme().obtainStyledAttributes(new int[]{16842836});
        this.ZV = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        this.mHandler.post(new Runnable() { // from class: com.xiaopeng.xui.app.a.-$$Lambda$c$rKnqnrOXwjrVv6ZxvCH-JnF0mTs
            @Override // java.lang.Runnable
            public final void run() {
                c.this.qB();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void qB() {
        com.xiaopeng.xui.d.f.f("XActivityDelegate", "activityTemplate: " + this.aav.toString());
        com.xiaopeng.xui.d.f.f("XActivityDelegate", "dismiss: " + this.aaw.toString());
        com.xiaopeng.xui.d.f.f("XActivityDelegate", "windowVisible: " + this.aau.toString());
        com.xiaopeng.xui.d.f.f("XActivityDelegate", "dismissCause: " + this.aax.toString());
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onCreate(Bundle bundle) {
        Iterator<h> it = this.aay.iterator();
        while (it.hasNext()) {
            it.next().onCreate(bundle);
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onStart() {
        Iterator<h> it = this.aay.iterator();
        while (it.hasNext()) {
            it.next().onStart();
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onResume() {
        Iterator<h> it = this.aay.iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void qA() {
        Iterator<h> it = this.aay.iterator();
        while (it.hasNext()) {
            it.next().qA();
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onPause() {
        Iterator<h> it = this.aay.iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onStop() {
        Iterator<h> it = this.aay.iterator();
        while (it.hasNext()) {
            it.next().onStop();
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onDestroy() {
        Iterator<h> it = this.aay.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onConfigurationChanged(Configuration configuration) {
        if (this.ZV > 0) {
            com.xiaopeng.xui.c.a.setWindowBackgroundResource(configuration, this.aas.getWindow(), this.ZV);
        }
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void installViewFactory() {
        this.aas.getLayoutInflater().setFactory2(new LayoutInflater.Factory2() { // from class: com.xiaopeng.xui.app.a.c.1
            @Override // android.view.LayoutInflater.Factory2
            @Nullable
            public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
                return c.this.createView(view, str, context, attributeSet);
            }

            @Override // android.view.LayoutInflater.Factory
            @Nullable
            public View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
                return onCreateView(null, str, context, attributeSet);
            }
        });
    }

    public View createView(@Nullable View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return this.aas.getDelegate().createView(view, str, context, attributeSet);
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override // com.xiaopeng.xui.app.a.b
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.aax.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    @Override // com.xiaopeng.xui.app.a.b
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.aax.qD();
        return false;
    }

    @Override // com.xiaopeng.xui.app.a.b
    public void onBackPressed() {
        this.aax.onBackPressed();
    }

    @Override // com.xiaopeng.xui.app.a.e.d
    public void eg(int i) {
        this.aaw.dismiss(i);
    }

    @Override // com.xiaopeng.xui.app.a.g.a
    public void eh(int i) {
        this.aau.be(false);
        if (i != 2 && this.aax.qI() != null) {
            this.aax.qI().qC();
        }
    }

    @Override // com.xiaopeng.xui.app.a.g.a
    public void ei(int i) {
    }
}
