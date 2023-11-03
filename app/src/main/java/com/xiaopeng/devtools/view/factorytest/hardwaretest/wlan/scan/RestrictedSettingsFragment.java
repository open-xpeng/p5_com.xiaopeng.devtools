package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.UserManager;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import java.util.HashSet;

/* loaded from: classes12.dex */
public class RestrictedSettingsFragment extends SettingsPreferenceFragment {
    private boolean Nm;
    private boolean Nn;
    private Bundle No;
    private UserManager Np;
    private final String Nq;
    private final HashSet<Preference> Nr = new HashSet<>();
    private BroadcastReceiver Ns = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.RestrictedSettingsFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            RestrictedSettingsFragment.this.Nm = false;
            if (RestrictedSettingsFragment.this.dm(RestrictedSettingsFragment.this.Nq)) {
                RestrictedSettingsFragment.this.a((Preference) null);
            }
        }
    };

    public RestrictedSettingsFragment(String str) {
        this.Nq = str;
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.SettingsPreferenceFragment, android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Np = (UserManager) getSystemService("user");
        if (bundle != null) {
            this.Nm = bundle.getBoolean("chsc", false);
            this.Nn = bundle.getBoolean("chrq", false);
            this.No = bundle.getBundle("rsmb");
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("chrq", this.Nn);
        if (this.No != null) {
            bundle.putBundle("rsmb", this.No);
        }
        if (getActivity().isChangingConfigurations()) {
            bundle.putBoolean("chsc", this.Nm);
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (dm(this.Nq)) {
            a((Preference) null);
        } else {
            this.Nm = false;
        }
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        getActivity().registerReceiver(this.Ns, intentFilter);
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.Ns);
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Preference findPreference;
        Intent intent2;
        if (i == 12309) {
            Bundle bundle = this.No;
            this.No = null;
            this.Nn = false;
            if (i2 != -1) {
                if (!isDetached()) {
                    nb();
                    return;
                }
                return;
            }
            this.Nm = true;
            String string = bundle != null ? bundle.getString("pref") : null;
            if (string != null && (findPreference = findPreference(string)) != null) {
                if ((findPreference instanceof CheckBoxPreference) && bundle.containsKey("isChecked")) {
                    ((CheckBoxPreference) findPreference).setChecked(bundle.getBoolean("isChecked", false));
                }
                if (!onPreferenceTreeClick(getPreferenceScreen(), findPreference) && (intent2 = findPreference.getIntent()) != null) {
                    findPreference.getContext().startActivity(intent2);
                    return;
                }
                return;
            }
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Preference preference) {
        this.Nm = false;
    }

    protected boolean dm(String str) {
        if (str == null) {
            return false;
        }
        return "restrictions_pin_set".equals(str) || this.Np.hasUserRestriction(str);
    }
}
