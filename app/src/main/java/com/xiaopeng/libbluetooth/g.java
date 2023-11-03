package com.xiaopeng.libbluetooth;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.xiaopeng.b.c.b;

/* compiled from: PhoneControlBox.java */
/* loaded from: classes12.dex */
public class g extends a {
    private com.xiaopeng.b.c.a XF;
    private c XG;
    private com.xiaopeng.b.c.b XH = new b.a() { // from class: com.xiaopeng.libbluetooth.g.1
        @Override // com.xiaopeng.b.c.b
        public void a(int i, Bundle bundle) {
            if (g.this.XG == null || bundle == null) {
                return;
            }
            try {
                switch (i) {
                    case 1:
                        String string = bundle.getString("CallerNumber");
                        String string2 = bundle.getString("CallerName");
                        g.this.XG.c(bundle.getInt("Value"), string, string2);
                        break;
                    case 2:
                        g.this.XG.dS(bundle.getInt("Value"));
                        break;
                    case 3:
                        String string3 = bundle.getString("text");
                        String string4 = bundle.getString("date");
                        g.this.XG.s(bundle.getString("number"), string4, string3);
                        break;
                    case 4:
                        g.this.XG.v(bundle.getInt("indKey"), bundle.getInt("indValue"));
                        break;
                    default:
                        return;
                }
            } catch (Exception e) {
                g.this.c(e);
            }
        }

        @Override // com.xiaopeng.b.c.b
        public void c(int i, int i2, int i3, int i4, int i5, String str, String str2, String str3) {
            if (g.this.XG == null) {
                return;
            }
            try {
                g.this.XG.a(i, i2, i3, i4, i5, str, str2, str3);
            } catch (Exception e) {
                g.this.c(e);
            }
        }

        @Override // com.xiaopeng.b.c.b
        public void b(int i, int i2, int i3, int i4, int i5, String str, String str2, String str3) {
            if (g.this.XG == null) {
                return;
            }
            try {
                g.this.XG.b(i, i2, i3, i4, i5, str, str2, str3);
            } catch (Exception e) {
                g.this.c(e);
            }
        }

        @Override // com.xiaopeng.b.c.b
        public void a(int i, int i2, int i3, int i4, String str, String str2, String str3) {
            if (g.this.XG == null) {
                return;
            }
            try {
                g.this.XG.a(i, i2, i3, i4, str, str2, str3);
            } catch (Exception e) {
                g.this.c(e);
            }
        }

        @Override // com.xiaopeng.b.c.b
        public void h(int i, String str) {
            if (g.this.XG == null) {
                return;
            }
            try {
                g.this.XG.g(i, str);
            } catch (Exception e) {
                g.this.c(e);
            }
        }
    };
    private Context mContext;

    public g(Context context, c cVar) {
        this.mContext = context;
        this.XG = cVar;
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void a(com.xiaopeng.b.a aVar) {
        try {
            this.XF = aVar.qm();
            this.XF.a(this.XH);
            if (this.XG != null) {
                this.XG.onBindSuccess();
            }
        } catch (Exception e) {
            c(e);
        }
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void pr() {
        this.XF = null;
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void ps() {
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void release() {
        if (this.XF != null) {
            try {
                this.XF.b(this.XH);
            } catch (Exception e) {
                c(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Exception exc) {
        Log.e("PhoneControlBox", exc != null ? exc.getMessage() : "null");
    }
}
