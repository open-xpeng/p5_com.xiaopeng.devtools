package com.xiaopeng.devtools.view.smartdrive;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.f.c;
import com.xiaopeng.devtools.view.AppBaseActivity;

/* loaded from: classes12.dex */
public class CanTestActivity extends AppBaseActivity implements a {
    private TableLayout SM;
    private Button SN;
    private TextView SO;
    private Button SQ;
    private Spinner SR;
    private Spinner SS;
    private EditText SU;
    int period;
    private c uE;
    private int[] ST = new int[64];
    int SV = 1;
    private int SW = 1;
    private String SX = "";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_can_test);
        this.uE = new com.xiaopeng.devtools.presenter.f.a(this);
        nN();
        init();
    }

    private void nN() {
        this.SM = (TableLayout) findViewById(R.id.tableLayout);
        this.SN = (Button) findViewById(R.id.btn_generateByte);
        this.SO = (TextView) findViewById(R.id.text_showByte);
        this.SQ = (Button) findViewById(R.id.period_event);
        this.SR = (Spinner) findViewById(R.id.spinner_period);
        this.SS = (Spinner) findViewById(R.id.spinner_can);
        this.SU = (EditText) findViewById(R.id.edit_text);
    }

    private void init() {
        int i = 63;
        for (int childCount = this.SM.getChildCount() - 4; childCount >= 0; childCount--) {
            TableRow tableRow = (TableRow) this.SM.getChildAt(childCount);
            for (int i2 = 0; i2 < tableRow.getChildCount(); i2++) {
                final Button button = (Button) tableRow.getChildAt(i2);
                button.setText("" + i);
                button.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.smartdrive.CanTestActivity.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        int intValue = Integer.valueOf(button.getText().toString()).intValue();
                        if (button.isSelected()) {
                            CanTestActivity.this.ST[intValue] = 0;
                            button.setSelected(false);
                            return;
                        }
                        CanTestActivity.this.ST[intValue] = 1;
                        button.setSelected(true);
                    }
                });
                i += -1;
            }
        }
        this.SN.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.smartdrive.CanTestActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CanTestActivity.this.uE.a(CanTestActivity.this.SW, CanTestActivity.this.period, CanTestActivity.this.SV, CanTestActivity.this.SX, CanTestActivity.this.ST);
            }
        });
        this.SQ.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.smartdrive.CanTestActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CanTestActivity.this.SQ.getText().toString().equals(CanTestActivity.this.getString(R.string.event))) {
                    CanTestActivity.this.SQ.setText(CanTestActivity.this.getString(R.string.period));
                    CanTestActivity.this.SW = 2;
                } else {
                    CanTestActivity.this.SQ.setText(CanTestActivity.this.getString(R.string.event));
                    CanTestActivity.this.SW = 1;
                }
                CanTestActivity.this.SO.setText((CharSequence) null);
                CanTestActivity.this.nO();
                CanTestActivity.this.nP();
            }
        });
        this.SR.setAdapter((SpinnerAdapter) ArrayAdapter.createFromResource(this, R.array.period_select, 17367049));
        this.SR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.xiaopeng.devtools.view.smartdrive.CanTestActivity.4
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j) {
                if (CanTestActivity.this.SW == 2) {
                    CanTestActivity.this.period = Integer.parseInt(adapterView.getItemAtPosition(i3).toString());
                    return;
                }
                CanTestActivity.this.period = 0;
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.SS.setAdapter((SpinnerAdapter) ArrayAdapter.createFromResource(this, R.array.can_select, 17367049));
        this.SS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.xiaopeng.devtools.view.smartdrive.CanTestActivity.5
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i3, long j) {
                String obj = adapterView.getItemAtPosition(i3).toString();
                Log.d("factory", obj);
                if ("CAN1".equals(obj)) {
                    CanTestActivity.this.SV = 1;
                } else {
                    CanTestActivity.this.SV = 2;
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.SU.addTextChangedListener(new TextWatcher() { // from class: com.xiaopeng.devtools.view.smartdrive.CanTestActivity.6
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                CanTestActivity.this.SX = editable.toString();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nO() {
        for (int i = 0; i < this.ST.length; i++) {
            this.ST[i] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nP() {
        for (int childCount = this.SM.getChildCount() - 4; childCount >= 0; childCount--) {
            TableRow tableRow = (TableRow) this.SM.getChildAt(childCount);
            for (int i = 0; i < tableRow.getChildCount(); i++) {
                ((Button) tableRow.getChildAt(i)).setSelected(false);
            }
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.uE.onDestroy();
    }
}
