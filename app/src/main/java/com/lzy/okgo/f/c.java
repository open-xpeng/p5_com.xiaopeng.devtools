package com.lzy.okgo.f;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/* compiled from: IOUtils.java */
/* loaded from: classes11.dex */
public class c {
    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            d.f(e);
        }
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0037: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:22:0x0037 */
    public static byte[] i(Object obj) {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        Closeable closeable;
        Closeable closeable2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (IOException e) {
                e = e;
                byteArrayOutputStream = null;
                objectOutputStream = null;
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (IOException e2) {
                e = e2;
                objectOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                closeQuietly(closeable2);
                closeQuietly(byteArrayOutputStream);
                throw th;
            }
            try {
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                closeQuietly(objectOutputStream);
                closeQuietly(byteArrayOutputStream);
                return byteArray;
            } catch (IOException e3) {
                e = e3;
                d.f(e);
                closeQuietly(objectOutputStream);
                closeQuietly(byteArrayOutputStream);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            closeable2 = closeable;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static Object s(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        ?? r0 = 0;
        r0 = 0;
        if (bArr == null) {
            return null;
        }
        try {
            try {
                byteArrayInputStream = new ByteArrayInputStream(bArr);
            } catch (Exception e) {
                e = e;
                objectInputStream = null;
                byteArrayInputStream = null;
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = null;
            }
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
            } catch (Exception e2) {
                e = e2;
                objectInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                closeQuietly(r0);
                closeQuietly(byteArrayInputStream);
                throw th;
            }
            try {
                Object readObject = objectInputStream.readObject();
                closeQuietly(objectInputStream);
                closeQuietly(byteArrayInputStream);
                return readObject;
            } catch (Exception e3) {
                e = e3;
                d.f(e);
                closeQuietly(objectInputStream);
                closeQuietly(byteArrayInputStream);
                return null;
            }
        } catch (Throwable th3) {
            r0 = bArr;
            th = th3;
        }
    }

    public static byte[] b(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }
}
