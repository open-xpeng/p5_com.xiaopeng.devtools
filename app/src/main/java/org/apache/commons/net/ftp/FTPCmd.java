package org.apache.commons.net.ftp;

/* loaded from: classes13.dex */
public enum FTPCmd {
    ABOR,
    ACCT,
    ALLO,
    APPE,
    CDUP,
    CWD,
    DELE,
    EPRT,
    EPSV,
    FEAT,
    HELP,
    LIST,
    MDTM,
    MFMT,
    MKD,
    MLSD,
    MLST,
    MODE,
    NLST,
    NOOP,
    PASS,
    PASV,
    PORT,
    PWD,
    QUIT,
    REIN,
    REST,
    RETR,
    RMD,
    RNFR,
    RNTO,
    SITE,
    SMNT,
    STAT,
    STOR,
    STOU,
    STRU,
    SYST,
    TYPE,
    USER;
    
    public static final FTPCmd ase = ABOR;
    public static final FTPCmd asf = ACCT;
    public static final FTPCmd asg = ALLO;
    public static final FTPCmd ash = APPE;
    public static final FTPCmd asi = CDUP;
    public static final FTPCmd asj = CWD;
    public static final FTPCmd ask = PORT;
    public static final FTPCmd asl = DELE;
    public static final FTPCmd asm = FEAT;
    public static final FTPCmd asn = STRU;
    public static final FTPCmd aso = MDTM;
    public static final FTPCmd asp = QUIT;
    public static final FTPCmd asq = MKD;
    public static final FTPCmd asr = MDTM;
    public static final FTPCmd ass = NLST;
    public static final FTPCmd ast = PASV;
    public static final FTPCmd asu = PASS;
    public static final FTPCmd asv = PWD;
    public static final FTPCmd asw = REIN;
    public static final FTPCmd asx = RMD;
    public static final FTPCmd asy = RNFR;
    public static final FTPCmd asz = RNTO;
    public static final FTPCmd asA = TYPE;
    public static final FTPCmd asB = REST;
    public static final FTPCmd asC = RETR;
    public static final FTPCmd asD = MFMT;
    public static final FTPCmd asE = SITE;
    public static final FTPCmd asF = STAT;
    public static final FTPCmd asG = STOR;
    public static final FTPCmd asH = STOU;
    public static final FTPCmd asI = SMNT;
    public static final FTPCmd asJ = SYST;
    public static final FTPCmd asK = MODE;
    public static final FTPCmd asL = USER;

    public final String getCommand() {
        return name();
    }
}
