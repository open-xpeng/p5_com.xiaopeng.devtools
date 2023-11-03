package com.alibaba.sdk.android.man.crashreporter.e;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;

/* loaded from: classes11.dex */
public class g {
    public static byte[] c(Map map) {
        byte[] a;
        if (map == null || map.size() <= 0) {
            return null;
        }
        try {
            a = a(map);
        } catch (Exception e) {
        }
        if (a == null) {
            return null;
        }
        return a;
    }

    private static byte[] a(Map map) {
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

    public static Map a(byte[] bArr) {
        try {
            return b(bArr);
        } catch (Exception e) {
            return null;
        }
    }

    private static Map b(byte[] bArr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Map map = (Map) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return map;
        } catch (StreamCorruptedException e) {
            return null;
        } catch (IOException e2) {
            return null;
        } catch (ClassNotFoundException e3) {
            return null;
        }
    }
}
