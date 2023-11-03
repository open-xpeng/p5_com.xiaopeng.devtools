package com.xiaopeng.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: IPCCallback.java */
/* loaded from: classes12.dex */
public interface b extends IInterface {
    void onReceive(IpcMessage ipcMessage) throws RemoteException;

    /* compiled from: IPCCallback.java */
    /* loaded from: classes12.dex */
    public static abstract class a extends Binder implements b {
        private static final String DESCRIPTOR = "com.xiaopeng.ipc.IPCCallback";
        static final int TRANSACTION_onReceive = 1;

        public a() {
            attachInterface(this, DESCRIPTOR);
        }

        public static b asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof b)) {
                return (b) queryLocalInterface;
            }
            return new C0079a(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IpcMessage ipcMessage;
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            if (parcel.readInt() != 0) {
                ipcMessage = IpcMessage.CREATOR.createFromParcel(parcel);
            } else {
                ipcMessage = null;
            }
            onReceive(ipcMessage);
            parcel2.writeNoException();
            return true;
        }

        /* compiled from: IPCCallback.java */
        /* renamed from: com.xiaopeng.ipc.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        private static class C0079a implements b {
            private IBinder mRemote;

            C0079a(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.xiaopeng.ipc.b
            public void onReceive(IpcMessage ipcMessage) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(a.DESCRIPTOR);
                    if (ipcMessage != null) {
                        obtain.writeInt(1);
                        ipcMessage.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
