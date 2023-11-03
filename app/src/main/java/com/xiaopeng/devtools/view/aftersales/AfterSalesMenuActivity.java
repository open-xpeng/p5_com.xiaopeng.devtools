package com.xiaopeng.devtools.view.aftersales;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.ButtonData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes12.dex */
public class AfterSalesMenuActivity extends AfterSalesBaseActivity implements View.OnClickListener {
    private ViewGroup mContainer;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_aftersales);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.mContainer = (ViewGroup) findViewById(R.id.container);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(HashMap<Integer, String> hashMap) {
        this.mContainer.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        layoutParams.setMargins(0, 5, 0, 5);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setWeightSum(4.0f);
        LinearLayout linearLayout2 = linearLayout;
        int i = 0;
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            i++;
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, 100, 1.0f);
            layoutParams2.setMargins(5, 5, 5, 5);
            button.setLayoutParams(layoutParams2);
            button.setId(entry.getKey().intValue());
            button.setTextColor(-16777216);
            button.setTextSize(30.0f);
            button.setText(entry.getValue());
            button.setBackgroundResource(R.drawable.btn_background);
            button.setOnClickListener(this);
            com.xiaopeng.lib.utils.c.d("AfterSalesMenuActivity", "createUI", "Create Button. id=" + entry.getKey() + ", name=" + entry.getValue());
            linearLayout2.addView(button);
            if (i % 4 == 0) {
                this.mContainer.addView(linearLayout2);
                linearLayout2 = new LinearLayout(this);
                linearLayout2.setLayoutParams(layoutParams);
                linearLayout2.setOrientation(0);
                linearLayout2.setWeightSum(4.0f);
            }
        }
        if (i % 4 != 0) {
            this.mContainer.addView(linearLayout2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(ArrayList<ButtonData> arrayList) {
        this.mContainer.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        layoutParams.setMargins(0, 5, 0, 5);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        linearLayout.setWeightSum(4.0f);
        Iterator<ButtonData> it = arrayList.iterator();
        LinearLayout linearLayout2 = linearLayout;
        int i = 0;
        while (it.hasNext()) {
            ButtonData next = it.next();
            i++;
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, 100, 1.0f);
            layoutParams2.setMargins(5, 5, 5, 5);
            button.setLayoutParams(layoutParams2);
            button.setId(i);
            button.setTextColor(-16777216);
            button.setTextSize(30.0f);
            button.setText(next.getTextString());
            button.setBackgroundResource(R.drawable.btn_background);
            button.setOnClickListener(this);
            com.xiaopeng.lib.utils.c.d("AfterSalesMenuActivity", "createUI", "Create Button. id=" + i + ", name=" + next.getTextString());
            linearLayout2.addView(button);
            if (i % 4 == 0) {
                this.mContainer.addView(linearLayout2);
                linearLayout2 = new LinearLayout(this);
                linearLayout2.setLayoutParams(layoutParams);
                linearLayout2.setOrientation(0);
                linearLayout2.setWeightSum(4.0f);
            }
        }
        if (i % 4 != 0) {
            this.mContainer.addView(linearLayout2);
        }
    }
}
