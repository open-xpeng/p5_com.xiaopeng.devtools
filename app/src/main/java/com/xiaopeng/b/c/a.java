package com.xiaopeng.b.c;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.b.c.b;

/* compiled from: IBtPhoneControlService.java */
/* loaded from: classes11.dex */
public interface a extends IInterface {
    boolean B(String str, int i) throws RemoteException;

    boolean C(String str, int i) throws RemoteException;

    boolean D(String str, int i) throws RemoteException;

    boolean E(String str, int i) throws RemoteException;

    void a(b bVar) throws RemoteException;

    void a(String str, int i, int i2, int i3, int i4, int i5, int i6) throws RemoteException;

    void a(String str, boolean z, int i) throws RemoteException;

    void a(String str, boolean z, int i, int i2, int i3, int i4, int i5, int i6) throws RemoteException;

    boolean a(String str, String[] strArr, String[] strArr2, int[] iArr, String[] strArr3, String[] strArr4, int[] iArr2) throws RemoteException;

    boolean aq(String str, String str2) throws RemoteException;

    boolean ar(String str, String str2) throws RemoteException;

    boolean as(String str, String str2) throws RemoteException;

    void b(b bVar) throws RemoteException;

    Bitmap d(String str, String str2, int i, int i2) throws RemoteException;

    boolean eS(String str) throws RemoteException;

    boolean eT(String str) throws RemoteException;

    boolean eU(String str) throws RemoteException;

    boolean eV(String str) throws RemoteException;

    boolean eW(String str) throws RemoteException;

    int eX(String str) throws RemoteException;

    void eY(String str) throws RemoteException;

    void eZ(String str) throws RemoteException;

    void fa(String str) throws RemoteException;

    int fb(String str) throws RemoteException;

    int fc(String str) throws RemoteException;

    int fd(String str) throws RemoteException;

    int fe(String str) throws RemoteException;

    void ff(String str) throws RemoteException;

    boolean fg(String str) throws RemoteException;

    boolean i(String str, boolean z) throws RemoteException;

    void j(String str, boolean z) throws RemoteException;

    boolean w(String str, String str2, String str3) throws RemoteException;

    /* compiled from: IBtPhoneControlService.java */
    /* renamed from: com.xiaopeng.b.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static abstract class AbstractBinderC0050a extends Binder implements a {
        public static a h(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof a)) {
                return (a) queryLocalInterface;
            }
            return new C0051a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String[] strArr;
            String[] strArr2;
            int[] iArr;
            String[] strArr3;
            String[] strArr4;
            int[] iArr2;
            if (i == 1598968902) {
                parcel2.writeString("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    a(b.a.i(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    b(b.a.i(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean eS = eS(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eS ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean eT = eT(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eT ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean eU = eU(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eU ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean aq = aq(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(aq ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean ar = ar(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ar ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean B = B(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(B ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean eV = eV(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eV ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    String readString = parcel.readString();
                    int readInt = parcel.readInt();
                    if (readInt >= 0) {
                        strArr = new String[readInt];
                    } else {
                        strArr = null;
                    }
                    int readInt2 = parcel.readInt();
                    if (readInt2 >= 0) {
                        strArr2 = new String[readInt2];
                    } else {
                        strArr2 = null;
                    }
                    int readInt3 = parcel.readInt();
                    if (readInt3 >= 0) {
                        iArr = new int[readInt3];
                    } else {
                        iArr = null;
                    }
                    int readInt4 = parcel.readInt();
                    if (readInt4 >= 0) {
                        strArr3 = new String[readInt4];
                    } else {
                        strArr3 = null;
                    }
                    int readInt5 = parcel.readInt();
                    if (readInt5 >= 0) {
                        strArr4 = new String[readInt5];
                    } else {
                        strArr4 = null;
                    }
                    int readInt6 = parcel.readInt();
                    if (readInt6 >= 0) {
                        iArr2 = new int[readInt6];
                    } else {
                        iArr2 = null;
                    }
                    boolean a = a(readString, strArr, strArr2, iArr, strArr3, strArr4, iArr2);
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    parcel2.writeStringArray(strArr);
                    parcel2.writeStringArray(strArr2);
                    parcel2.writeIntArray(iArr);
                    parcel2.writeStringArray(strArr3);
                    parcel2.writeStringArray(strArr4);
                    parcel2.writeIntArray(iArr2);
                    return true;
                case 11:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean i3 = i(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(i3 ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean eW = eW(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eW ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    int eX = eX(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eX);
                    return true;
                case 14:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    a(parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    eY(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean C = C(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(C ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    Bitmap d = d(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (d != null) {
                        parcel2.writeInt(1);
                        d.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 18:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean as = as(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(as ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    a(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    eZ(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    a(parcel.readString(), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    fa(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean w = w(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(w ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean D = D(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(D ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    int fb = fb(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(fb);
                    return true;
                case 26:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean E = E(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(E ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    int fc = fc(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(fc);
                    return true;
                case 28:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    int fd = fd(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(fd);
                    return true;
                case 29:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    int fe = fe(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(fe);
                    return true;
                case 30:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    j(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    ff(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    boolean fg = fg(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(fg ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* compiled from: IBtPhoneControlService.java */
        /* renamed from: com.xiaopeng.b.c.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        private static class C0051a implements a {
            private IBinder mRemote;

            C0051a(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.b.c.a
            public void a(b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void b(b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean eS(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean eT(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean eU(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean aq(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean ar(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean B(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean eV(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean a(String str, String[] strArr, String[] strArr2, int[] iArr, String[] strArr3, String[] strArr4, int[] iArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    if (strArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr.length);
                    }
                    if (strArr2 == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr2.length);
                    }
                    if (iArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr.length);
                    }
                    if (strArr3 == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr3.length);
                    }
                    if (strArr4 == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(strArr4.length);
                    }
                    if (iArr2 == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr2.length);
                    }
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.readStringArray(strArr);
                    obtain2.readStringArray(strArr2);
                    obtain2.readIntArray(iArr);
                    obtain2.readStringArray(strArr3);
                    obtain2.readStringArray(strArr4);
                    obtain2.readIntArray(iArr2);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean i(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean eW(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public int eX(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void a(String str, boolean z, int i, int i2, int i3, int i4, int i5, int i6) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    obtain.writeInt(i6);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void eY(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean C(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public Bitmap d(String str, String str2, int i, int i2) throws RemoteException {
                Bitmap bitmap;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bitmap = (Bitmap) Bitmap.CREATOR.createFromParcel(obtain2);
                    } else {
                        bitmap = null;
                    }
                    return bitmap;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean as(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void a(String str, int i, int i2, int i3, int i4, int i5, int i6) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    obtain.writeInt(i6);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void eZ(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void a(String str, boolean z, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void fa(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean w(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean D(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public int fb(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean E(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public int fc(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public int fd(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public int fe(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void j(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public void ff(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.c.a
            public boolean fg(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.phonecontrol.IBtPhoneControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(32, obtain, obtain2, 0);
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
