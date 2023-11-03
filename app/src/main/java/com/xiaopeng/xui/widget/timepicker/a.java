package com.xiaopeng.xui.widget.timepicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.icu.util.Calendar;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XNumberPicker;
import com.xiaopeng.xui.widget.timepicker.XTimePicker;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: XTimePickerSpinnerDelegate.java */
/* loaded from: classes13.dex */
public class a extends XTimePicker.AbstractTimePickerDelegate {
    private final XNumberPicker akV;
    private final XNumberPicker akW;
    private final Calendar akX;
    private boolean mIsEnabled;

    public a(XTimePicker xTimePicker, Context context, AttributeSet attributeSet, int i, int i2) {
        super(xTimePicker, context);
        this.mIsEnabled = true;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.XTimePicker, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XTimePicker_tp_xTimePickerLayout, R.layout.x_time_picker_layout);
        obtainStyledAttributes.recycle();
        LayoutInflater.from(this.mContext).inflate(resourceId, (ViewGroup) this.akR, true).setSaveFromParentEnabled(false);
        this.akV = (XNumberPicker) xTimePicker.findViewById(R.id.hour);
        this.akV.setMinValue(0);
        this.akV.setMaxValue(23);
        this.akV.setOnLongPressUpdateInterval(100L);
        String[] strArr = new String[24];
        for (int i3 = 0; i3 < strArr.length; i3++) {
            strArr[i3] = context.getResources().getString(R.string.x_time_picker_hour, Integer.valueOf(i3));
        }
        this.akV.setDisplayedValues(strArr);
        this.akV.setOnValueChangedListener(new XNumberPicker.e() { // from class: com.xiaopeng.xui.widget.timepicker.a.1
            @Override // com.xiaopeng.xui.widget.XNumberPicker.e
            public void onValueChange(XNumberPicker xNumberPicker, int i4, int i5) {
                a.this.sf();
            }
        });
        this.akW = (XNumberPicker) this.akR.findViewById(R.id.minute);
        this.akW.setMinValue(0);
        this.akW.setMaxValue(59);
        this.akW.setOnLongPressUpdateInterval(100L);
        this.akW.setFormatter(XNumberPicker.getTwoDigitFormatter());
        String[] strArr2 = new String[60];
        for (int i4 = 0; i4 < strArr2.length; i4++) {
            strArr2[i4] = context.getResources().getString(R.string.x_time_picker_minute, Integer.valueOf(i4));
        }
        this.akW.setDisplayedValues(strArr2);
        this.akW.setOnValueChangedListener(new XNumberPicker.e() { // from class: com.xiaopeng.xui.widget.timepicker.a.2
            @Override // com.xiaopeng.xui.widget.XNumberPicker.e
            public void onValueChange(XNumberPicker xNumberPicker, int i5, int i6) {
                int minValue = a.this.akW.getMinValue();
                int maxValue = a.this.akW.getMaxValue();
                if (i5 == maxValue && i6 == minValue) {
                    a.this.akV.setValue(a.this.akV.getValue() + 1);
                } else if (i5 == minValue && i6 == maxValue) {
                    a.this.akV.setValue(a.this.akV.getValue() - 1);
                }
                a.this.sf();
            }
        });
        this.akX = Calendar.getInstance(this.mLocale);
        setHour(this.akX.get(11));
        setMinute(this.akX.get(12));
        if (!isEnabled()) {
            setEnabled(false);
        }
        if (this.akR.getImportantForAccessibility() == 0) {
            this.akR.setImportantForAccessibility(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sf() {
        this.akR.sendAccessibilityEvent(4);
        if (this.akS != null) {
            this.akS.a(this.akR, getHour(), getMinute());
        }
        if (this.akT != null) {
            this.akT.a(this.akR, getHour(), getMinute());
        }
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public void setHour(int i) {
        i(i, true);
    }

    private void i(int i, boolean z) {
        if (i == getHour()) {
            return;
        }
        resetAutofilledValue();
        this.akV.setValue(i);
        if (z) {
            sf();
        }
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public int getHour() {
        return this.akV.getValue();
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public void setMinute(int i) {
        j(i, true);
    }

    private void j(int i, boolean z) {
        if (i == getMinute()) {
            return;
        }
        resetAutofilledValue();
        this.akW.setValue(i);
        if (z) {
            sf();
        }
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public int getMinute() {
        return this.akW.getValue();
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public void setDate(int i, int i2) {
        i(i, false);
        j(i2, false);
        sf();
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public void setEnabled(boolean z) {
        this.akW.setEnabled(z);
        this.akV.setEnabled(z);
        this.mIsEnabled = z;
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public int getBaseline() {
        return this.akV.getBaseline();
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public Parcelable onSaveInstanceState(Parcelable parcelable) {
        return new XTimePicker.AbstractTimePickerDelegate.SavedState(parcelable, getHour(), getMinute());
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof XTimePicker.AbstractTimePickerDelegate.SavedState) {
            XTimePicker.AbstractTimePickerDelegate.SavedState savedState = (XTimePicker.AbstractTimePickerDelegate.SavedState) parcelable;
            setHour(savedState.getHour());
            setMinute(savedState.getMinute());
        }
    }
}
