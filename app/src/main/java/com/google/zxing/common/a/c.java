package com.google.zxing.common.a;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ReedSolomonEncoder.java */
/* loaded from: classes11.dex */
public final class c {
    private final a jC;
    private final List<b> jE = new ArrayList();

    public c(a aVar) {
        this.jC = aVar;
        this.jE.add(new b(aVar, new int[]{1}));
    }

    private b bu(int i) {
        if (i >= this.jE.size()) {
            b bVar = this.jE.get(this.jE.size() - 1);
            for (int size = this.jE.size(); size <= i; size++) {
                bVar = bVar.b(new b(this.jC, new int[]{1, this.jC.bq((size - 1) + this.jC.cm())}));
                this.jE.add(bVar);
            }
        }
        return this.jE.get(i);
    }

    public void a(int[] iArr, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        b bu = bu(i);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] cn = new b(this.jC, iArr2).j(i, 1).c(bu)[1].cn();
        int length2 = i - cn.length;
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[length + i2] = 0;
        }
        System.arraycopy(cn, 0, iArr, length + length2, cn.length);
    }
}
