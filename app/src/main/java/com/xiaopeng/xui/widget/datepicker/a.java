package com.xiaopeng.xui.widget.datepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.icu.util.Calendar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.xiaopeng.commonfunc.bean.factorytest.TestResultItem;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XNumberPicker;
import com.xiaopeng.xui.widget.datepicker.XDatePicker;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: XDatePickerSpinnerDelegate.java */
/* loaded from: classes13.dex */
public class a extends XDatePicker.AbstractXDatePickerDelegate {
    private final LinearLayout aij;
    private XNumberPicker aik;
    private XNumberPicker ail;
    private XNumberPicker aim;
    private String[] ain;
    private final DateFormat aio;
    private int aip;
    private Calendar aiq;
    private Calendar air;
    private Calendar ais;
    private boolean mIsEnabled;

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.AbstractXDatePickerDelegate, com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public /* bridge */ /* synthetic */ void a(XDatePicker.a aVar) {
        super.a(aVar);
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.AbstractXDatePickerDelegate, com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public /* bridge */ /* synthetic */ void setOnDateChangedListener(XDatePicker.a aVar) {
        super.setOnDateChangedListener(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(XDatePicker xDatePicker, Context context, AttributeSet attributeSet, int i, int i2) {
        super(xDatePicker, context);
        this.aio = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        this.mIsEnabled = true;
        this.ahX = xDatePicker;
        this.mContext = context;
        setCurrentLocale(Locale.getDefault());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XDatePicker, i, i2);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.XDatePicker_dp_spinnersShown, true);
        int i3 = obtainStyledAttributes.getInt(R.styleable.XDatePicker_dp_startYear, 1900);
        int i4 = obtainStyledAttributes.getInt(R.styleable.XDatePicker_dp_endYear, 2100);
        String string = obtainStyledAttributes.getString(R.styleable.XDatePicker_dp_minDate);
        String string2 = obtainStyledAttributes.getString(R.styleable.XDatePicker_dp_maxDate);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XDatePicker_dp_xDatePickerLayout, R.layout.x_date_picker_layout);
        obtainStyledAttributes.recycle();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(resourceId, (ViewGroup) this.ahX, true).setSaveFromParentEnabled(false);
        XNumberPicker.e eVar = new XNumberPicker.e() { // from class: com.xiaopeng.xui.widget.datepicker.-$$Lambda$a$uYrKZL0amnnCL76jeMgIJHaOkHI
            @Override // com.xiaopeng.xui.widget.XNumberPicker.e
            public final void onValueChange(XNumberPicker xNumberPicker, int i5, int i6) {
                a.this.a(xNumberPicker, i5, i6);
            }
        };
        this.aij = (LinearLayout) this.ahX.findViewById(R.id.pickers);
        a(new XNumberPicker[]{(XNumberPicker) this.ahX.findViewById(R.id.picker1), (XNumberPicker) this.ahX.findViewById(R.id.picker2), (XNumberPicker) this.ahX.findViewById(R.id.picker3)});
        this.aik.setFormatter(XNumberPicker.getTwoDigitFormatter());
        this.aik.setOnLongPressUpdateInterval(100L);
        this.aik.setOnValueChangedListener(eVar);
        this.ail.setMinValue(0);
        this.ail.setMaxValue(this.aip - 1);
        this.ail.setDisplayedValues(this.ain);
        this.ail.setOnLongPressUpdateInterval(200L);
        this.ail.setOnValueChangedListener(eVar);
        this.aim.setOnLongPressUpdateInterval(100L);
        this.aim.setOnValueChangedListener(eVar);
        setSpinnersShown(z);
        this.aiq.clear();
        if (TextUtils.isEmpty(string)) {
            this.aiq.set(i3, 0, 1);
        } else if (!parseDate(string, this.aiq)) {
            this.aiq.set(i3, 0, 1);
        }
        setMinDate(this.aiq.getTimeInMillis());
        this.aiq.clear();
        if (!TextUtils.isEmpty(string2)) {
            if (!parseDate(string2, this.aiq)) {
                this.aiq.set(i4, 11, 31);
            }
        } else {
            this.aiq.set(i4, 11, 31);
        }
        setMaxDate(this.aiq.getTimeInMillis());
        this.mCurrentDate.setTimeInMillis(System.currentTimeMillis());
        a(this.mCurrentDate.get(1), this.mCurrentDate.get(2), this.mCurrentDate.get(5), null);
        if (this.ahX.getImportantForAccessibility() == 0) {
            this.ahX.setImportantForAccessibility(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(XNumberPicker xNumberPicker, int i, int i2) {
        this.aiq.setTimeInMillis(this.mCurrentDate.getTimeInMillis());
        if (xNumberPicker == this.aik) {
            int actualMaximum = this.aiq.getActualMaximum(5);
            if (i == actualMaximum && i2 == 1) {
                this.aiq.add(5, 1);
            } else if (i == 1 && i2 == actualMaximum) {
                this.aiq.add(5, -1);
            } else {
                this.aiq.add(5, i2 - i);
            }
        } else if (xNumberPicker == this.ail) {
            if (i == 11 && i2 == 0) {
                this.aiq.add(2, 1);
            } else if (i == 0 && i2 == 11) {
                this.aiq.add(2, -1);
            } else {
                this.aiq.add(2, i2 - i);
            }
        } else if (xNumberPicker == this.aim) {
            this.aiq.set(1, i2);
        } else {
            throw new IllegalArgumentException();
        }
        f(this.aiq.get(1), this.aiq.get(2), this.aiq.get(5));
        rN();
        rM();
    }

    private void rM() {
        this.ahX.sendAccessibilityEvent(4);
        if (this.ahY != null) {
            this.ahY.a(this.ahX, getYear(), getMonth(), getDayOfMonth());
        }
        if (this.ahZ != null) {
            this.ahZ.a(this.ahX, getYear(), getMonth(), getDayOfMonth());
        }
    }

    private void a(XNumberPicker[] xNumberPickerArr) {
        char[] dateFormatOrder = getDateFormatOrder(android.text.format.DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMMMdd"));
        for (int i = 0; i < dateFormatOrder.length; i++) {
            char c = dateFormatOrder[i];
            if (c == 'M') {
                this.ail = xNumberPickerArr[i];
            } else if (c == 'd') {
                this.aik = xNumberPickerArr[i];
            } else if (c == 'y') {
                this.aim = xNumberPickerArr[i];
            } else {
                throw new IllegalArgumentException(Arrays.toString(dateFormatOrder));
            }
        }
    }

    public static char[] getDateFormatOrder(String str) {
        char[] cArr = new char[3];
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt == 'd' || charAt == 'L' || charAt == 'M' || charAt == 'y') {
                if (charAt == 'd' && !z) {
                    cArr[i] = 'd';
                    i++;
                    z = true;
                } else if ((charAt == 'L' || charAt == 'M') && !z2) {
                    cArr[i] = 'M';
                    i++;
                    z2 = true;
                } else if (charAt == 'y' && !z3) {
                    cArr[i] = 'y';
                    i++;
                    z3 = true;
                }
            }
        }
        return cArr;
    }

    private void f(int i, int i2, int i3) {
        this.mCurrentDate.set(i, i2, i3);
        resetAutofilledValue();
        if (this.mCurrentDate.before(this.air)) {
            this.mCurrentDate.setTimeInMillis(this.air.getTimeInMillis());
        } else if (this.mCurrentDate.after(this.ais)) {
            this.mCurrentDate.setTimeInMillis(this.ais.getTimeInMillis());
        }
    }

    private boolean parseDate(String str, Calendar calendar) {
        try {
            calendar.setTime(this.aio.parse(str));
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void rN() {
        if (this.mCurrentDate.equals(this.air)) {
            this.aik.setMinValue(this.mCurrentDate.get(5));
            this.aik.setMaxValue(this.mCurrentDate.getActualMaximum(5));
            this.aik.setWrapSelectorWheel(false);
            this.ail.setDisplayedValues(null);
            this.ail.setMinValue(this.mCurrentDate.get(2));
            this.ail.setMaxValue(this.mCurrentDate.getActualMaximum(2));
            this.ail.setWrapSelectorWheel(false);
        } else if (this.mCurrentDate.equals(this.ais)) {
            this.aik.setMinValue(this.mCurrentDate.getActualMinimum(5));
            this.aik.setMaxValue(this.mCurrentDate.get(5));
            this.aik.setWrapSelectorWheel(false);
            this.ail.setDisplayedValues(null);
            this.ail.setMinValue(this.mCurrentDate.getActualMinimum(2));
            this.ail.setMaxValue(this.mCurrentDate.get(2));
            this.ail.setWrapSelectorWheel(false);
        } else {
            this.aik.setMinValue(1);
            this.aik.setMaxValue(this.mCurrentDate.getActualMaximum(5));
            this.aik.setWrapSelectorWheel(true);
            this.ail.setDisplayedValues(null);
            this.ail.setMinValue(0);
            this.ail.setMaxValue(11);
            this.ail.setWrapSelectorWheel(true);
        }
        String[] strArr = new String[this.mCurrentDate.getActualMaximum(5)];
        int i = 0;
        while (i < this.mCurrentDate.getActualMaximum(5)) {
            int i2 = 1 + i;
            strArr[i] = this.mContext.getResources().getString(R.string.x_date_picker_day, Integer.valueOf(i2));
            i = i2;
        }
        this.aik.setDisplayedValues(strArr);
        this.ail.setDisplayedValues((String[]) Arrays.copyOfRange(this.ain, this.ail.getMinValue(), this.ail.getMaxValue() + 1));
        this.aim.setMinValue(this.air.get(1));
        this.aim.setMaxValue(this.ais.get(1));
        String[] strArr2 = new String[TestResultItem.INDEX_BURNTEST_4G];
        for (int i3 = 0; i3 < strArr2.length; i3++) {
            strArr2[i3] = this.mContext.getResources().getString(R.string.x_date_picker_year, Integer.valueOf(this.aim.getMinValue() + i3));
        }
        this.aim.setDisplayedValues(strArr2);
        this.aim.setWrapSelectorWheel(false);
        this.aim.setValue(this.mCurrentDate.get(1));
        this.ail.setValue(this.mCurrentDate.get(2));
        this.aik.setValue(this.mCurrentDate.get(5));
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public void a(int i, int i2, int i3, XDatePicker.a aVar) {
        f(i, i2, i3);
        rN();
        this.ahY = aVar;
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public int getYear() {
        return this.mCurrentDate.get(1);
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public int getMonth() {
        return this.mCurrentDate.get(2);
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public int getDayOfMonth() {
        return this.mCurrentDate.get(5);
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public void setMinDate(long j) {
        this.aiq.setTimeInMillis(j);
        if (this.aiq.get(1) == this.air.get(1) && this.aiq.get(6) == this.air.get(6)) {
            return;
        }
        this.air.setTimeInMillis(j);
        if (this.mCurrentDate.before(this.air)) {
            this.mCurrentDate.setTimeInMillis(this.air.getTimeInMillis());
        }
        rN();
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public Calendar getMinDate() {
        if (this.air != null) {
            return this.air;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900, 1, 1);
        return calendar;
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public void setMaxDate(long j) {
        this.aiq.setTimeInMillis(j);
        if (this.aiq.get(1) == this.ais.get(1) && this.aiq.get(6) == this.ais.get(6)) {
            return;
        }
        this.ais.setTimeInMillis(j);
        if (this.mCurrentDate.after(this.ais)) {
            this.mCurrentDate.setTimeInMillis(this.ais.getTimeInMillis());
        }
        rN();
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public Calendar getMaxDate() {
        if (this.ais != null) {
            return this.air;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(2100, 11, 30);
        return calendar;
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public void setEnabled(boolean z) {
        this.aik.setEnabled(z);
        this.ail.setEnabled(z);
        this.aim.setEnabled(z);
        this.mIsEnabled = z;
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public void setSpinnersShown(boolean z) {
        this.aij.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.AbstractXDatePickerDelegate
    public void setCurrentLocale(Locale locale) {
        super.setCurrentLocale(locale);
        this.aiq = a(this.aiq, locale);
        this.air = a(this.air, locale);
        this.ais = a(this.ais, locale);
        this.mCurrentDate = a(this.mCurrentDate, locale);
        this.aip = this.aiq.getActualMaximum(2) + 1;
        this.ain = new DateFormatSymbols().getShortMonths();
    }

    private Calendar a(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance(locale);
        calendar2.setTimeInMillis(timeInMillis);
        return calendar2;
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
    public void onConfigurationChanged(Configuration configuration) {
        setCurrentLocale(configuration.getLocales().get(0));
    }
}
