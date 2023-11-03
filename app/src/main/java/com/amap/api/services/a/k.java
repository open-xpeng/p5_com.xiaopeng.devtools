package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* compiled from: ProxyUtil.java */
/* loaded from: classes11.dex */
public class k {
    public static Proxy G(Context context) {
        Proxy H;
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                H = a(context, new URI("http://restapi.amap.com"));
            } else {
                H = H(context);
            }
            return H;
        } catch (Throwable th) {
            o.a(th, "ProxyUtil", "getProxy");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:105:0x015d A[Catch: Throwable -> 0x00c9, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Throwable -> 0x00c9, blocks: (B:61:0x00c4, B:72:0x00e9, B:105:0x015d), top: B:125:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0168 A[Catch: Throwable -> 0x0175, TRY_LEAVE, TryCatch #6 {Throwable -> 0x0175, blocks: (B:107:0x0162, B:109:0x0168), top: B:123:0x0162 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0184 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c4 A[Catch: Throwable -> 0x00c9, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Throwable -> 0x00c9, blocks: (B:61:0x00c4, B:72:0x00e9, B:105:0x015d), top: B:125:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e9 A[Catch: Throwable -> 0x00c9, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Throwable -> 0x00c9, blocks: (B:61:0x00c4, B:72:0x00e9, B:105:0x015d), top: B:125:0x001d }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00fe A[Catch: all -> 0x0181, TryCatch #2 {all -> 0x0181, blocks: (B:7:0x0023, B:9:0x0029, B:11:0x0035, B:13:0x003d, B:15:0x0045, B:16:0x004e, B:18:0x0054, B:23:0x0065, B:70:0x00dd, B:77:0x00f1, B:79:0x00fe, B:81:0x0115, B:83:0x011b, B:88:0x012a, B:92:0x0135, B:94:0x013d, B:96:0x0143, B:101:0x0152, B:39:0x0085, B:41:0x008d, B:42:0x0096, B:44:0x009c, B:49:0x00ad), top: B:122:0x001d }] */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v36 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v49 */
    /* JADX WARN: Type inference failed for: r3v50 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Type inference failed for: r3v53 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:64:0x00ca -> B:123:0x0162). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.Proxy H(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 405
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.k.H(android.content.Context):java.net.Proxy");
    }

    public static String a(String str) {
        return m.c(str);
    }

    private static boolean i(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    private static String a() {
        String str;
        try {
            str = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            o.a(th, "ProxyUtil", "getDefHost");
            str = null;
        }
        if (str == null) {
            return "null";
        }
        return str;
    }

    private static Proxy a(Context context, URI uri) {
        Proxy proxy;
        if (c(context)) {
            try {
                List<Proxy> select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty() || (proxy = select.get(0)) == null) {
                    return null;
                }
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                return proxy;
            } catch (Throwable th) {
                o.a(th, "ProxyUtil", "getProxySelectorCfg");
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return g.s(context) == 0;
    }

    private static int b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            o.a(th, "ProxyUtil", "getDefPort");
            return -1;
        }
    }
}
