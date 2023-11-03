package com.xiaopeng.devtools.view.oled;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.oled.OledEvent;
import com.xiaopeng.devtools.bean.oled.CustomOledMusicBook;
import com.xiaopeng.devtools.bean.oled.OledData;
import com.xiaopeng.devtools.presenter.oled.CANMsg387;
import com.xiaopeng.devtools.view.ActionBarActivity;
import com.xiaopeng.devtools.view.oled.adapter.MusicBookAdapter;
import com.xiaopeng.devtools.view.oled.adapter.MusicNoteAdapter;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes12.dex */
public class OledActivity extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, b {
    private List<CustomOledMusicBook> RA;
    private List<CANMsg387> RB;
    private int RC;
    private com.xiaopeng.devtools.presenter.oled.c Rp;
    private LinearLayout Rq;
    private Spinner Rr;
    private Spinner Rs;
    private Button Rt;
    private MusicBookAdapter Ru;
    private MusicNoteAdapter Rv;
    private View Rw;
    private LinearLayout Rx;
    private ArrayMap<String, List<CANMsg387>> Ry;
    private String Rz;
    private int RD = 0;
    private int RE = 1;
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.view.oled.OledActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 2) {
                OledActivity.a(OledActivity.this);
                if (OledActivity.this.RC < OledActivity.this.RA.size()) {
                    CustomOledMusicBook customOledMusicBook = (CustomOledMusicBook) OledActivity.this.RA.get(OledActivity.this.RC);
                    OledActivity.this.Rp.a((CANMsg387) OledActivity.this.RB.get(customOledMusicBook.getNote()));
                    OledActivity.this.mHandler.sendEmptyMessageDelayed(2, customOledMusicBook.getDuration());
                }
            }
        }
    };

    static /* synthetic */ int a(OledActivity oledActivity) {
        int i = oledActivity.RC;
        oledActivity.RC = i + 1;
        return i;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OledEvent oledEvent) {
        int i = oledEvent.mMusicPlayStatus;
        if (i == 2) {
            com.xiaopeng.lib.utils.c.f("qytang", "MUSIC_COMPLETE");
            if (this.Rt != null) {
                this.Rt.setEnabled(true);
            }
            if (this.mHandler.hasMessages(2)) {
                this.mHandler.removeMessages(2);
            }
        } else if (i == 1) {
            this.RC = 0;
            if (this.RA != null) {
                CustomOledMusicBook customOledMusicBook = this.RA.get(this.RC);
                if (this.RB != null) {
                    this.Rp.a(this.RB.get(customOledMusicBook.getNote()));
                    this.mHandler.sendEmptyMessageDelayed(2, customOledMusicBook.getDuration());
                }
            }
        } else {
            if (this.Rt != null) {
                this.Rt.setEnabled(true);
            }
            if (this.mHandler.hasMessages(2)) {
                this.mHandler.removeMessages(2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fe() {
        super.fe();
        setContentView(R.layout.activity_oled);
        this.Rp = new com.xiaopeng.devtools.presenter.oled.c(this);
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void ff() {
        super.ff();
        this.Rq = (LinearLayout) findViewById(R.id.layout_Container);
        this.Ru = new MusicBookAdapter();
        this.Rv = new MusicNoteAdapter();
        this.Ry = new ArrayMap<>();
        this.RA = new ArrayList();
        this.Rp.onCreate();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void lL() {
        super.lL();
        this.Rp.jR();
    }

    @Override // com.xiaopeng.devtools.view.oled.b
    public void z(final List<OledData> list) {
        for (final int i = 0; i < list.size(); i++) {
            final OledData oledData = list.get(i);
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
            button.setText(oledData.getBtName());
            button.setTextColor(-1);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.oled.OledActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    OledActivity.this.Rp.a(oledData);
                    if (i == list.size() - 1) {
                        OledActivity.this.d(OledActivity.this.Rw, 0);
                        return;
                    }
                    OledActivity.this.d(OledActivity.this.Rw, 8);
                    OledActivity.this.Rp.ha();
                    if (OledActivity.this.Rt != null) {
                        OledActivity.this.Rt.setEnabled(true);
                    }
                    if (OledActivity.this.mHandler.hasMessages(2)) {
                        OledActivity.this.mHandler.removeMessages(2);
                    }
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
        this.Rw = LayoutInflater.from(this).inflate(R.layout.layout_custom_oled, (ViewGroup) null);
        this.Rr = (Spinner) this.Rw.findViewById(R.id.spinner_music_book);
        this.Rs = (Spinner) this.Rw.findViewById(R.id.spinner_music_note);
        this.Rt = (Button) this.Rw.findViewById(R.id.btn_play);
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("sound_of_music", "oled/sound_of_music/sound_of_music.mp3");
        this.Ru.setMusicBookMap(arrayMap);
        this.Rr.setAdapter((SpinnerAdapter) this.Ru);
        this.Rr.setSelection(0);
        this.Rz = (String) arrayMap.get(arrayMap.keyAt(0));
        this.Rs.setAdapter((SpinnerAdapter) this.Rv);
        this.Rr.setOnItemSelectedListener(this);
        this.Rs.setOnItemSelectedListener(this);
        this.Rt.setOnClickListener(this);
        this.Rp.jS();
        this.Rp.jT();
        this.Rq.addView(this.Rw);
        d(this.Rw, 8);
    }

    @Override // com.xiaopeng.devtools.view.oled.b
    public void A(List<CustomOledMusicBook> list) {
        this.RA = list;
    }

    @Override // com.xiaopeng.devtools.view.oled.b
    public void a(ArrayMap<String, List<CANMsg387>> arrayMap) {
        this.Ry = arrayMap;
        b(this.Ry);
    }

    private void b(ArrayMap<String, List<CANMsg387>> arrayMap) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < arrayMap.size(); i++) {
            arrayList.add(arrayMap.keyAt(i));
        }
        this.Rv.setNoteList(arrayList);
        this.Rv.notifyDataSetChanged();
    }

    @Override // com.xiaopeng.devtools.view.ActionBarActivity, com.xiaopeng.devtools.view.AppBaseActivity
    public void fg() {
        super.fg();
        EventBus.getDefault().register(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.Rp.onDestroy();
        this.Rp.ha();
        if (this.mHandler.hasMessages(2)) {
            this.mHandler.removeMessages(2);
        }
        EventBus.getDefault().unregister(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_play) {
            this.Rp.bC(this.Rz);
            this.Rt.setEnabled(false);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        switch (adapterView.getId()) {
            case R.id.spinner_music_book /* 2131297108 */:
                this.Rz = this.Ru.dv(i);
                return;
            case R.id.spinner_music_note /* 2131297109 */:
                if (i < this.Ry.size()) {
                    this.RB = this.Ry.get(this.Ry.keyAt(i));
                    this.RD = i;
                    return;
                }
                startActivityForResult(new Intent(this, OledMusicBookActivity.class), 3);
                return;
            default:
                return;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 3) {
            if (i2 == -1) {
                if (intent != null) {
                    ArrayList parcelableArrayList = intent.getExtras().getParcelableArrayList("music_note_param");
                    if (parcelableArrayList != null) {
                        ArrayMap<String, List<CANMsg387>> arrayMap = this.Ry;
                        arrayMap.put(getString(R.string.text_oled_settings) + this.RE, parcelableArrayList);
                        b(this.Ry);
                        Spinner spinner = this.Rs;
                        ArrayMap<String, List<CANMsg387>> arrayMap2 = this.Ry;
                        spinner.setSelection(arrayMap2.indexOfKey(getString(R.string.text_oled_settings) + this.RE));
                        this.RE = this.RE + 1;
                        return;
                    }
                    this.Rs.setSelection(this.RD);
                    return;
                }
                return;
            }
            this.Rs.setSelection(this.RD);
        }
    }
}
