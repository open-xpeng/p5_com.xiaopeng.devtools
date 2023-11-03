package org.apache.commons.net.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.net.MalformedServerReplyException;

/* compiled from: FTPClient.java */
/* loaded from: classes13.dex */
public class c extends org.apache.commons.net.ftp.b implements org.apache.commons.net.ftp.a {
    private static final Pattern aqY = Pattern.compile("(\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}),(\\d{1,3}),(\\d{1,3})");
    private int aqA;
    private InetAddress aqB;
    private InetAddress aqC;
    private InetAddress aqD;
    private int aqE;
    private int aqF;
    private int aqG;
    private int aqH;
    private boolean aqI;
    private long aqJ;
    private org.apache.commons.net.ftp.parser.d aqK;
    private int aqL;
    private int aqM;
    private int aqN;
    private boolean aqO;
    private boolean aqP;
    private String aqQ;
    private e aqR;
    private String aqS;
    private org.apache.commons.net.ftp.d aqT;
    private org.apache.commons.net.io.c aqU;
    private long aqV;
    private int aqW = 1000;
    private b aqX = new C0090c(this);
    private boolean aqZ = false;
    private int aqu;
    private int aqv;
    private int aqw;
    private String aqx;
    private final Random aqy;
    private int aqz;
    private HashMap<String, Set<String>> ara;

    /* compiled from: FTPClient.java */
    /* loaded from: classes13.dex */
    public interface b {
        String fM(String str) throws UnknownHostException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FTPClient.java */
    /* loaded from: classes13.dex */
    public static class d {
        static final Properties arg;

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:8:0x0017 -> B:21:0x0026). Please submit an issue!!! */
        static {
            Properties properties;
            InputStream resourceAsStream = c.class.getResourceAsStream("/systemType.properties");
            if (resourceAsStream != null) {
                properties = new Properties();
                try {
                    try {
                        properties.load(resourceAsStream);
                        resourceAsStream.close();
                    } catch (IOException e) {
                        resourceAsStream.close();
                    } catch (Throwable th) {
                        try {
                            resourceAsStream.close();
                        } catch (IOException e2) {
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                }
            } else {
                properties = null;
            }
            arg = properties;
        }
    }

    private static Properties uE() {
        return d.arg;
    }

    public c() {
        uF();
        this.aqv = -1;
        this.aqI = true;
        this.aqK = new org.apache.commons.net.ftp.parser.c();
        this.aqT = null;
        this.aqO = false;
        this.aqP = false;
        this.aqy = new Random();
        this.aqD = null;
    }

    private void uF() {
        this.aqu = 0;
        this.aqx = null;
        this.aqw = -1;
        this.aqB = null;
        this.aqC = null;
        this.aqz = 0;
        this.aqA = 0;
        this.aqE = 0;
        this.aqG = 7;
        this.aqF = 4;
        this.aqH = 10;
        this.aqJ = 0L;
        this.aqQ = null;
        this.aqR = null;
        this.aqS = "";
        this.ara = null;
    }

    protected void fG(String str) throws MalformedServerReplyException {
        Matcher matcher = aqY.matcher(str);
        if (!matcher.find()) {
            throw new MalformedServerReplyException("Could not parse passive host information.\nServer Reply: " + str);
        }
        this.aqx = matcher.group(1).replace(',', '.');
        try {
            this.aqw = Integer.parseInt(matcher.group(3)) | (Integer.parseInt(matcher.group(2)) << 8);
            if (this.aqX != null) {
                try {
                    String fM = this.aqX.fM(this.aqx);
                    if (!this.aqx.equals(fM)) {
                        k(0, "[Replacing PASV mode reply address " + this.aqx + " with " + fM + "]\n");
                        this.aqx = fM;
                    }
                } catch (UnknownHostException e) {
                    throw new MalformedServerReplyException("Could not parse passive host information.\nServer Reply: " + str);
                }
            }
        } catch (NumberFormatException e2) {
            throw new MalformedServerReplyException("Could not parse passive port information.\nServer Reply: " + str);
        }
    }

    protected void fH(String str) throws MalformedServerReplyException {
        String trim = str.substring(str.indexOf(40) + 1, str.indexOf(41)).trim();
        char charAt = trim.charAt(0);
        char charAt2 = trim.charAt(1);
        char charAt3 = trim.charAt(2);
        char charAt4 = trim.charAt(trim.length() - 1);
        if (charAt != charAt2 || charAt2 != charAt3 || charAt3 != charAt4) {
            throw new MalformedServerReplyException("Could not parse extended passive host information.\nServer Reply: " + trim);
        }
        try {
            int parseInt = Integer.parseInt(trim.substring(3, trim.length() - 1));
            this.aqx = un().getHostAddress();
            this.aqw = parseInt;
        } catch (NumberFormatException e) {
            throw new MalformedServerReplyException("Could not parse extended passive host information.\nServer Reply: " + trim);
        }
    }

    private boolean a(FTPCmd fTPCmd, String str, InputStream inputStream) throws IOException {
        return a(fTPCmd.getCommand(), str, inputStream);
    }

    protected boolean a(String str, String str2, InputStream inputStream) throws IOException {
        OutputStream d2;
        Socket ay = ay(str, str2);
        if (ay == null) {
            return false;
        }
        if (this.aqE == 0) {
            d2 = new org.apache.commons.net.io.g(d(ay.getOutputStream()));
        } else {
            d2 = d(ay.getOutputStream());
        }
        a aVar = null;
        if (this.aqV > 0) {
            aVar = new a(this, this.aqV, this.aqW);
        }
        a aVar2 = aVar;
        try {
            org.apache.commons.net.io.h.a(inputStream, d2, tq(), -1L, a(aVar2), false);
            d2.close();
            ay.close();
            if (aVar2 != null) {
                aVar2.uR();
            }
            return uL();
        } catch (IOException e) {
            org.apache.commons.net.io.h.closeQuietly(ay);
            if (aVar2 != null) {
                aVar2.uR();
            }
            throw e;
        }
    }

    protected Socket b(FTPCmd fTPCmd, String str) throws IOException {
        return ay(fTPCmd.getCommand(), str);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0038, code lost:
        if (org.apache.commons.net.ftp.j.fB(b(uK(), r2.getLocalPort())) == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.net.Socket ay(java.lang.String r9, java.lang.String r10) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.net.ftp.c.ay(java.lang.String, java.lang.String):java.net.Socket");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.ftp.b, org.apache.commons.net.e
    public void um() throws IOException {
        b((Reader) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.ftp.b
    public void b(Reader reader) throws IOException {
        super.b(reader);
        uF();
        if (this.aqZ) {
            ArrayList arrayList = new ArrayList(this.aql);
            int i = this.aqk;
            if (hasFeature("UTF8") || hasFeature("UTF-8")) {
                fA("UTF-8");
                this.aqs = new org.apache.commons.net.io.a(new InputStreamReader(this.aqe, ur()));
                this.aqt = new BufferedWriter(new OutputStreamWriter(this.aqf, ur()));
            }
            this.aql.clear();
            this.aql.addAll(arrayList);
            this.aqk = i;
            this.aqm = true;
        }
    }

    public void fx(int i) {
        this.aqv = i;
    }

    @Override // org.apache.commons.net.ftp.b, org.apache.commons.net.e
    public void disconnect() throws IOException {
        super.disconnect();
        uF();
    }

    public void bE(boolean z) {
        this.aqI = z;
    }

    public boolean az(String str, String str2) throws IOException {
        fC(str);
        if (j.fB(this.aqk)) {
            return true;
        }
        if (!j.fC(this.aqk)) {
            return false;
        }
        return j.fB(fD(str2));
    }

    public boolean uG() throws IOException {
        return j.fB(ux());
    }

    public boolean fI(String str) throws IOException {
        return j.fB(fE(str));
    }

    public void uH() {
        this.aqu = 2;
        this.aqx = null;
        this.aqw = -1;
    }

    private int uI() {
        if (this.aqz > 0 && this.aqA >= this.aqz) {
            if (this.aqA == this.aqz) {
                return this.aqA;
            }
            return this.aqy.nextInt((this.aqA - this.aqz) + 1) + this.aqz;
        }
        return 0;
    }

    private InetAddress uJ() {
        if (this.aqB != null) {
            return this.aqB;
        }
        return getLocalAddress();
    }

    private InetAddress uK() {
        if (this.aqC != null) {
            return this.aqC;
        }
        return uJ();
    }

    public boolean fy(int i) throws IOException {
        if (j.fB(fw(i))) {
            this.aqE = i;
            this.aqF = 4;
            return true;
        }
        return false;
    }

    public boolean uL() throws IOException {
        return j.fB(uu());
    }

    public boolean a(String str, OutputStream outputStream) throws IOException {
        return a(FTPCmd.RETR.getCommand(), str, outputStream);
    }

    protected boolean a(String str, String str2, OutputStream outputStream) throws IOException {
        InputStream c;
        Socket ay = ay(str, str2);
        if (ay == null) {
            return false;
        }
        if (this.aqE == 0) {
            c = new org.apache.commons.net.io.d(c(ay.getInputStream()));
        } else {
            c = c(ay.getInputStream());
        }
        a aVar = null;
        if (this.aqV > 0) {
            aVar = new a(this, this.aqV, this.aqW);
        }
        a aVar2 = aVar;
        try {
            org.apache.commons.net.io.h.a(c, outputStream, tq(), -1L, a(aVar2), false);
            return uL();
        } finally {
            org.apache.commons.net.io.h.closeQuietly(c);
            org.apache.commons.net.io.h.closeQuietly(ay);
            if (aVar2 != null) {
                aVar2.uR();
            }
        }
    }

    public boolean a(String str, InputStream inputStream) throws IOException {
        return a(FTPCmd.STOR, str, inputStream);
    }

    public boolean hasFeature(String str) throws IOException {
        if (!uM()) {
            return false;
        }
        return this.ara.containsKey(str.toUpperCase(Locale.ENGLISH));
    }

    private boolean uM() throws IOException {
        String[] uv;
        String substring;
        if (this.ara == null) {
            int uA = uA();
            if (uA == 530) {
                return false;
            }
            boolean fB = j.fB(uA);
            this.ara = new HashMap<>();
            if (!fB) {
                return false;
            }
            for (String str : uv()) {
                if (str.startsWith(" ")) {
                    String str2 = "";
                    int indexOf = str.indexOf(32, 1);
                    if (indexOf > 0) {
                        String substring2 = str.substring(1, indexOf);
                        str2 = str.substring(indexOf + 1);
                        substring = substring2;
                    } else {
                        substring = str.substring(1);
                    }
                    String upperCase = substring.toUpperCase(Locale.ENGLISH);
                    Set<String> set = this.ara.get(upperCase);
                    if (set == null) {
                        set = new HashSet<>();
                        this.ara.put(upperCase, set);
                    }
                    set.add(str2);
                }
            }
        }
        return true;
    }

    protected boolean O(long j) throws IOException {
        this.aqJ = 0L;
        return j.fC(fF(Long.toString(j)));
    }

    public String uN() throws IOException {
        if (this.aqQ == null) {
            if (j.fB(uB())) {
                this.aqQ = this.aql.get(this.aql.size() - 1).substring(4);
            } else {
                String property = System.getProperty("org.apache.commons.net.ftp.systemType.default");
                if (property != null) {
                    this.aqQ = property;
                } else {
                    throw new IOException("Unable to determine system type - response: " + uw());
                }
            }
        }
        return this.aqQ;
    }

    public FTPFile[] fJ(String str) throws IOException {
        return aA(null, str).vb();
    }

    public FTPFile[] uO() throws IOException {
        return fJ(null);
    }

    public i aA(String str, String str2) throws IOException {
        fK(str);
        return a(this.aqR, str2);
    }

    void fK(String str) throws IOException {
        String property;
        if (this.aqR == null || (str != null && !this.aqS.equals(str))) {
            if (str != null) {
                this.aqR = this.aqK.fX(str);
                this.aqS = str;
            } else if (this.aqT != null && this.aqT.uS().length() > 0) {
                this.aqR = this.aqK.b(this.aqT);
                this.aqS = this.aqT.uS();
            } else {
                String property2 = System.getProperty("org.apache.commons.net.ftp.systemType");
                if (property2 == null) {
                    property2 = uN();
                    Properties uE = uE();
                    if (uE != null && (property = uE.getProperty(property2)) != null) {
                        property2 = property;
                    }
                }
                if (this.aqT != null) {
                    this.aqR = this.aqK.b(new org.apache.commons.net.ftp.d(property2, this.aqT));
                } else {
                    this.aqR = this.aqK.fX(property2);
                }
                this.aqS = property2;
            }
        }
    }

    private i a(e eVar, String str) throws IOException {
        Socket b2 = b(FTPCmd.LIST, fL(str));
        i iVar = new i(eVar, this.aqT);
        if (b2 == null) {
            return iVar;
        }
        try {
            iVar.b(b2.getInputStream(), ur());
            org.apache.commons.net.io.h.closeQuietly(b2);
            uL();
            return iVar;
        } catch (Throwable th) {
            org.apache.commons.net.io.h.closeQuietly(b2);
            throw th;
        }
    }

    protected String fL(String str) {
        if (uP()) {
            if (str != null) {
                StringBuilder sb = new StringBuilder(str.length() + 3);
                sb.append("-a ");
                sb.append(str);
                return sb.toString();
            }
            return "-a";
        }
        return str;
    }

    public int tq() {
        return this.aqL;
    }

    @Override // org.apache.commons.net.ftp.a
    public void a(org.apache.commons.net.ftp.d dVar) {
        this.aqT = dVar;
    }

    public boolean uP() {
        return this.aqO;
    }

    public boolean uQ() {
        return this.aqP;
    }

    /* compiled from: FTPClient.java */
    /* renamed from: org.apache.commons.net.ftp.c$c  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0090c implements b {
        private c arf;

        public C0090c(c cVar) {
            this.arf = cVar;
        }

        @Override // org.apache.commons.net.ftp.c.b
        public String fM(String str) throws UnknownHostException {
            if (InetAddress.getByName(str).isSiteLocalAddress()) {
                InetAddress un = this.arf.un();
                if (!un.isSiteLocalAddress()) {
                    return un.getHostAddress();
                }
                return str;
            }
            return str;
        }
    }

    private OutputStream d(OutputStream outputStream) {
        if (this.aqL > 0) {
            return new BufferedOutputStream(outputStream, this.aqL);
        }
        return new BufferedOutputStream(outputStream);
    }

    private InputStream c(InputStream inputStream) {
        if (this.aqL > 0) {
            return new BufferedInputStream(inputStream, this.aqL);
        }
        return new BufferedInputStream(inputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FTPClient.java */
    /* loaded from: classes13.dex */
    public static class a implements org.apache.commons.net.io.c {
        private final c arb;
        private final long arc;
        private final int ard;
        private int are;
        private long time = System.currentTimeMillis();

        a(c cVar, long j, int i) throws SocketException {
            this.arc = j;
            this.arb = cVar;
            this.ard = cVar.getSoTimeout();
            cVar.setSoTimeout(i);
        }

        @Override // org.apache.commons.net.io.c
        public void a(long j, int i, long j2) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.time > this.arc) {
                try {
                    this.arb.us();
                } catch (SocketTimeoutException e) {
                    this.are++;
                } catch (IOException e2) {
                }
                this.time = currentTimeMillis;
            }
        }

        void uR() throws IOException {
            while (true) {
                try {
                    int i = this.are;
                    this.are = i - 1;
                    if (i > 0) {
                        this.arb.uq();
                    } else {
                        return;
                    }
                } finally {
                    this.arb.setSoTimeout(this.ard);
                }
            }
        }
    }

    private org.apache.commons.net.io.c a(org.apache.commons.net.io.c cVar) {
        if (cVar == null) {
            return this.aqU;
        }
        if (this.aqU == null) {
            return cVar;
        }
        org.apache.commons.net.io.b bVar = new org.apache.commons.net.io.b();
        bVar.b(cVar);
        bVar.b(this.aqU);
        return bVar;
    }
}
