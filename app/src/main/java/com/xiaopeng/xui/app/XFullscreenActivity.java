package com.xiaopeng.xui.app;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

@Deprecated
/* loaded from: classes13.dex */
public class XFullscreenActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a(this, 14);
    }

    private static void a(Activity activity, int i) {
        if (i > 0) {
            activity.requestWindowFeature(i);
        }
        activity.getWindow().getDecorView().setSystemUiVisibility(5894);
    }
}
