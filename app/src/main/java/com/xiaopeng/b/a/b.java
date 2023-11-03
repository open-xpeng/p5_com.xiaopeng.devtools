package com.xiaopeng.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.b.a.a;

/* compiled from: IBtA2DPControlService.java */
/* loaded from: classes11.dex */
public interface b extends IInterface {
    void a(com.xiaopeng.b.a.a aVar) throws RemoteException;

    void a(String str, String str2, int i, int i2, int i3) throws RemoteException;

    void a(String str, String str2, String str3, int i, int i2) throws RemoteException;

    void a(String str, String str2, String str3, int i, int i2, int i3) throws RemoteException;

    boolean a(String str, int i, String str2) throws RemoteException;

    boolean a(String str, int i, int[] iArr, int i2) throws RemoteException;

    void b(com.xiaopeng.b.a.a aVar) throws RemoteException;

    boolean b(String str, float f) throws RemoteException;

    boolean b(String str, int i, int i2) throws RemoteException;

    boolean b(String str, int i, String str2) throws RemoteException;

    void c(String str, String str2, int i, int i2) throws RemoteException;

    boolean c(String str, int i, int i2) throws RemoteException;

    void d(String str, int i, int i2) throws RemoteException;

    void d(String str, int i, int i2, int i3) throws RemoteException;

    boolean eg(String str) throws RemoteException;

    boolean eh(String str) throws RemoteException;

    boolean ei(String str) throws RemoteException;

    boolean ej(String str) throws RemoteException;

    boolean ek(String str) throws RemoteException;

    boolean el(String str) throws RemoteException;

    boolean em(String str) throws RemoteException;

    String en(String str) throws RemoteException;

    String eo(String str) throws RemoteException;

    String ep(String str) throws RemoteException;

    int eq(String str) throws RemoteException;

    boolean er(String str) throws RemoteException;

    boolean es(String str) throws RemoteException;

    boolean et(String str) throws RemoteException;

    boolean eu(String str) throws RemoteException;

    int ev(String str) throws RemoteException;

    void w(String str, int i) throws RemoteException;

    boolean x(String str, int i) throws RemoteException;

    boolean y(String str, int i) throws RemoteException;

    /* compiled from: IBtA2DPControlService.java */
    /* loaded from: classes11.dex */
    public static abstract class a extends Binder implements b {
        public static b e(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof b)) {
                return (b) queryLocalInterface;
            }
            return new C0046a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int[] iArr;
            if (i == 1598968902) {
                parcel2.writeString("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    a(a.AbstractBinderC0044a.d(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    b(a.AbstractBinderC0044a.d(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean eg = eg(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eg ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean eh = eh(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eh ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean ei = ei(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ei ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean ej = ej(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ej ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean ek = ek(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ek ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean el = el(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(el ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean b = b(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(b ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean em = em(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(em ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    String en = en(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(en);
                    return true;
                case 12:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    String eo = eo(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(eo);
                    return true;
                case 13:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    String ep = ep(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(ep);
                    return true;
                case 14:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    int eq = eq(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eq);
                    return true;
                case 15:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    w(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean er = er(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(er ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean es = es(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(es ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean x = x(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(x ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean c = c(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(c ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    String readString = parcel.readString();
                    int readInt = parcel.readInt();
                    int readInt2 = parcel.readInt();
                    if (readInt2 < 0) {
                        iArr = null;
                    } else {
                        iArr = new int[readInt2];
                    }
                    boolean a = a(readString, readInt, iArr, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    parcel2.writeIntArray(iArr);
                    return true;
                case 21:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    c(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    a(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    d(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    d(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean a2 = a(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(a2 ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean b2 = b(parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(b2 ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean et = et(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(et ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean eu = eu(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eu ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean y = y(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(y ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    int ev = ev(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ev);
                    return true;
                case 33:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    boolean b3 = b(parcel.readString(), parcel.readFloat());
                    parcel2.writeNoException();
                    parcel2.writeInt(b3 ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* compiled from: IBtA2DPControlService.java */
        /* renamed from: com.xiaopeng.b.a.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        private static class C0046a implements b {
            private IBinder mRemote;

            C0046a(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.b.a.b
            public void a(com.xiaopeng.b.a.a aVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void b(com.xiaopeng.b.a.a aVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean eg(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean eh(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean ei(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean ej(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean ek(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean el(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean b(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean em(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public String en(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public String eo(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public String ep(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public int eq(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void w(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean er(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean es(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean x(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean c(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean a(String str, int i, int[] iArr, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (iArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr.length);
                    }
                    obtain.writeInt(i2);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.readIntArray(iArr);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void c(String str, String str2, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void a(String str, String str2, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void a(String str, String str2, String str3, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void a(String str, String str2, String str3, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void d(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public void d(String str, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean a(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean b(String str, int i, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeString(str2);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean et(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean eu(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean y(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public int ev(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a.b
            public boolean b(String str, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.a2dpcontrol.IBtA2DPControlService");
                    obtain.writeString(str);
                    obtain.writeFloat(f);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
