package com.xiaopeng.devtools.utils.a;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.devtools.utils.d;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONObject;

/* compiled from: XmartV1IndivHelper.java */
/* loaded from: classes12.dex */
public class c implements com.xiaopeng.devtools.utils.a.a {
    private static final String pu = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("INDIV_SERVICE");

    @Override // com.xiaopeng.devtools.utils.a.a
    public IRequest a(Context context, com.xiaopeng.lib.security.a aVar) throws Exception {
        com.xiaopeng.lib.utils.c.f("XmartV1IndivHelper", "Build the individual request ......");
        HashMap hashMap = new HashMap();
        hashMap.put("version", Integer.valueOf(com.xiaopeng.lib.security.xmartv1.a.VU));
        return ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(pu, new Gson().toJson(hashMap)).build();
    }

    @Override // com.xiaopeng.devtools.utils.a.a
    public String b(IResponse iResponse) {
        com.xiaopeng.lib.http.a.a a2 = d.a(iResponse);
        String str = null;
        if (a2 != null) {
            try {
                if (200 == a2.getCode()) {
                    String data = a2.getData();
                    if (!TextUtils.isEmpty(data)) {
                        Log.d("XmartV1IndivHelper", "Get individual info success");
                        String string = new JSONObject(data).getString("secreteKey");
                        try {
                            return c(Base64.decode(string, 0), bf(com.xiaopeng.lib.utils.c.b.getHardwareId()));
                        } catch (Exception e) {
                            str = string;
                            e = e;
                            e.printStackTrace();
                            Log.d("XmartV1IndivHelper", "Get individual info failed");
                            return str;
                        }
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        return str;
    }

    @Override // com.xiaopeng.devtools.utils.a.a
    public IRequest b(Context context, com.xiaopeng.lib.security.a aVar) {
        return null;
    }

    @Override // com.xiaopeng.devtools.utils.a.a
    public boolean c(IResponse iResponse) {
        return true;
    }

    @Override // com.xiaopeng.devtools.utils.a.a
    public void Y(Context context) {
        com.xiaopeng.lib.utils.c.f("XmartV1IndivHelper", "Individual actions were finished!");
        try {
            Intent intent = new Intent();
            intent.setAction("com.xiaopeng.action.SECURE_STORE_RELOAD");
            if (context != null) {
                context.sendBroadcast(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: XmartV1IndivHelper.java */
    /* loaded from: classes12.dex */
    public static final class a {
        private static final c BX = new c();
    }

    public static c lH() {
        return a.BX;
    }

    @Nullable
    public static byte[] bf(String str) {
        byte[] bArr;
        com.xiaopeng.lib.utils.c.f("XmartV1IndivHelper", "Device ID:" + str);
        try {
            bArr = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            bArr = null;
        }
        com.xiaopeng.lib.utils.c.f("XmartV1IndivHelper", "Key:" + com.xiaopeng.lib.security.b.D(bArr));
        return bArr;
    }

    private static String c(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(2, secretKeySpec);
            return new String(cipher.doFinal(bArr), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
