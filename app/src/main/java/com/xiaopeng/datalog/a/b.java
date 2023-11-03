package com.xiaopeng.datalog.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounter;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IMoleEvent;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IMoleEventBuilder;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: CounterImpl.java */
/* loaded from: classes11.dex */
public class b implements Handler.Callback, ICounter {
    private final SharedPreferences.Editor mEditor;
    private Handler mHandler;
    private final String mName;
    private final long qk;
    private final Map<String, AtomicInteger> ql;
    private boolean qm;
    private AtomicLong qn;
    private final SharedPreferences qo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, String str, long j) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("name can't be empty!");
        }
        this.mName = str;
        this.qk = j;
        this.ql = new HashMap();
        this.qo = PreferenceManager.getDefaultSharedPreferences(context);
        this.mEditor = this.qo.edit();
        this.qn = new AtomicLong(this.qo.getLong(bh("time"), System.currentTimeMillis()));
        this.mHandler = new Handler(j.dR(0), this);
        eV();
        A(false);
    }

    private void eV() {
        Map<String, ?> all = this.qo.getAll();
        c.f("CounterImpl", "initValuesFromPref prefMap:" + all);
        for (String str : all.keySet()) {
            String bi = bi(str);
            if (!TextUtils.isEmpty(bi) && !bi.equals("time")) {
                Object obj = all.get(str);
                if (obj instanceof Integer) {
                    c.f("CounterImpl", "initValuesFromPref key:" + bi + " value:" + obj);
                    this.ql.put(bi, new AtomicInteger(((Integer) obj).intValue()));
                }
            }
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            if (this.qm) {
                c.f("CounterImpl", "mEditor.apply()");
            }
            this.mEditor.apply();
        }
        return true;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounter
    public void debug(boolean z) {
        this.qm = z;
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounter
    public synchronized int count(String str) {
        return count(str, 1);
    }

    @Override // com.xiaopeng.lib.framework.moduleinterface.datalogmodule.ICounter
    public synchronized int count(String str, int i) {
        int i2;
        int i3;
        AtomicInteger atomicInteger = this.ql.get(str);
        String bh = bh(str);
        if (atomicInteger == null) {
            try {
                i2 = this.qo.getInt(bh, 0);
            } catch (ClassCastException e) {
                c.b("CounterImpl", "mPreferences.getInt(" + bh + ") error!", e);
                i2 = 0;
            }
            if (this.qm) {
                c.e("CounterImpl", "count " + this.mName + ", load key:" + bh + " from pref, value is " + i2);
            }
            AtomicInteger atomicInteger2 = new AtomicInteger(i2);
            this.ql.put(str, atomicInteger2);
            atomicInteger = atomicInteger2;
        }
        i3 = atomicInteger.get() + i;
        atomicInteger.set(i3);
        this.mEditor.putInt(bh, i3);
        if (this.qm) {
            c.e("CounterImpl", "count " + this.mName + " " + str + ":" + i3 + ", cache is:" + this.ql);
        }
        boolean z = true;
        if (!this.mHandler.hasMessages(1)) {
            this.mHandler.sendEmptyMessageDelayed(1, 10000L);
        }
        if (i3 != Integer.MAX_VALUE) {
            z = false;
        }
        A(z);
        return i3;
    }

    private String bh(String str) {
        return this.mName + "_" + str;
    }

    private String bi(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = this.mName + "_";
        if (str.startsWith(str2)) {
            return str.substring(str2.length());
        }
        return null;
    }

    private void A(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        final long j = this.qn.get();
        if (j / this.qk < currentTimeMillis / this.qk || z) {
            final HashMap hashMap = new HashMap();
            for (String str : this.ql.keySet()) {
                AtomicInteger atomicInteger = this.ql.get(str);
                hashMap.put(str, Integer.valueOf(atomicInteger.get()));
                atomicInteger.set(0);
                this.mEditor.putInt(bh(str), 0);
            }
            j.a(2, new Runnable() { // from class: com.xiaopeng.datalog.a.b.1
                @Override // java.lang.Runnable
                public void run() {
                    c.g("CounterImpl", "[" + b.this.mName + " counter] send count event, name:" + b.this.mName + " values:" + hashMap);
                    IDataLog iDataLog = (IDataLog) Module.get(com.xiaopeng.datalog.a.class).get(IDataLog.class);
                    IMoleEventBuilder event = iDataLog.buildMoleEvent().setPageId("P00012").setButtonId("B001").setEvent(b.this.mName);
                    for (String str2 : hashMap.keySet()) {
                        event.setProperty(str2, (Number) hashMap.get(str2));
                    }
                    event.setProperty("time", Long.valueOf(j));
                    IMoleEvent build = event.build();
                    c.f("CounterImpl", "start sendStatData:" + build.toJson());
                    if (!com.xiaopeng.lib.utils.c.c.pq()) {
                        iDataLog.sendStatData(build);
                    }
                }
            });
            if (!this.mHandler.hasMessages(1)) {
                this.mHandler.sendEmptyMessageDelayed(1, 10000L);
            }
        }
        this.qn.set(currentTimeMillis);
        this.mEditor.putLong(bh("time"), currentTimeMillis);
    }
}
