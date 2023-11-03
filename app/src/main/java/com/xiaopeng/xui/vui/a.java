package com.xiaopeng.xui.vui;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import androidx.annotation.UiThread;
import com.xiaopeng.vui.commons.VuiElementType;
import com.xiaopeng.vui.commons.VuiFeedbackType;
import com.xiaopeng.vui.commons.VuiMode;
import com.xiaopeng.vui.commons.VuiPriority;
import com.xiaopeng.vui.commons.VuiUpdateType;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.f;
import com.xiaopeng.xui.d.h;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* compiled from: VuiView.java */
/* loaded from: classes13.dex */
public interface a {
    public static final SparseArray<C0087a> abc = new SparseArray<>();

    default void c(View view, AttributeSet attributeSet) {
        if (!com.xiaopeng.xui.a.qn() || view == null || attributeSet == null) {
            return;
        }
        C0087a c0087a = new C0087a();
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.vui);
        c0087a.acd = obtainStyledAttributes.getString(R.styleable.vui_vuiAction);
        c0087a.ace = com.xiaopeng.xui.vui.a.a.eB(obtainStyledAttributes.getInteger(R.styleable.vui_vuiElementType, -1));
        if (c0087a.ace == VuiElementType.UNKNOWN) {
            c0087a.ace = com.xiaopeng.xui.vui.a.b.p(view);
        }
        c0087a.aco = Integer.valueOf(obtainStyledAttributes.getInteger(R.styleable.vui_vuiPosition, -1));
        c0087a.acf = obtainStyledAttributes.getString(R.styleable.vui_vuiFatherElementId);
        c0087a.acg = obtainStyledAttributes.getString(R.styleable.vui_vuiLabel);
        c0087a.acn = obtainStyledAttributes.getString(R.styleable.vui_vuiFatherLabel);
        c0087a.ach = obtainStyledAttributes.getString(R.styleable.vui_vuiElementId);
        c0087a.aci = obtainStyledAttributes.getBoolean(R.styleable.vui_vuiLayoutLoadable, false);
        c0087a.acj = com.xiaopeng.xui.vui.a.a.ez(obtainStyledAttributes.getInteger(R.styleable.vui_vuiMode, 4));
        c0087a.ack = obtainStyledAttributes.getString(R.styleable.vui_vuiBizId);
        c0087a.acm = com.xiaopeng.xui.vui.a.a.ey(obtainStyledAttributes.getInt(R.styleable.vui_vuiPriority, 2));
        c0087a.acp = com.xiaopeng.xui.vui.a.a.eA(obtainStyledAttributes.getInteger(R.styleable.vui_vuiFeedbackType, 1));
        c0087a.acr = obtainStyledAttributes.getBoolean(R.styleable.vui_vuiDisableHitEffect, false);
        c0087a.acs = obtainStyledAttributes.getBoolean(R.styleable.vui_vuiEnableViewVuiMode, false);
        obtainStyledAttributes.recycle();
        c0087a.acv = view.getVisibility();
        c0087a.acw = view.isSelected();
        synchronized (abc) {
            abc.put(hashCode(), c0087a);
        }
    }

    default C0087a qU() {
        C0087a c0087a = abc.get(hashCode());
        if (c0087a == null) {
            fl("xAttr is null");
            c0087a = new C0087a();
            if (c0087a.ace == VuiElementType.UNKNOWN) {
                c0087a.ace = com.xiaopeng.xui.vui.a.b.p(this);
            }
            synchronized (abc) {
                abc.put(hashCode(), c0087a);
            }
        }
        return c0087a;
    }

    default boolean qV() {
        return qU().aci;
    }

    default void bf(boolean z) {
        qU().aci = z;
    }

    default VuiElementType qW() {
        return qU().ace;
    }

    default void a(VuiElementType vuiElementType) {
        qU().ace = vuiElementType;
    }

    default void fk(String str) {
        qU().acg = str;
        if (qV() && (this instanceof View)) {
            l((View) this);
        }
    }

    default void qX() {
        synchronized (abc) {
            abc.remove(hashCode());
        }
    }

    default void i(JSONObject jSONObject) {
        qU().acq = jSONObject;
    }

    default JSONObject qY() {
        return qU().acq;
    }

    default Object qZ() {
        return qU().act;
    }

    default com.xiaopeng.vui.commons.a ra() {
        C0087a qU = qU();
        if (qU.acu != null) {
            synchronized (abc) {
                if (qU.acu == null) {
                    return null;
                }
                return (com.xiaopeng.vui.commons.a) qU.acu.get();
            }
        }
        return null;
    }

    @UiThread
    default void e(View view, int i) {
        C0087a qU = qU();
        if (qU.acv != i) {
            if (f.dQ(2)) {
                fl("setVuiVisibility; xAttr.mVuiVisibility : " + h.ew(qU.acv) + ",visibility " + h.ew(i));
            }
            qU.acv = i;
            try {
                JSONObject qY = qY();
                if (qY != null && qY.has("canVoiceControl")) {
                    if (qY.getBoolean("canVoiceControl")) {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            l(view);
        }
    }

    @UiThread
    default void e(View view, boolean z) {
        C0087a qU = qU();
        if (qU.acw == z) {
            return;
        }
        qU.acw = z;
        String type = qW().getType();
        if (VuiElementType.CHECKBOX.getType().equals(type) || VuiElementType.SWITCH.getType().equals(type) || VuiElementType.RADIOBUTTON.getType().equals(type)) {
            l(view);
        }
    }

    default void l(View view) {
        a(view, VuiUpdateType.UPDATE_VIEW_ATTRIBUTE);
    }

    default void a(View view, VuiUpdateType vuiUpdateType) {
        com.xiaopeng.vui.commons.a ra = ra();
        if (ra != null) {
            com.xiaopeng.xui.vui.a.b.a(ra, view, vuiUpdateType);
        } else if (f.dQ(2)) {
            fl("listener is null");
        }
    }

    default void fl(String str) {
        f.d("xpui", "%s %s hashCode:%s", getClass().getSimpleName(), str, Integer.valueOf(hashCode()));
    }

    /* compiled from: VuiView.java */
    /* renamed from: com.xiaopeng.xui.vui.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0087a {
        private String acd;
        private String acf;
        private String acg;
        private String ach;
        private boolean aci;
        private String ack;
        private String acn;
        private VuiFeedbackType acp;
        private boolean acr;
        private Object act;
        private volatile WeakReference<com.xiaopeng.vui.commons.a> acu;
        private int acv;
        private boolean acw = false;
        private VuiElementType ace = com.xiaopeng.xui.vui.a.a.eB(-1);
        private Integer aco = -1;
        private VuiMode acj = VuiMode.NORMAL;
        private boolean acs = false;
        private VuiPriority acm = com.xiaopeng.xui.vui.a.a.ey(2);
        private JSONObject acq = null;

        C0087a() {
        }
    }
}
