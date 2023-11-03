package com.xiaopeng.libbluetooth;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import com.xiaopeng.b.b.b;

/* compiled from: GeneralControlBox.java */
/* loaded from: classes12.dex */
public class f extends com.xiaopeng.libbluetooth.a {
    private b XA;
    private a XB;
    private com.xiaopeng.b.b.b XC = new b.a() { // from class: com.xiaopeng.libbluetooth.f.1
        @Override // com.xiaopeng.b.b.b
        public void a(int i, Bundle bundle) {
            if (bundle == null) {
                return;
            }
            try {
                if (f.this.XA == null) {
                    return;
                }
                if (i == 9) {
                    boolean z = true;
                    if (bundle.getInt("Value") != 1) {
                        z = false;
                    }
                    boolean z2 = z;
                    f.this.XA.a(bundle.getString("Address"), z2, bundle.getInt("Linklost"), bundle.getInt("Profile"), bundle.getInt("Index"));
                } else if (i != 20) {
                    switch (i) {
                        case 5:
                            f.this.XA.onBtPower(bundle.getBoolean("Value"));
                            break;
                        case 6:
                            f.this.XA.f(bundle.getInt("Status"), bundle.getString("Address"));
                            break;
                        case 7:
                            f.this.XA.a(bundle.getString("Address"), bundle.getString("DeviceName"), bundle.getInt("PINCode", -1), bundle.getBoolean("SSP"));
                            break;
                    }
                } else {
                    f.this.XA.r(bundle.getString("Address", ""), bundle.getInt("Value", -1));
                }
            } catch (Exception e) {
                f.this.c(e);
            }
        }

        @Override // com.xiaopeng.b.b.b
        public void a(String str, String str2, int i, int i2, boolean z) {
            if (z) {
                try {
                    if (f.this.XB != null) {
                        f.this.XB.pB();
                        f.this.XB = null;
                    }
                } catch (Exception e) {
                    f.this.c(e);
                    return;
                }
            }
            if (f.this.XA == null) {
                return;
            }
            f.this.XA.a(str, str2, i, i2, z);
        }

        @Override // com.xiaopeng.b.b.b
        public void a(int i, byte[] bArr, int i2) throws RemoteException {
            if (f.this.XA == null) {
                return;
            }
            try {
                f.this.XA.a(i, bArr, i2);
            } catch (Exception e) {
                f.this.c(e);
            }
        }
    };
    private com.xiaopeng.b.b.a Xz;
    private Context mContext;

    /* compiled from: GeneralControlBox.java */
    /* loaded from: classes12.dex */
    public interface a {
        void pB();
    }

    public f(Context context, b bVar) {
        this.mContext = context;
        this.XA = bVar;
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void a(com.xiaopeng.b.a aVar) {
        try {
            this.Xz = aVar.qk();
            this.Xz.a(this.XC);
            if (this.XA != null) {
                this.XA.onBindSuccess();
            }
        } catch (Exception e) {
            c(e);
        }
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void pr() {
        this.Xz = null;
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void ps() {
    }

    @Override // com.xiaopeng.libbluetooth.a
    protected void release() {
        if (this.Xz != null) {
            try {
                this.Xz.b(this.XC);
            } catch (Exception e) {
                c(e);
            }
        }
    }

    public int dN() {
        try {
            if (this.Xz == null) {
                return 0;
            }
            return this.Xz.ey(e.getID());
        } catch (Exception e) {
            c(e);
            return 0;
        }
    }

    public boolean pA() {
        if (this.Xz == null) {
            return false;
        }
        try {
            this.Xz.eG(e.getID());
            return true;
        } catch (Exception e) {
            c(e);
            return false;
        }
    }

    public boolean a(int[] iArr, String[] strArr, String[] strArr2, int[] iArr2) {
        try {
            if (this.Xz == null) {
                return false;
            }
            return this.Xz.a(e.getID(), iArr, strArr, strArr2, iArr2);
        } catch (Exception e) {
            c(e);
            return false;
        }
    }

    public boolean a(int i, String[] strArr, String[] strArr2) {
        try {
            if (this.Xz == null) {
                return false;
            }
            return this.Xz.a(e.getID(), i, strArr, strArr2);
        } catch (Exception e) {
            c(e);
            return false;
        }
    }

    public boolean dO(String str) {
        if (this.Xz == null) {
            return false;
        }
        try {
            return this.Xz.an(e.getID(), str);
        } catch (Exception e) {
            c(e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Exception exc) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(exc);
        Log.e("GeneralControlBox", sb.toString() != null ? exc.getMessage() : "null");
    }
}
