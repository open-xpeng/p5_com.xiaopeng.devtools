package net.lingala.zip4j.model;

import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.AesVersion;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* loaded from: classes13.dex */
public class ZipParameters {
    private String anN;
    private AesVersion and;
    private AesKeyStrength anf;
    private CompressionMethod ang;
    private EncryptionMethod anp;
    private CompressionLevel aoj;
    private boolean aok;
    private boolean aol;
    private boolean aom;
    private boolean aon;
    private long aoo;
    private String aop;
    private String aoq;
    private long aor;
    private long aos;
    private boolean aot;
    private boolean aou;
    private String aov;
    private SymbolicLinkAction aow;
    private g aox;
    private boolean aoy;

    /* loaded from: classes13.dex */
    public enum SymbolicLinkAction {
        INCLUDE_LINK_ONLY,
        INCLUDE_LINKED_FILE_ONLY,
        INCLUDE_LINK_AND_LINKED_FILE
    }

    public ZipParameters() {
        this.ang = CompressionMethod.DEFLATE;
        this.aoj = CompressionLevel.NORMAL;
        this.aok = false;
        this.anp = EncryptionMethod.NONE;
        this.aol = true;
        this.aom = true;
        this.anf = AesKeyStrength.KEY_STRENGTH_256;
        this.and = AesVersion.TWO;
        this.aon = true;
        this.aor = System.currentTimeMillis();
        this.aos = -1L;
        this.aot = true;
        this.aou = true;
        this.aow = SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY;
    }

    public ZipParameters(ZipParameters zipParameters) {
        this.ang = CompressionMethod.DEFLATE;
        this.aoj = CompressionLevel.NORMAL;
        this.aok = false;
        this.anp = EncryptionMethod.NONE;
        this.aol = true;
        this.aom = true;
        this.anf = AesKeyStrength.KEY_STRENGTH_256;
        this.and = AesVersion.TWO;
        this.aon = true;
        this.aor = System.currentTimeMillis();
        this.aos = -1L;
        this.aot = true;
        this.aou = true;
        this.aow = SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY;
        this.ang = zipParameters.sN();
        this.aoj = zipParameters.tK();
        this.aok = zipParameters.tJ();
        this.anp = zipParameters.sT();
        this.aol = zipParameters.tL();
        this.aom = zipParameters.tM();
        this.anf = zipParameters.sM();
        this.and = zipParameters.sK();
        this.aon = zipParameters.tN();
        this.aoo = zipParameters.tO();
        this.aop = zipParameters.tP();
        this.aoq = zipParameters.tQ();
        this.aor = zipParameters.tR();
        this.aos = zipParameters.tS();
        this.aot = zipParameters.tT();
        this.aou = zipParameters.tU();
        this.aov = zipParameters.tV();
        this.anN = zipParameters.tm();
        this.aow = zipParameters.tW();
        this.aox = zipParameters.tX();
        this.aoy = zipParameters.tY();
    }

    public CompressionMethod sN() {
        return this.ang;
    }

    public void a(CompressionMethod compressionMethod) {
        this.ang = compressionMethod;
    }

    public boolean tJ() {
        return this.aok;
    }

    public void bA(boolean z) {
        this.aok = z;
    }

    public EncryptionMethod sT() {
        return this.anp;
    }

    public void a(EncryptionMethod encryptionMethod) {
        this.anp = encryptionMethod;
    }

    public CompressionLevel tK() {
        return this.aoj;
    }

    public void a(CompressionLevel compressionLevel) {
        this.aoj = compressionLevel;
    }

    public boolean tL() {
        return this.aol;
    }

    public boolean tM() {
        return this.aom;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public AesKeyStrength sM() {
        return this.anf;
    }

    public void a(AesKeyStrength aesKeyStrength) {
        this.anf = aesKeyStrength;
    }

    public AesVersion sK() {
        return this.and;
    }

    public boolean tN() {
        return this.aon;
    }

    public long tO() {
        return this.aoo;
    }

    public void G(long j) {
        this.aoo = j;
    }

    public String tP() {
        return this.aop;
    }

    public void fs(String str) {
        this.aop = str;
    }

    public String tQ() {
        return this.aoq;
    }

    public void ft(String str) {
        this.aoq = str;
    }

    public long tR() {
        return this.aor;
    }

    public void H(long j) {
        if (j <= 0) {
            return;
        }
        this.aor = j;
    }

    public long tS() {
        return this.aos;
    }

    public void I(long j) {
        this.aos = j;
    }

    public boolean tT() {
        return this.aot;
    }

    public void bB(boolean z) {
        this.aot = z;
    }

    public boolean tU() {
        return this.aou;
    }

    public String tV() {
        return this.aov;
    }

    public String tm() {
        return this.anN;
    }

    public SymbolicLinkAction tW() {
        return this.aow;
    }

    public g tX() {
        return this.aox;
    }

    public boolean tY() {
        return this.aoy;
    }
}
