package org.apache.commons.net;

import java.net.DatagramSocket;
import java.net.SocketException;

/* compiled from: DefaultDatagramSocketFactory.java */
/* loaded from: classes13.dex */
public class c implements b {
    @Override // org.apache.commons.net.b
    public DatagramSocket createDatagramSocket() throws SocketException {
        return new DatagramSocket();
    }
}
