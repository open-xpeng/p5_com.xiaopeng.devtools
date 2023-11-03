package com.xiaopeng.devtools.utils;

import android.net.Uri;
import android.util.Base64;
import com.google.gson.Gson;
import com.xiaopeng.devtools.bean.car.DidRequest;
import com.xiaopeng.devtools.bean.car.DidResponse;
import com.xiaopeng.lib.apirouter.ApiRouter;

/* compiled from: DidHelper.java */
/* loaded from: classes12.dex */
public class e {
    public static byte[] n(int i, int i2) {
        DidRequest didRequest = new DidRequest(i, i2);
        Uri.Builder builder = new Uri.Builder();
        builder.authority("com.xiaopeng.ota.OTAService").path("readDid").appendQueryParameter("requestJsonContent", new Gson().toJson(didRequest));
        byte[] bArr = null;
        try {
            DidResponse didResponse = (DidResponse) new Gson().fromJson((String) ApiRouter.route(builder.build()), (Class<Object>) DidResponse.class);
            if (didResponse.getCode() == 0 && didResponse.getAddress() == i && didResponse.getDid() == i2) {
                bArr = Base64.decode(didResponse.getValue(), 0);
            } else {
                com.xiaopeng.lib.utils.c.i("DidHelper", "read did fail : " + didResponse.getMessage());
            }
        } catch (Exception e) {
            com.xiaopeng.lib.utils.c.i("DidHelper", "read did Exception : " + e.getMessage());
        }
        com.xiaopeng.lib.utils.c.g("DidHelper", "readDid address: " + i + ", did: " + i2 + ", res : " + d.a(bArr, true));
        return bArr;
    }
}
