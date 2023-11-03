package com.xiaopeng.lib.framework.netchannelmodule.messaging.profile;

import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.messaging.IMessaging;
import com.xiaopeng.lib.framework.netchannelmodule.messaging.protocol.MemoryPersistenceProxy;
import com.xiaopeng.lib.utils.b.a;
import com.xiaopeng.lib.utils.c.b;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/* loaded from: classes12.dex */
public abstract class AbstractChannelProfile {
    private static final String AES_KEY = "@!chxpzi#0109$+/";
    private static final int CONNECTION_TIMEOUT = 10;
    private static final String DEBUG_FLAG_FILE = "/sdcard/MQTTTRACE.xp";
    private static final int MAX_INFLIGHT = 120;
    protected static final String SSL_PREFIX = "ssl://";
    public static final String SYSTEM_PROPERTY_MQTT_COMM_ID;
    public static final String SYSTEM_PROPERTY_MQTT_COMM_PASS;
    public static final String SYSTEM_PROPERTY_MQTT_COMM_URL;
    public static final String SYSTEM_PROPERTY_MQTT_COMM_USER;
    public static final String SYSTEM_PROPERTY_MQTT_ID;
    public static final String SYSTEM_PROPERTY_MQTT_PASS;
    public static final String SYSTEM_PROPERTY_MQTT_REPORTING_URL;
    public static final String SYSTEM_PROPERTY_MQTT_USER;
    private static final boolean sShorterAccountEncoding;
    private boolean mCanPublish;
    private boolean mCanSubscribe;
    private boolean mDebugging = checkDebuggingFlag();
    private MqttClientPersistenceFactory sPersistenceFactory;

    /* loaded from: classes12.dex */
    public interface MqttClientPersistenceFactory {
        MqttClientPersistence makePersistence();
    }

    public abstract IMessaging.CHANNEL channel();

    public abstract String serverUrl();

    static {
        sShorterAccountEncoding = Build.VERSION.SDK_INT != 19;
        SYSTEM_PROPERTY_MQTT_USER = "persist.sys.mqtt.user" + b.pj();
        SYSTEM_PROPERTY_MQTT_PASS = "persist.sys.mqtt.pass" + b.pj();
        SYSTEM_PROPERTY_MQTT_ID = "persist.sys.mqtt.id" + b.pj();
        SYSTEM_PROPERTY_MQTT_REPORTING_URL = "persist.sys.mqtt.url" + b.pj();
        SYSTEM_PROPERTY_MQTT_COMM_URL = "persist.sys.mqtt.comm_url" + b.pj();
        SYSTEM_PROPERTY_MQTT_COMM_ID = "persist.sys.mqtt.comm_id" + b.pj();
        SYSTEM_PROPERTY_MQTT_COMM_USER = "persist.sys.mqtt.comm_user" + b.pj();
        SYSTEM_PROPERTY_MQTT_COMM_PASS = "persist.sys.mqtt.comm_pass" + b.pj();
    }

    public AbstractChannelProfile(boolean z, boolean z2) {
        this.mCanPublish = z;
        this.mCanSubscribe = z2;
    }

    public String logTag() {
        return "PahoLogger";
    }

    public boolean canPublish() {
        return this.mCanPublish;
    }

    public boolean canSubscribe() {
        return this.mCanSubscribe;
    }

    public int defaultQoSLevel() {
        return IMessaging.QOS.LEVEL_2.value();
    }

    public boolean sendOutAllLogs() {
        return false;
    }

    public boolean enableTrace() {
        return this.mDebugging;
    }

    public boolean needToCollectData() {
        return true;
    }

    public boolean enableExtremePing() {
        return false;
    }

    public String clientId() {
        String str = SystemProperties.get(SYSTEM_PROPERTY_MQTT_ID);
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("Initialization failure!!");
        }
        return decode(str);
    }

    public MqttConnectOptions buildConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(false);
        mqttConnectOptions.setCleanSession(false);
        mqttConnectOptions.setMqttVersion(4);
        mqttConnectOptions.setMaxInflight(120);
        mqttConnectOptions.setConnectionTimeout(10);
        return mqttConnectOptions;
    }

    public DisconnectedBufferOptions buildBufferOptions() {
        return null;
    }

    public Set<String> getAcceptedLogList() {
        if (this.mDebugging) {
            return null;
        }
        return new HashSet(Arrays.asList("309", "627", "633", "802"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String decode(String str) {
        String Z;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (sShorterAccountEncoding) {
            Z = a.aa(str, AES_KEY);
        } else {
            Z = a.Z(str, AES_KEY);
        }
        if (Z == null) {
            return "";
        }
        return Z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021 A[Catch: UnknownHostException -> 0x0081, TryCatch #1 {UnknownHostException -> 0x0081, blocks: (B:3:0x0005, B:5:0x0017, B:6:0x001b, B:8:0x0021, B:10:0x002d, B:14:0x003f, B:12:0x0033), top: B:29:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String resolveWithDns(java.lang.String r5) {
        /*
            android.net.Uri r0 = android.net.Uri.parse(r5)
            com.xiaopeng.lib.framework.netchannelmodule.http.xmart.TimeoutDns r1 = com.xiaopeng.lib.framework.netchannelmodule.http.xmart.TimeoutDns.getInstance()     // Catch: java.net.UnknownHostException -> L81
            java.lang.String r2 = r0.getHost()     // Catch: java.net.UnknownHostException -> L81
            java.util.List r1 = r1.lookupAsync(r2)     // Catch: java.net.UnknownHostException -> L81
            boolean r2 = r1.isEmpty()     // Catch: java.net.UnknownHostException -> L81
            if (r2 != 0) goto L80
            java.util.Iterator r1 = r1.iterator()     // Catch: java.net.UnknownHostException -> L81
        L1b:
            boolean r2 = r1.hasNext()     // Catch: java.net.UnknownHostException -> L81
            if (r2 == 0) goto L80
            java.lang.Object r2 = r1.next()     // Catch: java.net.UnknownHostException -> L81
            java.net.InetAddress r2 = (java.net.InetAddress) r2     // Catch: java.net.UnknownHostException -> L81
            boolean r3 = r2.isSiteLocalAddress()     // Catch: java.net.UnknownHostException -> L81
            if (r3 != 0) goto L33
            boolean r3 = r2.isLoopbackAddress()     // Catch: java.net.UnknownHostException -> L81
            if (r3 == 0) goto L3f
        L33:
            java.lang.String r3 = r2.getHostAddress()     // Catch: java.net.UnknownHostException -> L81
            java.lang.String r4 = "10."
            boolean r3 = r3.startsWith(r4)     // Catch: java.net.UnknownHostException -> L81
            if (r3 == 0) goto L7f
        L3f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.net.UnknownHostException -> L81
            r1.<init>()     // Catch: java.net.UnknownHostException -> L81
            java.lang.String r3 = r0.getScheme()     // Catch: java.net.UnknownHostException -> L81
            r1.append(r3)     // Catch: java.net.UnknownHostException -> L81
            java.lang.String r3 = "://"
            r1.append(r3)     // Catch: java.net.UnknownHostException -> L81
            java.lang.String r2 = r2.getHostAddress()     // Catch: java.net.UnknownHostException -> L81
            r1.append(r2)     // Catch: java.net.UnknownHostException -> L81
            java.lang.String r1 = r1.toString()     // Catch: java.net.UnknownHostException -> L81
            int r5 = r0.getPort()     // Catch: java.net.UnknownHostException -> L7c
            if (r5 <= 0) goto L7a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.net.UnknownHostException -> L7c
            r5.<init>()     // Catch: java.net.UnknownHostException -> L7c
            r5.append(r1)     // Catch: java.net.UnknownHostException -> L7c
            java.lang.String r2 = ":"
            r5.append(r2)     // Catch: java.net.UnknownHostException -> L7c
            int r0 = r0.getPort()     // Catch: java.net.UnknownHostException -> L7c
            r5.append(r0)     // Catch: java.net.UnknownHostException -> L7c
            java.lang.String r5 = r5.toString()     // Catch: java.net.UnknownHostException -> L7c
            goto L80
        L7a:
            r5 = r1
            goto L80
        L7c:
            r5 = move-exception
            r5 = r1
            goto L82
        L7f:
            goto L1b
        L80:
            goto L82
        L81:
            r0 = move-exception
        L82:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Update server uri:"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            com.xiaopeng.lib.utils.c.d(r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.lib.framework.netchannelmodule.messaging.profile.AbstractChannelProfile.resolveWithDns(java.lang.String):java.lang.String");
    }

    private static boolean checkDebuggingFlag() {
        if (b.pl() && new File(DEBUG_FLAG_FILE).exists()) {
            return true;
        }
        return false;
    }

    public MqttClientPersistence mqttClientPersistence() {
        if (this.sPersistenceFactory != null) {
            return this.sPersistenceFactory.makePersistence();
        }
        return new MemoryPersistenceProxy();
    }

    public void setPersistenceFactory(MqttClientPersistenceFactory mqttClientPersistenceFactory) {
        this.sPersistenceFactory = mqttClientPersistenceFactory;
    }
}
