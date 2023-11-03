package com.xiaopeng.devtools.model.c;

import android.os.AsyncTask;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/* compiled from: LcdDebugModel.java */
/* loaded from: classes12.dex */
public class d {
    private com.xiaopeng.devtools.presenter.factorytest.a sj;

    public d(com.xiaopeng.devtools.presenter.factorytest.a aVar) {
        this.sj = aVar;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.xiaopeng.devtools.model.c.d$1] */
    public void fJ() {
        new AsyncTask<Void, Void, Integer>() { // from class: com.xiaopeng.devtools.model.c.d.1
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Removed duplicated region for block: B:37:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x002f -> B:39:0x005f). Please submit an issue!!! */
            @Override // android.os.AsyncTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.lang.Integer doInBackground(java.lang.Void... r6) {
                /*
                    r5 = this;
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>()
                    r0 = 0
                    java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
                    java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
                    java.lang.String r3 = "/sys/class/disp/disp/attr/lcdclk"
                    r2.<init>(r3)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
                    r1.<init>(r2)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L38
                    java.lang.String r0 = r1.readLine()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L70
                L17:
                    boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L70
                    if (r2 != 0) goto L29
                    java.lang.String r0 = r0.trim()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L70
                    r6.append(r0)     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L70
                    java.lang.String r0 = r1.readLine()     // Catch: java.lang.Exception -> L33 java.lang.Throwable -> L70
                    goto L17
                L29:
                    r1.close()     // Catch: java.io.IOException -> L2e
                L2d:
                    goto L5f
                L2e:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L2d
                L33:
                    r0 = move-exception
                    goto L3c
                L35:
                    r6 = move-exception
                    r1 = r0
                    goto L71
                L38:
                    r1 = move-exception
                    r4 = r1
                    r1 = r0
                    r0 = r4
                L3c:
                    r0.printStackTrace()     // Catch: java.lang.Throwable -> L70
                    java.lang.String r2 = "LcdDebugModel"
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70
                    r3.<init>()     // Catch: java.lang.Throwable -> L70
                    java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L70
                    r3.append(r0)     // Catch: java.lang.Throwable -> L70
                    java.lang.String r0 = ""
                    r3.append(r0)     // Catch: java.lang.Throwable -> L70
                    java.lang.String r0 = r3.toString()     // Catch: java.lang.Throwable -> L70
                    com.xiaopeng.lib.utils.c.i(r2, r0)     // Catch: java.lang.Throwable -> L70
                    if (r1 == 0) goto L5f
                    r1.close()     // Catch: java.io.IOException -> L2e
                    goto L2d
                L5f:
                    java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> L68
                    java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Exception -> L68
                    return r6
                L68:
                    r6 = move-exception
                    r6 = 40
                    java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
                    return r6
                L70:
                    r6 = move-exception
                L71:
                    if (r1 == 0) goto L7b
                    r1.close()     // Catch: java.io.IOException -> L77
                    goto L7b
                L77:
                    r0 = move-exception
                    r0.printStackTrace()
                L7b:
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.model.c.d.AnonymousClass1.doInBackground(java.lang.Void[]):java.lang.Integer");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Integer num) {
                super.onPostExecute((AnonymousClass1) num);
                d.this.sj.cl(num.intValue());
            }
        }.execute(new Void[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void bW(int i) {
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                try {
                    OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream("/sys/class/disp/disp/attr/lcdclk"));
                    try {
                        StringBuilder sb = new StringBuilder();
                        sb.append(i);
                        sb.append("");
                        outputStreamWriter2.write(sb.toString());
                        outputStreamWriter2.flush();
                        outputStreamWriter2.close();
                        outputStreamWriter = sb;
                    } catch (Exception e) {
                        e = e;
                        outputStreamWriter = outputStreamWriter2;
                        e.printStackTrace();
                        com.xiaopeng.lib.utils.c.i("LcdDebugModel", e.getMessage() + "");
                        if (outputStreamWriter != null) {
                            outputStreamWriter.close();
                            outputStreamWriter = outputStreamWriter;
                        }
                    } catch (Throwable th) {
                        th = th;
                        outputStreamWriter = outputStreamWriter2;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }
}
