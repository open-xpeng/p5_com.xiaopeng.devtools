package com.xiaopeng.devtools.presenter.a;

import android.car.hardware.CarPropertyValue;
import com.xiaopeng.lib.utils.c;
import java.util.Arrays;
import java.util.function.IntFunction;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: AlsStudyPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private com.xiaopeng.devtools.model.a.a wo = com.xiaopeng.devtools.model.a.a.fu();
    private com.xiaopeng.devtools.view.ecucheck.als.a wp;

    public a(com.xiaopeng.devtools.view.ecucheck.als.a aVar) {
        this.wp = aVar;
    }

    public void iv() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    public void init() {
        int[] alsInitializationStudyAndErrorState = com.xiaopeng.devtools.model.a.a.fu().getAlsInitializationStudyAndErrorState();
        if (alsInitializationStudyAndErrorState != null) {
            b((Integer[]) Arrays.stream(alsInitializationStudyAndErrorState).boxed().toArray(new IntFunction() { // from class: com.xiaopeng.devtools.presenter.a.-$$Lambda$a$Wk8v2v72jtNmr--D8ocl7UkQsU0
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    Integer[] ck;
                    ck = a.ck(i);
                    return ck;
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer[] ck(int i) {
        return new Integer[i];
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEvent(CarPropertyValue carPropertyValue) {
        if (carPropertyValue.getPropertyId() == 557914125) {
            b((Integer[]) carPropertyValue.getValue());
        }
    }

    private void b(Integer[] numArr) {
        c.f("AlsStudyPresenter", "result: " + numArr);
        if (numArr != null && numArr.length > 1) {
            c.f("AlsStudyPresenter", "ID_ALS_INIT_STUDY_AND_ERROR_ST result[0]:" + numArr[0] + ", result[1]:" + numArr[1]);
            switch (numArr[0].intValue()) {
                case 0:
                    if (numArr[1].intValue() == 0) {
                        cj(1);
                        return;
                    } else {
                        cj(0);
                        return;
                    }
                case 1:
                    cj(2);
                    return;
                case 2:
                    cj(3);
                    return;
                default:
                    return;
            }
        }
    }

    private void cj(int i) {
        if (this.wp != null) {
            this.wp.cj(i);
        }
    }

    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
