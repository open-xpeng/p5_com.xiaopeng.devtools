package android.pso;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import com.xiaopeng.aftersales.AfterSalesManager;

/* loaded from: classes.dex */
public class Diagnosis {
    private static Context a = null;
    private static AfterSalesManager b = null;
    private static com.xiaopeng.aftersales.manager.AfterSalesManager c = null;
    private static ServiceConnection d = new ServiceConnection() { // from class: android.pso.Diagnosis.3
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("Diagnosis", "AfterSales onServiceConnected, name: " + componentName + ", service: " + iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("Diagnosis", "AfterSales onServiceDisconnected, name: " + componentName);
        }
    };
    private static int[] e = {-7, -98, -2, -14, -15, -41};

    @SuppressLint({"WrongConstant"})
    private static void a(Context context) {
        try {
            if (a == null) {
                a = context;
            }
            if (c == null) {
                c = com.xiaopeng.aftersales.manager.AfterSalesManager.createAfterSalesManager(a, d);
                if (c != null) {
                    c.connect();
                }
            }
        } catch (Exception e2) {
            Log.e("Diagnosis", "Diagnosis getSystemService:" + e2.toString());
            c = null;
        }
    }

    @SuppressLint({"WrongConstant"})
    private static void b(Context context) {
        try {
            if (a == null) {
                a = context;
            }
            if (b == null) {
                b = (AfterSalesManager) context.getSystemService("xiaopeng_aftersales");
            }
        } catch (Exception e2) {
            Log.e("Diagnosis", "Diagnosis getSystemService:" + e2.toString());
            b = null;
        }
    }

    @SuppressLint({"WrongConstant"})
    public static void init(Context context) {
        if (Build.VERSION.SDK_INT <= 28) {
            b(context);
        } else {
            a(context);
        }
    }

    public static boolean isDiagnosisCode(int i) {
        for (int i2 = 0; i2 < e.length; i2++) {
            if (e[i2] == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean recordBleDiagnosisError(int i, final String str, final boolean z) {
        String str2;
        String str3;
        final int i2 = i + 5100;
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                Log.e("Diagnosis", "Diagnosis pso ble errorCode=" + i2 + "(" + i + "),errorMsg=" + str);
                if (b == null) {
                    return true;
                }
                b.recordDiagnosisError(5, i2, System.currentTimeMillis(), str, z);
                str2 = "Diagnosis";
                str3 = "record Ble Diagnosis recorded!";
            } else {
                Log.e("Diagnosis", "Diagnosis pso ble errorCode=" + i2 + "(" + i + "),errorMsg=" + str);
                if (c != null && !c.isConnected()) {
                    new Thread(new Runnable() { // from class: android.pso.Diagnosis.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Thread.sleep(1000L);
                                if (Diagnosis.c == null || !Diagnosis.c.isConnected()) {
                                    return;
                                }
                                Diagnosis.c.recordDiagnosisError(5, i2, System.currentTimeMillis(), str, z);
                                Log.e("Diagnosis", "record Ble Diagnosis recorded!");
                            } catch (InterruptedException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }).start();
                }
                if (c == null || !c.isConnected()) {
                    return true;
                }
                c.recordDiagnosisError(5, i2, System.currentTimeMillis(), str, z);
                str2 = "Diagnosis";
                str3 = "record Ble Diagnosis recorded!";
            }
            Log.e(str2, str3);
            return true;
        } catch (Exception e2) {
            Log.e("Diagnosis", "record Ble Diagnosis Error:" + e2.toString());
            return false;
        }
    }

    public static boolean recordDiagnosisError(int i, final String str, final boolean z) {
        String str2;
        String str3;
        final int i2 = i + 5500;
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                Log.e("Diagnosis", "Diagnosis pso errorCode=" + i2 + "(" + i + "),errorMsg=" + str);
                if (b == null) {
                    return true;
                }
                b.recordDiagnosisError(5, i2, System.currentTimeMillis(), str, z);
                str2 = "Diagnosis";
                str3 = "Diagnosis Error recorded!";
            } else {
                Log.e("Diagnosis", "Diagnosis pso errorCode=" + i2 + "(" + i + "),errorMsg=" + str);
                if (c != null && !c.isConnected()) {
                    new Thread(new Runnable() { // from class: android.pso.Diagnosis.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                Thread.sleep(1000L);
                                if (Diagnosis.c == null || !Diagnosis.c.isConnected()) {
                                    return;
                                }
                                Diagnosis.c.recordDiagnosisError(5, i2, System.currentTimeMillis(), str, z);
                                Log.e("Diagnosis", "Diagnosis Error recorded!");
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }).start();
                    return true;
                } else if (c == null || !c.isConnected()) {
                    return true;
                } else {
                    c.recordDiagnosisError(5, i2, System.currentTimeMillis(), str, z);
                    str2 = "Diagnosis";
                    str3 = "Diagnosis Error recorded!";
                }
            }
            Log.e(str2, str3);
            return true;
        } catch (Exception e2) {
            Log.e("Diagnosis", "record Diagnosis Error:" + e2.toString());
            return false;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (Build.VERSION.SDK_INT > 28 && c != null && c.isConnected()) {
                c.disconnect();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        super.finalize();
    }
}
