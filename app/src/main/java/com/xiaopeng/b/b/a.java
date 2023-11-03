package com.xiaopeng.b.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.b.b.b;

/* compiled from: IBtGeneralControlService.java */
/* loaded from: classes11.dex */
public interface a extends IInterface {
    boolean A(String str, int i) throws RemoteException;

    int a(String str, int i, byte[] bArr, int i2) throws RemoteException;

    int a(String str, String str2, byte[] bArr) throws RemoteException;

    void a(b bVar) throws RemoteException;

    boolean a(String str, int i, String[] strArr, String[] strArr2) throws RemoteException;

    boolean a(String str, String str2, String str3, boolean z) throws RemoteException;

    boolean a(String str, int[] iArr, String[] strArr, String[] strArr2, int[] iArr2) throws RemoteException;

    boolean aU(boolean z) throws RemoteException;

    boolean ai(String str, String str2) throws RemoteException;

    String aj(String str, String str2) throws RemoteException;

    boolean ak(String str, String str2) throws RemoteException;

    boolean al(String str, String str2) throws RemoteException;

    boolean am(String str, String str2) throws RemoteException;

    boolean an(String str, String str2) throws RemoteException;

    void ao(String str, String str2) throws RemoteException;

    void ap(String str, String str2) throws RemoteException;

    void b(b bVar) throws RemoteException;

    boolean d(String str, byte[] bArr) throws RemoteException;

    boolean e(String str, int i, int i2) throws RemoteException;

    boolean e(String str, String str2, String str3, int i) throws RemoteException;

    boolean e(String str, String str2, boolean z) throws RemoteException;

    String eA(String str) throws RemoteException;

    boolean eB(String str) throws RemoteException;

    boolean eC(String str) throws RemoteException;

    boolean eD(String str) throws RemoteException;

    boolean eE(String str) throws RemoteException;

    void eF(String str) throws RemoteException;

    void eG(String str) throws RemoteException;

    boolean eH(String str) throws RemoteException;

    boolean eI(String str) throws RemoteException;

    boolean eJ(String str) throws RemoteException;

    boolean eK(String str) throws RemoteException;

    boolean eL(String str) throws RemoteException;

    void eM(String str) throws RemoteException;

    void eN(String str) throws RemoteException;

    String eO(String str) throws RemoteException;

    boolean eP(String str) throws RemoteException;

    boolean eQ(String str) throws RemoteException;

    boolean eR(String str) throws RemoteException;

    void ew(String str) throws RemoteException;

    void ex(String str) throws RemoteException;

    int ey(String str) throws RemoteException;

    String ez(String str) throws RemoteException;

    boolean f(String str, int i, int i2) throws RemoteException;

    boolean g(String str, int i, int i2) throws RemoteException;

    boolean g(byte[] bArr, int i) throws RemoteException;

    boolean z(String str, int i) throws RemoteException;

    /* compiled from: IBtGeneralControlService.java */
    /* renamed from: com.xiaopeng.b.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static abstract class AbstractBinderC0047a extends Binder implements a {
        public static a f(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof a)) {
                return (a) queryLocalInterface;
            }
            return new C0048a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            int[] iArr;
            String[] strArr;
            String[] strArr2;
            String[] strArr3;
            if (i == 1598968902) {
                parcel2.writeString("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    a(b.a.g(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    b(b.a.g(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    ew(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    ex(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    int ey = ey(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ey);
                    return true;
                case 6:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean ai = ai(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ai ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String ez = ez(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(ez);
                    return true;
                case 8:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String eA = eA(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(eA);
                    return true;
                case 9:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String aj = aj(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(aj);
                    return true;
                case 10:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eB = eB(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eB ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean z = z(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(z ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eC = eC(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eC ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eD = eD(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eD ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eE = eE(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eE ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    eF(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    eG(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eH = eH(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eH ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean e = e(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(e ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean ak = ak(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(ak ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean a = a(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(a ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean e2 = e(parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(e2 ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String readString = parcel.readString();
                    int readInt = parcel.readInt();
                    if (readInt >= 0) {
                        iArr = new int[readInt];
                    } else {
                        iArr = null;
                    }
                    int readInt2 = parcel.readInt();
                    if (readInt2 >= 0) {
                        strArr = new String[readInt2];
                    } else {
                        strArr = null;
                    }
                    int readInt3 = parcel.readInt();
                    if (readInt3 >= 0) {
                        strArr2 = new String[readInt3];
                    } else {
                        strArr2 = null;
                    }
                    int readInt4 = parcel.readInt();
                    int[] iArr2 = readInt4 >= 0 ? new int[readInt4] : null;
                    boolean a2 = a(readString, iArr, strArr, strArr2, iArr2);
                    parcel2.writeNoException();
                    parcel2.writeInt(a2 ? 1 : 0);
                    parcel2.writeIntArray(iArr);
                    parcel2.writeStringArray(strArr);
                    parcel2.writeStringArray(strArr2);
                    parcel2.writeIntArray(iArr2);
                    return true;
                case 23:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean e3 = e(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(e3 ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eI = eI(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eI ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String readString2 = parcel.readString();
                    int readInt5 = parcel.readInt();
                    int readInt6 = parcel.readInt();
                    if (readInt6 >= 0) {
                        strArr3 = new String[readInt6];
                    } else {
                        strArr3 = null;
                    }
                    int readInt7 = parcel.readInt();
                    String[] strArr4 = readInt7 >= 0 ? new String[readInt7] : null;
                    boolean a3 = a(readString2, readInt5, strArr3, strArr4);
                    parcel2.writeNoException();
                    parcel2.writeInt(a3 ? 1 : 0);
                    parcel2.writeStringArray(strArr3);
                    parcel2.writeStringArray(strArr4);
                    return true;
                case 26:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean f = f(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(f ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean g = g(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(g ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean al = al(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(al ? 1 : 0);
                    return true;
                case 29:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eJ = eJ(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eJ ? 1 : 0);
                    return true;
                case 30:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eK = eK(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eK ? 1 : 0);
                    return true;
                case 31:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean am = am(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(am ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean an = an(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(an ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String readString3 = parcel.readString();
                    byte[] createByteArray = parcel.createByteArray();
                    boolean d = d(readString3, createByteArray);
                    parcel2.writeNoException();
                    parcel2.writeInt(d ? 1 : 0);
                    parcel2.writeByteArray(createByteArray);
                    return true;
                case 34:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eL = eL(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eL ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String readString4 = parcel.readString();
                    String readString5 = parcel.readString();
                    byte[] createByteArray2 = parcel.createByteArray();
                    int a4 = a(readString4, readString5, createByteArray2);
                    parcel2.writeNoException();
                    parcel2.writeInt(a4);
                    parcel2.writeByteArray(createByteArray2);
                    return true;
                case 36:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean A = A(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(A ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String readString6 = parcel.readString();
                    int readInt8 = parcel.readInt();
                    byte[] createByteArray3 = parcel.createByteArray();
                    int a5 = a(readString6, readInt8, createByteArray3, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(a5);
                    parcel2.writeByteArray(createByteArray3);
                    return true;
                case 38:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    eM(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    eN(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    ao(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    String eO = eO(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(eO);
                    return true;
                case 42:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    ap(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eP = eP(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eP ? 1 : 0);
                    return true;
                case 44:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eQ = eQ(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eQ ? 1 : 0);
                    return true;
                case 45:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean eR = eR(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(eR ? 1 : 0);
                    return true;
                case 46:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean g2 = g(parcel.createByteArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(g2 ? 1 : 0);
                    return true;
                case 47:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    boolean aU = aU(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(aU ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* compiled from: IBtGeneralControlService.java */
        /* renamed from: com.xiaopeng.b.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        private static class C0048a implements a {
            private IBinder mRemote;

            C0048a(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.b.b.a
            public void a(b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void b(b bVar) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void ew(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void ex(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public int ey(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean ai(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
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

            @Override // com.xiaopeng.b.b.a
            public String ez(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public String eA(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public String aj(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eB(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean z(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eC(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eD(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eE(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void eF(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void eG(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eH(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean e(String str, String str2, String str3, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(i);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean ak(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean a(String str, String str2, String str3, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean e(String str, String str2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean a(String str, int[] iArr, String[] strArr, String[] strArr2, int[] iArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    if (iArr == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr.length);
                    }
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
                    if (iArr2 == null) {
                        obtain.writeInt(-1);
                    } else {
                        obtain.writeInt(iArr2.length);
                    }
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.readIntArray(iArr);
                    obtain2.readStringArray(strArr);
                    obtain2.readStringArray(strArr2);
                    obtain2.readIntArray(iArr2);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean e(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eI(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean a(String str, int i, String[] strArr, String[] strArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
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
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.readStringArray(strArr);
                    obtain2.readStringArray(strArr2);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean f(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean g(String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean al(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eJ(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eK(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean am(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean an(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean d(String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    boolean z = obtain2.readInt() != 0;
                    obtain2.readByteArray(bArr);
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eL(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public int a(String str, String str2, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean A(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public int a(String str, int i, byte[] bArr, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i2);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void eM(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void eN(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void ao(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public String eO(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public void ap(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eP(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eQ(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean eR(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeString(str);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean g(byte[] bArr, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.b.a
            public boolean aU(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.generalcontrol.IBtGeneralControlService");
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(47, obtain, obtain2, 0);
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
