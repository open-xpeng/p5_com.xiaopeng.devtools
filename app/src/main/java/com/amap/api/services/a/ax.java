package com.amap.api.services.a;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* compiled from: DiskLruCache.java */
/* loaded from: classes11.dex */
public final class ax implements Closeable {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final OutputStream fs = new OutputStream() { // from class: com.amap.api.services.a.ax.2
        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
        }
    };
    private final File c;
    private final File fk;
    private final File fl;
    private final File fm;
    private Writer fn;
    private ay fp;
    private final int g;
    private long h;
    private final int i;
    private int m;
    private long j = 0;
    private final LinkedHashMap<String, c> fo = new LinkedHashMap<>(0, 0.75f, true);
    private long cn = 0;
    final ThreadPoolExecutor fq = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Callable<Void> fr = new Callable<Void>() { // from class: com.amap.api.services.a.ax.1
        @Override // java.util.concurrent.Callable
        /* renamed from: bv */
        public Void call() throws Exception {
            synchronized (ax.this) {
                if (ax.this.fn == null) {
                    return null;
                }
                ax.this.B();
                if (ax.this.aD()) {
                    ax.this.g();
                    ax.this.m = 0;
                }
                return null;
            }
        }
    };

    public void a(ay ayVar) {
        this.fp = ayVar;
    }

    private ax(File file, int i, int i2, long j) {
        this.c = file;
        this.g = i;
        this.fk = new File(file, "journal");
        this.fl = new File(file, "journal.tmp");
        this.fm = new File(file, "journal.bkp");
        this.i = i2;
        this.h = j;
    }

    public static ax a(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        File file2 = new File(file, "journal.bkp");
        if (file2.exists()) {
            File file3 = new File(file, "journal");
            if (file3.exists()) {
                file2.delete();
            } else {
                a(file2, file3, false);
            }
        }
        ax axVar = new ax(file, i, i2, j);
        if (axVar.fk.exists()) {
            try {
                axVar.e();
                axVar.f();
                axVar.fn = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(axVar.fk, true), ba.fE));
                return axVar;
            } catch (Throwable th) {
                axVar.c();
            }
        }
        file.mkdirs();
        ax axVar2 = new ax(file, i, i2, j);
        axVar2.g();
        return axVar2;
    }

    private void e() throws IOException {
        az azVar = new az(new FileInputStream(this.fk), ba.fE);
        try {
            String a2 = azVar.a();
            String a3 = azVar.a();
            String a4 = azVar.a();
            String a5 = azVar.a();
            String a6 = azVar.a();
            if (!"libcore.io.DiskLruCache".equals(a2) || !"1".equals(a3) || !Integer.toString(this.g).equals(a4) || !Integer.toString(this.i).equals(a5) || !"".equals(a6)) {
                throw new IOException("unexpected journal header: [" + a2 + ", " + a3 + ", " + a5 + ", " + a6 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    d(azVar.a());
                    i++;
                } catch (EOFException e) {
                    this.m = i - this.fo.size();
                    ba.a(azVar);
                    return;
                }
            }
        } catch (Throwable th) {
            ba.a(azVar);
            throw th;
        }
    }

    private void d(String str) throws IOException {
        String substring;
        int indexOf = str.indexOf(32);
        if (indexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(32, i);
        if (indexOf2 == -1) {
            substring = str.substring(i);
            if (indexOf == "REMOVE".length() && str.startsWith("REMOVE")) {
                this.fo.remove(substring);
                return;
            }
        } else {
            substring = str.substring(i, indexOf2);
        }
        c cVar = this.fo.get(substring);
        if (cVar == null) {
            cVar = new c(substring);
            this.fo.put(substring, cVar);
        }
        if (indexOf2 == -1 || indexOf != "CLEAN".length() || !str.startsWith("CLEAN")) {
            if (indexOf2 != -1 || indexOf != "DIRTY".length() || !str.startsWith("DIRTY")) {
                if (indexOf2 != -1 || indexOf != "READ".length() || !str.startsWith("READ")) {
                    throw new IOException("unexpected journal line: " + str);
                }
                return;
            }
            cVar.fA = new a(cVar);
            return;
        }
        String[] split = str.substring(indexOf2 + 1).split(" ");
        cVar.d = true;
        cVar.fA = null;
        cVar.c(split);
    }

    private void f() throws IOException {
        c(this.fl);
        Iterator<c> it = this.fo.values().iterator();
        while (it.hasNext()) {
            c next = it.next();
            int i = 0;
            if (next.fA == null) {
                while (i < this.i) {
                    this.j += next.fz[i];
                    i++;
                }
            } else {
                next.fA = null;
                while (i < this.i) {
                    c(next.L(i));
                    c(next.M(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g() throws IOException {
        if (this.fn != null) {
            this.fn.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fl), ba.fE));
        bufferedWriter.write("libcore.io.DiskLruCache");
        bufferedWriter.write("\n");
        bufferedWriter.write("1");
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.g));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.i));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (c cVar : this.fo.values()) {
            if (cVar.fA != null) {
                bufferedWriter.write("DIRTY " + cVar.b + '\n');
            } else {
                bufferedWriter.write("CLEAN " + cVar.b + cVar.a() + '\n');
            }
        }
        bufferedWriter.close();
        if (this.fk.exists()) {
            a(this.fk, this.fm, true);
        }
        a(this.fl, this.fk, false);
        this.fm.delete();
        this.fn = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.fk, true), ba.fE));
    }

    private static void c(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) throws IOException {
        if (z) {
            c(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized b K(String str) throws IOException {
        i();
        e(str);
        c cVar = this.fo.get(str);
        if (cVar == null) {
            return null;
        }
        if (!cVar.d) {
            return null;
        }
        InputStream[] inputStreamArr = new InputStream[this.i];
        for (int i = 0; i < this.i; i++) {
            try {
                inputStreamArr[i] = new FileInputStream(cVar.L(i));
            } catch (FileNotFoundException e) {
                for (int i2 = 0; i2 < this.i && inputStreamArr[i2] != null; i2++) {
                    ba.a(inputStreamArr[i2]);
                }
                return null;
            }
        }
        this.m++;
        this.fn.append((CharSequence) ("READ " + str + '\n'));
        if (aD()) {
            this.fq.submit(this.fr);
        }
        return new b(str, cVar.f, inputStreamArr, cVar.fz);
    }

    public a L(String str) throws IOException {
        return b(str, -1L);
    }

    private synchronized a b(String str, long j) throws IOException {
        i();
        e(str);
        c cVar = this.fo.get(str);
        if (j == -1 || (cVar != null && cVar.f == j)) {
            if (cVar == null) {
                cVar = new c(str);
                this.fo.put(str, cVar);
            } else if (cVar.fA != null) {
                return null;
            }
            a aVar = new a(cVar);
            cVar.fA = aVar;
            Writer writer = this.fn;
            writer.write("DIRTY " + str + '\n');
            this.fn.flush();
            return aVar;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(a aVar, boolean z) throws IOException {
        c cVar = aVar.fu;
        if (cVar.fA != aVar) {
            throw new IllegalStateException();
        }
        if (z && !cVar.d) {
            for (int i = 0; i < this.i; i++) {
                if (!aVar.fv[i]) {
                    aVar.b();
                    throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                } else if (!cVar.M(i).exists()) {
                    aVar.b();
                    return;
                }
            }
        }
        for (int i2 = 0; i2 < this.i; i2++) {
            File M = cVar.M(i2);
            if (z) {
                if (M.exists()) {
                    File L = cVar.L(i2);
                    M.renameTo(L);
                    long j = cVar.fz[i2];
                    long length = L.length();
                    cVar.fz[i2] = length;
                    this.j = (this.j - j) + length;
                }
            } else {
                c(M);
            }
        }
        this.m++;
        cVar.fA = null;
        if (cVar.d | z) {
            cVar.d = true;
            this.fn.write("CLEAN " + cVar.b + cVar.a() + '\n');
            if (z) {
                long j2 = this.cn;
                this.cn = 1 + j2;
                cVar.f = j2;
            }
        } else {
            this.fo.remove(cVar.b);
            this.fn.write("REMOVE " + cVar.b + '\n');
        }
        this.fn.flush();
        if (this.j > this.h || aD()) {
            this.fq.submit(this.fr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean aD() {
        return this.m >= 2000 && this.m >= this.fo.size();
    }

    public synchronized boolean c(String str) throws IOException {
        i();
        e(str);
        c cVar = this.fo.get(str);
        if (cVar != null && cVar.fA == null) {
            for (int i = 0; i < this.i; i++) {
                File L = cVar.L(i);
                if (L.exists() && !L.delete()) {
                    throw new IOException("failed to delete " + L);
                }
                this.j -= cVar.fz[i];
                cVar.fz[i] = 0;
            }
            this.m++;
            this.fn.append((CharSequence) ("REMOVE " + str + '\n'));
            this.fo.remove(str);
            if (aD()) {
                this.fq.submit(this.fr);
            }
            return true;
        }
        return false;
    }

    public synchronized boolean a() {
        return this.fn == null;
    }

    private void i() {
        if (this.fn == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void b() throws IOException {
        i();
        B();
        this.fn.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        if (this.fn == null) {
            return;
        }
        Iterator it = new ArrayList(this.fo.values()).iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.fA != null) {
                cVar.fA.b();
            }
        }
        B();
        this.fn.close();
        this.fn = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() throws IOException {
        while (this.j > this.h) {
            String key = this.fo.entrySet().iterator().next().getKey();
            c(key);
            if (this.fp != null) {
                this.fp.a(key);
            }
        }
    }

    public void c() throws IOException {
        close();
        ba.c(this.c);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    /* compiled from: DiskLruCache.java */
    /* loaded from: classes11.dex */
    public final class b implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] fx;
        private final long[] fy;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.fx = inputStreamArr;
            this.fy = jArr;
        }

        public InputStream K(int i) {
            return this.fx[i];
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (InputStream inputStream : this.fx) {
                ba.a(inputStream);
            }
        }
    }

    /* compiled from: DiskLruCache.java */
    /* loaded from: classes11.dex */
    public final class a {
        private boolean d;
        private boolean e;
        private final c fu;
        private final boolean[] fv;

        private a(c cVar) {
            this.fu = cVar;
            this.fv = cVar.d ? null : new boolean[ax.this.i];
        }

        public OutputStream J(int i) throws IOException {
            FileOutputStream fileOutputStream;
            C0027a c0027a;
            if (i < 0 || i >= ax.this.i) {
                throw new IllegalArgumentException("Expected index " + i + " to be greater than 0 and less than the maximum value count of " + ax.this.i);
            }
            synchronized (ax.this) {
                if (this.fu.fA != this) {
                    throw new IllegalStateException();
                }
                if (!this.fu.d) {
                    this.fv[i] = true;
                }
                File M = this.fu.M(i);
                try {
                    fileOutputStream = new FileOutputStream(M);
                } catch (FileNotFoundException e) {
                    ax.this.c.mkdirs();
                    try {
                        fileOutputStream = new FileOutputStream(M);
                    } catch (FileNotFoundException e2) {
                        return ax.fs;
                    }
                }
                c0027a = new C0027a(fileOutputStream);
            }
            return c0027a;
        }

        public void a() throws IOException {
            if (this.d) {
                ax.this.a(this, false);
                ax.this.c(this.fu.b);
            } else {
                ax.this.a(this, true);
            }
            this.e = true;
        }

        public void b() throws IOException {
            ax.this.a(this, false);
        }

        /* compiled from: DiskLruCache.java */
        /* renamed from: com.amap.api.services.a.ax$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        private class C0027a extends FilterOutputStream {
            private C0027a(OutputStream outputStream) {
                super(outputStream);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                try {
                    this.out.close();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Flushable
            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException e) {
                    a.this.d = true;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DiskLruCache.java */
    /* loaded from: classes11.dex */
    public final class c {
        private final String b;
        private boolean d;
        private long f;
        private a fA;
        private final long[] fz;

        private c(String str) {
            this.b = str;
            this.fz = new long[ax.this.i];
        }

        public String a() throws IOException {
            long[] jArr;
            StringBuilder sb = new StringBuilder();
            for (long j : this.fz) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(String[] strArr) throws IOException {
            if (strArr.length != ax.this.i) {
                throw d(strArr);
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.fz[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException e) {
                    throw d(strArr);
                }
            }
        }

        private IOException d(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        public File L(int i) {
            File file = ax.this.c;
            return new File(file, this.b + "." + i);
        }

        public File M(int i) {
            File file = ax.this.c;
            return new File(file, this.b + "." + i + ".tmp");
        }
    }
}
