package com.xiaopeng.lib.framework.netchannelmodule.http.traffic.plain;

import com.xiaopeng.lib.framework.netchannelmodule.http.statistic.SocketCounter;
import com.xiaopeng.lib.framework.netchannelmodule.http.traffic.CountingInputStream;
import com.xiaopeng.lib.framework.netchannelmodule.http.traffic.CountingOutputStream;
import com.xiaopeng.lib.framework.netchannelmodule.http.traffic.ICollector;
import com.xiaopeng.lib.utils.c;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.SocketOptions;

/* loaded from: classes12.dex */
public class HookPlainSocketKitKatImpl extends SocketImpl implements SocketOptions {
    private Field mAddress;
    private String mDomain;
    private Field mFd;
    private Method mHookAccept;
    private Method mHookAvailable;
    private Method mHookBind;
    private Method mHookClose;
    private Method mHookConnectHostPort;
    private Method mHookConnectInetAddrPort;
    private Method mHookConnectSocketAddrPort;
    private Method mHookCreate;
    private Method mHookGetFileDescriptor;
    private Method mHookGetInputStream;
    private Method mHookGetOutputStream;
    private Method mHookListen;
    private Method mHookSendUrgentData;
    private CountingInputStream mInputStream;
    private CountingOutputStream mOutputStream;
    private Field mPort;
    private final SocketImpl mSocketImpl;
    private final String TAG = "SocketImplHook";
    private ICollector mCollector = new ICollector() { // from class: com.xiaopeng.lib.framework.netchannelmodule.http.traffic.plain.HookPlainSocketKitKatImpl.1
        @Override // com.xiaopeng.lib.framework.netchannelmodule.http.traffic.ICollector
        public String getDomain() {
            return HookPlainSocketKitKatImpl.this.mDomain == null ? "" : HookPlainSocketKitKatImpl.this.mDomain;
        }
    };

    public HookPlainSocketKitKatImpl(SocketImpl socketImpl) {
        boolean z = false;
        if (socketImpl != null) {
            try {
                Class<?> cls = socketImpl.getClass();
                this.mHookCreate = cls.getDeclaredMethod("create", Boolean.TYPE);
                this.mHookConnectHostPort = cls.getDeclaredMethod("connect", String.class, Integer.TYPE);
                this.mHookConnectInetAddrPort = cls.getDeclaredMethod("connect", InetAddress.class, Integer.TYPE);
                this.mHookConnectSocketAddrPort = cls.getDeclaredMethod("connect", SocketAddress.class, Integer.TYPE);
                this.mHookBind = cls.getDeclaredMethod("bind", InetAddress.class, Integer.TYPE);
                this.mHookListen = cls.getDeclaredMethod("listen", Integer.TYPE);
                this.mHookAccept = cls.getDeclaredMethod("accept", SocketImpl.class);
                this.mHookGetInputStream = cls.getDeclaredMethod("getInputStream", new Class[0]);
                this.mHookGetOutputStream = cls.getDeclaredMethod("getOutputStream", new Class[0]);
                this.mHookAvailable = cls.getDeclaredMethod("available", new Class[0]);
                this.mHookClose = cls.getDeclaredMethod("close", new Class[0]);
                this.mHookCreate.setAccessible(true);
                this.mHookConnectHostPort.setAccessible(true);
                this.mHookConnectInetAddrPort.setAccessible(true);
                this.mHookConnectSocketAddrPort.setAccessible(true);
                this.mHookBind.setAccessible(true);
                this.mHookGetInputStream.setAccessible(true);
                this.mHookGetOutputStream.setAccessible(true);
                this.mHookAvailable.setAccessible(true);
                this.mHookClose.setAccessible(true);
                this.mHookSendUrgentData = cls.getDeclaredMethod("sendUrgentData", Integer.TYPE);
                this.mHookGetFileDescriptor = cls.getMethod("getFD$", new Class[0]);
                this.mHookGetFileDescriptor.setAccessible(true);
                hookSuperClassMembers(cls);
            } catch (Exception e) {
                c.a("SocketImplHook", (String) null, e);
            }
        }
        z = true;
        if (z) {
            this.mSocketImpl = socketImpl;
        } else {
            this.mSocketImpl = null;
        }
        this.fd = new FileDescriptor();
    }

    private void hookSuperClassMembers(Class<?> cls) throws NoSuchFieldException {
        Class<? super Object> superclass = cls.getSuperclass();
        if (SocketImpl.class.getName().equals(superclass.getName())) {
            this.mAddress = superclass.getDeclaredField("address");
            this.mPort = superclass.getDeclaredField("port");
            this.mFd = superclass.getDeclaredField("fd");
            this.mAddress.setAccessible(true);
            this.mPort.setAccessible(true);
            this.mFd.setAccessible(true);
        }
    }

    @Override // java.net.SocketImpl
    protected void create(boolean z) throws IOException {
        if (this.mSocketImpl != null && this.mHookCreate != null) {
            try {
                this.mHookCreate.invoke(this.mSocketImpl, Boolean.valueOf(z));
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected void connect(String str, int i) throws IOException {
        if (this.mAddress != null) {
            c.f("SocketImplHook", "host=" + str + ",port=" + i);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(":");
            sb.append(str);
            this.mDomain = sb.toString();
        }
        if (this.mSocketImpl != null && this.mHookConnectHostPort != null) {
            try {
                this.mHookConnectHostPort.invoke(this.mSocketImpl, str, Integer.valueOf(i));
                SocketCounter.getInstance().increaseRequest(this.mDomain, 0L);
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected void connect(InetAddress inetAddress, int i) throws IOException {
        if (inetAddress != null) {
            c.f("SocketImplHook", "InetAddr=" + inetAddress.toString() + ", port=" + i);
            StringBuilder sb = new StringBuilder();
            sb.append(inetAddress.getHostName());
            sb.append(":");
            sb.append(i);
            this.mDomain = sb.toString();
        }
        if (this.mSocketImpl != null && this.mHookConnectInetAddrPort != null) {
            try {
                this.mHookConnectInetAddrPort.invoke(this.mSocketImpl, inetAddress, Integer.valueOf(i));
                SocketCounter.getInstance().increaseRequest(this.mDomain, 0L);
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected void connect(SocketAddress socketAddress, int i) throws IOException {
        if (socketAddress != null) {
            c.f("SocketImplHook", "SocketAddr=" + socketAddress + ",port=" + this.port);
            this.mDomain = socketAddress.toString();
        }
        if (this.mSocketImpl != null && this.mHookConnectSocketAddrPort != null) {
            try {
                this.mHookConnectSocketAddrPort.invoke(this.mSocketImpl, socketAddress, Integer.valueOf(i));
                SocketCounter.getInstance().increaseRequest(this.mDomain, 0L);
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected void bind(InetAddress inetAddress, int i) throws IOException {
        if (this.mSocketImpl != null && this.mHookBind != null) {
            try {
                this.mHookBind.invoke(this.mSocketImpl, inetAddress, Integer.valueOf(i));
                this.fd = getFileDescriptor();
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected void listen(int i) throws IOException {
        if (this.mSocketImpl != null && this.mHookListen != null) {
            try {
                this.mHookListen.invoke(this.mSocketImpl, Integer.valueOf(i));
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected void accept(SocketImpl socketImpl) throws IOException {
        if (this.mSocketImpl != null && this.mHookAccept != null) {
            try {
                this.mHookAccept.invoke(this.mSocketImpl, socketImpl);
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected InputStream getInputStream() throws IOException {
        if (this.mInputStream != null) {
            return this.mInputStream;
        }
        if (this.mSocketImpl != null && this.mHookGetInputStream != null) {
            try {
                this.mInputStream = new CountingInputStream(this.mCollector, (InputStream) this.mHookGetInputStream.invoke(this.mSocketImpl, new Object[0]));
                this.mInputStream.setStatisticCounter(SocketCounter.getInstance());
                SocketCounter.getInstance().increaseSucceedWithSize(this.mDomain, 0L);
                return this.mInputStream;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected OutputStream getOutputStream() throws IOException {
        if (this.mOutputStream != null) {
            return this.mOutputStream;
        }
        if (this.mSocketImpl != null && this.mHookGetOutputStream != null) {
            try {
                this.mOutputStream = new CountingOutputStream(this.mCollector, (OutputStream) this.mHookGetOutputStream.invoke(this.mSocketImpl, new Object[0]));
                this.mOutputStream.setStatisticCounter(SocketCounter.getInstance());
                return this.mOutputStream;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected int available() throws IOException {
        if (this.mSocketImpl != null && this.mHookAvailable != null) {
            try {
                return ((Integer) this.mHookAvailable.invoke(this.mSocketImpl, new Object[0])).intValue();
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected void close() throws IOException {
        if (this.mSocketImpl != null && this.mHookClose != null) {
            try {
                this.mHookClose.invoke(this.mSocketImpl, new Object[0]);
                if (this.mInputStream != null) {
                    this.mInputStream.close();
                    this.mInputStream = null;
                }
                if (this.mOutputStream != null) {
                    this.mOutputStream.close();
                    this.mOutputStream = null;
                    return;
                }
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketImpl
    protected boolean supportsUrgentData() {
        return false;
    }

    @Override // java.net.SocketImpl
    protected void sendUrgentData(int i) throws IOException {
        if (this.mSocketImpl != null && this.mHookSendUrgentData != null) {
            try {
                this.mHookSendUrgentData.invoke(this.mSocketImpl, Integer.valueOf(i));
                return;
            } catch (Exception e) {
                Throwable cause = e.getCause();
                Throwable th = e;
                if (cause != null) {
                    th = e.getCause();
                }
                if (th instanceof IOException) {
                    throw ((IOException) th);
                }
                throw new IOException(th);
            }
        }
        throw new RuntimeException();
    }

    @Override // java.net.SocketOptions
    public void setOption(int i, Object obj) throws SocketException {
        syncWithImpl();
        if (this.mSocketImpl != null) {
            this.mSocketImpl.setOption(i, obj);
        }
    }

    @Override // java.net.SocketOptions
    public Object getOption(int i) throws SocketException {
        if (this.mSocketImpl != null) {
            return this.mSocketImpl.getOption(i);
        }
        return null;
    }

    @Override // java.net.SocketImpl
    protected void shutdownInput() throws IOException {
    }

    @Override // java.net.SocketImpl
    protected void shutdownOutput() throws IOException {
    }

    public FileDescriptor getFD$() {
        return getFileDescriptor();
    }

    @Override // java.net.SocketImpl
    protected FileDescriptor getFileDescriptor() {
        if (this.mSocketImpl != null && this.mHookGetFileDescriptor != null) {
            try {
                return (FileDescriptor) this.mHookGetFileDescriptor.invoke(this.mSocketImpl, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void syncWithImpl() {
        FileDescriptor fileDescriptor = getFileDescriptor();
        if (fileDescriptor == null || !fileDescriptor.valid()) {
            try {
                if (this.mFd != null) {
                    this.mFd.set(this.mSocketImpl, this.fd);
                }
                if (this.mAddress != null) {
                    this.mAddress.set(this.mSocketImpl, this.address);
                }
                if (this.mPort != null) {
                    this.mPort.set(this.mSocketImpl, Integer.valueOf(this.port));
                }
            } catch (IllegalAccessException e) {
                c.f("SocketImplHook", e.toString());
            }
        }
    }
}
