package com.amap.api.services.a;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: BinaryRequest.java */
/* loaded from: classes11.dex */
public abstract class bc extends bh {
    protected Context a;
    protected l eL;

    public abstract byte[] a();

    public abstract byte[] bi();

    public boolean aD() {
        return true;
    }

    public bc(Context context, l lVar) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        this.eL = lVar;
    }

    @Override // com.amap.api.services.a.bh
    public Map<String, String> b() {
        String f = d.f(this.a);
        String a = f.a();
        Context context = this.a;
        String a2 = f.a(context, a, "key=" + f);
        HashMap hashMap = new HashMap();
        hashMap.put("ts", a);
        hashMap.put("key", f);
        hashMap.put("scode", a2);
        return hashMap;
    }

    @Override // com.amap.api.services.a.bh
    public final byte[] bk() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(bx());
            byteArrayOutputStream.write(by());
            byteArrayOutputStream.write(bz());
            byteArrayOutputStream.write(bA());
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                o.a(th, "BinaryRequest", "getEntityBytes");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                o.a(th2, "BinaryRequest", "getEntityBytes");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th3) {
                    o.a(th3, "BinaryRequest", "getEntityBytes");
                    return null;
                }
            } catch (Throwable th4) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th5) {
                    o.a(th5, "BinaryRequest", "getEntityBytes");
                }
                throw th4;
            }
        }
    }

    private byte[] bx() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(m.a("PANDORA$"));
            byteArrayOutputStream.write(new byte[]{1});
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                o.a(th, "BinaryRequest", "getBinaryHead");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                o.a(th2, "BinaryRequest", "getBinaryHead");
                try {
                    byteArrayOutputStream.close();
                    return null;
                } catch (Throwable th3) {
                    o.a(th3, "BinaryRequest", "getBinaryHead");
                    return null;
                }
            } catch (Throwable th4) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th5) {
                    o.a(th5, "BinaryRequest", "getBinaryHead");
                }
                throw th4;
            }
        }
    }

    public byte[] by() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(new byte[]{3});
            if (aD()) {
                byte[] a = f.a(this.a, false);
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
            } else {
                byteArrayOutputStream.write(new byte[]{0, 0});
            }
            byte[] a2 = m.a(e());
            if (a2 == null || a2.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(a(a2));
                byteArrayOutputStream.write(a2);
            }
            byte[] a3 = m.a(j());
            if (a3 == null || a3.length <= 0) {
                byteArrayOutputStream.write(new byte[]{0, 0});
            } else {
                byteArrayOutputStream.write(a(a3));
                byteArrayOutputStream.write(a3);
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                o.a(th, "BinaryRequest", "getRequestEncryptData");
            }
            return byteArray;
        } catch (Throwable th2) {
            try {
                o.a(th2, "BinaryRequest", "getPublicData");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th3) {
                    o.a(th3, "BinaryRequest", "getRequestEncryptData");
                }
                return new byte[]{0};
            } catch (Throwable th4) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th5) {
                    o.a(th5, "BinaryRequest", "getRequestEncryptData");
                }
                throw th4;
            }
        }
    }

    public String j() {
        return String.format("platform=Android&sdkversion=%s&product=%s", this.eL.b(), this.eL.a());
    }

    protected String e() {
        return "2.1";
    }

    protected byte[] a(byte[] bArr) {
        int length = bArr.length;
        return new byte[]{(byte) (length / 256), (byte) (length % 256)};
    }

    private byte[] bz() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] a = a();
            if (a != null && a.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    o.a(th, "BinaryRequest", "getRequestRawData");
                }
                return byteArray;
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                o.a(th2, "BinaryRequest", "getRequestRawData");
            }
            return byteArray2;
        } catch (Throwable th3) {
            try {
                o.a(th3, "BinaryRequest", "getRequestRawData");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    o.a(th4, "BinaryRequest", "getRequestRawData");
                }
                return new byte[]{0};
            } catch (Throwable th5) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th6) {
                    o.a(th6, "BinaryRequest", "getRequestRawData");
                }
                throw th5;
            }
        }
    }

    private byte[] bA() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bi = bi();
            if (bi != null && bi.length != 0) {
                byteArrayOutputStream.write(new byte[]{1});
                byte[] a = f.a(this.a, bi);
                byteArrayOutputStream.write(a(a));
                byteArrayOutputStream.write(a);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th) {
                    o.a(th, "BinaryRequest", "getRequestEncryptData");
                }
                return byteArray;
            }
            byteArrayOutputStream.write(new byte[]{0});
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                o.a(th2, "BinaryRequest", "getRequestEncryptData");
            }
            return byteArray2;
        } catch (Throwable th3) {
            try {
                o.a(th3, "BinaryRequest", "getRequestEncryptData");
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th4) {
                    o.a(th4, "BinaryRequest", "getRequestEncryptData");
                }
                return new byte[]{0};
            } catch (Throwable th5) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th6) {
                    o.a(th6, "BinaryRequest", "getRequestEncryptData");
                }
                throw th5;
            }
        }
    }
}
