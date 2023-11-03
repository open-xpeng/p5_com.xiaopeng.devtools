package com.xiaopeng.devtools.view.oled;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.oled.CANMsg387;
import com.xiaopeng.devtools.view.ActionBar;
import com.xiaopeng.devtools.view.oled.c;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class OledMusicBookActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, a, c.a {
    private ActionBar Cq;
    private Button RJ;
    private Button RK;
    private Button RL;
    private TextView RM;
    private NoScrollViewPager RN;
    private List<CANMsg387> RO;
    private com.xiaopeng.devtools.presenter.oled.b RP;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oled_music_book);
        this.RO = new ArrayList();
        this.RP = new com.xiaopeng.devtools.presenter.oled.b(this);
        this.RJ = (Button) findViewById(R.id.btn_note_down);
        this.RK = (Button) findViewById(R.id.btn_note_up);
        this.RL = (Button) findViewById(R.id.bt_save_note);
        this.RM = (TextView) findViewById(R.id.tv_note);
        this.RN = (NoScrollViewPager) findViewById(R.id.viewpager_note);
        this.Cq = (ActionBar) findViewById(R.id.action_bar);
        this.RP.jQ();
        this.RL.setOnClickListener(this);
        this.RK.setOnClickListener(this);
        this.RJ.setOnClickListener(this);
        this.Cq.setOnBackListener(new ActionBar.a() { // from class: com.xiaopeng.devtools.view.oled.OledMusicBookActivity.1
            @Override // com.xiaopeng.devtools.view.ActionBar.a
            public void onBack(View view) {
                OledMusicBookActivity.this.setResult(0);
                OledMusicBookActivity.this.finish();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bt_save_note) {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelableList("music_note_param", this.RO);
            intent.putExtras(bundle);
            setResult(-1, intent);
            finish();
            return;
        }
        switch (id) {
            case R.id.btn_note_down /* 2131296486 */:
                int intValue = Integer.valueOf(this.RM.getText().toString()).intValue();
                if (intValue > 1) {
                    intValue--;
                }
                this.RN.setCurrentItem(intValue - 1, false);
                return;
            case R.id.btn_note_up /* 2131296487 */:
                this.RN.setCurrentItem(Integer.valueOf(this.RM.getText().toString()).intValue(), false);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.devtools.view.oled.a
    public void y(List<CANMsg387> list) {
        this.RO = list;
        this.RN.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) { // from class: com.xiaopeng.devtools.view.oled.OledMusicBookActivity.2
            @Override // android.support.v4.app.FragmentPagerAdapter
            public Fragment getItem(int i) {
                return c.b((CANMsg387) OledMusicBookActivity.this.RO.get(i + 1));
            }

            @Override // android.support.v4.view.PagerAdapter
            public int getCount() {
                return OledMusicBookActivity.this.RO.size() - 1;
            }
        });
        this.RN.setCurrentItem(0);
        this.RJ.setEnabled(false);
        this.RN.setOnPageChangeListener(this);
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (i == 0) {
            this.RJ.setEnabled(false);
            this.RK.setEnabled(true);
        } else if (i == this.RO.size() - 2) {
            this.RJ.setEnabled(true);
            this.RK.setEnabled(false);
        } else {
            this.RK.setEnabled(true);
            this.RJ.setEnabled(true);
        }
        TextView textView = this.RM;
        textView.setText("" + (i + 1));
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }
}
