package com.alibaba.sdk.android.man.crashreporter.a.a;

import android.content.Context;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.alibaba.sdk.android.man.crashreporter.a.a.a.a.d;
import com.alibaba.sdk.android.man.crashreporter.a.a.a.a.e;
import com.alibaba.sdk.android.man.crashreporter.d.c;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* loaded from: classes11.dex */
public final class a implements com.alibaba.sdk.android.man.crashreporter.a.a.b {

    /* renamed from: a  reason: collision with other field name */
    private b f58a = new b();
    private C0019a a = new C0019a();

    /* renamed from: a  reason: collision with other field name */
    private com.alibaba.sdk.android.man.crashreporter.a.a.a.a.a f59a = null;

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.b
    public void a(ReporterConfigure reporterConfigure, Context context, c cVar, com.alibaba.sdk.android.man.crashreporter.a.b bVar) {
        try {
            if (context != null) {
                this.f58a.a(new com.alibaba.sdk.android.man.crashreporter.a.a.a.a.c());
                this.f58a.a(new e(context));
                if (reporterConfigure.enableActivityMonitor) {
                    this.f59a = new com.alibaba.sdk.android.man.crashreporter.a.a.a.a.a(context, cVar, bVar);
                    this.f58a.a(this.f59a);
                }
                if (reporterConfigure.enableDumpSysLog) {
                    this.f58a.a(new d(com.alibaba.sdk.android.man.crashreporter.global.a.SYS_LOG));
                }
                if (reporterConfigure.enableDumpEventsLog) {
                    this.f58a.a(new d(com.alibaba.sdk.android.man.crashreporter.global.a.EVENTS_LOG));
                }
                if (reporterConfigure.enableDumpRadioLog) {
                    this.f58a.a(new d(com.alibaba.sdk.android.man.crashreporter.global.a.RADIO_LOG));
                }
                return;
            }
            com.alibaba.sdk.android.man.crashreporter.b.a.e("init collector failure!");
        } catch (Exception e) {
            com.alibaba.sdk.android.man.crashreporter.b.a.d("init collector err!", e);
        }
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.b
    /* renamed from: b  reason: collision with other method in class */
    public Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> mo43b() {
        EnumMap enumMap = new EnumMap(com.alibaba.sdk.android.man.crashreporter.global.a.class);
        this.f58a.a(enumMap);
        return enumMap;
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.b
    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        this.a.a(hashMap);
        return hashMap;
    }

    public void a(com.alibaba.sdk.android.man.crashreporter.a.a.a.a aVar) {
        this.a.a(aVar);
    }

    @Override // com.alibaba.sdk.android.man.crashreporter.a.a.b
    public String b() {
        if (this.f59a != null) {
            return this.f59a.b();
        }
        return "";
    }

    /* renamed from: com.alibaba.sdk.android.man.crashreporter.a.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    final class C0019a implements com.alibaba.sdk.android.man.crashreporter.a.a.a.a {

        /* renamed from: a  reason: collision with other field name */
        public LinkedList<com.alibaba.sdk.android.man.crashreporter.a.a.a.a> f60a = new LinkedList<>();

        C0019a() {
        }

        public void a(com.alibaba.sdk.android.man.crashreporter.a.a.a.a aVar) {
            if (aVar != null) {
                this.f60a.add(aVar);
            }
        }

        @Override // com.alibaba.sdk.android.man.crashreporter.a.a.a.c
        public void a(Map<String, String> map) {
            Iterator<com.alibaba.sdk.android.man.crashreporter.a.a.a.a> it = this.f60a.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(map);
                } catch (Exception e) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("External collect error.", e);
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    final class b implements com.alibaba.sdk.android.man.crashreporter.a.a.a.b {

        /* renamed from: a  reason: collision with other field name */
        public LinkedList<com.alibaba.sdk.android.man.crashreporter.a.a.a.b> f65a = new LinkedList<>();

        b() {
        }

        public void a(com.alibaba.sdk.android.man.crashreporter.a.a.a.b bVar) {
            if (bVar != null) {
                this.f65a.add(bVar);
            }
        }

        @Override // com.alibaba.sdk.android.man.crashreporter.a.a.a.c
        public void a(Map<com.alibaba.sdk.android.man.crashreporter.global.a, String> map) {
            Iterator<com.alibaba.sdk.android.man.crashreporter.a.a.a.b> it = this.f65a.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(map);
                } catch (Exception e) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("Internal collect error.", e);
                }
            }
        }
    }
}
