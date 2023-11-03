package com.xiaopeng.devtools.utils;

import android.net.InterfaceConfiguration;
import android.net.LinkAddress;
import android.os.INetworkManagementService;
import android.os.ServiceManager;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DataHelp.java */
/* loaded from: classes12.dex */
public class d {
    public static final Charset pb = Charset.forName("US-ASCII");
    public static final int[] pc = {0, 1, 2, 3, 4, 5, 6, 7, 8, 12, 16, 20, 24, 32, 48, 64};

    public static int cC(int i) {
        if (i < pc.length) {
            return pc[i];
        }
        return -1;
    }

    public static int b(byte b) {
        return b & 255;
    }

    public static int B(byte[] bArr) {
        return ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).getShort();
    }

    public static int C(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.BIG_ENDIAN);
        return wrap.getInt();
    }

    public static int t(byte[] bArr) {
        return ((bArr[1] & 255) << 8) | (bArr[0] & 255);
    }

    public static int cq(String str) {
        try {
            return Integer.parseInt(str, 16);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static byte[] a(byte[] bArr, int i, byte[] bArr2, int i2) {
        System.arraycopy(bArr2, 0, bArr, i, i2);
        return bArr;
    }

    public static byte[] c(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static String a(byte[] bArr, boolean z) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(2 * bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append("0123456789ABCDEF".charAt((bArr[i] >> 4) & 15));
            stringBuffer.append("0123456789ABCDEF".charAt(bArr[i] & 15));
            if (z) {
                stringBuffer.append(" ");
            }
        }
        return stringBuffer.toString();
    }

    public static com.xiaopeng.lib.http.a.a a(IResponse iResponse) {
        try {
            JSONObject jSONObject = new JSONObject(iResponse.body());
            com.xiaopeng.lib.http.a.a aVar = new com.xiaopeng.lib.http.a.a();
            aVar.setCode(jSONObject.getInt("code"));
            try {
                aVar.setData(jSONObject.getString("data"));
            } catch (JSONException e) {
            }
            try {
                aVar.dB(jSONObject.getString("msg"));
                return aVar;
            } catch (Exception e2) {
                return aVar;
            }
        } catch (Exception e3) {
            com.xiaopeng.lib.utils.c.a("CommonUtils", "Failed to parser the response data. response:" + iResponse.body(), e3);
            return null;
        }
    }

    public static boolean d(IResponse iResponse) {
        try {
            return new JSONObject(iResponse.body()).getInt("code") == 200;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String bI(int i) {
        int length = "defkSTUB345opqCDElxyzA26cQRF8IYZ0mJabKLMuPvwNOGHVWXnrst179".length();
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append("defkSTUB345opqCDElxyzA26cQRF8IYZ0mJabKLMuPvwNOGHVWXnrst179".charAt(random.nextInt(length)));
        }
        return stringBuffer.toString();
    }

    public static String k(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j == 0) {
            return "0B";
        }
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return decimalFormat.format(j) + "B";
        } else if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return decimalFormat.format(j / 1024.0d) + "KB";
        } else if (j < 1073741824) {
            return decimalFormat.format(j / 1048576.0d) + "MB";
        } else {
            return decimalFormat.format(j / 1.073741824E9d) + "GB";
        }
    }

    public static String getString(int i) {
        return MyApplication.getContext().getString(i);
    }

    public static String getString(int i, Object... objArr) {
        return MyApplication.getContext().getString(i, objArr);
    }

    public static boolean c(String[] strArr, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str2 : strArr) {
            if (str.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static String cr(String str) {
        try {
            InterfaceConfiguration interfaceConfig = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management")).getInterfaceConfig(str);
            if (interfaceConfig != null) {
                LinkAddress linkAddress = interfaceConfig.getLinkAddress();
                com.xiaopeng.lib.utils.c.f("getIpAddress", "linkAddr" + linkAddress.toString());
                if (linkAddress != null) {
                    InetAddress address = linkAddress.getAddress();
                    com.xiaopeng.lib.utils.c.f("getIpAddress", "inetaddr" + address.toString());
                    if (address != null) {
                        String hostAddress = address.getHostAddress();
                        if (hostAddress != null) {
                            com.xiaopeng.lib.utils.c.f("getIpAddress", "address " + hostAddress.toString());
                            return hostAddress;
                        }
                        return hostAddress;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            com.xiaopeng.lib.utils.c.i("test", "Error configuring interface :" + e);
            return null;
        }
    }

    private static String a(Charset charset, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        CharBuffer decode = charset.decode(ByteBuffer.wrap(bArr));
        return new String(decode.array(), 0, decode.length());
    }

    public static String a(BitSet bitSet, int i, int i2) {
        return a(pb, b(bitSet, i, i2));
    }

    private static byte[] b(BitSet bitSet, int i, int i2) {
        byte[] bArr = new byte[((i2 - i) + 7) / 8];
        int i3 = i;
        while (i3 < i2) {
            int i4 = i3 + 8;
            bArr[(i3 - i) / 8] = (byte) c(bitSet, i3, i4);
            i3 = i4;
        }
        return bArr;
    }

    public static int c(BitSet bitSet, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i2; i4++) {
            if (bitSet.get(i4)) {
                i3 = (int) (i3 + Math.pow(2.0d, i4 - i));
            }
        }
        return i3;
    }

    public static long d(BitSet bitSet, int i, int i2) {
        long j = 0;
        for (int i3 = i; i3 < i2; i3++) {
            if (bitSet.get(i3)) {
                j = (long) (j + Math.pow(2.0d, i3 - i));
            }
        }
        return j;
    }

    public static String e(BitSet bitSet, int i, int i2) {
        return a(b(bitSet, i, i2), false);
    }
}
