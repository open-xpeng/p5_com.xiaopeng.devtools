package com.xiaopeng.devtools.model.c;

import android.os.AsyncTask;
import android.os.SystemProperties;
import com.xiaopeng.devtools.bean.event.RebootPolicyEvent;
import org.greenrobot.eventbus.EventBus;

/* compiled from: TestRebootModel.java */
/* loaded from: classes12.dex */
public class e {

    /* compiled from: TestRebootModel.java */
    /* loaded from: classes12.dex */
    public interface b {
        void bY(int i);
    }

    private e() {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.xiaopeng.devtools.model.c.e$1] */
    public void a(final b bVar) {
        new AsyncTask<Void, Void, Integer>() { // from class: com.xiaopeng.devtools.model.c.e.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Integer doInBackground(Void... voidArr) {
                return Integer.valueOf(SystemProperties.getInt("persist.service.panic", -1));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Integer num) {
                super.onPostExecute((AnonymousClass1) num);
                if (bVar != null) {
                    bVar.bY(num.intValue());
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.xiaopeng.devtools.model.c.e$2] */
    public void bX(int i) {
        new AsyncTask<Integer, Void, Boolean>() { // from class: com.xiaopeng.devtools.model.c.e.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: a */
            public Boolean doInBackground(Integer... numArr) {
                try {
                    SystemProperties.set("persist.service.panic", String.valueOf(numArr[0]));
                    return true;
                } catch (Exception e) {
                    com.xiaopeng.lib.utils.c.i("TestRebootModel", e.getMessage() + "");
                    return false;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: a */
            public void onPostExecute(Boolean bool) {
                super.onPostExecute(bool);
                EventBus.getDefault().post(new RebootPolicyEvent(bool.booleanValue()));
            }
        }.execute(Integer.valueOf(i));
    }

    public static e fK() {
        return a.sn;
    }

    /* compiled from: TestRebootModel.java */
    /* loaded from: classes12.dex */
    private static class a {
        private static final e sn = new e();
    }
}
