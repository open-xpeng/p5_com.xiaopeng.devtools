package com.alibaba.sdk.android.man;

import com.alibaba.sdk.android.man.util.ToolKit;
import com.alibaba.sdk.android.man.util.UTWrapper;
import com.ut.mini.UTHitBuilders;
import java.util.Map;

/* loaded from: classes11.dex */
public class MANPageHitBuilder extends UTHitBuilders.UTPageHitBuilder {
    @Override // com.ut.mini.UTHitBuilders.UTHitBuilder
    public /* bridge */ /* synthetic */ UTHitBuilders.UTHitBuilder setProperties(Map map) {
        return setProperties((Map<String, String>) map);
    }

    public MANPageHitBuilder(String str) {
        super(str);
    }

    @Override // com.ut.mini.UTHitBuilders.UTPageHitBuilder
    public MANPageHitBuilder setReferPage(String str) {
        if (!ToolKit.isNullOrEmpty(str)) {
            super.setReferPage(str);
            UTWrapper.commitPageEvent("2");
        }
        return this;
    }

    @Override // com.ut.mini.UTHitBuilders.UTPageHitBuilder
    public MANPageHitBuilder setDurationOnPage(long j) {
        super.setDurationOnPage(j);
        UTWrapper.commitPageEvent("2");
        return this;
    }

    @Override // com.ut.mini.UTHitBuilders.UTHitBuilder
    public MANPageHitBuilder setProperty(String str, String str2) {
        super.setProperty(str, str2);
        UTWrapper.commitPageEvent("2");
        return this;
    }

    @Override // com.ut.mini.UTHitBuilders.UTHitBuilder
    public MANPageHitBuilder setProperties(Map<String, String> map) {
        super.setProperties(map);
        UTWrapper.commitPageEvent("2");
        return this;
    }

    @Override // com.ut.mini.UTHitBuilders.UTHitBuilder
    public Map<String, String> build() {
        return super.build();
    }
}
