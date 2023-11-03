package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;

/* compiled from: EndOfCentralDirectoryRecord.java */
/* loaded from: classes13.dex */
public class f extends o {
    private int anA;
    private int anB;
    private int anC;
    private int anD;
    private long anE;
    private long anF;
    private int anz;
    private String comment = "";

    public f() {
        a(HeaderSignature.END_OF_CENTRAL_DIRECTORY);
    }

    public int tb() {
        return this.anz;
    }

    public void fg(int i) {
        this.anz = i;
    }

    public int tc() {
        return this.anA;
    }

    public void fh(int i) {
        this.anA = i;
    }

    public int td() {
        return this.anB;
    }

    public void fi(int i) {
        this.anB = i;
    }

    public int te() {
        return this.anC;
    }

    public void fj(int i) {
        this.anC = i;
    }

    public void fk(int i) {
        this.anD = i;
    }

    public long tf() {
        return this.anE;
    }

    public void v(long j) {
        this.anE = j;
    }

    public long tg() {
        return this.anF;
    }

    public void w(long j) {
        this.anF = j;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        if (str != null) {
            this.comment = str;
        }
    }
}
