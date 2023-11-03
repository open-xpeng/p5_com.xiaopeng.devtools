package com.xiaopeng.lib.framework.netchannelmodule.messaging.profile;

import android.os.SystemProperties;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IMessaging;
import com.xiaopeng.lib.framework.netchannelmodule.common.GlobalConfig;
import com.xiaopeng.lib.utils.c.b;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/* loaded from: classes12.dex */
public final class CommunicationChannelProfile extends AbstractChannelProfile {
    private static final boolean FUNCTION_PUBLISH = true;
    private static final boolean FUNCTION_SUBSCRIBE = true;

    public CommunicationChannelProfile() {
        super(true, true);
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.messaging.profile.AbstractChannelProfile
    @NonNull
    public String logTag() {
        return "PahoBizLogger";
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.messaging.profile.AbstractChannelProfile
    @NonNull
    public String serverUrl() {
        String decode = decode(SystemProperties.get(SYSTEM_PROPERTY_MQTT_COMM_URL));
        if (TextUtils.isEmpty(decode)) {
            return "";
        }
        return resolveWithDns("ssl://" + decode);
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.messaging.profile.AbstractChannelProfile
    public String clientId() {
        String decode;
        String str = SystemProperties.get(SYSTEM_PROPERTY_MQTT_COMM_ID);
        if (TextUtils.isEmpty(str)) {
            decode = super.clientId();
        } else {
            decode = decode(str);
        }
        return decode + ":" + b.getSystemVersion();
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.messaging.profile.AbstractChannelProfile
    public IMessaging.CHANNEL channel() {
        return IMessaging.CHANNEL.COMMUNICATION;
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.messaging.profile.AbstractChannelProfile
    public MqttConnectOptions buildConnectOptions() {
        MqttConnectOptions buildConnectOptions = super.buildConnectOptions();
        buildConnectOptions.setSocketFactory(GlobalConfig.getSocketFactory());
        buildConnectOptions.setUserName(getMQTTUserName());
        buildConnectOptions.setPassword(getMQTTPassword().toCharArray());
        return buildConnectOptions;
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.messaging.profile.AbstractChannelProfile
    public boolean enableExtremePing() {
        return true;
    }

    private String getMQTTUserName() {
        String str = SystemProperties.get(SYSTEM_PROPERTY_MQTT_COMM_USER);
        if (TextUtils.isEmpty(str)) {
            return decode(SystemProperties.get(SYSTEM_PROPERTY_MQTT_USER));
        }
        return decode(str);
    }

    private String getMQTTPassword() {
        String str = SystemProperties.get(SYSTEM_PROPERTY_MQTT_COMM_PASS);
        if (TextUtils.isEmpty(str)) {
            return decode(SystemProperties.get(SYSTEM_PROPERTY_MQTT_PASS));
        }
        return decode(str);
    }
}
