package com.xiaopeng.devtools.view.pre_env;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.view.ToolBarActivity;
import com.xiaopeng.lib.utils.a.b;
import com.xiaopeng.lib.utils.a.c;

/* loaded from: classes12.dex */
public class PreEnvActivity extends ToolBarActivity implements View.OnClickListener {
    private TextView Sb;
    private Button Sc;
    private Button Sd;

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_pre_env);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Sb = (TextView) findViewById(R.id.tv_pre_env_status);
        this.Sc = (Button) findViewById(R.id.bt_set_pre_env);
        this.Sd = (Button) findViewById(R.id.bt_remove_pre_env);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        updateView();
    }

    private void updateView() {
        if (b.pb()) {
            this.Sb.setTextColor(getResources().getColor(17170453));
            this.Sb.setText(R.string.text_test_irdeto_pre_env);
            return;
        }
        this.Sb.setTextColor(getResources().getColor(17170457));
        this.Sb.setText(R.string.text_test_not_pre_env);
    }

    @Override // com.xiaopeng.devtools.view.ToolBarActivity, com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.Sc.setOnClickListener(this);
        this.Sd.setOnClickListener(this);
    }

    @Override // com.xiaopeng.devtools.view.factorytest.FactoryTestBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_remove_pre_env) {
            c.pf();
            updateView();
        } else if (id == R.id.bt_set_pre_env) {
            c.pe();
            updateView();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
