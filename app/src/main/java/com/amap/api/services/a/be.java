package com.amap.api.services.a;

import android.os.Build;
import com.amap.api.services.a.bb;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

/* compiled from: HttpUrlUtil.java */
/* loaded from: classes11.dex */
public class be {
    private static bf fI;
    private int b;
    private int c;
    private boolean d;
    private SSLContext fJ;
    private Proxy fK;
    private bb.a fL;
    private HostnameVerifier fM;
    private volatile boolean g;
    private long h;
    private long i;
    private String j;

    private void a() {
        try {
            this.j = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        } catch (Throwable th) {
            o.a(th, "HttpUrlUtil", "initCSID");
        }
    }

    public static void a(bf bfVar) {
        fI = bfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public be(int i, int i2, Proxy proxy, boolean z) {
        this(i, i2, proxy, z, null);
    }

    be(int i, int i2, Proxy proxy, boolean z, bb.a aVar) {
        this.g = false;
        this.h = -1L;
        this.i = 0L;
        this.fM = new HostnameVerifier() { // from class: com.amap.api.services.a.be.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                HostnameVerifier defaultHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
                return defaultHostnameVerifier.verify("*.amap.com", sSLSession) || defaultHostnameVerifier.verify("*.apilocate.amap.com", sSLSession);
            }
        };
        this.b = i;
        this.c = i2;
        this.fK = proxy;
        this.d = z;
        this.fL = aVar;
        a();
        if (z) {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLS");
                sSLContext.init(null, null, null);
                this.fJ = sSLContext;
            } catch (Throwable th) {
                o.a(th, "HttpUtil", "HttpUtil");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public be(int i, int i2, Proxy proxy) {
        this(i, i2, proxy, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j) {
        this.i = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(long j) {
        this.h = j;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    void a(java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9, java.util.Map<java.lang.String, java.lang.String> r10, com.amap.api.services.a.bd.a r11) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.be.a(java.lang.String, java.util.Map, java.util.Map, com.amap.api.services.a.bd$a):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public bj b(String str, Map<String, String> map, Map<String, String> map2) throws au {
        try {
            String a = a(map2);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            if (a != null) {
                stringBuffer.append("?");
                stringBuffer.append(a);
            }
            HttpURLConnection a2 = a(stringBuffer.toString(), map, false);
            a2.connect();
            return a(a2);
        } catch (au e) {
            throw e;
        } catch (ConnectException e2) {
            throw new au("http连接失败 - ConnectionException");
        } catch (SocketException e3) {
            throw new au("socket 连接异常 - SocketException");
        } catch (SocketTimeoutException e4) {
            throw new au("socket 连接超时 - SocketTimeoutException");
        } catch (InterruptedIOException e5) {
            throw new au("未知的错误");
        } catch (MalformedURLException e6) {
            throw new au("url异常 - MalformedURLException");
        } catch (UnknownHostException e7) {
            throw new au("未知主机 - UnKnowHostException");
        } catch (IOException e8) {
            throw new au("IO 操作异常 - IOException");
        } catch (Throwable th) {
            th.printStackTrace();
            throw new au("未知的错误");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public bj a(String str, Map<String, String> map, byte[] bArr) throws au {
        try {
            HttpURLConnection a = a(str, map, true);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(a.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.close();
            }
            a.connect();
            return a(a);
        } catch (au e) {
            o.a(e, "HttpUrlUtil", "makePostReqeust");
            throw e;
        } catch (InterruptedIOException e2) {
            throw new au("未知的错误");
        } catch (ConnectException e3) {
            e3.printStackTrace();
            throw new au("http连接失败 - ConnectionException");
        } catch (MalformedURLException e4) {
            e4.printStackTrace();
            throw new au("url异常 - MalformedURLException");
        } catch (SocketException e5) {
            e5.printStackTrace();
            throw new au("socket 连接异常 - SocketException");
        } catch (SocketTimeoutException e6) {
            e6.printStackTrace();
            throw new au("socket 连接超时 - SocketTimeoutException");
        } catch (UnknownHostException e7) {
            e7.printStackTrace();
            throw new au("未知主机 - UnKnowHostException");
        } catch (IOException e8) {
            e8.printStackTrace();
            throw new au("IO 操作异常 - IOException");
        } catch (Throwable th) {
            o.a(th, "HttpUrlUtil", "makePostReqeust");
            throw new au("未知的错误");
        }
    }

    HttpURLConnection a(String str, Map<String, String> map, boolean z) throws IOException {
        URLConnection uRLConnection;
        HttpURLConnection httpURLConnection;
        g.a();
        URL url = new URL(str);
        if (this.fL != null) {
            uRLConnection = this.fL.a(this.fK, url);
        } else {
            uRLConnection = null;
        }
        if (uRLConnection == null) {
            if (this.fK != null) {
                uRLConnection = url.openConnection(this.fK);
            } else {
                uRLConnection = url.openConnection();
            }
        }
        if (this.d) {
            httpURLConnection = (HttpsURLConnection) uRLConnection;
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(this.fJ.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(this.fM);
        } else {
            httpURLConnection = (HttpURLConnection) uRLConnection;
        }
        if (Build.VERSION.SDK != null && Build.VERSION.SDK_INT > 13) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        a(map, httpURLConnection);
        if (z) {
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
        }
        return httpURLConnection;
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0136 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0144 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0128 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0152 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amap.api.services.a.bj a(java.net.HttpURLConnection r11) throws com.amap.api.services.a.au, java.io.IOException {
        /*
            Method dump skipped, instructions count: 366
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.be.a(java.net.HttpURLConnection):com.amap.api.services.a.bj");
    }

    private void a(Map<String, String> map, HttpURLConnection httpURLConnection) {
        if (map != null) {
            for (String str : map.keySet()) {
                httpURLConnection.addRequestProperty(str, map.get(str));
            }
        }
        try {
            httpURLConnection.addRequestProperty("csid", this.j);
        } catch (Throwable th) {
            o.a(th, "HttpUrlUtil", "addHeaders");
        }
        httpURLConnection.setConnectTimeout(this.b);
        httpURLConnection.setReadTimeout(this.c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Map<String, String> map) {
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value == null) {
                    value = "";
                }
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(URLEncoder.encode(key));
                sb.append("=");
                sb.append(URLEncoder.encode(value));
            }
            return sb.toString();
        }
        return null;
    }
}
