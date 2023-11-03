package com.xiaopeng.xui.widget.timepicker;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStructure;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.core.math.MathUtils;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.d.f;
import com.xiaopeng.xui.widget.XFrameLayout;
import java.util.Locale;

/* loaded from: classes13.dex */
public class XTimePicker extends XFrameLayout {
    private static final String LOG_TAG = XTimePicker.class.getSimpleName();
    private final b akP;

    /* loaded from: classes13.dex */
    public interface a {
        void a(XTimePicker xTimePicker, int i, int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes13.dex */
    public interface b {
        void a(a aVar);

        void autofill(AutofillValue autofillValue);

        AutofillValue getAutofillValue();

        int getBaseline();

        int getHour();

        int getMinute();

        boolean isEnabled();

        void onRestoreInstanceState(Parcelable parcelable);

        Parcelable onSaveInstanceState(Parcelable parcelable);

        void setDate(@IntRange(from = 0, to = 23) int i, @IntRange(from = 0, to = 59) int i2);

        void setEnabled(boolean z);

        void setHour(@IntRange(from = 0, to = 23) int i);

        void setMinute(@IntRange(from = 0, to = 59) int i);

        void setOnTimeChangedListener(a aVar);
    }

    public XTimePicker(Context context) {
        this(context, null);
    }

    public XTimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XTimePicker(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.XTimePicker);
    }

    public XTimePicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (getImportantForAutofill() == 0) {
            setImportantForAutofill(1);
        }
        this.akP = new com.xiaopeng.xui.widget.timepicker.a(this, context, attributeSet, i, i2);
        this.akP.a(new a() { // from class: com.xiaopeng.xui.widget.timepicker.XTimePicker.1
            @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.a
            public void a(XTimePicker xTimePicker, int i3, int i4) {
                AutofillManager autofillManager = (AutofillManager) XTimePicker.this.getContext().getSystemService(AutofillManager.class);
                if (autofillManager != null) {
                    autofillManager.notifyValueChanged(XTimePicker.this);
                }
            }
        });
    }

    public void setHour(@IntRange(from = 0, to = 23) int i) {
        this.akP.setHour(MathUtils.clamp(i, 0, 23));
    }

    public int getHour() {
        return this.akP.getHour();
    }

    public void setMinute(@IntRange(from = 0, to = 59) int i) {
        this.akP.setMinute(MathUtils.clamp(i, 0, 59));
    }

    public int getMinute() {
        return this.akP.getMinute();
    }

    public void setCurrentHour(@NonNull Integer num) {
        setHour(num.intValue());
    }

    @NonNull
    public Integer getCurrentHour() {
        return Integer.valueOf(getHour());
    }

    public void setCurrentMinute(@NonNull Integer num) {
        setMinute(num.intValue());
    }

    @NonNull
    public Integer getCurrentMinute() {
        return Integer.valueOf(getMinute());
    }

    public void setOnTimeChangedListener(a aVar) {
        this.akP.setOnTimeChangedListener(aVar);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.akP.setEnabled(z);
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return this.akP.isEnabled();
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.akP.getBaseline();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        return this.akP.onSaveInstanceState(super.onSaveInstanceState());
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        View.BaseSavedState baseSavedState = (View.BaseSavedState) parcelable;
        super.onRestoreInstanceState(baseSavedState.getSuperState());
        this.akP.onRestoreInstanceState(baseSavedState);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return XTimePicker.class.getName();
    }

    /* loaded from: classes13.dex */
    static abstract class AbstractTimePickerDelegate implements b {
        private long aia;
        protected final XTimePicker akR;
        protected a akS;
        protected a akT;
        protected final Context mContext;
        protected final Locale mLocale;

        public AbstractTimePickerDelegate(@NonNull XTimePicker xTimePicker, @NonNull Context context) {
            this.akR = xTimePicker;
            this.mContext = context;
            this.mLocale = context.getResources().getConfiguration().getLocales().get(0);
        }

        @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
        public void setOnTimeChangedListener(a aVar) {
            this.akS = aVar;
        }

        @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
        public void a(a aVar) {
            this.akT = aVar;
        }

        @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
        public final void autofill(AutofillValue autofillValue) {
            if (autofillValue == null || !autofillValue.isDate()) {
                String str = XTimePicker.LOG_TAG;
                f.h(str, autofillValue + " could not be autofilled into " + this);
                return;
            }
            long dateValue = autofillValue.getDateValue();
            Calendar calendar = Calendar.getInstance(this.mLocale);
            calendar.setTimeInMillis(dateValue);
            setDate(calendar.get(11), calendar.get(12));
            this.aia = dateValue;
        }

        @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.b
        public final AutofillValue getAutofillValue() {
            if (this.aia != 0) {
                return AutofillValue.forDate(this.aia);
            }
            Calendar calendar = Calendar.getInstance(this.mLocale);
            calendar.set(11, getHour());
            calendar.set(12, getMinute());
            return AutofillValue.forDate(calendar.getTimeInMillis());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void resetAutofilledValue() {
            this.aia = 0L;
        }

        /* loaded from: classes13.dex */
        protected static class SavedState extends View.BaseSavedState {
            private final int akU;
            private final int mHour;
            private final int mMinute;

            public SavedState(Parcelable parcelable, int i, int i2) {
                this(parcelable, i, i2, 0);
            }

            public SavedState(Parcelable parcelable, int i, int i2, int i3) {
                super(parcelable);
                this.mHour = i;
                this.mMinute = i2;
                this.akU = i3;
            }

            public int getHour() {
                return this.mHour;
            }

            public int getMinute() {
                return this.mMinute;
            }

            @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                super.writeToParcel(parcel, i);
                parcel.writeInt(this.mHour);
                parcel.writeInt(this.mMinute);
                parcel.writeInt(this.akU);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        viewStructure.setAutofillId(getAutofillId());
        onProvideAutofillStructure(viewStructure, i);
    }

    @Override // android.view.View
    public void autofill(AutofillValue autofillValue) {
        if (isEnabled()) {
            this.akP.autofill(autofillValue);
        }
    }

    @Override // android.view.View
    public AutofillValue getAutofillValue() {
        if (isEnabled()) {
            return this.akP.getAutofillValue();
        }
        return null;
    }
}
