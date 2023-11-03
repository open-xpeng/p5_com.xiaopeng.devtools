package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ProtocolCommandSupport;

/* compiled from: FTP.java */
/* loaded from: classes13.dex */
public class b extends org.apache.commons.net.e {
    protected int aqk;
    protected ArrayList<String> aql;
    protected boolean aqm;
    protected String aqn;
    protected String aqo;
    protected ProtocolCommandSupport aqp;
    protected boolean aqq = false;
    private boolean aqr = true;
    protected BufferedReader aqs;
    protected BufferedWriter aqt;

    public b() {
        fv(21);
        this.aql = new ArrayList<>();
        this.aqm = false;
        this.aqn = null;
        this.aqo = "ISO-8859-1";
        this.aqp = new ProtocolCommandSupport(this);
    }

    private boolean av(String str, String str2) {
        return (str.startsWith(str2) && str.charAt(3) == ' ') ? false : true;
    }

    private boolean fz(String str) {
        return str.length() <= 3 || str.charAt(3) == '-' || !Character.isDigit(str.charAt(0));
    }

    private void up() throws IOException {
        bD(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void uq() throws IOException {
        bD(false);
    }

    private void bD(boolean z) throws IOException {
        this.aqm = true;
        this.aql.clear();
        String readLine = this.aqs.readLine();
        if (readLine == null) {
            throw new FTPConnectionClosedException("Connection closed without indication.");
        }
        int length = readLine.length();
        if (length >= 3) {
            try {
                String substring = readLine.substring(0, 3);
                this.aqk = Integer.parseInt(substring);
                this.aql.add(readLine);
                if (length > 3) {
                    char charAt = readLine.charAt(3);
                    if (charAt == '-') {
                        while (true) {
                            String readLine2 = this.aqs.readLine();
                            if (readLine2 == null) {
                                throw new FTPConnectionClosedException("Connection closed without indication.");
                            }
                            this.aql.add(readLine2);
                            if (uC()) {
                                if (!av(readLine2, substring)) {
                                    break;
                                }
                            } else if (!fz(readLine2)) {
                                break;
                            }
                        }
                    } else if (uD()) {
                        if (length == 4) {
                            throw new MalformedServerReplyException("Truncated server reply: '" + readLine + "'");
                        } else if (charAt != ' ') {
                            throw new MalformedServerReplyException("Invalid server reply: '" + readLine + "'");
                        }
                    }
                } else if (uD()) {
                    throw new MalformedServerReplyException("Truncated server reply: '" + readLine + "'");
                }
                if (z) {
                    k(this.aqk, uw());
                }
                if (this.aqk == 421) {
                    throw new FTPConnectionClosedException("FTP response 421 received.  Server closed connection.");
                }
                return;
            } catch (NumberFormatException e) {
                throw new MalformedServerReplyException("Could not parse response code.\nServer Reply: " + readLine);
            }
        }
        throw new MalformedServerReplyException("Truncated server reply: " + readLine);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.net.e
    public void um() throws IOException {
        b((Reader) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Reader reader) throws IOException {
        super.um();
        if (reader == null) {
            this.aqs = new org.apache.commons.net.io.a(new InputStreamReader(this.aqe, ur()));
        } else {
            this.aqs = new org.apache.commons.net.io.a(reader);
        }
        this.aqt = new BufferedWriter(new OutputStreamWriter(this.aqf, ur()));
        if (this.connectTimeout > 0) {
            int soTimeout = this.aqb.getSoTimeout();
            this.aqb.setSoTimeout(this.connectTimeout);
            try {
                try {
                    up();
                    if (j.fA(this.aqk)) {
                        up();
                    }
                    return;
                } catch (SocketTimeoutException e) {
                    IOException iOException = new IOException("Timed out waiting for initial connect reply");
                    iOException.initCause(e);
                    throw iOException;
                }
            } finally {
                this.aqb.setSoTimeout(soTimeout);
            }
        }
        up();
        if (j.fA(this.aqk)) {
            up();
        }
    }

    public void fA(String str) {
        this.aqo = str;
    }

    public String ur() {
        return this.aqo;
    }

    @Override // org.apache.commons.net.e
    public void disconnect() throws IOException {
        super.disconnect();
        this.aqs = null;
        this.aqt = null;
        this.aqm = false;
        this.aqn = null;
    }

    public int aw(String str, String str2) throws IOException {
        if (this.aqt == null) {
            throw new IOException("Connection is not open");
        }
        String ax = ax(str, str2);
        fB(ax);
        au(str, ax);
        up();
        return this.aqk;
    }

    private String ax(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (str2 != null) {
            sb.append(' ');
            sb.append(str2);
        }
        sb.append("\r\n");
        return sb.toString();
    }

    private void fB(String str) throws IOException, FTPConnectionClosedException, SocketException {
        try {
            this.aqt.write(str);
            this.aqt.flush();
        } catch (SocketException e) {
            if (!isConnected()) {
                throw new FTPConnectionClosedException("Connection unexpectedly closed.");
            }
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void us() throws IOException {
        fB(ax(FTPCmd.NOOP.getCommand(), null));
        uq();
    }

    public int a(FTPCmd fTPCmd) throws IOException {
        return a(fTPCmd, (String) null);
    }

    public int a(FTPCmd fTPCmd, String str) throws IOException {
        return aw(fTPCmd.getCommand(), str);
    }

    public int ut() {
        return this.aqk;
    }

    public int uu() throws IOException {
        up();
        return this.aqk;
    }

    public String[] uv() {
        return (String[]) this.aql.toArray(new String[this.aql.size()]);
    }

    public String uw() {
        if (!this.aqm) {
            return this.aqn;
        }
        StringBuilder sb = new StringBuilder(256);
        Iterator<String> it = this.aql.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append("\r\n");
        }
        this.aqm = false;
        String sb2 = sb.toString();
        this.aqn = sb2;
        return sb2;
    }

    public int fC(String str) throws IOException {
        return a(FTPCmd.USER, str);
    }

    public int fD(String str) throws IOException {
        return a(FTPCmd.PASS, str);
    }

    public int fE(String str) throws IOException {
        return a(FTPCmd.CWD, str);
    }

    public int ux() throws IOException {
        return a(FTPCmd.QUIT);
    }

    public int a(InetAddress inetAddress, int i) throws IOException {
        StringBuilder sb = new StringBuilder(24);
        sb.append(inetAddress.getHostAddress().replace('.', ','));
        sb.append(',');
        sb.append(i >>> 8);
        sb.append(',');
        sb.append(i & 255);
        return a(FTPCmd.PORT, sb.toString());
    }

    public int b(InetAddress inetAddress, int i) throws IOException {
        StringBuilder sb = new StringBuilder();
        String hostAddress = inetAddress.getHostAddress();
        int indexOf = hostAddress.indexOf("%");
        if (indexOf > 0) {
            hostAddress = hostAddress.substring(0, indexOf);
        }
        sb.append("|");
        if (inetAddress instanceof Inet4Address) {
            sb.append("1");
        } else if (inetAddress instanceof Inet6Address) {
            sb.append("2");
        }
        sb.append("|");
        sb.append(hostAddress);
        sb.append("|");
        sb.append(i);
        sb.append("|");
        return a(FTPCmd.EPRT, sb.toString());
    }

    public int uy() throws IOException {
        return a(FTPCmd.PASV);
    }

    public int uz() throws IOException {
        return a(FTPCmd.EPSV);
    }

    public int fw(int i) throws IOException {
        return a(FTPCmd.TYPE, "AEILNTCFRPSBC".substring(i, i + 1));
    }

    public int uA() throws IOException {
        return a(FTPCmd.FEAT);
    }

    public int fF(String str) throws IOException {
        return a(FTPCmd.REST, str);
    }

    public int uB() throws IOException {
        return a(FTPCmd.SYST);
    }

    public boolean uC() {
        return this.aqq;
    }

    public boolean uD() {
        return this.aqr;
    }

    @Override // org.apache.commons.net.e
    protected ProtocolCommandSupport uo() {
        return this.aqp;
    }
}
