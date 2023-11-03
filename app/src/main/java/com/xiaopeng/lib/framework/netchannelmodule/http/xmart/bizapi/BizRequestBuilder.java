package com.xiaopeng.lib.framework.netchannelmodule.http.xmart.bizapi;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.lzy.okgo.model.HttpHeaders;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IRequest;
import com.xiaopeng.lib.framework.netchannelmodule.common.GlobalConfig;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.GetRequestAdapter;
import com.xiaopeng.lib.framework.netchannelmodule.http.okgoadapter.PostRequestAdapter;
import com.xiaopeng.lib.framework.netchannelmodule.http.xmart.bizapi.BizConstants;
import com.xiaopeng.lib.http.Security;
import com.xiaopeng.lib.http.d;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.c.b;
import com.xiaopeng.lib.utils.e;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes12.dex */
public class BizRequestBuilder {
    private static final String EMPTY_BODY = "";
    private static final String NETWORK_TYPE_2G = "2G";
    private static final String NETWORK_TYPE_3G = "3G";
    private static final String NETWORK_TYPE_4G = "4G";
    private static final String NETWORK_TYPE_UNKNOWN = "UNKNOWN";
    private static final String NETWORK_TYPE_WIFI = "WIFI";
    private static final String TAG = "BizRequestBuilder";
    private static final List<String> sBasicBizHeaders = Arrays.asList("XP-Appid", BizConstants.HEADER_CLIENT, BizConstants.HEADER_CLIENT_ENCODING, BizConstants.HEADER_CLIENT_TYPE, BizConstants.HEADER_ENCRYPTION_TYPE, BizConstants.HEADER_NONCE, BizConstants.HEADER_UID, BizConstants.HEADER_AUTHORIZATION);
    private static Gson sGson = new Gson();
    private String mAppId;
    private String mBody;
    private int mEncryptionType;
    private Map<String, String> mExtAuthorizationInfo;
    private BizConstants.METHOD mMethod;
    private boolean mNeedAuthorizationInfo;
    private IRequest mRequest;
    private String mSecretKey;
    private String[] mTokensForAuthorization;
    private String mUid;

    private BizRequestBuilder() {
        this.mUid = null;
        this.mAppId = BizConstants.APPID_CDU;
        this.mBody = "";
        this.mSecretKey = null;
        this.mNeedAuthorizationInfo = false;
        this.mTokensForAuthorization = Security.UK;
        this.mExtAuthorizationInfo = null;
    }

    public BizRequestBuilder(@NonNull IRequest iRequest, BizConstants.METHOD method) {
        this.mUid = null;
        this.mAppId = BizConstants.APPID_CDU;
        this.mBody = "";
        this.mSecretKey = null;
        this.mNeedAuthorizationInfo = false;
        this.mTokensForAuthorization = Security.UK;
        this.mExtAuthorizationInfo = null;
        this.mRequest = iRequest;
        this.mMethod = method;
        this.mEncryptionType = 0;
    }

    public BizRequestBuilder body(@NonNull String str) {
        this.mBody = str;
        return this;
    }

    public BizRequestBuilder enableIrdetoEncoding() {
        this.mEncryptionType = 1;
        return this;
    }

    public BizRequestBuilder enableSecurityEncoding() {
        this.mEncryptionType = Security.oc().getValue();
        return this;
    }

    public BizRequestBuilder needAuthorizationInfo(Map<String, String> map) {
        this.mNeedAuthorizationInfo = true;
        this.mExtAuthorizationInfo = map;
        return this;
    }

    public BizRequestBuilder appId(@NonNull String str) {
        this.mAppId = str;
        return this;
    }

    public BizRequestBuilder uid(@NonNull String str) {
        this.mUid = str;
        return this;
    }

    public BizRequestBuilder setExtHeader(@NonNull String str, @NonNull String str2) {
        if (str.startsWith(BizConstants.HEADER_PREFIX)) {
            this.mRequest.headers(str, str2);
        }
        return this;
    }

    public BizRequestBuilder customTokensForAuth(@NonNull String[] strArr) {
        this.mTokensForAuthorization = strArr;
        return this;
    }

    public IRequest build(String str) {
        if (str != null) {
            this.mSecretKey = str;
        } else {
            this.mSecretKey = BizConstants.CAR_SECRET;
        }
        for (String str2 : sBasicBizHeaders) {
            String bizHeaderValue = bizHeaderValue(str2);
            if (bizHeaderValue != null) {
                this.mRequest.headers(str2, bizHeaderValue);
            }
        }
        this.mRequest.headers(BizConstants.HEADER_SIGNATURE, generateSignature());
        if (this.mMethod == BizConstants.METHOD.POST) {
            if (TextUtils.isEmpty(this.mBody)) {
                this.mBody = "{}";
            }
            this.mRequest.headers("Content-Type", BizConstants.CONTENT_TYPE_JSON);
            this.mRequest.uploadJson(this.mBody);
        }
        return this.mRequest;
    }

    @Nullable
    private String getAuthorization() {
        if (this.mNeedAuthorizationInfo) {
            String generateSignature = generateSignature();
            HashMap hashMap = new HashMap(1);
            hashMap.put(BizConstants.AUTHORIZATION_XPSIGN, generateSignature);
            if (this.mExtAuthorizationInfo != null) {
                for (String str : this.mExtAuthorizationInfo.keySet()) {
                    hashMap.put(str, this.mExtAuthorizationInfo.get(str));
                }
            }
            try {
                if (this.mEncryptionType == 1) {
                    return d.a(this.mTokensForAuthorization, sGson.toJson(hashMap).getBytes());
                }
                return Security.a(this.mTokensForAuthorization, sGson.toJson(hashMap).getBytes());
            } catch (NullPointerException e) {
                c.i(TAG, "getAuthorization fail : " + e);
                return null;
            }
        }
        return null;
    }

    private static String getSignParameters(HttpHeaders httpHeaders, @NonNull String str) {
        String queryParameter;
        ArrayList arrayList = new ArrayList(httpHeaders.headersMap.keySet());
        Uri parse = Uri.parse(str);
        Set<String> queryParameterNames = parse.getQueryParameterNames();
        arrayList.addAll(queryParameterNames);
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            String str2 = (String) arrayList.get(i);
            if (queryParameterNames.contains(str2) || str2.startsWith(BizConstants.HEADER_PREFIX)) {
                if (httpHeaders.headersMap.containsKey(str2)) {
                    if (b.pl() && !TextUtils.isEmpty(parse.getQueryParameter(str2))) {
                        throw new RuntimeException("Duplicate keys in header and parameters!");
                    }
                    queryParameter = httpHeaders.headersMap.get(str2);
                    str2 = str2.toUpperCase();
                } else {
                    queryParameter = parse.getQueryParameter(str2);
                }
                if (!TextUtils.isEmpty(queryParameter)) {
                    if (sb.length() > 0) {
                        sb.append('&');
                    }
                    sb.append(str2 + "=" + queryParameter);
                }
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Nullable
    private String bizHeaderValue(String str) {
        char c;
        switch (str.hashCode()) {
            case -1913874489:
                if (str.equals("XP-Appid")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1901900614:
                if (str.equals(BizConstants.HEADER_NONCE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1700294693:
                if (str.equals(BizConstants.HEADER_UID)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -576067417:
                if (str.equals(BizConstants.HEADER_CLIENT_TYPE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 366551663:
                if (str.equals(BizConstants.HEADER_ENCRYPTION_TYPE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 800782916:
                if (str.equals(BizConstants.HEADER_AUTHORIZATION)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 852785248:
                if (str.equals(BizConstants.HEADER_CLIENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1042155584:
                if (str.equals(BizConstants.HEADER_CLIENT_ENCODING)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return this.mAppId;
            case 1:
                return getAuthorization();
            case 2:
                return generateXpClient();
            case 3:
                return BizConstants.CLIENT_ENCODING_NONE;
            case 4:
                return "3";
            case 5:
                return String.valueOf(this.mEncryptionType);
            case 6:
                return String.valueOf(System.currentTimeMillis());
            case 7:
                return this.mUid;
            default:
                return null;
        }
    }

    @NonNull
    private String generateXpClient() {
        String pp = com.xiaopeng.lib.utils.c.c.pp();
        String replaceAll = TextUtils.isEmpty(pp) ? "" : pp.toUpperCase().replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        sb.append("di=" + getHardwareId() + ";");
        sb.append("pn=" + GlobalConfig.getApplicationName() + ";");
        sb.append("ve=" + b.getSystemVersion() + ";");
        sb.append("br=Xiaopeng;");
        sb.append("mo=" + replaceAll + ";");
        sb.append("st=1;");
        sb.append("sv=" + b.getSystemVersion() + ";");
        sb.append("nt=" + getNetworkType() + ";");
        sb.append("t=" + System.currentTimeMillis() + ";");
        if (com.xiaopeng.lib.utils.c.c.pq()) {
            sb.append("ln=en;");
        }
        return sb.toString();
    }

    @Nullable
    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    @Nullable
    private String generateSignature() {
        String value = this.mMethod.getValue();
        String signParameters = getSignParameters(getRequestHeaders(), this.mRequest.getUrl());
        try {
            String dH = TextUtils.isEmpty(this.mBody) ? "" : com.xiaopeng.lib.utils.d.dH(this.mBody);
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] bytes = this.mSecretKey.getBytes("UTF-8");
            mac.init(new SecretKeySpec(bytes, 0, bytes.length, "HmacSHA256"));
            c.f(TAG, "generateSignature origin:" + (value + '&' + signParameters + '&' + dH));
            byte[] doFinal = mac.doFinal((value + '&' + signParameters + '&' + dH).getBytes("UTF-8"));
            if (this.mEncryptionType == 0) {
                return bytesToHexString(doFinal);
            }
            return Security.D(doFinal).toLowerCase();
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpHeaders getRequestHeaders() {
        if (this.mRequest instanceof GetRequestAdapter) {
            return ((GetRequestAdapter) this.mRequest).getHeaders();
        }
        if (this.mRequest instanceof PostRequestAdapter) {
            return ((PostRequestAdapter) this.mRequest).getHeaders();
        }
        return null;
    }

    private String getNetworkType() {
        int as = e.as(GlobalConfig.getApplicationContext());
        if (as != 10) {
            switch (as) {
                case 1:
                    return NETWORK_TYPE_2G;
                case 2:
                    return NETWORK_TYPE_3G;
                case 3:
                    return NETWORK_TYPE_4G;
                default:
                    return NETWORK_TYPE_UNKNOWN;
            }
        }
        return NETWORK_TYPE_WIFI;
    }

    private String getHardwareId() {
        String hardwareId = b.getHardwareId();
        if (b.pl() && TextUtils.isEmpty(hardwareId)) {
            throw new RuntimeException("Invalid hardware ID.");
        }
        return hardwareId;
    }
}
