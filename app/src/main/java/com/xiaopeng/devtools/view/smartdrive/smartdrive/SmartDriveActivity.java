package com.xiaopeng.devtools.view.smartdrive.smartdrive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.smartdrive.SmartDriveData;
import com.xiaopeng.devtools.presenter.f.e;
import com.xiaopeng.devtools.presenter.f.f;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.xui.app.g;
import java.util.List;

/* loaded from: classes12.dex */
public class SmartDriveActivity extends ActionBarActivity implements com.xiaopeng.devtools.a, a {
    private LinearLayout Rq;
    private LinearLayout Rx;
    private TextView Tv;
    private TextView Tw;
    private TextView Tx;
    private e Ty;
    private int message = 0;

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_read_file);
        this.Ty = new f(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Rq = (LinearLayout) findViewById(R.id.layout_Container);
        this.Ty.kb();
        this.Ty.onCreate();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Ty.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.smartdrive.smartdrive.a
    public void s(@NonNull List<SmartDriveData> list) {
        for (int i = 0; i < list.size(); i++) {
            final SmartDriveData smartDriveData = list.get(i);
            int i2 = i % 3;
            if (i2 == 0) {
                this.Rx = new LinearLayout(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.topMargin = 48;
                this.Rx.setLayoutParams(layoutParams);
                this.Rx.setOrientation(0);
                this.Rx.setGravity(17);
            }
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams((int) TestResultItem.INDEX_KEYTEST, 90);
            layoutParams2.gravity = 17;
            if (i2 != 0) {
                layoutParams2.leftMargin = 90;
            }
            button.setLayoutParams(layoutParams2);
            button.setBackgroundResource(R.drawable.selector_btn_common);
            button.setTextSize(24.0f);
            button.setText(smartDriveData.getBtName());
            button.setTextColor(-1);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.smartdrive.smartdrive.-$$Lambda$SmartDriveActivity$JV-jbnAVcjPmqZUIS4AoOoSGHhw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SmartDriveActivity.this.a(smartDriveData, view);
                }
            });
            this.Rx.addView(button);
            if (i2 == 2) {
                this.Rq.addView(this.Rx);
            }
        }
        if (list.size() % 3 != 0) {
            this.Rq.addView(this.Rx);
        }
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.topMargin = 300;
        linearLayout.setLayoutParams(layoutParams3);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(1);
        this.Tv = new TextView(this);
        this.Tv.setTextSize(24.0f);
        this.Tv.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams4.gravity = 17;
        layoutParams4.leftMargin = 60;
        this.Tx = new TextView(this);
        this.Tx.setTextSize(24.0f);
        this.Tx.setLayoutParams(layoutParams4);
        this.Tw = new TextView(this);
        this.Tw.setTextSize(24.0f);
        this.Tw.setLayoutParams(layoutParams4);
        linearLayout.addView(this.Tv);
        linearLayout.addView(this.Tx);
        linearLayout.addView(this.Tw);
        this.Rq.addView(linearLayout);
        this.Ty.ka();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(SmartDriveData smartDriveData, View view) {
        this.Ty.b(smartDriveData);
        this.message++;
        g.i(MyApplication.getContext().getString(R.string.hint_message, Integer.valueOf(this.message)));
        nU();
    }

    private void nU() {
        if (this.Tv != null) {
            this.Tv.setText("");
        }
        if (this.Tw != null) {
            this.Tw.setText("");
        }
        if (this.Tx != null) {
            this.Tx.setText("");
        }
    }

    @Override // com.xiaopeng.devtools.view.smartdrive.smartdrive.a
    public void a(int[] iArr) {
        if (this.Tv != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("SCU_CDU_Mode_Index: " + iArr[6]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_1: " + iArr[0]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_2: " + iArr[1]);
            this.Tv.setText(sb.toString());
        }
    }

    @Override // com.xiaopeng.devtools.view.smartdrive.smartdrive.a
    public void b(int[] iArr) {
        if (this.Tw != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("SCU_CDU_Log_Data_7: " + iArr[0]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_8: " + iArr[1]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_9: " + iArr[2]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_10: " + iArr[2]);
            this.Tw.setText(sb.toString());
        }
    }

    @Override // com.xiaopeng.devtools.view.smartdrive.smartdrive.a
    public void c(int[] iArr) {
        if (this.Tx != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("SCU_CDU_Log_Data_3: " + iArr[0]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_4: " + iArr[1]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_5: " + iArr[2]);
            sb.append("\n\n");
            sb.append("SCU_CDU_Log_Data_6: " + iArr[3]);
            this.Tx.setText(sb.toString());
        }
    }
}
