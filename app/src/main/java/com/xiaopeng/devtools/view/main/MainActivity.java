package com.xiaopeng.devtools.view.main;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import com.xiaopeng.devtools.R;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class MainActivity extends Activity {
    private ArrayList<Uri> Qe;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if ("android.intent.action.SEND_MULTIPLE".equals(action) && type != null && "application/vnd.android.bugreport".equals(type)) {
            this.Qe = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        }
        if (this.Qe == null) {
            this.Qe = new ArrayList<>();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_submit) {
            nJ();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void nJ() {
        String str;
        EditText editText = (EditText) findViewById(R.id.summary);
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            str = null;
        } else {
            str = getString(R.string.error_no_text);
        }
        editText.setError(str);
        EditText editText2 = (EditText) findViewById(R.id.description);
        editText2.setError(TextUtils.isEmpty(editText2.getText().toString()) ? getString(R.string.error_no_text) : null);
        ((CheckBox) findViewById(R.id.ssCheckBox)).isChecked();
        if (editText2.getError() != null || editText.getError() != null) {
            return;
        }
        finish();
    }
}
