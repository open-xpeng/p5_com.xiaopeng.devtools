package net.lingala.zip4j.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ZipModel.java */
/* loaded from: classes13.dex */
public class p implements Cloneable {
    private File alk;
    private boolean aoh;
    private List<j> aoa = new ArrayList();
    private List<Object> aob = new ArrayList();
    private c aoc = new c();
    private d aod = new d();
    private f aoe = new f();
    private l aof = new l();
    private m aog = new m();
    private boolean aoi = false;
    private long amB = -1;

    public List<j> tB() {
        return this.aoa;
    }

    public d tC() {
        return this.aod;
    }

    public void a(d dVar) {
        this.aod = dVar;
    }

    public f tD() {
        return this.aoe;
    }

    public void a(f fVar) {
        this.aoe = fVar;
    }

    public boolean tE() {
        return this.aoh;
    }

    public void by(boolean z) {
        this.aoh = z;
    }

    public File tF() {
        return this.alk;
    }

    public void j(File file) {
        this.alk = file;
    }

    public l tG() {
        return this.aof;
    }

    public void a(l lVar) {
        this.aof = lVar;
    }

    public m tH() {
        return this.aog;
    }

    public void a(m mVar) {
        this.aog = mVar;
    }

    public boolean tI() {
        return this.aoi;
    }

    public void bz(boolean z) {
        this.aoi = z;
    }

    public long sD() {
        return this.amB;
    }

    public void F(long j) {
        this.amB = j;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
