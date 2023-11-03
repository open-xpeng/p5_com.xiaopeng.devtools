package org.apache.commons.net.ftp.parser;

import java.text.ParseException;
import java.util.Calendar;

/* compiled from: ConfigurableFTPFileEntryParserImpl.java */
/* loaded from: classes13.dex */
public abstract class b extends m implements org.apache.commons.net.ftp.a {
    private final e asV;

    protected abstract org.apache.commons.net.ftp.d vd();

    public b(String str) {
        super(str);
        this.asV = new f();
    }

    public b(String str, int i) {
        super(str, i);
        this.asV = new f();
    }

    public Calendar fW(String str) throws ParseException {
        return this.asV.fW(str);
    }

    @Override // org.apache.commons.net.ftp.a
    public void a(org.apache.commons.net.ftp.d dVar) {
        if (this.asV instanceof org.apache.commons.net.ftp.a) {
            org.apache.commons.net.ftp.d vd = vd();
            if (dVar != null) {
                if (dVar.uT() == null) {
                    dVar.fN(vd.uT());
                }
                if (dVar.uU() == null) {
                    dVar.fO(vd.uU());
                }
                ((org.apache.commons.net.ftp.a) this.asV).a(dVar);
                return;
            }
            ((org.apache.commons.net.ftp.a) this.asV).a(vd);
        }
    }
}
