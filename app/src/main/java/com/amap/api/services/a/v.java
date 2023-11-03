package com.amap.api.services.a;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.amap.api.services.a.ax;
import com.amap.api.services.a.l;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LogProcessor.java */
/* loaded from: classes11.dex */
public abstract class v {
    private int b;
    private l eN;
    private ay eO;
    private ax eP;

    protected abstract boolean a(Context context);

    protected abstract String h(List<l> list);

    /* JADX INFO: Access modifiers changed from: protected */
    public v(int i) {
        this.b = i;
    }

    private void e(Context context) {
        try {
            this.eP = d(context, a());
        } catch (Throwable th) {
            o.a(th, "LogProcessor", "LogUpDateProcessor");
        }
    }

    void a(l lVar, Context context, Throwable th, String str, String str2, String str3) {
        a(lVar, context, e(th), str, str2, str3);
    }

    void a(l lVar, Context context, String str, String str2, String str3, String str4) {
        a(lVar);
        String d = d();
        String b = b(context, lVar);
        String a2 = d.a(context);
        if (str == null || "".equals(str)) {
            return;
        }
        int b2 = b();
        StringBuilder sb = new StringBuilder();
        if (str3 != null) {
            sb.append("class:");
            sb.append(str3);
        }
        if (str4 != null) {
            sb.append(" method:");
            sb.append(str4);
            sb.append("$");
            sb.append("<br/>");
        }
        sb.append(str2);
        String a3 = a(str2);
        String a4 = a(a2, b, d, b2, str, sb.toString());
        if (a4 == null || "".equals(a4)) {
            return;
        }
        String a5 = a(context, a4);
        String a6 = a();
        synchronized (Looper.getMainLooper()) {
            af afVar = new af(context);
            a(afVar, lVar.a(), a3, b2, a(context, a3, a6, a5, afVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(Context context, Throwable th, String str, String str2) {
        String a2;
        List<l> J = J(context);
        if (J == null || J.size() == 0 || (a2 = a(th)) == null || "".equals(a2)) {
            return;
        }
        for (l lVar : J) {
            if (a(lVar.bl(), a2)) {
                a(lVar, context, th, a2.replaceAll("\n", "<br/>"), str, str2);
                return;
            }
        }
        if (a2.contains("com.amap.api.col")) {
            try {
                a(new l.a("collection", "1.0", "AMap_collection_1.0").b(new String[]{"com.amap.api.collection"}).bm(), context, th, a2, str, str2);
            } catch (au e) {
                e.printStackTrace();
            }
        }
    }

    public static String b(Context context, l lVar) {
        StringBuilder sb = new StringBuilder();
        try {
            String e = g.e(context);
            sb.append("\"sim\":\"");
            sb.append(e);
            sb.append("\",\"sdkversion\":\"");
            sb.append(lVar.b());
            sb.append("\",\"product\":\"");
            sb.append(lVar.a());
            sb.append("\",\"ed\":\"");
            sb.append(lVar.c());
            sb.append("\",\"nt\":\"");
            sb.append(g.n(context));
            sb.append("\",\"np\":\"");
            sb.append(g.a(context));
            sb.append("\",\"mnc\":\"");
            sb.append(g.b(context));
            sb.append("\",\"ant\":\"");
            sb.append(g.o(context));
            sb.append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(Context context) {
        String h;
        List<l> J = J(context);
        if (J == null || J.size() == 0 || (h = h(J)) == null || "".equals(h)) {
            return;
        }
        String d = d();
        String b = b(context, this.eN);
        String a2 = d.a(context);
        int b2 = b();
        String a3 = a(a2, b, d, b2, "ANR", h);
        if (a3 == null || "".equals(a3)) {
            return;
        }
        String a4 = a(h);
        String a5 = a(context, a3);
        String a6 = a();
        synchronized (Looper.getMainLooper()) {
            af afVar = new af(context);
            a(afVar, this.eN.a(), a4, b2, a(context, a4, a6, a5, afVar));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(l lVar) {
        this.eN = lVar;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0018 -> B:11:0x0019). Please submit an issue!!! */
    private List<l> J(Context context) {
        List<l> list = null;
        try {
            synchronized (Looper.getMainLooper()) {
                try {
                    List<l> a2 = new ah(context, false).a();
                    try {
                        return a2;
                    } catch (Throwable th) {
                        list = a2;
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            throw th;
        } catch (Throwable th3) {
            th3.printStackTrace();
            return list;
        }
    }

    private void a(af afVar, String str, String str2, int i, boolean z) {
        ag H = p.H(i);
        H.a(0);
        H.b(str);
        H.a(str2);
        afVar.a(H);
    }

    protected String a(String str) {
        return j.c(str);
    }

    protected ay a(af afVar) {
        try {
            if (this.eO == null) {
                this.eO = new a(afVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.eO;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LogProcessor.java */
    /* loaded from: classes11.dex */
    public class a implements ay {
        private af eQ;

        a(af afVar) {
            this.eQ = afVar;
        }

        @Override // com.amap.api.services.a.ay
        public void a(String str) {
            try {
                this.eQ.c(str, p.G(v.this.b()));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private String a(String str, String str2, String str3, int i, String str4, String str5) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str2);
        stringBuffer.append(",");
        stringBuffer.append("\"timestamp\":\"");
        stringBuffer.append(str3);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str4);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str5);
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    private String a(Context context, String str) {
        try {
            return f.e(context, m.a(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String d() {
        return m.a(new Date().getTime());
    }

    protected String a(Throwable th) {
        try {
            return b(th);
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    private String e(Throwable th) {
        return th.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [com.amap.api.services.a.ax] */
    /* JADX WARN: Type inference failed for: r7v5, types: [com.amap.api.services.a.ax] */
    /* JADX WARN: Type inference failed for: r7v6, types: [com.amap.api.services.a.ax] */
    /* JADX WARN: Type inference failed for: r7v8, types: [com.amap.api.services.a.ax] */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.amap.api.services.a.af] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v11, types: [com.amap.api.services.a.ax$b] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [com.amap.api.services.a.ax$b] */
    /* JADX WARN: Type inference failed for: r9v5, types: [com.amap.api.services.a.ax$b] */
    /* JADX WARN: Type inference failed for: r9v6, types: [com.amap.api.services.a.ax$b] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    private boolean a(Context context, String str, String str2, String str3, af afVar) {
        OutputStream outputStream = null;
        try {
            try {
                File file = new File(p.a(context, (String) str2));
                if (!file.exists() && !file.mkdirs()) {
                    return false;
                }
                str2 = ax.a(file, 1, 1, 20480L);
                try {
                    str2.a(a((af) afVar));
                    afVar = str2.K(str);
                    if (afVar != 0) {
                        if (afVar != 0) {
                            try {
                                afVar.close();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                        if (str2 != 0 && !str2.a()) {
                            try {
                                str2.close();
                            } catch (Throwable th2) {
                                th2.printStackTrace();
                            }
                        }
                        return false;
                    }
                    try {
                        byte[] a2 = m.a(str3);
                        ax.a L = str2.L(str);
                        OutputStream J = L.J(0);
                        try {
                            J.write(a2);
                            L.a();
                            str2.b();
                            if (J != null) {
                                try {
                                    J.close();
                                } catch (Throwable th3) {
                                    th3.printStackTrace();
                                }
                            }
                            if (afVar != 0) {
                                try {
                                    afVar.close();
                                } catch (Throwable th4) {
                                    th4.printStackTrace();
                                }
                            }
                            if (str2 != 0 && !str2.a()) {
                                try {
                                    str2.close();
                                } catch (Throwable th5) {
                                    th5.printStackTrace();
                                }
                            }
                            return true;
                        } catch (IOException e) {
                            e = e;
                            outputStream = J;
                            e.printStackTrace();
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th6) {
                                    th6.printStackTrace();
                                }
                            }
                            if (afVar != 0) {
                                try {
                                    afVar.close();
                                } catch (Throwable th7) {
                                    th7.printStackTrace();
                                }
                            }
                            if (str2 != 0 && !str2.a()) {
                                try {
                                    str2.close();
                                } catch (Throwable th8) {
                                    th8.printStackTrace();
                                }
                            }
                            return false;
                        } catch (Throwable th9) {
                            th = th9;
                            outputStream = J;
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Throwable th10) {
                                    th10.printStackTrace();
                                }
                            }
                            if (afVar != 0) {
                                try {
                                    afVar.close();
                                } catch (Throwable th11) {
                                    th11.printStackTrace();
                                }
                            }
                            if (str2 != 0 && !str2.a()) {
                                try {
                                    str2.close();
                                } catch (Throwable th12) {
                                    th12.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    } catch (Throwable th13) {
                        th = th13;
                    }
                } catch (IOException e3) {
                    e = e3;
                    afVar = 0;
                } catch (Throwable th14) {
                    th = th14;
                    afVar = 0;
                }
            } catch (IOException e4) {
                e = e4;
                str2 = 0;
                afVar = 0;
            } catch (Throwable th15) {
                th = th15;
                str2 = 0;
                afVar = 0;
            }
        } catch (Throwable th16) {
            th = th16;
        }
    }

    public static boolean a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            for (String str2 : str.split("\n")) {
                if (b(strArr, str2.trim())) {
                    return true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    public static boolean b(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        try {
            String str2 = str;
            for (String str3 : strArr) {
                str2 = str2.trim();
                if (str2.startsWith("at ") && str2.contains(str3) && str2.endsWith(")")) {
                    return true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(Context context) {
        try {
            e(context);
            if (!a(context)) {
                return;
            }
            synchronized (Looper.getMainLooper()) {
                af afVar = new af(context);
                a(afVar, b());
                List<? extends ag> a2 = afVar.a(0, p.G(b()));
                if (a2 != null && a2.size() != 0) {
                    String a3 = a(a2, context);
                    if (a3 == null) {
                        return;
                    }
                    if (I(a3) == 1) {
                        a(a2, afVar, b());
                    }
                }
            }
        } catch (Throwable th) {
            o.a(th, "LogProcessor", "processUpdateLog");
        }
    }

    private boolean b(String str) {
        if (this.eP == null) {
            return false;
        }
        try {
            return this.eP.c(str);
        } catch (Throwable th) {
            o.a(th, "LogUpdateProcessor", "deleteLogData");
            return false;
        }
    }

    protected String a() {
        return p.I(this.b);
    }

    protected int b() {
        return this.b;
    }

    private void a(af afVar, int i) {
        try {
            a(afVar.a(2, p.G(i)), afVar, i);
        } catch (Throwable th) {
            o.a(th, "LogProcessor", "processDeleteFail");
        }
    }

    private int I(String str) {
        q qVar = new q(m.p(m.a(str)));
        int i = 1;
        try {
            byte[] b = bb.bw().b(qVar);
            if (b == null) {
                return 0;
            }
            try {
                JSONObject jSONObject = new JSONObject(m.a(b));
                if (jSONObject.has("code")) {
                    return jSONObject.getInt("code");
                }
                return 0;
            } catch (JSONException e) {
                o.a(e, "LogProcessor", "processUpdate");
                return 1;
            }
        } catch (au e2) {
            if (e2.b() == 27) {
                i = 0;
            }
            o.a(e2, "LogProcessor", "processUpdate");
            return i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void a(List<? extends ag> list, af afVar, int i) {
        if (list != null && list.size() > 0) {
            for (ag agVar : list) {
                if (b(agVar.b())) {
                    afVar.a(agVar.b(), (Class<? extends ag>) agVar.getClass());
                } else {
                    agVar.a(2);
                    afVar.b(agVar);
                }
            }
        }
    }

    public static String d(Context context) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"key\":\"");
            sb.append(d.f(context));
            sb.append("\",\"platform\":\"android\",\"diu\":\"");
            sb.append(g.w(context));
            sb.append("\",\"pkg\":\"");
            sb.append(d.c(context));
            sb.append("\",\"model\":\"");
            sb.append(Build.MODEL);
            sb.append("\",\"appname\":\"");
            sb.append(d.b(context));
            sb.append("\",\"appversion\":\"");
            sb.append(d.d(context));
            sb.append("\",\"sysversion\":\"");
            sb.append(Build.VERSION.RELEASE);
            sb.append("\",");
        } catch (Throwable th) {
            o.a(th, "CInfo", "getPublicJSONInfo");
        }
        return sb.toString();
    }

    private String g(Context context) {
        try {
            String d = d(context);
            if ("".equals(d)) {
                return null;
            }
            return f.b(context, m.a(d));
        } catch (Throwable th) {
            o.a(th, "LogProcessor", "getPublicInfo");
            return null;
        }
    }

    private String a(List<? extends ag> list, Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"pinfo\":\"");
        sb.append(g(context));
        sb.append("\",\"els\":[");
        boolean z = true;
        for (ag agVar : list) {
            String d = d(agVar.b());
            if (d != null && !"".equals(d)) {
                String str = d + "||" + agVar.v();
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                sb.append("{\"log\":\"");
                sb.append(str);
                sb.append("\"}");
            }
        }
        if (z) {
            return null;
        }
        sb.append("]}");
        return sb.toString();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private java.lang.String d(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            com.amap.api.services.a.ax r1 = r6.eP     // Catch: java.lang.Throwable -> L68
            if (r1 != 0) goto Ld
        Lc:
            return r0
        Ld:
            com.amap.api.services.a.ax r1 = r6.eP     // Catch: java.lang.Throwable -> L68
            com.amap.api.services.a.ax$b r7 = r1.K(r7)     // Catch: java.lang.Throwable -> L68
            if (r7 != 0) goto L1a
        L19:
            return r0
        L1a:
            r1 = 0
            java.io.InputStream r7 = r7.K(r1)     // Catch: java.lang.Throwable -> L68
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L60
            r2.<init>()     // Catch: java.lang.Throwable -> L60
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch: java.lang.Throwable -> L5a
        L29:
            int r4 = r7.read(r3)     // Catch: java.lang.Throwable -> L5a
            r5 = -1
            if (r4 == r5) goto L34
            r2.write(r3, r1, r4)     // Catch: java.lang.Throwable -> L5a
            goto L29
        L34:
            byte[] r1 = r2.toByteArray()     // Catch: java.lang.Throwable -> L5a
            java.lang.String r1 = com.amap.api.services.a.m.a(r1)     // Catch: java.lang.Throwable -> L5a
            r2.close()     // Catch: java.io.IOException -> L42
            goto L4a
        L42:
            r0 = move-exception
            java.lang.String r2 = "LogProcessor"
            java.lang.String r3 = "readLog1"
            com.amap.api.services.a.o.a(r0, r2, r3)
        L4a:
            if (r7 == 0) goto L58
            r7.close()     // Catch: java.io.IOException -> L50
            goto L58
        L50:
            r7 = move-exception
            java.lang.String r0 = "LogProcessor"
            java.lang.String r2 = "readLog2"
            com.amap.api.services.a.o.a(r7, r0, r2)
        L58:
            return r1
        L5a:
            r1 = move-exception
            goto L6b
        L5c:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L93
        L60:
            r1 = move-exception
            r2 = r0
            goto L6b
        L63:
            r7 = move-exception
            r2 = r0
            r0 = r7
            r7 = r2
            goto L93
        L68:
            r1 = move-exception
            r7 = r0
            r2 = r7
        L6b:
            java.lang.String r3 = "LogProcessor"
            java.lang.String r4 = "readLog"
            com.amap.api.services.a.o.a(r1, r3, r4)     // Catch: java.lang.Throwable -> L92
            if (r2 == 0) goto L81
            r2.close()     // Catch: java.io.IOException -> L79
            goto L81
        L79:
            r1 = move-exception
            java.lang.String r2 = "LogProcessor"
            java.lang.String r3 = "readLog1"
            com.amap.api.services.a.o.a(r1, r2, r3)
        L81:
            if (r7 == 0) goto L8f
            r7.close()     // Catch: java.io.IOException -> L87
            goto L8f
        L87:
            r7 = move-exception
            java.lang.String r1 = "LogProcessor"
            java.lang.String r2 = "readLog2"
            com.amap.api.services.a.o.a(r7, r1, r2)
        L8f:
            return r0
        L92:
            r0 = move-exception
        L93:
            if (r2 == 0) goto La1
            r2.close()     // Catch: java.io.IOException -> L99
            goto La1
        L99:
            r1 = move-exception
            java.lang.String r2 = "LogProcessor"
            java.lang.String r3 = "readLog1"
            com.amap.api.services.a.o.a(r1, r2, r3)
        La1:
            if (r7 == 0) goto Lb0
            r7.close()     // Catch: java.io.IOException -> La7
            goto Lb0
        La7:
            r7 = move-exception
            java.lang.String r1 = "LogProcessor"
            java.lang.String r2 = "readLog2"
            com.amap.api.services.a.o.a(r7, r1, r2)
        Lb0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.v.d(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        try {
            if (this.eP != null && !this.eP.a()) {
                this.eP.close();
            }
        } catch (IOException e) {
            o.a(e, "LogProcessor", "closeDiskLru");
        } catch (Throwable th) {
            o.a(th, "LogProcessor", "closeDiskLru");
        }
    }

    private ax d(Context context, String str) {
        try {
            File file = new File(p.a(context, str));
            if (!file.exists() && !file.mkdirs()) {
                return null;
            }
            return ax.a(file, 1, 1, 20480L);
        } catch (IOException e) {
            o.a(e, "LogProcessor", "initDiskLru");
            return null;
        } catch (Throwable th) {
            o.a(th, "LogProcessor", "initDiskLru");
            return null;
        }
    }

    public static String b(Throwable th) {
        return m.a(th);
    }
}
