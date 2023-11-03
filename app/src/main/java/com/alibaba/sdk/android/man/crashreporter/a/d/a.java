package com.alibaba.sdk.android.man.crashreporter.a.d;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/* loaded from: classes11.dex */
public class a {
    public static void a(Map<String, String> map, Context context) {
        if (context != null) {
            try {
                if (!map.containsKey("pt")) {
                    String a = a(context, "package_type");
                    if (!TextUtils.isEmpty(a)) {
                        map.put("pt", a);
                    }
                }
                if (!map.containsKey("pid")) {
                    String a2 = a(context, "project_id");
                    if (!TextUtils.isEmpty(a2)) {
                        map.put("pid", a2);
                    }
                }
                if (!map.containsKey("bid")) {
                    String a3 = a(context, "build_id");
                    if (!TextUtils.isEmpty(a3)) {
                        map.put("bid", a3);
                    }
                }
                if (!map.containsKey("bv")) {
                    String a4 = a(context, "base_version");
                    if (!TextUtils.isEmpty(a4)) {
                        map.put("bv", a4);
                    }
                }
            } catch (Exception e) {
                com.alibaba.sdk.android.man.crashreporter.b.a.d("get MetaData err", e);
            }
        }
    }

    public static String d(Context context) {
        if (context != null) {
            String a = a(context, "base_version");
            if (!TextUtils.isEmpty(a)) {
                return a;
            }
            return null;
        }
        return null;
    }

    public static String a(Context context, String str) {
        int i;
        if (context == null) {
            return null;
        }
        try {
            i = context.getResources().getIdentifier(str, "string", context.getPackageName());
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("getString Id error", e);
            i = 0;
        }
        if (i == 0) {
            return null;
        }
        return context.getString(i);
    }

    public static byte[] b(Map map) {
        try {
            if (map == null) {
                com.alibaba.sdk.android.man.crashreporter.b.a.e("serializeMetaData err,map is null!");
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("serializeMetaData err!", e);
            return null;
        }
    }
}
