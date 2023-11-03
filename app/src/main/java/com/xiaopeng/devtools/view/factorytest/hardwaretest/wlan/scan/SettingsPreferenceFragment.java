package com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class SettingsPreferenceFragment extends PreferenceFragment implements b {
    private SettingsDialogFragment Nu;
    private String Nv;

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int na = na();
        if (na != 0) {
            this.Nv = getResources().getString(na);
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!TextUtils.isEmpty(this.Nv)) {
            setHasOptionsMenu(true);
        }
    }

    protected int na() {
        return 0;
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.Nv != null && getActivity() != null) {
            c.a(getActivity(), menu.add(0, 101, 0, R.string.help_label), this.Nv);
        }
    }

    public final void nb() {
        getActivity().onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getSystemService(String str) {
        return getActivity().getSystemService(str);
    }

    @Override // android.app.Fragment
    public void onDetach() {
        if (isRemoving() && this.Nu != null) {
            this.Nu.dismiss();
            this.Nu = null;
        }
        super.onDetach();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showDialog(int i) {
        if (this.Nu != null) {
            Log.e("SettingsPreferenceFragment", "Old dialog fragment not null!");
        }
        this.Nu = new SettingsDialogFragment(this, i);
        this.Nu.show(getActivity().getFragmentManager(), Integer.toString(i));
    }

    @Override // com.xiaopeng.devtools.view.factorytest.hardwaretest.wlan.scan.b
    public Dialog onCreateDialog(int i) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeDialog(int i) {
        if (this.Nu != null && this.Nu.nd() == i) {
            this.Nu.dismiss();
        }
        this.Nu = null;
    }

    public void nc() {
    }

    /* loaded from: classes12.dex */
    public static class SettingsDialogFragment extends DialogFragment {
        private int Nw;
        private Activity mActivity;
        private DialogInterface.OnCancelListener mOnCancelListener;
        private DialogInterface.OnDismissListener mOnDismissListener;
        private Fragment mParentFragment;

        public SettingsDialogFragment() {
        }

        public SettingsDialogFragment(b bVar, int i) {
            this.Nw = i;
            if (!(bVar instanceof Fragment)) {
                throw new IllegalArgumentException("fragment argument must be an instance of " + Fragment.class.getName());
            }
            this.mParentFragment = (Fragment) bVar;
        }

        @Override // android.app.DialogFragment, android.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            if (this.mParentFragment != null) {
                bundle.putInt("key_dialog_id", this.Nw);
                bundle.putInt("key_parent_fragment_id", this.mParentFragment.getId());
            }
        }

        @Override // android.app.Fragment
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            this.mActivity = activity;
        }

        @Override // android.app.DialogFragment, android.app.Fragment
        public void onStart() {
            super.onStart();
            if (this.mParentFragment != null && (this.mParentFragment instanceof SettingsPreferenceFragment)) {
                ((SettingsPreferenceFragment) this.mParentFragment).nc();
            }
        }

        @Override // android.app.Fragment
        public void onResume() {
            super.onResume();
            getDialog().getWindow().setLayout(-1, -1);
            getDialog().getWindow().setSoftInputMode(16);
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            Object valueOf;
            if (bundle != null) {
                this.Nw = bundle.getInt("key_dialog_id", 0);
                int i = bundle.getInt("key_parent_fragment_id", -1);
                if (i > -1) {
                    this.mParentFragment = getFragmentManager().findFragmentById(i);
                    if (!(this.mParentFragment instanceof b)) {
                        StringBuilder sb = new StringBuilder();
                        if (this.mParentFragment != null) {
                            valueOf = this.mParentFragment.getClass().getName();
                        } else {
                            valueOf = Integer.valueOf(i);
                        }
                        sb.append(valueOf);
                        sb.append(" must implement ");
                        sb.append(b.class.getName());
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                if (this.mParentFragment instanceof SettingsPreferenceFragment) {
                    ((SettingsPreferenceFragment) this.mParentFragment).Nu = this;
                }
            }
            return ((b) this.mParentFragment).onCreateDialog(this.Nw);
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            super.onCancel(dialogInterface);
            if (this.mOnCancelListener != null) {
                this.mOnCancelListener.onCancel(dialogInterface);
            }
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            if (this.mOnDismissListener != null) {
                this.mOnDismissListener.onDismiss(dialogInterface);
            }
        }

        public int nd() {
            return this.Nw;
        }

        @Override // android.app.DialogFragment, android.app.Fragment
        public void onDetach() {
            super.onDetach();
            this.mActivity.getWindow().getDecorView().setSystemUiVisibility(3846);
            if ((this.mParentFragment instanceof SettingsPreferenceFragment) && ((SettingsPreferenceFragment) this.mParentFragment).Nu == this) {
                ((SettingsPreferenceFragment) this.mParentFragment).Nu = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasNextButton() {
        return ((a) getActivity()).hasNextButton();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Button getNextButton() {
        return ((a) getActivity()).getNextButton();
    }
}
