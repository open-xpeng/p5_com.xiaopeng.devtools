package com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.factorytest.hardwaretest.xpu.XpuImagePresenter;
import com.xiaopeng.devtools.view.AsOrFacBaseActivity;
import com.xiaopeng.xui.app.c;
import com.xiaopeng.xui.app.d;

/* loaded from: classes12.dex */
public class XpuImageTestActivity extends AsOrFacBaseActivity implements b {
    private ImageView OG;
    private XpuImagePresenter OH;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        new c(this).e(getString(R.string.title_dialog)).f(getString(R.string.xpu_warning)).a(getString(R.string.dialog_confirm), (d.a) null).b(getString(R.string.cancel), (d.a) null).show(5, 0);
        this.OH.jz();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_test_xpu_image);
        this.OH = new XpuImagePresenter(this);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.OG = (ImageView) findViewById(R.id.iv_xpu_image);
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.OH.jy();
    }

    @Override // com.xiaopeng.devtools.view.AsOrFacBaseActivity, com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.b
    public void e(final Bitmap bitmap) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.XpuImageTestActivity.1
            @Override // java.lang.Runnable
            public void run() {
                XpuImageTestActivity.this.OG.setImageBitmap(bitmap);
            }
        });
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.OH.jA();
    }
}
