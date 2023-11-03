package com.xiaopeng.lib.security.xmartv1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaopeng.lib.http.Security;
import com.xiaopeng.lib.security.b;
import com.xiaopeng.lib.utils.c;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes12.dex */
public class RandomKeySecurity implements com.xiaopeng.lib.security.a {
    private static boolean po;
    private SharedPreferences Vt;
    private Context sContext;

    @VisibleForTesting
    /* loaded from: classes12.dex */
    public class SecurityKeyBean {
        public String key;
    }

    @Override // com.xiaopeng.lib.security.a
    public Security.EncryptionType oh() {
        return Security.EncryptionType.TEE_RANDOM_KEY;
    }

    @Override // com.xiaopeng.lib.security.a
    /* renamed from: ap */
    public RandomKeySecurity ak(Context context) throws Exception {
        synchronized (RandomKeySecurity.class) {
            po = KeyStoreHelper.init();
            this.Vt = context.getSharedPreferences("RandomKeySecurity", 0);
            this.sContext = context.getApplicationContext();
        }
        return this;
    }

    @Override // com.xiaopeng.lib.security.a
    /* renamed from: aq */
    public RandomKeySecurity al(Context context) throws Exception {
        if (!b.am(context)) {
            throw new RuntimeException("仅系统进程允许调用个性化操作!");
        }
        return ak(context);
    }

    @Override // com.xiaopeng.lib.security.a
    public boolean oi() {
        return po && KeyStoreHelper.oC();
    }

    @Override // com.xiaopeng.lib.security.a
    public void ok() {
        if (!b.am(this.sContext)) {
            throw new RuntimeException("仅系统进程允许调用个性化操作!");
        }
        try {
            KeyStoreHelper.oD();
            com.xiaopeng.lib.http.a.bn("/mnt/vendor/private/sec/si_flag.dat");
            this.Vt.edit().clear().commit();
        } catch (Exception e) {
            c.f("RandomKeySecurity", "清除个性化失败!");
            e.printStackTrace();
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public boolean dC(String str) throws Exception {
        c.f("RandomKeySecurity", str);
        try {
            LinkedHashMap linkedHashMap = (LinkedHashMap) new Gson().fromJson(str, new TypeToken<LinkedHashMap<Integer, SecurityKeyBean>>() { // from class: com.xiaopeng.lib.security.xmartv1.RandomKeySecurity.1
            }.getType());
            if (linkedHashMap.size() != com.xiaopeng.lib.security.xmartv1.a.VS) {
                c.f("RandomKeySecurity", "Wrong individual data with length " + linkedHashMap.size() + ", expected " + com.xiaopeng.lib.security.xmartv1.a.VS);
                return false;
            }
            HashMap hashMap = new HashMap(com.xiaopeng.lib.security.xmartv1.a.VS);
            for (Integer num : linkedHashMap.keySet()) {
                hashMap.put(com.xiaopeng.lib.security.xmartv1.a.VT + num, Base64.decode(((SecurityKeyBean) linkedHashMap.get(num)).key, 0));
                c.f("RandomKeySecurity", "Wrote security key #" + num + "!");
            }
            KeyStoreHelper.o(hashMap);
            KeyStoreHelper.oE();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public void destroy() {
    }

    @Override // com.xiaopeng.lib.security.a
    public void oj() {
        try {
            com.xiaopeng.lib.http.a.stringToFile("/mnt/vendor/private/sec/si_flag.dat", b.ol());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public void aP(boolean z) {
    }

    public String decode(String str) {
        if (!po) {
            throw new IllegalStateException("RandomKeySecurity:未初始化!");
        }
        try {
            return EncodingItem.F(str.getBytes("UTF-8")).ox();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getString(String str) {
        byte[] c;
        if (!po) {
            throw new IllegalStateException("RandomKeySecurity:未初始化!");
        }
        String string = this.Vt.getString(str, "");
        if (TextUtils.isEmpty(string) || (c = KeyStoreHelper.c(b.dD(string), EncodingItem.ow())) == null || c.length == 0) {
            return "";
        }
        try {
            return new String(c, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public String a(String[] strArr, byte[] bArr) {
        EncodingItem encodingItem;
        if (!po) {
            throw new IllegalStateException("RandomKeySecurity:未初始化!");
        }
        byte[] encode = Base64.encode(bArr, 2);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String string = getString(strArr[i2]);
            if (!TextUtils.isEmpty(string)) {
                linkedHashMap.put(strArr[i2], string);
                i += strArr[i2].length() + 2 + string.length();
            }
        }
        if (i > 0) {
            try {
                ByteBuffer put = ByteBuffer.allocate(encode.length + i).put(encode);
                for (int i3 = 0; i3 < strArr.length; i3++) {
                    put.put((byte) 38).put(strArr[i3].getBytes("UTF-8")).put((byte) 61).put(((String) linkedHashMap.get(strArr[i3])).getBytes("UTF-8"));
                }
                encode = put.array();
            } catch (Exception e) {
                e.printStackTrace();
                encodingItem = null;
            }
        }
        encodingItem = EncodingItem.G(encode);
        if (encodingItem.isEmpty()) {
            return null;
        }
        return encodingItem.oy();
    }

    public String t(String str, int i) {
        if (!po) {
            throw new IllegalStateException("RandomKeySecurity:未初始化!");
        }
        try {
            return EncodingItem.e(str.getBytes("UTF-8"), i).oy();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class a {
        private static final RandomKeySecurity VR = new RandomKeySecurity();
    }

    public static RandomKeySecurity oI() {
        return a.VR;
    }
}
