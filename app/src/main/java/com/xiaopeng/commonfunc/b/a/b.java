package com.xiaopeng.commonfunc.b.a;

import android.car.CarNotConnectedException;
import android.car.hardware.CarEcuManager;
import android.car.hardware.CarPropertyValue;
import com.xiaopeng.commonfunc.utils.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: CommonCarModel.java */
/* loaded from: classes11.dex */
public abstract class b<T extends CarEcuManager> {
    protected final String TAG;
    private a nM;
    private volatile T nO;
    private final Collection<Integer> nK = new ArrayList();
    private final Collection<Integer> nL = new ArrayList();
    protected CarEcuManager.CarEcuEventCallback nN = new CarEcuManager.CarEcuEventCallback() { // from class: com.xiaopeng.commonfunc.b.a.b.1
        public void onChangeEvent(CarPropertyValue carPropertyValue) {
            if (b.this.nM != null) {
                b.this.nM.onChangeEvent(carPropertyValue);
            }
        }

        public void onErrorEvent(int i, int i2) {
            String str = b.this.TAG;
            com.xiaopeng.lib.utils.c.i(str, "onErrorEvent propertyId: " + i + " , errorCode: " + i2);
        }
    };

    protected abstract String getServiceName();

    public b(String str) {
        this.TAG = str + "-" + getClass().getSimpleName();
        f.k(this);
        dF();
    }

    public T dE() {
        dF();
        return this.nO;
    }

    private void dF() {
        if (this.nO == null) {
            synchronized (this) {
                if (this.nO == null) {
                    if (com.xiaopeng.commonfunc.utils.c.ep() != null && com.xiaopeng.commonfunc.utils.c.ep().isConnected()) {
                        try {
                            this.nO = (T) com.xiaopeng.commonfunc.utils.c.ep().getCarManager(getServiceName());
                        } catch (CarNotConnectedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        com.xiaopeng.lib.utils.c.i(this.TAG, "Car is not connected yet");
                    }
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(Integer num) {
        if (num.intValue() == 66442200) {
            com.xiaopeng.lib.utils.c.f(this.TAG, "onEvent CAR_SERVICE_CONNECTED");
            dF();
            synchronized (this.nK) {
                if (!this.nK.isEmpty()) {
                    a(this.nK);
                }
            }
            synchronized (this.nL) {
                if (!this.nL.isEmpty()) {
                    c(this.nL);
                }
            }
            CarPropertyValue carPropertyValue = new CarPropertyValue(1, (Object) null);
            if (this.nM != null) {
                this.nM.onChangeEvent(carPropertyValue);
            }
        }
    }

    public void a(Collection<Integer> collection, a aVar) {
        com.xiaopeng.lib.utils.c.f(this.TAG, "registerPropCallback listener");
        synchronized (this.nK) {
            this.nK.addAll(collection);
            this.nM = aVar;
            a(collection);
        }
    }

    public void b(Collection<Integer> collection, a aVar) {
        com.xiaopeng.lib.utils.c.f(this.TAG, "registerPropCallbackWithCache listener");
        synchronized (this.nL) {
            this.nL.addAll(collection);
            this.nM = aVar;
            c(collection);
        }
    }

    private void a(Collection<Integer> collection) {
        String str = this.TAG;
        com.xiaopeng.lib.utils.c.g(str, "registerPropCallback" + Arrays.toString(collection.toArray()));
        try {
            dF();
            if (this.nO != null) {
                this.nO.registerPropCallback(collection, this.nN);
            } else {
                com.xiaopeng.lib.utils.c.i(this.TAG, "registerPropCallback carEcuManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void b(Collection<Integer> collection) {
        String str = this.TAG;
        com.xiaopeng.lib.utils.c.g(str, "unregisterPropCallback" + Arrays.toString(collection.toArray()));
        try {
            dF();
            if (this.nO != null) {
                this.nO.unregisterPropCallback(collection, this.nN);
            } else {
                com.xiaopeng.lib.utils.c.i(this.TAG, "registerPropCallback carEcuManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    private void c(Collection<Integer> collection) {
        com.xiaopeng.lib.utils.c.f(this.TAG, "registerPropCallbackWithCache");
        try {
            dF();
            if (this.nO != null) {
                this.nO.registerPropCallbackWithFlag(collection, this.nN, 1);
            } else {
                com.xiaopeng.lib.utils.c.i(this.TAG, "registerPropCallbackWithCache carEcuManager == null");
            }
        } catch (CarNotConnectedException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        f.l(this);
        try {
            if (this.nO != null) {
                this.nO.unregisterCallback(this.nN);
            }
            synchronized (this.nK) {
                this.nK.clear();
            }
            synchronized (this.nL) {
                this.nL.clear();
            }
            this.nM = null;
            this.nO = null;
            com.xiaopeng.lib.utils.c.f(this.TAG, "disconnect CarEcuManager");
        } catch (CarNotConnectedException e) {
            String str = this.TAG;
            com.xiaopeng.lib.utils.c.i(str, "disconnect CarEcuManager failed!" + e);
        }
    }
}
