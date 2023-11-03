package com.alibaba.sdk.android.man.crashreporter.a.c.a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes11.dex */
public class a {
    public Map<String, Object> c = new HashMap();

    public byte[] a() {
        byte[] a;
        if (this.c == null || this.c.size() <= 0) {
            return null;
        }
        try {
            a = a(this.c);
        } catch (Exception e) {
        }
        if (a != null) {
            return a;
        }
        return null;
    }

    private byte[] a(Map map) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(map);
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}
