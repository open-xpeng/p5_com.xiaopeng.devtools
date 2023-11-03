package com.xiaopeng.commonfunc.b.f;

import android.content.Context;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.g;
import java.util.HashMap;

/* compiled from: CduKeyModel.java */
/* loaded from: classes11.dex */
public class a {
    private static final String oe = a.f.ed("CHECK_CDU_KEY");
    private static final String of = a.f.ed("CHECK_V18_CDU_KEY");
    private Context mContext;
    private final com.xiaopeng.commonfunc.b.e.a og;
    private boolean oh = true;
    private String oi = null;

    public a(Context context) {
        this.mContext = context;
        this.og = new com.xiaopeng.commonfunc.b.e.a(context);
    }

    public void onDestroy() {
    }

    public boolean dY() {
        boolean dX = this.og.dX();
        g.au(this.mContext).dI("com.xiaopeng.action.CLIENT_SSL_UPDATE");
        return dX;
    }

    public boolean dZ() {
        boolean dW = this.og.dW();
        g.au(this.mContext).dI("com.xiaopeng.action.CLIENT_SSL_UPDATE");
        return dW;
    }

    public boolean ea() {
        try {
            return ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(oe, new Gson().toJson(new HashMap())).build().execute().body().contains("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eb() {
        try {
            return ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(of, new Gson().toJson(new HashMap())).build().execute().body().contains("ok");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
