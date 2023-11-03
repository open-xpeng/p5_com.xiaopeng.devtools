package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.content.Context;
import android.content.res.Resources;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiEnterpriseConfig;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.support.v4.media.subtitle.Cea708CCParser;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class WifiConfigController implements TextWatcher, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {
    private TextView NA;
    private int NB;
    private TextView NC;
    private String ND;
    private final ArrayAdapter<String> NE;
    private final ArrayAdapter<String> NF;
    private Spinner NG;
    private Spinner NH;
    private Spinner NI;
    private Spinner NJ;
    private ArrayAdapter<String> NK;
    private Spinner NM;
    private TextView NN;
    private TextView NP;
    private Spinner NQ;
    private TextView NR;
    private TextView NS;
    private TextView NT;
    private TextView NU;
    private TextView NV;
    private Spinner NW;
    private TextView NX;
    private TextView NY;
    private TextView NZ;
    private final e Nx;
    private final AccessPoint Ny;
    private boolean Nz;
    private final Handler Oa;
    private final View mView;

    public WifiConfigController(e eVar, View view, AccessPoint accessPoint, boolean z) {
        this.ND = "unspecified";
        this.Nx = eVar;
        this.mView = view;
        this.Ny = accessPoint;
        this.NB = accessPoint == null ? 0 : accessPoint.security;
        this.Nz = z;
        this.Oa = new Handler();
        Context context = this.Nx.getContext();
        Resources resources = context.getResources();
        this.NE = new ArrayAdapter<>(context, 17367048, context.getResources().getStringArray(R.array.wifi_peap_phase2_entries));
        this.NE.setDropDownViewResource(17367049);
        this.NF = new ArrayAdapter<>(context, 17367048, context.getResources().getStringArray(R.array.wifi_phase2_entries));
        this.NF.setDropDownViewResource(17367049);
        this.ND = context.getString(R.string.wifi_unspecified);
        this.NQ = (Spinner) this.mView.findViewById(R.id.ip_settings);
        this.NQ.setOnItemSelectedListener(this);
        this.NW = (Spinner) this.mView.findViewById(R.id.proxy_settings);
        this.NW.setOnItemSelectedListener(this);
        if (this.Ny == null) {
            this.Nx.dq(R.string.wifi_add_network);
            this.NA = (TextView) this.mView.findViewById(R.id.ssid);
            this.NA.addTextChangedListener(this);
            this.NG = (Spinner) this.mView.findViewById(R.id.security);
            nm();
            nn();
            this.Nx.dn(context.getString(R.string.wifi_save));
        } else {
            this.Nx.cQ(this.Ny.ssid);
            ViewGroup viewGroup = (ViewGroup) this.mView.findViewById(R.id.info);
            NetworkInfo.DetailedState mY = this.Ny.mY();
            if (mY != null) {
                a(viewGroup, R.string.wifi_status, d.a(this.Nx.getContext(), mY));
            }
            int level = this.Ny.getLevel();
            if (level != -1) {
                a(viewGroup, R.string.wifi_signal, resources.getStringArray(R.array.wifi_signal)[level]);
            }
            WifiInfo mX = this.Ny.mX();
            if (mX != null && mX.getLinkSpeed() != -1) {
                a(viewGroup, R.string.wifi_speed, mX.getLinkSpeed() + "Mbps");
            }
            a(viewGroup, R.string.wifi_security, this.Ny.aA(false));
            if (this.Ny.networkId != -1) {
                this.Ny.getConfig();
            }
            if (this.Ny.networkId == -1 || this.Nz) {
                ng();
                nm();
                nn();
            }
            if (this.Nz) {
                this.Nx.dn(context.getString(R.string.wifi_save));
            } else {
                com.xiaopeng.lib.utils.c.f("wifiController", "state = " + mY + ",level = " + level);
                if (mY == null && level != -1) {
                    this.Nx.dn(context.getString(R.string.wifi_connect));
                    com.xiaopeng.lib.utils.c.f("wifiController", "state == null && level != -1");
                } else {
                    this.mView.findViewById(R.id.ip_fields).setVisibility(8);
                    com.xiaopeng.lib.utils.c.f("wifiController", "! state == null && level != -1");
                }
                if (this.Ny.networkId != -1) {
                    com.xiaopeng.lib.utils.c.f("wifiController", "networkId = " + this.Ny.networkId);
                    this.Nx.nt();
                    if (mY != null) {
                        this.Nx.dn(context.getString(R.string.wifi_forget));
                    }
                }
            }
        }
        if (this.Nx.nu()) {
            ne();
        }
    }

    private void a(ViewGroup viewGroup, int i, String str) {
        View inflate = this.Nx.getLayoutInflater().inflate(R.layout.wifi_dialog_row, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.name)).setText(this.Nx.getContext().getString(i) + ":");
        ((TextView) inflate.findViewById(R.id.value)).setText(str);
        viewGroup.addView(inflate);
    }

    public void ne() {
        Button nv = this.Nx.nv();
        if (nv == null && nv.getVisibility() == 0) {
            return;
        }
        boolean z = false;
        boolean z2 = this.NC != null && ((this.NB == 1 && this.NC.length() == 0) || (this.NB == 2 && this.NC.length() < 8));
        if ((this.NA == null || this.NA.length() != 0) && (((this.Ny != null && this.Ny.networkId != -1) || !z2) && nf())) {
            z = true;
        }
        nv.setEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WifiConfiguration getConfig() {
        if (this.Ny == null || this.Ny.networkId == -1 || this.Nz) {
            WifiConfiguration wifiConfiguration = new WifiConfiguration();
            if (this.Ny == null) {
                wifiConfiguration.SSID = AccessPoint.dl(this.NA.getText().toString());
                wifiConfiguration.hiddenSSID = true;
            } else if (this.Ny.networkId == -1) {
                wifiConfiguration.SSID = AccessPoint.dl(this.Ny.ssid);
            } else {
                wifiConfiguration.networkId = this.Ny.networkId;
            }
            switch (this.NB) {
                case 0:
                    wifiConfiguration.allowedKeyManagement.set(0);
                    break;
                case 1:
                    wifiConfiguration.allowedKeyManagement.set(0);
                    wifiConfiguration.allowedAuthAlgorithms.set(0);
                    wifiConfiguration.allowedAuthAlgorithms.set(1);
                    if (this.NC.length() != 0) {
                        int length = this.NC.length();
                        String charSequence = this.NC.getText().toString();
                        if ((length == 10 || length == 26 || length == 58) && charSequence.matches("[0-9A-Fa-f]*")) {
                            wifiConfiguration.wepKeys[0] = charSequence;
                            break;
                        } else {
                            String[] strArr = wifiConfiguration.wepKeys;
                            strArr[0] = '\"' + charSequence + '\"';
                            break;
                        }
                    }
                    break;
                case 2:
                    wifiConfiguration.allowedKeyManagement.set(1);
                    if (this.NC.length() != 0) {
                        String charSequence2 = this.NC.getText().toString();
                        if (charSequence2.matches("[0-9A-Fa-f]{64}")) {
                            wifiConfiguration.preSharedKey = charSequence2;
                            break;
                        } else {
                            wifiConfiguration.preSharedKey = '\"' + charSequence2 + '\"';
                            break;
                        }
                    }
                    break;
                case 3:
                    wifiConfiguration.allowedKeyManagement.set(2);
                    wifiConfiguration.allowedKeyManagement.set(3);
                    wifiConfiguration.enterpriseConfig = new WifiEnterpriseConfig();
                    int selectedItemPosition = this.NH.getSelectedItemPosition();
                    int selectedItemPosition2 = this.NJ.getSelectedItemPosition();
                    wifiConfiguration.enterpriseConfig.setEapMethod(selectedItemPosition);
                    if (selectedItemPosition == 0) {
                        switch (selectedItemPosition2) {
                            case 0:
                                wifiConfiguration.enterpriseConfig.setPhase2Method(0);
                                break;
                            case 1:
                                wifiConfiguration.enterpriseConfig.setPhase2Method(3);
                                break;
                            case 2:
                                wifiConfiguration.enterpriseConfig.setPhase2Method(4);
                                break;
                            default:
                                Log.e("WifiConfigController", "Unknown phase2 method" + selectedItemPosition2);
                                break;
                        }
                    } else {
                        wifiConfiguration.enterpriseConfig.setPhase2Method(selectedItemPosition2);
                    }
                    String str = (String) this.NI.getSelectedItem();
                    if (str.equals(this.ND)) {
                        str = "";
                    }
                    wifiConfiguration.enterpriseConfig.setCaCertificateAlias(str);
                    String str2 = (String) this.NM.getSelectedItem();
                    if (str2.equals(this.ND)) {
                        str2 = "";
                    }
                    wifiConfiguration.enterpriseConfig.setClientCertificateAlias(str2);
                    wifiConfiguration.enterpriseConfig.setIdentity(this.NN.getText().toString());
                    wifiConfiguration.enterpriseConfig.setAnonymousIdentity(this.NP.getText().toString());
                    if (this.NC.isShown()) {
                        if (this.NC.length() > 0) {
                            wifiConfiguration.enterpriseConfig.setPassword(this.NC.getText().toString());
                            break;
                        }
                    } else {
                        wifiConfiguration.enterpriseConfig.setPassword(this.NC.getText().toString());
                        break;
                    }
                    break;
                default:
                    return null;
            }
            return wifiConfiguration;
        }
        return null;
    }

    private boolean nf() {
        return true;
    }

    private void ng() {
        if (this.NB == 0) {
            this.mView.findViewById(R.id.security_fields).setVisibility(8);
            return;
        }
        this.mView.findViewById(R.id.security_fields).setVisibility(0);
        if (this.NC == null) {
            this.NC = (TextView) this.mView.findViewById(R.id.password);
            this.NC.addTextChangedListener(this);
            ((CheckBox) this.mView.findViewById(R.id.show_password)).setOnCheckedChangeListener(this);
            if (this.Ny != null && this.Ny.networkId != -1) {
                this.NC.setHint(R.string.wifi_unchanged);
            }
        }
        if (this.NB != 3) {
            this.mView.findViewById(R.id.eap).setVisibility(8);
            return;
        }
        this.mView.findViewById(R.id.eap).setVisibility(0);
        if (this.NH == null) {
            this.NH = (Spinner) this.mView.findViewById(R.id.method);
            this.NH.setOnItemSelectedListener(this);
            this.NJ = (Spinner) this.mView.findViewById(R.id.phase2);
            this.NI = (Spinner) this.mView.findViewById(R.id.ca_cert);
            this.NM = (Spinner) this.mView.findViewById(R.id.user_cert);
            this.NN = (TextView) this.mView.findViewById(R.id.identity);
            this.NP = (TextView) this.mView.findViewById(R.id.anonymous);
            if (this.Ny != null && this.Ny.networkId != -1) {
                WifiEnterpriseConfig wifiEnterpriseConfig = this.Ny.getConfig().enterpriseConfig;
                int eapMethod = wifiEnterpriseConfig.getEapMethod();
                int phase2Method = wifiEnterpriseConfig.getPhase2Method();
                this.NH.setSelection(eapMethod);
                dn(eapMethod);
                if (eapMethod == 0) {
                    if (phase2Method == 0) {
                        this.NJ.setSelection(0);
                    } else {
                        switch (phase2Method) {
                            case 3:
                                this.NJ.setSelection(1);
                                break;
                            case 4:
                                this.NJ.setSelection(2);
                                break;
                            default:
                                Log.e("WifiConfigController", "Invalid phase 2 method " + phase2Method);
                                break;
                        }
                    }
                } else {
                    this.NJ.setSelection(phase2Method);
                }
                a(this.NI, wifiEnterpriseConfig.getCaCertificateAlias());
                a(this.NM, wifiEnterpriseConfig.getClientCertificateAlias());
                this.NN.setText(wifiEnterpriseConfig.getIdentity());
                this.NP.setText(wifiEnterpriseConfig.getAnonymousIdentity());
                return;
            }
            this.NH.setSelection(0);
            dn(0);
            return;
        }
        dn(this.NH.getSelectedItemPosition());
    }

    private void dn(int i) {
        this.mView.findViewById(R.id.l_method).setVisibility(0);
        this.mView.findViewById(R.id.l_identity).setVisibility(0);
        this.mView.findViewById(R.id.l_ca_cert).setVisibility(0);
        this.mView.findViewById(R.id.password_layout).setVisibility(0);
        this.mView.findViewById(R.id.show_password_layout).setVisibility(0);
        this.Nx.getContext();
        switch (i) {
            case 0:
                if (this.NK != this.NE) {
                    this.NK = this.NE;
                    this.NJ.setAdapter((SpinnerAdapter) this.NK);
                }
                this.mView.findViewById(R.id.l_phase2).setVisibility(0);
                this.mView.findViewById(R.id.l_anonymous).setVisibility(0);
                nj();
                return;
            case 1:
                this.mView.findViewById(R.id.l_user_cert).setVisibility(0);
                nh();
                nk();
                nl();
                return;
            case 2:
                if (this.NK != this.NF) {
                    this.NK = this.NF;
                    this.NJ.setAdapter((SpinnerAdapter) this.NK);
                }
                this.mView.findViewById(R.id.l_phase2).setVisibility(0);
                this.mView.findViewById(R.id.l_anonymous).setVisibility(0);
                nj();
                return;
            case 3:
                nh();
                ni();
                nk();
                nj();
                return;
            default:
                return;
        }
    }

    private void nh() {
        this.mView.findViewById(R.id.l_phase2).setVisibility(8);
        this.NJ.setSelection(0);
    }

    private void ni() {
        this.mView.findViewById(R.id.l_ca_cert).setVisibility(8);
        this.NI.setSelection(0);
    }

    private void nj() {
        this.mView.findViewById(R.id.l_user_cert).setVisibility(8);
        this.NM.setSelection(0);
    }

    private void nk() {
        this.mView.findViewById(R.id.l_anonymous).setVisibility(8);
        this.NP.setText("");
    }

    private void nl() {
        this.NC.setText("");
        this.mView.findViewById(R.id.password_layout).setVisibility(8);
        this.mView.findViewById(R.id.show_password_layout).setVisibility(8);
    }

    private void nm() {
        this.mView.findViewById(R.id.ip_fields).setVisibility(0);
        if (this.Ny != null && this.Ny.networkId != -1) {
            this.Ny.getConfig();
        }
        if (this.NQ.getSelectedItemPosition() == 1) {
            this.mView.findViewById(R.id.staticip).setVisibility(0);
            if (this.NR == null) {
                this.NR = (TextView) this.mView.findViewById(R.id.ipaddress);
                this.NR.addTextChangedListener(this);
                this.NS = (TextView) this.mView.findViewById(R.id.gateway);
                this.NS.addTextChangedListener(this);
                this.NT = (TextView) this.mView.findViewById(R.id.network_prefix_length);
                this.NT.addTextChangedListener(this);
                this.NU = (TextView) this.mView.findViewById(R.id.dns1);
                this.NU.addTextChangedListener(this);
                this.NV = (TextView) this.mView.findViewById(R.id.dns2);
                this.NV.addTextChangedListener(this);
                return;
            }
            return;
        }
        this.mView.findViewById(R.id.staticip).setVisibility(8);
    }

    private void nn() {
        this.mView.findViewById(R.id.proxy_settings_fields).setVisibility(0);
        if (this.Ny != null && this.Ny.networkId != -1) {
            this.Ny.getConfig();
        }
        if (this.NW.getSelectedItemPosition() == 1) {
            this.mView.findViewById(R.id.proxy_warning_limited_support).setVisibility(0);
            this.mView.findViewById(R.id.proxy_fields).setVisibility(0);
            if (this.NX == null) {
                this.NX = (TextView) this.mView.findViewById(R.id.proxy_hostname);
                this.NX.addTextChangedListener(this);
                this.NY = (TextView) this.mView.findViewById(R.id.proxy_port);
                this.NY.addTextChangedListener(this);
                this.NZ = (TextView) this.mView.findViewById(R.id.proxy_exclusionlist);
                this.NZ.addTextChangedListener(this);
                return;
            }
            return;
        }
        this.mView.findViewById(R.id.proxy_warning_limited_support).setVisibility(8);
        this.mView.findViewById(R.id.proxy_fields).setVisibility(8);
    }

    private void a(Spinner spinner, String str) {
        if (str != null) {
            ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
            for (int count = arrayAdapter.getCount() - 1; count >= 0; count--) {
                if (str.equals(arrayAdapter.getItem(count))) {
                    spinner.setSelection(count);
                    return;
                }
            }
        }
    }

    public boolean no() {
        return this.Nz;
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        this.Oa.post(new Runnable() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.WifiConfigController.1
            @Override // java.lang.Runnable
            public void run() {
                WifiConfigController.this.ne();
            }
        });
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i;
        if (compoundButton.getId() == R.id.show_password) {
            int selectionEnd = this.NC.getSelectionEnd();
            TextView textView = this.NC;
            if (z) {
                i = Cea708CCParser.Const.CODE_C1_SPA;
            } else {
                i = 128;
            }
            textView.setInputType(i | 1);
            if (selectionEnd >= 0) {
                ((EditText) this.NC).setSelection(selectionEnd);
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (adapterView == this.NG) {
            this.NB = i;
            ng();
        } else if (adapterView == this.NH) {
            ng();
        } else if (adapterView == this.NW) {
            nn();
        } else {
            nm();
        }
        ne();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
