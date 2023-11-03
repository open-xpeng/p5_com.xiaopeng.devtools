package com.xiaopeng.devtools.view;

import android.app.Activity;
import android.app.ApplicationErrorReport;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaopeng.devtools.R;

/* loaded from: classes12.dex */
public class CrashFeedbackActivity extends Activity {
    private static final String TAG = CrashFeedbackActivity.class.getSimpleName();
    Button CA;
    TextView CB;
    TextView CC;
    ViewGroup CD;
    ApplicationErrorReport Cy;
    Button Cz;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.xiaopeng.devtools.view.CrashFeedbackActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CrashFeedbackActivity.this.lN();
        }
    };
    private View.OnLongClickListener CE = new View.OnLongClickListener() { // from class: com.xiaopeng.devtools.view.CrashFeedbackActivity.2
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            Toast.makeText(CrashFeedbackActivity.this, CrashFeedbackActivity.this.getResources().getString(CrashFeedbackActivity.this.cN(CrashFeedbackActivity.this.getContent()) ? R.string.crash_clipboard_toast_message_success : R.string.crash_clipboard_toast_message_failure), 0).show();
            return true;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void lN() {
        boolean lQ = lQ();
        String str = TAG;
        Log.d(str, "onNetworkAvailabilityChange(), available: " + lQ);
        if (this.CD != null) {
            this.CD.setVisibility(lQ ? 8 : 0);
        }
        if (this.CA != null) {
            this.CA.setEnabled(lQ);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.submit_crash);
        this.Cz = (Button) findViewById(R.id.cancel);
        this.CA = (Button) findViewById(R.id.submit);
        this.CB = (TextView) findViewById(R.id.subject);
        this.CC = (TextView) findViewById(R.id.content);
        this.CC.setOnLongClickListener(this.CE);
        this.CD = (ViewGroup) findViewById(R.id.no_network_warning);
        this.Cz.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.CrashFeedbackActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CrashFeedbackActivity.this.finish();
            }
        });
        this.CA.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.view.CrashFeedbackActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CrashFeedbackActivity.this.lP();
            }
        });
        b(getIntent());
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        registerReceiver(this.mReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        lN();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.mReceiver);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        b(intent);
    }

    private void b(Intent intent) {
        if (intent == null) {
            return;
        }
        this.Cy = (ApplicationErrorReport) intent.getParcelableExtra("android.intent.extra.BUG_REPORT");
        this.CB.setText(lO());
        this.CC.setText(getContent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean cN(String str) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        String string = getResources().getString(R.string.crash_clipboard_label);
        if (clipboardManager != null && !TextUtils.isEmpty(str)) {
            clipboardManager.setPrimaryClip(ClipData.newPlainText(string, str));
            return true;
        }
        return false;
    }

    private String lO() {
        if (this.Cy == null || this.Cy.crashInfo == null) {
            return "";
        }
        return "[CRASH] " + this.Cy.packageName + " threw " + this.Cy.crashInfo.exceptionClassName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getContent() {
        if (this.Cy != null && this.Cy.crashInfo != null && this.Cy.crashInfo.stackTrace != null) {
            return this.Cy.crashInfo.stackTrace;
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lP() {
        if (getPreferences(0).getString("last_submission", "").equals(getContent())) {
            Toast.makeText(this, (int) R.string.already_submitted, 1).show();
        } else if (this.Cy != null) {
            if (this.Cy.crashInfo != null) {
                String str = TAG;
                Log.i(str, "Exception class name: " + this.Cy.crashInfo.exceptionClassName);
                String str2 = TAG;
                Log.i(str2, "Exception message: " + this.Cy.crashInfo.exceptionMessage);
                String str3 = TAG;
                Log.i(str3, "Throw class name: " + this.Cy.crashInfo.throwClassName);
                String str4 = TAG;
                Log.i(str4, "Throw file name: " + this.Cy.crashInfo.throwFileName);
                String str5 = TAG;
                Log.i(str5, "Throw line number: " + this.Cy.crashInfo.throwLineNumber);
                String str6 = TAG;
                Log.i(str6, "Throw method name: " + this.Cy.crashInfo.throwMethodName);
                String str7 = TAG;
                Log.i(str7, "Stack trace: " + this.Cy.crashInfo.stackTrace);
            }
        } else {
            Log.d(TAG, "getIntent() was null?");
        }
        finish();
    }

    private boolean lQ() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
