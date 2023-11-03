package com.xiaopeng.lib.framework.netchannelmodule.http.traffic.plain;

import android.os.Build;
import com.xiaopeng.lib.utils.c;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.net.SocketImplFactory;

/* loaded from: classes12.dex */
public class CountingPlainSocketInstrument implements SocketImplFactory {
    private static Constructor mPlainSocketImplConstruct;
    private static Constructor mSocksSocketImplConstruct;
    private boolean mIsServer;

    public CountingPlainSocketInstrument(boolean z) {
        this.mIsServer = z;
    }

    public static synchronized void initialize() {
        synchronized (CountingPlainSocketInstrument.class) {
            try {
                mPlainSocketImplConstruct = Class.forName("java.net.PlainSocketImpl").getDeclaredConstructor(new Class[0]);
                mPlainSocketImplConstruct.setAccessible(true);
                Socket.setSocketImplFactory(new CountingPlainSocketInstrument(false));
            } catch (Exception e) {
                c.w(e.toString());
            }
            if (!isKitkat()) {
                try {
                    mSocksSocketImplConstruct = Class.forName("java.net.SocksSocketImpl").getDeclaredConstructor(new Class[0]);
                    mSocksSocketImplConstruct.setAccessible(true);
                    ServerSocket.setSocketFactory(new CountingPlainSocketInstrument(true));
                } catch (Exception e2) {
                    c.w(e2.toString());
                }
            }
        }
    }

    private static boolean isKitkat() {
        return Build.VERSION.SDK_INT == 19;
    }

    public static synchronized void reset() {
        synchronized (CountingPlainSocketInstrument.class) {
            try {
                Field declaredField = Socket.class.getDeclaredField("factory");
                declaredField.setAccessible(true);
                declaredField.set(null, null);
            } catch (Exception e) {
                c.w(e.toString());
            }
            if (!isKitkat()) {
                try {
                    Field declaredField2 = ServerSocket.class.getDeclaredField("factory");
                    declaredField2.setAccessible(true);
                    declaredField2.set(null, null);
                } catch (Exception e2) {
                    c.w(e2.toString());
                }
            }
        }
    }

    @Override // java.net.SocketImplFactory
    public SocketImpl createSocketImpl() {
        try {
            if (isKitkat()) {
                return new HookPlainSocketKitKatImpl((SocketImpl) mPlainSocketImplConstruct.newInstance(new Object[0]));
            }
            return new HookPlainSocketPImpl((SocketImpl) (this.mIsServer ? mSocksSocketImplConstruct : mPlainSocketImplConstruct).newInstance(new Object[0]));
        } catch (Exception e) {
            if (!isKitkat() && this.mIsServer) {
                if (mSocksSocketImplConstruct != null) {
                    try {
                        return (SocketImpl) mSocksSocketImplConstruct.newInstance(new Object[0]);
                    } catch (Exception e2) {
                        c.w(e2.toString());
                        c.w(e.toString());
                        return new EmptySocketImpl();
                    }
                }
            } else if (mPlainSocketImplConstruct != null) {
                try {
                    return (SocketImpl) mPlainSocketImplConstruct.newInstance(new Object[0]);
                } catch (Exception e3) {
                    c.w(e3.toString());
                    c.w(e.toString());
                    return new EmptySocketImpl();
                }
            }
            c.w(e.toString());
            return new EmptySocketImpl();
        }
    }
}
