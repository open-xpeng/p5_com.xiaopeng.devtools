package com.xiaopeng.devtools.model.can;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.xiaopeng.a.a;

/* loaded from: classes12.dex */
public class CanCollectModel {
    private static CanCollectModel rr;
    public static final Long rp = 524288000L;
    public static final Long rq = Long.valueOf((long) OSSConstants.DEFAULT_FILE_SIZE_LIMIT);
    private static final String[] rw = {"ICAN", "DCAN", "BCAN", "CCAN", "ECAN", "ADCAN"};
    private boolean[] rs = new boolean[CanData.values().length];
    private String mErrorMsg = null;
    private int rv = 10002;
    private boolean rt = false;
    private boolean ru = false;

    /* loaded from: classes12.dex */
    public enum CanData {
        ICAN,
        DCAN,
        BCAN,
        CCAN,
        ECAN,
        ADCAN
    }

    public CanCollectModel() {
        for (int i = 0; i < rw.length; i++) {
            this.rs[i] = a.C0041a.dX(rw[i]);
        }
    }

    public static CanCollectModel fo() {
        if (rr == null) {
            rr = new CanCollectModel();
        }
        return rr;
    }

    public int fp() {
        return this.rv;
    }

    public void bT(int i) {
        this.rv = i;
    }

    public boolean bU(int i) {
        if (i >= 0 && i < CanData.values().length) {
            return this.rs[i];
        }
        return false;
    }

    public boolean fq() {
        return this.rt;
    }

    public void c(int i, boolean z) {
        if (i >= 0 && i < CanData.values().length && a.C0041a.dX(rw[i])) {
            this.rs[i] = z;
        }
    }

    public boolean fr() {
        for (int i = 0; i < CanData.values().length; i++) {
            if (this.rs[i]) {
                return true;
            }
        }
        return false;
    }

    public void B(boolean z) {
        String[] strArr;
        for (String str : rw) {
            if (a.C0041a.dX(str)) {
                this.rs[a.C0041a.getInt(str)] = z;
            }
        }
    }

    public void C(boolean z) {
        this.rt = z;
    }

    public boolean fs() {
        return this.ru;
    }

    public void D(boolean z) {
        this.ru = z;
    }

    public String getErrorMsg() {
        return this.mErrorMsg;
    }

    public void setErrorMsg(String str) {
        this.mErrorMsg = str;
    }

    public void ft() {
        this.mErrorMsg = null;
    }
}
