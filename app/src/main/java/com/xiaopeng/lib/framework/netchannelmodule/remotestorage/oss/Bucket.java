package com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss;

import android.os.SystemProperties;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.IRemoteStorage;
import com.xiaopeng.lib.utils.a.a;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.c.b;
import com.xiaopeng.lib.utils.i;

/* loaded from: classes12.dex */
public class Bucket {
    public static String BUCKET_AND_ENDPOINT = null;
    public static String END_POINT = null;
    public static final String END_POINT_DOMAIN = "oss-cn-hangzhou.aliyuncs.com/";
    public static final String END_POINT_DOMAIN_INT = "oss-eu-central-1.aliyuncs.com/";
    private static final long MAX_LOG_LENGTH = 20971520;
    private static final String SCHEMA = "https://";
    public static String TOKEN_URL_V5;
    private String mRootName;
    private String mUrl;

    static {
        TOKEN_URL_V5 = a.Xa + "/v5/aliyun/token";
        END_POINT = "https://oss-cn-hangzhou.aliyuncs.com/";
        BUCKET_AND_ENDPOINT = "https://%s.oss-cn-hangzhou.aliyuncs.com/";
        try {
            boolean z = !getVersionInCountryCode().contains("ZH");
            c.g("Bucket", "Version = " + getVersionInCountryCode() + ", international = " + z);
            if (z) {
                TOKEN_URL_V5 = "https://xmart-eu.xiaopeng.com/biz/v5/aliyun/token";
                END_POINT = "https://oss-eu-central-1.aliyuncs.com/";
                BUCKET_AND_ENDPOINT = "https://%s.oss-eu-central-1.aliyuncs.com/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bucket get(IRemoteStorage.CATEGORY category) {
        switch (category) {
            case CAN:
                return new CanBucket();
            case CDU:
                return new CduBucket();
            default:
                throw new RuntimeException("Wrong bucket was specified!");
        }
    }

    public Bucket(String str) {
        this.mRootName = str;
        this.mUrl = String.format(BUCKET_AND_ENDPOINT, str);
    }

    private Bucket() {
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getRootName() {
        return this.mRootName;
    }

    public long getMaxObjectSize() {
        return MAX_LOG_LENGTH;
    }

    public String generateObjectKey(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        return this.mRootName + "/" + str + "/" + b.getSystemVersion() + "/" + com.xiaopeng.lib.utils.a.n(currentTimeMillis) + "/" + i.lp() + "/" + currentTimeMillis + ".zip";
    }

    /* loaded from: classes12.dex */
    private static final class CduBucket extends Bucket {
        private static final String CDU_BUCKET_NAME = "xmart-cdu-service-log";

        private CduBucket() {
            super(CDU_BUCKET_NAME);
        }
    }

    /* loaded from: classes12.dex */
    private static final class CanBucket extends Bucket {
        private static final String CAN_BUCKET_NAME = "xmart-can-service-log";

        private CanBucket() {
            super(CAN_BUCKET_NAME);
        }
    }

    public static String getVersionInCountryCode() throws Exception {
        String str = SystemProperties.get("ro.xiaopeng.software", "");
        if (!"".equals(str) && str != null && str.length() >= 9) {
            return str.substring(7, 9);
        }
        return str;
    }
}
