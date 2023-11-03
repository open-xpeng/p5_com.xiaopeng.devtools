package com.alibaba.sdk.android.utils;

import android.text.TextUtils;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class AlicloudTracker {
    private c a;
    private String c;
    private String d;

    /* renamed from: d  reason: collision with other field name */
    private Map<String, String> f104d = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlicloudTracker(c cVar, String str, String str2) {
        this.a = cVar;
        this.c = str;
        this.d = str2;
    }

    public void sendCustomHit(String str, long j, Map<String, String> map) {
        try {
            if (this.a == null) {
                Log.e("AlicloudTracker", "dataTracker is null, can not sendCustomHit");
                return;
            }
            if (map == null) {
                map = new HashMap<>();
            }
            map.putAll(this.f104d);
            map.put("sdkId", this.c);
            map.put("sdkVersion", this.d);
            c cVar = this.a;
            cVar.sendCustomHit(this.c + "_" + str, j, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCustomHit(String str, Map<String, String> map) {
        sendCustomHit(str, 0L, map);
    }

    public void setGlobalProperty(String str, String str2) {
        if (!TextUtils.isEmpty(str) && str2 != null) {
            if (this.f104d.containsKey(str)) {
                this.f104d.remove(str);
            }
            this.f104d.put(str, str2);
            return;
        }
        Log.e("AlicloudTracker", "key is null or key is empty or value is null,please check it!");
    }

    public void removeGlobalProperty(String str) {
        if (!TextUtils.isEmpty(str) && this.f104d.containsKey(str)) {
            this.f104d.remove(str);
        } else {
            Log.e("AlicloudTracker", "key is null or key is empty,please check it!");
        }
    }
}
