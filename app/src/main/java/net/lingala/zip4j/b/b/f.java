package net.lingala.zip4j.b.b;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/* compiled from: NoCipherOutputStream.java */
/* loaded from: classes13.dex */
class f extends b<a> {
    public f(j jVar, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException {
        super(jVar, zipParameters, cArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.b.b.b
    /* renamed from: c */
    public a b(OutputStream outputStream, ZipParameters zipParameters, char[] cArr) {
        return new a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: NoCipherOutputStream.java */
    /* loaded from: classes13.dex */
    public static class a implements net.lingala.zip4j.a.c {
        a() {
        }

        @Override // net.lingala.zip4j.a.c
        public int d(byte[] bArr, int i, int i2) {
            return i2;
        }
    }
}
