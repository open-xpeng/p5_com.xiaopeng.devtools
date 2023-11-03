package com.alibaba.sdk.android.man.network;

import com.alibaba.sdk.android.man.util.ToolKit;
import java.util.HashMap;

/* loaded from: classes11.dex */
public class MANNetworkErrorInfo {
    private final HashMap<String, String> properties;

    /* JADX INFO: Access modifiers changed from: protected */
    public MANNetworkErrorInfo(HashMap<String, String> hashMap) {
        this.properties = hashMap;
    }

    public HashMap<String, String> getProperties() {
        return this.properties;
    }

    public MANNetworkErrorInfo withExtraInfo(String str, String str2) {
        if (!ToolKit.isNullOrEmpty(str) && !ToolKit.isNullOrEmpty(str2)) {
            this.properties.put(str, str2);
        }
        return this;
    }
}
