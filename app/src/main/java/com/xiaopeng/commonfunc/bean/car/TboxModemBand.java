package com.xiaopeng.commonfunc.bean.car;

/* loaded from: classes11.dex */
public class TboxModemBand {
    private TboxGsmBand GSM;
    private TboxLteBand LTE;
    private TboxUmtsBand UMTS;

    public TboxModemBand() {
    }

    public TboxModemBand(TboxGsmBand tboxGsmBand, TboxUmtsBand tboxUmtsBand, TboxLteBand tboxLteBand) {
        this.GSM = tboxGsmBand;
        this.UMTS = tboxUmtsBand;
        this.LTE = tboxLteBand;
    }

    public TboxGsmBand getGSM() {
        return this.GSM;
    }

    public TboxUmtsBand getUMTS() {
        return this.UMTS;
    }

    public TboxLteBand getLTE() {
        return this.LTE;
    }
}
