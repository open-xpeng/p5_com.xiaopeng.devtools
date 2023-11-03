package com.xiaopeng.lib.framework.netchannelmodule.http.xmart;

import com.lzy.okgo.c.a;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class ServerConverter implements a<ServerBean> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.lzy.okgo.c.a
    public ServerBean convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) {
            throw new IllegalStateException("null");
        }
        ServerBean serverBean = new ServerBean();
        JSONObject jSONObject = new JSONObject(body.string());
        serverBean.code(jSONObject.getInt("code"));
        try {
            serverBean.data(jSONObject.getString("data"));
        } catch (Throwable th) {
        }
        try {
            serverBean.message(jSONObject.getString("msg"));
        } catch (Throwable th2) {
        }
        return serverBean;
    }
}
