package android.pso;

import android.content.Context;
import android.util.Log;

/* loaded from: classes.dex */
public class XpPsoException extends Exception {
    private int a;

    public XpPsoException() {
    }

    public XpPsoException(Context context, final String str, final int i) {
        super(str);
        this.a = i;
        if (XpPso.isBleService) {
            return;
        }
        new Thread(new Runnable() { // from class: android.pso.XpPsoException.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (Diagnosis.isDiagnosisCode(i)) {
                        String str2 = str + "(XpPsoException=" + i + ")";
                        if (str2 != null) {
                            str2 = str2.replace("\r", "-").replace("\n", "-");
                        }
                        Diagnosis.recordDiagnosisError(i, str2, true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("XpPsoException", e.toString());
                }
            }
        }).start();
    }

    public XpPsoException(String str) {
        super(str);
    }

    public int getValue() {
        return this.a;
    }
}
