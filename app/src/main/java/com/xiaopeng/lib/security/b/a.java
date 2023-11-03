package com.xiaopeng.lib.security.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaopeng.lib.http.Security;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.c.b;
import com.xiaopeng.lib.utils.d;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: NoneSecurity.java */
/* loaded from: classes12.dex */
public class a implements com.xiaopeng.lib.security.a {
    private static String Vu = d.dH(b.getHardwareId());
    private static boolean po;
    private SharedPreferences Vt;

    /* synthetic */ a(AnonymousClass1 anonymousClass1) {
        this();
    }

    @Override // com.xiaopeng.lib.security.a
    public Security.EncryptionType oh() {
        return Security.EncryptionType.NONE_ENCODING;
    }

    @Override // com.xiaopeng.lib.security.a
    public com.xiaopeng.lib.security.a ak(Context context) throws Exception {
        synchronized (this) {
            po = true;
            this.Vt = context.getSharedPreferences("NoneSecurity", 0);
        }
        return this;
    }

    @Override // com.xiaopeng.lib.security.a
    public com.xiaopeng.lib.security.a al(Context context) throws Exception {
        return this;
    }

    @Override // com.xiaopeng.lib.security.a
    public boolean oi() {
        return po;
    }

    @Override // com.xiaopeng.lib.security.a
    public void oj() {
    }

    @Override // com.xiaopeng.lib.security.a
    public void ok() {
    }

    @Override // com.xiaopeng.lib.security.a
    public boolean dC(String str) throws Exception {
        return true;
    }

    @Override // com.xiaopeng.lib.security.a
    public void destroy() {
    }

    @Override // com.xiaopeng.lib.security.a
    public void aP(boolean z) {
    }

    public String getString(String str) {
        byte[] bArr;
        String string = this.Vt.getString(str, "");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        try {
            byte[] dD = com.xiaopeng.lib.security.b.dD(string);
            SecretKeySpec secretKeySpec = new SecretKeySpec(Vu.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            bArr = cipher.doFinal(dD);
        } catch (Exception e) {
            e.printStackTrace();
            bArr = null;
        }
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public void setString(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            this.Vt.edit().putString(str, str2).commit();
            return;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(Vu.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
            byte[] doFinal = cipher.doFinal(str2.getBytes("UTF-8"));
            if (doFinal != null && doFinal.length > 0) {
                this.Vt.edit().putString(str, com.xiaopeng.lib.security.b.D(doFinal)).commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.xiaopeng.lib.security.a
    public String a(String[] strArr, byte[] bArr) {
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            for (int i2 = 0; i2 < strArr.length; i2++) {
                String string = getString(strArr[i2]);
                if (!TextUtils.isEmpty(string)) {
                    linkedHashMap.put(strArr[i2], string);
                    i += 2 + strArr[i2].length() + string.length();
                }
            }
            if (i > 0) {
                ByteBuffer put = ByteBuffer.allocate(bArr.length + i).put(bArr);
                for (int i3 = 0; i3 < strArr.length; i3++) {
                    put.put((byte) 38).put(strArr[i3].getBytes("UTF-8")).put((byte) 61).put(((String) linkedHashMap.get(strArr[i3])).getBytes("UTF-8"));
                }
                bArr = put.array();
            }
            return new String(Base64.encode(bArr, 2), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* compiled from: NoneSecurity.java */
    /* renamed from: com.xiaopeng.lib.security.b.a$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String[] Vv;
        final /* synthetic */ String[] Vw;
        final /* synthetic */ Runnable Vx;
        final /* synthetic */ a Vy;

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.Vy.b(this.Vv, this.Vw);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            this.Vx.run();
        }
    }

    public void b(String[] strArr, String[] strArr2) {
        if (strArr.length != strArr2.length) {
            c.f("NoneSecurity", "Invalid tokens.");
            return;
        }
        for (int i = 0; i < strArr.length; i++) {
            setString(strArr[i], strArr2[i]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NoneSecurity.java */
    /* renamed from: com.xiaopeng.lib.security.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static final class C0083a {
        private static final a Vz = new a(null);
    }

    public static a ot() {
        return C0083a.Vz;
    }

    private a() {
    }
}
