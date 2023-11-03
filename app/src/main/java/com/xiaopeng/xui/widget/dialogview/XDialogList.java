package com.xiaopeng.xui.widget.dialogview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ListView;
import androidx.annotation.Nullable;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XFrameLayout;

@Deprecated
/* loaded from: classes13.dex */
class XDialogList extends XFrameLayout {
    private ListView mListView;

    public XDialogList(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XDialogList(Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XDialogList(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(context).inflate(R.layout.x_dialog_list, this);
        this.mListView = (ListView) findViewById(R.id.x_dialog_listview);
    }
}
