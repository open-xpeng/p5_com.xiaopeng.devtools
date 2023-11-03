package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.StringRes;
import com.xiaopeng.vui.commons.c;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.b.f;
import com.xiaopeng.xui.c.a;
import com.xiaopeng.xui.d.e;

/* loaded from: classes13.dex */
public abstract class XTextInput extends XRelativeLayout implements TextWatcher, View.OnFocusChangeListener {
    private c abP;
    private String abS;
    protected ImageButton ahC;
    protected TextView ahD;
    protected View ahE;
    protected boolean ahF;
    protected boolean ahG;
    protected int ahH;
    protected int ahI;
    protected int ahJ;
    protected int ahK;
    protected int ahL;
    protected int ahM;
    protected EditText mEditText;

    protected abstract void nN();

    public XTextInput(Context context) {
        this(context, null);
    }

    public XTextInput(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTextInput(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XTextInput(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        int i3;
        int i4;
        int i5;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XTextInput);
        CharSequence text = obtainStyledAttributes.getText(R.styleable.XTextInput_input_edit_hint);
        CharSequence text2 = obtainStyledAttributes.getText(R.styleable.XTextInput_input_edit_text);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.XTextInput_input_reset_enabled, false);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.XTextInput_input_error_enabled, false);
        if (obtainStyledAttributes.hasValue(R.styleable.XTextInput_input_edit_appearance)) {
            i3 = obtainStyledAttributes.getResourceId(R.styleable.XTextInput_input_edit_appearance, -1);
        } else {
            i3 = -1;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.XTextInput_input_edit_max_length)) {
            i4 = obtainStyledAttributes.getInt(R.styleable.XTextInput_input_edit_max_length, -1);
        } else {
            i4 = -1;
        }
        if (obtainStyledAttributes.hasValue(R.styleable.XTextInput_android_inputType)) {
            i5 = obtainStyledAttributes.getInt(R.styleable.XTextInput_android_inputType, -1);
        } else {
            i5 = -1;
        }
        this.ahH = obtainStyledAttributes.getResourceId(R.styleable.XTextInput_input_normal_color, -1);
        this.ahI = obtainStyledAttributes.getResourceId(R.styleable.XTextInput_input_focus_color, -1);
        this.ahJ = obtainStyledAttributes.getResourceId(R.styleable.XTextInput_input_error_color, -1);
        obtainStyledAttributes.recycle();
        nN();
        setResetEnable(z);
        setErrorEnable(z2);
        if (i3 > 0) {
            this.mEditText.setTextAppearance(i3);
        }
        if (i4 > 0) {
            this.mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i4)});
        }
        if (i5 > 0) {
            this.mEditText.setInputType(i5);
        }
        this.mEditText.setOnFocusChangeListener(this);
        this.mEditText.addTextChangedListener(this);
        this.ahC.setSoundEffectsEnabled(false);
        e.j(this.ahC);
        this.ahC.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.xui.widget.XTextInput.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XTextInput.this.ahC.setVisibility(8);
                XTextInput.this.mEditText.setText((CharSequence) null);
                XTextInput.this.mEditText.requestFocus();
                f.qN().play(5);
                if (XTextInput.this.abP != null && !TextUtils.isEmpty(XTextInput.this.abS)) {
                    XTextInput.this.abP.b(XTextInput.this.abS, XTextInput.this.ahC);
                }
            }
        });
        setEditHint(text);
        setEditContent(text2);
        ry();
        updateEditTextBackground();
    }

    public void setMaxLength(int i) {
        this.mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
    }

    public void setInputType(int i) {
        this.mEditText.setInputType(i);
    }

    public String getText() {
        return this.mEditText.getText().toString();
    }

    public void setEditHint(CharSequence charSequence) {
        this.mEditText.setHint(charSequence);
    }

    public void setEditHint(@StringRes int i) {
        this.mEditText.setHint(i);
    }

    public void setEditContent(CharSequence charSequence) {
        this.mEditText.setText(charSequence);
    }

    public void setEditContent(@StringRes int i) {
        this.mEditText.setText(i);
    }

    public EditText getEditText() {
        return this.mEditText;
    }

    public void setResetEnable(boolean z) {
        if (this.ahF != z) {
            this.ahF = z;
            this.ahC.setVisibility((!z || this.mEditText.getText().toString().length() <= 0) ? 8 : 0);
        }
    }

    public boolean rI() {
        return this.ahG;
    }

    public void setErrorEnable(boolean z) {
        if (this.ahG != z) {
            this.ahG = z;
            this.ahD.setVisibility(z ? 0 : 8);
            updateEditTextBackground();
        }
    }

    public void setErrorMsg(CharSequence charSequence) {
        if (!rI()) {
            if (TextUtils.isEmpty(charSequence)) {
                return;
            }
            setErrorEnable(true);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            showError(charSequence);
        } else {
            hideError();
        }
        updateEditTextBackground();
    }

    private void showError(CharSequence charSequence) {
        this.ahD.setText(charSequence);
    }

    private void hideError() {
        this.ahD.setText((CharSequence) null);
    }

    private void ry() {
        this.ahK = getResources().getColor(this.ahH, getContext().getTheme());
        this.ahL = getResources().getColor(this.ahI, getContext().getTheme());
        this.ahM = getResources().getColor(this.ahJ, getContext().getTheme());
    }

    void updateEditTextBackground() {
        Drawable background = this.ahE.getBackground();
        if (background == null) {
            return;
        }
        if (rJ()) {
            background.mutate().setTint(this.ahM);
        } else if (this.mEditText.isFocused()) {
            background.mutate().setTint(this.ahL);
        } else {
            background.mutate().setTint(this.ahK);
        }
    }

    private boolean rJ() {
        return rI() && !TextUtils.isEmpty(this.ahD.getText().toString());
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        b(z, true);
    }

    public void b(boolean z, boolean z2) {
        super.setEnabled(z);
        if (z2) {
            a(this, z);
        }
    }

    private void a(ViewGroup viewGroup, boolean z) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, z);
            }
            childAt.setEnabled(z);
        }
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.ahF) {
            return;
        }
        if (charSequence.length() > 0) {
            if (this.ahC.getVisibility() == 0) {
                return;
            }
            this.ahC.setVisibility(0);
            if (this.abP != null && !TextUtils.isEmpty(this.abS)) {
                this.abP.b(this.abS, this.ahC);
            }
        } else if (this.ahC.getVisibility() == 8) {
        } else {
            this.ahC.setVisibility(8);
            if (this.abP != null && !TextUtils.isEmpty(this.abS)) {
                this.abP.b(this.abS, this.ahC);
            }
        }
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View view, boolean z) {
        if (view == this.mEditText) {
            updateEditTextBackground();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XRelativeLayout, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (a.isThemeChanged(configuration)) {
            ry();
            updateEditTextBackground();
        }
    }
}
