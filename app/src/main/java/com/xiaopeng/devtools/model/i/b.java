package com.xiaopeng.devtools.model.i;

import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import com.xiaopeng.a.a;
import com.xiaopeng.commonfunc.utils.l;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.system.b.c;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.utils.r;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/* compiled from: UserSettingsModel.java */
/* loaded from: classes12.dex */
public class b implements com.xiaopeng.devtools.model.i.a {
    private b() {
    }

    public static b hN() {
        return C0065b.uT;
    }

    /* compiled from: UserSettingsModel.java */
    /* renamed from: com.xiaopeng.devtools.model.i.b$b  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    private static class C0065b {
        private static final b uT = new b();
    }

    @Override // com.xiaopeng.devtools.model.i.a
    public boolean hL() {
        return r.hL();
    }

    @Override // com.xiaopeng.devtools.model.i.a
    public void F(boolean z) {
        r.F(z);
        if (!hM()) {
            setDebugStatus(true);
            c.sleep(3000L);
            setDebugStatus(false);
            c.sleep(3000L);
        }
    }

    @Override // com.xiaopeng.devtools.model.i.a
    public boolean hM() {
        return a.e.getString("USB_MODE_PERIPHERAL").equalsIgnoreCase(g.cs(a.c.ec("SWITCH_DEBUG_FILE")));
    }

    @Override // com.xiaopeng.devtools.model.i.a
    public void setDebugStatus(boolean z) {
        if (!r.lv()) {
            if ((Settings.Secure.getInt(MyApplication.getContext().getContentResolver(), "adb_enabled", 0) > 0) != z) {
                Settings.Secure.putInt(MyApplication.getContext().getContentResolver(), "adb_enabled", z ? 1 : 0);
            }
        }
        r.U(z);
        l.ba("sync");
    }

    @Override // com.xiaopeng.devtools.model.i.a
    public void G(boolean z) {
        com.xiaopeng.lib.utils.c.f("UserSettingsModel", "setAutoCatchLog " + z);
        r.W(z);
        if (z) {
            r.cJ("catch_log_d");
        } else {
            r.cK("catch_log_d");
        }
    }

    @Override // com.xiaopeng.devtools.model.i.a
    public void H(boolean z) {
        com.xiaopeng.lib.utils.c.f("UserSettingsModel", "setShowDialog " + z);
        r.H(z);
    }

    @Override // com.xiaopeng.devtools.model.i.a
    public void bH(String str) {
        new Thread(new a(str), "UploadCanThread").start();
    }

    /* compiled from: UserSettingsModel.java */
    /* loaded from: classes12.dex */
    private class a implements Runnable {
        private String mType;
        private int uR = SystemProperties.getInt("sys.mcudebug.port", 18881);

        public a(String str) {
            this.mType = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Socket socket;
            BufferedWriter bufferedWriter = null;
            try {
                try {
                    InetAddress byName = InetAddress.getByName("127.0.0.1");
                    socket = new Socket();
                    try {
                        try {
                            socket.connect(new InetSocketAddress(byName, this.uR), 5000);
                            BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            try {
                                bufferedWriter2.write("CAN send " + this.mType + ".");
                                bufferedWriter2.flush();
                                try {
                                    bufferedWriter2.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                socket.close();
                            } catch (Exception e2) {
                                e = e2;
                                bufferedWriter = bufferedWriter2;
                                Log.e("UserSettingsModel", e.getMessage());
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                if (socket != null) {
                                    socket.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                bufferedWriter = bufferedWriter2;
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                if (socket != null) {
                                    try {
                                        socket.close();
                                    } catch (IOException e5) {
                                        Log.e("UserSettingsModel", e5.getMessage());
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Exception e6) {
                        e = e6;
                    }
                } catch (Exception e7) {
                    e = e7;
                    socket = null;
                } catch (Throwable th3) {
                    th = th3;
                    socket = null;
                }
            } catch (IOException e8) {
                Log.e("UserSettingsModel", e8.getMessage());
            }
        }
    }
}
