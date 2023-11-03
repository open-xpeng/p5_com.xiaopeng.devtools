package com.xiaopeng.commonfunc.utils;

import android.app.Application;
import android.car.Car;
import com.google.gson.Gson;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.IRemoteStorage;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.framework.netchannelmodule.http.xmart.bizapi.BizConstants;
import java.io.File;
import java.util.HashMap;

/* compiled from: FileUploadHelper.java */
/* loaded from: classes11.dex */
public class g {
    private static final String BUCKET_AND_ENDPOINT;
    private static final String BUCKET_NAME;

    static {
        BUCKET_NAME = com.xiaopeng.lib.utils.c.b.pk() ? "xp-log-local" : "xp-log";
        BUCKET_AND_ENDPOINT = "https://" + BUCKET_NAME + ".oss-cn-hangzhou.aliyuncs.com/";
    }

    public static void a(Application application, String str, final com.xiaopeng.commonfunc.a.b bVar) {
        IRemoteStorage iRemoteStorage = (IRemoteStorage) Module.get(NetworkChannelsEntry.class).get(IRemoteStorage.class);
        long currentTimeMillis = System.currentTimeMillis();
        String str2 = "xmart-cdu-service-log/" + com.xiaopeng.lib.utils.c.b.getSystemVersion() + File.separatorChar + com.xiaopeng.lib.utils.a.n(currentTimeMillis) + File.separatorChar + com.xiaopeng.lib.utils.i.lp() + File.separatorChar + i.aU(str);
        HashMap hashMap = new HashMap();
        hashMap.put("callbackUrl", "https://v-callback.xiaopeng.com/oss/vehicle/oss/callback/feedback");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("app_id", "xmart:appid:002");
        hashMap2.put("device", com.xiaopeng.lib.utils.i.getHardwareId());
        hashMap2.put("timestamp", String.valueOf(currentTimeMillis));
        hashMap2.put("sid", com.xiaopeng.lib.utils.i.oS());
        hashMap2.put("type", String.valueOf(1));
        hashMap2.put("address", "https://xp-security.oss-cn-hangzhou.aliyuncs.com/" + str2);
        hashMap2.put("timer", String.valueOf(currentTimeMillis));
        try {
            hashMap2.put("vmodel", Car.getHardwareCarType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        hashMap2.put("sign", j.a(application.getApplicationContext(), hashMap2, currentTimeMillis));
        com.xiaopeng.lib.utils.c.f("FileUploadHelper", new Gson().toJson(hashMap2));
        hashMap.put("callbackBody", new Gson().toJson(hashMap2));
        hashMap.put("callbackBodyType", BizConstants.CONTENT_TYPE_JSON);
        try {
            iRemoteStorage.initWithContext(application);
            iRemoteStorage.uploadWithPathAndCallback("xp-security", str2, str, new Callback() { // from class: com.xiaopeng.commonfunc.utils.g.1
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onStart(String str3, String str4) {
                    if (com.xiaopeng.commonfunc.a.b.this != null) {
                        com.xiaopeng.commonfunc.a.b.this.onStart(str3, str4);
                    }
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onSuccess(String str3, String str4) {
                    if (com.xiaopeng.commonfunc.a.b.this != null) {
                        com.xiaopeng.commonfunc.a.b.this.onSuccess(str3, str4);
                    }
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onFailure(String str3, String str4, StorageException storageException) {
                    if (com.xiaopeng.commonfunc.a.b.this != null) {
                        com.xiaopeng.commonfunc.a.b.this.a(str3, str4, storageException);
                    }
                }
            }, hashMap);
        } catch (Exception e2) {
            if (bVar != null) {
                bVar.a(null, null, e2);
            }
        }
    }
}
