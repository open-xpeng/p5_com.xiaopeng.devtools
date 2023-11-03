package com.ta.utdid2.c.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.ta.utdid2.b.a.i;
import com.ta.utdid2.c.a.b;
import java.io.File;
import java.util.Map;

/* compiled from: PersistentConfiguration.java */
/* loaded from: classes11.dex */
public class c {

    /* renamed from: a  reason: collision with other field name */
    private SharedPreferences f130a;

    /* renamed from: a  reason: collision with other field name */
    private b f132a;

    /* renamed from: a  reason: collision with other field name */
    private d f133a;
    private String e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private Context mContext;
    private SharedPreferences.Editor a = null;

    /* renamed from: a  reason: collision with other field name */
    private b.a f131a = null;

    /* JADX WARN: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01ac A[Catch: Exception -> 0x01bb, TRY_LEAVE, TryCatch #3 {Exception -> 0x01bb, blocks: (B:87:0x01a8, B:89:0x01ac), top: B:101:0x01a8 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public c(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 445
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.c.a.c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    private d a(String str) {
        File m76a = m76a(str);
        if (m76a != null) {
            this.f133a = new d(m76a.getAbsolutePath());
            return this.f133a;
        }
        return null;
    }

    /* renamed from: a  reason: collision with other method in class */
    private File m76a(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            File file = new File(String.format("%s%s%s", externalStorageDirectory.getAbsolutePath(), File.separator, str));
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    private void a(SharedPreferences sharedPreferences, b bVar) {
        b.a a;
        if (sharedPreferences != null && bVar != null && (a = bVar.a()) != null) {
            a.b();
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    a.a(key, (String) value);
                } else if (value instanceof Integer) {
                    a.a(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    a.a(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    a.a(key, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    a.a(key, ((Boolean) value).booleanValue());
                }
            }
            a.commit();
        }
    }

    private void a(b bVar, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit;
        if (bVar != null && sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
            edit.clear();
            for (Map.Entry<String, ?> entry : bVar.getAll().entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value instanceof String) {
                    edit.putString(key, (String) value);
                } else if (value instanceof Integer) {
                    edit.putInt(key, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    edit.putLong(key, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    edit.putFloat(key, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    edit.putBoolean(key, ((Boolean) value).booleanValue());
                }
            }
            edit.commit();
        }
    }

    private boolean b() {
        if (this.f132a != null) {
            boolean mo75a = this.f132a.mo75a();
            if (!mo75a) {
                commit();
            }
            return mo75a;
        }
        return false;
    }

    private void c() {
        if (this.a == null && this.f130a != null) {
            this.a = this.f130a.edit();
        }
        if (this.i && this.f131a == null && this.f132a != null) {
            this.f131a = this.f132a.a();
        }
        b();
    }

    public void putString(String str, String str2) {
        if (!i.m74a(str) && !str.equals("t")) {
            c();
            if (this.a != null) {
                this.a.putString(str, str2);
            }
            if (this.f131a != null) {
                this.f131a.a(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!i.m74a(str) && !str.equals("t")) {
            c();
            if (this.a != null) {
                this.a.remove(str);
            }
            if (this.f131a != null) {
                this.f131a.a(str);
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(4:3|(1:7)|8|(10:10|11|(1:15)|16|17|18|19|20|(4:22|(2:24|(2:26|(3:28|(1:30)(1:32)|31))(2:33|(1:37)))|38|(3:44|45|(1:47)))|50))|55|11|(2:13|15)|16|17|18|19|20|(0)|50) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0041, code lost:
        r2.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean commit() {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r5.a
            r3 = 0
            if (r2 == 0) goto L24
            boolean r2 = r5.j
            if (r2 != 0) goto L19
            android.content.SharedPreferences r2 = r5.f130a
            if (r2 == 0) goto L19
            android.content.SharedPreferences$Editor r2 = r5.a
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L19:
            android.content.SharedPreferences$Editor r0 = r5.a
            boolean r0 = r0.commit()
            if (r0 != 0) goto L24
        L22:
            r0 = r3
            goto L25
        L24:
            r0 = 1
        L25:
            android.content.SharedPreferences r1 = r5.f130a
            if (r1 == 0) goto L38
            android.content.Context r1 = r5.mContext
            if (r1 == 0) goto L38
            android.content.Context r1 = r5.mContext
            java.lang.String r2 = r5.e
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r5.f130a = r1
        L38:
            r1 = 0
            java.lang.String r2 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Exception -> L40
            r1 = r2
            goto L44
        L40:
            r2 = move-exception
            r2.printStackTrace()
        L44:
            boolean r2 = com.ta.utdid2.b.a.i.m74a(r1)
            if (r2 != 0) goto Lb9
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L93
            com.ta.utdid2.c.a.b r2 = r5.f132a
            if (r2 != 0) goto L85
        L57:
            java.lang.String r2 = r5.f
            com.ta.utdid2.c.a.d r2 = r5.a(r2)
            if (r2 == 0) goto L93
            java.lang.String r4 = r5.e
            com.ta.utdid2.c.a.b r2 = r2.a(r4, r3)
            r5.f132a = r2
            boolean r2 = r5.j
            if (r2 != 0) goto L75
            android.content.SharedPreferences r2 = r5.f130a
            com.ta.utdid2.c.a.b r4 = r5.f132a
            r5.a(r2, r4)
            goto L7c
        L75:
            com.ta.utdid2.c.a.b r2 = r5.f132a
            android.content.SharedPreferences r4 = r5.f130a
            r5.a(r2, r4)
        L7c:
            com.ta.utdid2.c.a.b r2 = r5.f132a
            com.ta.utdid2.c.a.b$a r2 = r2.a()
            r5.f131a = r2
            goto L93
        L85:
            com.ta.utdid2.c.a.b$a r2 = r5.f131a
            if (r2 == 0) goto L93
            com.ta.utdid2.c.a.b$a r2 = r5.f131a
            boolean r2 = r2.commit()
            if (r2 != 0) goto L93
        L92:
            r0 = r3
        L93:
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto La8
        L9c:
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto Lb9
            com.ta.utdid2.c.a.b r1 = r5.f132a
            if (r1 == 0) goto Lb9
        La8:
            com.ta.utdid2.c.a.d r1 = r5.f133a     // Catch: java.lang.Exception -> Lb8
            if (r1 == 0) goto Lb9
            com.ta.utdid2.c.a.d r1 = r5.f133a     // Catch: java.lang.Exception -> Lb8
            java.lang.String r2 = r5.e     // Catch: java.lang.Exception -> Lb8
            com.ta.utdid2.c.a.b r1 = r1.a(r2, r3)     // Catch: java.lang.Exception -> Lb8
            r5.f132a = r1     // Catch: java.lang.Exception -> Lb8
            goto Lb9
        Lb8:
            r1 = move-exception
        Lb9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.c.a.c.commit():boolean");
    }

    public String getString(String str) {
        b();
        if (this.f130a != null) {
            String string = this.f130a.getString(str, "");
            if (!i.m74a(string)) {
                return string;
            }
        }
        if (this.f132a != null) {
            return this.f132a.getString(str, "");
        }
        return "";
    }
}
