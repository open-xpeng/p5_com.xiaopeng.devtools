package com.xiaopeng.datalog;

import android.content.Context;
import android.os.SystemClock;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IMoleEvent;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IStatEvent;
import com.xiaopeng.lib.utils.i;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: MoleEvent.java */
/* loaded from: classes11.dex */
public class c implements IMoleEvent {
    private Map<String, Object> properties = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context) {
        this.properties.put(IStatEvent.CUSTOM_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
        this.properties.put(IStatEvent.CUSTOM_MODULE_VERSION, d.getVersion(context));
        this.properties.put(IStatEvent.CUSTOM_NETWORK, d.Z(context));
        this.properties.put(IStatEvent.CUSTOM_STARTUP, Long.valueOf(SystemClock.uptimeMillis() / 1000));
        this.properties.put(IStatEvent.CUSTOM_DEVICE_MCUVER, d.eR());
        this.properties.put(IStatEvent.CUSTOM_UID, Long.valueOf(i.oR()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void put(String str, String str2) {
        if (str == null || str2 == null) {
            return;
        }
        this.properties.put(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void put(String str, Number number) {
        if (str == null || number == null) {
            return;
        }
        this.properties.put(str, number);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void put(String str, Boolean bool) {
        if (str == null || bool == null) {
            return;
        }
        this.properties.put(str, bool);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void put(String str, Character ch) {
        if (str == null || ch == null) {
            return;
        }
        this.properties.put(str, ch);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IMoleEvent
    public String toJson() {
        return com.xiaopeng.datalog.b.b.eX().eY().toJson(this.properties, new TypeToken<Map<String, Object>>() { // from class: com.xiaopeng.datalog.c.1
        }.getType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void checkValid() {
        if (!this.properties.containsKey("page_id")) {
            throw new IllegalArgumentException("Please call setPageId() first");
        }
        if (!this.properties.containsKey("button_id")) {
            throw new IllegalArgumentException("Please call setButtonId() first");
        }
        if (!this.properties.containsKey(IStatEvent.CUSTOM_MODULE)) {
            throw new IllegalArgumentException("Please call setModule() first");
        }
    }
}
