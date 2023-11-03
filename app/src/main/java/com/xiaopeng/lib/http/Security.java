package com.xiaopeng.lib.http;

import android.content.Context;
import android.os.Build;

/* loaded from: classes12.dex */
public class Security {
    private static com.xiaopeng.lib.security.a UM;
    private static boolean po;
    private static Context sContext;
    public static final String[] UK = {"accessToken", "refreshToken"};
    public static final String[] UL = {"accessToken"};
    private static boolean UN = false;
    private static com.xiaopeng.lib.security.a pm = com.xiaopeng.lib.security.c.om();

    static {
        if (Build.VERSION.SDK_INT == 19) {
            UM = com.xiaopeng.lib.security.a.a.or();
        }
    }

    /* loaded from: classes12.dex */
    public enum EncryptionType {
        IRDETO(1),
        TEE_RANDOM_KEY(2),
        NONE_ENCODING(3);
        
        private int value;

        EncryptionType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public static EncryptionType oc() {
        if (UN && UM != null) {
            return UM.oh();
        }
        return pm.oh();
    }

    public static synchronized void init(Context context) {
        synchronized (Security.class) {
            if (po) {
                com.xiaopeng.lib.utils.c.f("Security", "sSecureSDKManager has init");
                return;
            }
            sContext = context;
            po = false;
            try {
                pm.ak(context);
                po = true;
                if (UM != null) {
                    try {
                        UM.ak(context);
                        if (!UM.oi()) {
                            UM = null;
                        }
                    } catch (Exception e) {
                        UM = null;
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean et() {
        if (UN && UM != null) {
            return UM.oi();
        }
        return pm.oi();
    }

    public static synchronized boolean od() {
        boolean z;
        synchronized (Security.class) {
            z = po;
        }
        return z;
    }

    public static synchronized void destroy() {
        synchronized (Security.class) {
            pm.destroy();
            po = false;
            if (UM != null) {
                try {
                    UM.destroy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String a(String[] strArr, byte[] bArr) {
        if (UN && UM != null) {
            return UM.a(strArr, bArr);
        }
        return pm.a(strArr, bArr);
    }

    public static String D(byte[] bArr) {
        return com.xiaopeng.lib.security.b.D(bArr);
    }
}
