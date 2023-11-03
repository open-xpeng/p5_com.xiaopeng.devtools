package com.xiaopeng.devtools.view.aftersales.cdu;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaopeng.commonfunc.bean.aftersales.DiagnosisErrorList;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.aftersales.c;
import com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity;
import com.xiaopeng.lib.utils.j;
import java.util.List;

/* loaded from: classes12.dex */
public class DiagnosisRecordActivity extends AfterSalesBaseActivity implements com.xiaopeng.devtools.view.aftersales.cdu.a {
    private TabLayout EV;
    private c EW;
    private DiagnosisRecordAdapter EX;
    private int EY;
    private ListView mListView;

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_diagnosis);
        this.EY = getIntent().getIntExtra("diagnosis_module", -1);
        this.EW = new c(this, this.EY - 1);
        this.EX = new DiagnosisRecordAdapter();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.mListView = (ListView) findViewById(R.id.diagnosis_list);
        this.EV = (TabLayout) findViewById(R.id.tabs);
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.mListView.setAdapter((ListAdapter) this.EX);
        for (int i = 0; i < this.EW.dI().length; i++) {
            this.EV.addTab(this.EV.newTab().setCustomView(dd(this.EW.dI()[i])));
        }
        if (this.EY > 0) {
            this.EV.setVisibility(8);
            this.Cq.setCenterText(getString(R.string.diagnosis_error_code_with_module, new Object[]{this.EW.m98if()}));
        }
    }

    @Override // com.xiaopeng.devtools.view.aftersales.AfterSalesBaseActivity, com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        this.EV.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.xiaopeng.devtools.view.aftersales.cdu.DiagnosisRecordActivity.1
            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                DiagnosisRecordActivity.this.EW.ch(tab.getPosition());
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.OnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.cdu.-$$Lambda$DiagnosisRecordActivity$YiRmnZqevffQrQi1S6Im9q2IjGg
            @Override // java.lang.Runnable
            public final void run() {
                DiagnosisRecordActivity.this.mi();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void mi() {
        this.EW.dJ();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.cdu.a
    public void a(final DiagnosisErrorList diagnosisErrorList) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.cdu.-$$Lambda$DiagnosisRecordActivity$a5uikKh9H0Y83v6nPzfYBUxY7s8
            @Override // java.lang.Runnable
            public final void run() {
                DiagnosisRecordActivity.this.b(diagnosisErrorList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(DiagnosisErrorList diagnosisErrorList) {
        this.EX.setDataList(diagnosisErrorList);
        this.EX.notifyDataSetChanged();
    }

    @Override // com.xiaopeng.devtools.view.aftersales.cdu.a
    public void v(final List<DiagnosisErrorList> list) {
        runOnUiThread(new Runnable() { // from class: com.xiaopeng.devtools.view.aftersales.cdu.-$$Lambda$DiagnosisRecordActivity$-Nu-C8V_38nnQJuVf_6cbEEUDI8
            @Override // java.lang.Runnable
            public final void run() {
                DiagnosisRecordActivity.this.w(list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(List list) {
        for (int i = 0; i < list.size(); i++) {
            a aVar = (a) this.EV.getTabAt(i).getCustomView().getTag();
            TextView textView = aVar.Es;
            textView.setText(this.EW.dI()[i] + "[" + ((DiagnosisErrorList) list.get(i)).getDiagnosisDataSize() + "]");
            aVar.mIconView.setImageResource(((DiagnosisErrorList) list.get(i)).getDiagnosisDataSize() == 0 ? R.drawable.icon_green : R.drawable.icon_red);
        }
    }

    private View dd(String str) {
        a aVar = new a();
        View inflate = LayoutInflater.from(this).inflate(R.layout.tab_text_icon, (ViewGroup) null);
        aVar.Es = (TextView) inflate.findViewById(R.id.textview);
        aVar.mIconView = (ImageView) inflate.findViewById(R.id.imageview);
        inflate.setTag(aVar);
        aVar.Es.setText(str);
        aVar.mIconView.setImageResource(R.drawable.icon_yellow);
        return inflate;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        this.EV.removeAllTabs();
        super.onDestroy();
        this.EW.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class a {
        TextView Es;
        ImageView mIconView;

        private a() {
        }
    }
}
