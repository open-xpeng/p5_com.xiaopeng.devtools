package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.xiaopeng.vui.commons.b;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.e;

/* loaded from: classes13.dex */
public class XTextFields extends XTextInput implements View.OnClickListener, b {
    private boolean ahA;
    private a ahB;
    private ImageButton ahy;
    private boolean ahz;
    private Context mContext;

    /* loaded from: classes13.dex */
    public interface a {
        void rH();
    }

    public XTextFields(Context context) {
        this(context, null);
    }

    public XTextFields(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTextFields(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public XTextFields(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XTextFields);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.XTextFields_text_fields_password_enabled, false);
        obtainStyledAttributes.recycle();
        setPasswordEnable(z);
        this.mContext = context;
    }

    @Override // com.xiaopeng.xui.widget.XTextInput
    protected void nN() {
        LayoutInflater.from(getContext()).inflate(R.layout.x_text_fields, this);
        this.ahC = (ImageButton) findViewById(R.id.x_text_fields_reset);
        this.ahy = (ImageButton) findViewById(R.id.x_text_fields_pass);
        this.mEditText = (EditText) findViewById(R.id.x_text_fields_edit);
        this.ahD = (TextView) findViewById(R.id.x_text_fields_error);
        this.ahE = findViewById(R.id.x_text_fields_line);
        this.ahy.setOnClickListener(this);
        e.j(this.ahy);
    }

    public void setPasswordEnable(boolean z) {
        if (this.ahz != z) {
            this.ahz = z;
            int selectionEnd = this.mEditText.getSelectionEnd();
            this.ahy.setVisibility(z ? 0 : 8);
            if (z) {
                this.ahA = true;
                this.mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                this.mEditText.setTransformationMethod(null);
                this.ahA = false;
            }
            this.ahy.setSelected(this.ahA);
            this.mEditText.setSelection(selectionEnd);
        }
    }

    public void rG() {
        if (this.ahz) {
            int selectionEnd = this.mEditText.getSelectionEnd();
            if (hasPasswordTransformation()) {
                this.mEditText.setTransformationMethod(null);
                this.ahA = false;
            } else {
                this.mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.ahA = true;
            }
            this.ahy.setSelected(this.ahA);
            this.mEditText.setSelection(selectionEnd);
            if (com.xiaopeng.xui.a.qn() && this.ahB != null) {
                this.ahB.rH();
            }
        }
    }

    private boolean hasPasswordTransformation() {
        return this.mEditText != null && (this.mEditText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.x_text_fields_pass) {
            rG();
        }
    }

    public void setCheckStateChangeListener(a aVar) {
        this.ahB = aVar;
    }
}
