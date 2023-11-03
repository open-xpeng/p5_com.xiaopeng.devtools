package com.xiaopeng.datalog.b;

import com.google.gson.Gson;

/* compiled from: GlobalGsonInstance.java */
/* loaded from: classes11.dex */
public class b {
    private Gson mGson;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GlobalGsonInstance.java */
    /* loaded from: classes11.dex */
    public static class a {
        static final b qw = new b();
    }

    public static b eX() {
        return a.qw;
    }

    private b() {
        this.mGson = new Gson();
    }

    public Gson eY() {
        return this.mGson;
    }
}
