package com.xiaopeng.lib.framework.moduleinterface.configurationmodule;

import android.support.annotation.Nullable;
import java.util.List;

/* loaded from: classes12.dex */
public class ConfigurationChangeEvent {
    @Nullable
    private List<IConfigurationData> mChangeList;

    public List<IConfigurationData> getChangeList() {
        return this.mChangeList;
    }

    public void setChangeList(List<IConfigurationData> list) {
        this.mChangeList = list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConfigurationChangeEvent{list size:");
        sb.append(this.mChangeList != null ? this.mChangeList.size() : 0);
        sb.append("}");
        return sb.toString();
    }
}
