package com.xiaopeng.devtools.view.factorytest.vehicle;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.u;
import com.xiaopeng.devtools.view.ActionBar;
import com.xiaopeng.devtools.view.AppBaseActivity;
import com.xiaopeng.lib.utils.c;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class VehicleFactoryTestActivity extends AppBaseActivity implements View.OnClickListener {
    private ActionBar Cq;
    private HashMap<Integer, String> DC;
    private SparseArray<Button> Hd;
    private ViewGroup mContainer;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Integer num) {
        Button button;
        if (this.Hd != null && (button = this.Hd.get(num.intValue())) != null) {
            button.setBackgroundResource(R.drawable.btn_test_background);
        }
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_vehicle_factory_test);
        this.DC = new HashMap<>();
        this.DC.put(1, getString(R.string.update_new_devices_id));
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.mContainer = (ViewGroup) findViewById(R.id.container);
        this.Cq = (ActionBar) findViewById(R.id.action_bar);
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Hd = u.a(this.mContainer, this, this, this.DC, 3);
        EventBus.getDefault().register(this);
    }

    @Override // com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Cq.setOnBackListener(new ActionBar.a() { // from class: com.xiaopeng.devtools.view.factorytest.vehicle.-$$Lambda$VehicleFactoryTestActivity$WzvG6nQPWMknuCKx8MDbgOmkgWw
            @Override // com.xiaopeng.devtools.view.ActionBar.a
            public final void onBack(View view) {
                VehicleFactoryTestActivity.this.g(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(View view) {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        c.f("VehicleFactoryTestActivity", "onResume");
        super.onResume();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == 1) {
            c.f("VehicleFactoryTestActivity", "GO TO UploadDevicesIdActivity.class");
            e(UploadDevicesIdActivity.class);
        }
    }
}
