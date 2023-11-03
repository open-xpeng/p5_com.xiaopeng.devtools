package com.alibaba.sdk.android.man.network;

import android.util.Log;
import com.alibaba.sdk.android.man.util.EventCommitTool;
import com.alibaba.sdk.android.man.util.MANConfig;
import com.alibaba.sdk.android.man.util.MANLog;
import com.alibaba.sdk.android.man.util.ToolKit;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class NetworkEvent {
    public static final String TAG = "MAN_NetworkEvent";
    Map<String, String> property;
    private a requestStatus;
    private long requestTimeStart = -1;
    private long requestRT = -1;
    private long connectTime = -1;
    private long firstByteRT = -1;
    private long loadBytes = 0;

    /* loaded from: classes11.dex */
    private enum a {
        SUCCESS,
        FAILED,
        INVALID
    }

    public void addMANEventProperty(Map<String, String> map) {
        if (this.property == null) {
            this.property = map;
        } else {
            this.property.putAll(map);
        }
    }

    public void requestStart() {
        this.requestTimeStart = System.currentTimeMillis();
    }

    public void connectionEnd() {
        if (this.connectTime != -1) {
            return;
        }
        this.connectTime = System.currentTimeMillis() - this.requestTimeStart;
        MANLog.Logd(TAG, "[connectionEnd] requestTimeStart : " + this.requestTimeStart);
    }

    public void firstByteEnd() {
        if (this.firstByteRT != -1) {
            return;
        }
        this.firstByteRT = System.currentTimeMillis() - this.requestTimeStart;
        MANLog.Logd(TAG, "[firstByteEnd] - " + this.firstByteRT);
    }

    public void requestEndWithError(Map<String, String> map) {
        if (this.requestTimeStart == -1 || map == null) {
            this.requestStatus = a.INVALID;
            return;
        }
        if (this.property != null) {
            for (String str : map.keySet()) {
                if (str != null && map.get(str) != null) {
                    this.property.put(str.toString(), map.get(str).toString());
                }
            }
        } else {
            this.property = map;
        }
        if (this.property.containsKey(MANConfig.NETWORK_SINGLE_REQUEST_ERROR_MSG)) {
            try {
                if (!isDefineErrorCode(Integer.parseInt(this.property.get(MANConfig.NETWORK_SINGLE_REQUEST_ERROR_MSG)))) {
                    this.property.remove(MANConfig.NETWORK_SINGLE_REQUEST_ERROR_MSG);
                }
            } catch (NumberFormatException e) {
                this.property.remove(MANConfig.NETWORK_SINGLE_REQUEST_ERROR_MSG);
            }
        }
        this.requestStatus = a.FAILED;
    }

    public void requestEndNormally(long j) {
        if (this.requestTimeStart == -1 || this.requestRT != -1) {
            MANLog.Loge(TAG, "[requestEnd] - illegal state");
            this.requestStatus = a.INVALID;
            return;
        }
        this.loadBytes = j;
        this.requestRT = System.currentTimeMillis() - this.requestTimeStart;
        if (this.property == null) {
            this.property = new HashMap();
        }
        if (this.property.containsKey("Host")) {
            String str = this.property.get("Host");
            if (!ToolKit.isHost(str) && !ToolKit.isIp(str)) {
                this.property.remove("Host");
            }
        }
        if (this.connectTime != -1) {
            Log.d("man", "connect: " + this.connectTime);
            this.property.put(MANConfig.NETWORK_SINGLE_CONNECT_TIME_KEY, String.valueOf(this.connectTime));
        }
        if (this.firstByteRT != -1) {
            Log.d("man", "connect: " + this.firstByteRT);
            this.property.put(MANConfig.NETWORK_SINGLE_FIRST_PACKAGE_RT_KEY, String.valueOf(this.firstByteRT));
        }
        if (this.requestRT != -1) {
            Log.d("man", "connect: " + this.requestRT);
            this.property.put(MANConfig.NETWORK_SINGLE_REQUEST_RT_KEY, String.valueOf(this.requestRT));
        }
        if (this.loadBytes >= 0) {
            Log.d("man", "loadBytes: " + this.loadBytes);
            this.property.put(MANConfig.NETWORK_SINGLE_REQUEST_SIZE_KEY, String.valueOf(this.loadBytes));
        }
        this.requestStatus = a.SUCCESS;
    }

    public void reportNetworkInfo() {
        if (this.property == null) {
            this.property = new HashMap();
        }
        if (this.requestStatus == a.SUCCESS) {
            EventCommitTool.commitEvent(3002, MANConfig.NETWORK_SIG_REQUEST_EVENT_LABEL, this.property);
        } else if (this.requestStatus == a.FAILED) {
            EventCommitTool.commitEvent(MANConfig.NETWORK_ERROR_EVENT_ID, MANConfig.NETWORK_ERROR_EVENT_LABEL, this.property);
        } else if (this.requestStatus == a.INVALID) {
        }
    }

    public boolean isAdvancedStat() {
        if (this.connectTime != -1 || this.firstByteRT != -1) {
            return true;
        }
        return false;
    }

    public boolean isDefineErrorCode(int i) {
        if (i < 1001 || i > 1010) {
            if (i >= 2001 && i <= 2010) {
                return true;
            }
            return false;
        }
        return true;
    }
}
