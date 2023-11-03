package com.xiaopeng.devtools.view.oled;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.oled.CANMsg387;

/* compiled from: OledMusicBookFragment.java */
/* loaded from: classes12.dex */
public class c extends Fragment {
    private TableLayout RR;
    private String[] RS;
    private CANMsg387 RT;
    private int[][] RU;
    private a RV;

    /* compiled from: OledMusicBookFragment.java */
    /* loaded from: classes12.dex */
    public interface a {
    }

    public static c b(CANMsg387 cANMsg387) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putParcelable("oled_note_params", cANMsg387);
        cVar.setArguments(bundle);
        return cVar;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.RT = (CANMsg387) getArguments().getParcelable("oled_note_params");
        }
        if (this.RT != null) {
            this.RU = new int[][]{new int[]{this.RT.yM, this.RT.yN, this.RT.yO, this.RT.yP, this.RT.yQ, this.RT.yR, this.RT.yS}, new int[]{this.RT.yF, this.RT.yG, this.RT.yH, this.RT.yI, this.RT.yJ, this.RT.yK, this.RT.yL}, new int[]{this.RT.za, this.RT.zb, this.RT.zc, this.RT.zd, this.RT.ze, this.RT.zf, this.RT.zg}, new int[]{this.RT.yT, this.RT.yU, this.RT.yV, this.RT.yW, this.RT.yX, this.RT.yY, this.RT.yZ}};
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_oled_music_book, viewGroup, false);
        this.RS = MyApplication.getContext().getResources().getStringArray(R.array.oled_note);
        this.RR = (TableLayout) inflate.findViewById(R.id.tableLayout_container);
        for (final int i = 0; i < this.RR.getChildCount(); i++) {
            TableRow tableRow = (TableRow) this.RR.getChildAt(i);
            final int i2 = 0;
            while (i2 < tableRow.getChildCount()) {
                final Button button = (Button) tableRow.getChildAt(i2);
                StringBuilder sb = new StringBuilder();
                sb.append(this.RS[i]);
                int i3 = i2 + 1;
                sb.append(i3);
                button.setText(sb.toString());
                if (this.RU != null) {
                    if (this.RU[i][i2] == 1) {
                        if (!button.isSelected()) {
                            button.setSelected(true);
                        }
                    } else if (button.isSelected()) {
                        button.setSelected(false);
                    }
                }
                button.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.oled.c.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (button.isSelected()) {
                            c.this.RU[i][i2] = 0;
                            button.setSelected(false);
                        } else {
                            c.this.RU[i][i2] = 1;
                            button.setSelected(true);
                        }
                        c.this.nM();
                    }
                });
                i2 = i3;
            }
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nM() {
        this.RT.yM = this.RU[0][0];
        this.RT.yN = this.RU[0][1];
        this.RT.yO = this.RU[0][2];
        this.RT.yP = this.RU[0][3];
        this.RT.yQ = this.RU[0][4];
        this.RT.yR = this.RU[0][5];
        this.RT.yS = this.RU[0][6];
        this.RT.yF = this.RU[1][0];
        this.RT.yG = this.RU[1][1];
        this.RT.yH = this.RU[1][2];
        this.RT.yI = this.RU[1][3];
        this.RT.yJ = this.RU[1][4];
        this.RT.yK = this.RU[1][5];
        this.RT.yL = this.RU[1][6];
        this.RT.za = this.RU[2][0];
        this.RT.zb = this.RU[2][1];
        this.RT.zc = this.RU[2][2];
        this.RT.zd = this.RU[2][3];
        this.RT.ze = this.RU[2][4];
        this.RT.zf = this.RU[2][5];
        this.RT.zg = this.RU[2][6];
        this.RT.yT = this.RU[3][0];
        this.RT.yU = this.RU[3][1];
        this.RT.yV = this.RU[3][2];
        this.RT.yW = this.RU[3][3];
        this.RT.yX = this.RU[3][4];
        this.RT.yY = this.RU[3][5];
        this.RT.yZ = this.RU[3][6];
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof a) {
            this.RV = (a) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.RV = null;
    }
}
