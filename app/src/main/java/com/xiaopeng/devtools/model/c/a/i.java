package com.xiaopeng.devtools.model.c.a;

import com.xiaopeng.a.a;
import java.io.File;

/* compiled from: USBModel.java */
/* loaded from: classes12.dex */
public class i {
    private static final String sG = a.c.ec("USB_DEVICES");
    private static final String sH = sG + "1-1.3/";
    private static final String sI = sG + "1-1.4/";
    private static final String sJ = sG + "1-1.2/";
    private static final String sK = sG + "3-1/";
    private boolean sL = false;
    private boolean sM = false;
    private boolean sN = false;
    private boolean sO = false;

    private boolean bx(String str) {
        File file = new File(str);
        return file.exists() && file.isDirectory();
    }

    public boolean fY() {
        boolean bx = bx(sH);
        if (bx) {
            this.sL = true;
        }
        return bx;
    }

    public boolean fZ() {
        boolean bx = bx(sI);
        if (bx) {
            this.sM = true;
        }
        return bx;
    }

    public boolean ga() {
        boolean bx = bx(sJ);
        if (bx) {
            this.sN = true;
        }
        return bx;
    }

    public boolean gb() {
        boolean bx = bx(sK);
        if (bx) {
            this.sO = true;
        }
        return bx;
    }

    public boolean gd() {
        return bx(sK);
    }

    public boolean ge() {
        return this.sL;
    }

    public boolean gf() {
        return this.sM;
    }

    public boolean gg() {
        return this.sN;
    }

    public boolean gh() {
        return this.sO;
    }
}
