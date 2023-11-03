package com.xiaopeng.a;

import android.os.SystemProperties;

/* compiled from: Support.java */
/* loaded from: classes11.dex */
public class a {

    /* compiled from: Support.java */
    /* loaded from: classes11.dex */
    public static class b {
        public static boolean getBoolean(String str) {
            return g.ac(str, "value");
        }

        public static String getString(String str) {
            return g.getString(str, "value");
        }

        public static int getInt(String str) {
            return g.ad(str, "value");
        }
    }

    /* compiled from: Support.java */
    /* loaded from: classes11.dex */
    public static class e {
        public static String getString(String str) {
            return g.getString(str, "value");
        }
    }

    /* compiled from: Support.java */
    /* loaded from: classes11.dex */
    public static class d {
        public static String get(String str) {
            String string = g.getString(str, "key");
            String str2 = SystemProperties.get(string, "Unknown");
            com.xiaopeng.a.c.t("Support", "Properties.get", "property=" + string + " value=" + str2);
            return str2;
        }

        public static String get(String str, String str2) {
            String string = g.getString(str, "key");
            String str3 = SystemProperties.get(string, str2);
            com.xiaopeng.a.c.t("Support", "Properties.get", "property=" + string + " value=" + str3);
            return str3;
        }

        public static int getInt(String str, int i) {
            String string = g.getString(str, "key");
            int i2 = SystemProperties.getInt(string, i);
            com.xiaopeng.a.c.t("Support", "Properties.getInt", "property=" + string + " value=" + i2);
            return i2;
        }

        public static boolean getBoolean(String str) {
            String string = g.getString(str, "key");
            boolean z = SystemProperties.getBoolean(string, false);
            com.xiaopeng.a.c.t("Support", "Properties.getBoolean", "property=" + string + " value=" + z);
            return z;
        }

        public static void set(String str, String str2) {
            String string = g.getString(str, "key");
            com.xiaopeng.a.c.v("Support", "Properties.set", "property=" + string + ", value=" + str2);
            SystemProperties.set(string, str2);
        }
    }

    /* compiled from: Support.java */
    /* renamed from: com.xiaopeng.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static class C0041a {
        public static boolean dX(String str) {
            return g.ac(str, "enable");
        }

        public static String getString(String str) {
            return g.getString(str, "value");
        }

        public static int getInt(String str) {
            return g.ad(str, "value");
        }

        public static int dY(String str) {
            try {
                return Integer.parseInt(g.getString(str, "address").substring(2), 16);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        }

        public static int dZ(String str) {
            try {
                return Integer.parseInt(g.getString(str, "did").substring(2), 16);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        }

        public static int ea(String str) {
            return g.ad(str, "length");
        }

        public static String eb(String str) {
            return g.getString(str, "extra");
        }
    }

    /* compiled from: Support.java */
    /* loaded from: classes11.dex */
    public static class c {
        public static String ec(String str) {
            return g.getString(str, "value");
        }
    }

    /* compiled from: Support.java */
    /* loaded from: classes11.dex */
    public static class f {
        public static String ed(String str) {
            return g.getString(str, "value");
        }

        public static String cC(String str) {
            return g.getString(str, "host");
        }
    }

    /* compiled from: Support.java */
    /* loaded from: classes11.dex */
    private static class g {
        /* JADX INFO: Access modifiers changed from: private */
        public static boolean ac(String str, String str2) {
            return c(str, str2, false);
        }

        private static boolean c(String str, String str2, boolean z) {
            boolean z2;
            try {
                z2 = Boolean.parseBoolean(com.xiaopeng.a.b.qi().ah(str, str2));
                try {
                    com.xiaopeng.a.c.t("Support", "Values.getBoolean", "id=" + str + ", value=" + z2);
                } catch (Exception e) {
                    e = e;
                    com.xiaopeng.a.c.d(e);
                    return z2;
                }
            } catch (Exception e2) {
                e = e2;
                z2 = z;
            }
            return z2;
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x003f A[ORIG_RETURN, RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private static java.lang.String d(java.lang.String r4, java.lang.String r5, boolean r6) {
            /*
                java.lang.String r0 = "Unknown"
                com.xiaopeng.a.b r1 = com.xiaopeng.a.b.qi()     // Catch: java.lang.Exception -> L38
                java.lang.String r1 = r1.ah(r4, r5)     // Catch: java.lang.Exception -> L38
                if (r6 == 0) goto L37
                java.lang.String r6 = "Support"
                java.lang.String r0 = "Values.getString"
                java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L35
                r2.<init>()     // Catch: java.lang.Exception -> L35
                java.lang.String r3 = "id="
                r2.append(r3)     // Catch: java.lang.Exception -> L35
                r2.append(r4)     // Catch: java.lang.Exception -> L35
                java.lang.String r4 = ", "
                r2.append(r4)     // Catch: java.lang.Exception -> L35
                r2.append(r5)     // Catch: java.lang.Exception -> L35
                java.lang.String r4 = "="
                r2.append(r4)     // Catch: java.lang.Exception -> L35
                r2.append(r1)     // Catch: java.lang.Exception -> L35
                java.lang.String r4 = r2.toString()     // Catch: java.lang.Exception -> L35
                com.xiaopeng.a.c.t(r6, r0, r4)     // Catch: java.lang.Exception -> L35
                goto L37
            L35:
                r4 = move-exception
                goto L3a
            L37:
                goto L3d
            L38:
                r4 = move-exception
                r1 = r0
            L3a:
                com.xiaopeng.a.c.d(r4)
            L3d:
                if (r1 != 0) goto L41
                java.lang.String r1 = "Unknown"
            L41:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.a.a.g.d(java.lang.String, java.lang.String, boolean):java.lang.String");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String getString(String str, String str2) {
            return d(str, str2, true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int ad(String str, String str2) {
            int i;
            try {
                i = Integer.parseInt(com.xiaopeng.a.b.qi().ah(str, str2));
            } catch (Exception e) {
                e = e;
                i = 0;
            }
            try {
                com.xiaopeng.a.c.t("Support", "Values.getInt", "id=" + str + ", value=" + i);
            } catch (Exception e2) {
                e = e2;
                com.xiaopeng.a.c.d(e);
                return i;
            }
            return i;
        }
    }
}
