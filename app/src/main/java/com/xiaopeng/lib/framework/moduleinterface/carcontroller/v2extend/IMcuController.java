package com.xiaopeng.lib.framework.moduleinterface.carcontroller.v2extend;

import com.xiaopeng.lib.framework.moduleinterface.carcontroller.AbstractEventMsg;

/* loaded from: classes12.dex */
public interface IMcuController {

    /* loaded from: classes12.dex */
    public static class McuIgStatusEventMsg extends AbstractEventMsg<Integer> {
    }

    int getIgStatusFromMcu() throws Exception;
}
