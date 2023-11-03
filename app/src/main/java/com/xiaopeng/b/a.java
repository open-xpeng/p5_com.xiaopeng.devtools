package com.xiaopeng.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.b.a.b;
import com.xiaopeng.b.b.a;
import com.xiaopeng.b.c.a;

/* compiled from: IXPBluetoothService.java */
/* loaded from: classes11.dex */
public interface a extends IInterface {
    String ee(String str) throws RemoteException;

    void ef(String str) throws RemoteException;

    com.xiaopeng.b.b.a qk() throws RemoteException;

    b ql() throws RemoteException;

    com.xiaopeng.b.c.a qm() throws RemoteException;

    /* compiled from: IXPBluetoothService.java */
    /* renamed from: com.xiaopeng.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static abstract class AbstractBinderC0042a extends Binder implements a {
        public static a c(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof a)) {
                return (a) queryLocalInterface;
            }
            return new C0043a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    String ee = ee(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(ee);
                    return true;
                case 2:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    ef(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    com.xiaopeng.b.b.a qk = qk();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(qk != null ? qk.asBinder() : null);
                    return true;
                case 4:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    b ql = ql();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(ql != null ? ql.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    com.xiaopeng.b.c.a qm = qm();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(qm != null ? qm.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: IXPBluetoothService.java */
        /* renamed from: com.xiaopeng.b.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        public static class C0043a implements a {
            private IBinder mRemote;

            C0043a(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.b.a
            public String ee(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    obtain.writeString(str);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a
            public void ef(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    obtain.writeString(str);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a
            public com.xiaopeng.b.b.a qk() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return a.AbstractBinderC0047a.f(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a
            public b ql() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return b.a.e(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.b.a
            public com.xiaopeng.b.c.a qm() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.xiaopeng.xpbluetoothservice.IXPBluetoothService");
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return a.AbstractBinderC0050a.h(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
