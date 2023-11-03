package com.xiaopeng.lib.security.xmartv1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Base64;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.c.b;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;

@VisibleForTesting
/* loaded from: classes12.dex */
public final class EncodingItem {
    private static final EncodingItem VD;
    private static byte[] VF;
    private byte VG;
    private byte[] VH;
    private byte[] VI;
    private byte[] VJ;
    private String VK;
    private long mTimestamp;
    private static final byte[] VA = "XP".getBytes();
    private static final SecureRandom VB = new SecureRandom();
    private static final ArrayList<String> VC = new ArrayList<>();
    private static byte VE = a.VU;

    static {
        for (int i = 0; i < a.VS; i++) {
            VC.add(a.VT + i);
        }
        VF = b.getHardwareId().getBytes();
        VD = ov();
    }

    public static EncodingItem ov() {
        EncodingItem encodingItem = new EncodingItem();
        encodingItem.VJ = new byte[0];
        encodingItem.VK = "";
        encodingItem.VG = (byte) -1;
        return encodingItem;
    }

    public static String ow() {
        return VC.get(0);
    }

    @NonNull
    public static EncodingItem F(byte[] bArr) {
        byte[] decode = Base64.decode(bArr, 2);
        ByteBuffer wrap = ByteBuffer.wrap(decode);
        if (wrap.get() != VA[0] || wrap.get() != VA[1]) {
            c.f("EncodingItem", "Wrong magic number!");
            return VD;
        } else if (wrap.get() != VE) {
            c.f("EncodingItem", "Wrong version number!");
            return VD;
        } else {
            EncodingItem encodingItem = new EncodingItem();
            encodingItem.VG = wrap.get();
            int i = wrap.get();
            if (i > 0) {
                wrap.get(new byte[i]);
            }
            encodingItem.VH = new byte[wrap.get()];
            encodingItem.mTimestamp = wrap.getLong();
            encodingItem.VI = new byte[wrap.get()];
            wrap.get(encodingItem.VI);
            byte[] a = KeyStoreHelper.a(ByteBuffer.wrap(decode, encodingItem.oA(), decode.length - encodingItem.oA()), VC.get(encodingItem.VG), encodingItem.VI);
            if (a != null && a.length > 0) {
                ByteBuffer wrap2 = ByteBuffer.wrap(a);
                wrap2.get(encodingItem.VH);
                encodingItem.VJ = new byte[a.length - encodingItem.VH.length];
                wrap2.get(encodingItem.VJ);
                return encodingItem;
            }
            return VD;
        }
    }

    @NonNull
    public static EncodingItem G(byte[] bArr) {
        return e(bArr, oB());
    }

    @NonNull
    public static EncodingItem e(byte[] bArr, int i) {
        if (bArr == null && bArr.length == 0) {
            return VD;
        }
        if (i < 0 || i > a.VS) {
            throw new RuntimeException("Wrong security key index!");
        }
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        byte[] bArr2 = new byte[a.VV];
        VB.nextBytes(bArr2);
        EncodingItem encodingItem = new EncodingItem();
        encodingItem.VH = bytes;
        encodingItem.VI = bArr2;
        encodingItem.VG = (byte) i;
        encodingItem.VJ = bArr;
        encodingItem.mTimestamp = System.currentTimeMillis();
        if (!encodingItem.oz()) {
            return VD;
        }
        return encodingItem;
    }

    public boolean isEmpty() {
        return this.VJ == null || this.VJ.length == 0;
    }

    @NonNull
    public String ox() {
        if (isEmpty()) {
            return "";
        }
        try {
            return new String(this.VJ, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            c.f("EncodingItem", "Failed to convert raw content to string!");
            return "";
        }
    }

    @Nullable
    public String oy() {
        return this.VK;
    }

    private boolean oz() {
        byte[] a = KeyStoreHelper.a(ByteBuffer.allocate(this.VJ.length + this.VH.length).put(this.VH).put(this.VJ).array(), VC.get(this.VG), this.VI);
        if (a == null || a.length == 0) {
            c.f("EncodingItem", "Failed to encrypt the string.");
            return false;
        }
        this.VK = Base64.encodeToString(ByteBuffer.allocate(oA() + a.length).put(VA).put(VE).put(this.VG).put((byte) VF.length).put(VF).put((byte) this.VH.length).putLong(this.mTimestamp).put((byte) this.VI.length).put(this.VI).put(a).array(), 2);
        return true;
    }

    private EncodingItem() {
    }

    private int oA() {
        return 15 + VF.length + this.VI.length;
    }

    private static byte oB() {
        return (byte) ((VB.nextInt() & 127) % a.VS);
    }
}
