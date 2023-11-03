package com.xiaopeng.xui.widget.datepicker;

import android.content.Context;
import android.content.res.Configuration;
import android.icu.util.Calendar;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.autofill.AutofillManager;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.widget.XFrameLayout;
import java.util.Locale;

/* loaded from: classes13.dex */
public class XDatePicker extends XFrameLayout {
    private static final String LOG_TAG = XDatePicker.class.getSimpleName();
    private final b ahV;

    /* loaded from: classes13.dex */
    public interface a {
        void a(XDatePicker xDatePicker, int i, int i2, int i3);
    }

    /* loaded from: classes13.dex */
    interface b {
        void a(int i, int i2, int i3, a aVar);

        void a(a aVar);

        int getDayOfMonth();

        Calendar getMaxDate();

        Calendar getMinDate();

        int getMonth();

        int getYear();

        boolean isEnabled();

        void onConfigurationChanged(Configuration configuration);

        void setEnabled(boolean z);

        void setMaxDate(long j);

        void setMinDate(long j);

        void setOnDateChangedListener(a aVar);
    }

    public XDatePicker(Context context) {
        this(context, null);
    }

    public XDatePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XDatePicker(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XDatePicker);
    }

    public XDatePicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (getImportantForAutofill() == 0) {
            setImportantForAutofill(1);
        }
        this.ahV = b(context, attributeSet, i, i2);
        this.ahV.a(new a() { // from class: com.xiaopeng.xui.widget.datepicker.XDatePicker.1
            @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.a
            public void a(XDatePicker xDatePicker, int i3, int i4, int i5) {
                AutofillManager autofillManager = (AutofillManager) XDatePicker.this.getContext().getSystemService(AutofillManager.class);
                if (autofillManager != null) {
                    autofillManager.notifyValueChanged(XDatePicker.this);
                }
            }
        });
    }

    private b b(Context context, AttributeSet attributeSet, int i, int i2) {
        return new com.xiaopeng.xui.widget.datepicker.a(this, context, attributeSet, i, i2);
    }

    public void a(int i, int i2, int i3, a aVar) {
        this.ahV.a(i, i2, i3, aVar);
    }

    public void setOnDateChangedListener(a aVar) {
        this.ahV.setOnDateChangedListener(aVar);
    }

    public int getYear() {
        return this.ahV.getYear();
    }

    public int getMonth() {
        return this.ahV.getMonth();
    }

    public int getDayOfMonth() {
        return this.ahV.getDayOfMonth();
    }

    public long getMinDate() {
        return this.ahV.getMinDate().getTimeInMillis();
    }

    public void setMinDate(long j) {
        this.ahV.setMinDate(j);
    }

    public long getMaxDate() {
        return this.ahV.getMaxDate().getTimeInMillis();
    }

    public void setMaxDate(long j) {
        this.ahV.setMaxDate(j);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        if (this.ahV.isEnabled() == z) {
            return;
        }
        super.setEnabled(z);
        this.ahV.setEnabled(z);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.ahV.isEnabled();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return XDatePicker.class.getName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.xui.widget.XFrameLayout, android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.ahV.onConfigurationChanged(configuration);
    }

    /* loaded from: classes13.dex */
    static abstract class AbstractXDatePickerDelegate implements b {
        protected XDatePicker ahX;
        protected a ahY;
        protected a ahZ;
        private long aia;
        protected Context mContext;
        protected Calendar mCurrentDate;
        protected Locale mCurrentLocale;

        public AbstractXDatePickerDelegate(XDatePicker xDatePicker, Context context) {
            this.ahX = xDatePicker;
            this.mContext = context;
            setCurrentLocale(Locale.getDefault());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setCurrentLocale(Locale locale) {
            if (!locale.equals(this.mCurrentLocale)) {
                this.mCurrentLocale = locale;
                onLocaleChanged(locale);
            }
        }

        @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
        public void setOnDateChangedListener(a aVar) {
            this.ahY = aVar;
        }

        @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.b
        public void a(a aVar) {
            this.ahZ = aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void resetAutofilledValue() {
            this.aia = 0L;
        }

        protected void onLocaleChanged(Locale locale) {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes13.dex */
        public static class SavedState extends View.BaseSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.xiaopeng.xui.widget.datepicker.XDatePicker.AbstractXDatePickerDelegate.SavedState.1
                @Override // android.os.Parcelable.Creator
                /* renamed from: aD */
                public SavedState createFromParcel(Parcel parcel) {
                    return new SavedState(parcel);
                }

                @Override // android.os.Parcelable.Creator
                /* renamed from: eO */
                public SavedState[] newArray(int i) {
                    return new SavedState[i];
                }
            };
            private final int aib;
            private final int aic;
            private final int aid;
            private final long aie;
            private final long aif;
            private final int aig;
            private final int aih;
            private final int aii;

            private SavedState(Parcel parcel) {
                super(parcel);
                this.aib = parcel.readInt();
                this.aic = parcel.readInt();
                this.aid = parcel.readInt();
                this.aie = parcel.readLong();
                this.aif = parcel.readLong();
                this.aig = parcel.readInt();
                this.aih = parcel.readInt();
                this.aii = parcel.readInt();
            }

            @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeInt(this.aib);
                parcel.writeInt(this.aic);
                parcel.writeInt(this.aid);
                parcel.writeLong(this.aie);
                parcel.writeLong(this.aif);
                parcel.writeInt(this.aig);
                parcel.writeInt(this.aih);
                parcel.writeInt(this.aii);
            }
        }
    }
}
