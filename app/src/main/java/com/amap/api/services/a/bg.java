package com.amap.api.services.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.net.Proxy;

/* compiled from: NetManger.java */
/* loaded from: classes11.dex */
public class bg extends bb {
    private static bg fN;
    private Handler ek;
    private bo fO;

    public static bg k(boolean z) {
        return a(z, 5);
    }

    private static synchronized bg a(boolean z, int i) {
        bg bgVar;
        synchronized (bg.class) {
            try {
                if (fN == null) {
                    fN = new bg(z, i);
                } else if (z && fN.fO == null) {
                    fN.fO = bo.N(i);
                }
                bgVar = fN;
            }
        }
        return bgVar;
    }

    private bg(boolean z, int i) {
        if (z) {
            try {
                this.fO = bo.N(i);
            } catch (Throwable th) {
                r.b(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.ek = new a(Looper.getMainLooper(), null);
        } else {
            this.ek = new a();
        }
    }

    /* compiled from: NetManger.java */
    /* renamed from: com.amap.api.services.a.bg$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    class AnonymousClass1 extends bp {
        final /* synthetic */ bh fP;
        final /* synthetic */ bi fQ;
        final /* synthetic */ bg fR;

        @Override // com.amap.api.services.a.bp
        public void a() {
            try {
                this.fR.a(this.fR.b(this.fP, false), this.fQ);
            } catch (au e) {
                this.fR.a(e, this.fQ);
            }
        }
    }

    @Override // com.amap.api.services.a.bb
    public byte[] b(bh bhVar) throws au {
        try {
            bj a2 = a(bhVar, false);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (au e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            r.bn().c(th, "NetManager", "makeSyncPostRequest");
            throw new au("未知的错误");
        }
    }

    public bj b(bh bhVar, boolean z) throws au {
        Proxy proxy;
        try {
            c(bhVar);
            if (bhVar.fS == null) {
                proxy = null;
            } else {
                proxy = bhVar.fS;
            }
            return new be(bhVar.e, bhVar.f, proxy, z).b(bhVar.g(), bhVar.c(), bhVar.b());
        } catch (au e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new au("未知的错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(au auVar, bi biVar) {
        bk bkVar = new bk();
        bkVar.fT = auVar;
        bkVar.fQ = biVar;
        Message obtain = Message.obtain();
        obtain.obj = bkVar;
        obtain.what = 1;
        this.ek.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(bj bjVar, bi biVar) {
        biVar.a(bjVar.b, bjVar.a);
        bk bkVar = new bk();
        bkVar.fQ = biVar;
        Message obtain = Message.obtain();
        obtain.obj = bkVar;
        obtain.what = 0;
        this.ek.sendMessage(obtain);
    }

    /* compiled from: NetManger.java */
    /* loaded from: classes11.dex */
    static class a extends Handler {
        /* synthetic */ a(Looper looper, AnonymousClass1 anonymousClass1) {
            this(looper);
        }

        private a(Looper looper) {
            super(looper);
        }

        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        ((bk) message.obj).fQ.a();
                        break;
                    case 1:
                        bk bkVar = (bk) message.obj;
                        bkVar.fQ.a(bkVar.fT);
                        break;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
