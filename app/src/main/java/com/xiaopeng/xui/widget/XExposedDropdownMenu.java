package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.h;

/* loaded from: classes13.dex */
public class XExposedDropdownMenu extends XTextView implements AdapterView.OnItemClickListener {
    private static final String TAG = XExposedDropdownMenu.class.getSimpleName();
    private a adc;
    private final int ade;
    private final int adf;
    private ArrayAdapter<String> mAdapter;
    private int mCurrentSelection;
    private ListView mListView;
    private PopupWindow mPopupWindow;

    /* loaded from: classes13.dex */
    public interface a {
        void i(int i, String str);
    }

    public XExposedDropdownMenu(Context context) {
        this(context, null);
    }

    public XExposedDropdownMenu(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XExposedDropdownMenu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, 0);
        this.ade = h.g(16.0f) - getResources().getDimensionPixelOffset(R.dimen.x_exposed_dropdown_menu_inset_vertical);
        this.adf = getResources().getDimensionPixelOffset(R.dimen.x_exposed_dropdown_menu_inset_horizontal);
        this.mCurrentSelection = 0;
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainAttributes = getResources().obtainAttributes(attributeSet, R.styleable.XExposedDropdownMenu);
        CharSequence[] textArray = obtainAttributes.getTextArray(R.styleable.XExposedDropdownMenu_edmDropdownEntries);
        int integer = obtainAttributes.getInteger(R.styleable.XExposedDropdownMenu_edmDropdownSelection, 0);
        this.mAdapter = new ArrayAdapter<>(getContext(), R.layout.x_exposed_dropdown_menu_item);
        createPopupWindow();
        setEntries(textArray);
        setSelection(integer);
        setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.xui.widget.XExposedDropdownMenu.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XExposedDropdownMenu.this.show();
            }
        });
        obtainAttributes.recycle();
    }

    private void createPopupWindow() {
        this.mPopupWindow = new PopupWindow(getContext());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.x_exposed_dropdown_menu_list, (ViewGroup) null);
        this.mPopupWindow.setContentView(inflate);
        this.mListView = (ListView) inflate.findViewById(R.id.list_view);
        this.mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.mPopupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setClippingEnabled(false);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void show() {
        this.mPopupWindow.setWidth(getWidth() - (this.adf * 2));
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int height = iArr[1] + getHeight() + this.mPopupWindow.getHeight() + this.ade;
        int i = getContext().getResources().getDisplayMetrics().heightPixels;
        int i2 = this.ade;
        if (height > i) {
            i2 = ((-getHeight()) - this.mPopupWindow.getHeight()) - this.ade;
        }
        this.mPopupWindow.showAsDropDown(this, this.adf, i2);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        setSelection(i);
        String eC = eC(i);
        if (this.adc != null) {
            this.adc.i(i, eC);
        }
        if (this.mPopupWindow.isShowing()) {
            this.mPopupWindow.dismiss();
        }
    }

    public void setSelection(int i) {
        if (i < 0 || i >= this.mAdapter.getCount()) {
            return;
        }
        this.mCurrentSelection = i;
        setText(eC(i));
    }

    public int getSelection() {
        return this.mCurrentSelection;
    }

    public String eC(int i) {
        if (i >= 0 && i < this.mAdapter.getCount()) {
            return this.mAdapter.getItem(i);
        }
        return "";
    }

    public String getSelectionTitle() {
        return eC(getSelection());
    }

    public void setEntries(String[] strArr) {
        this.mAdapter.clear();
        if (strArr != null && strArr.length > 0) {
            this.mAdapter.addAll(strArr);
            setSelection(0);
        }
        setDropdownHeight(this.mAdapter.getCount());
    }

    public void setEntries(CharSequence[] charSequenceArr) {
        this.mAdapter.clear();
        if (charSequenceArr != null && charSequenceArr.length > 0) {
            for (CharSequence charSequence : charSequenceArr) {
                this.mAdapter.add(charSequence.toString());
            }
            setSelection(0);
        }
        setDropdownHeight(this.mAdapter.getCount());
    }

    private void setDropdownHeight(int i) {
        int i2;
        if (i > 5) {
            i2 = h.g(408.0f);
        } else if (i > 0) {
            i2 = h.g((i * 80) + ((i - 1) * 2));
        } else {
            i2 = 0;
        }
        this.mPopupWindow.setHeight(i2);
    }

    public void setOnItemClickListener(a aVar) {
        this.adc = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XTextView, android.widget.TextView, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (com.xiaopeng.xui.c.a.isThemeChanged(configuration)) {
            rk();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XTextView, android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        rk();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XTextView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        rk();
    }

    private void rk() {
        if (this.mListView != null) {
            this.mListView.setBackground(getContext().getDrawable(R.drawable.x_exposed_dropdown_menu_list_bg));
            this.mListView.setDivider(getContext().getDrawable(R.drawable.x_exposed_dropdown_menu_divider));
        }
    }
}
