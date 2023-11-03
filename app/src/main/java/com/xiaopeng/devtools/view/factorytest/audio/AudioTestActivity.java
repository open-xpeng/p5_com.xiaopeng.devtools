package com.xiaopeng.devtools.view.factorytest.audio;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.g;
import com.xiaopeng.devtools.view.ActionBarActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class AudioTestActivity extends ActionBarActivity {
    TextView Gm = null;
    RecyclerView Gn = null;
    private a Go = null;
    private List<String> Gp = new ArrayList();
    public String mPath;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_audio_test);
        dJ();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Gm = (TextView) findViewById(R.id.txt_audio_test_total_tip);
        this.Gn = (RecyclerView) findViewById(R.id.rv_audio_test);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Gm.setText(getString(R.string.text_test_audio_total_tip, new Object[]{Integer.valueOf(this.Gp.size())}));
        this.Go = new a(this, this.mPath);
        this.Go.x(this.Gp);
        this.Gn.setLayoutManager(new LinearLayoutManager(this));
        this.Gn.setAdapter(this.Go);
    }

    private void dJ() {
        this.mPath = g.V(this) + "/xptestmusic/";
        this.Gp = g.cw(this.mPath);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Go.stopPlay();
    }
}
