package com.xiaopeng.xui.b;

import android.util.SparseArray;

/* compiled from: SoundEffectFactory.java */
/* loaded from: classes13.dex */
class b {
    private static SparseArray<d> abc = new SparseArray<>();
    private static SparseArray<d> abd;

    static {
        abc.put(-1, new a("CDU_touch.wav"));
        abc.put(1, new a("CDU_touch.wav"));
        abc.put(2, new a("CDU_wheel_scroll_7.wav"));
        abc.put(3, new a("CDU_switch_on_2.wav"));
        abc.put(4, new a("CDU_switch_off_2.wav"));
        abc.put(5, new a("CDU_delete_4.wav"));
        abd = abc.clone();
    }

    /* compiled from: SoundEffectFactory.java */
    /* loaded from: classes13.dex */
    static class a extends d {
        a(String str) {
            super("system/media/audio/xiaopeng/cdu/wav/" + str, 1, 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static e et(int i) {
        return new c(abc.get(i));
    }
}
