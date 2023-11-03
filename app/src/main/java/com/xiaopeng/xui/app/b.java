package com.xiaopeng.xui.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.widget.datepicker.XDatePicker;
import java.util.Calendar;

/* compiled from: XDatePickerDialog.java */
/* loaded from: classes13.dex */
public class b extends c implements d.a, XDatePicker.a {
    private final XDatePicker ZK;
    private a ZL;

    /* compiled from: XDatePickerDialog.java */
    /* loaded from: classes13.dex */
    public interface a {
        void onDateSet(XDatePicker xDatePicker, int i, int i2, int i3);
    }

    public b(@NonNull Context context, @Nullable a aVar, int i, int i2, int i3) {
        this(context, R.style.XDialogView_Large, aVar, null, i, i2, i3);
    }

    private b(@NonNull Context context, @StyleRes int i, @Nullable a aVar, @Nullable Calendar calendar, int i2, int i3, int i4) {
        super(context, resolveDialogTheme(context, i));
        View inflate = LayoutInflater.from(context).inflate(R.layout.x_date_picker, qu(), false);
        d(inflate, false);
        a(" ", this);
        b(" ", this);
        if (calendar != null) {
            i2 = calendar.get(1);
            i3 = calendar.get(2);
            i4 = calendar.get(5);
        }
        this.ZK = (XDatePicker) inflate.findViewById(R.id.x_date_picker);
        this.ZK.a(i2, i3, i4, this);
        this.ZL = aVar;
    }

    @Override // com.xiaopeng.xui.app.c
    @Deprecated
    public c a(@Nullable CharSequence charSequence, d.a aVar) {
        return super.a(charSequence, this);
    }

    @Override // com.xiaopeng.xui.app.c
    @Deprecated
    public c b(@Nullable CharSequence charSequence, d.a aVar) {
        return super.b(charSequence, this);
    }

    @StyleRes
    private static int resolveDialogTheme(@NonNull Context context, @StyleRes int i) {
        if (i == 0) {
            return R.style.XDialogView_Large;
        }
        return i;
    }

    @Override // com.xiaopeng.xui.widget.datepicker.XDatePicker.a
    public void a(XDatePicker xDatePicker, int i, int i2, int i3) {
        this.ZK.a(i, i2, i3, this);
    }

    @Override // com.xiaopeng.xui.app.d.a
    public void onClick(c cVar, int i) {
        switch (i) {
            case -2:
                super.getDialog().cancel();
                return;
            case -1:
                if (this.ZL != null) {
                    this.ZK.clearFocus();
                    this.ZL.onDateSet(this.ZK, this.ZK.getYear(), this.ZK.getMonth(), this.ZK.getDayOfMonth());
                    return;
                }
                return;
            default:
                return;
        }
    }
}
