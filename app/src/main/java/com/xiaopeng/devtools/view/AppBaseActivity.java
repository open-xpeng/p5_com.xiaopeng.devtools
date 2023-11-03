package com.xiaopeng.devtools.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.xiaopeng.libtheme.ThemeManager;

/* loaded from: classes12.dex */
public abstract class AppBaseActivity extends Activity {
    protected int theme = -1;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        lM();
        fe();
        ff();
        lL();
        fg();
    }

    public void lM() {
    }

    public void fe() {
        this.theme = getIntent().getIntExtra(ThemeManager.AttributeSet.THEME, -1);
    }

    public void ff() {
    }

    public void lL() {
    }

    public void fg() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
    }

    public void d(View view, int i) {
        if (view != null && view.getVisibility() != i) {
            view.setVisibility(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(Class cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(335544320);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Class cls, int i) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(ThemeManager.AttributeSet.THEME, i);
        intent.addFlags(335544320);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Intent intent) {
        intent.addFlags(335544320);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Intent intent, int i) {
        intent.putExtra(ThemeManager.AttributeSet.THEME, i);
        intent.addFlags(335544320);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void recordRepairModeAction(String str, String str2) {
        if (this.theme == 1) {
            com.xiaopeng.devtools.utils.b.recordRepairModeAction(str, str2);
        }
    }
}
