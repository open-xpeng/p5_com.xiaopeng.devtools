package com.ut.mini.plugin;

/* loaded from: classes11.dex */
public abstract class UTPluginMsgDispatchDelegate {
    private Object g;

    public final Object getMsgObj() {
        return this.g;
    }

    public UTPluginMsgDispatchDelegate(Object obj) {
        this.g = null;
        this.g = obj;
    }

    public boolean isMatchPlugin(UTPlugin uTPlugin) {
        return true;
    }

    public Object getDispatchObject(UTPlugin uTPlugin) {
        return this.g;
    }
}
