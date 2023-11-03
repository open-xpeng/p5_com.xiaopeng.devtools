package com.xiaopeng.devtools.utils;

import android.car.Car;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import java.util.HashMap;

/* compiled from: HttpUtil.java */
/* loaded from: classes12.dex */
public class i {
    public static final String BP = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("GEN_UUID");

    public static void a(final Callback callback) {
        com.xiaopeng.lib.utils.j.execute(new Runnable() { // from class: com.xiaopeng.devtools.utils.-$$Lambda$i$yAGoB-mOPbDSL-R7XsChIEWZ1jw
            @Override // java.lang.Runnable
            public final void run() {
                i.b(Callback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(Callback callback) {
        HashMap hashMap = new HashMap();
        IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
        iHttp.cancelTag(BP);
        iHttp.bizHelper().post(BP, new Gson().toJson(hashMap)).build().tag(BP).execute(callback);
    }

    public static String cC(String str) {
        String str2;
        String str3 = com.xiaopeng.lib.utils.a.a.WY;
        try {
            String cC = a.f.cC(str);
            char c = 65535;
            int hashCode = cC.hashCode();
            if (hashCode != -79017120) {
                if (hashCode != 3179) {
                    if (hashCode == 3248 && cC.equals("eu")) {
                        c = 2;
                    }
                } else if (cC.equals("cn")) {
                    c = 1;
                }
            } else if (cC.equals("optional")) {
                c = 0;
            }
            switch (c) {
                case 0:
                    if (Car.isExportVersion()) {
                        str2 = com.xiaopeng.lib.utils.a.a.WZ;
                        break;
                    } else {
                        return str3;
                    }
                case 1:
                    str2 = com.xiaopeng.lib.utils.a.a.WY;
                    break;
                case 2:
                    str2 = com.xiaopeng.lib.utils.a.a.WZ;
                    break;
                default:
                    return str3;
            }
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return str3;
        }
    }
}
