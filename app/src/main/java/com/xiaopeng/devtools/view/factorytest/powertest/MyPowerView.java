package com.xiaopeng.devtools.view.factorytest.powertest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class MyPowerView extends LinearLayout implements View.OnClickListener {
    private TextView Pa;
    private TextView Pb;
    private Button Pc;
    private Button Pd;
    private Button Pe;
    private Button Pf;
    private a Pg;

    /* loaded from: classes12.dex */
    interface a {
        void nA();

        void nB();

        void nC();

        void nz();
    }

    public MyPowerView(Context context) {
        this(context, null);
    }

    public MyPowerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyPowerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_power_test, this);
        this.Pa = (TextView) findViewById(R.id.tv_current_state);
        this.Pb = (TextView) findViewById(R.id.result_control_tv);
        this.Pc = (Button) findViewById(R.id.bt_control_success);
        this.Pd = (Button) findViewById(R.id.bt_control_fail);
        this.Pe = (Button) findViewById(R.id.bt_control_on);
        this.Pf = (Button) findViewById(R.id.bt_control_off);
        this.Pc.setOnClickListener(this);
        this.Pd.setOnClickListener(this);
        this.Pe.setOnClickListener(this);
        this.Pf.setOnClickListener(this);
    }

    public void setCurrentState(String str) {
        this.Pa.setText(getContext().getString(R.string.text_on_off_state, str));
    }

    public void setResult(boolean z) {
        if (z) {
            this.Pb.setText(R.string.test_succeed);
            this.Pb.setTextColor(getResources().getColor(17170450));
            return;
        }
        this.Pb.setText(R.string.test_fail);
        this.Pb.setTextColor(getResources().getColor(17170454));
    }

    public void setOnControlListener(a aVar) {
        this.Pg = aVar;
    }

    public void setControlOnEnableStatus(boolean z) {
        this.Pe.setEnabled(z);
    }

    public void setControlOffEnableStatus(boolean z) {
        this.Pf.setEnabled(z);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.Pg == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.bt_control_fail /* 2131296415 */:
                this.Pg.nC();
                return;
            case R.id.bt_control_off /* 2131296416 */:
                this.Pg.nA();
                return;
            case R.id.bt_control_on /* 2131296417 */:
                this.Pg.nz();
                return;
            case R.id.bt_control_success /* 2131296418 */:
                this.Pg.nB();
                return;
            default:
                return;
        }
    }
}
