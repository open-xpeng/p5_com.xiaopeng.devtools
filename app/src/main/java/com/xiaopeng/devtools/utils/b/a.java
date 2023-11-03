package com.xiaopeng.devtools.utils.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ByteArrayPool.java */
/* loaded from: classes12.dex */
public class a {
    protected static final Comparator<byte[]> BUF_COMPARATOR = new Comparator() { // from class: com.xiaopeng.devtools.utils.b.-$$Lambda$a$7iU6FO91hu3XZRUnlhRInt2INII
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            int d;
            d = a.d((byte[]) obj, (byte[]) obj2);
            return d;
        }
    };
    private List<byte[]> BY = new LinkedList();
    private List<byte[]> BZ = new ArrayList(1024);
    private int Ca = 0;
    private final int Cb;

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int d(byte[] bArr, byte[] bArr2) {
        return bArr.length - bArr2.length;
    }

    public a(int i) {
        this.Cb = i;
    }

    public synchronized byte[] getBuf(int i) {
        for (int i2 = 0; i2 < this.BZ.size(); i2++) {
            byte[] bArr = this.BZ.get(i2);
            if (bArr.length >= i) {
                this.Ca -= bArr.length;
                this.BZ.remove(i2);
                this.BY.remove(bArr);
                return bArr;
            }
        }
        return new byte[i];
    }

    public synchronized void returnBuf(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.Cb) {
                this.BY.add(bArr);
                int binarySearch = Collections.binarySearch(this.BZ, bArr, BUF_COMPARATOR);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.BZ.add(binarySearch, bArr);
                this.Ca += bArr.length;
                lJ();
            }
        }
    }

    private synchronized void lJ() {
        while (this.Ca > this.Cb) {
            byte[] remove = this.BY.remove(0);
            this.BZ.remove(remove);
            this.Ca -= remove.length;
        }
    }
}
