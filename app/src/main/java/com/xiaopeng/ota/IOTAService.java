package com.xiaopeng.ota;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.xiaopeng.ota.IOTAListener;

/* loaded from: classes12.dex */
public interface IOTAService extends IInterface {
    void checkCampaign() throws RemoteException;

    String getCduVersion() throws RemoteException;

    void getECUSVersion() throws RemoteException;

    double getInstallProgress() throws RemoteException;

    int getInstallState() throws RemoteException;

    String getPsuVersion() throws RemoteException;

    String getText(String str) throws RemoteException;

    String getUpgradeTime() throws RemoteException;

    boolean hasCduFileFromUsb() throws RemoteException;

    boolean hasMcuFileFromUsb() throws RemoteException;

    boolean hasMeterFileFromUsb() throws RemoteException;

    boolean hasPsuFileFromUsb() throws RemoteException;

    boolean hasScuFileFromUsb() throws RemoteException;

    boolean isCarTypeD10() throws RemoteException;

    void onScreenHide() throws RemoteException;

    void onScreenShow() throws RemoteException;

    void registerListener(IOTAListener iOTAListener) throws RemoteException;

    boolean resetPsuKey(byte[] bArr) throws RemoteException;

    void setUpgradeTime() throws RemoteException;

    void unregisterListener(IOTAListener iOTAListener) throws RemoteException;

    void updateCduFromUsb() throws RemoteException;

    void updateEcuComplete(String str, boolean z) throws RemoteException;

    void updateMcuFromUsb() throws RemoteException;

    void updateMeterFromUsb() throws RemoteException;

    void updatePsuFromUsb() throws RemoteException;

    void updateScuFromUsb() throws RemoteException;

    void upgradeNow() throws RemoteException;

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IOTAService {
        private static final String DESCRIPTOR = "com.xiaopeng.ota.IOTAService";
        static final int TRANSACTION_checkCampaign = 5;
        static final int TRANSACTION_getCduVersion = 1;
        static final int TRANSACTION_getECUSVersion = 4;
        static final int TRANSACTION_getInstallProgress = 9;
        static final int TRANSACTION_getInstallState = 8;
        static final int TRANSACTION_getPsuVersion = 23;
        static final int TRANSACTION_getText = 7;
        static final int TRANSACTION_getUpgradeTime = 25;
        static final int TRANSACTION_hasCduFileFromUsb = 14;
        static final int TRANSACTION_hasMcuFileFromUsb = 10;
        static final int TRANSACTION_hasMeterFileFromUsb = 12;
        static final int TRANSACTION_hasPsuFileFromUsb = 21;
        static final int TRANSACTION_hasScuFileFromUsb = 16;
        static final int TRANSACTION_isCarTypeD10 = 26;
        static final int TRANSACTION_onScreenHide = 19;
        static final int TRANSACTION_onScreenShow = 18;
        static final int TRANSACTION_registerListener = 2;
        static final int TRANSACTION_resetPsuKey = 22;
        static final int TRANSACTION_setUpgradeTime = 24;
        static final int TRANSACTION_unregisterListener = 3;
        static final int TRANSACTION_updateCduFromUsb = 15;
        static final int TRANSACTION_updateEcuComplete = 27;
        static final int TRANSACTION_updateMcuFromUsb = 11;
        static final int TRANSACTION_updateMeterFromUsb = 13;
        static final int TRANSACTION_updatePsuFromUsb = 20;
        static final int TRANSACTION_updateScuFromUsb = 17;
        static final int TRANSACTION_upgradeNow = 6;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOTAService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IOTAService)) {
                return (IOTAService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            boolean z;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    String cduVersion = getCduVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(cduVersion);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerListener(IOTAListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterListener(IOTAListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    getECUSVersion();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    checkCampaign();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    upgradeNow();
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    String text = getText(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeString(text);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int installState = getInstallState();
                    parcel2.writeNoException();
                    parcel2.writeInt(installState);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    double installProgress = getInstallProgress();
                    parcel2.writeNoException();
                    parcel2.writeDouble(installProgress);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasMcuFileFromUsb = hasMcuFileFromUsb();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasMcuFileFromUsb ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMcuFromUsb();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasMeterFileFromUsb = hasMeterFileFromUsb();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasMeterFileFromUsb ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateMeterFromUsb();
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasCduFileFromUsb = hasCduFileFromUsb();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasCduFileFromUsb ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateCduFromUsb();
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasScuFileFromUsb = hasScuFileFromUsb();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasScuFileFromUsb ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateScuFromUsb();
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScreenShow();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScreenHide();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    updatePsuFromUsb();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean hasPsuFileFromUsb = hasPsuFileFromUsb();
                    parcel2.writeNoException();
                    parcel2.writeInt(hasPsuFileFromUsb ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean resetPsuKey = resetPsuKey(parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(resetPsuKey ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    String psuVersion = getPsuVersion();
                    parcel2.writeNoException();
                    parcel2.writeString(psuVersion);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    setUpgradeTime();
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    String upgradeTime = getUpgradeTime();
                    parcel2.writeNoException();
                    parcel2.writeString(upgradeTime);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isCarTypeD10 = isCarTypeD10();
                    parcel2.writeNoException();
                    parcel2.writeInt(isCarTypeD10 ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    if (parcel.readInt() == 0) {
                        z = false;
                    } else {
                        z = true;
                    }
                    updateEcuComplete(readString, z);
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* loaded from: classes12.dex */
        private static class Proxy implements IOTAService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.xiaopeng.ota.IOTAService
            public String getCduVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void registerListener(IOTAListener iOTAListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOTAListener != null ? iOTAListener.asBinder() : null);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void unregisterListener(IOTAListener iOTAListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOTAListener != null ? iOTAListener.asBinder() : null);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void getECUSVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void checkCampaign() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void upgradeNow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public String getText(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public int getInstallState() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public double getInstallProgress() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public boolean hasMcuFileFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void updateMcuFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public boolean hasMeterFileFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void updateMeterFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public boolean hasCduFileFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void updateCduFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public boolean hasScuFileFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void updateScuFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void onScreenShow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void onScreenHide() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void updatePsuFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public boolean hasPsuFileFromUsb() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public boolean resetPsuKey(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public String getPsuVersion() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void setUpgradeTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public String getUpgradeTime() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public boolean isCarTypeD10() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.xiaopeng.ota.IOTAService
            public void updateEcuComplete(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
